pipeline {

    // jenkinsfile-starter version  1.0.0

    agent any

    environment {
        PATH = "/opt/jdk/bin:/opt/setmy.info/bin:/opt/has/bin:/opt/maven/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk/"

        // The same as GitHub Actions sets: tests that need a developer machine (for example a Firefox profile of
        // java-crawler) are off with @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
        CI = 'true'

        MASTER_TO_LIVE = 'DEPLOY'

        MASTER_TO_PRELIVE = 'DEPLOY'
        RELEASE_TO_PRELIVE = 'DEPLOY'

        DEVELOPMENT_TO_TESTING = 'DEPLOY'
        RELEASE_TO_TESTING = 'DEPLOY'

        DEVELOPMENT_TO_DEV = 'DEPLOY'
        RELEASE_TO_DEV = 'DEPLOY'

        // No servers in this repository: SMI_DEPLOY_TEST_HOSTS and SMI_DEPLOY_LIVE_HOSTS ("[user@]host[:port]",
        // several separated by spaces) come from the Jenkins job / node configuration and reach make and
        // smi-deploy as environment variables. SMI_DEPLOY_USER, SMI_DEPLOY_PORT, ... the same way, when needed.
        SPRINGBOOT_APP_DIR = 'springboot-start-project/springboot-start-app'
    }

    stages {
        stage('Inspection') {
            parallel {
                stage('Build tools') {
                    steps {
                        sh 'mvn --version'
                        sh 'java --version'
                        sh 'which java'
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
                sh 'mvn clean install -Pci'

                // RPMs of the Spring Boot app (LIVE and TEST), from the jar the install above built
                sh 'make -C "${SPRINGBOOT_APP_DIR}" clean rpms'

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
            }
        }

        stage('Publish') {
            parallel {
                stage('Release') {
                    when {
                        branch 'master'
                    }
                    steps {
                        echo 'Put here software release steps'
                    }
                }
                stage('Snapshot') {
                    when {
                        expression { env.BRANCH_NAME.startsWith('devel') }
                    }
                    steps {
                        echo 'Put here software snapshot publishing steps'
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
                        expression { env.BRANCH_NAME.startsWith('devel') }
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
                        expression {
                            (env.DEVELOPMENT_TO_DEV == 'DEPLOY' && env.BRANCH_NAME.startsWith('devel')) ||
                            (env.RELEASE_TO_DEV == 'DEPLOY' && env.BRANCH_NAME.startsWith('release'))
                        }
                    }
                    steps {
                        echo 'Put here software development installations steps'
                    }
                }
                stage('testing') {
                    when {
                        expression {
                            (env.DEVELOPMENT_TO_TESTING == 'DEPLOY' && env.BRANCH_NAME.startsWith('devel')) ||
                            (env.RELEASE_TO_TESTING == 'DEPLOY' && env.BRANCH_NAME.startsWith('release'))
                        }
                    }
                    steps {
                        // Upload and hand-over to the deployment service of the TEST machines, which installs it.
                        // journalctl -u setmy-info-deploy.service on the machine says how it went.
                        sh 'make -C "${SPRINGBOOT_APP_DIR}" deploy-test'
                    }
                }
                stage('prelive') {
                    when {
                        expression {
                            env.RELEASE_TO_PRELIVE == 'DEPLOY' && env.BRANCH_NAME.startsWith('release')
                        }
                    }
                    steps {
                        echo 'Put here software prelive installations steps'
                    }
                }
                stage('live') {
                    when {
                        expression {
                            env.MASTER_TO_LIVE == 'DEPLOY' && env.BRANCH_NAME == 'master'
                        }
                    }
                    steps {
                        // LIVE BE machines: the same jar, Spring profile live
                        sh 'make -C "${SPRINGBOOT_APP_DIR}" deploy-live'
                    }
                }
            }
        }
        stage('Tag') {
            when {
                branch 'master'
                expression { env.MASTER_TO_LIVE == 'DEPLOY' }
            }
            steps {
                echo 'Put here taging'
            }        
        }
    }

    post {
        always {
            // junit '**/target/*-reports/*.xml'
            sh 'echo "Allways"'
        }

        success {
            emailext (
                subject: "Jenkins job: $JOB_NAME, build: $BUILD_NUMBER type: SUCCESSFUL",
                body: "Job: $JOB_NAME, build: $BUILD_NUMBER, url: ${env.BUILD_URL}, git: ${env.GIT_URL}, branch: ${env.GIT_BRANCH} SUCCESSFUL post step",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }

        failure {
            emailext (
                subject: "Jenkins job: $JOB_NAME, build: $BUILD_NUMBER type: FAILED",
                body: "Job: $JOB_NAME, build: $BUILD_NUMBER, url: ${env.BUILD_URL}, git: ${env.GIT_URL}, branch: ${env.GIT_BRANCH}  FAILED post step",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}
