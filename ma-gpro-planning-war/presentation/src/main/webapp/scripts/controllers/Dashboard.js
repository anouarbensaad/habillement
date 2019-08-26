'use strict'

angular.module('theme.dashboard',  [])

  .controller('DashboardController', [
      '$scope', 
      '$rootScope',
      '$location',
      function ($scope, $rootScope,$location) {
    	  
    	// init tiles
        
        $scope.tile1 = { text: 'Reporting',color: 'inverse', classes: 'fa fa-calendar' };
        $scope.tile2 = { text: 'Reponse Client',color: 'danger', classes: 'fa fa-calendar' };
        $scope.tile3 = { text: 'Commandes',color: 'success', classes: 'fa fa-calendar' };
        $scope.tile4 = { text: 'Planning',color: 'info', classes: 'fa fa-calendar' };
     
        $scope.tile6 = { text: 'Traciblité',color: 'primary', classes: 'fa fa-calendar' };
        $scope.tile7 = { text: 'Parametrage',color: 'surprise', classes: 'fa fa-calendar' };
        $scope.tile8 = { text: 'capactieglobal',color: 'warning', classes: 'fa fa-calendar' };
        $scope.tile9 = { text: 'capactieclient',color: 'primary', classes: 'fa fa-calendar' };
        
        $scope.tile10 = { text: 'importation',color: 'primary', classes: 'fa fa-calendar' };
        $scope.tile5 = { text: 'Production',color: 'warning', classes: 'fa fa-calendar' };
        $scope.tile12 = { text: 'Collisage',color: 'success', classes: 'fa fa-calendar' };
        $scope.tile11 = { text: 'Suivi Commande',color: 'inverse', classes: 'fa fa-calendar' };
        
        $scope.tile13 = { text: 'Envoi',color: 'info', classes: 'fa fa-calendar' };
        $scope.tile14 = { text: 'Réception',color: 'info', classes: 'fa fa-calendar' };
        $scope.tile15 = { text: 'Etat',color: 'danger', classes: 'fa fa-calendar' };

        $scope.goToProduction = function(){
            $location.path('parametrage/productionJour');
          }
        
        $scope.goToReport = function(){
            $location.path('/commercial/productionJourReport');  
          }
          $scope.goToImport = function(){
              $location.path('/commercial/detailsSuiviTR');
            }
          
          $scope.goToSuiviCommande = function(){
              $location.path('commercial/ConfirmationLivraison');
            }
    	  
          $scope.goToCollisage = function(){
              $location.path('commercial/collisage');
            }
          $scope.goToMvtEnvoi = function(){
              $location.path('commercial/collisage');
            }
          $scope.goToMvtReception = function(){
              $location.path('commercial/collisage');
            }
          $scope.goToMvtEtat = function(){
              $location.path('commercial/collisage');
            }
          
          
    	  $scope.todayDate = new Date();
    	  
    	  
    	  $scope.goToPlanning = function(){
    		  $location.path('/commercial/planning');
    	  }
        $scope.goToCommandes = function(){
          $location.path('/commercial/commandes');
        }
         $scope.goToReponse = function(){
          $location.path('/commercial/reponseClient');
        }
  

          /*$scope.goToTracabilite = function(){
          $location.path('/commercial/productionJourReport');
        }*/
        /*$scope.goToReport = function(){
          $location.path('/parametrage/productionJour');
        }*/
  }])
  
    

