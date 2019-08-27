
pipeline {
agent any       
   stages { 

      stage('Test SCM') {          
         steps {
            checkout scm
         }
      }

      stage('Build Modules') { 
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

      stage(' Docker Build ')
         steps {
            sh "cp ./ma-gpro-planning-war/presentation/target/ma-gpro-planning-1.0.1.0-SNAPSHOT.war ."
         }

   }
   
}
