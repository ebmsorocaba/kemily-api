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
                resolve: {
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
                if(User.setor == 'Social' || User.setor == "Administração"){
                  return $q.when();
                }
                else{
                  var confirm = $mdDialog.alert()
                        .title('Não permitido!')
                        .textContent('Apenas adminitradores e o setor social pode acessar esse módulo.')
                        .ariaLabel('Vou verificar!')
                        .ok('Vou verificar!')

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
                return $q.reject();
              }
            }

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
