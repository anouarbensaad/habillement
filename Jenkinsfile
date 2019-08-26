node{
    stage('Checkout SCM'){
        checkout scm
    }
    stage('Compile & build'){
        def mvnHome = tool name: 'Maven', type: 'maven'
        def mvnCmd = "${mvnHome}/bin/mvn"
        def mvnVer = "${mvnCmd}/bin/mvn --version"
        sh "echo -e '===============\n Maven Version\n===============\'"
        sh "${mvnVer}"
        sh "echo -e \"\n===============\"
        sh "echo -e  ======Maven Version : ;${mvnVer}"
        sh "${mvnCmd} -X clean install -DskipTests"
    }
}
