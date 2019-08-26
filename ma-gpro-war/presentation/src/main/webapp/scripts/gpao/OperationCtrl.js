
'use strict'
angular
		.module('gpro.operations', [ "ngResource" ])
		.filter('unique', function() {
   return function(collection, keyname) {
      var output = [], 
          keys = [];

      angular.forEach(collection, function(item) {
          var key = item[keyname];
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);
//              $log.debug("test");
          }
      });
      return output;
   };
})
		.controller(
				'OperationController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'downloadService',
						'baseUrlGpao',
						'baseUrl',
						function($scope, $filter, $http,$log, downloadService,
								baseUrlGpao, baseUrl) {
							$log.info("----------- GPAO Operation > Catalogue ----------");
               
							// Déclaration des variables globales utilisées
							/** ***Liste desVariables **** */
							var data;
							
							$scope.displayMode = "list";
							$scope.operationCourante = {};
							
							
						
						
							$scope.operationCourante = {};
							$scope.listeMachines = [];
							$scope.listSections = [];
							

							/***************************************************
							 * Gestion des Operations*
							 **************************************************/
							$scope.deleteValue = "oui";
							// Annuler Ajout
							$scope.cancelAddOperation = function(rowform, index,
									id, designation, liste) {
								// $log.debug("* Designation
								// "+liste[0].designation);
								if (angular.isDefined(id)) {

									$log.debug("DEF ID");
									$scope.deleteValue = "non";
									rowform.$cancel();
									$log.debug("CANCEL");
								} else {
									$log.debug("UND ID  " + id);
									if (designation == "") {
										$scope.deleteValue == "oui"
										$log.debug("Designation : "
												+ designation);
										$log.debug("$scope.deleteValueOUI : "
												+ $scope.deleteValue);
										liste.splice(index, 1);
										rowform.$cancel();
										$log.debug("DELETE")
									} else {
										$log.debug("Designation :"
												+ designation);
										$log.debug("$scope.deleteValueNON : "
												+ $scope.deleteValue);
										rowform.$cancel();
										$log.debug("CANCEL");
									}
								}
								$scope.deleteValue = "oui";
							}
							// declaration variable
							$scope.displayAlert = "sleep";
							
							// Rechercher Operation
							$scope.rechercherOperation = function(operationCourante) {
                              
								$http
								   .post(
										   baseUrlGpao+"/catalogue/rechercheMulticritere",
								operationCourante)
										.success(
												function(resultat) {
													//$log.debug("recherchepppp"+$scope.operationCourante.designation+""+$scope.operationCourante.code)
													//console.debug();
													$scope.myData = resultat.list;
													//$log.debug("-----scope.myData operations!!!!!! : "+JSON.stringify($scope.myData));
													//data, page,pageSize

													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																					   $scope.pagingOptions.pageSize										
																					   );
													
													$scope.rechercheOperationForm.$setPristine();
													$scope.displayMode = "rechercher";
													$scope.displayAlert = "sleep";
													//$log.debug("recherche Operations 1..");
												});
							}
							

							
						//	$scope.rechercherOperation({});
							
							// Rechercher Operation by code
//							$scope.rechercherOperationByCode = function(code) {
//                              
//								$http
//								   .post(
//										   baseUrlGpao+"/catalogue/rechercheMulticritere",
//								operationCourante)
//										.success(
//												function(resultat) {
//													//$log.debug("recherchepppp"+$scope.operationCourante.designation+""+$scope.operationCourante.code)
//													//console.debug();
//													$scope.myData = resultat.list;
//													//$log.debug("-----scope.myData operations!!!!!! : "+JSON.stringify($scope.myData));
//													//data, page,pageSize
//
//													$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
//																					   $scope.pagingOptions.pageSize										
//																					   );
//													
//													$scope.rechercheOperationForm.$setPristine();
//													$scope.displayMode = "rechercher";
//													$scope.displayAlert = "sleep";
//													//$log.debug("recherche Operations 1..");
//												});
//							}
							
							// Ajout et Modification Operation
							$scope.modifierOuCreerOperation = function() {
								var index = this.row.rowIndex;
								// getOperation
								$http
										.get(
												baseUrlGpao
														+ "/catalogue/getById:"
														+ $scope.myData[index].id)
										.success(
												function(datagetOperation) {
																
													
                                                     $scope.code=datagetOperation.code;
                                                     $scope.designation=datagetOperation.designation;
                                                     $scope.sectionId=datagetOperation.sectionId;
                                                     $scope.temps=datagetOperation.temps;
                                                     $scope.machineId=datagetOperation.machineId;
                                                     $scope.observations=datagetOperation.observations;
                                                     $scope.pdh=datagetOperation.pdh;
                                                    $log.debug("modifier ou creer "+$scope.code+" "+$scope.designation+" "+$scope.sectionId+" "+$scope.temps+" "+$scope.machineId+" "+$scope.observations);
											
												});

								$scope.operationCourante = $scope.myData[index] ? angular
										.copy($scope.myData[index])
										: {};

								$scope.displayMode = "edit";
							}

							// Sauvegarder Operation
							$scope.sauvegarderOperation = function(operation) {
                                 
								
								if (angular.isDefined(operation.id)) {
								     $log.debug("id "+operation.id);
									$scope.updateOperation(operation);
									
								} else {
									
									$scope.creerOperation(operation);
									
								}
						
							}

							// Mise à jour des operations
							$scope.updateOperation = function(operation) {
								
								
								
								
								 operation.code=$scope.code;
                                 operation.designation=$scope.designation;
                                 operation.sectionId=$scope.sectionId;
                                 operation.temps=$scope.temps;
                                 operation.pdh=$scope.pdh;
                                 operation.machineId=$scope.machineId;
                                 operation.observations=$scope.observations;
                                 
                                //$log.debug(" "+$scope.code+" "+$scope.designation+" "+$scope.sectionId+" "+$scope.temps+" "+$scope.machineId+" "+$scope.observations);
						

								$http
										.put(
												baseUrlGpao
														+ "/catalogue/update",
												operation)
										.success(
												function(operationModifiee) {
													//TODO Code à revoir
													
													
													for (var i = 0; i < $scope.myData.length; i++) {

														if ($scope.myData[i].id == operationModifiee) {
															$scope.myData[i] = operationModifiee;
															
															break;
														}
													}
													//TODO getId
													$scope.annulerAjout();
												});
							}
							// Création Opération
							$scope.creerOperation= function(operation) {
								operation.code= $scope.code;
								operation.designation = $scope.designation;
								operation.machineId = $scope.machineId;
								operation.sectionId = $scope.sectionId;
								operation.temps = $scope.temps;
								operation.pdh = $scope.pdh;
								operation.observations = $scope.observations;
								$http
								.post(baseUrlGpao+"/catalogue/create",
								operation)
										.success(
												function(newOperation) {
												
													//TODO getId
													$scope.annulerAjout();
												});
								
							}
							
							// Annulation de l'ajout => vider tout les champs
							$scope.annulerAjout = function() {
								$scope.operationCourante = {  "code": "",
															  "designation": "",
															  "machineId": "",
															  "sectionId": ""
															};
								$scope.creationOperationForm.$setPristine();
								$scope.rechercheOperationForm.$setPristine();
								  $scope.code="";
                                  $scope.designation="";
                                  $scope.sectionId="";
                                  $scope.temps="";
                                  $scope.machineId="";
                                  $scope.pdh="";
                                  $scope.observations="";
								$scope.rechercherOperation({});
								$scope.displayMode = "list";
							}

							 // Suppression Operation
			                $scope.supprimerOperation = function() {
			                    $log.debug("INDEX" + $scope.index);
			                    $log.debug("**OBJET :" +
			                        $scope.myData[$scope.index]);
			                    $log.debug("DELETE .." +
			                        $scope.myData[$scope.index].id);
			                    $http({
			                        method: "DELETE",
			                        url: baseUrlGpao +
			                            "/catalogue/delete:" +
			                            $scope.myData[$scope.index].id
			                    }).success(function() {
			                        $scope.myData.splice($scope.index, 1);
			                        $scope.rechercherOperation({});
				                    $scope.closePopupDelete();
				                   //$scope.rechercherOperation({});
			                    });
			                   
//			                    $scope.rechercherOperation({});
//			                    $scope.closePopupDelete();
//			                    $scope.rechercherOperation({});
			                };

			                /*********** PDF ************/
			                //generer rapport de tous les Ordre de Fabrication. mode : List
              $scope.downloadAllCataloguesTemps = function(gammeOFCourante) {
                
                var url;
                
                //console.log("------- gammeOFCourante " + JSON.stringify(gammeOFCourante, null, "  ") );
                    
                    url = baseUrlGpao + "/report/listeCatalogue?ordreFabricationId="+gammeOFCourante.ordreFabricationId
                           + "&code=" + gammeOFCourante.code
                           + "&designation=" + gammeOFCourante.designation
                           + "&machineId="+gammeOFCourante.machineId
                           + "&sectionId="+gammeOFCourante.sectionId
                           + "&type=pdf";
                  
                        //console.log("-- URL--- :" + url );
                 downloadService.download(url).then(
                     function(success) {
                      //$log.debug('success : ' + success);
                     }, function(error) {
                      //$log.debug('error : ' + error);
                     });
               };




						
							$scope.pagingOptions = {
									pageSizes : [ 5, 10, 13 ],
									pageSize : 13,
									currentPage : 1
								};
							$scope.colDefs = [];
							
							$scope
									.$watch(
											'myData',
											function() {
												$scope.colDefs = [
														{
															field : 'designation',
															displayName : 'Opération',
															width:'20%'
														},
														{
															field : 'machineDesignation',
															displayName : 'Machine',
															width:'15%'
														},
														{
															field : 'sectionDesignation',
															displayName : 'Section',
															width:'15%'
														},
														{
															field : 'temps',
															displayName : 'Temps',
															width:'10%'
														},
														{
															field : 'pdh',
															displayName : 'PDH',
															width:'10%'
														},
														{
															field : 'observations',
															displayName : 'Observations',
															width:'10%'
														}
														,{
															field : '',
															width:'5%',
															cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerOperation()"><i class="fa fa-fw fa-pencil"></i></button>'
																	+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(22)"><i class="fa fa-fw fa-trash-o"></i></button></div>'
														} 
														];
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
								//$log.debug("$scope.setPagingData 2 "+$scope.myData);
								$scope.totalServerItems = data.length;
								if (!$scope.$$phase) {
									$scope.$apply();
								}
								
							};
							
							
							// Liste des machines
							$scope.listeMachines = function() {
								$http
                               .get(baseUrlGpao+"/machine/getAll")
										.success(
												function(dataPartieInteressee) {
													//$log.debug("liste machines : "+dataPartieInteressee.length);
													$scope.listeMachines = dataPartieInteressee;
//													$log.debug("liste des machines:"+JSON.stringify($scope.listeMachines));
													
												});
							}
							
 
							// Liste des sections
							$scope.listSections = function() {
								$http
								.get(baseUrlGpao+ "/section/getAll")
										.success(
												function(dataPartieInteressee) {
													//$log.debug("liste sections : "+dataPartieInteressee.length);
													$scope.listSections = dataPartieInteressee;
													//$log.debug("liste des sections:"+JSON.stringify($scope.listSections));
												});
							}
							
							
							$scope.getPagedDataAsync = function(pageSize, page,
									searchText) {
								setTimeout(
										function() {
											var data;
											var operationCourante= $scope.operationCourante;
											if (searchText) {
												var ft = searchText
														.toLowerCase();
												$http
														.post(
																baseUrlGpao
																		+ "/catalogue/rechercheMulticritere",
																		operationCourante)
														.success(
																function(
																		largeLoad) {
																	data = largeLoad.list
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
																baseUrlGpao
																		+ "/catalogue/rechercheMulticritere",
																		operationCourante)
														.success(
																function(
																		largeLoad) {
																	$scope
																			.setPagingData(
																					largeLoad.list,
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
							
							$scope.listeMachines();
							$scope.listSections();
							/** ***********Fin****************** */

						} ])
		.controller(
				'DatepickerDController2',
				[
						'$scope',
						function($scope) {
							$scope.maxToDay = new Date();
							// date piker defit
							// $scope.today = function() {
							// $scope.operationCourante.dateIntroduction = new
							// Date();
							// };
							// $scope.today();
							$scope.clear = function() {
								$scope.operationCourante.dateIntroduction = null;
							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20/08/2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];
						} ])
		.controller(
				'DatepickerControllerMin',
				[
						'$scope',
						function($scope) {
							// date piker defit
							$scope.today = function() {
								$scope.seuilApprovisionnement.dateDebut = new Date();
							};
							$scope.today();
							$scope.clear = function() {
								$scope.seuilApprovisionnement.dateDebut = null;
							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20/08/2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
	
		.controller(
				'DatepickerControllerMax',
				[
						'$scope',
						function($scope) {
							// date piker defit
							$scope.today = function() {
								$scope.seuilApprovisionnement.dateFin = new Date();
							};
							$scope.today();
							$scope.clear = function() {
								$scope.seuilApprovisionnement.dateFin = null;
							};
							// Disable weekend selection
							$scope.disabled = function(date, mode) {
								return (mode === 'day' && (date.getDay() === 0 || date
										.getDay() === 6));
							};
							$scope.toggleMin = function() {
								$scope.minDate = $scope.minDate ? null
										: new Date();
							};
							$scope.toggleMin();
							$scope.open = function($event) {
								$event.preventDefault();
								$event.stopPropagation();
								$scope.opened = true;
							};
							$scope.dateOptions = {
								formatYear : 'yy',
								startingDay : 1
							};
							$scope.initDate = new Date('20/08/2015');
							$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
									'dd.MM.yyyy', 'shortDate' ];
							$scope.format = $scope.formats[0];

						} ])
						
				
						

                                    
