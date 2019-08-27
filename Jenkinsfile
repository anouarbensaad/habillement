pipeline {
    agent any
    stages {
        stage('Checkout SCM'){
            steps {
                checkout scm
            }
        }
        stage('Compile & build'){
            tools 
            {
                jdk "jdk8"
                maven "3.5.4"
            }
        }
    }
}
