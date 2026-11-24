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

`http://localhost:8082/*` redirect URIs are fine in a non-live realm for developers, never in live.

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
      `http://localhost:8082/*`, `https://dev.example.com/*`, `https://test.example.com/*`.
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
curl -i -H "Authorization: Bearer $TOKEN" "http://localhost:8082/api/calculator/whoami"
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

The argument is the environment, it sets the defaults: `./src/main/keycloak/keycloak-setup.sh <environment>`.

| Environment                         | Defaults |
|-------------------------------------|----------|
| `compose`                           | the Keycloak of `src/main/docker/docker-compose.yaml`: `kcadm.sh` inside the container `microservice-keycloak` at `http://localhost:8080/iam`, `admin` / `password`, SPA `http://localhost:8082`, test users with password `password` |
| `k8s`                               | the Keycloak of `src/main/k8s/local` (K3s, namespace `microservice-local`): `kcadm.sh` inside the pod of `keycloak-deployment` at `http://localhost:8080/iam`, `admin` / `password`, SPA `http://microservice.localhost`, test users with password `password` |
| `local`                             | a Keycloak on the developer machine `http://localhost:8180/iam`, `kcadm.sh` of `PATH`, SPA `http://localhost:8082`, test users |
| `dev`, `ci`, `test`, `prelive`, `live` | a shared Keycloak: `KEYCLOAK_URL`, `KEYCLOAK_ADMIN_PASSWORD` and `KEYCLOAK_SPA_URLS` required, no test users unless asked, never in `live` |

Every variable overrides the default of the environment:

| Variable                      | Default                           | Meaning |
|-------------------------------|-----------------------------------|---------|
| `KCADM`                       | `kcadm.sh` (`compose`: `docker exec -i microservice-keycloak /opt/keycloak/bin/kcadm.sh`) | admin CLI command |
| `KEYCLOAK_URL`                | by environment                    | Keycloak base URL, as `kcadm.sh` reaches it |
| `KEYCLOAK_ADMIN_REALM`        | `master`                          | realm of the administrator |
| `KEYCLOAK_ADMIN_USER`         | `admin`                           | administrator |
| `KEYCLOAK_ADMIN_PASSWORD`     | (required, `compose`: `password`) | administrator password |
| `KEYCLOAK_REALM`              | `example`                         | application realm |
| `KEYCLOAK_API_CLIENT_ID`      | `springboot-start-app-api`        | API client |
| `KEYCLOAK_API_ROLES`          | `calculator-user,calculator-admin`| API client roles |
| `KEYCLOAK_API_SCOPE`          | `calculator`                      | API client scope |
| `KEYCLOAK_SPA_CLIENT_ID`      | `springboot-start-app-spa`        | SPA client |
| `KEYCLOAK_SPA_URLS`           | by environment                    | comma separated SPA base URLs of all environments |
| `KEYCLOAK_M2M_CLIENT_ID`      | `springboot-start-app-m2m`        | machine client |
| `KEYCLOAK_M2M_ROLES`          | `calculator-user`                 | roles of the machine client |
| `KEYCLOAK_TEST_USERS`         | `true` in `compose` and `local`, else `false` | `true`: users `calculator-user`, `calculator-admin` |
| `KEYCLOAK_TEST_USER_PASSWORD` | (required with test users, `compose`: `password`) | test users password |

Shared non-live Keycloak:

```shell
KEYCLOAK_URL=https://example.com/iam KEYCLOAK_REALM=example \
KEYCLOAK_SPA_URLS=http://localhost:8082,https://dev.example.com,https://test.example.com,https://prelive.example.com \
KEYCLOAK_ADMIN_PASSWORD=... ./src/main/keycloak/keycloak-setup.sh dev
```

### 6.1 Local Keycloak in docker compose, for developers

[`src/main/docker/docker-compose.yaml`](../../../src/main/docker/docker-compose.yaml) runs Keycloak
`http://localhost:8180/iam` (administrator `admin` / `password`) next to the application, with Postgres as its
database: `keycloak_local`, user `localkeycloakliquibase` (the owner, Keycloak migrates its own tables), created by
[`keycloak-init.sql`](../../../src/main/docker/keycloak-init.sql) on an empty volume. The file is generated with
`smi-postgresql-create-db-sql keycloak local`, the full command is in its first line.

Everything made in Keycloak (realms, clients, roles, users) is stored in that database, in the volume
`postgres-data`: it stays when the containers stop, restart or are removed with `down`. Only `down -v` removes it.

Commands in the `springboot-start-app` directory:

```shell
C="docker compose -f src/main/docker/docker-compose.yaml"
```

**First time, empty database, no export yet** (once per team, then the export below does it for everybody):

1. Start: `$C up -d microservice-postgres microservice-keycloak`, wait until `$C logs microservice-keycloak`
   says `Running the server in development mode`.
2. Optional: log in at `http://localhost:8180/iam/admin` with `admin` / `password` to look around. Nothing has to be
   made by hand, the script of the next step makes chapter 5.
3. Realm `example`, the API, SPA and machine clients, roles and the test users `calculator-user` and
   `calculator-admin`:

   ```shell
   ./src/main/keycloak/keycloak-setup.sh compose
   ```
4. Check: `$C up -d`, open `http://localhost:8082/calculator.html` and log in as `calculator-user` / `password`
   (chapter 5.7 shows the token).
5. Anything else the developers need (more users, roles, groups): admin console, realm `example`, chapter 5.

**Export, so the next developer gets it all from an empty database.** The export runs with the server stopped, on the
same database, and writes `example-realm.json` with the users into
[`src/main/docker/keycloak`](../../../src/main/docker/keycloak), where the import reads it:

```shell
$C stop microservice-keycloak
$C run --rm --no-deps -v "$PWD/src/main/docker/keycloak:/opt/keycloak/data/export" microservice-keycloak \
    export --dir /opt/keycloak/data/export --realm example --users realm_file
$C start microservice-keycloak
```

Keycloak runs as user 1000 in the container: when the directory is not writable for it, `chmod o+w
src/main/docker/keycloak` before and `chmod o-w` after. Commit `example-realm.json`. It holds the client secret of
the machine client and the password hashes of the test users: development data only, never a live realm.

**Every other time, or from scratch:**

```shell
$C down -v          # only to start from an empty database
$C up -d
```

On an empty database Keycloak imports every `*.json` of `src/main/docker/keycloak` at the start
(`start-dev --import-realm`), so realm, clients and users are there without the script. A realm that is already in
the database is never overwritten by the import; to take a newer export, `down -v` first.

Inside the compose network the application fetches the signing keys from `microservice-keycloak:8080`, while the
tokens carry the issuer of the browser, `http://localhost:8180/iam/realms/example` (`KC_HOSTNAME`), the value of
`application.yaml`.

[`src/test/docker/docker-compose.yaml`](../../../src/test/docker/docker-compose.yaml) has a Keycloak of its own with
an H2 database, also on port 8180: only one of the two runs at a time.

```sh
C="docker compose -f src/main/docker/docker-compose.yaml"
$C up -d microservice-postgres microservice-keycloak
./src/main/keycloak/keycloak-setup.sh compose
```

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
# open http://localhost:8082/calculator.html
```

`http://localhost:8082/*` must be in the SPA client redirect URIs (chapter 5.4).

### systemd (RPM)

The RPMs install [`src/main/sh/etc/systemd/system/springboot-start-app.service`](../../../src/main/sh/etc/systemd/system/springboot-start-app.service),
see [rpm.md](rpm.md). The variables go into `/opt/setmy.info/etc/springboot-start-app/` (`springboot-start-app.env`, `machine.env`,
`local.env`), which is not in the RPM but in the machine's own configuration package; example in
[`src/main/sh/etc/springboot-start-app/springboot-start-app.env`](../../../src/main/sh/etc/springboot-start-app/springboot-start-app.env).

```shell
sudo systemctl restart springboot-start-app   # after the .env file changed
journalctl -u springboot-start-app -n 50
```

### Docker

```shell
docker run -p 8082:8082 -e SMI_IAM_URL=https://example.com/iam -e SMI_IAM_REALM=example \
    setmyinfo/springboot-start-app:latest
```

Same variables in [`src/main/docker/docker-compose.yaml`](../../../src/main/docker/docker-compose.yaml).

### Kubernetes

Local K3s ([`src/main/k8s/local`](../../../src/main/k8s/local), namespace `microservice-local`, see README.md):
Keycloak runs as `keycloak-deployment` on the database `keycloak_local` of the Postgres pod and imports
`src/main/docker/keycloak/*.json` (ConfigMap `keycloak-realm-config-map`) on an empty database, the same as docker
compose. The browser reaches it as `http://keycloak.localhost/iam` (`keycloak-ingress`), so tokens carry that issuer; the
application pods fetch the signing keys from `http://keycloak-service:8080/iam`. These values are in the profile
`k8s` ([`application-k8s.yml`](../../../src/main/resources/application-k8s.yml)), the deployment runs the profiles
`local,k8s`. The SPA is `http://microservice.localhost`, its redirect URI is in the realm JSON. Setup without the import:
`./src/main/keycloak/keycloak-setup.sh k8s`. M2M client secrets of calling services belong to a Secret, not to a
ConfigMap.

## 10. SPA (Vue) side

[`src/main/resources/static/calculator.html`](../../../src/main/resources/static/calculator.html), Vue 3 + Vue Router +
[`keycloak-js`](https://www.npmjs.com/package/keycloak-js):

1. Loads `/api/public/auth-config` (Keycloak URL, realm, SPA client ID from the server environment variables).
2. `keycloak.init({pkceMethod: 'S256', responseMode: 'query', checkLoginIframe: false})`. `responseMode: 'query'`
   because the router uses `#` URLs, the Keycloak answer would otherwise collide with them.
3. Router guard: a route with `meta.requiresAuth` and no login stores the route and calls `keycloak.login()`.
   After login the SPA goes back to the stored route.
4. Before every API call `keycloak.updateToken(30)` refreshes the token, then
   `Authorization: Bearer <token>` is sent. While the user is idle, `onTokenExpired` refreshes the token too, and again
   when a hidden tab is shown. A refresh that fails (SSO session ended: idle or max time, logout elsewhere) shows the
   page as logged out. Background refreshes keep the SSO session alive, so it ends at "SSO Session Max".
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
