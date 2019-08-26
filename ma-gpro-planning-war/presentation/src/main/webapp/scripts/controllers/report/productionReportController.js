// /*'use strict'

// angular
// 		.module('gpro.productionReport',
// 				[ 'mwl.calendar', 'ui.bootstrap', 'ngAnimate', 'ngSanitize' ])
// 		.controller(
// 				'productionReportController',
// 				[
// 						'$scope',
// 						'$http',
// 						'$filter',
// 						'$log',
// 						'downloadService',
// 						'baseUrlGpao',
// 						'baseUrlGc',
// 						'baseUrl',
// 						function($scope, $http, $filter, $log,downloadService, baseUrlGpao ,baseUrlGc ,baseUrl) {
// 							$scope.modeAdd = "Notok";
						
// 							$scope.planningCourant  = {};
							
		
// 							var tab = [];
// 							//detailProduitCommande a affecté a produitCommande
// 							$scope.maGproDetailPrCmdGpao = [];
// 							$scope.listeColor=[];
// 							$scope.listeTaille =[];
// 							$scope.maGprogetMatrixEditGpao = [];
// 							$scope.demoQuantiteGpao = 0;
// 							$scope.maGprotapedValueGpao = [];


// 							$scope.listQuantiteProduitCommande = [];
// 							$scope.listQuantiteProduitCommande.quantiteOF = {};
// 							$scope.listPhaseProduit = [];
// 							$scope.listQuantiteOF = [];
// 							$scope.listPhaseOF = [];
// 							$scope.listDetailsOF = [];
// 							$scope.chainesList = [];
							
// 							$scope.item=0;
							
// 							$scope.displayMode = "list";
							
							
							
// 							// Liste des ChaineOFCache
// 							$scope.listeChainePlanningCache = function() {
// 								$http
// 										.get( baseUrlGpao +"/chaine/all")
// 										.success(
// 												function(dataChaineCache) {
// 													$scope.chainesList = dataChaineCache;

// 												});
// 							}
// 							$scope.listeChainePlanningCache();
				

// 				//$log.debug(" ======= after : itemTab : "+JSON.stringify(itemTab,null,"  ")) ;
				
							

							

							
						

// 							/*****************************
// 							 * Conversion ID / Designation
// 							 ****************************/
// 							 // Produit 
//               					$scope.produitId = {
//             						status : ''
//               					};
              				
//                   			//PartieInteressee
//               					$scope.partieInteresseeId = {
//               						status : ''
//                   				};
// 							/*************************
// 							 *    Ordre de Fabrication
// 							 *************************/

// 							$scope.myDataOF = [];
// 							$scope.listQuantiteOF = [];
// 							$scope.listePhaseOF = [];
// 							$scope.creation = true;

// 							$scope.displayMode = "list";



							
// 							$scope.phaseOff=[];
// 							// Liste des Ordres de Fabrication
// 							$scope.listeOrdreFabrication = function() {
// 								$http
// 										.get(
// 												baseUrlGpao
// 														+ "/ordreFabrication/all")
// 										.success(
// 												function(dataOrdreFabrication) {
													
// 													//$scope.myDataOF = dataOrdreFabrication;
													
// 											});
// 							}

// 							$scope.pagingOptions = {
// 									pageSizes : [ 5, 10, 13 ],
// 									pageSize : 13,
// 									currentPage : 1
// 								};
							
// 							// Rechercher Ordre Fabrication
// 							$scope.rechercherPlanning = function(
// 									planningCourant) {
// 								$log.debug("Recherche : "+JSON.stringify($scope.planningCourant,null, "  "));
// 								$http
// 										.post(
// 												baseUrlGpao
// 														+ "/planning/rechercheMulticritere",
// 												planningCourant)
// 										.success(
// 												function(resultat) {
// 													$scope.myDataOF = resultat.planningValue;
// 													////$log.debug('rslts recherche Planning  ..'+$scope.myDataOF.length);
// 													// Pagination du resultat de
// 											        // recherche
// 											        // data, page,pageSize
// 											            $scope
// 											                .setPagingData(
// 											                  $scope.myDataOF,
// 											                  $scope.pagingOptions.currentPage,
// 											                  $scope.pagingOptions.pageSize);

// 													$scope.displayMode = "rechercher";
// 													$scope.creationOFForm.$setPristine();
// 													$scope.rechercheOFForm.$setPristine();
// 													$scope.displayAlert = "sleep";
// 												});
// 							}
					
// 							// Appel des fonctions
// 							$scope.listeOrdreFabrication();

// 							/*** PDF ***/
//                       		//generer rapport de tous les Ordre de Fabrication. mode : List
//                       		//conversion date en String
// 							function formattedDate(date) {
// 							    var d = new Date(date),
// 							        month = '' + (d.getMonth() + 1),
// 							        day = '' + d.getDate(),
// 							        year = d.getFullYear();

// 							    if (month.length < 2) month = '0' + month;
// 							    if (day.length < 2) day = '0' + day;
// 							    return [year,month,day].join('-');
// 							}

// 							$scope.downloadAllPlanning = function(planningCourant) {
							
// 							//dateDebut Min
// 							 	var newdateDebutDeFormat="";
// 								if(angular.isDefined(planningCourant.dateDebutDe)){
									
// 									if(planningCourant.dateDebutDe != ""){
// 										newdateDebutDeFormat = formattedDate(planningCourant.dateDebutDe);
// 									}else{
// 										newdateDebutDeFormat = "";
// 									}
// 								}
// 							 	//dateDebut Max
// 							 	var newdateDebutAFormat="";
// 								if(angular.isDefined(planningCourant.dateDebutA)){
									
// 									if(planningCourant.dateDebutA != ""){
// 										newdateDebutAFormat = formattedDate(planningCourant.dateDebutA);
// 									}else{
// 										newdateDebutAFormat = "";
// 									}
// 								}

							 	

// 							 	//dateFin Prevue min
// 							 	var newdateFinPrevueDeFormat="";
// 								if(angular.isDefined(planningCourant.dateFinPrevueDe)){
									
// 									if(planningCourant.dateFinPrevueDe != ""){
// 										newdateFinPrevueDeFormat = formattedDate(planningCourant.dateFinPrevueDe);
// 									}else{
// 										newdateFinPrevueDeFormat = "";
// 									}
// 								}

// 								//datefin prevue max
// 							 	var newdateFinPrevueAFormat="";
// 								if(angular.isDefined(planningCourant.dateFinPrevueA)){
									
// 									if(planningCourant.dateFinPrevueA != ""){
// 										newdateFinPrevueAFormat = formattedDate(planningCourant.dateFinPrevueA);
// 									}else{
// 										newdateFinPrevueAFormat = "";
// 									}
// 								}
								
// 								//datefin reel  min
// 								var newdateFinReelDeFormat="";
// 								if(angular.isDefined(planningCourant.dateFinReelDe)){
									
// 									if(planningCourant.dateFinReelDe != ""){
// 										newdateFinReelDeFormat = formattedDate(planningCourant.dateFinReelDe);
// 									}else{
// 										newdateFinReelDeFormat = "";
// 									}
// 								}

// 								//dateFin Reel max
// 							 	var newdateFinReelAFormat="";
// 								if(angular.isDefined(planningCourant.dateFinReelA)){
									
// 									if(planningCourant.dateFinReelA != ""){
// 										newdateFinReelAFormat = formattedDate(planningCourant.dateFinReelA);
// 									}else{
// 										newdateFinReelAFormat = "";
// 									}
// 								}

// 								var url;

// 								////$log.debug("-- OFCourant " + JSON.stringify(planningCourant, null, "  ") );
// 				       			url = baseUrlGpao + "/reportPlanning/listPlanning?chaine=" + planningCourant.chaine
// 								 					 + "&oFId=" + planningCourant.oFId
// 								 					 //+ "&vCompositionClient="+planningCourant.vCompositionClient
// 													 + "&dateDebutA="+newdateDebutAFormat
// 													 + "&dateDebutDe="+newdateDebutDeFormat
// 													 + "&dateFinPrevueA="+newdateFinPrevueAFormat
// 													 + "&dateFinPrevueDe="+newdateFinPrevueDeFormat
// 													 + "&dateFinReelA="+newdateFinReelAFormat
// 													 + "&dateFinReelDe="+newdateFinReelDeFormat
// 													 + "&type=pdf";
									
// 				                 ////$log.debug("-- URL--- :" + url );
// 								 downloadService.download(url).then(
// 										 function(success) {
// 											//$log.debug('success : ' + success);
// 										 }, function(error) {
// 											//$log.debug('error : ' + error);
// 										 });
// 							 };

// 							/***************************************************
// 							 * Gestion des CompositionOF
// 							 **************************************************/
							

							
				
// 							/***************************************************
// 							 * Gestion de Grid OF
// 							 **************************************************/
// 							$scope.colDefs = [];
// 							$scope.$watch(
// 								'myDataOF',
// 									function() {
// 										$scope.colDefs = [
// 										{
// 											field : '',
// 											headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
// 											cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
// 											width:'3%'
// 										},
// 										{
// 											field : 'idOF',
// 											displayName : 'N° OF',
// 											width:'5%'
// 										},
// 										{
// 											field : 'chaineDesignation',
// 											displayName : 'chaineDesignation',
// 											width:'10%'
// 										},
// 										{
// 											field : 'dateDebut',
// 											displayName : 'date Debut .',
// 											cellFilter: 'date:\'dd-MM-yyyy\'',
// 											width:'10%'
// 										},
// 										{
// 											field : 'dateFinPrevue',
// 											displayName : 'dateFinPrevue',
// 											cellFilter: 'date:\'dd-MM-yyyy\'',
// 											width:'10%'
// 										},
// 										{
// 											//field : 'produitReference',
// 											displayName : 'Réf.Produit',
// 											width:'10%'
// 										},
// 										{
// 											//field : 'produitDesignation', 
// 											displayName : 'Produit',
// 											width:'20%'
// 										},
// 										{
// 											//field : 'produitSousFamilleDesignation',
// 											displayName : 'Sous Famille',
// 											width:'10%'
// 										},
// 										{
// 											//field : 'etatDesignation',
// 											displayName : 'etat',
// 											width:'10%'
// 										},
// 										{
// 											field : 'quantite',
// 											displayName : 'Quantité',
// 											width:'8%'
// 										}];
// 								});

// 								$scope.filterOptions = {
// 									filterText : "",
// 									useExternalFilter : true
// 								};

// 								$scope.totalServerItems = 0;
								
// 								$scope.setPagingData = function(data, page,
// 										pageSize) {
// 									var pagedData = data.slice((page - 1)
// 											* pageSize, page * pageSize);
// 									$scope.myDataOF = pagedData;
// 									$scope.totalServerItems = data.length;
// 									if (!$scope.$$phase) {
// 										$scope.$apply();
// 									}
// 								};

// 								$scope.getPagedDataAsync = function(pageSize, page,
// 										searchText) {
// 									setTimeout(
// 											function() {
// 												var data;
// 												var planningCourant = $scope.planningCourant;
// 												if (searchText) {
// 													var ft = searchText
// 															.toLowerCase();
															
// 													$http
// 															.post(
// 																	baseUrlGpao	+ "/planning/rechercheMulticritere",
// 																	planningCourant)
// 															.success(
// 																	function(
// 																			largeLoad) {
// 																		data = largeLoad.planningValue
// 																				.filter(function(
// 																						item) {
// 																					return JSON
// 																							.stringify(
// 																									item)
// 																							.toLowerCase()
// 																							.indexOf(
// 																									ft) != -1;
// 																				});
// 																		$scope
// 																				.setPagingData(
// 																						data,
// 																						page,
// 																						pageSize);
// 																	});

// 												} else {
// 													$http
// 															.post(
// 																	baseUrlGpao	+ "/planning/rechercheMulticritere",
// 																	planningCourant)
// 															.success(
// 																	function(
// 																			largeLoad) {
// 																		$scope
// 																				.setPagingData(
// 																						largeLoad.planningValue,
// 																						page,
// 																						pageSize);
// 																	});
// 												}
// 											}, 100);
// 								};

// 								$scope.getPagedDataAsync(
// 										$scope.pagingOptions.pageSize,
// 										$scope.pagingOptions.currentPage);

// 								$scope
// 										.$watch(
// 												'pagingOptions',
// 												function(newVal, oldVal) {
// 													if (newVal !== oldVal
// 															&& newVal.currentPage !== oldVal.currentPage) {
// 														$scope
// 																.getPagedDataAsync(
// 																		$scope.pagingOptions.pageSize,
// 																		$scope.pagingOptions.currentPage,
// 																		$scope.filterOptions.filterText);
// 													}
// 												}, true);
// 								$scope.$watch('filterOptions', function(newVal,
// 										oldVal) {
// 									if (newVal !== oldVal) {
// 										$scope.getPagedDataAsync(
// 												$scope.pagingOptions.pageSize,
// 												$scope.pagingOptions.currentPage,
// 												$scope.filterOptions.filterText);
// 									}
// 								}, true);

// 								$scope.gridOptions = {
// 									data : 'myDataOF',
// 									columnDefs : 'colDefs',
// 									enablePaging : true,
// 									enableRowSelection : true,
// 									enableHighlighting : true,
// 									showFooter : true,
// 									totalServerItems : 'totalServerItems',
// 									pagingOptions : $scope.pagingOptions,
// 									selectedItems : $scope.selectedRows,
// 									filterOptions : $scope.filterOptions,
// 								};


// 							/** Fin de gestion des Grids GPAO */
// 						} ]);


// */