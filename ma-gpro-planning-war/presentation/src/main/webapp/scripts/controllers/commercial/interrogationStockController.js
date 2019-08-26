'use strict'
/**
 * Gestion Etat
 */
angular.module('gpro.interrogationStock', [])
.controller('InterrogationStockController',[ '$scope', '$http', '$filter','$log', 'baseUrlGs','baseUrl','downloadService',
	function($scope, $http, $filter,$log, baseUrlGs ,baseUrl,downloadService) {
	//declaration variable
        $scope.etatCourant={};
        $scope.myData = [];
        $scope.initMyData = [];
        $scope.listeMagasinCache = [];
		$scope.ListeEmplacementCache = [];
		$scope.ListeRaisonCache = [];
		$scope.ListFamillesCache =[] ;
		$scope.listeSousFamilleCache =[];
		$scope.listeArticle = [];
		$scope.listeFournisseur = [];
        $scope.quantite=0;
		$scope.capacite=1;
		$scope.taux=0;
		
		$scope.listeArticleFiltred=[];

		$scope.onChangeType = function(typeId)
		{
			//console.log("#################onchangeType######### ",typeId);
			
			if($scope.listeArticle.length == 0)
				$scope.listeArticleFct();

			var items = $scope.listeFamilleCache;
			var filtered = [];
	
			
			if (typeId === undefined || typeId == null) {
				return items;
			}
			angular.forEach(items, function (item) {
				if (typeId == item.idTypeArticle) {
					filtered.push(item);
				}
			});
			$scope.listeFamilleCacheFiltred = filtered;

		//	console.log('liste filtred Famille : ',filtered);


		
			return 	$scope.listeFamilleCacheFiltred;
		}

		$scope.onChangeFamille = function(idFamille)
		{
			//console.log("#################onChangeFamille######### ",idFamille);

			var items = $scope.listeSousFamilleArticleCache;
			var filtered = [];
			//console.log("#################ITEMS######### ",items);
			
			if (idFamille === undefined || idFamille == null) {
				return items;
			}
			angular.forEach(items, function (item) {
				if (idFamille == item.idFamilleArticle) {
					filtered.push(item);
				}
			});
			$scope.listeSousFamilleCacheFiltred = filtered;

		//console.log('liste filtred sousFamille : ',filtered);


		
			return 	$scope.listeSousFamilleCacheFiltred;
		}

		$scope.onChangeSousFamille = function(idSousFamille)
		{
		//	console.log("#################onChangeSousFamille######### ",idSousFamille);

			if(idSousFamille=='')
			{
				$scope.listeArticleFiltred = $scope.listeArticle;
				return ;
			}
			var items = $scope.listeArticle;
			var filtered = [];
			//console.log("#################ITEMS######### ",items);
			
			if (idSousFamille === undefined || idSousFamille == null) {
				return items;
			}
			angular.forEach(items, function (item) {
				if (idSousFamille == item.sousFamilleArtEntite) {
					filtered.push(item);
				}
			});
			$scope.listeArticleFiltred = filtered;

		//console.log('liste filtred Article : ',filtered);


		
			return 	$scope.listeArticleFiltred;
		}
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
								$scope.listeArticleFiltred = dataArticle;
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
									+ "/emplacement/all")
					.success(
							function(dataEmplacementCache) {
								$scope.ListeEmplacementCache = dataEmplacementCache;

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
		//$scope.listeArticleFct();
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
				//	url = baseUrlGs+ "/reportgs/etatStockGlobal?"+ params;
					url = baseUrlGs+ "/fiches/etatStockGlobal?"+ params;

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
	$scope.rechercherEtat = function(etatCourant) {
		
		if(typeof $scope.etatCourant.date === 'undefined'){
			var date = "";
		}
		else{
			var longDate= Date.parse($scope.etatCourant.date);
			var date = new Date(longDate);
			var date = varDate.getFullYear()  +'-'+ (varDate.getMonth() + 1) +'-'+ varDate.getDate();
		}

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
		+ "&typeRapport=Global"
		+ "&type=xls";
	
		//console.log("-----------etatCourante--------------" + JSON.stringify(etatCourante,null,""));
		$http
				.get(
						baseUrlGs
						+ "/entiteStock/etatStockGlobal?"+
						params)
				.success(
						function(resultat) {
							$scope.myData = resultat;
							$scope.initMyData = $scope.myData;
                        //   $scope.quantite=resultat.quantiteTotale;
							//console.log("------etat---------" + JSON.stringify($scope.myData,null,""));

					});


				
	}
	
	
	$scope.resetFields = function(){
		$scope.etatCourant={};
		$scope.etatCourant.typeArticle='1';
		$scope.myData=[];
	}
	
	$scope.calculOccupation = function(){
		$scope.taux=($scope.quantite/$scope.capacite)*100;
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
				field : 'familleArticle',
				displayName : 'Famille',
				width :'12%',
				enableCellEdit : false
			},
			{
				field : 'sousFamilleArticle',
				displayName : 'S. Famille',
				width :'12%',
				enableCellEdit : false
			},
				{
					field : 'referenceArticle',
					displayName : 'Réf.',
					width :'12%',
					enableCellEdit : false
				},
				{
					field : 'libelleArticle',
					displayName : 'Désignation',
					width :'20%',
					enableCellEdit : false
				},

				{
					field : 'codeFournisseur',
					displayName : 'Code Fournisseur',
					width :'12%',
					enableCellEdit : false
				},

				{
					field : 'qteActuelle',
					displayName : 'Q.Act.',
					width :'10%',
					cellFilter: 'number:3',
					enableCellEdit : false

				},
				{
					field : 'qteReservee',
					displayName : 'Q.Res.',
					width :'10%',
					cellFilter: 'number:3',
					enableCellEdit : false

				},
								
					
				{
					field : 'qteLibre',
					displayName : 'Q.Libre',
					width :'10%',
					cellFilter: 'number:3',
					enableCellEdit : false
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
					field : 'familleArticle',
					displayName : 'Famille',
					width :'12%',
					enableCellEdit : false
				},
				{
					field : 'sousFamilleArticle',
					displayName : 'S. Famille',
					width :'12%',
					enableCellEdit : false
				},
					{
						field : 'referenceArticle',
						displayName : 'Réf.',
						width :'12%',
						enableCellEdit : false
					},
					{
						field : 'libelleArticle',
						displayName : 'Désignation',
						width :'20%',
						enableCellEdit : false
					},
	
					{
						field : 'codeFournisseur',
						displayName : 'Code Fournisseur',
						width :'10%',
						enableCellEdit : false
					},
	
					{
						field : 'qteActuelle',
						displayName : 'Q.Act.',
						width :'10%',
						cellFilter: 'number:3',
						enableCellEdit : false
	
					},
					{
						field : 'qteReservee',
						displayName : 'Q.Res.',
						width :'10%',
						cellFilter: 'number:3',
						enableCellEdit : false
	
					},
									
						
					{
						field : 'qteLibre',
						displayName : 'Q.Libre',
						width :'10%',
						cellFilter: 'number:3',
						enableCellEdit : false
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
					field : 'familleArticle',
					displayName : 'Famille',
					width :'12%',
					enableCellEdit : false
				},
				{
					field : 'sousFamilleArticle',
					displayName : 'S. Famille',
					width :'12%',
					enableCellEdit : false
				},
					{
						field : 'referenceArticle',
						displayName : 'Réf.',
						width :'12%',
						enableCellEdit : false
					},
					{
						field : 'libelleArticle',
						displayName : 'Désignation',
						width :'20%',
						enableCellEdit : false
					},
					{
						field : 'couleur',
						displayName : 'Couleur',
						width :'10%',
						enableCellEdit : false
					},
					{
						field : 'producteur',
						displayName : 'Producteur',
						width :'10%',
						enableCellEdit : false
					},
					{
						field : 'codeFournisseur',
						displayName : 'Code Fournisseur',
						width :'10%',
						enableCellEdit : false
					},
	
					{
						field : 'qteActuelle',
						displayName : 'Q.Act.',
						width :'10%',
						cellFilter: 'number:3',
						enableCellEdit : false
	
					},
					{
						field : 'qteReservee',
						displayName : 'Q.Res.',
						width :'10%',
						cellFilter: 'number:3',
						enableCellEdit : false
	
					},
					
					{
						field : 'qteLibre',
						displayName : 'Q.Libre',
						width :'10%',
						cellFilter: 'number:3',
						enableCellEdit : false
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
		$scope.objectRecherche.historique="RESERVATION";
		$scope.objectRecherche.sortie = false;
		$scope.objectRecherche.article = $scope.myData[index].articleId;
		$scope.objectRecherche.entiteStockId = null;
		$scope.objectRecherche.etat = null;
		

		
		$http
				.post(
						baseUrlGs
								+ "/mouvementStock/rechercheParCritere",
								$scope.objectRecherche)
				.success(
						function(resultat) {
							$scope.listeReservations = resultat.mouvementStock;
							//$log.debug("------LISTE : $scope.listeEntrees----** " + JSON.stringify($scope.listeEntrees , null,"  ") );
							
							//Récupération de la liste des sorties
							$scope.objectRecherche.historique="COMMANDE";
							$scope.objectRecherche.etat ="En attente de livraison";
							$scope.objectRecherche.sortie = null;
							$scope.objectRecherche.entiteStockId = null;
							$scope.objectRecherche.article = $scope.myData[index].articleId;

							$http
									.post(
											baseUrlGs
													+ "/mouvementStock/rechercheParCritere",
													$scope.objectRecherche)
									.success(
											function(resultat) {
												$scope.listeAchats = resultat.mouvementStock;
											
												var sommeQteAchat =0;

												angular.forEach(resultat.mouvementStock, function (value) {
													
													sommeQteAchat += value.quantiteReelle;
											   });
														
												$scope.entiteStockCourante = $scope.myData[index];

												$scope.entiteStockCourante.achat = sommeQteAchat;
												//$scope.entiteStockCourante.stockLibre = Number($scope.entiteStockCourante.qteActuelle)-Number($scope.entiteStockCourante.qteReservee) ;
												//console.log("entiteStockCourante"+ JSON.stringify($scope.entiteStockCourante,null," "));
												$scope.modeConsultation = true;
										
												
											});
						});
		
	
		

	}
	
	$scope.retour = function(){
		$scope.modeConsultation = false;
	}
	
} ]);