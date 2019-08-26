'use strict'
angular
    .module('gpro.importationDesDor', ["ui.sortable", 
                                    "ngResource"])
    .controller(
        'importationCmdDesDorController', [
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
				/*
				 * 
				 */
            	 $scope.listeDetailsCSV = undefined;
                 $scope.listOfDistinctMatricules=[];
                 $scope.alertCreat = false ;
             	 $scope.msg = "liste des commande rejete" ;
                 
            	$scope.listeChaineAtelier = function() {
					$http
					.get( 
						baseUrl 
						+"/sitePartieIntersse/all")
					.success(
						function(resultat) {
							$scope.listeChaineAtelier = resultat;

						});
				}
				$scope.listeChaineAtelier();
				$scope.reset  = function (){
					delete $scope.myData ;
					$scope.listeDetailsCSV= undefined ;
					$scope.detailsSaisieTRForm.$pristine;
					$scope.nbreMatricules = 0 ;
					$scope.nbreCodesABarres = 0 ; 
					$scope.alertCreat=false
				}
				
				
				$scope.importCommande = function() {
					var ordreF = $scope.listeDetailsCSV ;
					ordreF[0].atelier = $scope.listeChaineAtelier.atelier ;
					ordreF[0].clientParametre = 'DESDOR' ;
					 
							$http
							.post(
									baseUrlGpao
											+ "/CommandeCSV/creer", ordreF )
							.success(
                                    function(data) {
                                    	if(data != ''){
                                    	$scope.alertCreat = true ;
                                    	$scope.msg="Creation Impossible , Verifier les numero d'Ordre de fabrication!! ";
                                    	$scope.nbreCodesABarres = data.length;
         		                        
                                    		
                                    	var liste  = $scope.listeDetailsCSV ;
                                    	$scope.listeDetailsCSV = [];
                                    	for(var i = 0; i<  liste.length; i++) {
                                    		var element = liste[i] ;
                                    		if (data.indexOf(element.code) != -1) {
                                    			
                                    			$scope.listeDetailsCSV.push(element);
                                    		}
                                    	}	
                                    	$scope.nbreMatricules = $scope.listeDetailsCSV.length;
                                    	$scope.myData = $scope.listeDetailsCSV ;							
                                    	$scope
										.setPagingData(
												$scope.myData,
												$scope.pagingOptions.currentPage,
												$scope.pagingOptions.pageSize);

                                    	}
                                    });
                                    
                                    }
                
                $scope.$watch(
		                    "csv.result",
		                    function handleCSVResultChange( newValue, oldValue ) {
		                      
		                        $scope.listeDetailsCSV = [];
		                        $scope.listOfDistinctMatricules=[];
		                        var i = 1;
		                        //ListeCode à barre 
		                        angular.forEach(newValue, function(ligneCsv, key){
								
								if(ligneCsv[0].length >0){
									var formattedLine = ligneCsv[0].split(";");

			                        	var element = {
			                        		pos:'',
			                        		code:'',
			                        		description:'',
			                        		
			                        		quantite: '',
			                        		iman: '',
			                        		numeroInterne: '',
			                        		prixUnitaire: '',
			                        		dateFin: ''
			                        		
			                        		
			                        	}
			                        	/*
			                        	private String dateSaisie;
			                        	private String code;
			                        	private String description;
			                        	private String taille;
			                        	private String iman;
			                        	private Long quantite;
			                        	private String type;
			                        	private String atelier;
			                        	private String typeOrdre;
			                        	private String lieuLivraison;
			                        	private String dateLivraison;
			                        	private String observations;

			                        	private String ;
			                        	private Double ;
			                        	private Calendar dateFin;
*/
			                        	var date;
			                    		
			                    		if(formattedLine[6].indexOf("/") != -1){
			                    			 date = formattedLine[6].split('/');
			                    		}else if(formattedLine[6].indexOf("-")!=-1){
			                    			 date = formattedLine[6].split('-');
			                    		};
			                    		
			                    		var day = date[0];
			                    		var month = date[1];
			                    		var year = date[2];
			                    		
			                    		var calendarDate = new Date(year, month-1 ,day);
			                    		
                                        element.dateSaisie = calendarDate.toLocaleDateString();
			                    		element.pos = i++;
			                        	element.code = formattedLine[0];
			                        	element.description = formattedLine[2];
			                        	element.numeroInterne = formattedLine[1];
			                        	element.iman= formattedLine[7];
			                        	element.quantite= formattedLine[3];
			                        	element.prixUnitaire = formattedLine[4];
			                        	//element.dateFin = formattedLine[6];
			                        	$scope.listeDetailsCSV.push(element);
			                        	
			                        	
			                        	if( $scope.listOfDistinctMatricules.indexOf(element.code) == -1){
			                        		$scope.listOfDistinctMatricules.push(element.code);
			                        	}
								}

		                        });
		                        $scope.myData = $scope.listeDetailsCSV ;
		                        $scope.nbreCodesABarres = $scope.listeDetailsCSV.length;
		                        $scope.nbreMatricules = $scope.listOfDistinctMatricules.length; 
		                        $scope
								.setPagingData(
										$scope.myData,
										$scope.pagingOptions.currentPage,
										$scope.pagingOptions.pageSize);
		                    }
		                );
                
              
              
        
            
              /******************************************************************************/
              $scope.isLoading=false;
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
		       
             
		        $scope.filterOptions = {
						filterText : "",
						useExternalFilter : true
					};

					$scope.totalServerItems = 0;
					
					$scope.setPagingData = function(data, page,
							pageSize) {
						var pagedData = data.slice((page - 1)
								* pageSize, page * pageSize);
						$scope.myData = pagedData;
						$scope.totalServerItems = data.length;
						if (!$scope.$$phase) {
							$scope.$apply();
						}
					}; 
                



					$scope
							.$watch(
									'myData',
									function() {
										$scope.colDefs = [
											     {
													field : 'pos',
													displayName : '',
													width: '3%'
													
												},          
												{
													field : 'code',
													displayName : 'Numero ',
													width: '13%'
												},
												{
													field : 'numeroInterne',
													displayName : 'numero Interne',
													width: '12%'
												},
												{
													field : 'description',
													displayName : ' Designation',
													width: '30%'
												},
												
												{
													field : 'quantite',
													displayName : 'quantite',
													width: '10%'
												},
												
												{
													field : '',
													displayName : ' temps',
													width: '7%'
													
												},
												{
													field : 'prixUnitaire',
													displayName : ' prix',
													width: '10%'
													
												},
												{
													field : 'dateSaisie',
													displayName : 'Delai ',
													width: '13%'
												}];
									});

					$scope.getPagedDataAsync = function(pageSize, page,
							searchText) {
						setTimeout(
								function() {
									var ft = searchText.toLowerCase();
									var data;
									data =  $scope.listeDetailsCSV
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
									} , 100);
					};
					
					$scope.pagingOptions = {
						    pageSizes: [10, 20, 50, 100],
						    pageSize: 10,
						    currentPage: 1
						};

					$scope.getPagedDataAsync(
							$scope.pagingOptions.pageSize,
							$scope.pagingOptions.currentPage);

					$scope
							.$watch(
									'pagingOptions',
									function(newVal, oldVal) {
										if (newVal !== oldVal
												&& newVal.currentPage !== oldVal.currentPage) {
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
								     data : 'myData',
									columnDefs : 'colDefs',
									 enableCellSelection: true,
									 enableCellEditOnFocus: true,
									 cellEditableCondition: 'row.entity.editable',
							        enableRowSelection: false,
							        enablePaging: true,
							        headerRowHeight: 45,
							        rowHeight: 45,
							        enableColumnResize: true,
							        showFooter: true,
							        totalServerItems: 'totalServerItems',
							        pagingOptions: $scope.pagingOptions,
							        showFilter: true,
							        filterOptions: $scope.filterOptions,
							        useExternalSorting: true // for some reason, with this, the sorting doesn't works at all
							    };
             }
        ])
    //end controller

;

