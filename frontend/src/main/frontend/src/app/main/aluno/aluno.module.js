(function ()
{
    'use strict';

    angular
        .module('app.aluno', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.aluno', {
                url    : '/aluno',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/aluno/aluno.html',
                        controller : 'AlunoController as vm'
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
        msNavigationServiceProvider.saveItem('social', {
            title : 'Social',
            group : true,
            weight: 8
        });

        msNavigationServiceProvider.saveItem('social.aluno', {
            title    : 'Gerenciar Alunos',
            icon     : 'icon-school',
            state    : 'app.aluno',
            weight   : 1
        });
    }
})();
