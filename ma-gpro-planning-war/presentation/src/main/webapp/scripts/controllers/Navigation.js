'use strict'

angular
  .module('theme.navigation-controller', [])
  .controller(
		  'NavigationController',
		  [
		   '$scope',
           '$location', 
           '$timeout',
           '$global',
           '$rootScope',
           function ($scope, $location, $timeout, $global, $rootScope) {
			   
			   
	$scope.menu =[
		{
		    label: 'Tableau de bord',
		    iconClasses: 'fa fa-home',
		    url: '#/',
		    isChild : false
		},
		/* {
		    label:"Paramètrage",
		    iconClasses:"fa fa-suitcase",
		    url:"",
		    isChild : false,
		    children:[
						{
						    label:"Capacité Globale ",
						    iconClasses:"fa fa-suitcase",
						    url:"#/parametrage/capaciteGlobale"
						},
						{
						    label:"Capacité Client ",
						    iconClasses:"fa fa-suitcase",
						    url:"#/parametrage/capaciteClient"
						}
				    ]
		},
		{
		    label:"Interrogations ",
		    iconClasses:"fa fa-suitcase",
		    url:"",
			isChild : false,
			children:[
				{
					label:"Etat",
					iconClasses:"fa fa-suitcase",
					url:"#/commercial/interrogationStock"
				},
				{
					label:"Reporting",
					iconClasses:"fa fa-suitcase",
					url:"#/commercial/interrogationReporting"
				}
			]
		},
		{
		    label:"Traçabilité",
		    iconClasses:"fa fa-suitcase",
		    url:"",
		    isChild : false,
		    children:[
						{
						    label:"Reponse Client",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/reponseClient"
						},
						{
						    label:"Traçabilité Client ",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/TracabiliteClient"
						}
				    ]
		}, */
		//Confirmation Livraison
		{
		    label:"Suivi Commande ",
		    iconClasses:"fa fa-suitcase",
		    url:"#/commercial/ConfirmationLivraison",
		    isChild : false
		},
	/* 	{
		    label:"Commandes ",
		    iconClasses:"fa fa-suitcase",
		    url:"#/commercial/commandes",
		    isChild : false
		}, */
		{
		    label:"Importation Commande ",
		    iconClasses:"fa fa-suitcase",
		    url:"",
		    isChild : false ,
		    children:[
						{
						    label:"Format CSV",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/detailsSuiviTR"
						}
						/*,{
						    label:"Format Standard ",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/standardImportation"
						}, /*
						/*{
						    label:"Format Des DOR",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/importationCommandeDesDor"
						}*/
					/*	{
						    label:"Format Forcast",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/importationCommandeForcast"
						}*/
				    ]
		},
		/* {
		    label:"Planning Chaine",
		    iconClasses:"fa fa-suitcase",
		    url:"#/commercial/planning",
		    isChild : false
		},
		{
		    label:"Reporting ",
		    iconClasses:"fa fa-suitcase",
		    url:"#/commercial/productionJourReport",
		    isChild : false
		}, */
		{
		    label:"Production Externe",
		    iconClasses:"fa fa-suitcase",
		    url:"#/parametrage/productionJour",
		    isChild : false
		},
		{
		    label:"Collisage",
		    iconClasses:"fa fa-suitcase",
		    url:"#/parametrage/collisage",
		    isChild : false
		
		},
		
		{
		    label:"Mouvement TUSEL",
		    iconClasses:"fa fa-suitcase",
		  //  url:"#/parametrage/collisage",
		    isChild : false,
				   children:[
						{
						    label:"Envoi ",
						    iconClasses:"fa fa-suitcase",
						    url:"#/commercial/mouvementEnvoi"
						},
						{
						    label:"Réception ",
						    iconClasses:"fa fa-suitcase",
						 url:"#/commercial/mouvementReception"
						},
							{
						    label:"Etat ",
						    iconClasses:"fa fa-suitcase",
						   url:"#/commercial/mouvementEtat"
						}
				    ]
		}
	];
	
		
	    
    var setParent = function (children, parent) {
        angular.forEach(children, function (child) {
            child.parent = parent;
            if (child.children !== undefined) {
                setParent (child.children, child);
            }
        });
    };

    $scope.findItemByUrl = function (children, url) {
      for (var i = 0, length = children.length; i<length; i++) {
        if (children[i].url && children[i].url.replace('#', '') == url) return children[i];
        if (children[i].children !== undefined) {
          var item = $scope.findItemByUrl (children[i].children, url);
          if (item) return item;
        }
      }
    };
    
    setParent ($scope.menu, null);
    
    $scope.openItems = [];
    $scope.selectedItems = [];
    $scope.selectedFromNavMenu = false;
    
    $scope.select = function (item) {

    if (! item.isChild){ // isParent
    	
    	// close open nodes
        if (item.open) {
            item.open = false;
            return;
        }
        for (var i = $scope.openItems.length - 1; i >= 0; i--) {
            $scope.openItems[i].open = false;
        };
        $scope.openItems = [];
        var parentRef = item;
        while (parentRef !== null) {
            parentRef.open = true;
            $scope.openItems.push(parentRef);
            parentRef = parentRef.parent;
        }

        // handle leaf nodes
        if (!item.children || (item.children && item.children.length<1)) {
            $scope.selectedFromNavMenu = true;
            for (var j = $scope.selectedItems.length - 1; j >= 0; j--) {
                $scope.selectedItems[j].selected = false;
            };
            $scope.selectedItems = [];
            var parentRef = item;
            while (parentRef !== null) {
                parentRef.selected = true;
                $scope.selectedItems.push(parentRef);
                parentRef = parentRef.parent;
            }
        };
      }else{
    	  // isChild
//    	  $location.path('/view/pdp/definitionProjet');
//    	  $rootScope.showDetailsProjectFromMenu=true;
//    	  $rootScope.projectDetails= item;
      }
    };

    $scope.$watch(function () {
      return $location.path();
    }, function (newVal, oldVal) {
      if ($scope.selectedFromNavMenu == false) {
        var item = $scope.findItemByUrl ($scope.menu, newVal);
        if (item)
          $timeout (function () { $scope.select (item); });
      }
      $scope.selectedFromNavMenu = false;
    });

    // searchbar
    $scope.showSearchBar = function ($e) {
        $e.stopPropagation();
        $global.set('showSearchCollapsed', true);
    }
    $scope.$on('globalStyles:changed:showSearchCollapsed', function (event, newVal) {
      $scope.style_showSearchCollapsed = newVal;
    });
    $scope.goToSearch = function () {
        $location.path('/extras-search')
    };
    
    $scope.selectPlus = function(item){
    	item.open = false;
    	$rootScope.showNewProjectFromMenu=true;
    	$location.path('/view/pdp/definitionProjet');
    }

    
  }])











