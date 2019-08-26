'use strict'
/**
 * Menu Gestion commerciale
 */
angular
		.module('gpro.menuGestionCommerciale', [])
		.controller(
				'MenuGestionCommercialeController',function($scope,$log){
//					$scope.ITEM = 'vente';
//					$scope.ITEM_Sub = 'bonCommande';
					$scope.goAchat = function(){$scope.ITEM = 'achat'; $scope.ITEM_Sub = 'achat';}
					$scope.goVente = function(){$scope.ITEM = 'vente'; }
					$scope.goVenteBC = function(){$scope.ITEM = 'vente'; $scope.ITEM_Sub = 'bonCommande';
                                                    $log.info("ITEM  :" + $scope.ITEM);
                                                    $log.info("ITEM_Sub  :" + $scope.ITEM_Sub);
                                            	}
                    $scope.goVenteBL = function(){$scope.ITEM = 'vente'; $scope.ITEM_Sub = 'bonLiv';
                                                    $log.info("ITEM  :" + $scope.ITEM);
                                                    $log.info("ITEM_Sub  :" + $scope.ITEM_Sub);
                                            	}
                    $scope.goVenteFacture = function(){$scope.ITEM = 'vente'; $scope.ITEM_Sub = 'facture';
                                                    $log.info("ITEM  :" + $scope.ITEM);
                                                    $log.info("ITEM_Sub  :" + $scope.ITEM_Sub);
                                            	}
					$scope.goTableauBord= function(){$scope.ITEM = 'tableauBord'; $scope.ITEM_Sub = 'tableauBord';}
					$scope.goDetailProduitCommande= function(){$scope.ITEM = 'detailProduitCommande'; $scope.ITEM_Sub = 'detailProduitCommande';}
					$scope.goToGestionCommercialeCharts = function(){$scope.ITEM = 'charts'; $scope.ITEM_Sub = 'charts';}
				
				});
