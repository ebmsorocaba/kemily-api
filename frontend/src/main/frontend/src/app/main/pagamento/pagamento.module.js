(function ()
{
    'use strict';

    angular
        .module('app.pagamento',
            [
                // 3rd Party Dependencies
                // 'xeditable'
            ]
        )
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {

        $stateProvider.state('app.pagamento', {
            url    : '/pagamento',
            views  : {
                'content@app': {
                    templateUrl: 'app/main/pagamento/pagamento.html',
                    controller : 'PagamentoController as vm'
                }
            },
            resolve: {
                Pagamentos: function (msApi)
                {
                    return msApi.resolve('pagamento.pagamentos@query'); // GET para Arrays
                },
                User: function ($window)
                {
                    return JSON.parse($window.localStorage.getItem("currentUser"));
                },
                Associados: function (msApi)
                {
                    return msApi.resolve('pagamento.associados@query');
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
            return $q.reject()
          }
        }

        // Api
        msApiProvider.register('pagamento.pagamentos', ['/api/pagamento']);
        msApiProvider.register('pagamento.associados', ['/api/associado']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        //msApiProvider.register('pagamento.user', ['app/data/contacts/user.json']);

        // Navigation
        msNavigationServiceProvider.saveItem('financeiro', { // Adiciona um item no menu
            title : 'Financeiro', // Nome do item/grupo no menu.
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 2 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('financeiro.pagamento', {
            title : 'Gerenciar Pagamentos',
            icon  : 'icon-credit-card',
            state : 'app.pagamento',
            weight: 1
        });

    }
})();
