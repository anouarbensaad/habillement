pipeline{
    agent any
        stage('Checkout SCM'){
            checkout scm
        }
        stage('Compile & build'){
            def mvnHome = tool name: 'maven-3', type: 'maven'
            def mvnCmd = "${mvnHome}/bin/mvn"
            sh "${mvnCmd} clean install"
        }
}
