'use strict'
/**
 * Gestion Etat
 */
angular.module('gpro.etat', [])
.controller('EtatCtrl',[ '$scope', '$http', '$filter','$log', 'baseUrlGs','baseUrl','downloadService',
	function($scope, $http, $filter,$log, baseUrlGs ,baseUrl,downloadService) {
	//declaration variable
        $scope.etatCourant={};
        $scope.myData = [];
        $scope.initMyData = [];
        $scope.listeMagasinCache = [];
		$scope.ListEmplacementCache = [];
		$scope.ListeRaisonCache = [];
		$scope.ListFamillesCache =[] ;
		$scope.listeSousFamilleCache =[];
		$scope.listeArticle = [];
		$scope.listeFournisseur = [];
		
		/***************************************************
		 * Gestion de Cache des Referentiels *
		 **************************************************/
		
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

		 //liste famille cache 
         	// Liste des FamilleCache
		  	$scope.listeFamilleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeFamilleArticleCache")
										.success(
												function(dataC) {
													$scope.listeFamilleCache = dataC;
													$log.debug("Famille : "+dataC.length);

												});
							}

	                    
//Liste sous famille cache 
								$scope.listeSousFamilleCache = function() {
								$http
										.get(
												baseUrl
														+ "/gestionnaireCache/listeSousFamilleArticleCache")
										.success(
												function(dataC) {
													$scope.listeSousFamilleArticleCache = dataC;

												});
							}
                             



		// Liste des magasinCache
		$scope.listeMagasinCache = function() {
			$http
					.get(
							baseUrlGs
									+ "/gestionnaireCache/listeMagasinCache")
					.success(
							function(dataMagasin) {
								$scope.listeMagasinCache = dataMagasin;

							});
		}
		// Liste des emplacementCache
		$scope.ListeEmplacementCache = function() {
			$http
					.get(
							baseUrlGs
									+ "/gestionnaireCache/listEmplacementCache")
					.success(
							function(dataEmplacementCache) {
								$scope.ListEmplacementCache = dataEmplacementCache;

							});
		}
		// Liste des raisonCache
		$scope.ListeRaisonCache = function() {
			$http
					.get(
							baseUrlGs
									+ "/gestionnaireCache/listeRaisonCache")
					.success(
							function(dataSousFamilleCache) {
								$scope.ListeRaisonCache = dataSousFamilleCache;

							});
		}
		
		// Liste Fournisseur
		// Liste des PartieInteressee (avec FamilleId=1)
		$scope.listeFournisseursCache = function() {
			$http
					.get(baseUrl + "/gestionnaireCache/listePartieInteresseeCache")
					.success(
							function(dataPartieInteressee) {

								$scope.listeFournisseur = $filter ('filter') (dataPartieInteressee , {famillePartieInteressee : 2});

							});
		}
		
		$scope.listeFournisseursCache();
		$scope.listeArticleFct();
        $scope.listeFamilleCache ();
		$scope.listeSousFamilleCache();
		$scope.listeMagasinCache();
		$scope.ListeRaisonCache();
		$scope.ListeEmplacementCache();
		
		
		   /*** PDF ***/
		$scope.download = function(etatCourant, typeRapport) {
			
			if(typeof $scope.etatCourant.date === 'undefined'){
				var date = "";
			}
			else{
				var longDate= Date.parse($scope.etatCourant.date);
				var date = new Date(longDate);
				var date = varDate.getFullYear()  +'-'+ (varDate.getMonth() + 1) +'-'+ varDate.getDate();
			}
			var url;
			
				var params = 
				"articleType=" + etatCourant.typeArticle
				+ "&famille=" + etatCourant.familleArticle
				+ "&sousFamille=" + etatCourant.sousFamilleArticle
				+ "&refArticle=" + etatCourant.refArticle
				+ "&article=" + etatCourant.article
				+ "&grosseur=" + etatCourant.grosseur
				+ "&metrage=" + etatCourant.metrage
				+ "&matiere=" + etatCourant.matiere
				+ "&lot=" + etatCourant.lot
				+ "&date=" +date
				+ "&operateurQuantite=" + etatCourant.operateurQuantite
				+ "&quantite=" + etatCourant.quantite
				+ "&magasin=" + etatCourant.magasin
				+ "&emplacement=" + etatCourant.emplacement
				+ "&site=" + etatCourant.site
				+ "&zone=" + etatCourant.zoneDispo
				+ "&oa=" + etatCourant.oa
				+ "&typeRapport="+ typeRapport
				+ "&type=xls";
			
				if (typeRapport == "Basique"){
					url = baseUrlGs+ "/reportgs/etatStock?"+ params;
				} 
					
				else if (typeRapport == "Global"){
					url = baseUrlGs+ "/reportgs/etatStockGlobal?"+ params;

				} 
									
				else{
					url = baseUrlGs+ "/reportgs/etatStockDetaille?" + params;
				}
				
				$log.info("url"+url);
					
			downloadService.download(url).then(
					function(success) {
						//$log.debug('success : ' + success);
						//$scope.annulerAjout();
					}, function(error) {
						//$log.debug('error : ' + error);
					});

		};	
    //Voir Etat
	$scope.voirEtat = function(){
		if($scope.etatCourant.typeArticle == "1"){
			$scope.displayEtat = "fourniture";
		}else if($scope.etatCourant.typeArticle == "3"){
			$scope.displayEtat = "filaCoudre";
		}else if($scope.etatCourant.typeArticle == "2"){
			$scope.displayEtat = "tissu";
		}
		else{
			$scope.displayEtat = "alert";
		}
		
		$scope.rechercherEtat($scope.etatCourant);
	}
	//Recherche Etat par type
	$scope.rechercherEtat = function(etatCourante) {
		
		//console.log("-----------etatCourante--------------" + JSON.stringify(etatCourante,null,""));
		$http
				.post(
						baseUrlGs
						+ "/entiteStock/rechercheParCritere",
								etatCourante)
				.success(
						function(resultat) {
							$scope.myData = resultat.entiteStock;
							$scope.initMyData = $scope.myData;
							//console.log("------etat---------" + JSON.stringify($scope.myData,null,""));

					});


				
	}
	
	
	$scope.resetFields = function(){
		$scope.etatCourant={};
		$scope.etatCourant.typeArticle='1';
		$scope.myData=[];
	}
	
	
	/***************************************************
	 * Gestion des Grids Etat Fourniture
	 **************************************************/
	$scope.typeAlert = 3;
	$scope.filterOptions = {
		filterText : "",
		useExternalFilter : true
	};
	$scope.totalServerItems = 0;
	$scope.pagingOptions = {
		pageSizes : [ 5, 10, 20  ],
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
//	$scope.getPagedDataAsync = function(pageSize, page,
//			searchText) {
//		setTimeout(function() {
//			var data;
//			  data = $scope.myData;
//
//			if (searchText) {
//				var ft = searchText.toLowerCase();
//				  data = $scope.myData
//						.filter(function(item) {
//							return JSON.stringify(item)
//									.toLowerCase()
//									.indexOf(ft) != -1;
//						});
//				$scope.setPagingData(data, page,
//						pageSize);
//			} else {
//
//				$scope.setPagingData($scope.myData,
//						page, pageSize);
//
//			}
//		}, 100);
//	};
	
	$scope.getPagedDataAsync = function(pageSize, page,
			searchText) {
		setTimeout(
				function() {
					if (searchText) {
						var ft = searchText
								.toLowerCase();
						
						var data  = $scope.initMyData.filter(function(item) {
	                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
						});
						
	                        $scope
							.setPagingData(
									data,
									page,
									pageSize);

					} else {
						
						
						$scope
						.setPagingData(
								$scope.initMyData,
								page,
								pageSize);
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
		columnDefs : [
				{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'10%',
					enableCellEdit : false
				},
				{
					field : 'codeFournisseur',
					displayName : 'Code Fournisseur',
					width :'6%',
					enableCellEdit : false
				},
				{
					field :'dateEntree | date : "dd/MM/yyyy"',
					displayName : 'Date Entree',
					width :'8%',
					enableCellEdit : false
				},
				{
					field : 'referenceLot',
					displayName : 'Lot',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'20%',
					enableCellEdit : false
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%',
					enableCellEdit : false
				},
				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'6%',
					enableCellEdit : false

				},
				{
					field : 'prixUnitaire',
					displayName : 'PU',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'5%',
					enableCellEdit : false
				},
				{
					field : 'prixTotal',
					displayName : 'P.T',
					width :'5%',
					enableCellEdit : false
				},
				{
					field : 'designationMagasin',
					displayName : 'Magasin',
					width :'8%',
					enableCellEdit : false
				},
				{
					field : 'emplacement',
					displayName : 'Empl.',
					width :'8%'
				},
				{
					field : 'oa',
					displayName : 'OA',
					enableCellEdit : false,
					width :'10%'
				},
				{
					field : '',
					enableCellEdit : false,
					width :'3%',
					cellTemplate : '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="consulterMouvement()"><i class="fa fa-fw fa-info"></i></button></div>'
				}
				 ],
		enablePaging : true,
		showFooter : true,
		enableHighlighting : false,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,
		enableCellSelection: true,
        enableRowSelection: false,
        enableCellEditOnFocus: true,
	};
	/** Fin de gestion des Grids Etat Fourniture */
	
	/** Gestion Grid Etat Fil A coudre **/
	$scope.gridOptionsFAC = {
			dataselected : 'myDataselected',
			selectedItems : [],

			data : 'myData',
			columnDefs : [
					{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'10%',
					enableCellEdit : false
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'30%',
					enableCellEdit : false
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%',
					enableCellEdit : false
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'8%',
					enableCellEdit : false

				},
				{
					field : 'prixUnitaire',
					displayName : 'PU',
					width :'8%',
					enableCellEdit : false
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'prixTotal',
					displayName : 'P.T',
					width :'8%',
					enableCellEdit : false
				},
				{
					field : 'designationMagasin',
					displayName : 'Magasin',
					width :'10%',
					enableCellEdit : false
				},
				{
					field : 'emplacement',
					displayName : 'Empl.',
					width :'10%'
				},
				{
					field : 'oa',
					displayName : 'OA',
					enableCellEdit : false,
					width :'8%'
				},
				{
					field : '',
					enableCellEdit : false,
					width :'3%',
					cellTemplate : '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="consulterMouvement()"><i class="fa fa-fw fa-pencil"></i></button></div>'
				}
					 ],
		enablePaging : true,
		showFooter : true,
		enableHighlighting : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,
		enableCellSelection: true,
        enableRowSelection: false,
        enableCellEditOnFocus: true,
	};
	/** Fin Gestion Grid Etat Fil A Coudre **/
	
	/** Gestion Grid Etat Tissu **/
	$scope.gridOptionsTisuu = {
			dataselected : 'myDataselected',
			selectedItems : [],

			data : 'myData',
			columnDefs : [
					{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'10%',
					enableCellEdit : false
				},
				{
					field : 'codeFournisseur',
					displayName : 'Code Fournisseur',
					width :'8%',
					enableCellEdit : false
				},
				{
					field :'dateEntree | date : "dd/MM/yyyy"',
					displayName : 'Date Entree',
					width :'8%',
					enableCellEdit : false
				},
				{
					field : 'referenceLot',
					displayName : 'Lot',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'20%',
					enableCellEdit : false
				},
				{
					field : 'familleArticle',
					displayName : 'Famille',
					width :'10%',
					enableCellEdit : false
				},
				
				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'rouleauxActuel',
					displayName : 'R.Act.',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'prixUnitaire',
					displayName : 'PU',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'pmp',
					displayName : 'PMP',
					width :'5%',
					enableCellEdit : false
				},
				{
					field : 'prixTotal',
					displayName : 'P.T',
					width :'6%',
					enableCellEdit : false
				},
				{
					field : 'designationMagasin',
					displayName : 'Magasin',
					width :'10%',
					enableCellEdit : false
				},
				{
					field : 'emplacement',
					displayName : 'Empl.',
					width :'8%'
				},
				{
					field : 'oa',
					displayName : 'OA',
					enableCellEdit : false,
					width :'8%'
				},
				{
					field : '',
					enableCellEdit : false,
					width :'3%',
					cellTemplate : '<div class="buttons pull-right"><button data-nodrag class="btn btn-default btn-xs" ng-click="consulterMouvement()"><i class="fa fa-fw fa-pencil"></i></button></div>'
				}
				],
				
		enablePaging : true,
		showFooter : true,
		enableHighlighting : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		filterOptions : $scope.filterOptions,
		enableCellSelection: true,
        enableRowSelection: false,
        enableCellEditOnFocus: true
	};
	/** Fin Gestion Grid Etat Tisuu **/

	
	$scope.sauvegarderNouveauEmplacement = function(){
		
		$http
		.post(
				baseUrlGs
						+ "/entiteStock/modifierTout",
						$scope.myData)
		.success(
				function(response) {

					console.log("success");
				});
	}
	
	$scope.modeConsultation = false;
	$scope.objectRecherche={};
	$scope.consulterMouvement = function(){
		
		var index = this.row.rowIndex;
		
		$scope.objectRecherche.entiteStockId=$scope.myData[index].id;
		//Récupération de la liste des entrées
		$scope.objectRecherche.historique="ENTREE";
		
		$http
				.post(
						baseUrlGs
								+ "/mouvementStock/rechercheParCritere",
								$scope.objectRecherche)
				.success(
						function(resultat) {
							$scope.listeEntrees = resultat.mouvementStock;
							//$log.debug("------LISTE : $scope.listeEntrees----** " + JSON.stringify($scope.listeEntrees , null,"  ") );
							
							//Récupération de la liste des sorties
							$scope.objectRecherche.historique="SORTIE";
							$http
									.post(
											baseUrlGs
													+ "/mouvementStock/rechercheParCritere",
													$scope.objectRecherche)
									.success(
											function(resultat) {
												$scope.listeSorties = resultat.mouvementStock;
												//console.log("------LISTE : $scope.listeSorties----** " + JSON.stringify($scope.listeSorties , null,"  ") );
												
												//Récupération de la liste des réservations
												
												$scope.objectRecherche.historique="RESERVATION";
												$http
														.post(
																baseUrlGs
																		+ "/mouvementStock/rechercheParCritere",
																		$scope.objectRecherche)
														.success(
																function(resultat) {
																	$scope.listeReservations = resultat.mouvementStock;
																	//$log.debug("------LISTE : $scope.listeReservation----** " + JSON.stringify($scope.listeReservations , null,"  ") );
																	
																	$scope.entiteStockCourante = $scope.myData[index];
																	//console.log("entiteStockCourante"+ JSON.stringify($scope.entiteStockCourante,null," "));
																	$scope.modeConsultation = true;
																});
												
											});
						});
		
	
		

	}
	
	$scope.retour = function(){
		$scope.modeConsultation = false;
	}
	
} ]);