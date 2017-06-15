(function ()
{
    'use strict';

    angular
        .module('app.esqueciSenha', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider.state('app.esqueciSenha', {
            url    : '/esqueciSenha',
            views  : {
              'main@'                       : {
                templateUrl: 'app/core/layouts/content-only.html',
                controller : 'MainController as vm'
              },

              'content@app.esqueciSenha': {
                  templateUrl: 'app/main/esqueciSenha/esqueciSenha.html',
                  controller : 'EsqueciSenhaController as vm'
              }

            },
            bodyClass: 'login'
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
        /*
        msNavigationServiceProvider.saveItem('social.educador', {
            title    : 'Gerenciar Educadores',
            icon     : 'icon-human',
            state    : 'app.educador',
            weight   : 2
        });
        */
    }
})();
