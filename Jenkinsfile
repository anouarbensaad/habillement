pipeline {

   environment {
      registry = "anouarbensaad/gpro-ci"
      // provide docker hub credentials to deploy images
      registryCredential = 'dockerhub'
      dockerImage = ''
  }
   agent any
   // stages contain one or more stage directives
   stages {
      
     stage('Checkout SCM') {          
         steps {
            checkout scm
         }
      }

      stage('Quality & Analysis') {
         /**
         * tools to select the path of tools 
         * tools used openjdk8 , Maven version 3.6.0
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
                  sh "mvn -q clean install -DskipTests"
               }catch(Exception err) {
                  sh """
                     echo [-] stage Quality & Analysis Maven Build Error.
                     echo ${err}
                  """
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }
      
      stage('Prepare') {
         // declare the path of files & Directory Path.
         environment {
            WARPATH = "./ma-gpro-war/presentation/target/ma-gpro-1.0.1.0-SNAPSHOT.war"
            WARDIR  = "Builds"
         }
         steps {
            // copy *(.jar , .war) buildings file to Builds Directory.
            sh """
               if [ -d $WARDIR ] ; then
                  echo Build directory exist.
                  cp $WARPATH $WARDIR/
               else
                  mkdir $WARDIR
                  echo Builds directory has been created
                  mv $WARPATH $WARDIR/
                  echo $WARPATH has been copied to $WARDIR directory.
               fi
            """
         }
      }

      stage('Build') {
         steps {
            script{
               // Test errors if docker image build ?.
               try{
                  // to build add docker group to jenkins user.
                  sh "whoami"
                  sh "pwd"
                  dockerImage = docker.build registry + ":$BUILD_NUMBER"
               }catch(Exception err) {
                  sh """
                     echo [-] stage Build Error.
                     echo ${err}
                  """
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }

      stage('Deploy') {
         steps {
            script {
               try{
                  docker.withRegistry( '', registryCredential ) {
                  dockerImage.push()
                  }
               }catch(Exception err) {
                  sh """
                     echo [-] stage Deploy Error.
                     echo ${err}
                  """
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }

   } //end of stages.

   /**
   * post section condition blocks: always, failure, success
   */
   post {
      always {
         // CleanUP..
         sh "docker rmi $registry:$BUILD_NUMBER" // remove the unused images from docker images.
         // clean up workspace
         //deleteDir() 
         //sh 'rm .env'
         /**
         * Send Test Email to Developper.
         */
         emailext body: 'A Test EMail',
         recipientProviders: [[$class: 'DevelopersRecipientProvider'],
         [$class: 'RequesterRecipientProvider']],
         subject: 'Test'
      }
      success {
         echo 'success :)'
      }
      failure {
         echo 'I failed :('
      }
   }
   
}
