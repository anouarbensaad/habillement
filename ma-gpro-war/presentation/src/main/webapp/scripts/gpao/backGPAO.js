'use strict'

var app = angular.module('gpro.back-gpao', [ "ngResource" ]);
app.controller('BackGpaoCtr', [ '$scope','baseUrlGpao','$log', function($scope,baseUrlGpao,$log) {

			// Déclaration des variables globales utilisées

			/** ***Liste desVariables **** */
			
			$scope.displayMenu = "gpao";
			$scope.parametre = "Statut";
			
			  //Annuler Ajout
		    $scope.cancelAdd = function(rowform, index, id, designation, liste){
		    	$log.info("* Designation "+liste[0].designation);
		    		  if (angular.isDefined(id)) {
		    			  $log.info("ID "+id);
		    			  rowform.$cancel();
		    		  }else{
		    			  $log.info("ID "+id);
		    			  if(designation ==""){
		    				  $log.info("DELETE" +designation);
		    				  liste.splice(index, 1);
					    	  rowform.$cancel();
		    			  }else{
		    				  $log.info("NOT DELETE "+designation);
		    				  rowform.$cancel();
		    			  }
		    		}
		    }
		    
		}]);
		 /*****************************
	     * Gestion des Statuts 
	     ****************************/
		  app.controller('backStatutPIController', ['$scope','$filter', '$http','baseUrlGpao','$log', function ($scope,$filter, $http, baseUrlGpao,$log) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "gpao";
				    $scope.StatutCourante = null;
				    $scope.listeStatut=[]; 
				    $scope.resultatRecherche=$scope.listeStatut;
				    /********************************
				     * Gestion de la  Statut 
				     ********************************/
				    //Lister Statut  
				    $scope.listeStatut = function () {
				    	$http.get(baseUrlGpao+"/statut/all").success(function (data) {
				    		$scope.listeStatut = data;
				    	});
				    }
				  
				     // ajout d'un Statut
					    $scope.ajouterStatut = function() {
					      $scope.StatutCourante = {
					        designation: ''
					      };
					    
					     // $log.info("* $scope.StatutCourante "+ $scope.StatutCourante);
					      $scope.listeStatut.push($scope.StatutCourante);
					    };
					    
					  
				   //Enregistrer Statut
				  $scope.saveStatut = function(data, id) {
					  if (angular.isDefined(id)) {
						     $http.post(baseUrlGpao + "/statut/modifierStatut", data)
						     .success(function (newStatut) {
						    	 $log.info("Success Modification");
						    	angular.extend(newStatut);
						     });
				        } else {
						     $http.post(baseUrlGpao + "/statut/creerStatut", data)
						     .success(function (newStatut) {
						    	 $log.info("Success Creation");
							    	angular.extend(newStatut);
						     });
				        }
					     
					  }
				   // Suppression Statut 
				    $scope.removeStatut = function (index) {
				    	$log.info("INDEX :" + index);
						$log.info("**OBJET :" + $scope.listeStatut[index]);
						$log.info("DELETE .." + $scope.listeStatut[index].id);
			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGpao + "/statut/supprimerStatut:"+$scope.listeStatut[index].id
			 	    		}).success(function () {
							$log.info("Success Delete");
							$scope.listeStatut.splice(index, 1);
						});
			        	$scope.listeStatut.splice(index, 1);
				    }
				    $scope.listeStatut();
			   }
			  ]);
	 	/*****************************
	     * Gestion de Chaine 
	     ****************************/
		  app.controller('backChaineController', ['$scope','$filter', '$http','baseUrlGpao','$log', function ($scope,$filter, $http, baseUrlGpao,$log) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "gpao";
				    $scope.ChaineCourante = null;
				    $scope.listeChaine=[]; 
				    $scope.resultatRecherche=$scope.listeChaine;
				    /********************
				     * Gestion de Chaine
				     *********************************/
				    
				    //Lister Chaine
				    $scope.listeChaineFct = function () {
				    	$http.get(baseUrlGpao+"/chaine/all").success(function (dataChaine) {
				    		$scope.listeChaine = dataChaine;
				    		$log.info("Chaine "+ dataChaine.length)
				    	});
				    }
				    
				     
				     // ajout d'une Chaine
				    $scope.ajouterChaine = function() {
					      $scope.ChaineCourante = {
					        designation: ''
					      };
					      $scope.listeChaine.push($scope.ChaineCourante);
					      
					    };
				   //Enregistrer Chaine
				  $scope.saveChaine = function(chaine, id) {
					  if (angular.isDefined(id)) {
						     $http.post(baseUrlGpao + "/chaine/modifierChaine", chaine)
						     .success(function (newChaine) {
						    	angular.extend(newChaine);
						     });
						  //   $scope.listeChaine();
						     
				        } else {
				        	$log.info(chaine);
						     $http.post(baseUrlGpao + "/chaine/creerChaine", chaine)
						     .success(function (newChaine) {
							    	angular.extend(newChaine);
						     });
						   //  $scope.listeChaine();
				        }
					  }
				   // Suppression Chaine
				    $scope.removeChaine = function (index) {
				    	$log.info("INDEX :" + index);
						$log.info("**OBJET :" + $scope.listeChaine[index]);
						$log.info("DELETE .." + $scope.listeChaine[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGpao + "/chaine/supprimerChaine:"+ $scope.listeChaine[index].id
			 	    		}).success(function () {
			 	    			$log.info("DELETE Chaine");
			 	    		//	$scope.listeChaine.splice(index, 1);
			 	    	});
			        	$scope.listeChaine.splice(index, 1);
				    }
				    
				    $scope.listeChaineFct();
			   }
			  ]);

		/*****************************
	     * Gestion de Machine 
	     ****************************/
		  app.controller('backMachineController', ['$scope','$filter', '$http','baseUrlGpao','$log', function ($scope,$filter, $http, baseUrlGpao,$log) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "gpao";
				    $scope.MachineCourante = null;
				    $scope.listeMachine=[]; 
				    /********************
				     * Gestion de Chaine
				     *********************************/
				    
				    //Lister Machine
				    $scope.listeMachineFct = function () {
				    	$http.get(baseUrlGpao+"/machine/getAll").success(function (dataMachine) {
				    		if(dataMachine.length>0)
				    		$scope.listeMachine = dataMachine;
				    		$log.info("Machine "+ dataMachine.length)
				    	});
				    }
				    
				     
				     // ajout d'une Machine
				    $scope.ajouterMachine = function() {
					      $scope.MachineCourante = {
					        designation: ''
					      };
					      $scope.listeMachine.push($scope.MachineCourante);
					      
					    };
				   //Enregistrer Machine
				  $scope.saveMachine = function(machine, id) {
					  if (angular.isDefined(id)) {
						     $http.post(baseUrlGpao + "/machine/update", machine)
						     .success(function (newMachine) {
						    	angular.extend(newMachine);
						     });
						     
				        } else {
						     $http.post(baseUrlGpao + "/machine/create", machine)
						     .success(function (newMachine) {
							    	angular.extend(newMachine);
						     });
				        }
					  }
				   // Suppression Machine
				    $scope.removeMachine = function (index) {
				    	$log.info("INDEX :" + index);
						$log.info("**OBJET :" + $scope.listeMachine[index]);
						$log.info("DELETE .." + $scope.listeMachine[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGpao + "/machine/delete:"+ $scope.listeMachine[index].id
			 	    		}).success(function () {
			 	    			$log.info("DELETE Machine");
			 	    		//	$scope.listeMachine.splice(index, 1);
			 	    	});
			        	$scope.listeMachine.splice(index, 1);
				    }
				    
				    $scope.listeMachineFct();
			   }
			  ]);

		/*****************************
	     * Gestion de Machine 
	     ****************************/
		  app.controller('backSectionController', ['$scope','$filter', '$http','baseUrlGpao','$log', function ($scope,$filter, $http, baseUrlGpao,$log) {
				//Déclaration des variables globales utilisées
					var data;
				  	$scope.displayMode = "gpao";
				    $scope.SectionCourante = null;
				    $scope.listeSection = []; 
				    
				    //Lister Section
				    $scope.listeSectionFct = function () {
				    	$http.get(baseUrlGpao+"/section/getAll").success(function (dataSection) {
				    		if(dataSection.length>0)
				    		$scope.listeSection= dataSection;
				    		$log.info("Section "+ dataSection.length)
				    	});
				    }
				    
				     
				     // ajout d'une Section
				    $scope.ajouterSection = function() {
					      $scope.SectionCourante = {
					        designation: ''
					      };
					      $scope.listeSection.push($scope.SectionCourante);
					      
					    };
				   //Enregistrer Section
				  $scope.saveSection = function(section, id) {
					  if (angular.isDefined(id)) {
						     $http.post(baseUrlGpao + "/section/update", section)
						     .success(function (newSection) {
						    	angular.extend(newSection);
						     });
						     
				        } else {
						     $http.post(baseUrlGpao + "/section/create", section)
						     .success(function (newSection) {
							    	angular.extend(newSection);
						     });
				        }
					  }
				   // Suppression Section
				    $scope.removeSection = function (index) {
				    	$log.info("INDEX :" + index);
						$log.info("**OBJET :" + $scope.listeSection[index]);
						$log.info("DELETE .." + $scope.listeSection[index].id);

			        	$http({
			 	    		method: "DELETE",
			 	    		url: baseUrlGpao + "/section/delete:"+ $scope.listeSection[index].id
			 	    		}).success(function () {
			 	    			$log.info("DELETE Section");
			 	    		//	$scope.listeChaine.splice(index, 1);
			 	    	});
			        	$scope.listeSection.splice(index, 1);
				    }
				    
				    $scope.listeSectionFct();
			   }
			  ]);

		
