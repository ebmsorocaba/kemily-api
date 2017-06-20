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
                    User: function ($window)
                    {
                        return JSON.parse($window.localStorage.getItem("currentUser"));
                    },
                    authenticate: authenticate
                }


            });

            function authenticate($q, User, $state, $timeout, $mdDialog) {
              if (User != null) {
                // Resolve the promise successfully
                if(User.setor == 'Financeiro' || User.setor == "Administração"){
                  return $q.when();
                }
                else{
                  var confirm = $mdDialog.alert()
                        .title('Não permitido!')
                        .textContent('Apenas adminitradores e o setor financeiro pode acessar esse módulo.')
                        .ariaLabel('OK')
                        .ok('OK')

                  $mdDialog.show(confirm).then(function() {

                  });
                  return $q.reject();
                }
              } else {
                // The next bit of code is asynchronously tricky.

                $timeout(function() {
                  // This code runs after the authentication promise has been rejected.
                  // Go to the log-in page
                  $window.location.href = '/#/login';
                })

                // Reject the authentication promise to prevent the state from loading
                return $q.reject()
              }
            }

            //msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

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
