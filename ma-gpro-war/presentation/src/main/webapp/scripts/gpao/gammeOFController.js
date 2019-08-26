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
    .module('gpro.gammeOF', ["ui.sortable"])
    .filter('unique', function() {
   return function(collection, keyname) {
     
      var output = [], 
          keys = [];

      angular.forEach(collection, function(item) {
          var key = item[keyname];
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);

          }
      });
      return output;
   };
})
    .controller(
        'gammeOFController', [
            '$scope',
            '$http',
            'gammeOfFactory',
            '$filter',

            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$route',

            function($scope, $http, gammeOfFactory, $filter,
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
                $scope.OFListWS = [];

                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
                $scope.listeMachines = [];
                $scope.listSections = [];
                $scope.listeProduitAvailable= [];
               
                $scope.listGammeOF = [];

                $scope.myStyleBtnOF = {
									
                    "background-color" : "green"
             
                };
                $scope.numOfVerifier = "false";
                $scope.gammeOFCourante.ordreFabricationId  ='';

        //$scope.produitId = $scope.listeProduitUsed[0].id;
                
               
                
                $scope.updateListProduitUsedAanAv = function(){
                 
                  $scope.listeProduitAvailable();
                };
                
                //Mise à jour de a liste des Gammes OF après ajout dans l'onglet "OF"
                $scope.updateListOF = function(){
                  
                
                  
                   $http




                     .get(baseUrlGpao + "/ordreFabrication/all")
                     .success(
                         function(dataProduit) {
                             $scope.listGammeOF = dataProduit;
                             //console.log(" $scope.listGammeOF "+JSON.stringify($scope.listGammeOF));
                         });
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
                           // console.log("DELETE");

                        } else {
//                            console.log("Designation :" +
//                                designation);
//                            console.log("$scope.deleteValueNON : " +
//                                $scope.deleteValue);
                            rowform.$cancel();
                            //console.log("CANCEL");

                        }
                    }
                    $scope.deleteValue = "oui";
                }

                // declaration variable
                $scope.displayAlert = "sleep";

                // Rechercher Gamme
                $scope.rechercheGammeOF = function(gammeOFCourante) {
                   
                //  $scope.updateListOF();
                  

                    $http
                        .post(
                            baseUrlGpao +
                            "/gammeof/rechercheMulticritere",
                            gammeOFCourante)

                    .success(
                        function(resultat) {

                            $scope.myData = resultat.list;
                            // data, page,pageSize
                           // console.log("-----Data Gamme Produit:"+JSON.stringify($scope.myData));
                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.rechercheGammeOFForm
                                .$setPristine();
                            $scope.displayMode = "rechercher";
                            $scope.displayAlert = "sleep";
                        });
                    
//                       }
                }

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
                $scope.listeProduit();

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
               // $scope.listGammeOF();

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
              //$scope.listeProduitAvailable();

                /** ********************************************* */
             

                //$scope.rechercheGammeOF({});
               
                // ** Ajout Gamme
                $scope.AffectationGamme = function(gamme) {
                  
                  
                  

                   if(Object.keys(gamme).length == 0){
                    
                       return;
                }
                   else{
                  



                 // $scope.updateListOF();  
                  $scope.UpdateOperationsAll();
                 
                  





                  $scope.gammeOFCourante = {};
                  $scope.InitializeArray();
                    
                    
                    $scope.creationGammeOFForm.$setPristine();
                   


                    $scope.displayMode = "edit";

                    $scope.EnableGamme();
                   
                    
                    
                    $('.update').hide();
                    $('.add').show();
                    $('.generate').hide();
                    
                   }

                }

                
             
	
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
                                    
                          
                                    $scope.gammeOFCourante.ordreFabricationId = resultat.id;
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


                $scope.validerAvailableOF = function (numOf,type) {

                    //console.log("sssssssssssssssssssssssssss");

                    if (numOf == '') return;

                     $scope.numOfVerifier = "false";


                    $http
                        .post(
                            baseUrlGpao +
                            "/ordreFabrication/getByNumForGamme", numOf
                        )
                        .success(
                            function (resultat) {
                                if (resultat == '') {
                                    $scope.numOfVerifier = "false";
                                } else {
                                    
                          
                                    $scope.gammeOFCourante.ordreFabricationId = resultat.id;
                                    $scope.numOfVerifier = "true";
                                    
                                    
                                    
                                    
                                    $scope.duplicategammeof(resultat.id);
                                    
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
                    $scope.gammeOFCourante.ordreFabricationId  ='';
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
                    // .get("http://192.168.1.8:8080/ma-gpro-gpao-rest/catalogue/getAll")
                        .get(baseUrlGpao + "/section/getAll")
                        .success(
                            function(dataPartieInteressee) {
//                                console

//                                    .log("liste sections Gamme: " +
//                                        dataPartieInteressee.length);
                                $scope.listSections = dataPartieInteressee;
                                // console.log("liste des

                                // sections:"+JSON.stringify($scope.listSections));
                            });
                }
                
               
                var _deleteCodeOccurences = function(item) {
                   


    var _temp = $scope.copyofOpList;
    for (var i = 0; i < item.length; i++){      
        for (var j = _temp.length-1; j >=0; j--) {
           if (item[i].operationCode == _temp[j].code) {
               _temp.splice(j, 1);
              
           } 
        } 
    } 
    $scope.copyofOpList = _temp;
    //console.log("_temps : " +_temp);
                };
               
                
                // Ajout et Modification Gamme
                $scope.modifierOuCreerGamme = function() {
                     //debugger;
                     

                    var index = this.row.rowIndex;
                    

                  
                    // getGamme
                    $http
                        .get(
                            baseUrlGpao +
                            "/gammeof/getById:" +
                            $scope.myData[index].id)
                        .success(
                            function(datagetGamme) {
                
                            //debugger;

                              
                                $scope.gammeOFCourante.nbOperationProduit = datagetGamme.nbOperationProduit;
                                $scope.gammeOFCourante.tempsTotalProduit = datagetGamme.tempsTotalProduit;
                                $scope.gammeOFCourante.produitId = datagetGamme.produitId;
                                $scope.finalOperationsList = datagetGamme.listElementGammeOf;   
                                $scope.myData[index].listElementGammeOf = $scope.finalOperationsList;
                                

                               _deleteCodeOccurences($scope.finalOperationsList);
                              
                                
                            });
                    
                    $scope.gammeOFCourante = $scope.myData[index] ? angular
              .copy($scope.myData[index])
              : {};

          $scope.displayMode = "edit";

                    
           $scope.DisableGamme();
                    
                    
                    $('.update').show();
                    $('.add').hide();
                    $('.generate').show();
                }
                
               

                //enable Elements
                $scope.EnableGamme = function() {
                  
                   $scope.disableSelect = false;
                     $scope.inactive =true;
                     
                     $scope.tempsTotalOF = true;
                     $scope.nbOPOF = true;
                     $scope.tempsTotalP = true;
                     $scope.nbOPP = true;
                     
                     $scope.nbOPP = true;
                     $scope.ordreFab = true;
                     $scope.clientAbr = true;
                     $scope.RefProd = true;
                     $scope.DesigProd = true;
                
                }
                
                
                //disable Elements
               $scope.DisableGamme = function() {
                  
                $scope.disableSelect = true;
                    $scope.inactive = true;
                    $scope.tempsTotalOF = true;
                    $scope.nbOPOF = true;
                    $scope.tempsTotalP = true;
                    
                    $scope.nbOPP = true;
                    $scope.ordreFab = true;
                    $scope.clientAbr = true;
                    $scope.RefProd = true;
                    $scope.DesigProd = true;
                  
                }
                
                
                
                
             // Sauvegarder Gamme
        $scope.sauvegarderAjout = function(gamme) {
                    

           if(gamme.ordreFabricationId == ""){
                 
               $scope.creationGammeOFForm.$setPristine();
          }
           
           else{
     
          if (angular.isDefined(gamme.id)) {
            
            $scope.updateGamme(gamme);
            
          
          } else {
            $scope.saveForm();
            $scope.creerGamme(gamme);
            
          }
          
          }

        }
        
        /******************************************************************************/
        //chercher les produits selon gamme similaire
         $scope.duplicategammeof = function(ordreFabricationId) {
             
          // debugger;
           
           if (ordreFabricationId == null) {
                return;
              }
           
           else{
                 

                     $http
                            .get(baseUrlGpao
                                     + "/gammeof/getByOrdreFabricationId:" +ordreFabricationId)
                            .success(
                                   function(gammeof) {
                                     
                                     //debugger;
                                     
                                     if(gammeof.listElementGammeOf  == null){
                                           
                                           $scope.InitializeArray();
                                           
                                          }
                                     else{

                                       $scope.viderSubLists ();
                       
                    
                                      $scope.classifierlistElementGammeOf(gammeof.listElementGammeOf);
                                      
                                      gammeof.id = undefined;
                                      
                                      $scope.gammeOFCourante.ordreFabricationNumero = gammeof.ordreFabricationNumero;
                                    
                                      
                                      $scope.gammeOFCourante.nbOperationProduit = gammeof.nbOperationProduit;
                                      $scope.gammeOFCourante.tempsTotalProduit = gammeof.tempsTotalProduit;
                                      
                                      $scope.gammeOFCourante.clientAbreviation = gammeof.clientAbreviation;
                                      $scope.gammeOFCourante.tempsTotal = gammeof.tempsTotal;
                                      $scope.gammeOFCourante.nbOperation = gammeof.nbOperation;
                                      $scope.gammeOFCourante.produitId = gammeof.produitId;
                                      
                                      $scope.finalOperationsList = gammeof.listElementGammeOf;
                                      $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                                      
                                      }
                                   });
                                
                                       
                             }
        
         }
        
        $scope.classifierlistElementGammeOf = function(listElementGammeOf){
          
            angular.forEach(listElementGammeOf, function(elementGamme, key){
             // console.log( "-----classifierlistElementGammeOf 1---------------:", JSON.stringify(listElementGammeOf ,null, "  "));
              //console.log( "-----classifierlistElementGammeOf 2---------------:", JSON.stringify(elementGamme ,null, "  "));
              $scope.finalOperationsList.push(elementGamme);
            });
            
            
              }
        
              $scope.viderSubLists = function(){
          $scope.finalOperationsList = [];
              }
              
              /******************************************************************************/


                // Mise à jour des Gammes
          $scope.updateGamme= function(gamme) {
          $scope.UpdateOperations();
         
          
          gamme.tempsTotal = $scope.getTotalTemps();
          gamme.nbOperation = $scope.getTotalNbOperations();
          
             var finalFormToSave = {
                            
                            finalOperationsList: []
                        };

                        for (var index = 0; index < $scope.finalOperationsList.length; index++) {
                             var index1 = index+1;
                            var tmpElement = {
                                ordre: index1,
                                id:$scope.finalOperationsList[index].id,
                                temps: $scope.finalOperationsList[index].temps,
                                operationDesignation: $scope.finalOperationsList[index].operationDesignation,
                                operationCode: $scope.finalOperationsList[index].operationCode,
                                code: $scope.finalOperationsList[index].code,
                                pdh: $scope.finalOperationsList[index].pdh,
                                sectionId: $scope.finalOperationsList[index].sectionId,
                                machineId: $scope.finalOperationsList[index].machineId,
                                comptage: $scope.finalOperationsList[index].comptage,
                                observations: $scope.finalOperationsList[index].observations,
                                operationId : $scope.finalOperationsList[index].operationId
                            };

                            finalFormToSave.finalOperationsList
                                .push(tmpElement);
                           
                        }
                        //call ws to save the data 

                        $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                        gamme.listElementGammeOf = $scope.finalOperationsList;
                        
                        "use strict";

                        $scope.DeleteEmptyRows(gamme.listElementGammeOf);
                      



//console.log("======= Update : Objet : "+ JSON.stringify (gamme,null, " "));

             $http
                 .put(
                     baseUrlGpao +
                     "/gammeof/update",
                     gamme)
                 .success(
                     function(gammeModifiee) {
                       

                         // TODO Code à revoir
                         for (var i = 0; i < $scope.myData.length; i++) {

                             if ($scope.myData[i].id == gammeModifiee) {
                                 $scope.myData[i] = gammeModifiee;
                                 
                                 break;
                             }



                         }
                         // TODO getId
                         $scope.UpdateOperations();
                         $scope.annulerAjout();
                        
                        
                     });



             
             }
        
        
        
        $scope.UpdateOperations = function() {
          
           $http
                     .get(
                         baseUrlGpao +
                         "/catalogue/getAll")
                     .success(
                         function(newGamme) {
                            
                             // TODO getId
                           $scope.copyofOpList = newGamme;
                             //$scope.annulerAjout();
                             
                          
                         });
          
            }
        
        $scope.UpdateOperationsAll = function() {
            

             $http
                       .get(
                           baseUrlGpao +
                           "/catalogue/getAll")
                       .success(
                           function(newGamme) {
                              //debugger;

                               // TODO getId
                             $scope.copyofOpList = newGamme;
                             
                               
                            
                           });
            
              }
        
                    // Création Gamme





                $scope.creerGamme = function(gamme) {
                 
                  $scope.UpdateOperations();
                  
                  //console.log( "-----gamme.listElementGammeOf lors de creation---------------:", JSON.stringify(gamme.listElementGammeOf ,null, "  ") );
                  
                  $scope.gammeOFCourante.clientAbreviation = gamme.clientAbreviation;
                  gamme.listElementGammeOf = $scope.finalOperationsList;
                  
                  "use strict";

                  $scope.DeleteEmptyRows(gamme.listElementGammeOf);
           
                    $http
                        .post(
                            baseUrlGpao +
                            "/gammeof/create",
                            gamme)
                        .success(
                            function(newGamme) {

                              // TODO getId
                                $scope.UpdateOperations();
                                
                                $scope.annulerAjout();
                                                                
                            });

                }
                
              //elements à supprimer
                $scope.DeleteEmptyRows = function(listelements){
                  

                   for (var i = listelements.length-1; i >=0; i--) {
                         if (listelements[i].operationDesignation == "") {
                             listelements.splice(i, 1);
                                      }
                         
                   }
            


                }
        



                $scope.CreateOp = function(nvOp){
                  
                    $http
                      .post(
                          baseUrlGpao +
                          "/catalogue/create",
                          nvOp)
                      .success(
                          function(newGamme) {
                            
                            $scope.copyofOpList.push(nvOp);
                              // TODO getId
                              $scope.UpdateOperations();
                              
                              
                              
                          });
        }
                
                
                $scope.getTotalTemps = function(){
            var total = 0;
            for(var i = 0; i < $scope.finalOperationsList.length; i++){
                var product = $scope.finalOperationsList[i].temps;
                total += product;
            }
            return total;
        }
                
                $scope.getTotalNbOperations = function(){
            var total1 = 0;
            var op = new Array();
            for(var i = 0; i < $scope.finalOperationsList.length; i++){
                op[i] = $scope.finalOperationsList[i].operationDesignation;
                
            }
                    
                    // for(var j = 0; j < op.length; j++){
              //  op[j] = $scope.finalOperationsList[j].operationDesignation;
            //}
            total1 = op.length;

            return total1;
        }
                
                
                
                $scope.InitializeArray = function() {
                     //initialisation des arrays
                     $scope.finalOperationsList = []; 
                      for(i=1; i<11; i++){
                         $scope.addElement(i);
                              } 
                     
                }
                // Annulation de l'ajout
                $scope.annulerAjout = function() {
                  $scope.gammeOFCourante = {"ordreFabricationId":"",
                  "produitId":"",
                  "tempsTotalMin":"",
                  "tempsTotalMax":"",
                  "machineId":"",
                  "sectionId":""
                   };

                    //init final list
                    $scope.finalOperationsList = []; 
                    for(i=1; i<10; i++){
                      $scope.addElement(i);
                    } 
                   
                     











                        $scope.creationGammeOFForm.$setPristine();
                        $scope.rechercheGammeOFForm.$setPristine();
                        $scope.listElementGammeOf = [];
                       
                       
                        $scope.rechercheGammeOF({});
                        $scope.updateListProduitUsedAanAv();
                        $scope.displayMode = "list";
                    }
                    // Suppression Gamme
                      $scope.supprimerGamme = function() {
//                    console.log("INDEX" + $scope.index);
//                    console.log("**OBJET :" +
//                        $scope.myData[$scope.index]);
//                    console.log("DELETE .." +
//                        $scope.myData[$scope.index].id);
                    $http({
                        method: "DELETE",
                        url: baseUrlGpao +
                            "/gammeof/delete:" +
                            $scope.myData[$scope.index].id
                    }).success(function() {
                        $scope.myData.splice($scope.index, 1);
                        //$scope.rechercheGammeOF({});
                        $scope.closePopupDelete();
                        $scope.rechercheGammeOF({});
                        $scope.updateListProduitUsedAanAv();
                    });
                   

                };
                /** Fin de gestion des Gammes */

              /*** PDF ***/
              //boutton Generer
                 $scope.Generate = function(idOf) {
                     
                     var url = baseUrlGpao+"/report/gammeOF?id="+idOf+"&type=pdf";
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
              //generer rapport de tous les Ordre de Fabrication. mode : List
              $scope.downloadAllGammeOF = function(gammeOFCourante) {
                
                var url;
                if(gammeOFCourante.produitId == null){
                  gammeOFCourante.produitId = "";
                }
                if(gammeOFCourante.ordreFabricationId == null){
                  gammeOFCourante.ordreFabricationId = "";
                }

               // console.log("------- gammeOFCourante " + JSON.stringify(gammeOFCourante, null, "  ") );
                    url = baseUrlGpao + "/report/listGammeOF?ordreFabricationId="+gammeOFCourante.ordreFabricationId
                           + "&produitId=" + gammeOFCourante.produitId
                           + "&tempsTotalMin=" + gammeOFCourante.tempsTotalMin
                           + "&tempsTotalMax="+gammeOFCourante.tempsTotalMax
                           + "&machineId="+gammeOFCourante.machineId
                           + "&sectionId="+gammeOFCourante.sectionId
                           + "&type=pdf";
                  
                     //   console.log("-- URL--- :" + url );
                 downloadService.download(url).then(
                     function(success) {
                      //$log.debug('success : ' + success);
                     }, function(error) {
                      //$log.debug('error : ' + error);
                     });
               };






























              /*** PDF ***/
              //generer rapport de tous les Ordre de Fabrication. mode : List
              $scope.downloadAllGammeOF = function(gammeOFCourante) {
                
                var url;
                if(gammeOFCourante.produitId == null){
                  gammeOFCourante.produitId = "";
                }
                if(gammeOFCourante.ordreFabricationId == null){
                  gammeOFCourante.ordreFabricationId = "";
                }

              //  console.log("------- gammeOFCourante " + JSON.stringify(gammeOFCourante, null, "  ") );
                    url = baseUrlGpao + "/report/listGammeOF?ordreFabricationId="+gammeOFCourante.ordreFabricationId
                           + "&produitId=" + gammeOFCourante.produitId
                           + "&tempsTotalMin=" + gammeOFCourante.tempsTotalMin
                           + "&tempsTotalMax="+gammeOFCourante.tempsTotalMax
                           + "&machineId="+gammeOFCourante.machineId
                           + "&sectionId="+gammeOFCourante.sectionId
                           + "&type=pdf";
                  
                      //  console.log("-- URL--- :" + url );
                 downloadService.download(url).then(
                     function(success) {
                      //$log.debug('success : ' + success);
                     }, function(error) {
                      //$log.debug('error : ' + error);
                     });
               };
                $scope.pagingOptions = {
                    pageSizes: [5, 10, 20],
                    pageSize: 20,
                    currentPage: 1
                };
                $scope.colDefs = [];
                $scope
                    .$watch(
                        'myData',
                        function() {
                            $scope.colDefs = [
                                
                               {
                                    field: 'clientAbreviation',
                                    displayName: 'Client',
                                    width: '15%'
                                },
                                {
                                    field: 'produitReference',
                                    displayName: 'Réf.Produit',
                                    width: '15%'
                                },  {
                                    field: 'produitDesignation',
                                    displayName: 'Produit',
                                    width: '15%'
                                },  {
                                    field: 'ordreFabricationNumero',
                                    displayName: 'Réf.OF',
                                    width: '15%'
                                }, {
                                    field: 'tempsTotal',
                                    displayName: 'Temps Total',
                                    width: '15%'
                                }, {
                                    field: 'nbOperation',
                                    displayName: 'Nbr.Opérations',
                                    width: '15%'

                                }, {
                                    field: '',
                                    width:'10%',
                                    cellTemplate: '<div class="buttons TableHeaderalignment" ng-show="!rowform.$visible" >' +
                                        '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerGamme()"><i class="fa fa-fw fa-pencil"></i></button>' +
                                        '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(23)"> <i class="fa fa-fw fa-trash-o"></i></button></div>'
                                }
                            ];
                        });

                $scope.typeAlert = 0;
                $scope.filterOptions = {
                    filterText: "",
                    useExternalFilter: true
                };
                $scope.totalServerItems = 0;

                $scope.setPagingData = function(data, page,
                    pageSize) {
                    var pagedData = data.slice((page - 1) *
                        pageSize, page * pageSize);
                    $scope.myData = pagedData;
                    $scope.totalServerItems = data.length;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                };

                $scope.getPagedDataAsync = function(pageSize, page,
                    searchText) {
                    setTimeout(
                        function() {
                            var data;
                            var gammeOFCourante = $scope.gammeOFCourante;
                            if (searchText) {
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/gammeof/rechercheMulticritere",
                                        gammeOFCourante)
                                    .success(
                                        function(
                                            largeLoad) {
                                            data = largeLoad.list
                                                .filter(function(
                                                    item) {
                                                    return JSON
                                                        .stringify(
                                                            item)
                                                        .toLowerCase()
                                                        .indexOf(
                                                            ft) != -1;
                                                });
                                            $scope
                                                .setPagingData(
                                                    data,
                                                    page,
                                                    pageSize);
                                            /*$scope
                                                .rechercheGammeOF({});*/
                                        });

                            } else {
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/gammeof/rechercheMulticritere",
                                        gammeOFCourante)
                                    .success(
                                        function(
                                            largeLoad) {
                                            $scope
                                                .setPagingData(
                                                    largeLoad.list,
                                                    page,
                                                    pageSize);
                                            /*$scope.rechercheGammeOF({});*/
//                                            console

//                                                .log("test");
                                        });
                            }
                        }, 100);
                };

                $scope.getPagedDataAsync(
                    $scope.pagingOptions.pageSize,
                    $scope.pagingOptions.currentPage);

                $scope
                    .$watch(
                        'pagingOptions',
                        function(newVal, oldVal) {
                            if (newVal !== oldVal &&
                                newVal.currentPage !== oldVal.currentPage) {
                                $scope
                                    .getPagedDataAsync(
                                        $scope.pagingOptions.pageSize,
                                        $scope.pagingOptions.currentPage,
                                        $scope.filterOptions.filterText);
                            }
                        }, true);
                $scope.$watch('filterOptions', function(newVal,
                    oldVal) {
                    if (newVal !== oldVal) {
                        $scope.getPagedDataAsync(
                            $scope.pagingOptions.pageSize,
                            $scope.pagingOptions.currentPage,
                            $scope.filterOptions.filterText);
                    }
                }, true);

                //afficher comme orginal
                $scope.formToSave = {
                    selectedRef: {
                        "id": 0,
                        "ref": "ref0"
                    },
                    finalOperationsList: []
                    //console.log("finalOperationsList " +[]);
                };

                var operationsListWS = [];
                $scope.selectedRef = '';

                // called when we select an element from
                // autocomplete list
                $scope.onRefSelected = function(item) {
                    $scope.selectedRef = item;
                    $scope.formToSave.selectedRef = $scope.selectedRef;

                };

                // used to add new element into list of table
                //var _ordre = 0;
                $scope.addElement = function(_ordre) {
                     
                  //debugger;



















                    
                    var tmpElement = {
                        ordre: (_ordre === undefined) ? (parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1) :
                            _ordre,
                        operationCode : '',
                        operationDesignation : '',
                        temps: 0,
                        pdh: 0,
                        sectionId : null,
                        machineId : null,
                        observations: '',
                        designation: '',
                        operationId : null,
                        disable: false,
                        comptage : false
                    };
                       
                    if ($scope.finalOperationsList
                        .indexOf(tmpElement) == -1) {
                        $scope.finalOperationsList.push(tmpElement);
//                        $scope.gammeOFCourante.tempsTotal = $scope.getTotalTemps();
//                        $scope.gammeOFCourante.nbOperation = $scope.getTotalNbOperations();
                         var t = parseInt(_ordre)+1;
                        _ordre =t;
                        //console.log(t);

                        //console.log("$scope.finalOperationsList 1 "+JSON.stringify($scope.finalOperationsList));
                        
                    }
                    //console.log("dern  " + _ordre);
                };

                // first function to call
                var _init = function() {
                

//                  gammeOfFactory.listRef().then(
//                        function(refBackData) {
//                            $scope.refListWS = refBackData;
//
//                        }); 
                    // operationsListWS
                  gammeOfFactory
                        .getListOperations()
                        .then(
                            function(operationsBackData) {
                                operationsListWS = JSON
                                    .parse(JSON
                                        .stringify(operationsBackData));
                                $scope.copyofOpList = JSON
                                    .parse(JSON
                                        .stringify(operationsBackData));
                            })

//                  gammeOfFactory
//                        .getListOF()
//                        .then(
//                            function(operationsBackData) {
//                                
//                                $scope.OFListWS= JSON
//                                    .parse(JSON
//                                        .stringify(operationsBackData));
//                                
//                                console.log(" liste des OF lors d'initialisation : "+$scope.OFListWS);
//                            })

                            
                            
                    $scope.finalOperationsList= []; 
                    for(i=1;i<11;i++){
                      $scope.addElement(i);
                    } 
                  $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                  //console.log("$scope.formToSave.finalOperationsList "+$scope.formToSave.finalOperationsList);
               
                
                  
                };
               

                // used to delete an element from the list
                $scope.deleteElement = function(item, indexLine) {




                    if ($scope.finalOperationsList.length > 1) {
                        // delete line from final list
                        $scope.finalOperationsList.splice(
                            indexLine, 1);



                        // on retourne l'item supprimer
                        for (var i = 0; i < operationsListWS.length; i++)
                            if (operationsListWS[i].code === item.code) {
                                $scope.copyofOpList.splice(i, 0,
                                    item);
                                //console.log("$scope.copyofOpList call 1   "+$scope.copyofOpList);
                                // $scope.copyofOpList.push(operationsListWS[i]);
                                break;
                            }

                    }
                };

                // delete an element from a autocomplete list in
                // order to do not repate it another time in the
                // table
                var _deleteUsedElement = function(item) {
                  //debugger;


                    for (var i = 0; i < $scope.copyofOpList.length; i++)
                        if ($scope.copyofOpList[i].codeOperation === item.codeOperation) {
                            $scope.copyofOpList.splice(i, 1);
                         //   console.log("$scope.copyofOpList Call 2   "+JSON.stringify($scope.copyofOpList));
                            break;

                        }
                };

                // Will be called inside the function that executed
                // when we select an element from autocomplete list
                //call when I define varibales into forms dynamically
                var _affectElement = function(item, indexLine) {
                  
                  
                  



                    $scope.finalOperationsList[indexLine].operationCode = item.code;
                    $scope.finalOperationsList[indexLine].operationDesignation = item.designation ;   
                    $scope.finalOperationsList[indexLine].temps = item.temps;
                    $scope.finalOperationsList[indexLine].pdh = item.pdh;
                    $scope.finalOperationsList[indexLine].sectionId = item.sectionId;
                    $scope.finalOperationsList[indexLine].machineId = item.machineId;
                    $scope.finalOperationsList[indexLine].observations = item.observations;
                    $scope.finalOperationsList[indexLine].operationId = item.id;
                   // $scope.finalOperationsList[indexLine].comptage = item.comptage;
                    $scope.finalOperationsList[indexLine].disable = true; // pour ajouter et supprimer
                    
                    $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                };

                // called when we select an element from
                // autocomplete list
                $scope.onOperationSelected = function(item,
                    indexLine) {
                    // on dois chercher cette objet et on le
                    // supprime de la list!
                  

                    _deleteUsedElement(item);
                    
                    // Affect selected item to the line
                    _affectElement(item, indexLine);
                    
                };

                // this function is used to set the final object and
                // call the WS of save
                $scope.saveForm = function() {
                    var finalFormToSave = {
                        
                        finalOperationsList: []
                    };
                    //finalFormToSave.selectedRef.id = $scope.formToSave.selectedRef.id;
                    //finalFormToSave.selectedRef.ref = $scope.formToSave.selectedRef.ref;
                    for (var index = 0; index < $scope.formToSave.finalOperationsList.length; index++) {
                         var index1 = index+1;
                        var tmpElement = {
                            // otrdre :
                            // $scope.formToSave.finalOperationsList[index].order,
                            ordre: index1,
                          //ordre: $scope.formToSave.finalOperationsList[index].ordre,
                            temps: $scope.formToSave.finalOperationsList[index].temps,
                            operationDesignation: $scope.formToSave.finalOperationsList[index].operationDesignation,
                            operationCode: $scope.formToSave.finalOperationsList[index].operationCode,
                            code: $scope.formToSave.finalOperationsList[index].code,
                            pdh: $scope.formToSave.finalOperationsList[index].pdh,
                            sectionId: $scope.formToSave.finalOperationsList[index].sectionId,
                            machineId: $scope.formToSave.finalOperationsList[index].machineId,
                            comptage: $scope.formToSave.finalOperationsList[index].comptage,
                            observations: $scope.formToSave.finalOperationsList[index].observations,
                            operationId : $scope.formToSave.finalOperationsList[index].operationId
                        };
//                        console.log("tmpElement 5555" +
//                            JSON.stringify(tmpElement));
                        finalFormToSave.finalOperationsList
                            .push(tmpElement);
                    }
                    //call ws to save the data 
                   
//                    console.log("Final Object " +
//                        JSON.stringify(finalFormToSave,
//                            undefined, 2));
                    $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                }

                $scope.gridOptions = {

                    data: 'myData',
                    columnDefs: 'colDefs',
                    enablePaging: true,
                    showFooter: true,
                    enableHighlighting : true,
                    totalServerItems: 'totalServerItems',
                    pagingOptions: $scope.pagingOptions,
                    selectedItems: $scope.selectedRows,
                    filterOptions: $scope.filterOptions,
                };

                _init();

                $scope.listeMachines();
                $scope.listSections();

            }
        ])
    //end controller

.factory(
    'gammeOfFactory',
    function($http, $q,baseUrlGpao) {

        var backObject = {

            getListOperations: _getListOperations,
            getListOperationsById: _getListOperationsById
            //getListOF : _getListOF
        }

        return backObject;

        //used to get list of operations from ws
        function _getListOperations() {

            var defer = $q.defer();
            $http({
                method: "GET",
                url: baseUrlGpao+"/catalogue/getAll"
            }).then(function mySucces(response) {
              



                defer.resolve(response.data);
            }, function myError(response) {
                


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
               



                defer.resolve(response.data);
            }, function myError(response) {
            


            });

            return defer.promise;
        };
        
    })
;