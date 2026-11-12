# Keycloak with Spring Boot API and SPA

How to protect a Spring Boot REST API and a browser application (SPA) served by the same Spring Boot application
with Keycloak and JWT access tokens. Generic guide: names below are the template defaults, replace them with your own.

| Thing                     | Example value used here       |
|---------------------------|-------------------------------|
| Keycloak base URL         | `https://example.com/iam` or `https://iam.example.com` |
| Realm                     | `example`                     |
| API (resource server)     | `springboot-start-app-api`    |
| Browser application (SPA) | `springboot-start-app-spa`    |
| Machine client (M2M)      | `springboot-start-app-m2m`    |
| Client scope of the API   | `calculator`                  |
| API roles                 | `calculator-user`, `calculator-admin` |

## 1. How it works

```
 Browser (Vue SPA, served by Spring Boot)          Other service (machine to machine)
   |  1. login: authorization code + PKCE              |  1. client credentials grant
   v                                                   v      (client id + secret)
 Keycloak  ------------- access token (JWT) ---------------------->
   |                                                   |
   |  2. Authorization: Bearer <JWT>                   |  2. Authorization: Bearer <JWT>
   v                                                   v
 Spring Boot API (OAuth2 resource server)
   3. checks token signature (Keycloak public keys, JWKS), iss, aud, exp
   4. maps scopes and roles to Spring Security authorities, checks endpoint rules
```

* **Keycloak** is the identity provider and authorization server: users log in there, it issues tokens.
* **Spring Boot** is a *resource server*: it never sees passwords, it only validates tokens. Validation is local
  (signature with the cached Keycloak public keys), Keycloak is not called on every request.
* **SPA** is a *public client*: it runs in the browser, so it can not keep a secret. It uses the authorization code
  flow with PKCE.
* **Machine to machine (M2M)** clients are *confidential clients*: they run on a server, keep a secret and use the
  client credentials grant. There is no user.

For the API both cases are the same: a request with a valid JWT. The difference is only in Keycloak (client type,
flow, where roles are assigned) and in the token content, see [chapter 8](#8-spa-and-machine-to-machine-differences).

### Terms

* **Realm**: an isolated space of users, clients, roles. Keep application users in their own realm, never in
  `master` (`master` is for Keycloak administration only).
* **Client**: an application known to Keycloak (SPA, API, M2M service).
* **Realm role / client role**: realm roles are realm-wide, client roles belong to one client. API permissions are
  best kept as client roles of the API client, so every application has its own role namespace.
* **Client scope**: a named set of claims and mappers. Its name goes into the token `scope` claim.
* **Issuer (`iss`)**: `<Keycloak base URL>/realms/<realm>`.
* **Audience (`aud`)**: for whom the token is meant. The API accepts only tokens with its own client ID in `aud`.

## 2. URLs: base URL and realm

The Keycloak base URL does **not** contain the realm. The realm is added as `/realms/<realm>`:

| What                  | URL                                                                     |
|-----------------------|-------------------------------------------------------------------------|
| Base URL (`SMI_IAM_URL`) | `https://example.com/iam`                                           |
| Issuer                | `https://example.com/iam/realms/example`                                |
| OIDC discovery        | `https://example.com/iam/realms/example/.well-known/openid-configuration` |
| Public keys (JWKS)    | `https://example.com/iam/realms/example/protocol/openid-connect/certs`  |
| Token endpoint        | `https://example.com/iam/realms/example/protocol/openid-connect/token`  |
| Admin console         | `https://example.com/iam/admin/`                                        |

With Keycloak on its own host (`https://iam.example.com`) the issuer is `https://iam.example.com/realms/example`.

The issuer must be exactly the same everywhere: the URL browsers use to reach Keycloak decides the `iss` claim, and
the API compares it character by character.

## 3. Environments

The application is configured only with environment variables (chapter 9), so `application.yaml` is the same in
every environment. Typical setup:

| Environment | Keycloak                  | Realm     |
|-------------|---------------------------|-----------|
| local       | shared non-live Keycloak, or local docker compose Keycloak | `example` |
| dev         | shared non-live Keycloak  | `example` |
| test        | shared non-live Keycloak  | `example` |
| prelive     | shared non-live Keycloak  | `example` |
| live        | separate Keycloak (or at least a separate realm) | `example` |

When several environments share one realm, they share users and roles. Two ways to register the SPA:

1. **One SPA client for all non-live environments** (simple): list every environment URL in *Valid redirect URIs*.
2. **One SPA client per environment** (stricter): `springboot-start-app-spa-dev`, `...-test`, and set
   `SMI_IAM_SPA_CLIENT_ID` per environment.

`http://localhost:8080/*` redirect URIs are fine in a non-live realm for developers, never in live.

## 4. Keycloak behind a reverse proxy on a path (/iam)

Keycloak options (environment variables of the Keycloak server):

```shell
KC_HTTP_RELATIVE_PATH=/iam            # Keycloak serves everything under /iam
KC_HOSTNAME=https://example.com/iam   # public URL, used for the issuer and redirects
KC_PROXY_HEADERS=xforwarded           # trust X-Forwarded-* from the proxy
KC_HTTP_ENABLED=true                  # proxy talks plain HTTP to Keycloak
```

Nginx:

```nginx
location /iam/ {
    proxy_pass http://127.0.0.1:8080/iam/;
    proxy_set_header Host              $host;
    proxy_set_header X-Forwarded-For   $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_set_header X-Forwarded-Host  $host;
    proxy_set_header X-Forwarded-Port  $server_port;
    proxy_buffer_size 128k;           # Keycloak responses have large headers
    proxy_buffers 4 256k;
}
```

Do not expose `/iam/admin/` publicly in live if you can avoid it (allow it only from admin networks).

## 5. Keycloak configuration, step by step

Admin console: `<base URL>/admin/`, log in to the `master` realm as administrator. The same steps are automated in
[`src/main/keycloak/keycloak-setup.sh`](../../../src/main/keycloak/keycloak-setup.sh) (chapter 6).

### 5.1 Realm

1. Realm dropdown (top left) → *Create realm*.
2. *Realm name*: `example`. *Enabled*: on. *Create*.
3. *Realm settings → General → Require SSL*: `external requests` (default) or `all requests` in live.
4. Optional: *Realm settings → Sessions / Tokens*: access token lifespan (default 5 minutes is good: short-lived
   tokens, the SPA refreshes them), SSO session idle and max.
5. Optional, recommended in live: *Realm settings → Security defenses → Brute force detection*.

### 5.2 API client (resource server)

The API client does not log anyone in. It owns the API roles and is the token audience.

1. *Clients → Create client*.
2. *Client type*: `OpenID Connect`. *Client ID*: `springboot-start-app-api`. *Next*.
3. *Client authentication*: on. *Authentication flow*: uncheck everything (standard flow, direct access grants,
   implicit, service accounts, device grant). *Next*, *Save*.
4. Client → *Roles* tab → *Create role*: `calculator-user`. Again: `calculator-admin`.

Roles show up in the token under `resource_access.springboot-start-app-api.roles`.

### 5.3 API client scope with audience

Without this, tokens have `aud: account` only and the API rejects them.

1. *Client scopes → Create client scope*.
2. *Name*: `calculator`. *Type*: `None`. *Protocol*: `OpenID Connect`. *Include in token scope*: on.
   *Display on consent screen*: off. *Save*.
3. Scope → *Mappers* tab → *Configure a new mapper* → *Audience*.
4. *Name*: `springboot-start-app-api-audience`. *Included Client Audience*: `springboot-start-app-api`.
   *Add to access token*: on. *Add to ID token*: off. *Save*.

Now every client that has the `calculator` scope gets tokens with `scope: "... calculator"` and
`aud: ["springboot-start-app-api", ...]`. The scope name means "may call the calculator API".

### 5.4 SPA client (public, PKCE)

1. *Clients → Create client*. *Client ID*: `springboot-start-app-spa`. *Next*.
2. *Client authentication*: **off** (public client). *Authentication flow*: only *Standard flow*. *Next*.
3. *Login settings*:
    * *Root URL*: empty (several environments) or the app URL.
    * *Valid redirect URIs*: every app base URL with `/*`, for example
      `http://localhost:8080/*`, `https://dev.example.com/*`, `https://test.example.com/*`.
    * *Valid post logout redirect URIs*: `+` (same as redirect URIs).
    * *Web origins*: `+` (same origins as redirect URIs, needed for CORS of the token endpoint).
4. *Save*. Client → *Advanced* tab → *Advanced settings* → *Proof Key for Code Exchange Code Challenge Method*:
   `S256`. *Save*.
5. Client → *Client scopes* tab → *Add client scope* → `calculator` → *Add* as **Default**.

### 5.5 Machine to machine client (confidential)

1. *Clients → Create client*. *Client ID*: `springboot-start-app-m2m`. *Next*.
2. *Client authentication*: **on**. *Authentication flow*: only *Service accounts roles*. *Next*, *Save*.
3. Client → *Credentials* tab: copy the *Client secret*. Give it to the calling service as a secret
   (Kubernetes Secret, vault), never commit it.
4. Client → *Service accounts roles* tab → *Assign role* → *Filter by clients* → `springboot-start-app-api`
   `calculator-user` → *Assign*.
5. Client → *Client scopes* tab → *Add client scope* → `calculator` as **Default**.

One M2M client per calling service, so each one has its own secret and roles and can be disabled alone.

### 5.6 Users and roles

1. *Users → Add user*: username, email, first and last name, *Email verified* if you do not send verification.
2. User → *Credentials* → *Set password* (temporary: user must change it at first login).
3. User → *Role mapping* → *Assign role* → *Filter by clients* → `springboot-start-app-api` roles.

Recommended for many users: create *Groups* (for example `calculator-admins`), assign the roles to the group and
put users into groups.

### 5.7 Check the token

M2M token from the command line:

```shell
KEYCLOAK_URL=https://example.com/iam
REALM=example
TOKEN=$(curl -s -X POST "$KEYCLOAK_URL/realms/$REALM/protocol/openid-connect/token" \
    -d grant_type=client_credentials \
    -d client_id=springboot-start-app-m2m \
    -d client_secret="$CLIENT_SECRET" | jq -r .access_token)
# Look into the token payload
echo "$TOKEN" | cut -d. -f2 | tr '_-' '/+' | base64 -d 2>/dev/null | jq .
curl -i -H "Authorization: Bearer $TOKEN" "http://localhost:8080/api/calculator/whoami"
```

The payload should contain (shortened):

```json
{
  "iss": "https://example.com/iam/realms/example",
  "aud": ["springboot-start-app-api"],
  "azp": "springboot-start-app-m2m",
  "scope": "profile email calculator",
  "preferred_username": "service-account-springboot-start-app-m2m",
  "resource_access": { "springboot-start-app-api": { "roles": ["calculator-user"] } }
}
```

User tokens are tested through the SPA (`/calculator.html`). The SPA client has no direct access grant (password
grant) on purpose: it is deprecated and makes the client handle passwords.

## 6. Automated setup: keycloak-setup.sh

[`src/main/keycloak/keycloak-setup.sh`](../../../src/main/keycloak/keycloak-setup.sh) creates chapter 5 with the Keycloak
admin CLI `kcadm.sh` (in the Keycloak distribution `bin` directory and in the Keycloak container image). Missing
objects are created, existing clients are updated to the script settings (manual changes to those settings are
overwritten).

| Variable                      | Default                           | Meaning |
|-------------------------------|-----------------------------------|---------|
| `KCADM`                       | `kcadm.sh`                        | admin CLI command |
| `KEYCLOAK_URL`                | `http://localhost:8180/iam`       | Keycloak base URL |
| `KEYCLOAK_ADMIN_REALM`        | `master`                          | realm of the administrator |
| `KEYCLOAK_ADMIN_USER`         | `admin`                           | administrator |
| `KEYCLOAK_ADMIN_PASSWORD`     | (required)                        | administrator password |
| `KEYCLOAK_REALM`              | `example`                         | application realm |
| `KEYCLOAK_API_CLIENT_ID`      | `springboot-start-app-api`        | API client |
| `KEYCLOAK_API_ROLES`          | `calculator-user,calculator-admin`| API client roles |
| `KEYCLOAK_API_SCOPE`          | `calculator`                      | API client scope |
| `KEYCLOAK_SPA_CLIENT_ID`      | `springboot-start-app-spa`        | SPA client |
| `KEYCLOAK_SPA_URLS`           | `http://localhost:8080`           | comma separated SPA base URLs of all environments |
| `KEYCLOAK_M2M_CLIENT_ID`      | `springboot-start-app-m2m`        | machine client |
| `KEYCLOAK_M2M_ROLES`          | `calculator-user`                 | roles of the machine client |
| `KEYCLOAK_TEST_USERS`         | `false`                           | `true`: users `calculator-user`, `calculator-admin` |
| `KEYCLOAK_TEST_USER_PASSWORD` | (required with test users)        | test users password |

Shared non-live Keycloak:

```shell
KEYCLOAK_URL=https://example.com/iam KEYCLOAK_REALM=example \
KEYCLOAK_SPA_URLS=http://localhost:8080,https://dev.example.com,https://test.example.com,https://prelive.example.com \
KEYCLOAK_ADMIN_PASSWORD=... ./src/main/keycloak/keycloak-setup.sh
```

Local docker compose Keycloak (`src/test/docker/docker-compose.yaml`, `http://localhost:8180/iam`, admin/password):

```shell
docker compose -f ./src/test/docker/docker-compose.yaml up -d keycloak
KCADM="docker exec -i keycloak /opt/keycloak/bin/kcadm.sh" KEYCLOAK_URL=http://localhost:8080/iam \
KEYCLOAK_ADMIN_PASSWORD=password KEYCLOAK_TEST_USERS=true KEYCLOAK_TEST_USER_PASSWORD=... \
./src/main/keycloak/keycloak-setup.sh
```

(`KEYCLOAK_URL` is the URL inside the container there.)

## 7. Spring Boot side

### 7.1 Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security-oauth2-resource-server</artifactId>
</dependency>
<!-- tests: jwt() mock tokens -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

Keycloak specific Spring adapters (`keycloak-spring-boot-starter`) are deprecated and removed. Use standard Spring
Security OAuth2, it works with any OIDC provider.

### 7.2 Configuration

`application.yaml` is the same for all environments. The `SMI_IAM_*` keys at the top of the file are the defaults;
an environment variable with the same name has higher priority in Spring Boot and replaces the value. Derived values
(`SMI_IAM_ISSUER_URL`, `SMI_IAM_JWK_SET_URL`, `SMI_IAM_AUDIENCE`) follow the overridden ones automatically.

```yaml
SMI_IAM_URL: http://localhost:8180/iam
SMI_IAM_REALM: example
SMI_IAM_ISSUER_URL: ${SMI_IAM_URL}/realms/${SMI_IAM_REALM}
SMI_IAM_JWK_SET_URL: ${SMI_IAM_ISSUER_URL}/protocol/openid-connect/certs
SMI_IAM_API_CLIENT_ID: springboot-start-app-api
SMI_IAM_AUDIENCE: ${SMI_IAM_API_CLIENT_ID}
SMI_IAM_SPA_CLIENT_ID: springboot-start-app-spa
SMI_IAM_PRINCIPAL_CLAIM: preferred_username

spring:
    security:
        oauth2:
            resourceserver:
                jwt:
                    issuer-uri: ${SMI_IAM_ISSUER_URL}
                    jwk-set-uri: ${SMI_IAM_JWK_SET_URL}
                    audiences: ${SMI_IAM_AUDIENCE}

springBootStartProject:
    security:
        keycloak:
            url: ${SMI_IAM_URL}
            realm: ${SMI_IAM_REALM}
            issuerUrl: ${SMI_IAM_ISSUER_URL}
            jwkSetUrl: ${SMI_IAM_JWK_SET_URL}
            apiClientId: ${SMI_IAM_API_CLIENT_ID}
            audience: ${SMI_IAM_AUDIENCE}
            spaClientId: ${SMI_IAM_SPA_CLIENT_ID}
            principalClaim: ${SMI_IAM_PRINCIPAL_CLAIM}
```

The `springBootStartProject.security.keycloak.*` values are bound to `KeycloakProperties` of the
[`spring-boot-keycloak-starter`](../../../../spring-boot-keycloak-starter) module (registered by its auto-configuration).

`jwk-set-uri` is set explicitly, so the application starts without Keycloak (keys are fetched on the first request)
and the issuer is still checked.

### 7.3 Security configuration

[`SecurityConfiguration`](../../../src/main/java/info/setmy/microservice/web/config/SecurityConfiguration.java):

* stateless (no HTTP session), CSRF off: there are no authentication cookies, the token is sent explicitly in the
  `Authorization` header, so CSRF attacks do not apply;
* `oauth2ResourceServer().jwt()` with a custom converter;
* URL rules, closed by default (`anyRequest().authenticated()`);
* `@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)` for the annotations below.

[`KeycloakGrantedAuthoritiesConverter`](../../../src/main/java/info/setmy/microservice/web/security/KeycloakGrantedAuthoritiesConverter.java)
maps the token to authorities:

| Token claim                                   | Authority          |
|-----------------------------------------------|--------------------|
| `scope: "openid calculator"`                  | `SCOPE_openid`, `SCOPE_calculator` |
| `realm_access.roles`                          | `ROLE_<role>`      |
| `resource_access.<SMI_IAM_API_CLIENT_ID>.roles` | `ROLE_<role>`   |

Spring Security does not know Keycloak role claims by itself, without a converter only scopes are mapped.

### 7.4 Endpoint protection options

[`CalculatorController`](../../../src/main/java/info/setmy/microservice/web/controller/CalculatorController.java), every
endpoint uses another option:

| Endpoint                  | Option | Code |
|---------------------------|--------|------|
| `/api/calculator/add`      | URL rule, any valid token | `.requestMatchers("/api/calculator/**").authenticated()` |
| `/api/calculator/subtract` | OAuth2 scope | `@PreAuthorize("hasAuthority('SCOPE_calculator')")` |
| `/api/calculator/multiply` | role, Spring Expression Language (SpEL) | `@PreAuthorize("hasRole('calculator-user')")` |
| `/api/calculator/divide`   | role, JSR-250 (Jakarta) | `@RolesAllowed("calculator-user")` |
| `/api/calculator/modulo`   | role, Spring | `@Secured("ROLE_calculator-admin")` |
| `/api/calculator/power`    | SpEL with method arguments | `@PreAuthorize("hasRole('calculator-admin') or (hasRole('calculator-user') and #b <= 10)")` |
| `/api/calculator/whoami`   | any valid token, token data in controller | `@PreAuthorize("isAuthenticated()")`, `@AuthenticationPrincipal Jwt jwt` |
| `/api/public/auth-config`  | public | `.requestMatchers("/api/public/**").permitAll()` |

Notes:

* `@PreAuthorize` is the most flexible and the usual choice. `hasRole('x')` checks authority `ROLE_x`,
  `hasAuthority('x')` checks `x` as is.
* URL rules can also check roles: `.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("calculator-admin")`.
* SpEL can use method arguments (`#b`, needs compiling with `-parameters`), the token
  (`authentication.token.claims['azp'] == 'springboot-start-app-m2m'`) and beans (`@myBean.check(#id)`).
* `@PostAuthorize("returnObject.owner == authentication.name")` checks the returned value.
* Put the annotations on controllers (or services) consistently; the URL rule is the safety net.

Answers:

| Situation                                  | Status |
|--------------------------------------------|--------|
| no token, expired, wrong signature, wrong `iss` or `aud` | `401 Unauthorized` (`WWW-Authenticate` header says why) |
| valid token, missing role or scope         | `403 Forbidden` |

`@ControllerAdvice` handlers that catch `Exception` must let `AccessDeniedException` and `AuthenticationException`
through, otherwise they become 500 (see `ExceptionHandlerComponent`).

### 7.5 CORS

The SPA is served by the same Spring Boot application (same origin), so the API needs no CORS configuration.
If the SPA is hosted on another origin, add `http.cors(...)` with a `CorsConfigurationSource` allowing that origin
and the `Authorization` header. Keycloak CORS for the SPA is the *Web origins* setting of the SPA client.

### 7.6 Tests

* [`KeycloakGrantedAuthoritiesConverterTest`](../../../src/test/java/info/setmy/microservice/web/security/KeycloakGrantedAuthoritiesConverterTest.java):
  token to authorities mapping.
* [`CalculatorControllerIT`](../../../src/test/java/info/setmy/microservice/it/security/CalculatorControllerIT.java):
  MockMvc with `jwt()` from spring-security-test, a Keycloak like token without Keycloak.

## 8. SPA and machine to machine differences

|                          | SPA (browser, user)                          | Machine to machine (service)          |
|--------------------------|----------------------------------------------|---------------------------------------|
| Keycloak client type     | public (*Client authentication* off)         | confidential (*Client authentication* on) |
| Flow                     | authorization code + PKCE (S256)             | client credentials                    |
| Secret                   | none (impossible to keep in a browser)      | client secret (or signed JWT / mTLS)  |
| Who is in the token      | the user (`sub` = user ID, `preferred_username`) | service account `service-account-<client id>` |
| Roles assigned to        | users or groups                              | the client service account            |
| Refresh token            | yes, `keycloak-js` refreshes                 | no, ask a new token when expired      |
| Redirect URIs, web origins | required                                   | not used                              |
| API side                 | the same: Bearer JWT, same validation and rules |                                    |

When an endpoint must be user only or machine only, check it explicitly, for example
`@PreAuthorize("authentication.token.claims['azp'] == 'springboot-start-app-m2m'")` or use different roles.

## 9. Application environment variables

| Variable                  | Default                                               | Meaning |
|---------------------------|-------------------------------------------------------|---------|
| `SMI_IAM_URL`             | `http://localhost:8180/iam`                           | Keycloak base URL, without `/realms/...` |
| `SMI_IAM_REALM`           | `example`                                             | realm |
| `SMI_IAM_ISSUER_URL`      | `${SMI_IAM_URL}/realms/${SMI_IAM_REALM}`              | expected `iss`, set only when it differs |
| `SMI_IAM_JWK_SET_URL`     | `${SMI_IAM_ISSUER_URL}/protocol/openid-connect/certs` | public keys; set to an internal Keycloak address when the server can not use the public one |
| `SMI_IAM_API_CLIENT_ID`   | `springboot-start-app-api`                            | client whose client roles are mapped |
| `SMI_IAM_AUDIENCE`        | `${SMI_IAM_API_CLIENT_ID}`                            | required `aud` value |
| `SMI_IAM_SPA_CLIENT_ID`   | `springboot-start-app-spa`                            | SPA client, given to the browser by `/api/public/auth-config` |
| `SMI_IAM_PRINCIPAL_CLAIM` | `preferred_username`                                  | claim used as principal name |

### Local, with Maven

```shell
export SMI_IAM_URL=https://example.com/iam
export SMI_IAM_REALM=example
./mvnw spring-boot:run          # local profile
# open http://localhost:8080/calculator.html
```

`http://localhost:8080/*` must be in the SPA client redirect URIs (chapter 5.4).

### systemd

[`src/main/systemd/springboot-start-app.service`](../../../src/main/systemd/springboot-start-app.service) reads
`/etc/opt/setmy.info/springboot-start-app.env`, example in
[`src/main/systemd/springboot-start-app.env`](../../../src/main/systemd/springboot-start-app.env):

```shell
sudo cp src/main/systemd/springboot-start-app.service /etc/systemd/system/
sudo cp src/main/systemd/springboot-start-app.env /etc/opt/setmy.info/   # edit the values
sudo systemctl daemon-reload
sudo systemctl enable --now springboot-start-app
```

### Docker

```shell
docker run -p 8080:8080 -e SMI_IAM_URL=https://example.com/iam -e SMI_IAM_REALM=example \
    setmyinfo/springboot-start-project:latest
```

Same variables in [`src/main/docker/docker-compose.yaml`](../../../src/main/docker/docker-compose.yaml).

### Kubernetes

Values in the ConfigMap ([`microservice-config-map.yaml`](../../../src/main/k8s/local/microservice-config-map.yaml),
keys `keycloak-url`, `keycloak-realm`, `keycloak-api-client-id`, `keycloak-spa-client-id`), mapped to the
variables in [`microservice-deployment.yaml`](../../../src/main/k8s/local/microservice-deployment.yaml) with
`configMapKeyRef`. The ConfigMap is `immutable: true`: delete and create it again to change values, then restart
the pods. M2M client secrets of calling services belong to a Secret, not to a ConfigMap.

## 10. SPA (Vue) side

[`src/main/resources/static/calculator.html`](../../../src/main/resources/static/calculator.html), Vue 3 + Vue Router +
[`keycloak-js`](https://www.npmjs.com/package/keycloak-js):

1. Loads `/api/public/auth-config` (Keycloak URL, realm, SPA client ID from the server environment variables).
2. `keycloak.init({pkceMethod: 'S256', responseMode: 'query', checkLoginIframe: false})`. `responseMode: 'query'`
   because the router uses `#` URLs, the Keycloak answer would otherwise collide with them.
3. Router guard: a route with `meta.requiresAuth` and no login stores the route and calls `keycloak.login()`.
   After login the SPA goes back to the stored route.
4. Before every API call `keycloak.updateToken(30)` refreshes the token, then
   `Authorization: Bearer <token>` is sent.
5. Tokens stay in memory only (not in localStorage). Reloading the page logs in again silently through the
   Keycloak SSO session.

## 11. Alternative: Backend for Frontend (BFF)

Higher security option for browser applications: the browser holds no tokens at all. Spring Boot is then an OAuth2
client (`spring-boot-starter-security-oauth2-client`, `oauth2Login()`), keeps tokens on the server and gives the
browser an HTTP-only session cookie. That needs a confidential client, CSRF protection and server side sessions
(sticky or shared). The resource server setup in this guide stays the same for M2M clients.

## 12. Troubleshooting

| Symptom | Cause |
|---------|-------|
| 401, `WWW-Authenticate: ... The iss claim is not valid` | issuer differs: `KC_HOSTNAME` / proxy headers in Keycloak, or `SMI_IAM_URL` / `SMI_IAM_ISSUER_URL` |
| 401, `The aud claim is not valid` | audience mapper missing (5.3), or client lacks the `calculator` scope |
| 401 at first request, connection error in log | server can not reach the JWKS URL, set `SMI_IAM_JWK_SET_URL` |
| 403 with valid token | role not assigned, or assigned on another client than `SMI_IAM_API_CLIENT_ID`; look into the token (5.7) |
| Keycloak page `Invalid parameter: redirect_uri` | app URL missing in *Valid redirect URIs* |
| Browser CORS error on the token endpoint | *Web origins* of the SPA client |
| Logout error page | *Valid post logout redirect URIs* missing |
| 500 instead of 403 | an exception handler catches security exceptions (7.4) |
| Token valid in one server, not in another | clock skew, synchronise time (NTP) |
