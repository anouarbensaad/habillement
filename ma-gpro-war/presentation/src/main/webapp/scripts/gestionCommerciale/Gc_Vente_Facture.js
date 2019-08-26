'use strict'
angular
		.module('gpro.gcVenteFacture', [])
		.controller(
				'VenteFactureCtrl',
				[
						'$scope',
						'$http',
						'$filter',
						'$rootScope',
						'$log',
						'downloadService',
						'baseUrlGc',
						'baseUrl',
						function($scope, $http, $filter,$rootScope, $log, downloadService, baseUrlGc,baseUrl) {
							
							$log.debug("----------- Vente Facture ----------");
							//** Variables Recherche
							$scope.listProduitCommande = [];
							$scope.ListClientCommandeVenteCache = [];
							
							//**Variables Modif/Ajout
							$scope.factureVenteCourante = {};
							$scope.factureVenteCouranteEdit = {};
							$scope.listProduitDrop = [];
							$scope.listeDocumentCommandeVente = [];
						
							//**Variables ajout DiversFacture
							 $scope.factureVenteCouranteEdit.diversFactureValue=[];
							//**Variable Grid
							$scope.myData = [];
							//bouton pdf hide
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
							$scope.listProduitFactureVentePRBL = [];
							/*********************************************************
							 * Gestion de Cache des Referentiels Gestion Commerciale *
							 *********************************************************/
							$scope.ListEtatCommandeVenteCache = [];
							$scope.ListTypeCommandeVenteCache = [];
							$scope.listeSitePartieInteresseeCache = [];
							$scope.ListTypeDocumentCache = [];
							$scope.ListSousFamilleProduitCache = [];
							
							// Liste des ClientCommandeVenteCache
							$scope.listeClientCommandeVenteCache = function() {
								$http
										.get(baseUrlGc+	"/gestionCommercialCache/listeClientCache")
										.success(
												function(listeClientCache) {
													$log.debug("listClientCommandeVenteCache : " + listeClientCache.length);
													$scope.ListClientCommandeVenteCache = listeClientCache;

												});
							}
							
							// Liste des EtatCommandeVenteCache
							$scope.listeEtatCommandeVenteCache = function() {
								$http
										.get(
												baseUrlGc
														+ "/gestionCommercialCache/listeEtatCommandeVenteCache")
										.success(
												function(EtatCommandeVenteCache) {
													$log.debug("listEtatCommandeVenteCache : " + EtatCommandeVenteCache.length);
													$scope.ListEtatCommandeVenteCache = EtatCommandeVenteCache;

												});
							}
							// Liste des TypeCommandeVenteCache
							$scope.listeTypeCommandeVenteCache = function() {
								$http
										.get(
												baseUrlGc
														+ "/gestionCommercialCache/listeTypeCommandeVenteCache")
										.success(
												function(TypeCommandeVenteCache) {
													$log.debug("listTypeCommandeVenteCache : "+ TypeCommandeVenteCache.length);
													$scope.ListTypeCommandeVenteCache = TypeCommandeVenteCache;

												});
							}
							
							// Liste des SiteeCache
							$scope.listeSitesPartieInteresseeCache = function() {
								$http
										.get(baseUrlGc+"/gestionCommercialCache/listeSiteCache")
										.success(
												function(dataSiteCache) {
													$log.debug("listSiteCache : "+ dataSiteCache.length);
													$scope.listeSitePartieInteresseeCache = dataSiteCache;

												});
							}// Liste TypeDocumentCache
							$scope.listeTypeDocumentsCache = function() {
								$http
										.get(baseUrl+"/gestionnaireCache/listeTypeDocumentCache")
										.success(
												function(dataTypeDocumentCache) {
													$log.debug("listTypeDocumentCache : " + dataTypeDocumentCache.length);
													$scope.ListTypeDocumentCache = dataTypeDocumentCache;
												});
							}
							// Liste des SousFamilleCache
							$scope.ListSousFamillesProduitCache = function() {
								$http
										.get(baseUrl+"/gestionnaireCache/listeSousFamilleProduitCache")
										.success(
												function(
														dataSousFamilleProduitCache) {
													$log.debug("listSousFamilleProduitCache : " + dataSousFamilleProduitCache.length);
													$scope.ListSousFamilleProduitCache = dataSousFamilleProduitCache;

												});
							}

							$scope.listeModePaiement = function() {
								//TODO
								/*$http
								 	.get(baseUrlGc + "/modePaiement/getAll")
								 	.success(
										 function(dataPaiement) {

											 $scope.listeModePaiement = dataPaiement;
										 });*/
							}

							// Liste des Agent
							$scope.ListAgent = function() {
								$http
										.get(baseUrlGc+"/agentGc/all")
										.success(
												function(data) {
													$log.debug("listAgent: " + data.length);
													$scope.listAgent = data;

												});
							}

							$scope.listeModePaiement();
							$scope.listeClientCommandeVenteCache();
							$scope.listeTypeCommandeVenteCache();
							$scope.listeEtatCommandeVenteCache();
							$scope.listeSitesPartieInteresseeCache();
							$scope.listeTypeDocumentsCache();
							$scope.ListSousFamillesProduitCache();
//							$scope.ListAgent();
							/************************************************/
							// Liste des produits
							$scope.listeProduit = function() {
								$http.get(baseUrl+"/produit/all").success(
										function(dataProduit) {
											$scope.listProduitDrop = dataProduit;
										});
							}
							$scope.listeProduit();
							
							/************************************************/

							//champ autoSaisie du champs: referenceBC 
							$scope.listReferenceBL = function () {
							 //reload de la liste des RefBC
							 	$http
									.get(
											baseUrlGc+"/livraisonVente/getListBLReference")
											.success(
													function(resultat) {
														$log.debug("--ResultatListBC "+resultat.length);
														$scope.listReferenceBL = resultat;
														$log.debug("--listReferenceBL** : "+JSON.stringify($scope.listReferenceBL, null, "    "));
													});
											//$log.debug("--OnClicklistReferenceBL : "+JSON.stringify($scope.listReferenceBL, null, "    "));
											return $scope.listReferenceBL;
						          };
							$scope.listReferenceBL();
							$scope.select2TaggingOptions = {
									 'multiple': true,
									 'simple_tags': true,
									 'tags': $scope.listReferenceBL()
							 };

							$scope.validerBL = function(){

							 $log.debug(" Recherche des Produits appartenants à ces Bons de Livraison ...");
							 $log.debug("-- tagReferenceBLList ** ** : "+JSON.stringify($scope.tagReferenceBLList, null, "    ") );

							 //idBonLivVente: si undefined -> urlValier SANS idBonLivVente, sinon -> idBonLivVente AVEC idBonLivVente
							 //$log.debug("Valider : idBonLivVente "+ $scope.idBonLivVente );

							 var urlValider = baseUrlGc+ "/factureVente/validate";
								 $log.debug("-- urlValider Sans idBonLivVente : "+ urlValider );

								 //Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
								 $http
								 .post(
										 urlValider,$scope.tagReferenceBLList)
										 .success(
												 function(resultat) {
													$scope.factureVenteCouranteEdit.agentId = resultat.agentBLId;

													//listProduitsBL
													$scope.listProduitFactureVentePRBL = resultat.produitLivraison;
													$log.debug("==== resultat**** : "+ JSON.stringify(resultat,null,"  ") );


//													$log.debug("-- listProduitFactureVentePRBL Size** ** : "+ $scope.listProduitFactureVentePRBL.length);
													$log.debug("-- listProduitFactureVentePRBL ** **: "+ JSON.stringify($scope.listProduitFactureVentePRBL,null,'  '));
//													$log.debug("-- $scope.listProduitFactureVentePRBL ** **: "+ JSON.stringify($scope.listProduitFactureVentePRBL,null,'  '));

												 });



						 	}  

							$scope.pagingOptions = {
										pageSizes : [ 5, 10, 13 ],
										pageSize : 13,
										currentPage : 1
									};

							$scope.cancelAddCommandeVente = function(rowform, index, id, designation, liste){
						    		  if (angular.isDefined(id)) {
						    			 
						    				  $scope.deleteValue="non";
						    				  rowform.$cancel();
						    				  $log.debug("CANCEL");
						    		  }else{	
						    			  if(designation ==""){
						    				  $scope.deleteValue=="oui"
						    				  liste.splice(index, 1);
									    	  rowform.$cancel();
						    			  }else{
						    				  rowform.$cancel();
						    			  }
						    		}
						    		  $scope.deleteValue="oui";
						    }
							// ** Ajout Bon de Commande de Vente
							$scope.AffectationVenteBC = function(commande) {
								$scope.factureVenteCourante = {};
								$scope.factureVenteCourante = commande ? angular
										.copy(commande) : {};

								$scope.displayMode = "edit";
							}
							
							
							//Retour
							$scope.retourRecherche = function() {
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              					$scope.varPrix = [{index:'', prix:''}];
              					$scope.factureVenteCouranteEdit = {'tauxTVA':20};
   							 $scope.factureVenteCouranteEdit.diversFactureValue=[];
              					$scope.tagReferenceBLList = [];
              					//init objetCourant
								$scope.factureVenteCourante =  $scope.rechercherFactureVenteTemp; 
//								console.log("------rechercherFactureVenteTemp"+Json.stringify($scope.rechercherFactureVenteTemp,null,""));
//								{
//														  "reference":"",
//														  "partieInteresseeId":"",
//														  "dateFactureDu":"",
//														  "dateFactureAu":"",
//														  "dateEcheanceDu":"",
//														  "dateEcheanceAu":"",
//														  "coutDu":"", 
//														  "coutA":""
//														};
								$scope.listProduitCommande = [];
								$scope.listProduitFactureVentePRBL = [];
								$scope.rechercherFactureVente($scope.rechercherFactureVenteTemp);
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
							}
							
							
							
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              					$scope.varPrix = [{index:'', prix:''}];
              					$scope.factureVenteCouranteEdit = {'tauxTVA':20};
   							 $scope.factureVenteCouranteEdit.diversFactureValue=[];
              					$scope.tagReferenceBLList = [];
              					//init objetCourant
								$scope.factureVenteCourante =   {
														  "reference":"",
														  "partieInteresseeId":"",
														  "dateFactureDu":"",
														  "dateFactureAu":"",
														  "dateEcheanceDu":"",
														  "dateEcheanceAu":"",
														  "coutDu":"", 
														  "coutA":""
														};
								$scope.rechercherFactureVente({});
								$scope.listProduitCommande = [];
								$scope.listProduitFactureVentePRBL = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
							}
							
							// Ajout et Modification CommandeVente
							$scope.modifierOuCreerFactureVente = function() {
								
								// bouton PDF activé
              					$scope.modePdf = "actif";

								var index = this.row.rowIndex;
								// getCommandeVente
								$http
										.get(
												baseUrlGc
														+ "/factureVente/getById:"+
														 $scope.myData[index].id)
										.success(
												function(datagetCommandeVente) {

													$log.debug("getById : "+ $scope.myData[index].id +" \n  "+JSON.stringify(datagetCommandeVente,null,"  ") );
													//produitFacture
													//$scope.listProduitCommande = datagetCommandeVente.produitFactureVente;
													
													$scope.listProduitFactureVentePRBL = datagetCommandeVente.produitFactureVente;
													$scope.tagReferenceBLList = datagetCommandeVente.refLivraison
													$log.debug("==== $scope.tagReferenceBLList : "+ JSON.stringify($scope.tagReferenceBLList,null,"  ") );
													
													$scope.factureVenteCouranteEdit = datagetCommandeVente;
													//disable update de la dropList 'Produit' 
					 								angular.forEach($scope.listProduitFactureVentePRBL, function(produitFacture, key){
									            		produitFacture.checked = true
													});

													$log.debug("==== factureVenteCouranteEdit : "+ JSON.stringify($scope.factureVenteCouranteEdit,null,"  ") );
													
								$scope.displayMode = "edit";
								});
							}

							// Rechercher CommandeVente
							$scope.rechercherFactureVenteTemp = {}
							$scope.rechercherFactureVente = function(factureVenteCourante) {
								
								$scope.rechercherFactureVenteTemp = factureVenteCourante;
								$log.debug("------rechercherFactureVenteTemp INIT-- "+JSON.stringify($scope.rechercherFactureVenteTemp,null," "));
								
								$http
										.post(
												baseUrlGc
														+ "/factureVente/rechercheMulticritere",
														factureVenteCourante)
										.success(
												function(resultat) {
													
													$log.debug("------resultat : rech. Facture-- "+JSON.stringify(resultat,null," "));

													$scope.myData = resultat.factureVenteValues ;
													$log.debug('resultat de recherche CommandeVente ..'+$scope.myData.length);
													// Pagination du resultat de
											        // recherche
											        // data, page,pageSize
											            $scope
											                .setPagingData(
											                  $scope.myData,
											                  $scope.pagingOptions.currentPage,
											                  $scope.pagingOptions.pageSize);

													//$scope.displayMode = "rechercher";
													$scope.creationSSForm.$setPristine();
													$scope.rechercheSSForm.$setPristine();
												});
								
							}
							
							
							
							/*----------------------Debut gestion DiversFacture----------------------------------------------*/
							
							 // ajout DiversFacture
							 $scope.ajoutDiversFacture = function() {
								 $scope.diversProduitInserree = {
											designation :'',
											prix:'',
											quantite:'',
											prixTotal:''
									};
								 
									$log.debug("==== $scope.diversProduitInserree**** : "+ JSON.stringify($scope.diversProduitInserree,null,"  ") );
									$log.debug("==== Rowform**** : "+ JSON.stringify($scope.rowform,null,"  ") );

								 $scope.factureVenteCouranteEdit.diversFactureValue.push($scope.diversProduitInserree);
									$log.debug("====  $scope.factureVenteCouranteEdit.diversFactureValue**** : "+ JSON.stringify( $scope.factureVenteCouranteEdit.diversFactureValue,null,"  ") );

							 };
							 
							 
							 // Enregistrer DiversFacture
							 $scope.saveDiversFacture = function(data, id) {
								 $scope.deleteValue = "non";
								 angular.extend(data, {
										 id : id
									 });
								 
							 };
							 
							 $scope.deleteValue="oui";
							 //Annuler ajout DiversFacture
							 $scope.cancelAjoutDiversFacture = function(rowform, index, id, designation, prix, quantite, prixTotal){
									$log.debug("====  $scope.factureVenteCouranteEdit.diversFactureValue**** : "+ JSON.stringify($scope.factureVenteCouranteEdit.diversFactureValue,null,"  ") );

								 
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
							 
							 // Supprimer DiversFacture
							 $scope.DiversFacture = function(index) {
								 $scope.factureVenteCouranteEdit.diversFactureValue.splice(index, 1);
							 };

							
							/*-----------------------------fin gestion DiversFacture------------------------------------------*/
							
							// Sauvegarder CommandeVente
							$scope.sauvegarderAjoutFactureVente = function(commande) {

								if (angular.isDefined(commande.id)) {
									$log.debug("Sauvegarder Modification"+commande.id);
									$scope.updateFactureVente(commande);
									
									//rechercheTemp
									$scope.retourRecherche();
									
								} else {
									$log.debug("Sauvegarder Ajout" + commande.reference);
									$scope.creerFactureVente(commande);
									
									//refresh de la liste
									$scope.rechercherFactureVente({});
								}
//								//refresh de la liste
//								$scope.rechercherFactureVente({});
							}
							
							// Mise à jour des Bons de Commandes de Vente
							$scope.updateFactureVente= function(commande) {
								//produitCommande
								commande.produitFactureVente = $scope.listProduitFactureVentePRBL;
								$log.debug("Update "+JSON.stringify(commande,null,"  "));
								
								$http
										.put(
												baseUrlGc
														+ "/factureVente/modifier",commande)
										.success(
												function(commandeModifiee) {
													$log.debug("success Modification "+commandeModifiee);
													
//													$scope.annulerAjout();
													$scope.retourRecherche();

												});
							}
							
							// Création d'un Bon de commande de vente
							$scope.creerFactureVente = function(commande) {

								commande.refLivraison = $scope.tagReferenceBLList.join('-');
								//produitCommande
								commande.produitFactureVente = $scope.listProduitFactureVentePRBL;
								
								$log.info("-- Create "+JSON.stringify(commande,null,"  "));
								
								$http.post(baseUrlGc + "/factureVente/creer",commande)
									 .success(
										function(newCommandeId) {
											$log.debug("success creation : "+newCommandeId);
											//
											//getCommandeVente 
											$http.get(
														baseUrlGc
																+ "/factureVente/getById:"+newCommandeId)
												.success(
														function(datagetCommandeVente) {
															$scope.annulerAjout();
					          								// bouton PDF activé
						 									/*$scope.modePdf = "actif";
															
															//produitCommande
															$scope.listProduitFactureVentePRBL = datagetCommandeVente.produitFactureVente;
															
															//disable update de la dropList 'Produit' 
							 								angular.forEach($scope.listProduitCommande, function(produitCommande, key){
											            		produitCommande.checked = true
															});
*/
															//document
															/*$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;

															$scope.factureVenteCourante = datagetCommandeVente ? angular
																  .copy(datagetCommandeVente)
																	: {};*/

														});
										});
								
							}

							//suppression BonCommande
							$scope.supprimerCommandeVente = function() {
								//var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url :baseUrlGc + "/commandeVente/supprimerCommandeVente:"
													+ $scope.myData[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
              								$scope.myData.splice($scope.index, 1);
										})
										.error(function(){
											$log.debug("error Delete");
											$scope.myData.splice($scope.index, 1);
										});
								
								$scope.closePopupDelete();
								$scope.rechercherFactureVente({});
							};

							$scope.rechercherFactureVente({});

							/*** PDF ***/
							//conversion date en String
							function formattedDate(date) {
							    var d = new Date(date),
							        month = '' + (d.getMonth() + 1),
							        day = '' + d.getDate(),
							        year = d.getFullYear();

							    if (month.length < 2) month = '0' + month;
							    if (day.length < 2) day = '0' + day;
							    return [year,month,day].join('-');
							}

                      		//generer rapport apres creation d'une Facture de Vente. mode : Modification/Consultation
              				$scope.download = function(id,a) {
								$log.debug("-- id"+id);
								$log.debug("-- a"+a);
								if (a==1) {
									
									var url = baseUrlGc+"/report/factureVente?id=" + id+"&a="+a+"&type=pdf";
									
								}
								if (a==2){
									
									var url = baseUrlGc+"/report/factureVente?id=" + id+"&a="+a+"&type=pdf";

								}
								
								$log.debug('----------url : '+ JSON.stringify(url, null, "  "));

//								var url = baseUrlGc+"/report/factureVente?id=" + id+"&type=pdf";
								downloadService.download(url)
										.then(
												function(success) {
													$log.debug('success : '+ success);
													//$scope.annulerAjout();
												},
												function(error) {
													$log.debug('error : '+ error);
												});
							};
        						
        					//generer rapport de tous les bons de livraison. mode : List 
							$scope.downloadAllCommandeVente = function(factureVenteCourante) {

								var newdateLivBCMinFormat="";
								if(angular.isDefined(factureVenteCourante.dateFactureDu)){
									
									if(factureVenteCourante.dateFactureDu != ""){
										newdateLivBCMinFormat = formattedDate(factureVenteCourante.dateFactureDu);
										$log.debug("===== newdateLivBCMinFormat "+newdateLivBCMinFormat);
									}else{
										$log.debug("===== newdateLivBCMinFormat is Null");
										newdateLivBCMinFormat = "";
									}
								}else{
									$log.debug("==dateFactureDu Undefined");
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(factureVenteCourante.dateFactureAu)){
									
									if(factureVenteCourante.dateFactureAu != ""){
										newdateLivBCMaxFormat = formattedDate(factureVenteCourante.dateFactureAu);
									//	$log.debug("===== newdateLivBCMaxFormat "+newdateLivBCMaxFormat);
									}else{
										$log.debug("===== newdateLivBCMaxFormat is Null");
										newdateLivBCMaxFormat = "";
									}
								}else{
									$log.debug("==dateFactureAu Undefined");
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(factureVenteCourante.dateEcheanceDu)){
									
									if(factureVenteCourante.dateEcheanceDu != ""){
										newdateIntroBCMinFormat = formattedDate(factureVenteCourante.dateEcheanceDu);
										$log.debug("===== newdateIntroBCMinFormat "+newdateIntroBCMinFormats);
									}else{
										$log.debug("===== newdateIntroBCMinFormat is Null");
										newdateIntroBCMinFormat = "";
									}
								}else{
									$log.debug("==dateEcheanceDu Undefined");
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(factureVenteCourante.dateEcheanceAu)){
									
									if(factureVenteCourante.dateEcheanceAu != ""){
										newdateIntroBCMaxFormat = formattedDate(factureVenteCourante.dateEcheanceAu);
										$log.debug("===== newdateIntroBCMaxFormat "+newdateIntroBCMaxFormat);
									}else{
										$log.debug("===== newdateIntroBCMaxFormat is Null");
										newdateIntroBCMaxFormat = "";
									}
								}else{
									$log.debug("==dateEcheanceAu Undefined");
								}


								$log.debug("-- factureVenteCourante" + JSON.stringify(factureVenteCourante, null, "  ") );

								var url;
								$log.debug("PI  "+factureVenteCourante.vTypePartieInteressee );
								 	if(factureVenteCourante.vTypePartieInteressee == null){
								 	factureVenteCourante.vTypePartieInteressee = "";
								 }

								$log.debug("Produit  "+factureVenteCourante.vProduit );
								 	if(factureVenteCourante.vProduit == null){
								 	factureVenteCourante.vProduit = "";
								 }
								// FIXME:Date
			        			url = baseUrlGc + "/report/listCommandeVente?reference=" + factureVenteCourante.reference
								 					 + "&partieInteressee=" + factureVenteCourante.partieInteresseeId
								 					 + "&produit="+factureVenteCourante.vProduit
													 + "&dateIntroductionDu="+newdateIntroBCMinFormat
													 + "&dateIntroductionA="+newdateIntroBCMaxFormat
													 + "&dateLivraisonDu="+newdateLivBCMinFormat
													 + "&dateLivraisonA="+newdateLivBCMaxFormat
													 + "&coutDu="+factureVenteCourante.coutDu
													 + "&coutA="+factureVenteCourante.coutA
													 + "&quantiteDu="+factureVenteCourante.quantiteDu
													 + "&quantiteA="+factureVenteCourante.quantiteA
													 + "&coutDu="+factureVenteCourante.coutDu
													 + "&coutA="+factureVenteCourante.coutA
													 + "&type=pdf";
				                  
				                 $log.debug("-- URL" + url );
								 downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });
							};	
						
							/***************************************************
							 * Gestion des Produits
							 **************************************************/
							 /***************************************************
    							 * Conversion Id / Designation 
    							 **************************************************/
    							 
              					// Produit 
              					$scope.produitId = {
            						status : ''
              					};
              					//SousFamilleProduit
              					$scope.sousFamilleProduitId = {
              						status : ''
                  				};

							 // showProduitDetails
              					$scope.showProduitDetails = function(produitId, attributRecherche) {
              						
              						$scope.produitId.status = produitId;
              						var selected = $filter('showProduitFilterBS')(
              								$scope.listProduitDrop, {
              									id : $scope.produitId.status
              								});
              						if ($scope.produitId.status && selected.length) {
              							if(attributRecherche == 'reference'){
              								return selected[0].reference;
              							}
              							else if(attributRecherche == 'tissu'){
              								return selected[0].designation;
              							}
              							if(attributRecherche == 'prix'){
              								return selected[0].prixUnitaire;
              							}
              							else if(attributRecherche == 'type'){
              								//conversion de sousFamilleId par son designation
              								$scope.sousFamilleProduitId.status = selected[0].sousFamilleId;
                      						var selected2= $filter('filterSousFamilleBS')(
                      								$scope.ListSousFamilleProduitCache , {
                      									id : $scope.sousFamilleProduitId.status
                      								});
                      						if ($scope.sousFamilleProduitId.status && selected2.length) {
                      							return selected2[0].designation;
                      						}else{
                      							return "--";
                      						}
              							}
              							
              						} 
              					}

							$scope.showBtnCalender = true;
							// show bottons Calender
							$scope.showBC = function() {
								$scope.showBtnCalender = true;
							}
							// GetId.designation Produit
						       $scope.designation = {

						        status : ''
						       };
						       $scope.showStatusDesignationProduit = function(id) {
						        $scope.designation.status = id;
						        var selected3 = $filter('showProduitFilterBS')(
						          $scope.listProduitDrop, {
						           id : $scope.designation.status
						          });
						        if ($scope.designation.status && selected3.length) {
						         return selected3[0].designation;
						        }else {
						         return "Not Set";
						        }
						       };  
							
							// GetId.designation Produit
						       $scope.reference = {

						        status : ''
						       };
						       $scope.showStatusReferenceProduit = function(id) {
						        $scope.reference.status = id;
						        var selected2 = $filter('showProduitFilterBS')(
						          $scope.listProduitDrop, {
						           id : $scope.reference.status
						          });
						        if ($scope.reference.status && selected2.length) {
						         return selected2[0].reference;
						        }else {
						                   return "Not Set";
						                  }
						       };   
						       
							// GetId.SousFamille Produit
						      
								$scope.type = {
									status : ''
								};

								$scope.famille = {
									status : ''
								};

								$scope.showStatusSousFamilleProduit = function(id) {
									$scope.famille.status = id;
									var selected = $filter('showProduitFilterBS')(
											$scope.listProduitDrop, {
												id : $scope.famille.status
											});
									if ($scope.famille.status && selected.length) {

										$scope.type.status = selected[0].sousFamilleId;
										var selected2 = $filter('filterSousFamilleBS')(
												$scope.ListSousFamilleProduitCache, {
													id : $scope.type.status
												});
										return ($scope.type.status && selected2.length) ? selected2[0].designation
												: 'Not set';
									} else {
										return "NOT SET";
									}

								};
						       
						   // GetId.PrixUnitaire Produit
						       $scope.prix = {

						        status : ''
						       };
						       
								$scope.varPrix = [{index:'', prix:''}];

						       $scope.changePrix = function (event, item, index) {
						       /*	$log.debug("--------------------- KeyUp ----------------------");
						       	$log.debug("Add .. ");
    							 	$log.debug("itemAdd  : "+ item);
    							 	$log.debug("indexAdd : "+ index);

    							 	$log.debug("////////////////// $scope.listProduitCommande[index].prix : "+ $scope.listProduitCommande[index].prix);
*/

    							 }

    							 
						        $scope.clickProduit = function( id, index){
						        	$log.debug(" Click Produit : ");


						        	 $scope.prix.status = id;

						        var selected2 = $filter('showProduitFilterBS')(
						          $scope.listProduitDrop, {
						           id : $scope.prix.status
						          });
						        if ($scope.prix.status && selected2.length) {

						        	/*$log.debug("--BEFORE---------------------------------");
						        	$log.debug("   varPrix .. "+JSON.stringify($scope.varPrix, null, "  ") );
						        	$log.debug(" * listProduitCommande .. "+JSON.stringify($scope.listProduitCommande, null, "  ") );
						        	*/
						        	
						        	var item = { index:index,
						        				 prix:selected2[0].prixUnitaire
						        				};

						        	$log.debug("Item  : "+JSON.stringify(item, null, "  ") );

						        	if( angular.isDefined($scope.varPrix[index]) ){
						        		$log.debug("1111 : $scope.varPrix["+index+"]."+index+" : " + $scope.varPrix[index].index+"  | item.index  :"+item.index);
						        		
						        		if($scope.varPrix[index].index != item.index){
						        			$log.debug("---A  "+ $scope.varPrix[index].index +" !=  "+ item.index);
						        			$scope.varPrix[index].prix = item.prix;
						        		}else{
						        			$log.debug("---B  "+ $scope.varPrix[index].index +" ==  "+ item.index);
						        			$scope.varPrix[index].index = item.index;
						        			$scope.varPrix[index].prix = item.prix;

						        			$scope.listProduitCommande[index].prix = $scope.varPrix[index].prix ;
						        		}

						        	}else{
						        		$log.debug("2222");
						        		$scope.varPrix.push(item);
						        		$scope.listProduitCommande[index].prix= item.prix ;
						        	}
						        	
						        	/*$log.debug("--AFTER----------------------------------");
						        	$log.debug("   varPrix .. "+JSON.stringify($scope.varPrix, null, "  ") );
									$log.debug(" * listProduitCommande .. "+JSON.stringify($scope.listProduitCommande, null, "  ") );
									*/
						        // return selected2[0].prixUnitaire;
						        }else {
						             return "Not Set";
						        }


						        }
    							 
						    // ajout d'un Produit
							$scope.ajoutProduit = function() {
								$log.debug("----------------- New Line ------------------");
								$log.debug("New Line ");
								
								if($scope.listProduitCommande.length == 0){
									$scope.indexNewPrix = $scope.listProduitCommande.length;
								
								}else{

									$scope.indexNewPrix = $scope.listProduitCommande.length;
								}
								

								$scope.produitInserree = {
										produitId :'',
										quantite:'',
										prix:'',
										commandeVenteId:'',
										'checked' : false

								};
								
								$scope.listProduitCommande
										.push($scope.produitInserree);
								
							};
							
							// Enregistrer Produit
							$scope.saveProduit = function(
									dataProduit, id) {
								$scope.deleteValue = "non";
								
								angular.extend(dataProduit, {
									id : id
								});
								$scope.showBtnCalender = false;
							};

							// Supprimer Produit
							$scope.removeProduitFacture = function(
									index) {
								$scope.listProduitFactureVentePRBL.splice(
										index, 1);
								$log.debug("Success Delete Produit ");
							};

							/** Fin de gestion des Produit */

							/***************************************************
							 * Gestion de la Grid Bon de Commande de Vente 
							 **************************************************/
							$scope.typeAlert = 3;
								
							$scope.colDefs = [];
							$scope.$watch(
								'myData',
									function() {
										$scope.colDefs = [
										{
											field : '',
											headerCellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
											cellTemplate : '<input type=\"checkbox\" style=\"margin:8px;\"/>',
											width:'3%'
										},
										{
											field : 'reference',
											displayName : 'Réf. Fact.',
											width:'10%'
										},
										{
											field : 'partieInteresseDesignation',
											displayName : 'Client',
											width:'20%'
										},
										{
											field : 'dateFacturation',
											displayName : 'Date Fact.',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:'10%'
										},
										{
											field : 'dateEcheance',
											displayName : 'Date Ech.',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:'10%'
										},
										{
											field : 'modeReglement',
											displayName : 'Mode Regl.',
											width:'12%'
										},
										{
											field : 'referenceCommande',
											displayName : 'ref BL.',
											width:'20%'
										},
										{
											field : 'prixTotalHT',
											displayName : 'Cout',
											width:'10%'
										},
										{
											field : '',
											width:'5%',
											cellTemplate :  '<div class="buttons">'
												+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerFactureVente()"><i class="fa fa-fw fa-pencil"></i></button>'
												+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(20)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'
										}];
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
												var factureVenteCourante = $scope.factureVenteCourante;
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$http
															.post(
																	baseUrlGc+ "/factureVente/rechercheMulticritere",
																	factureVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		data = largeLoad.factureVenteValues 
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
																	baseUrlGc+ "/factureVente/rechercheMulticritere",
																	factureVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		$scope
																				.setPagingData(
																						largeLoad.factureVenteValues ,
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

								$scope.factureVenteGridOptions = {
									data : 'myData',
									columnDefs : 'colDefs',
									enablePaging : true,
									showFooter : true,
									enableHighlighting : true,
									totalServerItems : 'totalServerItems',
									pagingOptions : $scope.pagingOptions,
									selectedItems : $scope.selectedRows,
									filterOptions : $scope.filterOptions,
								};

							
							/** Fin de gestion des Grids Vente BC */
						} ])
				.filter('showProduitFilterBS', function() {
				  return function(listeProduit, id) {
					 var listeProduitFiltre = [];
					$log.debug("IdProduitt=  "+id.id);
					$log.debug("listeProduit "+ JSON.stringify(listeProduit, null, "    "));
					 angular.forEach(listeProduit, function(produit, key){
						
						if(produit.id == id.id){
							$log.debug(produit.id +" == "+ id.id);
							listeProduitFiltre.push(produit);
						}
							
					 });
					// $log.debug(" ** listeProduitFiltre "+ JSON.stringify(listeProduitFiltre, null, "    "));
					 return listeProduitFiltre;
				  };
				})

				.filter('filterSousFamilleBS', function() {
				  return function(listeSousFamille, id) {
					 var listeSousFamilleFiltre = [];
					// $log.debug("IdSousFamille=  "+id.id);
					// $log.debug("listeSousFamille "+ JSON.stringify(listeSousFamille, null, "    "));
					 angular.forEach(listeSousFamille, function(sousFamille, key){
						
						if(sousFamille.id == id.id){
							$log.debug(sousFamille.id +" == "+ id.id);
							listeSousFamilleFiltre.push(sousFamille);
						}
							
					 });
					// $log.debug(" ** listeSousFamilleFiltre "+ JSON.stringify(listeSousFamilleFiltre, null, "    "));
					 return listeSousFamilleFiltre;
				  };
				});
