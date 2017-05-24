(function ()
{
    'use strict';

    angular
        .module('fuse')
        .config(config);

    /** @ngInject */
    function config($translateProvider, $resourceProvider, $mdDateLocaleProvider)
    {
        // Impede que o Angular remova a barra '/' do final da URL
        // Necessário para a API de Associados
        $resourceProvider.defaults.stripTrailingSlashes = false;



        $mdDateLocaleProvider.formatDate = function(date) {
          return date ? moment(date).format('DD/MM/YYYY') : '';
        };

        $mdDateLocaleProvider.parseDate = function(dateString) {
          var m = moment(dateString, 'DD-MM-YYYY', true);
          return m.isValid() ? m.toDate() : new Date(NaN);
        };

        // Put your common app configurations here

        // angular-translate configuration
        // $translateProvider.useLoader('$translatePartialLoader', {
        //     urlTemplate: '{part}/i18n/{lang}.json'
        // });
        // $translateProvider.preferredLanguage('br');
        // $translateProvider.useSanitizeValueStrategy('sanitize');
    }

})();
