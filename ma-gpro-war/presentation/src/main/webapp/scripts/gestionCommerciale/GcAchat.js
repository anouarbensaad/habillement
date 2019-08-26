
'use strict'
angular
		.module('gpro.gcAchat', [])
		.controller(
				'AchatCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter, $log, baseUrlGc,baseUrl) {
							$log.info("----------- Achat BC ----------");
														
							//** Variables Recherche
							$scope.listProduitCommande = [];
							$scope.ListClientCommandeAchatCache = [];
							
							//**Variables Modif/Ajout
							$scope.commandeAchatCourante = {};
							$scope.listProduitDrop = [];
							$scope.listeDocumentCommandeAchat = [];
							//**Variable Grid
							$scope.myDataCommandeAchat = [];
							
							$scope.displayMode = "list";
							
							/*********************************************************
							 * Gestion de Cache des Referentiels Gestion Commerciale *
							 *********************************************************/
							$scope.ListEtatCommandeAchatCache = [];
							$scope.ListTypeCommandeAchatCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.ListSousFamilleProduitCache = [];
							
							// Liste des ClientCommandeAchatCache
							$scope.listeClientCommandeAchatCache = function() {
								$http
										.get(baseUrlGc+	"/gestionCommercialCache/listeClientCache")
										.success(
												function(listeClientCache) {
													console
															.log("*LISTClientCommandeAchatCache :"
																	+ listeClientCache);
													$scope.ListClientCommandeAchatCache = listeClientCache;

												});
							}
							
							// Liste des EtatCommandeAchatCache
							$scope.listeEtatCommandeAchatCache = function() {
								$http
										.get(
												baseUrlGc
														+ "/gestionCommercialCache/listeEtatCommandeAchatCache")
										.success(
												function(EtatCommandeAchatCache) {
													console
															.log("*LISTEtatCommandeAchatCache :"
																	+ EtatCommandeAchatCache);
													$scope.ListEtatCommandeAchatCache = EtatCommandeAchatCache;

												});
							}
							// Liste des TypeCommandeAchatCache
							$scope.listeTypeCommandeAchatCache = function() {
								$http
										.get(
												baseUrlGc
														+ "/gestionCommercialCache/listeTypeCommandeAchatCache")
										.success(
												function(TypeCommandeAchatCache) {
													console
															.log("*LISTTypeCommandeAchatCache :"
																	+ TypeCommandeAchatCache);
													$scope.ListTypeCommandeAchatCache = TypeCommandeAchatCache;

												});
							}
							
							// Liste des SiteeCache
							$scope.listeSitesPartieInteresseeCache = function() {
								$http
										.get(baseUrlGc+"/gestionCommercialCache/listeSiteCache")
										.success(
												function(dataSiteCache) {
													console
															.log("*LISTESiteCache :"
																	+ dataSiteCache);
													$scope.listeSitePartieInteresseeCache = dataSiteCache;

												});
							}// Liste TypeDocumentCache
							$scope.listeTypeDocumentsCache = function() {
								$http
										.get(baseUrl+"/gestionnaireCache/listeTypeDocumentCache")
										.success(
												function(dataTypeDocumentCache) {
													console
															.log("*LISTTypeDocumentCache :"
																	+ dataTypeDocumentCache);
													$scope.ListTypeDocumentCache = dataTypeDocumentCache;
												});
							}
							// Liste des SousFamilleCache
							$scope.ListSousFamillesProduitCache = function() {
								$http
										.get(baseUrl+"/gestionnaireCache/listeSousFamilleProduitCache")
										.success(
												function(
														dataSousFamilleProduitCache) {
													console
															.log("*LISTESousFamilleProduitCache :"
																	+ dataSousFamilleProduitCache);
													$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

												});
							}
							$scope.listeClientCommandeAchatCache();
							$scope.listeTypeCommandeAchatCache();
							$scope.listeEtatCommandeAchatCache();
							$scope.listeSitesPartieInteresseeCache();
							$scope.listeTypeDocumentsCache();
							$scope.ListSousFamillesProduitCache();
							/************************************************/
							// Liste des produits
							$scope.listeProduit = function() {
								$http.get(baseUrl+"/produit/all").success(
										function(dataProduit) {
											$scope.listProduitDrop = dataProduit;
										});
							}
							$scope.listeProduit();
							
							/************************************************/
							$scope.cancelAddCommandeAchat = function(rowform, index, id, designation, liste){
						    		  if (angular.isDefined(id)) {
						    			 
						    				  $scope.deleteValue="non";
						    				  rowform.$cancel();
						    				  $log.debug("CANCEL");
						    		  }else{	
						    			  if(designation ==""){
						    				  $scope.deleteValue=="oui"
						    				  liste.splice(index, 1);
									    	  rowform.$cancel();
						    			  }else{
						    				  rowform.$cancel();
						    			  }
						    		}
						    		  $scope.deleteValue="oui";
						    }
							// ** Ajout Bon de Commande de Achat
							$scope.AffectationAchatBC = function(commande) {
								$scope.commandeAchatCourante = {};
								$scope.commandeAchatCourante = commande ? angular
										.copy(commande) : {};

								$scope.displayMode = "edit";
							}
							
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt=0;
								$scope.commandeAchatCourante = {};
								$scope.listProduitCommande = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								// Refresh de la Grid
								$scope.listeBonCommandeAchat();
								$scope.displayMode = "list";
							}
							
							// Liste des Bons de Commande Achat
							$scope.listeBonCommandeAchat = function() {
								$http.get(baseUrlGc + "/commandeAchat/all").success(
										function(dataCommandeAchat) {
											$scope.myDataCommandeAchat = dataCommandeAchat;
										});
							}
							
							// Ajout et Modification CommandeAchat
							$scope.modifierOuCreerCommandeAchat = function() {
								var index = this.row.rowIndex;
								// getCommandeAchat
								$http
										.get(
												baseUrlGc
														+ "/commandeAchat/getId:"+
														 $scope.myDataCommandeAchat[index].id)
										.success(
												function(datagetCommandeAchat) {
													
													$scope.listElementCommande = datagetCommandeAchat.elementCommandes;
													$scope.listeDocumentCommandeAchat = datagetCommandeAchat.documentCommandeAchat;

													$scope.myDataCommandeAchat[index].elementCommandes = $scope.listElementCommande;
													$scope.myDataCommandeAchat[index].documentCommandeAchat = $scope.listeDocumentCommandeAchat;
												});

								$scope.commandeAchatCourante = $scope.myDataCommandeAchat[index] ? angular
										.copy($scope.myDataCommandeAchat[index])
										: {};
								$scope.displayMode = "edit";
							}

							// Rechercher CommandeAchat
							$scope.rechercherCommandeAchat = function(commandeAchatCourante) {
								$http
										.post(
												baseUrlGc
														+ "/commandeAchat/rechercheCommandeAchatMulticritere",
														commandeAchatCourante)
										.success(
												function(resultat) {
													$scope.myDataCommandeAchat = resultat.commandeAchatValues;
													$scope.displayMode = "rechercher";
												});
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
							}
							// Sauvegarder CommandeAchat
							$scope.sauvegarderAjout = function(commande) {

								if (angular.isDefined(commande.id)) {
									$log.debug("Sauvegarder Modification"+commande);
									$scope.updateCommandeAchat(commande);
								} else {
									$log.debug("Sauvegarder Ajout" + commande);
									$scope.creerCommandeAchat(commande);
								}
							}
							
							// Mise à jour des Bons de Commandes de Achat
							$scope.updateCommandeAchat= function(commande) {
								
								commande.elementCommandes = $scope.listElementCommande;
								commande.documentCommandeAchat = $scope.listeDocumentCommandeAchat ;
								
								$http
										.post(
												baseUrlGc
														+ "/commandeAchat/modifierCommandeAchat",
												commande)
										.success(
												function(commandeModifiee) {
													for (var i = 0; i < $scope.listeBonCommandeAchat.length; i++) {

														if ($scope.myDataCommandeAchat[i].id == commandeModifiee.id) {
															$scope.myDataCommandeAchat[i] = commandeModifiee;
															break;
														}
													}
													$scope.displayMode = "list";
												});
								$scope.commandeAchatCourante = {};
								$scope.rechercheSSForm.$setPristine();
								$scope.creationSSForm.$setPristine();
							}
							
							// Création d'un Bon de commande de Achat
							$scope.creerCommandeAchat = function(commande) {
								
								commande.elementCommandes = $scope.listElementCommande;
								commande.documentCommandeAchat = $scope.listeDocumentCommandeAchat ;
								
								$http.post(baseUrlGc + "/commandeAchat/creerCommandeAchat",
										commande).success(
										function(newCommande) {
											$scope.myDataCommandeAchat.push(newCommande);
											$scope.displayMode = "list";
										});
								$scope.commandeAchatCourante = {};
								$scope.rechercheSSForm.$setPristine();
								$scope.creationSSForm.$setPristine();
							}
							$scope.supprimerCommandeAchat = function() {
								var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url :baseUrlGc + "/commandeAchat/supprimerCommandeAchat:"
													+ $scope.myDataCommandeAchat[index].id
										}).success(function() {

								});
								//$scope.closePopupDelete();
								$scope.listeBonCommandeAchat();
							};
							// Suppression d'un bon de commande de Achat

							$scope.listeBonCommandeAchat();
						
							/***************************************************
							 * Gestion des ElementCommandes
							 **************************************************/
													       
							// ajout d'un ElementCommande
							$scope.ajoutElement = function() {
								$scope.elementCmdInserree = {

										quantite:'',
										prixUnitiaire:'',
										prixTotal:'',
										livre:''
									//	articleId:''

								};
								$scope.listElementCommande
										.push($scope.elementCmdInserree);
								
							};

							
							// Enregistrer ElementCommande
							$scope.saveElement = function(
									dataElement, id) {
								$scope.deleteValue = "non";
								angular.extend(dataElement, {
									id : id
								});
							};

							// Supprimer ElementCommande
							$scope.removeElement = function(
									index) {
								$scope.listElementCommande.splice(
										index, 1);
								$log.debug("Success Delete Element ");
							};
							/** Fin de gestion des Produit */
							

							/***************************************************
							 * Gestion des DocumentCommandeAchat
							 **************************************************/
							 $scope.name="BCV";
							 
							 $scope.listeDocumentCommandeAchat = [];
							 
								// GetId.designation
								$scope.doc = {

									status : ''
								};
								$scope.showStatus = function(id) {

									$scope.doc.status = id;
									var selected = $filter('filter')(
											$scope.ListTypeDocumentCache, {
												id : $scope.doc.status
											});
									if ($scope.doc.status && selected.length) {
										return selected[0].designation;
									} else {
										return "Not Set";
									}
								};
								
								// ajout d'un DocumentCommandeAchat
								$scope.ajoutDocumentCommandeAchat = function() {

									$scope.documentCommandeAchatInserree = {
										typeDocumentId:'',
										uidDocument : '',
										path : ''

									};
									$scope.listeDocumentCommandeAchat
											.push($scope.documentCommandeAchatInserree);

								};

								// Enregistrer DocumentCommandeAchat
								$scope.saveDocumentCommandeAchat = function(										
										dataDocumentCommandeAchat, id) {
									$log.debug("**SAVE DOC "+dataDocumentCommandeAchat);
									$scope.deleteValue = "non";
									angular.extend(dataDocumentCommandeAchat, {
										id : id
									});
								};

								// Supprimer DocumentCommandeAchat
								$scope.removeDocumentCommandeAchat = function(index) {
									$scope.listeDocumentCommandeAchat.splice(index, 1);
								};
								/** Fin de gestion des DocumentCommandeAchat */
							/** Fin de gestion des DocumentCommandeAchat */
							/***************************************************
							 * Gestion de la Grid Bon de Commande de Achat 
							 **************************************************/
							$scope.typeAlert = 3;
							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};
							$scope.totalServerItems = 0;
							$scope.pagingOptions = {
								pageSizes : [ 25, 50, 100 ],
								pageSize : 25,
								currentPage : 1
							};
							$scope.setPagingData = function(data, page,
									pageSize) {
								var pagedData = data.slice((page - 1)
										* pageSize, page * pageSize);
								$scope.myDataCommandeAchat = pagedData;
								$scope.totalServerItems = data.length;
								if (!$scope.$$phase) {
									$scope.$apply();
								}
							};
							$scope.getPagedDataAsync = function(pageSize, page,
									searchText) {
								setTimeout(function() {

									if (searchText) {
										var ft = searchText.toLowerCase();

										data = $scope.myDataCommandeAchat
												.filter(function(item) {
													return JSON.stringify(item)
															.toLowerCase()
															.indexOf(ft) != -1;
												});
										$scope.setPagingData(data, page,
												pageSize);
									} else {

										$scope.setPagingData($scope.myDataCommandeAchat,
												page, pageSize);

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

								data : 'myDataCommandeAchat',
								columnDefs : [
										{
											field : '',
											headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
											cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>'
										},
										{
											field : 'reference',
											displayName : 'Réf.BC'
										},
										{
											field : 'partieIntersseId',
											displayName : 'Fournisseur'
										},
										{
											field : 'dateCommande',
											displayName : 'Date Commande',
											 cellFilter: 'date:\'dd-MM-yyyy\''
										},
										{
											field : 'dateLivraisonPrevue',
											displayName : 'Date Livraison',
											cellFilter: 'date:\'dd-MM-yyyy\''
										},
										{
											field : 'coutTotal',
											displayName : 'Cout Total'
										},
										{
											field : 'etat',
											displayName : 'Etat'
										},
										{
											field : 'siteId',
											displayName : 'Site'
										},
										{
											field : '',
											cellTemplate :  '<div class="buttons">'
												+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerCommandeAchat()"><i class="fa fa-fw fa-pencil"></i></button>'
												+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="supprimerCommandeAchat()">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
										} ],
								enablePaging : true,
								showFooter : true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								filterOptions : $scope.filterOptions,

							};
							/** Fin de gestion des Grids Achat BC */
						} ]);