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

        stage('SonarQube') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'SonarQube-pwd', variable: 'sonarqubepwd')]) {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=${sonarqubepwd}'
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