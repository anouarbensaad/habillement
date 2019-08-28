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
      
      }

      always {

         // CleanUP..
         sh "docker rmi $registry:$BUILD_NUMBER" // remove the unused images from docker images.
         // clean up workspace
         //deleteDir() 
         //sh 'rm .env'

      }
      // Style Email Body
      success {
         echo 'I success :D'
         emailext (
            body: """
               <head>
                  <title>Build report</title>
                     <style type="text/css">
                        body
                           {margin: 0px;
                           padding: 15px;}
                        body, td, th
                           {font-family: "Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Tahoma, sans-serif;
                           font-size: 10pt;}
                        th
                           {text-align: left;}
                        h1
                           {margin-top: 0px;}
                        li
                           {line-height: 15pt;}
                        .change-add
                           {color: #272;}
                        .change-delete
                           {color: #722;}
                        .change-edit
                           {color: #247;}
                        .grayed
                           {color: #AAA;}
                        .error
                           {color: #A33;}
                        pre.console
                        {color: #333;
                           font-family: "Lucida Console", "Courier New";
                           padding: 5px;
                           line-height: 15px;
                           background-color: #EEE;
                           border: 1px solid #DDD;}
                     </style>
               </head>
               <body>
                  <h1>Build ${build.result}</h1>
                  <table>
                     <tr><th>Build URL:</th><td><a href="${rooturl}${build.url}">${rooturl}${build.url}</a></td></tr>
                     <tr><th>Project:</th><td>${project.name}</td></tr>
                     <tr><th>Date of build:</th><td>${it.timestampString}</td></tr>
                     <tr><th>Build duration:</th><td>${build.durationString}</td></tr>
                  </table>
            </body>
            """,
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
                        body
                           {margin: 0px;
                           padding: 15px;}
                        body, td, th
                           {font-family: "Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, Tahoma, sans-serif;
                           font-size: 10pt;}
                        th
                           {text-align: left;}
                        h1
                           {margin-top: 0px;}
                        li
                           {line-height: 15pt;}
                        .change-add
                           {color: #272;}
                        .change-delete
                           {color: #722;}
                        .change-edit
                           {color: #247;}
                        .grayed
                           {color: #AAA;}
                        .error
                           {color: #A33;}
                        pre.console
                        {color: #333;
                           font-family: "Lucida Console", "Courier New";
                           padding: 5px;
                           line-height: 15px;
                           background-color: #EEE;
                           border: 1px solid #DDD;}
                     </style>
               </head>
               <body>
                  <h1>Build ${build.result}</h1>
                  <table>
                     <tr><th>Build URL:</th><td><a href="${rooturl}${build.url}">${rooturl}${build.url}</a></td></tr>
                     <tr><th>Project:</th><td>${project.name}</td></tr>
                     <tr><th>Date of build:</th><td>${it.timestampString}</td></tr>
                     <tr><th>Build duration:</th><td>${build.durationString}</td></tr>
                  </table>
            </body>
            """,
            recipientProviders: [[$class: 'DevelopersRecipientProvider'],
            [$class: "RequesterRecipientProvider"]],
            subject: "${env.JOB_NAME}:${BUILD_NUMBER} - Failed",
         )
         echo "I Fail :("
      }
   }
   
}
