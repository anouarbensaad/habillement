'use strict'
angular
		.module('gpro.gc_produitCommandeVente', [])
		.controller(
				'ProduitCommandeVenteCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$rootScope',
						'$log',
						'downloadService',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter,$rootScope, $log, downloadService, baseUrlGc,baseUrl) {
							$log.info("----------- ProduitBC  ----------");
		
							//** Variables Recherche
							$scope.listProduitCommande = [];
							$scope.ListClientCommandeVenteCache = [];
							
							//**Variables Modif/Ajout
							$scope.produitCommandeVenteCourant = {};
							$scope.listProduitDrop = [];
							//**Variable Grid
							$scope.myData = [];
							//bouton pdf hide
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
							/*********************************************************
							 * Gestion de Cache des Referentiels Gestion Commerciale *
							 *********************************************************/
							
							// Liste des ClientCommandeVenteCache
							$scope.listeClientCommandeVenteCache = function() {
								$http
										.get(baseUrlGc+	"/gestionCommercialCache/listeClientCache")
										.success(
												function(listeClientCache) {
													console
															.log("*LISTClientCommandeVenteCache :"
																	+ listeClientCache);
													$scope.ListClientCommandeVenteCache = listeClientCache;

												});
							}
							
							$scope.listeClientCommandeVenteCache();
							
							/************************************************/
							// Liste des produits
							$scope.listeProduit = function() {
								$http.get(baseUrl+"/produit/all").success(
										function(dataProduit) {
											$scope.listProduitDrop = dataProduit;
										});
							}
							$scope.listeProduit();
							
							/************************************************/
							$scope.pagingOptions = {
										pageSizes : [ 5, 10, 13 ],
										pageSize : 13,
										currentPage : 1
									};

							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
								$scope.produitCommandeVenteCourant =  {
																		"refCommande": "",
																		"clientId": null,
																		"produitId": null,
																		"dateCommandeMin": "",
																		"dateCommandeMax": "",
																		"dateLivraisonMin": "",
																		"dateLivraisonMax": ""
																	};
								$scope.rechercherProduitCommandeVente({});
								$scope.listProduitCommande = [];
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
							}
							
							// Rechercher ProduitCommandeVente
							$scope.rechercherProduitCommandeVente = function(produitCommandeVenteCourant) {
								
								$http
										.post(
												baseUrlGc
														+ "/produitCommande/rechercheMulticritere",
														produitCommandeVenteCourant)
										.success(
												function(resultat) {
													$scope.myData = resultat.listProduitCommandeValue;
													$log.debug('recherche Produit Commande de Vente.. '+$scope.myData.length);
													// Pagination du resultat de
											        // recherche
											        // data, page,pageSize
											              $scope
											                .setPagingData(
											                  $scope.myData,
											                  $scope.pagingOptions.currentPage,
											                  $scope.pagingOptions.pageSize);

													$scope.displayMode = "rechercher";
												});
								$scope.rechercheSSForm.$setPristine();
							}
							
							//suppression BonCommande
							$scope.supprimerProduitCommandeVente = function() {
								//var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url :baseUrlGc + "/produitCommande/supprimerProduitCommande:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
              								$scope.myData.splice($scope.index, 1);
								});
								$scope.closePopupDelete();
								$scope.rechercherProduitCommandeVente({});
							};
							// Suppression d'un bon de commande de vente

							/*** PDF ***/
							//conversion date en String
							function formattedDate(date) {
							    var d = new Date(date),
							        month = '' + (d.getMonth() + 1),
							        day = '' + d.getDate(),
							        year = d.getFullYear();

							    if (month.length < 2) month = '0' + month;
							    if (day.length < 2) day = '0' + day;
							    return [year,month,day].join('-');
							}
        						
        					//generer rapport de tous les bons de livraison. mode : List 
							$scope.downloadAllProduitCommandeVente = function(produitCommandeVenteCourant) {

								var newdateLivBCMinFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateLivraisonMin)){
									
									if(produitCommandeVenteCourant.dateLivraisonMin != ""){
										newdateLivBCMinFormat = formattedDate(produitCommandeVenteCourant.dateLivraisonMin);
									}else{
										newdateLivBCMinFormat = "";
									}
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateLivraisonMax)){
									
									if(produitCommandeVenteCourant.dateLivraisonMax != ""){
										newdateLivBCMaxFormat = formattedDate(produitCommandeVenteCourant.dateLivraisonMax);
									}else{
										newdateLivBCMaxFormat = "";
									}
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateCommandeMin)){
									
									if(produitCommandeVenteCourant.dateCommandeMin != ""){
										newdateIntroBCMinFormat = formattedDate(produitCommandeVenteCourant.dateCommandeMin);
									}else{
										newdateIntroBCMinFormat = "";
									}
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateCommandeMin)){
									
									if(produitCommandeVenteCourant.dateCommandeMin != ""){
										newdateIntroBCMaxFormat = formattedDate(produitCommandeVenteCourant.dateCommandeMin);
									}else{
										newdateIntroBCMaxFormat = "";
									}
								}

								$log.debug("-- produitCommandeVenteCourant" + JSON.stringify(produitCommandeVenteCourant, null, "  ") );

								var url;
								$log.debug("PI  "+produitCommandeVenteCourant.clientId );
								 	if(produitCommandeVenteCourant.clientId == null){
								 	produitCommandeVenteCourant.clientId = "";
								 }

								$log.debug("Produit  "+produitCommandeVenteCourant.produitId );
								 	if(produitCommandeVenteCourant.produitId == null){
								 	produitCommandeVenteCourant.produitId = "";
								 }

			        			url = baseUrlGc + "/report/listProduitCommandeVente?referenceBC=" + produitCommandeVenteCourant.refCommande
								 					 + "&clientId=" + produitCommandeVenteCourant.clientId
								 					 + "&produitId="+produitCommandeVenteCourant.produitId
													 + "&dateCommandeMin="+newdateIntroBCMinFormat
													 + "&dateCommandeMax="+newdateIntroBCMaxFormat
													 + "&dateLivraisonMin="+newdateLivBCMinFormat
													 + "&dateLivraisonMax="+newdateLivBCMaxFormat
													 + "&type=pdf";
				                  
				                $log.debug("-- URL" + url );
								 downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });
							};	

							$scope.downloadAllBesoinArticle = function(produitCommandeVenteCourant){
								var newdateLivBCMinFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateLivraisonMin)){
									
									if(produitCommandeVenteCourant.dateLivraisonMin != ""){
										newdateLivBCMinFormat = formattedDate(produitCommandeVenteCourant.dateLivraisonMin);
									}else{
										newdateLivBCMinFormat = "";
									}
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateLivraisonMax)){
									
									if(produitCommandeVenteCourant.dateLivraisonMax != ""){
										newdateLivBCMaxFormat = formattedDate(produitCommandeVenteCourant.dateLivraisonMax);
									}else{
										newdateLivBCMaxFormat = "";
									}
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateCommandeMin)){
									
									if(produitCommandeVenteCourant.dateCommandeMin != ""){
										newdateIntroBCMinFormat = formattedDate(produitCommandeVenteCourant.dateCommandeMin);
									}else{
										newdateIntroBCMinFormat = "";
									}
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(produitCommandeVenteCourant.dateCommandeMin)){
									
									if(produitCommandeVenteCourant.dateCommandeMin != ""){
										newdateIntroBCMaxFormat = formattedDate(produitCommandeVenteCourant.dateCommandeMin);
									}else{
										newdateIntroBCMaxFormat = "";
									}
								}

								$log.debug("-- produitCommandeVenteCourant" + JSON.stringify(produitCommandeVenteCourant, null, "  ") );

								var url;
								$log.debug("PI  "+produitCommandeVenteCourant.clientId );
								 	if(produitCommandeVenteCourant.clientId == null){
								 	produitCommandeVenteCourant.clientId = "";
								 }

								$log.debug("Produit  "+produitCommandeVenteCourant.produitId );
								 	if(produitCommandeVenteCourant.produitId == null){
								 	produitCommandeVenteCourant.produitId = "";
								 }

			        			url = baseUrlGc + "/report/rechercheBesoinArticleMulticritere?referenceBC=" + produitCommandeVenteCourant.refCommande
								 					 + "&clientId=" + produitCommandeVenteCourant.clientId
								 					 + "&produitId="+produitCommandeVenteCourant.produitId
													 + "&dateCommandeMin="+newdateIntroBCMinFormat
													 + "&dateCommandeMax="+newdateIntroBCMaxFormat
													 + "&dateLivraisonMin="+newdateLivBCMinFormat
													 + "&dateLivraisonMax="+newdateLivBCMaxFormat
													 + "&type=pdf";
				                  
				                $log.debug("-- URL" + url );
								 downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });
							}
						
							/***************************************************
							 * Gestion des Produits
							 **************************************************/
							 /***************************************************
    							 * Conversion Id / Designation 
    							 **************************************************/
              					// Produit 
              					$scope.produitId = {
            						status : ''
              					};
              					//SousFamilleProduit
              					$scope.sousFamilleProduitId = {
              						status : ''
                  				};

							 // showProduitDetails
              					$scope.showProduitDetails = function(produitId, attributRecherche) {
              						
              						$scope.produitId.status = produitId;
              						var selected = $filter('showProduitFilterBS')(
              								$scope.listProduitDrop, {
              									id : $scope.produitId.status
              								});
              						if ($scope.produitId.status && selected.length) {
              							if(attributRecherche == "reference"){
              								return selected[0].reference;
              							}
              							else if(attributRecherche == "tissu"){
              								return selected[0].designation;
              							}
              							else if(attributRecherche == "type"){
              								//conversion de sousFamilleId par son designation
              								$scope.sousFamilleProduitId.status = selected[0].sousFamilleId;
                      						var selected2= $filter('filterSousFamilleBS')(
                      								$scope.ListSousFamilleProduitCache , {
                      									id : $scope.sousFamilleProduitId.status
                      								});
                      						if ($scope.sousFamilleProduitId.status && selected2.length) {
                      							return selected2[0].designation;
                      						}else{
                      							return "--";
                      						}
              							}
              							
              						} 
              					}

							$scope.showBtnCalender = true;
							// show bottons Calender
							$scope.showBC = function() {
								$scope.showBtnCalender = true;
							}
							// GetId.designation Produit
						       $scope.designation = {

						        status : ''
						       };
						       $scope.showStatusDesignationProduit = function(id) {
						        $scope.designation.status = id;
						        var selected3 = $filter('filter')(
						          $scope.listProduitDrop, {
						           id : $scope.designation.status
						          });
						        if ($scope.designation.status && selected3.length) {
						         return selected3[0].designation;
						        }else {
						         return "Not Set";
						        }
						       };  
							
							// GetId.designation Produit
						       $scope.reference = {

						        status : ''
						       };
						       $scope.showStatusReferenceProduit = function(id) {
						        $scope.reference.status = id;
						        var selected2 = $filter('filter')(
						          $scope.listProduitDrop, {
						           id : $scope.reference.status
						          });
						        if ($scope.reference.status && selected2.length) {
						         return selected2[0].reference;
						        }else {
						                   return "Not Set";
						                  }
						       };   
						       
							// GetId.SousFamille Produit
						      
								$scope.type = {
									status : ''
								};

								$scope.famille = {
									status : ''
								};

								$scope.showStatusSousFamilleProduit = function(id) {
									$scope.famille.status = id;
									var selected = $filter('filter')(
											$scope.listProduitDrop, {
												id : $scope.famille.status
											});
									if ($scope.famille.status && selected.length) {

										$scope.type.status = selected[0].sousFamilleId;
										var selected2 = $filter('filter')(
												$scope.ListSousFamilleProduitCache, {
													id : $scope.type.status
												});
										return ($scope.type.status && selected2.length) ? selected2[0].designation
												: 'Not set';
									} else {
										return "NOT SET";
									}

								};
						       
						   
							// ajout d'un Produit
							$scope.ajoutProduit = function() {
								$scope.produitInserree = {
										produitId :'',
										prix:'',
										quantite:'',
										commandeVenteId:''
								};
								
								$scope.listProduitCommande
										.push($scope.produitInserree);
								
							};
							
							// Enregistrer Produit
							$scope.saveProduit = function(
									dataProduit, id) {
								$scope.deleteValue = "non";
								
								angular.extend(dataProduit, {
									id : id
								});
								$scope.showBtnCalender = false;
							};

							// Supprimer Produit
							$scope.removeProduitCommande = function(
									index) {
								$scope.listProduitCommande.splice(
										index, 1);
								$log.debug("Success Delete Produit ");
							};

							/** Fin de gestion des Produit */

							/***************************************************
							 * Gestion de la Grid Bon de Commande de Vente 
							 **************************************************/
							$scope.typeAlert = 3;
								
							$scope.colDefs = [];
							$scope.$watch(
								'myData',
									function() {
										$scope.colDefs = [
										{
											field : 'clientAbreviation',
											displayName : 'Client',
											width:270
										},
										{
											field : 'refCommandeVente',
											displayName : 'ref.BC',
											width:150
										},
										{
											field : 'referenceProduit',
											displayName : 'ref.Produit',
											width:150
										},
										{
											field : 'produitDesignation',
											displayName : 'Produit',
											width:270
										},
										{
											field : 'prix',
											displayName : 'prix',
											width:90
										},
										{
											field : 'quantite',
											displayName : 'quantite',
											width:90
										},
										{
											field : 'dateLivraison',
											displayName : 'Date Liv.',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:90
										},
										{
											field : '',
											width:40,
											cellTemplate :  '<div class="buttons">'
												+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(21)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
										}];
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
												var produitCommandeVenteCourant = $scope.produitCommandeVenteCourant;
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$http
															.post(
																	baseUrlGc + "/produitCommande/rechercheMulticritere",
																	produitCommandeVenteCourant)
															.success(
																	function(
																			largeLoad) {
																		data = largeLoad.listProduitCommandeValue
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
																	baseUrlGc + "/produitCommande/rechercheMulticritere",
														produitCommandeVenteCourant)
															.success(
																	function(
																			largeLoad) {
																		$scope
																				.setPagingData(
																						largeLoad.listProduitCommandeValue,
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

							
							/** Fin de gestion des Grids Vente BC */
						} ])
				.filter('showProduitFilterBS', function() {
				  return function(listeProduit, id) {
					 var listeProduitFiltre = [];
					//$log.debug("IdProduitt=  "+id.id);
					//$log.debug("listeProduit "+ JSON.stringify(listeProduit, null, "    "));
					 angular.forEach(listeProduit, function(produit, key){
						
						if(produit.id == id.id){
							//$log.debug(produit.id +" == "+ id.id);
							listeProduitFiltre.push(produit);
						}
							
					 });
					// $log.debug(" ** listeProduitFiltre "+ JSON.stringify(listeProduitFiltre, null, "    "));
					 return listeProduitFiltre;
				  };
				})

				.filter('filterSousFamilleBS', function() {
				  return function(listeSousFamille, id) {
					 var listeSousFamilleFiltre = [];
					// $log.debug("IdSousFamille=  "+id.id);
					// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
					 angular.forEach(listeSousFamille, function(sousFamille, key){
						
						if(sousFamille.id == id.id){
							//$log.debug(sousFamille.id +" == "+ id.id);
							listeSousFamilleFiltre.push(sousFamille);
						}
							
					 });
					// $log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
					 return listeSousFamilleFiltre;
				  };
				});
