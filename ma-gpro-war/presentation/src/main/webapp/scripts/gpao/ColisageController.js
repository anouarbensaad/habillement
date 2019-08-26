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
    .module('gpro.colisage', ["ui.sortable"])
    .filter('unique', function () {
        return function (collection, keyname) {

            var output = [],
                keys = [];

            angular.forEach(collection, function (item) {
                var key = item[keyname];
                if (keys.indexOf(key) === -1) {
                    keys.push(key);
                    output.push(item);
                    //              console.log("test");
                }
            });
            return output;
        };
    })
    .controller(
        'ColisageCtrl', [
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

            function ($scope, $http, elementFactory, $filter,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope, $route, $log) {

                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.mode = "";
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
                //$scope.produitId = $scope.listeProduitUsed[0].id;



                $scope.hiddenNotif = "true";
                $scope.hiddenNotifSucc = "true";

                $scope.showNotif = function () {
                    $scope.hiddenNotif = "false";
                }
                $scope.closeNotif = function () {
                    $scope.hiddenNotif = "true";
                }

                $scope.showNotifS = function () {
                    $scope.hiddenNotifSucc = "false";
                }


                $scope.closeNotifS = function () {
                    $scope.hiddenNotifSucc = "true";
                }



                //elements à supprimer
                $scope.DeleteEmptyRows = function (listelements) {
                    for (var i = listelements.length - 1; i >= 0; i--) {
                        if (listelements[i].quantite == 0) {
                            listelements.splice(i, 1);
                        }
                    }

                }



                /***************************************************
                 * Gestion des Gammes *
                 **************************************************/
                $scope.deleteValue = "oui";
                // Annuler Ajout
                $scope.cancelAddGamme = function (rowform, index,
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


                $scope.listePartieInteresseeCache = function () {
                    $http
                        .get(
                            baseUrl
                            + "/gestionnaireCache/listePartieInteresseeCache")
                        .success(
                            function (dataSiteCache) {
                                $log.debug("listeSiteCache : "
                                    + dataSiteCache.length);
                                $scope.listePartieInteresseeCache = dataSiteCache;

                            });
                }

                $scope.listePartieInteresseeCache();



                $scope.getOf = function (refOf) {

                    if (refOf == null) return;

                    var ofSearchCriteria = {};
                    ofSearchCriteria.vNumero = refOf;
                    $http
                        .post(
                            baseUrlGpao
                            + "/ordreFabrication/rechercheOrdreFabricationMulticritere",
                            ofSearchCriteria)
                        .success(
                            function (resultat) {
                                //$scope.myDataOF = resultat.ordreFabricationValues;
                                //console.log("############resultat :  ",resultat.ordreFabricationValues[0]);
                                if (resultat.ordreFabricationValues[0]) {
                                    $scope.eclatementCourante.produitReference = resultat.ordreFabricationValues[0].produitReference;
                                    $scope.eclatementCourante.produitDesignation = resultat.ordreFabricationValues[0].produitDesignation;
                                    $scope.eclatementCourante.clientId = resultat.ordreFabricationValues[0].partieInterresId;
                                    $scope.eclatementCourante.quantiteTotale = resultat.ordreFabricationValues[0].quantite;
                                    $scope.eclatementCourante.solder = resultat.ordreFabricationValues[0].solder;
                                }


                            });
                }

                
       





                //Mise à jour de a liste des Gammes OF après ajout dans l'onglet "OF"
                //                $scope.updateListOF = function(){
                //                  
                //                  
                //                  
                //                   $http
                //                     .get(baseUrlGpao + "/ordreFabrication/all")
                //                     .success(
                //                         function(dataProduit) {
                //                             $scope.listGammeOF = dataProduit;
                //                         });
                //                }

                // declaration variable
                $scope.displayAlert = "sleep";

                $scope.pagingOptions = {
                    pageSizes: [5, 10, 13],
                    pageSize: 13,
                    currentPage: 1
                };

                // Rechercher Gamme
                $scope.rechercheeclatement = function (eclatementCourante) {


                    // $scope.updateListOF();

                    $http
                        .post(
                            baseUrlGpao +
                            "/ficheColisage/rechercheMulticritere",
                            eclatementCourante)

                        .success(
                            function (resultat) {


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

                /** ********************************************* */


                /** ********************************************* */






                //$scope.rechercheeclatement({});

                // ** Ajout Gamme
                $scope.AffectationGamme = function (gamme) {
                    $scope.mode = "ADD";
                    $('.checkedValider').hide();
                    console.log("1" + $scope.displayMode);
                    if (Object.keys(gamme).length == 0) {
                        console.log("2" + $scope.displayMode);
                        // $scope.updateListOF();  

                        $scope.eclatementCourante = {};
                        $scope.InitializeArray();


                        $scope.creationeclatementForm.$setPristine();
                        $scope.finalOperationsList = [];
                        $scope.displayMode = "edit";

                        $scope.EnableGamme();



                        $('.update').hide();
                        $('.add').show();
                        $('.generate').hide();
                        $('.valider').show();
                        $('.validerOf').show();


                        $scope.nblignes = true;
                        $scope.btnValide = true;
                        $scope.nombrePaquet = true;
                        $scope.quantiteEclate = true;
                        $scope.selectChoice = false;

                    }

                    else {
                        console.log("3" + $scope.displayMode);
                        // $scope.updateListOF();  

                        $scope.eclatementCourante = {};
                        $scope.InitializeArray();


                        $scope.creationeclatementForm.$setPristine();
                        $scope.finalOperationsList = [];
                        $scope.displayMode = "edit";

                        $scope.EnableGamme();



                        $('.update').hide();
                        $('.add').show();
                        $('.generate').hide();
                        $('.valider').show();
                        $('.validerOf').show();



                        $scope.nblignes = true;
                        $scope.btnValide = true;
                        $scope.nombrePaquet = true;
                        $scope.quantiteEclate = true;
                        $scope.selectChoice = false;
                    }
                }



                $scope.modifierOuCreerGamme = function () {
                    $scope.mode = "UPDATE";
                    //                  $scope.finalOperationsList = [];
                    var index = this.row.rowIndex;
                    // console.log("index "+index);

                    // getGamme
                    $http
                        .get(
                            baseUrlGpao +
                            "/ficheColisage/getById:" +
                            $scope.myData[index].id)
                        .success(
                            function (datagetGamme) {

                                //debugger;
                                //                              
                                if (datagetGamme.listColis.length > 0) {
                                    $scope.finalOperationsList2 = [];
                                    $scope.finalOperationsList2 = datagetGamme.listColis;
                                }


                                if (datagetGamme.listDetails.length > 0) {
                                    $scope.finalOperationsList = [];
                                    $scope.finalOperationsList = datagetGamme.listDetails;

                                }

                                $scope.eclatementCourante = $scope.myData[index] ? angular
                                    .copy($scope.myData[index])
                                    : {};
                                $scope.eclatementCourante.couleur = datagetGamme.couleur;
                                $scope.eclatementCourante.observations = datagetGamme.observations;
                                $scope.eclatementCourante.semaine = datagetGamme.semaine;
                                $scope.eclatementCourante.clientId = datagetGamme.clientId;
                                $scope.eclatementCourante.quantiteColisBefore = datagetGamme.quantiteColisBefore;
                                $scope.eclatementCourante.quantiteTotale = datagetGamme.quantiteTotale;
                                
                                $scope.eclatementCourante.solder = datagetGamme.solder;


                            });



                    $scope.displayMode = "edit";


                    $scope.DisableGamme();


                    $('.update').show();
                    $('.add').hide();
                    $('.generate').show();
                    $('.valider').hide();
                    $('.validerOf').hide();
                    $('.checkedValider').show();
                    $scope.nblignes = true;
                    $scope.btnValide = true;
                    $scope.nombrePaquet = true;
                    $scope.quantiteEclate = true;
                    $scope.selectChoice = true;
                }


                //enable Elements
                $scope.EnableGamme = function () {

                    $scope.disableSelect = false;
                    $scope.inactive = false;

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
                $scope.DisableGamme = function () {

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

                $scope.eclatementChoice = function (methode) {


                    //debugger;

                    if (methode == null) {
                        return;
                    }

                    else {

                        if (methode == 0) {


                            $scope.nblignes = true;
                            $scope.btnValide = true;

                        }
                        else {


                            $scope.nblignes = false;
                            $scope.btnValide = false;

                        }
                    }

                }

                $scope.AddTableRows = function () {
                    // le 19.03.2019 pour permettre la validation en mode edition

                    if ($scope.mode == "UPDATE") {
                        console.log("##################_MODE UPDATE_###################");
                        //  console.log($scope.finalOperationsList);


                        var index1 = $scope.finalOperationsList2.length + 1;
                        for (var index = 0; index < $scope.finalOperationsList.length; index++) {
                            if ($scope.finalOperationsList[index].checked) {
                             //   console.log($scope.finalOperationsList[index]);
                                //console.log("quantite :" + $scope.finalOperationsList[index].quantite);
                                // console.log("bcp:" + $scope.finalOperationsList[index].pcb);

                                //changer par samer le 19.04.19 pour permettre le 
                               // var nbl = (($scope.finalOperationsList[index].quantite) / ($scope.finalOperationsList[index].pcb));
                                var nbl = $scope.finalOperationsList[index].quantite;
                                // var dec = (($scope.finalOperationsList[index].quantite)%($scope.finalOperationsList[index].pcb));
                                var nbligne = nbl - nbl % 1;
                                console.log("nbligne :" + nbligne);
                                for (var j = 0; j < nbligne; j++) {
                                    var tmpElement = {

                                        ordre: index1,
                                        ordreFiche: index1,
                                        //ordre: $scope.formToSave.finalOperationsList[index].ordre,

                                        tailleDesignation: $scope.finalOperationsList[index].tailleDesignation,
                                        couleurDesignation: $scope.finalOperationsList[index].couleurDesignation,
                                        quantite: $scope.finalOperationsList[index].pcb,
                                        quantiteDesignation: $scope.finalOperationsList[index].pcb,
                                        poidsNet: $scope.finalOperationsList[index].poidsNet,
                                        poidsBrut: $scope.finalOperationsList[index].poidsBrut


                                    };
                                    $scope.finalOperationsList2.push(tmpElement);
                                    index1++;
                                  
                                    $scope.finalOperationsList[index].quantiteRestante = 0;
                                    //desactiver par samer le 19.04.19
                                   // $scope.finalOperationsList[index].quantiteRestante = ($scope.finalOperationsList[index].quantite - (nbligne * $scope.finalOperationsList[index].pcb));
                                    // console.log("size finalOperationsList2 :" + $scope.finalOperationsList2.length);
                                }

                            }


                        };

                        //   console.log("size finalOperationsList2 :" + $scope.finalOperationsList2.length);

                        //   console.log("size finalOperationsList2 :", JSON.stringify($scope.finalOperationsList2, null, "  "));


                    }
                    else {
                        //  console.log("##################_MODE ADD_###################");
                        //  console.log($scope.finalOperationsList);

                        //  var index1 = 1;
                        var index1 = $scope.finalOperationsList2.length + 1;
                        for (var index = 0; index < $scope.finalOperationsList.length; index++) {
                            if ($scope.finalOperationsList[index].checked) {

                              //  console.log("quantite :" + $scope.finalOperationsList[index].quantite);
                               // console.log("bcp:" + $scope.finalOperationsList[index].pcb);
                               //changer par samer le 19.04.19 pour facilite le calcul de nbr carton
                                var nbl = $scope.finalOperationsList[index].quantite;
                              //  var nbl = (($scope.finalOperationsList[index].quantite) / ($scope.finalOperationsList[index].pcb));
                               
                                // var dec = (($scope.finalOperationsList[index].quantite)%($scope.finalOperationsList[index].pcb));
                                var nbligne = nbl - nbl % 1;
                                console.log("nbligne :" + nbligne);
                                for (var j = 0; j < nbligne; j++) {
                                    var tmpElement = {

                                        ordre: index1,
                                        ordreFiche:index1,
                                        //ordre: $scope.formToSave.finalOperationsList[index].ordre,

                                        tailleDesignation: $scope.finalOperationsList[index].tailleDesignation,
                                        couleurDesignation: $scope.finalOperationsList[index].couleurDesignation,
                                        quantite: $scope.finalOperationsList[index].pcb,
                                        quantiteDesignation: $scope.finalOperationsList[index].pcb,
                                        poidsNet: $scope.finalOperationsList[index].poidsNet,
                                        poidsBrut: $scope.finalOperationsList[index].poidsBrut


                                    };
                                    $scope.finalOperationsList2.push(tmpElement);
                                    index1++;
                                    
                                    $scope.finalOperationsList[index].quantiteRestante = 0;
                                     //desactiver par samer le 19.04.19
                                 //   $scope.finalOperationsList[index].quantiteRestante = ($scope.finalOperationsList[index].quantite - (nbligne * $scope.finalOperationsList[index].pcb));
                                   // console.log("size finalOperationsList2 :" + $scope.finalOperationsList2.length);
                                }
                            }

                        };

                        //  console.log("size finalOperationsList2 :" + $scope.finalOperationsList2.length);

                        // console.log("size finalOperationsList2 :", JSON.stringify($scope.finalOperationsList2, null, "  "));


                    }

                }


                // Sauvegarder Gamme
                $scope.sauvegarderAjout = function (gamme) {

                    if ($scope.verifierQteColis()) {
                        $scope.hiddenNotif = "false";
                    }

                    else {
                        $scope.hiddenNotifSucc = "false";
                        console.log("############_ELSE##################");
                    }

                    if (angular.isDefined(gamme.id)) {

                        $scope.updateGamme(gamme);

                    } else {
                        $scope.saveForm();
                        $scope.creerGamme(gamme);

                    }


                }

                $scope.verifierQteColis = function () {

                 //   console.log("############eclatementCourante == ", $scope.eclatementCourante);
                 //   console.log("############_QuantiteTotale == ", $scope.eclatementCourante.quaniteTotale);

                    var sommeQteColis = 0;
                    for (var index = 0; index < $scope.finalOperationsList2.length; index++) {
                        //console.log("############$scope.finalOperationsList2[",index,"].qunatite = ",$scope.finalOperationsList2[index].quantite );

                        var qte = parseInt($scope.finalOperationsList2[index].quantite);
                        sommeQteColis += qte;
                    }
                 //   console.log("############_QuantiteColis == ", sommeQteColis);

                    var qteTotale = parseInt($scope.eclatementCourante.quaniteTotale);
                    if (sommeQteColis > qteTotale) {
                      //  console.log("############ _QuantiteColis > QuantiteTotale_ #############");

                        return true;

                    }
                    return null;
                }

                /******************************************************************************/
                //chercher les produits selon gamme similaire
                //         $scope.duplicateGammeOF = function(ordreFabricationId) {
                //                     
                //           if (ordreFabricationId == null) {
                //                return;
                //              }
                //           
                //           else{
                //           
                //                     $http
                //                            .get(baseUrlGpao
                //                                     + "/gammeof/getByOrdreFabricationId:" +ordreFabricationId)
                //                            .success(
                //                                   function(gammeof) {
                //
                //                
                //                                      
                //                                      gammeof.id = undefined;
                //                                      
                //                                      $scope.eclatementCourante.ordreFabricationNumero = gammeof.ordreFabricationNumero;
                //                                      //$scope.gammeOFCourante.ordreFabricationId = gammeof.ordreFabricationId;
                //                                      
                //                                      
                //                                      
                //                                      $scope.eclatementCourante.clientAbreviation = gammeof.clientAbreviation;
                //                                     
                //                                      //console.log( "-----gammeof.tempsTotal---------------:", JSON.stringify(gammeof.tempsTotal ,null, "  "));
                //                                      
                //                                      //console.log( "-----gammeof.nbOperation---------------:", JSON.stringify(gammeof.nbOperation ,null, "  "));
                //                                      $scope.eclatementCourante.produitId = gammeof.produitId;
                //                                    
                ////                                   
                //                                      $scope.CoulorsByOF(ordreFabricationId);
                //                            $scope.tailleOF(ordreFabricationId);
                //                                      
                //                                   });
                //                                
                //         
                //                             }
                //        
                //         }

                // Liste des couleurs par ordre de fabrication
                //                  $scope.CoulorsByOF = function(ordreFabricationId) {
                //                      $http
                //                          .get(baseUrlGpao + "/ordreFabrication/getCouleurOf:"+ordreFabricationId)
                //                        
                //                          .success(
                //                              function(dataCouleur) {
                //
                //                            if(dataCouleur.length>0)
                //                              $scope.listCouleurs = dataCouleur;
                //
                //                              });
                //                  }
                //
                //                  // Liste des tailles par ordre de fabrication
                //                  $scope.tailleOF = function(ordreFabricationId) {
                //                      $http
                //                       
                //                     .get(baseUrlGpao + "/ordreFabrication/getTailleOf:"+ordreFabricationId).success(function(dataTaille) {
                //               // console.log(" * listeTaille " + dataTaille.length);
                //                if(dataTaille.length>0)
                //                  $scope.listTailles = dataTaille;
                //              });
                //                  }

                $scope.viderSubLists = function () {
                    $scope.finalOperationsList = [];
                }

                /******************************************************************************/


                // Mise à jour des Gammes
                $scope.updateGamme = function (gamme) {
                    gamme.listColis = $scope.finalOperationsList2;
                    gamme.listDetails = $scope.finalOperationsList;

                    $http
                        .put(
                            baseUrlGpao +
                            "/ficheColisage/update",
                            gamme)
                        .success(
                            function (gammeModifiee) {
                                // TODO Code à revoir
                                for (var i = 0; i < $scope.myData.length; i++) {

                                    if ($scope.myData[i].id == gammeModifiee) {
                                        $scope.myData[i] = gammeModifiee;

                                        break;
                                    }

                                }
                                // TODO getId
                                //  $scope.annulerAjout();

                         
                            });


                }



                //methode pour ajouter des rows dans le tableau de la liste des paquets
                //        $scope.count=0;
                //        $scope.change=function(){
                //              $scope.count++;
                //          }



                // Création Gamme

                $scope.creerGamme = function (gamme) {
                    // $scope.eclatementCourante.clientAbreviation = gamme.clientAbreviation;
                    gamme.listColis = $scope.finalOperationsList2;
                    gamme.listDetails = $scope.finalOperationsList;
                    //                  "use strict";
                    //                  $scope.DeleteEmptyRows(gamme.listPaquet);

                    $http
                        .post(
                            baseUrlGpao + "/ficheColisage/create",
                            gamme)
                        .success(
                            function (newGamme) {
                                $scope.eclatementCourante.id=newGamme;
                                $('.generate').show();
                                
                                $log.debug("==========success");
                                // TODO getId

                                //  $scope.annulerAjout();


                            });

                }



                $scope.getTotalTemps = function () {
                    var total = 0;
                    for (var i = 0; i < $scope.finalOperationsList.length; i++) {
                        var product = $scope.finalOperationsList[i].temps;
                        total += product;
                    }
                    return total;
                }

                $scope.getTotalNbOperations = function () {
                    var total1 = 0;
                    var op = new Array();
                    for (var i = 0; i < $scope.finalOperationsList.length; i++) {
                        op[i] = $scope.finalOperationsList[i].operationDesignation;

                    }

                    // for(var j = 0; j < op.length; j++){
                    //  op[j] = $scope.finalOperationsList[j].operationDesignation;
                    //}
                    total1 = op.length;

                    return total1;
                }



                $scope.InitializeArray = function () {
                    //initialisation des arrays
                    $scope.finalOperationsList = [];
                    $scope.finalOperationsList2 = [];
                    for (i = 1; i < 2; i++) {
                        $scope.addElement(i);
                        //Desactiver par Samer LE : 25/03/2019
                        //  $scope.addElement2(i);
                    }

                }


                // Annulation de l'ajout
                $scope.annulerAjout = function () {



                    //init final list
                    $scope.finalOperationsList = [];
                    $scope.finalOperationsList2 = [];
                    for (i = 1; i < 2; i++) {
                        $scope.addElement(i);
                        $scope.addElement2(i);
                    }

                    $scope.creationeclatementForm.$setPristine();
                    $scope.rechercheeclatementForm.$setPristine();
                    //$scope.listPaquet = [];



                    $scope.eclatementCourante = {};
                    $scope.rechercheeclatement({});

                    $scope.hiddenNotif = "true";
                    $scope.hiddenNotifSucc = "true";


                }
                // Suppression Gamme
                $scope.supprimerGamme = function () {

                    $http({
                        method: "DELETE",
                        url: baseUrlGpao +
                            "/ficheColisage/delete:" +
                            $scope.myData[$scope.index].id
                    }).success(function () {
                        $scope.myData.splice($scope.index, 1);
                        $scope.rechercheeclatement({});
                        $scope.closePopupDelete();
                        $scope.rechercheeclatement({});

                    });


                };
                /** Fin de gestion des Gammes */

                /*********** PDF ************/
                //boutton GenererDePartage
                $scope.GeneratePartage = function (idOf) {

                    var url = baseUrlGpao + "/report/ficheDepartageColis?id=" + idOf + "&type=pdf";
                    downloadService.download(url)
                        .then(
                            function (success) {
                                $log.debug('success : ' + success);
                                //$scope.annulerAjout();
                            },
                            function (error) {
                                $log.debug('error : ' + error);
                            });
                }

                $scope.GenerateEAN13 = function (idOf) {

                    var url = baseUrlGpao + "/report/ficheColisEAN13?id=" + idOf + "&type=pdf";
                    downloadService.download(url)
                        .then(
                            function (success) {
                                $log.debug('success : ' + success);
                                //$scope.annulerAjout();
                            },
                            function (error) {
                                $log.debug('error : ' + error);
                            });
                }

                //boutton GenererDePartage
                $scope.GeneratePaquet = function (idOf) {

                    var url = baseUrlGpao + "/report/ficheColis?id=" + idOf + "&type=pdf";
                    downloadService.download(url)
                        .then(
                            function (success) {
                                $log.debug('success : ' + success);
                                //$scope.annulerAjout();
                            },
                            function (error) {
                                $log.debug('error : ' + error);
                            });


                }

                //generer rapport de tous les Ordre de Fabrication. mode : List
                $scope.downloadAllEclatement = function (gammeOFCourante) {

                    var url;
                    if (gammeOFCourante.clientId == null) {
                        gammeOFCourante.clientId = "";
                    }
                    if (gammeOFCourante.ordreFabricationId == null) {
                        gammeOFCourante.ordreFabricationId = "";
                    }
                    if (gammeOFCourante.semaine == null) {
                        gammeOFCourante.semaine = "";
                    }
                    url = baseUrlGpao + "/report/listColisage?ordreFabricationId=" + gammeOFCourante.ordreFabricationId
                        + "&clientId=" + gammeOFCourante.partieIntersseId
                        + "&semaine=" + gammeOFCourante.semaine
                        + "&type=pdf";

                    downloadService.download(url).then(
                        function (success) {
                            //$log.debug('success : ' + success);
                        }, function (error) {
                            //$log.debug('error : ' + error);
                        });
                };
                /*********** Grid **/

                $scope.colDefs = [];
                $scope
                    .$watch(
                        'myData',
                        function () {
                            $scope.colDefs = [

                                {
                                    field: 'clientAbreviation',
                                    displayName: 'Client',
                                    width: '12%'
                                },
                                {
                                    field: 'semaine',
                                    displayName: 'Num BL Export',
                                    width: '13%'
                                },

                                {
                                    field: 'ordreFabricationNumero',
                                    displayName: 'OF',
                                    width: '12%'
                                },
                                {
                                    field: 'produitReference',
                                    displayName: 'Réf.Produit',
                                    width: '14%'
                                }, {
                                    field: 'produitDesignation',
                                    displayName: 'Produit',
                                    width: '20%'
                                },
                                {
                                    field: 'couleur',
                                    displayName: 'Couleur',
                                    width: '8%'
                                },
                                {
                                    field: 'nombreColis',
                                    displayName: 'Nb Colis',
                                    width: '6%'
                                }, {
                                    field: 'quantiteColis',
                                    displayName: 'QteColis',
                                    width: '7%'
                                },
                                {
                                    field: '',
                                    width: '7%',
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

                $scope.setPagingData = function (data, page,
                    pageSize) {
                    var pagedData = data.slice((page - 1) *
                        pageSize, page * pageSize);
                    $scope.myData = pagedData;
                    $scope.totalServerItems = data.length;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
                };

                $scope.getPagedDataAsync = function (pageSize, page,
                    searchText) {
                    setTimeout(
                        function () {
                            var data;
                            var eclatementCourante = $scope.eclatementCourante;
                            if (searchText) {
                                console.log("if");
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/ficheColisage/rechercheMulticritere",
                                        eclatementCourante)
                                    .success(
                                        function (
                                            largeLoad) {
                                            data = largeLoad.list
                                                .filter(function (
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
                                console.log("else");
                                eclatementCourante = {};
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/ficheColisage/rechercheMulticritere",
                                        eclatementCourante)
                                    .success(
                                        function (
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
                        function (newVal, oldVal) {
                            if (newVal !== oldVal &&
                                newVal.currentPage !== oldVal.currentPage) {
                                $scope
                                    .getPagedDataAsync(
                                        $scope.pagingOptions.pageSize,
                                        $scope.pagingOptions.currentPage,
                                        $scope.filterOptions.filterText);
                            }
                        }, true);
                $scope.$watch('filterOptions', function (newVal,
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
                $scope.onRefSelected = function (item) {
                    $scope.selectedRef = item;
                    $scope.formToSave.selectedRef = $scope.selectedRef;

                };

                // used to add new element into list of table
                //var _ordre = 0;
                $scope.addElement = function (_ordre) {

                    //debugger;



                    var tmpElement = {
                        poidbrut: 0,
                        couleurId: null,
                        quantite: 0,
                        poidNet: 0,
                    };

                    if ($scope.finalOperationsList
                        .indexOf(tmpElement) == -1) {
                        $scope.finalOperationsList.push(tmpElement);
                        //                        $scope.eclatementCourante.tempsTotal = $scope.getTotalTemps();
                        //                        $scope.eclatementCourante.nbOperation = $scope.getTotalNbOperations();
                        var t = parseInt(_ordre) + 1;
                        _ordre = t;

                    }
                };

                // first function to call
                var _init = function () {

                    $scope.displayMode = "recherche";




                    $scope.finalOperationsList = [];
                    $scope.finalOperationsList2 = [];

                    $scope.eclatementCourante = {};



                    $scope.rechercheeclatement({});

                    $scope.displayMode = "list";

                };



                // used to delete an element from the list
                $scope.deleteElement = function (item, indexLine) {
                    //                  var tr = parseInt($scope.item.ordre);
                    //                  tr-=tr;
                    //                  $scope.item.ordre=tr ;
                    if ($scope.finalOperationsList.length >= 1) {
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
                var _deleteUsedElement = function (item) {
                    for (var i = 0; i < $scope.copyofOpList.length; i++)
                        if ($scope.copyofOpList[i].codeOperation === item.codeOperation) {
                            $scope.copyofOpList.splice(i, 1);
                            //  console.log("$scope.copyofOpList Call 2   "+JSON.stringify($scope.copyofOpList));
                            break;
                        }
                };


                // this function is used to set the final object and
                // call the WS of save
                $scope.saveForm = function () {

                    //debugger;
                    var finalFormToSave = {

                        finalOperationsList: []
                    };
                    //finalFormToSave.selectedRef.id = $scope.formToSave.selectedRef.id;
                    //finalFormToSave.selectedRef.ref = $scope.formToSave.selectedRef.ref;
                    for (var index = 0; index < $scope.finalOperationsList.length; index++) {
                        var index1 = index + 1;
                        var tmpElement = {
                            // otrdre :
                            // $scope.formToSave.finalOperationsList[index].order,
                            // ordre: index1,


                            tailleDesignation: $scope.finalOperationsList[index].tailleDesignation,
                            quantite: $scope.finalOperationsList[index].quantite,
                            quantiteRestante: $scope.finalOperationsList[index].quantiteRestante,
                            pcb: $scope.finalOperationsList[index].pcb,
                            poidsNet: $scope.finalOperationsList[index].poidsNet,
                            poidsBrut: $scope.finalOperationsList[index].poidsBrut,
                        };
                        finalFormToSave.finalOperationsList
                            .push(tmpElement);
                    }
                    //call ws to save the data 

                    //   $scope.finalOperationsList=finalFormToSave.finalOperationsList;
                }

                $scope.addElement2 = function (_ordre) {

                   /* ordre avant le 19.04.2019 ==> tmpElement
                      ordre: (_ordre === undefined) ? (parseInt($scope.finalOperationsList2[$scope.finalOperationsList2.length - 1].ordre) + 1) :
                    _ordre,*/
                    if ($scope.finalOperationsList2.length > 0) {
                        var tmpElement = {
                            ordreFiche:(_ordre === undefined) ? ($scope.finalOperationsList2.length +1) : _ordre,
                            ordre: (_ordre === undefined) ? ($scope.finalOperationsList2.length + 1) :
                                _ordre,
                            num: (_ordre === undefined) ? (parseInt($scope.finalOperationsList2[$scope.finalOperationsList2.length - 1].ordre) + 1) :
                                _ordre,
                            tailleId: null,
                            couleurId: null,
                            quantite: 0,
                            numMatelas: 0,
                        };
                    }
                    else {
                        var tmpElement = {
                            ordreFiche : 1,
                            ordre: 1,

                            num: 1,

                            tailleId: null,
                            couleurId: null,
                            quantite: 0,
                            numMatelas: 0,
                        };
                    }



                    if ($scope.finalOperationsList2
                        .indexOf(tmpElement) == -1) {
                        $scope.finalOperationsList2.push(tmpElement);
                        //                          $scope.eclatementCourante.tempsTotal = $scope.getTotalTemps();
                        //                          $scope.eclatementCourante.nbOperation = $scope.getTotalNbOperations();
                        var t = parseInt(_ordre) + 1;
                        _ordre = t;

                    }
                };

                // used to delete an element from the list
                $scope.deleteElement2 = function (item, indexLine) {
                    //                    var tr = parseInt($scope.item.ordre);
                    //                    tr-=tr;
                    //                    $scope.item.ordre=tr ;
                    if ($scope.finalOperationsList2.length >=1) {
                        // delete line from final list
                        $scope.finalOperationsList2.splice(
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
                var _deleteUsedElement2 = function (item) {
                    for (var i = 0; i < $scope.copyofOpList.length; i++)
                        if ($scope.copyofOpList[i].codeOperation === item.codeOperation) {
                            $scope.copyofOpList.splice(i, 1);
                            //  console.log("$scope.copyofOpList Call 2   "+JSON.stringify($scope.copyofOpList));
                            break;
                        }
                };
                $scope.gridOptions = {

                    data: 'myData',
                    columnDefs: 'colDefs',
                    enablePaging: true,
                    showFooter: true,
                    totalServerItems: 'totalServerItems',
                    enableHighlighting: true,
                    pagingOptions: $scope.pagingOptions,
                    selectedItems: $scope.selectedRows,
                    filterOptions: $scope.filterOptions,
                };

                // _init();
                //$scope.rechercheeclatement({});



            }
        ])
    //end controller

