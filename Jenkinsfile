pipeline {

    // jenkinsfile-starter version  1.0.0

    agent any

    environment {
        PATH = "/opt/jdk/bin:/opt/has/bin:/opt/apache-maven-3.6.3/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk/"

        MASTER_TO_LIVE = 'DEPLOY'

        MASTER_TO_PRELIVE = 'DEPLOY'
        RELEASE_TO_PRELIVE = 'DEPLOY'

        DEVELOPMENT_TO_TESTING = 'DEPLOY'
        RELEASE_TO_TESTING = 'DEPLOY'

        DEVELOPMENT_TO_DEV = 'DEPLOY'
        RELEASE_TO_DEV = 'DEPLOY'
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
                sh 'mvn clean install'

                // Sites
                sh 'cd groovy-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                sh 'cd groovy-services; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-accounting; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-db; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-document-db; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-graph-db; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-hr; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-indexing; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-linguistics; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-reports; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-scripting; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-services; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-storage; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-web; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-web-doc-format; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                sh 'cd jwt-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-communication; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-vcs; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-modular-ssn; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
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
                        echo 'Put here software development installations steps'
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
                        echo 'Put here software production installations steps'
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
