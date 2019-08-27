pipeline {
agent any       
   stages {           
      stage('Checkout SCM') {          
         steps {
            checkout scm
         }
      }
      stage('Compile & build') { 
         tools{
            jdk "JDK"
            maven "Maven"
         }
         steps {    
                sh "echo -*- Java Version"   
                sh "java -version"
                sh "echo -*- Maven Version" 
                sh "mvn --version"
                sh "echo -*- Maven Building" 
                sh "mvn -X clean install -DskipTests" 
         }
      }
   }  
}
