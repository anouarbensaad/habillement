pipeline {
    agent any
    stages {
        stage('Checkout SCM'){
            steps {
                checkout scm
            }
        }
        stage('Compile & build'){
            tools{
                jdk "JDK"
                maven "Maven"
            }
            steps {
                sh "java -version"
                sh "mvn --version"
            }
        }
    }
}
