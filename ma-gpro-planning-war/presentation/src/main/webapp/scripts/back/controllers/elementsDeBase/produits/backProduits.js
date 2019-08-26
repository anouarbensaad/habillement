'use strict'

var app = angular.module('gpro.back-produits', [ "ngResource" ]);
app.controller('BackProduitCtrl2', [ '$scope', '$log', function($scope,$log) {
	$scope.parametre = "Famille";
	 
	console.log("Back produits / elements de base");
	
	//Annuler Ajout
    $scope.cancelAdd = function(rowform, index, id, designation, liste){
    	//$log.debug("* Designation "+liste[0].designation);
    		  if (angular.isDefined(id)) {
    			  $log.debug("ID "+id);
    			  rowform.$cancel();
    		  }else{
    			  $log.debug("ID "+id);
    			  if(designation ==""){
    				  $log.debug("DELETE" +designation);
    				  liste.splice(index, 1);
			    	  rowform.$cancel();
    			  }else{
    				  $log.debug("NOT DELETE "+designation);
    				  rowform.$cancel();
    			  }
    		}
    }
}]);

/*******************************************************************************
 * Gestion des Familles
 ******************************************************************************/
app
		.controller(
				'backFamilleProduitController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						function($scope, $filter, $http, $log, UrlCommun) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.familleCourante = null;
							$scope.listeFamille = [];
							$scope.resultatRecherche = $scope.listeFamille;
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(UrlCommun + "/famillelProduit/all")
										.success(function(dataFamille) {
											$log.debug("listeFamille " + dataFamille.length);
											$scope.listeFamille = dataFamille;
										});
							}

							// ajout d'une Famille
							$scope.ajouterFamille = function() {
								$scope.familleCourante = {
									designation : '',
								};
								$scope.listeFamille
										.push($scope.familleCourante);

							};

							// Enregistrer famille
							$scope.saveFamille = function(data, id) {
								if (angular.isDefined(id)) {
									$log.debug("famille existe deja");
									$http
											.post(
													UrlCommun
															+ "/famillelProduit/modifierFamilleProduit",
													data)
											.success(function(newfamille) {
												$log.debug("Success Modification");
												angular.extend(newfamille);
											});
								} else {
									$http
											.post(
													UrlCommun
															+ "/famillelProduit/creerFamilleProduit",
													data)
											.success(function(newfamille) {
												$log.debug("Success Creation");
												angular.extend(newfamille);
											});
								}

							}

							// Suppression d'une Famille
							$scope.removeFamilleProduit = function(index) {
								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :" + $scope.listeFamille[index]);
								$log.debug("DELETE .." + $scope.listeFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/famillelProduit/supprimerFamilleProduit:"+ $scope.listeFamille[index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.listeFamille.splice(index, 1);
								});
								$scope.listeFamille.splice(index, 1);
							}
							$scope.listeFamilleProduit();

						} ]);
/*******************************************************************************
 * Gestion des SousFamilles
 ******************************************************************************/
app
		.controller(
				'backSousSousFamilleProduitController',
				[
						'$scope',
						'$filter',
						'$http',
						'$log',
						'UrlCommun',
						function($scope, $filter, $http, $log, UrlCommun) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.SousFamilleCourante = null;
							$scope.listeSousFamille = [];
							$scope.resultatRecherche = $scope.listeSousFamille;
							
							// Lister SousFamille
							$scope.listeSousFamilleProduit = function() {
								$http.get(UrlCommun + "/sousFamilleProduit/all")
										.success(function(dataSousFamille) {
											$log.debug("listeSousFamille " + dataSousFamille.length);
											$scope.listeSousFamille = dataSousFamille;
										});
							}
							
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(UrlCommun + "/famillelProduit/all")
										.success(function(dataFamille) {
											$log.debug("listeFamille : "+dataFamille.length);
											$scope.listeFamille = dataFamille;
										});
							}
							
							// GetId.designation StdTaille
							$scope.type = {

								status : ''
							};

							$scope.showStatusFamille = function(id) {
								$scope.type.status = id;
								var selected = $filter('sousFamillefilterBackProduit')(
										$scope.listeFamille, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

							};

							
							// ajout d'une sousFamille
							$scope.ajouterSousFamille = function() {
								$scope.SousFamilleCourante = {
									designation : '',
									familleProduitId:''
								};
								$scope.listeSousFamille
										.push($scope.SousFamilleCourante);

							};

							// Enregistrer SousFamille
							$scope.saveSousFamille = function(data, id) {
								
								if (angular.isDefined(id)) {
									$log.debug("SousFamille existe deja");
									$http.post(
												UrlCommun+ "/sousFamilleProduit/modifierSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												$log.debug("Success Modification");
												angular.extend(newSousFamille);
											});
								} else {
									$http.post(
											UrlCommun+ "/sousFamilleProduit/creerSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												$log.debug("Success Creation");
												angular.extend(newSousFamille);
											});
								}

							}

							// Suppression SousFamille produit
							$scope.removeSousFamilleProduit = function(index) {
								$log.debug("INDEX :" + index);
								$log.debug("**OBJET :" + $scope.listeSousFamille[index]);
								$log.debug("DELETE .." + $scope.listeSousFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : UrlCommun
													+ "/sousFamilleProduit/supprimerSousFamilleProduit:"
													+ $scope.listeSousFamille[index].id
										}).success(function() {
									$log.debug("Success Delete");
									$scope.listeSousFamille.splice(index, 1);
								});
								$scope.listeSousFamille.splice(index, 1);
							}
							
							$scope.listeFamilleProduit();
							$scope.listeSousFamilleProduit();
						} ])
						
			.filter('sousFamillefilterBackProduit', function() {
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
