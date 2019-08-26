'use strict'

angular
		.module('gpro.mouvementEtat',
				[ 'mwl.calendar', 'ui.bootstrap', 'ngAnimate', 'ngSanitize' ])
		.controller(
				'mouvementEtatController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'$compile',
						'$rootScope',
						'moment',
						'$window',
						'baseUrlGpao',
						'baseUrl',
						function($scope, $filter, $http, $log,downloadService, $compile,
								$rootScope, moment, $window,
								baseUrlGpao,baseUrl) {
							
							
							
							 $scope.myStyleBtnOF = {
										
					                    "background-color" : "green"
					             
					                };
					                $scope.numOfVerifier = "false";

						

							/** *************** OF Grid ****************** */

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
															field : 'id',
															displayName : 'NumOf Chaine',
															visible : false
														},    
														{
															field : 'numOF',
															displayName : 'OF'
														},
														{
															field : 'designationOF',
															displayName : 'Désignation'
														},
														{
															field : 'couleurDesignation',
															displayName : 'Couleur'
														},
														{
															field : 'tailleDesignation',
															displayName : 'Taille'
														},
														{
															field : 'quantite',
															displayName : 'Quantite'
														},
													/*	{
															field : 'date',
															cellFilter: 'date:\'dd-MM-yyyy\'',
															displayName : 'Date'
														},*/
														{
															field : 'qteStock',
															displayName : ' Quantite Stock'
														},

														{
															field : '',
															cellTemplate :  '<div class="buttons">'
															
																+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(15)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
														} ];
											});

							$scope.filterOptions = {
								filterText : "",
								useExternalFilter : true
							};

							$scope.totalServerItems = 0;
							$scope.setPagingData = function(data, page,
									pageSize) {
								if(data){
									var pagedData = data.slice((page - 1)
										* pageSize, page * pageSize);
								$scope.myData = pagedData;
								$scope.totalServerItems = data.length;
								if (!$scope.$$phase) {
									$scope.$apply();
								}
								}
								
							};

							/*
							$scope.getPagedDataAsync = function(pageSize, page,
									searchText) {
								setTimeout(function() {
									if (searchText) {
										var ft = searchText.toLowerCase();

									} else {

									}
								}, 100);
							};*/
							
							
							
							$scope.getPagedDataAsync = function(pageSize, page,
									searchText) {
								setTimeout(
										function() {
											var data;
											var objectCourant = $scope.objectCourant;
											
											if (searchText) {
												var ft = searchText
														.toLowerCase();
														
												$http
														.post(
																baseUrlGpao	+ "/detailOf/rechercheDetailOfMulticritere",
																objectCourant)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.detailOfValues
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
																baseUrlGpao	+ "/detailOf/rechercheDetailOfMulticritere",
																objectCourant)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.detailOfValues,
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

							/**
							 * ********************** Production jour ********************/
							
							
							
		           	$scope.downloadProduction = function(objectCourant) {
								
								
							 	//dateIntroMin
							 	var newdateDe="";
								if(angular.isDefined(objectCourant.dateDe)){
									
									if(objectCourant.dateDe != ""){
										newdateDe = formattedDate(objectCourant.dateDe);
									}else{
										newdateDe = "";
									}
								}

							 	//dateIntroMax
							 	var newdateA="";
								if(angular.isDefined(objectCourant.dateA)){
									
									if(objectCourant.dateA != ""){
										newdateA = formattedDate(objectCourant.dateA);
									}else{
										newdateA = "";
									}
								}

						

						

								var url;
								
					

								//$log.debug("-- OFCourant " + JSON.stringify(ordreFabricationCourant, null, "  ") );
				       			url = baseUrlGpao + "/fiches/detailOf?clientId="+objectCourant.clientId
											
													 + "&numOF="+objectCourant.numOF
												;												 
													
													 
									
				                 ////$log.debug("-- URL--- :" + url );
								 downloadService.download(url).then(
										 function(success) {
											//$log.debug('success : ' + success);
										 }, function(error) {
											//$log.debug('error : ' + error);
										 });
							 };
							
							 
								function formattedDate(date) {
								    var d = new Date(date),
								        month = '' + (d.getMonth() + 1),
								        day = '' + d.getDate(),
								        year = d.getFullYear();

								    if (month.length < 2) month = '0' + month;
								    if (day.length < 2) day = '0' + day;
								    return [year,month,day].join('-');
								}
								

							// Rechercher Ordre Fabrication
							$scope.rechercher = function(objectCourant) {
								
								/*objectCourant.dateDe.setMinutes( objectCourant.dateDe.getMinutes() + objectCourant.dateDe.getTimezoneOffset() );
								
								objectCourant.dateA.setMinutes( objectCourant.dateA.getMinutes() + objectCourant.dateA.getTimezoneOffset() );
								*/
								
								$http
										.post(
												baseUrlGpao
														+ "/detailOf/rechercheDetailOfMulticritere",
														objectCourant)
										.success(
												function(resultat) {
													$scope.myData = resultat.detailOfValues;
													
													$scope
															.setPagingData(
																	$scope.myData,
																	$scope.pagingOptions.currentPage,
																	$scope.pagingOptions.pageSize);

												});
							}
							
							// ** Ajout Ordre de Fabrication
							$scope.Affectation = function(objectCourant) {
								$scope.objectCourant = {};
								$scope.objectCourant = objectCourant ? angular
										.copy(objectCourant)
										: {};

								$scope.displayMode = "edit";
							}
							
							
							// Liste des ClientCommandeVenteCache
							$scope.listeClientOrdreFabricationCache = function() {
								$http
										.get(
												baseUrlGpao+"/gestionProduitAOCache/listeClientCache")
										.success(
												function(dataClientCache) {
													$scope.ListClientOFCache = dataClientCache;
												});
							}
							$scope.listeClientOrdreFabricationCache();
						
							$scope.modifierOuCreer = function() {
								var index = this.row.rowIndex;

								$http
										.get(
												baseUrlGpao
														+ "/detailOf/getId:"
														+ $scope.myData[index].id)
										.success(
												function(data) {
													$scope.objectCourant = data;
													

												});

								$scope.displayMode = "edit";
							}

							// Sauvegarder Article
							$scope.sauvegarderAjout = function(objetCourant) {

								if (angular.isDefined(objetCourant.id)) {
									$scope.update(objetCourant);
								} else {
									$scope.creer(objetCourant);
								}
							 $scope.rechercher({});
							}

							$scope.creer = function(objetCourant) {
								
						
								objetCourant.partieInterresId = $scope.ordreFabricationCourant.partieInterresId;
								objetCourant.urgent = $scope.ordreFabricationCourant.urgent;
								
								$http
								.post(
										baseUrlGpao
												+ "/detailOf/creer",
										objetCourant)
								.success(
										function(newId) {
											
											objetCourant.id = newId;
											$scope.myData.unshift(objetCourant);
											//for(var i=0 ; i< $scope.myData.length; i++){
											//	 $scope.getAtelierName(objetCourant.idAtelier, i);
											//}
											$scope.annulerAjout();
											
										});
							}
							
							// Mise à jour des ordres de Fabrications
							$scope.update = function(objetCourant) {
								
								
								$http
										.put(
												baseUrlGpao
														+ "/detailOf/modifier",
														objetCourant)
										.success(
												function(modifiedId) {
												
													 for (var i = 0; i < $scope.myData.length; i++) {

							                             if ($scope.myData[i].id == modifiedId) {
							                                 $scope.myData[i] = objetCourant;
							                               //  $scope.getAtelierName(objetCourant.idAtelier, i);
							                                 break;
							                             }
							                         }
													 


													 
													$scope.annulerAjout();
													
												});
								
							}
		// ************** ici changement 
							
							
						     $scope.validerOF = function (numOf,type) {

				                    //console.log("sssssssssssssssssssssssssss");

				                    if (numOf == '') return;

				                     $scope.numOfVerifier = "false";

				                    $http
				                        .post(
				                            baseUrlGpao +
				                            "/ordreFabrication/getByNum", numOf
				                        )
				                        .success(
				                            function (resultat) {
				                                if (resultat == '') {
				                                    $scope.numOfVerifier = "false";
				                                } else {
				                                    
           
				                                    $scope.numOfVerifier = "true";
				                                    
				                                    $scope.ordreFabricationCourant =resultat;
				                                    
	                                    
				                                }


				                            });


				                }
				                
				                $scope.ofChanged = function ()
				                {
				                    $scope.numOfVerifier = "false";
				                    $scope.ordreFabricationCourant ={};
				                 
				                }
							
								$scope.annulerAjout = function() {
									 $scope.numOfVerifier = "false";
					                 $scope.ordreFabricationCourant ={};
					                 
									$scope.init();
									$scope.objectCourant = {};
								$scope.creationForm.$setPristine();
								$scope.rechercheForm.$setPristine();
								

								$scope
										.setPagingData(
												$scope.myData,
												$scope.pagingOptions.currentPage,
												$scope.pagingOptions.pageSize);
								
								$scope.displayMode = "list";
							}
							


					
							
							
							
							$scope.listeAnneess = [];
							$scope.getListeAnnees = function(){
								
								for(var i=2016; i<2051 ; i++){
									$scope.listeAnneess.push(i);
								}
								
							}
							
							$scope.listeSemaines = [];
							$scope.getListeSemaines = function(){
								
								for(var i=1; i<53 ; i++){
									$scope.listeSemaines.push(i);
								}
								
							}
							
							
							
							
							
							// Enregistrer Phase
							$scope.save = function(list, id) {
								$scope.deleteValue = "non";
								// $scope.Representant not updated yet
								angular.extend(list, {
									id : id
								});
							};

							// Supprimer Phase
							$scope.remove = function(index) {
								$scope.cnt--;
								$scope.listCapaciteSemaine.splice(index, 1);
							};

							
							
							
							/** *********** Init ************** */

							$scope.init = function() {
								
								$scope.displayMode = "list";
								
								
							
								
								
							}
						

							$scope.init();
						} ])
