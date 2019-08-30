def getLogZipFile() {
   return "Logs_${JOB_NAME}_${BUILD_NUMBER}.zip"
}

def logWriter(logname) {
    // Publish HTML reports (HTML Publisher plugin)
         setLogs([allowMissing         : true,
                  alwaysLinkToLastBuild: true,
                  keepAll              : true,
                  logDir            : './target/logs',
                  logFiles          : 'index.html',
                  logName           : logname])
}

pipeline {

   environment {
      registry = "anouarbensaad/gpro-ci"
      // provide docker hub credentials to deploy images
      registryCredential = 'dockerhub'
      dockerImage = ''
      registrynotif = ''
   }
   agent any
   // stages contain one or more stage directives
   stages {
      
     stage('Checkout SCM') {          
         steps {
            checkout scm: [
               $class: 'GitSCM',
               branches: scm.branches,
               doGenerateSubmoduleConfigurations: false,
               extensions: [[$class: 'SubmoduleOption',
                              disableSubmodules: false,
                              parentCredentials: false,
                              recursiveSubmodules: true,
                              reference: '',
                              trackingSubmodules: false]],
               submoduleCfg: [],
               userRemoteConfigs: scm.userRemoteConfigs
            ]
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
                  parallel(
                        "Maven Build": {
                           sh "mvn -q clean install -DskipTests"
                        },
                        "Sonar Scan": {
                           sh "mvn sonar:sonar"
                        }
                  )
               }catch(Exception err) {
                  sh """
                     echo [-] stage Quality & Analysis Maven Build Error.
                     echo ${err}
                  """
                  currentBuild.result = 'FAILURE'
               }finally {
                  logWriter('Reports')
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
         when {
            expression {env.GIT_BRANCH == 'origin/master'}
         }
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
                  zip dir: "${workspace}/target", zipFile: "$LogZipFile" // Create a zip file of content in the workspace
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
    } 
      success {
         echo 'I success :D'
         emailext (
            body: """
               <head>
                  <title>Build report</title>
                  <style type="text/css">
                     body{margin: 0px;padding: 0px;
                        font-family:Arial, Helvetica, sans-serif}img{margin-left: 50px;float: left;width: 22px;}
                     .container{width: 1100px;margin: 100px auto;box-sizing: border-box;}
                     .container .column {width: calc(40.3% - 10px);height: 40%;float: left;padding: 0 0 20px;margin: 0 15px;box-sizing: border-box;
                     text-align: center;background: #fff;overflow: hidden;box-shadow: 0 10px 10px rgba(3, 77, 13, 0.486);transition: .5s;}
                     .title{background: #b5ffd8;padding: 20px 0;}.title .fa{color: #1f9438;font-size: 60px;}
                     .title h2{margin:0;padding: 0;font-size: 24px;color : #1f9438;font-weight: 700;text-transform: uppercase;}
                     .branch{padding: 10px;}
                     .branch h4{margin:0;padding:0;color: #323f3e;font-weight: 400;font-size: 30px;}
                     .branch h4 span{font-weight: 700;font-size : 60px;}
                     .info ul{margin: 0;padding: 0;}.info ul li{list-style: none;padding: 15px 10px;border-bottom: 1px solid rgba(0,0,0,.05);
                     font-weight: 300;}
                     .option ul li:last-child{border-bottom: none;}.option ul li .fa-check{color: #4caf50}
                  </style>
                  </head>
                     <body>
                        <div class="container">
                           <div class="column">
                              <div class="title">
                                 <h2><i><b>Build ${JOB_NAME} </b></i><h2>
                              </div>
                           <div class="branch">
                              <h4> Master </h4>
                           </div>
                           <div class="info">
                           <ul>
                              <li><span>Build URL:</span><a href="root">url</a></li>
                              <li><span>Project:</span> ${JOB_NAME}</li>
                              <li><span>Date of build:</span> ${JOB_NAME}</li>
                              <li><span>Build duration:</span> ${JOB_NAME}</li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </body>
            """,
            attachLog: true,
            compressLog: true,
            attachmentsPattern: "$logZipFile",
            recipientProviders: [[$class: 'DevelopersRecipientProvider'],
            [$class: "RequesterRecipientProvider"]],
            subject: "${env.JOB_NAME}:${BUILD_NUMBER} - Failed",
         )
      }
      failure {
         emailext (
            body: """
               <head>
                  <title>Build report</title>
                  <style type="text/css">
                     body{margin: 0px;padding: 0px;
                        font-family:Arial, Helvetica, sans-serif}img{margin-left: 50px;float: left;width: 22px;}
                     .container{width: 1100px;margin: 100px auto;box-sizing: border-box;}
                     .container .column {width: calc(40.3% - 10px);height: 40%;float: left;padding: 0 0 20px;margin: 0 15px;box-sizing: border-box;
                     text-align: center;background: #fff;overflow: hidden;box-shadow: 0 10px 10px rgba(3, 77, 13, 0.486);transition: .5s;}
                     .title{background: #b5ffd8;padding: 20px 0;}.title .fa{color: #1f9438;font-size: 60px;}
                     .title h2{margin:0;padding: 0;font-size: 24px;color : #1f9438;font-weight: 700;text-transform: uppercase;}
                     .branch{padding: 10px;}
                     .branch h4{margin:0;padding:0;color: #323f3e;font-weight: 400;font-size: 30px;}
                     .branch h4 span{font-weight: 700;font-size : 60px;}
                     .info ul{margin: 0;padding: 0;}.info ul li{list-style: none;padding: 15px 10px;border-bottom: 1px solid rgba(0,0,0,.05);
                     font-weight: 300;}
                     .option ul li:last-child{border-bottom: none;}.option ul li .fa-check{color: #4caf50}
                  </style>
                  </head>
                     <body>
                        <div class="container">
                           <div class="column">
                              <div class="title">
                                 <h2><i><b>Build ${JOB_NAME} </b></i><h2>
                              </div>
                           <div class="branch">
                              <h4> Master </h4>
                           </div>
                           <div class="info">
                           <ul>
                              <li><span>Build URL:</span><a href="root">url</a></li>
                              <li><span>Project:</span> ${JOB_NAME}</li>
                              <li><span>Date of build:</span> ${JOB_NAME}</li>
                              <li><span>Build duration:</span> ${JOB_NAME}</li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </body>
            """,
            attachLog: true,
            compressLog: true,
            attachmentsPattern: "$logZipFile",
            recipientProviders: [[$class: 'DevelopersRecipientProvider'],
            [$class: "RequesterRecipientProvider"]],
            subject: "${env.JOB_NAME}:${BUILD_NUMBER} - Failed",
         )
         echo "I Fail :("
      }
   }
   
}
