

'use strict'
angular
.module('gpro.popupDetailProduitBLVente', [])
.controller('DetailProduitLivraisonVenteController', ['$rootScope','$scope', '$modal', '$bootbox', '$log','$filter','baseUrl','$http','$routeParams','popCouleurTailleVenteService' ,
	function ($rootScope,$scope, $modal, $bootbox, $log,$filter,baseUrl,$http, $routeParams,popCouleurTailleVenteService) {
		$log.info("----------- MyCtrlVente BC ----------");
										
		$scope.items = ['item1', 'item2', 'item3'];
		
		$scope.listeColor=[];
		$scope.listeTaille =[];
		
		var itemTab = [];
		var matrix = [];
		//detailProduitCommande a affecté a produitCommande
		$rootScope.maGproDetailPrCmd = [];
		//recuperation de detailList 
		$rootScope.maGprogetMatrixEdit = [];
		$rootScope.demoQuantite = 0;
		$rootScope.maGprotapedValue = [];

		$rootScope.listXColorPopUpPromise = [];
		$rootScope.listXTaillePopUpPromise = [];


		$scope.open = function (size,indexLigne,listProduitCommande,mode) {
			var modalInstance = $modal.open({
				templateUrl: 'couleurPopUp.html',
				controller: function ($scope, $modalInstance, items) {

					//idProduit
					$rootScope.maGprogetMatrixEdit = [];
					$scope.idProduit = listProduitCommande[indexLigne].produitId;
					
					if(listProduitCommande[indexLigne].listDetailsProduitLivraison != null){
					$log.debug( "CASE Detail : 1 =>> listDetail Pleinne : updateListeDetail");
						$rootScope.maGprogetMatrixEdit = listProduitCommande[indexLigne].listDetailsProduitLivraison;

					}else{
						$log.debug( "CASE Detail : 2 =>> listDetail Vide : createListeDetail"); // nouvelle insertion ds le tableau
					}
					
					$scope.items = items;
					$scope.selected = {
						item: $scope.items[0]
					};

					////$log.debug (" listProduitCommande selectionne  : "+JSON.stringify(listProduitCommande[indexLigne], null, "  ") );
					$scope.addNewElement = function(event, myValue,indexTD, indexTR, taille, couleur){

						//$log.debug(" beforeAdd : itemTab : "+JSON.stringify(itemTab,null,"  ")) ;
						var elementObjet = {
							tailleId   : taille.id,
							couleurId   : couleur.id,
							quantite  : myValue.replace(/\s+/g, '')
						};	

						if((myValue==="") && (itemTab.length!==0)){
							$log.debug( "CASE ADD : 1 =>> delete Item");
							for(var i=0;i<itemTab.length;i++){
								if((elementObjet.tailleId===itemTab[i].tailleId) && (elementObjet.couleurId===itemTab[i].couleurId)){
									itemTab.splice(i,1); //delete Item
								}//end if exist 
							}//end for
						}else{
							$log.debug( "CASE ADD : 2");
							//$log.debug("ELSE 1");
							if((itemTab.length===0)&&(myValue!=='')){
								$log.debug( "CASE ADD : 2 -A =>> new Item");
								//$log.debug("A "+JSON.stringify(elementObjet,null,"  ")) ;
								itemTab.push(elementObjet); // new Item
							}			
							else{
								$log.debug( "CASE ADD : 2 -B =>> Update Or Create"); //Update item ; x,y => new qte 
								//$log.debug("B T "+JSON.stringify(elementObjet.taille.id,null,"  ")) ;
								//$log.debug("B C "+JSON.stringify(elementObjet.taille.id,null,"  ")) ;
								var exist=false;
								for(var i=0;i<itemTab.length;i++){
									if((elementObjet.tailleId===itemTab[i].tailleId) && (elementObjet.couleurId ===itemTab[i].couleurId)){
										$log.debug( "CASE ADD : 2 -B -1 =>> Update");
										itemTab[i].quantite =  myValue.replace(/\s+/g, '');
										//$log.debug("D "+JSON.stringify(itemTab[i],null,"  ")) ;
										exist=true;
									}//end if exist 
								}//end for
							if(exist===false){
								$log.debug( "CASE ADD : 2 -C =>> Create"); // Create Item
								itemTab.push(elementObjet);	
							}
						}//end else 						
					}		

					$log.debug(" ngKeyUp itemTab : " + JSON.stringify(itemTab, null ,"  ") );
					$log.debug(" ngKeyUp MATRIX : " + JSON.stringify(matrix, null ,"  ") );
				   matrix = itemTab;
				   $log.debug(" ngKeyUp MATRIX: " + JSON.stringify(matrix, null ,"  ") );
				   $log.debug(" ----------------------------- " );
				}

				var tab = [];
				popCouleurTailleVenteService.getListCouleurProduit(baseUrl, $scope.idProduit).then(
					function(listCouleurParPod){
						$scope.listeColor  =  listCouleurParPod;
						////$log.debug("-- listeColor "+JSON.stringify(listCouleurParPod,null,"  "));

						popCouleurTailleVenteService.getListTailleProduit(baseUrl, $scope.idProduit).then(
							function(listTailleParProd){
								$scope.listeTaille =   listTailleParProd;

								////$log.debug("-- listeTaille "+JSON.stringify(listTailleParProd,null,"  "));
								////$log.debug(">>>>>>>> AAAA: " +$scope.listeColor.length+" ROWS : "+$scope.listeTaille.length);
								
								//Init Matrix 
								var tab = [];
								
								$log.debug("xxxxxxxxxxxxxxxxxx listProduitCommande[indexLigne].id : " +listProduitCommande[indexLigne].id);
								if(angular.isDefined(listProduitCommande[indexLigne].id)){
									$log.debug( "CASE ID : A ");
									//mode modification
									if(listProduitCommande[indexLigne].id != ""){
										$log.debug( "CASE ID A: 1 =>> Tab Plein ===>> Mode Consultation --edition");
										tab = listProduitCommande[indexLigne].listDetailsProduitLivraison;
										$log.debug( "=================>> before "+JSON.stringify($rootScope.maGproDetailPrCmd,null, "  "));
										$rootScope.maGproDetailPrCmd = tab;
										$log.debug( "=================>> after "+JSON.stringify($rootScope.maGproDetailPrCmd,null, "  "));

										$log.debug( "UPDATE  TAB >>>>> :" + JSON.stringify(tab,null, "  ") );


									}else{
										$log.debug( "CASE ID A: 2");
									
									}
									
								}else{
									$log.debug( "CASE ID : B");

								var listCouleurLength = $scope.listeColor.length;
								var listTailleLength = $scope.listeTaille.length;
								var elementNbr = listCouleurLength * listTailleLength;

										var colone = 0;
										var ligne = 0;
										var nbrElement = 0;

										//setQte si nouvelle creation => qte Vide else qte prend les valeurs deja attribués
										if($rootScope.maGprogetMatrixEdit.length == 0){
											$log.debug( "CASE ID B: 1 =>> Tab Vide ===>> Mode Nouvelle Creation");
											//$log.debug("mode Creation Tab Vide");
												for(var colTailleTmp=0;colTailleTmp<listTailleLength;colTailleTmp++){
													for(var ligneCouleurTmp = 0;ligneCouleurTmp<listCouleurLength;ligneCouleurTmp++){

														$scope.tabInt = {"couleurId":'',"tailleId":'',"quantite":'',"produitCommandeId":''};
														
														$scope.tabInt.tailleId = $scope.listeTaille[colTailleTmp].id;
														$scope.tabInt.couleurId = $scope.listeColor[ligneCouleurTmp].id;
														$scope.tabInt.quantite = "";
														//setQte si nouvelle creation => qte Vide else qte prend les valeurs deja attribués
														
														tab.push($scope.tabInt);
													}

												}
											$log.debug( "CREATE TAB B1 >>>>> :" + JSON.stringify(tab,null, "  ") );
										
										}else{
										
											$log.debug( "**** CASE  B : 2 =>> Tab Plein ===>> Mode Consultation ");
											//$log.debug("mode Consultation Tab Plein");
											tab = $rootScope.maGprogetMatrixEdit;
											$log.debug( "=================>> before "+JSON.stringify($rootScope.maGproDetailPrCmd,null, "  "));
											$rootScope.maGproDetailPrCmd = $rootScope.maGprogetMatrixEdit;
											$log.debug( "=================>> after "+JSON.stringify($rootScope.maGproDetailPrCmd,null, "  "));

											$log.debug( "CREATE TAB>>>>> :" + JSON.stringify(tab,null, "  ") );
										}
										

								}

								//Affectation Quantite
								
								$rootScope.demoQuantite = function(couleurId, tailleId){
									//$log.debug( "DEMO  Tab :" + JSON.stringify(tab,null, "  ") );
									var quantite = null;									

									angular.forEach(tab, function(tabValue, key){
										if(couleurId == tabValue.couleurId && tailleId == tabValue.tailleId){
											//$log.debug( "CASE Demo : 1");	
											quantite = tabValue.quantite;
												
										}else{
											//$log.debug( "CASE Demo : 2");
										}
										
										
									});
									return quantite;
								}




							})		
					});

				////$log.debug(">>>>>>>> BBBB : " +JSON.stringify($scope.listeColor+" ROWS : "+ $scope.listeTaille );

				
				$scope.ok = function () {
					$log.debug("validation ");
					
					
					$log.debug('>>>>>>>MATRIX '+JSON.stringify(matrix,null,"  "));
					//$log.debug('>>>>>>> maGproDetailPrCmd BEFORE '+JSON.stringify($rootScope.maGproDetailPrCmd,null,"  "));
					$log.debug('>>>>>>> maGprogetMatrixEdit '+JSON.stringify($rootScope.maGprogetMatrixEdit,null,"  "));

					//update insert
					var quantite = null;									

					if($rootScope.maGproDetailPrCmd.length != 0){
						var trouve=false;
						//$log.debug("NON VIDE : parcour Matrix ");
						$log.debug("case Validation 1 ");
						for ( var i=0 ;i<$rootScope.maGproDetailPrCmd.length ; i++){
							for ( var j=0 ;j<matrix.length ; j++){
								if((matrix[j].couleurId == $rootScope.maGproDetailPrCmd[i].couleurId) &&(matrix[j].tailleId == $rootScope.maGproDetailPrCmd[i].tailleId)){
									$log.debug("if  : M C : "+matrix[j].couleurId +" = L C : "+ $rootScope.maGproDetailPrCmd[i].couleurId+" M T : " +matrix[j].tailleId+"=  L T :" +$rootScope.maGproDetailPrCmd[i].tailleId);
									$rootScope.maGproDetailPrCmd[i].quantite = matrix[j].quantite;

									$log.debug("--Before S P L I C E --" +JSON.stringify(matrix,null,"  "));
									
									//supprimer element de la matrice
									matrix.splice(j,1);

									$log.debug("--After S P L I C E --" +JSON.stringify(matrix,null,"  "));
									

									trouve = true;
									$log.debug("--Trouve TRUE--" +trouve);
								}
							}
							$log.debug("------------------");
							
						}

						$log.debug("--MATRIX Restante --" +JSON.stringify(matrix,null,"  "));
						
						//if(trouve == false){
							$log.debug("--Trouve FALSE--" +trouve);
							if(matrix.length != 0){
								$log.debug("--P U S H --" +JSON.stringify(matrix,null,"  "));
								var tmpNewListMatrix = [];
								for ( var i=0 ;i<matrix.length ; i++){

									$rootScope.maGproDetailPrCmd.push({
						            			tailleId:matrix[i].tailleId,
						            			couleurId:matrix[i].couleurId,
						            			quantite:matrix[i].quantite,
						            			
			                           });
								}
							}else{
								$log.debug("Matrix Vide! nothing to insert");
							}
							
						//}
							$log.debug("========================================");
					}else{
						$log.debug("case Validation 2 ");
						//$log.debug("VIDE : affectation Matrix");
						$rootScope.maGproDetailPrCmd = matrix; 
					}
					

					$log.debug('------> maGproDetailPrCmd AFTER '+JSON.stringify($rootScope.maGproDetailPrCmd,null,"  "));

					//detailProduitCommande
					var tmpListMatrix = [];
					for ( var i=0 ;i<$rootScope.maGproDetailPrCmd.length ; i++){

	            		tmpListMatrix.push({
	            			tailleId:$rootScope.maGproDetailPrCmd[i].tailleId,
	            			couleurId:$rootScope.maGproDetailPrCmd[i].couleurId,
	            			quantite:$rootScope.maGproDetailPrCmd[i].quantite,
	            			produitCommandeId:''
			                           });
	            	
						listProduitCommande[indexLigne].listDetailsProduitLivraison = tmpListMatrix; 
					};

					$log.debug('VALIDATION listDetail =>>  '+JSON.stringify(listProduitCommande[indexLigne].listDetailsProduitLivraison,null,"  "));

					//vider list de qui recupere la matrix 
					//$rootScope.maGproDetailPrCmd = [];
					$modalInstance.close($scope.selected.item);
				};		    

				$scope.cancel = function () {
					$modalInstance.dismiss('cancel');
				};
			},
			size: size,
			resolve: {
				items: function () {
					return $scope.items;
				}
			}
		});

			modalInstance.result.then(function (selectedItem) {
				$scope.selected = selectedItem;
			}, function () {
				$log.info('Modal dismissed at: ' + new Date());
			});
		};

		$scope.openDemoModal = function (size) {
			var modalInstance = $modal.open({
				templateUrl: 'demoModalContent.html',
				controller: function ($scope, $modalInstance) {
					$scope.cancel = function () {
						$modalInstance.dismiss('cancel');
					};
				},
				size: size,
			});
		};





	}])



.factory('popCouleurTailleVenteService', popCouleurTailleVenteService);

function popCouleurTailleVenteService($http,$q){


	var responseObject = {
		getListCouleurProduit: getListCouleurProduit,
		getListTailleProduit : getListTailleProduit

	};

	return responseObject;


	function getListCouleurProduit(baseUrl,idProduit){
	  		//var deferred = $q.defer();	
	            // Liste de couleur cache
	            var deferred = $q.defer();
	            $http.get(baseUrl+"/couleur/CouleurParProduit:"+idProduit)
	            .success(
	            	function(data) {


	            		deferred.resolve(data);

	            	})
	            .error(function(data,status){
	            	deferred.reject('Erreur into url '+data);
	            });

	            return deferred.promise;
		}//End function




		function getListTailleProduit(baseUrl, idProduit){
			// Liste de taille cache
			var deferred = $q.defer();
			$http.get(baseUrl+"/taille/TailleParProduit:"+idProduit)
			.success(
				function(data) {
					deferred.resolve(data);

				}).error(function(data,status){
					deferred.reject('Erreur into url '+data);
				});

				return deferred.promise;
		}//End function


	}  
