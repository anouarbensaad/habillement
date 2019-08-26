'use strict'

angular
    .module('gpro.detailssaisie', ["ui.sortable"])
  
    .controller(
        'DetailsSaisieController', [
            '$scope',
            '$http',
            '$filter',
            'downloadService',
            'baseUrlGpao',
            'baseUrl',
            '$rootScope',
            '$timeout',
            

            function($scope, $http,  $filter,
                downloadService, baseUrlGpao, baseUrl,
                $rootScope,$timeout) {

                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.detailsSaisieCourante = {};
                
               

                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
                $scope.listMatricule = [];
                $scope.listCodes = [];
                

                // declaration variable
                $scope.displayAlert = "sleep";

                $scope.myStyleBtnOF = {
									
                    "background-color" : "green"
             
                };
                $scope.numOfVerifier = "false";

                $scope.validerOF = function (numOf,type) {

                    //console.log("sssssssssssssssssssssssssss");

                    if (numOf == '') return;

                     $scope.numOfVerifier = "false";

                    $http
                        .post(
                            baseUrlGpao +
                            "/ordreFabrication/getByNum", numOf
                        )
                        .success(
                            function (resultat) {
                                if (resultat == '') {
                                    $scope.numOfVerifier = "false";
                                } else {
                                    
                          
                                  
                                    $scope.numOfVerifier = "true";
                                    
                                if(type == 'SEARCH')
                                        {
                                        $scope.detailsSaisieCourante.ofId = resultat.id;
                                 
                                       
                                        }
                             

                            
                                    
                                }


                            });


                }
                
                $scope.ofChanged = function ()
                {
                    $scope.numOfVerifier = "false";
                    $scope.detailsSaisieCourante.ofId = '';
                }
                // Rechercher Gamme
                $scope.rechercheDetailsSaisie = function(detailsSaisieCourante) {
                    //console.log("recherche Gamme ..");
                    $http
                        .post(
                            baseUrlGpao +
                            "/detailFeuilleSaisie/rechercheMulticritere",
                            detailsSaisieCourante)

                    .success(
                        function(resultat) {


                            $scope.myData = resultat.list;
                            // data, page,pageSize
                           //console.log("-----Data Details Saisie:"+JSON.stringify($scope.myData));
                            $scope
                                .setPagingData(
                                    $scope.myData,
                                    $scope.pagingOptions.currentPage,
                                    $scope.pagingOptions.pageSize);

                            $scope.rechercheDetailsSaisieForm
                                .$setPristine();
                            $scope.displayMode = "rechercher";
                            $scope.displayAlert = "sleep";
                        });
                }

                /** ********************************************* */
                // Liste des produits
                $scope.listeProduit = function() {
                    $http
                        .get(baseUrl + "/produit/all")
                        .success(
                            function(dataProduit) {
                                $scope.listProduitDrop = dataProduit;
                            });
                }
                //$scope.listeProduit();
                
                // Liste des produits
                $scope.listeOFDrop = [];
                $scope.listeOF = function() {
                    $http
                        .get(baseUrlGpao + "/ordreFabrication/all")
                        .success(
                            function(dataProduit) {
                                $scope.listeOFDrop = dataProduit;
                            });
                }
              //  $scope.listeOF();

                // Liste des matricules
                $scope.listeMatricule = function() {
                    $http
                        .get(baseUrlGpao + "/feuilleSaisie/listPersonnel")
                        .success(
                            function(dataProduit) {
                                $scope.listMatricule= dataProduit;
                            });
                }
                $scope.listeMatricule();

                /** ********************************************* */
             
               // $scope.rechercheDetailsSaisie({});
               
            

              
             

                
                // Liste des codes
                $scope.listeCodes = function() {
                    $http
                        .get(baseUrlGpao + "/catalogue/getAll")

                        .success(
                            function(dataPartieInteressee) {
                                $scope.listCodes = dataPartieInteressee;
                            });
                }
                $scope.listeCodes();

        
              
              
              /******************************************************************************/


                
                
                // Annulation de l'ajout
                $scope.annulerAjout = function() {
                    
                    $scope.detailsSaisieCourante = {
                    		
                    		  "matricule": "",
    						  "paquetId": null,
    						  "ofId": null,
    						  "dateSaisieMin": "",
    						  "dateSaisieMax": "",
    						  "operation": null,
    						  
                    };
                   
                  
                        
                        $scope.rechercheDetailsSaisieForm.$setPristine();
                        $scope.rechercheDetailsSaisie({});
                        $scope.produitId = {};
                        $scope.displayMode = "list";
                    }
            
                /** Fin de gestion des Gammes */
                /*** PDF ***/
              //generer rapport apres creation d'un bon de sortie. mode : Modification/Consultation
  				$scope.download = function(id) {
					//console.log("-- id"+id);
					var url = baseUrlGpao+"/report/gammeProduit?id=" + id+"&type=pdf";
					downloadService.download(url)
							.then(
									function(success) {
										console.log('success : '+ success);
										//$scope.annulerAjout();
									},
									function(error) {
										$log.debug('error : '+ error);
									});
				};

                //generer rapport de tous les Ordre de Fabrication. mode : List
                
                $scope.downloadAllGammeMontage = function(detailsSaisieCourante) {
                    
                    var url;
                    if(detailsSaisieCourante.produitId == null){
                        detailsSaisieCourante.produitId = "";
                    }
                    //console.log("------- GammeMontageCourant " + JSON.stringify(detailsSaisieCourante, null, "  ") );
                    url = baseUrlGpao + "/report/listGammeProduit?produitId=" + detailsSaisieCourante.produitId
                                         + "&tempsTotalMin=" + detailsSaisieCourante.tempsTotalMin
                                         + "&tempsTotalMax="+detailsSaisieCourante.tempsTotalMax
                                         + "&machineId="+detailsSaisieCourante.machineId
                                         + "&sectionId="+detailsSaisieCourante.sectionId
                                         + "&type=pdf";
                        
                    // console.log("-- URL--- :" + url );
                     downloadService.download(url).then(
                             function(success) {
                                //$log.debug('success : ' + success);
                             }, function(error) {
                                //$log.debug('error : ' + error);
                             });
                 };

                
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
                                    field: 'codeBarre',
                                    displayName: 'Code Barre',
                                    width: '10%'

                                }, {
                                    field: 'paquetId',
                                    displayName: 'Paquet',
                                    width: '5%'

                                }, {
                                    field: 'operation',
                                    displayName: 'Operation',
                                    width: '25%'

                                }, {
                                    field: 'temps',
                                    displayName: 'Temps',
                                    width: '5%'


                                },{
                                    field: 'ordreFabricationNumero',
                                    displayName: 'OF',
                                    width: '10%'


                                },{
                                    field: 'quantite',
                                    displayName: 'Quantite',
                                    width: '8%'


                                },{
                                    field: 'minutage',
                                    displayName: 'Minutage',
                                    width: '7%'


                                },{
                                    field: 'chaine',
                                    displayName: 'Chaine',
                                    width: '10%'


                                },{
                                    field: 'date',
                                    displayName: 'Date',
                                    cellFilter: 'date:\'dd-MM-yyyy\'',
                                    width: '10%'


                                },{
                                    field: 'matricule',
                                    displayName: 'Matricule',
                                    width: '10%'


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
                            var detailsSaisieCourante = $scope.detailsSaisieCourante;
                            if (searchText) {
                                var ft = searchText
                                    .toLowerCase();
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/detailFeuilleSaisie/rechercheMulticritere",
                                        detailsSaisieCourante)
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
                                            $scope.setPagingData(
                                                    data,
                                                    page,
                                                    pageSize);
                                            /*$scope.rechercheDetailsSaisie({});*/
                                        });

                            } else {
                                $http
                                    .post(
                                        baseUrlGpao +
                                        "/detailFeuilleSaisie/rechercheMulticritere",
                                        detailsSaisieCourante)
                                    .success(
                                        function(
                                            largeLoad) {
                                            $scope
                                                .setPagingData(
                                                    largeLoad.list,
                                                    page,
                                                    pageSize);
                                            /*$scope
                                                .rechercheDetailsSaisie({});*/

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
                    totalServerItems: 'totalServerItems',
                    enableHighlighting : true,
                    pagingOptions: $scope.pagingOptions,
                    selectedItems: $scope.selectedRows,
                    filterOptions: $scope.filterOptions,
                };

             

                
               

            }
        ])
    //end controller

;

