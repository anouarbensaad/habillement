'use strict'
angular.module('gpro.suiviCommande', ["ngResource"])
.controller(
				'SuiviCommandeCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$log',
						'baseUrlGpao',
function($scope, $http, $filter, $log,baseUrlGpao) {
	$log.info("----------- GPAO suiviCommande ??----------");
               
	// Déclaration des variables						
	$scope.displayMode = "list";	
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
		$http.get(baseUrl+ "/gestionnaireCache/listePhaseProduitCache")
				.success(
						function(data) {
							$scope.listePhaseCache = data;
							$scope.listePhaseCache.subItem = {designation: data.designation};
						});
	}
	
	// Liste de Client cache
	$scope.listeClientCache = function() {
		$http
				.get(
						baseUrlGs
								+ "/gestionnaireCache/listeClientCache")
				.success(function(dataC) {
					$scope.listeClientCache = dataC;

				});
	}
	
	$scope.listePhaseCache();
	$scope.listeClientCache();
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
		 * $log.debug("Article UPDATE ..Max " +
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
	$scope.annulerAjout = function() {
		$scope.OFCourant = {};
		$scope.displayMode = "list";
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
	$scope.typeAlert = 3;
	$scope.filterOptions = {
		filterText : "",
		useExternalFilter : true
	};
	$scope.totalServerItems = 0;
	$scope.pagingOptions = {
		pageSizes : [ 25, 50, 100 ],
		pageSize : 25,
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
		setTimeout(function() {

			if (searchText) {
				var ft = searchText.toLowerCase();

				data = $scope.myData
						.filter(function(item) {
							return JSON.stringify(item)
									.toLowerCase()
									.indexOf(ft) != -1;
						});
				$scope.setPagingData(data, page,
						pageSize);
			} else {

				$scope.setPagingData($scope.myData,
						page, pageSize);

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
		dataselected : 'myDataselected',
		selectedItems : [],

		data : 'myData',
		columnDefs : [
				{
					field : 'clientId',
					displayName : 'Client'
				},
				{
					field : '',
					displayName : 'Réf.Produit'
				},
				{
					field : '',
					displayName : 'Produit'
				},
				{
					field : '',
					displayName : 'N° OF'
				},
				{
					field : 'quantite',
					displayName : 'Q.OF'
				},
				{
					field : 'phaseId',
					displayName : 'Phase'
				},
				{
					field : 'methode',
					displayName : 'Méthode'
				},
				{
					field : 'dateDebut',
					displayName : 'Date Déb.'
				},
				{
					field : 'dateFin',
					displayName : 'Date Fin.'
				},
				{
					field : '',
					displayName : 'Q.E'
				},
				{
					field : '',
					displayName : 'Q.S'
				},
				{
					field : 'manque',
					displayName : 'Q.Manq.'
				},
				{
					field : '',
					displayName : 'Etat'
				},
				{
					field : '',
					cellTemplate : '<div class="buttons" ><button data-nodrag class="btn btn-suivi btn-xs" ng-click="modifierOuCreerSuiviOrdreFabrication()">Suivi</button></div>'
				} ],
		enablePaging : true,
		showFooter : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,
		showSelectionCheckbox : true,
		enableHighlighting : true,
		beforeSelectionChange : $scope.beforeVehicleSelectionChange,
		checkboxHeaderTemplate : '<input class="ngSelectionHeader" type="checkbox" ng-show="multiSelect" ng-model="allSelected" ng-change="toggleSelectAll(allSelected, true)"/>',
		selectedItems : $scope.mySelections,
		selectWithCheckboxOnly : false,
		multiSelect : true

	};
	/** Fin de gestion des Grids Suivi de Commande */

}]);
