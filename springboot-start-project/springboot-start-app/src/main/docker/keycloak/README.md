# Keycloak realm import

Every `*.json` file here is imported by the compose Keycloak (`start-dev --import-realm`) when it starts and the
realm does not exist yet, that is on an empty database (after `docker compose ... down -v`). A realm that is already
in the database is not changed.

`example-realm.json` is made by the export of [keycloak.md](../../../site/markdown/keycloak.md), chapter 6.1. It holds
development clients, test users and secrets only: never a live realm.
