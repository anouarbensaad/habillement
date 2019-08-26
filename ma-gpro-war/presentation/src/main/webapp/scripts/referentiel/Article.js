'use strict'
angular
		.module('gpro.articles', [ "ngResource" ])
		.controller(
				'ArticleController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'baseUrl',
						'ARTICLE_REF_EXIST_ERROR',
						'ARTICLE_CODE_FOUR_EXIST_ERROR',
						function($scope, $filter, $http ,$log, downloadService,
								baseUrl,ARTICLE_REF_EXIST_ERROR, 
								ARTICLE_CODE_FOUR_EXIST_ERROR) {
							// Déclaration des variables globales utilisées
							/** ***Liste desVariables **** */
							var data;
							$scope.articleBD = [];
							$scope.displayMode = "list";
							$scope.articleCourante = {};
							$scope.listeDocuments = [];
							$scope.listeSeuilApprovisionnement = [];
							$scope.listeDocumentArticle = [];
							$scope.listeGetSeuil = [];
							$scope.mySelections = [];
							$scope.resultatRecherche = $scope.listeArticle;
							$scope.ListType = [];
							$scope.ListGrosseur = [];
							$scope.ListSites = [];
							$scope.ListTypeArticleCache = [];
							$scope.ListFamilleArticleCache = [];
							$scope.ListSousFamilleArticleCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListUniteArticleCache = [];
							$scope.ListGrosseurArticleCache = [];
							$scope.ListMetrageArticleCache = [];
							$scope.ListMatiereArticleCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.articleCourante = {};
							$scope.referenceExistError = false;
							$scope.codeFournisseurExistError = false;
							$scope.alertMsg = '';
							
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/
							// Liste des TypesCache
							$scope.listeTypesArticleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeTypeArticleCache")
										.success(
												function(dataType) {
													$log.debug("*LISTTypeCache :"+ dataType.length);
													$scope.ListTypeArticleCache = dataType;

												});
							}

							// Liste des FamilleCache
							$scope.ListFamillesArticleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeFamilleArticleCache")
										.success(
												function(dataFamilleCache) {
													$log.debug("*LISTEFamilleCache :"+ dataFamilleCache.length);
													$scope.ListFamilleArticleCache = dataFamilleCache;

												});
							}

							// Liste des SousFamilleCache
							$scope.ListSousFamillesArticleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSousFamilleArticleCache")
										.success(
												function(dataSousFamilleCache) {
													$log.debug("*LISTESousFamilleCache :"+ dataSousFamilleCache.length);
													$scope.ListSousFamilleArticleCache = dataSousFamilleCache;

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
													$log.debug("*LISTESiteCache :"+ dataSiteCache.length);
													$scope.listeSitePartieInteresseeCache = dataSiteCache;

												});
							}

							// Liste des UniteCache
							$scope.listeUnitesArticleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeUniteArticleCache")
										.success(
												function(dataUniteCache) {
													$log.debug("*LISTEUniteCache :"+ dataUniteCache.length);
													$scope.ListUniteArticleCache = dataUniteCache;

												});
							}

							// Liste MatiereCache
							$scope.listeMatiereArticlesCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeMatiereArticleCache")
										.success(
												function(dataMatiereCache) {
													$log.debug("*LISTEMatiereCache :"+ dataMatiereCache.length);
													$scope.ListMatiereArticleCache = dataMatiereCache;

												});
							}

							// Liste MetrageCache
							$scope.listeMetragesArticleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeMetrageArticleCache")
										.success(
												function(dataMetrageCache) {
													$log.debug("*LISTEMetrageCache :"+ dataMetrageCache.length);
													$scope.ListMetrageArticleCache = dataMetrageCache;

												});
							}

							// Liste GrosseurCache
							$scope.listeGrosseursArticleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeGrosseurArticleCache")
										.success(
												function(dataGrosseurCache) {
													$log.debug("*LISTEGrosseurCache :"+ dataGrosseurCache.length);
													$scope.ListGrosseurArticleCache = dataGrosseurCache;

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
													$log.debug("*LISTTypeDocumentCache :"+ dataTypeDocumentCache.length);
													$scope.ListTypeDocumentCache = $filter('filter')(dataTypeDocumentCache, {module:"ARTICLE"});

												});
							}

							$scope.listeTypesArticleCache();
							$scope.ListFamillesArticleCache();
							$scope.ListSousFamillesArticleCache();
							$scope.listeSitesPartieInteresseeCache();
							$scope.listeUnitesArticleCache();
							$scope.listeMatiereArticlesCache();
							$scope.listeMetragesArticleCache();
							$scope.listeGrosseursArticleCache();
							$scope.listeTypeDocumentsCache();

							
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
							
							/***************************************************
							 * Gestion des Articles *
							 **************************************************/
							
							//$scope.articleCourante.dateIntroduction = '20/10/2016';
							$scope.deleteValue = "oui";
							// Annuler Ajout
							$scope.cancelAddArticle = function(rowform, index,
									id, designation, liste) {
								
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

							// declaration variable
							$scope.displayAlert = "sleep";
							
							// Rechercher Article
							$scope.rechercherArticle = function(articleCourante) {
								$log.debug("recherche Article ..");
								$http
										.post(
												baseUrl
														+ "/article/rechercheArticleMulticritere",
												articleCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.articleValues;
													//data, page,pageSize
													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													$scope.rechercheArticleForm.$setPristine();
													$scope.displayMode = "rechercher";
													$scope.displayAlert = "sleep";
												});
							}
							
							$scope.rechercherArticle({});
							
							// ** Ajout Article
							$scope.AffectationArticle = function(article) {
								$scope.articleCourante = article ? angular
										.copy(article) : {};
								$scope.articleCourante = {};
								$scope.creationArticleForm.$setPristine();

								$scope.displayMode = "edit";
							}
							
							// Ajout et Modification Article
							$scope.modifierOuCreerArticle = function() {
								var index = this.row.rowIndex;

								// getArticle
								$http
										.get(
												baseUrl
														+ "/article/getId:"
														+ $scope.myData[index].id)
										.success(
												function(datagetArticle) {

													$scope.listeSeuilApprovisionnement = datagetArticle.seuilEntities;
													$scope.listeDocumentArticle = datagetArticle.documentEntites;

													$scope.myData[index].seuilEntities = $scope.listeSeuilApprovisionnement;
													$scope.myData[index].documentEntites = $scope.listeDocumentArticle;
													
												});

								$scope.articleCourante = $scope.myData[index] ? angular
										.copy($scope.myData[index])
										: {};
										
										if($scope.articleCourante.dateIntroduction != null){
											$scope.creationArticleForm.dateIntroduction.$valid = true;
										}
										

										$scope.currentRef = $scope.articleCourante.ref;
										$scope.currentCodeFournisseur = $scope.articleCourante.codeFournisseur;


								$scope.displayMode = "edit";
							}

							// Sauvegarder Article
							$scope.sauvegarderAjout = function(article) {

								if (angular.isDefined(article.id)) {
									$log.debug("Sauvegarder Modification"
											+ article.reference);
									$scope.updateArticle(article);
								} else {
									$log.debug("Sauvegarder Ajout");
									$scope.creerArticle(article);
								}
							//	$scope.rechercherArticle({});
							}

							$scope.successUpdateCode = function (result){
								
								//TODO Code à revoir
								for (var i = 0; i < $scope.myData.length; i++) {

									if ($scope.myData[i].id == result) {
										$scope.myData[i] = result;
										break;
									}
								}

								//TODO getId
								$scope.annulerAjout();
							}
							
							// Mise à jour des Articles
							$scope.updateArticle = function(article) {
								
								article.refBeforeUpdate = $scope.currentRef;
								article.codeFournisseurBeforeUpdate = $scope.currentCodeFournisseur;
								
								article.seuilEntities = $scope.listeSeuilApprovisionnement;
								article.documentEntites = $scope.listeDocumentArticle;

								$http
										.post(
												baseUrl
														+ "/article/modifierArticle",
												article)
										.success(
												function(result) {
														if(result == ARTICLE_REF_EXIST_ERROR){
															$scope.referenceExistError = true;
															$scope.alertMsg = "Référence existante";
															$scope.showNotif();
														}
														else if(result == ARTICLE_CODE_FOUR_EXIST_ERROR){
															$scope.codeFournisseurExistError = true;
															$scope.alertMsg = "Code fournisseur existant";
															$scope.showNotif();
														}
														
														else{
														$scope.successUpdateCode(result);
													}
													
												});
							}
							
					
							
							// Création Article
							$scope.creerArticle = function(article) {
								article.seuilEntities = $scope.listeSeuilApprovisionnement;
								article.documentEntites = $scope.listeDocumentArticle;
								$http
										.post(
												baseUrl
														+ "/article/creerArticle",
												article)
										.success(
												function(result) {
													
													if(result == ARTICLE_REF_EXIST_ERROR){
														$scope.referenceExistError = true;
														$scope.alertMsg = "Référence existante";
														$scope.showNotif();
														
													}else  if(result == ARTICLE_CODE_FOUR_EXIST_ERROR){
														$scope.codeFournisseurExistError = true;
														$scope.alertMsg = "Code fournisseur existant";
														$scope.showNotif();
													}
													else{
														$scope.closeNotif();
														$scope.initReferenceErrorValue();
														$scope.initCodeFournisseurErrorValue();
														
														$log.debug("Success Creation : id= "+ result);
														//TODO getId
														$scope.annulerAjout();
													}
																
												});
								
							}
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								
								/** CLose notif and init validation variables**/
								
								$scope.closeNotif();
								$scope.initReferenceErrorValue();
								$scope.initCodeFournisseurErrorValue();
								
								$scope.articleCourante = { "ref": "",
														   "designation": "",
														   "typeEntite": "",
														   "familleEntite": "",
														   "sousFamilleEntite": "",
														   "prix_inf": "",
														   "prix_sup": "",
														   "site": "" };
								$scope.creationArticleForm.$setPristine();
								$scope.rechercheArticleForm.$setPristine();
								$scope.listeSeuilApprovisionnement = [];
								$scope.listeDocumentArticle = []
								$scope.rechercherArticle({});
								$scope.displayMode = "list";
							}
							// Suppression Article
							$scope.supprimerArticle = function() {
								$log.debug("INDEX" + $scope.index);
								$log.debug("**OBJET :"
										+ $scope.myData[$scope.index]);
								$log.debug("DELETE .."
										+ $scope.myData[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/article/supprimerArticle:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
											$scope.myData.splice($scope.index, 1);
								});
								$scope.closePopupDelete();
								$scope.rechercherArticle({});
							};
							/** Fin de gestion des Articles */

							/*** PDF ***/
                      		//generer rapport de tous les bons de livraison. mode : List

							 $scope.downloadAllArticles = function(articleCourant) {
							 	
								var url;
								
								$log.debug("-- articleCourant " + JSON.stringify(articleCourant, null, "  ") );
				       			
				       			if(articleCourant.sousFamilleEntite	== null){
									articleCourant.sousFamilleEntite = "";
								}
								if(articleCourant.typeEntite	== null){
									articleCourant.typeEntite = "";
								}
								if(articleCourant.familleEntite	== null){
									articleCourant.familleEntite = "";
								}
								if(articleCourant.sousFamilleEntite	== null){
									articleCourant.sousFamilleEntite = "";
								}
								url = baseUrl + "/reportCommun/listArticle?reference=" + articleCourant.ref
									 					 + "&designation=" + articleCourant.designation
														 + "&sousfamille="+articleCourant.sousFamilleEntite
														 + "&famille="+articleCourant.familleEntite
														 + "&typeArticle="+articleCourant.typeEntite
														 + "&site="+articleCourant.site
														 + "&prixInf="+articleCourant.prix_inf
														 + "&prixSup="+articleCourant.prix_sup
														 + "&type=pdf";
									
				                   $log.debug("-- URL--- :" + url );
								 downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });
							 };
							/***************************************************
							 * Gestion des SeuilApprovisionnement
							 **************************************************/
							$scope.showBtnCalender = true;
							// show bottons Calender
							$scope.showBC = function() {
								$scope.showBtnCalender = true;
							}
							// ajout d'un SeuilApprovisionnement
							$scope.ajoutSeuilApprovisionnement = function() {

								$scope.SeuilApprovisionnementInserree = {

									dateDebut : '',
									dateFin : '',
									maxSeuil : '',
									minSeuil : ''

								};
								$scope.listeSeuilApprovisionnement
										.push($scope.SeuilApprovisionnementInserree);
							};
							$scope.minSeuil = [];
							// Enregistrer SeuilApprovisionnement
							$scope.saveSeuilApprovisionnement = function(
									dataSeuilApprovisionnement, id, index) {
								// $scope.Representant not updated yet
								if (parseInt($scope.listeSeuilApprovisionnement[index].minSeuil) <= parseInt($scope.listeSeuilApprovisionnement[index].maxSeuil)) {
									angular.extend(dataSeuilApprovisionnement,
											{
												id : id
											});
									$scope.showBtnCalender = false;
									$scope.deleteValue = "non";
									$("#min" + index).css('border',
											'solid 1px #ccc');
									$("#max" + index).css('border',
											'solid 1px #ccc');
								} else {
									$scope.listeSeuilApprovisionnement[index].minSeuil = "";
									$scope.listeSeuilApprovisionnement[index].maxSeuil = "";
									// $scope.rowform.$visible = true;
									// $("#min"+index).removeClass('ng-valid');
									// $("#min"+index).removeClass('ng-dirty');
									// $("#min"+index).addClass('ng-invalid');
									$("#min" + index).css('border',
											'solid 1px #ff0000');
									$("#max" + index).css('border',
											'solid 1px #ff0000');
									$scope.rowform.$visible = true;
								}
							};
							// Supprimer SeuilApprovisionnement
							$scope.removeSeuilApprovisionnement = function(
									index) {
								$log.debug("INDEX :" + index);
								$scope.listeSeuilApprovisionnement.splice(
										index, 1);
								$log.debug("Success Delete Seuil ");
							};
							/** Fin de gestion des SeuilApprovisionnement */

							/***************************************************
							 * Gestion des DocumentArticle
							 **************************************************/
							$scope.name = "A";
							// GetId.designation
							$scope.type = {

								status : ''
							};
							$scope.showStatus = function(id) {

								$scope.type.status = id;
								var selected = $filter('filterListArticle')(
										$scope.ListTypeDocumentCache, {
											id : $scope.type.status
										});
								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							};
							// ajout d'un DocumentArticle
							$scope.ajoutDocumentArticle = function() {

								$scope.DocumentArticleInserree = {

									typeDocumentEntite : '',
									path : '',
									uidDocument : ''

								};
								$scope.listeDocumentArticle
										.push($scope.DocumentArticleInserree);
							};

							// Enregistrer DocumentArticle
							$scope.saveDocumentArticle = function(
									dataDocumentArticle, id) {
								$scope.deleteValue = "non";
								$log.debug("deleteValue = "
										+ $scope.deleteValue);
								// $scope.Representant not updated yet
								angular.extend(dataDocumentArticle, {
									id : id
								});
								$log.debug("Success Save Document "
										+ dataDocumentArticle.id);
							};

							// Supprimer DocumentArticle
							$scope.removeDocumentArticle = function(index) {
								$scope.listeDocumentArticle.splice(index, 1);
							};

							
							/** Fin de gestion des DocumentArticle */

							/**************************************
							*			GestionGrid
							***************************************/
							$scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 ],
									pageSize : 13,
									currentPage : 1
								};
							$scope.colDefs = [];
							$scope
									.$watch(
											'myData',
											function() {
												$scope.colDefs = [
														{
															field : 'ref',
															displayName : 'Réf.Article ',
															width:'21%'
														},
														{
															field : 'designation',
															displayName : 'Article',
															width:'30%'
														},
														{
															field : 'typeArticleDesignation',
															displayName : 'Type',
															width:'5%'
														},
														{
															field : 'familleArticleDesignation',
															displayName : 'Famille',
															width:'12%'
														},
														{
															field : 'sousFamilleArtEntiteDesignation',
															displayName : 'Sous Famille',
															width:'12%'
														},
														{
															field : 'pu',
															displayName : 'PU',
															width:'5%'
														},
														{
															field : 'pmp',
															displayName : 'P.M.P',
															width:'5%'
														},
														{
															field : 'siteEntiteDesignation',
															displayName : 'Site',
															width:'5%'
														},
														{
															field : '',
															width:'5%',
															cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerArticle()"><i class="fa fa-fw fa-pencil"></i></button>'
																	+ '<button data-nodrag class="btn btn-default btn-xs" disabled ng-click="showPopupDelete(0)">	<i class="fa fa-fw fa-trash-o" ></i></button></div>'
														} ];
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
											var articleCourante = $scope.articleCourante;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																baseUrl
																		+ "/article/rechercheArticleMulticritere",
																articleCourante)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.articleValues
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
																		+ "/article/rechercheArticleMulticritere",
																articleCourante)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.articleValues,
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
							
						/************* Init validation error values ************/
							
							$scope.initReferenceErrorValue = function(){
								$scope.referenceExistError = false;
							}
							
							
							$scope.initCodeFournisseurErrorValue = function(){
								$scope.codeFournisseurExistError = false;
							}
							
							

							/** ***********Fin****************** */

							/***************************************************
							 * Gestion des Grids de recherche
							 **************************************************
							$scope.typeAlert = 0;
							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};
							$scope.totalServerItems = 0;

							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 20 ],
								pageSize : 14,
								currentPage : 1
							};
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
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.get(
																baseUrl
																		+ "/article/all")
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
														.get(
																baseUrl
																		+ "/article/all")
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad,
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
								selectedItems : $scope.mySelections,
								columnDefs : [
										{
											field : 'id',
											displayName : 'Id ',
											visible : false
										},
										{
											field : 'ref',
											displayName : 'Réf.Article '
										},
										{
											field : 'designation',
											displayName : 'Article'
										},
										{
											field : 'typeArticleDesignation',
											displayName : 'Type'
										},
										{
											field : 'familleArticleDesignation',
											displayName : 'Famille'
										},
										{
											field : 'sousFamilleArtEntiteDesignation',
											displayName : 'Sous Famille'
										},
										{
											field : 'pu',
											displayName : 'Prix Unitaire'
										},
										{
											field : 'pmp',
											displayName : 'P.M.P'
										},
										{
											field : 'siteEntiteDesignation',
											displayName : 'Site'
										},
										{
											field : '',
											cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
													+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerArticle()"><i class="fa fa-fw fa-pencil"></i></button>'
													+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete('
													+ $scope.typeAlert
													+ ')">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
										} ],
								enablePaging : true,
								showFooter : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								filterOptions : $scope.filterOptions
							};

							 Fin de gestion des Grids de la partie Interesse */
						} ])
		.controller(
				'DatepickerDController2',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
							// date piker defit
							// $scope.today = function() {
							 
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
								startingDay : 1,
								initDate : new Date()
							};
							$scope.initDate = new Date();
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];
						} ])
		.controller(
				'DatepickerControllerMin',
				[
						'$scope',
						function($scope) {
							// date piker defit
							$scope.today = function() {
								$scope.seuilApprovisionnement.dateDebut = new Date();
							};
							$scope.today();
							$scope.clear = function() {
								$scope.seuilApprovisionnement.dateDebut = null;
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
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
		.filter('filterListArticle', function() {
		  return function(liste, id) {
			 var listeFiltre = [];
			// $log.debug("IdSousFamille=  "+id.id);
			// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
			 angular.forEach(liste, function(elementListe, key){
				
				if(elementListe.id == id.id){
					//$log.debug(sousFamille.id +" == "+ id.id);
					listeFiltre.push(elementListe);
				}
					
			 });
			// $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
			 return listeFiltre;
		  };
		})
		.controller(
				'DatepickerControllerMax',
				[
						'$scope',
						function($scope) {
							// date piker defit
							$scope.today = function() {
								$scope.seuilApprovisionnement.dateFin = new Date();
							};
							$scope.today();
							$scope.clear = function() {
								$scope.seuilApprovisionnement.dateFin = null;
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
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
