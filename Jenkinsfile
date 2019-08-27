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
                jdk "jdk8"
                maven "3.6.0"
            }
            steps {
                sh "java -version"
                sh "mvn --version"
            }
        }
    }
}
