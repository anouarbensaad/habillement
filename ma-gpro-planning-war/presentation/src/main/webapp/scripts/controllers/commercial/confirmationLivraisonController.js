'use strict'
var app = angular.module('gpro.livraison', []);
app.controller(
	'confirmationLivraisonController',
	[
		'$scope',
		'$http',
		'$filter',
		'$log',
		'downloadService',
		'baseUrlGpao',
		'baseUrlGc',
		'baseUrl',
		function ($scope, $http, $filter, $log,
			downloadService,
			baseUrlGpao, baseUrlGc, baseUrl
		) {

			$scope.myDataOF = [];
			$scope.modeAdd = "Notok";
			$scope.listPhaseCache = [];
			$scope.listTypeBCCache = [];
			$scope.listCommandeVenteDrop = [];
			$scope.listeDetailsPrix = [];
			$scope.listeDocumentProduit = [];
			$scope.listePhaseProduit = [];
			$scope.listeCouleurProduit = [];
			$scope.listeTailleProduit = [];
			$scope.ordreFabricationCourant = {};

			$scope.resultatRechercheProduit = [];

			var itemTab = [];
			var matrix = [];
			var tab = [];
			//detailProduitCommande a affecté a produitCommande
			$scope.maGproDetailPrCmdGpao = [];
			$scope.listeColor = [];
			$scope.listeTaille = [];
			$scope.maGprogetMatrixEditGpao = [];
			$scope.demoQuantiteGpao = 0;
			$scope.maGprotapedValueGpao = [];


			$scope.listQuantiteProduitCommande = [];
			$scope.listQuantiteProduitCommande.quantiteOF = {};
			$scope.listPhaseProduit = [];
			$scope.listQuantiteOF = [];
			$scope.listPhaseOF = [];
			$scope.listDetailsOF = [];
			$scope.ListClientOFCache = [];
			$scope.ListDeviseCache = [];
			$scope.ListSousFamilleProduitCache = [];
			$scope.phaseIdRemove = [];
			$scope.item = 0;
			$scope.listQuantite = [{ id: '', value: '' }];
			$scope.displayMode = "list";

			$scope.numeroExistError = false;
			$scope.alertMsg = '';



			/***************************************************
			 * Notifications
			 **************************************************/

			$scope.hiddenNotif = "true";


			$scope.showNotif = function () {
				$scope.hiddenNotif = "false";
			}

			$scope.closeNotif = function () {
				$scope.hiddenNotif = "true";
			}


			// Liste des Etats des OF
			$scope.listeEtatOrdreFabricationCache = function () {
				$http
					.get(baseUrlGpao + "/statut/all")
					.success(
						function (dataStatutCache) {
							$scope.listeEtatOrdreFabrication = dataStatutCache;

						});
			}
			$scope.listeEtatOrdreFabricationCache();

			// Liste des ChaineOFCache
			$scope.listeChaineOrdreFabricationCache = function () {
				$http
					.get(baseUrlGpao + "/chaine/all")
					.success(
						function (dataChaineCache) {
							$scope.listeChaineOrdreFabrication = dataChaineCache;

						});
			}
			$scope.listeChaineOrdreFabricationCache();

			// Liste des Methodes OF
			$scope.listMethode = [{
				id: '1',
				designation: "methode1"
			}, {
				id: '2',
				designation: "methode2"
			}];

			// Liste des DeviseCache
			$scope.ListeDeviseCache = function () {
				$http
					.get(baseUrl + "/gestionnaireCache/listeDeviseCache")
					.success(
						function (dataDeviseCache) {
							$scope.ListDeviseCache = dataDeviseCache;

						});
			}
			$scope.ListeDeviseCache();

			// Liste des SousFamilleCache
			$scope.ListSousFamillesProduitCache = function () {
				$http
					.get(baseUrl + "/gestionnaireCache/listeSousFamilleProduitCache")
					.success(
						function (
							dataSousFamilleProduitCache) {

							$log.debug("*LISTESousFamilleProduitCache :"
								+ dataSousFamilleProduitCache.length);
							$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

						});
			}
			$scope.ListSousFamillesProduitCache();
			// GetId.designation Devise
			$scope.devise = {

				status: ''
			};
			$scope.showDeviseDesigation = function (id) {
				$scope.devise.status = id;
				var selected = $filter('filter')(
					$scope.ListDeviseCache, {
						id: $scope.devise.status
					});
				if ($scope.devise.status && selected.length) {
					return selected[0].designation;
				} else {
					return "Not Set";
				}
			};

			// Liste des SiteeCache
			$scope.listeSitesPartieInteresseeCache = function () {
				$http
					.get(
						baseUrl
						+ "/gestionnaireCache/listeSitePartieInteresseeCache")
					.success(
						function (dataSiteCache) {
							$log.debug("*LISTESiteCache :" + dataSiteCache.length);
							$scope.listeSitePartieInteresseeCache = dataSiteCache;

						});
			}

			$scope.listeSitesPartieInteresseeCache();

			// Liste des ClientCommandeVenteCache
			$scope.listeClientOrdreFabricationCache = function () {
				$http
					.get(
						baseUrlGpao + "/gestionProduitAOCache/listeClientCache")
					.success(
						function (dataClientCache) {
							$scope.ListClientOFCache = dataClientCache;
						});
			}
			$scope.listeClientOrdreFabricationCache();

			/** **** DROP Invocation des Services ****** */
			// Liste des Bons de Commande Vente
			//							$scope.listeBonCommandeVente = function() {
			//								$http
			//										.get(
			//												baseUrlGc+"/commandeVente/all")
			//										.success(
			//												function(dataCommandeVenteDrop) {
			//													$scope.listCommandeVenteDrop = dataCommandeVenteDrop;
			//												});
			//							}
			//							$scope.listeBonCommandeVente();


			// Ajout et Modification Produit
			$scope.remplirOrdreFabrication = function (id) {
				$scope.maGprotapedValueGpao = [];
				$scope.maGproDetailPrCmdGpao = [];
				$scope.maGprogetMatrixEditGpao = [];
				$scope.listeColor = [];
				$scope.listeTaille = [];

				// getProduit
				if (id != null) {
					////$log.debug("Remplir Produit id :" + id);
					$http
						.get(
							baseUrl + "/produit/getId:" + id)
						.success(
							function (datagetProduit) {

								//Produit attributs
								//$scope.showProduitDetails(datagetProduit.id);

								// Quantite
								//													$http.get( baseUrlGpao  + "/ordreFabrication/getQteProduitId:"+id)
								//															.success(
								//																	function(dataQuantiteProduitCommande) {
								//																		$scope.listQuantiteProduitCommande = dataQuantiteProduitCommande;
								//																		
								//																		for(var x=0; x<=$scope.listQuantiteProduitCommande.length;x++){
								//																			$scope.listIntermediaireBis = {id:'0', value:'0'};
								//																			$scope.listQuantite.push($scope.listIntermediaireBis);
								//																		}
								//																	});
								// Phase
								/*$http.get(baseUrlGpao   + "/ordreFabrication/getPhaseProduitId:"+ id)
										.success(
												function(dataPhaseProduit) {

													$scope.listPhaseProduit = dataPhaseProduit;
													////$log.debug(" *** $scope.listPhaseProduit Phase: "
																	+ $scope.listPhaseProduit[0].designation);
												
													//Liste des phaseId deja affectées 
													////$log.debug("dataPhaseProduit[0].phaseId Creation"+dataPhaseProduit[0].idPhase);
													//Remove repeated PhaseId (Mode Creation)
													$scope.phaseIdRemove.push(dataPhaseProduit[0].idPhase);
													for(var i=0; i<dataPhaseProduit.length; i++){
														if(dataPhaseProduit[0].idPhase ==dataPhaseProduit[i].idPhase){
															////$log.debug("Equal 0 0");
															
														} else{
															////$log.debug("NOT Equal 0 0");
															$scope.phaseIdRemove.push(dataPhaseProduit[i].idPhase);
															
														}
													}
								});*/

								// TailleProduit
								$scope.getListCouleurProduit(id);

								//CouleurProduit
								$scope.getListTailleProduit(id);

								// $scope.ajoutCompositionOF();

							});
				}


				//$scope.displayMode = "edit";
			}

			$scope.getListCouleurProduit = function (idProduit) {
				//var deferred = $q.defer();	
				// TODO :Liste de couleur cache
				$http.get(baseUrl + "/couleur/CouleurParProduit:" + idProduit)
					.success(
						function (data) {
							//////$log.debug("ListeCouleurs : "+data.length);
							$scope.listeColor = data;
							////$log.debug("-- listeColor : "+data);

						})
					.error(function (data, status) {
						////$log.debug('Erreur into urlCouleur '+ data);
					});


			}//End function

			$scope.getListTailleProduit = function (idProduit) {
				// TODO :Liste de taille cache
				$http.get(baseUrl + "/taille/TailleParProduit:" + idProduit)
					.success(
						function (data) {
							//////$log.debug("ListeCouleurs : "+data.length);
							$scope.listeTaille = data;
							////$log.debug(" -- listeTaille : "+data);

						}).error(function (data, status) {
							////$log.debug('Erreur into urlProduit '+ data);
						});


			}//End function


			//Affectation Quantite

			$scope.demoQuantiteGpao = function (couleurId, tailleId) {
				// ////$log.debug( "DEMO  Tab :" + JSON.stringify(tab,null, "  ") );
				var quantite = null;

				angular.forEach(tab, function (tabValue, key) {
					if (couleurId == tabValue.couleurId && tailleId == tabValue.tailleId) {
						// ////$log.debug( "CASE Demo : 1");	
						quantite = tabValue.quantite;

					}

				});
				return quantite;
			}

			// AddNewElement 
			var modeAnnuler = "NotActif";
			$scope.addNewElementGpao = function (event, myValue, indexTD, indexTR, taille, couleur) {

				//$log.debug("-- MyValue = "+myValue);
				if ($scope.modeAdd == "ok") {
					//$log.debug(" actif beforeEmplty: itemTab : "+JSON.stringify(itemTab,null,"  ")) ;
					itemTab = [];
					//$log.debug(" =========>Empty: itemTab : "+JSON.stringify(itemTab,null,"  ")) ;
					$scope.modeAdd = "Notok";
				}
				//$log.debug(" beforeAdd : itemTab : "+JSON.stringify(itemTab,null,"  ")) ;
				var elementObjet = {
					tailleId: taille.id,
					couleurId: couleur.id,
					quantite: myValue.replace(/\s+/g, '')
				};

				if ((myValue === "") && (itemTab.length !== 0)) {
					//$log.debug( "CASE ADD : 1 =>> delete Item");
					for (var i = 0; i < itemTab.length; i++) {
						if ((elementObjet.tailleId === itemTab[i].tailleId) && (elementObjet.couleurId === itemTab[i].couleurId)) {
							itemTab.splice(i, 1); //delete Item
						}//end if exist 
					}//end for
				} else {
					//$log.debug( "CASE ADD : 2");
					//$log.debug("ELSE 1");
					if ((itemTab.length === 0) && (myValue !== '')) {
						//$log.debug( "CASE ADD : 2 -A =>> new Item");
						//$log.debug("A "+JSON.stringify(elementObjet,null,"  ")) ;
						itemTab.push(elementObjet); // new Item
					}
					else {
						//$log.debug( "CASE ADD : 2 -B =>> Update Or Create"); //Update item ; x,y => new qte 
						//$log.debug("B T "+JSON.stringify(elementObjet.tailleId,null,"  ")) ;
						//$log.debug("B C "+JSON.stringify(elementObjet.couleurId,null,"  ")) ;
						var exist = false;
						for (var i = 0; i < itemTab.length; i++) {
							if ((elementObjet.tailleId === itemTab[i].tailleId) && (elementObjet.couleurId === itemTab[i].couleurId)) {
								//$log.debug( "CASE ADD : 2 -B -1 =>> Update");
								itemTab[i].quantite = myValue.replace(/\s+/g, '');
								//$log.debug("D "+JSON.stringify(itemTab[i],null,"  ")) ;
								exist = true;
							}//end if exist 
						}//end for
						if (exist === false) {
							////$log.debug( "CASE ADD : 2 -C =>> Create"); // Create Item
							itemTab.push(elementObjet);
						}
					}//end else 						
				}

				//$log.debug(" itemTab : " + JSON.stringify(itemTab, null ,"  ") );
				//$log.debug(" ngKeyUp MATRIX : " + JSON.stringify(matrix, null ,"  ") );
				matrix = itemTab;
				//$log.debug(" ngKeyUp MATRIX: " + JSON.stringify(matrix, null ,"  ") );
				//$log.debug(" ----------------------------- " );
			}

			//$log.debug(" ======= after : itemTab : "+JSON.stringify(itemTab,null,"  ")) ;

			// $scope.listeProduit = function() {
			// $http
			// .get(
			// baseUrl+"/produit/all")
			// .success(
			// function(dataProduit) {
			// $scope.listProduitDrop = dataProduit;
			// console.log("ofCourant update "
			// + JSON.stringify($scope.listProduitDrop,
			// null, " "));
			// });
			// }
			//$scope.listeProduit();
			/**********************************************changer cadence de produits **********************************************/

			$scope.change = function () {
				console.log("change chaine " + JSON.stringify($scope.ordreFabricationCourant,
					null, " "));
				$http
					.get(
						baseUrl + "/produit/getId:" + $scope.ordreFabricationCourant.produitId)
					.success(
						function (datagetProduit) {
							//console.log("change chaine " + JSON.stringify(datagetProduit,
							//	null, " "));
							$scope.ordreFabricationCourant.cadence = datagetProduit.cadence;
							$scope.ordreFabricationCourant.special = datagetProduit.special;
							$scope.ordreFabricationCourant.partieInterresId = datagetProduit.partieIntersseId;
						});
			}


			/************************************************************************************************************************/
			$scope.listPhase = function () {
				$http
					.get(
						baseUrl + "/gestionnaireCache/listePhaseProduitCache")
					.success(
						function (dataPhase) {
							$scope.listPhaseCache = dataPhase;
							$log.debug(" * $scope.listPhaseCache : "
								+ $scope.listPhaseCache.length);

						});
			}
			$scope.listPhase();


			//							$scope.listTypeBC = function() {
			//								$http
			//										.get(
			//												baseUrlGc+"/gestionCommercialCache/listeTypeCommandeVenteCache")
			//										.success(
			//												function(dataTypeBC) {
			//													$scope.listTypeBCCache = dataTypeBC;
			//													$log.debug(" * $scope.listTypeBCCache : "
			//																	+ $scope.listTypeBCCache.length);
			//												});
			//							}
			//							$scope.listTypeBC();

			/*****************************
			 * Conversion ID / Designation
			 ****************************/
			// Produit 
			$scope.produitId = {
				status: ''
			};
			//SousFamilleProduit
			$scope.sousFamilleProduitId = {
				status: ''
			};
			//PartieInteressee
			$scope.partieInteresseeId = {
				status: ''
			};
			// showProduitDetails
			$scope.showProduitDetails = function (produitId) {

				// showProduitDetails
				$scope.produitId.status = produitId;
				var selected = $filter('showProduitFilterGpao')(
					$scope.listProduitDrop, {
						id: $scope.produitId.status
					});
				if ($scope.produitId.status && selected.length) {
					//produitId
					$scope.resultatRechercheProduit.produitId = produitId;
					//ClientId
					$scope.resultatRechercheProduit.partieInterresId = selected[0].partieIntersseId;
					////$log.debug("partieIntersseId "+selected[0].partieIntersseId);

					//conversion de partieIntersseId par son abreviation
					$scope.partieInteresseeId.status = selected[0].partieIntersseId;
					var selected3 = $filter('filterSousFamilleGpao')(
						$scope.ListClientOFCache, {
							id: $scope.partieInteresseeId.status
						});
					if ($scope.partieInteresseeId.status && selected3.length) {
						//TODO : partieInteresseeId id => Abreviation
						//$log.info("**----Produit sousFamilleDesignation : "+ selected2[0].designation);
						$scope.resultatRechercheProduit.clientAbreviation = selected3[0].abreviation;

					}

					//referenceProduit 
					//$log.info("**Produit Reference "+selected[0].reference);
					$scope.resultatRechercheProduit.reference = selected[0].reference;
					//designationProduit
					//$log.info("**Produit Designation "+selected[0].designation);
					$scope.resultatRechercheProduit.designation = selected[0].designation;

					//conversion de sousFamilleId par son designation
					$scope.sousFamilleProduitId.status = selected[0].sousFamilleId;
					var selected2 = $filter('filterSousFamilleGpao')(
						$scope.ListSousFamilleProduitCache, {
							id: $scope.sousFamilleProduitId.status
						});
					if ($scope.sousFamilleProduitId.status && selected2.length) {
						//TODO : sousFamilleProduit id => Designation
						//$log.info("**----Produit sousFamilleDesignation : "+ selected2[0].designation);
						$scope.resultatRechercheProduit.sousFamilleDesignation = selected2[0].designation;

					} else {
						$scope.resultatRechercheProduit.sousFamilleDesignation = "--";
					}
				}
			}

			/*************************
			 *    Ordre de Fabrication
			 *************************/

			$scope.myDataOF = [];
			$scope.listQuantiteOF = [];
			$scope.listePhaseOF = [];
			$scope.creation = true;

			$scope.displayMode = "list";

			/* cancelAddCompositionOF */
			$scope.cancelAddOrdreFabrication = function (
				rowform, index, id, designation, liste) {
				if (angular.isDefined(id)) {

					////$log.debug("DEF ID");
					$scope.deleteValue = "non";
					rowform.$cancel();
					////$log.debug("CANCEL");
				} else {
					////$log.debug("UND ID  " + id);
					if (designation == "") {
						$scope.deleteValue == "oui"
						////$log.debug("Designation : "		+ designation);
						////$log.debug("$scope.deleteValueOUI : "+ $scope.deleteValue);
						liste.splice(index, 1);
						rowform.$cancel();
						////$log.debug("DELETE")
					} else {
						////$log.debug("Designation :"	+ designation);
						////$log.debug("$scope.deleteValueNON : "+ $scope.deleteValue);
						rowform.$cancel();
						////$log.debug("CANCEL");
					}
				}
				$scope.deleteValue = "oui";
			}


			// ** Ajout Ordre de Fabrication
			$scope.AffectationOF = function (commande) {
				$scope.modeAdd = "ok";
				$scope.ordreFabricationCourant = {};
				$scope.ordreFabricationCourant = commande ? angular
					.copy(commande)
					: {};

				$scope.displayMode = "edit";
			}
			$scope.phaseOff = [];
			// Liste des Ordres de Fabrication


			$scope.pagingOptions = {
				pageSizes: [5, 10, 13],
				pageSize: 13,
				currentPage: 1
			};

			// Rechercher Ordre Fabrication
			$scope.rechercherOrdreFabrication = function (
				ordreFabricationCourant) {
					
							    	var dateIntroDu = ordreFabricationCourant.vDateIntroductionDu	;
									var dateIntroAu = ordreFabricationCourant.vDateIntroductionAu ;
									
									var dateLivDu = ordreFabricationCourant.dateLivraisonDu ;
									var dateLivAu = ordreFabricationCourant.dateLivraisonTo ;
										
										
													//dateIntroMin
								var newdateIntroOFMinFormat="";
							   if(angular.isDefined(ordreFabricationCourant.vDateIntroductionDu)){
								   
								   if(ordreFabricationCourant.vDateIntroductionDu != ""){
									   newdateIntroOFMinFormat = formattedDate(ordreFabricationCourant.vDateIntroductionDu);
									   
									   
								   }else{
									   newdateIntroOFMinFormat = "";
								   }
							   }

								//dateIntroMax
								var newdateIntroOFMaxFormat="";
							   if(angular.isDefined(ordreFabricationCourant.vDateIntroductionAu)){
								   
								   if(ordreFabricationCourant.vDateIntroductionAu != ""){
									   newdateIntroOFMaxFormat = formattedDate(ordreFabricationCourant.vDateIntroductionAu);
								   }else{
									   newdateIntroOFMaxFormat = "";
								   }
							   }

								//dateLiv min
								var newdateLivOFMinFormat="";
							   if(angular.isDefined(ordreFabricationCourant.dateLivraisonDu)){
								   
								   if(ordreFabricationCourant.dateLivraisonDu != ""){
									   newdateLivOFMinFormat = formattedDate(ordreFabricationCourant.dateLivraisonDu);
								   }else{
									   newdateLivOFMinFormat = "";
								   }
							   }

							   //dateLiv max
								var newdateLivOFMaxFormat="";
							   if(angular.isDefined(ordreFabricationCourant.dateLivraisonTo)){
								   
								   if(ordreFabricationCourant.dateLivraisonTo != ""){
									   newdateLivOFMaxFormat = formattedDate(ordreFabricationCourant.dateLivraisonTo);
								   }else{
									   newdateLivOFMaxFormat = "";
								   }
							   }

							        ordreFabricationCourant.vDateIntroductionAu = newdateIntroOFMaxFormat;
									ordreFabricationCourant.vDateIntroductionDu	= newdateIntroOFMinFormat;
									ordreFabricationCourant.vdateLivraisonDu = newdateLivOFMinFormat;
									ordreFabricationCourant.vdateLivraisonTo = newdateLivOFMaxFormat;
					
					
					
					
				//console.log("Recherche : "+JSON.stringify($scope.ordreFabricationCourant,null, "  "));
				ordreFabricationCourant.planning = true;
				$http
					.post(
						baseUrlGpao
						+ "/ordreFabrication/rechercheOrdreFabricationMulticritere",
						ordreFabricationCourant)
					.success(
						function (resultat) {
							$scope.myDataOF = resultat.ordreFabricationValues;
							for (var i = 0; i < $scope.myDataOF.length; i++) {
								if ($scope.myDataOF[i].dateLivraison != null) {
									$scope.myDataOF[i].dateLivraison = formattedDate($scope.myDataOF[i].dateLivraison)

								}
							}
							////$log.debug('rslts recherche OrdreFabrication  ..'+$scope.myDataOF.length);
							// Pagination du resultat de
							// recherche
							// data, page,pageSize
							$scope
								.setPagingData(
									$scope.myDataOF,
									$scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize);

							$scope.displayMode = "rechercher";
							$scope.creationOFForm.$setPristine();
							$scope.rechercheOFForm.$setPristine();
							$scope.displayAlert = "sleep";
						});
						
										
							        ordreFabricationCourant.vDateIntroductionAu = dateIntroAu;
									ordreFabricationCourant.vDateIntroductionDu	= dateIntroDu;
									ordreFabricationCourant.dateLivraisonDu = dateLivDu;
									ordreFabricationCourant.dateLivraisonTo = dateLivAu;	
						
						
			}
			// Ajout et Modification OF
			$scope.modifierOuCreerOrdreFabrication = function () {
				$scope.creation = false;
				var index = this.row.rowIndex;
				// getOrdreFabrication
				$http
					.get(
						baseUrlGpao
						+ "/ordreFabrication/getId:"
						+ $scope.myDataOF[index].id)
					.success(
						function (datagetOF) {

							var idProduit = datagetOF.produitId;

							$scope.ordreFabricationCourant.produitId = datagetOF.produitId;
							$scope.ordreFabricationCourant.clientId = datagetOF.partieInterresId;
							$scope.ordreFabricationCourant.cadence = datagetOF.cadence;
							//$scope.ordreFabricationCourant.idSite = datagetOF.idSite ;	
							$scope.ordreFabricationCourant.idSite = $scope.myDataOF[index].idSite;
							$scope.ordreFabricationCourant.special = $scope.myDataOF[index].special;
							$scope.ordreFabricationCourant.cadence = $scope.myDataOF[index].cadence;
							$scope.ordreFabricationCourant.numero = $scope.myDataOF[index].numero;
							$scope.ordreFabricationCourant.type = $scope.myDataOF[index].type;
							$scope.ordreFabricationCourant.quantite = $scope.myDataOF[index].quantite;
							$scope.ordreFabricationCourant.id = $scope.myDataOF[index].id;
							$scope.ordreFabricationCourant.dateLivraison = $scope.myDataOF[index].dateLivraison;


						});

				// $scope.ordreFabricationCourant = $scope.myDataOF[index] ? angular
				// 		.copy($scope.myDataOF[index])
				// 		: {};


				$scope.currentNumOF = $scope.ordreFabricationCourant.numero;
				$scope.displayMode = "edit";
			}

			$scope.filterPhase = function () {

				return function (item) {
					var condition = false;

					for (var k = 0; k < $scope.phaseIdRemove.length; k++) {
						if (item.id != $scope.phaseIdRemove[k]) {
							condition = true
						} else {
							condition = false;
							break;
						}
					}

					if (condition == true) {
						return true;
					}
					return false;
				};
			};

			$scope.cmp = 0;
			//TODO Methode A Supprimer
			$scope.refresh = function (id, index) {

				if ($scope.cmp < $scope.phaseIdRemove.length) {
					$scope.phaseIdRemove[$scope.phaseIdRemove.length + 1] = id
				}
			}

			// Sauvegarder CommandeVente
			$scope.sauvegarderAjout = function (ordreF) {

				if (angular.isDefined(ordreF.id)) {
					////$log.debug("Sauvegarder Modification"	+ ordreF);
					$scope.updateOrdreFabrication(ordreF);
				} else {
					$log.debug("Sauvegarder Ajout" + ordreF);
					$scope.creerOrdreFabrication(ordreF);
				}
			}

			// Annulation de l'ajout
			$scope.annulerAjout = function () {

				/** CLose notif and init validation variables**/

				$scope.closeNotif();
				$scope.initNumeroErrorValue();

				$scope.creation = true;
				//$scope.somme = 0;
				$scope.modeAdd = "Notok";
				$scope.resultatRechercheProduit = [];
				$scope.maGprotapedValueGpao = [];
				$scope.listeColor = [];
				$scope.listeTaille = [];
				$scope.listDetailsOF = [];

				$scope.id = {};

				$scope.phaseIdRemove = [];
				$scope.ordreFabricationCourant = {};
				//								$scope.ordreFabricationCourant = {
				//																	"vNumero": "",
				//																	"vCompositionBC": "",
				//																	"clientId": "",
				//																	"produitId": "",
				//																	"vDateIntroductionDu": "",
				//																	"vDateIntroductionAu": "",
				//																	"dateLivraisonDu": "",
				//																	"vDateIntroductionTo": "",
				//																	"vEtat": ""
				//
				//																};
				$scope.listCompositionOF = [];
				$scope.listQuantiteProduitCommande = [];
				$scope.listPhaseOF = [];
				$scope.listPhaseProduit = [];
				$scope.maGprogetMatrixEditGpao = [];
				$scope.maGproDetailPrCmdGpao = [];
				//$scope.rechercherOrdreFabrication({});
				$scope.creationOFForm.$setPristine();
				$scope.rechercheOFForm.$setPristine();
				$scope.displayMode = "list";
			}

			// Mise à jour des ordres de Fabrications
			$scope.updateOrdreFabrication = function (ordreF) {

				ordreF.numOFBeforeUpdate = $scope.currentNumOF;


				console.log("OF" + JSON.stringify(ordreF, null, " "));
				ordreF.partieInterresId = ordreF.clientId;
				$http
					.post(
						baseUrlGpao
						+ "/ordreFabrication/modifierOrdreFabrication",
						ordreF)
					.success(
						function (modifiedId) {

							for (var i = 0; i < $scope.myDataOF.length; i++) {

								if ($scope.myDataOF[i].id == modifiedId) {
									$scope.myDataOF[i] = ordreF;
									break;
								}
							}


							$scope
								.setPagingData(
									$scope.myDataOF,
									$scope.pagingOptions.currentPage,
									$scope.pagingOptions.pageSize);


							$scope.annulerAjout();

						});

			}

			// Création d'un Ordre de Fabrication
			$scope.creerOrdreFabrication = function (ordreF) {
				ordreF.a_planifier = "true";
				ordreF.quantitePlanifie = 0;
				console.log("of avant zaineb >>>>> :" + JSON.stringify(ordreF, null, "  "));

				//ordreF.produitId = $scope.resultatRechercheProduit.produitId;
				//ordreF.partieInterresId = $scope.resultatRechercheProduit.partieInterresId;


				//ordreF.partieInterresId = ordreF.clientId ;
				//console.log( "of apres zaineb >>>>> :" + JSON.stringify(ordreF,null, "  ") );
				$http
					.post(
						baseUrlGpao
						+ "/ordreFabrication/creerOrdreFabrication",
						ordreF)
					.success(
						function (result) {

							$http
								.get(
									baseUrlGpao
									+ "/ordreFabrication/getId:"
									+ result)
								.success(
									function (OF) {
										$scope.myDataOF.unshift(OF);
										$scope.annulerAjout();
										matrix = [];
									})


						});

			}

			$scope.supprimerOrdreFabrication = function () {
				//var index = this.row.rowIndex;
				////$log.debug("INDEX" + index);
				////$log.debug("**OBJET :"+ $scope.myDataOF[index]);
				////$log.debug("DELETE .."+ $scope.myDataOF[index].id);
				$http(
					{
						method: "DELETE",
						url: baseUrlGpao
							+ "/ordreFabrication/supprimerOrdreFabrication:"
							+ $scope.myDataOF[$scope.index].id
					}).success(function () {
						////$log.debug("Success Delete ");
						$scope.myDataOF.splice($scope.index, 1);

					});

				$scope.rechercherOrdreFabrication({});
				$scope.closePopupDelete();

			};

			// Appel des fonctions
			//$scope.listeOrdreFabrication();

			/*** PDF ***/
			//generer rapport de tous les Ordre de Fabrication. mode : List
			//conversion date en String
			function formattedDate(date) {
				var d = new Date(date),
					month = '' + (d.getMonth() + 1),
					day = '' + d.getDate(),
					year = d.getFullYear();

				if (month.length < 2) month = '0' + month;
				if (day.length < 2) day = '0' + day;
				return [year, month, day].join('-');
			}

			$scope.downloadAllOrdreFabrication = function (ordreFabricationCourant) {

				//dateIntroMin
				var newdateIntroOFMinFormat = "";
				if (angular.isDefined(ordreFabricationCourant.vDateIntroductionDu)) {

					if (ordreFabricationCourant.vDateIntroductionDu != "") {
						newdateIntroOFMinFormat = formattedDate(ordreFabricationCourant.vDateIntroductionDu);
					} else {
						newdateIntroOFMinFormat = "";
					}
				}

				//dateIntroMax
				var newdateIntroOFMaxFormat = "";
				if (angular.isDefined(ordreFabricationCourant.vDateIntroductionAu)) {

					if (ordreFabricationCourant.vDateIntroductionAu != "") {
						newdateIntroOFMaxFormat = formattedDate(ordreFabricationCourant.vDateIntroductionAu);
					} else {
						newdateIntroOFMaxFormat = "";
					}
				}

				//dateLiv min
				var newdateLivOFMinFormat = "";
				if (angular.isDefined(ordreFabricationCourant.dateLivraisonDu)) {

					if (ordreFabricationCourant.dateLivraisonDu != "") {
						newdateLivOFMinFormat = formattedDate(ordreFabricationCourant.dateLivraisonDu);
					} else {
						newdateLivOFMinFormat = "";
					}
				}

				//dateLiv max
				var newdateLivOFMaxFormat = "";
				if (angular.isDefined(ordreFabricationCourant.dateLivraisonTo)) {

					if (ordreFabricationCourant.dateLivraisonTo != "") {
						newdateLivOFMaxFormat = formattedDate(ordreFabricationCourant.dateLivraisonTo);
					} else {
						newdateLivOFMaxFormat = "";
					}
				}

				var url;

				$log.debug("-- OFCourant " + JSON.stringify(ordreFabricationCourant, null, "  ") );

    if(ordreFabricationCourant.solder == undefined)
      	ordreFabricationCourant.solder = '';
				url = baseUrlGpao + "/fiches/commandeSuivie?clientId=" + ordreFabricationCourant.clientId
					+ "&produitId=" + ordreFabricationCourant.vEtat
					+ "&DateIntroductionDe=" + newdateIntroOFMinFormat
					+ "&DateIntroductionA=" + newdateIntroOFMaxFormat
					+ "&dateLivraisonDu=" + newdateLivOFMinFormat
					+ "&dateLivraisonTo=" + newdateLivOFMaxFormat				
					+ "&numOf=" + ordreFabricationCourant.vNumero
					+ "&refProduit=" + ordreFabricationCourant.referenceProduit
					+ "&desProduit=" + ordreFabricationCourant.designationProduit
					+ "&vEtat=" + ordreFabricationCourant.vEtat
					+ "&type=" + ordreFabricationCourant.type
					+ "&etatCoupe=" + ordreFabricationCourant.etatCoupe
					+ "&etatFabrication=" + ordreFabricationCourant.etatFabrication
					+ "&etatConditionnement=" + ordreFabricationCourant.etatConditionnement
					+ "&etatCollisage=" + ordreFabricationCourant.etatCollisage
					+ "&etatExpedition=" + ordreFabricationCourant.etatExpedition 
					+ "&etatEngagement=" + ordreFabricationCourant.etatEngagement ;
			



				////$log.debug("-- URL--- :" + url );
				downloadService.download(url).then(
					function (success) {
						//$log.debug('success : ' + success);
					}, function (error) {
						//$log.debug('error : ' + error);
					});
			};

			/**  */
			$scope.confirmationDateLivraison = function () {

				$http
					.post(
						baseUrlGpao
						+ "/ordreFabrication/modifierTout",
						$scope.myDataOF)
					.success(
						function (response) {

							console.log("success");
						});
			}
			/***************************************************
			 * Gestion des CompositionOF
			 **************************************************/
			$scope.listQuantiteProduitCommande = [];

			// Get referenceProduit

			$scope.reference = {

				status: ''
			};
			$scope.showReferenceProduit = function (id) {
				$scope.reference.status = id;
				var selected = $filter('filter')(
					$scope.listProduitDrop, {
						id: $scope.reference.status
					});
				if ($scope.reference.status && selected.length) {
					return selected[0].reference;
				} else {
					return "Not Set";
				}
			};

			// GetId.designation Methode
			$scope.methode = {

				status: ''
			};

			$scope.showMethodeDesigation = function (id) {
				$scope.methode.status = id;
				var selected = $filter('filter')(
					$scope.listMethode, {
						id: $scope.methode.status
					});
				if ($scope.methode.status && selected.length) {
					return selected[0].designation;
				} else {
					return "Not Set";
				}
			};
			// Get designationProduit

			$scope.designation = {

				status: ''
			};
			$scope.showDesignationProduit = function (id) {
				$scope.designation.status = id;
				var selected = $filter('filter')(
					$scope.listProduitDrop, {
						id: $scope.designation.status
					});
				if ($scope.designation.status
					&& selected.length) {
					return selected[0].designation;
				} else {
					return "Not Set";
				}
			};

			// Get designationProduit

			$scope.sousFamille = {

				status: ''
			};
			$scope.showSousFamilleProduit = function (id) {
				$scope.sousFamille.status = id;
				var selected = $filter('filter')(
					$scope.listProduitDrop, {
						id: $scope.sousFamille.status
					});
				if ($scope.sousFamille.status
					&& selected.length) {
					return selected[0].sousFamilleDesignation;
				} else {
					return "Not Set";
				}
			};

			// Calcul du champ Quantite lors d'une modification de la quantiteOF.
			$scope.somme = 0;

			$scope.changeQuantiteOF = function (index, item, partie) {



				$scope.listQuantiteProduitCommande[index].quantiteOF = item;
				////$log.debug(" Change quantiteOF : "		+ $scope.listQuantiteProduitCommande[index].quantiteOF
				//+ " index " + index);

				$scope.quantiteChanged = $scope.listQuantiteProduitCommande[index].quantiteOF;
				$scope.item = index;

				////$log.debug($scope.quantiteChanged + " ** "+$scope.item);


				$scope.listIntermediaire = { id: '', value: '' };

				$scope.listIntermediaire.id = $scope.item;
				$scope.listIntermediaire.value = $scope.quantiteChanged;
				$scope.listQuantite[$scope.item].value = $scope.quantiteChanged;



			};

			/************* Init validation error values ************/

			$scope.initNumeroErrorValue = function () {
				$scope.numeroExistError = false;
			}



			// ajout d'une CompositionOF
			$scope.ajoutCompositionOF = function () {

				$scope.compositionOFInserree = {
					numeroBC: '',
					quantiteBC: '',
					quantite: ''

				};
				$scope.listQuantiteProduitCommande
					.push($scope.compositionOFInserree);

				$log.debug("Ajout quantiteOF "
					+ $scope.compositionOFInserree[0].quantite);
			};

			// Enregistrer CompositionOF
			$scope.saveCompositionOF = function (compositionOF,
				quantite, id, index) {
				$scope.deleteValue = "non";
				// $scope.listQuantiteProduitCommande[index].quantiteOF
				// = compositionOF.quantite;
				angular.extend(compositionOF, {
					id: id
				});
			};

			// Supprimer CompositionOF
			$scope.removeCompositionOFQuantiteVue = function (
				index, liste) {
				liste.splice(index, 1);
			};


			/** Fin de Composition */

			/***************************************************
			 * Gestion des PhaseOF
			 **************************************************/
			$scope.listPhaseProduit = [];

			$scope.showBtnCalender = true;
			// show bottons Calender
			$scope.showBC = function () {
				$scope.showBtnCalender = true;
			}

			// GetId.designation
			$scope.phase = {

				status: ''
			};
			$scope.showPhaseDesigation = function (id) {
				$scope.phase.status = id;
				var selected = $filter('filter')(
					$scope.listPhaseCache, {
						id: $scope.phase.status
					});
				if ($scope.phase.status && selected.length) {
					return selected[0].designation;
				} else {
					return "Not Set";
				}
			};

			// Modification PhaseOF
			$scope.chaine = {
				status: ''
			};

			$scope.showChaineStatus = function (id) {
				$scope.chaine.status = id;
				var selected = $filter('filter')(
					$scope.listeChaineOrdreFabrication, {
						id: $scope.chaine.status
					});
				return ($scope.chaine.status && selected.length) ? selected[0].designation
					: 'Not set';
			};

			//Client/ Façonnier
			$scope.client = {
				status: ''
			};
			$scope.showPIStatus = function (id) {
				$scope.client.status = id;
				var selected = $filter('filter')(
					$scope.ListClientOFCache, {
						id: $scope.client.status
					});
				return ($scope.client.status && selected.length) ? selected[0].abreviation
					: 'Not set';
			};
			$scope.ajout = false;
			// Ajout PhaseOF / PhaseProduit
			$scope.ajoutPhaseOF = function (mode) {

				$scope.phaseProduitInserreeCreation = {
					idPhase: '',
					prix: '',
					devise: '',
					chaineId: '',
					clientId: '',
					facturee: false,
					methode: "",
					dateDebut: '',
					dateFin: ''
				};

				$scope.phaseProduitInserreeModification = {
					phaseId: '',
					prix: '',
					devise: '',
					chaineId: '',
					clientId: '',
					facturee: false,
					methode: "",
					dateDebut: '',
					dateFin: ''
				};


				if (mode == '1') {
					////$log.debug("Creation ");
					$scope.ajout = true;
					$scope.listPhaseProduit.push($scope.phaseProduitInserreeCreation);

				} else if (mode == '2') {
					////$log.debug("Modification ");
					$scope.ajout = true;
					$scope.listPhaseOF.push($scope.phaseProduitInserreeModification);
				}

			};

			$scope.oldPhase = 0;
			$scope.changePhaseProduit = function (phaseId, index) { //= function(index, item, partie) {
				$scope.oldPhase = phaseId;
				$scope.ajout = false;

			};

			$scope.newPhase = 0;
			$scope.updateFct = false;
			$scope.refreshPhase = function (newPhase) {
				$scope.newPhase = newPhase;
				$scope.updateFct = true;

				if ($scope.updateFct == true) {
					if ($scope.newPhase == undefined) {
						var index = $scope.phaseIdRemove.length;
						$scope.phaseIdRemove.splice(index, 1);
					} else {
						if ($scope.newPhase == null) {
							////$log.debug(" NULL ");
						} else {
							if ($scope.ajout == false) {
								/** Splice **/
								$scope.phaseIdRemove.splice(0, 1);

								/** Push **/
								$scope.phaseIdRemove.push($scope.newPhase);

							} else {

								if ($scope.phaseIdRemove.indexOf($scope.newPhase) == -1) {
									$scope.phaseIdRemove.push($scope.newPhase);


								} else {
									////$log.debug( $scope.newPhase + " Exist " );
									//$scope.ajout = false;
								}

							}

						}
					}

					$scope.updateFct = false;
				} else {
					//////$log.debug("On Passe");
				}
				$scope.updateFct = false;
			}


			// Enregistrer PhaseOF
			$scope.savePhaseOF = function (phaseOF, id) {
				$scope.deleteValue = "non";
				angular.extend(phaseOF, {
					id: id
				});
			};

			// Supprimer PhaseOF
			$scope.removePhaseOF = function (index) {
				$scope.listPhaseOF.splice(index, 1);
			};

			// Supprimer PhaseProduit 
			$scope.removePhaseProduit = function (index) {
				$scope.listPhaseProduit.splice(index, 1);
			};
			/** Fin de PhaseOF */

			/***************************************************
			 * Gestion de Grid OF
			 **************************************************/
			$scope.colDefs = [];
			$scope.$watch(
				'myDataOF',
				function () {
					$scope.colDefs = [

						/*{
							field : '',
							headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
							cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
							width:'3%'
						},*/
						{
							field: 'numero',
							displayName: 'N° OF',
							width: '10%',
							enableCellEdit: false
						},
						{
							field: 'partieInterresAbreviation',
							displayName: 'Client',
							width: '12%',
							enableCellEdit: false
						},
						{
							field: 'produitReference',
							displayName: 'Réference',
							width: '15%',
							enableCellEdit: false
						},
						{
							field: 'produitDesignation',
							displayName: 'Designation',
							width: '12%',
							enableCellEdit: false
						},
						{
							field: 'quantite',
							displayName: 'Qté',
							width: '7%',
							enableCellEdit: false
						},
						{
							field: 'qtCoupe',
							displayName: 'Coupe',
							width: '6%',
							enableCellEdit: false
						},
						{
							field: 'qtEngagement',
							displayName: 'Eng.',
							width: '6%',
							enableCellEdit: false
						},
						
						{
							field: 'qtSortie',
							displayName: 'S.FAB',
							width: '6%',

							enableCellEdit: false
						},

						{
							field: 'qtFinition',
							displayName: 'S.EMB',
							width: '6%',

							enableCellEdit: false
						},

						{
							field: 'qtColisage',
							displayName: 'Colisage',
							width: '6%',

							enableCellEdit: false
						},

						{
							field: 'qtExpedition',
							displayName: 'EXP',
							width: '6%',

							enableCellEdit: false
						},

						{
							field: 'dateLivraison',
							displayName: 'Date Liv.',
							cellFilter: 'date:\'dd-MM-yyyy\'',
							width: '9%',
							editableCellTemplate: '<input ng-class="\'colt\' + col.index" datepicker-popup is-open="true" ng-model="COL_FIELD" />',
							enableCellEdit: true
						}

					];
				});

			$scope.filterOptions = {
				filterText: "",
				useExternalFilter: true
			};

			$scope.totalServerItems = 0;

			$scope.setPagingData = function (data, page,
				pageSize) {
				var pagedData = data.slice((page - 1)
					* pageSize, page * pageSize);
				$scope.myDataOF = pagedData;
				$scope.totalServerItems = data.length;
				if (!$scope.$$phase) {
					$scope.$apply();
				}
			};

			$scope.getPagedDataAsync = function (pageSize, page,
				searchText) {
				setTimeout(
					function () {
						var data;
						var ordreFabricationCourant = $scope.ordreFabricationCourant;

						if (searchText) {
							var ft = searchText
								.toLowerCase();

							$http
								.post(
									baseUrlGpao + "/ordreFabrication/rechercheOrdreFabricationMulticritere",
									ordreFabricationCourant)
								.success(
									function (
										largeLoad) {

										data = largeLoad.ordreFabricationValues
											.filter(function (
												item) {
												return JSON
													.stringify(
														item)
													.toLowerCase()
													.indexOf(
														ft) != -1;
											});
										for (var i = 0; i < data.length; i++) {
											if (data[i].dateLivraison != null) {
												data[i].dateLivraison = formattedDate(data[i].dateLivraison)
											}
										}
										$scope
											.setPagingData(
												data,
												page,
												pageSize);
									});

						} else {
							$http
								.post(
									baseUrlGpao + "/ordreFabrication/rechercheOrdreFabricationMulticritere",
									ordreFabricationCourant)
								.success(
									function (
										largeLoad) {
										for (var i = 0; i < largeLoad.ordreFabricationValues.length; i++) {
											if (largeLoad.ordreFabricationValues[i].dateLivraison != null) {
												largeLoad.ordreFabricationValues[i].dateLivraison = formattedDate(largeLoad.ordreFabricationValues[i].dateLivraison)
											}
										}
										$scope
											.setPagingData(
												largeLoad.ordreFabricationValues,
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
					function (newVal, oldVal) {
						if (newVal !== oldVal
							&& newVal.currentPage !== oldVal.currentPage) {
							$scope
								.getPagedDataAsync(
									$scope.pagingOptions.pageSize,
									$scope.pagingOptions.currentPage,
									$scope.filterOptions.filterText);
						}
					}, true);
			$scope.$watch('filterOptions', function (newVal,
				oldVal) {
				if (newVal !== oldVal) {
					$scope.getPagedDataAsync(
						$scope.pagingOptions.pageSize,
						$scope.pagingOptions.currentPage,
						$scope.filterOptions.filterText);
				}
			}, true);

			$scope.gridOptions = {
				data: 'myDataOF',
				columnDefs: 'colDefs',
				selectedItems: $scope.selectedRows,
				enablePaging: true,
				showFooter: true,
				enableHighlighting: false,
				totalServerItems: 'totalServerItems',
				pagingOptions: $scope.pagingOptions,
				filterOptions: $scope.filterOptions,
				enableCellSelection: true,
				enableRowSelection: false,
				enableCellEditOnFocus: true,
			};


			/** Fin de gestion des Grids GPAO */
		}])

app.filter('showProduitFilterGpao', function () {
	return function (listeProduit, id) {
		var listeProduitFiltre = [];
		//////$log.debug("IdProduitt=  "+id.id);
		//////$log.debug("listeProduit "+ JSON.stringify(listeProduit, null, "    "));
		angular.forEach(listeProduit, function (produit, key) {

			if (produit.id == id.id) {
				//////$log.debug(produit.id +" == "+ id.id);
				listeProduitFiltre.push(produit);
			}

		});
		// ////$log.debug(" ** listeProduitFiltre "+ JSON.stringify(listeProduitFiltre, null, "    "));
		return listeProduitFiltre;
	};
})

app.filter('filterSousFamilleGpao', function () {
	return function (listeSousFamille, id) {
		var listeSousFamilleFiltre = [];
		// ////$log.debug("IdSousFamille=  "+id.id);
		// ////$log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
		angular.forEach(listeSousFamille, function (sousFamille, key) {

			if (sousFamille.id == id.id) {
				//////$log.debug(sousFamille.id +" == "+ id.id);
				listeSousFamilleFiltre.push(sousFamille);
			}

		});
		// ////$log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
		return listeSousFamilleFiltre;
	};
});


