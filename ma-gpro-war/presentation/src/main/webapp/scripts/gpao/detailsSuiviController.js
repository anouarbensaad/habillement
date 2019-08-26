'use strict'
angular
    .module('gpro.detailsSuivi', ["ui.sortable"])
  
    .controller(
        'detailsSuiviController', [
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
                $scope.listCodesOperations = [];
                

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
                                    $scope.detailsSuiviCourante.ofId = resultat.id;
                                    $scope.getListOperations("OF",$scope.detailsSuiviCourante.ofId);
                                if(type == 'SEARCH')
                                        {
                                        $scope.detailsSuiviCourante.ofId = resultat.id;
                                        $scope.getListOperations("OF",$scope.detailsSuiviCourante.ofId);
                                       
                                        }
                             

                            
                                    
                                }


                            });


                }
                
                $scope.ofChanged = function ()
                {
                    $scope.numOfVerifier = "false";
                    $scope.detailsSaisieCourante.ofId = '';
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
                $scope.listeProduit();
                
                /**************************************************/
                //operation
                $scope.operationByCode = function(operationCode) {
                    $http
                        .get(baseUrlGpao + "/catalogue/getByCode?operationCode"+operationCode)
                        .success(
                            function(data) {
                                $scope.operation = data;
                                console.log(" ** **$scope.operationByCode:operation ** ** "+JSON.stringify($scope.operation));
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
               // $scope.listeOF();


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
                      						+"&ofId="+$scope.detailsSuiviCourante.ofId
                      						+"&produitId="+$scope.detailsSuiviCourante.produitId
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

