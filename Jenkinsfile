pipeline {

    agent any

    environment {
        PATH = "/opt/has/bin:/opt/apache-maven-3.6.0/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk-12/"
    }

    stages {
        stage('Environment') {
            steps {
                echo "PATH is: $PATH"
                sh 'mvn --version'
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
                sh 'cd springboot-start-project; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-communication; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-vcs; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd java-modular-ssn; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                sh 'cd documentation; mvn site:site; cd ..'
                sh 'cd tomcat-start-project; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
                // sh 'cd micronaut-start-project; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
            }
        }
    }

    post {
        always {
            junit '**/target/*-reports/*.xml'
        }
    }
}
