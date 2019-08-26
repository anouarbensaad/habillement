'use strict'
angular
		.module('gpro.GpaoChart', [])
		.controller(
				'tableauBordGPAO',
				[
						'$scope',
						'$http',
						'$filter',
						'$rootScope',
						'$log',
						'downloadService',
						'baseUrlGc',
						'baseUrl',
						'baseUrlGpao',
						'baseUrlGgpro',
						function($scope, $http, $filter, $rootScope, $log,
								downloadService, baseUrlGc, baseUrl,
								baseUrlGpao, baseUrlGgpro) {

							$scope.listeClientCache = [];
							$scope.subMenu = "OF";
							
							var result = [];
							var result2 = [];

							$scope.nombreResultaRechercherProduit = 0;
							$scope.nombreResultaRechercherClient = 0;
							$scope.nombreResultaRechercherSFamille = 0;
							$scope.currentRequestParClient = {};
							$scope.currentRequestParProduit = {};
							$scope.currentRequestParSFamille = {};
							//								$scope.chartType = "client";

							//								//Client
							//								$scope.switchToChartClient = function(){
							//									$scope.chartType = "client";
							//									$scope.annulerProduit();
							//									$scope.annulerSFamille();
							//								};

							$scope.annulerClient = function() {
								$scope.currentRequestParClient = {};
								//									$scope.rechercherOFParClient({});
								$scope.requestParClientForm.$setPristine();
							};
							//								
							//								
							//								//Produit
							//								$scope.switchToChartProduit = function(){
							//									$scope.chartType = "produit";
							//									$scope.annulerClient();
							//									$scope.annulerSFamille();
							//								};
							//								
							$scope.annulerProduit = function() {
								$scope.currentRequestParProduit = {};
								//									$scope.rechercherOFParProduit({});
								$scope.requestParProduitForm.$setPristine();
							};
							//								
							//								//SFamille
							//								$scope.switchToChartSFamille = function(){
							//									$scope.chartType = "SFamille";
							//									$scope.annulerClient();
							//									$scope.annulerProduit();
							//
							//								};
							//								
							$scope.annulerSFamille = function() {
								$scope.currentRequestParSFamille = {};
								//									$scope.rechercherOFParSFamille({});
								$scope.requestParSFamilleForm.$setPristine();
							};

							$scope.annulerChaineParMatricule = function() {
								$scope.currentRequestChaineParMatricule = {};
								$scope.requestParChaineParMatriculeForm
										.$setPristine();
							};

							$scope.annulerMatriculeParJour = function() {
								$scope.currentRequestMatriculeParJour = {};
								$scope.requestParMatriculeParJourForm
										.$setPristine();
							};
							
							$scope.annulerChaineParJour = function() {
								$scope.currentRequestChaineParJour = {};
								$scope.requestParChaineParJourForm
										.$setPristine();
							};
							
							$scope.annulerRecapRendement = function() {
								$scope.currentRequestRecapRendement = {};
								$scope.requestRecapRendementForm
										.$setPristine();
							};
							
							$scope.annulerRecapProduction = function() {
								$scope.currentRequestRecapProduction = {};
								$scope.requestRecapProductionForm
										.$setPristine();
							};
							
							
							$scope.listChaine = [];
							//listChaines
							$scope.listeChaines = function() {
								$http.get(baseUrlGpao + "/chaine/all").success(
										function(dataProduit) {
											$scope.listChaine = dataProduit;
										});
							}
							$scope.listeChaines();

							$scope.ListClientOFCache = [];
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

							$scope.listeProduit = function() {

								$http
										.get(baseUrl + "/produit/all")
										.success(
												function(dataProduit) {
													$scope.listProduitDrop = dataProduit;

												});
							};

							$scope.listeProduit();
							$scope.listeClientOrdreFabricationCache();

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
							
							//Liste OF
							   $scope.listeOFDrop = [];
				                $scope.listeOF = function() {
				                    $http
				                        .get(baseUrlGpao + "/ordreFabrication/all")
				                        .success(
				                            function(dataProduit) {
				                                $scope.listeOFDrop = dataProduit;
				                            });
				                }
				                $scope.listeOF();

				                $scope.getListOperations=function(type, param){
				                	 switch(type){
				               	  case "OF" :var  webServiceMethod = "/getAllByOFId?OFId=" + param;
				               	  			break;
				               	  			
				               	  case "PRODUIT" : var webServiceMethod = "/getAllByProduit?produitId=" + param;
				               	  				   break;
				               	  				   
				               	  }
				               	  
				                
				              	  $scope.listeCodesOperations(webServiceMethod);
				              	 
				                }

//				                // Liste des codes
				                $scope.listeCodesOperations = function(webServiceMethod) {
				                    $http
				                        .get(baseUrlGpao + "/catalogue" + webServiceMethod)

				                        .success(
				                            function(data) {
				                            	$scope.listCodesOperations = [];
				                                $scope.listCodesOperations = data;
				                            });
				                }
				                
				                // Liste des codes
								$scope.listeCodesOperationsComptage = function() {
									$http
									   .get(baseUrlGpao + "/catalogue/getSwitchComptage?comptage=true" )

									   .success(
										   function(data) {
											   $scope.listeCodesOperationsComptage = [];
											   $scope.listeCodesOperationsComptage = data;
										   });
							   }
							   
								
				                $scope.listeCodesOperationsComptage();
				           
				               
				                
							//Switch Sub Menu Mode
							$scope.switchSubMenu=function(subMenu){
								$scope.subMenu = subMenu;
							}
							
							/*-----------------------Répartition des quantités par Client---------------------------------*/

							$scope.rechercherOFParClient = function(
									currentRequestParClient) {

								var result = [];
								var result2 = [];

								$http
										.post(
												baseUrlGpao
														+ "/ordreFabrication/rechercheOrdreFabricationMulticritere",
												currentRequestParClient)
										.success(
												function(data) {

													var c = [];
													if (data.nombreResultaRechercher != 0) {

														$scope.nombreResultaRechercherClient = data.nombreResultaRechercher;

														$scope.nombreResult = data.nombreResultaRechercher;
														for (var i = 0; i < data.nombreResultaRechercher; i++) {

															if (data.ordreFabricationValues[i].quantite != 0) {
																c
																		.push({
																			"client" : data.ordreFabricationValues[i].partieInterresAbreviation,
																			"qual" : data.ordreFabricationValues[i].quantite

																		})
															}
														}

													} else {

														$scope.nombreResultaRechercher = 0;

														$scope.nombreResulta = 0;
														c.push({

															"client" : "",
															"qual" : ""

														})
													}

													c
															.reduce(
																	function(
																			res,
																			value) {
																		if (!res[value.client]) {
																			res[value.client] = {

																				client : value.client,
																				qual : 0

																			};
																			result
																					.push(res[value.client])

																		}
																		res[value.client].qual += value.qual

																		return res;
																	}, {})

													var chart;
													var legend;

													chart = new AmCharts.AmPieChart();
													chart.dataProvider = result;
													chart.titleField = "client";
													chart.valueField = "qual";
													chart.outlineColor = "#FFFFFF";
													chart.outlineAlpha = 0.8;
													chart.outlineThickness = 2;
													chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";

													// this makes the chart 3D
													chart.depth3D = 15;
													chart.angle = 30;

													chart.export = {
														enabled : true,
														fileName : "gpro-consulting_chart"
													}
													//new attributes
															chart.labelsEnable = false,
															chart.autoMargins = false,
															chart.pullOutRadius = 30,

															// WRITE
															chart
																	.write("chartdiv");
													$scope.chartType = "client";
												})
							};

							//											$scope.rechercherOFParClient({});

							/*------------------------------------Répartition des quantités par Produit--------------------------------------------*/

							$scope.rechercherOFParProduit = function(
									currentRequestParProduit) {

								var result = [];
								var result2 = [];

								$http
										.post(
												baseUrlGpao
														+ "/ordreFabrication/rechercheOrdreFabricationMulticritere",
												currentRequestParProduit)
										.success(
												function(data) {

													var c = [];
													if (data.nombreResultaRechercher != 0) {

														$scope.nombreResultaRechercherProduit = data.nombreResultaRechercher;

														$scope.nombreResult = data.nombreResultaRechercher;
														for (var i = 0; i < data.nombreResultaRechercher; i++) {

															if (data.ordreFabricationValues[i].quantite != 0) {
																c
																		.push({

																			"produit" : data.ordreFabricationValues[i].produitReference,
																			"qual" : data.ordreFabricationValues[i].quantite

																		})
															}
														}

													} else {

														$scope.nombreResultaRechercherProduit = 0;

														$scope.nombreResulta = 0;
														c.push({

															"produit" : "",
															"qual" : ""

														})
													}

													c
															.reduce(
																	function(
																			res,
																			value) {
																		if (!res[value.produit]) {
																			res[value.produit] = {

																				produit : value.produit,
																				qual : 0

																			};
																			result2
																					.push(res[value.produit])

																		}
																		res[value.produit].qual += value.qual
																		return res;
																	}, {})

													var chart;
													var legend;

													chart = new AmCharts.AmPieChart();
													chart.dataProvider = result2;
													chart.titleField = "produit";
													chart.valueField = "qual";
													chart.outlineColor = "#FFFFFF";
													chart.outlineAlpha = 0.8;
													chart.outlineThickness = 2;
													chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";

													// this makes the chart 3D
													chart.depth3D = 15;
													chart.angle = 30;

													chart.export = {
														enabled : true,
														fileName : "gpro-consulting_chart"
													}
													//new attributes
															chart.labelsEnable = false,
															chart.autoMargins = false,
															chart.pullOutRadius = 35,

															// WRITE
															chart
																	.write("chartdiv2");
													$scope.chartTypeP = "produit";
												})
							};

							//												$scope.rechercherOFParProduit({});

							/*--------------------------------------Répartition des quantités par SFamille-----------------------------------------*/

							$scope.rechercherOFParSFamille = function(
									currentRequestParSFamille) {

								var result = [];
								var result2 = [];
								console
										.log("-------currentRequestParSFamille----"
												+ JSON
														.stringify(
																currentRequestParSFamille,
																null, " "));

								$http
										.post(
												baseUrlGpao
														+ "/ordreFabrication/rechercheOrdreFabricationMulticritere",
												currentRequestParSFamille)
										.success(
												function(data) {

													var c = [];
													if (data.nombreResultaRechercher != 0) {

														$scope.nombreResultaRechercherSFamille = data.nombreResultaRechercher;

														$scope.nombreResult = data.nombreResultaRechercher;
														for (var i = 0; i < data.nombreResultaRechercher; i++) {

															if (data.ordreFabricationValues[i].quantite != 0) {
																c
																		.push({
																			"sfamille" : data.ordreFabricationValues[i].produitSousFamilleDesignation,
																			"qual" : data.ordreFabricationValues[i].quantite

																		})
															}
														}

													} else {

														$scope.nombreResultaRechercherSFamille = 0;

														$scope.nombreResulta = 0;
														c.push({

															"client" : "",
															"qual" : ""

														})
													}

													c
															.reduce(
																	function(
																			res,
																			value) {
																		if (!res[value.sfamille]) {
																			res[value.sfamille] = {

																				sfamille : value.sfamille,
																				qual : 0

																			};
																			result
																					.push(res[value.sfamille])

																		}
																		res[value.sfamille].qual += value.qual
																		return res;
																	}, {})

													var chart;
													var legend;

													chart = new AmCharts.AmPieChart();
													chart.dataProvider = result;
													chart.titleField = "sfamille";
													chart.valueField = "qual";
													chart.outlineColor = "#FFFFFF";
													chart.outlineAlpha = 0.8;
													chart.outlineThickness = 2;
													chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
													// this makes the chart 3D
													chart.depth3D = 15;
													chart.angle = 30;
													chart.export = {
														enabled : true,
														fileName : "gpro-consulting_chart",
													}

													//new attributes
															chart.labelsEnable = false,
															chart.autoMargins = false,
															chart.pullOutRadius = 30,

															// WRITE
															chart
																	.write("chartdiv3");
													$scope.chartTypeS = "SFamille";

												})
							};

							/********************************** Rendement Chaine par Matricule   *********************************************/

							$scope.rendementParChaineIsLoading = false;

							//conversion date en String
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

							//init chartData
							$scope.chartData = null;
							$scope.rechercherRendementChaineParMatricule = function(
									currentRequestChaineParMatricule, type) {

								var dateDebut = "";
								if (angular
										.isDefined(currentRequestChaineParMatricule.vDateIntroductionDu)) {

									if (currentRequestChaineParMatricule.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestChaineParMatricule.vDateIntroductionDu);
									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestChaineParMatricule.vDateIntroductionAu)) {

									if (currentRequestChaineParMatricule.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestChaineParMatricule.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/feuilleSaisie/rendementChaineParMatricule?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&chaineId="
										+ currentRequestChaineParMatricule.chaineId

								//init chartData
								$scope.chartData = null;

								$scope.rendementParChaineIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.rendementParChaineIsLoading = false;
													$scope.chartData = [];
													//if (data.length > 0) {

														for (var j = 0; j < data.length; j++) {

															var element = data[j]
															//Read hashmap content
															for ( var i in element) {

																$scope.chartData
																		.push({
																			"matricule" : i,
																			"rendement" : element[i].toFixed(3)

																		})
															}
														}
//														$scope.chartData
//																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartData;
														//															                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "matricule";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "rendement";

														graphs.balloonText = "[[category]]: <b>[[value]]</b>";

														if (type == "COURBE") {
															graphs.type = "smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														} else if (type == "COLUMN") {
															graphs.type = "column";
															graphs.fillAlphas = 1;
														}

														chart.addGraph(graphs);
														// WRITE
														chart
																.write("chartdiv4");

												//	}

												})

							}

							/********************************** Rendement Individuel Par Jour / Matricule   *********************************************/

							$scope.rendementParMatriculeParJourIsLoading = false;

							//conversion date en String
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

							//init chartDataRendementParMatricule
							$scope.chartDataRendementParMatricule = null;

							$scope.rechercherRendementMatriculeParJour = function(
									currentRequestMatriculeParJour, type) {

								var dateDebut = "";
								if (angular
										.isDefined(currentRequestMatriculeParJour.vDateIntroductionDu)) {

									if (currentRequestMatriculeParJour.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestMatriculeParJour.vDateIntroductionDu);
									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestMatriculeParJour.vDateIntroductionAu)) {

									if (currentRequestMatriculeParJour.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestMatriculeParJour.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/feuilleSaisie/rendementMatriculeParJour?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&matricule="
										+ currentRequestMatriculeParJour.matricule

										//init chartDataRendementParMatricule
										$scope.chartDataRendementParMatricule = null;

								$scope.rendementParMatriculeParJourIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.rendementParMatriculeParJourIsLoading = false;
													$scope.chartDataRendementParMatricule = [];

															//Read hashmap content
															for ( var i in data) {

																$scope.chartDataRendementParMatricule
																		.push({
																			"jour" : formattedDateChart(i),
																			"rendement" :data[i].toFixed(3)

																		})

															}
//														$scope.chartDataRendementParMatricule
//																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataRendementParMatricule;
														//																														                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "jour";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "rendement";
														graphs.balloonText = "[[category]]: <b>[[value]]</b>";
														

										                if(type == "COURBE"){
										                	graphs.type="smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														}else if(type == "COLUMN"){
															graphs.type="column";
															graphs.fillAlphas = 1;
														}
										                
														chart.addGraph(graphs);

														// WRITE
														chart
																.write("chartdiv5");

												})
							}

							
							/********************************** Rendement Individuel Par Jour / Chaine   *********************************************/

							$scope.rendementParChaineParJourIsLoading = false;

							//conversion date en String
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

							//init chartData
							$scope.chartDataRendementParChaine = null;

							$scope.rechercherRendementChaineParJour = function(
									currentRequestChaineParJour, type) {

								var dateDebut = "";
								if (angular
										.isDefined(currentRequestChaineParJour.vDateIntroductionDu)) {

									if (currentRequestChaineParJour.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestChaineParJour.vDateIntroductionDu);
									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestChaineParJour.vDateIntroductionAu)) {

									if (currentRequestChaineParJour.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestChaineParJour.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/feuilleSaisie/rendementChaineParJour?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&chaineId="
										+ currentRequestChaineParJour.chaineId

								//init chartData
								$scope.chartDataRendementParChaine = null;

								$scope.rendementParChaineParJourIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.rendementParChaineParJourIsLoading = false;
													$scope.chartDataRendementParChaine = [];

															//Read hashmap content
															for ( var i in data) {

																$scope.chartDataRendementParChaine
																		.push({
																			"jour" : formattedDateChart(i),
																			"rendement" : data[i].toFixed(3)

																		})

															}
//														$scope.chartDataRendementParChaine
//																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataRendementParChaine;
														//																														                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "jour";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "rendement";
														graphs.balloonText = "[[category]]: <b>[[value]]</b>";
														

										                if(type == "COURBE"){
										                	graphs.type="smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														}else if(type == "COLUMN"){
															graphs.type="column";
															graphs.fillAlphas = 1;
														}
										                
														chart.addGraph(graphs);

														// WRITE
														chart
																.write("chartdiv6");

												})
							}

							
							
							/********************************** Production OF Operation par jour   *********************************************/

							$scope.productionOfOperationIsLoading = false;

							//conversion date en String
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

							//init chartData
							$scope.chartDataProductionOfOperation = null;
							$scope.currentRequestOfOperation={};
							$scope.rechercherProductionOfOperation = function(
									currentRequestOfOperation, type) {

								var dateDebut = "";
								if (angular
										.isDefined(currentRequestOfOperation.vDateIntroductionDu)) {

									if (currentRequestOfOperation.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestOfOperation.vDateIntroductionDu);

									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestOfOperation.vDateIntroductionAu)) {

									if (currentRequestOfOperation.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestOfOperation.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/detailFeuilleSaisie/productionParOperation?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&operationId="
										+ currentRequestOfOperation.operation

								//init chartData
								$scope.chartDataProductionOfOperation = null;

								$scope.productionOfOperationIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.productionOfOperationIsLoading = false;
													$scope.chartDataProductionOfOperation = [];

															//Read hashmap content
															for ( var i in data) {

																$scope.chartDataProductionOfOperation
																		.push({
																			"jour" : formattedDateChart(i),
																			"quantite" : data[i].toFixed(3)

																		})

															}
//														$scope.chartDataProductionOfOperation
//																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataProductionOfOperation;
														//																														                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "jour";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "quantite";
														graphs.balloonText = "[[category]]: <b>[[value]]</b>";
														

										                if(type == "COURBE"){
										                	graphs.type="smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														}else if(type == "COLUMN"){
															graphs.type="column";
															graphs.fillAlphas = 1;
														}
										                
														chart.addGraph(graphs);

														// WRITE
														chart
																.write("chartdiv7");

												})
							}
							
							/********************************** Production Produit Operation par jour   *********************************************/

							$scope.productionProduitOperationIsLoading = false;

							//conversion date en String
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

							//init chartData
							$scope.chartDataProductionProduitOperation = null;

							$scope.rechercherProductionProduitOperation = function(
									currentRequestOfOperation, type) {

								var dateDebut = "";
								if (angular
										.isDefined(currentRequestOfOperation.vDateIntroductionDu)) {

									if (currentRequestOfOperation.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestOfOperation.vDateIntroductionDu);

									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestOfOperation.vDateIntroductionAu)) {

									if (currentRequestOfOperation.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestOfOperation.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/detailFeuilleSaisie/productionParOperation?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&operationId="
										+ currentRequestOfOperation.operation

								//init chartData
								$scope.chartDataProductionProduitOperation = null;

								$scope.productionProduitOperationIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.productionProduitOperationIsLoading = false;
													$scope.chartDataProductionProduitOperation = [];

															//Read hashmap content
															for ( var i in data) {

																$scope.chartDataProductionProduitOperation
																		.push({
																			"jour" : formattedDateChart(i),
																			"quantite" : data[i].toFixed(3)

																		})

															}
//														$scope.chartDataProductionProduitOperation
//																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataProductionProduitOperation;
														//																														                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "jour";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "quantite";
														graphs.balloonText = "[[category]]: <b>[[value]]</b>";
														

										                if(type == "COURBE"){
										                	graphs.type="smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														}else if(type == "COLUMN"){
															graphs.type="column";
															graphs.fillAlphas = 1;
														}
										                
														chart.addGraph(graphs);

														// WRITE
														chart
																.write("chartdiv8");

												})
							}

							
							
							/********************************** Production Chaine Operation par jour   *********************************************/

							$scope.productionChaineOperationIsLoading = false;

							//conversion date en String
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

							//init chartData
							$scope.chartDataProductionChaineOperation = null;

							$scope.rechercherProductionChaineOperation = function(
									currentRequestOfOperation, type) {

								var dateDebut = "";
								if (angular
										.isDefined(currentRequestOfOperation.vDateIntroductionDu)) {

									if (currentRequestOfOperation.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestOfOperation.vDateIntroductionDu);

									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestOfOperation.vDateIntroductionAu)) {

									if (currentRequestOfOperation.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestOfOperation.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/detailFeuilleSaisie/productionParOperation?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&operationId="
										+ currentRequestOfOperation.operation
										+ "&chaineId="
										+ currentRequestOfOperation.chaineId

										console.log("requete" + requete);
								//init chartData
								$scope.chartDataProductionChaineOperation = null;

								$scope.productionChaineOperationIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.productionChaineOperationIsLoading = false;
													$scope.chartDataProductionChaineOperation = [];

															//Read hashmap content
															for ( var i in data) {

																$scope.chartDataProductionChaineOperation
																		.push({
																			"jour" : formattedDateChart(i),
																			"quantite" : data[i].toFixed(3)

																		})

															}
//														$scope.chartDataProductionChaineOperation
//																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataProductionChaineOperation;
														//																														                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "jour";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "quantite";
														graphs.balloonText = "[[category]]: <b>[[value]]</b>";
														

										                if(type == "COURBE"){
										                	graphs.type="smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														}else if(type == "COLUMN"){
															graphs.type="column";
															graphs.fillAlphas = 1;
														}
										                
														chart.addGraph(graphs);

														// WRITE
														chart
																.write("chartdiv9");

												})
							}

							
							
							/********************************** Recap de Rendement   *********************************************/

							$scope.recapRendementIsLoading = false;


							//init chartDataRecapRendement
							$scope.chartDataRecapRendement = null;
							$scope.currentRequestRecapRendement={};
							
							$scope.rechercherRecapRendement = function(
									currentRequestRecapRendement, type) {

								console.log("currentRequestRecapRendement" + currentRequestRecapRendement);
								var dateDebut = "";
								if (angular
										.isDefined(currentRequestRecapRendement.vDateIntroductionDu)) {

									if (currentRequestRecapRendement.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestRecapRendement.vDateIntroductionDu);
									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestRecapRendement.vDateIntroductionAu)) {

									if (currentRequestRecapRendement.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestRecapRendement.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/feuilleSaisie/recapRendementChaine?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin

								//init chartDataRecapRendement
								$scope.chartDataRecapRendement = null;

								$scope.recapRendementIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.recapRendementIsLoading = false;
													$scope.chartDataRecapRendement = [];
													//if (data.length > 0) {

														for (var j = 0; j < data.length; j++) {

															var element = data[j]
															//Read hashmap content
															for ( var i in element) {

																$scope.chartDataRecapRendement
																		.push({
																			"chaine" : i,
																			"rendement" : element[i].toFixed(3)

																		})
															}
														}
														$scope.chartDataRecapRendement
																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataRecapRendement;
														//															                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "chaine";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "rendement";

														graphs.balloonText = "[[category]]: <b>[[value]]</b>";

														if (type == "COURBE") {
															graphs.type = "smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														} else if (type == "COLUMN") {
															graphs.type = "column";
															graphs.fillAlphas = 1;
														}

														chart.addGraph(graphs);
														// WRITE
														chart
																.write("chartdiv10");

												//	}

												})

							}
							
							

							/********************************** Recap de production   *********************************************/

							$scope.recapProductionIsLoading = false;


							//init chartDataRecapProduction
							$scope.chartDataRecapProduction = null;
							$scope.currentRequestRecapProduction={};
							
							$scope.rechercherRecapProduction = function(
									currentRequestRecapProduction, type) {

								console.log("currentRequestRecapProduction" + currentRequestRecapProduction);
								var dateDebut = "";
								if (angular
										.isDefined(currentRequestRecapProduction.vDateIntroductionDu)) {

									if (currentRequestRecapProduction.vDateIntroductionDu != "") {
										dateDebut = formattedDate(currentRequestRecapProduction.vDateIntroductionDu);
									} else {
										dateDebut = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var dateFin = "";
								if (angular
										.isDefined(currentRequestRecapProduction.vDateIntroductionAu)) {

									if (currentRequestRecapProduction.vDateIntroductionAu != "") {
										dateFin = formattedDate(currentRequestRecapProduction.vDateIntroductionAu);
									} else {
										dateFin = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var requete = baseUrlGpao
										+ "/detailFeuilleSaisie/recapProductionParChaine?"
										+ "dateDebut="
										+ dateDebut
										+ "&dateFin="
										+ dateFin
										+ "&operationId="
										+ currentRequestRecapProduction.operation

								//init chartDataRecapProduction
								$scope.chartDataRecapProduction = null;

								$scope.recapProductionIsLoading = true;

								$http
										.post(requete)
										.success(
												function(data) {

													$scope.recapProductionIsLoading = false;
													$scope.chartDataRecapProduction = [];
													//if (data.length > 0) {
													//Read hashmap content
													for ( var i in data) {

														$scope.chartDataRecapProduction
																.push({
																	"chaine" : i,
																	"production" : data[i]

																})

													}
														$scope.chartDataRecapProduction
																.reverse();

														var chart;
														var legend;

														chart = new AmCharts.AmSerialChart();
														chart.dataProvider = $scope.chartDataRecapProduction;
														//															                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
														// this makes the chart 3D
														chart.depth3D = 15;
														//chart.angle = 30;
														chart.export = {
															enabled : true,
															fileName : "gpro-consulting_chart",
														}
														chart.categoryField = "chaine";
														var graphs = new AmCharts.AmGraph();
														graphs.valueField = "production";

														graphs.balloonText = "[[category]]: <b>[[value]]</b>";

														if (type == "COURBE") {
															graphs.type = "smoothedLine";
															graphs.fillAlphas = 0;
															graphs.lineThickness = 2;
															graphs.bullet ="round";
															graphs.bulletSize= 8;	
														} else if (type == "COLUMN") {
															graphs.type = "column";
															graphs.fillAlphas = 1;
														}

														chart.addGraph(graphs);
														// WRITE
														chart
																.write("chartdiv11");

												//	}

												})

							}
							
							
							/*-------------------------------------------------------Reporting-----------------------------------------------------------*/

							//conversion date en String
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
							
							function formattedDateChart(date) {
								var d = new Date(date), month = ''
										+ (d.getMonth() + 1), day = ''
										+ d.getDate(), year = d.getFullYear();

//								if (month.length < 2)
//									month = '0' + month;
//								if (day.length < 2)
//									day = '0' + day;
								year = year.toString();
								year = year[2]+ year[3];
								return [ year, month, day ].join('-');
							}

							//downloadQteParClient
							$scope.downloadQteParClient = function(
									currentRequestParClient) {
								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(currentRequestParClient.vDateIntroductionDu)) {
									$log
											.debug("==dateSaisieMin "
													+ currentRequestParClient.vDateIntroductionDu);

									if (currentRequestParClient.vDateIntroductionDu != "") {
										newdateSaisieMinFormat = formattedDate(currentRequestParClient.vDateIntroductionDu);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(currentRequestParClient.vDateIntroductionAu)) {
									$log
											.debug("==dateSaisieMax "
													+ currentRequestParClient.vDateIntroductionAu);

									if (currentRequestParClient.vDateIntroductionAu != "") {
										newdateSaisieMaxFormat = formattedDate(currentRequestParClient.vDateIntroductionAu);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								var url;
								//Operateur competence
								url = baseUrlGpao
										+ "/report/qteParClientReport?dateMin="
										+ newdateSaisieMinFormat + "&dateMax="
										+ newdateSaisieMaxFormat + "&type=pdf";
								//Generate
								downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
										}, function(error) {
											$log.debug('error : ' + error);
										});
							}

							/*--------------------------------downloadQteParProduit--------------------------------------------------*/
							$scope.downloadQteParProduit = function(
									currentRequestParProduit) {
								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(currentRequestParProduit.vDateIntroductionDu)) {
									$log
											.debug("==dateSaisieMin "
													+ currentRequestParProduit.vDateIntroductionDu);

									if (currentRequestParProduit.vDateIntroductionDu != "") {
										newdateSaisieMinFormat = formattedDate(currentRequestParProduit.vDateIntroductionDu);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(currentRequestParProduit.vDateIntroductionAu)) {
									$log
											.debug("==dateSaisieMax "
													+ currentRequestParProduit.vDateIntroductionAu);

									if (currentRequestParProduit.vDateIntroductionAu != "") {
										newdateSaisieMaxFormat = formattedDate(currentRequestParProduit.vDateIntroductionAu);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								//											                  var clientId = "";
								//											                  
								//											                  if(angular.isDefined(currentRequestParProduit.clientId)){
								//												                    $log.debug("==clientId "+currentRequestParProduit.clientId);
								//												                    
								//												                    if(currentRequestParProduit.clientId != ""){
								//													                      clientId = currentRequestParProduit.clientId;
								//													                    }else{
								//													                    	clientId = "";
								//													                    }
								//												                  }else{
								//												                    $log.debug("==clientId Undefined");
								//												                  }

								$log
										.info("** ** currentRequestParProduit ** ** "
												+ JSON
														.stringify(
																currentRequestParProduit,
																null, "  "));

								var url;
								//Operateur competence
								url = baseUrlGpao
										+ "/report/qteParProduitReport?dateMin="
										+ newdateSaisieMinFormat + "&dateMax="
										+ newdateSaisieMaxFormat + "&clientId="
										+ currentRequestParProduit.clientId
										+ "&type=pdf";

								$log.info("---URL---- " + url);
								//Generate
								downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
										}, function(error) {
											$log.debug('error : ' + error);
										});
							}

							/*--------------------------------downloadQteParSFamille--------------------------------------------------*/
							$scope.downloadQteParSFamille = function(
									currentRequestParSFamille) {

								$log.debug("=====downloadQteParSFamille ");

								var newdateSaisieMinFormat = "";
								if (angular
										.isDefined(currentRequestParSFamille.vDateIntroductionDu)) {
									$log
											.debug("==dateSaisieMin "
													+ currentRequestParSFamille.vDateIntroductionDu);

									if (currentRequestParSFamille.vDateIntroductionDu != "") {
										newdateSaisieMinFormat = formattedDate(currentRequestParSFamille.vDateIntroductionDu);
									} else {
										newdateSaisieMinFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMin Undefined");
								}

								var newdateSaisieMaxFormat = "";
								if (angular
										.isDefined(currentRequestParSFamille.vDateIntroductionAu)) {
									$log
											.debug("==dateSaisieMax "
													+ currentRequestParSFamille.vDateIntroductionAu);

									if (currentRequestParSFamille.vDateIntroductionAu != "") {
										newdateSaisieMaxFormat = formattedDate(currentRequestParSFamille.vDateIntroductionAu);
									} else {
										newdateSaisieMaxFormat = "";
									}
								} else {
									$log.debug("==dateSaisieMax Undefined");
								}

								//											                  var clientId = "";
								//											                  
								//											                  if(angular.isDefined(currentRequestParSFamille.clientId)){
								//												                    $log.debug("==clientId "+currentRequestParSFamille.clientId);
								//												                    
								//												                    if(currentRequestParSFamille.clientId != ""){
								//													                      clientId = currentRequestParSFamille.clientId;
								//													                    }else{
								//													                    	clientId = "";
								//													                    }
								//												                  }else{
								//												                    $log.debug("==clientId Undefined");
								//												                  }

								$log
										.info("** ** currentRequestParSFamille ** ** "
												+ JSON
														.stringify(
																currentRequestParSFamille,
																null, "  "));

								var url;
								//Operateur competence
								url = baseUrlGpao
										+ "/report/qteParSFamilleReport?dateMin="
										+ newdateSaisieMinFormat + "&dateMax="
										+ newdateSaisieMaxFormat + "&clientId="
										+ currentRequestParSFamille.clientId
										+ "&type=pdf";

								$log.info("---URL---- " + url);
								//Generate
								downloadService.download(url).then(
										function(success) {
											$log.debug('success : ' + success);
										}, function(error) {
											$log.debug('error : ' + error);
										});
							}

							/***********************fin Reporting*******************************/

						} ]);
