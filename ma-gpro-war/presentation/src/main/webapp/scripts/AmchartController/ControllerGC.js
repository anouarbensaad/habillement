'use strict'
	angular
			.module('gpro.gc_chart', [])
			.controller(
					'ControllerGC',
					[
							'$scope',
							'$http',
							'$filter',
							'$rootScope',
							'downloadService',
							'baseUrlGc',
							'baseUrlGs',
							'baseUrl',
							function($scope, $http, $filter,$rootScope,downloadService, baseUrlGc, baseUrlGs, baseUrl) {
								
								$scope.listeClientCache = [];
								
								var result = [];
								var result2 = [];
								
								$scope.nombreResultaRechercherProduit = 0;
								$scope.nombreResultaRechercherClient = 0;
							
								$scope.chartType = "produit";
								
								$scope.switchToChartClient = function(){
									$scope.chartType = "client";
									$scope.annulerProduit();
								};
								
								$scope.switchToChartProduit = function(){
									$scope.chartType = "produit";
									$scope.annulerProduit();
								};
								
								$scope.annulerProduit = function(){
									$scope.currentRequestParProduit = {};
									$scope.rechercherProduitCommandeVenteParProduit({});
									$scope.requestParProduitForm.$setPristine();
								};
								
								$scope.annulerClient = function(){
									$scope.currentRequestParClient = {};
									$scope.rechercherProduitCommandeVenteParClient({});
									$scope.requestParClientForm.$setPristine();
								};
								
								$scope.listeClientCache = function() {
									$http
											.get(baseUrlGc+	"/gestionCommercialCache/listeClientCache")
											.success(
													function(dataC) {
														$scope.listeClientCache = dataC;

													});
								};
								$scope.listeClientCache();
								
								$scope.listeProduit = function() {
									
									$http.get(baseUrl+"/produit/all")
												.success(
													function(dataProduit) {
														$scope.listProduitDrop = dataProduit;
														
													});
								};
								
								$scope.listeProduit();
									

								$scope.rechercherProduitCommandeVenteParProduit = function(currentRequestParProduit) {
										
									var result = [];
									var result2 = [];
									
									$http.post(baseUrlGc + "/produitCommandeChart/rechercheMulticritere", currentRequestParProduit)
											.success(
														function(data) {
															console.log("-------rechercherProduitCommandeVenteParProduit :data----"+JSON.stringify(data,null," "));

															var c=[];
															if(data.nombreResultaRechercher != 0)
															{
																
																$scope.nombreResultaRechercherProduit = data.nombreResultaRechercher;
																
																$scope.nombreResult=data.nombreResultaRechercher;
																	for (var i = 0; i < data.nombreResultaRechercher; i++)
																	 {
																		if(data.listProduitCommandeValue[i].quantite != 0)
																	         { 
																				 c.push({
																						"client": data.listProduitCommandeValue[i].clientAbreviation,
																						"qual": data.listProduitCommandeValue[i].quantite
																						 })	
																				} 
																   }
																	
																	console.log("-------rechercherProduitCommandeVenteParProduit : c.push----"+JSON.stringify(c,null," "));

															}
															else 
															{
																
																$scope.nombreResultaRechercherProduit = 0;
																
																$scope.nombreResulta=0;
																c.push({

																	"client": "",
																	"qual": ""
																	 })	
															}
			
															
															c.reduce(function (res, value) {
															    if (!res[value.client]) {
															        res[value.client] = {
															            
															        	client: value.client,
															            qual: 0
															          
															        };
															        result.push(res[value.client])
																	console.log("------- result.push----"+JSON.stringify(result,null," "));

															    }
															    res[value.client].qual += value.qual
																console.log("------- res----"+JSON.stringify(res,null," "));

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
																		enabled: true,
																		fileName:"gpro-consulting_chart"
																	}
																
												                // WRITE
												                chart.write("chartdiv");
														
													})};
													$scope.rechercherProduitCommandeVenteParProduit({});
										
	                         
	                         
	                         
								
								
								// Rechercher ProduitCommandeVente par client
								$scope.rechercherProduitCommandeVenteParClient = function(currentRequestParClient) {
									
									var result = [];
									var result2 = [];
									
									$http.post(baseUrlGc + "/produitCommandeChart/rechercheMulticritere", currentRequestParClient)
									.success(
											function(data) {
												
												var c=[];
												if(data.nombreResultaRechercher != 0)
												{
												
													
													$scope.nombreResultaRechercherClient = data.nombreResultaRechercher;
													
													$scope.nombreResult=data.nombreResultaRechercher;
														for (var i = 0; i < data.nombreResultaRechercher; i++)
														 {
																							
															if(data.listProduitCommandeValue[i].quantite != 0)
														         { 
																	 c.push({
							
																			"produit": data.listProduitCommandeValue[i].referenceProduit,
																			"qual": data.listProduitCommandeValue[i].quantite
																			
																			 })	
																	} 
													   }
														
														console.log("-------rechercherProduitCommandeVenteParClient : c.push----"+JSON.stringify(c,null," "));

												}
												else 
												{
													
													$scope.nombreResultaRechercherClient = 0;
													
													$scope.nombreResulta=0;
													c.push({

														"produit": "",
														"qual": ""
														
														 })	
												}

												
												c.reduce(function (res, value) {
												    if (!res[value.produit]) {
												        res[value.produit] = {
												            
												        	produit: value.produit,
												            qual: 0
												          
												        };
												        result2.push(res[value.produit])
														console.log("-------rechercherProduitCommandeVenteParClient : result2----"+JSON.stringify(result2,null," "));

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
															enabled: true,
															fileName:"gpro-consulting_chart"	
														}

									                // WRITE
									                chart.write("chartdiv2");
									            
											})};
											$scope.rechercherProduitCommandeVenteParClient({});
	                         
									} ]);

