'use strict'
/**
 * Menu suivi de commande
 */
angular
		.module('gpro.menuSuiviCommnde', [])
		.controller(
				'menuSuiviCommndeController',function($scope){
					$scope.ITEM = 'SuiviOF';
					$scope.goSuiviOF = function(){$scope.ITEM = 'SuiviOF';}
				
				});


