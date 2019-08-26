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
    .module('gpro.eclatement', ["ui.sortable"])
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
        'EclatementCtrl', [
            '$scope',
            '$http',
            'elementFactory',
            '$filter',
            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$route',
			'$log',

            function($scope, $http, elementFactory, $filter,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$route,$log) {

                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.eclatementCourante = {};
                $scope.listPaquet = [];
                $scope.liste = [];
                $scope.liste1 = [];

                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
                $scope.listCouleurs = [];
                $scope.listTailles = [];
               
               
                $scope.listeGammeOF = [];


                $scope.myStyleBtnOF = {
									
                    "background-color" : "green"
             
                };
                $scope.numOfVerifier = "false";
                //$scope.gammeOFCourante.ordreFabricationId  ='';

                //$scope.produitId = $scope.listeProduitUsed[0].id;
                
                //elements à supprimer
                $scope.DeleteEmptyRows = function(listelements){
                  for (var i = listelements.length-1; i >=0; i--) {
                      if (listelements[i].quantite == 0) {
                          listelements.splice(i, 1);
                                   }     
                }            
                  
                }
                
                $scope.validerOF = function (numOf,type) {

                    //console.log("sssssssssssssssssssssssssss");

                    if (numOf == '') return;

                     $scope.numOfVerifier = "false";

                    $http
                        .post(
                            baseUrlGpao +
                            "/ordreFabrication/getByNumForEclatement", numOf
                        )
                        .success(
                            function (resultat) {
                                if (resultat == '') {
                                    $scope.numOfVerifier = "false";
                                } else {
                                    
                          
                                  
                                    $scope.numOfVerifier = "true";
                                    
                                if(type == 'ADD')
                                        {
                                        $scope.eclatementCourante.ordreFabricationId = resultat.id;
                                        $scope.eclatementCourante.clientAbreviation = resultat.partieInterresAbreviation;
                                        $scope.eclatementCourante.produitId = resultat.produitId;
                                        $scope.eclatementCourante.produitReference = resultat.produitReference;
                                        $scope.eclatementCourante.produitDesignation = resultat.produitDesignation;
                                       
                                        }
                             

                            
                                    
                                }


                            });


                }
                
                $scope.ofChanged = function ()
                {
                    $scope.numOfVerifier = "false";
                    $scope.eclatementCourante.produitId = '';
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
                
                //Mise à jour de a liste des Gammes OF après ajout dans l'onglet "OF"
                $scope.updateListOF = function(){
                  
                  
                  
                   $http
                     .get(baseUrlGpao + "/ordreFabrication/all")
                     .success(
                         function(dataProduit) {
                             $scope.listGammeOF = dataProduit;
                         });
                }

                // declaration variable
                $scope.displayAlert = "sleep";

                 $scope.pagingOptions = {
                    pageSizes: [5, 10, 13],
                    pageSize: 13,
                    currentPage: 1
                };

                // Rechercher Gamme
                $scope.rechercheeclatement = function(eclatementCourante) {
                   
                  
                 // $scope.updateListOF();
                  
                    $http
                        .post(
                            baseUrlGpao +
                            "/ficheEclatement/rechercheMulticritere",
                            eclatementCourante)

                    .success(
                        function(resultat) {


                            $scope.myData = resultat.list;
                            // data, page,pageSize
                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.rechercheeclatementForm
                                .$setPristine();
                            $scope.displayMode = "rechercher";
                            $scope.displayAlert = "sleep";
                        });
//                }
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
                $scope.listeGammeOF = function() {
                    $http
                        .get(baseUrlGpao + "/ordreFabrication/all")
                        .success(
                            function(dataProduit) {
                                $scope.listGammeOF= dataProduit;
                            });
                }
              //  $scope.listeGammeOF();

                /** ********************************************* */
                
                /** ********************************************* */
               

                /** ********************************************* */
             

               // $scope.rechercheeclatement({});
               
                // ** Ajout Gamme
                $scope.AffectationGamme = function(gamme) {
                  
                  // if(Object.keys(gamme).length == 0){
                    
                      // console.log(" * test " );
                // }
                  
                 // else{
                    
                 // $scope.updateListOF();  
                  
                  $scope.eclatementCourante = {};
                  $scope.InitializeArray();
                    
                    
                    $scope.creationeclatementForm.$setPristine();
                    //$scope.finalOperationsList = [];
                    $scope.displayMode = "edit";

                    $scope.EnableGamme();
                   
                    
                    
                    $('.update').hide();
                    $('.add').show();
                    $('.generate').hide();
                    $scope.nblignes = true;
                    $scope.btnValide = true;
                    $scope.nombrePaquet= true;
                    $scope.quantiteEclate = true;
                    $scope.selectChoice = false;
                    //}
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
              //console.log(" * listeTaille " + dataTaille.length);
              if(dataTaille.length>0)
                $scope.listTailles = dataTaille;
            });
                }

                // Ajout et Modification Gamme
                $scope.modifierOuCreerGamme = function() {
//                  $scope.finalOperationsList = [];
                    var index = this.row.rowIndex;
                    // console.log("index "+index);
                  
                    // getGamme
                    $http
                        .get(
                            baseUrlGpao +
                            "/ficheEclatement/getById:" +
                            $scope.myData[index].id)
                        .success(
                            function(datagetGamme) {
                
                              //debugger;
//                              
                              if(datagetGamme.listPaquet.length == 0)
                                {
                                $scope.finalOperationsList= []; 
                                  for(i=1;i<11;i++){
                                    $scope.addElement(i);
                                  } 
                                $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                              }
                              else{
                              
//                                $scope.eclatementCourante.nbOperationProduit = datagetGamme.nbOperationProduit;
//                                $scope.eclatementCourante.tempsTotalProduit = datagetGamme.tempsTotalProduit;
                              $scope.eclatementCourante.produitId = datagetGamme.produitId;
                                $scope.finalOperationsList = datagetGamme.listPaquet;   
                                $scope.myData[index].listElementeclatement = $scope.finalOperationsList;
                                }
                                
                            });
                    
                    $scope.eclatementCourante = $scope.myData[index] ? angular
              .copy($scope.myData[index])
              : {};

          $scope.displayMode = "edit";

                    
           $scope.DisableGamme();
                    
                    
                    $('.update').show();
                    $('.add').hide();
                    $('.generate').show();
                    $scope.nblignes = true;
                    $scope.btnValide = true;
                    $scope.nombrePaquet= true;
                    $scope.quantiteEclate = true;
                    $scope.selectChoice = true;
                }
                
                
                //enable Elements
                $scope.EnableGamme = function() {
                  
                   $scope.disableSelect = false;
                     $scope.inactive =false;
                     
                     $scope.tempsTotalOF = false;
                     $scope.nbOPOF = false;
                     $scope.tempsTotalP = false;
                     $scope.nbOPP = false;
                     
                     $scope.nbOPP = false;
                     $scope.ordreFab = false;
                     $scope.clientAbr = true;
                     $scope.RefProd = true;
                     $scope.DesigProd = true;
                
                }
                
                
                //disable Elements
               $scope.DisableGamme = function() {
                  
                $scope.disableSelect = false;
                    $scope.inactive = false;
                    $scope.tempsTotalOF = false;
                    $scope.nbOPOF = false;
                    $scope.tempsTotalP = false;
                    
                    $scope.nbOPP = false;
                    $scope.ordreFab = false;
                    $scope.clientAbr = true;
                    $scope.RefProd = true;
                    $scope.DesigProd = true;
                  
                }
                
               $scope.eclatementChoice = function(methode) {
                   
                  
                 //debugger;
                 
           if (methode == null) {
                return;
              }
           
           else{
             
             if(methode == 0){
               
              
               $scope.nblignes = true;
               $scope.btnValide = true;
               
             }
             else{
               
              
               $scope.nblignes = false;
               $scope.btnValide = false;
               
             }
           }
           
               }
               
               
            //chercher les produits selon gamme similaire
         $scope.AddTableRows = function(ordreFabricationId,quantitePaquet) {
           
           
           
           //debugger;
                   
                   
                   if( $scope.finalOperationsList.length == 0){
                     $scope.finalOperationsList= []; 
                       for(i=1;i<11;i++){
                         $scope.addElement(i);
                       } 
                     $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                     
                   }
                   
           if (((ordreFabricationId == null)) || (quantitePaquet == 0)){
                return;
              }
           
           else{
           
                   $http
                          .get(baseUrlGpao
                                   + "/ficheEclatement/getPaquetListByOfIdAndQuantitePaquet?ordreFabricationId="+ordreFabricationId+"&quantitePaquet="+quantitePaquet)
                          .success(
                                 function(gammeof) {

                                //debugger;
                                    
                                    gammeof.id = undefined;
                                    $scope.finalOperationsList = gammeof;
                                   
                               
                                 });
                              
         
                             }
        
         }
                
        



                
            //chercher les produits selon gamme similaire
         $scope.AddTableRowsNum = function(ordreFabricationId,quantitePaquet) {
           
           
           
           //debugger;
                   
                   
                   if( $scope.finalOperationsList.length == 0){
                     $scope.finalOperationsList= []; 
                       for(i=1;i<11;i++){
                         $scope.addElement(i);
                       } 
                     $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
                     
                   }
                   
           if (((ordreFabricationId == null)) || (quantitePaquet == 0)){
                return;
              }
           
           else{
           
                   $http
                          .get(baseUrlGpao
                                   + "/ficheEclatement/getPaquetListByOfNumAndQuantitePaquet?ordreFabricationId="+ordreFabricationId+"&quantitePaquet="+quantitePaquet)
                          .success(
                                 function(gammeof) {

                                //debugger;
                                    
                                    gammeof.id = undefined;
                                    $scope.finalOperationsList = gammeof;
                                   
                               
                                 });
                              
         
                             }
        
         }





















        
             // Sauvegarder Gamme
        $scope.sauvegarderAjout = function(gamme) {
                     
          //console.log("gamme :"+gamme);
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

                
                                      
                                      gammeof.id = undefined;
                                      
                                      $scope.eclatementCourante.ordreFabricationNumero = gammeof.ordreFabricationNumero;
                                      //$scope.gammeOFCourante.ordreFabricationId = gammeof.ordreFabricationId;
                                      
                                      
                                      
                                      $scope.eclatementCourante.clientAbreviation = gammeof.clientAbreviation;
                                     
                                      //console.log( "-----gammeof.tempsTotal---------------:", JSON.stringify(gammeof.tempsTotal ,null, "  "));
                                      
                                      //console.log( "-----gammeof.nbOperation---------------:", JSON.stringify(gammeof.nbOperation ,null, "  "));
                                      $scope.eclatementCourante.produitId = gammeof.produitId;
                                    
//                                   
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
               // console.log(" * listeTaille " + dataTaille.length);
                if(dataTaille.length>0)
                  $scope.listTailles = dataTaille;
              });
                  }
        
              $scope.viderSubLists = function(){
          $scope.finalOperationsList = [];
              }
              
              /******************************************************************************/


                // Mise à jour des Gammes
        $scope.updateGamme= function(gamme) {
          
         
        
//          gamme.tempsTotal = $scope.getTotalTemps();
//                    gamme.nbOperation = $scope.getTotalNbOperations();
//          
             var finalFormToSave = {
                            
                            finalOperationsList: []
                        };

                        for (var index = 0; index < $scope.finalOperationsList.length; index++) {
                             var index1 = index+1;
                             var tmpElement = {

                                     ordre: index1,
                                     id:$scope.finalOperationsList[index].id,
                                  //ordre: $scope.formToSave.finalOperationsList[index].ordre,
                                     num: $scope.finalOperationsList[index].num,
                                     tailleId: $scope.finalOperationsList[index].tailleId,
                                     couleurId: $scope.finalOperationsList[index].couleurId,
                                     quantite: $scope.finalOperationsList[index].quantite,
                                     numMatelas: $scope.finalOperationsList[index].numMatelas,
                                    
                                 };
                                 
                                 finalFormToSave.finalOperationsList
                                     .push(tmpElement);
                             }
                            
                         
                        $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                        
                        gamme.listPaquet = $scope.finalOperationsList;
                        "use strict";
                        $scope.DeleteEmptyRows(gamme.listPaquet);
             $http
                 .put(
                     baseUrlGpao +
                     "/ficheEclatement/update",
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
                         $scope.annulerAjout();
                        
                        
                     });
 
             
             }
        
                
        
        //methode pour ajouter des rows dans le tableau de la liste des paquets
//        $scope.count=0;
//        $scope.change=function(){
//              $scope.count++;
//          }
        
        
        
                    // Création Gamme

                $scope.creerGamme = function(gamme) {
                  $scope.eclatementCourante.clientAbreviation = gamme.clientAbreviation;
                  gamme.listPaquet = $scope.finalOperationsList;
                  
                  "use strict";
                  $scope.DeleteEmptyRows(gamme.listPaquet);
            
                    $http
                        .post(
                            baseUrlGpao +"/ficheEclatement/create",
                            gamme)
                        .success(
                            function(newGamme) {
                                $log.debug("==========success");
                                // TODO getId
                              
                                $scope.annulerAjout();
                                
                                
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
                  
                    //init final list
                    $scope.finalOperationsList = []; 
                    for(i=1; i<11; i++){
                      $scope.addElement(i);
                    } 
                 
                  //mes solutions
                        $scope.nblignes = true;
                  
                        $scope.eclatementCourante = {};
                        $scope.creationeclatementForm.$setPristine();
                        $scope.rechercheeclatementForm.$setPristine();
                        //$scope.listPaquet = [];
                       
                       
                        $scope.rechercheeclatement({});
                        
                        $scope.displayMode = "list";
                    }
                    // Suppression Gamme
                      $scope.supprimerGamme = function() {

                    $http({
                        method: "DELETE",
                        url: baseUrlGpao +
                            "/ficheEclatement/delete:" +
                            $scope.myData[$scope.index].id
                    }).success(function() {
                        $scope.myData.splice($scope.index, 1);
                        //$scope.rechercheeclatement({});
                        $scope.closePopupDelete();
                        $scope.rechercheeclatement({});
                        
                    });
                   

                };
                /** Fin de gestion des Gammes */

 /*********** PDF ************/
              //boutton GenererDePartage
                 $scope.GeneratePartage = function(idOf) {
                                    
                     var url = baseUrlGpao+"/report/ficheDepartage?ordreFabricationId="+idOf+"&type=pdf";
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

                 //boutton GenererDePartage
                 $scope.GeneratePaquet = function(idOf) {
                                    
                     var url = baseUrlGpao+"/report/fichePaquets?ordreFabricationId="+idOf+"&type=pdf";
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

                //boutton GenererDePartage
                 $scope.GenerateExport = function(idOf) {
                                    
                     var url = baseUrlGpao+"/fiches/ExportReport?id="+idOf+"&type=pdf";
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

				 
				 
				   //boutton GenererDePartage
                 $scope.GenerateEtiquette = function(idOf) {
                                    
                     var url = baseUrlGpao+"/fiches/EtiquetteReport?id="+idOf+"&type=pdf";
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
              $scope.downloadAllEclatement = function(gammeOFCourante) {
                
                var url;
                    if(gammeOFCourante.produitId == null){
                      gammeOFCourante.produitId = "";
                    }
                    if(gammeOFCourante.ordreFabricationId == null){
                      gammeOFCourante.ordreFabricationId = "";
                    }
                    url = baseUrlGpao + "/report/listEclatement?ordreFabricationId="+gammeOFCourante.ordreFabricationId
                           + "&produitId=" + gammeOFCourante.produitId
                           + "&type=pdf";
                  
                 downloadService.download(url).then(
                     function(success) {
                      //$log.debug('success : ' + success);
                     }, function(error) {
                      //$log.debug('error : ' + error);
                     });
               };
               /*********** Grid **/
               
                $scope.colDefs = [];
                $scope
                    .$watch(
                        'myData',
                        function() {
                            $scope.colDefs = [
                                
                               {
                                    field: 'clientAbreviation',
                                    displayName: 'Client',
                                    width: '23%'
                                },
                                {
                                    field: 'produitReference',
                                    displayName: 'Réf.Produit',
                                    width: '10%'
                                },  {
                                    field: 'produitDesignation',
                                    displayName: 'Produit',
                                    width: '30%'
                                },  {
                                    field: 'ordreFabricationNumero',
                                    displayName: 'Réf.OF',
                                    width: '10%'
                                }, {
                                    field: 'nombrePaquet',
                                    displayName: 'Nombre de Paquets',
                                    width: '10%'
                                }, {
                                    field: 'quantiteEclate',
                                    displayName: 'Quantité Eclatée',
                                    width: '10%'

                                }, {
                                    field: '',
                                    width:'7%',
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
                            var eclatementCourante = $scope.eclatementCourante;
                            if (searchText) {
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/ficheEclatement/rechercheMulticritere",
                                        eclatementCourante)
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
                                                .rechercheeclatement({});*/
                                        });

                            } else {
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/ficheEclatement/rechercheMulticritere",
                                        eclatementCourante)
                                    .success(
                                        function(
                                            largeLoad) {
                                            $scope
                                                .setPagingData(
                                                    largeLoad.list,
                                                    page,
                                                    pageSize);
                                            /*$scope.rechercheeclatement({});*/
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
                        num : (_ordre === undefined) ? (parseInt($scope.finalOperationsList[$scope.finalOperationsList.length - 1].ordre) + 1) :
                            _ordre,
                        tailleId : null,
                        couleurId : null,
                        quantite: 0,
                        numMatelas: 0,
                    };
                       
                    if ($scope.finalOperationsList
                        .indexOf(tmpElement) == -1) {
                        $scope.finalOperationsList.push(tmpElement);
//                        $scope.eclatementCourante.tempsTotal = $scope.getTotalTemps();
//                        $scope.eclatementCourante.nbOperation = $scope.getTotalNbOperations();
                         var t = parseInt(_ordre)+1;
                        _ordre =t;
                        
                    }
                };

                // first function to call
                var _init = function() {
                  //debugger;

                    // operationsListWS
                  elementFactory
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
                        // on retourne l'item supprimer
                        for (var i = 0; i < operationsListWS.length; i++)
                            if (operationsListWS[i].code === item.code) {
                                $scope.copyofOpList.splice(i, 0,
                                    item);
                             //   console.log("$scope.copyofOpList call 1   "+$scope.copyofOpList);
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
                          //  console.log("$scope.copyofOpList Call 2   "+JSON.stringify($scope.copyofOpList));
                            break;
                        }
                };

                // Will be called inside the function that executed
                // when we select an element from autocomplete list
                //call when I define varibales into forms dynamically
//                var _affectElement = function(item, indexLine) {
//                    $scope.finalOperationsList[indexLine].operationCode = item.code;
//                    $scope.finalOperationsList[indexLine].operationDesignation = item.designation ;   
//                    $scope.finalOperationsList[indexLine].temps = item.temps;
//                    $scope.finalOperationsList[indexLine].pdh = item.pdh;
//                    $scope.finalOperationsList[indexLine].sectionId = item.sectionId;
//                    $scope.finalOperationsList[indexLine].machineId = item.machineId;
//                    $scope.finalOperationsList[indexLine].observations = item.observations;
//                    $scope.finalOperationsList[indexLine].operationId = item.id;
//                   // $scope.finalOperationsList[indexLine].comptage = item.comptage;
//                    $scope.finalOperationsList[indexLine].disable = true; // pour ajouter et supprimer
//                    
//                    $scope.formToSave.finalOperationsList = $scope.finalOperationsList;
//                };

                // called when we select an element from
                // autocomplete list
//                $scope.onOperationSelected = function(item,
//                    indexLine) {
//                    // on dois chercher cette objet et on le
//                    // supprime de la list!
//                    _deleteUsedElement(item);
//                    
//                    // Affect selected item to the line
//                    _affectElement(item, indexLine);
//                    
//                };

                // this function is used to set the final object and
                // call the WS of save
                $scope.saveForm = function() {
                  
                  //debugger;
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
                            num: $scope.finalOperationsList[index].num,
                            tailleId: $scope.finalOperationsList[index].tailleId,
                            couleurId: $scope.finalOperationsList[index].couleurId,
                            quantite: $scope.finalOperationsList[index].quantite,
                            numMatelas: $scope.finalOperationsList[index].numMatelas,
                           
                        };
                        finalFormToSave.finalOperationsList
                            .push(tmpElement);
                    }
                    //call ws to save the data 
                   
                    $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                }

                $scope.gridOptions = {

                    data: 'myData',
                    columnDefs: 'colDefs',
                    enablePaging: true,
                    showFooter: true,
                    totalServerItems: 'totalServerItems',
                    enableHighlighting : true,
                    pagingOptions: $scope.pagingOptions,
                    selectedItems: $scope.selectedRows,
                    filterOptions: $scope.filterOptions,
                };

                _init();

                $scope.listCouleurs();
                $scope.listTailles();

            }
        ])
    //end controller




.factory(
    'elementFactory',
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
