'use strict'
/**
 * Gestion Stock
 */
angular
		.module('gpro.stockMouvementOF', [])
		.controller(
				'StockMvtOFReservationController',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'baseUrlGs',
						'baseUrl',
						'baseUrlGpao',
						'$rootScope',
						function($scope, $http, $filter, $log, downloadService,
								baseUrlGs, baseUrl, baseUrlGpao, $rootScope) {
							var data;
							//btn Valider disabled
							$scope.modeValider = false;
							//btn Generer disabled
							$scope.modePdf = "notActif";
							//objet de recherche de details Client, Produit par idOF
							$scope.resultatRechercheDetail = {};

							$scope.fcon = true;
							$scope.myData = [];
							$scope.stockBD = [];
							$scope.listArticlesFACE = [];
							$scope.displayMode = "list";
							$scope.stockMvtOFRvtCourant = {
								typeBonMouvement : "RESERVATION"
							};
							$scope.resultatRechercheDetail = {};
							$scope.listeBonMouvements = [];
							$scope.listeEntiteStock = {};
							$scope.listemouvementStock = [];
							// declaration variable liste cache
							$scope.listeMagasinCache = [];
							$scope.ListEmplacementCache = [];
							$scope.listeClientCache = [];
							$scope.listeFournisseurCache = [];
							$scope.ListeRaisonCache = [];
							$scope.listeSousTraitantCache = [];
							$scope.listeFamilleCache = [];
							$scope.listeSousFamilleCache = [];

							// declaration variable liste des Mouvements
							$scope.listArticlesFournituresE = [];
							$scope.listArticlesTissusE = [];
							$scope.listArticlesFACE = [];
							$scope.idSelectionnee = [];
							
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
							 * Slides Articles RESERVATION *
							 **************************************************/
							$scope.animateArticleFourniture = function() {
								$("#articles").click(
										function() {
											$scope.openOrClose(
													'panel-articles',
													'#articles', 'hidePlus');
										});
								$("#tissuBtn").click(
										function() {
											$scope.openOrClose('tissu',
													'#tissuBtn',
													'hidePlusTissu');
										});
								$("#filBtn").click(
										function() {
											$scope.openOrClose('fil',
													'#filBtn', 'hidePlusFil');
										});
								$("#fournituresBtn").click(
										function() {
											$scope.openOrClose('fournitures',
													'#fournituresBtn',
													'hidePlusFourniture');
										});
							}

							$scope.openOrClose = function(id_panel, id_div,
									classe) {
								$("div[id=" + id_panel + "]").slideToggle(
										"slow");
								$(id_div).toggleClass(classe);
								if ($(id_div).hasClass(classe)) {
									$(id_div).text('+');
								} else {
									$(id_div).text('-');
								}
							}

							$scope.animateArticleFourniture();
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/
							// Liste de Famille cache
							$scope.listeFamilleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeFamilleArticleCache")
										.success(function(data) {
											$scope.listeFamilleCache = data;

										});
							}
							// Liste de Sous Famille cache
							$scope.listeSousFamilleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSousFamilleArticleCache")
										.success(
												function(data) {
													$scope.listeSousFamilleCache = data;

												});
							}
							// Liste Fournisseur
							$scope.listeFournisseurCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeFournisseurCache")
										.success(
												function(dataC) {
													$scope.listeFournisseurCache = dataC;

												});
							}
							// Liste de Client cache
							$scope.listeClientCache = function() {
								$http.get(baseUrlGs

								+ "/gestionnaireCache/listeClientCache")
										.success(function(dataC) {
											$scope.listeClientCache = dataC;

										});
							}
							// Liste de Sous traitant Cache
							$scope.listeSousTraitantCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeSousTraitantCache")
										.success(
												function(dataS) {
													$scope.listeSousTraitantCache = dataS;

												});
							}

							//TODO Cache: Liste OF
							$scope.listeOF = function() {
								$http
										.get(
												baseUrlGpao
														+ "/ordreFabrication/all")
										.success(
												function(data) {
													$log.debug("liste OF : "
															+ data.length);
													$scope.listeOF = data;

												});
							}

							// Liste de magasinCache
							$scope.listeMagasinCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeMagasinCache")
										.success(
												function(dataMagasin) {
													$scope.listeMagasinCache = dataMagasin;

												});
							}

							// Liste de magasinCache
							$scope.listeMagasinCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeMagasinCache")
										.success(
												function(dataMagasin) {
													$scope.listeMagasinCache = dataMagasin;

												});
							}

							// Liste de raisonCache
							$scope.ListeRaisonCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeRaisonCache")
										.success(
												function(dataSousFamilleCache) {
													$scope.ListeRaisonCache = dataSousFamilleCache;

												});
							}

							$scope.listeFamilleCache();
							$scope.listeSousFamilleCache();
							$scope.listeFournisseurCache();
							$scope.listeClientCache();
							$scope.listeSousTraitantCache();
							$scope.listeOF();
							$scope.listeMagasinCache();
							$scope.ListeRaisonCache();

							/***************************************************
							 * Gestion des Mouvements
							 **************************************************/
							// GetFamilleDesignation
							$scope.famille = {

								status : ''
							};

							// show Famille Mouvements
							$scope.showStatusFamille = function(id) {
								$scope.famille.status = id;

								var selected = $filter('customfilter')(
										$scope.listeFamilleCache, {
											id : $scope.famille.status
										});

								if ($scope.famille.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							}
							// show Sous Famille Mouvements
							$scope.showStatusSousFamille = function(id) {
								$scope.famille.status = id;

								var selected = $filter('customfilter')(
										$scope.listeSousFamilleCache, {
											id : $scope.famille.status
										});

								if ($scope.famille.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}
							}
							
							$scope.listArticlesFournitureSupprimes = {
									typeArticle : "",
									listeElementsSupprimes : []
								}


							// Supprimer Mouvements
							$scope.removeMvtFourniture = function(index) {
								
								$log.debug("element supprimes"	+ JSON.stringify($scope.listArticlesFournituresE[index],	null, " "));

								$scope.listArticlesFournitureSupprimes.typeArticle = "1";

								$scope.listArticlesFournitureSupprimes.listeElementsSupprimes
								.push({
									"entiteStockId" : $scope.listArticlesFournituresE[index].entiteStock,
									"qteReservee" : $scope.listArticlesFournituresE[index].besoinOF
								});

								$log
								.debug("listArticlesFournitureSupprimes"
										+ JSON
												.stringify(
														$scope.listArticlesFournitureSupprimes,
														null, " "));
								$scope.listArticlesFournituresE
										.splice(index, 1);
								$scope.idSelectionnee.splice(index, 1);
							}
							
							
							$scope.listArticlesTissuSupprimes = {
									typeArticle : "",
									listeElementsSupprimes : []
								}
								
							$scope.removeMvtTissu = function(index) {
								
								$scope.listArticlesTissuSupprimes.typeArticle = "2";
								
								$scope.listArticlesTissuSupprimes.listeElementsSupprimes
										.push({
											"entiteStockId" : $scope.listArticlesTissusE[index].entiteStock,
											"qteReservee" : $scope.listArticlesTissusE[index].besoinOF,
											"nbRouleauxReserve": $scope.listArticlesTissusE[index].nbrRouleau
										});
								$scope.listArticlesTissusE.splice(index, 1);
							};
							
							$scope.listArticlesFACESupprimes = {
									typeArticle : "",
									listeElementsSupprimes : []
								}

							$scope.removeMvtFAC = function(index) {
								
								$scope.listArticlesFACESupprimes.typeArticle = "3";
//								
//								$scope.listArticlesFACESupprimes.listeElementsSupprimes
//										.push({
//											"entiteStockId" : $scope.listArticlesFACE[index].entiteStock,
//											"conesReservees" : $scope.listArticlesFACE[index].conesReel,
//											"finconesReservess": $scope.listArticlesFACE[index].finconesReel,
//											"poidsReserve": $scope.listArticlesFACE[index].poids
//										});
								
								
								$scope.listArticlesFACE.splice(index, 1);
							};

							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ],
								pageSize : 13,
								currentPage : 1
							};

							// Rechercher par Type RESERVATION
							$scope.rechercherStockEntrerParType = function(type) {
								$http
										.get(
												baseUrlGs
														+ "/bonMouvementStock/getBonMouvementParType:"
														+ type)
										.success(function(resultat) {
											$scope.myData = resultat;

											$scope.displayMode = "rechercher";
										});
								//$scope.creationMvtOFForm.$setPristine();
							}

							$scope.rechercherStockEntrerParType("RESERVATION");

							// Rechercher Stock RESERVATION
							$scope.rechercherStockEntrer = function(
									mouvementCourante) {

								mouvementCourante.typeBonMouvement = "RESERVATION";

								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/rechercheParCritere",
												mouvementCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.bonMouvementStock;
													//data, page,pageSize
													$scope
															.setPagingData(
																	$scope.myData,
																	$scope.pagingOptions.currentPage,
																	$scope.pagingOptions.pageSize);
													$scope.displayMode = "rechercher";
												});

							}

							$scope.setBR = function() {

								var referenceOfR = [];
								referenceOfR = $filter('filter')
										(
												$scope.listeOF,
												{
													id : $scope.stockMvtOFRvtCourant.ofId
												});

								var ofReference = "";

								if (referenceOfR.length != 0) {
									ofReference = referenceOfR[0].numero + "/";
								}

								var magasinR = [];
								magasinR = $filter('filter')
										(
												$scope.listeMagasinCache,
												{
													id : $scope.stockMvtOFRvtCourant.magasinId
												});

								var magasin = "";

								if (magasinR.length != 0) {
									magasin = magasinR[0].designation;
								}

								$scope.stockMvtOFRvtCourant.numero = "R/"
										+ ofReference + magasin;

							}

							$scope.remplirDetails = function(idOf) {

								$http
										.get(
												baseUrlGpao
														+ "/ordreFabrication/getId:"
														+ idOf)
										.success(
												function(data) {
													//affecter le resultat au ng-model
													//refeerence, designation Produit
													$scope.resultatRechercheDetail.produitReference = data.produitReference;
													$scope.resultatRechercheDetail.produitDesignation = data.produitDesignation;
													//abreviation Client
													$scope.resultatRechercheDetail.partieInterresAbreviation = data.partieInterresAbreviation;
												});

							}

							//Liste des Articles récuperée de l'objet Vue
							$scope.remplirListMouvement = function(idOf,
									idMagain) {

								$scope.closeNotif();
								
								$scope.setBR();
								//show btn Valider
								$scope.modeValider = true;
								//viderSubLists
								viderSubLists();
								
								$http
								.get(
										baseUrlGs
												+ "/bonMouvementStock/ofIsReserved?ofId="
												+ idOf)
								.success(
										function(isReserved) {

											if(isReserved === 'true'){
												$scope.showNotif();
											}else{
												
												$http
												.get(
														baseUrlGpao
																+ "/reservationProduit/getByOrdreFabricationId?magasinId="
																+ idMagain
																+ "&ordreFabricationId="
																+ idOf)
												.success(
														function(resultat) {

															//getResult
															classifierListMouvement(resultat);
															//showResults in panel
															$scope.openOrClose(
																	'panel-articles',
																	'#articles',
																	'hidePlus');
															$scope
																	.openOrClose(
																			'fourniture',
																			'#fournitureBtn',
																			'hidePlusFourniture');
															$scope.openOrClose('tissu',
																	'#tissuBtn',
																	'hidePlusTissu');
															$scope.openOrClose('fil',
																	'#filBtn',
																	'hidePlusFil');

														});
											}
										});
										
										
								
							}

							//vider les listes
							var viderSubLists = function() {
								//init subListMouvement
								$scope.listArticlesFournituresE = [];
								$scope.listArticlesTissusE = [];
								$scope.listArticlesFACE = [];
							}

							//Classification des ListMouvement selon le Type
							var classifierListMouvement = function(
									listElementMouvement) {
								// subListes de ListMouvement: 1:"fourniture", 2:"tissu", 3: "fil à coudre" 
								angular
										.forEach(
												listElementMouvement,
												function(elementMouvement, key) {
													//TODO A modifier 
													if(elementMouvement.besoinOF <= elementMouvement.quantiteLibre){
														elementMouvement.aReserver = elementMouvement.besoinOF;
													}else{
														elementMouvement.aReserver = elementMouvement.quantiteLibre;
													}
													
													var typeArticle = elementMouvement.typeArticle;
													if (typeArticle == 1) {
														$scope.listArticlesFournituresE
																.push(elementMouvement);
													} else if (typeArticle == 2) {
														$scope.listArticlesTissusE
																.push(elementMouvement);
													} else if (typeArticle == 3) {
														$scope.listArticlesFACE
																.push(elementMouvement);
													}

												});

//								console
//										.log("listArticlesFournituresE : "
//												+ JSON
//														.stringify(
//																$scope.listArticlesFournituresE,
//																null, ""));
//								console.log("listArticlesTissusE : "
//										+ JSON.stringify(
//												$scope.listArticlesTissusE,
//												null, " "));
//								console.log("listArticlesFACE : "
//										+ $scope.listArticlesFACE.length);

							}

							/*$log.debug("idSelectionnee : " + $scope.idSelectionnee);*/
							// ** Ajout Article
							$scope.AffectationArticle = function(article) {
								$scope.articleCourante = article ? angular
										.copy(article) : {};
								$scope.articleCourante = {};
								$scope.creationArticleForm.$setPristine();
								$scope.displayMode = "edit";
							}
							// ** Ajout mvtOF Reservation
							$scope.AffectationMvtOFReservation = function(
									stockMvtOFRvtSearchCourant) {
								$scope.stockMvtOFRvtSearchCourant = stockMvtOFRvtSearchCourant ? angular
										.copy(stockMvtOFRvtSearchCourant)
										: {};
								$scope.stockMvtOFRvtSearchCourant = {};
								$scope.rechercheMvtOFReservationForm
										.$setPristine();

								$scope.displayMode = "edit";
							}
							// Ajout et Modification mouvementStock
							$scope.modifierOuCreerBonMouvementStock = function() {
								var index = this.row.rowIndex;
								// getmouvementStock
								$http
										.get(
												baseUrlGs
														+ "/bonMouvementStock/getId:"
														+ $scope.myData[index].id)
										.success(
												function(data) {
													console
															.log("**---- getById : "
																	+ JSON
																			.stringify(
																					data,
																					null,
																					"  "));
													//show btn Generer
													$scope.modePdf = "actif";
													//show btn Valider
													$scope.modeValider = false;

													//remplir les champs :reference, Produit & Client à  partir de ofId
													$scope
															.remplirDetails(data.ofId);
													/*if(data.mouvements.length != 0){
														//$log.debug("-------- IdMagasin : "+ data.mouvements[0].idMagasin);
														showMagasin(data.mouvements[0].idMagasin);
													}*/
													//affectation des listes
													classifierListMouvement(data.mouvements);
													$scope.stockMvtOFRvtCourant = $scope.myData[index] ? angular
															.copy($scope.myData[index])
															: {};
													//idMagasin > magasinDesignation
													$log
															.debug("-------- IdMagasin : "
																	+ data.mouvements[0].idMagasin);
													$scope.stockMvtOFRvtCourant.magasinId = data.mouvements[0].idMagasin;
													$log
															.debug("-*** stockMvtOFRvtCourant IdMagasin : "
																	+ $scope.stockMvtOFRvtCourant.idMagasin);

													$scope.displayMode = "edit";

													$scope.openOrClose(
															'panel-articles',
															'#articles',
															'hidePlus');
													$scope
															.openOrClose(
																	'fourniture',
																	'#fournitureBtn',
																	'hidePlusFourniture');
													$scope.openOrClose('tissu',
															'#tissuBtn',
															'hidePlusTissu');
													$scope.openOrClose('fil',
															'#filBtn',
															'hidePlusFil');
												});

							}

							// Sauvegarder Entrer Stock
							$scope.sauvegarderAjout = function(mouvementStock) {
								$log.debug("sauvegarder Ajout");

								console.log("mouvementStock"
										+ JSON.stringify(mouvementStock, null,
												" "));

								if (angular.isDefined(mouvementStock.id)) {
									$log.debug("Sauvegarder Modification"
											+ mouvementStock);
									$scope.updateBonMouvement(mouvementStock);
								} else {
									console
											.log("Sauvegarder Ajout B.Mouvement ");
									$scope
											.creerBonMouvementStock(mouvementStock);
								}
							}

							// Mise à jour des mouvementStocks
							$scope.updateBonMouvement = function(
									bonMouvementStock) {

								var tmpMouvementStock = [];

								for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
									$scope.listArticlesFournituresE[i].typeArticle = "1";
									tmpMouvementStock
											.push($scope.listArticlesFournituresE[i]);
								}
								for (var i = 0; i < $scope.listArticlesTissusE.length; i++) {
									$scope.listArticlesTissusE[i].typeArticle = "2";
									tmpMouvementStock
											.push($scope.listArticlesTissusE[i]);
								}
								for (var i = 0; i < $scope.listArticlesFACE.length; i++) {
									$scope.listArticlesFACE[i].typeArticle = "3";
									tmpMouvementStock
											.push($scope.listArticlesFACE[i]);
								}
								bonMouvementStock.mouvements = tmpMouvementStock;
								bonMouvementStock.type = "RESERVATION";
								bonMouvementStock.ofId = $scope.stockMvtOFRvtCourant.ofId;

								/******* Attacher les elements supprimés *******/
								
								bonMouvementStock.listeMouvementsSupprimes=[];
								
								if($scope.listArticlesFournitureSupprimes.listeElementsSupprimes.length>0){
									bonMouvementStock.listeMouvementsSupprimes
									.push($scope.listArticlesFournitureSupprimes);
								}
								
								if($scope.listArticlesTissuSupprimes.listeElementsSupprimes.length>0){
									bonMouvementStock.listeMouvementsSupprimes
									.push($scope.listArticlesTissuSupprimes);
								}
								
//								if($scope.listArticlesFACESupprimes.listeElementsSupprimes.length>0){
//									bonMouvementStock.listeMouvementsSupprimes
//									.push($scope.listArticlesFACESupprimes);
//								}

								$scope.listArticlesFournitureSupprimes = {
										typeArticle : "",
										listeElementsSupprimes : []
									}
								$scope.listArticlesTissuSupprimes = {
										typeArticle : "",
										listeElementsSupprimes : []
									}
//								$scope.listArticlesFACESupprimes = {
//										typeArticle : "",
//										listeElementsSupprimes : []
//									}
								
								console.log("Objet à Modifier : "
										+ JSON.stringify(bonMouvementStock,
												null, "  "));

								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/modifierBonMouvementStock",
												bonMouvementStock)
										.success(
												function(mouvementStockModifiee) {

													$scope.displayMode = "list";
													$scope.annulerAjout();
												});
							}

							$scope.creerBonMouvementStock = function(
									mouvementStock) {
								var tmpMouvementStock = [];

								for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
									$scope.listArticlesFournituresE[i].quantiteReelle = $scope.listArticlesFournituresE[i].aReserver;
									$scope.listArticlesFournituresE[i].typeArticle = "1";
									tmpMouvementStock
											.push($scope.listArticlesFournituresE[i]);
								}
								for (var i = 0; i < $scope.listArticlesTissusE.length; i++) {
									$scope.listArticlesTissusE[i].quantiteReelle = $scope.listArticlesTissusE[i].aReserver;
									$scope.listArticlesTissusE[i].typeArticle = "2";
									tmpMouvementStock
											.push($scope.listArticlesTissusE[i]);
								}
								for (var i = 0; i < $scope.listArticlesFACE.length; i++) {
									$scope.listArticlesFACE[i].quantiteReelle = $scope.listArticlesFACE[i].aReserver;
									$scope.listArticlesFACE[i].typeArticle = "3";
									tmpMouvementStock
											.push($scope.listArticlesFACE[i]);
								}
								mouvementStock.ofId = $scope.stockMvtOFRvtCourant.ofId;
								mouvementStock.type = "RESERVATION";
								mouvementStock.mouvements = tmpMouvementStock;

								console.log("mouvementStock"
										+ JSON.stringify(mouvementStock, null,
												" "));
								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/creerBonMouvementStock",
												mouvementStock)
										.success(function(id) {
											//Todo : getById
											$scope.annulerAjout();

											$scope.displayMode = "list";
										});

							}

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								//$log.debug("Retour ..")
								//show btn Generer
								$scope.modePdf = "notActif";
								//btn Valider disabled
								$scope.modeValider = false;
								$scope.stockMvtOFRvtCourant.ofId = '';

								$scope.stockMvtOFRvtCourant = {};
								$scope.resultatRechercheDetail = {};
								$scope.creationMvtOFForm.$setPristine();

								$scope.listArticlesFournituresE = [];
								$scope.listArticlesTissusE = [];
								$scope.listArticlesFACE = [];

								$scope.openOrClose('panel-articles',
										'#articles', 'hidePlus');
								$scope.openOrClose('fourniture',
										'#FournitureBtn', 'hidePlusFourniture');
								$scope.openOrClose('tissu', '#tissuBtn',
										'hidePlusTissu');
								$scope.openOrClose('fil', '#filBtn',
										'hidePlusFil');

								//refreshGrid
								$scope.rechercherStockEntrer({
									typeBonMouvement : "RESERVATION"
								});
								//displaymode :list
								$scope.displayMode = "list";
							}
							// Suppression bon mouvement
							$scope.supprimerMouvementStock = function() {
								//var index = this.row.rowIndex;
								//$scope.index = this.row.rowIndex;
								$log.debug("DELETE .."
										+ $scope.myData[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrlGs
													+ "/bonMouvementStock/supprimerBonMouvementStock:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
									//Pas d'entree au niveau success delete 403 erreur
									//$log.debug("success delete");
									$scope.myData.splice(index, 1);

									$scope.closePopupDelete();
									$scope.annulerAjout();

								})
								.error(function(){
									$scope.myData.splice($scope.index, 1);
								})
								$scope.closePopupDelete();
								$scope.annulerAjout();

							};

							//generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
							$scope.downloadMvtReservation = function(id) {
								$log.debug("-- id" + id);
								var url = baseUrlGs
										+ "/reportgs/bonMouvementStockById?id="
										+ id + "&type=pdf";
								downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
											//$scope.annulerAjout();
										}, function(error) {
											//	$log.debug('error : '+ error);
										});
							};
							// $scope.listeMouvement();
							$scope.typeAlert = 3;

							$scope
									.$watch(
											'myData',
											function() {
												$scope.colDefs = [
														{
															field : 'numero',
															displayName : 'N° BR',
															width:'20%'
														},
														{
															field : 'client',
															displayName : 'Client',
															width:'20%'
														},
														{
															field : 'refProduit',
															displayName : 'Réf. Produit',
															width:'20%'
														},
														{
															field : 'numOF',
															displayName : 'N° OF',
															width:'15%'
														},
														{
															field : 'date | date : "dd/MM/yyyy"',
															displayName : 'Date Réservation',
															width:'10%'
														},

														{
															field : 'etat',
															displayName : 'Etat',
															width:'8%'
														},
														{
															field : '',
															cellTemplate : '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonMouvementStock()">	<i class="fa fa-fw fa-pencil"></i></button>	<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(4)"><i class="fa fa-fw fa-trash-o"></i></button></div>',
															width:'7%'
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
											var BonMvtCourant = {
												typeBonMouvement : "RESERVATION"
											};
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																baseUrlGs
																		+ "/bonMouvementStock/rechercheParCritere",
																BonMvtCourant)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.bonMouvementStock
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
																baseUrlGs
																		+ "/bonMouvementStock/rechercheParCritere",
																BonMvtCourant)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.bonMouvementStock,
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
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};

						} ])

		.filter('customfilter', function() {
			return function(liste, id) {
				var listeFiltre = [];
				// $log.debug("IdSousFamille=  "+id.id);
				// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
				angular.forEach(liste, function(elementListe, key) {

					if (elementListe.id == id.id) {
						//$log.debug(sousFamille.id +" == "+ id.id);
						listeFiltre.push(elementListe);
					}

				});
				// $log.debug(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
				return listeFiltre;
			};
		})

		/**
		 * ***************SORTIE*************************	 */

		.controller(
				'StockMvtOFSortieController',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'baseUrlGs',
						'baseUrl',
						'baseUrlGpao',
						'downloadService',
						'$rootScope',
						function($scope, $http, $filter, $log, baseUrlGs,
								baseUrl, baseUrlGpao, downloadService,
								serviceCache, $rootScope) {
							var data;

							$scope.myData = [];
							$scope.stockBD = [];
							$scope.displayMode = "list";
							$scope.modePdf = "notActif";
							$scope.mouvementStockCourante = {
								typeBonMouvement : "SORTIE",
								mouvementOF :true
							};
							$scope.stockMvtOFRvtCourant = {};
							$scope.MvtInserree = {

								referenceArticle : '',
								designationArticle : '',
								quantite : '',
								prixTotal : '',
								emplacement : ''
							};
							$scope.listeBonMouvements = {};
							$scope.listeEntiteStock = {};
							$scope.listemouvementStock = [];
							// declaration variable liste cache
							$scope.listeMagasinCache = [];
							$scope.ListEmplacementCache = [];
							$scope.listeClientCache = [];
							$scope.ListeRaisonCache = [];
							$scope.listeSousTraitantCache = [];
							$scope.listeFamilleCache = [];
							$scope.listeSousFamilleCache = [];
							$scope.listeMatiereCache = [];
							$scope.listeMetrageCache = [];
							$scope.listeGrosseurCache = [];
							// declaration variable liste des Mouvements
							$scope.listArticlesFournitures = [];
							$scope.listArticlesFournituresE = [];
							$scope.listArticlesTissus = [];
							$scope.listArticlesFilaCoudres = [];
							$scope.idSelectionnee = [];
							/***************************************************
							 * Slides Articles Sortie *
							 **************************************************/
							$scope.animateArticleFourniture = function() {
								$("#articlesS").click(
										function() {
											$scope.openOrClose(
													'panel-articlesS',
													'#articlesS', 'hidePlus');
										});
								$("#tissuBtnS").click(
										function() {
											$scope.openOrClose('tissuS',
													'#tissuBtnS',
													'hidePlusTissu');
										});
								$("#filBtnS").click(
										function() {
											$scope.openOrClose('filS',
													'#filBtnS', 'hidePlusFil');
										});
								$("#fournituresBtnS").click(
										function() {
											$scope.openOrClose('fournituresS',
													'#fournituresBtnS',
													'hidePlusFourniture');
										});
							}
							$scope.openOrClose = function(id_panel, id_div,
									classe) {
								$("div[id=" + id_panel + "]")
										.fadeToggle("slow");
								$(id_div).toggleClass(classe);
								if ($(id_div).hasClass(classe)) {
									$(id_div).text('+');
								} else {
									$(id_div).text('-');
								}
							}

							$scope.animateArticleFourniture();
							/***************************************************
							 * Gestion de Cache des Referentiels *
							 **************************************************/
							// Liste de Matieres cache
							$scope.listeMatiereCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeMatiereArticleCache")
										.success(function(data) {
											$scope.listeMatiereCache = data;

										});
							}

							// Liste de Metrage cache
							$scope.listeMetrageCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeMetrageArticleCache")
										.success(function(data) {
											$scope.listeMetrageCache = data;

										});
							}
							// Liste de Grosseur cache
							$scope.listeGrosseurCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeGrosseurArticleCache")
										.success(function(data) {
											$scope.listeGrosseurCache = data;

										});
							}
							// Liste de Famille cache
							$scope.listeFamilleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeFamilleArticleCache")
										.success(function(data) {
											$scope.listeFamilleCache = data;

										});
							}
							// Liste de Sous Famille cache
							$scope.listeSousFamilleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSousFamilleArticleCache")
										.success(
												function(data) {
													$scope.listeSousFamilleCache = data;

												});
							}
							// Liste de Client cache

							// Liste de Client cache
							$scope.listeClientCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeClientCache")
										.success(function(dataC) {
											$scope.listeClientCache = dataC;

										});
							}

							// Liste de Sous traitant Cache
							$scope.listeSousTraitantCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeSousTraitantCache")
										.success(
												function(dataS) {
													$scope.listeSousTraitantCache = dataS;

												});
							}
							// able or disable list partie interesse
							$('#checkboxPI').change(
									function() {
										if (this.checked) {
											$("#selectPIStockSortie").attr(
													'disabled', false);
										} else {
											$("#selectPIStockSortie").attr(
													'disabled', 'disabled');
										}
									});
							// Liste de magasinCache
							$scope.listeMagasinCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeMagasinCache")
										.success(
												function(dataMagasin) {
													$scope.listeMagasinCache = dataMagasin;

												});
							}
							// Liste de emplacementCache
							$scope.ListeEmplacementCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeEmplacementCache")
										.success(
												function(dataFamilleCache) {
													$scope.ListEmplacementCache = dataEmplacementCache;

												});
							}
							// Liste de raisonCache
							$scope.ListeRaisonCache = function() {
								$http
										.get(
												baseUrlGs
														+ "/gestionnaireCache/listeRaisonCache")
										.success(
												function(dataSousFamilleCache) {
													$scope.ListeRaisonCache = dataSousFamilleCache;

												});
							}

							$scope.getlisteBR = function(type) {

								$http
										.get(
												baseUrlGs
														+ "/bonMouvementStock/getListeNumParType?type="
														+ type).success(
												function(liste) {
													$scope.listeBR = liste;

												});

								console.log("$scope.listeBR"
										+ JSON.stringify($scope.listeBR, null,
												" "));
							}

							$scope.listeOF = function() {
								$http
										.get(
												baseUrlGpao
														+ "/ordreFabrication/all")
										.success(
												function(data) {
													$log.debug("liste OF : "
															+ data.length);
													$scope.listeOF = data;

												});
							}

							$scope.listeMatiereCache();
							$scope.listeMetrageCache();
							$scope.listeGrosseurCache();
							$scope.listeFamilleCache();
							$scope.listeSousFamilleCache();
							$scope.listeClientCache();
							$scope.listeSousTraitantCache();
							$scope.listeMagasinCache();
							$scope.ListeRaisonCache();
							$scope.getlisteBR("reservation");
							$scope.listeOF();

							/***************************************************
							 * Gestion des Bon mouvements *
							 **************************************************/

							$scope.setBS = function() {

								var bonMouvementR = [];
								bonMouvementR = $filter('filter')(
										$scope.listeBR, {
											id : $scope.stockMvtOFRvtCourant.BR
										});

								var bonMouvement = "";

								if (bonMouvementR.length != 0) {
									bonMouvement = bonMouvementR[0].numero;
								}

								$scope.stockMvtOFRvtCourant.numero = "S/"
										+ bonMouvement;

							}

							$scope.resultatRechercheDetail = {};

							$scope.remplirDetails = function(BRId) {

								var bonMouvement = [];

								bonMouvement = $filter('filter')(
										$scope.listeBR, {
											id : BRId
										});
								console.log("bonMouvement" + bonMouvement);
								if (bonMouvement.length != 0) {
									$http
											.get(
													baseUrlGpao
															+ "/ordreFabrication/getId:"
															+ bonMouvement[0].idOF)
											.success(
													function(data) {
														//affecter le resultat au ng-model
														//refeerence, designation Produit
														$scope.resultatRechercheDetail.produitReference = data.produitReference;
														$scope.resultatRechercheDetail.produitDesignation = data.produitDesignation;
														//abreviation Client
														$scope.resultatRechercheDetail.partieInterresAbreviation = data.partieInterresAbreviation;

														console
																.log("$scope.resultatRechercheDetail"
																		+ JSON
																				.stringify(
																						$scope.resultatRechercheDetail,
																						null,
																						" "));
													});
								} else {
									console
											.log("bonMouvement n'existe pas dans la liste");
								}

							}

							// Rechercher par Type Sortie
							$scope.rechercherStockEntrerParType = function(type) {
								//$log.debug("recherche en cours");
								$scope.myData = [];
								$http
										.get(
												baseUrlGs
														+ "/bonMouvementStock/getBonMouvementParType:"
														+ type)
										.success(
												function(resultat) {
													$scope.myData = resultat;
													console
															.log("listeBonMouvementEntree  :"
																	+ $scope.myData.length);
													$scope.displayMode = "rechercher";
												});
								//$scope.rechercheSSForm.$setPristine();
							}
							//							$scope.rechercherStockEntrerParType("SORTIE");

							// Annuler Ajout
							$scope.rechercherStockEntrerParType("SORTIE");
							$scope.cancelAdd = function(rowform, index, id,
									designation, liste) {
								$log.debug("* Designation "
										+ liste[0].designation);
								if (angular.isDefined(id)) {
									$log.debug("DEF ID " + id);
									if (id == "") {
										if (designation == "") {
											// $log.debug("IdDELETE"
											// +designation);
											liste.splice(index, 1);
											rowform.$cancel();
										} else {
											$log.debug("IdNOT DELETE "
													+ designation);
											rowform.$cancel();
										}
									}
									rowform.$cancel();
								} else {
									$log.debug("UND ID " + id);
									if (designation == "") {
										$log.debug("DELETE" + designation);
										liste.splice(index, 1);
										rowform.$cancel();
									} else {
										$log.debug("NOT DELETE " + designation);
										rowform.$cancel();
									}
								}
							}

							$scope.rechercherStockSortie = function(
									mouvementCourante) {

								$log.debug("mouvementCourante"
										+ JSON.stringify(mouvementCourante,
												null, " "));
								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/rechercheParCritere",
												mouvementCourante)
										.success(
												function(resultat) {
													$log
															.debug("size rech"
																	+ resultat.nombreResultaRechercher);
													$scope.myData = resultat.bonMouvementStock;
													//data, page,pageSize
													$scope
															.setPagingData(
																	$scope.myData,
																	$scope.pagingOptions.currentPage,
																	$scope.pagingOptions.pageSize);
													$scope.displayMode = "rechercher";

												});

							}

							//Liste des Articles récuperée de l'objet Vue
							$scope.remplirListMouvementSortie = function(BRId) {

								$scope.setBS();

								//show btn Valider
								$scope.modeValider = true;
								//viderSubLists
								viderSubLists();

								$http
										.get(
												baseUrlGs
														+ "/bonMouvementStock/getListeMouvementsSortie?bonMouvementId="
														+ BRId)
										.success(
												function(bonMouvement) {

													//getResult
													classifierListMouvement(bonMouvement);
													//showResults in panel
													$scope.openOrClose(
															'panel-articlesS',
															'#articlesS',
															'hidePlus');

													$scope
															.openOrClose(
																	'fournituresS',
																	'#fournituresBtnS',
																	'hidePlusFourniture');

													$scope.openOrClose(
															'tissuS',
															'#tissuBtnS',
															'hidePlusTissu');

													$scope.openOrClose('filS',
															'#filBtnS',
															'hidePlusFil');

												});
							}

							//vider les listes
							var viderSubLists = function() {
								//init subListMouvement
								$scope.listArticlesFournituresE = [];
								$scope.listArticlesTissusE = [];
								$scope.listArticlesFACE = [];
							}

							//Classification des ListMouvement selon le Type
							var classifierListMouvement = function(
									listElementMouvement) {
								// subListes de ListMouvement: 1:"fourniture", 2:"tissu", 3: "fil à coudre" 
								angular.forEach(listElementMouvement, function(
										elementMouvement, key) {

									console.log("elementMouvement"
											+ JSON.stringify(elementMouvement,
													null, " "));
									var type = elementMouvement.type;
									$log.debug("--------- TYPE " + type);
									if (type == 1) {
										$scope.listArticlesFournituresE
												.push(elementMouvement);
									} else if (type == 2) {
										$scope.listArticlesTissusE
												.push(elementMouvement);

									} else if (type == 3) {
										$scope.listArticlesFACE
												.push(elementMouvement);
									}

								});

								$log
										.debug("listArticlesFournituresE : "
												+ $scope.listArticlesFournituresE.length);
								console.log("listArticlesTissusE : "
										+ JSON.stringify(
												$scope.listArticlesTissusE,
												null, " "));
								$log.debug("listArticlesFACE : "
										+ $scope.listArticlesFACE.length);

							}
							//						$scope.rechercherStockSortie = function(
							//									mouvementCourante) {
							//								$http
							//										.post(
							//												baseUrl
							//														+ "/bonMouvementStock/rechercheParCritere",
							//												mouvementCourante)
							//										.success(
							//												function(resultat) {
							//													$scope.myData = resultat.bonMouvementStock;
							//													$scope.displayMode = "rechercher";
							//												});
							//								$scope.rechercheSSForm.$setPristine();
							//							}
							// Rechercher Stock Sortie
							/*$scope.rechercherStockSortie = function(
									mouvementCourante) {
								$log.debug("recherche en cours");
								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/rechercheParCritere",
												mouvementCourante)
										.success(
												function(resultat) {
													$scope.myData = resultat.bonMouvementStock;
													$scope.displayMode = "rechercher";
												});
								$scope.rechercheSSForm.$setPristine();
							}*/
							// ** Ajout mouvementStock
							$scope.AffectationMouvement = function(
									mouvementStock) {
								$scope.mouvementStockCourante = mouvementStock ? angular
										.copy(mouvementStock)
										: {};
								$scope.mouvementCourante = {};
								//$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "edit";

							}
							// Ajout et Modification mouvementStock
							$scope.modifierOuCreerBonMouvementStock = function() {
								// vider les Listes
								$scope.listeArticles = [];
								$scope.listArticlesFournituresE = [];
								$scope.listArticlesTissusE = [];
								$scope.listArticlesFACE = [];

								var index = this.row.rowIndex;

								console
										.log("modif - getByID - $scope.myData[index].id"
												+ $scope.myData[index].id);
								// getmouvementStock
								$http
										.get(
												baseUrlGs
														+ "/bonMouvementStock/getId:"
														+ $scope.myData[index].id)
										.success(
												function(data) {

													console
															.log("getByID - data"
																	+ JSON
																			.stringify(
																					data,
																					null,
																					" "));

													$scope.modePdf = "actif";

													$scope.listeMouvements = data.mouvements;
													$scope.listeArticles = $scope.listeMouvements;
													for (var i = 0; i < $scope.listeArticles.length; i++) {
														if ($scope.listeArticles[i].typeArticle == 1) {
															$scope.listArticlesFournituresE
																	.push($scope.listeArticles[i]);
														} else if ($scope.listeArticles[i].typeArticle == 2) {
															$scope.listArticlesTissusE
																	.push($scope.listeArticles[i]);
														} else {
															$scope.listArticlesFACE
																	.push($scope.listeArticles[i]);
														}
													}

													$scope.stockMvtOFRvtCourant = $scope.myData[index] ? angular
															.copy($scope.myData[index])
															: {};

													var bonMouvementR = [];
													bonMouvementR = $filter(
															'filter')
															(
																	$scope.listeBR,
																	{
																		idOF : data.ofId
																	});

													var bonMouvement = "";

													if (bonMouvementR.length != 0) {
														$scope.stockMvtOFRvtCourant.BR = bonMouvementR[0].id;
														console
																.log("$scope.stockMvtOFRvtCourant.BR"
																		+ $scope.stockMvtOFRvtCourant.BR);
														$scope
																.remplirDetails($scope.stockMvtOFRvtCourant.BR);
													}

												});

								$scope.displayMode = "edit";
								//showResults in panel
								$scope.openOrClose('panel-articlesS',
										'#articlesS', 'hidePlus');

								$scope.openOrClose('fournituresS',
										'#fournituresBtnS',
										'hidePlusFourniture');

								$scope.openOrClose('tissuS', '#tissuBtnS',
										'hidePlusTissu');
								$scope.openOrClose('filS', '#filBtnS',
										'hidePlusFil');
							}
							// Sauvegarder Entrer Stock
							$scope.sauvegarderAjout = function(mouvementStock) {

								console.log("mouvementStock"
										+ JSON.stringify(mouvementStock, null,
												" "));
								if (angular.isDefined(mouvementStock.id)) {
									//$log.debug("Modification"	+ mouvementStock.numero);
									$scope.updateBonMouvement(mouvementStock);
								} else {
									//$log.debug("Sauvegarder: Creation"		+ mouvementStock.numero);
									$scope
											.creerBonMouvementStock(mouvementStock);
								}
							}
							// Mise à jour des mouvementStocks
							$scope.updateBonMouvement = function(
									bonMouvementStock) {
								var tmpListeArticles = [];
								for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
									$scope.listArticlesFournitures[i].typeArticle = "1";
									tmpListeArticles
											.push($scope.listArticlesFournituresE[i]);
								}
								for (var i = 0; i < $scope.listArticlesTissusE.length; i++) {
									$scope.listArticlesTissusE[i].typeArticle = "2";
									tmpListeArticles
											.push($scope.listArticlesTissusE[i]);
								}
								for (var i = 0; i < $scope.listArticlesFACE.length; i++) {
									$scope.listArticlesFACE[i].typeArticle = "3";
									tmpListeArticles
											.push($scope.listArticlesFACE[i]);
								}
								//$log.debug("----tmpListeArticles = "	+ JSON.stringify(tmpListeArticles,null,"  "));
								bonMouvementStock.mouvements = tmpListeArticles;
								bonMouvementStock.type = "RESERVATION";

								var bonMouvementR = [];
								bonMouvementR = $filter('filter')(
										$scope.listeBR, {
											id : $scope.stockMvtOFRvtCourant.BR
										});

								var bonMouvement = "";

								if (bonMouvementR.length != 0) {
									bonMouvementStock.ofId = bonMouvementR[0].idOF;
									bonMouvementStock.numBRSortie = bonMouvementR[0].numero;
								}

								console.log("Objet Modifié : "
										+ JSON.stringify(bonMouvementStock,
												null, "  "));
								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/modifierBonMouvementStock",
												bonMouvementStock)
										.success(
												function(mouvementStockModifiee) {

													//													$scope.rechercheSSForm.$setPristine();

													//													$scope.creationSSForm.$setPristine ();
													$scope.displayMode = "list";
													$scope.annulerAjoutS();
												});

							}
							// Création mouvementStock
							$scope.listeArticles = [];

							$scope.creerBonMouvementStock = function(
									bonMouvementStock) {
								var tmpListeArticles = [];

								for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
									$scope.listArticlesFournituresE[i].quantiteReelle = $scope.listArticlesFournituresE[i].aReserver;
									$scope.listArticlesFournituresE[i].typeArticle = "1";

									tmpListeArticles
											.push($scope.listArticlesFournituresE[i]);
								}
								for (var i = 0; i < $scope.listArticlesTissusE.length; i++) {
									$scope.listArticlesTissusE[i].quantiteReelle = $scope.listArticlesTissusE[i].aReserver;
									$scope.listArticlesTissusE[i].typeArticle = "2";
									tmpListeArticles
											.push($scope.listArticlesTissusE[i]);
								}
								for (var i = 0; i < $scope.listArticlesFACE.length; i++) {
									$scope.listArticlesFACE[i].quantiteReelle = $scope.listArticlesFACE[i].aReserver;
									$scope.listArticlesFACE[i].typeArticle = "3";
									tmpListeArticles
											.push($scope.listArticlesFACE[i]);
								}

								bonMouvementStock.mouvements = tmpListeArticles;
								bonMouvementStock.type = "SORTIE";

								var bonMouvementR = [];
								bonMouvementR = $filter('filter')(
										$scope.listeBR, {
											id : $scope.stockMvtOFRvtCourant.BR
										});

								var bonMouvement = "";

								if (bonMouvementR.length != 0) {
									bonMouvementStock.ofId = bonMouvementR[0].idOF;
									bonMouvementStock.numBRSortie = bonMouvementR[0].numero;
								}

								console.log("Objet Créé : "
										+ JSON.stringify(bonMouvementStock,
												null, "  "));

								$http
										.post(
												baseUrlGs
														+ "/bonMouvementStock/creerBonMouvementStock",
												bonMouvementStock)
										.success(function(id) {

											//													$scope.rechercheSSForm.$setPristine();

											//													$scope.creationSSForm.$setPristine();

											$scope.annulerAjoutS();

											$scope.displayMode = "list";

										});

							}

							// Annulation de l'ajout
							$scope.annulerAjoutS = function() {

								console.log('annulationn..');
								//btn Valider disabled
								$scope.modeValider = false;
								$scope.mouvementStockCourante = {};
								$scope.stockMvtOFRvtCourant = {};
								$scope.resultatRechercheDetail = {};
								$scope.modePdf = "notActif";
								//$scope.creationSSForm.$setPristine();
								//								$scope.rechercheSSForm.$setPristine();

								$scope.listeArticles = [];
								$scope.listArticlesFournituresE = [];
								$scope.listArticlesTissusE = [];
								$scope.listArticlesFACE = [];

								$scope.displayMode = "list";
								$scope.openOrClose('panel-articlesS',
										'#articlesS', 'hidePlus');
								$scope.openOrClose('tissuS', '#tissuBtnS',
										'hidePlusTissu');
								$scope.openOrClose('filS', '#filBtnS',
										'hidePlusFil');
								//								$scope.openOrClose('fournitureS', '#fournitureBtnS',
								//								'hidePlusFourniture');

								$scope.rechercherStockSortie({
									typeBonMouvement : "SORTIE",
									mouvementOF :true
								});
								$scope.displayMode = "List";

							}

							//							// Suppression bon mouvement
							//							$scope.supprimerMouvementStock = function() {
							//								//var index = this.row.rowIndex;
							//								$http(
							//										{
							//											method : "DELETE",
							//											url : baseUrlGs
							//													+ "/bonMouvementStock/supprimerBonMouvementStock:"
							//													+ $scope.myData[index].id
							//										}).success(function() {
							//
							//											$log.debug("success delete");
							//											$scope.myData.splice(index, 1);
							//										 
							//											
							//
							//								});
							// Suppression bon mouvement9
							$scope.supprimerMouvementStock = function() {
								//var index = this.row.rowIndex;
								//$scope.index = this.row.rowIndex;
								$log.debug("DELETE .."
										+ $scope.myData[$scope.index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrlGs
													+ "/bonMouvementStock/supprimerBonMouvementStock:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
									//Pas d'entree au niveau success delete 403 erreur
									$log.debug("success delete");

								});
								$scope.myData.splice($scope.index, 1);
								$scope.annulerAjoutS();
								$scope.closePopupDelete();
							};
							// $scope.listeMouvement();
							/***************************************************
							 * Fin de Gestion des Bon mouvements *
							 **************************************************/
							/***************************************************
							 * Gestion des Mouvements
							 **************************************************/

							$scope.listArticlesFournitureSupprimes = {
								typeArticle : "",
								listeElementsSupprimes : []
							}

							// Supprimer Mouvements
							$scope.removeMvtFourniture = function(index) {

//								$log
//										.debug("element supprimes"
//												+ JSON
//														.stringify(
//																$rootScope.listArticlesFournitures[index],
//																null, " "));
//
//								$scope.listArticlesFournitureSupprimes.typeArticle = "1";
//
//								$scope.listArticlesFournitureSupprimes.listeElementsSupprimes
//										.push({
//											"entiteStockId" : $rootScope.listArticlesFournitures[index].entiteStock,
//											"qteReservee" : $rootScope.listArticlesFournitures[index].besoinOF
//										});
//
//								$log
//										.debug("listArticlesFournitureSupprimes"
//												+ JSON
//														.stringify(
//																$scope.listArticlesFournitureSupprimes,
//																null, " "));

								for (var i = 0; i < $scope.listeArticles.length; i++) {
									console
											.log("ID entite Stock in Article List : "
													+ $scope.listeArticles[i].entiteStock);
									console
											.log("$scope.listArticlesFournitures[index].entiteStock : "
													+ $scope.listArticlesFournitures[index].entiteStock);
									$log.debug("$scope.listeArticles : "
											+ $scope.listeArticles);
									if ($scope.listeArticles[i].entiteStock == $scope.listArticlesFournitures[index].entiteStock)
										$scope.listeArticles.splice(i, 1);
								}
								$scope.listArticlesFournitures.splice(index, 1);
								$log.debug("$scope.listeArticles : "
										+ $scope.listeArticles);
							};
							
							$scope.listArticlesTissuSupprimes = {
									typeArticle : "",
									listeElementsSupprimes : []
								}
							
							$scope.removeMvtTissu = function(index) {
								
//								$scope.listArticlesTissuSupprimes.typeArticle = "2";
//								
//								$scope.listArticlesTissuSupprimes.listeElementsSupprimes
//										.push({
//											"entiteStockId" : $rootScope.listArticlesTissus[index].entiteStock,
//											"qteReservee" : $rootScope.listArticlesTissus[index].besoinOF,
//											"nbRouleauxReserve": $rootScope.listArticlesTissus[index].nbrRouleau
//										});
//								
								$scope.listArticlesTissus.splice(index, 1);
							};
							
							$scope.listArticlesFACESupprimes = {
									typeArticle : "",
									listeElementsSupprimes : []
								}

							$scope.removeMvtFAC = function(index) {

//								$scope.listArticlesFACESupprimes.typeArticle = "3";
//								
//								$scope.listArticlesFACESupprimes.listeElementsSupprimes
//										.push({
//											"entiteStockId" : $rootScope.listArticlesFACE[index].entiteStock,
//											"conesReservees" : $rootScope.listArticlesFACE[index].conesReel,
//											"finconesReservess": $rootScope.listArticlesFACE[index].finconesReel,
//											"poidsReserve": $rootScope.listArticlesFACE[index].poids
//										});
								
								$scope.listArticlesFACE.splice(index, 1);
							};
							/** Fin de gestion des Mouvements */

							//generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
							$scope.downloadMvtSortie = function(id) {
								$log.debug("-- id" + id);
								var url = baseUrlGs
										+ "/reportgs/bonMouvementStockById?id="
										+ id + "&type=pdf";
								downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
											//$scope.annulerAjout();
										}, function(error) {
											//	$log.debug('error : '+ error);
										});
							};
							/**
							 * ********************gestion grid
							 * stock****************
							 */
							$scope.typeAlert = 3;
							$scope.pagingOptions = {
								pageSizes : [ 5, 10, 13 ],
								pageSize : 13,
								currentPage : 1
							};

							$scope
									.$watch(
											'myData',
											function() {
												$scope.colDefs = [
														{
															field : 'numero',
															displayName : 'N° BS',
															width:'21%'
														},
														{
															field : 'date | date : "dd/MM/yyyy"',
															displayName : 'Date de sortie',
															width:'10%'
														},
														{
															field : 'numBRSortie',
															displayName : 'N° BR',
															width:'21%'
														},
														{
															field : 'dateReservation | date : "dd/MM/yyyy"',
															displayName : 'Date de réservation',
															width:'10%'
														},
														{
															field : 'raisonMouvementDesignation',
															displayName : ' Raison de sortie',
															width:'10%'
														},

														{
															field : 'valeur',
															displayName : 'Valeur BS',
															width:'10%'
															
														},
														{
															field : 'etat',
															displayName : 'Etat',
															width:'10%'
														},
														{
															field : '',
															cellTemplate : '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerBonMouvementStock()">	<i class="fa fa-fw fa-pencil"></i></button>	<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(4)"><i class="fa fa-fw fa-trash-o"></i></button></div>',
															width:'8%'
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
											var BonMvtCourant = {
												typeBonMouvement : "SORTIE",
												mouvementOF : true
											};
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																baseUrlGs
																		+ "/bonMouvementStock/rechercheParCritere",
																BonMvtCourant)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.bonMouvementStock
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
												BonMvtCourant.typeBonMouvement = "SORTIE";
												$http
														.post(
																baseUrlGs
																		+ "/bonMouvementStock/rechercheParCritere",
																BonMvtCourant)
														.success(
																function(
																		largeLoad) {

																	$scope
																			.setPagingData(
																					largeLoad.bonMouvementStock,
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

							$scope.gridOptionsS = {
								data : 'myData',
								columnDefs : 'colDefs',
								enablePaging : true,
								showFooter : true,
								enableHighlighting : true,
								totalServerItems : 'totalServerItems',
								pagingOptions : $scope.pagingOptions,
								selectedItems : $scope.selectedRows,
								filterOptions : $scope.filterOptions,
							};

							/***************************************************
							 * Gestion des Grids Stock
							 * ************************************************
							 * $scope.typeAlert = 3; $scope.filterOptions = {
							 * filterText : "", useExternalFilter : true };
							 * $scope.totalServerItems = 0; $scope.pagingOptions = {
							 * pageSizes : [ 5, 10, 20 ], pageSize : 14,
							 * currentPage : 1 }; $scope.setPagingData =
							 * function(data, page, pageSize) { var pagedData =
							 * data.slice((page - 1) pageSize, page * pageSize);
							 * $scope.myData = pagedData;
							 * $scope.totalServerItems = data.length; if
							 * (!$scope.$$phase) { $scope.$apply(); } };
							 * $scope.getPagedDataAsync = function(pageSize,
							 * page, searchText) { setTimeout(function() {
							 * 
							 * if (searchText) { var ft =
							 * searchText.toLowerCase();
							 * 
							 * data = $scope.myData .filter(function(item) {
							 * return JSON.stringify(item) .toLowerCase()
							 * .indexOf(ft) != -1; });
							 * $scope.setPagingData(data, page, pageSize); }
							 * else {
							 * 
							 * $scope.setPagingData($scope.myData, page,
							 * pageSize); } }, 100); };
							 * $scope.getPagedDataAsync(
							 * $scope.pagingOptions.pageSize,
							 * $scope.pagingOptions.currentPage);
							 * 
							 * $scope .$watch( 'pagingOptions', function(newVal,
							 * oldVal) { if (newVal !== oldVal &&
							 * newVal.currentPage !== oldVal.currentPage) {
							 * $scope .getPagedDataAsync(
							 * $scope.pagingOptions.pageSize,
							 * $scope.pagingOptions.currentPage,
							 * $scope.filterOptions.filterText); } }, true);
							 * $scope.$watch('filterOptions', function(newVal,
							 * oldVal) { if (newVal !== oldVal) {
							 * $scope.getPagedDataAsync(
							 * $scope.pagingOptions.pageSize,
							 * $scope.pagingOptions.currentPage,
							 * $scope.filterOptions.filterText); } }, true);
							 * 
							 * $scope.gridOptionsS = { dataselected :
							 * 'myDataselected', selectedItems : [],
							 * 
							 * data : 'myData', columnDefs : [ { field :
							 * 'numero', displayName : 'N° BS' }, { field :
							 * 'date | date : "dd/MM/yyyy"', displayName : 'Date
							 * Entrée' }, { field :
							 * 'raisonMouvementDesignation', displayName :
							 * 'Raison d\'entrée' }, { field : 'valeur',
							 * displayName : 'Valeur BS' }, { field : 'etat',
							 * displayName : 'Etat' }, { field : '',
							 * cellTemplate : '<div class="buttons pull-right"><button
							 * data-nodrag class="btn btn-default btn-xs"
							 * ng-click="modifierOuCreerBonMouvementStock()"> <i
							 * class="fa fa-fw fa-pencil"></i></button>
							 * <button data-nodrag class="btn btn-default
							 * btn-xs" ng-click="supprimerMouvementStock()"><i
							 * class="fa fa-fw fa-trash-o"></i></button></div>' } ],
							 * showSelectionCheckbox : true,
							 * beforeSelectionChange :
							 * $scope.beforeVehicleSelectionChange,
							 * checkboxHeaderTemplate : '<input
							 * class="ngSelectionHeader" type="checkbox"
							 * ng-show="multiSelect" ng-model="allSelected"
							 * ng-change="toggleSelectAll(allSelected,
							 * true)"/>', enablePaging : true, showFooter :
							 * true, totalServerItems : 'totalServerItems',
							 * pagingOptions : $scope.pagingOptions,
							 * filterOptions : $scope.filterOptions, }; /** Fin
							 * de gestion des Grids Stock
							 */
						} ])
		.controller(
				'DateEntreeIntro',
				[
						'$scope',
						function($scope) {

							$scope.maxToDay = new Date();
							// $scope.stockMvtOFRvtCourant = {};
							$scope.today = function() {
								$scope.stockMvtOFRvtCourant.date = new Date();
							};
							// $scope.today();

							$scope.clear = function() {
								$scope.stockMvtOFRvtCourant.date = null;
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

							$scope.initDate = new Date('2016-15-20');
							$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];
						} ])
