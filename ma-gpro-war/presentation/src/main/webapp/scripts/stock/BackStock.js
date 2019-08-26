'use strict'
angular.module('gpro.back', [ "ngResource" ])
.controller('backStockCtrl',[ '$scope', '$filter', '$http','$log', 'baseUrlGs',
	function($scope, $filter, $http,$log, baseUrlGs,baseUrl) {
			/** ***Liste desVariables **** */
			$scope.parametre = "Magasin";
	}])
	.controller('backMagasinController', ['$scope','$filter', '$http','baseUrlGs','baseUrl', function ($scope,$filter, $http, baseUrlGs,baseUrl) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.magasinCourante = null;
	    $scope.listeMagasin=[];
		$scope.listeSites=[];
	    $scope.resultatRecherche=$scope.listeMagasin;
	    /********************
	     * Gestion de la  magasin Produit
	     *********************************/
	    $scope.siteChoisi = [];
	    $scope.addSite = function(index){
	    	  $scope.listeMagasin[index].piComSiteId = $scope.siteChoisi[$index].piComSiteId;
	    }
	    //Lister magasin  
	    $scope.listeMagasin = function () {
	    	$http.get(baseUrlGs+"/magasin/all").success(function (data) {
	    		$scope.listeMagasin = data;
	    	});
	    }
	     $scope.listeMagasin();
	     //Lister sites  
		    $scope.listeSites = function () {
		    	$http.get(baseUrl+"/gestionnaireCache/listeSitePartieInteresseeCache").success(function (data) {
		    		$scope.listeSites = data;
		    	});
		    }
		     $scope.listeMagasin();
		     $scope.listeSites();
		     
				// GetId.designation
				$scope.type = {

					status : ''
				};

				$scope.showStatus = function(id) {

					$scope.type.status = id;
					var selected = $filter('filter')(
							$scope.listeSites, {
								id : $scope.type.status
							});

					if ($scope.type.status && selected.length) {
						return selected[0].nom;
					} else {
						return "Not Set";
					}
				};
	     
	     // ajout d'un couleur
		    $scope.ajouterMagasin = function() {
		      $scope.magasinCourante = {
		        designation: '',
		      };
		      $scope.listeMagasin.push($scope.magasinCourante);
		      
		    };
		   
	
	   //Enregistrer Magasin
	  $scope.saveMagasin = function(data, id) {
		  if (angular.isDefined(id)) {
			     $log.debug(data);
			     $http.post(baseUrlGs + "/magasin/modifierMagasin", data)
			     .success(function (newMagasin) {
			    	angular.extend(newMagasin);
			     });
	        } else {
	        	$log.debug(data);
			     $http.post(baseUrlGs + "/magasin/creerMagasin", data)
			     .success(function (newMagasin) {
				    	angular.extend(newMagasin);
			     });
	        }
		  }
	   // Suppression Magasin produit
	    $scope.removeMagasinProduit = function (id) {
	    	 alert(""+id);
        	$http({
 	    		method: "DELETE",
 	    		url: baseUrlGs + "/magasin/supprimerMagasin:"+id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
	    }

   }
  ])
   .controller('backEmplacementController', ['$scope','$filter', '$http','baseUrlGs', function ($scope,$filter, $http, baseUrlGs) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.emplacementCourante = null;
	    $scope.listeEmplacement=[]; 
	    $scope.listeEmplacementMagasin=[]; 
	    $scope.resultatRecherche=$scope.listeEmplacement;
	    /********************
	     * Gestion de la  emplacement 
	     *********************************/
	    
	    //Lister emplacement  
	    $scope.listeEmplacement = function () {
	    	$http.get(baseUrlGs+"/emplacement/all").success(function (dataMagasin) {
	    		$scope.listeEmplacement = dataMagasin;
	    	});
	    }
	    $scope.listeEmplacement();
	  //Lister magasin  
	    $scope.listeEmplacementMagasin = function () {
	    	$http.get(baseUrlGs+"/magasin/all").success(function (data) {
	    		$scope.listeEmplacementMagasin = data;
	    	});
	    }
	    $scope.listeEmplacementMagasin();
	    
		//GetId.designation
	  	    $scope.magasin = {
	  	    	    status: ''
	  	    	  }; 
	  	    	$scope.showStatus = function(id) {
					$scope.magasin.status = id;
					var selected = $filter('filter')(
							$scope.listeEmplacementMagasin, {
								id : $scope.magasin.status
							});
					if ($scope.magasin.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				};
	 
	  	    	  
	     // ajout d'un emlacement
		    $scope.ajouterEmplacement = function() {
		      $scope.emplacementCourante = {
		        designation: '',
		        magasin :'',
		      };
		      $scope.listeEmplacement.push($scope.emplacementCourante);
		      
		    };
		   
	
	   //Enregistrer emplacement
	  $scope.saveEmplacement = function(data, id) {
		  if (angular.isDefined(id)) {
			     $log.debug(data);
			     $log.debug("id  modifier "+id);
			     $http.post(baseUrlGs + "/emplacement/modifierEmplacement", data)
			     .success(function (newEmplacement) {
			    	angular.extend(newEmplacement);
			     });
	        } else {
	        	 $log.debug("id ajouter "+id);
	        	 $log.debug(data);
			     $http.post(baseUrlGs + "/emplacement/creerEmplacement", data)
			     .success(function (newEmplacement) {
				    	angular.extend(newEmplacement);
			     });
	        }
		     
		  }
		    
	  
	   // Suppression emplacement 
	    $scope.removeEmplacement = function (id) {
        	$http({
 	    		method: "DELETE",
 	    		url: baseUrlGs + "/emplacement/supprimerEmplacement:"+$scope.listeEmplacement[id].id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
        	$scope.listeEmplacement.splice(id, 1);

	    }
   	 $scope.listeEmplacement();
   }
  ])
   .controller('backEmplacementController', ['$scope','$filter', '$http','baseUrlGs', function ($scope,$filter, $http, baseUrlGs) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.emplacementCourante = null;
	    $scope.listeEmplacement=[]; 
	    $scope.listeEmplacementMagasin=[]; 
	    $scope.resultatRecherche=$scope.listeEmplacement;
	    /********************
	     * Gestion de la  emplacement 
	     *********************************/
	    
	    //Lister emplacement  
	    $scope.listeEmplacement = function () {
	    	$http.get(baseUrlGs+"/emplacement/all").success(function (dataMagasin) {
	    		$scope.listeEmplacement = dataMagasin;
	    	});
	    }
	    $scope.listeEmplacement();
	  //Lister magasin  
	    $scope.listeEmplacementMagasin = function () {
	    	$http.get(baseUrlGs+"/magasin/all").success(function (data) {
	    		$scope.listeEmplacementMagasin = data;
	    	});
	    }
	    $scope.listeEmplacementMagasin();
	    
		//GetId.designation
	  	    $scope.magasin = {
	  	    	    status: ''
	  	    	  }; 
	  	    	$scope.showStatus = function(id) {
					$scope.magasin.status = id;
					var selected = $filter('filter')(
							$scope.listeEmplacementMagasin, {
								id : $scope.magasin.status
							});
					if ($scope.magasin.status && selected.length) {
						return selected[0].designation;
					} else {
						return "Not Set";
					}
				};
	 
	  	    	  
	     // ajout d'un emlacement
		    $scope.ajouterEmplacement = function() {
		      $scope.emplacementCourante = {
		        designation: '',
		        magasin :'',
		      };
		      $scope.listeEmplacement.push($scope.emplacementCourante);
		      
		    };
		   
	
	   //Enregistrer emplacement
	  $scope.saveEmplacement = function(data, id) {
		  if (angular.isDefined(id)) {
			     $log.debug(data);
			     $log.debug("id  modifier "+id);
			     $http.post(baseUrlGs + "/emplacement/modifierEmplacement", data)
			     .success(function (newEmplacement) {
			    	angular.extend(newEmplacement);
			     });
	        } else {
	        	 $log.debug("id ajouter "+id);
	        	 $log.debug(data);
			     $http.post(baseUrlGs + "/emplacement/creerEmplacement", data)
			     .success(function (newEmplacement) {
				    	angular.extend(newEmplacement);
			     });
	        }
		     
		  }
		    
	  
	   // Suppression emplacement 
	    $scope.removeEmplacement = function (id) {
        	$http({
 	    		method: "DELETE",
 	    		url: baseUrlGs + "/emplacement/supprimerEmplacement:"+$scope.listeEmplacement[id].id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
        	$scope.listeEmplacement.splice(id, 1);

	    }
   	 $scope.listeEmplacement();
   }
  ])
   .controller('backRaisonMouvementController', ['$scope','$filter', '$http','baseUrlGs', function ($scope,$filter, $http, baseUrlGs) {
	//Déclaration des variables globales utilisées
		var data;
	  	$scope.displayMode = "";
	    $scope.raisonCourante = null;
	    $scope.listeRaison=[]; 
	    $scope.resultatRecherche=$scope.listeRaison;
	    /********************
	     * Gestion de la  Raison 
	     *********************************/
	    
	    //Lister Raison  
	    $scope.listeRaison = function () {
	    	$http.get(baseUrlGs+"/raisonMouvement/all").success(function (data) {
	    		$scope.listeRaison = data;
	    	});
	    }
	     $scope.listeRaison();
	     
	     // ajout d'un couleur
		    $scope.ajouterRaison = function() {
		      $scope.raisonCourante = {
		        designation: '',
		      };
		      $scope.listeRaison.push($scope.raisonCourante);
		      
		    };
		   
	
	   //Enregistrer Raison
	  $scope.saveRaison = function(data, id) {
		  if (angular.isDefined(id)) {
			  alert("Raison existe deja");
			     $log.debug(data);
			     $http.post(baseUrlGs + "/raisonMouvement/modifierRaisonMouvement", data)
			     .success(function (newRaison) {
			    	angular.extend(newRaison);
			     });
	        } else {
	        	$log.debug(data);
			     $http.post(baseUrlGs + "/raisonMouvement/creerRaisonMouvement", data)
			     .success(function (newRaison) {
				    	angular.extend(newRaison);
			     });
	        }
		     
		  }
		    
	  
	   // Suppression Raison 
	    $scope.removeRaison = function (id) {
	    	 alert(""+id);
        	$http({
 	    		method: "DELETE",
 	    		url: baseUrlGs + "/raisonMouvement/supprimerRaisonMouvement:"+id
 	    		}).success(function () {
 	    	    alert("Success Delete");
 	    	});
	    }

   }
  ])
