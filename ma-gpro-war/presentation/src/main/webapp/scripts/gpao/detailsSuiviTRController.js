'use strict'
angular
    .module('gpro.detailsSuiviTR', ["ui.sortable", "ngResource", "ngCsvImport"])
  
    .controller(
        'detailsSuiviTRController', [
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

            	
            	//listChaines
                $scope.listeChaines = function() {
                    $http
                        .get(baseUrlGpao + "/chaine/all")
                        .success(
                            function(dataProduit) {
                                $scope.listChaine= dataProduit;
                            });
                }
                $scope.listeChaines();
                
                $scope.$watch(
		                    "csv.result",
		                    function handleCSVResultChange( newValue, oldValue ) {
		                      
		                        $scope.listeDetailsCSV = [];
		                        $scope.listOfDistinctMatricules=[];
		                        
		                        //ListeCode à barre 
		                        angular.forEach(newValue, function(ligneCsv, key){
								
								if(ligneCsv[0].length >0){
									var formattedLine = ligneCsv[0].split(";");

			                        	var element = {
			                        		code:'',
			                        		matricule:'',
			                        		dateSaisie:''
			                        	}
			                        	
			                        	element.code = formattedLine[0];
			                        	element.matricule = formattedLine[2];
			                        	element.dateSaisie= formattedLine[3];

			                        	$scope.listeDetailsCSV.push(element);
			                        	
			                        	
			                        	if( $scope.listOfDistinctMatricules.indexOf(element.matricule) == -1){
			                        		$scope.listOfDistinctMatricules.push(element.matricule);
			                        	}
								}

		                        });
		                        
		                        $scope.nbreCodesABarres = $scope.listeDetailsCSV.length;
		                        $scope.nbreMatricules = $scope.listOfDistinctMatricules.length; 
		                        
		                    }
		                );
                
                var matriculeExistence = function(matriculeId){
					var result = $filter('filter')(listeFeuillesSaisies, {personnelId:matriculeId});
 
					if(result && result.length>0){
						return true;
					}
					
					return false;
                }
                
                $scope.listeFeuillesSaisies = [];

                $scope.ajoutOuUpdateFeuilleSaisie = function(){
                	
                	var feuilleSaisieTR = {};
              	  
                	feuilleSaisieTR.chaineId = $scope.detailsSuiviTRCourante.chaine;
                	feuilleSaisieTR.minPresence = $scope.detailsSuiviTRCourante.minPresence;
                	
                	var liste = [];
                	liste = Object.assign([], $scope.listeDetailsCSV);

                	for(var i=0; i<liste.length ; i++){
                		
                		var element = liste[i];
                		
                		var date;
                		
                		if(element.dateSaisie.indexOf("/") != -1){
                			 date = element.dateSaisie.split('/');
                		}else if(element.dateSaisie.indexOf("-")!=-1){
                			 date = element.dateSaisie.split('-');
                		}else{
                			console.log("error format date");
                		}
                		
                		var day = date[0];
                		var month = date[1];
                		var year = date[2];
                		
                		var calendarDate = new Date(year, month, day);
                		
                		element.date = calendarDate;
                		
                	}
                	
                	feuilleSaisieTR.listSaisieTR = liste;
                	console.log("feuilleSaisieTR"+ JSON.stringify(feuilleSaisieTR, null," "));

                    $http.post(baseUrlGpao + "/feuilleSaisie/addOrUpdateFeuilleSaisieTR", feuilleSaisieTR)
                            .success(
                                    function(data) {
                                        console.log("MAJ avec success");
                                        
                                    });
                    
                }
       
                
                
                // Déclaration des variables globales utilisées
                /** ***Liste des Variables **** */
                var data;

                $scope.displayMode = "list";
                $scope.detailsSaisieCourante = {};
                
               

                /** ****la liste des Produits ******** */

                $scope.listProduitDrop = [];
                $scope.listCodesOperations = [];
                

                // declaration variable
                $scope.displayAlert = "sleep";

       
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
                $scope.listeProduit();
                
                /**************************************************/
                //operation
                $scope.operationByCode = function(operationCode) {
                    $http
                        .get(baseUrlGpao + "/catalogue/getByCode?operationCode"+operationCode)
                        .success(
                            function(data) {
                                $scope.operation = data;
                            });
                }
                

                // Liste des OF
                $scope.listeOFDrop = [];
                $scope.listeOF = function() {
                    $http
                        .get(baseUrlGpao + "/ordreFabrication/all")
                        .success(
                            function(data) {
                            	var operation={};
                                $scope.listeOFDrop = data;
//                                console.log(" ** **$scope.listeOFDrop = data ** ** "+JSON.stringify( $scope.listeOFDrop));
//                                for (var i=0;i<$scope.listeOFDrop.length;i++){
//                                	
//                             operation = $scope.operationByCode($scope.listeOFDrop[i].numero);
//                                  console.log(" ** **oprationdesign ** ** "+operationdesign);
//
//                                }

                            });
                }
                $scope.listeOF();


                /** ********************************************* */
             
               
//                // Liste des codes
                $scope.listeCodesOperations = function(webServiceMethod) {
                    $http
                        .get(baseUrlGpao + "/catalogue" + webServiceMethod)

                        .success(
                            function(data) {
                            	$scope.listCodesOperations = [];
                                $scope.listCodesOperations = data;
                            });
                }
//                $scope.listeCodesOperations();

        
              $scope.getListOperations=function(type, param){
            	  switch(type){
            	  case "OF" :var  webServiceMethod = "/getAllByOFId?OFId=" + param;
            	  			$scope.detailsSuiviCourante.produitId="";
            	  			break;
            	  			
            	  case "PRODUIT" : var webServiceMethod = "/getAllByProduit?produitId=" + param;
            	  				   $scope.detailsSuiviCourante.ofId ="";
            	  				   break;
            	  				   
            	  }
            	  
            	  $scope.listeCodesOperations(webServiceMethod);
            	  
              }
              
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

              $scope.getSommeQteProduite= function(){
            	  $scope.isLoading=true;
            	  $http
                  .get(
                      baseUrlGpao +
                      "/detailFeuilleSaisie/getSommeQteProduite?operationId="+$scope.detailsSuiviCourante.operation
                      						+"&dateMin="+formattedDate($scope.detailsSuiviCourante.dateMin)
                      						+"&dateMax="+formattedDate($scope.detailsSuiviCourante.dateMax)
                      )
                      

              .success(
                  function(resultat) {
                	  $scope.qteProduite= resultat;
                	  $scope.isLoading=false;
                  });
              }
                
                
                // Annulation de l'ajout
                $scope.annulerAjout = function() {
                    
                    $scope.detailsSaisieCourante = {
                    		
    						  "ofId": "",
    						  "dateMin": "",
    						  "dateMax": "",
    						  "operation":""
                    };
                    
                    $scope.qteProduite="";
                   
                   }
            
             }
        ])
    //end controller

;

