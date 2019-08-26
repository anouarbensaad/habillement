'use strict'
angular
    .module('gpro.importationCmdForcast', ["ui.sortable", 
                                    "ngResource"])
  
    .controller(
        'importationCmdForcastController', [
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
             	$scope.alertCreatSuc = false;
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
					$scope.alertCreat=false;
					$scope.alertCreatSuc = false;
				}
				
				
				$scope.importCommande = function() {
					$scope.alertCreat = false;
					$scope.alertCreatSuc = false;
					console.log("$scope.listeDetailsCSV",$scope.listeDetailsCSV);
					var ordreF = $scope.listeDetailsCSV ;
					ordreF[0].atelier = $scope.listeChaineAtelier.atelier ;
					 
							$http
							.post(
									baseUrlGpao
											+ "/CommandeCSV/creerForcast", ordreF )
							.success(
                                    function(data) {
                                    	$scope.alertCreat = false ;
                                    	$scope.alertCreatSuc = true;
                                    	
                                    	$scope.msg="Importation terminée avec succès ";
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

                                   
                                    })
                                    .error(function(data){
                                    	console.log('######________ERROR________######');
                                    	console.log(data);
                                    	
                                    	$scope.alertCreat = true ;
                                    	$scope.alertCreatSuc = false;
                                    	$scope.msg="Merci de verifier les informations données ";
                                    })	
                                    
                                    }
                
                $scope.$watch(
		                    "csv.result",
		                    function handleCSVResultChange( newValue, oldValue ) {
		                      
		                        $scope.listeDetailsCSV = [];
		                        $scope.listOfDistinctMatricules=[];
								var i = 1;
								var formattedFirstLine;
		                        //ListeCode à barre 
		                        angular.forEach(newValue, function(ligneCsv, key){
									
								if(ligneCsv[0].length >0){

									if (key == 0)
									{
										 formattedFirstLine = ligneCsv[0].split(";");      
									}
								         
									console.log("ligneCsv[0].length = ",ligneCsv[0].length);
									var formattedLine = ligneCsv[0].split(";");
									console.log("formattedLine[0].length = ",formattedLine.length);
			                        	var element = {
			                        		pos:'',
			                        		code:'',
			                        		description:'',
			                        		dateSaisie:'',
			                        		quantite :'',
			                        		iman :'',
			                        		taille :'',
			                        		type :'',
			                        		typeOrdre:'',
			                        		lieuLivraison:'',
			                        		prixUnitaire:''			                        		
			                        		
			                        	}
			                        /*	var date;
			                    		
			                    		if(formattedLine[0].indexOf("/") != -1){
			                    			 date = formattedLine[0].split('/');
			                    		}else if(formattedLine[0].indexOf("-")!=-1){
			                    			 date = formattedLine[0].split('-');
			                    		};
			                    		
			                    		var day = date[0];
			                    		var month = date[1];
			                    		var year = date[2];
			                    		
			                    		var calendarDate = new Date(year, month-1 ,day);
			                    		
                                        var date2;
			                    		
			                    		if(formattedLine[4].indexOf("/") != -1){
			                    			 date2 = formattedLine[4].split('/');
			                    		}else if(formattedLine[4].indexOf("-")!=-1){
			                    			 date2 = formattedLine[4].split('-');
			                    		};
			                    		
			                    		var day2 = date2[0];
			                    		var month2 = date2[1];
			                    		var year2 = date2[2];
			                    		
			                    		var calendarDate2 = new Date(year2, month2-1 ,day2);
			                    		
										element.dateLivraison = calendarDate2.toLocaleDateString();
										
										element.dateSaisie = calendarDate.toLocaleDateString();
                                      */

									 var calendarDatIntroduction = new Date();
									element.dateSaisie = calendarDatIntroduction.toLocaleDateString();
									  if(key > 0)
									  {
                                        for (var j =5;j<formattedFirstLine.length-1;j++)
										{

											//var calendarDate = new Date(year, month-1 ,day);
											
											var yearWeek = formattedFirstLine[j];
											var yearString = yearWeek[0]+yearWeek[1]+yearWeek[2]+yearWeek[3];
											var weekString = yearWeek[4]+yearWeek[5];

											var yearNumber = Number(yearString);
											var weekNumber = Number(weekString);
									
                                            var d = (1 + (weekNumber - 1) * 7+6); 
											var calendarDatLivraison = new Date(yearNumber, 0, d);
											
											
											
											element.dateFin = calendarDatLivraison;
											element.dateLivraison = calendarDatLivraison.toLocaleDateString();

											element.pos = i++;
											element.code = formattedLine[0]+'/'+formattedFirstLine[j];
											element.description = formattedLine[2];
											element.taille= formattedLine[3];
											element.quantite= formattedLine[j];
											element.type= 'Forecast';
											
											element.semaine = formattedFirstLine[j];

											element.iman= formattedLine[0];
											//element.prixUnitaire = formattedLine[4];
											//element.typeOrdre= formattedLine[5];
											//element.lieuLivraison=formattedLine[18];

											var finalElement = angular.copy(element);
											$scope.listeDetailsCSV.push(finalElement);

											if( $scope.listOfDistinctMatricules.indexOf(finalElement.code) == -1){
												$scope.listOfDistinctMatricules.push(finalElement.code);
											}
											
										}
			                    
			                        	
			                        	
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
													displayName : 'N°',
													width: '5%'
													
												},          
												/*{
													field : 'dateSaisie',
													displayName : 'D.Saisie ',
													width: '13%'
												},*/
												{
													field : 'semaine',
													displayName : 'Semaine ',
													width: '15%'
												},
												{
													field : 'iman',
													displayName : ' Reference',
													width: '20%'
												},
												{
													field : 'description',
													displayName : ' Designation',
													width: '40%'
												},
												{
													field : 'taille',
													displayName : 'Taille',
													width: '10%'
												},
												{
													field : 'quantite',
													displayName : ' Quantite',
													width: '10%'
													
												}/*,
												
												{
													field : 'code',
													displayName : 'OF',
													width: '12%'
												},
											
											
											
												
											
												{
													field : 'typeOrdre',
													displayName : ' Type Commande',
													width: '10%'
													
												},
												{
													field : 'dateLivraison',
													displayName : 'date de Livraison ',
													width: '13%'
												},
												{
													field : 'lieuLivraison',
													displayName : 'Lieu de saisie ',
													width: '15%'
												},
												{
													field : 'type',
													displayName : ' type',
													width: '9%'
												}*/];
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

