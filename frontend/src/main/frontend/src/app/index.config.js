(function ()
{
    'use strict';

    angular
        .module('fuse')
        .config(config);

    /** @ngInject */
    function config($translateProvider, $resourceProvider)
    {
        // Impede que o Angular remova a barra '/' do final da URL
        // Necessário para a API de Associados
        $resourceProvider.defaults.stripTrailingSlashes = false;

        // Put your common app configurations here

        // angular-translate configuration
        // $translateProvider.useLoader('$translatePartialLoader', {
        //     urlTemplate: '{part}/i18n/{lang}.json'
        // });
        // $translateProvider.preferredLanguage('br');
        // $translateProvider.useSanitizeValueStrategy('sanitize');
    }

})();
