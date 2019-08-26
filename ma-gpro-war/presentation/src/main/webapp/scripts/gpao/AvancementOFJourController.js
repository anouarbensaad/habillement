

'use strict'
angular
.module('gpro.popupAvancementOFJour', [])
.controller('AvancementJourController', ['$rootScope','$scope', '$modal', '$bootbox', '$log','$filter','baseUrl','$http','$routeParams','popCouleurTailleVenteService' ,
	function ($rootScope,$scope, $modal, $bootbox, $log,$filter,baseUrl,$http, $routeParams,popCouleurTailleVenteService) {
		$log.info("----------- MyPopup_Avancement_Jour ----------");
										
		$scope.items = ['item1', 'item2', 'item3'];
		
		$scope.listeColor=[];
		$scope.listeTaille =[];
		$scope.listJourProductionGammeOf = [];
		$scope.open = function (size,indexLigne,listJourProductionGammeOf,mode) {
			var modalInstance = $modal.open({
				templateUrl: 'quantiteJourPopUp.html',
				controller: function ($scope, $modalInstance, items) {
				$scope.listJourProductionGammeOf = [];

				$log.info("**listJourProductionGammeOf : "+JSON.stringify(listJourProductionGammeOf,null,"  "));
				$scope.listJourProductionGammeOf = listJourProductionGammeOf;


				$scope.cancel = function () {
					listJourProductionGammeOf = [];
					$scope.listJourProductionGammeOf = [];
					$modalInstance.dismiss('cancel');
				};
			},
			size: size,
			resolve: {
				items: function () {
					return $scope.items;
				}
			}
		});

			modalInstance.result.then(function (selectedItem) {
				$scope.selected = selectedItem;
			}, function () {
				$log.info('Modal dismissed at: ' + new Date());
			});
		};

		$scope.openDemoModal = function (size) {
			var modalInstance = $modal.open({
				templateUrl: 'demoModalContent.html',
				controller: function ($scope, $modalInstance) {
					$scope.cancel = function () {
						$modalInstance.dismiss('cancel');
					};
				},
				size: size,
			});
		};

	}])
