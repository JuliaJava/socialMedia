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
                    withCredentials([file(credentialsId: '111', variable: 'KUBECONFIG')]) {
                        sh '''
                            export KUBECONFIG=${KUBECONFIG}
                            kubectl apply -f k8s/postgres-deployment.yaml
                            kubectl apply -f k8s/postgres-service.yaml
                            kubectl apply -f k8s/app-deployment.yaml
                            kubectl apply -f k8s/app-service.yaml

                            # Проверка статуса
                            kubectl get pods
                            kubectl get services
                        '''
                    }
                }

        }
    }
}