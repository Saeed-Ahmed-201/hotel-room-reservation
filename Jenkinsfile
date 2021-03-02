pipeline {
    agent any
    stages {
        stage('---clean---') {
            steps {
                echo "clean stage"
                bat "mvn clean"
            }
        }
        stage('--test--') {
            steps {
                bat "mvn test"
            }
        }
        stage('--package--') {
            steps {
                bat "mvn package"
            }
        }
        stage('--- depoly ---'){
            steps{ 
                      bat 'move M:/DevOps/Jenkins/JenkinsHome/workspace/hotel-reservation/target  m:/tomcat/tomcat/webapps'
            }
        }
    }
}
