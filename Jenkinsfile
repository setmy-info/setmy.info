pipeline {

    agent any

    environment {
        PATH = "/opt/has/bin:/opt/apache-maven-3.5.4/bin:/usr/local/bin:$PATH"
        JAVA_HOME = "/opt/jdk-10.0.2/"
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
    }
}
