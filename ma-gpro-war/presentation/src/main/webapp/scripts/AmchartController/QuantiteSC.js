'use strict'
	angular
			.module('gpro.quantiteScChart', [])
			.controller(
					'QuantiteSC',
					[
							'$scope',
							'$http',
							'$filter',
							'$rootScope',
							'downloadService',
							'baseUrlGc',
							'baseUrlGs',
							'baseUrl',
							'baseUrlFE',
							function($scope, $http, $filter,$rootScope,downloadService, baseUrlGc, baseUrlGs, baseUrl, baseUrlFE) {
									
								$scope.Listesousfamille = function() {
									$http
											.get(baseUrl + "/gestionnaireCache/listeSousFamilleArticleCache")
											.success(
													function(data) {
														console.log(data);
														$scope.Listesousfamille = data;

													});
								}
								$scope.Listesousfamille();
								$scope.Listearticle = function() {
									$http
											.get(baseUrl + "/gestionnaireCache/listeTypeArticleCache")
											.success(
													function(data) {
														console.log(data);
														$scope.Listearticle = data;

													});
								}
								$scope.Listearticle();
									 $scope.ch= function() {
										 var result = [];
										 var resultm = [];
										 var resulta = [];
										 $http.post(baseUrlFE + "/#/ChartGraphiqueGSS", {							             								             
								         }).
								         	success(function(data, status, headers) {
								            	console.log($scope.datefrom);
								            	console.log($scope.dateto);
								            	console.log($scope.d);
								            	
												if($scope.d==1)
												{
													datachart=result;
												}
												else if($scope.d==2)
													{
													datachart=resultm;
													}
												else if($scope.d==3)
												{
												datachart=resulta;
												}
												else
												{
													datachart=result;
												}
								            		obj.push({
								            			 "typeArticle":"TISSU" ,
														    "typeMouvement": "SORTIE",
														    "sousFamilleId":$scope.sousFamilleId,
														    "articleId":$scope.articleId,
															"dateFrom" : $scope.datefrom,
													        "dateTo" : $scope.dateto
											            
									            	})
								            	
								               });
										 var datachart="";
									
										 var obj = JSON.stringify({
											 "typeArticle":"TISSU" ,
											    "typeMouvement": "SORTIE",
											    "sousFamilleId":$scope.sousFamilleId,
											    "articleId":$scope.articleId,
												"dateFrom" : $scope.datefrom,
										        "dateTo" : $scope.dateto
											
											 
											  });
										 
									
											 
									console.log(obj);

									var res = $http.post(baseUrlGs + "/variationChart/rechercheMulticritere", obj);
									res.success(function(data, status, headers, config) {
										console.log("sssss");
										console.log(data);
										//return data;
										var c=[];
									     var testm=[];
									     var testa=[];
										if(data.nombreResultaRechercher != 0){
										$scope.nombreResulta=data.nombreResultaRechercher;
										for (var i = 0; i < data.nombreResultaRechercher; i++) {
											var varDate = new Date(data.listVariation[i].date);
											var d,m;
											if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
											if((varDate.getDate() + 1)<10){d='0'+(varDate.getDate() + 1);}else{d=(varDate.getDate() + 1);}

											var date = varDate.getFullYear()  +'-'+ m +'-'+ d;
											var datem=varDate.getFullYear()  +'-'+ m;
											var datea=varDate.getFullYear();
											testm.push({

												"date": datem,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#3F51B5"
													    })	
											testa.push({

												"date": datea,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#3F51B5"
													    })	
											c.push({

												"date": date,

												"qual": data.listVariation[i].quantiteReelle,
												"color":"#3F51B5"
													    })	
											}}
										else{
											$scope.nombreResulta=0;
											c.push({

												"date": "",
												"qual": "",
													"color":"#FFFFFF"
												 })	
										}
				//******************************** par jour **************************************************				
										
										c.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0
										          
										        };
										        result.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										console.log(result);
				//**********************************************************************************	
										
				//**********************************par mois************************************************	
										
										testm.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0
										          
										        };
										        resultm.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										console.log(testm);
										console.log(resultm);
										
				//**********************************************************************************		
				//**********************************par ans************************************************	
									
										testa.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0
										          
										        };
										        resulta.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										console.log(testa);
										console.log(resulta);
										
				//**********************************************************************************	
										
										
										var chart;
										chart = new AmCharts.AmSerialChart();
						                chart.dataProvider = datachart;
						                chart.categoryField = "date";
						                chart.startDuration = 1;

						                // AXES
						                // category
						                var categoryAxis = chart.categoryAxis;
						                categoryAxis.labelRotation = 45; // this line makes category values to be rotated
						                categoryAxis.gridAlpha = 0;
						                categoryAxis.fillAlpha = 1;
						                categoryAxis.fillColor = "#FAFAFA";
						                categoryAxis.gridPosition = "start";
						                //categoryAxis.parseDates = true;
							    		//categoryAxis.minPeriod = "DD";
							    		//categoryAxis.equalSpacing = true;

						                // value
						                var valueAxis = new AmCharts.ValueAxis();
						                valueAxis.dashLength = 5;
						                valueAxis.title = "Quantite Reelle";
						                valueAxis.axisAlpha = 0;
						                chart.addValueAxis(valueAxis);

						                // GRAPH
						                var graph = new AmCharts.AmGraph();
						                graph.valueField = "qual";
						                graph.colorField = "color";
						                graph.balloonText = "<b>[[category]]: [[value]]</b>";
						                graph.type = "column";
						                graph.lineAlpha = 0;
						                graph.fillAlphas = 1;
						                chart.addGraph(graph);

						                // CURSOR
						                var chartCursor = new AmCharts.ChartCursor();
						                chartCursor.cursorAlpha = 0;
						                chartCursor.zoomable = false;
						                chartCursor.categoryBalloonEnabled = false;
						                chart.addChartCursor(chartCursor);

						                chart.creditsPosition = "top-right";

						                // WRITE
						                chart.write("chartdiv");
										
									});
									res.error(function(data, status, headers, config) {
										
										//alert( "failure message: " );
									});
									 }
									 
									 
									 
								
									
							
					}]);

		
