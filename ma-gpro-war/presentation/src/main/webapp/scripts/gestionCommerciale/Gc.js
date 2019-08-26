'use strict'
angular
		.module('gpro.gc', [])
		.controller(
				'VenteCtrl',
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
							
							$log.debug("----------- VENTE ----------");
							//** Variables Recherche
							$scope.listProduitCommande = [];
							$scope.ListClientCommandeVenteCache = [];
							
							//**Variables Modif/Ajout
							$scope.commandeVenteCourante = {};
							$scope.listProduitDrop = [];
							$scope.listeDocumentCommandeVente = [];
							//**Variable Grid
							$scope.myDataCommandeVente = [];
							//bouton pdf hide
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
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
							$scope.listeClientCommandeVenteCache();
							$scope.listeTypeCommandeVenteCache();
							$scope.listeEtatCommandeVenteCache();
							$scope.listeSitesPartieInteresseeCache();
							$scope.listeTypeDocumentsCache();
							$scope.ListSousFamillesProduitCache();
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
								$scope.commandeVenteCourante = {};
								$scope.commandeVenteCourante = commande ? angular
										.copy(commande) : {};

								$scope.displayMode = "edit";
							}
							
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              					$scope.varPrix = [{index:'', prix:''}];
              					//init objetCourant
								$scope.commandeVenteCourante =   {
														  "vReference": "",
														  "vTypePartieInteressee": "",
														  "vProduit": "",
														  "dateIntroductionDu": "",
														  "dateIntroductionA": "",
														  "dateLivraisonDu": "",
														  "dateLivraisonA": "",
														  "quantiteDu": "",
														  "quantiteA": "",
														  "coutDu": "",
														  "coutA": "",
														  "vTypeCommande": "",
														  "vEtatCommande": ""
														};
								$scope.rechercherCommandeVente({});
								$scope.listProduitCommande = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
							}
							
							// Liste des Bons de Commande Vente
							$scope.listeBonCommandeVente = function() {
								$http.get(baseUrlGc + "/commandeVente/all").success(
										function(dataCommandeVente) {
											$scope.myDataCommandeVente = dataCommandeVente;
										});
							}
							
							// Ajout et Modification CommandeVente
							$scope.modifierOuCreerCommandeVente = function() {
								
								// bouton PDF activé
              					$scope.modePdf = "actif";

								var index = this.row.rowIndex;
								// getCommandeVente
								$http
										.get(
												baseUrlGc
														+ "/commandeVente/getId:"+
														 $scope.myDataCommandeVente[index].id)
										.success(
												function(datagetCommandeVente) {

													$log.debug("getById : "+ $scope.myDataCommandeVente[index].id +" \n  "+JSON.stringify(datagetCommandeVente,null,"  ") );
													//produitCommande
													$scope.listProduitCommande = datagetCommandeVente.produitCommandes;
													

													//disable update de la dropList 'Produit' 
					 								angular.forEach($scope.listProduitCommande, function(produitCommande, key){
									            		produitCommande.checked = true
													});

													//document
													$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;


													$scope.myDataCommandeVente[index].produitCommandes = $scope.listProduitCommande;
													$scope.myDataCommandeVente[index].documentCommandeVentes = $scope.listeDocumentCommandeVente;
												});

								$scope.commandeVenteCourante = $scope.myDataCommandeVente[index] ? angular
										.copy($scope.myDataCommandeVente[index])
										: {};
								$scope.displayMode = "edit";
							}

							// Rechercher CommandeVente
							$scope.rechercherCommandeVente = function(commandeVenteCourante) {
								
								$http
										.post(
												baseUrlGc
														+ "/commandeVente/rechercheCommandeVenteMulticritere",
														commandeVenteCourante)
										.success(
												function(resultat) {
													$scope.myDataCommandeVente = resultat.commandeVenteValues;
													$log.debug('resultat de recherche CommandeVente ..'+$scope.myDataCommandeVente.length);
													// Pagination du resultat de
											        // recherche
											        // data, page,pageSize
											            $scope
											                .setPagingData(
											                  $scope.myDataCommandeVente,
											                  $scope.pagingOptions.currentPage,
											                  $scope.pagingOptions.pageSize);

													//$scope.displayMode = "rechercher";
													$scope.creationSSForm.$setPristine();
													$scope.rechercheSSForm.$setPristine();
												});
								
							}
							// Sauvegarder CommandeVente
							$scope.sauvegarderAjout = function(commande) {

								if (angular.isDefined(commande.id)) {
									$log.debug("Sauvegarder Modification"+commande.id);
									$scope.updateCommandeVente(commande);
								} else {
									$log.debug("Sauvegarder Ajout" + commande.reference);
									$scope.creerCommandeVente(commande);
								}
								//refresh de la liste
								$scope.rechercherCommandeVente({});
							}
							
							// Mise à jour des Bons de Commandes de Vente
							$scope.updateCommandeVente= function(commande) {
								//produitCommande
								commande.produitCommandes = $scope.listProduitCommande;

								//document
								commande.documentCommandeVentes = $scope.listeDocumentCommandeVente ;
								$log.debug("Update "+JSON.stringify(commande,null,"  "));
								
								$http
										.post(
												baseUrlGc
														+ "/commandeVente/modifierCommandeVente",
												commande)
										.success(
												function(commandeModifiee) {
													for (var i = 0; i < $scope.listeBonCommandeVente.length; i++) {

														if ($scope.myDataCommandeVente[i].id == commandeModifiee.id) {
															$scope.myDataCommandeVente[i] = commandeModifiee;
															break;
														}
													}
													$log.debug("success Modification "+commandeModifiee);
													
													//getCommandeVente 
        											$http.get(
																baseUrlGc
																		+ "/commandeVente/getId:"+commandeModifiee)
														.success(
																function(datagetCommandeVente) {
							          								// bouton PDF activé
								 									$scope.modePdf = "actif";
																	
																	//produitCommande
																	$scope.listProduitCommande = datagetCommandeVente.produitCommandes;
																	
																	//disable update de la dropList 'Produit' 
									 								angular.forEach($scope.listProduitCommande, function(produitCommande, key){
													            		produitCommande.checked = true
																	});

																	//document
																	$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;

																	$scope.commandeVenteCourante = datagetCommandeVente ? angular
																		  .copy(datagetCommandeVente)
																			: {};

																});

												});
							}
							
							// Création d'un Bon de commande de vente
							$scope.creerCommandeVente = function(commande) {
								//produitCommande
								commande.produitCommandes = $scope.listProduitCommande;

								//DocumentProduitCommande
								commande.documentCommandeVentes = $scope.listeDocumentCommandeVente ;
								
								$log.debug("-- Create "+JSON.stringify(commande,null,"  "));
								
								$http.post(baseUrlGc + "/commandeVente/creerCommandeVente",	commande)
									 .success(
										function(newCommandeId) {
											$log.debug("success creation : "+newCommandeId);
											//
											//getCommandeVente 
											$http.get(
														baseUrlGc
																+ "/commandeVente/getId:"+newCommandeId)
												.success(
														function(datagetCommandeVente) {
					          								// bouton PDF activé
						 									$scope.modePdf = "actif";
															
															//produitCommande
															$scope.listProduitCommande = datagetCommandeVente.produitCommandes;
															
															//disable update de la dropList 'Produit' 
							 								angular.forEach($scope.listProduitCommande, function(produitCommande, key){
											            		produitCommande.checked = true
															});

															//document
															$scope.listeDocumentCommandeVente = datagetCommandeVente.documentCommandeVentes;

															$scope.commandeVenteCourante = datagetCommandeVente ? angular
																  .copy(datagetCommandeVente)
																	: {};

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
													+ $scope.myDataCommandeVente[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
              								$scope.myDataCommandeVente.splice($scope.index, 1);
								});
								$scope.closePopupDelete();
								$scope.rechercherCommandeVente({});
							};

							$scope.rechercherCommandeVente({});
							$scope.listeBonCommandeVente();

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

                      		//generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
              				$scope.download = function(id) {
								$log.debug("-- id"+id);
								var url = baseUrlGc+"/report/commandeVente?id=" + id+"&type=pdf";
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
							$scope.downloadAllCommandeVente = function(commandeVenteCourante) {

								var newdateLivBCMinFormat="";
								if(angular.isDefined(commandeVenteCourante.dateLivraisonDu)){
									
									if(commandeVenteCourante.dateLivraisonDu != ""){
										newdateLivBCMinFormat = formattedDate(commandeVenteCourante.dateLivraisonDu);
										$log.debug("===== newdateLivBCMinFormat "+newdateLivBCMinFormat);
									}else{
										$log.debug("===== newdateLivBCMinFormat is Null");
										newdateLivBCMinFormat = "";
									}
								}else{
									$log.debug("==dateLivraisonDu Undefined");
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(commandeVenteCourante.dateLivraisonA)){
									
									if(commandeVenteCourante.dateLivraisonA != ""){
										newdateLivBCMaxFormat = formattedDate(commandeVenteCourante.dateLivraisonA);
									//	$log.debug("===== newdateLivBCMaxFormat "+newdateLivBCMaxFormat);
									}else{
										$log.debug("===== newdateLivBCMaxFormat is Null");
										newdateLivBCMaxFormat = "";
									}
								}else{
									$log.debug("==dateLivraisonA Undefined");
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(commandeVenteCourante.dateLivraisonDu)){
									
									if(commandeVenteCourante.dateLivraisonDu != ""){
										newdateIntroBCMinFormat = formattedDate(commandeVenteCourante.dateLivraisonDu);
										$log.debug("===== newdateIntroBCMinFormat "+newdateIntroBCMinFormats);
									}else{
										$log.debug("===== newdateIntroBCMinFormat is Null");
										newdateIntroBCMinFormat = "";
									}
								}else{
									$log.debug("==dateLivraisonDu Undefined");
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(commandeVenteCourante.dateIntroductionA)){
									
									if(commandeVenteCourante.dateIntroductionA != ""){
										newdateIntroBCMaxFormat = formattedDate(commandeVenteCourante.dateIntroductionA);
										$log.debug("===== newdateIntroBCMaxFormat "+newdateIntroBCMaxFormat);
									}else{
										$log.debug("===== newdateIntroBCMaxFormat is Null");
										newdateIntroBCMaxFormat = "";
									}
								}else{
									$log.debug("==dateIntroductionA Undefined");
								}


								$log.debug("-- commandeVenteCourante" + JSON.stringify(commandeVenteCourante, null, "  ") );

								var url;
								$log.debug("PI  "+commandeVenteCourante.vTypePartieInteressee );
								 	if(commandeVenteCourante.vTypePartieInteressee == null){
								 	commandeVenteCourante.vTypePartieInteressee = "";
								 }

								$log.debug("Produit  "+commandeVenteCourante.vProduit );
								 	if(commandeVenteCourante.vProduit == null){
								 	commandeVenteCourante.vProduit = "";
								 }

			        			url = baseUrlGc + "/report/listCommandeVente?reference=" + commandeVenteCourante.vReference
								 					 + "&partieInteressee=" + commandeVenteCourante.vTypePartieInteressee
								 					 + "&produit="+commandeVenteCourante.vProduit
													 + "&dateIntroductionDu="+newdateIntroBCMinFormat
													 + "&dateIntroductionA="+newdateIntroBCMaxFormat
													 + "&dateLivraisonDu="+newdateLivBCMinFormat
													 + "&dateLivraisonA="+newdateLivBCMaxFormat
													 + "&typeCommande="+commandeVenteCourante.vTypeCommande
													 + "&etatCommande="+commandeVenteCourante.vEtatCommande
													 + "&quantiteDu="+commandeVenteCourante.quantiteDu
													 + "&quantiteA="+commandeVenteCourante.quantiteA
													 + "&coutDu="+commandeVenteCourante.coutDu
													 + "&coutA="+commandeVenteCourante.coutA
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
							$scope.removeProduit = function(
									index) {
								$scope.listProduitCommande.splice(
										index, 1);
								$log.debug("Success Delete Produit ");
							};

							/** Fin de gestion des Produit */
							

							/***************************************************
							 * Gestion des DocumentCommandeVente
							 **************************************************/
							 $scope.name="BCV";
							 
							 $scope.listeDocumentCommandeVente = [];
							 
								// GetId.designation
								$scope.doc = {

									status : ''
								};
								$scope.showStatus = function(id) {

									$scope.doc.status = id;
									var selected = $filter('filter')(
											$scope.ListTypeDocumentCache, {
												id : $scope.doc.status
											});
									if ($scope.doc.status && selected.length) {
										return selected[0].designation;
									} else {
										return "Not Set";
									}
								};
								
								// ajout d'un DocumentCommandeVente
								$scope.ajoutDocumentCommandeVente = function() {

									$scope.documentCommandeVenteInserree = {
										typeDocumentId:'',
										uidDocument : '',
										path : ''

									};
									$scope.listeDocumentCommandeVente
											.push($scope.documentCommandeVenteInserree);

								};

								// Enregistrer DocumentCommandeVente
								$scope.saveDocumentCommandeVente = function(										
										dataDocumentCommandeVente, id) {
									$log.debug("**SAVE DOC "+dataDocumentCommandeVente);
									$scope.deleteValue = "non";
									angular.extend(dataDocumentCommandeVente, {
										id : id
									});
								};

								// Supprimer DocumentCommandeVente
								$scope.removeDocumentCommandeVente = function(index) {
									$scope.listeDocumentCommandeVente.splice(index, 1);
								};
								/** Fin de gestion des DocumentCommandeVente */
							/** Fin de gestion des DocumentCommandeVente */
							/***************************************************
							 * Gestion de la Grid Bon de Commande de Vente 
							 **************************************************/
							$scope.typeAlert = 3;
								
							$scope.colDefs = [];
							$scope.$watch(
								'myDataCommandeVente',
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
											displayName : 'Réf.BC',
											width:'10%'
										},
										{
											field : 'typeCommandeDesignation',
											displayName : 'Type BC',
											width:'10%'
										},
										{
											field : 'partieIntersseDesignation',
											displayName : 'Client',
											width:'24%'
										},
										{
											field : 'dateIntroduction',
											displayName : 'Date Cmd.',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:'9%'
										},
										{
											field : 'dateLivraison',
											displayName : 'Date Liv.',
											cellFilter: 'date:\'dd-MM-yyyy\'',
											width:'9%'
										},
										{
											field : 'saison',
											displayName : 'Saison',
											width:'5%'
										},
										{
											field : 'quantite',
											displayName : 'Quantite',
											width:'10%'
										},
										{
											field : 'prixTotal',
											displayName : 'Cout',
											width:'5%'
										},
										{
											field : 'etatCommandeDesignation',
											displayName : 'Etat',
											width:'10%'
										},
										{
											field : '',
											width:'5%',
											cellTemplate :  '<div class="buttons">'
												+ '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerCommandeVente()"><i class="fa fa-fw fa-pencil"></i></button>'
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
									$scope.myDataCommandeVente = pagedData;
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
												var commandeVenteCourante = $scope.commandeVenteCourante;
												if (searchText) {
													var ft = searchText
															.toLowerCase();
													$http
															.post(
																	baseUrlGc+ "/commandeVente/rechercheCommandeVenteMulticritere",
																	commandeVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		data = largeLoad.commandeVenteValues
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
																	baseUrlGc+ "/commandeVente/rechercheCommandeVenteMulticritere",
																	commandeVenteCourante)
															.success(
																	function(
																			largeLoad) {
																		$scope
																				.setPagingData(
																						largeLoad.commandeVenteValues,
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
									data : 'myDataCommandeVente',
									columnDefs : 'colDefs',
									enablePaging : true,
									showFooter : true,
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
