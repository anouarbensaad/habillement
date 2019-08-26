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
    .module('gpro.feuilleSaisie', ["ui.sortable", "xeditable"])

    .controller(
        'FeuilleSaisieCtrl', [
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
            'FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR',
            function($scope, $http, elementFactory, $filter,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$route,$log,
                FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR) {

                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.feuilleCourante = {};
                $scope.listPaquet = [];
             
             // Déclaration des variables globales utilisés
                    var data;
                    $scope.displayMode = "list";
                    //bouton pdf hide
                    $scope.modePdf = "notActive";
                    $scope.feuilleCourante = null;
                    //init modification Metrage du rouleau
                    $scope.isModifie = false;
                    $scope.listeFeuilleSaisieFini = [];
                    $scope.personnelNom = "";
              	    $scope.personnelPrenom = "";
                    
                    //init urlValider
                    $scope.urlValider = "";
                    $scope.nbrColis = 0;
                
                $scope.feuilleSaisiePresence = null ;
                $scope.feuilleSaisieChaine = null;
                $scope.feuilleSaisieDate = null;
                
				$scope.personnelDateChaineExistError = false;
				$scope.alertMsg = '';

				
				/***************************************************
				 * Notifications
				 **************************************************/
				
				$scope.hiddenNotif ="true";
				
				
				$scope.showNotif = function(){
					$scope.hiddenNotif ="false";
				}
				
				$scope.closeNotif = function(){
					$scope.hiddenNotif ="true";
				}
                
                
            	/************* Init validation error values ************/
				
				$scope.initPersonnelDateChaineErrorValue = function(){
					$scope.personnelDateChaineExistError = false;
				}
				
				
                /*******/
                  $scope.detailPersonnel = function(personnelId){
                	  if(angular.isDefined(personnelId)){
                		  $http
                          .get(
                              baseUrlGpao +"/personnel/getById:"+personnelId)
    	                  .success(
    	                      function(resultat) {
    	                    	  $scope.personnelNom =  resultat.nom;
    	                    	  $scope.personnelPrenom = resultat.prenom;
    	                      });
                	  }
                	  
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

                // Rechercher Gamme
                $scope.rechercheFeuilleSaisie = function(feuilleCourante) {
                    
                    $http
                        .post(
                            baseUrlGpao +
                            "/feuilleSaisie/rechercheMulticritere",
                            feuilleCourante)

                    .success(
                        function(resultat) {


                            $scope.myData = resultat.list;
                            // data, page,pageSize
                           //console.log("-----Data Gamme Produit:"+JSON.stringify($scope.myData));
                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.rechercheFeuilleSaisieForm
                                .$setPristine();
                            $scope.displayMode = "rechercher";
                            $scope.displayAlert = "sleep";
                        });
                
              }
               
                // Liste des Gammes OF
                $scope.listeMatricule = function() {
                    $http
                        .get(baseUrlGpao + "/feuilleSaisie/listPersonnel")
                        .success(
                            function(dataProduit) {
                                $scope.listMatricule= dataProduit;
                            });
                }
                $scope.listeMatricule();
                /*------------------------------------------Aleas--------------------------------------*/
                //Liste des Aleas
                $scope.listAleas = function() {
                    $http
                        .get(baseUrlGpao + "/aleas/all")
                        .success(
                            function(data) {
                                $scope.listAleas= data;

                            });
                }
                $scope.listAleas();
//                $scope.aleas ={};
//               $scope.aleas.aleasId = $scope.listAleas[0].id;
                
           	 // Ajout ligne  Aleas
//                $scope.listAleasIns = [];
                $scope.feuillesaisie = {};
                $scope.feuillesaisie.listElementAleas = [];
				 $scope.ajoutAleas = function() {
					 $scope.aleasInserree = {
								duree:'',
								observation:''
				};
					 

//					 $scope.factureVenteCouranteEdit.diversFactureValue.push($scope.aleasInserree);feuilleCourante
					 $scope.feuillesaisie.listElementAleas.push($scope.aleasInserree);
				 };
				 
				 // Enregistrer Aleas
				 $scope.saveAleas = function(data, id) {
					 $scope.deleteValue = "non";
					 angular.extend(data, {
							 id : id
						 });
					 
				 };
				 
				 $scope.deleteValue="oui";
				 //Annuler ajout Aleas
				 $scope.cancelAjoutAleas = function(rowform, index, id, aleasId, duree, observations,listAleasIns){
					 
					 if (angular.isDefined(id)) {

						 $log.debug("DEF ID");
						 $scope.deleteValue="non";
						 rowform.$cancel();
						 $log.debug("CANCEL");
					 }else{	
						 $log.debug("UND ID  "+id);
						 if(aleasId ==""){
							 $scope.deleteValue=="oui"
								 $log.debug("aleasId : "+aleasId);
							 $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
							 listAleasIns.splice(index, 1);
							 rowform.$cancel();
							 $log.debug("DELETE")
						 }else{
							 $log.debug("aleasId :"+aleasId);
							 $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
							 rowform.$cancel();
							 $log.debug("CANCEL");
						 }
					 }
					 $scope.deleteValue="oui";
				 }
				
				 
				 // Supprimer Aleas
				 $scope.removeAleas = function(index) {
					 $scope.feuillesaisie.listElementAleas.splice(index, 1);
				 };


				 
				 
				 
				 
             

                //Recherche
               // $scope.rechercheFeuilleSaisie({});
               
                // ** Ajout Gamme
                $scope.AffectationFeuilleSaisie = function(feuilleSaisie) {
                    
                    
                    
                    if(Object.keys(feuilleSaisie).length == 0){
                        
                       return;
                }
                    
                    else{
                        
                        $scope.feuilleCourante = {};

                        $http
                        .post(
                            baseUrlGpao +
                            "/feuilleSaisie/rechercheMulticritere",
                             $scope.feuilleCourante)

                    .success(
                        function(resultat) {
                            $scope.feuilleCourante.minPresence = resultat.list[0].minPresence;
                            $scope.feuilleCourante.chaineId = resultat.list[0].chaineId
                            var d = new Date(),
                            month = '' + (d.getMonth() + 1),
                            day = '' + d.getDate()-1,
                            year = d.getFullYear();
                            if (month.length < 2) month = '0' + month;
                            if (day.length < 2) day = '0' + day;
                            var dateSaisieFSaisie = [year,month,day].join('-')

                            $scope.feuilleCourante.dateSaisie = dateSaisieFSaisie;
                            

                        });

                        $scope.feuilleCourante = feuilleSaisie ? angular
                                .copy(feuilleSaisie) : {};
                    
                    $scope.creationFeuilleSaisieForm.$setPristine();
                    
                 $('.updateM').hide();
                 $('.addM').show();
                   
                 
    
                    
                    //mode edit activé
                        $scope.displayMode = "edit";
                    
                    // show tableau Code à Barre
                        $log.debug("mode : Code à Barre");
                        $scope.displayView = "view1";
                    
                    }
                }

             
             
                
             // Sauvegarder Gamme
                $scope.sauvegarderAjout = function(feuillesaisie) {
                     
                    //console.log("gamme :"+gamme);
                    if (angular.isDefined(feuillesaisie.id)) {
                        
                        $scope.updateFeuilleSaisie(feuillesaisie);
                    
                    } else {
                        
                        $scope.creerFeuilleSaisie(feuillesaisie);
                        
                    }
                    
                }
                
                    // Création Gamme

                $scope.creerFeuilleSaisie = function(feuillesaisie) {
                    
                    //feuillesaisie.pscProd = $scope.nbrColis;
                    feuillesaisie.listSaisie = $scope.listeFeuilleSaisieFini;
                    feuillesaisie.listElementAleas = $scope.feuillesaisie.listElementAleas;     
                            
                            $http.post(baseUrlGpao + "/feuilleSaisie/create", feuillesaisie)
                                 .success(
                                    function(result) {
                                        
                                    	 if(result == FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR){
												$scope.personnelDateChaineExistError = true;
												$scope.alertMsg = "Personnel / Date / Chaine existents";
												$scope.showNotif();
											}
											else{
												
												 //idfeuilleSaisie : valider avec idfeuilleSaisie
			                                    $scope.idfeuilleSaisie = result;
			                                        
		                                        //getBonSortie 
			                                    $http.get(baseUrlGpao + "/feuilleSaisie/getById:"
			                                                         + result)
			                                            .success(
			                                                    function(dataGetBonSortie) {
			                                                        
			                                                        
//			                                                      // bouton PDF activé
			                                                        $scope.modePdf = "actif";
//			                                                      
//			                                                      // show codeABarre
			                                                        $log.debug("mode : codeABarre");
			                                                        $scope.displayView = "view1";
//			                                                      //modeValider true
			                                                        $scope.modeValider = true;
//			                                                      //vider la liste et la remplacer par la liste Reelle des Rouleaux
			                                                        $scope.listCode = [];
//			                                                      //ListeCode à barre correspondante à ce BonSortie
			                                                        angular.forEach(dataGetBonSortie.listeFeuilleSaisieFini, function(rouleauFini, key){
			                                                            $scope.listCode.push(rouleauFini.codeBarre); 
			                                                        });
//			                                                      
//			                                                      // Attributs de Recherche
			                                                        $scope.feuilleCourante = dataGetBonSortie ? angular
			                                                                .copy(dataGetBonSortie)
			                                                                : {};
			                                                                
			                                                       $scope.annulerAjout();
			                                                    });
											}
                                    	 
                                   
                                      
                                    
                                    
                                    });
                        

                }
             
               
                
               
               
                
                /** Fin de gestion des Gammes */
                

                $scope.pagingOptions = {
                    pageSizes: [5, 10, 13],
                    pageSize: 13,
                    currentPage: 1
                };
                $scope.colDefs = [];
                $scope
                    .$watch(
                        'myData',
                        function() {
                            $scope.colDefs = [
                                
                               {
                                    field: 'personnelMatricule',
                                    displayName: 'Matricule',
                                    width: '13%'
                                },
                                {
                                    field: 'periode',
                                    displayName: 'Periode',
                                    width: '10%'
                                },
                                {
                                    field: 'dateSaisie',
                                    displayName: 'Date Saisie',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    width: '10%'
                                },  {
                                    field: 'chaineDesignation',
                                    displayName: 'Chaine',
                                    width: '10%'
                                },  {
                                    field: 'rendement',
                                    displayName: 'Rendement',
                                    cellFilter: 'prixFiltre',
                                    width: '10%'
                                }, {
                                    field: 'activite',
                                    displayName: 'Activite',
                                    cellFilter: 'prixFiltre',
                                    width: '10%'
                                }, {
                                    field: 'minAleas',
                                    displayName: 'Aleas',
                                    width: '10%'

                                }, {
                                    field: 'minProd',
                                    displayName: 'Min Produite',
                                    width: '10%'

                                },{
                                    field: 'minPresence',
                                    displayName: 'Min Presence',
                                    width: '10%'

                                }, {
                                    field: '',
                                    width:'7%',
                                    cellTemplate: '<div class="buttons TableHeaderalignment" ng-show="!rowform.$visible" >' +
                                        '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerFeuilleSaisie()"><i class="fa fa-fw fa-pencil"></i></button>' +
                                        '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(24)"> <i class="fa fa-fw fa-trash-o"></i></button></div>'
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
                            var feuilleCourante = $scope.feuilleCourante;
                            
                            if (searchText) {
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/feuilleSaisie/rechercheMulticritere",
                                        feuilleCourante)
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
                                        });

                            } else {
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/feuilleSaisie/rechercheMulticritere",
                                        feuilleCourante)
                                    .success(
                                        function(
                                            largeLoad) {
                                            $scope
                                                .setPagingData(
                                                    largeLoad.list,
                                                    page,
                                                    pageSize);
                                            
                                            /*$scope.rechercheFeuilleSaisie({});*/
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
                
              //==================== Partie  Bon Sortie ============================================
                
                /********************************
                     * Gestion des listes deroulantes*
                     * à changer par Cache
                     ********************************/
                    
                
    
                        
                    /**
                     *  saisie des codes à barre et clique sur "Enter" 
                     */
                    $scope.listCode = [];
                    
                    $scope.modeValider = false;
                    $scope.keyPress = function(keyCode, code){
                         
                           if(keyCode == '13'){
                               //show btn Valider
                               $scope.modeValider = true ;
                               //increment nbr de colis si le code à barre n'existe pas dans listeCode
                              // $log.debug("ListeCode ="+JSON.stringify($scope.listCode,null,"  ") );
                               if($scope.listCode.indexOf(code) == -1){
                                   //add the bareCodes entred to the liste 
                                $scope.listCode.push(code);
                                $scope.nbrColis ++;
                               }else{
                                   $log.debug(code +"Existe");
                               }
                               //init field feuilleCourante.listBareCode
                               $scope.feuilleCourante.listBareCode = "";
                           }
                        }
                    
                    /**
                     * validerCode: en cliquant sur ce bouton 'Valider', le tableau complet s'affiche.  view2 activée
                     */ 
                    $scope.displayView = "view1";
                    $scope.validerCode = function(feuilleCourante){
                        
                
                        
                       // $log.info(" Valider ..");
                       // $log.info("*-- listCode : "+JSON.stringify($scope.listCode, null, "    ") );
                        
                        $scope.modeValider = true;
                        
                        //idfeuilleSaisie: si undefined -> urlValier SANS id, sinon -> urlValier AVEC idfeuilleSaisie
                        
                      //  $log.info("log0 : "+$scope.idfeuilleSaisie);
                        if(angular.isDefined($scope.idfeuilleSaisie)){
                      //  $log.debug("log1");
                        
                        
                        if($scope.idfeuilleSaisie != ''){
                            //modification
                            
                            
//                         //Url With idfeuilleSaisie
                            $scope.urlValider =  baseUrlGpao+ "/feuilleSaisie/validate";
                            feuilleCourante.listBareCode = $scope.listCode;   
                            
                            console.log("-1------feuilleCourante.listBareCode: "+JSON.stringify(feuilleCourante.listBareCode, null, "    ") );
                            
                            
                            console.log("-1------feuilleCourante: "+JSON.stringify(feuilleCourante, null, "    ") );
                            
//                         //Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
                           $http
                                  .put(
                                      $scope.urlValider,feuilleCourante)
                            .success(
                                    function(resultat) {
                                       // $log.info("=======Rslt "+resultat);
                                        $scope.nbrColis = resultat.listSaisie.length;
                                      //  $log.info("=======nbrColis "+$scope.nbrColis);
                                        //listeRouleauFini
                                        $scope.listeFeuilleSaisieFini = resultat.listSaisie;
                                        
                                        $log.debug("---------** ** $scope.listeFeuilleSaisieFini"+ resultat);
                                        $log.debug("-- listeFeuilleSaiseFini : "+JSON.stringify($scope.listeFeuilleSaisieFini, null, "    ") );
                                    });
                        }else{
                            //IDVIDE
                          //  $log.info("$scope.idfeuilleSaisie vide! ");
                        }
                    }else{
                        //creation
                        
                  
                        
                        $scope.urlValider =  baseUrlGpao+ "/feuilleSaisie/validate";
                        feuilleCourante.listBareCode = $scope.listCode; 
                        //Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
                     //   console.log("feuilleCourante : " +JSON.stringify(feuilleCourante));
                        
                        
                        console.log("-2------feuilleCourante: "+JSON.stringify(feuilleCourante, null, "    ") );
                        
                        $http
                               .put(
                                      $scope.urlValider,feuilleCourante)
                        .success(
                                function(resultat) {
                                
                                     //   $log.info("=======Rslt "+resultat);
                                        $scope.nbrColis = resultat.listSaisie.length;
                                     //   $log.info("=======nbrColis "+$scope.nbrColis);
                                        
                                    $scope.listeFeuilleSaisieFini = resultat.listSaisie;
                                    
                                    //ajouter ça
                                    //$scope.listeFeuilleSaisieFini.codeBarre = resultat.listBareCode;
                                    
                                    $log.debug("$scope.listeFeuilleSaisieFini: "+ resultat.length);
                                    $log.debug("-- $scope.listeFeuilleSaisieFini : "+JSON.stringify($scope.listeFeuilleSaisieFini, null, "    ") );
                                });
                    }
                  
                        $scope.displayMode = "edit";
                        $scope.displayView = "view2";
                        
                        //Aleas
//                        $scope.listAleasIns = [];
//                        $scope.aleas = {};
                        
                    }
                
                    
                
        
                    $scope.deleteValue="oui";
                    //Annuler Ajout
                    $scope.cancelAddRouleau = function(rowform, index, id, designation, liste){
                    //$log.debug("* Designation "+liste[0].designation);
                          if (angular.isDefined(id)) {
                                  $log.debug("DEF ID");
                                  $scope.deleteValue="non";
                                  rowform.$cancel();
                                  $log.debug("CANCEL");
                          }else{    
                              $log.debug("UND ID  "+id);
                              if(designation ==""){
                                  $scope.deleteValue=="oui"
                                  $log.debug("Designation : "+reference);
                                  $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
                                  liste.splice(index, 1);
                                  rowform.$cancel();
                                  $log.debug("DELETE")
                              }else{
                                  $log.debug("Designation :"+reference);
                                  $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
                                  rowform.$cancel();
                                  $log.debug("CANCEL");
                              }
                        }
                          $scope.deleteValue="oui";
                }
                    
                

                // Supprimer Rouleau
                $scope.removeRouleau = function(index) {
                    $scope.listeFeuilleSaisieFini.splice(index, 1);
                    //dec nbrColi 
                    $scope.nbrColis --;
                };
                
                        // annuler Recherche
                        $scope.annulerAjout = function() {
                        	
							/** CLose notif and init validation variables**/

                        	$scope.closeNotif();
							$scope.initPersonnelDateChaineErrorValue();
							
                            
                            $scope.modePdf = "notActive";
                            
                            $scope.feuilleCourante = {
                                                        "matricule": "",
                                                        "chaineId": "",
                                                        "dateSaisieMin": "",
                                                        "dateSaisieMax": "",
                                                        "minAleas": 0,
                                                        "dateSaisie": "",
                                                        "rendementMin": "",
                                                        "rendementMax": "",
                                                        "activiteMin": "",
                                                        "activiteMax": ""};

                            $scope.personnelNom = "";
                            $scope.personnelPrenom = "";
                            $scope.nbrColis = 0;
                            $scope.listCode = [];
                            $scope.modeValider = false;
                            $scope.listeFeuilleSaisieFini = [];
                            $scope.feuilleCourante.listBareCode = "";
                            $scope.idfeuilleSaisie = undefined;
//                            $scope.listAleasIns = [];
                            
                            
                            $scope.rechercheFeuilleSaisie({});
                            $scope.rechercheFeuilleSaisieForm.$setPristine();
                            $scope.creationFeuilleSaisieForm.$setPristine();
                            $scope.displayMode = "list";
                            $scope.displayView = "view1";
                            
                             
                        }
                        
                    
                    
                        
                        // Ajout et Modification BonSortie
                        $scope.modifierOuCreerFeuilleSaisie = function() {
                            
                            // bouton PDF activé
                            $scope.modePdf = "actif";
                            
                            var index = this.row.rowIndex;
                            
                            //idfeuilleSaisie: va etre affecté à l'Url du service Valider en cas de modification 
                            $scope.idfeuilleSaisie = $scope.myData[index].id;
                            $log.debug("idfeuilleSaisie "+$scope.idfeuilleSaisie);
                             $('.updateM').show();
                             $('.addM').hide();
                            // getBonSortie
                            $http
                                    .get(
                                            baseUrlGpao
                                                    + "/feuilleSaisie/getById:"
                                                    + $scope.myData[index].id)
                                    .success(
                                            function(datagetBonSortie) {
                                                
                                                // Nbre Colis de ce bon de sortie
                                               // $scope.nbrColis = datagetBonSortie.pscProd; 

                                                $scope.feuilleCourante = datagetBonSortie;
                                                $scope.listeFeuilleSaisieFini = datagetBonSortie.listSaisie;
                                                //ListeCode à barre correspondante à ce BonSortie
                                                angular.forEach($scope.listeFeuilleSaisieFini, function(rouleauFini, key){
                                                    $scope.listCode.push(rouleauFini.codeBarre); 
                                                });

                                                $scope.detailPersonnel(datagetBonSortie.personnelId);
                                               
	                                            $scope.creationFeuilleSaisieForm.$setPristine();
                                                $scope.myData[index].listeFeuilleSaisieFini = $scope.listeFeuilleSaisieFini;

                                            });

                            $scope.feuilleCourante = $scope.myData[index] ? angular
                                    .copy($scope.myData[index])
                                    : {};
                        
                                    
                             $scope.chaineCourante =  $scope.feuilleCourante.chaineId;
                             $scope.personnelCourante =  $scope.feuilleCourante.personnelMatricule;
                             $scope.dateCourante=  $scope.feuilleCourante.dateSaisie;
                                	

                        // mode edit activé 
                            $scope.displayMode = "edit";
                        // show codeABarre
                            $log.debug("mode : codeABarre");
                            $scope.displayView = "view1";
                        //modeValider true
                            $scope.modeValider = true;
                        }

                         //listChaines
                        $scope.listeChaines = function() {
                            $http
                                .get(baseUrlGpao + "/chaine/all")
                                .success(
                                    function(dataProduit) {
                                        $scope.listChaines= dataProduit;
                                    });
                        }
                        $scope.listeChaines();

                        // Mise à jour des BonSorties
                        $scope.updateFeuilleSaisie = function(feuillesaisie) {
                            
                        	feuillesaisie.chaineBeforeUpdate = $scope.chaineCourante;
                        	feuillesaisie.personnelBeforeUpdate = $scope.personnelCourante;
                        	feuillesaisie.dateBeforeUpdate = $scope.dateCourante;

                        	
                            feuillesaisie.pscProd = $scope.nbrColis;
                            feuillesaisie.listSaisie = $scope.listeFeuilleSaisieFini;
                            feuillesaisie.listElementAleas = $scope.feuillesaisie.listElementAleas;
                            
                            $http
                                    .put(
                                            baseUrlGpao
                                                    + "/feuilleSaisie/update",  feuillesaisie)
                                    .success(
                                            function(result) {
                                                
                                                
                                            	if(result == FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR){
    												$scope.personnelDateChaineExistError = true;
    												$scope.alertMsg = "Personnel / Date / Chaine existents";
    												$scope.showNotif();
    											}
    											else{
    												
    												for (var i = 0; i < $scope.myData.length; i++) {

                                                        if ($scope.myData[i].id == result.id) {

                                                            $scope.myData[i] = result;
                                                            break;
                                                        }
                                                    }

    												 //getfeuillesaisie 
                                                    $http.get(baseUrlGpao + "/feuilleSaisie/getById:"
                                                 + result)
                                                        .success(
                                                                function(dataGetBonSortie) {
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    // show codeABarre
                                                                    $scope.displayView = "view1";
                                                                    //modeValider true
                                                                    $scope.modeValider = true;
                                                                    
//                                                                  //vider la liste et la remplacer par la liste Reelle des Rouleaux
                                                                    $scope.listCode = [];
//                                                                  //ListeCode à barre correspondante à ce BonSortie
                                                                    angular.forEach(dataGetBonSortie.listeFeuilleSaisieFini, function(rouleauFini, key){
                                                                         $scope.listCode.push(rouleauFini.codeBarre); 
                                                                    });
//                                                                  
//                                                                  // Attributs de Recherche
                                                                  $scope.feuilleCourante = dataGetBonSortie ? angular
                                                                            .copy(dataGetBonSortie)
                                                                            : {};
                                                                           $scope.feuillesaisie.listElementAleas = $scope.feuilleCourante.listElementAleas; 
                                                                           
                                                                  $scope.annulerAjout();    
                                                                });
                                                    
    											}
                                            });
                        }


                        // Suppression BonSortie
                        $scope.supprimerFeuilleSaisie = function() {
                            
                            $http(
                                    {
                                        method : "DELETE",
                                        url : baseUrlGpao
                                                + "/feuilleSaisie/delete:"
                                                + $scope.myData[$scope.index].id
                                    }).success(function() {
                                $log.debug("Success Delete");
                                $scope.myData.splice($scope.index, 1);
                                $scope.closePopupDelete();
                                //$scope.$apply();
                            });
                            $scope.closePopupDelete();
                            $scope.rechercheFeuilleSaisie({});
                        }
                    
          /*** PDF ***/
      //generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
                $scope.download = function(id) {
                        //$log.debug("-- id"+id);
//                      var url = UrlAtelier+"/report/bonsortiefini?id=" + id+"&type=pdf";
//                      downloadService.download(url)
//                              .then(
//                                      function(success) {
//                                          $log.debug('success : '+ success);
//                                          $scope.annulerAjout();
//                                      },
//                                      function(error) {
//                                          $log.debug('error : '+ error);
//                                      });
                    };
                    
                    //generer rapport de tous les bons de sortie. mode : List 

      //conversion date en String
        function formattedDate(date) {
            var d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;
            return [year,month,day].join('-');
        }
    
            $scope.GenerateAllOuProductivite = function(feuilleCourante, action) {
                  var newdateSaisieMinFormat="";
                  if(angular.isDefined(feuilleCourante.dateSaisieMin)){
                    $log.debug("==dateSaisieMin "+feuilleCourante.dateSaisieMin);
                    
                    if(feuilleCourante.dateSaisieMin != ""){
                      newdateSaisieMinFormat = formattedDate(feuilleCourante.dateSaisieMin);
                  //    $log.info("===== newdateSaisieMinFormat "+newdateSaisieMinFormat);
                    }else{
                    //  $log.info("===== newdateSaisieMinFormat is Null");
                   //   newdateSaisieMinFormat = "";
                    }
                  }else{
                    $log.debug("==dateSaisieMin Undefined");
                  }

                  var newdateSaisieMaxFormat="";
                  if(angular.isDefined(feuilleCourante.dateSaisieMax)){
                    $log.debug("==dateSaisieMax "+feuilleCourante.dateSaisieMax);
                    
                    if(feuilleCourante.dateSaisieMax != ""){
                      newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateSaisieMax);
                   //   $log.info("===== newdateSaisieMaxFormat "+newdateSaisieMaxFormat);
                    }else{
                    //  $log.info("===== newdateSaisieMaxFormat is Null");
                      newdateSaisieMaxFormat = "";
                    }
                  }else{
                    $log.debug("==dateSaisieMax Undefined");
                  }

             //   $log.info("-- feuilleCourante" + JSON.stringify($scope.feuilleCourante, null, "  ") );
               
                if(action != null){
                    var url;
                    if(action == 1){
                       // $log.info("-- ListeFicheSaisie");
                        url = baseUrlGpao + "/report/listeFicheSaisie?matricule=" + feuilleCourante.matricule
                                                          + "&dateSaisieMin="+newdateSaisieMinFormat
                                                          + "&dateSaisieMax="+newdateSaisieMaxFormat
                                                          + "&chaineId="+feuilleCourante.chaineId
                                                          + "&rendementMin="+feuilleCourante.rendementMin
                                                          + "&rendementMax="+feuilleCourante.rendementMax
                                                          + "&activiteMin="+feuilleCourante.activiteMin
                                                          + "&activiteMax="+feuilleCourante.activiteMax
                                                          + "&type=pdf";
                    }else if(action == 2){
                      //  $log.info("-- Productivite");
                        url = baseUrlGpao + "/report/productiviteGlobaleByMatChaineDateGrByMat?matricule=" + feuilleCourante.matricule
                                                          + "&dateSaisieMin="+newdateSaisieMinFormat
                                                          + "&dateSaisieMax="+newdateSaisieMaxFormat
                                                          + "&chaineId="+feuilleCourante.chaineId
                                                          + "&rendementMin="+feuilleCourante.rendementMin
                                                          + "&rendementMax="+feuilleCourante.rendementMax
                                                          + "&activiteMin="+feuilleCourante.activiteMin
                                                          + "&activiteMax="+feuilleCourante.activiteMax
                                                          + "&type=pdf";
                    }
                    //Generate
                    downloadService.download(url).then(
                        function(success) {
                             $log.debug('success : ' + success);
                        }, function(error) {
                             $log.debug('error : ' + error);
                        });
                    }
                }
                    /** Fin de gestion des Grids de la BonSortie */
                
                
        

            }
        ])
    //end controller


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
 //Filtre sur le champ prix du tableau ProduitBS: retourne les 3 chiffres apres le point .
         .filter('prixFiltre', function() {
             return function(prix) {
                 if(prix){
                     prix = prix.toString();
                     // $log.debug("Prix "+prix);
                     if(prix.indexOf(".") == -1){
                         return prix;
                     }else{
                         var index = prix.indexOf(".");
                         // $log.debug("index . "+index);
                         return prix.substring(0,index+3);
                     } 
                 }
             };
         })

.controller(
                    'DateIntroCtrl',
                    [
                            '$scope',
                            function($scope) {
                                $scope.maxToDay = new Date();
                                // date piker defit
//                                  $scope.today = function() {
//                                      $scope.articleCourante.dateIntroduction = new Date();
//                                  };
//                                  $scope.today();
                                $scope.clear = function() {
                                    $scope.articleCourante.dateIntroduction = null;
                                };
                                // Disable weekend selection
                                $scope.disabled = function(date, mode) {
                                    return (mode === 'day' && (date.getDay() === 0 || date
                                            .getDay() === 6));
                                };
                                $scope.toggleMin = function() {
                                    $scope.minDate = $scope.minDate ? null
                                            : new Date();
                                };
                                $scope.toggleMin();
                                $scope.open = function($event) {
                                    $event.preventDefault();
                                    $event.stopPropagation();
                                    $scope.opened = true;
                                };
                                $scope.dateOptions = {
                                    formatYear : 'yy',
                                    startingDay : 1
                                };
                                $scope.initDate = new Date('20/08/2015');
                                 $scope.formats = ['dd-MMMM-yyyy', 'dd/MM/yyyy', 'dd.MM.yyyy', 'shortDate'];
                                    $scope.format = $scope.formats[0];

                            } ])
;
