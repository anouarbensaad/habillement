'use strict';

angular
  .module('themesApp', [
'easypiechart',
'toggle-switch',
'ui.bootstrap',
'ui.tree',
'ui.select2',
'ui.sortable',
'ngGrid',
'xeditable',
'flow',
'theme.services',
'theme.directives',
'theme.navigation-controller',
'theme.notifications-controller',
'theme.messages-controller',
'theme.colorpicker-controller',
'theme.layout-horizontal',
'theme.layout-boxed',
//'theme.vector_maps',
'theme.calendars',
'theme.gallery',
'theme.tasks',
'gpro.detailssaisie',
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
'theme.ui-nestable',
'theme.form-components',
'theme.form-directives',
'theme.form-validation',
'theme.form-inline',
'theme.form-image-crop',
'theme.form-uploads',
'theme.tables-ng-grid',
'theme.tables-editable',
'theme.charts-flot',
'theme.personne',
'theme.client',
'config',
'components.donwload',
'gpro.articles',
'gpro.back-articles',
'gpro.partieInteressee',
'gpro.back-partieInteressee',
'gpro.produit',
'gpro.back-produits',
'gpro.ficheBesoin',
'gpro.gestionnaireDocument',
'gpro.stock',
'gpro.stockMouvementOF',
'gpro.feuilleSaisie',
'gpro.etat',
'gpro.avancementOF',
'gpro.popupAvancementOFJour',
'gpro.popupAvancementOFTailleCouleur',
'gpro.gc_chart',
'gpro.gcGpaoChart',
'gpro.quantiteScChart',
'gpro.stockEChart',
'gpro.gcVenteBC',
'gpro.gcVenteBL',
'gpro.gcVenteFacture',
'gpro.popupDetailProduitBCVente',
'gpro.popupDetailProduitBLVente',
'gpro.back-gcVente',
'gpro.gc_produitCommandeVente',
'gpro.gcAchat',
'gpro.menuGestionCommerciale',
'gpro.menuGPAO',
'gpro.gpao',
'gpro.gammeOF',
'gpro.gammeMonatage',
'gpro.operations',
'gpro.eclatement',
'gpro.colisage',
'atelier.bonSortie',
'gpro.ficheSuiveuse',
'gpro.detailssaisie',
'gpro.gpaoReporting',
'gpro.personnel',
'gpro.detailsSuivi',
'gpro.back-gpao',
'gpro.historique',
'gpro.gpaoSuiviJournalier',
'gpro.back',
'gpro.menuElementBase',
'gpro.suiviCommande',
'gpro.menuSuiviCommnde',
'gpro.stockMenu',
'gpro.detailsSuiviTR',
'theme.charts-canvas',
'theme.charts-svg',
'theme.charts-inline',
'theme.pages-controllers',
'theme.dashboard',
'theme.templates',
'theme.template-overrides',
'ngCookies',
'ngResource',
'ngSanitize',
'ngRoute',
'ngAnimate',
'gpro.PopUpElementBase',
'gpro.PopupCouleurElementBase',
'gpro.PopupStockEntree',
'gpro.PopupStockSortie',
'pascalprecht.translate',
'gpro.GpaoChart',
'gpro.stockRetour',
'language.translate-controller'/*,
'chart'*/
    
  ])
  .controller('MainController', ['$scope', '$rootScope', '$global', '$timeout', 'progressLoader', '$location','$http','baseUrl','baseUrlGs','baseUrlGc', function ($scope, $rootScope, $global, $timeout, progressLoader, $location, $http, baseUrl, baseUrlGs,baseUrlGc) {
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
    $scope.displayBORF = 'f';
    $scope.displayMode ='front';
    $scope.goBack = function(){
      $scope.displayMode ='back'; 
      $scope.displayBORF = 'b';
      window.location.href = "#/backPartieInteresse";
      $("#menuFront").removeClass("navFront").addClass("navBack");
      $("#menuFront>ul").removeClass("navGPRO").addClass("navGPROBack");
      $("#menuFront>ul>li").removeClass("active");
      $( "#menuFront>ul>li" ).eq( 6 ).addClass("active");
    }
    $scope.goFront = function(){
      //reload referentiel commun
      $http.get(baseUrl
        + "/gestionnaireCache/reloadReferentiel");
      //reload stock
      $http.get(baseUrlGs
        + "/gestionnaireCache/reloadGs");
      //reload Commercial
      $http.get(baseUrlGc
        + "/gestionCommercialCache/reloadGc");
      //TODO : reload GPAO
      /*$http.get(baseUrlGpao
        + "/gestionProduitAOCache/reloadGpao");*/
      
      $scope.displayMode ='front';
      $scope.displayBORF = 'f';
      window.location.href = "#/partieInteressee";
      $("#menuFront").removeClass("navBack").addClass("navFront");
      $("#menuFront>ul").removeClass("navGPROBack").addClass("navGPRO");
      $("#menuFront>ul>li").removeClass("active");
      $( "#menuFront>ul>li" ).eq( 1 ).addClass("active");
      
    }
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

   
    // there are better ways to do this, e.g. using a dedicated service
    // but for the purposes of this demo this will do :P
  $rootScope.isLoggedIn = false;
//    $rootScope.isLoggedIn = false;
    
//    $rootScope.isLoggedIn = true;
//    $global.set('fullscreen', false);
    
    $scope.loginError = false;
    
    $scope.userlogin = {};
    $scope.currentUser = {};
  
    $scope.logOut = function () {
   
      $scope.userlogin.userName = "";
      $scope.userlogin.password = "";
        
      $scope.loginError = false;
        $global.set('fullscreen', true);
      $rootScope.isLoggedIn = false;
    };
    $scope.logIn = function (userlogin) {
      
      $scope.loginPostAction = function(userlogin) {
        
        $http.post(baseUrl + "/user/login", userlogin)
           .success(function(resultat) {
                  
                if(resultat.userName !=null){
                  
                  $scope.currentUser = resultat;
                  
                    $global.set('fullscreen', false);
                    $rootScope.isLoggedIn = true;
                }else{
                  
                  $scope.loginError = true;
                }
              });
      }
      
      $scope.loginPostAction($scope.userlogin);
    };

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
  }])
  
  
   // Enable / Disable Debugging mode : true/false
  .config(['$logProvider', function($logProvider){
      $logProvider.debugEnabled(true);
  }])
  .config(['$provide', '$routeProvider', '$translateProvider', function ($provide, $routeProvider, $translateProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/index.html',
      })
      .when('/calendar', {
        templateUrl: 'views/calendar.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'assets/plugins/fullcalendar/fullcalendar.js'
            ]);
          }]
        }
      })
      .when('/form-ckeditor', {
        templateUrl: 'views/form-ckeditor.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'assets/plugins/form-ckeditor/ckeditor.js',
              'assets/plugins/form-ckeditor/lang/fr.js'
            ]);
          }]
        }
      })
      .when('/form-imagecrop', {
        templateUrl: 'views/form-imagecrop.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'assets/plugins/jcrop/js/jquery.Jcrop.js'
            ]);
          }]
        }
      })
      .when('/form-wizard', {
        templateUrl: 'views/form-wizard.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/jquery-validation/dist/jquery.validate.js',
              'bower_components/stepy/lib/jquery.stepy.js'
            ]);
          }]
        }
      })
      .when('/form-masks', {
        templateUrl: 'views/form-masks.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/jquery.inputmask/dist/jquery.inputmask.bundle.js'
            ]);
          }]
        }
      })
      .when('/maps-vector', {
        templateUrl: 'views/maps-vector.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/jqvmap/jqvmap/maps/jquery.vmap.europe.js',
              'bower_components/jqvmap/jqvmap/maps/jquery.vmap.usa.js'
            ]);
          }]
        }
      })
      .when('/charts-canvas', {
        templateUrl: 'views/charts-canvas.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/Chart.js/Chart.min.js'
            ]);
          }]
        }
      })
      .when('/charts-svg', {
        templateUrl: 'views/charts-svg.html',
        resolve: {
          lazyLoad: ['lazyLoad', function (lazyLoad) {
            return lazyLoad.load([
              'bower_components/raphael/raphael.js',
              'bower_components/morris.js/morris.js'
            ]);
          }]
        }
      })
      .when('/ChartGraphiqueGS', {
        controller:'QuantiteSC',
          templateUrl: 'views/ChartGraphiqueGS.html',
         
        })
        
        //tableau de bord GPAO
//        .when('/tableau', {
//        controller:'QuantiteSC',
//          templateUrl: 'views/ChartGraphiqueGS.html',
//         
//        })
      .when('/:templateFile', {
        templateUrl: function (param) { return 'views/'+param.templateFile+'.html' }
      })
      .otherwise({
        redirectTo: '/'
      });
    
    $translateProvider
	  .useStaticFilesLoader({
	    prefix: 'translations/',
	    suffix: '.json'
	})
	.preferredLanguage('fr');
  }]);
