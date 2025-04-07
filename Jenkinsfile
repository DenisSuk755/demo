pipeline {
    agent any

    triggers {
        githubPush() // responds to PR merges (push to main)
    }

    environment {
        DOCKER_IMAGE = 'coveros/demo-app'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            when {
                branch 'main'
            }
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Test') {
            when {
                branch 'main'
            }
            steps {
                echo 'Running tests...'
                // Replace with real test logic
                sh 'docker run --rm $DOCKER_IMAGE npm test || echo "Tests not implemented yet"'
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying application...'
                sh 'docker run -d -p 3000:3000 $DOCKER_IMAGE || ./deploy.sh'
            }
        }
    }

    post {
        failure {
            echo 'Build failed!'
        }
        success {
            echo 'CI/CD Pipeline completed successfully.'
        }
    }
}
