pipeline {
    agent any
    def mvnHome = tool name: 'maven-3', type: 'maven'
    def mvnCmd = "${mvnHome}/bin/mvn"
    }
    stages {
        stage("Checkout SCM") {
            steps {
                checkout scm
            }
        }
        stage("Build Modules"){
            steps {
                sh "${mvnCmd} clean install"
            }
        }
    }
}
