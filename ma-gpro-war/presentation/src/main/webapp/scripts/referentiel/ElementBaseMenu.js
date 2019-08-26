'use strict'
/**
 * Menu Element de Base
 */
angular
		.module('gpro.menuElementBase', [])
		.controller(
				'MenuElementBaseController',function($scope, $rootScope){
//					if($rootScope.ITEM == null){
//						$rootScope.ITEM = 'article';
//					}
					
					$scope.goArticle = function(){$rootScope.ITEM = 'article';}
					$scope.goProduit = function(){$rootScope.ITEM = 'produit';}
				});
