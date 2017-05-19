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
                User: function (msApi)
                {
                    return msApi.resolve('contacts.user@get');
                }
            }
        });

        // Api
        msApiProvider.register('usuarios.usuarios', ['/api/usuario']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

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
