(function ()
{
    'use strict';

    angular
        .module('app.relatorioSocial', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.relatorioSocial', {
                url    : '/relatorioSocial',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/relatorioSocial/relatorioSocial.html',
                        controller : 'RelatorioSocialController as vm'
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

        msNavigationServiceProvider.saveItem('social.relatorioSocial', {
            title    : 'Relatório Social',
            icon     : 'icon-document',
            state    : 'app.relatorioSocial',
            weight   : 4
        });
    }
})();
