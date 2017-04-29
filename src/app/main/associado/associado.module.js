(function ()
{
    'use strict';

    angular
        .module('app.associado', []) // Nome do módulo. Deve estar presente no arquivo [index.module.js]
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.associado', {
                url    : '/associado', // Define a URL que leva até este módulo.
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/associado/associado.html',
                        controller : 'AssociadoController as vm'
                    }
                },
                resolve: {
                    SampleData: function (msApi)
                    {
                        return msApi.resolve('sample@get');
                    }
                }
            });

        // Translation
        // Usado no sistema de múltiplos idiomas. Indica a pasta onde fica a pasta com os JSONs de Strings. Veja: \index.config.js
        $translatePartialLoaderProvider.addPart('app/main/associado');

        // Api
        msApiProvider.register('sample', ['app/data/sample/sample.json']);

        // Navigation
        // Esta seção controla como este módulo aparece no menu.
        msNavigationServiceProvider.saveItem('financeiro', { // Adiciona um item no menu
            title : 'ASSOCIADO.ASSOCIADO_MENU', // Nome do item/grupo no menu. Veja: [...]\associado\i18n
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 2 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('financeiro.associado', { // Adiciona um sub-item dentro de [administrador].
            title    : 'Associado',
            icon     : 'icon-tile-four', // Ícone deste item no menu.
            state    : 'app.associado', // Qual módulo o item aponta/abre.
            /*stateParams: {
                'param1': 'page'
             },*/
            translate: 'ASSOCIADO.ASSOCIADO_ITEM', // Usado no sistema de multiplos idiomas. Veja: [...]\usuario\i18n
            weight   : 0 // Ordem no menu. Baseado em prioridade.
        });
    }
})();
