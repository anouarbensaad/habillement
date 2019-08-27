pipeline {
    agent any
    stages {
        stage('Checkout SCM'){
            steps {
                checkout scm
            }
        }
        stage('Compile & build'){
            steps{
                tools{
                    jdk "jdk8"
                    maven "3.6.0"
                }
                sh "echo hey"
            }
        }
    }
}
