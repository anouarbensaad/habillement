node{
    stage('Checkout SCM'){
        checkout scm
    }
    stage('Compile & build'){
        def mvnHome = tool name: 'Maven', type: 'maven'
        def mvnCmd = "${mvnHome}/bin/mvn"
        def mvnVer = "${mvnCmd}/bin/mvn --version"
        sh "echo -e =============== Maven Version ==============="
        sh "${mvnVer}"
        sh "echo -e ==============="
        sh "${mvnCmd} -X clean install -DskipTests"
    }
}
