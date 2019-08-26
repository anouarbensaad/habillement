(function() {
    'use strict';

    var downloadModule = angular.module('components.donwload', []);

    downloadModule.factory('downloadService', ['$q', '$timeout', '$window','baseUrl',
        function($q, $timeout, $window,baseUrl) {
            return {
                download: function(url) {
                    var defer = $q.defer();
                    
                    $timeout(function() {
                          // $window.location = UrlAtelier+"/report/inventaire?critereRechercheRouleauStandard="+inventaireCourant+"&type=pdf";
                          // $window.location = url;
                            $window.open(url);
                        }, 1000)
                        .then(function() {
                            defer.resolve('success');
                        }, function() {
                            defer.reject('error');
                        });
                    return defer.promise;
                }
            };
        }
    ]);
})();