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
    .module('gpro.gammeMonatage', ["ui.sortable"])
    .filter('unique', function() {
   return function(collection, keyname) {
       
      var output = [], 
          keys = [];

      angular.forEach(collection, function(item) {
          var key = item[keyname];
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);
//              console.log("test");
          }
      });
      return output;
   };
})
    .controller(
        'GammeMonatageCtrl', [
            '$scope',
            '$http',
            'gammeMonatageFactory',
            '$filter',
            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$timeout',
            '$log',

            function($scope, $http, gammeMonatageFactory, $filter,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$timeout,$log) {

                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.gammeCourante = {};
                $scope.listElementGamme = [];
                $scope.liste = [];
                $scope.liste1 = [];

                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
                $scope.listeMachines = [];
                $scope.listSections = [];
                $scope.listeProduitAvailable= [];
                $scope.listeProduitUsed = [];
                //$scope.produitId = $scope.listeProduitUsed[0].id;
                
                
                $scope.updateListProduitUsedAanAv = function(){
                  $scope.listeProduitAvailable();
                  $scope.listeProduitUsed();
                };
                
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
                            //console.log("DELETE");
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

                // Rechercher Gamme
                $scope.rechercheGamme = function(gammeCourante) {
                    //console.log("recherche Gamme ..");
                    $http
                        .post(
                            baseUrlGpao +
                            "/gammeproduit/rechercheMulticritere",
                            gammeCourante)

                    .success(
                        function(resultat) {
                            $scope.myData = resultat.list;
                            // data, page,pageSize
                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.rechercheGammeForm
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
                // Liste des produits Available
                $scope.listeProduitAvailable = function() {
                    $http
                        .get(baseUrlGpao + "/gammeproduit/getProduitListAvailable")
                        .success(
                            function(dataProduit) {
                                $scope.listProduitAvailable = dataProduit;
                            });
                }
              $scope.listeProduitAvailable();

                /** ********************************************* */
                
                /** ********************************************* */
                // Liste des produits Used
                $scope.listeProduitUsed = function() {
                    $http
                        .get(baseUrlGpao + "/gammeproduit/getProduitListUsed")
                        .success(
                            function(dataProduit) {
                                //debugger;
                                $scope.listProduitUsed = dataProduit;
                                //$scope.produitId = $scope.listProduitUsed[0].reference;
                                //console.log("testtttttt selected: "+$scope.listProduitUsed[0].reference);
                            });
                }
              $scope.listeProduitUsed();

                /** ********************************************* */

              //  $scope.rechercheGamme({});
               
                // ** Ajout Gamme
                $scope.AffectationGamme = function(gamme) {
                    
                  //debugger;

                  
                    $scope.UpdateOperationsAll();
                    $scope.gammeCourante = {};
                    
                    $scope.creationGammeForm.$setPristine();
                   
                    $scope.displayMode = "edit";
                    $('.box').hide();
                    $('.box1').show();
                    $scope.disableSelect = false;
                    $scope.inactive =true;
                    $scope.inactivetemps =false;
                    $scope.tempsTotal = true;
                    $scope.nbOperation = true;
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
                 
  //debugger;
   var _temp = $scope.copyofOpList;
   for (var i = 0; i < item.length; i++){      
       for (var j = _temp.length-1; j >=0; j--) {
          if (item[i].operationCode == _temp[j].code) {
              _temp.splice(j, 1);
             
          } 

       } 
   } 
   $scope.copyofOpList = _temp;
  // console.log("_temps : " +JSON.stringify(_temp));
               };

                // Ajout et Modification Gamme
                $scope.modifierOuCreerGamme = function() {

                    var index = this.row.rowIndex;
                  
                  
                    // getGamme
                    $http
                        .get(
                            baseUrlGpao +
                            "/gammeproduit/getById:" +
                            $scope.myData[index].id)
                        .success(
                            function(datagetGamme) {
                                
                               
                                $scope.finalOperationsList = datagetGamme.listElementGamme;   
                                $scope.myData[index].listElementGamme = $scope.finalOperationsList;
                                $scope.UpdateOperationsAll();
                                _deleteCodeOccurences($scope.finalOperationsList);
                                
                            });
                    
                    $scope.gammeCourante = $scope.myData[index] ? angular
                            .copy($scope.myData[index])
                            : {};

                    $scope.displayMode = "edit";
                    $('.box').show();
                    $('.box1').hide();
                    $scope.disableSelect = true;
                    $scope.inactive = true;
                    $scope.inactivetemps = false;
                    $scope.tempsTotal = true;
                    $scope.nbOperation = true;
                    $scope.prdReference= true;
                    $scope.prdDesignation = true;
                }
                
                
                
                
             // Sauvegarder Gamme
                $scope.sauvegarderAjout = function(gamme) {
                     
                     
                     if(Object.keys(gamme).length == 0){
                         
                       $scope.creationGammeForm.$setPristine();
                  }
                     else{
                    //console.log("gamme :"+gamme);
                    if (angular.isDefined(gamme.id)) {
                        
                        $scope.updateGamme(gamme);
                        
                    } else {
                        $scope.saveForm();
                        $scope.creerGamme(gamme);
                        
                    }
                   
                    }

                }
                

                //chercher les produits selon gamme similaire
                 $scope.duplicateGammeProduit = function(produitId) {
                   
                     
                     if (produitId == null) {
                            return;
                          }
                     
                     else{
                     
                     $http
                            .get(baseUrlGpao
                                     + "/gammeproduit/getByProduitId:" +produitId)
                            .success(
                                   function(gammeProduit) {
                                       
                                      // debugger;
                                     
                                     
                                       if(gammeProduit.listElementGamme  == null){
                                           
                                           $scope.InitializeArray();
                                           
                                          }
                                     else{
                                     

                                     $scope.viderSubLists ();
                       
                                        //console.log( "-----gammeProduit.listElementGamme---------------:", JSON.stringify(gammeProduit.listElementGamme ,null, "  "));
                                        
                                      $scope.classifierListElementGamme(gammeProduit.listElementGamme);
                                      
                                      gammeProduit.id = null
                                      $scope.gammeCourante.tempsTotal = gammeProduit.tempsTotal;
                                      $scope.gammeCourante.nbOperation = gammeProduit.nbOperation;
                                      
                                      angular.forEach(gammeProduit.listElementGamme, function(elementGamme, key){
                                          elementGamme.id = null;
                                         
                                      });
                                      
                                      
                                      $scope.finalOperationsList = gammeProduit.listElementGamme;
                                      $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                                      

                                     }

                                   });
                                
                 
                     
                     
                                     }
                
                 }
                
                $scope.classifierListElementGamme = function(listElementGamme){
                    
                      angular.forEach(listElementGamme, function(elementGamme, key){
                          $scope.finalOperationsList.push(elementGamme);
                      })
                      
                      
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
                                    // otrdre :
                                    // $scope.formToSave.finalOperationsList[index].order,
                                	id: $scope.finalOperationsList[index].id,
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
                                
                                //console.log("----------------tmpElement.operationId: "+tmpElement.operationId);
                                

                                finalFormToSave.finalOperationsList
                                    .push(tmpElement);
                               
                            }
                            //call ws to save the data 
                           
//                          console.log("Final Object modif" +
//                              JSON.stringify(finalFormToSave,
//                                  undefined, 2));
                            $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                            
                            gamme.listElementGamme = $scope.finalOperationsList;
                            
                            "use strict";
                            $scope.DeleteEmptyRows(gamme.listElementGamme);
                            

                            
//                          console.log( "-------------gamme to update: ", JSON.stringify(gamme ,null, "  ") );
                            
             $http
                 .put(
                     baseUrlGpao +
                     "/gammeproduit/update",
                     gamme)
                 .success(
                     function(gammeModifiee) {
                         // TODO Code à revoir
                         for (var i = 0; i < $scope.myData.length; i++) {

                             if ($scope.myData[i].id == gammeModifiee) {
                                 $scope.myData[i] = gammeModifiee;
                                 
                                 break;
                             }
//                             console.log("Data:" +
//                                  $scope.myData[i]);
                         }
                         // TODO getId
                         $scope.UpdateOperations();
                         $scope.annulerAjout();
                        
                         
                     });
//             console.log("modification data apres modif :" +
//                     JSON.stringify($scope.finalOperationsList));  
             
             }
                
                
                
                
                
                    // Création Gamme

                $scope.UpdateOperations = function() {
                    //debugger;
                
                     $http
                     .get(
                         baseUrlGpao +
                         "/catalogue/getAll")
                     .success(
                         function(newGamme) {
                            
                             // TODO getId
                             $scope.copyofOpList = newGamme;
                             
                             
                          
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
                
                $scope.creerGamme = function(gamme) {
                   
                   //DISCUSS : refer to AnnulerAjout : displayMode = list
                    $scope.UpdateOperations();
                     //console.log( "-----gamme.listElementGamme lors de creation---------------:", JSON.stringify(gamme.listElementGamme ,null, "  ") );
                    //console.log( "-----finalOperationList lors de creation 1---------------:", JSON.stringify($scope.finalOperationsList,null, "  ") );
                    //$scope.finalOperationsList = $scope.formToSave.finalOperationsList; //modifie ajourdhui
                    gamme.listElementGamme = $scope.finalOperationsList; //finalOperationList est vide
                     
                    //console.log( "-----finalOperationList lors de creation 2---------------:", JSON.stringify($scope.finalOperationsList,null, "  ") );
                    
                    "use strict";
                    $scope.DeleteEmptyRows(gamme.listElementGamme);
                    
                    $http
                        .post(
                            baseUrlGpao + "/gammeproduit/create",
                            gamme)
                        .success(
                            function(newGamme) {
                                $log.debug("==========success");
                                // TODO getId
                                $scope.UpdateOperations();
                                $scope.annulerAjout();
                                $scope.produitId = {};
                                
                                
                            });
                        

                }
                
              //elements à supprimer
                $scope.DeleteEmptyRows = function(listelements){
                  //debugger;

                   for (var i = listelements.length-1; i >=0; i--) {
                         if (listelements[i].operationDesignation == "") {
                             listelements.splice(i, 1);
                                      }
                         
                   }            
                  
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
                    
                   
                    total1 = op.length;

                    return total1;
                }
                
                
                
                $scope.InitializeArray = function() {
                     //initialisation des arrays
                     $scope.finalOperationsList = []; 
                      for(i=1; i<10; i++){
                         $scope.addElement(i);
                              } 
                     
                }
                
                
                // Annulation de l'ajout
                $scope.annulerAjout = function() {
                    
                    $scope.gammeCourante = {
                          "produitId": "",
                          "machineId": "",
                          "tempsTotalMin": "",
                          "tempsTotalMax": "",
                          "sectionId": "",
                          "produitReference": "",
                          "produitDesignation": ""
                        }
                    //init final list
                    $scope.finalOperationsList = []; 
                    for(i=1; i<10; i++){
                      $scope.addElement(i);
                    } 
                  
                        
                        $scope.creationGammeForm.$setPristine();
                        $scope.rechercheGammeForm.$setPristine();
                        $scope.listElementGamme = [];
                       
                       
                        $scope.rechercheGamme({});
                        $scope.updateListProduitUsedAanAv();
                        $scope.produitId = {};
                        $scope.displayMode = "list";
                    }
                    // Suppression Gamme
                $scope.supprimerGamme = function() {
                    
                    
                    
                   //console.log("INDEX" + $scope.index);
//                    console.log("**OBJET :" +
//                        $scope.myData[$scope.index]);
                   console.log("DELETE .." +
                        $scope.myData[$scope.index].id);
                    $http({
                        method: "DELETE",
                        url: baseUrlGpao +
                            "/gammeproduit/delete:" +
                            $scope.myData[$scope.index].id
                    }).success(function() {
                        $scope.myData.splice($scope.index, 1);
                        $scope.rechercheGamme({});
                        $scope.closePopupDelete();
                        //$scope.rechercheGamme({});
                    });
//                    $scope.rechercheGamme({});
//                    $scope.closePopupDelete();
                   

                };
                /** Fin de gestion des Gammes */
                /*** PDF ***/
              //generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
          $scope.download = function(id) {
          //console.log("-- id"+id);
          var url = baseUrlGpao+"/report/gammeProduit?id=" + id+"&type=pdf";
          downloadService.download(url)
              .then(
                  function(success) {
                    console.log('success : '+ success);
                    //$scope.annulerAjout();
                  },
                  function(error) {
                    $log.debug('error : '+ error);
                  });
        };

                //generer rapport de tous les Ordre de Fabrication. mode : List
                
                $scope.downloadAllGammeMontage = function(gammeCourante) {
                    
                    var url;
                    if(gammeCourante.produitId == null){
                        gammeCourante.produitId = "";
                    }
                   // console.log("------- GammeMontageCourant " + JSON.stringify(gammeCourante, null, "  ") );
                    url = baseUrlGpao + "/report/listGammeProduit?produitId=" + gammeCourante.produitId
                                         + "&tempsTotalMin=" + gammeCourante.tempsTotalMin
                                         + "&tempsTotalMax="+gammeCourante.tempsTotalMax
                                         + "&machineId="+gammeCourante.machineId
                                         + "&sectionId="+gammeCourante.sectionId
                                         + "&type=pdf";
                        
                   //  console.log("-- URL--- :" + url );
                     downloadService.download(url).then(
                             function(success) {
                                //$log.debug('success : ' + success);
                             }, function(error) {
                                //$log.debug('error : ' + error);
                             });
                 };
//
                
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
                                    field: 'produitReference',
                                    displayName: 'Réf.Produit',
                                    width: '20%'

                                }, {
                                    field: 'produitDesignation',
                                    displayName: 'Produit',
                                    width: '30%'

                                }, {
                                    field: 'tempsTotal',
                                    displayName: 'Temps Total',
                                    width: '20%'

                                }, {
                                    field: 'nbOperation',
                                    displayName: 'Nbr.Opérations',
                                    width: '20%'


                                }, {
                                    field: '',
                                    width: '10%',

                                    cellTemplate: '<div class="buttons TableHeaderalignment" ng-show="!rowform.$visible">' +
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
                            var gammeCourante = $scope.gammeCourante;
                            if (searchText) {
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/gammeproduit/rechercheMulticritere",
                                        gammeCourante)
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
                                            $scope.setPagingData(
                                                    data,
                                                    page,
                                                    pageSize);
                                            /*$scope.rechercheGamme({});*/
                                        });

                            } else {
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/gammeproduit/rechercheMulticritere",
                                        gammeCourante)
                                    .success(
                                        function(
                                            largeLoad) {
                                            $scope
                                                .setPagingData(
                                                    largeLoad.list,
                                                    page,
                                                    pageSize);
                                            /*$scope
                                                .rechercheGamme({});*/
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
                    
                     // console.log("_ordre 1 _ordre" + _ordre);
                      
                      
//                    if (_ordre == undefined) {
//                        _ordre = 0;
//                    }
                      
                      
//                    _ordre = (parseInt(_ordre) + 1).toString();
//                    console.log("_ordre 2 " + _ordre);
                    // _ordre = (parseInt(_ordre) + 1).toString();
                    // console.log("_ordre 3 "+_ordre);
                     // $scope._ordre = 0;
                     
//                    if (_ordre == 0) {
//                    _ordre = 1;
//                }
                      
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

                         var t = parseInt(_ordre)+1;
                        _ordre =t;
                         //console.log(t);
                      
                        
                    }
                    //console.log("dern  " + _ordre);
                };

                // first function to call
                var _init = function() {
                    //debugger;
//                    gammeMonatageFactory.listRef().then(
//                        function(refBackData) {
//                            $scope.refListWS = refBackData;
//
//                        }); 
                    // operationsListWS
                    gammeMonatageFactory
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
                  $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                //  console.log("$scope.formToSave.finalOperationsList "+$scope.formToSave.finalOperationsList);
                };
               

                // used to delete an element from the list
                $scope.deleteElement = function(item, indexLine) {

                    if ($scope.finalOperationsList.length > 1) {
                        // delete line from final list
                        $scope.finalOperationsList.splice(
                            indexLine, 1);
                        // console.log("SLECGTEd OBJECT
                        // "+JSON.stringify(item, undefined, 2));
                        // on retourne l'item supprimer
                        for (var i = 0; i < operationsListWS.length; i++)
                            if (operationsListWS[i].code === item.code) {
                                $scope.copyofOpList.splice(i, 0,
                                    item);
                                //console.log("$scope.copyofOpList 1"+$scope.copyofOpList);
                                // $scope.copyofOpList.push(operationsListWS[i]);
                                break;
                            }
                        $scope.UpdateOperationsAll();
                    }
                };

                // delete an element from a autocomplete list in
                // order to do not repate it another time in the
                // table
                var _deleteUsedElement = function(item) {
                  //debugger;

                  
                    for (var i = 0; i < $scope.copyofOpList.length; i++)
                        if ($scope.copyofOpList[i].code === item.code) {
                            $scope.copyofOpList.splice(i, 1);
                           //console.log("$scope.copyofOpList 2"+JSON.stringify($scope.copyofOpList));
                           // break;
                        }
                };

                // Will be called inside the function that executed
                // when we select an element from autocomplete list
                //call when I define varibales into forms dynamically
                var _affectElement = function(item, indexLine) {
                  
                  //debugger;

                  
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
                  //debugger;

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

                
                $scope.gridOptions =  {

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
    'gammeMonatageFactory',
    function($http, $q,baseUrlGpao) {

        var backObject = {
           // listRef: _getlistRef,
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
                //deferred.reject('Erreur into url ' + response);
            });

            return defer.promise;
        };

        function _getListOperationsById(val) {
            //console.log("val:" + val);
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
                //deferred.reject('Erreur into url ' + response);
            });

            return defer.promise;
        };
    })
;

