pipeline {

    agent any

    environment {
        PATH = "/opt/jdk-13.0.1/bin:/opt/has/bin:/opt/apache-maven-3.6.3/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk-13.0.1/"
    }

    stages {
        stage('Environment') {
            steps {
                echo "PATH is: $PATH"
                sh 'mvn --version'
                sh 'java --version'
                sh 'which java'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Sites') {
            steps {
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
    }

    post {
        always {
            junit '**/target/*-reports/*.xml'
        }
        success {
            emailext (
                subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                            <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
        failure {
            emailext (
                subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                            <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}
