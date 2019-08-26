'use strict'
angular
		.module('gpro.gcVenteBL', [])
		.controller(
				'VenteBLCtrl',
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
							
							$log.debug("----------- Vente BL ----------");
							//** Variables Recherche
							$scope.listProduitCommande = [];
							$scope.ListClientCommandeVenteCache = [];
							
							//**Variables Modif/Ajout
							$scope.bonLivVenteCourant = {};
							$scope.listProduitDrop = [];
							$scope.listeDocumentCommandeVente = [];
							$scope.listProduitLivraisonVentePRBC = [];
							//**Variable Grid
							$scope.myDataCommandeVente = [];
							//bouton pdf hidef
              				$scope.modePdf = "notActive";
							
							$scope.displayMode = "list";
							
						/***** Cache *****/
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


							// Liste des produits
							$scope.listeProduit = function() {
								$http.get(baseUrl+"/produit/all").success(
										function(dataProduit) {
											$scope.listProduitDrop = dataProduit;
										});
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
							$scope.listeProduit();
//							$scope.ListAgent();
						/***** fin Gestion Cache *******/	
						
						/****** Gestion BL *****/
							$scope.pagingOptions = {
										pageSizes : [ 5, 10, 13 ],
										pageSize : 13,
										currentPage : 1
									};

							$scope.cancelAddBonLivraisonVente = function(rowform, index, id, designation, liste){
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
							// ** Ajout Bon de Livraison de Vente
							$scope.AffectationVenteBC = function(commande) {
								$scope.bonLivVenteCourant = {};
								$scope.bonLivVenteCourant = commande ? angular
										.copy(commande) : {};

								$scope.displayMode = "edit";
							}
							//champ autoSaisie du champs: referenceBC 
							$scope.listReferenceBC = function () {
											 //reload de la liste des RefBC
											 	$http
													.get(
															baseUrlGc+"/commandeVente/getListBCReferences")
															.success(
																	function(resultat) {
																		$log.debug("--ResultatListBC "+resultat.length);
																		$scope.listReferenceBC = resultat;
																		$log.debug("--listReferenceBC : "+JSON.stringify($scope.listReferenceBC, null, "    "));
																	});
															//$log.debug("--OnClicklistReferenceBC : "+JSON.stringify($scope.listReferenceBC, null, "    "));
															return $scope.listReferenceBC;
										          };
							$scope.listReferenceBC();
							$scope.select2TaggingOptions = {
									 'multiple': true,
									 'simple_tags': true,
									 'tags': $scope.listReferenceBC()
							 };

							$scope.validerBS = function(){

							 $log.debug(" Recherche des Produits appartenants à ces Bons de Commande ...");
							 $log.debug("-- tagReferenceBCList : "+JSON.stringify($scope.tagReferenceBCList, null, "    ") );

							 var urlValider = baseUrlGc+ "/livraisonVente/validate";
								 $log.debug("-- urlValider Sans idBonLivVente : "+ urlValider );

								 //Invocation du service Validate qui nous recupere la liste des RouleauxFini qui ne soont PAS affectés au BonSortie Auparavant.
								 $http
								 .post(
										 urlValider,$scope.tagReferenceBCList)
										 .success(
												 function(resultat) {
													 //listProduitLivraisonVentePRBC
													 $scope.listProduitLivraisonVentePRBC = resultat;
													 $log.debug("-- listProduitLivraisonVentePRBC Size : "+ $scope.listProduitLivraisonVentePRBC.length);

													 $log.debug("-- listProduitLivraisonVentePRBC ** ** ** : "+ JSON.stringify($scope.listProduitLivraisonVentePRBC,null,'  '));

												 });

						 	}  
							
							// Annulation de l'ajout
							$scope.annulerAjout = function() {
								$scope.cnt=0;
								//bouton pdf hide
              					$scope.modePdf = "notActive";
              					//vider le tab
              					$scope.varPrix = [{index:'', prix:''}];
              					$scope.listProduitLivraisonVentePRBC = [];
              					$scope.bonLivVenteCourantEdit = {};
              					$scope.tagReferenceBCList = [];
              					//init objetCourant
								$scope.bonLivVenteCourant =   {
														  "reference":"",
														  "partieInteresseeId":"",
														  "produitId":"",
														  "dateIntroductionDu":"",
														  "dateIntroductionA":"",
														  "dateLivraisonDu":"",
														  "dateLivraisonA":"",
														  "quantiteDu": "",
														  "quantiteA": "",
														  "coutDu": "",
														  "coutA": ""
														};
								$scope.rechercherLivraisonVente({});
								$scope.listProduitCommande = [];
								$scope.creationSSForm.$setPristine();
								$scope.rechercheSSForm.$setPristine();
								$scope.displayMode = "list";
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
														+ "/livraisonVente/getById:"+
														 $scope.myDataCommandeVente[index].id)
										.success(
												function(datagetCommandeVente) {

													$log.debug("getById : "+ $scope.myDataCommandeVente[index].id +" \n  "+JSON.stringify(datagetCommandeVente,null,"  ") );
													
													//produitCommande
													//$scope.listProduitCommande = datagetCommandeVente.produitLivraison;
													$scope.listProduitLivraisonVentePRBC = datagetCommandeVente.produitLivraison;
													
													$log.debug ("$scope.listProduitLivraisonVentePRBC" + JSON.stringify($scope.listProduitLivraisonVentePRBC,null," "));
													$scope.tagReferenceBCList = datagetCommandeVente.refCommande
													
													//disable update de la dropList 'Produit' 
					 								angular.forEach($scope.listProduitLivraisonVentePRBC, function(produitLivraison, key){
									            		produitLivraison.checked = true
													});

													$scope.bonLivVenteCourantEdit = datagetCommandeVente;
													$scope.displayMode = "edit";
													$log.debug("bonLivVenteCourantEdit **** : "+JSON.stringify($scope.bonLivVenteCourantEdit,null,"  ") );
												});

								
							}

							// Rechercher CommandeVente
							$scope.rechercherLivraisonVente = function(bonLivVenteCourant) {
								$log.info('------bonLivVenteCourant : '+JSON.stringify(bonLivVenteCourant,null," "));
													
								$http
										.post(
												baseUrlGc
														+ "/livraisonVente/rechercheMulticritere",
														bonLivVenteCourant)
										.success(
												function(resultat) {
													$scope.myDataCommandeVente = resultat.livraisonVenteValues;
													$log.debug('resultat de recherche CommandeLiv ..'+$scope.myDataCommandeVente.length);
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
								$scope.rechercherLivraisonVente({});
							}
							
							// Mise à jour des Bons de Commandes de Vente
							$scope.updateCommandeVente= function(commande) {
								//produitCommande
								commande.produitLivraison = $scope.listProduitLivraisonVentePRBC;

								$log.debug("Update "+JSON.stringify(commande,null,"  "));
								
								$http
										.put(
												baseUrlGc
														+ "/livraisonVente/modifier",
												commande)
										.success(
												function(commandeModifiee) {
													
													$log.debug("success Modification "+commandeModifiee);
													
													//getCommandeVente 
        											$http.get(
																baseUrlGc
																		+ "/livraisonVente/getById:"+commandeModifiee)
														.success(
																function(datagetCommandeVente) {
							          								
							          								$scope.annulerAjout();

																});

												});
							}
							
							// Création d'un Bon de commande de vente
							$scope.creerCommandeVente = function(commande) {
								//produitCommande
								commande.refCommande = $scope.tagReferenceBCList.join('-');
								
								commande.produitLivraison = $scope.listProduitLivraisonVentePRBC;

								$log.debug("-- Create "+JSON.stringify(commande,null,"  "));
								
								$http.post(baseUrlGc + "/livraisonVente/creer",	commande)
									 .success(
										function(newCommandeId) {
											$log.debug("success creation : "+newCommandeId);
											//
											//getCommandeVente 
											$http.get(
														baseUrlGc
																+ "/livraisonVente/getById:"+newCommandeId)
												.success(
														function(datagetCommandeVente) {
															$scope.annulerAjout();
															

														});
										});
								
							}

							//suppression BonCommande
							$scope.supprimerCommandeVente = function() {
								//var index = this.row.rowIndex;
								$http(
										{
											method : "DELETE",
											url :baseUrlGc + "/livraisonVente/supprimer:"
													+ $scope.myDataCommandeVente[$scope.index].id
										}).success(function() {
											$log.debug("Success Delete");
              								$scope.myDataCommandeVente.splice($scope.index, 1);
										})
										.error(function(){
											$log.debug("Error");
              								$scope.myDataCommandeVente.splice($scope.index, 1);
										});
								
								$scope.closePopupDelete();
								$scope.rechercherLivraisonVente({});
							};

							$scope.rechercherLivraisonVente({});
							

						/*** Fin Gestion Vente BL ***/	
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
								var url = baseUrlGc+"/report/bonLivraisonVente?id=" + id+"&type=pdf";
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
							$scope.downloadAllCommandeVente = function(bonLivVenteCourant) {

								var newdateLivBCMinFormat="";
								if(angular.isDefined(bonLivVenteCourant.dateLivraisonDu)){
									
									if(bonLivVenteCourant.dateLivraisonDu != ""){
										newdateLivBCMinFormat = formattedDate(bonLivVenteCourant.dateLivraisonDu);
										$log.debug("===== newdateLivBCMinFormat "+newdateLivBCMinFormat);
									}else{
										$log.debug("===== newdateLivBCMinFormat is Null");
										newdateLivBCMinFormat = "";
									}
								}else{
									$log.debug("==dateLivraisonDu Undefined");
								}

								var newdateLivBCMaxFormat="";
								if(angular.isDefined(bonLivVenteCourant.dateLivraisonA)){
									
									if(bonLivVenteCourant.dateLivraisonA != ""){
										newdateLivBCMaxFormat = formattedDate(bonLivVenteCourant.dateLivraisonA);
									//	$log.debug("===== newdateLivBCMaxFormat "+newdateLivBCMaxFormat);
									}else{
										$log.debug("===== newdateLivBCMaxFormat is Null");
										newdateLivBCMaxFormat = "";
									}
								}else{
									$log.debug("==dateLivraisonA Undefined");
								}

								var newdateIntroBCMinFormat="";
								if(angular.isDefined(bonLivVenteCourant.dateLivraisonDu)){
									
									if(bonLivVenteCourant.dateLivraisonDu != ""){
										newdateIntroBCMinFormat = formattedDate(bonLivVenteCourant.dateLivraisonDu);
										$log.debug("===== newdateIntroBCMinFormat "+newdateIntroBCMinFormats);
									}else{
										$log.debug("===== newdateIntroBCMinFormat is Null");
										newdateIntroBCMinFormat = "";
									}
								}else{
									$log.debug("==dateLivraisonDu Undefined");
								}

								var newdateIntroBCMaxFormat="";
								if(angular.isDefined(bonLivVenteCourant.dateIntroductionA)){
									
									if(bonLivVenteCourant.dateIntroductionA != ""){
										newdateIntroBCMaxFormat = formattedDate(bonLivVenteCourant.dateIntroductionA);
										$log.debug("===== newdateIntroBCMaxFormat "+newdateIntroBCMaxFormat);
									}else{
										$log.debug("===== newdateIntroBCMaxFormat is Null");
										newdateIntroBCMaxFormat = "";
									}
								}else{
									$log.debug("==dateIntroductionA Undefined");
								}


								$log.debug("-- bonLivVenteCourant" + JSON.stringify(bonLivVenteCourant, null, "  ") );

								var url;
								$log.debug("PI  "+bonLivVenteCourant.vTypePartieInteressee );
								 	if(bonLivVenteCourant.vTypePartieInteressee == null){
								 	bonLivVenteCourant.vTypePartieInteressee = "";
								 }

								$log.debug("Produit  "+bonLivVenteCourant.vProduit );
								 	if(bonLivVenteCourant.vProduit == null){
								 	bonLivVenteCourant.vProduit = "";
								 }
								 //FIXME: Date
			        			url = baseUrlGc + "/report/listCommandeVente?reference=" + bonLivVenteCourant.reference
								 					 + "&partieInteressee=" + bonLivVenteCourant.partieInteresseeId
								 					 + "&produit="+bonLivVenteCourant.produitId
													 + "&dateIntroductionDu="+newdateIntroBCMinFormat
													 + "&dateIntroductionA="+newdateIntroBCMaxFormat
													 + "&dateLivraisonDu="+newdateLivBCMinFormat
													 + "&dateLivraisonA="+newdateLivBCMaxFormat
													 + "&quantiteDu="+bonLivVenteCourant.quantiteDu
													 + "&quantiteA="+bonLivVenteCourant.quantiteA
													 + "&coutDu="+bonLivVenteCourant.coutDu
													 + "&coutA="+bonLivVenteCourant.coutA
													 + "&type=pdf";
				                  
				                 $log.debug("-- URL" + url );
								 downloadService.download(url).then(
										 function(success) {
											// $log.debug('success : ' + success);
										 }, function(error) {
											// $log.debug('error : ' + error);
										 });
							};	
						/******* Fin PDF *****/
						/******Gestion des ProduitCommandeLivraison*******/
              					// Produit 
              					$scope.produitId = {
            						status : ''
              					};
              					//SousFamilleProduit
              					$scope.sousFamilleProduitId = {
              						status : ''
                  				};

							 	//showProduitDetails
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
							        if ($scope.reference.status && selected2.length){
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
								$scope.removeProduitLivraison = function(
										index) {
									$scope.listProduitLivraisonVentePRBC.splice(
											index, 1);
									$log.debug("Success Delete Produit ");
								};

						/****** Fin de gestion des Produits *****/
							
						/****** Grid  ******/
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
												displayName : 'Réf.BL',
												width:'10%'
											},
											{
												field : 'partieIntersseDesignation',
												displayName : 'Client',
												width:'30%'
											},
											{
												field : 'dateCommande',
												displayName : 'Date Cmd.',
												cellFilter: 'date:\'dd-MM-yyyy\'',
												width:'10%'
											},
											{
												field : 'dateLivraison',
												displayName : 'Date Liv.',
												cellFilter: 'date:\'dd-MM-yyyy\'',
												width:'10%'
											},
											{
												field : 'referenceCommande',
												displayName : 'Ref. Commande',
												width:'10%'
											},
											{
												field : 'colis',
												displayName : 'Colis',
												width:'10%'
											},
											{
												field : 'prixTotal',
												displayName : 'Cout',
												width:'10%'
											},
											{
												field : '',
												width:'7%',
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
													var bonLivVenteCourant = $scope.bonLivVenteCourant;
													if (searchText) {
														var ft = searchText
																.toLowerCase();
														$http
																.post(
																		baseUrlGc+ "/livraisonVente/rechercheMulticritere",
																		bonLivVenteCourant)
																.success(
																		function(
																				largeLoad) {
																			data = largeLoad.livraisonVenteValues
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
																		baseUrlGc+ "/livraisonVente/rechercheMulticritere",
																		bonLivVenteCourant)
																.success(
																		function(
																				largeLoad) {
																			$scope
																					.setPagingData(
																							largeLoad.livraisonVenteValues,
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

									$scope.BLivGridOptions = {
										data : 'myDataCommandeVente',
										columnDefs : 'colDefs',
										enablePaging : true,
										showFooter : true,
										enableHighlighting : true,
										totalServerItems : 'totalServerItems',
										pagingOptions : $scope.pagingOptions,
										selectedItems : $scope.selectedRows,
										filterOptions : $scope.filterOptions,
									};

						/** Fin de gestion Grid Vente BL */
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
