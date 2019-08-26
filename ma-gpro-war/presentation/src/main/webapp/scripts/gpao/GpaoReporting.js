'use strict'
var app = angular.module('gpro.gpaoReporting', []);

// Chaine
app
		.controller(
				'GpaoReportingChaineCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'baseUrlGpao',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter, $log, downloadService,
								baseUrlGpao, baseUrlGc, baseUrl) {
							$scope.listChaine = [];
							// listChaines
							$scope.listeChaines = function() {
								$http.get(baseUrlGpao + "/chaine/all").success(
										function(dataProduit) {
											$scope.listChaine = dataProduit;
										});
							}
							$scope.listeChaines();
							$scope.listProduitDrop = [] ;
							$scope.listeProduit = function() {
								$http
										.get(baseUrl + "/produit/all")
										.success(
												function(dataProduit) {
													$scope.listProduitDrop = dataProduit;
													// //$log.debug(" *
													// $scope.listProduit : "+
													// $scope.listProduitDrop.length);
												});
							}
							$scope.listeProduit();
							$scope.ListClientOFCache =[];
							// Liste des ClientCommandeVenteCache
							$scope.listeClientOrdreFabricationCache = function() {
								$http
										.get(
												baseUrlGpao
														+ "/gestionProduitAOCache/listeClientCache")
										.success(
												function(dataClientCache) {
													$scope.ListClientOFCache = dataClientCache;
												});
							}
							$scope.listeClientOrdreFabricationCache();


							// annuler Recherche
							$scope.annulerChaineAjout = function() {

								$scope.modePdf = "notActive";

								$scope.ordreFabricationCourant = {
									"chaineId" : "",
									"dateSaisieMin" : "",
									"dateSaisieMax" : "",
									"clientId" :"",
									"produitId" :""
								};

								$scope.rechercheOFForm.$setPristine();
							}

							$scope.listCodes = [];
			                // Liste des codes
							$scope.listCodesOperationsComptage = function() {
			                	 $http
			                        .get(baseUrlGpao + "/catalogue/getSwitchComptage?comptage=true")

			                        .success(
			                            function(data) {
			                            	
			                                $scope.listCodes = data;
			                            });
			                }
			                $scope.listCodesOperationsComptage();
			               
							
							// generer rapport de tous les bons de sortie. mode
							// : List
							// conversion date en String
							function formattedDate(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

								if (month.length < 2)
									month = '0' + month;
								if (day.length < 2)
									day = '0' + day;
								return [ year, month, day ].join('-');
							}

							$scope.GenerateAllOuProductivite = function(
									feuilleCourante, action) {
								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(feuilleCourante.dateSaisieMin)) {
									$log.debug("==dateSaisieMin "
											+ feuilleCourante.dateSaisieMin);

									if (feuilleCourante.dateSaisieMin != "") {
										newdateSaisieMinFormat = formattedDate(feuilleCourante.dateSaisieMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(feuilleCourante.dateSaisieMax)) {
									$log.debug("==dateSaisieMax "
											+ feuilleCourante.dateSaisieMax);

									if (feuilleCourante.dateSaisieMax != "") {
										newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateSaisieMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								// $log.info("-- feuilleCourante" +
								// JSON.stringify($scope.feuilleCourante, null,
								// " ") );
								$log.info("feuilleCouranteChaine "
										+ JSON.stringify(feuilleCourante, null,
												"  "));

								if (action != null) {
									var url;
									if (action == 1) {
										// Recap
										url = baseUrlGpao
												+ "/report/rapportRecapProdChaine?dateSaisieMin="
												+ newdateSaisieMinFormat
												+ "&dateSaisieMax="
												+ newdateSaisieMaxFormat
												+ "&chaineId="
												+ feuilleCourante.chaineId
												+ "&type=pdf";
									} else if (action == 2) {
										// Comptage
										url = baseUrlGpao
												+ "/report/rapportChaineComptage?chaineId="
												+ feuilleCourante.chaineId
												+ "&dateIntroductionMin="
												+ newdateSaisieMinFormat
												+ "&dateIntroductionMax="
												+ newdateSaisieMaxFormat
												+ "&type=pdf";
									} else if (action == 3) {
										// GlobalOperation
										url = baseUrlGpao
												+ "/report/rapportChaineGlobalOperation?chaineId="
												+ feuilleCourante.chaineId
												+ "&dateIntroductionMin="
												+ newdateSaisieMinFormat
												+ "&dateIntroductionMax="
												+ newdateSaisieMaxFormat
												+ "&type=pdf";
									} else if (action == 4) {
									
										
//										@RequestParam("dateDebut") String dateDebut,
//										@RequestParam("dateFin") String dateFin,
//										@RequestParam("clientId") Long clientId,
//										@RequestParam("pointCmpt") Long pointCmpt ,
//										@RequestParam("produitId") Long produitId,
										
										// GlobalOF
										url = baseUrlGpao
												+ "/report/ProductionReport?pointCmpt="
												+ feuilleCourante.operation
												+ "&dateDebut="
												+ newdateSaisieMinFormat
												+ "&dateFin="
												+ newdateSaisieMaxFormat
												+"&clientId="
												+feuilleCourante.partieInteresseId
												+"&produitId="
												+feuilleCourante.produitId
												+"&chaineId="
												+feuilleCourante.chaineId
												+ "&type=pdf";
									} else if (action == 5) {
										// JourOf
										url = baseUrlGpao
												+ "/report/chaineJourOFReport?chaineId="
												+ feuilleCourante.chaineId
												+ "&dateIntroductionMin="
												+ newdateSaisieMinFormat
												+ "&dateIntroductionMax="
												+ newdateSaisieMaxFormat
												+ "&type=pdf";
									}
									$log.info("URL " + url);
									// Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}
							}

						} ]);

// Operateur Competence

// operateur
app
		.controller(
				'GpaoReportingOperateurCompetenceCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'baseUrlGpao',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter, $log, downloadService,
								baseUrlGpao, baseUrlGc, baseUrl) {
							//                       
							// annuler Recherche
							$scope.annulerChaineAjout = function() {

								$scope.modePdf = "notActive";

								$scope.operateurCompetenceCourant = {
									"matricule" : "",
									"dateSaisieMin" : "",
									"dateSaisieMax" : ""
								};

								$scope.rechercheOFForm.$setPristine();
							}

							$scope.listMatricule = [];

							// Liste des matricules
							$scope.listeMatricule = function() {
								$http
										.get(
												baseUrlGpao
														+ "/feuilleSaisie/listPersonnel")
										.success(function(dataProduit) {
											$scope.listMatricule = dataProduit;
										});
							}
							$scope.listeMatricule();

							// generer rapport de tous les operateur
							// competences. mode : List
							// conversion date en String
							function formattedDate(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

								if (month.length < 2)
									month = '0' + month;
								if (day.length < 2)
									day = '0' + day;
								return [ year, month, day ].join('-');
							}

							$scope.downloadOperateur = function(
									operateurCompetenceCourant, action) {
								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(operateurCompetenceCourant.dateSaisieMin)) {
									$log
											.debug("==dateSaisieMin "
													+ operateurCompetenceCourant.dateSaisieMin);

									if (operateurCompetenceCourant.dateSaisieMin != "") {
										newdateSaisieMinFormat = formattedDate(operateurCompetenceCourant.dateSaisieMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(operateurCompetenceCourant.dateSaisieMax)) {
									$log
											.debug("==dateSaisieMax "
													+ operateurCompetenceCourant.dateSaisieMax);

									if (operateurCompetenceCourant.dateSaisieMax != "") {
										newdateSaisieMaxFormat = formattedDate(operateurCompetenceCourant.dateSaisieMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								// $log.info("-- feuilleCourante" +
								// JSON.stringify($scope.feuilleCourante, null,
								// " ") );
								$log
										.info("** ** operateurCompetenceCourant ** ** "
												+ JSON
														.stringify(
																operateurCompetenceCourant,
																null, "  "));

								if (action != null) {
									var url;
									if (action == 1) {
										// Operateur competence
										url = baseUrlGpao
												+ "/report/operateurCompetence?dateSaisieMin="
												+ newdateSaisieMinFormat
												+ "&dateSaisieMax="
												+ newdateSaisieMaxFormat
												+ "&matricule="
												+ operateurCompetenceCourant.matricule
												+ "&type=pdf";
									} else if (action == 2) {
										// Historique
										url = baseUrlGpao
												+ "/report/operateurHistorique?dateSaisieMin="
												+ newdateSaisieMinFormat
												+ "&dateSaisieMax="
												+ newdateSaisieMaxFormat
												+ "&matricule="
												+ operateurCompetenceCourant.matricule
												+ "&type=pdf";
									}

									$log.info("URL " + url);
									// Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}
							}

						} ]);

// OF
app
		.controller(
				'GpaoReportingOFCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'baseUrlGpao',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter, $log, downloadService,
								baseUrlGpao, baseUrlGc, baseUrl) {

							$scope.ListClientOFCache = [];
							$scope.listChaine = [];
							$scope.listStatut = [];

							$scope.listeStatus = function() {
								$http.get(baseUrlGpao + "/statut/all").success(
										function(dataProduit) {
											$scope.listStatut = dataProduit;
											// //$log.debug(" *
											// $scope.listProduit : "+
											// $scope.listProduitDrop.length);
										});
							}
							$scope.listeStatus();

							$scope.listeProduit = function() {
								$http
										.get(baseUrl + "/produit/all")
										.success(
												function(dataProduit) {
													$scope.listProduitDrop = dataProduit;
													// //$log.debug(" *
													// $scope.listProduit : "+
													// $scope.listProduitDrop.length);
												});
							}
							$scope.listeProduit();

							// Liste des ClientCommandeVenteCache
							$scope.listeClientOrdreFabricationCache = function() {
								$http
										.get(
												baseUrlGpao
														+ "/gestionProduitAOCache/listeClientCache")
										.success(
												function(dataClientCache) {
													$scope.ListClientOFCache = dataClientCache;
												});
							}
							$scope.listeClientOrdreFabricationCache();

							// annuler Recherche
							$scope.annulerOFAjout = function() {

								$scope.modePdf = "notActive";

								$scope.ordreFabricationCourant = {
									"vNumero" : "",
									"dateIntroductionMin" : "",
									"dateIntroductionMax" : "",
									"partieInteresseId" : "",
									"produitId" : "",
									"statutId" :"",
									"dateLivraisonMin" : "",
									"dateLivraisonMax" : ""
								};

								$scope.rechercheOFForm.$setPristine();
							}

							// generer rapport de tous les bons de sortie. mode
							// : List
							// conversion date en String
							function formattedDate(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

								if (month.length < 2)
									month = '0' + month;
								if (day.length < 2)
									day = '0' + day;
								return [ year, month, day ].join('-');
							}

							$scope.downloadAllOrdreFabrication = function(
									feuilleCourante, action) {
								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(feuilleCourante.dateIntroductionMin)) {
									$log
											.debug("==dateIntroductionMin "
													+ feuilleCourante.dateIntroductionMin);

									if (feuilleCourante.dateIntroductionMin != "") {
										newdateSaisieMinFormat = formattedDate(feuilleCourante.dateIntroductionMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(feuilleCourante.dateIntroductionMax)) {
									$log
											.debug("==dateIntroductionMax "
													+ feuilleCourante.dateIntroductionMax);

									if (feuilleCourante.dateIntroductionMax != "") {
										newdateSaisieMaxFormat = formattedDate(feuilleCourante.dateIntroductionMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMax Undefined");
								}

								var newdateLivMinFormat = "";
								if (angular
										.isDefined(feuilleCourante.dateLivraisonMin)) {
									$log.debug("==dateLivraisonMin "
											+ feuilleCourante.dateLivraisonMin);

									if (feuilleCourante.dateLivraisonMin != "") {
										newdateLivMinFormat = formattedDate(feuilleCourante.dateLivraisonMin);
									} else {
										newdateLivMinFormat = "";
									}
								} else {
									$log
											.debug("==newdateLivMinFormat Undefined");
								}

								var newdateLivMaxFormat = "";
								if (angular
										.isDefined(feuilleCourante.dateLivraisonMax)) {
									$log.debug("==dateLivraisonMax "
											+ feuilleCourante.dateLivraisonMax);

									if (feuilleCourante.dateLivraisonMax != "") {
										newdateLivMaxFormat = formattedDate(feuilleCourante.dateLivraisonMax);
									} else {
										newdateLivMaxFormat = "";
									}
								} else {
									$log
											.debug("==newdateLivMaxFormat Undefined");
								}

								$log.info("feuilleCouranteOF "
										+ JSON.stringify(feuilleCourante, null,
												"  "));
								if (action != null) {
									var url;
									if (action == 2) {
										// Comptage
										url = baseUrlGpao
												+ "/report/recapProduction?partieInteresseId="
												+ feuilleCourante.partieInteresseId
												+ "&dateIntroductionMin="
												+ newdateSaisieMinFormat
												+ "&dateIntroductionMax="
												+ newdateSaisieMaxFormat
												+ "&dateLivraisonMin="
												+ newdateLivMinFormat
												+ "&dateLivraisonMax="
												+ newdateLivMaxFormat
												+"&produitId="
												+feuilleCourante.produitId
												+"&statutId="
												+feuilleCourante.statutId
												+ "&type=pdf";
									}

									// Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}
							}

						} ]);

app
		.controller(
				'GpaoReportingAleaCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'baseUrlGpao',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter, $log, downloadService,
								baseUrlGpao, baseUrlGc, baseUrl) {

							$scope.listChaine = [];
							// listChaines
							$scope.listeChaines = function() {
								$http.get(baseUrlGpao + "/chaine/all").success(
										function(dataProduit) {
											$scope.listChaine = dataProduit;
										});
							}
							$scope.listeChaines();

							$scope.listAleas = function() {
								$http.get(baseUrlGpao + "/aleas/all").success(
										function(data) {
											$scope.listAleas = data;

										});
							}
							$scope.listAleas();
							
							// Liste des matricules
							$scope.listeMatricule = function() {
								$http
										.get(
												baseUrlGpao
														+ "/feuilleSaisie/listPersonnel")
										.success(function(dataProduit) {
											$scope.listMatricule = dataProduit;
										});
							}
							$scope.listeMatricule();


							$scope.ListClientOFCache = [];
							$scope.listChaine = [];
							$scope.listStatut = [];
							$scope.aleaCourant = {
									matricule:"",
									chaineId:"",
									aleaId : "",
									dateSaisieMin :"",
									dateSaisieMax : ""
							};

							// annuler Recherche
							$scope.annulerAjout = function() {

								$scope.modePdf = "notActive";

								$scope.aleaCourant = {
										matricule:"",
										chaineId:"",
										aleaId : "",
										dateSaisieMin :"",
										dateSaisieMax : ""
								};

								$scope.rechercheAleaForm.$setPristine();
							}

							// generer rapport de tous les bons de sortie. mode
							// : List
							// conversion date en String
							function formattedDate(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

								if (month.length < 2)
									month = '0' + month;
								if (day.length < 2)
									day = '0' + day;
								return [ year, month, day ].join('-');
							}

							$scope.downloadAlea = function(
									aleaCourant) {
								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(aleaCourant.dateSaisieMin)) {
									$log
											.debug("==dateIntroductionMin "
													+ aleaCourant.dateSaisieMin);

									if (aleaCourant.dateSaisieMin != "") {
										newdateSaisieMinFormat = formattedDate(aleaCourant.dateSaisieMin);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log
											.debug("==dateSaisieMax Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(aleaCourant.dateSaisieMax)) {
									$log
											.debug("==dateIntroductionMax "
													+ aleaCourant.dateSaisieMax);

									if (aleaCourant.dateSaisieMax != "") {
										newdateSaisieMaxFormat = formattedDate(aleaCourant.dateSaisieMax);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log
											.debug("==dateIntroductionMax Undefined");
								}

								if(typeof aleaCourant.matricule == "undefined"){
									aleaCourant.matricule = "";
								}

								$log.info("obj "
										+ JSON.stringify(aleaCourant, null,
												"  "));
										// Comptage
										var url = baseUrlGpao
												+ "/report/aleaReport?dateMin="
												+ newdateSaisieMinFormat
												+ "&dateMax="
												+ newdateSaisieMaxFormat
												+ "&matricule="
												+ aleaCourant.matricule
												+ "&chaineId="
												+ aleaCourant.chaineId
												+ "&aleaId="
												+ aleaCourant.aleaId
												+ "&type=pdf";

									// Generate
									downloadService.download(url).then(
											function(success) {
												$log.debug('success : '
														+ success);
											}, function(error) {
												$log.debug('error : ' + error);
											});
								}

						} ]);
