pipeline {
    agent any

    triggers {
        pollSCM('* * * * *') // optional fallback if webhook fails
    }

    environment {
        BRANCH_NAME = "${env.GIT_BRANCH}"
    }

    stages {
        stage('Build') {
            when {
                branch 'main'
            }
            steps {
                echo "Building branch ${env.BRANCH_NAME}"
                // your build steps, e.g.:
                sh 'make build'
            }
        }

        stage('Test') {
            when {
                branch 'main'
            }
            steps {
                echo "Running tests on main branch"
                sh 'make test'
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying changes from main"
                sh './deploy.sh'
            }
        }
    }
}
