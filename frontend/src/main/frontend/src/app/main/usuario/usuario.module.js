(function ()
{
    'use strict';

    angular
        .module('app.usuario',
            [
                // 3rd Party Dependencies
                // 'xeditable'
            ]
        )
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {

        $stateProvider.state('app.usuario', {
            url    : '/usuario',
            views  : {
                'content@app': {
                    templateUrl: 'app/main/usuario/usuario.html',
                    controller : 'UsuariosController as vm'
                }
            },
            resolve: {
                Usuarios: function (msApi)
                {
                    return msApi.resolve('usuarios.usuarios@query'); // GET para Arrays
                },
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
            if(User.setor == "Administração"){
              return $q.when();
            }
            else{
              var confirm = $mdDialog.alert()
                    .title('Não permitido!')
                    .textContent('Apenas adminitradores podem acessar esse módulo.')
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
        msApiProvider.register('usuarios.usuarios', ['/api/usuario']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        //msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

        // Navigation
        msNavigationServiceProvider.saveItem('administrador', { // Adiciona um item no menu
            title : 'Administração', // Nome do item/grupo no menu.
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 0 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('administrador.usuario', { // Adiciona um sub-item dentro de [administrador].
            title    : 'Gerenciar Usuários',
            icon     : 'icon-worker', // Ícone deste item no menu.
            state    : 'app.usuario', // Qual módulo o item aponta/abre.
            weight   : 1 // Ordem no menu. Baseado em prioridade.
        });

    }
})();
