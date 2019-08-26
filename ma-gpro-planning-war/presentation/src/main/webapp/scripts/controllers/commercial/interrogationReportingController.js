'use strict'
/**
 * Gestion Etat
 */
angular.module('gpro.interrogationReporting', [])
.controller('InterrogationReportingController',[ '$scope', '$http', '$filter','$log', 'baseUrlGs','baseUrl','downloadService',
	function($scope, $http, $filter,$log, baseUrlGs ,baseUrl,downloadService) {
	//declaration variable
        $scope.etatCourant={};
		$scope.listeArticle = [];




		/***************************************************
		 * Gestion de Cache des Referentiels *
		 **************************************************/
		

		// Liste des Articles
		$scope.listeProduitFct = function() {
			$http
					.get(
							baseUrl
									+ "/produit/all")
					.success(
							function(dataProduit) {
								$scope.listeProduit = dataProduit;
							
							});
			
		}


		// Liste des Articles
		$scope.listeArticleFct = function() {
			$http
					.get(
							baseUrl
									+ "/article/all")
					.success(
							function(dataArticle) {
								$scope.listeArticle = dataArticle;
						
							});
			
		}



	                    




		$scope.listeArticleFct();
		$scope.listeProduitFct();
		
		$scope.genererReportModele = function (etatCourant,type)
		{
	
			var url;
			
			var params = 
			"produitId=" + etatCourant.refProduit1
			+ "&type=" + type ;
		

					url = baseUrlGs+ "/fiches/interrogationReportProduit?"+ params;
			
		

			
				$log.info("url"+url);
					
			downloadService.download(url).then(
					function(success) {
						//$log.debug('success : ' + success);
						//$scope.annulerAjout();
					}, function(error) {
						//$log.debug('error : ' + error);
					});
		}

		$scope.genererReportModeleArticle = function (etatCourant,type)
		{
			var url;
			
	
				var params = 
				"produitId=" + etatCourant.refProduit2
				+"&articleId="+etatCourant.refArticle
				+ "&type=" + type ;
		
					url = baseUrlGs+ "/fiches/interrogationReportProduitArticle?"+ params;
			
		

			
				$log.info("url"+url);
					
			downloadService.download(url).then(
					function(success) {
						//$log.debug('success : ' + success);
						//$scope.annulerAjout();
					}, function(error) {
						//$log.debug('error : ' + error);
					});
		}
		   /*** PDF ***/
		$scope.download = function(etatCourant, typeRapport) {
			
	
			var url;
			
				var params = 
				"produitId=" + etatCourant.typeArticle
				+ "&type=" + etatCourant.familleArticle ;
		
			
		
					url = baseUrlGs+ "/fiches/interrogationReportProduit?"+ params;
			
		

			
				$log.info("url"+url);
					
			downloadService.download(url).then(
					function(success) {
						//$log.debug('success : ' + success);
						//$scope.annulerAjout();
					}, function(error) {
						//$log.debug('error : ' + error);
					});

		};	


	
	
	$scope.resetFields = function(){
		$scope.etatCourant={};
		$scope.myData=[];
	}
	


	
	$scope.modeConsultation = false;
	$scope.objectRecherche={};

	
	$scope.retour = function(){
		$scope.modeConsultation = false;
	}
	
} ]);