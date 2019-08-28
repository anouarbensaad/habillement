pipeline {

   environment {
      registry = "anouarbensaad/gpro-ci"
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

      stage('Maven Build & Analysis') {
      /**
      *  tools be used : 
      *     openjdk8
   *        maven3.6.0
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
                  echo "Error maven build and test."
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }
      
      stage('Prepare building files.') {
         environment {
            WARPATH = "ma-gpro-1.0.1.0-SNAPSHOT.war"
            WARDIR  = "Builds"
         }
         steps {
            // copy *(.jar , .war) buildings file to Builds Directory.
            sh """
               if [ -d $WARDIR ] ; then
                  echo Build directory exist.
                  cp ./ma-gpro-war/presentation/target/$WARPATH $WARDIR/
               else
                  mkdir $WARDIR
                  echo Builds directory has been created
                  mv ./ma-gpro-planning-war/presentation/target/$WARPATH $WARDIR/
                  echo $WARPATH has been copied to $WARDIR directory.
               fi
            """
         }
      }

      stage('Build Image') {
         steps {
            script{
               // Test errors if docker image build ?.
               try{
                  // to build add docker group to jenkins user.
                  sh "whoami"
                  sh "pwd"
                  dockerImage = docker.build("$registry:$BUILD_NUMBER", ".")
               }catch (Exception err) {
                  sh "echo ${err}"
               }
            }
         }
      }

      stage('Deploy Image') {
         steps {
            script {
               docker.withRegistry( '', registryCredential ) {
               dockerImage.push()
               }
            }
         }
      }

   } //end of stages.

   post {
      always {
         // CleanUP..
         // remove the unused images from docker images.
         sh "docker image rm -f $registry:$BUILD_NUMBER"
         // remove .war files , & Build Directory.
         sh "rm -rf ./Builds"
         // clean up workspace
         //deleteDir() 
      }
      success {
         echo 'success :)'
      }
      failure {
         echo 'I failed :('
      }
   }
   
}
