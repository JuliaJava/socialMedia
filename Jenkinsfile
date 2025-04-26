pipeline {
    agent any

    tools {
        jdk 'jdk-211'
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

       stage('Deploy') {
             steps {
                sh 'docker-compose up -d'
             }
       }
    }
}