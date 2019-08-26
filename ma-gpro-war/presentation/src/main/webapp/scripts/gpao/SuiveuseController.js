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
    .module('gpro.suiveuse', ["ui.sortable"])

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
                $log.info("----------- GPAO Suiveuse?? ----------");
               
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
               
                $scope.listGammeOF = [];
                //$scope.produitId = $scope.listeProduitUsed[0].id;
                
                
                $scope.updateListProduitUsedAanAv = function(){
                 
                  $scope.listeProduitAvailable();
                };
                
                /***************************************************
                 * Gestion des Gammes *
                 **************************************************/
                $scope.deleteValue = "oui";
                // Annuler Ajout
                $scope.cancelAddGamme = function(rowform, index,
                    id, designation, liste) {
                    // $log.debug("* Designation
                    // "+liste[0].designation);
                    if (angular.isDefined(id)) {

                        //$log.debug("DEF ID");
                        $scope.deleteValue = "non";
                        rowform.$cancel();
                        //$log.debug("CANCEL");
                    } else {
                        //$log.debug("UND ID  " + id);
                        if (designation == "") {
                            $scope.deleteValue == "oui"
//                            $log.debug("Designation : " +
//                                designation);
//                            $log.debug("$scope.deleteValueOUI : " +
//                                $scope.deleteValue);
                            liste.splice(index, 1);
                            rowform.$cancel();
                            $log.debug("DELETE");
                        } else {
//                            $log.debug("Designation :" +
//                                designation);
//                            $log.debug("$scope.deleteValueNON : " +
//                                $scope.deleteValue);
                            rowform.$cancel();
                            $log.debug("CANCEL");
                        }
                    }
                    $scope.deleteValue = "oui";
                }

                // declaration variable
                $scope.displayAlert = "sleep";

                // Rechercher Gamme
                $scope.rechercheGammeOF = function(gammeOFCourante) {
                    //$log.debug("recherche Gamme ..");
                    $http
                        .post(
                            baseUrlGpao +
                            "/gammeof/rechercheMulticritere",
                            gammeOFCourante)

                    .success(
                        function(resultat) {

//                            console
//                                .log("recherchepppp gamme OF" +
//                                    $scope.gammeOFCourante.ordreFabricationNumero
//                                    );
//                           console
//                             .log("recherchepppp gamme" +
//                                 $scope.gammeOFCourante.produitId +
//                                 " " +
//                                 $scope.gammeOFCourante.machineId +
//                                 " " +
//                                 $scope.gammeOFCourante.tempsTotalMin +
//                                 " " +
//                                 $scope.gammeOFCourante.tempsTotalMax +
//                                 " " +
//                                 $scope.gammeOFCourante.sectionId);
                            $scope.myData = resultat.list;
                            // data, page,pageSize
                           // $log.debug("-----Data Gamme Produit:"+JSON.stringify($scope.myData));
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
                $scope.listGammeOF();

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

                /** ********************************************* */
             

                $scope.rechercheGammeOF({});
               
                // ** Ajout Gamme
                $scope.AffectationGamme = function(gamme) {
                    
                    //$log.debug("liste lors clique Ajouter : "+JSON.stringify(gamme));
                        //$scope.gammeOFCourante = gamme ? angular
                          //  .copy(gamme) : {};
                    
                    $scope.gammeOFCourante = {};
                    $scope.InitializeArray();
                    
                    
                    $scope.creationGammeOFForm.$setPristine();
                    //$scope.finalOperationsList = [];
                    //$log.debug("$scope.finalOperationsList 3"+JSON.stringify($scope.finalOperationsList));
                    $scope.displayMode = "edit";

                    $scope.EnableGamme();
                   
                    
                    
                    $('.update').hide();
                    $('.add').show();
                    $('.generate').hide();
                }

              
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
                        $log.debug(" * listeTaille " + dataTaille.length);
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

                // Ajout et Modification Gamme
                $scope.modifierOuCreerGamme = function() {
//                  $scope.finalOperationsList = [];
                    var index = this.row.rowIndex;
                    // $log.debug("index "+index);
                  
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
                     
                    //$log.debug("gamme :"+gamme);
                    if (angular.isDefined(gamme.id)) {
                        
                        $scope.updateGamme(gamme);
                    
                    } else {
                        $scope.saveForm();
                        $scope.creerGamme(gamme);
                        
                    }
            
                }
                
                /******************************************************************************/
                //chercher les produits selon gamme similaire
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

                                
                                      //debugger;
                                      
                                      gammeof.id = undefined;
                                      
                                      $scope.eclatementCourante.ordreFabricationNumero = gammeof.ordreFabricationNumero;
                                      //$scope.gammeOFCourante.ordreFabricationId = gammeof.ordreFabricationId;
                                      
                                      
                                      
                                      $scope.eclatementCourante.clientAbreviation = gammeof.clientAbreviation;
                                     
                                      //$log.debug( "-----gammeof.tempsTotal---------------:", JSON.stringify(gammeof.tempsTotal ,null, "  "));
                                      
                                      //$log.debug( "-----gammeof.nbOperation---------------:", JSON.stringify(gammeof.nbOperation ,null, "  "));
                                      $scope.eclatementCourante.produitId = gammeof.produitId;
                                    
                                      //$scope.finalOperationsList = gammeof.listElementGammeOf;
                                  
                                    $scope.CoulorsByOF(ordreFabricationId);
                                    $scope.tailleOF(ordreFabricationId);
                                    
                                    
                                   });
                                
                 
                                     }
                     
                    
                
                 }
                 
                 
                 // Liste des couleurs par ordre de fabrication
                    $scope.CoulorsByOF = function(ordreFabricationId) {
                        $http
                            .get(baseUrlGpao + "/ordreFabrication/getCouleurOf:"+ordreFabricationId)
                          
                            .success(
                                function(dataCouleur) {

                                        if(dataCouleur.length>0)
                                            $scope.listCouleurs = dataCouleur;

                                });
                    }

                    // Liste des tailles par ordre de fabrication
                    $scope.tailleOF = function(ordreFabricationId) {
                        $http
                         
                       .get(baseUrlGpao + "/ordreFabrication/getTailleOf:"+ordreFabricationId).success(function(dataTaille) {
                            $log.debug(" * listeTaille " + dataTaille.length);
                            if(dataTaille.length>0)
                                $scope.listTailles = dataTaille;
                        });
                    }
                 
                 
                //chercher les produits selon gamme similaire
                 $scope.duplicateFichePaquets = function(ordreFabricationId) {
                     
                     if (ordreFabricationId == null) {
                            return;
                          }
                     
                     else{
                     
                     $http
                            .get(baseUrlGpao
                                     + "/ficheSuiveuse/getByOrdreFabricationId:" +ordreFabricationId)
                            .success(
                                   function(gammeof) {

                                //debugger;
                                      
                                      gammeof.id = undefined;
                                      
                                     // $scope.eclatementCourante.ordreFabricationNumero = gammeof.ordreFabricationNumero;
                                      //$scope.gammeOFCourante.ordreFabricationId = gammeof.ordreFabricationId;
                                      
                                      
                                      
                                     // $scope.eclatementCourante.clientAbreviation = gammeof.clientAbreviation;
                                     
                                      //$log.debug( "-----gammeof.tempsTotal---------------:", JSON.stringify(gammeof.tempsTotal ,null, "  "));
                                      
                                      //$log.debug( "-----gammeof.nbOperation---------------:", JSON.stringify(gammeof.nbOperation ,null, "  "));
                                     // $scope.eclatementCourante.produitId = gammeof.produitId;
                                    
                                      $scope.finalOperationsList = gammeof.operationsList;
                                      $scope.finalPaquetsList = gammeof.paquetsList;
                                  
                                   
                                   });
                                
                 
                                     }
                
                 }
                
                $scope.classifierlistElementGammeOf = function(listElementGammeOf){
                    
                      angular.forEach(listElementGammeOf, function(elementGamme, key){
                         // $log.debug( "-----classifierlistElementGammeOf 1---------------:", JSON.stringify(listElementGammeOf ,null, "  "));
                          //$log.debug( "-----classifierlistElementGammeOf 2---------------:", JSON.stringify(elementGamme ,null, "  "));
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
                            //finalFormToSave.selectedRef.id = $scope.formToSave.selectedRef.id;
                            //finalFormToSave.selectedRef.ref = $scope.formToSave.selectedRef.ref;
                            for (var index = 0; index < $scope.finalOperationsList.length; index++) {
                                 var index1 = index+1;
                                var tmpElement = {
                                    // otrdre :
                                    // $scope.formToSave.finalOperationsList[index].order,
                                    ordre: index1,
                                    //ordre: $scope.formToSave.finalOperationsList[index].ordre,
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
                                
                                //$log.debug("----------------tmpElement.operationId: "+tmpElement.operationId);
                                
                                
//                              $log.debug("tmpElement 6666" +
//                                  JSON.stringify(tmpElement));
                                finalFormToSave.finalOperationsList
                                    .push(tmpElement);
                               
                            }
                            //call ws to save the data 
                           
//                          $log.debug("Final Object modif" +
//                              JSON.stringify(finalFormToSave,
//                                  undefined, 2));
                            $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                            
                            gamme.listElementGammeOf = $scope.finalOperationsList;
                            //$log.debug("$scope.copyofOpList modif"+JSON.stringify(gamme.listElementGammeOf));
//             $log.debug("modification data:" +
//                 JSON.stringify($scope.finalOperationsList));
//             $log.debug("gamme.listElementGammeOf modif: " +
//                 gamme.listElementGammeOf);
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
//                             $log.debug("Data:" +
//                                  $scope.myData[i]);
                         }
                         // TODO getId
                         $scope.UpdateOperations();
                         $scope.annulerAjout();
                        
                        
                     });
//             $log.debug("modification data apres modif :" +
//                     JSON.stringify($scope.finalOperationsList));  
             
             }
                
                
                $scope.UpdateOperations = function() {
                    //debugger;
//                   $scope.copyofOpList = suiveuseFactory.getListOperations();
//                   //$scope.$apply();
//                   //$location.path('/path1/path2');
//                   //$route.reload();
//                   $log.debug("$scope.copyofOpList "+JSON.stringify($scope.copyofOpList));
                     
//                  var t;
//                  
                     $http
                     .get(
                         baseUrlGpao +
                         "/catalogue/getAll")
                     .success(
                         function(newGamme) {
                            
                             // TODO getId
                             $scope.copyofOpList = newGamme;
                             $scope.annulerAjout();
                             
                          
                         });
                    
                    }
                
                
                    // Création Gamme

                
//              $scope.finalOperationsList = [];
                //$log.debug( "-----avant creation----------------:", JSON.stringify($scope.finalOperationsList,null, "  ") );
                
                $scope.creerGamme = function(gamme) {
                    //debugger;
                    $scope.UpdateOperations();
                    //$log.debug( "-----gamme.listElementGammeOf lors de creation---------------:", JSON.stringify(gamme.listElementGammeOf ,null, "  ") );
                    
                    $scope.gammeOFCourante.clientAbreviation = gamme.clientAbreviation;
                    gamme.listElementGammeOf = $scope.finalOperationsList;
                    
                    //$scope.CreateOp(gamme.listElementGammeOf);
                    //$scope.copyofOpList.push(gamme.listElementGammeOf); 
                    
                    //$log.debug("$scope.copyofOpList create"+JSON.stringify($scope.copyofOpList));
                    
                    //$log.debug("$scope.copyofOpList create"+JSON.stringify(gamme.listElementGammeOf));
                    //$log.debug( "-----finalOperationList lors de creation---------------:", JSON.stringify($scope.finalOperationsList,null, "  ") );
                    //$log.debug("------------ "+gamme);
                    
                    $http
                        .post(
                            baseUrlGpao +
                            "/gammeof/create",
                            gamme)
                        .success(
                            function(newGamme) {
//                                console
//                                    .log("Success Creation : id= " +
//                                        newGamme);
//                                console
//                                    .log("test add gamme  " +
//                                        $scope.gammeOFCourante.produitId +
//                                        "  " +
//                                        $scope.gammeOFCourante.tempsTotal +
//                                        " " +
//                                        $scope.gammeOFCourante.nbOperation +
//                                        " " +
//                                        $scope.gammeOFCourante.clientAbreviation +
//                                        " " +
//                                        $scope.gammeOFCourante.clientAbreviation +
//                                        "create Data of $scope.listElementGammeOf" +
//                                        JSON.stringify($scope.listElementGammeOf)
//                                        );
                                // TODO getId
                                $scope.UpdateOperations();
                                $scope.annulerAjout();
                                
                                
                            });

                }
                
                //$log.debug("après creation: "+$scope.finalOperationsList);
                //$log.debug( "-----finalOperationList après creation---------------:", JSON.stringify($scope.finalOperationsList,null, "  ") );
                
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
                       
                       
                        $scope.rechercheGammeOF({});
                        $scope.updateListProduitUsedAanAv();
                        $scope.displayMode = "list";
                    }
                    // Suppression Gamme
                      $scope.supprimerGamme = function() {
//                    $log.debug("INDEX" + $scope.index);
//                    $log.debug("**OBJET :" +
//                        $scope.myData[$scope.index]);
//                    $log.debug("DELETE .." +
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
                                    width: 250
                                },
                                {
                                    field: 'produitReference',
                                    displayName: 'Réf.Produit',
                                    width: 250
                                },  {
                                    field: 'produitDesignation',
                                    displayName: 'Produit',
                                    width: 250
                                },  {
                                    field: 'ordreFabricationNumero',
                                    displayName: 'Réf.OF',
                                    width: 150
                                }, {
                                    field: 'tempsTotal',
                                    displayName: 'Temps Total',
                                    width: 120
                                }, {
                                    field: 'nbOperation',
                                    displayName: 'Nbr.Opérations',
                                    width: 120

                                }, {
                                    field: '',
                                    width:188,
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
                                           /* $scope.rechercheGammeOF({});*/
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
                    //$log.debug("finalOperationsList " +[]);
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
                        comptage : false
                    };
                       
                    if ($scope.finalOperationsList
                        .indexOf(tmpElement) == -1) {
                        $scope.finalOperationsList.push(tmpElement);

                         var t = parseInt(_ordre)+1;
                        _ordre =t;
                        //$log.debug(t);
                        //$log.debug("$scope.finalOperationsList 1 "+JSON.stringify($scope.finalOperationsList));
                        
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
                          };
                             
                          if ($scope.finalPaquetsList
                              .indexOf(tmpElement) == -1) {
                              $scope.finalPaquetsList.push(tmpElement);
//                              $scope.eclatementCourante.tempsTotal = $scope.getTotalTemps();
//                              $scope.eclatementCourante.nbOperation = $scope.getTotalNbOperations();
                               var t = parseInt(_ordre)+1;
                              _ordre =t;
                              //$log.debug(t);
                              //$log.debug("$scope.finalOperationsList 1 "+JSON.stringify($scope.finalOperationsList));
                              
                          }
                   
                };
                

                // first function to call
                var _init = function() {
                    //debugger;
//                  suiveuseFactory.listRef().then(
//                        function(refBackData) {
//                            $scope.refListWS = refBackData;
//
//                        }); 
                    // operationsListWS
                    suiveuseFactory
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


                            
                            
                    $scope.finalOperationsList= []; 
                    for(i=1;i<11;i++){
                      $scope.addElement(i);
                    } 
                    
                    $scope.finalPaquetsList= []; 
                    for(i=1;i<11;i++){
                      $scope.addElementPaquet(i);
                    }
                    
                    
                  $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                  //$log.debug("$scope.formToSave.finalOperationsList "+$scope.formToSave.finalOperationsList);
              
                 
                };
               

                // used to delete an element from the list
                $scope.deleteElement = function(item, indexLine) {
//                  var tr = parseInt($scope.item.ordre);
//                  tr-=tr;
//                  $scope.item.ordre=tr ;
                    if ($scope.finalOperationsList.length > 1) {
                        // delete line from final list
                        $scope.finalOperationsList.splice(
                            indexLine, 1);
                        // $log.debug("SLECGTEd OBJECT
                        // "+JSON.stringify(item, undefined, 2));
                        // on retourne l'item supprimer
                        for (var i = 0; i < operationsListWS.length; i++)
                            if (operationsListWS[i].code === item.code) {
                                $scope.copyofOpList.splice(i, 0,
                                    item);
                                $log.debug("$scope.copyofOpList call 1   "+$scope.copyofOpList);
                                // $scope.copyofOpList.push(operationsListWS[i]);
                                break;
                            }
                    }
                };

                // delete an element from a autocomplete list in
                // order to do not repate it another time in the
                // table
                var _deleteUsedElement = function(item) {
                    for (var i = 0; i < $scope.copyofOpList.length; i++)
                        if ($scope.copyofOpList[i].codeOperation === item.codeOperation) {
                            $scope.copyofOpList.splice(i, 1);
                            $log.debug("$scope.copyofOpList Call 2   "+JSON.stringify($scope.copyofOpList));
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
//                        $log.debug("tmpElement 5555" +
//                            JSON.stringify(tmpElement));
                        finalFormToSave.finalOperationsList
                            .push(tmpElement);
                    }
                    //call ws to save the data 
                   
//                    $log.debug("Final Object " +
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
//.directive('ngModelOnblur', function() {
//    return {
//        restrict: 'A',
//        require: '^ngModel',
//        priority: 1, 
//        link: function(scope, elm, attr, ngModelCtrl) {
//            if (attr.type === 'radio' || attr.type === 'checkbox') return;
//
//            elm.unbind('select').unbind('change');
//            elm.bind('blur', function() {
//                scope.$apply(function() {
//                    ngModelCtrl.$setViewValue(elm.val());
//                });         
//            });
//        }
//    };
//})
;