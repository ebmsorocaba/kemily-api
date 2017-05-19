(function ()
{
    'use strict';

    angular
        .module('app.projeto', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.projeto', {
                url    : '/projeto',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/projeto/projeto.html',
                        controller : 'ProjetoController as vm'
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
        // $translatePartialLoaderProvider.addPart('app/main/relatorio');

        // Api
        //msApiProvider.register('sample', ['app/data/sample/sample.json']);

        //Navigation
        // msNavigationServiceProvider.saveItem('social', {
        //     title : 'Social',
        //     group : true,
        //     weight: 8
        // });

        msNavigationServiceProvider.saveItem('social.projeto', {
            title    : 'Gerenciar Projetos',
            icon     : 'icon-table-edit',
            state    : 'app.projeto',
            weight   : 3
        });
    }
})();
