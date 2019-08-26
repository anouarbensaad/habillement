'use strict'

var app = angular.module('gpro.back-articles', [ "ngResource" ]);
app.controller('BackPartieInteresseeCtrl2', [ '$scope', function($scope) {

	/** ***Liste desVariables **** */

	$scope.displayMenu = "eb";
	$scope.parametre = "Unite";
	$scope.displayBORF = 'b';
	/***************************************************************************
	 * Gestion de la Menu PI
	 **************************************************************************/
	$scope.changeTaPartieInteresse = function() {
		$scope.displayMenu = "pi";
	}
	$scope.changeTaElementBase = function() {
		$scope.displayMenu = "eb";
	}
	$scope.changeTaGestionCommerciale = function() {
		$scope.displayMenu = "gc";
	}
	
	  //Annuler Ajout
    $scope.cancelAdd = function(rowform, index, id, designation, liste){
    	console.log("* Designation "+liste[0].designation);
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
    
} ]);
/*******************************************************************************
 * Gestion des unites
 ******************************************************************************/

app.controller('BackArticleUniteController', [
		'$scope',
		'$filter',
		'$http',
		'baseUrl',
		function($scope, $filter, $http, baseUrl) {
			/** ***Liste desVariables ***** */
			$scope.listeUniteArticle = [];
			$scope.UniteArticleInserree = null;

			// Lister la UniteArticles des fils
			$scope.listeUniteArticleFct = function() {
				$http.get(baseUrl + "/uniteArticle/all").success(
						function(dataUnite) {
							console.log("listeUnite : " + dataUnite.length);
							if(dataUnite.length>0)
							$scope.listeUniteArticle = dataUnite;

						});
			}

			// ajout d'un UniteArticle
			$scope.ajoutUniteArticle = function() {
				$scope.UniteArticleInserree = {

					designation : ''

				};
				$scope.listeUniteArticle.push($scope.UniteArticleInserree);

			};

			// Enregistrer Representant
			$scope.saveUniteArticle = function(dataUnite, id) {

				if ((angular.isDefined(dataUnite.id))) {
					console.log("Modifier Ajout" + dataUnite.designation);

					$http.post(baseUrl + "/uniteArticle/modifierUniteArticle",
							dataUnite).success(function(UniteArticleModifiee) {
						console.log("Success Modification");
						angular.extend(UniteArticleModifiee);
					});
				} else {

					if (dataUnite.designation == "") {
						console.log("Entree Vide !");
					} else {

						$http.post(baseUrl + "/uniteArticle/creerUniteArticle",
								dataUnite).success(function(newUniteArticle) {
							console.log("Success Creation");
							angular.extend(newUniteArticle);

						});
					}
				}
			};
			// Supprimer UniteArticle
			$scope.removeUniteArticle = function(index) {
				console.log("INDEX :" + index);
				console.log("**OBJET :" + $scope.listeUniteArticle[index]);
				console.log("DELETE .." + $scope.listeUniteArticle[index].id);
				$http(
						{
							method : "DELETE",
							url : baseUrl
									+ "/uniteArticle/supprimerUniteArticle:"
									+ $scope.listeUniteArticle[index].id
						}).success(function() {
					console.log("Success Delete");
					// $scope.listeUniteArticle.splice(index, 1);
				});
				$scope.listeUniteArticle.splice(index, 1);
			};
			$scope.listeUniteArticleFct();
			/** Fin de gestion des Unites */

		} ]);
/*******************************************************************************
 * Gestion des Metrage
 ******************************************************************************/
app.controller('BackArticleMetrageController', [
		'$scope',
		'$filter',
		'$http',
		'baseUrl',
		function($scope, $filter, $http, baseUrl) {

			/** ***Liste desVariables ***** */
			$scope.listeMetrage = [];
			$scope.metrageInserree = {};
			
			// Lister la Metrages des fils
			$scope.listeMetrageFct = function() {
				$http.get(baseUrl + "/metrage/all").success(
						function(dataMetrage) {
							console.log("listeMetrage : " + dataMetrage.length);
							if(dataMetrage.length>0)
							 $scope.listeMetrage = dataMetrage;

						});
			}
			
			// ajout d'un Metrage
			$scope.ajoutMetrage = function() {
				
				$scope.metrageInserree = {

					designation : ''

				};
				$scope.listeMetrage.push($scope.metrageInserree);

			};

			// Enregistrer Metrage
			$scope.saveMetrage = function(dataMetrage, id) {

				if ((angular.isDefined(dataMetrage.id))) {
					console.log("Modifier " + dataMetrage.designation);

					$http.post(baseUrl + "/metrage/modifierMetrage",
							dataMetrage).success(function(MetrageModifiee) {
						console.log("Success Modification");
						angular.extend(MetrageModifiee);
					});
				} else {

					if (dataMetrage.designation == "") {
						console.log("designation vide !");
					} else {

						$http.post(baseUrl + "/metrage/creerMetrage",
								dataMetrage).success(function(newMetrage) {
							console.log("Success Creation");
							angular.extend(newMetrage);

						});
					}
				}

			};

			// Supprimer Metrage
			$scope.removeMetrage = function(index) {

				console.log("INDEX :" + index);
				console.log("**OBJET :" + $scope.listeMetrage[index]);
				console.log("DELETE .." + $scope.listeMetrage[index].id);
				$http(
						{
							method : "DELETE",
							url : baseUrl + "/metrage/supprimerMetrage:"
									+ $scope.listeMetrage[index].id
						}).success(function() {
					console.log("Success Delete");
					 
				});
				$scope.listeMetrage.splice(index, 1);
			};
			$scope.listeMetrageFct();
			
			/** Fin de gestion des Metrage */

		} ]);
/*******************************************************************************
 * Gestion des Matieres
 ******************************************************************************/
app
		.controller(
				'BackArticleMatiereController',
				[
						'$scope',
						'$filter',
						'$http',
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {

							/** ***Liste desVariables ***** */
							$scope.listeMatiere = [];
							$scope.matiereInserree = {};
							// Lister la Matieres des fils
							$scope.listeMatiereFct = function() {
								$http
										.get(baseUrl + "/matiereArticle/all")
										.success(
												function(dataMatiere) {
													console.log("listeMatiere : "+ dataMatiere.length);
													if(dataMatiere.length>0)
													$scope.listeMatiere = dataMatiere;

												});
							}

							// ajout d'un Matiere
							$scope.ajoutMatiere = function() {
								$scope.matiereInserree = {

									designation : ''

								};
								$scope.listeMatiere.push($scope.matiereInserree);

							};

							// Enregistrer Matiere
							$scope.saveMatiere = function(dataMatiere, id) {

								if ((angular.isDefined(dataMatiere.id))) {
									console.log("Modifier Ajout"
											+ dataMatiere.designation);

									$http
											.post(
													baseUrl
															+ "/matiereArticle/modifierMatiereArticle",
													dataMatiere)
											.success(
													function(MatiereModifiee) {
														console
																.log("Success Modification");
														angular
																.extend(MatiereModifiee);
													});
								} else {

									if (dataMatiere.designation == "") {
										console.log("Entree Vide !");
									} else {

										$http
												.post(
														baseUrl
																+ "/matiereArticle/creerMatiereArticle",
														dataMatiere)
												.success(
														function(newMatiere) {
															console
																	.log("Success Creation");
															angular
																	.extend(newMatiere);

														});
									}
								}

							};

							// Supprimer Matiere
							$scope.removeMatiere = function(index) {

								console.log("INDEX :" + index);
								console.log("**OBJET :"
										+ $scope.listeMatiere[index]);
								console.log("DELETE .."
										+ $scope.listeMatiere[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/matiereArticle/supprimerMatiereArticle:"
													+ $scope.listeMatiere[index].id
										}).success(function() {
									console.log("Success Delete");
									// $scope.listeMatiere.splice(index, 1);
								});
								$scope.listeMatiere.splice(index, 1);
							};

							$scope.listeMatiereFct();
							/** Fin de gestion des Matieres */

						} ]);

/*******************************************************************************
 * Gestion des Grosseur
 ******************************************************************************/
app.controller('BackArticleGrosseurController', [
		'$scope',
		'$filter',
		'$http',
		'baseUrl',
		function($scope, $filter, $http, baseUrl) {

			/** ***Liste desVariables ***** */
			$scope.listeGrosseur = [];
			$scope.grosseurInserree = null;
			// Lister la grosseurs des fils
			$scope.listeGrosseurFct = function() {
				$http.get(baseUrl + "/grosseur/all").success(
						function(dataGrosseur) {
							console.log("listeGrosseur : " + dataGrosseur.length);
							if(dataGrosseur.length>0)
							$scope.listeGrosseur = dataGrosseur;

						});
			}

			// ajout d'un Grosseur
			$scope.ajoutGrosseur = function() {
				$scope.grosseurInserree = {

					designation : ''

				};
				$scope.listeGrosseur.push($scope.grosseurInserree);

			};

			// Enregistrer Grosseur
			$scope.saveGrosseur = function(dataGrosseur, id) {

				if ((angular.isDefined(dataGrosseur.id))) {
					console.log("Modifier Ajout" + dataGrosseur.designation);

					$http.post(baseUrl + "/grosseur/modifierGrosseur",
							dataGrosseur).success(function(grosseurModifiee) {
						console.log("Success Modification");
						angular.extend(grosseurModifiee);
					});
				} else {

					if (dataGrosseur.designation == "") {
						console.log("Entree Vide !");
					} else {

						$http.post(baseUrl + "/grosseur/creerGrosseur",
								dataGrosseur).success(function(newGrosseur) {
							console.log("Success Creation");
							angular.extend(newGrosseur);

						});
					}
				}

			};

			// Supprimer Grosseur
			$scope.removeGrosseur = function(index) {

				console.log("INDEX :" + index);
				console.log("**OBJET :" + $scope.listeGrosseur[index]);
				console.log("DELETE .." + $scope.listeGrosseur[index].id);
				$http(
						{
							method : "DELETE",
							url : baseUrl + "/grosseur/supprimerGrosseur:"
									+ $scope.listeGrosseur[index].id
						}).success(function() {
					console.log("Success Delete");
					// $scope.listeGrosseur.splice(index, 1);
				});
				$scope.listeGrosseur.splice(index, 1);
			};

			$scope.listeGrosseurFct();
			/** Fin de gestion des Grosseurs */

		} ]);

/*******************************************************************************
 * Gestion des Familles
 ******************************************************************************/
app
		.controller(
				'BackArticleFamilleController',
				[
						'$scope',
						'$filter',
						'$http',
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {

							/** ***Liste desVariables ***** */
							$scope.listeFamilleArticle = [];
							$scope.listeTypeArticle = []; //
							$scope.typeArticleActuel = [];
							$scope.FamilleArticleInserree = null;
							$scope.type = {
								
								designation : ''
							};

							// Lister la FamilleArticles des fils
							$scope.listeFamilleArticleFct = function() {

								$http
										.get(baseUrl + "/familleArticle/all")
										.success(
												function(dataFamilleArticle) {
													console.log("listeFamilleArticle : "
																	+ dataFamilleArticle.length);
													if(dataFamilleArticle.length>0)
													$scope.listeFamilleArticle = dataFamilleArticle;

												});
								$http
										.get(baseUrl + "/typeArticle/all")
										.success(
												function(dataTypeArticle) {
													console.log("listeTypeArticle : "
																	+ dataTypeArticle.length);
													if(dataTypeArticle.length>0)
													$scope.listeTypeArticle = dataTypeArticle;

												});
							}

							// GetId.designation
							$scope.type = {

								status : ''
							};

							$scope.showStatus = function(id) {

								$scope.type.status = id;
								var selected = $filter('filterListBackArticle')(
										$scope.listeTypeArticle, {
											id : $scope.type.status
										});

								if ($scope.type.status && selected.length) {
									return selected[0].designation;
								} else {
									return "Not Set";
								}

								// return ($scope.type.status &&
								// selected.length) ? selected[0].designation:
								// 'Not set';
							};

							// ajout d'un FamilleArticle
							$scope.ajoutFamilleArticle = function() {
								$scope.FamilleArticleInserree = {

									designation : '',
									idTypeArticle : ''

								};
								$scope.listeFamilleArticle
										.push($scope.FamilleArticleInserree);

							};

							// Enregistrer FamilleArticle
							$scope.saveFamilleArticle = function(
									dataFamilleArticle, id) {

								if ((angular.isDefined(dataFamilleArticle.id))) {
									console.log("Modifier Ajout"
											+ dataFamilleArticle.designation);

									$http
											.post(
													baseUrl
															+ "/familleArticle/modifierFamilleArticle",
													dataFamilleArticle)
											.success(
													function(
															FamilleArticleModifiee) {
														console.log("Success Modification");
														angular.extend(FamilleArticleModifiee);
													});
								} else {

									if (dataFamilleArticle.designation == "") {
										// alert("Entree Vide !");
										console.log("Entree Vide !");
									} else {

										$http
												.post(
														baseUrl
																+ "/familleArticle/creerFamilleArticle",
														dataFamilleArticle)
												.success(
														function(
																newFamilleArticle) {
															console
																	.log("Success Creation");
															angular
																	.extend(newFamilleArticle);

														});
									}
								}

							};

							// Supprimer FamilleArticle
							$scope.removeFamilleArticle = function(index) {

								console.log("INDEX :" + index);
								console.log("**OBJET :"
										+ $scope.listeFamilleArticle[index]);
								console.log("DELETE .."
										+ $scope.listeFamilleArticle[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/familleArticle/supprimerFamilleArticle:"
													+ $scope.listeFamilleArticle[index].id
										}).success(function() {
									Console.log("Success Delete");
									// $scope.listeFamilleArticle.splice(index,
									// 1);
								});
								$scope.listeFamilleArticle.splice(index, 1);
							};

							$scope.listeFamilleArticleFct();
							/** Fin de gestion des FamilleArticles */

						} ]);

/*******************************************************************************
 * Gestion des SousFamille
 ******************************************************************************/
app
		.controller(
				'BackArticleSousFamilleController',
				[
						'$scope',
						'$filter',
						'$http',
						'baseUrl',
						function($scope, $filter, $http, baseUrl) {

							/** ***Liste desVariables ***** */
							$scope.listeSousFamilleArticle = [];
							$scope.listeFamilleArticle = [];
							$scope.listeTypeArticle = []; //
							$scope.typeArticleActuel = [];
							$scope.SousFamilleArticleInserree = null;
							/*$scope.type = {
								id : '',
								designation : ''
							};
*/
							// Lister la SousFamilleArticles des fils
							$scope.listeSousFamilleArticleFct = function() {
								$http
										.get(
												baseUrl
														+ "/sousFamilleArticle/all")
										.success(
												function(dataSousFamilleArticle) {
													console.log("listeSousFamilleArticle : "
																	+ dataSousFamilleArticle.length);
													if(dataSousFamilleArticle.length>0)
													$scope.listeSousFamilleArticle = dataSousFamilleArticle;

												});
								$http
										.get(baseUrl + "/familleArticle/all")
										.success(
												function(dataFamilleArticle) {
													console.log(" * listeFamilleArticle : "
																	+ dataFamilleArticle.length);
													if(dataFamilleArticle.length>0)
													$scope.listeFamilleArticle = dataFamilleArticle;

												});
								$http
										.get(baseUrl + "/typeArticle/all")
										.success(
												function(dataTypeArticle) {
													console.log(" * * listeTypeArticle : "
																	+ dataTypeArticle.length);
													if(dataTypeArticle.length>0)
													$scope.listeTypeArticle = dataTypeArticle;

												});
							}

							// GetId.designation
							$scope.type = {
								status : ''
							};

							$scope.famille = {
								status : ''
							};

							$scope.showStatusSousFamille = function(id) {
								$scope.famille.status = id;
								var selected = $filter('filterListBackArticle')(
										$scope.listeFamilleArticle, {
											id : $scope.famille.status
										});
								return ($scope.famille.status && selected.length) ? selected[0].designation
										: 'Not set';
							};

							$scope.showStatusSousFamilleType = function(id) {
								$scope.famille.status = id;
								var selected = $filter('filterListBackArticle')(
										$scope.listeFamilleArticle, {
											id : $scope.famille.status
										});
								if ($scope.famille.status && selected.length) {

									$scope.type.status = selected[0].idTypeArticle;
									var selected2 = $filter('filterListBackArticle')(
											$scope.listeTypeArticle, {
												id : $scope.type.status
											});
									return ($scope.type.status && selected2.length) ? selected2[0].designation
											: 'Not set';
								} else {
									return "NOT SET";
								}

							};

							// ajout d'un SousFamilleArticle
							$scope.ajoutSousFamilleArticle = function() {
								$scope.SousFamilleArticleInserree = {

										designation : '',
										idFamilleArticle : ''

								};
								$scope.listeSousFamilleArticle
										.push($scope.SousFamilleArticleInserree);

							};

							// Enregistrer SousFamilleArticle
							$scope.saveSousFamilleArticle = function(
									dataSousFamilleArticle, id) {

								if ((angular
										.isDefined(dataSousFamilleArticle.id))) {
									console
											.log("Modifier Ajout"
													+ dataSousFamilleArticle.designation);

									$http
											.post(
													baseUrl
															+ "/sousFamilleArticle/modifierSousFamilleArticle",
													dataSousFamilleArticle)
											.success(
													function(
															SousFamilleArticleModifiee) {
														console
																.log("Success Modification");
														angular
																.extend(SousFamilleArticleModifiee);
													});
								} else {

									if (dataSousFamilleArticle.designation == "") {
										console.log("Entree Vide !");
									} else {

										$http
												.post(
														baseUrl
																+ "/sousFamilleArticle/creerSousFamilleArticle",
														dataSousFamilleArticle)
												.success(
														function(
																newSousFamilleArticle) {
															console
																	.log("Success Creation");
															angular
																	.extend(newSousFamilleArticle);

														});
									}
								}

							};

							// Supprimer SousFamilleArticle
							$scope.removeSousFamilleArticle = function(index) {

								console.log("INDEX :" + index);
								console
										.log("**OBJET :"
												+ $scope.listeSousFamilleArticle[index]);
								console
										.log("DELETE .."
												+ $scope.listeSousFamilleArticle[index].id);
								$http(
										{
											method : "DELETE",
											url : baseUrl
													+ "/sousFamilleArticle/supprimerSousFamilleArticle:"
													+ $scope.listeSousFamilleArticle[index].id
										}).success(function() {
									console.log("Success Delete");
									// $scope.listeSousFamilleArticle.splice(index,
									// 1);
								});
								$scope.listeSousFamilleArticle.splice(index, 1);
							};

							$scope.listeSousFamilleArticleFct();
							/** Fin de gestion des SousFamilleArticles */

						} ]);
app.filter('filterListBackArticle', function() {
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
app.controller('BackElBase', [ '$scope', function($scope) {
	
	$scope.ITEM = 'article';
	$scope.goArticle = function(){$scope.ITEM = 'article';}
	$scope.goProduit = function(){$scope.ITEM = 'produit';}
}]);
