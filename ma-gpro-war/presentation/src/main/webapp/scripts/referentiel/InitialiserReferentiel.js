/*angular.module('gpro.initReferentiel')
    .factory('initReferentielFactory', ['$http','baseUrl', function($http, baseUrl) {

    var initReferentielCacheService = {};

    initReferentielCacheService.nuke = {};

    //Gets the list of nuclear weapons
    initReferentielCacheService.getNukes = function() {
        $http.get('nukes/nukes.json')
            .success(function(data) {
                initReferentielCacheService.nukes = data;
            });

        return initReferentielCacheService.nukes;
    };

    return initReferentielCacheService;

}]);*/