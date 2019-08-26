'use strict'

angular
  .module('gpromoteApp.authController', [])
  .controller('AuthController',
    ['$scope', 
     '$global',
     '$routeParams',
     '$http', 
     '$location',
     'LoginFactory',
     '$rootScope',
     'UserService',
     'localStorageService',
    function ($scope, $global, $routeParams, $http, $location, LoginFactory, $rootScope, UserService,localStorageService) {
    $global.set('fullscreen', true);

    $scope.$on('$destroy', function () {
      $global.set('fullscreen', false);
    });
    
    $scope.login = function () {
    	LoginFactory.login($scope.username, $scope.password, function (response) {
    		
    		
    		UserService.getByEmail($scope.username).then(function(user) {
    			console.log("user "+ user.prenom, user.nom);
    			$rootScope.userLogged=user;
    			$rootScope.isAuthentified =true;
        		$location.path("/");

    		}, function(error) {
    			console.log(error.statusText);
    		});
    		

		}, function(data, status, headers, config) {
			// Failure handler
			$scope.loginError=true;
			console.log('Something went wrong while trying to login... ');
		});
	};
	
//    $scope.login = function(){
//		var data = "j_username="+$scope.username+"&j_password="+$scope.password+"&submit=Login";
//		$http.post('j_spring_security_check', data, {
//			  headers: {
//			    'Content-Type': 'application/x-www-form-urlencoded',
//			  }
//		}).
//	    success(function(data, status, headers, config) {
//	    	console.info("You're now logged in, welcome "+$scope.username);
////	    	jQuery('#userLogin').hide();
////			jQuery('#userInfo').show();
//	    }).
//	    error(function(data, status, headers, config){
//	    	console.warn('This is a wrong username or/and a wrong password. Try again');
////	    	jQuery('#loginAlert').html("Wrong username or password !");
////	    	jQuery('#loginAlert').show();
////	    	setTimeout(function(){jQuery('#loginAlert').hide();},4000);
//	    });
//	};
    
  }])