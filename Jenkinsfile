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
                    bat 'cd /target/'
                    bat 'move hotel-0.0.1-SNAPSHOT  m:/t/omcat/tomcat/webapps'
            }
        }
    }
}
