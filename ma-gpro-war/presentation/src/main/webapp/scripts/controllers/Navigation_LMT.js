'use strict'

angular
  .module('theme.navigation-controller', [])
  .controller('NavigationController', ['$scope', '$location', '$timeout', '$global', function ($scope, $location, $timeout, $global) {
    $scope.menu = [
        {
            label: ' Tableau de bord',
            iconClasses: 'fa fa-tachometer',
            url: '#/',
            back:"f"
        },
        {
            label:" Parties Intéressée",
            iconClasses:"fa fa-group",
            url:"#/partieInteressee",
            back:"f"
        },
        {
            label:" Elements de base",
            iconClasses:"fa fa-cubes",
            url:"#/ElementBase",
            back:"f"
        },/*
        {
            label:" Gestion Commerciale",
            iconClasses:"fa fa-shopping-cart",
            url:"#/gestionCommerciale",
            back:"f"
        },*/
        
        {
            label:" Gestion Stock",
            iconClasses:"fa fa-exchange",
            url:"#/stock",
            back:"f"
        },

        {
            label:" GPAO",
            iconClasses:"fa fa-cogs",
            url:"#/GpaoMenu",
            back:"f"
        },
        {
            label:" Partie Intéressées  ",
            iconClasses:"",
            url:"#/backPartieInteresse",
            back:"b"

        },
        {
            label:" Elements de base  ",
            iconClasses:"",
            url:"#/backArticle",
            back:"b"
        },
      {
          label:" Gestion Stock  ",
          iconClasses:"",
          url:"#/backStock",
          back:"b"

      } ,
      {
          label:" GPAO",
          iconClasses:"",
          url:"#/backGpao",
          back:"b"
      }/*,
      {    label:"suiviCommande",
         iconClasses:"",
         url:"#/suiviCommande",
          back:"f"
       },
       {
           label:"Gestion Commerciale",
           iconClasses:"",
           url:"#/backGestionCommercialeVente",
           back:"b"
       }*/
       
    ];
//    .navFront{
//      background-color: #1f8597; margin: 20px auto 20px auto
//    }
//    .navBack{
//      background-color: #c2c2c0; margin: 20px auto 20px auto
//    }
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
  }])











