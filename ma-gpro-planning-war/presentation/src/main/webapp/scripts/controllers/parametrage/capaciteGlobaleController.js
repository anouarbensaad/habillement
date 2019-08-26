'use strict'

angular
		.module('gpro.capaciteGlobale',
				[ 'mwl.calendar', 'ui.bootstrap', 'ngAnimate', 'ngSanitize' ])
		.controller(
				'capaciteGlobaleController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'$compile',
						'$rootScope',
						'moment',
						'$window',
						'baseUrlGpao',
						'baseUrl',
						function($scope, $filter, $http, $log, $compile,
								$rootScope, moment, $window,
								baseUrlGpao,baseUrl) {
							$scope.exist= false ;
						

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
															displayName : 'id',
															visible : false
														},          
														{
															field : 'annee',
															displayName : 'Année'
														},
														{
															field : 'atelierName',
															displayName : 'Atelier'
														},
														{
															field : 'capaciteTotale',
															displayName : ' Capacité Totale'
														},

														{
															field : '',
															cellTemplate :  '<div class="buttons">'
																+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreer()"><i class="fa fa-fw fa-pencil"></i></button>'
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

							$scope.getPagedDataAsync = function(pageSize, page,
									searchText) {
								setTimeout(function() {
									if (searchText) {
										var ft = searchText.toLowerCase();

									} else {

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
							 * ********************** OF ********************/

							// Rechercher Ordre Fabrication
							$scope.rechercher = function(objectCourant) {
								
								$http
										.post(
												baseUrlGpao
														+ "/capaciteGlobale/rechercheMulticritere",
														objectCourant)
										.success(
												function(resultat) {
													$scope.myData = resultat.list;
													for(var i=0 ; i< $scope.myData.length; i++){
														 $scope.getAtelierName($scope.myData[i].idAtelier, i);
													}
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
							
						
							$scope.modifierOuCreer = function() {
								var index = this.row.rowIndex;

								$http
										.get(
												baseUrlGpao
														+ "/capaciteGlobale/getById:"
														+ $scope.myData[index].id)
										.success(
												function(data) {
													$scope.objectCourant = data;
													$scope.listCapaciteSemaine = data.listCapaciteSemaine;
													

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
							//	$scope.rechercherArticle({});
							}
							$scope.testExist = function( element, arrayTable){
								var i = 0;
								for(var j = 0; j < arrayTable.length; j++ ){
									if (arrayTable[j].semaine == element){
										i++ ;
									}
								}
								return i ; 
							}
							$scope.creer = function(objetCourant) {
								if ($scope.listCapaciteSemaine[0].semaine == 'Tous') {
									objetCourant.listCapaciteSemaine = [];
									for(var i=1; i<53 ; i++){
										var params ={
												capaciteTotale: $scope.listCapaciteSemaine[0].capaciteTotale,
												id: '',
												semaine: i
												
										} 
										objetCourant.listCapaciteSemaine.push(params);
									}
									
								}else{
									objetCourant.listCapaciteSemaine = $scope.listCapaciteSemaine;
								}
								//objetCourant.listCapaciteSemaine = $scope.listCapaciteSemaine;
								for(var j= 0; j < objetCourant.listCapaciteSemaine.length; j++ ){
									if (objetCourant.listCapaciteSemaine[j].id == '') {
										if($scope.testExist(objetCourant.listCapaciteSemaine[j].semaine,objetCourant.listCapaciteSemaine)>1){
											console.log("existe" + objetCourant.listCapaciteSemaine[j]);
											$scope.exist= true ;
											$scope.msg="Attention la semaine N°: "+objetCourant.listCapaciteSemaine[j].semaine+" existe dans la liste ";
											return false ;
										}
										
									}
								}
								
								$http
								.post(
										baseUrlGpao
												+ "/capaciteGlobale/creer",
										objetCourant)
								.success(
										function(newId) {
											
											objetCourant.id = newId;
											$scope.myData.unshift(objetCourant);
											for(var i=0 ; i< $scope.myData.length; i++){
												 $scope.getAtelierName(objetCourant.idAtelier, i);
											}
											$scope.annulerAjout();
											
										});
							}
							
							// Mise à jour des ordres de Fabrications
							$scope.update = function(objetCourant) {
								
								objetCourant.listCapaciteSemaine = $scope.listCapaciteSemaine;
								for(var j= 0; j < objetCourant.listCapaciteSemaine.length; j++ ){
									if (objetCourant.listCapaciteSemaine[j].id == '') {
										if($scope.testExist(objetCourant.listCapaciteSemaine[j].semaine,objetCourant.listCapaciteSemaine)>1){
											console.log("existe" + objetCourant.listCapaciteSemaine[j]);
											$scope.exist= true ;
											$scope.msg="Attention la semaine n°:"+objetCourant.listCapaciteSemaine[j].semaine+" existe dans la liste ";
											return false ;
										}
										
									}
								}			
								
								
								$http
										.post(
												baseUrlGpao
														+ "/capaciteGlobale/modifier",
														objetCourant)
										.success(
												function(modifiedId) {
												
													 for (var i = 0; i < $scope.myData.length; i++) {

							                             if ($scope.myData[i].id == modifiedId) {
							                                 $scope.myData[i] = objetCourant;
							                                 $scope.getAtelierName(objetCourant.idAtelier, i);
							                                 break;
							                             }
							                         }
													 


													 
													$scope.annulerAjout();
													
												});
								
							}
		// ************** ici changement 
								$scope.annulerAjout = function() {
									$scope.exist = false ;
									$scope.init();
									$scope.objectCourant = {};
								$scope.creationOFForm.$setPristine();
								$scope.rechercheOFForm.$setPristine();
								

								$scope
										.setPagingData(
												$scope.myData,
												$scope.pagingOptions.currentPage,
												$scope.pagingOptions.pageSize);
								
								$scope.displayMode = "list";
							}
							
							// Liste des SiteeCache
							$scope.listeSitesPartieInteresseeCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSitePartieInteresseeCache")
										.success(
												function(dataSiteCache) {
													$scope.listeSitePartieInteresseeCache = dataSiteCache;
													$scope.rechercher({});

												});
							}
							
							
							$scope.listeAnneess = [];
							$scope.getListeAnnees = function(){
								
								for(var i=2018; i<2051 ; i++){
									$scope.listeAnneess.push(i);
								}
								
							}
							
							$scope.listeSemaines = [];
							$scope.getListeSemaines = function(){
								$scope.listeSemaines.push('Tous');
								for(var i=1; i<53 ; i++){
									$scope.listeSemaines.push(i);
								}
								
							}
							
							$scope.getAtelierName = function(atelierId, index){
								var atelier = $scope.listeSitePartieInteresseeCache.filter(
									function(element){
										return element.id == atelierId;
										
								});
								$scope.myData[index].atelierName = atelier && atelier.length > 0 ? atelier[0].nom : '-'; 
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

							
							$scope.ajoutCapaciteSemaine= function() {

								$scope.objetInsere = {

									id:'',
									semaine:'',
									capaciteTotale:''

								};
								$scope.listCapaciteSemaine
										.push($scope.objetInsere);
							};
							
							/** *********** Init ************** */

							$scope.init = function() {
								
								$scope.displayMode = "list";
								
								
								$scope.listeSitesPartieInteresseeCache();
								$scope.getListeAnnees();
								$scope.getListeSemaines();
								$scope.listCapaciteSemaine=[];
								
							}

							$scope.init();
						} ])
