pipeline {
    agent none
    stages {
        stage('Build') {
            agent { docker {  image 'maven:3.8.6-openjdk-11-slim' } }
            steps {
                sh '''
                    mvn clean package
                '''
            }
        }
        stage('Unit Test') {
            steps {
                sh '''
                    echo 'Unit Testing..'
                '''
            }
        }
    }
}
