'use strict'
/**
 * Gestion Etat
 */
angular.module('gpro.stockMenu', [])
.controller('stockMenuController',[ '$scope', '$rootScope', function($scope,$rootScope) {
	
	$scope.stockIsActive = false;
	
	$rootScope.goToStock=function(){
		console.log("goTostock call");
		$scope.stockIsActive = true;
	}
} ]);