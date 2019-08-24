pipeline {
    agent any
    stages {
        stage("Checkout SCM") {
            steps{
                checkout scm
            }
        }
        stage("Build Modules"){
            steps{
                def mvnHome = tool name: 'maven-3', type: 'maven'
                    // Maven Home Path
                    sh "${mvnHome} clean install"
            }
        }
    }
}
