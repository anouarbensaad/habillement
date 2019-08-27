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
                  sh "mvn -q clean install -DskipTests"
               }catch(Exception err) {
                  echo "Error maven build and test."
                  currentBuild.result = 'FAILURE'
               }
            }
         }
      }
      
      stage('Prepare building files.') {
         steps {
            script {
               WARPATH = "ma-gpro-planning-1.0.1.0-SNAPSHOT.war"
               WARDIR  = "Builds"
            }
            
            // copy *(.jar , .war) buildings file to Builds Directory.
            
            sh """
               if [ -d ${WARDIR} ] ; then
                  echo Build directory exist.
                  cp ./ma-gpro-war/presentation/target/${WARPATH} ${WARDIR}/
               else
                  mkdir ${WARDIR}
                  echo Builds directory has been created
                  cp ./ma-gpro-planning-war/presentation/target/${WARPATH} ${WARDIR}/
                  echo ${WARPATH} has been copied to ${WARDIR} directory.
               fi
            """
         }
      }

      stage('Building image') {
         steps {
            script{
               // Test errors if docker image build ?.
               try{
                  // to build add docker group to jenkins user.
                  sh "whoami"
                  sh "pwd"
                  myService = docker.build("gpro-ci:latest", ".")
               }catch (Exception err) {
                  sh "echo ${err}"
               }
            }
         }
      }
/*
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
