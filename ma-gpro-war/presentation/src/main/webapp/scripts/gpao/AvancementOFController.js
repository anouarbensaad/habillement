/**
 * $scope.formToSave : the final object $scope.selectedRef : object selectionner
 * de la liste de reference 
 * operationsListWS : liste des operation recupérer de notre WS 
 * $scope.refListWS : liste des ref recupérer de notre WS
 * $scope.copyofOpList : une copie de notre operationsListWS et utiliser pour l'autosaisie tmpElement : cette variable représente une ligne de notre tableau d'operation 
 * $scope.finalOperationsList : la liste final que sera envoyer à notre BE
 */

'use strict'

angular
    .module('gpro.avancementOF', ["ui.sortable"])
   
    .controller(
        'AvancementOFController', [
            '$scope',
            '$http',
            '$filter',
            '$log',
            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$route',

            function($scope, $http, $filter,$log,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$route) {



                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.avctOFCourante = {};
                $scope.listElementGammeOf = [];
                $scope.OFListWS = [];

                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
               
                $scope.myStyleBtnOF = {
									
                  "background-color" : "green"
           
              };
              $scope.numOfVerifier = "false";

              $scope.validerOF = function (numOf,type) {

                  //console.log("sssssssssssssssssssssssssss");

                  if (numOf == '') return;

                   $scope.numOfVerifier = "false";

                  $http
                      .post(
                          baseUrlGpao +
                          "/ordreFabrication/getByNum", numOf
                      )
                      .success(
                          function (resultat) {
                              if (resultat == '') {
                                  $scope.numOfVerifier = "false";
                              } else {
                                  
                        
                                
                                  $scope.numOfVerifier = "true";
                                  
                              if(type == 'SEARCH')
                                      {
                                      $scope.avctOFCourante.ordreFabricationId = resultat.id;
                                      
                                      $scope.avctOFCourante.clientAbreviation = resultat.partieInterresAbreviation;

                                      $scope.avctOFCourante.produitId = resultat.produitId;
                                      $scope.avctOFCourante.produitReference = resultat.produitReference;
                                      $scope.avctOFCourante.produitDesignation = resultat.produitDesignation;

                                      }
                           

                          
                                  
                              }


                          });


              }
              
              $scope.ofChanged = function ()
              {
                  $scope.numOfVerifier = "false";
                  $scope.avctOFCourante.ordreFabricationId = '';
              }
       
                
              
            
                
                //Mise à jour de a liste des Gammes OF après ajout dans l'onglet "OF"
                $scope.updateListOF = function(){
                  
                
                  
                   $http




                     .get(baseUrlGpao + "/ordreFabrication/all")
                     .success(
                         function(dataProduit) {
                        	 
                        	 
                             $scope.listavctOF = dataProduit;
                            
                         });
                }
             //   $scope.updateListOF();
                
           

                // declaration variable
                $scope.displayAlert = "sleep";

                

                /** ********************************************* */
                // Liste des produits
                $scope.listeProduit = function() {
                    $http
                        .get(baseUrl + "/produit/all")
                        .success(
                            function(dataProduit) {
                                $scope.listProduitDrop = dataProduit;
                            });
                }
               // $scope.listeProduit();

                /** ********************************************* */
                
                /** ********************************************* */
                // Liste des Gammes OF
                $scope.listGammeOF = function() {
                  

                    $http
                        .get(baseUrlGpao + "/ordreFabrication/all")
                        .success(
                            function(dataProduit) {
                                $scope.listGammeOF = dataProduit;
                            });
                }
             //   $scope.listGammeOF();

       
        
        /******************************************************************************/
        //http://localhost:8080/ma-gpro-gpao-rest/report/avancementOF?ordreFabricationId=29&type=pdf
        
        //generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
              $scope.download = function(idOf) {
                var url = baseUrlGpao+"/report/avancementOF?ordreFabricationId=" + idOf+"&type=pdf";
                downloadService.download(url)
                    .then(
                        function(success) {
                          $log.debug('success : '+ success);
                          //$scope.annulerAjout();
                        },
                        function(error) {
                          $log.debug('error : '+ error);
                        });
              };
        //chercher les produits selon gamme OF
         $scope.rechercheavctOF = function(ordreFabricationId) {
                
           if (ordreFabricationId == null) {
                return;
              }
           
           else{
                     $http
                            .get(baseUrlGpao
                                     + "/gammeof/validateByOrdreFabricationId:" +ordreFabricationId)
                            .success(
                                   function(avctOF) {
                                	  
                                	   $scope.updateListOF();
                                	   if(avctOF.listElementGammeOf == null){
                                           
                                           $scope.InitializeArray();
                                           
                                          }
                                	   else{
                                  
                                       $scope.viderSubLists ();
                       
                                      //console.log( "-----avctOF.listElementGammeOf---------------:", JSON.stringify(avctOF.listElementGammeOf ,null, "  "));
                    
                                     // $scope.classifierlistElementGammeOf(avctOF.listElementGammeOf);
                                      
                                      avctOF.id = undefined;
                                      
                                      $scope.avctOFCourante.ordreFabricationNumero = avctOF.ordreFabricationNumero;
                                    
                                      
                                      $scope.avctOFCourante.nbOperationProduit = avctOF.nbOperationProduit;
                                      $scope.avctOFCourante.tempsTotalProduit = avctOF.tempsTotalProduit;
                                      
                                      $scope.avctOFCourante.clientAbreviation = avctOF.clientAbreviation;
                                      $scope.avctOFCourante.tempsTotal = avctOF.tempsTotal;
                                      //console.log( "-----avctOF.tempsTotal---------------:", JSON.stringify(avctOF.tempsTotal ,null, "  "));
                                      $scope.avctOFCourante.nbOperation = avctOF.nbOperation;
                                      //console.log( "-----avctOF.nbOperation---------------:", JSON.stringify(avctOF.nbOperation ,null, "  "));
                                      $scope.avctOFCourante.produitId = avctOF.produitId;
                                      angular.forEach(avctOF.listElementGammeOf, function(elementGamme, key){
                                          elementGamme.id = undefined;
                                         
                                      });
                                      
                                      $scope.finalOperationsList = avctOF.listElementGammeOf;
                                      }
                                   });
                                
                                       
                             }
        
         }
        
       /* $scope.classifierlistElementGammeOf = function(listElementGammeOf){
          //console.log( "-----listElementGammeOf ---------------:", JSON.stringify(listElementGammeOf ,null, "  "));
              
            angular.forEach(listElementGammeOf, function(elementGamme, key){
              $scope.finalOperationsList.push(elementGamme);
            });
            console.log( "*******finalOperationsList*******:", JSON.stringify($scope.finalOperationsList.listJourProductionGammeOf ,null, "  "));
          
            
              }*/
        
              $scope.viderSubLists = function(){
          $scope.finalOperationsList = [];
              }
    

              // used to add new element into list of table

              $scope.addElement = function(_ordre) {
                   

                    

                  var tmpElement = {
                      ordre: (_ordre === undefined) ? (parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1) :
                          _ordre,
                      code : '',
                      designation : '',
                      temps: 0,
                      pdh: 0,
                      sectionId : null,
                      machineId : null,
                      observations: '',
                      operationId : null,
                      disable: false,
                      comptage : false,
                      checked : false
                  };
                     
                  if ($scope.finalOperationsList
                      .indexOf(tmpElement) == -1) {
                      $scope.finalOperationsList.push(tmpElement);

                       var t = parseInt(_ordre)+1;
                      _ordre =t;
                     


                      
                  }
                 
              };
                
                
                
                $scope.InitializeArray = function() {
                     //initialisation des arrays
                     $scope.finalOperationsList = []; 
                      for(i=1; i<11; i++){
                         $scope.addElement(i);
                              } 
                     
                }
                // Annulation de l'ajout
                $scope.annulerAjout = function() {
                  $scope.avctOFCourante = {
                		  
                		  "ordreFabricationId": "",
						  "clientAbreviation": "",
						  "tempsTotal": "",
						  "nbOperation": "",
						  "produitId": "",
						
                  };

                    //init final list
                    $scope.finalOperationsList = []; 
                    for(i=1; i<11; i++){
                      $scope.addElement(i);
                    } 
                        $scope.listElementGammeOf = [];
                       
                       
                     
                        $scope.displayMode = "list";
                    }
                
                   
            

              /*** PDF ***/
              //boutton Generer
                 $scope.Generate = function(idOf) {
                     var res = $scope.array.toString();
                     var resP = $scope.arrayP.toString();
                                    
                     var url = baseUrlGpao+"/report/ficheSuiveuse?ordreFabricationId="+idOf+"&paquetsList="+resP+"&operationsList="+res+"&type=pdf";
            downloadService.download(url)
                .then(
                    function(success) {
                      $log.debug('success : '+ success);
                      
                    },
                    function(error) {
                      $log.debug('error : '+ error);
                    });
            
                
                 }
              //generer rapport de tous les Ordre de Fabrication. mode : List
              $scope.downloadAllavctOF = function(avctOFCourante) {
                
                var url;
                if(avctOFCourante.produitId == null){
                  avctOFCourante.produitId = "";
                }
                if(avctOFCourante.ordreFabricationId == null){
                  avctOFCourante.ordreFabricationId = "";
                }

                //console.log("------- avctOFCourante " + JSON.stringify(avctOFCourante, null, "  ") );
                    url = baseUrlGpao + "/report/listGammeOF?ordreFabricationId="+avctOFCourante.ordreFabricationId
                           + "&produitId=" + avctOFCourante.produitId
                           + "&tempsTotalMin=" + avctOFCourante.tempsTotalMin
                           + "&tempsTotalMax="+avctOFCourante.tempsTotalMax
                           + "&machineId="+avctOFCourante.machineId
                           + "&sectionId="+avctOFCourante.sectionId
                           + "&type=pdf";
                  
                       // console.log("-- URL--- :" + url );
                 downloadService.download(url).then(
                     function(success) {
                      //$log.debug('success : ' + success);
                     }, function(error) {
                      //$log.debug('error : ' + error);
                     });
               };


              /*** PDF ***/
              //generer rapport de tous les Ordre de Fabrication. mode : List
              $scope.downloadAllavctOF = function(avctOFCourante) {
                
                var url;
                if(avctOFCourante.produitId == null){
                  avctOFCourante.produitId = "";
                }
                if(avctOFCourante.ordreFabricationId == null){
                  avctOFCourante.ordreFabricationId = "";
                }

               // console.log("------- avctOFCourante " + JSON.stringify(avctOFCourante, null, "  ") );
                    url = baseUrlGpao + "/report/listGammeOF?ordreFabricationId="+avctOFCourante.ordreFabricationId
                           + "&produitId=" + avctOFCourante.produitId
                           + "&tempsTotalMin=" + avctOFCourante.tempsTotalMin
                           + "&tempsTotalMax="+avctOFCourante.tempsTotalMax
                           + "&machineId="+avctOFCourante.machineId
                           + "&sectionId="+avctOFCourante.sectionId
                           + "&type=pdf";
                  
                       // console.log("-- URL--- :" + url );
                 downloadService.download(url).then(
                     function(success) {
                      //$log.debug('success : ' + success);
                     }, function(error) {
                      //$log.debug('error : ' + error);
                     });
               };
                
               



                // first function to call
                var _init = function() {
                        
                    $scope.finalOperationsList= []; 
                    for(i=1;i<11;i++){
                      $scope.addElement(i);
                    } 
               
                  
                };
               

                _init();

            }
        ])
    //end controller

;