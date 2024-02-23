pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\Program Files\Java\jdk-17\bin'
        MAVEN_HOME = 'C:\Users\HP\Downloads\apache-maven-3.9.6\bin'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/HassinaHm/SpringbootBackendt'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        // stage('Docker Build') {
        //     steps {
        //         script {
        //             def dockerImage = docker.build('spring')
        //             dockerImage.inside('-v /var/run/docker.sock:/var/run/docker.sock') {
        //                 sh 'docker login -u your-dockerhub-username -p your-dockerhub-password'
        //                 sh 'docker push your-dockerhub-username/your-docker-image-name:latest'
        //             }
        //         }
        //     }
        // }
    }
}
