pipeline {
    agent any

    stages {
        stage('Buil Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
    }
}