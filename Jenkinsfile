
pipeline {

   environment {
      registry = "anouarbensaad/gprotest"
      registryCredential = 'dockerhub'
      dockerImage = ''
  }

   agent any

   stages {

      
      stage('Test SCM') {          
         steps {
            checkout scm
         }
      }


      stage('Build Modules') {
         /**
         * tools be used : 
         * openjdk8
         * maven3.6.0
         */
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
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }


      stage('Copy the war files.') {
         steps {
            script {
               WARPATH = "ma-gpro-planning-1.0.1.0-SNAPSHOT.war"
            }
            sh "cp ./ma-gpro-planning-war/presentation/target/${WARPATH} ."
            // Run dockerbuild.sh to build Images.
            // add the excutable right to run this script.
         }
      }


      stage('docker build images.'){
         steps {
            script{
               dockerImage = docker.build registry + ":$BUILD_NUMBER"
            }
         }
      }


      stage('docker pull images.'){
         steps {
            sh "echo no job for now"
         }
      }

   }
}
