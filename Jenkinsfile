pipeline {

    agent any

    environment {
        PATH = "/opt/has/bin:/opt/apache-maven-3.5.4/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk-11/"
    }

    stages {
        stage('Environment') {
            steps {
                echo "PATH is: $PATH"
                sh 'mvn --version'
            }
        }
        stage('Cleaning') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Compiling') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test Compiling') {
            steps {
                sh 'mvn test-compile'
            }
        }
        stage('Unit test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn install'
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
                sh 'cd java-models; mvn org.pitest:pitest-maven:mutationCoverage site:site; cd ..'
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
            }
        }
    }

    post {
        always {
            junit '**/target/*-reports/*.xml'
        }
    }
}
