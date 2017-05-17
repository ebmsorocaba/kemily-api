(function ()
{
    'use strict';

    angular
        .module('app.usuario', []) // Nome do módulo. Deve estar presente no arquivo [index.module.js]
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.usuario', {
                url    : '/usuario', // Define a URL que leva até este módulo.
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/usuario/usuario.html',
                        controller : 'UsuarioController as vm'
                    }
                },
                // resolve: {
                //     SampleData: function (msApi)
                //     {
                //         return msApi.resolve('sample@get');
                //     }
                // }
            });

        // Translation
        // Usado no sistema de múltiplos idiomas. Indica a pasta onde fica a pasta com os JSONs de Strings. Veja: \index.config.js
        // $translatePartialLoaderProvider.addPart('app/main/usuario');

        // Api
        // msApiProvider.register('sample', ['app/data/sample/sample.json']);

        // Navigation
        // Esta seção controla como este módulo aparece no menu.
        msNavigationServiceProvider.saveItem('administrador', { // Adiciona um item no menu
            title : 'Administrador', // Nome do item/grupo no menu. Veja: [...]\usuario\i18n
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 1 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('administrador.usuario', { // Adiciona um sub-item dentro de [administrador].
            title    : 'Gerenciar Usuários',
            icon     : 'icon-account-circle', // Ícone deste item no menu.
            state    : 'app.usuario', // Qual módulo o item aponta/abre.
            /*stateParams: {
                'param1': 'page'
             },*/
            //translate: 'USUARIO.USUARIO_ITEM', // Usado no sistema de multiplos idiomas. Veja: [...]\usuario\i18n
            weight   : 1 // Ordem no menu. Baseado em prioridade.
        });
    }
})();
