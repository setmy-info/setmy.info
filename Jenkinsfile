pipeline {
    agent any

    environment {
        PATH = "/opt/apache-maven-3.5.4/bin:/usr/local/bin:$PATH"
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "PATH is: $PATH"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
