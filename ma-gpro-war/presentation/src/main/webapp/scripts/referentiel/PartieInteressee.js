'use strict'
angular
.module('gpro.partieInteressee', [ "ngResource" ])
.controller(
		'PartieInteresseeController',
		[
		 '$scope',
		 '$filter',
		 '$http',
		 '$log',
		 'downloadService',
		 'baseUrl',
		 function($scope, $filter, $http, $log, downloadService, baseUrl) {
			 // Déclaration des variables globales utilisées
			 var data;
			 $scope.displayMode = "list";
			 $scope.displayMenu = "pi";
			 $scope.listeRepresentant = [];
			 $scope.listeDocument = [];
			 $scope.ListCategoriePICache = [];
			 $scope.ListTypePICache = [];
			 $scope.ListFamillePICache = [];
			 $scope.ListSitePICache = [];
			 $scope.ListActifPI = [{actif:true,designation:'oui'},{actif:false,designation:'non'}];
			 $scope.partieInteresseeCourante = {};
			 $scope.resultatRecherche = $scope.listePartieInteressee;
			 /***************************************************
			  * Gestion de la Menu PI
			  **************************************************/
			 $scope.changeTaPartieInteresse = function() {
				 $scope.displayMenu = "pi";
			 }
			 $scope.changeTaElementBase = function() {
				 $scope.displayMenu = "eb";
			 }
			 $scope.changeTaGestionCommerciale = function() {
				 $scope.displayMenu = "gc";
			 }

			 /***************************************************
			  * Gestion Cache
			  */
			 // Liste des DeviseCache
			 $scope.ListDeviseCache = function() {
				 $http
				 .get(
						 baseUrl
						 + "/gestionnaireCache/listeDeviseCache")
						 .success(
								 function(dataDeviseCache) {
									 $log.debug("listeDeviseCache :"
											 + dataDeviseCache.length);
									 $scope.ListDeviseCache = dataDeviseCache;
								 });
			 }
			 // Liste des TypeDocs
			 $scope.listeTypeDocumentCache = function() {
				 $http
				 .get(
						 baseUrl
						 + "/gestionnaireCache/listeTypeDocumentCache")
						 .success(
								 function(dataTypeDocCache) {
									 $log.debug("listeTypeDocumentCache :"
											 + dataTypeDocCache.length);
									 $scope.listeTypeDocumentCache = $filter('filter')(dataTypeDocCache, {module:"PI"});
								 });
			 }
			 $scope.ListDeviseCache();
			 $scope.listeTypeDocumentCache();
			 /***************************************************
			  * Gestion de la PI
			  **************************************************/
			 $scope.deleteValue="oui";
			 //Annuler Ajout
			 $scope.cancelAddPartieInteressee = function(rowform, index, id, designation, liste){
				 if (angular.isDefined(id)) {

					 $log.debug("DEF ID");
					 $scope.deleteValue="non";
					 rowform.$cancel();
					 $log.debug("CANCEL");
				 }else{	
					 $log.debug("UND ID  "+id);
					 if(designation ==""){
						 $scope.deleteValue=="oui"
							 $log.debug("Designation : "+designation);
						 $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
						 liste.splice(index, 1);
						 rowform.$cancel();
						 $log.debug("DELETE")
					 }else{
						 $log.debug("Designation :"+designation);
						 $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
						 rowform.$cancel();
						 $log.debug("CANCEL");
					 }
				 }
				 $scope.deleteValue="oui";
			 }


			 // Lister les parties interessées
			 $scope.listePartieInteressee = function() {
				 $http.get(baseUrl + "/partieInteressee/all")
				 .success(function(data) {
					 $log.debug("listePartieInteressee : "+data.length);
					 $scope.partieInteresssees = data;
					 $scope.myData = data;
				 });
			 }
			 // Rechercher PI
			 $scope.pagingOptions = {
					 pageSizes : [ 5, 10, 13 ],
					 pageSize : 13,
					 currentPage : 1
			 };

			 $scope.rechercherPartieInteressee = function(
					 partieInteresseeCourante) {
				 $http
				 .post(
						 baseUrl
						 + "/partieInteressee/recherchePIMulticritere",
						 partieInteresseeCourante)
						 .success(
								 function(resultat) {
									 $scope.myData = resultat.partieInteresseValues;
									 // Pagination du resultat de
									 // data, page,pageSize
									 $scope.setPagingData(
											 $scope.myData,
											 $scope.pagingOptions.currentPage,
											 $scope.pagingOptions.pageSize);
									 $scope.displayMode = "rechercher";
									 $scope.recherchePartieIntereseeFormCritere
									 .$setPristine();
								 });

			 }
			 $scope.rechercherPartieInteressee({});
			 // ** Ajout Partie Interesse **
			 $scope.AffectationPartieInteressee = function(
					 partieInteressee) {
				 $scope.partieInteresseeCourante = {};
				 $scope.creationPartieInteressee.$setPristine();
				 $scope.listeRepresentant = [];
				 $scope.listeDocument = [];
				 $scope.displayMode = "edit";
			 }
			 // Ajout et Modification Partie Interessee
			 $scope.modifierOuCreerPartieInteressee = function() {
				 var index = this.row.rowIndex;
				 // getArticle
				 $http
				 .get(
						 baseUrl
						 + "/partieInteressee/getId:"
						 + $scope.myData[index].id)
						 .success(
								 function(datagetPI) {
									 $scope.listeRepresentant = datagetPI.representants;
									 $scope.listeDocument = datagetPI.documents;
									 $scope.myData[index].representants = $scope.listeRepresentant;
									 $scope.myData[index].documents = $scope.listeDocument;
									 $scope.partieInteresseeCourante.actif=datagetPI.actif.toString();
//									 if(datagetPI.actif == true){
//									 $scope.partieInteresseeCourante.actif='oui';
//									 $scope.ListActifPI.actif=true; 
//									 }else{
//									 $scope.partieInteresseeCourante.actif='non';
//									 $scope.ListActifPI.actif=false;
//									 }
								 });
				 $scope.partieInteresseeCourante = $scope.myData[index] ? angular
						 .copy($scope.myData[index])
						 : {};

						 $scope.displayMode = "edit";
			 }
			 // Sauvegarder PI
			 $scope.sauvegarderAjout = function(partieInteressee) {
				 if (angular.isDefined(partieInteressee.id)) {
					 $scope
					 .updatePartieInteressee(partieInteressee);
				 } else {
					 $scope
					 .creerPartieInteressee(partieInteressee);
				 }
				// $scope.rechercherPartieInteressee({});
			 }
			 // Mise à jour de la partie interessée
			 $scope.updatePartieInteressee = function(
					 partieInteressee) {
				 partieInteressee.representants = $scope.listeRepresentant;
				 partieInteressee.documents = $scope.listeDocument;
				 $http
				 .post(
						 baseUrl
						 + "/partieInteressee/modifierPartieInteressee",
						 partieInteressee)
						 .success(
								 function(
										 partieInteresseeModifiee) {
									 $log.debug("success modification");
									 for (var i = 0; i < $scope.listePartieInteressee.length; i++) {
										 if ($scope.myData[i].id == partieInteresseeModifiee.id) {
											 $scope.myData[i] = partieInteresseeModifiee;
											 break;
										 }
									 }
									 // TODO getId
									 $scope.annulerAjout();
								 });
				
			 }
			 
			 // Création PI
			 $scope.creerPartieInteressee = function(
					 partieInteressee) {
				 partieInteressee.representants = $scope.listeRepresentant;
				 partieInteressee.documents = $scope.listeDocument;
				 $http
				 .post(
						 baseUrl
						 + "/partieInteressee/creerPartieInteressee",
						 partieInteressee)
						 .success(
								 function(newPartieInteressee) {
									 $log.debug("success creation");
									 // TODO getId
									 $scope.annulerAjout();
								 });
				 }

			 // Annulation de l'ajout
			 $scope.annulerAjout = function() {
				 $scope.recherchePartieIntereseeFormCritere
				 .$setPristine();
				 $scope.partieInteresseeCourante = {
													"vReference": "",
													"vRaisonSociale": "",
													"vFamillePartieInteressee": "",
													"vCategoriePartieInteressee": "",
													"vTypePartieInteressee": "",
													"actif": ""};
				 $scope.rechercherPartieInteressee({});
				 $scope.listeRepresentant = [];
				 $scope.listeDocument = [];
				 $scope.displayMode = "list";
			 }
			 // Suppression PI
			 $scope.supprimerPartieInteressee = function() {
				 $log.debug("DELETE .."
						 + $scope.myData[$scope.index].id);
				 $http(
						 {
							 method : "DELETE",
							 url : baseUrl
							 + "/partieInteressee/supprimerPartieInteressee:"
							 + $scope.myData[$scope.index].id
						 }).success(function() {
							 $scope.myData.splice($scope.index, 1);
						 });
				 $scope.closePopupDelete();
				 $scope.rechercherPartieInteressee({});
			 }

			 /** Fin de gestion de la partie Interessée */

			 /***************************************************
			  * Gestion des representants
			  **************************************************/
			 // ajout d'un Representant
			 $scope.ajoutRepresentant = function() {
				 $scope.representantInserree = {
						 nom : '',
						 fonction : null,
						 email : null
				 };
				 $scope.listeRepresentant
				 .push($scope.representantInserree);

			 };

			 // Enregistrer Representant
			 $scope.saveRepresentant = function(data, id) {
				 $scope.deleteValue = "non";
				 angular.extend(data, {
						 id : id
					 });
				 
			 };

			 // Supprimer representant
			 $scope.removeRepresentant = function(index) {
				 $scope.listeRepresentant.splice(index, 1);
			 };
			 /** Fin de gestion des representants */
			 /************gestion com*****************/
			 $scope.totalServerItems = 0;

			 $scope.colDefs = [];
			 $scope.$watch(
					 'myData',
					 function() {
						 $scope.colDefs = [

						                   {
						                	   field : 'reference',
						                	   displayName : 'Réf.PI',
						                	   width:'10%'
						                   },
						                   {
						                	   field : 'raisonSociale',
						                	   displayName : 'Raison Sociale',
						                	   width:'30%'
						                   },
						                   {
						                	   field : 'famillePartieInteresseeDesignation',
						                	   displayName : 'Famille PI',
						                	   width:'10%'
						                   },
						                   {
						                	   field : 'categoriePartieInteresseeDesignation',
						                	   displayName : 'Catégorie PI',
						                	   width:'15%'
						                   },
						                   {
						                	   field : 'typePartieInteresseeDesignation',
						                	   displayName : 'Type PI',
						                	   width:'15%'
						                   },
						                   {
						                	   field : 'telephone',
						                	   displayName : 'Téléphone',
						                	   width:'13%'
						                   },
						                   {
						                	   field : '',
						                	   width:'7%',
						                	   cellTemplate : '<div class="buttons" ng-show="!rowform.$visible">'
						                		   + '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPartieInteressee()">	<i class="fa fa-fw fa-pencil"></i></button>'
						                		   + '<button data-nodrag class="btn btn-default btn-xs"	ng-click="showPopupDelete(1)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
						                   } ];
					 });
			 $scope.filterOptions = {
					 filterText : "",
					 useExternalFilter : true
			 };

			 $scope.totalServerItems = 0;

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
							 var partieInteresseeCourante = $scope.partieInteresseeCourante;
							 if (searchText) {
								 var ft = searchText
								 .toLowerCase();
								 $http
								 .post(
										 baseUrl
										 + "/partieInteressee/recherchePIMulticritere",
										 partieInteresseeCourante)
										 .success(
												 function(
														 largeLoad) {
													 data = largeLoad.partieInteresseValues
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
								 .post(
										 baseUrl
										 + "/partieInteressee/recherchePIMulticritere",
										 partieInteresseeCourante)
										 .success(
												 function(
														 largeLoad) {
													 $scope
													 .setPagingData(
															 largeLoad.partieInteresseValues,
															 page,
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
				 var selected = $filter('filterListProduit')(
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
					 $log.debug('success : ' + success);
				 }, function(error) {
					 $log.debug('error : ' + error);
				 });
			 };

			 /*** PDF ***/
      		//generer rapport de tous les bons de livraison. mode : List

			 $scope.downloadAllPartieInteressees = function(partieInteresseeCourante) {
			 	
				var url;
						
				$log.debug("-- partieInteresseeCourante " + JSON.stringify(partieInteresseeCourante, null, "  ") );
       				if(partieInteresseeCourante.vFamillePartieInteressee	== null){
						partieInteresseeCourante.vFamillePartieInteressee = "";
					}
					if(partieInteresseeCourante.vCategoriePartieInteressee	== null){
						partieInteresseeCourante.vCategoriePartieInteressee = "";
					}
					if(partieInteresseeCourante.vTypePartieInteressee	== null){
						partieInteresseeCourante.vTypePartieInteressee = "";
					}	
					url = baseUrl + "/reportCommun/listPartieInteressee?reference=" + partieInteresseeCourante.vReference
					 					 + "&raisonSociale=" + partieInteresseeCourante.vRaisonSociale
					 					 /*+ "&famille="+produitCourant.famille*/
										 + "&famillePI="+partieInteresseeCourante.vFamillePartieInteressee
										 + "&categoriePI="+partieInteresseeCourante.vCategoriePartieInteressee
										 + "&typePI="+partieInteresseeCourante.vTypePartieInteressee
										 + "&actif="+partieInteresseeCourante.actif
										 + "&type=pdf";
					
                   $log.debug("-- URL--- :" + url );
				 downloadService.download(url).then(
						 function(success) {
							// $log.debug('success : ' + success);
						 }, function(error) {
							// $log.debug('error : ' + error);
						 });
			 };


			 /***************************************************
			  * Gestion de Cache des Referentiels *
			  **************************************************/

			 // Liste des CategorieCache
			 $scope.listCategoriePICache = function() {
				 $http
				 .get(
						 baseUrl
						 + "/gestionnaireCache/listeCategoriePICache")
						 .success(
								 function(dataCategorieCache) {
									 $log.debug("listCategoriePICache : "+dataCategorieCache.length);
									 $scope.ListCategoriePICache = dataCategorieCache;

								 });
			 }
			 // Liste des FamilleCache
			 $scope.listFamillePICache = function() {
				 $http
				 .get(
						 baseUrl
						 + "/gestionnaireCache/listeFamillePICache")
						 .success(
								 function(dataFamilleCache) {
									 $log.debug("listFamillePICache : "+dataFamilleCache.length);
									 $scope.ListFamillePICache = dataFamilleCache;
								 });
			 }
			 // Liste des TypeCache
			 $scope.listTypePICache = function() {
				 $http
				 .get(
						 baseUrl
						 + "/gestionnaireCache/listeTypePICache")
						 .success(
								 function(dataTypeCache) {
									 $log.debug("listTypePICache : "+dataTypeCache.length);
									 $scope.ListTypePICache = dataTypeCache;

								 });
			 }
			 $scope.listSitePICache = function() {
				 $http
				 .get(
						 baseUrl
						 + "/gestionnaireCache/listeSitePartieInteresseeCache")
						 .success(
								 function(dataSiteCache) {
									 $log.debug("listSitePICache : "+dataSiteCache.length);
									 $scope.ListSitePICache = dataSiteCache;

								 });
			 }
			 $scope.listCategoriePICache();
			 $scope.listFamillePICache();
			 $scope.listTypePICache();
			 $scope.listSitePICache();
		 } ])
		 .filter('filterListProduit', function() {
			 return function(liste, id) {
				 var listeFiltre = [];
				 angular.forEach(liste, function(elementListe, key){

					 if(elementListe.id == id.id){
						 listeFiltre.push(elementListe);
					 }

				 });
				 return listeFiltre;
			 };
		 })
		 .controller(
				 'DatepickerDController',
				 [
				  '$scope',
				  function($scope) {
					  $scope.maxToDay = new Date();
//					  // date piker defit
//					  $scope.today = function() {
//					  $scope.partieInteresseeCourante.dateIntroduction = new Date();
//					  };
//					  $scope.today();
//					  $scope.clear = function() {
//					  $scope.partieInteresseeCourante.dateIntroduction = null;
//					  };
					  // Disable weekend selection
					  $scope.disabled = function(date, mode) {
						  return (mode === 'day' && (date.getDay() === 0 || date
								  .getDay() === 6));
					  };
					  $scope.toggleMin = function() {
						  $scope.minDate = $scope.minDate ? null
								  : new Date();
					  };
					  $scope.toggleMin();
					  $scope.open = function($event) {
						  $event.preventDefault();
						  $event.stopPropagation();
						  $scope.opened = true;
					  };
					  $scope.dateOptions = {
							  formatYear : 'yy',
							  startingDay : 1
					  };
					  $scope.initDate = new Date('20-08-2015');
					  $scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy',
					                     'dd.MM.yyyy', 'shortDate' ];
					  $scope.format = $scope.formats[0];

				  } ]);
