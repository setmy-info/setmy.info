#!/usr/bin/env bash
# Mutation coverage and site for all modules, one module at a time (bash port of build-sites.cmd).
# Run after "mvnw clean install", so that the modules find each other in the local repository.
# MVN overrides the Maven command, default is the project Maven wrapper.

set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MVN="${MVN:-${ROOT}/mvnw}"

MODULES=(
    dependencies-bom
    java-models
    java-services
    groovy-models
    groovy-services
    java-clojure
    java-jwt-models
    java-linguistics
    java-accounting
    java-ai
    java-db
    java-document-db
    java-graph-db
    java-hr
    java-indexing
    java-reports
    java-templates
    java-scripting
    java-storage
    java-web-doc-format
    java-web
    java-wf
    java-communication
    java-vcs
    java-exec
    java-stealer
    java-modular-ssn
    java-rest-client
    java-crawler
    maven-plugin-start-project
    java-lexer-parser
    java-protocol
    java-graph
    java-graphics
    springboot-start-project
    springboot-start-project/springboot-start-app
    springboot-start-project/spring-boot-example-starter
    springboot-start-project/spring-boot-accounting-starter
    springboot-start-project/spring-boot-ai-starter
    springboot-start-project/spring-boot-clojure-starter
    springboot-start-project/spring-boot-communication-starter
    springboot-start-project/spring-boot-crawler-starter
    springboot-start-project/spring-boot-db-starter
    springboot-start-project/spring-boot-document-db-starter
    springboot-start-project/spring-boot-exec-starter
    springboot-start-project/spring-boot-graph-starter
    springboot-start-project/spring-boot-graph-db-starter
    springboot-start-project/spring-boot-graphics-starter
    springboot-start-project/spring-boot-hr-starter
    springboot-start-project/spring-boot-indexing-starter
    springboot-start-project/spring-boot-jwt-models-starter
    springboot-start-project/spring-boot-keycloak-starter
    springboot-start-project/spring-boot-lexer-parser-starter
    springboot-start-project/spring-boot-linguistics-starter
    springboot-start-project/spring-boot-modular-ssn-starter
    springboot-start-project/spring-boot-protocol-starter
    springboot-start-project/spring-boot-reports-starter
    springboot-start-project/spring-boot-rest-client-starter
    springboot-start-project/spring-boot-scripting-starter
    springboot-start-project/spring-boot-stealer-starter
    springboot-start-project/spring-boot-storage-starter
    springboot-start-project/spring-boot-templates-starter
    springboot-start-project/spring-boot-vcs-starter
    springboot-start-project/spring-boot-web-starter
    springboot-start-project/spring-boot-web-doc-format-starter
    springboot-start-project/spring-boot-wf-starter
)

for module in "${MODULES[@]}"; do
    echo "Building site for module: ${module}"
    (cd "${ROOT}/${module}" && "${MVN}" -B org.pitest:pitest-maven:mutationCoverage site:site "$@")
done

echo "All builds completed successfully."
