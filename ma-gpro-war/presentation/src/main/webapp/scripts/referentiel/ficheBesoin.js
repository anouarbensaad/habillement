'use strict'
angular
.module('gpro.ficheBesoin', [])
.controller('FicheBesoinController',function($location,$scope,$http,downloadService,$routeParams,$log,$rootScope,baseUrl,baseUrlGc, $filter){

			var idProduit = $routeParams.idProduit;
			$log.debug("ID Produit = "+idProduit);

			$scope.ficheBesoinCourante = {};
			$scope.btnValider = false;
			$rootScope.listArticlesFournituresES = [];
			$rootScope.listArticlesTissusE = [];
			$rootScope.listArticlesFACE = [];
			$scope.idSelectionnee = [];
			$scope.listeElementBesoinsInitiale = [];
			$scope.listProduitDrop = [];

			var data;

			/***************************************************
			 * Gestion de la cache
			 **************************************************/
			$scope.listePhaseCache = function ()
			{

				$http.get(baseUrl+ "/phase/all")
				.success(
					function(data) {
						$log.debug("listePhaseCache : "+data.length);
						$scope.listePhaseCache = data;
											});

			}

			$scope.listePhaseCache ();

			// Liste des produits
			$scope.listeProduit = function() {
				$http.get(baseUrl+"/produit/all").success(
						function(dataProduit) {
							$scope.listProduitDrop = dataProduit;
						});
			}
			
			$scope.listeProduit();
			/***************************************************
			 * Slides Articles Entree *
			 **************************************************/
			 $scope.animateArticleFourniture = function() {
			 	$("#articles").click(
			 		function() {
			 			$scope.openOrClose(
			 				'panel-articles',
			 				'#articles', 'hidePlus');
			 		});
			 	$("#tissuBtn").click(
			 		function() {
			 			$scope.openOrClose('tissu',
			 				'#tissuBtn',
			 				'hidePlusTissu');
			 		});
			 	$("#filBtn").click(
			 		function() {
			 			$scope.openOrClose('fil',
			 				'#filBtn', 'hidePlusFil');
			 		});
			 	$("#fournituresBtn").click(
			 		function() {
			 			$scope.openOrCloseInv(
			 				'fournitures',
			 				'#fournituresBtn',
			 				'hidePlusFourniture');
			 		});
			 }
			 $scope.openOrClose = function(id_panel, id_div,
			 	classe) {
			 	$("div[id=" + id_panel + "]")
			 	.slideToggle("slow");
			 	$(id_div).toggleClass(classe);
			 	if ($(id_div).hasClass(classe)) {
			 		$(id_div).text('+');
			 	} else {
			 		$(id_div).text('-');
			 	}
			 }
			 $scope.openOrCloseInv = function(id_panel, id_div,
			 	classe) {
			 	$("div[id=" + id_panel + "]")
			 	.slideToggle("slow");
			 	$(id_div).toggleClass(classe);
			 	if ($(id_div).hasClass(classe)) {
			 		$(id_div).text('-');
			 	} else {
			 		$(id_div).text('+');
			 	}
			 }
			 $scope.animateArticleFourniture();

			 

				/*************** Notification *************************/
				
				$scope.hiddenNotif ="true";
				
				
				$scope.showNotif = function(){
					$scope.hiddenNotif ="false";
				}
				
				$scope.closeNotif = function(){
					$scope.hiddenNotif ="true";
				}
				
			/***************************************************
			 * Gestion Fiche Besoin *
			 **************************************************/
			//get fiche besoin  id produit
			$scope.getFichierBesoinByIdProduct =function(){
				$http.get(baseUrl+ "/ficheBesoins/produits/"+idProduit)
				.success(
					function(data) {
						$scope.ficheBesoinCourante = data;
						$scope.idFicheBesoin = data.idFicheBesoin;
						//Liste des recettes correspendantes à cette ficheBesoin
						if(data.elementBesoinValue != null){
							 
							 $scope.listeElementBesoinsInitiale = data.elementBesoinValue;
							 
	                        // subListes de elementBesoinValue: Fourniture -1, Tissu -2,Fil A  Coudre -3 
	                        angular.forEach(data.elementBesoinValue, function(elementFicheBesoin, key){
	                          
	                          var type = elementFicheBesoin.type;
	                          //$log.debug("--- TYPE elementFicheBesoin : "+ type);

	                           switch (type) {
	                                case '1':
	                                    $log.debug(" Fourniture ");
	                                    $rootScope.listArticlesFournituresES.push(elementFicheBesoin);
	                                    break;
	                                case '2':
	                                    $log.debug(" Tissu ");
	                                    $rootScope.listArticlesTissusE.push(elementFicheBesoin);
	                                    break;
	                                case '3':
	                                    $log.debug("Fil à Coudre");
	                                    $rootScope.listArticlesFACE.push(elementFicheBesoin);
	                                    break;
	                                    
	                                default:
	                                     $log.debug("elementFicheBesoin n'a pas encore de Type =>> " +type);
	                            }
	                          
	                        });

						}
						
						$scope.btnValider = true;

                        
					});
				// Animation des div 
				$scope.openOrClose('panel-articles','#articles', 'hidePlus');
				$scope.openOrClose('tissu', '#tissuBtn','hidePlusTissu');
				$scope.openOrClose('fil', '#filBtn','hidePlusFil');
			}
			
			$scope.getFichierBesoinByIdProduct();


			// Rechercher Article
			$scope.rechercherArticle = function(articleCourantrecherchePopup) {}
			
			$scope.rechercherArticle({});
		//Valider Fiche Similaire 
			
			//get fiche besoin  id produit
			$scope.validerFicheSimilaire =function(idProduit){
				$http.get(baseUrl+ "/ficheBesoins/produitsimilaire/"+idProduit)
				.success(
					function(data) {
//						var ficheBesoinC = data;
//						$scope.ficheBesoinC.idFicheBesoin = null;
//						$scope.ficheBesoinCourante = ficheBesoinC;
						//Liste des recettes correspendantes à cette ficheBesoin
						if(data.elementBesoinValue != null){
							 
							 $scope.listeElementBesoinsInitiale = data.elementBesoinValue;
							 
	                        // subListes de elementBesoinValue: Fourniture -1, Tissu -2,Fil A  Coudre -3 
	                        angular.forEach(data.elementBesoinValue, function(elementFicheBesoin, key){
	                          
	                          var type = elementFicheBesoin.type;
	                          //$log.debug("--- TYPE elementFicheBesoin : "+ type);
	                          elementFicheBesoin.idElementBesoinValue = null;
	                           switch (type) {
	                                case '1':
	                                    $log.debug(" Fourniture ");
	                                    $rootScope.listArticlesFournituresES.push(elementFicheBesoin);
	                                    break;
	                                case '2':
	                                    $log.debug(" Tissu ");
	                                    $rootScope.listArticlesTissusE.push(elementFicheBesoin);
	                                    break;
	                                case '3':
	                                    $log.debug("Fil à Coudre");
	                                    $rootScope.listArticlesFACE.push(elementFicheBesoin);
	                                    break;
	                                    
	                                default:
	                                     $log.debug("elementFicheBesoin n'a pas encore de Type =>> " +type);
	                            }
	                          
	                        });
	                        

						}
                        
					});
				// Animation des div 
				$scope.openOrClose('panel-articles','#articles', 'hidePlus');
				$scope.openOrClose('tissu', '#tissuBtn','hidePlusTissu');
				$scope.openOrClose('fil', '#filBtn','hidePlusFil');
			}
			
			//creer fiche besoin Produit
			$scope.creerOuModifierFicheBesoin = function(ficheBesoin) {

				//Tmp listeElementFicheBesoinTmp
	              var listeElementFicheBesoinTmp = []; 

	              //listeTraitementsMise: Teinture Finissage, Preparation
	              angular.forEach($rootScope.listArticlesFournituresES, function(elementArticleFourniture, key){
	                  
	                  listeElementFicheBesoinTmp.push(elementArticleFourniture);
	                  
	              });

	              angular.forEach($rootScope.listArticlesTissusE, function(elementArticleTissu, key){
	              
	                listeElementFicheBesoinTmp.push(elementArticleTissu);
	              
	              });

	              
	              angular.forEach($rootScope.listArticlesFACE, function(elementArticleFilACoudre, key){
	              
	                listeElementFicheBesoinTmp.push(elementArticleFilACoudre);
	              
	              });
			
				ficheBesoin.elementBesoinValue = listeElementFicheBesoinTmp ;

				console.log("Object to send "+JSON.stringify(ficheBesoin,null, "  ") );

				$http   .post(baseUrl+ "/ficheBesoins/",ficheBesoin)
						.success(
							function(id) {
								$log.debug("success Creation Fiche Besoin " + id );
								//$scope.retour();
								$scope.showNotif();	
								$scope.ficheBesoinCourante.idFicheBesoin = id;

								});
	}

	//generer rapport apres creation d'une fiche de Besoin. 
		$scope.download = function() {
		console.log("-- idProduit"+ idProduit);
		var url = baseUrl+"/reportCommun/getBesoinProduit?produitId=" + idProduit+"&type=pdf";
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

			// 	Retour Produit Page		
			$scope.retour = function (){
				
				$location.$$search = {};
				$rootScope.ITEM='produit';
				$location.path("/ElementBase");
				
			}
			
			
			$scope.deleteItem = function(index, type){
				
				if(type == "fourniture"){
					$rootScope.listArticlesFournituresES.splice(index, 1);
				}
				else if(type == "tissue"){
					$rootScope.listArticlesTissusE.splice($scope.index, 1);
				}
				else if(type == "fil"){
					$rootScope.listArticlesFACE.splice($scope.index, 1);
				}
				
			}
			

		});