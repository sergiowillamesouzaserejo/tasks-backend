pipeline {
    agent any

    stages {
        stage('Buil Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL'){
                    bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_a15e6494de5e41fe15af72dfcdee79491a5d22aa -Dsonar.java.binaries=target"
                }
            }
        }
        stage('Quality Gate') {
            steps {
                sleep(40)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy Backend'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage('Test API'){
            steps{
                dir('api-test'){
                    git credentialsId: 'UsuarioGit', url: 'https://github.com/sergiowillamesouzaserejo/api-test.git'
                    bat 'mvn test'
                }
            }
        }
    }
}