pipeline {
agent any       
   stages { 

      stage('Checkout SCM') {          
         steps {
            checkout scm
         }
      }

      stage('Test') { 
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
            script {
               try {
                  sh "mvn clean install -DskipTests"
               }catch(Exception err) {
                  echo "Error maven build and test."
               }
            }
         }
      }

   }
   
}
