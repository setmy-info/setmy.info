#!/bin/sh
# Creates or updates in Keycloak what springboot-start-app needs, as described in src/site/markdown/keycloak.md:
#   realm, API client with client roles, API client scope with audience mapper, SPA (public) client,
#   machine to machine (confidential) client with service account roles and, optionally, test users.
# Safe to run again: missing objects are created, existing clients are updated to the settings below.
# Everything is configured with environment variables, defaults are for the local docker compose Keycloak.
#
# POSIX shell script (sh, dash, ksh, bash), uses only POSIX shell features and tools (awk, grep) besides kcadm.sh.
#
# Uses the Keycloak admin CLI kcadm.sh (in the Keycloak distribution bin directory and in the Keycloak image):
#   KEYCLOAK_ADMIN_PASSWORD=password ./src/main/keycloak/keycloak-setup.sh
#   KCADM="docker exec -i keycloak /opt/keycloak/bin/kcadm.sh" KEYCLOAK_URL=http://localhost:8080/iam \
#       KEYCLOAK_ADMIN_PASSWORD=password ./src/main/keycloak/keycloak-setup.sh

set -eu
# No file name expansion: KCADM and comma lists are split on purpose, values like "/*" must stay as they are
set -f

KCADM="${KCADM:-kcadm.sh}"
KEYCLOAK_URL="${KEYCLOAK_URL:-http://localhost:8180/iam}"
KEYCLOAK_ADMIN_REALM="${KEYCLOAK_ADMIN_REALM:-master}"
KEYCLOAK_ADMIN_USER="${KEYCLOAK_ADMIN_USER:-admin}"
KEYCLOAK_ADMIN_PASSWORD="${KEYCLOAK_ADMIN_PASSWORD:?KEYCLOAK_ADMIN_PASSWORD is not set}"

KEYCLOAK_REALM="${KEYCLOAK_REALM:-example}"
KEYCLOAK_API_CLIENT_ID="${KEYCLOAK_API_CLIENT_ID:-springboot-start-app-api}"
KEYCLOAK_API_ROLES="${KEYCLOAK_API_ROLES:-calculator-user,calculator-admin}"
KEYCLOAK_API_SCOPE="${KEYCLOAK_API_SCOPE:-calculator}"
KEYCLOAK_SPA_CLIENT_ID="${KEYCLOAK_SPA_CLIENT_ID:-springboot-start-app-spa}"
# Comma separated base URLs of every environment where the SPA runs, for example
# http://localhost:8082,https://dev.example.com,https://test.example.com,https://prelive.example.com
KEYCLOAK_SPA_URLS="${KEYCLOAK_SPA_URLS:-http://localhost:8082}"
KEYCLOAK_M2M_CLIENT_ID="${KEYCLOAK_M2M_CLIENT_ID:-springboot-start-app-m2m}"
KEYCLOAK_M2M_ROLES="${KEYCLOAK_M2M_ROLES:-calculator-user}"
# Test users calculator-user and calculator-admin, never in live
KEYCLOAK_TEST_USERS="${KEYCLOAK_TEST_USERS:-false}"

REALM="${KEYCLOAK_REALM}"

# Functions with ( ) bodies run in a subshell: their variables and IFS changes stay local (POSIX has no "local").

kc() {
    # KCADM unquoted on purpose: it may be a command with arguments ("docker exec -i keycloak .../kcadm.sh").
    # stdin from /dev/null: kcadm.sh (update without -s, docker exec -i) must never wait for input.
    # shellcheck disable=SC2086
    ${KCADM} "$@" </dev/null
}

log() {
    printf 'keycloak-setup: %s\n' "$*"
}

# json_array "a,b" "/*" -> ["a/*","b/*"]
json_array() (
    suffix="${2:-}"
    out=""
    IFS=','
    for item in $1; do
        out="${out}${out:+,}\"${item%/}${suffix}\""
    done
    printf '[%s]\n' "${out}"
)

client_uuid() {
    kc get clients -r "${REALM}" -q "clientId=$1" --fields id --format csv --noquotes
}

client_scope_uuid() {
    kc get client-scopes -r "${REALM}" --fields id,name --format csv --noquotes \
        | awk -F, -v name="$1" '$2 == name { print $1 }'
}

user_uuid() {
    kc get users -r "${REALM}" -q "username=$1" -q exact=true --fields id --format csv --noquotes
}

# ensure_client <clientId> <kcadm -s settings...>: create, or update to the given settings
ensure_client() (
    client_id="$1"
    shift
    id="$(client_uuid "${client_id}")"
    if [ -z "${id}" ]; then
        log "create client ${client_id}"
        kc create clients -r "${REALM}" -s "clientId=${client_id}" -s protocol=openid-connect "$@" >/dev/null
    else
        log "update client ${client_id}"
        kc update "clients/${id}" -r "${REALM}" "$@"
    fi
)

# ensure_client_roles <client uuid> <comma separated roles>
ensure_client_roles() (
    client="$1"
    IFS=','
    for role in $2; do
        if ! kc get "clients/${client}/roles/${role}" -r "${REALM}" >/dev/null 2>&1; then
            log "create client role ${role}"
            kc create "clients/${client}/roles" -r "${REALM}" -s "name=${role}"
        fi
    done
)

# add_api_roles <username> <comma separated API client roles>
add_api_roles() (
    username="$1"
    roles="$2"
    IFS=','
    for role in ${roles}; do
        kc add-roles -r "${REALM}" --uusername "${username}" --cclientid "${KEYCLOAK_API_CLIENT_ID}" --rolename "${role}"
    done
    log "user ${username} has API roles ${roles}"
)

# ensure_test_user <username> <comma separated API client roles>
ensure_test_user() (
    username="$1"
    password="${KEYCLOAK_TEST_USER_PASSWORD:?KEYCLOAK_TEST_USER_PASSWORD is not set}"
    if [ -z "$(user_uuid "${username}")" ]; then
        log "create test user ${username}"
        kc create users -r "${REALM}" -s "username=${username}" -s enabled=true \
            -s "email=${username}@example.com" -s emailVerified=true \
            -s firstName=Calculator -s "lastName=${username#calculator-}"
    fi
    kc set-password -r "${REALM}" --username "${username}" --new-password "${password}"
    add_api_roles "${username}" "$2"
)

log "login to ${KEYCLOAK_URL} realm ${KEYCLOAK_ADMIN_REALM} as ${KEYCLOAK_ADMIN_USER}"
kc config credentials --server "${KEYCLOAK_URL}" --realm "${KEYCLOAK_ADMIN_REALM}" \
    --user "${KEYCLOAK_ADMIN_USER}" --password "${KEYCLOAK_ADMIN_PASSWORD}"

# 1. Realm
if ! kc get "realms/${REALM}" >/dev/null 2>&1; then
    log "create realm ${REALM}"
    kc create realms -s "realm=${REALM}" -s enabled=true -s sslRequired=external
fi

# 2. API client: the resource server. No login flows, it only owns the roles and is the token audience.
ensure_client "${KEYCLOAK_API_CLIENT_ID}" \
    -s "name=Spring Boot API" \
    -s publicClient=false -s standardFlowEnabled=false -s implicitFlowEnabled=false \
    -s directAccessGrantsEnabled=false -s serviceAccountsEnabled=false
API_UUID="$(client_uuid "${KEYCLOAK_API_CLIENT_ID}")"
ensure_client_roles "${API_UUID}" "${KEYCLOAK_API_ROLES}"

# 3. API client scope: puts the scope into the token "scope" claim and the API client into "aud"
SCOPE_UUID="$(client_scope_uuid "${KEYCLOAK_API_SCOPE}")"
if [ -z "${SCOPE_UUID}" ]; then
    log "create client scope ${KEYCLOAK_API_SCOPE}"
    SCOPE_UUID="$(kc create client-scopes -r "${REALM}" -i -s "name=${KEYCLOAK_API_SCOPE}" -s protocol=openid-connect \
        -s 'attributes."include.in.token.scope"=true' -s 'attributes."display.on.consent.screen"=false')"
fi
AUDIENCE_MAPPER="${KEYCLOAK_API_CLIENT_ID}-audience"
# Mapper names into a variable first: without pipefail a failing kcadm.sh in a pipe would go unnoticed
MAPPER_NAMES="$(kc get "client-scopes/${SCOPE_UUID}/protocol-mappers/models" -r "${REALM}" \
    --fields name --format csv --noquotes)"
if ! printf '%s\n' "${MAPPER_NAMES}" | grep -qx "${AUDIENCE_MAPPER}"; then
    log "create audience mapper ${AUDIENCE_MAPPER}"
    kc create "client-scopes/${SCOPE_UUID}/protocol-mappers/models" -r "${REALM}" \
        -s "name=${AUDIENCE_MAPPER}" -s protocol=openid-connect -s protocolMapper=oidc-audience-mapper \
        -s "config.\"included.client.audience\"=${KEYCLOAK_API_CLIENT_ID}" \
        -s 'config."access.token.claim"=true' -s 'config."id.token.claim"=false' \
        -s 'config."introspection.token.claim"=true'
fi

# 4. SPA client: public (no secret in the browser), authorization code flow with PKCE
ensure_client "${KEYCLOAK_SPA_CLIENT_ID}" \
    -s "name=Spring Boot SPA" \
    -s publicClient=true -s standardFlowEnabled=true -s implicitFlowEnabled=false \
    -s directAccessGrantsEnabled=false -s serviceAccountsEnabled=false \
    -s "redirectUris=$(json_array "${KEYCLOAK_SPA_URLS}" "/*")" \
    -s 'webOrigins=["+"]' \
    -s 'attributes."pkce.code.challenge.method"=S256' \
    -s 'attributes."post.logout.redirect.uris"=+'
SPA_UUID="$(client_uuid "${KEYCLOAK_SPA_CLIENT_ID}")"
kc update "clients/${SPA_UUID}/default-client-scopes/${SCOPE_UUID}" -r "${REALM}"

# 5. Machine to machine client: confidential, client credentials grant through the service account
ensure_client "${KEYCLOAK_M2M_CLIENT_ID}" \
    -s "name=Spring Boot API machine client" \
    -s publicClient=false -s clientAuthenticatorType=client-secret -s serviceAccountsEnabled=true \
    -s standardFlowEnabled=false -s implicitFlowEnabled=false -s directAccessGrantsEnabled=false
M2M_UUID="$(client_uuid "${KEYCLOAK_M2M_CLIENT_ID}")"
kc update "clients/${M2M_UUID}/default-client-scopes/${SCOPE_UUID}" -r "${REALM}"
add_api_roles "service-account-${KEYCLOAK_M2M_CLIENT_ID}" "${KEYCLOAK_M2M_ROLES}"

# 6. Test users
if [ "${KEYCLOAK_TEST_USERS}" = "true" ]; then
    ensure_test_user calculator-user calculator-user
    ensure_test_user calculator-admin calculator-user,calculator-admin
fi

log "done. Issuer: ${KEYCLOAK_URL}/realms/${REALM}"
log "M2M client secret: ${KCADM} get clients/${M2M_UUID}/client-secret -r ${REALM}"
