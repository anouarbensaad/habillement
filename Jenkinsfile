node{
    stage('Checkout SCM'){
        checkout scm
    }
    stage('Compile & build'){
        def mvnHome = tool name: 'Maven', type: 'maven'
        def mvnCmd = "${mvnHome}/bin/mvn"
        def mvnVer = "${mvnCmd} --version"
        sh "echo =============== Maven Version ==============="
        sh "${mvnVer}"
        sh "echo ==============="
        sh "${mvnCmd} -X clean install -DskipTests"
    }
}
