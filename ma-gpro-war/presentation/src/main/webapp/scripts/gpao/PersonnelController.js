'use strict'

angular
    .module('gpro.personnel', ["ui.sortable"])

    .controller(
        'PersonnelCtrl', [
            '$scope',
            '$http',
            '$filter',
            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$route',
            '$log',

            function($scope, $http, $filter,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$route,$log) {

                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.personne = {};
                $scope.listPaquet = [];
             
             // Déclaration des variables globales utilisés
                    var data;
                    $scope.displayMode = "list";
                    //bouton pdf hide
                    $scope.modePdf = "notActive";
                    $scope.personneCourante ={};
                    //init modification Metrage du rouleau
                    $scope.isModifie = false;
                    $scope.listePersonneFini = [];
                    
                    //init urlValider
                    $scope.urlValider = "";
                
         
             
                
                /***************************************************
                 * Gestion des Gammes *
                 **************************************************/
                $scope.deleteValue = "oui";
                // Annuler Ajout
                $scope.cancelAddGamme = function(rowform, index,
                    id, designation, liste) {
                    // console.log("* Designation
                    // "+liste[0].designation);
                    if (angular.isDefined(id)) {

                        //console.log("DEF ID");
                        $scope.deleteValue = "non";
                        rowform.$cancel();
                        //console.log("CANCEL");
                    } else {
                        //console.log("UND ID  " + id);
                        if (designation == "") {
                            $scope.deleteValue == "oui"

                            liste.splice(index, 1);
                            rowform.$cancel();
                            console.log("DELETE");
                        } else {

                            rowform.$cancel();
                            console.log("CANCEL");
                        }
                    }
                    $scope.deleteValue = "oui";
                }

                // declaration variable
                $scope.displayAlert = "sleep";

                // Rechercher Personne
                $scope.recherchePersonne = function(personneCourante) {
                   
                    $http
                        .post(
                            baseUrlGpao +
                            "/personnel/rechercheMulticritere",
                            personneCourante)

                    .success(
                        function(resultat) {
                             
                       
                            $scope.myData = resultat.list;
                            // data, page,pageSize
                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.recherchePersonneForm
                                .$setPristine();
                            $scope.displayMode = "rechercher";
                            $scope.displayAlert = "sleep";
                        });
                
              }
               
                // Liste des Matricules
                $scope.listeMatricule = function() {
                    $http
                        .get(baseUrlGpao + "/feuilleSaisie/listPersonnel")
                        .success(
                            function(dataProduit) {
                                $scope.listMatricule= dataProduit;
                            });
                }
                $scope.listeMatricule();

              
             

                $scope.recherchePersonne({});
               
                // ** Ajout Personne
                $scope.AffectationPersonne = function(Personne) {
                    
                   
                	$scope.personneCourante = {
                			
                  		  "matricule": "",
    						  "nom": "",
    						  "prenom": "",
                  	};
                       
                  
                    
                    $scope.creationPersonneForm.$setPristine();
         
                    
                    //mode edit activé
                        $scope.displayMode = "edit";
                    
                  
                    
                   
                }

             
             
                
             // Sauvegarder Personne
                $scope.sauvegarderAjout = function(Personne) {
                     
                    //console.log("gamme :"+gamme);
                    if (angular.isDefined(Personne.id)) {
                        
                        $scope.updatePersonne(Personne);
                    
                    } else {
                        
                        $scope.creerPersonne(Personne);
                        
                    }
                    

            
                }
                
        
    
                
                    // Création Personne

                $scope.creerPersonne = function(Personne) {
                    
                   
                
                	
                	Personne.matricule=$scope.personneCourante.matricule;
                	Personne.nom=$scope.personneCourante.nom;
                	Personne.prenom=$scope.personneCourante.prenom;
                	
                	
					$http
					.post(baseUrlGpao+"/personnel/creer",
							Personne)
							.success(
									function(newPersonne) {
									
										//TODO getId
										$scope.annulerAjout();
									});
                	
            
                }
             
               
                
               
               
                
                /** Fin de gestion des Personnes */

                $scope.pagingOptions = {
                    pageSizes: [5, 10, 13],
                    pageSize: 13,
                    currentPage: 1
                };
                $scope.colDefs = [];
                $scope
                    .$watch(
                        'myData',
                        function() {
                            $scope.colDefs = [
                                
                               {
                                    field: 'matricule',
                                    displayName: 'Matricule',
                                    width: '30%'
                                },
                                {
                                    field: 'nom',
                                    displayName: 'Nom',
                                    width: '30%'
                                },  {
                                    field: 'prenom',
                                    displayName: 'Prenom',
                                    width: '30%'
                                },  {
                                    field: '',
                                    width:'10%',
                                    cellTemplate: '<div class="buttons TableHeaderalignment" ng-show="!rowform.$visible" >' +
                                        '<button data-nodrag class="btn btn-default btn-xs" ng-click="modifierOuCreerPersonne()"><i class="fa fa-fw fa-pencil"></i></button>' +
                                        '<button data-nodrag class="btn btn-default btn-xs" ng-click="showPopupDelete(25)"> <i class="fa fa-fw fa-trash-o"></i></button></div>'
                                }
                            ];
                        });

                $scope.typeAlert = 0;
                $scope.filterOptions = {
                    filterText: "",
                    useExternalFilter: true
                };
                $scope.totalServerItems = 0;

                $scope.setPagingData = function(data, page,
                    pageSize) {
                    var pagedData = data.slice((page - 1) *
                        pageSize, page * pageSize);
                    $scope.myData = pagedData;
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
                            var personne = $scope.personneCourante;
                            if (searchText) {
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/personnel/rechercheMulticritere",
                                        personne)
                                    .success(
                                        function(
                                            largeLoad) {
                                            data = largeLoad.list
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
                                            /*$scope.recherchePersonne({});*/
                                        });

                            } else {
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/personnel/rechercheMulticritere",
                                        personne)
                                    .success(
                                        function(
                                            largeLoad) {
                                            $scope
                                                .setPagingData(
                                                    largeLoad.list,
                                                    page,
                                                    pageSize);
                                            /*$scope.recherchePersonne({});*/
//                                            console
//                                                .log("test");
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
                            if (newVal !== oldVal &&
                                newVal.currentPage !== oldVal.currentPage) {
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

           
            

             

            
            


                $scope.gridOptions = {

                    data: 'myData',
                    columnDefs: 'colDefs',
                    enablePaging: true,
                    showFooter: true,
                    enableHighlighting : true,
                    totalServerItems: 'totalServerItems',
                    pagingOptions: $scope.pagingOptions,
                    selectedItems: $scope.selectedRows,
                    filterOptions: $scope.filterOptions,
                };
              
        
                    $scope.deleteValue="oui";
                    //Annuler Ajout
                    $scope.cancelAddRouleau = function(rowform, index, id, designation, liste){
                    //$log.debug("* Designation "+liste[0].designation);
                          if (angular.isDefined(id)) {
                                  $log.debug("DEF ID");
                                  $scope.deleteValue="non";
                                  rowform.$cancel();
                                  $log.debug("CANCEL");
                          }else{    
                              $log.debug("UND ID  "+id);
                              if(designation ==""){
                                  $scope.deleteValue=="oui"
                                  $log.debug("Designation : "+reference);
                                  $log.debug("$scope.deleteValueOUI : "+$scope.deleteValue);
                                  liste.splice(index, 1);
                                  rowform.$cancel();
                                  $log.debug("DELETE")
                              }else{
                                  $log.debug("Designation :"+reference);
                                  $log.debug("$scope.deleteValueNON : "+$scope.deleteValue);
                                  rowform.$cancel();
                                  $log.debug("CANCEL");
                              }
                        }
                          $scope.deleteValue="oui";
                }
                    
                

         
                        // annuler Recherche
                        $scope.annulerAjout = function() {
                            
                           
                        	
                        	$scope.personneCourante = {
                        			
                        		  "matricule": "",
          						  "nom": "",
          						  "prenom": "",
                        	};
                        	$scope.recherchePersonneForm.$setPristine();
                            $scope.creationPersonneForm.$setPristine();
                            $scope.recherchePersonne({});
		                    $scope.displayMode = "list";
                        	
                       
                             
                        }
                        
                    
                    
                        
                        // Ajout et Modification Personnel
                        $scope.modifierOuCreerPersonne = function() {
                           
                            // bouton PDF activé
                            $scope.modePdf = "actif";
                            
                            var index = this.row.rowIndex;
                            
                          
                           
                            $http
                                    .get(
                                            baseUrlGpao
                                                    + "/personnel/getById:"
                                                    + $scope.myData[index].id)
                                    .success(
                                            function(personnel) {
                                                
                                            	 $scope.personneCourante = personnel;
                                               

                                            });

//                            $scope.personneCourante = $scope.myData[index] ? angular
//                                    .copy($scope.myData[index])
//                                    : {};
                        
                        // mode edit activé 
                            $scope.displayMode = "edit";
                      
                        }

                         //listChaines
                        $scope.listeChaines = function() {
                            $http
                                .get(baseUrlGpao + "/chaine/all")
                                .success(
                                    function(dataProduit) {
                                        $scope.listChaines= dataProduit;
                                    });
                        }
                        $scope.listeChaines();

                        // Mise à jour des personnes
                        $scope.updatePersonne = function(Personne) {
                            
                      
                        	
                        	Personne.matricule=$scope.personneCourante.matricule;
                        	Personne.nom=$scope.personneCourante.nom;
                        	Personne.prenom=$scope.personneCourante.prenom;
                            
                            
							$http
									.put(
											baseUrlGpao
													+ "/personnel/modifier", 
													Personne)
									.success(
											function(PersonneModifiee) {
												//TODO Code à revoir
												
											
												for (var i = 0; i < $scope.myData.length; i++) {

													if ($scope.myData[i].id == PersonneModifiee) {
														$scope.myData[i] = PersonneModifiee;
														
														break;
													}
												}
												//TODO getId
												$scope.annulerAjout();
											});
                        	
                        
                            
                        }


                        // Suppression Personne
                        $scope.supprimerPersonne = function() {
                          
                            $http(
                                    {
                                        method : "DELETE",
                                        url : baseUrlGpao
                                                + "/personnel/supprimer:"
                                                + $scope.myData[$scope.index].id
                                    }).success(function() {
                                    	
                                $log.debug("Success Delete");
                                $scope.myData.splice($scope.index, 1);
                                $scope.closePopupDelete();
                                //$scope.$apply();
                            });
                            $scope.closePopupDelete();
                            $scope.recherchePersonne({});
                        }
                    
          /*** PDF ***/
      //generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
                $scope.download = function(id) {
                        //$log.debug("-- id"+id);
//                      var url = UrlAtelier+"/report/bonsortiefini?id=" + id+"&type=pdf";
//                      downloadService.download(url)
//                              .then(
//                                      function(success) {
//                                          $log.debug('success : '+ success);
//                                          $scope.annulerAjout();
//                                      },
//                                      function(error) {
//                                          $log.debug('error : '+ error);
//                                      });
                    };
                    
                    //generer rapport de tous les bons de sortie. mode : List 

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
                     $scope.downloadAllBonSortie = function(bonSortieCourant) {

          var newdateSortieMinFormat="";
          if(angular.isDefined(bonSortieCourant.dateSortieMin)){
            $log.debug("==dateSortieMin "+bonSortieCourant.dateSortieMin);
            
            if(bonSortieCourant.dateSortieMin != ""){
              newdateSortieMinFormat = formattedDate(bonSortieCourant.dateSortieMin);
              $log.info("===== newdateSortieMinFormat "+newdateSortieMinFormat);
            }else{
              $log.info("===== newdateSortieMinFormat is Null");
              newdateSortieMinFormat = "";
            }
          }else{
            $log.debug("==dateSortieMin Undefined");
          }

          var newdateSortieMaxFormat="";
          if(angular.isDefined(bonSortieCourant.dateSortieMax)){
            $log.debug("==dateSortieMax "+bonSortieCourant.dateSortieMax);
            
            if(bonSortieCourant.dateSortieMax != ""){
              newdateSortieMaxFormat = formattedDate(bonSortieCourant.dateSortieMax);
              $log.info("===== newdateSortieMaxFormat "+newdateSortieMaxFormat);
            }else{
              $log.info("===== newdateSortieMaxFormat is Null");
              newdateSortieMaxFormat = "";
            }
          }else{
            $log.debug("==dateSortieMax Undefined");
          }

                         $log.debug("-- bonSortieCourant" + JSON.stringify($scope.bonSortieCourant, null, "  ") );
                         var url;
                            if($scope.bonSortieCourant.partieInt == null){
           
//                              var url = UrlAtelier + "/report/listbonsortie?reference=" + bonSortieCourant.reference
//                                                       + "&dateSortieMin="+newdateSortieMinFormat
//                                                       + "&dateSortieMax="+newdateSortieMaxFormat
//                                                       + "&typeBonSortie="+bonSortieCourant.type
//                                                       + "&partieInt="
//                                                       +"&rempli="+bonSortieCourant.rempli
//                                                       + "&type=pdf";
          }else{
//            var url = UrlAtelier + "/report/listbonsortie?reference=" + bonSortieCourant.reference
//                                                       + "&dateSortieMin="+newdateSortieMinFormat
//                                                       + "&dateSortieMax="+newdateSortieMaxFormat
//                                                       + "&typeBonSortie="+bonSortieCourant.type
//                                                       + "&partieInt="+bonSortieCourant.partieInt
//                                                       +"&rempli="+bonSortieCourant.rempli
//                                                       + "&type=pdf";
          }

                         downloadService.download(url).then(
                                 function(success) {
                                     $log.debug('success : ' + success);
                                 }, function(error) {
                                     $log.debug('error : ' + error);
                                 });
                     };
                        
                
                        

            }
        ])
    //end controller



;
