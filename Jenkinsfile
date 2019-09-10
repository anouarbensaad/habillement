def getReportZipFile() {
   // report format name example: report_GPROConsult_99.zip to send it with email to developper.
   return "report_${JOB_NAME}_${BUILD_NUMBER}.zip"
}

def reportWriter(report_name) {

   /**
   * add HTML Publisher plugin to jenkins.
   * jenkins -> plugin manage -> available -> HTML Publisher
   */
   publishHTML([allowMissing         : true,
               alwaysLinkToLastBuild: true,
                  keepAll              : true,
                  reportDir            : 'target\\view',
                  reportFiles          : 'index.html',
                  reportName           : report_name])
}
/*************************************************/

pipeline {

/*************************************************/

   environment {
      registry = "souhirabd/gpro-ci"
      MAVEN_OPTS = "-Xmx512m -XX:MaxPermSize=128m"

      // Jenkins credentials (add dockerhub , user&password => dockerhub account)
      registryCredential = 'dockerhub'

      dockerImage = ''
   }
   
/*************************************************/

   agent none

/*************************************************/

   stages {

      /**
      * git checkout (git plugin)
      * add git crendentials user & password
      * install Pull Request Plugin
      * add jenkins webhook and set ngrok forwarding if u in localhost, to build after push automatics. 
      */

     stage('Checkout SCM') {  
        agent any
         steps {
            checkout scm
         }
      }

      stage('Quality & Analysis') {

         /**
         * jenkins -> manage system -> global tool
         * add the path of jdk stable version 8 & Maven 3.6 
         * add Tools name : JDK , Maven.
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
                  parallel(
                        "Maven Build": {
                           sh "mvn -q clean install -DskipTests"
                        },

                        /** 
                        * Install Sonarqube scanner plugin
                        * Manage Jenkins > Manage Plugins > Available > Search for SonarQube Scanner> Install
                        * pull sonarqube image & create container, with exposing port to 9000.
                        */

                        "Sonar Scan": {
                           sh """
                           mvn sonar:sonar \
                              -Dsonar.host.url=${env.SONARQUBE_HOST} \
                           """
                        }
                  )
               }catch(Exception err) {
                  sh """
                     echo [-] stage Quality & Analysis Maven Build Error.
                     echo ${err}
                  """
                  currentBuild.result = 'FAILURE'
               }finally {
                  reportWriter('Reports')
               }
            }
         }
      }
      
      stage('Prepare') {
         environment {
            WARPATH = "./ma-gpro-war/presentation/target/ma-gpro-1.0.1.0-SNAPSHOT.war"
            WARDIR  = "Builds"
         }
         steps {
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
          agent { 
                label 'dockernode'
            }
         when {
            expression {env.GIT_BRANCH == 'origin/master'}
         }
         steps {
            /** 
            * add group docker to jenkins user
            * use this command -> usermod -aG docker jenkins
            */
            script{
               try{
                  sh "whoami"
                  sh "pwd"
                  dockerImage = docker.build(registry + ":$BUILD_NUMBER" , "./docker/app/")
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
      
         when {
            expression {env.GIT_BRANCH == 'origin/master'}
         }
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
      stage('Collect Logs') {
         steps {
         sh "echo Logs directory: ${workspace}/target/logs"
         script{
            // compress the report file to send it with email.
            zip dir: "${workspace}/target", zipFile: "$reportZipFile"
         }

         }
      }

   }

/*************************************************/

   post {

      always {
         /**
         * CleanUP in every code push
         *  1- remove the unused images from docker images.
         *  2- delete workspace from jenkins /var/lib/jenkins/workspace & envirement variable.
         */
         sh "docker rmi $registry:$BUILD_NUMBER"
         //deleteDir() 
         //sh 'rm .env'
      } 
      success {
         echo 'I success :D'
      }
      failure {
         echo "I Fail :("
      }
   }
   
}
