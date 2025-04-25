pipeline {
    agent any

    tools {
        jdk 'jdk-21'
    }

    environment {
        SONAR_TOKEN = credentials('SonarQube1')
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

        stage('SonarQube') {
            steps {
                script {
                    withSonarQubeEnv('Sonar') {
                        sh './mvnw sonar:sonar -Dsonar.projectKey=socialMedia -Dsonar.token=$SONAR_TOKEN'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t social-media:latest .'
            }
        }

       stage('Deploy') {
             steps {
                sh 'docker-compose up -d'
             }
       }
    }
}