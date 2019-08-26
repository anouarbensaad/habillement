'use strict'

var app = angular.module('gpro.back-gcVente', [ "ngResource" ]);
app.controller('BackGcVenteCtr', [ '$scope', function($scope) {

			// Déclaration des variables globales utilisées

			/** ***Liste desVariables **** */
			
			$scope.displayMenu = "pi";
			$scope.parametre = "Etat";
			
			  //Annuler Ajout
		    $scope.cancelAdd = function(rowform, index, id, designation, liste){
		    	console.log("* Designation "+liste[0].designation);
		    		  if (angular.isDefined(id)) {
		    			  console.log("ID "+id);
		    			  rowform.$cancel();
		    		  }else{
		    			  console.log("ID "+id);
		    			  if(designation ==""){
		    				  console.log("DELETE" +designation);
		    				  liste.splice(index, 1);
					    	  rowform.$cancel();
		    			  }else{
		    				  console.log("NOT DELETE "+designation);
		    				  rowform.$cancel();
		    			  }
		    		}
		    }
		    
		}]);
		 /*****************************
	     * Gestion des EtatCommandes 
	     ****************************/
		  app.controller('backEtatCommandeController', ['$scope','$filter', '$http','baseUrlGc', function ($scope,$filter, $http, baseUrlGc) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "";
				    $scope.EtatCommandeCourante = null;
				    $scope.listeEtatCommande=[]; 
				    $scope.resultatRecherche=$scope.listeEtatCommande;
				    /********************************
				     * Gestion de la  EtatCommande 
				     ********************************/
				    //Lister EtatCommande  
				    $scope.listeEtatCommande = function () {
				    	$http.get(baseUrlGc+"/etatCommande/all").success(function (data) {
				    		$scope.listeEtatCommande = data;
				    	});
				    }
				  
				     // ajout d'un EtatCommande
					    $scope.ajouterEtatCommande = function() {
					      $scope.EtatCommandeCourante = {
					        designation: ''
					      };
					    
					     // console.log("* $scope.EtatCommandeCourante "+ $scope.EtatCommandeCourante);
					      $scope.listeEtatCommande.push($scope.EtatCommandeCourante);
					    };
					    
					  
				   //Enregistrer EtatCommande
				  $scope.saveEtatCommande = function(data, id) {
				  	console.log("======= Etat : "+JSON.stringify(data,null, "  "));
					  if (angular.isDefined(id)) {
						     $http.post(baseUrlGc + "/etatCommande/modifierEtatCommande", data)
						     .success(function (newEtatCommande) {
						    	 console.log("Success Modification");
						    	angular.extend(newEtatCommande);
						     });
				        } else {
						     $http.post(baseUrlGc + "/etatCommande/creerEtatCommande", data)
						     .success(function (newEtatCommande) {
						    	 console.log("Success Creation");
							    	angular.extend(newEtatCommande);
						     });
				        }
					     
					  }
				   // Suppression EtatCommande 
				    $scope.removeEtatCommande = function (index) {
				    	console.log("INDEX :" + index);
						console.log("**OBJET :" + $scope.listeEtatCommande[index]);
						console.log("DELETE .." + $scope.listeEtatCommande[index].id);
			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGc + "/etatCommande/supprimerEtatCommande:"+$scope.listeEtatCommande[index].id
			 	    		}).success(function () {
							console.log("Success Delete");
							$scope.listeEtatCommande.splice(index, 1);
						});
			        	$scope.listeEtatCommande.splice(index, 1);
				    }
				    $scope.listeEtatCommande();
			   }
			  ]);
	 	
	 	 /*****************************
	     * Gestion de TypeCommande 
	     ****************************/
		  app.controller('backTypeCommandeController', ['$scope','$filter', '$http','baseUrlGc', function ($scope,$filter, $http, baseUrlGc) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "";
				    $scope.TypeCommandeCourante = null;
				    $scope.listeTypeCommande=[]; 
				    $scope.resultatRecherche=$scope.listeTypeCommande;
				    /********************
				     * Gestion de TypeCommande
				     *********************************/
				    
				    //Lister TypeCommande
				    $scope.listeTypeCommandeFct = function () {
				    	$http.get(baseUrlGc+"/typeCommande/all").success(function (dataTypeCommande) {
				    		$scope.listeTypeCommande = dataTypeCommande;
				    		console.log("TypeCommande "+ dataTypeCommande.length)
				    	});
				    }
				    
				     
				     // ajout d'une TypeCommande
				    $scope.ajouterTypeCommande = function() {
					      $scope.TypeCommandeCourante = {
					        designation: ''
					      };
					      $scope.listeTypeCommande.push($scope.TypeCommandeCourante);
					      
					    };
				   //Enregistrer TypeCommande
				  $scope.saveTypeCommande = function(typeCommande, id) {
				  	console.log("======= Type : "+JSON.stringify(typeCommande,null, "  "));
					  if (angular.isDefined(id)) {
						     $http.post(baseUrlGc + "/typeCommande/modifierTypeCommande", typeCommande)
						     .success(function (newTypeCommande) {
						    	angular.extend(newTypeCommande);
						     });
						  //   $scope.listeTypeCommande();
						     
				        } else {
				        	console.log(typeCommande);
						     $http.post(baseUrlGc + "/typeCommande/creerTypeCommande", typeCommande)
						     .success(function (newTypeCommande) {
							    	angular.extend(newTypeCommande);
						     });
						   //  $scope.listeTypeCommande();
				        }
					  }
				   // Suppression TypeCommande
				    $scope.removeTypeCommande = function (index) {
				    	console.log("INDEX :" + index);
						console.log("**OBJET :" + $scope.listeTypeCommande[index]);
						console.log("DELETE .." + $scope.listeTypeCommande[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGc + "/typeCommande/supprimerTypeCommande:"+ $scope.listeTypeCommande[index].id
			 	    		}).success(function () {
			 	    			console.log("DELETE TypeCommande");
			 	    		//	$scope.listeTypeCommande.splice(index, 1);
			 	    	});
			        	$scope.listeTypeCommande.splice(index, 1);
				    }
				    
				    $scope.listeTypeCommandeFct();
			   }
			  ]);
		 /*****************************
	     * Gestion de TypeCommande 
	     ****************************/
		  app.controller('backAgentController', ['$scope','$filter', '$http','baseUrlGc', function ($scope,$filter, $http, baseUrlGc) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "";
				    $scope.AgentCourant = null;
				    $scope.listeAgent=[]; 
				    $scope.resultatRecherche=$scope.listeAgent;
				    /********************
				     * Gestion de Agent
				     *********************************/
				    
				    //Lister Agent
				    $scope.listeAgentFct = function () {
				    	$http.get(baseUrlGc+"/agentGc/all").success(function (data) {
				    		$scope.listeAgent = data;
				    		console.log("Agent "+ data.length)
				    	});
				    }
				    
				     
				     // ajout d'une Agent
				    $scope.ajouterAgent = function() {
					      $scope.AgentCourant = {
					        nom: ''
					      };
					      $scope.listeAgent.push($scope.AgentCourant);
					      
					    };
				   //Enregistrer Agent
				  $scope.saveAgent = function(agentGc, id) {

					  if (angular.isDefined(id)) {
						     $http.put(baseUrlGc + "/agentGc/modifier", agentGc)
						     .success(function (newId) {
						    	angular.extend(newId);
						     });
						  //   $scope.listeAgent();
						     
				        } else {
						     $http.post(baseUrlGc + "/agentGc/creer", agentGc)
						     .success(function (newId) {
							    	angular.extend(newId);
						     });
						   //  $scope.listeAgent();
				        }
					  }
				   // Suppression Agent
				    $scope.removeAgent = function (index) {
				    	console.log("INDEX :" + index);
						console.log("**OBJET :" + $scope.listeAgent[index]);
						console.log("DELETE .." + $scope.listeAgent[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGc + "/agentGc/supprimer:"+ $scope.listeAgent[index].id
			 	    		}).success(function () {
			 	    			console.log("DELETE Agent");
			 	    		//	$scope.listeAgent.splice(index, 1);
			 	    	});
			        	$scope.listeAgent.splice(index, 1);
				    }
				    
				    $scope.listeAgentFct();
			   }
			  ]);
