//'use strict'
//
//var app = angular.module('gpro.back-commercial', [ "ngResource" ]);
//app.controller('BackCommercialCtr', [ '$scope','$log', function($scope,$log) {
//
////	console.log("Back commercial");
////	
////			// Déclaration des variables globales utilisées
////
////			/** ***Liste desVariables **** */
////			
////			$scope.displayMenu = "commercial";
////			$scope.parametre = "Type Reglement";
////			
////			  //Annuler Ajout
////		    $scope.cancelAdd = function(rowform, index, id, designation, liste){
////		    	$log.debug("* Designation "+liste[0].designation);
////		    		  if (angular.isDefined(id)) {
////		    			  $log.debug("ID "+id);
////		    			  rowform.$cancel();
////		    		  }else{
////		    			  $log.debug("ID "+id);
////		    			  if(designation ==""){
////		    				  $log.debug("DELETE" +designation);
////		    				  liste.splice(index, 1);
////					    	  rowform.$cancel();
////		    			  }else{
////		    				  $log.debug("NOT DELETE "+designation);
////		    				  rowform.$cancel();
////		    			  }
////		    		}
////		    }
//		    
//		}]);
//		 /*****************************
//	     * Gestion de TypeReglement 
//	     ****************************/
//		  app.controller('backTypeReglementController', ['$scope','$filter', '$http','$log', function ($scope,$filter, $http, $log) {
//				//Déclaration des variables globales utilisées
//					var data;
//				  	$scope.displayMode = "commercial";
//				    $scope.MachineCourante = null;
//				    $scope.listeMachine=[]; 
//				    /********************
//				     * Gestion de Chaine
//				     *********************************/
//				    
//				    //Lister Machine
//				    $scope.listeMachineFct = function () {
//				    	$http.get(UrlAtelier+"/typeReglement/getAll").success(function (dataMachine) {
//				    		if(dataMachine.length>0)
//				    		$scope.listeMachine = dataMachine;
//				    		$log.debug("typeReglement "+ dataMachine.length)
//				    	});
//				    }
//
//				    // ajout d'une Machine
//				    $scope.ajouterMachine = function() {
//					      $scope.MachineCourante = {
//					        designation: ''
//					      };
//					      $scope.listeMachine.push($scope.MachineCourante);
//					      
//					    };
//				  	//Enregistrer Machine
//				  	$scope.saveMachine = function(machine, id) {
//					  if (angular.isDefined(id)) {
//						     $http.post(UrlAtelier + "/typeReglement/update", machine)
//						     .success(function (newMachine) {
//						    	angular.extend(newMachine);
//						     });
//						     
//				        } else {
//						     $http.post(UrlAtelier + "/typeReglement/create", machine)
//						     .success(function (newMachine) {
//							    	angular.extend(newMachine);
//						     });
//				        }
//					  }
//				    // Suppression Machine
//				    $scope.removeMachine = function (index) {
//				    	
//			        	$http({
//			 	    		method: "DELETE",
//			 	    		url: UrlAtelier + "/typeReglement/delete:"+ $scope.listeMachine[index].id
//			 	    		}).success(function () {
//			 	    			$log.debug("DELETE Machine");
//			 	    		//	$scope.listeMachine.splice(index, 1);
//			 	    	});
//			        	$scope.listeMachine.splice(index, 1);
//				    }
//				    
//				    $scope.listeMachineFct();
//			   }
//			  ]);
//
//		/*****************************
//	     * Gestion de Traitement 
//	     ****************************/
//		  app.controller('backModePaiementController', ['$scope','$filter', '$http','UrlAtelier','$log', function ($scope,$filter, $http, UrlAtelier,$log) {
//				//Déclaration des variables globales utilisées
//					var data;
//				  	$scope.displayMode = "commercial";
//				    $scope.ModePaiementCourant = null;
//				    $scope.listeTraitement=[]; 
//				    /********************
//				     * Gestion de Chaine
//				     *********************************/
//				    
//				    //Lister Traitement
//				    $scope.listeTraitementFct = function () {
//				    	$http.get(UrlAtelier+"/modePaiement/getAll").success(function (dataTraitement) {
//				    		if(dataTraitement.length>0)
//				    		$scope.listeTraitement = dataTraitement;
//				    		$log.debug("modePaiement "+ dataTraitement.length)
//				    	});
//				    }
//				    
//				     
//				     // ajout d'une Traitement
//				    $scope.ajouterModePaiement = function() {
//					      $scope.ModePaiementCourant = {
//					        designation: ''
//					      };
//					      $scope.listeTraitement.push($scope.ModePaiementCourant);
//					      
//					    };
//				   //Enregistrer Traitement
//				  $scope.saveTraitement = function(traitement, id) {
//					  if (angular.isDefined(id)) {
//						     $http.post(UrlAtelier + "/modePaiement/update", traitement)
//						     .success(function (newTraitement) {
//						    	angular.extend(newTraitement);
//						     });
//						     
//				        } else {
//						     $http.post(UrlAtelier + "/modePaiement/create", traitement)
//						     .success(function (newTraitement) {
//							    	angular.extend(newTraitement);
//						     });
//				        }
//					  }
//				   // Suppression Traitement
//				    $scope.removeTraitement = function (index) {
//
//			        	$http({
//			 	    		method: "DELETE",
//			 	    		url: UrlAtelier + "/modePaiement/delete:"+ $scope.listeTraitement[index].id
//			 	    		}).success(function () {
//			 	    			$log.debug("DELETE Traitement");
//			 	    		//	$scope.listeTraitement.splice(index, 1);
//			 	    	});
//			        	$scope.listeTraitement.splice(index, 1);
//				    }
//				    
//				    $scope.listeTraitementFct();
//			   }
//			  ]);
//
//
//		
