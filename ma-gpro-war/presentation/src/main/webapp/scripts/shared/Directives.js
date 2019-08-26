'use strict'

angular
		.module('theme.directives', ['config'])
		.directive('disableAnimation', [ '$animate', function($animate) {
			return {
				restrict : 'A',
				link : function($scope, $element, $attrs) {
					$attrs.$observe('disableAnimation', function(value) {
						$animate.enabled(!value, $element);
					});
				}
			}
		} ])
		.directive('slideOut', function() {
			return {
				restrict : 'A',
				scope : {
					show : '=slideOut'
				},
				link : function(scope, element, attr) {
					element.hide();
					scope.$watch('show', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							element.slideToggle({
								complete : function() {
									scope.$apply();
								}
							});
						}
					});
				}
			}
		})
		.directive('slideOutNav', [ '$timeout', function($t) {
			return {
				restrict : 'A',
				scope : {
					show : '=slideOutNav'
				},
				link : function(scope, element, attr) {
					scope.$watch('show', function(newVal, oldVal) {
						if ($('body').hasClass('collapse-leftbar')) {
							if (newVal == true)
								element.css('display', 'block');
							else
								element.css('display', 'none');
							return;
						}
						if (newVal == true) {
							element.slideDown({
								complete : function() {
									$t(function() {
										scope.$apply()
									})
								}
							});
						} else if (newVal == false) {
							element.slideUp({
								complete : function() {
									$t(function() {
										scope.$apply()
									})
								}
							});
						}
					});
				}
			}
		} ])
		.directive('panel', function() {
			return {
				restrict : 'E',
				transclude : true,
				scope : {
					panelClass : '@',
					heading : '@',
					panelIcon : '@'
				},
				templateUrl : 'templates/panel.html',
			}
		})
		.directive('pulsate', function() {
			return {
				scope : {
					pulsate : '='
				},
				link : function(scope, element, attr) {
					// stupid hack to prevent FF from throwing error
					if (element.css('background-color') == "transparent") {
						element.css('background-color', "rgba(0,0,0,0.01)");
					}
					$(element).pulsate(scope.pulsate);
				}
			}
		})
		.directive('prettyprint', function() {
			return {
				restrict : 'C',
				link : function postLink(scope, element, attrs) {
					element.html(prettyPrintOne(element.html(), '', true));
				}
			};
		})
		.directive(
				"passwordVerify",
				function() {
					return {
						require : "ngModel",
						scope : {
							passwordVerify : '='
						},
						link : function(scope, element, attrs, ctrl) {
							scope.$watch(function() {
								var combined;

								if (scope.passwordVerify || ctrl.$viewValue) {
									combined = scope.passwordVerify + '_'
											+ ctrl.$viewValue;
								}
								return combined;
							}, function(value) {
								if (value) {
									ctrl.$parsers.unshift(function(viewValue) {
										var origin = scope.passwordVerify;
										if (origin !== viewValue) {
											ctrl.$setValidity("passwordVerify",
													false);
											return undefined;
										} else {
											ctrl.$setValidity("passwordVerify",
													true);
											return viewValue;
										}
									});
								}
							});
						}
					};
				})
		.directive(
				'backgroundSwitcher',
				function() {
					return {
						restrict : 'EA',
						link : function(scope, element, attr) {
							$(element).click(
									function() {
										$('body').css('background',
												$(element).css('background'));
									});
						}
					};
				})
		.directive('panelControls', [ function() {
			return {
				restrict : 'E',
				require : '?^tabset',
				link : function(scope, element, attrs, tabsetCtrl) {
					var panel = $(element).closest('.panel');
					if (panel.hasClass('.ng-isolate-scope') == false) {
						$(element).appendTo(panel.find('.options'));
					}
				}
			};
		} ])
		.directive(
				'panelControlCollapse',
				function() {
					return {
						restrict : 'EAC',
						link : function(scope, element, attr) {
							element.bind('click', function() {
								$(element).toggleClass(
										"fa-chevron-down fa-chevron-up");
								$(element).closest(".panel")
										.find('.panel-body').slideToggle({
											duration : 200
										});
								$(element).closest(".panel-heading")
										.toggleClass('rounded-bottom');
							})
							return false;
						}
					};
				})
		.directive(
				'icheck',
				function($timeout, $parse) {
					return {
						require : '?ngModel',
						link : function($scope, element, $attrs, ngModel) {
							return $timeout(function() {
								var parentLabel = element.parent('label');
								if (parentLabel.length)
									parentLabel.addClass('icheck-label');
								var value;
								value = $attrs['value'];

								$scope.$watch($attrs['ngModel'], function(
										newValue) {
									$(element).iCheck('update');
								})

								return $(element)
										.iCheck(
												{
													checkboxClass : 'icheckbox_minimal-blue',
													radioClass : 'iradio_minimal-blue'

												})
										.on(
												'ifChanged',
												function(event) {
													if ($(element).attr('type') === 'checkbox'
															&& $attrs['ngModel']) {
														$scope
																.$apply(function() {
																	return ngModel
																			.$setViewValue(event.target.checked);
																});
													}
													if ($(element).attr('type') === 'radio'
															&& $attrs['ngModel']) {
														return $scope
																.$apply(function() {
																	return ngModel
																			.$setViewValue(value);
																});
													}
												});
							});
						}
					};
				})
		.directive('knob', function() {
			return {
				restrict : 'EA',
				template : '<input class="dial" type="text"/>',
				scope : {
					options : '='
				},
				replace : true,
				link : function(scope, element, attr) {
					$(element).knob(scope.options);
				}
			}
		})
		.directive('uiBsSlider', [ '$timeout', function($timeout) {
			return {
				link : function(scope, element, attr) {
					// $timeout is needed because certain wrapper directives
					// don't
					// allow for a correct calculaiton of width
					$timeout(function() {
						element.slider();
					});
				}
			};
		} ])
		.directive('tileLarge', function() {
			return {
				restrict : 'E',
				scope : {
					item : '=data'
				},
				templateUrl : 'templates/tile-large.html',
				replace : true,
				transclude : true
			}
		})
		.directive('tileMini', function() {
			return {
				restrict : 'E',
				scope : {
					item : '=data'
				},
				replace : true,
				templateUrl : 'templates/tile-mini.html'
			}
		})
		.directive('tile', function() {
			return {
				restrict : 'E',
				scope : {
					heading : '@',
					type : '@'
				},
				transclude : true,
				templateUrl : 'templates/tile-generic.html',
				link : function(scope, element, attr) {
					var heading = element.find('tile-heading');
					if (heading.length) {
						heading.appendTo(element.find('.tiles-heading'));
					}
				},
				replace : true
			}
		})
		.directive('jscrollpane', [ '$timeout', function($timeout) {
			return {
				restrict : 'A',
				scope : {
					options : '=jscrollpane'
				},
				link : function(scope, element, attr) {
					$timeout(function() {
						if (navigator.appVersion.indexOf("Win") != -1)
							element.jScrollPane($.extend({
								mouseWheelSpeed : 20
							}, scope.options))
						else
							element.jScrollPane(scope.options);
						element.on('click', '.jspVerticalBar', function(event) {
							event.preventDefault();
							event.stopPropagation();
						});
						element.bind('mousewheel', function(e) {
							e.preventDefault();
						});
					});
				}
			};
		} ])
		// specific to app
		.directive('stickyScroll', function() {
			return {
				restrict : 'A',
				link : function(scope, element, attr) {
					function stickyTop() {
						var topMax = parseInt(attr.stickyScroll);
						var headerHeight = $('header').height();
						if (headerHeight > topMax)
							topMax = headerHeight;
						if ($('body').hasClass('static-header') == false)
							return element.css('top', topMax + 'px');
						var window_top = $(window).scrollTop();
						var div_top = element.offset().top;
						if (window_top < topMax) {
							element.css('top', (topMax - window_top) + 'px');
						} else {
							element.css('top', 0 + 'px');
						}
					}

					$(function() {
						$(window).scroll(stickyTop);
						stickyTop();
					});
				}
			}
		})
		.directive(
				'rightbarRightPosition',
				function() {
					return {
						restrict : 'A',
						scope : {
							isFixedLayout : '=rightbarRightPosition'
						},
						link : function(scope, element, attr) {
							scope.$watch('isFixedLayout', function(newVal,
									oldVal) {
								if (newVal != oldVal) {
									setTimeout(
											function() {
												var $pc = $('#page-content');
												var ending_right = ($(window)
														.width() - ($pc
														.offset().left + $pc
														.outerWidth()));
												if (ending_right < 0)
													ending_right = 0;
												$('#page-rightbar').css(
														'right', ending_right);
											}, 100);
								}
							});
						}
					};
				})
		.directive(
				'fitHeight',
				[
						'$window',
						'$timeout',
						'$location',
						function($window, $timeout, $location) {
							return {
								restrict : 'A',
								scope : true,
								link : function(scope, element, attr) {
									scope.docHeight = $(document).height();
									var setHeight = function(newVal) {
										var diff = $('header').height();
										if ($('body').hasClass(
												'layout-horizontal'))
											diff += 112;
										if ((newVal - diff) > element
												.outerHeight()) {
											element.css('min-height',
													(newVal - diff) + 'px');
										} else {
											element.css('min-height', $(window)
													.height()
													- diff);
										}
									};
									scope.$watch('docHeight', function(newVal,
											oldVal) {
										setHeight(newVal);
									});
									$(window).on('resize', function() {
										setHeight($(document).height());
									});
									var resetHeight = function() {
										scope.docHeight = $(document).height();
										$timeout(resetHeight, 1000);
									}
									$timeout(resetHeight, 1000);
								}
							};
						} ])
		.directive('jscrollpaneOn', [ '$timeout', function($timeout) {
			return {
				restrict : 'A',
				scope : {
					applyon : '=jscrollpaneOn'
				},
				link : function(scope, element, attr) {
					scope.$watch('applyon', function(newVal) {
						if (newVal == false) {
							var api = element.data('jsp');
							if (api)
								api.destroy();
							return;
						}
						$timeout(function() {
							element.jScrollPane({
								autoReinitialise : true
							});
						});
					});
				}
			};
		} ])
		.directive('backToTop', function() {
			return {
				restrict : 'AE',
				link : function(scope, element, attr) {
					element.click(function(e) {
						$('body').scrollTop(0);
					});
				}
			}
		})

		.directive(
				'popUpp',[ 'baseUrl','baseUrlGs',	function($compile, baseUrl, baseUrlGs) {
					return {
						restrict : 'E',
						templateUrl : 'views/templates/popup.html',
						link : function(scope, element, attr, model) {

						},
						controller : function($rootScope,$scope, $element, $http,$resource, baseUrl,baseUrlGs ) {
							// declaration variables
							$scope.matrix = [ [], [], [], [], [] ];
							$scope.elementValueRow = [];
							$scope.elementNGgridShow = true;
							$scope.elementNGgridShowFAC = true;
							$scope.elementNGgridShowTissu = true;
							$scope.mySelections = [];
							$rootScope.mySelectionsETissuTmp = [];
                            $rootScope.mySelectionsFTemp =[];
                             $rootScope.mySelectionsEFACtemp =[];
							//$scope.mySelectionsETissuTmp = [];
							$scope.mySelectionsTissu = [];
							$scope.mySelectionsETissu = [];
							$scope.mySelectionsFAC = [];
						

							$scope.gridTissusS= {};
							$scope.gridFournituresS= {};
							$scope.gridFilaCoudresS = {};
							$scope.gridTissusE= {};
							// liste de tous les types des articles
							$scope.messages = [
									"Suppression article",
									"Suppression PI",
									"",
									"Suppression Produit", 
									"Suppression Stock",""];

							// show Popup Taille Color
							$scope.showPopupTailleColor = function(type,idArticle, ref, designation, famille, sousFamille, index) {
								console.log("ref :"+ref);
								$scope.indexRowToDelete = index;
								console.log("indexRowToDelete :"+$scope.indexRowToDelete);
								$scope.message = 14;
								$scope.articleFicheBesoin = {
									id : idArticle,
									ref : ref,
									designation : designation,
									familleArticleDesignation : famille,
									sousFamilleArtEntiteDesignation:sousFamille
								};
								$scope.message = type;
								$('div[id=light]').fadeIn("slow");
								$('#lightS').fadeIn("slow");
								$('#lightS').draggable();
								$('div[id=light]').draggable();
								$('div[id=fade]').fadeIn("slow");
							}
							// show POPUP
							$rootScope.showPopupDelete = function(type, id) {
								switch (type) {
								case 0:
									// suppression article
									$scope.index = this.row.rowIndex;
									$scope.message = 0;
									break;
								case 1:
									// suppression partie interessée
									$scope.index = this.row.rowIndex;
									$scope.message = 1;
									break;
								case 2:
									// email
									$scope.message = 2;
									break;
								case 3:
									// suppression produit
									$scope.index = this.row.rowIndex;
									$scope.message = 3;
									break;
								case 4:
									//Suppression Stock entree
									$scope.index = this.row.rowIndex;
									$scope.message = 4;
									break;
								case 5:
									
									console.log("----entree stock case 5 N1-------");
									$scope.stockEntreeFournitureForm
											.$setPristine();
									if ($scope.listArticlesFournituresE != null) { 
										for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournituresE[i].idArticle);
										}
									}
									$scope.gridFournituresE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 5;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 1
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);
									break;
								case 6:
									// MVT-STOCK SORTIE Fourniture
									console.log("popUpp :case : 6. Sortie Fourniture");
									$scope.gridFournituresS.$gridScope
											.toggleSelectAll(false, true);
									if ($scope.listArticlesFournitures != null ){
									 for(var i = 0; i < $scope.listArticlesFournitures.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournitures[i].entiteStock);
										}
									}
									$scope.entiteStockFournitureCourante = {};
									$scope.message = 6;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.entiteStockFournitureCourante = {
										magasin : id,
										typeArticle : "fourniture"
									};
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 7:
									// MVT-STOCK SORTIE TISSU
									console.log("popUpp :case : 7. Sortie TISSU");
									$scope.gridFournituresS.$gridScope
											.toggleSelectAll(false, true);
									if ($scope.listArticlesTissus != null) { 
										console.log('test tissu sortie')
										for (var i = 0; i < $scope.listArticlesTissus.length; i++) {

											$scope.idSelectionnee
													.push($scope.listArticlesTissus[i].entiteStock);
										}
									}
									$scope.entiteStockFournitureCourante = {};
									$scope.message = 7;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.entiteStockFournitureCourante = {
										magasin : id,
										typeArticle : "tissu"
									};
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 8:
									// MVT-STOCK SORTIE Fil A Coudre
									console.log("popUpp :case : 8. Sortie Fil A Coudre");
									$scope.gridFournituresS.$gridScope
											.toggleSelectAll(false, true);
									if ($scope.listArticlesFilaCoudres != null) { 
										for (var i = 0; i < $scope.listArticlesFilaCoudres.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFilaCoudres[i].entiteStock);
										}
									}
									$scope.entiteStockFournitureCourante = {};
									$scope.message = 8;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.entiteStockFournitureCourante = {
										magasin : id,
										typeArticle : "fil à coudre"
									};
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 9:
									// MVT-STOCK ENTREE TISSU
									console.log("popUpp :case Entree : 9");
									$scope.gridTissusE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 9;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 2
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);
									break;
								case 10:
									// MVT-STOCK ENTREE Fil A Coudre
									console.log("popUpp :case Entree : 10");
									$scope.gridFilaCoudresE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 10;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 3
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);
									break;
								case 11:
									// FicheBesoin Fourniture
//									$scope.gridFournituresE.$gridScope
//									.toggleSelectAll(false, true);
//									$scope.articleElementFicheBesoinCourant = {};
//									if ($scope.listArticlesFournituresE != null) { 
//										for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
//											$scope.idSelectionnee
//													.push($scope.listArticlesFournituresE[i].article.id);
//											
//										}
//									}
//									$scope.message = 11;
//									$scope.articleElementFicheBesoinCourant = {
//										typeEntite : 1
//									};
//									$scope.rechercheArticleEntree(
//											$scope.articleElementFicheBesoinCourant, 1);
									console.log("supprimer fourniture FB D1");
									$scope.message = 11;
									$scope.index = this.row.rowIndex;
									break;
								case 12:
									// FicheBesoin TISSU
									$scope.articleFicheBesoin = {};
									$scope.message = 12;
									$scope.articleFicheBesoin = {
										typeEntite : 2
									};
									$scope.rechercheArticleEntree(
											$scope.articleFicheBesoin, 1);
									break;
								case 13:
									// FicheBesoin Fil A Coudre
									$scope.articleFicheBesoin = {};
									$scope.message = 13;
									$scope.articleFicheBesoin = {
										typeEntite : 3
									};
									$scope.rechercheArticleEntree(
											$scope.articleFicheBesoin, id);
									break;
								case 14:
									// MVT-STOCK ENTREE Fourniture
									/*$scope.stockEntreeFournitureForm.$setPristine();
									if ($scope.listArticlesFournituresE != null) { 
										for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournituresE[i].idArticle);
										}
									}
									$scope.gridFournituresE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 5;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 1
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);*/
									break;
								case 15:
									// suppression partie interessée
									console.log("popUpp");
									$scope.index = this.row.rowIndex;
									$scope.message = 15;
									break;
								}
								$('div[id=light]').fadeIn("slow");
								$('#lightS').fadeIn("slow");
								$('#lightS').draggable();
								$('div[id=light]').draggable();
								$('div[id=fade]').fadeIn("slow");
							}
							
							
							
							// close POPUP
							$scope.closePopupDelete = function(type) {
								$('div[id=fade]').css("display", "none");
								$('#lightS').fadeOut();
								$('div[id=light]').fadeOut();
								$('#fade').fadeOut();
							}
							// vider Form
							// vider Form Sortie
							$scope.viderFormSortie = function(type) {
								console.log("--PopUpP :viderFormSortie..");
								var obj = {
									typeArticle : type
								};

								//relancer la recherche du tableau
								//TODO: séparer viderForm Entrée du viderForm Sortie
								$http   .post( baseUrlGs
														+ "/entiteStock/rechercheEntiteStockMouvement",
												obj)
										.success(
												function(resultat) {
													console.log("refresh popup Sortie ..  "+ resultat.entiteStockMouvement.length);
													$scope.myDataEntite = resultat.entiteStockMouvement;
													//vider l'objet de recherche
													$scope.entiteStockFournitureCourante = {
														typeArticle : type
													};

													$scope.stockSortieFournitureForm.$setPristine();
													$scope.stockSortieTissuForm.$setPristine();
													$scope.stockSortieFilForm.$setPristine();

												});
							}
							
							// Rechercher Articles Fournitures par Magasin
							$scope.articleFournitureCourante = {
								designation : "art 0"
							};
							$scope.myDataFourniture = [];

							$scope.rechercheArticleEntree = function(
									articleEntree, id) {
								var widhout = [];
								$scope.myDataEntite = [];
								var inter2 = 0;
								$http
										.post(
												baseUrlGs
														+ "/articleEntree/rechercheArticleEntreeMultiCritere",
												articleEntree)
										.success(
												function(resultat) {
                                                    console.log("$scope.idSelectionneep : "+$scope.idSelectionnee);
													widhout = resultat.articleEntree;
													if ($scope.idSelectionnee != null) {
														for (var i = 0; i < widhout.length; i++) {
															console.log("widhout[i].id : "+widhout[i].id);
															for (var j = 0; j < $scope.idSelectionnee.length; j++) {
																if ($scope.idSelectionnee[j] == widhout[i].id) {
																	inter2 = $scope.idSelectionnee[j];
																}
															}
															if (widhout[i].id != inter2)
																$scope.myDataEntite
																		.push(widhout[i]);
														}
													} else {
														$scope.myDataEntite = widhout;
														$scope.idSelectionnee = [];
													}
													
												});
							}
							$scope.rechercheEntiteStockMouvement = function(
									entiteStock) {
								var widhout = [];
								$scope.myDataEntite = [];
								var inter2 = 0;
								$http
										.post(
												baseUrlGs
														+ "/entiteStock/rechercheEntiteStockMouvement",
												entiteStock)
										.success(
												function(resultat) {
													console.log("$scope.idSelectionnee  : "+$scope.idSelectionnee);
													widhout = resultat.entiteStockMouvement;
													if($scope.idSelectionnee != null){
														for (var i = 0; i < widhout.length; i++) {
															console.log("widhout[i].id : "+widhout[i].entiteStockId);
															for (var j = 0; j < $scope.idSelectionnee.length; j++) {
																if ($scope.idSelectionnee[j] == widhout[i].entiteStockId) {
																	inter2 = $scope.idSelectionnee[j];
																}
															}
															if (widhout[i].entiteStockId != inter2)
																$scope.myDataEntite
																		.push(widhout[i]);
														}
													}else{
													$scope.myDataEntite = resultat.entiteStockMouvement;}
													$scope.idSelectionnee = [];
												});
							}
							// recherche Mouvement par critere
							$scope.rechercheMvtParCritere = function(type) {
								switch (type) {
								case 0:
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 1:
									$scope.rechercheArticleEntree($scope.articleEntree,$scope.idMagasinFourniture);
									break;
								case 2:
									$scope.rechercheArticleEntree($scope.articleEntree, 1);
									break;
								}
							}
							/***************************************************
							 * Gestion des Grids Fourniture Entree
							 **************************************************/
							$scope.gridFournituresE = {
								data : 'myDataEntite',
								columnDefs : [
										{
											field : 'ref',
											displayName : 'Réf.'
										},
										{
											field : 'designation',
											displayName : 'Désignation',
											width: "40%"
										},
										
										{
											field : 'famille',
											displayName : 'Famille'
										},
										{
											field : 'unite',
											displayName : 'Unité'
										},
										{
											field : 'pmp',
											displayName : 'PMP'
										},
										{
											field : 'quantiteActuelle',
											displayName : 'Q.Act'
										},
										
										{
											field : '',
											cellTemplate : '<span class="rawform-icon2"></span>'
										} ],
								showSelectionCheckbox : true,
								beforeSelectionChange : $scope.beforeVehicleSelectionChange,
								checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelected" ng-change="toggleSelectAll(allSelected, true)"/>',
								selectedItems :$rootScope.mySelectionsETissuTmp,
								selectWithCheckboxOnly : false,
								multiSelect : true

							};
							/** Fin de gestion des Grids Fourniture Entree */
							/***************************************************
							 * Gestion des Grids Tissu Entree
							 **************************************************/
							$scope.gridTissusEtemp = {
								data : 'myDataEntite',
								columnDefs : [
										{
											field : 'ref',
											displayName : 'Réf.'
										},
										{
											field : 'designation',
											displayName : 'Désignation',
											width: "40%"
										},
										
										{
											field : 'sousFamille',
											displayName : 'S.Famille'
										},
										{
											field : 'unite',
											displayName : 'Unité'
										},
										{
											field : 'pmp',
											displayName : 'PMP'
										},
										{
											field : 'quantiteActuelle',
											displayName : 'Q.Act'
										},
										
										{
											field : '',
											cellTemplate : '<span class="rawform-icon2"></span>'
										} ],
								showSelectionCheckbox : true,
								beforeSelectionChange : $scope.beforeVehicleSelectionChange,
								checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelected" ng-change="toggleSelectAll(allSelected, true)"/>',
								selectedItems : $rootScope.mySelectionsFTemp,
								selectWithCheckboxOnly : false,
								multiSelect : true

							};
							/** Fin de gestion des Grids Tissu Entree */
							/***************************************************
							 * Gestion des Grids Fil A coudre Entree
							 **************************************************/
							$scope.gridFilaCoudresE = {
								data : 'myDataEntite',
								columnDefs : [
										{
											field : 'ref',
											displayName : 'Réf.'
										},
										{
											field : 'designation',
											displayName : 'Désignation',
											width: "40%"
										},
										
										{
											field : 'famille',
											displayName : 'Famille'
										},
										{
											field : 'unite',
											displayName : 'Unité'
										},
										{
											field : 'pmp',
											displayName : 'PMP'
										},
										{
											field : 'cone',
											displayName : 'Q.Act'
										},
										{
											field : '',
											displayName : 'P.T'
										},
										{
											field : '',
											cellTemplate : '<span class="rawform-icon2"></span>'
										} ],
								showSelectionCheckbox : true,
								beforeSelectionChange : $scope.beforeVehicleSelectionChange,
								checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelectedFilAcoudre" ng-change="toggleSelectAll(allSelectedFilAcoudre, true)"/>',
								selectedItems : $rootScope.mySelectionsEFACtemp,
								selectWithCheckboxOnly : false,
								multiSelect : true

							};
							/** Fin de gestion des Grids Fil A coudre Entree */
							
							// showSelectedInfo Entree
							$scope.showSelectedInfoPopUpEntree = function(type) {
								$scope.objectInter = {
									entiteStock : "",
									referenceArticle : "",
									designationArticle : "",
									quantite : "",
									quantiteReelle : "",
									typeArticle : "",
									type : "",
									nouveau:true
								};
								$scope.mouvementFourniture = [];
								$scope.mouvementFournitureE = [];
								$scope.mouvementTissu = [];
								$scope.mouvementTissusE = [];
								$scope.mouvementFilaCoudre = [];
								$scope.mouvementEFAC = [];
								switch (type) {
							
									case 3:
										for (var i = 0; i < $rootScope.mySelectionsETissuTmp.length; i++) {
												$scope.objectInter.entiteStock = $rootScope.mySelectionsETissuTmp[i].entiteStockId;
												$scope.objectInter.referenceArticle = $rootScope.mySelectionsETissuTmp[i].ref;
												$scope.objectInter.designationArticle = $rootScope.mySelectionsETissuTmp[i].designation;
												$scope.objectInter.familleArticle = $rootScope.mySelectionsETissuTmp[i].famille;
												$scope.objectInter.sousFamilleArticle = $rootScope.mySelectionsETissuTmp[i].sousFamille;
												$scope.objectInter.quantite = $rootScope.mySelectionsETissuTmp[i].quantite;
												$scope.objectInter.quantiteActuelle = $rootScope.mySelectionsETissuTmp[i].quantiteActuelle;
												$scope.objectInter.quantiteReelle = $rootScope.mySelectionsETissuTmp[i].qteR;
												$scope.objectInter.typeArticle = 1;
												$scope.objectInter.type = "ENTREE";
												$scope.objectInter.nouveau = true;
												$scope.objectInter.idMagasin = $scope.idMagasinFourniture;
												
												$scope.objectInter.idArticle = $rootScope.mySelectionsETissuTmp[i].id;
												$scope.objectInter.typeElementFicheBesoin = 1;
												$scope.objectInter.article = {
																			id: $rootScope.mySelectionsETissuTmp[i].id,
																	        ref: $rootScope.mySelectionsETissuTmp[i].ref,
																	        designation: $rootScope.mySelectionsETissuTmp[i].designation,
																	        sousFamilleArtEntiteDesignation: $rootScope.mySelectionsETissuTmp[i].sousFamille,
																	        familleArticleDesignation: $rootScope.mySelectionsETissuTmp[i].famille,
																	        quantie:$rootScope.mySelectionsETissuTmp[i].quantite ,
																	        idmagasin : $scope.idMagasinFourniture
																			};
												$scope.mouvementFournitureE[i] = $scope.objectInter;
												
												$scope.objectInter = {};
											}
											
											console.log($scope.idMagasinFourniture);
											
											for (var i = 0; i < $rootScope.mySelectionsETissuTmp.length; i++) {
												console.log("$scope.mouvementFournitureE  : "+$scope.mouvementFournitureE[i].article);
												$scope.listArticlesFournituresE
														.push($scope.mouvementFournitureE[i]);
												console.log("$scope.listArticlesFournituresE  : "+$scope.listArticlesFournituresE[i].article);
											}
											if ($scope.idSelectionnee != null) {
												for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
													$scope.idSelectionnee[i] = $scope.listArticlesFournituresE[i].idArticle;
												}
											}
											$rootScope.mySelectionsETissuTmp.length = 0;
									break;
									case 4:
									// Tissu ENTREE 
										console.log("AAAAAAAAAAAAAA TISSU "+$rootScope.mySelectionsFTemp.length);
										for (var i = 0; i < $rootScope.mySelectionsFTemp.length; i++) {

											$scope.objectInter.entiteStock = $rootScope.mySelectionsFTemp[i].entiteStockId;
											$scope.objectInter.referenceArticle = $rootScope.mySelectionsFTemp[i].ref;
											$scope.objectInter.designationArticle = $rootScope.mySelectionsFTemp[i].designation;
											$scope.objectInter.familleArticle = $rootScope.mySelectionsFTemp[i].famille;
											$scope.objectInter.sousFamilleArticle = $rootScope.mySelectionsFTemp[i].sousFamille;
											$scope.objectInter.quantite = $rootScope.mySelectionsFTemp[i].quantite;
											$scope.objectInter.quantiteActuelle = $rootScope.mySelectionsFTemp[i].quantiteActuelle
											$scope.objectInter.quantiteReelle = "";
											$scope.objectInter.typeArticle = 2;
											$scope.objectInter.idMagasin = $scope.idMagasinFourniture;
											$scope.objectInter.idArticle = $rootScope.mySelectionsFTemp[i].id ;
											$scope.objectInter.nouveau = true;
											console.log ($scope.idMagasinFourniture);
											
											$scope.objectInter.type = "ENTREE";
											$scope.mouvementTissusE[i] = $scope.objectInter;
											$scope.objectInter = {};
										}
										for (var i = 0; i <$rootScope.mySelectionsFTemp.length; i++) {
											$scope.listArticlesTissusE.push($scope.mouvementTissusE[i]);
										}
										
										if ($scope.idSelectionnee != null) {
											for (var i = 0; i < $scope.listArticlesTissusE.length; i++) {
												$scope.idSelectionnee[i] = $scope.listArticlesTissusE[i].idArticle;
											}
										}
										$rootScope.mySelectionsFTemp.length= 0;
									break;

									case 5:
										// Fil A Coudre ENTREE
											console.log("coudres"+$rootScope.mySelectionsFTemp.length);
										for (var i = 0; i < $rootScope.mySelectionsEFACtemp.length; i++) {
											$scope.objectInter.entiteStock = $rootScope.mySelectionsEFACtemp[i].entiteStockId;
											$scope.objectInter.referenceArticle = $rootScope.mySelectionsEFACtemp[i].ref;
											$scope.objectInter.designationArticle = $rootScope.mySelectionsEFACtemp[i].designation;
											$scope.objectInter.familleArticle = $rootScope.mySelectionsEFACtemp[i].famille;
											$scope.objectInter.sousFamilleArticle = $rootScope.mySelectionsEFACtemp[i].sousFamille;
											$scope.objectInter.quantite = $rootScope.mySelectionsEFACtemp[i].quantite;
											$scope.objectInter.quantiteActuelle = $rootScope.mySelectionsEFACtemp[i].quantiteActuelle
											$scope.objectInter.quantiteReelle = $rootScope.mySelectionsEFACtemp[i].qteR;
											$scope.objectInter.idMagasin =$scope.idMagasinFourniture;
											$scope.objectInter.idArticle = $rootScope.mySelectionsEFACtemp[i].id ;
											$scope.objectInter.typeArticle = 3;
											$scope.objectInter.type = "ENTREE";
											$scope.objectInter.nouveau = true;
											console.log($scope.idMagasinFourniture);
											$scope.mouvementEFAC[i] = $scope.objectInter;
											$scope.objectInter = {};
										}
										for (var i = 0; i < $rootScope.mySelectionsEFACtemp.length; i++) {
											$scope.listArticlesFACE
													.push($scope.mouvementEFAC[i]);
										}

										if ($scope.idSelectionnee != null) {
											for (var i = 0; i < $scope.listArticlesFACE.length; i++) {
												$scope.idSelectionnee[i] = $scope.listArticlesFACE[i].idArticle;
											}
										}
										$rootScope.mySelectionsEFACtemp.length= 0;
									break;
								case 6:
									// Table Détail Couleur et Taille
//										$("#tableElementBesoin tr:nth-child("+$scope.indexRowToDelete+2+")").css("color","red");
										$scope.listArticlesFournituresE.splice($scope.indexRowToDelete , 1);
//										 setInterval(function(){
//											}, 3000);
										for (var i = 0; i < $scope.listeColor.length; i++) {
											
											for (var j = 0; j < $scope.listeTaille.length; j++) {
												if ($scope.matrix[i][j] != null
														&& $scope.matrix[i][j] != false) {
													$scope.listArticlesFournituresE.push({
															eb_taille_id:$scope.matrix[i][j].taille.ebTailleId,
															eb_couleur_id:$scope.matrix[i][j].couleur.ebCouleurId,
															designationTaille:$scope.matrix[i][j].taille.designation,
															designationCouleur:$scope.matrix[i][j].couleur.designation,
															quantite:$scope.matrix[i][j].quantite,
															 global:true,
															 ordre:"22",
															article:{
																id:$scope.articleFicheBesoin.id,
																ref:$scope.articleFicheBesoin.ref,
																designation:$scope.articleFicheBesoin.designation,
																familleArticleDesignation:$scope.articleFicheBesoin.familleArticleDesignation,
																sousFamilleArtEntiteDesignation:$scope.articleFicheBesoin.sousFamilleArtEntiteDesignation,
															    }
													});
												}
											}
										}
									
									break;
									
								}
							}

							$scope.getArticleById = function(id){
								var pffff;
								$http
								.get(
										baseUrl
												+ "/article/getId:"
												+ id)
								.success(function(data){
									pffff = data.ref;
									console.log("pffff : " + pffff);
									
								});
								return pffff;
								}
							$scope.addNewElement = function(taille, couleur, indexTD, indexTR) {
								var elementObjet = {
									taille : taille,
									couleur : couleur,
									quantite : $(
											"#TC" + taille.ebTailleId + "" + couleur.ebCouleurId).val()
								};
								console.log("taille : " + taille);
								console.log("couleur : " + couleur);
								console.log("colIndex : " + indexTD);
								console.log("rowIndex : " + indexTR);
								console.log("ID = #TC" + taille.ebTailleId + ""
										+ couleur.ebCouleurId);
								if ($("#TC" + taille.ebTailleId + "" + couleur.ebCouleurId).val() != '') {
									$scope.matrix[indexTR][indexTD] = elementObjet;
									console.log(" matrix : " + $scope.matrix);
								} else {
									$scope.matrix[indexTR].splice(indexTD, 1);
									$scope.matrix[indexTR][indexTD] = false;
									console.log(" matrix : " + $scope.matrix);
								}

							}

						}
					}
				}])
		.directive(
				'popUp',[ 'baseUrl','baseUrlGs',	function($compile, baseUrl, baseUrlGs) {
					return {
						restrict : 'E',
						templateUrl : 'views/templates/popup.html',
						link : function(scope, element, attr, model) {

						},
						controller : function($scope, $element, $http,$resource, baseUrl,baseUrlGs, $rootScope ) {
							// declaration variables
							$scope.matrix = [ [], [], [], [], [] ];
							$scope.elementValueRow = [];
							$scope.elementNGgridShow = true;
							$scope.elementNGgridShowFAC = true;
							$scope.elementNGgridShowTissu = true;
							$scope.mySelections = [];
							$scope.mySelectionsE = [];
							$scope.mySelectionsTissu = [];
							$scope.mySelectionsETissu = [];
							$scope.mySelectionsFAC = [];
							$scope.mySelectionsEFAC = [];
							$scope.gridTissusE= {};
							$scope.gridFournituresE= {};
							$scope.gridFilaCoudresE = {};
							$scope.gridTissusE= {};
							$scope.gridTissusEtemp={};
							// liste de tous les types des articles
							$scope.messages = [
									"Suppression article",
									"Suppression PI",
									"",
									"Suppression Produit", 
									"Suppression Stock",
									"Suppression d'une Commande de Vente",
									"Suppression d'un Produit Commande",
									"Suppression Operation",
							        "Suppression Gamme"
									];

							// show Popup Taille Color
							$scope.showPopupTailleColor = function(type,idArticle, ref, designation, famille, sousFamille, index) {
								console.log("ref :"+ref);
								$scope.indexRowToDelete = index;
								console.log("indexRowToDelete :"+$scope.indexRowToDelete);
								$scope.message = 14;
								$scope.articleFicheBesoin = {
									id : idArticle,
									ref : ref,
									designation : designation,
									familleArticleDesignation : famille,
									sousFamilleArtEntiteDesignation:sousFamille
								};
								$scope.message = type;
								$('div[id=light]').fadeIn("slow");
								$('#lightS').fadeIn("slow");
								$('#lightS').draggable();
								$('div[id=light]').draggable();
								$('div[id=fade]').fadeIn("slow");
							}
							// show POPUP
							$rootScope.showPopupDelete = function(type, id) {
								switch (type) {
								case 0:
									// suppression article
									$scope.index = this.row.rowIndex;
									$scope.message = 0;
									break;
								case 1:
									// suppression partie interessée
									$scope.index = this.row.rowIndex;
									$scope.message = 1;
									break;
								case 2:
									// email
									$scope.message = 2;
									break;
								case 3:
									// suppression produit
									$scope.index = this.row.rowIndex;
									$scope.message = 3;
									break;
								case 4:
									//Suppression Stock entree
									$scope.index = this.row.rowIndex;
									$scope.message = 4;
									break;
								case 5:
									// MVT-STOCK ENTREE Fourniture
									console.log("----entree stock case 5 N2-------");
									$scope.stockEntreeFournitureForm.$setPristine();
									if ($scope.listArticlesFournituresE != null) { 
										for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournituresE[i].idArticle);
										}
									}
									
									$scope.gridFournituresE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 5;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 1
									};
									$scope.rechercheArticleEntree($scope.articleEntree, id);
									break;
								case 6:
									// MVT-STOCK SORTIE Fourniture
									console.log("--popUp :case : 6. Sortie Fourniture");
									$scope.gridFournituresS.$gridScope
											.toggleSelectAll(false, true);
									if ($scope.listArticlesFournitures != null) { 
										for (var i = 0; i < $scope.listArticlesFournitures.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournitures[i].entiteStock);
										}
									}
									$scope.entiteStockFournitureCourante = {};
									$scope.message = 6;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.entiteStockFournitureCourante = {
										magasin : id,
										typeArticle : "fourniture"
									};
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 7:
									// MVT-STOCK SORTIE TISSU
									console.log("--popUp :case : 7. Sortie TISSU");
									$scope.gridFournituresS.$gridScope
											.toggleSelectAll(false, true);

									if ($scope.listArticlesTissusS!= null) { 
										console.log ("test test");
										console.log ($scope.listArticlesTissusS);
										for (var i = 0; i < $scope.listArticlesTissusS.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesTissusS[i].entiteStock);
										}
									}
									$scope.entiteStockFournitureCourante = {};
									$scope.message = 7;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.entiteStockFournitureCourante = {
										magasin : id,
										typeArticle : "tissu"
									};
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 8:
									// MVT-STOCK SORTIE Fil A Coudre
									console.log("--popUp :case : 8. Sortie Fil A Coudre");
									$scope.gridFournituresS.$gridScope
											.toggleSelectAll(false, true);
									if ($scope.listArticlesFilaCoudres != null) { 
										for (var i = 0; i < $scope.listArticlesFilaCoudres.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFilaCoudres[i].entiteStock);
										}
									}
									$scope.entiteStockFournitureCourante = {};
									$scope.message = 8;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.entiteStockFournitureCourante = {
										magasin : id,
										typeArticle : "fil à coudre"
									};
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 9:
									// MVT-STOCK ENTREE TISSU
									console.log("**popUp :case Entree : 9");
									$scope.gridTissusE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 9;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 2
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);
									break;
								case 10:
									// MVT-STOCK ENTREE Fil A Coudre
									console.log("**popUp :case Entree : 10");
									$scope.gridFilaCoudresE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 10;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 3
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);
									break;
								case 11:
									// FicheBesoin Fourniture
									/*$scope.gridFournituresE.$gridScope
									.toggleSelectAll(false, true);
									$scope.articleElementFicheBesoinCourant = {};
									if ($scope.listArticlesFournituresE != null) { 
										for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournituresE[i].article.id);
											console.log("haya 3ad : "+$scope.idSelectionnee);
										}
									}*/
									
									console.log("supprimer fourniture FB D2");
									$scope.message = 11;
									$scope.index = this.row.rowIndex;
									
//									$scope.articleElementFicheBesoinCourant = {
//										typeEntite : 1
//									};
//									$scope.rechercheArticleEntree(
//											$scope.articleElementFicheBesoinCourant, 1);
									break;
								case 12:
									// FicheBesoin TISSU
									$scope.articleFicheBesoin = {};
									$scope.message = 12;
									$scope.articleFicheBesoin = {
										typeEntite : 2
									};
									$scope.rechercheArticleEntree(
											$scope.articleFicheBesoin, 1);
									break;
								case 13:
									// FicheBesoin Fil A Coudre
									$scope.articleFicheBesoin = {};
									$scope.message = 13;
									$scope.articleFicheBesoin = {
										typeEntite : 3
									};
									$scope.rechercheArticleEntree(
											$scope.articleFicheBesoin, id);
									break;
								case 14:
									// MVT-STOCK ENTREE Fourniture
									/*$scope.stockEntreeFournitureForm.$setPristine();
									if ($scope.listArticlesFournituresE != null) { 
										for (var i = 0; i < $scope.listArticlesFournituresE.length; i++) {
											$scope.idSelectionnee
													.push($scope.listArticlesFournituresE[i].idArticle);
										}
									}
									$scope.gridFournituresE.$gridScope
											.toggleSelectAll(false, true);
									$scope.articleEntree = {};
									$scope.message = 5;
									console.log("id Magasin : " + id);
									$scope.idMagasinFourniture = id;
									$scope.articleEntree = {
										typeEntite : 1
									};
									$scope.rechercheArticleEntree(
											$scope.articleEntree, id);*/
									break;
								case 15:
									// suppression partie interessée
									console.log("popUp");
									$scope.index = this.row.rowIndex;
									$scope.message = 15;
									break;
								case 20:
									// suppression ProduitCommande -Gc Vente
									$scope.index = this.row.rowIndex;
									$scope.message = 20;
									break;

								case 21:
									// suppression ProduitCommande -Gc Vente
									$scope.index = this.row.rowIndex;
									$scope.message = 21;
									break;
								case 22:
									// suppression Opretation -GPAO 
									$scope.index = this.row.rowIndex;
									$scope.message = 22;
									break;
								case 23:
									// suppression GAmmeMontage -GPAO
									$scope.index = this.row.rowIndex;
									$scope.message = 23;
									break;
								case 24:
									// suppression Feuille Saisie
									$scope.index = this.row.rowIndex;
									$scope.message = 24;
									break;	
								case 25:
									// suppression Personnel
									$scope.index = this.row.rowIndex;
									$scope.message = 25;
									break;	
								}
								$('div[id=light]').fadeIn("slow");
								$('#lightS').fadeIn("slow");
								$('#lightS').draggable();
								$('div[id=light]').draggable();
								$('div[id=fade]').fadeIn("slow");
							}
							// close POPUP
							$scope.closePopupDelete = function(type) {
								$('div[id=fade]').css("display", "none");
								$('#lightS').fadeOut();
								$('div[id=light]').fadeOut();
								$('#fade').fadeOut();
							}
							// vider Form Entree
							$scope.viderFormEntree = function(type) {
								console.log("viderFormEntree..");
								var obj = {
									"typeArticle" : type
								};
								//relancer la recherche du tableau
								//séparer viderForm Entrée du viderForm Sortie
								$http   .post( baseUrlGs + "/articleEntree/rechercheArticleEntreeMultiCritere",
												obj)
										.success(
												function(resultat) {
													console.log("refresh popup Entree  .." + resultat.articleEntree.length);
													$scope.myDataEntite = resultat.articleEntree;
													//vider l'objet de recherche
													$scope.articleEntree = {
														typeEntite : type
													};

													//TODO : formPristine
													$scope.stockEntreeFournitureForm.$setPristine();
													$scope.stockEntreeTissuForm.$setPristine();
													$scope.stockEntreeFilForm.$setPristine();

													
												});
							}

							// vider Form Sortie
							$scope.viderFormSortie = function(type) {
								console.log("--PopUp :viderFormSortie..");
								var obj = {
									typeArticle : type
								};

								//relancer la recherche du tableau
								//TODO: séparer viderForm Entrée du viderForm Sortie
								$http   .post( baseUrlGs
														+ "/entiteStock/rechercheEntiteStockMouvement",
												obj)
										.success(
												function(resultat) {
													console.log("refresh popup Sortie ..  "+ resultat.entiteStockMouvement.length);
													$scope.myDataEntite = resultat.entiteStockMouvement;
													//vider l'objet de recherche
													$scope.entiteStockFournitureCourante = {
														typeArticle : type
													};

													$scope.stockSortieFournitureForm.$setPristine();
													$scope.stockSortieTissuForm.$setPristine();
													$scope.stockSortieFilForm.$setPristine();

												});
							}
							// Rechercher Articles Fournitures par Magasin
							$scope.articleFournitureCourante = {
								designation : "art 0"
							};
							$scope.myDataFourniture = [];

							// recherche Mouvement par critere
							$scope.rechercheMvtParCritere = function(type) {
								switch (type) {
								case 0:
									$scope.rechercheEntiteStockMouvement($scope.entiteStockFournitureCourante);
									break;
								case 1:
									$scope.rechercheArticleEntree(
											$scope.articleEntree,
											$scope.idMagasinFourniture);
									break;
								case 2:
									$scope.rechercheArticleEntree(
											$scope.articleEntree, 1);
									break;
								}
							}
							
							$scope.rechercheArticleEntree = function(articleEntree, id) {
								var widhout = [];
								$scope.myDataEntite = [];
								var inter2 = 0;
								$http
										.post(
												baseUrlGs
														+ "/articleEntree/rechercheArticleEntreeMultiCritere",
												articleEntree)
										.success(
												function(resultat) {
                                                    console.log("$scope.idSelectionneep : "+$scope.idSelectionnee);
													widhout = resultat.articleEntree;
													console.log("Resultat Recherche Popup : "+resultat.articleEntree.length);
													if ($scope.idSelectionnee != null) {
														for (var i = 0; i < widhout.length; i++) {
															//console.log("widhout[i].id : "+widhout[i].id);
															for (var j = 0; j < $scope.idSelectionnee.length; j++) {
																if ($scope.idSelectionnee[j] == widhout[i].id) {
																	inter2 = $scope.idSelectionnee[j];
																}
															}
															if (widhout[i].id != inter2)
																$scope.myDataEntite
																		.push(widhout[i]);
														}
													} else {
														$scope.myDataEntite = widhout;
														$scope.idSelectionnee = [];
													}
													
												});
							}
							
							
							$scope.rechercheEntiteStockMouvement = function(entiteStock) {
								var widhout = [];
								$scope.myDataEntite = [];
								var inter2 = 0;
								$http
										.post(
												baseUrlGs
														+ "/entiteStock/rechercheEntiteStockMouvement",
												entiteStock)
										.success(
												function(resultat) {
													
													widhout = resultat.entiteStockMouvement;
													if($scope.idSelectionnee != null){
														for (var i = 0; i < widhout.length; i++) {
															//console.log("widhout[i].id : "+widhout[i].entiteStockId);
															for (var j = 0; j < $scope.idSelectionnee.length; j++) {
																if ($scope.idSelectionnee[j] == widhout[i].entiteStockId) {
																	inter2 = $scope.idSelectionnee[j];
																}
															}
															if (widhout[i].entiteStockId != inter2)
																$scope.myDataEntite
																		.push(widhout[i]);
														}
													}else{
													$scope.myDataEntite = resultat.entiteStockMouvement;}
													$scope.idSelectionnee = [];
												});
							}

							
							/** Fin de gestion des Grids Fil A coudre Entree */
							/***************************************************
							 * Gestion des Grids Fourniture Sortie
							 **************************************************/
							$scope.gridFournituresS = {
								data : 'myDataEntite',
								columnDefs : [
										{
											field : 'reference',
											displayName : 'Réf.'
										},
										{
											field : 'designation',
											displayName : 'Désignation',
											width: "40%"
										},
										
										{
											field : 'famille',
											displayName : 'Famille'
										},
										{
											field : 'unite',
											displayName : 'Unité'
										},
										{
											field : 'pmp',
											displayName : 'PMP'
										},
										{
											field : 'qteActuel',
											displayName : 'Q.Act'
										},
										
										{
											field : '',
											cellTemplate : '<span class="rawform-icon2"></span>'
										} ],
								showSelectionCheckbox : true,
								beforeSelectionChange : $scope.beforeVehicleSelectionChange,
								checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelected" ng-change="toggleSelectAll(allSelected, true)"/>',
								selectedItems : $scope.mySelections,
								selectWithCheckboxOnly : false,
								multiSelect : true

							};
							/** Fin de gestion des Grids Fourniture Sortie */
							/***************************************************
							 * /***************************************************
							 * Gestion des Grids Tissu Sortie
							 **************************************************/
							$scope.gridTissusS = {
								data : 'myDataEntite',
								columnDefs : [
										{
											field : 'reference',
											displayName : 'Réf.'
										},
										{
											field : 'designation',
											displayName : 'Désignation',
											width: "40%"
										},
										
										{
											field : 'famille',
											displayName : 'Famille'
										},
										{
											field : 'unite',
											displayName : 'Unité'
										},
										{
											field : 'pmp',
											displayName : 'PMP'
										},
										{
											field : 'qteActuel',
											displayName : 'Q.Act'
										},
										
										{
											field : '',
											cellTemplate : '<span class="rawform-icon2"></span>'
										} ],
								showSelectionCheckbox : true,
								beforeSelectionChange : $scope.beforeVehicleSelectionChange,
								checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelected" ng-change="toggleSelectAll(allSelected, true)"/>',
								selectedItems : $scope.mySelectionsTissu,
								selectWithCheckboxOnly : false,
								multiSelect : true

							};
							/** Fin de gestion des Grids Tissu Sortie */
							/***************************************************
							 * Gestion des Grids Fil A coudre Sortie
							 **************************************************/
							$scope.gridFilaCoudresS = {
								data : 'myDataEntite',
								columnDefs : [
										{
											field : 'reference',
											displayName : 'Réf.'
										},
										{
											field : 'designation',
											displayName : 'Désignation',
											width: "40%"
										},
										
										{
											field : 'famille',
											displayName : 'Famille'
										},
										{
											field : 'unite',
											displayName : 'Unité'
										},
										{
											field : 'pmp',
											displayName : 'PMP'
										},
										{
											field : 'cone',
											displayName : 'Q.Act'

										},
										{
											field : '',
											cellTemplate : '<span class="rawform-icon2"></span>'
										} ],
								showSelectionCheckbox : true,
								beforeSelectionChange : $scope.beforeVehicleSelectionChange,
								checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelectedFilAcoudre" ng-change="toggleSelectAll(allSelectedFilAcoudre, true)"/>',
								selectedItems : $scope.mySelectionsFAC,
								selectWithCheckboxOnly : false,
								multiSelect : true

							};
							/** Fin de gestion des Grids Fil A coudre Sortie */
							// showSelectedInfo
							$scope.showSelectedInfo = function(type) {
								$scope.objectInter = {
									entiteStock : "",
									referenceArticle : "",
									designationArticle : "",
									quantite : "",
									quantiteReelle : "",
									typeArticle : "",
									type : "",
									nouveau:true
									
								};
								$scope.mouvementFourniture = [];
								$scope.mouvementFournitureE = [];
								$scope.mouvementTissu = [];
								$scope.mouvementTissusE = [];
								$scope.mouvementFilaCoudre = [];
								$scope.mouvementEFAC = [];
								switch (type) {
									case 0:
										// Fourniture Sortie
										for (var i = 0; i < $scope.mySelections.length; i++) {
											$scope.objectInter.entiteStock = $scope.mySelections[i].entiteStockId;
											$scope.objectInter.referenceArticle = $scope.mySelections[i].reference;
											$scope.objectInter.designationArticle = $scope.mySelections[i].designation;
											$scope.objectInter.familleArticle = $scope.mySelections[i].famille;
											$scope.objectInter.sousFamilleArticle = $scope.mySelections[i].sousFamille;
											$scope.objectInter.quantite = $scope.mySelections[i].qteActuel;
											$scope.objectInter.quantiteReelle = $scope.mySelections[i].qteR;
											$scope.objectInter.idMagasin =$scope.idMagasinFourniture;
											$scope.objectInter.typeArticle = 1;
											$scope.objectInter.nouveau = true ;
										    $scope.objectInter.idArticle = $scope.mySelections[i].id;
											$scope.objectInter.type = "SORTIE";
											$scope.mouvementFourniture[i] = $scope.objectInter;
											$scope.objectInter = {};
										}
										for (var i = 0; i < $scope.mouvementFourniture.length; i++) {

											$scope.listArticlesFournitures
													.push($scope.mouvementFourniture[i]);
										}


										if ($scope.idSelectionnee != null) {
											for (var i = 0; i < $scope.listArticlesFournitures.length; i++) {
												$scope.idSelectionnee[i] = $scope.listArticlesFournitures[i].idArticle;
											}
										}
										$scope.mySelections.length = 0;
									break;
									case 1:
										// Tissu Sortie
										for (var i = 0; i < $scope.mySelectionsTissu.length; i++) {
											$scope.objectInter.entiteStock = $scope.mySelectionsTissu[i].entiteStockId;
											$scope.objectInter.referenceArticle = $scope.mySelectionsTissu[i].reference;
											$scope.objectInter.designationArticle = $scope.mySelectionsTissu[i].designation;
											$scope.objectInter.familleArticle = $scope.mySelectionsTissu[i].famille;
											$scope.objectInter.sousFamilleArticle = $scope.mySelectionsTissu[i].sousFamille;
											$scope.objectInter.quantite = $scope.mySelectionsTissu[i].qteActuel;
											$scope.objectInter.quantiteReelle = $scope.mySelectionsTissu[i].qteR;
											$scope.objectInter.idMagasin =$scope.idMagasinFourniture;
											$scope.objectInter.idArticle = $scope.mySelectionsTissu[i].id;
											$scope.objectInter.typeArticle = 2;
											$scope.objectInter.nouveau = true;
											$scope.objectInter.type = "SORTIE";
											$scope.mouvementTissu[i] = $scope.objectInter;
											$scope.objectInter = {};
										}
										for (var i = 0; i < $scope.mouvementTissu.length; i++) {
											$scope.listArticlesTissus
													.push($scope.mouvementTissu[i]);
										}

										if ($scope.idSelectionnee != null) {
											for (var i = 0; i < $scope.listArticlesTissus.length; i++) {
												$scope.idSelectionnee[i] = $scope.listArticlesTissus[i].idArticle;
											}
										}
										$scope.mySelectionsTissu.length = 0;
									break;
									case 2: 
										// Fil a Coudre Sortie
										for (var i = 0; i < $scope.mySelectionsFAC.length; i++) {
											$scope.objectInter.entiteStock = $scope.mySelectionsFAC[i].entiteStockId;
											$scope.objectInter.referenceArticle = $scope.mySelectionsFAC[i].reference;
											$scope.objectInter.designationArticle = $scope.mySelectionsFAC[i].designation;
											$scope.objectInter.familleArticle = $scope.mySelectionsFAC[i].famille;
											$scope.objectInter.sousFamilleArticle = $scope.mySelectionsFAC[i].sousFamille;
											$scope.objectInter.quantite = $scope.mySelectionsFAC[i].qteActuel;
											$scope.objectInter.quantiteReelle = $scope.mySelectionsFAC[i].qteR;
											$scope.objectInter.idMagasin =$scope.idMagasinFourniture;
											$scope.objectInter.typeArticle = 3;
											$scope.objectInter.idArticle = $scope.mySelectionsFAC[i].id;
											$scope.objectInter.type = "SORTIE";
											$scope.objectInter.nouveau = true ;
											$scope.mouvementFilaCoudre[i] = $scope.objectInter;
											$scope.objectInter = {};
										}
										for (var i = 0; i < $scope.mouvementFilaCoudre.length; i++) {
											$scope.listArticlesFilaCoudres
													.push($scope.mouvementFilaCoudre[i]);
										}
										if ($scope.idSelectionnee != null) {
											for (var i = 0; i < $scope.listArticlesFilaCoudres.length; i++) {
												$scope.idSelectionnee[i] = $scope.listArticlesFilaCoudres[i].idArticle;
											}
										}
										$scope.mySelectionsFAC.length = 0;
										break;

								case 6:
									// Table Détail Couleur et Taille
//										$("#tableElementBesoin tr:nth-child("+$scope.indexRowToDelete+2+")").css("color","red");
										$scope.listArticlesFournituresE.splice($scope.indexRowToDelete , 1);
//										 setInterval(function(){
//											}, 3000);
										for (var i = 0; i < $scope.listeColor.length; i++) {
											
											for (var j = 0; j < $scope.listeTaille.length; j++) {
												if ($scope.matrix[i][j] != null
														&& $scope.matrix[i][j] != false) {
													$scope.listArticlesFournituresE.push({
															eb_taille_id:$scope.matrix[i][j].taille.ebTailleId,
															eb_couleur_id:$scope.matrix[i][j].couleur.ebCouleurId,
															designationTaille:$scope.matrix[i][j].taille.designation,
															designationCouleur:$scope.matrix[i][j].couleur.designation,
															quantite:$scope.matrix[i][j].quantite,
															 global:true,
															 ordre:"22",
															article:{
																id:$scope.articleFicheBesoin.id,
																ref:$scope.articleFicheBesoin.ref,
																designation:$scope.articleFicheBesoin.designation,
																familleArticleDesignation:$scope.articleFicheBesoin.familleArticleDesignation,
																sousFamilleArtEntiteDesignation:$scope.articleFicheBesoin.sousFamilleArtEntiteDesignation,
															    }
													});
												}
											}
										}
									
									break;
									
								}
							}
							$scope.getArticleById = function(id){
								var pffff;
								$http
								.get(
										baseUrl
												+ "/article/getId:"
												+ id)
								.success(function(data){
									pffff = data.ref;
									console.log("pffff : " + pffff);
									
								});
								return pffff;
								}
							$scope.addNewElement = function(taille, couleur,
									indexTD, indexTR) {
								var elementObjet = {
									taille : taille,
									couleur : couleur,
									quantite : $(
											"#TC" + taille.ebTailleId + "" + couleur.ebCouleurId).val()
								};
								console.log("taille : " + taille);
								console.log("couleur : " + couleur);
								console.log("colIndex : " + indexTD);
								console.log("rowIndex : " + indexTR);
								console.log("ID = #TC" + taille.ebTailleId + ""
										+ couleur.ebCouleurId);
								if ($("#TC" + taille.ebTailleId + "" + couleur.ebCouleurId).val() != '') {
									$scope.matrix[indexTR][indexTD] = elementObjet;
									console.log(" matrix : " + $scope.matrix);
								} else {
									$scope.matrix[indexTR].splice(indexTD, 1);
									$scope.matrix[indexTR][indexTD] = false;
									console.log(" matrix : " + $scope.matrix);
								}

							}

						}
					}
				}])
