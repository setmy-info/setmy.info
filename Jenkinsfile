pipeline {

    agent any

    environment {
        PATH = "/opt/has/bin:/opt/apache-maven-3.5.4/bin:/usr/local/bin:$PATH"
    }

    stages {
        stage('Environment') {
            steps {
                echo "PATH is: $PATH"
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
        stage('Build') {
            steps {
                sh 'mvn install'
            }
        }
    }
}
