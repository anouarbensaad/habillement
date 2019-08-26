
'use strict'
angular
.module('gpro.PopUpElementBase', [])
.controller('MyCtrl', ['$scope', '$modal','$q','$http','baseUrlGs','baseUrl','$rootScope' ,'$filter',
                       function ($scope, $modal , $q ,$http , baseUrlGs, baseUrl,$rootScope, $filter) {

	$rootScope.typePopUp=[] ;

	var tmpSelectedItems	= [];
	$scope.entiteStockFournitureCourante = {};
	$scope.objetRechercher = {};

	$scope.whatYouSelect = [];	
	$scope.listeFamilleCache= [];
	$scope.listeSousFamilleCache = [];
	
	$scope.objectInter = {

    		referenceArticle : "",
    		designationArticle : "",
    		quantite : "",
    		quantiteReelle : "",
    		typeArticle : "",
    		type : "",
    		article : {
    			id : "",
    			familleArticleDesignation:""
    		}
    		
    	};
	
	var myWsData = [];

	$scope.isLoading=false;
	
	//Data from webservice  
	var dataProduitFromWs = function (id){
		//$scope.objetRechercher = entiteStockFourniture;
		$scope.objetRechercher.typeEntite = id;
		var deferred = $q.defer();
		$scope.isLoading=true;
		
		$http.post(
			baseUrlGs
			+ "/articleEntree/rechercheArticleEntreeMultiCritereFB",
			$scope.objetRechercher)
		.success(function(data,status){
			$scope.isLoading=false;
			deferred.resolve(data);
			
		})
		.error(function(data,status){
			$scope.isLoading=false;
			deferred.reject('Erreur into url '+data);
		})


		return deferred.promise;

	}




	//Decalration of modal pop up	
	var openModalAfterSuccess = function(myWsData, currentIdTypeArticle){
		var modalInstance = $modal.open({
			templateUrl: 'myModalContent.html',
			controller: ModalInstanceCtrl,
			resolve: {
				itemsToModal: function () {
                                       
		          	return myWsData;
		          },
		
				  currentIdTypeArticle: function () {
		            
		          	return currentIdTypeArticle;
		          }

      }		
  });		

	 //When  we click on ok button
	 modalInstance.result.then(function (selectedItem) {
        //$scope.whatYouSelect = selectedItem;
		//console.log("whatYouSelect "+JSON.stringify($scope.whatYouSelect, null , " ") );
	}, function () {
        //console.log('Modal dismissed at: ' + new Date());
    });


	};
	
    //Decalaration of modal pop up controller
    var ModalInstanceCtrl = function ($scope, $modalInstance,itemsToModal, currentIdTypeArticle) {
    	$scope.myCurrentList  = itemsToModal.articleEntree;
    	$scope.objectInter = {

    		referenceArticle : "",
    		designationArticle : "",
    		quantite : "",
    		quantiteReelle : "",
    		typeArticle : "",
    		type : "",
    		article : {
    			id : "",
    			familleArticleDesignation:""
    		}
    		
    	};
    	
    	   	
    	$scope.mouvementFournitureE = [];

        //grid definition
//        $scope.gridOptions = { 
//        	data: 'myCurrentList',
//        	showSelectionCheckbox: true,
//        	columnDefs: [
//        	{
//        		field: 'ref',
//        		displayName: 'Reference',
//        		width:"50%"
//        	}, 
//        	{
//        		field:'designation',
//        		displayName:'Designation',
//        		width:"50%",
//        	},
//        	{
//        		field: 'familleArticleDesignation',
//        		displayName:'Famille',
//        		width:"20%"
//        	},
//        	{
//        		field: 'pu', 
//        		displayName:'PU',
//        		width:"10%"
//        	}
//        	],
//        	selectedItems: tmpSelectedItems,
//        	pagingOptions: $scope.pagingOptions
//        };

	      // Liste de Famille cache
	      $scope.listeFamilleCacheFCT = function() {
	      	$http.get(baseUrl+ "/gestionnaireCache/listeFamilleArticleCache")
	      	.success(
	      		function(data) {

	      			$scope.listeFamilleCache =data;
	      			$scope.listeFamilleParIdArticle();
	      		});
	      }
	      

	   // Liste de Famille par type article
	      $scope.listeFamilleParIdArticle = function() {
	      	
	    	  $scope.listeFamilleCacheParCategorie=[];
	    	  
	    	  $scope.listeFamilleCacheParCategorie = $filter ('filter')($scope.listeFamilleCache, {idTypeArticle : currentIdTypeArticle});
	    	  
	    	  $scope.listeFamilleCacheParCategorieFiltree=$scope.listeFamilleCacheParCategorie;
	      }
	      
	      
	     
	      
	   // Liste de Sous Famille par famille sélectionnée
	      
	      $scope.listeSousFamilleFiltree = function(familleDesignation) {
	      	
	    	  $scope.listeFamilleCacheParCategorieFiltree = $filter ('filter')($scope.listeFamilleCacheParCategorie, {designation : familleDesignation});
	    	  
	      }
	      
		// Liste de Sous Famille cache
		$scope.listeSousFamilleCacheFCT= function() {
			$http.get(baseUrl+ "/gestionnaireCache/listeSousFamilleArticleCache")
			.success(
				function(data) {

					$scope.listeSousFamilleCache= data;
									});
		}

		$scope.listeFamilleCacheFCT();
		$scope.listeSousFamilleCacheFCT();
         //search PopUp :
		// Not used after applying angularJs filter for search
         $scope.rechercherArticle = function(entiteStockFournitureCourante) {
				$http
						.post( 
								baseUrl
										+ "/article/rechercheArticleMulticritere",
								entiteStockFournitureCourante)
						.success(
								function(resultat) {
									console.log(" --- myCurrentList" +JSON.stringify(resultat.articleValues,null, " "));

									$scope.myCurrentList = resultat.articleValues;
									//data, page,pageSize
									/*$scope.setPagingData($scope.myData,$scope.pagingOptions.currentPage,
																	   $scope.pagingOptions.pageSize										
																	   );
									*/
									//$scope.rechercheArticleForm.$setPristine();
									//$scope.displayMode = "rechercher";
									//$scope.displayAlert = "sleep";
								});
         }
         
         $scope.articleCourantrecherchePopup ={
        		 "familleEntite" :"",
        		 "sousFamilleEntite" : "",
        		 "ref": "",
        		 "designation":""
         }

         // Annulation de l'ajout
			$scope.annulerAjoutPopUp = function () {

				$scope.articleCourantrecherchePopup = {};
				$scope.listeFamilleCacheParCategorie = $filter ('filter')($scope.listeFamilleCache, {idTypeArticle : currentIdTypeArticle});
				$scope.listeFamilleCacheParCategorieFiltree=$scope.listeFamilleCacheParCategorie;
				
				//$scope.rechercherArticle({});
			}

         
         $scope.addSelectedArticle = function(article){
        	
        	 console.log("article.isChecked"+ article.isChecked);
        	 if(article.isChecked){
        		 tmpSelectedItems.push(article);
        	 }else{
        		 var index= tmpSelectedItems.indexOf(article);
        		 tmpSelectedItems.splice(index,1);
        	 }
        	 
        	 console.log("tmpSelectedItems"+ JSON.stringify(tmpSelectedItems, null, " "));
        	 
         }
         
		//ok popUp
		$scope.ok = function () {
			
			console.log("tmpSelectedItems"+ JSON.stringify(tmpSelectedItems, null, " "));
			$modalInstance.close(tmpSelectedItems);

			var TypeArticles= $rootScope.typePopUp ;
			$scope.objectInter = {

		    		referenceArticle : "",
		    		designationArticle : "",
		    		quantite : "",
		    		quantiteReelle : "",
		    		typeArticle : "",
		    		type : "",
		    		article : {
		    			"id" : "",
		    			"familleArticleDesignation":""
		    		}
		    		
		    	};
			
			switch (TypeArticles) {
				case "fourniture":
				
					for (var i = 0; i < tmpSelectedItems.length; i++) {
						console.log("tmpSelectedItems.length "+JSON.stringify(tmpSelectedItems, null , " ") );

						$scope.objectInter.article.ref = tmpSelectedItems[i].ref;
						$scope.objectInter.article.designation= tmpSelectedItems[i].designation;
						$scope.objectInter.article.familleArticleDesignation = tmpSelectedItems[i].familleArticleDesignation;
						$scope.objectInter.article.sousFamilleArtEntiteDesignation =tmpSelectedItems[i].sousFamille;						
						$scope.objectInter.article.pu = tmpSelectedItems[i].pu;
						
						$scope.objectInter.prixTotal = tmpSelectedItems[i].prixTotal;
						$scope.objectInter.typeArticle = 1;
						$scope.objectInter.idMagasin = $scope.idMagasinFourniture;
						$scope.objectInter.article.id =  tmpSelectedItems[i].id ;


						$scope.objectInter.typeElementFicheBesoin = 1;
						$scope.objectInter.type = 1;
						$rootScope.listArticlesFournituresES.push($scope.objectInter);
						console.log("$scope.objectInter"+ JSON.stringify($scope.objectInter,null," "));
						console.log("Liste "+JSON.stringify($rootScope.listArticlesFournituresES,null, "  ") );

						
						//Object create
				       $scope.objectInter = {
									       	referenceArticle : "",
									       	designationArticle : "",
									       	quantite : "",
									       	quantiteReelle : "",
									       	typeArticle : "",
									       	article : {
									       				id : "",
									       			},

	       									};
	   						}

	   				tmpSelectedItems.length = 0;
	   				break;

   				case "tissu":

				   for (var i = 0; i < tmpSelectedItems.length; i++) {
						/*console.log("--- tmpSelectedItems.length "+tmpSelectedItems.length );
						console.log("--- tmpSelectedItems : "+JSON.stringify(tmpSelectedItems, null , " ") );
				*/
					   	$scope.objectInter.article.ref = tmpSelectedItems[i].ref;
					   	$scope.objectInter.article.designation= tmpSelectedItems[i].designation;
					   	$scope.objectInter.article.familleArticleDesignation = tmpSelectedItems[i].famille;
					   	$scope.objectInter.article.sousFamilleArtEntiteDesignation =tmpSelectedItems[i].sousFamille;

					   	$scope.objectInter.quantite = tmpSelectedItems[i].prixTotal;
					   	$scope.objectInter.quantiteActuelle = tmpSelectedItems[i].pu;
					   	$scope.objectInter.quantiteReelle = tmpSelectedItems[i].qteR;
					   	$scope.objectInter.typeArticle = 2;
					   	$scope.objectInter.type = 2;
					   	$scope.objectInter.idMagasin = $scope.idMagasinFourniture;

					   	$scope.objectInter.article.id =  tmpSelectedItems[i].id ;
															//$scope.objectInter.idArticle =tmpSelectedItems[i].id;
															$scope.objectInter.typeElementFicheBesoin = 2;

															$scope.mouvementFournitureE[i] = $scope.objectInter;
															
															$scope.objectInter = {
																				referenceArticle : "",
																				designationArticle : "",
																				quantite : "",
																				quantiteReelle : "",
																				typeArticle : "",
																				article : {
																							id : "",
																							ref :"" ,
																							designation :"",
																							familleArticleDesignation :"",
																							sousFamilleArtEntiteDesignation : ""
																				}

															};
					}
					
					for (var i = 0; i <tmpSelectedItems.length; i++) {
						
						$rootScope.listArticlesTissusE.push($scope.mouvementFournitureE[i]);

					}

					tmpSelectedItems.length = 0;
					break;

						case "coudre":

									for (var i = 0; i < tmpSelectedItems.length; i++) {

										
										$scope.objectInter.article.ref = tmpSelectedItems[i].ref;
										$scope.objectInter.article.designation= tmpSelectedItems[i].designation;
										$scope.objectInter.article.familleArticleDesignation = tmpSelectedItems[i].famille;
										$scope.objectInter.article.sousFamilleArtEntiteDesignation =tmpSelectedItems[i].sousFamille;
										$scope.objectInter.quantite = tmpSelectedItems[i].prixTotal;
										$scope.objectInter.quantiteActuelle = tmpSelectedItems[i].pu;
										$scope.objectInter.quantiteReelle = tmpSelectedItems[i].qteR;
										$scope.objectInter.typeArticle = 3;
										$scope.objectInter.type = 3;

										$scope.objectInter.idMagasin = $scope.idMagasinFourniture;

										$scope.objectInter.article.id =  tmpSelectedItems[i].id ;
										//$scope.objectInter.idArticle =tmpSelectedItems[i].id;
										$scope.objectInter.typeElementFicheBesoin = 3;

										$scope.mouvementFournitureE[i] = $scope.objectInter;
										
										$scope.objectInter = {
															referenceArticle : "",
															designationArticle : "",
															quantite : "",
															quantiteReelle : "",
															typeArticle : "",
															article : {
																id : "",
															}
										};
									}
									for (var i = 0; i <tmpSelectedItems.length; i++) {
										
										$rootScope.listArticlesFACE.push($scope.mouvementFournitureE[i]);
									}

									tmpSelectedItems.length = 0;

									break;	
								}


							};

							$scope.cancel = function () {
								$modalInstance.dismiss('cancel');
							};
						};


						
						
	//this function will be called when we click on rechercher button
	$scope.open = function (typePopUp ) {
		$rootScope.typePopUp = typePopUp;
		
		switch (typePopUp) {
			case "fourniture":
			
  			
				//Here we have to call ws and in save the response into variable named	 myWsData
				dataProduitFromWs(1).then(
					function(data) {
			                    // promise fulfilled
			                    if (data) {
			                    	myWsData =  data
			                    ////console.log(" Test response " + JSON.stringify( myWsData, null,"  ") );
			                    //Call this function when sucess of web service	
			                    
			                    openModalAfterSuccess(myWsData, "1");

			                } else {

			                }
			            }, function(error) {
			                    // promise rejected, could log the error with: //console.log('error', error);

			                }

			                );
				


				break;
				case "tissu":
				//Here we have to call ws and in save the response into variable named	 myWsData
				dataProduitFromWs(2).then(
					function(data) {
				                    // promise fulfilled
				                    if (data) {
				                    ////console.log("LISTE   " + JSON.stringify(  data, null,"  ") );
				                    myWsData =  data
				                    //console.log(" Test response " + JSON.stringify( myWsData, null,"  ") );
				                   //Call this function when sucess of web service	
				                   openModalAfterSuccess(myWsData, "2");

				               } else {

				               }
				           }, function(error) {
				                    // promise rejected, could log the error with: //console.log('error', error);

				                }

				                );

				break;
				
				case "coudre":
				//Here we have to call ws and in save the response into variable named	 myWsData
				dataProduitFromWs(3).then(
					function(data) {
				                    // promise fulfilled
				                    if (data) {
				                    ////console.log("LISTE   " + JSON.stringify(  data, null,"  ") );
				                    myWsData =  data
				                    //console.log(" Test response " + JSON.stringify( myWsData, null,"  ") );
				                   //Call this function when sucess of web service	
				                   openModalAfterSuccess(myWsData, "3");
				               } else {

				               }
				           }, function(error) {
				                    // promise rejected, could log the error with: //console.log('error', error);

				                }

				                );

				break;	
			}



		};   

	}])
