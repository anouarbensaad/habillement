'use stric'
angular
		.module('gpro.suivicommande', [ "ngResource" ])
		.controller(
				'suiviOfController',
				[
						'$scope',
						'$filter',
						'$http',
						'downloadService',
						'UrlCommun',
						function($scope, $filter, $http, downloadService, UrlCommun) { 
							
							
							// Déclaration des variables globales utilisées
							// Déclaration des variables						
							$scope.displayMode = "list";
							$scope.displayMode1 = "list";	
							$scope.displayMode2 = "list";
							
						 
							
						    //ajout cause 
						    $scope.ajoutCause = function()
						    {
						    	$scope.causeInserer= {
						    			cause : '',
						    			quantité : ''
						    	}
						    }
							
							// ajout Cause
							$scope.ajoutCause = function() {

								$scope.CauseInserree = {
									cause : '',
									quantite : ''
								};
								$scope.listeCause.push($scope.CauseInserree);

							};
							
							/***************************/
							// Annulation de le suivi :
							$scope.annulerSuivi = function() {
								$scope.OFCourant = {};
								$scope.displayMode = "list";
								$scope.displayMode1 = "list";
								$scope.displayMode2 = "list";
							}
							
							/***************************************************
							 * Gestion Cache
							 */
							// Liste des OFCache
							$scope.ListDeviseCache = function() {
								$http
										.get(
												UrlCommun
														+ "/gestionnaireCache/")
										.success(
												function(dataDeviseCache) {
													console
															.log("listeOFCache :"
																	+ dataOFCache.length);
													$scope.ListOFCache = dataOFCache;
												});
							}
							
						       $scope.ListOFCache();
						       
						       
						       //Variale de Pagination de la grid
						       
						       
							    $scope.pagingOptions = {
										pageSizes : [ 5, 10, 14 ],
										pageSize : 14,
										currentPage : 1
									};
							
							 // Lister les OFs
								$scope.listeOF = function() {
									$http.get(UrlCommun + "")
											.success(function(data) {
												console.log(" : "+data.length);
												$scope.partieInteresssees = data;
												$scope.myData = data;
												//data, page,pageSize
												$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																				   $scope.pagingOptions.pageSize										
																				   );
												
												
												
												
											});
								}
								
								// Rechercher OF
								$scope.rechercherOF = function(
										OFCourant) {
									$http
											.post(
													UrlCommun
															+ "",
													OFcourant)
											.success(
													function(resultat) {
														$scope.myData = resultat.partieInteresseValues;
														//data, page,pageSize
														$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																						   $scope.pagingOptions.pageSize										
																						   );
														
														//$scope.displayMode = "rechercher";
														//$scope.recherchePartieIntereseeFormCritere
																//.$setPristine();
													});

								}
							
								/***************************************************
								 * Gestion des Grids de recherche
								 **************************************************/
								$scope.colDefs = [];
								$scope
										.$watch(
												'myData',
												function() {
													$scope.colDefs = [
															
															{
																field : 'cliente',
																displayName : 'Client',
															},
															{
																field : 'reference',
																displayName : 'Réf.Produit'
															},
															{
																field : 'produit',
																displayName : 'Produit'
															},
															{
																field : 'N° OF',
																displayName : 'N° OF'
															},
															{
																field : 'Q.OF',
																displayName : 'Q.OF'
															},
															{
																field : 'Phase',
																displayName : 'phase'
															},
															{
																field : 'méthode',
																displayName : 'Méthode'
															},
															{
																field : 'Date',
																displayName : 'Date Déb.Prév '
															},
															{
																field : 'Date',
																displayName : 'Date fin.Prév '
															},
															{
																field : 'Q.E',
																displayName : 'Q.E'
															},
															
															{ 
																field : 'Q.S',
																displayname: 'Q.S'
																
															},
															{ 
																field : 'Q.Manq.',
																displayname: 'Q.Manq.'
																
															},
															{ 
																field : 'Etat',
																displayname: 'Etat'
																
															},
															{
																field : '',
																cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
														             +'<a  class="btn-action btn" ng-click="editSuiviCommande()">SG</a>'
														             +' '
														             +'<a  class="btn-action btn" ng-click="editSuiviCommandeD()">SD</a>'
														             +'</div>'
															},
															{
																field : '',
																cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																		+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPartieInteressee()">	<i class="fa fa-fw fa-pencil"></i></button>'
																		+ '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(1)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
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
														if (searchText) {
															var ft = searchText
																	.toLowerCase();
															$http
																	.get(
																			UrlCommun
																					+ "")
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
																	.get(UrlCommun
																			+ "")
															.success(function(largeLoad) {
																$scope.setPagingData(
																		largeLoad, page,
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
							
							/***********************fin gestion de la grid *****************************************/
										
							
						} ]);
