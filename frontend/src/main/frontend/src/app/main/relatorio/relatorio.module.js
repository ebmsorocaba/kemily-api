(function ()
{
    'use strict';

    angular
        .module('app.relatorio', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msApiProvider, msNavigationServiceProvider)
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
                // resolve: {
                //     SampleData: function (msApi)
                //     {
                //         return msApi.resolve('sample@get');
                //     }
                // }
            });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/relatorio');

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
            icon     : 'icon-tile-four',
            state    : 'app.relatorio',
            /*stateParams: {
                'param1': 'page'
             },*/
            //translate: 'Relatório Financeiro',
            weight   : 0
        });
    }
})();
