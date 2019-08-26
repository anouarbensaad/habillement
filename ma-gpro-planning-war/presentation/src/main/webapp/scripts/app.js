angular.module(
		'gpromoteApp',
		[   'ngAnimate', 
		    'ngCsvImport',
		    'ngRoute', 
		    'ui.sortable',
		    
		   
		    /****** Controllers *************/
		    'gpromoteApp.authController',
		    'gpro.planning',
		    'gpro.gestionnaireDocument',
		    'gpro.capaciteGlobale',
		    'gpro.capaciteClient',
		    'gpro.commandes',
		    'gpro.productionReport',
		    'gpro.productionJour',
		  
		    'gpro.detailsSuiviTR',
				'gpro.importationCmdStandard',
				'gpro.importationCmdForcast',
		    'gpro.importationDesDor',
		    'gpro.reponseClient',
				'gpro.livraison',
				
				'gpro.interrogationStock',
				'gpro.interrogationReporting',

				/******* Mouvement Tusel ************/
				
				'gpro.mouvementEnvoi',
				'gpro.mouvementReception',
				'gpro.mouvementEtat',
				
		    /******* Services ************/
		   
		    'LocalStorageModule',
		    'gpromoteApp.cookiesServices',
		    'gpromoteApp.authServices',
		   // 'gpro.UserServices',
		    'components.donwload',
		    
//		    'nemLogging',
		    'config',
		    'toggle-switch',
		    'ui.bootstrap',
		    'ui.tree',
		    'ui.select2',
		    'ngGrid',
		    'xeditable',
		    'flow',
		    'theme.services',
		    'theme.directives',
		    'theme.navigation-controller',
		    'theme.notifications-controller',
		    'theme.messages-controller',
//		    'theme.colorpicker-controller',
		    'theme.layout-horizontal',
		    'theme.layout-boxed',
//		    'theme.vector_maps',
//		    'theme.google_maps',
		    'theme.calendars',
		    'theme.tasks',
		    'theme.ui-tables-basic',
		    'theme.ui-panels',
		    'theme.ui-ratings',
		    'theme.ui-modals',
		    'theme.ui-tiles',
		    'theme.ui-alerts',
		    'theme.ui-sliders',
		    'theme.ui-progressbars',
		    'theme.ui-paginations',
		    'theme.ui-carousel',
		    'theme.ui-tabs',
//		    'theme.ui-nestable',
		    'theme.form-components',
		    'theme.form-directives',
		    'theme.form-validation',
		    'theme.form-inline',
		    'theme.form-image-crop',
		    'theme.form-uploads',
		    'theme.tables-ng-grid',
		    'theme.tables-editable',
		    'theme.pages-controllers',
		    'theme.dashboard',
		    'theme.templates',
		    //'theme.template-overrides',
		    'ngCookies',
		    'ngResource',
		    'ngSanitize',
		    'ngQuickDate'
		    ])

.controller('MainController',
		['$scope', '$global', '$timeout', 'progressLoader', '$location','authServices','$rootScope',
		 'LoginFactory',
    function ($scope, $global, $timeout, progressLoader, $location, authServices, $rootScope, LoginFactory) {
	
	//$rootScope.isAuthentified=null;
	
	// Temp logged in user
//	$rootScope.isAuthentified=true;
//	$rootScope.userLogged ={
//			id: "1" ,
//			password : "admin" ,
//			firstName : "test", 
//			lastName :"test",
//			email : "admin@gmail.com",
//			type : "admin"
//	}
			
	  /************ invoke rest application **********************/
	
    $scope.style_fixedHeader = $global.get('fixedHeader');
    $scope.style_headerBarHidden = $global.get('headerBarHidden');
    $scope.style_layoutBoxed = $global.get('layoutBoxed');
    $scope.style_fullscreen = $global.get('fullscreen');
    $scope.style_leftbarCollapsed = $global.get('leftbarCollapsed');
    $scope.style_leftbarShown = $global.get('leftbarShown');
    $scope.style_rightbarCollapsed = $global.get('rightbarCollapsed');
    $scope.style_isSmallScreen = false;
    $scope.style_showSearchCollapsed = $global.get('showSearchCollapsed');
    $scope.style_layoutHorizontal = $global.get('layoutHorizontal');

    $scope.hideSearchBar = function () {
        $global.set('showSearchCollapsed', false);
    };

    $scope.hideHeaderBar = function () {
        $global.set('headerBarHidden', true);
    };

    $scope.showHeaderBar = function ($event) {
      $event.stopPropagation();
      $global.set('headerBarHidden', false);
    };

    $scope.toggleLeftBar = function () {
      if ($scope.style_isSmallScreen) {
        return $global.set('leftbarShown', !$scope.style_leftbarShown);
      }
      $global.set('leftbarCollapsed', !$scope.style_leftbarCollapsed);
    };

    $scope.toggleRightBar = function () {
      $global.set('rightbarCollapsed', !$scope.style_rightbarCollapsed);
    };

    $scope.$on('globalStyles:changed', function (event, newVal) {
      $scope['style_'+newVal.key] = newVal.value;
    });
    $scope.$on('globalStyles:maxWidth767', function (event, newVal) {
      $timeout( function () {      
        $scope.style_isSmallScreen = newVal;
        if (!newVal) {
          $global.set('leftbarShown', false);
        } else {
          $global.set('leftbarCollapsed', false);
        }
      });
    });


//    $scope.isLoggedIn = true;
//    $scope.logOut = function () {
//      $scope.isLoggedIn = false;
//    };
//    $scope.logIn = function () {
//      $scope.isLoggedIn = true;
//    };

    $scope.rightbarAccordionsShowOne = false;
    $scope.rightbarAccordions = [{open:true},{open:true},{open:true},{open:true},{open:true},{open:true},{open:true}];

    $scope.$on('$routeChangeStart', function (e) {
      // console.log('start: ', $location.path());
      progressLoader.start();
      progressLoader.set(50);
    });
    $scope.$on('$routeChangeSuccess', function (e) {
      // console.log('success: ', $location.path());
      progressLoader.end();
    });
  
    $scope.logout = function() {
    	LoginFactory.logout(function (data, status, headers, config) {
			// Success handler
//			delete $cookies['JSESSIONID'];
			console.info('The user has been logged out!'+ data + " " + status);
			$rootScope.isAuthentified =null;
			$location.path( "/login" );

		}, function(data, status, headers, config) {
			// Failure handler
			console.error('Something went wrong while trying to logout... ', data, status, headers, config);
		});
	};
	

}
])

/** ROUTAGE **/
		    
.config(function($routeProvider) {
	
	
	$routeProvider.when('/', {
		templateUrl : 'views/dashboard.html'
	})

	 .when('/login', {
        templateUrl: 'views/login.html' 
      })
      
		
//      .when('/commun/:templateFile', {
//        templateUrl: function (param) { return 'views/commun/'+param.templateFile+'.html' }
//      })
//      
//        .when('/commun/:templateFile/:add' , {
//		templateUrl: function (param) { return 'views/commun/'+param.templateFile+'.html' }
//		})
      
      .when('/commercial/:templateFile', {
        templateUrl: function (param) { return 'views/commercial/'+param.templateFile+'.html' }
      })
      
      .when('/parametrage/:templateFile', {
        templateUrl: function (param) { return 'views/parametrage/'+param.templateFile+'.html' }
      })
      
        .when('/report/:templateFile', {
        templateUrl: function (param) { return 'views/report/'+param.templateFile+'.html' }
      })
      
	.otherwise({
		redirectTo : '/'
	})

})
// cache tools en mode dev: desable mode prod 
  .config(['$httpProvider', function($httpProvider) {
    //initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};    
    }    

    // Answer edited to include suggestions from comments
    // because previous version of code introduced browser-related errors

    //disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    // extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}])

// .run( function($rootScope, $location) {

    // register listener to watch route changes
//    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
//    	console.log("$rootScope.isAuthentified" + $rootScope.isAuthentified);
//      if ( $rootScope.isAuthentified == null ) {
//        // no logged user, we should be going to #login
//        if ( next.templateUrl == "views/login.html" ) {
//          // already going to #login, no redirect needed
//        } else {
//          // not going to #login, we should redirect now
//          $location.path( "/login" );
//        }
//      }         
//    });
    
// })

/** MISE EN PAGE COMPOSANTS **/





