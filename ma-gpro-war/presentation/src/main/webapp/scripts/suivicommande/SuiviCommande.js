'use strict'
angular.module('gpro.suiviCommande', ["ngResource"])
.controller(
				'SuiviCommandeCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'baseUrlGpao',
						'baseUrl',
						'baseUrlGc',
		
						
function($scope, $http, $filter, baseUrlGpao,baseUrl,baseUrlGc) {
	
	// Déclaration des variables						
	$scope.displayMode = "list";
	$scope.displayMode1 = "list";	
	$scope.displayMode2 = "list";
	$scope.OFCourant = {};
	$scope.vOFCourant = {};
	$scope.listePhaseCache = [];
	$scope.listeClientCache= [];
	$scope.listeCause = [{id:1,cause:"cause 1",quantite:1},{id:2,cause:"cause 2",quantite:2}];
	$scope.listeTaille = [{ebTailleId:1,designation:"38"},{ebTailleId:2,designation:"40"},{ebTailleId:3,designation:"42"},{ebTailleId:4,designation:"44"}];
	$scope.listeCouleur = [{ebCouleurId:1,designation:"Rouge"},{ebCouleurId:2,designation:"Bleu"},{ebCouleurId:3,designation:"Vert"}];
	
	
	
	
	
	
	
	
	
	
	/***************************************************
	 * Gestion de Cache
	 **************************************************/
	// Liste de phase cache
	$scope.listePhaseCache = function() {
		$http.get(baseUrl+ "/gestionnaireCache/")
				.success(
						function(data) {
							$scope.listePhaseCache = data;
							$scope.listePhaseCache.subItem = {designation: data.designation};
						});
	}
	
	
	//Liste des clients cache
	$scope.listephaseCache = function(){
		
		$http.get(baseUrl+ "/" )
		.success(
				function (data)
				{
				
			
				});
				
		
	} 
	
	// Liste de Client cache
	$scope.listeClientCache = function() {
		$http
				.get(
						baseUrlGc
								+ "/gestionnaireCache/listeClientCache")
				.success(function(dataC) {
					$scope.listeClientCache = dataC;

				});
	}
	
	$scope.listePhaseCache();
	$scope.listeClientCache();
	
	/***************************************************
	 * test
	 */
	// Lister les parties interessées
	$scope.listePartieInteressee = function() {
		$http.get(baseUrl + "/partieInteressee/all")
				.success(function(data) {
					console.log("listePartieInteressee : "+data.length);
					$scope.myData = data;
				});
	}
	$scope.listePartieInteressee();
	/***************************************************
	 * Gestion des OFs
	 **************************************************/
	// Liste des Ofs
	$scope.rechercherOf = function(ofValue) {
		$http.post(baseUrlGpao + "/phaseof/recherchePhaseOfMulticritere/",ofValue).success(
				function(data) {
					$scope.myData = data.phaseOf;
				});
	}
	$scope.rechercherOf($scope.OFCourant);
	
	// Sauvegarder  Phase Of
	$scope.sauvegarderAjout = function(phaseOf) {

			$scope.updatePhaseOf(phaseOf);
	}

	// Mise à jour des Phases Ofs
	$scope.updatePhaseOf = function(phaseOf) {
		/*
		 * console.log("Article UPDATE ..Max " +
		 * article.documentEntites[0].path);
		 */
//		article.documentEntites = $scope.listeDocumentArticle;

		$http
				.post(
						baseUrlGpao
								+ "/phaseof/modifiephase",
								phaseOf)
				.success(
						function(phaseOfModifiee) {
//							$scope.refreshNgGrid(phaseOfModifiee);
							for (var i = 0; i < $scope.myData.length; i++) {

								if ($scope.myData[i].id == phaseOfModifiee) {
//									$scope.myData[i] = $scope.MyDataForRefresk;
									break;
								}
							}
							$scope.displayMode = "list";
						});
		$scope.OFCourant = {};
//		$scope.creationArticleForm.$setPristine();
//		$scope.rechercheArticleForm.$setPristine();
//		$scope.listeSeuilApprovisionnement = [];
//		$scope.listeDocumentArticle = [];
	}
	
	
	
	//recuperer la suivi d'une OF
	$scope.modifierOuCreerSuiviOrdreFabrication = function(){
		var index = this.row.rowIndex;
		$scope.displayMode = "edit";
		$scope.OFCourant = $scope.myData[index];
	}
	// Annulation de l'ajout
	$scope.annulerSuivi = function() {
		$scope.OFCourant = {};
		$scope.displayMode = "list";
		$scope.displayMode1 = "list";
		$scope.displayMode2 = "list";
	}
	/***************************************************
	 * Gestion des Causes
	 **************************************************/
	// ajout Cause
	$scope.ajoutCause = function() {

		$scope.CauseInserree = {
			cause : '',
			quantite : ''
		};
		$scope.listeCause.push($scope.CauseInserree);

	};

	// Enregistrer Cause
	$scope.saveCause = function(
			data, id) {
		angular.extend(data, {
			id : id
		});
	};
	
	/***************************************************
	 * Gestion de Grid Suivi de Commande
	 **************************************************/
	/*{
	field : 'client',
	displayName : 'Client',
},
{
	field : 'Réf produit',
	displayName : 'Réf produit'
},
{
	field : 'Produit',
	displayName : 'Produit'
},
{
	field : 'N° OF',
	displayName : 'N° OF'
},
{
	field : 'Q OF',
	displayName : ' Q OF'
},
{
	field : 'Phase',
	displayName : 'Phase'
},
{
	field : 'Méthode',
	displayName : 'Méthode'
},
{
	field : 'Date Début Prév',
	displayName : 'Date Début Prév'
},
{
	field : 'Date Fin Prév',
	displayName : 'Date Fin Prév'
},
{
	field : 'Q.E',
	displayName : 'Q.E'
},
{
	field : 'Q.S',
	displayName : 'Q.S'
},
{
	field : ' Q.Manq.',
	displayName : 'Q.Manq.'
},
{
	field : ' Etat',
	displayName : 'Etat'
},*/
	$scope.colDefs = [];
	$scope
			.$watch(
					'myData',
					function() {
						$scope.colDefs = [
								
								{
											field : 'reference',
											displayName : 'Réf.Produit'
										},
										{
											field : 'designation',
											displayName : 'Désignation'
										},
										{
											field : 'familleDesignation',
											displayName : 'Famille'
										},
										{
											field : 'sousFamilleDesignation',
											displayName : 'Sous Famille'
										},
										{
											field : 'partieIntersseDesignation',
											displayName : 'PI'
										},
										{
											field : 'prixUnitaire',
											displayName : 'Prix'
										},
										{
											field : 'siteEntiteDesignation',
											displayName : 'Site'
										},
										{
											field : 'etat',
											displayName : 'Etat'
										},
										{
											field : '',
											cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
									             +'<a  class="btn-action btn" ng-click="editSuiviCommande()">SG</a>'
									             +' '
									             +'<a  class="btn-action btn" ng-click="editSuiviCommandeD()">SD</a>'
									             +'</div>'
										},
										{
											field : '',
											cellTemplate : '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerProduit()"> <i class="fa fa-fw fa-pencil"></i></button> <button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete('
									             + $scope.typeAlert
									             + ')"> <i class="fa fa-fw fa-trash-o"></i></button>'
									             +'</div>'
										} ];
					});
	$scope.filterOptions = {
			filterText : "",
			useExternalFilter : true
		};

	
	
		$scope.totalServerItems = 0;
		$scope.pagingOptions = {
			pageSizes : [ 5, 10, 20 ],
			pageSize : 14,
			currentPage : 1
		};
		
		
		$scope.setPagingData = function(data, page,
				pageSize) {
			var pagedData = data.slice((page - 1)
					* pageSize, page * pageSize);
			$scope.myData = pagedData;
			$scope.totalServerItems = data.length;
			if (!$scope.$$phase) {
				$scope.$apply();
			}
		};

		
		
		$scope.getPagedDataAsync = function(pageSize, page,
				searchText) {
			setTimeout(
					function() {
						var data;
						if (searchText) {
							var ft = searchText
									.toLowerCase();
							$http
									.get(
											baseUrl
													+ "/partieInteressee/all")
									.success(
											function(
													largeLoad) {
												data = largeLoad
														.filter(function(
																item) {
															return JSON
																	.stringify(
																			item)
																	.toLowerCase()
																	.indexOf(
																			ft) != -1;
														});
												$scope
														.setPagingData(
																data,
																page,
																pageSize);
											});

						} else {
							$http
									.get(baseUrl
											+ "/partieInteressee/all")
							.success(function(largeLoad) {
								$scope.setPagingData(
										largeLoad, page,
										pageSize);
							});
						}
					}, 100);
		};

		$scope.getPagedDataAsync(
				$scope.pagingOptions.pageSize,
				$scope.pagingOptions.currentPage);

		$scope
				.$watch(
						'pagingOptions',
						function(newVal, oldVal) {
							if (newVal !== oldVal
									&& newVal.currentPage !== oldVal.currentPage) {
								$scope
										.getPagedDataAsync(
												$scope.pagingOptions.pageSize,
												$scope.pagingOptions.currentPage,
												$scope.filterOptions.filterText);
							}
						}, true);
		$scope.$watch('filterOptions', function(newVal,
				oldVal) {
			if (newVal !== oldVal) {
				$scope.getPagedDataAsync(
						$scope.pagingOptions.pageSize,
						$scope.pagingOptions.currentPage,
						$scope.filterOptions.filterText);
			}
		}, true);

		$scope.gridOptions = {
			data : 'myData',
			columnDefs : 'colDefs',
			enablePaging : true,
			showFooter : true,
			totalServerItems : 'totalServerItems',
			pagingOptions : $scope.pagingOptions,
			selectedItems : $scope.selectedRows,
			filterOptions : $scope.filterOptions,
		};
	/** Fin de gestion des Grids de la partie Interesse */

		/** Fin de gestion des Grids de la produit */
		$scope.editSuiviCommande = function() {
	        $scope.displayMode1="edit"
	        $scope.displayMode= "edit"
	       $scope.displayMode2= "list"
	        	console.log(  $scope.displayMode);
	       };
	       $scope.editSuiviCommandeD = function() {
		        $scope.displayMode2="edit"
		        $scope.displayMode="edit"
		        $scope.displayMode1="list"
		        	console.log(  $scope.displayMode2);
		       };
	       
	/***************************************************
	 * Gestion des Documents
	 **************************************************/
	/** ***Liste desVariables ****/
       $scope.listeDocument = [];
       $scope.listeTypeDoc = [];
       $scope.name="PI";
       // GetId.designation
       $scope.type = {

        status : ''
       };
       $scope.showStatus = function(id) {

        $scope.type.status = id;
        var selected = $filter('filter')(
          $scope.listeTypeDocumentCache, {
           id : $scope.type.status
          });
        if ($scope.type.status && selected.length) {
         return selected[0].designation;
        }else {
                   return "Not Set";
                  }
       };           
       // ajout d'un Document Partie Interesee
       $scope.ajoutDocPI = function() {
        $scope.DocPartieInteresseInserree = {
         typeDocument : '',
         vUUIDDocument : '',
         path:''
        };
        $scope.listeDocument
          .push($scope.DocPartieInteresseInserree);
       };
       // Enregistrer Document Partie Interesee
       $scope.saveDocPI = function(dataDocPI, id) {
    	   $scope.deleteValue = "non";
        angular.extend(dataDocPI, {
         id : id
        });
        console
          .log("Success Save Document in Partie Interessee "
            + dataDocPI.id);
       };
    // Supprimer Document
		$scope.removeDocPi = function(index) {
			$scope.listeDocument.splice(index, 1);
		};
		
	//Download Document
		$scope.download = function(uuid) {
		    downloadService.download(uuid)
		        .then(function(success) {
		            console.log('success : ' + success);
		        }, function(error) {
		            console.log('error : ' + error);
		        });
		};
		
	/** Fin de gestion des Grids Suivi de Commande */

}]);
