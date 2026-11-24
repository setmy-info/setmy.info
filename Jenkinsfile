def runCommand(String command) {
    if (isUnix()) {
        sh command
    } else {
        bat command
    }
}

pipeline {

    // jenkinsfile-starter version  1.3.0, without draft: master releases and deploys to the live publish stage

    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    options {
        buildDiscarder(
            logRotator(
                numToKeepStr: '20',
                artifactNumToKeepStr: '10'
            )
        )

        quietPeriod(15)
        disableConcurrentBuilds(abortPrevious: true)
    }

    environment {
        PATH = "/opt/jdk/bin:/opt/setmy.info/bin:/opt/has/bin:/opt/maven/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk/"

        // The same as GitHub Actions sets: tests that need a developer machine (for example a Firefox profile of
        // java-crawler) are off with @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
        CI = 'true'

        SMI_PROFILES = 'ci'

        SMI_MAIL_TO = 'has@gintra'

        MASTER_TO_LIVE = 'DEPLOY'

        RELEASE_TO_PRELIVE = 'DEPLOY'
        HOTFIX_TO_PRELIVE = 'DEPLOY'

        DEVELOPMENT_TO_TEST_DRAFT = 'DEPLOY'
        RELEASE_TO_TEST_DRAFT = 'DEPLOY'
        HOTFIX_TO_TEST_DRAFT = 'DEPLOY'

        //DEVELOPMENT_TO_TEST_PUBLISH = 'DEPLOY'
        //RELEASE_TO_TEST_PUBLISH = 'DEPLOY'
        //HOTFIX_TO_TEST_PUBLISH = 'DEPLOY'

        DEVELOPMENT_TO_DEV = 'DEPLOY'

        // No servers in this repository: SMI_DEPLOY_TEST_HOSTS and SMI_DEPLOY_LIVE_HOSTS ("[user@]host[:port]",
        // several separated by spaces) come from the Jenkins job / node configuration and reach make and
        // smi-deploy as environment variables. SMI_DEPLOY_USER, SMI_DEPLOY_PORT, ... the same way, when needed.
        SPRINGBOOT_APP_DIR = 'springboot-start-project/springboot-start-app'
    }

    stages {
        stage('Inspection') {
            parallel {
                stage('Pre-build') {
                    steps {
                        echo "Jenkins node: ${env.NODE_NAME}"
                        echo "Operating system: ${isUnix() ? 'Unix/Linux' : 'Windows'}"

                        script {
                            if (!fileExists('README.md')) {
                                error('README.md missing')
                            }
                            if (!fileExists("${env.SPRINGBOOT_APP_DIR}/src/main/sh/etc/systemd/system/springboot-start-app.service")) {
                                error("${env.SPRINGBOOT_APP_DIR}/src/main/sh/etc/systemd/system/springboot-start-app.service missing")
                            }
                        }
                    }
                }
                stage('Build tools') {
                    steps {
                        runCommand 'mvn --version'
                        runCommand 'java --version'
                        runCommand 'which java'
                        runCommand 'make --version'
                        runCommand 'rpmbuild --version'
                        runCommand 'rpm --eval "%{_topdir}"'
                    }
                }
            }
        }

        stage('Preparation') {
            parallel {
                stage('Install') {
                    steps {
                        echo 'no tools onstallations and build setup'
                    }
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Cleaning, compilation, unit and integration tests are in the install below'
                runCommand 'mvn clean install -Pci'

                echo 'No mutation tests'
                echo 'mvn org.pitest:pitest-maven:mutationCoverage'

                // Sites
                echo 'cd dependencies-bom; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-services; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd groovy-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd groovy-services; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-clojure; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-jwt-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-linguistics; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-accounting; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-ai; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-db; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-document-db; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-graph-db; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-hr; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-indexing; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-reports; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-templates; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-scripting; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-storage; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-web-doc-format; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-web; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-wf; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-communication; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-vcs; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-exec; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-stealer; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-modular-ssn; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-rest-client; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-crawler; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd maven-plugin-start-project; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-lexer-parser; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-protocol; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-graph; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd java-graphics; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd springboot-start-project; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                echo 'cd springboot-start-project/springboot-start-app; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-example-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-accounting-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-ai-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-clojure-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-communication-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-crawler-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-db-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-document-db-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-exec-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-graph-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-graph-db-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-graphics-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-hr-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-indexing-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-jwt-models-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-keycloak-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-lexer-parser-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-linguistics-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-modular-ssn-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-protocol-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-reports-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-rest-client-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-scripting-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-stealer-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-storage-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-templates-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-vcs-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-web-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-web-doc-format-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'
                echo 'cd springboot-start-project/spring-boot-wf-starter; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ../..'

                echo 'No e2e tests'
                echo 'mvn verify -Pe2e -Pci'

                echo 'No system tests'
                echo 'No acceptance tests'

                // RPMs of the Spring Boot app (LIVE and TEST), from the jar the install above built
                runCommand 'make -C "${SPRINGBOOT_APP_DIR}" clean rpms'
                runCommand 'for spec in "${SPRINGBOOT_APP_DIR}"/rpm/*.spec; do rpmspec -q "$spec" >/dev/null; done'

                echo 'No local publishing'
            }
        }

        stage('Publish') {
            parallel {
                stage('Release') {
                    when {
                        branch 'master'
                    }
                    steps {
                        echo 'No release repository: the RPMs are uploaded by the Deploy stage'
                    }
                }
                stage('Snapshot') {
                    when {
                        branch pattern: 'devel.*', comparator: 'REGEXP'
                    }
                    steps {
                        echo 'No snapshot repository: the RPMs are uploaded by the Deploy stage'
                    }
                }
                stage('Release reports') {
                    when {
                        branch 'master'
                    }
                    steps {
                        echo 'Put here reports publishing steps'
                    }
                }
                stage('Snapshot reports') {
                    when {
                        branch pattern: 'devel.*', comparator: 'REGEXP'
                    }
                    steps {
                        echo 'Put here reports publishing steps'
                    }
                }
            }
        }
        stage('Deploy') {
            parallel {
                stage('dev') {
                    when {
                        environment name: 'DEVELOPMENT_TO_DEV', value: 'DEPLOY'
                        branch pattern: 'devel.*', comparator: 'REGEXP'
                    }
                    steps {
                        echo 'No DEV machine'
                    }
                }
                stage('test: draft') {
                    when {
                        anyOf {
                            allOf {
                                environment name: 'DEVELOPMENT_TO_TEST_DRAFT', value: 'DEPLOY'
                                branch pattern: 'devel.*', comparator: 'REGEXP'
                            }
                            allOf {
                                environment name: 'RELEASE_TO_TEST_DRAFT', value: 'DEPLOY'
                                branch pattern: 'release.*', comparator: 'REGEXP'
                            }
                            allOf {
                                environment name: 'HOTFIX_TO_TEST_DRAFT', value: 'DEPLOY'
                                branch pattern: 'hotfix.*', comparator: 'REGEXP'
                            }
                        }
                    }
                    steps {
                        // Upload and hand-over to the deployment service of the TEST machines, which installs it.
                        // journalctl -u setmy-info-deploy.service on the machine says how it went.
                        script {
                            if (env.SMI_DEPLOY_TEST_HOSTS || env.SMI_SPRINGBOOT_TEST_HOSTS) {
                                runCommand 'make -C "${SPRINGBOOT_APP_DIR}" deploy-test'
                            } else {
                                echo 'No SMI_SPRINGBOOT_TEST_HOSTS / SMI_DEPLOY_TEST_HOSTS in the job: nothing to deploy to'
                            }
                        }
                    }
                }
                stage('test: publish') {
                    when {
                        anyOf {
                            allOf {
                                environment name: 'DEVELOPMENT_TO_TEST_PUBLISH', value: 'DEPLOY'
                                branch pattern: 'devel.*', comparator: 'REGEXP'
                            }
                            allOf {
                                environment name: 'RELEASE_TO_TEST_PUBLISH', value: 'DEPLOY'
                                branch pattern: 'release.*', comparator: 'REGEXP'
                            }
                            allOf {
                                environment name: 'HOTFIX_TO_TEST_PUBLISH', value: 'DEPLOY'
                                branch pattern: 'hotfix.*', comparator: 'REGEXP'
                            }
                        }
                    }
                    steps {
                        echo 'No test publish stage: the service has no draft'
                    }
                }
                stage('prelive') {
                    when {
                        anyOf {
                            allOf {
                                environment name: 'RELEASE_TO_PRELIVE', value: 'DEPLOY'
                                branch pattern: 'release.*', comparator: 'REGEXP'
                            }
                            allOf {
                                environment name: 'HOTFIX_TO_PRELIVE', value: 'DEPLOY'
                                branch pattern: 'hotfix.*', comparator: 'REGEXP'
                            }
                        }
                    }
                    steps {
                        echo 'No PRELIVE machine'
                    }
                }
                stage('live: draft') {
                    when {
                        environment name: 'DRAFT_TO_LIVE', value: 'DEPLOY'
                        branch 'draft'
                    }
                    steps {
                        echo 'No draft branch: master deploys to the live publish stage'
                    }
                }
                stage('live: publish') {
                    when {
                        environment name: 'MASTER_TO_LIVE', value: 'DEPLOY'
                        branch 'master'
                    }
                    steps {
                        // LIVE BE machines: the same jar, Spring profile live
                        script {
                            if (env.SMI_DEPLOY_LIVE_HOSTS || env.SMI_SPRINGBOOT_LIVE_HOSTS) {
                                runCommand 'make -C "${SPRINGBOOT_APP_DIR}" deploy-live'
                            } else {
                                echo 'No SMI_SPRINGBOOT_LIVE_HOSTS / SMI_DEPLOY_LIVE_HOSTS in the job: nothing to deploy to'
                            }
                        }
                    }
                }
            }
        }
        stage('Tag') {
            when {
                environment name: 'MASTER_TO_LIVE', value: 'DEPLOY'
                branch 'master'
            }
            steps {
                echo 'Put here taging'
                echo 'smi-new-tag 1.2.3'
            }
        }
    }

    post {
        always {
            // junit '**/target/*-reports/*.xml'
            runCommand 'ls -l "$(rpm --eval "%{_topdir}")/RPMS/noarch" || true'
        }

        success {
            emailext (
                to: "${SMI_MAIL_TO}",
                subject: "Jenkins job: $JOB_NAME, build: $BUILD_NUMBER type: SUCCESSFUL",
                body: "Job: $JOB_NAME, build: $BUILD_NUMBER, url: ${env.BUILD_URL}, git: ${env.GIT_URL}, branch: ${env.GIT_BRANCH} SUCCESSFUL post step",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }

        unstable {
            emailext (
                to: "${SMI_MAIL_TO}",
                subject: "Jenkins job: $JOB_NAME, build: $BUILD_NUMBER type: UNSTABLE",
                body: "Job: $JOB_NAME, build: $BUILD_NUMBER, url: ${env.BUILD_URL}, git: ${env.GIT_URL}, branch: ${env.GIT_BRANCH} UNSTABLE post step",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }

        failure {
            emailext (
                to: "${SMI_MAIL_TO}",
                subject: "Jenkins job: $JOB_NAME, build: $BUILD_NUMBER type: FAILED",
                body: "Job: $JOB_NAME, build: $BUILD_NUMBER, url: ${env.BUILD_URL}, git: ${env.GIT_URL}, branch: ${env.GIT_BRANCH}  FAILED post step",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                attachLog: true,
                compressLog: true
            )
        }
    }
}
