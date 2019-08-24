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
                withMaven(maven : 'maven_3_6_0'){
                    // Maven Home Path
                    sh 'mvn clean install'
                }
            }
        }
    }
}
