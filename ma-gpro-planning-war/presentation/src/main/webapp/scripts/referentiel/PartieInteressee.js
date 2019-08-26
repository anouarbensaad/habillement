'use strict'

angular
  .module('gpro.partieInteressee', ["ngResource"])
 .constant("baseUrl", "http://localhost:8080/mt-gpro-commun-rest")
 .controller('PartieInteresseeController', ['$scope','$filter', '$http','baseUrl', function ($scope,$filter, $http, baseUrl) {
	  
	  //Déclaration des variables globales utilisées
	  
	 	var data;
	  
	  	$scope.displayMode = "list";
	    $scope.partieInteresseeCourante = null;
	    $scope.listeRepresentant=[]; 
	    $scope.listeDocuments=[];
	  
	    $scope.resultatRecherche=$scope.listePartieInteressee;
	    
	    /********************
	     * Gestion de la PI
	     ********************/
	    //Lister les parties interessées
	    $scope.listePartieInteressee = function () {
	    	$http.get(baseUrl+"/partieInteressee/all").success(function (data) {
	    		$scope.partieInteresssees = data;
	    		$scope.myData=data;
	    		});
	    }
	    
	    //Rechercher PI
	    $scope.rechercherPartieInteressee = function (partieInteresseeCourante) {
	    	$http.post(baseUrl + "/partieInteressee/recherchePIMulticritere",partieInteresseeCourante).success(function (resultat) {
	    		$scope.myData=resultat.partieInteresseValues;
	    		$scope.displayMode = "rechercher";
	    	});
    		
	    }
	    
	     // Ajout et Modification
	    $scope.modifierOuCreerPartieInteressee = function (partieInteressee) {
	        $scope.partieInteresseeCourante =
	        	partieInteressee ? angular.copy(partieInteressee) : {};
	        $scope.displayMode = "edit";
	    }
	    
	    
	     //  Sauvegarder PI
	    $scope.sauvegarderAjout = function (partieInteressee) {
	        if (angular.isDefined(partieInteressee.id)) {
	            $scope.updatePartieInteressee(partieInteressee);
	        } else {
	            $scope.creerPartieInteressee(partieInteressee);
	        }
	    }
	    //Mise à jour de la partie interessée
	    $scope.updatePartieInteressee = function (partieInteressee) {
	    	$http({
	    		url: baseUrl +"/partieInteressee/modifierPartieInteressee:{"+partieInteressee.id+"}",
	    		method: "PUT",
	    		data: partieInteressee
	    		}).success(function (partieInteresseeModifiee) {
	        for (var i = 0; i < $scope.listePartieInteressee.length; i++) {
	            if ($scope.myData[i].id == partieInteresseeModifiee.id) {
	                $scope.myData[i] = partieInteresseeModifiee;
	                break;
	            }
	        }
	        $scope.displayMode = "list";
	    		});
	    }
	    
	    //Création PI
	    $scope.creerPartieInteressee = function (partieInteressee) {
	    	partieInteressee.representants=$scope.listeRepresentant;
	    	$http.post(baseUrl + "/partieInteressee/creerPartieInteressee", partieInteressee).success(function (newPartieInteressee) {
	        $scope.myData.push(newPartieInteressee);
	        $scope.displayMode = "list";
	    	});
	    }
	    

	    //  Annulation de l'ajout 
	    $scope.annulerAjout = function () {
	        $scope.partieInteresseeCourante = {};
	        $scope.displayMode = "list";
	    }
	    
	     // Suppression PI
	    $scope.supprimerPartieInteressee = function (partieInteressee) {
	    	$http({
	    		method: "DELETE",
	    		url: baseUrl + "/partieInteressee/supprimerPartieInteressee:"+partieInteressee.id
	    		}).success(function () {
	    	$scope.myData.splice($scope.myData.indexOf(partieInteressee), 1);
	    	});
	    }
	    $scope.listePartieInteressee();

	    /** Fin de gestion de la partie Interessée */
	    
	    /*****************************
	     * Gestion des representants 
	     ****************************/

	    // ajout d'un Representant
	    $scope.ajoutRepresentant = function() {
	      $scope.representantInserree = {
	        nom: '',
	        fonction: null,
	        email: null 
	      };
	      $scope.listeRepresentant.push($scope.representantInserree);
	      
	    };
	    
	    	//Enregistrer Representant
	    	$scope.saveRepresentant = function(data, id) {
	        //$scope.Representant not updated yet
	        angular.extend(data, {id: id});

	      };
	      
	      // Supprimer representant
	      $scope.removeRepresentant = function(index) {
	        $scope.listeRepresentant.splice(index, 1);
	      };
	      /** Fin de gestion des representants */
	      
	    /*********************************
	     *  Gestion des Grids de recherche 
	     *********************************/

	    $scope.filterOptions = {
	            filterText: "",
	            useExternalFilter: true
	        };
	    
	        $scope.totalServerItems = 0;
	        $scope.pagingOptions = {
	            pageSizes: [25, 50, 100],
	            pageSize: 25,
	            currentPage: 1
	        }; 
	        
	        $scope.setPagingData = function(data, page, pageSize){  
	            var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
	            $scope.myData = pagedData;
	            $scope.totalServerItems = data.length;
	            if (!$scope.$$phase) {
	                $scope.$apply();
	            }
	        };
	        
	        $scope.getPagedDataAsync = function (pageSize, page, searchText) {
	            setTimeout(function () {
	                
	                if (searchText) {
	                    var ft = searchText.toLowerCase();
	                           
	                        data = $scope.myData.filter(function(item) {
	                            return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
	                      
	                        $scope.setPagingData(data,page,pageSize);
	                    });            
	                } else {
	                    
	                        $scope.setPagingData($scope.myData,page,pageSize);
	                    
	                }
	            }, 100);
	        };
	        
	        $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	        
	        $scope.$watch('pagingOptions', function (newVal, oldVal) {
	            if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
	              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
	            }
	        }, true);
	        
	        $scope.$watch('filterOptions', function (newVal, oldVal) {
	            if (newVal !== oldVal) {
	              $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
	            }
	        }, true);
	    
	        

	    $scope.gridOptions = {
	        data: 'myData',
	        columnDefs: [{field:'id', displayName:'Id'},
	                     {field:'reference', displayName:'Reference PI'}, 
	                     {field:'raisonSociale', displayName:'Raison Sociale'}, 
	                     {field:'famillePartieInteressee', displayName:'Famille PI'},
	                     {field:'categoriePartieInteressee', displayName:'Categorie PI'},
	                     {field:'typePartieInteressee', displayName:'Type PI'},
	                     {field:'telephone', displayName:'Telephone'},
	                     { field: '', cellTemplate: '<div class="buttons" ng-show="!rowform.$visible"><button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPartieInteressee(this)">	<i class="fa fa-fw fa-pencil"></i></button>	<button data-nodrag class="btn btn-default btn-xs"	ng-click="supprimerPartieInteressee(this)">	<i class="fa fa-fw fa-trash-o"></i></button></div>'}
	                     ],
	        enablePaging: true,
	        showFooter: true,
	        totalServerItems: 'totalServerItems',
	        pagingOptions: $scope.pagingOptions,
	        filterOptions: $scope.filterOptions
	    };
	  /** Fin de gestion des Grids de la partie Interesse */
  }
  ])

					