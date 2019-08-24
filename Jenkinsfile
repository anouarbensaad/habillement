pipeline {
    agent any
    stages {
        stage("Checkout SCM") {
                checkout scm
        }
        stage("Build Modules"){
            def mvnHome = tool name: 'maven-3', type: 'maven'
                    // Maven Home Path
            def mvnCmd = "${mvnHome}/bin/mvn"
            sh "${mvnCmd} clean install"
            
        }
    }
}
