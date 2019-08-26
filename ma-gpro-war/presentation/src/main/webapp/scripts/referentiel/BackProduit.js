'use strict'

var app = angular.module('gpro.back-produits', [ "ngResource" ]);
app.controller('BackProduitCtrl2', [ '$scope', function($scope) {
	$scope.parametre = "Couleur";
	 
	//Annuler Ajout
    $scope.cancelAdd = function(rowform, index, id, designation, liste){
    	//console.log("* Designation "+liste[0].designation);
    		  if (angular.isDefined(id)) {
    			  console.log("ID "+id);
    			  rowform.$cancel();
    		  }else{
    			  console.log("ID "+id);
    			  if(designation ==""){
    				  console.log("DELETE" +designation);
    				  liste.splice(index, 1);
			    	  rowform.$cancel();
    			  }else{
    				  console.log("NOT DELETE "+designation);
    				  rowform.$cancel();
    			  }
    		}
    }
}]);

/*******************************************************************************
 * Gestion des Couleurs
 ******************************************************************************/
app.controller('backCouleurProduitController', [
		'$scope',
		'$filter',
		'$http',
		'baseUrl',
		function($scope, $filter, $http, baseUrl) {
			// Déclaration des variables globales utilisées
			var data;
			$scope.displayMode = "couleur";
			$scope.couleurCourante = null;
			$scope.listeCouleur = [];
			$scope.resultatRecherche = $scope.listeCouleur;

			// Lister Couleur
			$scope.listeCouleurProduit = function() {
				$http.get(baseUrl + "/couleur/all").success(function(dataCouleur) {
					console.log("listeCouleur : " + dataCouleur.length);
					if(dataCouleur.length>0)
					$scope.listeCouleur = dataCouleur;
				});
			}

			// ajout d'un couleur
			$scope.ajouterCouleur = function() {
				$scope.couleurCourante = {
					designation : ''
				};
				$scope.listeCouleur.push($scope.couleurCourante);

			};

			// Enregistrer couleur
			$scope.saveCouleur = function(data, id) {
				if (angular.isDefined(id)) {
					console.log("couleur existe deja");
					$http.post(baseUrl + "/couleur/modifierCouleurProduit",
							data).success(function(newcouleur) {
								console.log("Success Modification");
						angular.extend(newcouleur);
					});
				} else {
					$http.post(baseUrl + "/couleur/creerCouleurProduit", data)
							.success(function(newcouleur) {
								console.log("Success Creation");
								angular.extend(newcouleur);
							});
				}

			}

			// Suppression famille produit
			$scope.removeCouleur = function(index) {
				console.log("INDEX :" + index);
				console.log("**OBJET :" + $scope.listeCouleur[index]);
				console.log("DELETE .." + $scope.listeCouleur[index].id);
				$http({
					method : "DELETE",
					url : baseUrl + "/couleur/supprimerCouleurProduit:" + 
					+ $scope.listeCouleur[index].id
				}).success(function() {
					console.log("Success Delete");

				});
				$scope.listeCouleurProduit();
				//$scope.listeCouleur.splice(index, 1);
			}

			$scope.listeCouleurProduit();

		} ]);
/*******************************************************************************
 * Gestion des Phases
 ******************************************************************************/
app.controller('backPhaseProduitController', [
		'$scope',
		'$filter',
		'$http',
		'baseUrl',
		function($scope, $filter, $http, baseUrl) {
			// Déclaration des variables globales utilisées
			var data;
			$scope.displayMode = "couleur";
			$scope.phaseCourante = null;
			$scope.listePhase = [];
			$scope.resultatRecherche = $scope.listePhase;

			// Lister Phase produit
			$scope.listePhaseProduit = function() {
				$http.get(baseUrl + "/phase/all").success(function(dataPhase) {
					console.log("listePhase : " + dataPhase.length);
					if(dataPhase.length>0)
					$scope.listePhase = dataPhase;
				});
			}

			// ajout d'une Phase
			$scope.ajouterPhase = function() {
				$scope.phaseCourante = {
					designation : '',
				};
				$scope.listePhase.push($scope.phaseCourante);

			};

			// Enregistrer Phase
			$scope.savePhase = function(data, id) {
				if (angular.isDefined(id)) {
					console.log("phase existe deja");
					$http.post(baseUrl + "/phase/modifierPhaseProduit", data)
							.success(function(newPhase) {
								console.log("Success Modification");
								angular.extend(newPhase);
							});
				} else {
					$http.post(baseUrl + "/phase/creerPhaseProduit", data)
							.success(function(newPhase) {
								console.log("Success Creation");
								angular.extend(newPhase);
							});
				}

			}

			// Suppression Phase
			$scope.removePhase = function(index) {
				console.log("INDEX :" + index);
				console.log("**OBJET :" + $scope.listePhase[index]);
				console.log("DELETE .." + $scope.listePhase[index].id);
				$http({
					method : "DELETE",
					url : baseUrl + "/phase/supprimerPhaseProduit:" + 
					+ $scope.listePhase[index].id
				}).success(function() {
					console.log("Success Delete");

				});
				//$scope.listePhase.splice(index, 1);
				$scope.listePhaseProduit();
			}
			$scope.listePhaseProduit();
			

		} ]);
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
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.familleCourante = null;
							$scope.listeFamille = [];
							$scope.resultatRecherche = $scope.listeFamille;
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(baseUrl + "/famillelProduit/all")
										.success(function(dataFamille) {
											console.log("listeFamille : " + dataFamille.length);
											if(dataFamille.length>0)
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
									console.log("famille existe deja");
									$http
											.post(
													baseUrl
															+ "/famillelProduit/modifierFamilleProduit",
													data)
											.success(function(newfamille) {
												console.log("Success Modification");
												angular.extend(newfamille);
											});
								} else {
									$http
											.post(
													baseUrl
															+ "/famillelProduit/creerFamilleProduit",
													data)
											.success(function(newfamille) {
												console.log("Success Creation");
												angular.extend(newfamille);
											});
								}

							}

							// Suppression d'une Famille
							$scope.removeFamilleProduit = function(index) {
								console.log("INDEX :" + index);
								console.log("**OBJET :" + $scope.listeFamille[index]);
								console.log("DELETE .." + $scope.listeFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/famillelProduit/supprimerFamilleProduit:"+ $scope.listeFamille[index].id
										}).success(function() {
									console.log("Success Delete");
									$scope.listeFamille.splice(index, 1);
								});

								$scope.listeFamilleProduit();
							
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
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "";
							$scope.SousFamilleCourante = null;
							$scope.listeSousFamille = [];
							$scope.resultatRecherche = $scope.listeSousFamille;
							
							// Lister SousFamille
							$scope.listeSousFamilleProduit = function() {
								$http.get(baseUrl + "/sousFamilleProduit/all")
										.success(function(dataSousFamille) {
											console.log("listeSousFamille : " + dataSousFamille);
											if(dataSousFamille.length>0)
											$scope.listeSousFamille = dataSousFamille;
										});
							}
							
							// Lister Famille produit
							$scope.listeFamilleProduit = function() {
								$http.get(baseUrl + "/famillelProduit/all")
										.success(function(dataFamille) {
											console.log("* listeFamille " + dataFamille.length);
											if(dataFamille.length>0)
											$scope.listeFamille = dataFamille;
										});
							}
							
							// GetId.designation StdTaille
							$scope.type = {

								status : ''
							};

							$scope.showStatusFamille = function(id) {
								$scope.type.status = id;
								var selected = $filter('filterListBackProduit')(
										$scope.listeFamille, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "--";
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
									console.log("SousFamille existe deja");
									$http
											.post(
													baseUrl
															+ "/sousFamilleProduit/modifierSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												console.log("Success Modification");
												angular.extend(newSousFamille);
											});
								} else {
									$http
											.post(
													baseUrl
															+ "/sousFamilleProduit/creerSousFamilleProduit",
													data)
											.success(function(newSousFamille) {
												console.log("Success Creation");
												angular.extend(newSousFamille);
											});
								}

							}

							// Suppression SousFamille produit
							$scope.removeSousFamilleProduit = function(index) {
								console.log("INDEX :" + index);
								console.log("**OBJET :" + $scope.listeSousFamille[index]);
								console.log("DELETE .." + $scope.listeSousFamille[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/sousFamilleProduit/supprimerSousFamilleProduit:"
													+ $scope.listeSousFamille[index].id
										}).success(function() {
									console.log("Success Delete");
									$scope.listeSousFamille.splice(index, 1);
								});

							$scope.listeFamilleProduit();
							$scope.listeSousFamilleProduit();
								
							}
							$scope.listeFamilleProduit();
							$scope.listeSousFamilleProduit();
						} ]);
/*******************************************************************************
 * Gestion des Tailles
 ******************************************************************************/
app.controller('backTailleProduitController', [
		'$scope',
		'$filter',
		'$http',
		'baseUrl',
		function($scope, $filter, $http, baseUrl) {
			// Déclaration des variables globales utilisées
			var data;
			$scope.displayMode = "";
			$scope.tailleCourante = null;
			$scope.listeTaille = [];
			$scope.listeStandardTaille = [];
			$scope.resultatRecherche = $scope.listeTaille;

			// Lister standardTaille produit
			$scope.listeStandardTailleProduit = function() {
				$http.get(baseUrl + "/standardTaille/all").success(
						function(dataStandardTaille) {
							console.log("listeStandardTaille " + dataStandardTaille.length);
							if(dataStandardTaille.length>0)
							$scope.listeStandardTaille = dataStandardTaille;
						});
			}
			

			// Lister Taille produit
			$scope.listeTailleProduit = function() {
				$http.get(baseUrl + "/taille/all").success(function(dataTaille) {
					console.log(" * listeTaille " + dataTaille.length);
					if(dataTaille.length>0)
					$scope.listeTaille = dataTaille;
				});
			}
			
			// GetId.designation StdTaille
			$scope.type = {

				status : ''
			};

			$scope.showStatusStandardTaille = function(id) {

				$scope.type.status = id;
				var selected = $filter('filterListBackProduit')(
						$scope.listeStandardTaille, {
							id : $scope.type.status
						});

				if ($scope.type.status && selected.length) {
					return selected[0].designation;
				} else {
					return "Not Set";
				}

			};
			$scope.cnt = 0;
			$scope.items = [];
			// ajout d'une Taille
			$scope.ajouterTaille = function() {
				$scope.items = $scope.listeTaille; 
				var indexFin = $scope.items.length;
				
				if (indexFin <= 0) {
					$scope.cnt++;
				} else {
					$scope.items = $scope.listeTaille; 
					var index = $scope.items.length;
					$scope.cnt = index;
					$scope.cnt++;
				}

				$scope.tailleCourante = {
					ordre:$scope.cnt++,
					designation : '',
					eb_sttaille_id :''
				};
				$scope.listeTaille.push($scope.tailleCourante);

			};

			// Enregistrer Taille
			$scope.saveTaille = function(data, id) {
				if (angular.isDefined(id)) {
					console.log("famille existe deja");
					$http.post(baseUrl + "/taille/modifierTailleProduit", data)
							.success(function(newfamille) {
								console.log("Success Modification");
								angular.extend(newfamille);
							});
				} else {
					$http.post(baseUrl + "/taille/creerTailleProduit", data).success(
							function(newfamille) {
								console.log("Success Creation");
								angular.extend(newfamille);
							});
				}

			}
			
			// Suppression Taille produit
			$scope.removeTaille = function(index) {
				console.log("INDEX :" + index);
				console.log("**OBJET :" + $scope.listeTaille[index]);
				console.log("DELETE .." + $scope.listeTaille[index].id);
				$http({
					method : "DELETE",
					url : baseUrl + "/taille/supprimerTailleProduit:" + $scope.listeTaille[index].id
				}).success(function() {
					console.log("Success Delete");
					$scope.listeTaille.splice(index, 1);
				});

				$scope.listeTailleProduit();
				$scope.listeStandardTailleProduit();

			}
			$scope.listeTailleProduit();
			$scope.listeStandardTailleProduit();

		} ]);
/*******************************************************************************
 * Gestion des Standards de Taille
 ******************************************************************************/
app
		.controller(
				'backStandardTailleController',
				[
						'$scope',
						'$filter',
						'$http',
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {
							// Déclaration des variables globales utilisées
							var data;
							$scope.displayMode = "TailleStandard";
							$scope.standardTailleCourante = null;
							$scope.listeStandardTaille = [];
							$scope.resultatRecherche = $scope.listeStandardTaille;

							// Lister StandardTaille
							$scope.listeStandardTailleProduit = function() {
								$http.get(baseUrl + "/standardTaille/all")
										.success(function(dataStandardTaille) {
											console.log("listeStandardTaille : "+dataStandardTaille.length);
											if(dataStandardTaille.length>0)
											$scope.listeStandardTaille = dataStandardTaille;
										});
							}

							// ajout d'un standard
							$scope.ajouterStandardTaille = function() {
								$scope.standardTailleCourante = {
									designation : '',
								};
								$scope.listeStandardTaille
										.push($scope.standardTailleCourante);

							};

							// Enregistrer famille
							$scope.saveStandardTaille = function(data, id) {
								if (angular.isDefined(id)) {
									console.log("standardtaille existe deja");
									$http
											.post(
													baseUrl
															+ "/standardTaille/modifierStandardTailleProduit",
													data)
											.success(function(newStandardTaille) {
												console.log("Success Modification");
												//$scope.displayMode = "Famille";
												angular.extend(newStandardTaille);
											});
								} else {
									$http
											.post(
													baseUrl
															+ "/standardTaille/creerStandardTailleProduit",
													data)
											.success(
													function(newStandardTaille) {
														console.log("Success Creation");
													//	$scope.displayMode = "TailleStandard";
														angular.extend(newStandardTaille);
													});
								}

							}

							// Suppression famille produit
							$scope.removeStandardTailleProduit = function(index) {
								console.log("INDEX :" + index);
								console.log("**OBJET :" + $scope.listeStandardTaille[index]);
								console.log("DELETE .." + $scope.listeStandardTaille[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/standardTaille/supprimerStandarTailleProduit:"+ listeStandardTaille[index].id
										}).success(function() {
									console.log("Success Delete");
									$scope.listeStandardTaille.splice(index, 1);
								});

							$scope.listeStandardTailleProduit();
									
							}
							
							$scope.listeStandardTailleProduit();

						} ]);
app.filter('filterListBackProduit', function() {
	  return function(liste, id) {
		 var listeFiltre = [];
		// console.log("IdSousFamille=  "+id.id);
		// console.log("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
		 angular.forEach(liste, function(elementListe, key){
			
			if(elementListe.id == id.id){
				//console.log(sousFamille.id +" == "+ id.id);
				listeFiltre.push(elementListe);
			}
				
		 });
		// console.log(" ** listeFiltre "+ JSON.stringify(listeFiltre, null, "    "));
		 return listeFiltre;
	  };
	})
