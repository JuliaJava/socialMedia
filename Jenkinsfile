pipeline {
    agent any

    tools {
        jdk 'jdk-21'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/JuliaJava/socialMedia.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t social-media:latest .'
            }
        }

        stage('Deploy to Kubernetes') {
                    steps {
                        script {
                            sh "kubectl apply -f /k8s/postgres-deployment.yaml"
                            sh "kubectl apply -f /k8s/postgres-service.yaml"
                            sh "kubectl apply -f /k8s/app-deployment.yaml"
                            sh "kubectl apply -f /k8s/app-service.yaml"
                        }
                    }

        }
    }
}