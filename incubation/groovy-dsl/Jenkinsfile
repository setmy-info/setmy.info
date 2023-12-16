import static info.setmy.groovy.example.Dsl.pipeline

println '''
Hello
'''

pipeline {
    agent any

    environment {
        EXAMPLE_NUMBER = 1234
        EXAMPLE_STRING = "String example"
    }

    stages {

        stage("Stage I") {
            steps {
                sh "groovy --version"
                sh(script: "date")
                echo "Hello"
            }
        }

        stage("Stage II") {
            steps {
                sh "groovy --version"
            }
        }
    }
}
