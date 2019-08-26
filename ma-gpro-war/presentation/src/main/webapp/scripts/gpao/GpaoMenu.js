'use strict'
/**
 * Menu GPAO
 * @Author : Ameni
 */
angular.module('gpro.menuGPAO', [])

.controller('GPAOMenuController', function($scope, $log) {
//	$scope.ITEM = 'OF';
//	$scope.ITEM_Sub = 'OF';


	$scope.goPalette = function() {
		$scope.ITEM = 'palette';
		$scope.ITEM_Sub = 'palette';
	}
	
		$scope.goColisage = function() {
		$scope.ITEM = 'colisage';
		$scope.ITEM_Sub = 'colisage';
	}

	$scope.goOF = function() {
		$scope.ITEM = 'OF';
		$scope.ITEM_Sub = 'OF';
	}
	$scope.goOperation = function() {
		$scope.ITEM = 'operation';
		$scope.ITEM_Sub = 'catalogue';
	}
	$scope.goGammeMontage = function() {
		$scope.ITEM = 'operation';
		$scope.ITEM_Sub = 'gammemontage';
	}
	$scope.goGammeMontageOF = function() {
		$scope.ITEM = 'operation';
		$scope.ITEM_Sub = 'gammemontageOF';
	}
	$scope.goEclatement = function() {
		$scope.ITEM = 'eclatement';
		$scope.ITEM_Sub = 'eclatement';
	}
	$scope.goFicheSuiveuse = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'ficheSuiveuse';
	}
	$scope.goFeuilleSaisie = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'feuilleSaisie';
	}
	$scope.goFeuilleSaisieJournalier = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'feuilleSaisieJour';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	$scope.goDetailsSaisie = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'detailsaisie';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	$scope.goPersonnel = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'personnel';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	$scope.goAvancementOF = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'avancementof';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	$scope.goDetailsSuivi = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'detailsSuivi';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	$scope.goDetailsSuiviTR = function() {
		$scope.ITEM = 'ficheSuiveuse';
		$scope.ITEM_Sub = 'detailsSuiviTR';
	}
	$scope.goGPAO = function() {
		$scope.ITEM = 'gpao';
		$scope.ITEM_Sub = 'gpao';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	
	$scope.gochartGgpao = function() {
		$scope.ITEM = 'chartGgpao';
		$scope.ITEM_Sub = 'chartGgpao';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}
	
	$scope.goReportingGpao = function() {
		$scope.ITEM = 'reportingGpao';
		$scope.ITEM_Sub = 'reportingGpao';
		$log.debug("ITEM  :" + $scope.ITEM);
		$log.debug("ITEM_Sub  :" + $scope.ITEM_Sub);
	}

});