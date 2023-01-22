pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                githubNotify(
                    credentialsId: 'd13dda6c-4afa-418b-987f-c1a71349eb0a'
                    description: 'Estágio 1 - Backend',
                    status: 'PENDING'
                );
                sh 'docker build --rm -t vbsantos-tcc/backend:latest .'
            }
        }
    }
    post {
        success {
            githubNotify(
                credentialsId: 'd13dda6c-4afa-418b-987f-c1a71349eb0a'
                description: 'Estágio 1 - Backend',
                status: 'SUCCESS'
            );
        }
        failure {
            githubNotify(
                credentialsId: 'd13dda6c-4afa-418b-987f-c1a71349eb0a'
                description: 'Estágio 1 - Backend',
                status: 'FAILURE'
            );
        }
    }
}
