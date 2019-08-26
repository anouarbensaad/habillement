'use strict'
angular
    .module('gpro.ficheSuiveuse', ["ui.sortable"])

    .controller(
        'suiveuseController', [
            '$scope',
            '$http',
            'suiveuseFactory',
            '$filter',
            '$log',
            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$route',

            function($scope, $http, suiveuseFactory, $filter,$log,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$route) {



                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.gammeOFCourante = {};
                $scope.listElementGammeOf = [];
                $scope.liste = [];
                $scope.liste1 = [];
                
                $scope.listCouleurs = [];
                $scope.listTailles = [];
                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
                $scope.listeMachines = [];
                $scope.listSections = [];
                $scope.listeProduitAvailable= [];
                $scope.finalOperationsList = [];
                

                $scope.listGammeOF = [];
                //$scope.produitId = $scope.listeProduitUsed[0].id;
                $scope.array = [];
                $scope.array_ = angular.copy($scope.array);
                $scope.arrayP = [];
                $scope.arrayP_ = angular.copy($scope.arrayP);

                $scope.myStyleBtnOF = {
									
                    "background-color" : "green"
             
                };
                $scope.numOfVerifier = "false";
               // $scope.eclatementCourante.ordreFabricationId  ='';


                $scope.updateListProduitUsedAanAv = function(){
                 

                  $scope.listeProduitAvailable();
                };
                

                
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
                                    
                          
                                    $scope.eclatementCourante.ordreFabricationId = resultat.id;
                                    $scope.eclatementCourante.produitReference = resultat.produitReference;
                                    $scope.eclatementCourante.produitDesignation = resultat.produitDesignation;
                                    $scope.eclatementCourante.clientAbreviation = resultat.partieInterresAbreviation;
                                
                                    $scope.numOfVerifier = "true";
                                    
                                  /*  if(type == 'EDIT')
                                        {
                                        $scope.resultatRechercheDetail.produitReference = resultat.produitReference;
                                        $scope.resultatRechercheDetail.produitDesignation = resultat.produitDesignation;
                                        //abreviation Client
                                        $scope.resultatRechercheDetail.partieInterresAbreviation = resultat.partieInterresAbreviation;
                                
                                        }
                                 */

                            
                                    
                                }


                            });


                }


                          
                $scope.ofChanged = function ()
                {
                    $scope.numOfVerifier = "false";
                    $scope.eclatementCourante.ordreFabricationId  ='';
                }
                /***************************************************
                 * Gestion des Gammes *
                 **************************************************/
                $scope.deleteValue = "oui";
                // Annuler Ajout
                $scope.cancelAddGamme = function(rowform, index,
                    id, designation, liste) {
                    // console.log("* Designation
                    // "+liste[0].designation);
                    if (angular.isDefined(id)) {

                        //console.log("DEF ID");

                        $scope.deleteValue = "non";
                        rowform.$cancel();
                        //console.log("CANCEL");

                    } else {
                        //console.log("UND ID  " + id);

                        if (designation == "") {
                            $scope.deleteValue == "oui"
//                            console.log("Designation : " +
//                                designation);
//                            console.log("$scope.deleteValueOUI : " +
//                                $scope.deleteValue);
                            liste.splice(index, 1);
                            rowform.$cancel();
                            console.log("DELETE");

                        } else {
//                            console.log("Designation :" +
//                                designation);
//                            console.log("$scope.deleteValueNON : " +
//                                $scope.deleteValue);
                            rowform.$cancel();
                            console.log("CANCEL");

                        }
                    }
                    $scope.deleteValue = "oui";
                }

                // declaration variable
                $scope.displayAlert = "sleep";

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
              //  $scope.listGammeOF();

                /** ********************************************* */
                
                /** ********************************************* */
                // Liste des produits Available
                $scope.listeProduitAvailable = function() {
                    $http
                        .get(baseUrlGpao + "/gammeof/getOrdreFabricationListAvailable")
                        .success(
                            function(dataProduit) {
                                $scope.listProduitAvailable = dataProduit;
                            });
                }
              $scope.listeProduitAvailable();

                // Liste des couleurs
                $scope.listCouleurs = function() {
                    $http
                        .get(baseUrl + "/couleur/all")
                      
                        .success(
                            function(dataCouleur) {

                                    if(dataCouleur.length>0)
                                        $scope.listCouleurs = dataCouleur;

                            });
                }

                // Liste des tailles
                $scope.listTailles = function() {
                    $http
                     
                   .get(baseUrl + "/taille/all").success(function(dataTaille) {
                        console.log(" * listeTaille " + dataTaille.length);
                        if(dataTaille.length>0)
                            $scope.listTailles = dataTaille;
                    });

                }

                // Liste des machines
                $scope.listeMachines = function() {
                    $http
                        .get(baseUrlGpao + "/machine/getAll")
                        .success(
                            function(dataPartieInteressee) {

                                $scope.listeMachines = dataPartieInteressee;
                              
                            });
                }

                // Liste des sections
                $scope.listSections = function() {
                    $http
                        .get(baseUrlGpao + "/section/getAll")
                        .success(
                            function(dataPartieInteressee) {

                                $scope.listSections = dataPartieInteressee;
                             
                            });
                }

                /******************************************************************************/
               $scope.duplicateGammeOF = function(ordreFabricationId) {
                     
                     if (ordreFabricationId == null) {
                            return;
                          }
                     else{

                     $http
                            .get(baseUrlGpao
                                     + "/gammeof/getByOrdreFabricationId:" +ordreFabricationId)
                            .success(
                                   function(gammeof) {
                                      
                                      gammeof.id = undefined;
                                      
                                      $scope.eclatementCourante.ordreFabricationNumero = gammeof.ordreFabricationNumero;
                                      $scope.eclatementCourante.clientAbreviation = gammeof.clientAbreviation;
                                      $scope.eclatementCourante.produitId = gammeof.produitId;
                                      $scope.InitializeArray();
                                     
                                   });
                 
                                     }
                 }

                //chercher les produits et les operations selon ordre de fabrication
                 $scope.duplicateFichePaquets = function(ordreFabricationId) {
                     
                     if (ordreFabricationId == null) {
                            return;
                          }
                     else{
                     $http
                            .get(baseUrlGpao
                                     + "/ficheSuiveuse/getByOrdreFabricationNum:" +ordreFabricationId)
                            .success(
                                   function(gammeof) {

                            
                           if(($scope.finalOperationsList.length == 0) || ($scope.finalPaquetsList.length == 0)){
                             
                             $scope.InitializeArray ();
                             
                           }
                           else{
                                      gammeof.id = undefined;
                                      $scope.finalOperationsList = gammeof.operationsList;
                                      $scope.finalPaquetsList = gammeof.paquetsList;
                                      
                                      angular.forEach($scope.finalPaquetsList, function(finalpaquet, key){
                                        finalpaquet.checked = false;
                                         
                                      });

                                      angular.forEach($scope.finalOperationsList, function(finalOperation, key){
                                        finalOperation.checked = false;
                                      });
                                  }
                                   
                                   });
                                
                 
                                     }
                
                 }
                  //$scope.allPaquetChecked = false;
                  var listPaqId = [];
                  $scope.allPaquetChecked = false;
                 $scope.allPaquet = function(){
                  $scope.arrayP = [];
                  if($scope.allPaquetChecked == true){
                    angular.forEach($scope.finalPaquetsList, function(finalpaquet, key){
                      finalpaquet.checked = true;
                        listPaqId.push(finalpaquet.id);
                       
                    });
                    $scope.arrayP = listPaqId;
                  console.log('======= arrayPaquet-------- : '+ JSON.stringify($scope.arrayP,null,"  "));
                 
                  }else{
                    angular.forEach($scope.finalPaquetsList, function(finalpaquet, key){
                      finalpaquet.checked = false;
                    });
                    $scope.arrayP = [];
                    listPaqId = [];
                   console.log('======= arrayPaquetDel : '+ JSON.stringify($scope.arrayP,null,"  "));
                    
                  }
                }

               // $scope.allOperationChecked = false;
                var listOpId = [];
                $scope.allOperationChecked = false;
                  $scope.allOperation = function(){
                    $scope.array = [];
                  if($scope.allOperationChecked == true){

                  angular.forEach($scope.finalOperationsList, function(finalOperation, key){
                        finalOperation.checked = true;
                        listOpId.push(finalOperation.id);
                       
                    });
                   $scope.array = listOpId;
                   console.log('======= arrayOperation------- : '+ JSON.stringify($scope.array,null,"  "));
                    
                  }else{
                    angular.forEach($scope.finalOperationsList, function(finalOperation, key){
                        finalOperation.checked = false;
                    });
                    $scope.array = [];
                    listOpId = [];
                   console.log('======= arrayOperationDel : '+ JSON.stringify($scope.array,null,"  "));
                    
                  }
                }
        
                  $scope.CheckWithShift =function(){   
                      var lastChecked = null;
               	    
                      $(document).ready(function() {
                          var $chkboxes = $('.chkbox');
                          $chkboxes.click(function(e) {
                              if(!lastChecked) {
                                  lastChecked = this;
                                  return;
                              }
              
                              if(e.shiftKey) {
                                  var start = $chkboxes.index(this);
                                  var end = $chkboxes.index(lastChecked);

                                  $chkboxes.slice(Math.min(start,end), Math.max(start,end)+ 1).prop('checked', lastChecked.checked);
              
                              }
              
                              lastChecked = this;
                          });
                      });
                      
                      }
                  
                  
               $scope.sendOperationId = function(index, idSelected, bool ){
            	   
            	   $scope.CheckWithShift();
            	   
                  console.log('======= IdOperation : '+ idSelected);
                   if(bool == true){
                   $scope.array.push(idSelected);

                   console.log('======= arrayOperation : '+ JSON.stringify($scope.array,null,"  "));
                  }else if(bool == false) {
                    $scope.array.splice($scope.array.indexOf(idSelected),1);
                  console.log('======= arrayOperationDel : '+ JSON.stringify($scope.arrayP,null,"  "));
                  }
                }

                 $scope.sendPaquetId = function(index, idSelected, bool){
                	 
                	 $scope.CheckWithShift();
                  console.log('======= IdPaquet : '+ idSelected);
                  if(bool == true){
                  $scope.arrayP.push(idSelected);
                  console.log('======= arrayPaquet : '+ JSON.stringify($scope.arrayP,null,"  "));
                   }else if(bool == false){
                    $scope.arrayP.splice($scope.arrayP.indexOf(idSelected),1);
                  console.log('======= arrayPaquetDel : '+ JSON.stringify($scope.arrayP,null,"  "));
                   
                   }
                  }

                //boutton Generer
                 $scope.Generate = function(idOf, inverse) {
                     var res = $scope.array.toString();
                     var resP = $scope.arrayP.toString();

                     console.log("inverse" + JSON.stringify(inverse,null," "));

                     var url = baseUrlGpao+"/report/ficheSuiveuse?ordreFabricationId="+idOf+"&paquetsList="+resP+"&operationsList="+res+"&type=pdf&inverse="+inverse;
            downloadService.download(url)
                .then(
                    function(success) {
                      $log.debug('success : '+ success);
                      //$scope.annulerAjout();
                    },
                    function(error) {
                      $log.debug('error : '+ error);
                    });
            
                
                 }
                
                
                $scope.InitializeArray = function() {
                     //initialisation des arrays
                     $scope.finalOperationsList = []; 
                      for(i=1; i<11; i++){
                         $scope.addElement(i);
                              } 
                      
                      $scope.finalPaquetsList = []; 
                      for(i=1; i<11; i++){
                         $scope.addElementPaquet(i);
                              } 
                     
                }
                
                
                // Annulation de l'ajout
                $scope.annulerAjout = function() {
                    

                    //init final list
                    $scope.finalOperationsList = []; 
                    for(i=1; i<11; i++){
                      $scope.addElement(i);
                    } 
                    
                    $scope.finalPaquetsList = []; 
                    for(i=1; i<11; i++){
                      $scope.addElementPaquet(i);
                    } 
                
                        $scope.eclatementCourante = {};

                        $scope.listElementGammeOf = [];
                       
                       
                  

                        $scope.updateListProduitUsedAanAv();
                        $scope.displayMode = "list";
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
                
                
                $scope.addElementPaquet = function(_ordre) {
                    


                      var tmpElement = {
                              ordre: (_ordre === undefined) ? (parseInt($scope.finalPaquetsList[$scope.finalPaquetsList.length - 1].ordre) + 1) :
                                  _ordre,
                              num : (_ordre === undefined) ? (parseInt($scope.finalPaquetsList[$scope.finalPaquetsList.length - 1].ordre) + 1) :
                                  _ordre,
                              tailleId : null,
                              couleurId : null,
                              quantite: 0,
                              numMatelas: 0,
                              checked : false
                          };
                             
                          if ($scope.finalPaquetsList
                              .indexOf(tmpElement) == -1) {
                              $scope.finalPaquetsList.push(tmpElement);


                               var t = parseInt(_ordre)+1;
                              _ordre =t;
                             


                              
                          }
                   
                };
                

                // first function to call
                var _init = function() {



















                    



                    $scope.finalOperationsList= []; 
                    for(i=1;i<11;i++){
                      $scope.addElement(i);
                    } 
                    
                    $scope.finalPaquetsList= []; 
                    for(i=1;i<11;i++){
                      $scope.addElementPaquet(i);
                    }
                    



              
                 
                };
               

         
  
              
              






















































































































                _init();

                $scope.listeMachines();
                $scope.listSections();
                
                $scope.listCouleurs();
                $scope.listTailles();

            }
        ])
    //end controller

.factory(
    'suiveuseFactory',
    function($http, $q,baseUrlGpao) {

        var backObject = {
            //listRef: _getlistRef,
            getListOperations: _getListOperations,
            getListOperationsById: _getListOperationsById
        }

        return backObject;

        //used to get list of operations from ws
        function _getListOperations() {

            var defer = $q.defer();
            $http({
                method: "GET",
                url: baseUrlGpao+"/catalogue/getAll"
            }).then(function mySucces(response) {
                //see http://stackoverflow.com/questions/7486085/copying-array-by-value-in-javascript
                //$scope.copyofOpList=   JSON.parse(JSON.stringify(operationsListWS));          
                //alert(JSON.stringify(operationsListWS, undefined, 2));
                defer.resolve(response.data);
            }, function myError(response) {
                //$scope.myWelcome = response.statusText;
                ////deferred.reject('Erreur into url ' + response);
            });

            return defer.promise;
        };

        function _getListOperationsById(val) {
        
            var defer = $q.defer();
            $http({
                method: "GET",
                url: baseUrlGpao+"/catalogue/getById:" +
                    val
            }).then(function mySucces(response) {
                //see http://stackoverflow.com/questions/7486085/copying-array-by-value-in-javascript
                //$scope.copyofOpList=   JSON.parse(JSON.stringify(operationsListWS));          
                //alert(JSON.stringify(operationsListWS, undefined, 2));
                defer.resolve(response.data);
            }, function myError(response) {
                //$scope.myWelcome = response.statusText;
               // //deferred.reject('Erreur into url ' + response);
            });

            return defer.promise;
        };

       

    })
.directive("checkboxGroup", function() {
        return {
            restrict: "A",
            link: function(scope, elem, attrs) {
                // Determine initial checked boxes
                if (scope.array.indexOf(scope.item.id) !== -1) {
                    elem[0].checked = true;
                }

                // Update array on click
                elem.bind('click', function() {
                    var index = scope.array.indexOf(scope.item.id);
                    // Add if checked
                    if (elem[0].checked) {
                        if (index === -1) scope.array.push(scope.item.id);
                    }
                    // Remove if unchecked
                    else {
                        if (index !== -1) scope.array.splice(index, 1);
                    }
                    // Sort and update DOM display
                    scope.$apply(scope.array.sort(function(a, b) {
                        return a - b
                    }));
                });
            }
        }
    })
.directive("checkboxGroupPaquets", function() {
        return {
            restrict: "A",


            link: function(scope, elem, attrs) {
                // Determine initial checked boxes
                if (scope.arrayP.indexOf(scope.item.id) !== -1) {
                    elem[0].checked = true;
                }

                // Update array on click



                elem.bind('click', function() {
                    var index = scope.arrayP.indexOf(scope.item.id);
                    // Add if checked
                    if (elem[0].checked) {
                        if (index === -1) scope.arrayP.push(scope.item.id);
                    }
                    // Remove if unchecked
                    else {
                        if (index !== -1) scope.arrayP.splice(index, 1);
                    }
                    // Sort and update DOM display
                    scope.$apply(scope.arrayP.sort(function(a, b) {
                        return a - b
                    }));
                });
            }
        }
    })    


    





;