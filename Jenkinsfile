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
      registry = "anouarbensaad/gpro-ci"

      // Jenkins credentials (add dockerhub , user&password => dockerhub account)
      registryCredential = 'dockerhub'

      dockerImage = ''
   }
   
/*************************************************/

   agent any

/*************************************************/

   stages {

      /**
      * git checkout (git plugin)
      * add git crendentials user & password
      * install Pull Request Plugin
      * add jenkins webhook and set ngrok forwarding if u in localhost, to build after push automatics. 
      */

     stage('Checkout SCM') {          
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
/*                  parallel(
                        "Maven Build": {
*/
sh "mvn -q clean install -DskipTests"
           //             },

                        /** 
                        * Install Sonarqube scanner plugin
                        * Manage Jenkins > Manage Plugins > Available > Search for SonarQube Scanner> Install
                        * pull sonarqube image & create container, with exposing port to 9000.
                        */

 /*                       "Sonar Scan": {
                           sh """
                           mvn sonar:sonar \
                              -Dsonar.host.url=http://192.168.1.13:9000 \
                           """
                        }
   
   )*/
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
/*
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
  */
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

         /**
         * SMTP Server Configurations :
         *   Manage Jenkins > Configure System > search for “Extended E-mail Notification”.
         *     1- SMTP server : smtp.gmail.com
         *     2- Allow : Use SMTP Authentification.
         *     3- sender email & Password. 
         */


         emailext (
            // in body html & css code.
            body: """
               <head>
                  <style type="text/css">
                     body{margin: 0px;padding: 0px;      font-family:Arial, Helvetica, sans-serif}img{margin-left: 50px;float: left;width: 22px;}   .container{width: 1100px;margin: 100px auto;box-sizing: border-box;}   .container .column {width: calc(40.3% - 10px);height: 40%;float: left;padding: 0 0 20px;margin: 0 15px;box-sizing: border-box;   text-align: center;background: #fff;overflow: hidden;box-shadow: 0 10px 10px rgba(3, 77, 13, 0.486);transition: .5s;}   .title{background: #b5ffd8;padding: 20px 0;}.title .fa{color: #1f9438;font-size: 60px;}   .title h2{margin:0;padding: 0;font-size: 24px;color : #1f9438;font-weight: 700;text-transform: uppercase;}   .branch{padding: 10px;}   .branch h4{margin:0;padding:0;color: #323f3e;font-weight: 400;font-size: 30px;}   .branch h4 span{font-weight: 700;font-size : 60px;}   .info ul{margin: 0;padding: 0;}.info ul li{list-style: none;padding: 15px 10px;border-bottom: 1px solid rgba(0,0,0,.05);   font-weight: 300;}   .option ul li:last-child{border-bottom: none;}.option ul li .fa-check{color: #4caf50}
                  </style>
               </head>
               <body>
                  <div class="container"><div class="column"><div class="title"><h2><i><b>Build ${JOB_NAME} </b></i><h2></div><div class="branch"><h4> Master </h4></div><div class="info"><ul><li><span>Build URL:</span><a href="root">url</a></li><li><span>Project:</span> ${JOB_NAME}</li><li><span>Date of build:</span> ${JOB_NAME}</li><li><span>Build duration:</span> ${JOB_NAME}</li></ul></div></div></div>
               </body>
            """,
            attachLog: true,
            compressLog: true,
            attachmentsPattern: "$reportZipFile",
            recipientProviders: [[$class: 'DevelopersRecipientProvider'],
            [$class: "RequesterRecipientProvider"]],
            subject: "${env.JOB_NAME}:${BUILD_NUMBER} - Failed",
         )
      }
      failure {
         emailext (
            body: """
               <head>
                  <style type="text/css">
                     body{margin: 0px;padding: 0px;      font-family:Arial, Helvetica, sans-serif}img{margin-left: 50px;float: left;width: 22px;}   .container{width: 1100px;margin: 100px auto;box-sizing: border-box;}   .container .column {width: calc(40.3% - 10px);height: 40%;float: left;padding: 0 0 20px;margin: 0 15px;box-sizing: border-box;   text-align: center;background: #fff;overflow: hidden;box-shadow: 0 10px 10px rgba(3, 77, 13, 0.486);transition: .5s;}   .title{background: #b5ffd8;padding: 20px 0;}.title .fa{color: #1f9438;font-size: 60px;}   .title h2{margin:0;padding: 0;font-size: 24px;color : #1f9438;font-weight: 700;text-transform: uppercase;}   .branch{padding: 10px;}   .branch h4{margin:0;padding:0;color: #323f3e;font-weight: 400;font-size: 30px;}   .branch h4 span{font-weight: 700;font-size : 60px;}   .info ul{margin: 0;padding: 0;}.info ul li{list-style: none;padding: 15px 10px;border-bottom: 1px solid rgba(0,0,0,.05);   font-weight: 300;}   .option ul li:last-child{border-bottom: none;}.option ul li .fa-check{color: #4caf50}
                  </style>
               </head>
               <body>
                  <div class="container"><div class="column"><div class="title"><h2><i><b>Build ${JOB_NAME} </b></i><h2></div><div class="branch"><h4> Master </h4></div><div class="info"><ul><li><span>Build URL:</span><a href="root">url</a></li><li><span>Project:</span> ${JOB_NAME}</li><li><span>Date of build:</span> ${JOB_NAME}</li><li><span>Build duration:</span> ${JOB_NAME}</li></ul></div></div></div>
               </body>
            """,
            attachLog: true,
            compressLog: true,
            attachmentsPattern: "$reportZipFile",
            recipientProviders: [[$class: 'DevelopersRecipientProvider'],
            [$class: "RequesterRecipientProvider"]],
            subject: "${env.JOB_NAME}:${BUILD_NUMBER} - Failed",
         )
         echo "I Fail :("
      }
   }

/*************************************************/

   options {
      // keep just only last 10 builds
      buildDiscarder(logRotator(numToKeepStr: '10'))
      // job expire after 60 minutes
      timeout(
         time: 60,
         unit: 'MINUTES'
      )
   }
   
}
