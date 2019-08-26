

'use strict'
angular
.module('gpro.PopupCouleurElementBase', [])
.controller('ModalsDemoController', ['$rootScope','$scope', '$modal', '$bootbox', '$log','baseUrl','$http','$routeParams','popCouleurTailleService' ,
	function ($rootScope,$scope, $modal, $bootbox, $log,baseUrl,$http, $routeParams,popCouleurTailleService) {
		$scope.items = ['item1', 'item2', 'item3'];
		$scope.listeColor=[];
		$scope.listeTaille =[];

		var itemTab = [];
		var matrix = [];
		$rootScope.xArticleFicheBesoin = {} ;
		var idProduit = $routeParams.idProduit;
		console.log ($routeParams.idProduit);


		$scope.open = function (size,indexLigne,listArticlesFournituresES) {
			var modalInstance = $modal.open({
				templateUrl: 'couleurPopUp.html',
				controller: function ($scope, $modalInstance, items) {
					$scope.items = items;
					$scope.selected = {
						item: $scope.items[0]
					};
         //Call here
					
//		console.log("listArticlesFournituresES"+ JSON.stringify(listArticlesFournituresES, null, " "));
         
         //  View Detail
         $rootScope.xArticleFicheBesoin.ref = listArticlesFournituresES[indexLigne].article.ref;
         $rootScope.xArticleFicheBesoin.designation = listArticlesFournituresES[indexLigne].article.designation;
         $rootScope.xArticleFicheBesoin.familleArticleDesignation= listArticlesFournituresES[indexLigne].article.familleArticleDesignation;
         $rootScope.xArticleFicheBesoin.sousFamilleArtEntiteDesignation = listArticlesFournituresES[indexLigne].article.sousFamilleArtEntiteDesignation;
         $rootScope.xArticleFicheBesoin.type =  listArticlesFournituresES[indexLigne].type;
         console.log (" ----article selectionne  : "+JSON.stringify(listArticlesFournituresES[indexLigne], null, "  ") );
       
         $scope.addNewElement = function(event, myValue,indexTD, indexTR, taille, couleur){

         	var elementObjet = {
         		taille    : taille,
         		couleur   : couleur,
         		quantite  : myValue.replace(/\s+/g, '')
         	};	

         	if((myValue==="") && (itemTab.length!==0)){
         		console.log("IF 1");
         		for(var i=0;i<itemTab.length;i++){
         			if((elementObjet.taille.id===itemTab[i].taille.id) && (elementObjet.couleur.id===itemTab[i].couleur.id)){
         				itemTab.splice(i,1);
								}//end if exist 
							}//end for
						}else{
							console.log("ELSE 1");
							if((itemTab.length===0)&&(myValue!=='')){
								console.log("A 1");
								itemTab.push(elementObjet);
							}			
							else{
								var exist=false;
								for(var i=0;i<itemTab.length;i++){
									if((elementObjet.taille.id===itemTab[i].taille.id) && (elementObjet.couleur.id===itemTab[i].couleur.id)){
										itemTab[i].quantite =  myValue.replace(/\s+/g, '');
										exist=true;
								}//end if exist 
							}//end for
							if(exist===false)
								itemTab.push(elementObjet);	
						}//end else 						
					}		

					console.log(" Matrice : " + JSON.stringify(itemTab, null ,"") );
				   matrix = itemTab;//JSON.stringify(itemTab, null ,"");





				}





				popCouleurTailleService.getListCouleurProduit(idProduit,baseUrl).then(
					function(listCouleurParPod){
						$scope.listeColor  =  listCouleurParPod;


						popCouleurTailleService.getListTailleProduit(idProduit,baseUrl).then(
							function(listTailleParProd){
								$scope.listeTaille =   listTailleParProd;


							}
							)		
							}//End function 1
							);

				$scope.ok = function () {
					$modalInstance.close($scope.selected.item);
					console.log('Valider xArticle '+ JSON.stringify($rootScope.xArticleFicheBesoin,null," "));
		            if($rootScope.xArticleFicheBesoin.type == 1){
		            	console.log("-----------Type Fourniture");
		            	var tmpListeFo = [ ];
			            var articleTmp =  {
			            	ref :$rootScope.listArticlesFournituresES[indexLigne].article.ref,
			            	designation: $rootScope.listArticlesFournituresES[indexLigne].article.designation,
			              	familleArticleDesignation : $rootScope.listArticlesFournituresES[indexLigne].article.familleArticleDesignation ,
			           		sousFamilleArtEntiteDesignation :  $rootScope.listArticlesFournituresES[indexLigne].article.sousFamilleArtEntiteDesignation,

			            	id : $rootScope.listArticlesFournituresES[indexLigne].article.id 
			            };
			            if (matrix.length >0)
			            {
			            	for ( var i=0 ;i<matrix.length ; i++){


			            		tmpListeFo.push({
			            			eb_taille_id:matrix[i].taille.id,
			            			eb_couleur_id:matrix[i].couleur.id,
			            			designationTaille:matrix[i].taille.designation,
			            			designationCouleur:matrix[i].couleur.designation,
			            			quantite:matrix[i].quantite,
			            			global:true,
			            			ordre:"22",
			            			typeArticle : 1,
			           				type : 1,
			                 		article : articleTmp
					                               // listArticlesFournituresES[$index].article.quantite
					                           });
			            	}
			            	$rootScope.listArticlesFournituresES[indexLigne] = tmpListeFo[0];
			            	if(tmpListeFo.length>0){
			            		$rootScope.listArticlesFournituresES[indexLigne] = tmpListeFo[0];
			            		for(var s=1;s<tmpListeFo.length;s++)
			            			$rootScope.listArticlesFournituresES[$rootScope.listArticlesFournituresES.length++] = tmpListeFo[s];
			            	}

			            }
		            }else if($rootScope.xArticleFicheBesoin.type == 2){
		            	console.log("-----------Type Tissu");
		            	var tmpListeFo = [ ];
			            var articleTmp =  {
			            	ref :$rootScope.listArticlesTissusE[indexLigne].article.ref,
			            	designation: $rootScope.listArticlesTissusE[indexLigne].article.designation,
			              	familleArticleDesignation : $rootScope.listArticlesTissusE[indexLigne].article.familleArticleDesignation ,
			           		sousFamilleArtEntiteDesignation :  $rootScope.listArticlesTissusE[indexLigne].article.sousFamilleArtEntiteDesignation,

			            	id : $rootScope.listArticlesTissusE[indexLigne].article.id 
			            };
			            if (matrix.length >0)
			            {
			            	for ( var i=0 ;i<matrix.length ; i++){


			            		tmpListeFo.push({
			            			eb_taille_id:matrix[i].taille.id,
			            			eb_couleur_id:matrix[i].couleur.id,
			            			designationTaille:matrix[i].taille.designation,
			            			designationCouleur:matrix[i].couleur.designation,
			            			quantite:matrix[i].quantite,
			            			global:true,
			            			ordre:"22",
			            			typeArticle : 2,
			           				type : 2,
			                 		article : articleTmp
					                               
					                           });
			            	}
			            	$rootScope.listArticlesTissusE[indexLigne] = tmpListeFo[0];
			            	if(tmpListeFo.length>0){
			            		$rootScope.listArticlesTissusE[indexLigne] = tmpListeFo[0];
			            		for(var s=1;s<tmpListeFo.length;s++)
			            			$rootScope.listArticlesTissusE[$rootScope.listArticlesTissusE.length++] = tmpListeFo[s];
			            	}

			            }
		            }else if ($rootScope.xArticleFicheBesoin.type == 3){
		            	console.log("-----------Type Fil A Coudre");
		            	var tmpListeFo = [ ];
			            var articleTmp =  {
			            	ref :$rootScope.listArticlesFACE[indexLigne].article.ref,
			            	designation: $rootScope.listArticlesFACE[indexLigne].article.designation,
			              	familleArticleDesignation : $rootScope.listArticlesFACE[indexLigne].article.familleArticleDesignation ,
			           		sousFamilleArtEntiteDesignation :  $rootScope.listArticlesFACE[indexLigne].article.sousFamilleArtEntiteDesignation,

			            	id : $rootScope.listArticlesFACE[indexLigne].article.id 
			            };
			            if (matrix.length >0)
			            {
			            	for ( var i=0 ;i<matrix.length ; i++){


			            		tmpListeFo.push({
			            			eb_taille_id:matrix[i].taille.id,
			            			eb_couleur_id:matrix[i].couleur.id,
			            			designationTaille:matrix[i].taille.designation,
			            			designationCouleur:matrix[i].couleur.designation,
			            			quantite:matrix[i].quantite,
			            			global:true,
			            			ordre:"22",
			            			typeArticle : 3,
			           				type : 3,
			                 		article : articleTmp
					                           });
			            	}
			            	$rootScope.listArticlesFACE[indexLigne] = tmpListeFo[0];
			            	if(tmpListeFo.length>0){
			            		$rootScope.listArticlesFACE[indexLigne] = tmpListeFo[0];
			            		for(var s=1;s<tmpListeFo.length;s++)
			            			$rootScope.listArticlesFACE[$rootScope.listArticlesFACE.length++] = tmpListeFo[s];
			            	}

			            }
		            }
            

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



.factory('popCouleurTailleService', popCouleurTailleService);

function popCouleurTailleService($http,$q){


	var responseObject = {
		getListCouleurProduit: getListCouleurProduit,
		getListTailleProduit : getListTailleProduit

	};

	return responseObject;


	function getListCouleurProduit(idProduit,baseUrl){

	  		//var deferred = $q.defer();	
	            // Liste de couleur cache
	            var deferred = $q.defer();
	            $http.get(baseUrl+ "/couleur/CouleurParProduit:"+ idProduit )
	            .success(
	            	function(data) {


	            		deferred.resolve(data);

	            	})
	            .error(function(data,status){
	            	deferred.reject('Erreur into url '+data);
	            });

	            return deferred.promise;
		}//End function




		function getListTailleProduit(idProduit,baseUrl){
			// Liste de taille cache
			var deferred = $q.defer();
			$http.get(baseUrl+ "/taille/TailleParProduit:" + idProduit)
			.success(
				function(data) {
					deferred.resolve(data);

				}).error(function(data,status){
					deferred.reject('Erreur into url '+data);
				});

				return deferred.promise;
		}//End function


	}  
