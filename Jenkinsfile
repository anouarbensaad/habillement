pipeline {
    agent any       
    currentBuild.result = "SUCCESS"    
    try{       
        stages {           
            stage('Checkout SCM'){          
                steps {
                    checkout scm
                }
            }
            stage('Compile & build'){ 
                tools{
                    jdk "JDK"
                    maven "Maven"}
                steps {        
                    sh "echo ============== JavaJDK Version =============="
                    sh "java -version"
                    sh "echo =============== Maven Version ==============="   
                    sh "mvn --version"
                    sh "echo =============== Maven _ Build ==============="
                    sh "mvn -X clean install -DskipTests" 
                }
            }
        }
    }catch(err){
        currentBuild.result = "FAILURE"
        throw err
    }
}
