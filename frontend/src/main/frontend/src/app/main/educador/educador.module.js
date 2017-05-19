(function ()
{
    'use strict';

    angular
        .module('app.educador', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.educador', {
                url    : '/educador',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/educador/educador.html',
                        controller : 'EducadorController as vm'
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

        msNavigationServiceProvider.saveItem('social.educador', {
            title    : 'Gerenciar Educadores',
            icon     : 'icon-human',
            state    : 'app.educador',
            weight   : 2
        });
    }
})();
