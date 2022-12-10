pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'docker build --rm -t vbsantos-tcc/backend:latest .'
            }
        }
    }
}
