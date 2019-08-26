

'use strict'
angular
.module('gpro.popupAvancementOFTailleCouleur', [])
.controller('AvancementTailleCouleurController', ['$rootScope','$scope', '$modal', '$bootbox', '$log','$filter','baseUrl','$http','$routeParams','popCouleurTailleVenteService' ,
	function ($rootScope,$scope, $modal, $bootbox, $log,$filter,baseUrl,$http, $routeParams,popCouleurTailleVenteService) {
		$log.info("----------- MyPopup_Avancement_TailleCouleur ----------");
										
		$scope.items = ['item1', 'item2', 'item3'];
		
		$scope.listeColor=[];
		$scope.listeTaille =[];
		$scope.listTailleCouleurGammeOf = [];
		$scope.open = function (size,indexLigne,listTailleCouleurGammeOf,mode) {
			var modalInstance = $modal.open({
				templateUrl: 'tailleCouleurPopUp.html',
				controller: function ($scope, $modalInstance, items) {
				$scope.listTailleCouleurGammeOf = [];

				$log.info("**listTailleCouleurGammeOf : "+JSON.stringify(listTailleCouleurGammeOf,null,"  "));
				$scope.listTailleCouleurGammeOf = listTailleCouleurGammeOf;
				
				
				$scope.cancel = function () {
					listTailleCouleurGammeOf = [];
					$scope.listTailleCouleurGammeOf = [];
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