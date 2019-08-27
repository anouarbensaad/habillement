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

      stage('Changing PATH WARFILE') {
         steps {
            script {
               WARPATH = "ma-gpro-planning-1.0.1.0-SNAPSHOT.war"
            }
            sh '''
            mkdir Snapshot
            '''
            sh "cp ./ma-gpro-planning-war/presentation/target/${WARPATH} ./Snapshot/"
            
            // Run dockerbuild.sh to build Images.
            // add the excutable right to run this script.
         }
      }
/*
      stage('Building image') {
         steps {
            script{
               // Test errors if docker image build ?.
               try{
                  dockerImage = docker.build registry + ":$BUILD_ID"
               }catch (Exception err) {
                  sh "echo ${err}"
               }
            }
         }
      }

      stage('Deploy Image') {
         steps{
            script {
               docker.withRegistry( '', registryCredential ) {
                  dockerImage.push()
               }
            }
         }
      }

      stage('Remove Unused docker image') {
            steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
            }
         }
*/         
   }
}
