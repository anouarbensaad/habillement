'use strict'
	angular
			.module('gpro.gcGpaoChart', [])
			.controller(
					'ControllerGPAO',
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
							function($scope, $http, $filter,$rootScope,$log, downloadService, baseUrlGc,baseUrl,baseUrlGpao,baseUrlGgpro) {
									
										$scope.chartType = "gpao1";
										
										$scope.currentRequestgpao1 = {};
										$scope.currentRequestgpao2 = {};
										$scope.currentRequestgpao3 = {};
										$scope.currentRequestgpao4 = {};
										$scope.currentRequestgpao5 = {};
										$scope.currentRequestgpaosj = {};
										$scope.currentRequestgpaovp = {};
										$scope.currentRequestgpaovr = {};
										
										$scope.reload = function()
										{
											
										   location.reload(); 
										   
										}
										
										$scope.switchToChartgpao1= function(){
											$scope.chartType = "gpao1";
											
											

										};
										
										$scope.switchToChartgpao2 = function(){
											$scope.chartType = "gpao2";
											
											
										};
										$scope.switchToChartgpao3 = function(){
											$scope.chartType = "gpao3";
											
											
										};
										$scope.switchToChartgpao4 = function(){
											$scope.chartType = "gpao4";
											
										};
										$scope.switchToChartgpao5 = function(){
											$scope.chartType = "gpao5";
										};
										$scope.switchToChartgpaoRapport = function(){
											$scope.chartType = "rapport";
											
										};
										$scope.switchToChartgpaoaff = function(){
											$scope.chartType = "gpaoaff";
											
											
										};
										$scope.switchToChartgpaovp = function(){
											$scope.chartType = "gpaovp";
										};
										$scope.switchToChartgpaovr = function(){
											$scope.chartType = "gpaovr";
										};
										
										
										
										
			
										
										$scope.annulergpao1 = function(){
											$scope.currentRequestgpao1 = {};
											$scope.gpao1({});
											$scope.requestGpao1Form.$setPristine();
										};
										
										$scope.annulergpao2 = function(){
											$scope.currentRequestgpao2 = {};
											$scope.gpao2({});
											$scope.requestGpao2Form.$setPristine();
										};
										$scope.annulergpao3 = function(){
											$scope.currentRequestgpao3 = {};
											$scope.gpao3({});
											$scope.requestGpao3Form.$setPristine();
										};
										$scope.annulergpao4 = function(){
											$scope.currentRequestgpao4 = {};
											$scope.gpao4({});
											$scope.requestGpao4Form.$setPristine();
										};
										$scope.annulergpao5 = function(){
											$scope.currentRequestgpao5 = [{}];
											$scope.gpao5({});
											$scope.requestGpao5Form.$setPristine();
										};
										$scope.annulergpaosj = function(){
											$scope.currentRequestgpaosj= {};
											$scope.gpaosj({});
											$scope.requestGpaosjForm.$setPristine();
										};
										$scope.annulergpaovp = function(){
											$scope.currentRequestgpaovp= {};
											$scope.gpaovp({});
											$scope.requestGpaovpForm.$setPristine();
										};
										$scope.annulergpaovr = function(){
											$scope.currentRequestgpaovr= {};
											$scope.gpaovr({});
											$scope.requestGpaovrForm.$setPristine();
										};
										
										  
										  $scope.chaineall =[];
										  $scope.chainegetall= function() {
												  $http.get(baseUrlGpao+'/chaine/all', {								             								             
											         }).
											         	success(function(data) {
										         		 $scope.chaineall = data;
														
											               });
										  }
										  $scope.chainegetall();
										  
										  $scope.chaineallfromrecherche =[];
										  $scope.chainegetallfromrecherche= function() {
												  $http.post(baseUrlGpao+'/production/rechercheMulticritere', {								             								             
											         }).
											         	success(function(data) {
											         		
											         		
											         		//console.log("----------------1");
											         		
										         		 $scope.chaineallfromrecherche = data.list;
										         		 														
											               });
										  }
										  $scope.chainegetallfromrecherche();
										  
										  $scope.listProduitDrop =[];

										  $scope.listeProduit = function() {
												
												$http.get(baseUrl+"/produit/all")
															.success(
																function(dataProduit) {
																	$scope.listProduitDrop = dataProduit;
																	
																});
											};
											
											$scope.listeProduit();
  
										  $scope.productionday =[];
										  $scope.productiongetday= function() {
										  $http.post(baseUrlGpao+'/production/rechercheMulticritere', {								             								             
									         }).
									         	success(function(data) {
									         		
									         		//console.log("----------------2");
									         		
									         		
									         		 var today = new Date();
										                var dd = today.getDate();
										                var mm = today.getMonth()+1; //January is 0!
										                var yyyy = today.getFullYear();

										                if(dd<10) {
										                    dd='0'+dd
										                } 

										                if(mm<10) {
										                    mm='0'+mm
										                } 

										                today = yyyy+'-'+mm+'-'+dd;
										                
										               
									         		for(var i=0;i<data.nombreResultaRechercher;i++)
									         		{
									         			var varDate = new Date(data.list[i].date);
									         			
														var d,m;
														if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
														if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}

														var daten = varDate.getFullYear()  +'-'+ m +'-'+ d;
														
									         			 
									         			if(daten==today)
									         				{
									         				
									         					$scope.productionday.push(data.list[i]);
									         				 
									         				}
									         			
									         		}
								         		
												
									               });
										  }
										  $scope.productiongetday();
										
										
										
										
										  $scope.list = {};
										  
										  $scope.liste= function() {
										  $http.post(baseUrlGpao+'/aBCArticleDetailEtapeJour/rechercheMulticritere', {								             								             
									         }).
									         	success(function(data, status, headers) {
									         		
									         		//console.log("----------------3");
									         		
								         		 $scope.list = data.list;
												
									               });
										  }
										  $scope.liste();

								
			                          $scope.gpao1= function(currentRequestgpao1,d) {
			                        	  
			                        	 // console.log(currentRequestgpao1);
			                        	  
			                        		//console.log("--currentRequestgpao1: "+JSON.stringify(currentRequestgpao1,null,"  ") ) ;
			                        	  
			                        	  
			                        	 // console.log(d);
												 var result = [];
												 var resultm = [];
												 var resulta = [];
												 var datachart=result;
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
								
									var urlBase=baseUrlGpao+"/aBCArticleDetailEtapeJour/rechercheMulticritere";
									var res = $http.post(urlBase, currentRequestgpao1);
									res.success(function(data, status, headers, config) {
										
										//console.log("----------------4");
										
										//console.log("sssss");
										//console.log(data);
										
										var c=[];
									     var testm=[];
									     var testa=[];
									     $scope.nombreResultaRecherchergpao1=data.nombreResultaRechercher;
										if(data.nombreResultaRechercher != 0){
										for (var i = 0; i < data.nombreResultaRechercher; i++) {
											var varDate = new Date(data.list[i].dateSaisie);
											var d,m;
											if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
											if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}

											var date = varDate.getFullYear()  +'-'+ m +'-'+ d;
											var datem=varDate.getFullYear()  +'-'+ m;
											var datea=varDate.getFullYear();
											testm.push({

												"date": datem,

												"qual": data.list[i].qte,
												"color":"#3F51B5"
													    })	
											testa.push({

												"date": datea,

												"qual":data.list[i].qte,
												"color":"#3F51B5"
													    })	
											c.push({

												"date": date,

												"qual": data.list[i].qte,
												"color":"#3F51B5"
													    })	
											}}
										
				//******************************** par jour **************************************************				
										
										c.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0,
										            color:"#2196F3"
										          
										        };
										        result.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
				//**********************************************************************************	
										
				//**********************************par mois************************************************	
										
										testm.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0,
										            color:"#2196F3"
										          
										        };
										        resultm.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										
										
				//**********************************************************************************		
				//**********************************par ans************************************************	
									
										testa.reduce(function (res, value) {
										    if (!res[value.date]) {
										        res[value.date] = {
										            
										            date: value.date,
										            qual: 0,
										            color:"#2196F3"
										          
										        };
										        resulta.push(res[value.date])
										    }
										    res[value.date].qual += value.qual
										    return res;
										}, {})
										
										
										
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
						               

						                // value
						                var valueAxis = new AmCharts.ValueAxis();
						                valueAxis.dashLength = 5;
						                valueAxis.title = "Quantite Production";
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
						                var today = new Date();
						                var dd = today.getDate();
						                var mm = today.getMonth()+1; //January is 0!
						                var yyyy = today.getFullYear();

						                if(dd<10) {
						                    dd='0'+dd
						                } 

						                if(mm<10) {
						                    mm='0'+mm
						                } 

						                today = mm+'/'+dd+'/'+yyyy;
						                
										chart.export = {
											enabled: true,
											position: "bottom-right",
												fileName:"V.SORTIE "+ today,
												
										}
										
										chart.initHC = false;
										chart.validateNow();
								        

						                // WRITE
						            
						                chart.write("chartdiv");
										
									});
									res.error(function(data, status, headers, config) {
										
										//alert( "failure message: " );
									});
									 }
			                          $scope.gpao1({});
			                          
			                          
			                      $scope.gpao2= function(currentRequestgpao2) {
			                    	  var result = [];
			                    	  var urlBase=baseUrlGpao+"/aBCArticleDetailEtapeJour/rechercheMulticritere";
										var res = $http.post(urlBase, currentRequestgpao2);
										res.success(function(data, status, headers, config) {
											//console.log("sssss");
											
											//console.log("----------------5");
											
											
											var c=[];
											$scope.nombreResultaRecherchergpao2=data.nombreResultaRechercher;
											if(data.nombreResultaRechercher != 0){
										
											for (var i = 0; i < data.nombreResultaRechercher; i++) {
												
												
												c.push({

													"lieu": data.list[i].lieuExcecution,

													"qual": data.list[i].qte,
													"color":"#3F51B5"
														    })	
												}}
											
					//******************************** par jour **************************************************				
											
											c.reduce(function (res, value) {
											    if (!res[value.lieu]) {
											        res[value.lieu] = {
											            
											            lieu: value.lieu,
											            qual: 0
											          
											        };
											        result.push(res[value.lieu])
											    }
											    res[value.lieu].qual += value.qual
											    return res;
											}, {})
											//console.log(result);
					//**********************************************************************************	
											
			
											
					//**********************************************************************************	
											
										 
											var chart;
											var legend;
											
										      chart = new AmCharts.AmPieChart();
								                chart.dataProvider = result;
								                chart.titleField = "lieu";
								                chart.valueField = "qual";
								                chart.outlineColor = "#FFFFFF";
								                chart.outlineAlpha = 0.8;
								                chart.outlineThickness = 2;
								                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
								                // this makes the chart 3D
								                chart.depth3D = 15;
								                chart.angle = 30;
								                
								                var today = new Date();
								                var dd = today.getDate();
								                var mm = today.getMonth()+1; //January is 0!
								                var yyyy = today.getFullYear();

								                if(dd<10) {
								                    dd='0'+dd
								                } 

								                if(mm<10) {
								                    mm='0'+mm
								                } 

								                today = mm+'/'+dd+'/'+yyyy;
							                if(dd<10) {
							                    dd='0'+dd
							                } 

							                if(mm<10) {
							                    mm='0'+mm
							                } 

							                today = mm+'/'+dd+'/'+yyyy;
							                
											chart.export = {
												enabled: true,
												position: "bottom-right",
													fileName:"V.SORTIE "+ today,
													
											}
											
											chart.initHC = false;
											chart.validateNow();
									        

							                // WRITE
							            
							                chart.write("chartdiv2");
											
										});
										res.error(function(data, status, headers, config) {
											
											//alert( "failure message: " );
										});
			                        	  
			                          }
			                      $scope.gpao2({});
			                      
			                      
			                      $scope.gpao3= function(currentRequestgpao3) {
						                    	  	var result = [];
						              			
						                    	  	var urlBase=baseUrlGpao+"/aBCArticleDetailEtapeJour/rechercheMulticritere";
													var res = $http.post(urlBase, currentRequestgpao3);
													res.success(function(data, status, headers, config) {
														
														//console.log("----------------6");
														
							         				//console.log("sssss");
							         				//console.log(data);
						         			
						         				var c=[];
						         				$scope.nombreResultaRecherchergpao3=data.nombreResultaRechercher;
						         				if(data.nombreResultaRechercher != 0){
						         				
						         				for (var i = 0; i < data.nombreResultaRechercher; i++) {
						         					
						         					
						         					c.push({
			
						         						"produit": data.list[i].produitAbrevation,
			
						         						"qual": data.list[i].qte,
						         						"color":"#3F51B5"
						         							    })	
						         					}}
						         				
						         //******************************** par jour **************************************************				
						         				
						         				c.reduce(function (res, value) {
						         				    if (!res[value.produit]) {
						         				        res[value.produit] = {
						         				            
						         				        	produit: value.produit,
						         				            qual: 0
						         				          
						         				        };
						         				        result.push(res[value.produit])
						         				    }
						         				    res[value.produit].qual += value.qual
						         				    return res;
						         				}, {})
						         				//console.log(result);
						         //**********************************************************************************	
						         				
			
						         				
						         //**********************************************************************************	
						         				
						         			 
						         				var chart;
						         				var legend;
						         				
						         			      chart = new AmCharts.AmPieChart();
						         	                chart.dataProvider = result;
						         	                chart.titleField = "produit";
						         	                chart.valueField = "qual";
						         	                chart.outlineColor = "#FFFFFF";
						         	                chart.outlineAlpha = 0.8;
						         	                chart.outlineThickness = 2;
						         	                chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
						         	                // this makes the chart 3D
						         	                chart.depth3D = 15;
						         	                chart.angle = 30;
						         	                
						         	                var today = new Date();
						         	                var dd = today.getDate();
						         	                var mm = today.getMonth()+1; //January is 0!
						         	                var yyyy = today.getFullYear();
			
						         	                if(dd<10) {
						         	                    dd='0'+dd
						         	                } 
			
						         	                if(mm<10) {
						         	                    mm='0'+mm
						         	                } 
			
						         	                today = mm+'/'+dd+'/'+yyyy;
						                        if(dd<10) {
						                            dd='0'+dd
						                        } 
			
						                        if(mm<10) {
						                            mm='0'+mm
						                        } 
			
						                        today = mm+'/'+dd+'/'+yyyy;
						                        
						         				chart.export = {
						         					enabled: true,
						         					position: "bottom-right",
						         						fileName:"V.SORTIE "+ today,
						         						
						         				}
						         				
						         				chart.initHC = false;
						         				chart.validateNow();
						         		        
			
						                        // WRITE
						                    
						                        chart.write("chartdiv3");
						         				
						         			});
						         			res.error(function(data, status, headers, config) {
						         				
						         				//alert( "failure message: " );
						         			});
			                      }
			                      $scope.gpao3({});
			                      
			                      $scope.gpao4= function(currentRequestgpao4) {
					                    	  var result = [];
					              		
					                    	  var urlBase=baseUrlGpao+"/aBCArticleDetailEtapeJour/rechercheMulticritere";
												var res = $http.post(urlBase, currentRequestgpao4);
					         			res.success(function(data, status, headers, config) {
					         				
					         				//console.log("----------------7");
					         				
					         				//console.log("sssss");
					         				//console.log(data);
					         			
					         				var c=[];
					         				$scope.nombreResultaRecherchergpao4=data.nombreResultaRechercher;
					         				if(data.nombreResultaRechercher != 0){
					         				
					         				for (var i = 0; i < data.nombreResultaRechercher; i++) {
					         					
					         					
					         					c.push({
		
					         						"client": data.list[i].clientAbrevation,
		
					         						"qual": data.list[i].qte,
					         						"color":"#3F51B5"
					         							    })	
					         					}}
					         				
			         //******************************** par jour **************************************************				
			         				
			         				c.reduce(function (res, value) {
			         				    if (!res[value.client]) {
			         				        res[value.client] = {
			         				            
			         				        	client: value.client,
			         				            qual: 0
			         				          
			         				        };
			         				        result.push(res[value.client])
			         				    }
			         				    res[value.client].qual += value.qual
			         				    return res;
			         				}, {})
			         				//console.log(result);
			         //**********************************************************************************	
			         				

			         				
			         //**********************************************************************************	
			         				
			         			 
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
			         	                
			         	                var today = new Date();
			         	                var dd = today.getDate();
			         	                var mm = today.getMonth()+1; //January is 0!
			         	                var yyyy = today.getFullYear();

			         	                if(dd<10) {
			         	                    dd='0'+dd
			         	                } 

			         	                if(mm<10) {
			         	                    mm='0'+mm
			         	                } 

			         	                today = mm+'/'+dd+'/'+yyyy;
			                        if(dd<10) {
			                            dd='0'+dd
			                        } 

			                        if(mm<10) {
			                            mm='0'+mm
			                        } 

			                        today = mm+'/'+dd+'/'+yyyy;
			                        
			         				chart.export = {
			         					enabled: true,
			         					position: "bottom-right",
			         					fileName:"V.SORTIE "+ today,
			         						
			         				}
			         				
			         				chart.initHC = false;
			         				chart.validateNow();
			         		        

			                        // WRITE
			                    
			                        chart.write("chartdiv4");
			         				
			         			});
			         			res.error(function(data, status, headers, config) {

			         				//alert( "failure message: " );
			         			});
			                      }
			                      $scope.gpao4({});
			                      
			                      
			                      
			                      $scope.gpao5= function(currentRequestgpao5) {
			                    	  var result = [];
										
									
									  var urlBase=baseUrlGpao+"/aBCArticleDetailEtapeJour/rechercheMulticritere";
										var res = $http.post(urlBase, currentRequestgpao5);
								res.success(function(data, status, headers, config) {
									
									//console.log("----------------8");
									
									//console.log("sssss");
									//console.log(data);
								    
									var c=[];
									$scope.nombreResultaRecherchergpao5=data.nombreResultaRechercher;
									$scope.sommeqte=0;
									$scope.sommetotal=0;
									if(data.nombreResultaRechercher != 0){
										
										 
									$scope.sommetotal=$scope.sommeqte+data.list[0].qteCmd;
									for (var i = 0; i < data.nombreResultaRechercher; i++) {
										 
										 
										var varDate = new Date(data.list[i].dateSaisie);
										var d,m;
										if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
										if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}

										var date = varDate.getFullYear()  +'-'+ m +'-'+ d;
										
										$scope.sommeqte=$scope.sommeqte+data.list[i].qte;
										
										
										c.push({

											"date": date,

											"qual": data.list[i].qte,
											"color":"#3F51B5"
												    })	
										}
									$scope.sommep=0;
									
									$scope.sommep=(100*$scope.sommeqte)/$scope.sommetotal;
									//console.log($scope.sommep);
									}
									
			//******************************** par jour **************************************************				
									
									c.reduce(function (res, value) {
									    if (!res[value.date]) {
									        res[value.date] = {
									            
									            date: value.date,
									            qual: 0,
									            color:"#8BC34A"
									          
									        };
									        result.push(res[value.date])
									    }
									    res[value.date].qual += value.qual
									    return res;
									}, {})
									//console.log(result);
			//**********************************************************************************	
			
			//**********************************************************************************	
									
								 
								var chart;
									chart = new AmCharts.AmSerialChart();
					                chart.dataProvider = result;
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
					               

					                // value
					                var valueAxis = new AmCharts.ValueAxis();
					                valueAxis.dashLength = 5;
					                valueAxis.title = "Quantite Production";
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
					                var today = new Date();
					                var dd = today.getDate();
					                var mm = today.getMonth()+1; //January is 0!
					                var yyyy = today.getFullYear();

					                if(dd<10) {
					                    dd='0'+dd
					                } 

					                if(mm<10) {
					                    mm='0'+mm
					                } 

					                today = mm+'/'+dd+'/'+yyyy;
					                
									chart.export = {
										enabled: true,
										position: "bottom-right",
											fileName:"V.SORTIE "+ today,
											
									}
									
									chart.initHC = false;
									chart.validateNow();
							        

					                // WRITE
					            
					                chart.write("chartdiv5");
									
								});
								res.error(function(data, status, headers, config) {
									
									//alert( "failure message: " );
								});
			                    	  
			                      }
			                      $scope.gpao5({});
			                      
			                      $scope.gpaoobj = function() {  
			                    	  
			                    	var obj={ "date": $scope.currentRequestgpaosj.date,
				                    		    "chaineId": $scope.currentRequestgpaosj.chaineId,
				                    		    "produitId" : $scope.currentRequestgpaosj.produitId,
				                    		    "listProductionElement": [
										                    		        {
										          			                  "heure": 1,
										          			                  "dem": $scope.currentRequestgpaosj.listProductionElement[0].dem,
										          			                  "prod": $scope.currentRequestgpaosj.listProductionElement[0].prod,
										          			                },
										                    		      	{
										                    		            "heure":2,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[1].dem,
										                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[1].prod,
										                    		        },
										                    		        {
										                    		            "heure": 3,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[2].dem,
										                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[2].prod,
										                    		        },
										                    		        {
										                    		            "heure": 4,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[3].dem,
										                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[3].prod,
										                    		        },
										                    		        {
										                    		            "heure": 5,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[4].dem,
										                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[4].prod,
										                    		        },
										                    		        {
										                    		            "heure": 6,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[5].dem,
										                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[5].prod,
										                    		        },
										                    		        {
										                    		            "heure":7,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[6].dem,
										                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[6].prod,
										                    		        },
										                    		        {
										                    		            "heure":8,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[7].dem,
										                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[7].prod,
										                    		        },
										                    		        {
										                    		            "heure":9,
										                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[8].dem,
										                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[8].prod,
										                    		        }
										                    		    ]
				                    		};
				                    	  
			                    	  $http .post(baseUrlGgpro+'/ChartGraphiqueGPAO', {})
			                    	  		.success(function(data, status, headers) {
						                    	  obj.push({

						                    		    "date": $scope.currentRequestgpaosj.date,
						                    		    "chaineId": $scope.currentRequestgpaosj.chaineId,
						                    		    "produitId" : $scope.currentRequestgpaosj.produitId,
						                    		    "listProductionElement": [
												                    		        {
												          			                  "heure": 1,
												          			                  "dem": $scope.currentRequestgpaosj.listProductionElement[0].dem,
												          			                  "prod": $scope.currentRequestgpaosj.listProductionElement[0].prod,
												          			                },
												                    		      	{
												                    		            "heure": 2,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[1].dem,
												                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[1].prod,
												                    		        },
												                    		        {
												                    		            "heure": 3,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[2].dem,
												                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[2].prod,
												                    		        },
												                    		        {
												                    		            "heure":4,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[3].dem,
												                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[3].prod,
												                    		        },
												                    		        {
												                    		            "heure": 5,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[4].dem,
												                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[4].prod,
												                    		        },
												                    		        {
												                    		            "heure": 6,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[5].dem,
												                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[5].prod,
												                    		        },
												                    		        {
												                    		            "heure": 7,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[6].dem,
												                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[6].prod,
												                    		        },
												                    		        {
												                    		            "heure": 8,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[7].dem,
												                    		            "prod": $scope.currentRequestgpaosj.listProductionElement[7].prod,
												                    		        },
												                    		        {
												                    		            "heure": 9,
												                    		            "dem": $scope.currentRequestgpaosj.listProductionElement[8].dem,
												                    		            "prod":$scope.currentRequestgpaosj.listProductionElement[8].prod,
												                    		        }
												                    		    ]
						                    				});
								         				});
			                    	  return obj;
			                    	  }
			                      
			                $scope.gpaosj= function(currentRequestgpaosj) {
			                    	  
                                 	 // //console.log("-------yourJsonObject : "+JSON.stringify($scope.gpaoobj() , null, "  ")   );
                                 	
			                    	  $scope.saisiesucc=0;
			                    	  var urlBase=baseUrlGpao+"/production/create";
										var res = $http.post(urlBase, $scope.gpaoobj());
										res.success(function(data, status, headers, config) {
											$scope.saisiesucc=1;
											$scope.productionday =[];
											$scope.productiongetday();
											$scope.annulergpaosj();
											
											
											
			                            });
										res.error(function(data, status, headers, config) {
											
											//alert( "failure message: " );
										});
			                      }
			                      
			                
			                $scope.gpaovp= function(currentRequestgpaovp) {
			                	 
								//console.log(currentRequestgpaovp);
								
			                		
			                	var urlBase=baseUrlGpao+"/production/rechercheMulticritere";
								var res = $http.post(urlBase,currentRequestgpaovp);
										res.success(function(data, status, headers, config) {
											
											var c=[];
											$scope.nombreResultaRecherchergpaovp=data.nombreResultaRechercher;											
											if(data.nombreResultaRechercher != 0){ 											
											for (var i = 0; i < data.list.length; i++) {
												for (var j = 0; j < 9; j++) {

												c.push({

													"heure": data.list[i].listProductionElement[j].heure,
													"chaine":data.list[i].chaineId,
													"prod": data.list[i].listProductionElement[j].prod,
													"color":"#3F51B5"
														    })	
												}}
											
											var result=[];
											c.reduce(function (res, value) {
												
					         				    if (!res[value.heure]) {
					         				        res[value.heure] = {
					         				            
					         				        		heure: value.heure,
					         				        		prod: 0,
					         				        		color:"#3F51B5"
					         				        		
					         				          
					         				        };
					         				        result.push(res[value.heure])
					         				    }
					         				    res[value.heure].prod += value.prod;
					         				   
					         				    return res;
					         				}, {})
					         				
											}			
											
										var chart;
											chart = new AmCharts.AmSerialChart();
							                chart.dataProvider = result;
							                chart.categoryField = "heure";
							                chart.startDuration = 1;

							                // AXES
							                // category
							                var categoryAxis = chart.categoryAxis;
							                categoryAxis.labelRotation = 45; // this line makes category values to be rotated
							                categoryAxis.gridAlpha = 0;
							                categoryAxis.fillAlpha = 1;
							                categoryAxis.fillColor = "#FAFAFA";
							                categoryAxis.gridPosition = "start";
							               

							                // value
							                var valueAxis = new AmCharts.ValueAxis();
							                valueAxis.dashLength = 5;
							                valueAxis.title = "Quantite Production";
							                valueAxis.axisAlpha = 0;
							                chart.addValueAxis(valueAxis);

							                // GRAPH
							                var graph = new AmCharts.AmGraph();
							                 
							                graph.valueField = "prod";
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
							                var today = new Date();
							                var dd = today.getDate();
							                var mm = today.getMonth()+1; //January is 0!
							                var yyyy = today.getFullYear();

							                if(dd<10) {
							                    dd='0'+dd
							                } 

							                if(mm<10) {
							                    mm='0'+mm
							                } 

							                today = mm+'/'+dd+'/'+yyyy;
							                
											chart.export = {
												enabled: true,
												position: "bottom-right",
													fileName:"V.SORTIE "+ today,
													
											}
											
											chart.initHC = false;
											chart.validateNow();
									        

							                // WRITE
							            
							                chart.write("chartdivvp");
											
						                });
										res.error(function(data, status, headers, config) {
											
										});
						                	
			                }
			                $scope.gpaovp({});
			                
			                $scope.gpaovr= function(currentRequestgpaovr) {
			                	
			                	var urlBase=baseUrlGpao+"/production/rechercheMulticritere";
								var res = $http.post(urlBase,currentRequestgpaovr);
									res.success(function(data, status, headers, config) {
										
										var c=[];
										$scope.nombreResultaRecherchergpaovp=data.nombreResultaRechercher;											
										if(data.nombreResultaRechercher != 0){ 											
										for (var i = 0; i < data.nombreResultaRechercher; i++) {
											
											
											for (var j = 0; j < 9; j++) {
												var varDate = new Date(data.list[i].date);
												var d,m;
												if((varDate.getMonth() + 1)<10){m='0'+(varDate.getMonth() + 1);}else{m=(varDate.getMonth() + 1);}
												if((varDate.getDate())<10){d='0'+(varDate.getDate());}else{d=(varDate.getDate());}

												var dater = varDate.getFullYear()  +'-'+ m +'-'+ d;
										
											c.push({

												"date": dater,
													
												"rend": data.list[i].listProductionElement[j].rend,
												"color":"#3F51B5"
													    })	
											}}
										var result=[];
										c.reduce(function (res, value) {
				         				    if (!res[value.date]) {
				         				        res[value.date] = {
				         				            
				         				        		date: value.date,
				         				        		rend: 0,
				         				        		color:"#3F51B5"
				         				        		
				         				          
				         				        };
				         				        result.push(res[value.date])
				         				    }
				         				    res[value.date].rend +=(value.rend)/9
				         				    return res;
				         				}, {})
				         				
										}			
										
									var chart;
										chart = new AmCharts.AmSerialChart();
						                chart.dataProvider = result;
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
						               

						                // value
						                var valueAxis = new AmCharts.ValueAxis();
						                valueAxis.dashLength = 5;
						                valueAxis.title = "Rendement";
						                valueAxis.axisAlpha = 0;
						                chart.addValueAxis(valueAxis);

						                // GRAPH
						                var graph = new AmCharts.AmGraph();
						                graph.valueField = "rend";
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
						                var today = new Date();
						                var dd = today.getDate();
						                var mm = today.getMonth()+1; //January is 0!
						                var yyyy = today.getFullYear();

						                if(dd<10) {
						                    dd='0'+dd
						                } 

						                if(mm<10) {
						                    mm='0'+mm
						                } 

						                today = mm+'/'+dd+'/'+yyyy;
						                
										chart.export = {
											enabled: true,
											position: "bottom-right",
												fileName:"V.SORTIE "+ today,
												
										}
										
										chart.initHC = false;
										chart.validateNow();
								        

						                // WRITE
						            
						                chart.write("chartdivvr");
										
					                });
									res.error(function(data, status, headers, config) {
										
										//alert( "failure message: " );
									});
					                	
			                }
			                $scope.gpaovr({});

			    $scope.annulerRapport = function(){
			    	$scope.productionRapportCourant = {
					  "phaseId": "",
					  "dateSaisieMin": "",
					  "dateSaisieMax": ""
					};
					$scope.affichage = "Jours";
			    }
			    //Liste Affichage : Jour, Mois, Annee          
			    $scope.listeAffichage = [{designation:"Jour"},{designation:"Mois"},{designation:"Annee"}];

				//Production
				$scope.downloadProduction = function(productionRapportCourant) {
							 	
					var url;
					var newdateMinFormat="";
					if(angular.isDefined(productionRapportCourant.dateSaisieMin)){
						
						if(productionRapportCourant.dateSaisieMin != ""){
							newdateMinFormat = formattedDate(productionRapportCourant.dateSaisieMin);
						}else{
							newdateMinFormat = "";
						}
					}

					var newdateMaxFormat="";
					if(angular.isDefined(productionRapportCourant.dateSaisieMax)){
						
						if(productionRapportCourant.dateSaisieMax != ""){
							newdateMaxFormat = formattedDate(productionRapportCourant.dateSaisieMax);
						}else{
							newdateMaxFormat = "";
						}
					}

					$log.debug("-- productionRapportCourant " + JSON.stringify(productionRapportCourant, null, "  ") );
	       			url = baseUrlGpao + "/report/abcArticleDetailEtapeJourByPhaseIdDateSaisie?phaseId="+ productionRapportCourant.phaseId
	       									 +"&dateSaisieDu="+newdateMinFormat
	       									 +"&dateSaisieAu="+newdateMaxFormat
											 +"&type=pdf";
						
	                   console.log("-- URL--- :" + url );
					 downloadService.download(url).then(
							 function(success) {
								// $log.debug('success : ' + success);
							 }, function(error) {
								// $log.debug('error : ' + error);
							 });
				 };	

				//Variation de Production
				$scope.downloadVariationProduction = function(productionRapportCourant, affichage) {
							 	
					var url;
					var newdateMinFormat="";
					if(angular.isDefined(productionRapportCourant.dateSaisieMin)){
						
						if(productionRapportCourant.dateSaisieMin != ""){
							newdateMinFormat = formattedDate(productionRapportCourant.dateSaisieMin);
							//console.log("===== newdateMinFormat "+newdateLivBCMinFormat);
						}else{
							//$log.debug("===== newdateMinFormat is Null");
							newdateMinFormat = "";
						}
					}

					var newdateMaxFormat="";
					if(angular.isDefined(productionRapportCourant.dateSaisieMax)){
						
						if(productionRapportCourant.dateSaisieMax != ""){
							newdateMaxFormat = formattedDate(productionRapportCourant.dateSaisieMax);
						//	console.log("===== newdateMaxFormat "+newdateMaxFormat);
						}else{
							//$log.debug("===== newdateMaxFormat is Null");
							newdateMaxFormat = "";
						}
					}
					console.log("-- productionRapportCourant " + JSON.stringify(productionRapportCourant, null, "  ") );
	       			
	       			url = baseUrlGpao + "/report/chartProductionByDateGroupeByAffichage?affichage="+ affichage
	       									 +"&dateSaisieDu="+newdateMinFormat
	       									 +"&dateSaisieAu="+newdateMaxFormat
											 +"&type=pdf";
						
	                   console.log("-- URL--- :" + url );
					 downloadService.download(url).then(
							 function(success) {
								// $log.debug('success : ' + success);
							 }, function(error) {
								// $log.debug('error : ' + error);
							 });
				 };					
							
}]);

		
