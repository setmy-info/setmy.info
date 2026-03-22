@echo off
setlocal enabledelayedexpansion

REM Build script for pitest and site for all modules
REM mvn org.pitest:pitest-maven:mutationCoverage site:site

set "MODULES=dependencies-bom java-models java-services groovy-models groovy-services java-clojure java-jwt-models java-linguistics java-accounting java-ai java-db java-document-db java-graph-db java-hr java-indexing java-reports java-templates java-scripting java-storage java-web-doc-format java-web java-wf java-communication java-vcs java-exec java-stealer java-modular-ssn java-rest-client java-crawler maven-plugin-start-project java-lexer-parser java-protocol java-graph java-graphics springboot-start-project springboot-start-project\springboot-start-app springboot-start-project\spring-boot-example-starter springboot-start-project\spring-boot-accounting-starter springboot-start-project\spring-boot-ai-starter springboot-start-project\spring-boot-clojure-starter springboot-start-project\spring-boot-communication-starter springboot-start-project\spring-boot-crawler-starter springboot-start-project\spring-boot-db-starter springboot-start-project\spring-boot-document-db-starter springboot-start-project\spring-boot-exec-starter springboot-start-project\spring-boot-graph-starter springboot-start-project\spring-boot-graph-db-starter springboot-start-project\spring-boot-graphics-starter springboot-start-project\spring-boot-hr-starter springboot-start-project\spring-boot-indexing-starter springboot-start-project\spring-boot-jwt-models-starter springboot-start-project\spring-boot-lexer-parser-starter springboot-start-project\spring-boot-linguistics-starter springboot-start-project\spring-boot-modular-ssn-starter springboot-start-project\spring-boot-protocol-starter springboot-start-project\spring-boot-reports-starter springboot-start-project\spring-boot-rest-client-starter springboot-start-project\spring-boot-scripting-starter springboot-start-project\spring-boot-stealer-starter springboot-start-project\spring-boot-storage-starter springboot-start-project\spring-boot-templates-starter springboot-start-project\spring-boot-vcs-starter springboot-start-project\spring-boot-web-starter springboot-start-project\spring-boot-web-doc-format-starter springboot-start-project\spring-boot-wf-starter"

for %%m in (%MODULES%) do (
    echo Building site for module: %%m
    cd %%m
    if !errorlevel! neq 0 (
        echo Error: Could not change directory to %%m
        exit /b 1
    )
    
    call mvn org.pitest:pitest-maven:mutationCoverage site:site
    if !errorlevel! neq 0 (
        echo Error: Build failed in module %%m
        exit /b !errorlevel!
    )
    
    cd %~dp0
)

echo All builds completed successfully.
exit /b 0
