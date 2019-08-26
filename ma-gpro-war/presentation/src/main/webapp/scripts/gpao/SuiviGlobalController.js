'use strict'
var app = angular.module('gpro.gpaoSuiviJournalier', []);
app.controller(
				'SuiviGlobalGpaoController',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'downloadService',
						'baseUrl',
						'baseUrlGpao',
						'baseUrlGgpro',
						function($scope, $http, $filter, $log,downloadService,baseUrl, baseUrlGpao, baseUrlGgpro) {
							 $log.info("----------- GPAO Suivi > Feuille SuiveuseJournaliere ----------");
               
							$scope.currentRequestgpaosj = {};
							$scope.listProduitDrop =[];
							$scope.saisiesucc=0;
							$scope.displayMode = "list";
							$('.tableHS').show();
							$('.tableHSPH').hide();

							$scope.annulergpaosj = function(){
											$scope.currentRequestgpaosj= {};
											$scope.saisiesucc=0;
											//$scope.gpaosj({});
											 $scope.rechercheProduitSaisie({});
											$scope.requestGpaosjForm.$setPristine();
											$('.tableHS').show();
											$('.tableHSPH').hide();

											$scope.displayMode = "list";
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

						    $scope.listeProduit = function() {
								
								$http.get(baseUrl+"/produit/all")
											.success(
												function(dataProduit) {
													$scope.listProduitDrop = dataProduit;
													$log.debug("listProduitDrop : "+dataProduit.length);
												});
							};
							
							$scope.listeProduit();
  
  	// Rechercher Production Horaire
							                $scope.rechercheProduitSaisie = function(produithoraireCourante) {
							                   
							               
							                	
							                    $http
							                        .post(
							                            baseUrlGpao +
							                            "/production/rechercheMulticritere",
							                            produithoraireCourante)

							                    .success(
							                        function(resultat) {

							                        $scope.myData = resultat.list;
							                           
							                        });
							                
							              }
							                $scope.rechercheProduitSaisie({});
							                
							// ** Ajout Article
							$scope.AffectationSGlobal = function(currentRequestgpaosj) {
								$scope.currentRequestgpaosj = currentRequestgpaosj ? angular
										.copy(currentRequestgpaosj) : {};
								$scope.currentRequestgpaosj = {};
								$scope.requestGpaohpForm.$setPristine();

								$scope.displayMode = "edit";
							}

							             //getById pour la production horaire
							                $scope.getByProductH = function(produit){
							                $scope.displayMode = "edit";
							                	
							                	var index = $scope.myData.indexOf(produit);
										    	$scope.idproduit = $scope.myData[index].id;

												$http
														.get(
																baseUrlGpao
																		+ "/production/getById:"
																		+ $scope.idproduit)
														.success(
																function(datagetBonSortie) {
																	
																	$scope.currentRequestgpaosj.id = datagetBonSortie.id;
																	$scope.currentRequestgpaosj.date = datagetBonSortie.date;
																	$scope.currentRequestgpaosj.produitId = datagetBonSortie.produitId;
																	$scope.currentRequestgpaosj.chaineId = datagetBonSortie.chaineId;
																	$scope.listProductionElement = datagetBonSortie.listProductionElement;
																});
												$('.tableHSPH').show();
												$('.tableHS').hide();
							                }
										
										  $scope.productionday =[];
										  $scope.productiongetday= function() {
										  $http.post(baseUrlGpao+'/production/rechercheMulticritere', {								             								             
									         }).
									         	success(function(data) {
									         		
									         		$log.debug("----------------2");
									         		
									         		
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
										
										

							$scope.gpaoobj = function(){    
				                    	
				                    	  	var obj = { "date": $scope.currentRequestgpaosj.date,
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
							                    	  
						                    
	                    	  			return obj;
	                    	  			}// end obj
/*
			                $scope.gpaosj= function(currentRequestgpaosj) {
			                    	  
                                 	  $log.debug("-------yourJsonObject : "+JSON.stringify($scope.gpaoobj() , null, "  ")   );
                                 	
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
											
										});
			                      } //end gpaosj*/

			                $scope.gpaosj= function(currentRequestgpaosj) {
                               
			                	if (angular.isDefined(currentRequestgpaosj.id)) {
									
									$scope.updategpaosj(currentRequestgpaosj);
								   
								}else{
			                	
			                	    $scope.creergpaosj(currentRequestgpaosj);
										
			                	    }
			                }
			                $scope.creergpaosj = function(currentRequestgpaosj){
			                	var urlBase=baseUrlGpao+"/production/create";
										var res = $http.post(urlBase, $scope.gpaoobj());
										res.success(function(data, status, headers, config) {
											$scope.saisiesucc=1;
											console.log("successCreation");
											$scope.productionday =[];
											$scope.productiongetday();
											$scope.annulergpaosj();
			                            });
										res.error(function(data, status, headers, config) {
											
										});
			                }
			                      
			                $scope.updategpaosj = function(currentRequestgpaosj){
			                	
			                	currentRequestgpaosj.listProductionElement = $scope.listProductionElement;
		                    	    var urlBase=baseUrlGpao+"/production/update";
									var res = $http.put(urlBase, currentRequestgpaosj);
									res.success(function(data, status, headers, config) {
										
										$scope.saisiesucc=1;
										console.log("successUpdate");
										$scope.annulergpaosj();
										$scope.rechercheProduitSaisie({});
		                            });
									res.error(function(data, status, headers, config) {
										
										console.log("erreur");
									});
			                }
					           
						}//End function
					
				])
