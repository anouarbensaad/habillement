	'use strict'

	angular
			.module('gpro.produit', [ "ngResource" ])
			.controller(
					'produitController',
					[
							'$scope',
							'$filter',
							'$http',
							'$log',
							'downloadService',
							'baseUrl',
							'PRODUIT_REF_EXIST_ERROR',
							function($scope, $filter, $http, $log, downloadService, baseUrl,
									PRODUIT_REF_EXIST_ERROR) {
								// Déclaration des variables globales utilisés
								var data;
								$scope.modePdf = "notActif";
								$scope.displayMode = "list";
								$scope.produitCourante = null;
								$scope.listePartieInteressee = [];
								$scope.listeSeuil = [];
								$scope.listeDocuments = [];
								$scope.listeDocumentProduit = [];
								$scope.listePhaseProduit = [];
								$scope.listeCouleurProduit = [];
								$scope.resultatRecherche = $scope.listeProduit;
								$scope.listeTailleProduit = [];
								$scope.ListFamilleProduitCache = [];
								$scope.ListSousFamilleProduitCache = [];
								$scope.listeSitePartieInteresseeCache = [];
								$scope.ListStandardTailleCache = [];
								$scope.listeDeviseCache = [];
								$scope.listeCouleurProduit = [];
								$scope.listeCouleurProduitCache = [];
								$scope.listeTailleProduitCache = [];
								$scope.ListeDevise = [];
								$scope.prixFab = 0;
								$scope.listeverificationColor = [];
								$scope.colorIdRemoved = [];
								$scope.creation = true;
								//Pavet Developpement ne s'affiche pas au demarrage de la page
								$scope.isCollapsed = true;
								/***************************************************
								 * Gestion de Cache des Referentiels *
								 **************************************************/
								// Liste des FamilleCache
								$scope.ListFamillesProduitCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeFamilleProduitCache")
											.success(
													function(
															dataFamilleProduitCache) {
														$log.debug("listFamilleProduitCache : "
																		+ dataFamilleProduitCache.length);
														$scope.ListFamilleProduitCache = dataFamilleProduitCache;

													});
								}

								// Liste des SousFamilleCache
								$scope.ListSousFamillesProduitCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeSousFamilleProduitCache")
											.success(
													function(
															dataSousFamilleProduitCache) {
														$log.debug("listeSousFamilleProduitCache : "
																		+ dataSousFamilleProduitCache.length);
														$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

													});
								}

								// Liste des SiteeCache
								$scope.listeSitesPartieInteresseeCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeSitePartieInteresseeCache")
											.success(
													function(dataSiteCache) {
														$log.debug("listeSiteCache : "
																		+ dataSiteCache.length);
														$scope.listeSitePartieInteresseeCache = dataSiteCache;

													});
								}

								// Liste des StandardTailleCache
								$scope.ListStandardTailleCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeStandardTailleProduitCache")
											.success(
													function(
															dataStandardTailleProduitCache) {
														$log.debug("listeStandardTailleProduitCache : "
																		+ dataStandardTailleProduitCache.length);
														$scope.ListStandardTailleCache = dataStandardTailleProduitCache;

													});
								}

								// Liste des DeviseCache
								$scope.ListDeviseCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeDeviseCache")
											.success(
													function(dataDeviseCache) {
														$log.debug("listeDeviseCache :"
																		+ dataDeviseCache.length);
														$scope.ListDeviseCache = dataDeviseCache;

													});
								}
								// Liste des CouleurCache
								$scope.listeCouleurFnc = function() {

									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeCouleurProduitCache")
											.success(
													function(dataCouleurCache) {
														$log.debug("listeCouleurCache : "+ dataCouleurCache.length);
														angular.forEach(dataCouleurCache, function(elementCouleurCache, key){
															elementCouleurCache.isUsed = false;
														});

														$scope.listeCouleurProduitCache = dataCouleurCache;
													});
								}

								// Liste des TailleCache
								$scope.listeTailleProduitCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeTailleProduitCache")
											.success(
													function(dataTailleCache) {
														$log.debug("*LISTETailleCache :"
																		+ dataTailleCache.length);
														$scope.listeTailleProduitCache = dataTailleCache;

													});
								}
								// Liste des PhaseCache
								$scope.listePhaseProduitCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listePhaseProduitCache")
											.success(
													function(dataPhaseCache) {
														$log.debug("listePhaseProduitCache : "
																		+ dataPhaseCache.length);
														$scope.listePhaseProduitCache = dataPhaseCache;

													});
								}

								// Liste des PICache
								$scope.listePartieInteresseeCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listePartieInteresseeCache")
											.success(
													function(dataPICache) {
														$log.debug("listePartieInteresseeCache : "
																		+ dataPICache.length);
														$scope.listePartieInteresseeCache = dataPICache;

													});
								}

								// Liste TypeDocumentCache
								$scope.listeTypeDocumentsCache = function() {
									$http
											.get(
													baseUrl
															+ "/gestionnaireCache/listeTypeDocumentCache")
											.success(
													function(dataTypeDocumentCache) {
														$log.debug("listeTypeDocumentCache : "
																		+ dataTypeDocumentCache.length);
														
														console.log(JSON.stringify(dataTypeDocumentCache,null," "));
														$scope.ListTypeDocumentCache = $filter('filter')(dataTypeDocumentCache, {module:"PRODUIT"});

													});
								}
								
								// Liste des Fiche Besoin
								$scope.ListFicheBesoin = function() {
									$http
											.get(
													baseUrl
															+ "/ficheBesoins/all")
											.success(
													function(
															data) {
														$scope.ListFicheBesoin = data;

													});
								}
								
								$scope.ListFamillesProduitCache();
								$scope.ListSousFamillesProduitCache();
								$scope.listeSitesPartieInteresseeCache();
								$scope.ListStandardTailleCache();
								$scope.ListDeviseCache();
								$scope.listeCouleurFnc();
								$scope.listeTailleProduitCache();
								$scope.listePhaseProduitCache();
								$scope.listePartieInteresseeCache();
								$scope.listeTypeDocumentsCache();
								$scope.ListFicheBesoin(); 
								$scope.referenceExistError = false;
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
								
								$scope.initReferenceErrorValue = function(){
									$scope.referenceExistError = false;
								}
								
								/***************************************************
								 * Gestion des Produits
								 **************************************************/
								
								
								$scope.pagingOptions = {
										pageSizes : [ 5, 10, 13 ],
										pageSize : 13,
										currentPage : 1
									};
								
								$scope.deleteValue = "oui";
								// Annuler Ajout
								$scope.cancelAddProduit = function(rowform, index,
										id, designation, liste) {

									// $log.debug("* Designation
									// "+liste[0].designation);
									if (angular.isDefined(id)) {

										$log.debug("DEF ID");
										$scope.deleteValue = "non";
										rowform.$cancel();
										$log.debug("CANCEL");
									} else {
										$log.debug("UND ID  " + id);
										if (designation == "") {
											$scope.deleteValue == "oui"
											$log.debug("Designation : "
													+ designation);
											$log.debug("$scope.deleteValueOUI : "
													+ $scope.deleteValue);
											liste.splice(index, 1);
											rowform.$cancel();
											$log.debug("DELETE")
										} else {
											$log.debug("Designation :"
													+ designation);
											$log.debug("$scope.deleteValueNON : "
													+ $scope.deleteValue);
											rowform.$cancel();
											$log.debug("CANCEL");
										}
									}
									$scope.deleteValue = "oui";
								}

								// Liste des Devises
								$scope.ListeDevise = function() {
									$http.get(baseUrl + "/devise/all").success(
											function(dataDevise) {
												$log.debug("listeDevise : "+dataDevise.length);
												$scope.ListeDevise = dataDevise;
											});
								}

								// Liste des PartieInteressee
								$scope.listePartieInteressee = function() {
									$http
											.get(baseUrl + "/partieInteressee/all")
											.success(
													function(dataPartieInteressee) {
														$log.debug("listePartieInteressee : "+dataPartieInteressee.length);
														$scope.listePartieInteressee = dataPartieInteressee;
													});
								}
								// Rechercher produit
								$scope.rechercheProduit = function(produitCourante) {
									
									console.log("============produitCourante recherché"+JSON.stringify(produitCourante,null,""));
									$http
											.post(
													baseUrl
															+ "/produit/rechercheProduitMulticritere",
													produitCourante)
											.success(
													function(resultat) {
														
														$scope.myData = resultat.produitValues;
														// Pagination du resultat de
											            // recherche
											            // data, page,pageSize
														
											              $scope
											                .setPagingData(
											                  $scope.myData,
											                  $scope.pagingOptions.currentPage,
											                  $scope.pagingOptions.pageSize);
											              
														$scope.rechercheProduitForm
																.$setPristine();
														$scope.displayMode = "rechercher";
													});//fin success recherche
								}//fin rechercheProduit
								// Annulation de l'ajout
								$scope.annulerAjout = function() {

									/** CLose notif and init validation variables**/

									$scope.closeNotif();
									$scope.initReferenceErrorValue();
									
									$scope.modePdf = "notActif";
									$scope.cnt = 0;
									$scope.creation = true;
									$scope.listCouleurCreation = [];
									$scope.colorIdRemoved = [];
									$scope.produitCourante = {};
									$scope.listeCouleurFnc() ;
									$scope.listeDetailsPrix = [];
									$scope.listePhaseProduit = [];
									$scope.listeDocumentProduit = [];
									$scope.listeCouleurProduit = [];
									$scope.listeTailleProduit = [];
									$scope.colorIdRemoved = [];
									$scope.produitCourante = {
																"reference": "",
																"designation": "",
																"sousfamille": "",
																"partieInteressee": "",
																"prix_inf": "",
																"prix_sup": "",
																"site": "",
																"etat": "",
																"ficheB":""
																};
									//Pavet Developpement ne s'affiche pas au demarrage de la page
									$scope.isCollapsed = true;
									$scope.rechercheProduit({});
									$scope.displayMode = "list";
								}
								// ** Ajout Produit
								$scope.AffectationProduit = function(Produit) {
									$scope.produitCourante = {};
									$scope.produitCourante = Produit ? angular
											.copy(Produit) : {};
									$scope.displayMode = "edit";
								}
								// put list number in prix Fab.
								$scope.prixFabtication = function() {
									$scope.prixFab = 0;
									for (var i = 0; i < $scope.listePhaseProduit.length; i++) {
										$log.debug("prix : "
												+ $scope.listePhaseProduit[i].prix);
										$scope.prixFab = $scope.prixFab
												+ parseInt($scope.listePhaseProduit[i].prix);
									}
								}

								// Ajout et Modification Produit
								$scope.modifierOuCreerProduit = function() {
									var index = this.row.rowIndex;
									
									$scope.creation = false;
									$scope.listeCouleurFnc();
									$scope.interColor = [];
									$scope.inter = [];
									// getProduit
									$http
											.get(
													baseUrl
															+ "/produit/getId:"
															+ $scope.myData[index].id)
											.success(
													function(datagetProduit) {
														$scope.modePdf = "actif";
														$scope.listeDetailsPrix = datagetProduit.detailsPrix;
														$scope.listeDocumentProduit = datagetProduit.documentProduits;
														$scope.listePhaseProduit = datagetProduit.phaseProduits;
														$scope.listeCouleurProduit = datagetProduit.couleurProduit;

														$scope.listeTailleProduit = datagetProduit.tailleProduit;
														$scope.creationProduitForm
																.$setPristine();
														$scope.myData[index].detailsPrix = $scope.listeDetailsPrix;
														$scope.myData[index].documentProduits = $scope.listeDocumentProduit;
														$scope.myData[index].phaseProduits = $scope.listePhaseProduit;
														$scope.myData[index].couleurProduit = $scope.listeCouleurProduit;
														$scope.myData[index].tailleProduit = $scope.listeTailleProduit;
														
														
													});

									$scope.produitCourante = $scope.myData[index] ? angular
											.copy($scope.myData[index])
											: {};
									// $scope.listeCouleurFnc();
											
									$scope.currentRef = $scope.produitCourante.reference;
											
									$scope.displayMode = "edit";
									$scope.modePdf = "actif";
								}

								// Sauvegarder Produit
								$scope.sauvegarderAjout = function(Produit) {
									$log.debug("Sauvegarder Modification"
											+ Produit);
									if (angular.isDefined(Produit.id)) {
										$scope.updateProduit(Produit);
									} else {
										$log.debug("**Sauvegarder Ajout"
												+ Produit.SiteId);
										$scope.creerProduit(Produit);
									}
									//$scope.rechercheProduit({});
								}

								// Mise à jour des Produits
								$scope.updateProduit = function(produit) {
									
									produit.refBeforeUpdate = $scope.currentRef;

									produit.detailsPrix = $scope.listeDetailsPrix;
									produit.phaseProduits = $scope.listePhaseProduit;
									produit.documentProduits = $scope.listeDocumentProduit;
									produit.couleurProduit = $scope.listeCouleurProduit;
									produit.tailleProduit = $scope.listeTailleProduit;

									$http
											.post(
													baseUrl
															+ "/produit/modifierProduit",
													produit)
											.success(
													function(result) {
														
														if(result == PRODUIT_REF_EXIST_ERROR){
															$scope.referenceExistError = true;
															$scope.alertMsg = "Référence existante";
															$scope.showNotif();
															
														}else{
															
															

															for (var i = 0; i < $scope.myData.length; i++) {

																if ($scope.myData[i].id == result.id) {

																	$scope.myData[i] = result;
																	break;
																}
															}
															
															//TODO getId
															$scope.annulerAjout();
														}

													});
								}

								// Création Produit
								$scope.listCouleurCreation = [];
								$scope.creerProduit = function(produit) {

									// Affectation ColorVue à colorProduit
									for (var i = 0; i < $scope.listeCouleurProduit.length; i++) {
										$scope.colorIntermediaire = {
											id : '',
											ebCouleurId : '',
											
										};
										$scope.colorIntermediaire.ebCouleurId = $scope.listeCouleurProduit[i].idEbCouleur;
										
										//Affectation de la vue phaseProduit à la liste PhaseOf
										$scope.listCouleurCreation.push($scope.colorIntermediaire);
									}

									produit.detailsPrix = $scope.listeDetailsPrix;
									produit.phaseProduits = $scope.listePhaseProduit;
									produit.documentProduits = $scope.listeDocumentProduit;
									produit.couleurProduit = $scope.listCouleurCreation;
									produit.tailleProduit = $scope.listeTailleProduit;

									$log.debug("== Creation : "+JSON.stringify(produit,null, "  "));

									$http.post(baseUrl + "/produit/creerProduit",
											produit).success(
											function(result) {
												
												if(result == PRODUIT_REF_EXIST_ERROR){
													$scope.referenceExistError = true;
													$scope.alertMsg = "Référence existante";
													$scope.showNotif();
													
												}else{
													
													//TODO getId
													$scope.annulerAjout();
												}
												
											});
								}

								// Suppression produit
								$scope.supprimerProduit = function() {
									$log.debug("DELETE .." 
											+ $scope.myData[$scope.index].id);
									$http(
											{
												method : "DELETE",
												url : baseUrl
														+ "/produit/supprimerProduit:"
														+ $scope.myData[$scope.index].id
											}).success(function() {
										$log.debug("Success Delete");
										$scope.myData.splice($scope.index, 1);
										$scope.$apply();
									});
									$scope.closePopupDelete();
									$scope.rechercheProduit({});
								}

							/*** PDF ***/
                      		//generer rapport de tous les bons de livraison. mode : List
								$scope.produitCourante = {
										"reference": "",
										"designation": "",
										"sousfamille": "",
										"partieInteressee": "",
										"prix_inf": "",
										"prix_sup": "",
										"site": "",
										"etat": ""
										};
								
							 $scope.downloadAllProduits = function(produitCourant) {
							 	
								 $log.debug("-- produitCourant Before " + JSON.stringify(produitCourant, null, "  ") );

								var url;
								//$log.debug("PI  "+produitCourant.partieInteressee );
								
								if(produitCourant.partieInteressee == null){
									produitCourant.partieInteressee = "";
								}
								if(produitCourant.sousfamille == null){
									produitCourant.sousfamille = "";
								}
								
								$log.debug("-- produitCourant After" + JSON.stringify(produitCourant, null, "  ") );
				       			
				       			url = baseUrl + "/reportCommun/listproduit?reference=" + produitCourant.reference
									 					 + "&designation=" + produitCourant.designation
									 					 /*+ "&famille="+produitCourant.famille*/
														 + "&sousfamille="+produitCourant.sousfamille
														 + "&partieInteressee="+produitCourant.partieInteressee
														 + "&etat="+produitCourant.etat
														 + "&site="+produitCourant.site
														 + "&prixInf="+produitCourant.prix_inf
														 + "&prixSup="+produitCourant.prix_sup
														 + "&type=pdf";
									
				                   $log.debug("-- URL--- :" + url );
								 downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });
							 };




								$scope.listePartieInteressee();
								$scope.ListeDevise();

								/** Fin de gestion des Produits */

								/***************************************************
								 * Gestion des DetailsPrix
								 **************************************************/

								$scope.listeDetailsPrix = [];

								// Liste de DetailPrix
								$scope.listeDetailPrix = function() {
									$http
											.get(baseUrl + "/detailPrixProduit/all")
											.success(
													function(datadetailPrixProduit) {
														$scope.listeDetailPrix = datadetailPrixProduit;
													});
								}

								// GetMethode 'Statique'
								$scope.methode = {
									status : 2
								};
								$scope.listMethodes = [ {
									value : 1,
									text : 'M1'
								}, {
									value : 2,
									text : 'M2'
								}, {
									value : 3,
									text : 'M3'
								}, {
									value : 4,
									text : 'M4'
								} ];

								$scope.showMethode = function(id) {
									$scope.methode.status = id;

									var selectedM = $filter('filterListProduit')(
											$scope.listMethodes, {
												value : $scope.methode.status
											});
									return ($scope.methode.status && selectedM.length) ? selectedM[0].text
											: 'Not set';
								};

								// ajout d'un Phase
								$scope.ajoutDetailsPrix = function() {

									$scope.DetailsPrixInserree = {
										methode : '',
										prixVente : '',
										quantiteInferieur : '',
										quantiteSuperieur : ''

									};
									$scope.listeDetailsPrix
											.push($scope.DetailsPrixInserree);
								};

								// Enregistrer DetailsPrix
								$scope.saveDetailsPrix = function(dataDetailsPrix,
										id) {
									$scope.deleteValue = "non";
									// $scope.Representant not updated yet
									angular.extend(dataDetailsPrix, {
										id : id
									});
									$log.debug("Success Save DetailsPrix "
											+ dataDetailsPrix);
								};

								// Supprimer DetailsPrix
								$scope.removeDetailsPrix = function(index) {
									$log.debug("INDEX :" + index);
									$scope.listeDetailsPrix.splice(index, 1);
									$log.debug("Success Delete DetailsPrix ");
								};
								/** Fin de gestion des DetailsPrix */

								/***************************************************
								 * Gestion des Phase
								 **************************************************/
								// $scope.ordre = {id:''};
								// GetDeviseDesignation
								$scope.ordre = {

									status : ''
								};

								$scope.cnt = 0;
								$scope.items = [];

								// ajout d'un Phase
								$scope.ajoutPhase = function() {
									$scope.items = $scope.listePhaseProduit; // $filter('orderBy')($scope.listePhaseProduit,
									// "ordre");
									var indexFin = $scope.items.length;

									$log.debug("** indexFin : " + indexFin);

									if (indexFin <= 0) {
										$log.debug("Undefined :" + indexFin);
										// $scope.cnt
										// =$scope.listePhaseProduit[indexFin].ordre;
										$scope.cnt++;
										$log.debug("CNT :" + $scope.cnt);
									} else {
										// $log.debug(" [0]
										// "+$scope.items[indexFin-3].ordre);
										// $log.debug(" [1]
										// "+$scope.items[indexFin-2].ordre);
										// $log.debug(" [2]
										// "+$scope.items[indexFin-1].ordre);
										// $log.debug(" [3]
										// "+$scope.items[indexFin].ordre);

										$log.debug("Defined :" + indexFin);
										$scope.items = $scope.listePhaseProduit; // $filter('orderBy')($scope.listePhaseProduit,
										// "ordre");
										var index = $scope.items.length;
										$log.debug("INDEXXX " + index);
										$scope.cnt = index;
										$log.debug("CONT " + $scope.cnt);
										$scope.cnt++;
										$log.debug("CONT ++" + $scope.cnt);
									}
									// $log.debug("CONT "+$scope.cnt);
									$scope.PhaseInserree = {

										eb_phase_id : '',
										ordre : $scope.cnt,
										prix : '',
										devise : '',
										variante : ''

									};
									// $scope.ordre.status = index+1;

									$scope.listePhaseProduit
											.push($scope.PhaseInserree);
								};

								// GetDeviseDesignation
								$scope.devise = {

									status : ''
								};

								$scope.showStatusDevise = function(id) {
									$scope.devise.status = id;

									var selected = $filter('filterListProduit')(
											$scope.ListDeviseCache, {
												id : $scope.devise.status
											});

									if ($scope.devise.status && selected.length) {
										return selected[0].designation;
									} else {
										return "Not Set";
									}

									// return ($scope.type.status &&
									// selected.length) ? selected[0].designation:
									// 'Not set';
								};

								// GetPhasedesignation
								$scope.phase = {

									status : ''
								};

								$scope.showStatusPhase = function(id) {
									$scope.phase.status = id;

									var selected = $filter('filterListProduit')(
											$scope.listePhaseProduitCache, {
												id : $scope.phase.status
											});

									if ($scope.phase.status && selected.length) {
										return selected[0].designation;
									} else {
										return "Not Set";
									}

									// return ($scope.type.status &&
									// selected.length) ? selected[0].designation:
									// 'Not set';
								};

								// Enregistrer Phase
								$scope.savePhase = function(dataPhaseProduit, id) {
									$scope.deleteValue = "non";
									// $scope.Representant not updated yet
									angular.extend(dataPhaseProduit, {
										id : id
									});
									// $log.debug("Success Save PhaseProduit "+
									// dataPhaseProduit[0].eb_phase_id);
									$scope.prixFab = $scope.prixFab
											+ parseInt(dataPhaseProduit.prix);
								};

								// Supprimer Phase
								$scope.removePhase = function(index) {
									$scope.cnt--;
									$log.debug("INDEX :" + index);
									$scope.listePhaseProduit.splice(index, 1);
									$scope.prixFabtication();
								};

								/***************************************************
								 * Gestion des Couleurs
								 **************************************************/
								$scope.filterColor = function () {
									
								    return function (item) {
								    	var condition = false;
								    	
								    	for(var k=0; k<$scope.colorIdRemoved.length; k++){
								    		if(item.id != $scope.colorIdRemoved[k]){
								    			condition = true
									    	}else{
									    		condition = false;
									    		break;
									    	}
								    	}
								    	
								        if (condition==true){
								            return true;
								        }
								        return false;
								    };
								};
								
								$scope.ajout =false;
								
								$scope.refreshlisteCouleurProduitCache = function(couleurId){
									       
									for (var i = 0; i < $scope.listeCouleurProduitCache.length; i++){
										$log.debug("couleur : "+$scope.listeCouleurProduitCache[i].id);
				                        if ($scope.listeCouleurProduitCache[i].id == couleurId) {
				                        	$log.debug(couleurId+"  === "+$scope.listeCouleurProduitCache[i].id);
				                        	$scope.listeCouleurProduitCache[i].isUsed = true;
				                           // $scope.listeCouleurProduitCache.splice(i, 1);
				                           // $log.debug("Remaining : "+JSON.stringify($scope.listeCouleurProduitCache ));
				                            break;
				                        }else{
				                        	$log.debug(couleurId+" ! === "+$scope.listeCouleurProduitCache[i].id);
				                        }
				                    }
									
								}

								
								
								// Ajout Couleur
								$scope.ajoutCouleur = function(mode) {
									
									$scope.colorInserreeCreation = {
											idEbCouleur : ''
										};
									
									$scope.colorInserreeModification = {
											ebCouleurId : ''
										};
									
									
									if(mode == '1' ){
										$log.debug("Creation ");
										$scope.ajout = true;
										$scope.listeCouleurProduit.push($scope.colorInserreeCreation);
										
									}else if(mode == '2'){
										$log.debug("Modification ");
										$scope.ajout = true;
										$scope.listeCouleurProduit.push($scope.colorInserreeModification);
									}

								};
								
								$scope.oldPhase = 0;
								$scope.changeColor = function( phaseId,index){ 
									$scope.oldPhase = phaseId;
									$scope.ajout = false;
									
								};

								$scope.newPhase = 0;
								$scope.updateFct = false;
								$scope.refreshColor = function(newPhase){
									$scope.newPhase = newPhase;
									$scope.updateFct = true;
								
									if($scope.updateFct == true){
										if($scope.newPhase == undefined){
											var index = $scope.colorIdRemoved.length;
											$scope.colorIdRemoved.splice(index,1);
										}else{
											if($scope.newPhase == null){
												$log.debug(" NULL ");
											}else{
												if($scope.ajout == false){
													/** Splice **/
													$scope.colorIdRemoved.splice(0,1);
													
													/** Push **/
													$scope.colorIdRemoved.push($scope.newPhase);
													
												}else{
													
													if($scope.colorIdRemoved.indexOf($scope.newPhase) == -1){
															$scope.colorIdRemoved.push($scope.newPhase);
														
														
													}else{
														$log.debug( $scope.newPhase + " Exist " );
														//$scope.ajout = false;
													}
													
												}
													
											}
										}
										
										$scope.updateFct = false;
									}else{
										//$log.debug("On Passe");
									}
									$scope.updateFct = false;
								}
								
								
								
								
								/*// ajout d'un Couleur
								$scope.ajoutCouleur = function() {
									$scope.CouleurProduitInserree = {
										ebCouleurId : ''
									};
									$scope.listeCouleurProduit
											.push($scope.CouleurProduitInserree);

								};*/

								// GetId.designation
								$scope.couleur = {

									status : ''
								};

								$scope.showStatusCouleur = function(id) {

									$scope.couleur.status = id;

									var selected = $filter('filterListProduit')(
											$scope.listeCouleurProduitCache, {
												id : $scope.couleur.status
											});

									if ($scope.couleur.status && selected.length) {
										return selected[0].designation;
									} else {
										return "not Yet";
									}

									// return ($scope.type.status &&
									// selected.length) ? selected[0].designation:
									// 'Not set';
								};
								// Enregistrer Representant
								$scope.saveCouleur = function(dataCouleur, id) {
									$scope.deleteValue = "non";
									if ((angular.isDefined(dataCouleur.id))) {
										$log.debug("Modifier Couleur id:"
												+ dataCouleur.id);
										angular.extend(dataCouleur);

									} else {

										if (dataCouleur.designation == "") {
											$log.debug("Entree Vide !");
										} else {
											$log.debug("Success Creation Couleur");
											angular.extend(dataCouleur);
										}
									}
								};

								// Supprimer Couleur
								$scope.removeCouleur = function(index,couleurIdToAdd) {

									console.log("INDEX :" + index);
									console.log("**OBJET :"
											+ $scope.listeCouleurProduit[index]);
									console.log("DELETE .."
											+ $scope.listeCouleurProduit[index].id);

									
									for(var i = 0, len = $scope.listeCouleurProduitCache.length; i < len; i++) {
									    if ($scope.listeCouleurProduitCache[i].id == couleurIdToAdd) {
									        
									        $scope.listeCouleurProduitCache[i].isUsed = false;
									        break;
									    }
									}

									$scope.listeCouleurProduit.splice(index, 1);

									

								};

								/** Fin de gestion des Couleur */

								/***************************************************
								 * Gestion des Tailles
								 **************************************************/
								/** ***Liste desVariables ***** */

								// GetId.designationFiltre
								$scope.taille = {

									status : ''
								};

								$scope.showStatusTaille = function(id) {

									$scope.taille.status = id;
									var selected = $filter('filterListProduit')(
											$scope.listeTailleProduitCache, {
												id : $scope.taille.status
											});

									if ($scope.taille.status && selected.length) {
										return selected[0].designation;
									} else {
										return "Not Set";
									}

									// return ($scope.type.status &&
									// selected.length) ? selected[0].designation:
									// 'Not set';
								};

								// ajout d'un TailleProduit
								$scope.ajoutTailleProduit = function() {
									$scope.TailleProduitInserree = {
										ebTailleId : ''
									};
									$scope.listeTailleProduit
											.push($scope.TailleProduitInserree);
								};

								// Enregistrer Taille
								$scope.saveTailleProduit = function(
										dataTailleProduit, id) {
									$scope.deleteValue = "non";
									// $log.debug("**SaveTaille :$scope.deleteValue
									// "+$scope.deleteValue);
									$log.debug("dataTailleProduit Id"
											+ dataTailleProduit.id)
									if ((angular.isDefined(dataTailleProduit.id))) {
										$log.debug("Modifier Ajout TailleProduit id:"
														+ dataTailleProduit.id);
										angular.extend(dataTailleProduit);
									} else {

										if (dataTailleProduit.designation == "") {
											// alert("Entree Vide !");
											$log.debug("Entree Vide !");
										} else {
											$log.debug("Success Creation TailleProduit");
											angular.extend(dataTailleProduit);
										}
									}

								};

								// Supprimer TailleProduit
								$scope.removeTailleProduit = function(index) {

									$log.debug("INDEX :" + index);
									$log.debug("**OBJET :"
											+ $scope.listeTailleProduit[index]);
									$log.debug("DELETE .."
											+ $scope.listeTailleProduit[index].id);

									$scope.listeTailleProduit.splice(index, 1);
									$log.debug("Delete Success");
								};

								/** Fin de gestion des TailleProduit */

								/***************************************************
								 * Gestion des DocumentProduit
								 **************************************************/
								$scope.listeDocumentProduit = [];
								$scope.name = "P";
								// GetId.designation
								$scope.type = {

									status : ''
								};
								$scope.showStatus = function(id) {

									$scope.type.status = id;
									var selected = $filter('filterListProduit')(
											$scope.ListTypeDocumentCache, {
												id : $scope.type.status
											});
									if ($scope.type.status && selected.length) {
										return selected[0].designation;
									} else {
										return "Not Set";
									}
								};
								// ajout d'un DocumentProduit
								$scope.ajoutDocumentProduit = function() {

									$scope.DocumentProduitInserree = {
										typeDocumentId : '',
										uidDocument : '',
										path : ''

									};
									$scope.listeDocumentProduit
											.push($scope.DocumentProduitInserree);

								};

								// Enregistrer DocumentProduit
								$scope.saveDocumentProduit = function(
										dataDocumentProduit, id) {
									$scope.deleteValue = "non";
									$log.debug("$scope.deleteValue :"
											+ $scope.deleteValue);
									angular.extend(dataDocumentProduit, {
										id : id
									});
								};

								// Supprimer DocumentProduit
								$scope.removeDocumentProduit = function(index) {
									$scope.listeDocumentProduit.splice(index, 1);
								};
								
								// Download Document
								$scope.download = function(uuid) {
								    downloadService.download(uuid)
								        .then(function(success) {
								            $log.debug('success : ' + success);
								        }, function(error) {
								            $log.debug('error : ' + error);
								        });
								};
								/** Fin de gestion des DocumentProduit */
								
								
							/*******************************************************
							 * gst grid*******
							 * 
							 */
								$scope.typeAlert = 3;
								
								$scope.colDefs = [];
								$scope.$watch(
												'myData',
												function() {
													$scope.colDefs = [
															{
																field : 'reference',
																displayName : 'Réf.Produit',
																width: '10%'
															},
															{
																field : 'designation',
																displayName : 'Désignation',
																width: '25%'
															},
															{
																field : 'familleDesignation',
																displayName : 'Famille',
																width: '10%'
															},
															{
																field : 'sousFamilleDesignation',
																displayName : 'Sous Famille',
																width: '10%'
															},
															{
																field : 'partieIntersseDesignation',
																displayName : 'PI',
																width: '15%'
															},
															{
																field : 'prixUnitaire',
																displayName : 'Prix',
																width: '5%'
															},
															{
																field : 'etat',
																displayName : 'Etat',
																width: '10%'
															},
															{
																field : 'hasFB',
																visible: false
															},
															{
																field : '',
																width: '10%',
																cellTemplate : '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" disabled ng-click="showPopupDelete('
															         + $scope.typeAlert
															         + ')"> <i class="fa fa-fw fa-trash-o"></i></button>'
															         +'<a ng-if="row.entity.hasFB" class="btn btn-success" ng-click="editFicheBesoin()" >FB</a>'
															         +'<a ng-if="!row.entity.hasFB" class="btn btn-danger" ng-click="editFicheBesoin()" >FB</a>'
															         +'</div>'
															}  ];
												});
								$scope.filterOptions = {
									filterText : "",
									useExternalFilter : true
								};

								$scope.totalServerItems = 0;
								
								$scope.setPagingData = function(data, page,
										pageSize) {
									var pagedData = data.slice((page - 1)
											* pageSize, page * pageSize);
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
												var produitCourante = $scope.produitCourante;
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$http
															.post(
																	baseUrl
																			+ "/produit/rechercheProduitMulticritere",
																	produitCourante)
															.success(
																	function(
																			largeLoad) {
																		data = largeLoad.produitValues
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
																	baseUrl
																			+ "/produit/rechercheProduitMulticritere",
																	produitCourante)
															.success(
																	function(
																			largeLoad) {
																		$scope
																				.setPagingData(
																						largeLoad.produitValues,
																						page,
																						pageSize);
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
													if (newVal !== oldVal
															&& newVal.currentPage !== oldVal.currentPage) {
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
									data : 'myData',
									columnDefs : 'colDefs',
									enablePaging : true,
									showFooter : true,
									totalServerItems : 'totalServerItems',
									pagingOptions : $scope.pagingOptions,
									selectedItems : $scope.selectedRows,
									filterOptions : $scope.filterOptions,
								};

								/** Fin */
								
								
								
								
								
								

								/***************************************************
								 * Gestion des Grids de recherche
								 *************************************************
								$scope.typeAlert = 3;
								$scope.filterOptions = {
										filterText : "",
										useExternalFilter : true
									};
									$scope.totalServerItems = 0;

										$scope.setPagingData = function(data, page,
												pageSize) {
											var pagedData = data.slice((page - 1)
													* pageSize, page * pageSize);
											$scope.myData = pagedData;
											$scope.totalServerItems = data.length;
											if (!$scope.$$phase) {
												$scope.$apply();
											}
										};
										$scope.totalServerItems = 0;
										

										
										$scope.getPagedDataAsync = function(pageSize, page,
												searchText) {
											setTimeout(
													function() {
														var data;
														var produitCourant = {};
														if (searchText) {
															
															var ft = searchText
																	.toLowerCase();
															$http
																	.post(
																			baseUrl
																					+ "/produit/rechercheProduitMulticritere",produitCourant)
																	.success(
																			function(
																					largeLoad) {
																				data = largeLoad
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
																	.post(baseUrl
																			+ "/produit/rechercheProduitMulticritere",produitCourant)
															.success(function(largeLoad) {
																$scope.setPagingData(
																		largeLoad, page,
																		pageSize);
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
															if (newVal !== oldVal
																	&& newVal.currentPage !== oldVal.currentPage) {
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
									dataselected : 'myDataselected',
									selectedItems : [],

									data : 'myData',
									columnDefs : [
											{
												field : 'reference',
												displayName : 'Réf.Produit'
											},
											{
												field : 'designation',
												displayName : 'Désignation'
											},
											{
												field : 'familleDesignation',
												displayName : 'Famille'
											},
											{
												field : 'sousFamilleDesignation',
												displayName : 'Sous Famille'
											},
											{
												field : 'partieIntersseDesignation',
												displayName : 'PI'
											},
											{
												field : 'prixUnitaire',
												displayName : 'Prix'
											},
											{
												field : 'siteEntiteDesignation',
												displayName : 'Site'
											},
											{
												field : 'etat',
												displayName : 'Etat'
											},
											{
												field : '',
												 cellTemplate : '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete('
										             + $scope.typeAlert
										             + ')"> <i class="fa fa-fw fa-trash-o"></i></button>'
										             +'<a  class="btn btn-success" ng-click="editFicheBesoin()">FB</a>'
										             +'</div>'
											} ],
									enablePaging : true,
									showFooter : true,
									totalServerItems : 'totalServerItems',
									pagingOptions : $scope.pagingOptions,
									filterOptions : $scope.filterOptions,

								};
								
								$scope.filterOptions = {
										filterText : "",
										useExternalFilter : true
									};
								
								
								
								/** Fin de gestion des Grids de la produit */
								$scope.editFicheBesoin = function() {
							        var index = this.row.rowIndex;
							        window.location.href = "#/ficheBesoin?idProduit="+$scope.myData[index].id;
							       };
							} ])
			.controller(
					'DateIntroCtrl',
					[
							'$scope',
							function($scope) {
								$scope.maxToDay = new Date();
								// date piker defit
	// $scope.today = function() {
	// $scope.articleCourante.dateIntroduction = new Date();
	// };
	// $scope.today();
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
