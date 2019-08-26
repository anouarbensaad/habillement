'use strict'

angular
		.module('gpro.reponseClient',
				[ 'mwl.calendar', 'ui.bootstrap', 'ngAnimate', 'ngSanitize', 'amChartsDirective' ])
	
			 .controller('amChartsController', function ($scope, $http, baseUrlGpao ,baseUrl) {
			 	
			 	/*************************************critere de recherche ****************************************/
					$scope.listeAnneess = [];
					$scope.getListeAnnees = function(){
						
						for(var i=2016; i<2051 ; i++){
							$scope.listeAnneess.push(i);
						}
						
					}
					
					$scope.listSemainesDeb = [];
					$scope.getSemainesDeb = function(){
						
						for(var i=1; i<53 ; i++){
							$scope.listSemainesDeb.push(i);
						}
						
					}
					$scope.listSemainesFin = [];
					$scope.getSemainesFin = function(){
						
						for(var i=1; i<53 ; i++){
							$scope.listSemainesFin.push(i);
						}
						
					}
					// Liste des SiteeCache
					$scope.listeSitesPartieInteresseeCache = [] ;
					$scope.listeSitesPartieInteresseeCache = function() {
						$http
								.get(
										baseUrl
												+ "/gestionnaireCache/listeSitePartieInteresseeCache")
								.success(
										function(dataSiteCache) {
											$scope.listeSitePartieInteresseeCache = dataSiteCache;
											

										});
					}
					
					
					
					// liste des client 
					$scope.getListePartieInteresseeCache = function() {
						$http
								.get(
										baseUrl
												+ "/gestionnaireCache/listePartieInteresseeCache")
								.success(
										function(dataPICache) {
											$scope.listePartieInteresseeCache = dataPICache;
											$scope.rechercher({});
										});
					}
					$scope.getListePartieInteresseeCache();
/***********************************************************************************************************************/					
					// recherche capacite client 
					
					$scope.rechercheCapaciteClient = function(objectCourant){
						var planning ={
							
						}
						
						console.log("recherche liste capacite en cours ..");
						
						$http.post(
							baseUrlGpao+'//capaciteClient/rechercheMulticritere', objectCourant)

						

							.success(
										function(resultat) {
										$scope.myData = resultat.list;
										console.log("liste : "+JSON.stringify($scope.myData));
											$scope.repClientIsLoading = false;
											$scope.chartDataRepClient = [];
											//if (data.length > 0) {
											//Read hashmap content
											for (var i= 0; i < $scope.myData.length; i++) {
										  	for (var j= 0; j < $scope.myData[i].listSemaineCli.length; j++) {
										  		if(($scope.myData[i].listSemaineCli[j].semaine <= objectCourant.semainesFin  )&&( $scope.myData[i].listSemaineCli[j].semaine >= objectCourant.semainesDeb )){
										  			$scope.chartDataRepClient
														.push({
															
														"semaine" : $scope.myData[i].listSemaineCli[j].semaine,
												        "capaciteConfirme": $scope.myData[i].listSemaineCli[j].capaciteConfirme ,
												        "capacitePrevisionnelle":  $scope.myData[i].listSemaineCli[j].capacitePrevisionnelle ,
												        "capaciteTotal" : $scope.myData[i].listSemaineCli[j].capaciteTotale - (  $scope.myData[i].listSemaineCli[j].capacitePrevisionnelle + $scope.myData[i].listSemaineCli[j].capaciteConfirme  )
														})
										  		}
												

											}
										}
											//	$scope.chartDataRepClient.reverse();

												var chart;
												//var legend = new AmCharts.AmLegend();
													$scope.chart = new AmCharts.makeChart("chartdiv11", {
										    			"type": "serial",
														"theme": "none",
												    	"legend": {
												    		divId: "legenddiv"
												   		 },
												    	"dataProvider": $scope.chartDataRepClient,
												   		"valueAxes": [{
													        "stackType": "regular",
													        "axisAlpha": 0.3,
													        "gridAlpha": 0
												    	}],
												    "graphs": [{
												        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
												        "fillAlphas": 0.8,
												        "labelText": "[[value]]",
												        "lineAlpha": 0.3,
												        "title": " Confirmée",
												        "type": "column",
														"color": "#000000",
												        "valueField": "capaciteConfirme"
												    },{
												        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
												        "fillAlphas": 0.8,
												        "labelText": "[[value]]",
												        "lineAlpha": 0.3,
												        "title": " Prevue",
												        "type": "column",
												        "color": "#000000",
												        "valueField": "capacitePrevisionnelle"
												    
													}, {
												        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
												        "fillAlphas": 0.8,
												        "labelText": "[[value]]",
												        "lineAlpha": 0.3,
												        "title": "Libre",
												        "type": "column",
												        "color": "#000000",
												        "valueField": "capaciteTotal"
												    
													}],
												    "categoryField": "semaine",
												    "categoryAxis": {
												        "gridPosition": "start",
												        "axisAlpha": 0,
												        "gridAlpha": 0,
												        "position": "left"
												    },
												    "export": {
												    	"enabled": true 
												     }

												});
													$scope.chart.addLegend(legend, "legenddiv");
												});

					}
				// recherche capacite par atelier 	
			 	$scope.rechercheCapacite = function(objectCourant){
				var planning ={
					
				}
				
				console.log("recherche liste capacite en cours ..");
				
				$http.post(
					baseUrlGpao+'/capaciteGlobale/rechercheMulticritere', objectCourant)

				

					.success(
								function(resultat) {
								$scope.myData = resultat.list;
								console.log("liste : "+JSON.stringify($scope.myData));
									$scope.repClientIsLoading = false;
									$scope.chartDataRepClient = [];
									//if (data.length > 0) {
									//Read hashmap content
									for (var i= 0; i < $scope.myData.length; i++) {
								  	for (var j= 0; j < $scope.myData[i].listCapaciteSemaine.length; j++) {
								  		if(($scope.myData[i].listCapaciteSemaine[j].semaine <= objectCourant.semainesFin  )&&( $scope.myData[i].listCapaciteSemaine[j].semaine >= objectCourant.semainesDeb )){
								  			$scope.chartDataRepClient
												.push({
													
												"semaine" : $scope.myData[i].listCapaciteSemaine[j].semaine,
										        "capaciteConfirme": $scope.myData[i].listCapaciteSemaine[j].capaciteConfirme ,
										        "capacitePrevisionnelle":  $scope.myData[i].listCapaciteSemaine[j].capacitePrevisionnelle ,
										        "capaciteTotal" : $scope.myData[i].listCapaciteSemaine[j].capaciteTotale - (  $scope.myData[i].listCapaciteSemaine[j].capacitePrevisionnelle + $scope.myData[i].listCapaciteSemaine[j].capaciteConfirme  )
												})
								  		}
										

									}
								}
									//	$scope.chartDataRepClient.reverse();

										var chart;
										//var legend = new AmCharts.AmLegend();
											$scope.chart = new AmCharts.makeChart("chartdiv11", {
								    			"type": "serial",
												"theme": "none",
										    	"legend": {
										    		divId: "legenddiv"
										   		 },
										    	"dataProvider": $scope.chartDataRepClient,
										   		"valueAxes": [{
											        "stackType": "regular",
											        "axisAlpha": 0.3,
											        "gridAlpha": 0
										    	}],
										    "graphs": [{
										        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
										        "fillAlphas": 0.8,
										        "labelText": "[[value]]",
										        "lineAlpha": 0.3,
										        "title": " Confirmée",
										        "type": "column",
												"color": "#000000",
										        "valueField": "capaciteConfirme"
										    },{
										        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
										        "fillAlphas": 0.8,
										        "labelText": "[[value]]",
										        "lineAlpha": 0.3,
										        "title": " Prevue",
										        "type": "column",
										        "color": "#000000",
										        "valueField": "capacitePrevisionnelle"
										    
											}, {
										        "balloonText": "<b>[[title]]</b><br><span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>",
										        "fillAlphas": 0.8,
										        "labelText": "[[value]]",
										        "lineAlpha": 0.3,
										        "title": "Libre",
										        "type": "column",
										        "color": "#000000",
										        "valueField": "capaciteTotal"
										    
											}],
										    "categoryField": "semaine",
										    "categoryAxis": {
										        "gridPosition": "start",
										        "axisAlpha": 0,
										        "gridAlpha": 0,
										        "position": "left"
										    },
										    "export": {
										    	"enabled": true 
										     }

										});
											$scope.chart.addLegend(legend, "legenddiv");
										});

			}
			
			
				//$scope.rechercheCapacite() ;

				/** *********** Init ************** */

							$scope.init = function() {
								
								$scope.displayMode = "list";
								
								
								$scope.listeSitesPartieInteresseeCache();
								$scope.getListeAnnees();
								$scope.getSemainesDeb();
								$scope.getSemainesFin();
								
								
							}

							$scope.init();
			 		
			 });
									