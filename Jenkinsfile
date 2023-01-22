pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                githubNotify(description: 'Estágio 1 - Backend', status: 'PENDING');
                sh 'docker build --rm -t vbsantos-tcc/backend:latest .'
            }
        }
    }
    post {
        success {
            githubNotify(description: 'Estágio 1 - Backend', status: 'SUCCESS');
        }
        failure {
            githubNotify(description: 'Estágio 1 - Backend', status: 'FAILURE');
        }
    }
}
