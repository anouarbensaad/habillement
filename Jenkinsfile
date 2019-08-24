node{
    stage('Checkout SCM'){
        checkout scm
    }
    stage('Compile & build'){
        def mvnHome = tool name: 'Maven', type: 'maven'
        def mvnCmd = "${mvnHome}/bin/mvn"
        sh "${mvnCmd} clean package"
    }
}
