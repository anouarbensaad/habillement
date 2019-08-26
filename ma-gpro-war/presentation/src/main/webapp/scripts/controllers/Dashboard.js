'use strict'

angular.module('theme.dashboard',  [])
  .controller('DashboardController', ['$scope','$http','$log','baseUrl','baseUrlGpao', function ($scope, $http,$log, baseUrl, baseUrlGpao) {

    $scope.clientValeur = "";
    $scope.clientUnite = "";
    $scope.produitValeur = "";
    $scope.produitUnite="";
    $scope.stockValeur = "";
    $scope.stockUnite="";
    $scope.productionValeur = "";
    $scope.productionUnite="";
    
    //initInfo
    var initBaseInfo = function() {
      $http.get(baseUrl + "/baseinfo/getAll").success(
          function(data) {
              angular.forEach(data , function(infoBaseElement, key){
              
                  switch (infoBaseElement.designation) {
                      case "Stock":
                        $scope.stockValeur = infoBaseElement.valeur;
                        $scope.stockUnite = infoBaseElement.unite;
                      break;
                      case "Production":
                        $scope.productionValeur = infoBaseElement.valeur;
                        $scope.productionUnite = infoBaseElement.unite;
                      break;
                      case "Produit":
                        $scope.produitValeur = infoBaseElement.valeur;
                        $scope.produitUnite = infoBaseElement.unite;
                      break;
                      case "Client":
                        $scope.clientValeur = infoBaseElement.valeur;
                        $scope.clientUnite = infoBaseElement.unite;
                      break;
                    }
              });
          });
    }

    initBaseInfo();
    
   

    // AmChart Client
    var result = [];
    $scope.totalClient = 0;                          
    $http.post(baseUrlGpao+"/aBCArticleDetailEtapeJour/rechercheMulticritere", {})
         .success(function(data, status, headers, config) {
      
      
      $scope.totalClient = data.nombreResultaRechercher;
      if(data.nombreResultaRechercher != 0){
      
      for (var i = 0; i < data.nombreResultaRechercher; i++) {
    		  
        result.push({
            "client": data.list[i].clientAbrevation,
            "qte": data.list[i].qte
                  })  
      }

      }
      
      var chart = AmCharts.makeChart("chartClient", {
              "type": "pie",
              "startDuration": 3,
              "theme": "light",
              "addClassNames": true,
              "legend":{
                        "position":"top"
                        },
              "innerRadius": "30%",
              "defs": {
                "filter": [{
                            "id": "shadow",
                            "width": "200%",
                            "height": "200%",
                            "feOffset": {
                              "result": "offOut",
                              "in": "SourceAlpha",
                              "dx": 0,
                              "dy": 0
                            },
                            "feGaussianBlur": {
                              "result": "blurOut",
                              "in": "offOut",
                              "stdDeviation": 5
                            },
                            "feBlend": {
                              "in": "SourceGraphic",
                              "in2": "blurOut",
                              "mode": "normal"
                            }
                          }]
                      },
              "dataProvider": data.list,
              "valueField": "qte",
              "titleField": "clientAbrevation",
              "export": {
                          "enabled": false
                          }
            });

    });

    //AmChart VProd
    var resultatVProd = [];
    $scope.totalVProd = 0;
    $http.post(baseUrlGpao+"/production/rechercheMulticritere",{})
         .success(function(data, status, headers, config) {
        var c = [];
        $scope.totalVProd = data.nombreResultaRechercher;                      
        if(data.nombreResultaRechercher != 0){                      
        for (var i = 0; i < data.list.length; i++) {
          for (var j = 0; j < 9; j++) {

          c.push({

            "heure": data.list[i].listProductionElement[j].heure,
            "chaine":data.list[i].chaineId,
            "prod": data.list[i].listProductionElement[j].prod,
            "color":"#3F51B5"
                  });  
          }
        }
      }

    c.reduce(function (res, value) {
                            
      if (!res[value.heure]) {
        
          res[value.heure] = {
              
              heure: value.heure,
              prod: 0,
              color:"#3F51B5"
          };

          resultatVProd.push(res[value.heure]);
      }

      res[value.heure].prod += value.prod;
      return res;
    }, {});

    var chart = AmCharts.makeChart( "chartVProd", {
      "type": "serial",
      "theme": "light",
      "dataProvider": resultatVProd,
      "valueAxes": [ {
        "gridColor": "#FFFFFF",
        "gridAlpha": 0,
        "dashLength": 5,
        "title": "Quantité de Production"
      } ],
      "gridAboveGraphs": false,
      "startDuration": 3,
      "graphs": [ {
        "balloonText": "[[category]]: <b>[[value]]</b>",
        "fillAlphas": 1,
        "lineAlpha": 0,
        "type": "column",
        "valueField": "prod"
      } ],
      "chartCursor": {
        "categoryBalloonEnabled": false,
        "cursorAlpha": 0,
        "zoomable": false
      },
      "categoryField": "heure",
      "categoryAxis": {
        "gridPosition": "start",
        "gridAlpha": 0,
        "tickPosition": "start",
        "tickLength": 20,
        "title": "Nombre d'heure de Production"
      },
      "export": {
        "enabled": false
      }

    });

});
}]);

