'use strict'
angular.module('gpro.quantiteScChart',[])
.service('QualiteService',QualiteService);
function QualiteService($http, $q){
		
		var urlBase="http://localhost:8080/ma-gpro-gs-rest/mouvementStockChart/rechercheMulticritere";
	  
	  
		$scope.getQualiteS =function(){
			var obj=[{
			    
			    "type": "SORTIE",
			  "dateFrom" : "2016-02-05",
			  "dateTo" : "2016-03-01"
			}];
			
			var res = $http.post(urlBase, Obj);
			res.success(function(data) {
				console.log(data);
				return data;
				
			});
			res.error(function(data) {
				alert( "failure message: " );
			});
			
		};
	};