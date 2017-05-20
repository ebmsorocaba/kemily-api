(function ()
{
    'use strict';

    angular
        .module('app.relatorio', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.relatorio', {
                url    : '/relatorio',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/relatorio/relatorio.html',
                        controller : 'RelatorioController as vm'
                    }
                },
                resolve: {
                    // Associados: function (msApi)
                    // {
                    //     return msApi.resolve('associados.associados@query'); // GET para Arrays
                    // },
                    User: function (msApi)
                    {
                        return msApi.resolve('contacts.user@get');
                    }
                }
            });

            msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

        // Translation
        // $translatePartialLoaderProvider.addPart('app/main/relatorio');

        // Api
        //msApiProvider.register('sample', ['app/data/sample/sample.json']);

        // Navigation
        // msNavigationServiceProvider.saveItem('financeiro.operacao', {
        //     title : 'Operação',
        //     group : true,
        //     weight: 8
        // });

        msNavigationServiceProvider.saveItem('financeiro.relatorio', {
            title    : 'Relatório Financeiro',
            icon     : 'icon-document',
            state    : 'app.relatorio',
            weight   : 3
        });
    }
})();
