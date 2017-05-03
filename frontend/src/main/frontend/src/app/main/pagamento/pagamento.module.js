(function ()
{
    'use strict';

    angular
        .module('app.pagamento', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.pagamento', {
                url    : '/pagamento',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/pagamento/pagamento.html',
                        controller : 'PagamentoController as vm'
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
        // $translatePartialLoaderProvider.addPart('app/main/pagamento');

        // Api
        //msApiProvider.register('sample', ['app/data/sample/sample.json']);

        // Navigation
        // msNavigationServiceProvider.saveItem('financeiro.operacao', {
        //     title : 'Operação',
        //     group : true,
        //     weight: 8
        // });

        msNavigationServiceProvider.saveItem('financeiro.pagamento', {
            title    : 'Informar Pagamento',
            icon     : 'icon-tile-four',
            state    : 'app.pagamento',
            /*stateParams: {
                'param1': 'page'
             },*/
            //translate: 'Informar Pagamento',
            weight   : 0
        });
    }
})();
