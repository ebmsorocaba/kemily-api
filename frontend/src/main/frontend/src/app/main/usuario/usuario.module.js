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
                Contacts: function (msApi)
                {
                    return msApi.resolve('contacts.contacts@query'); // GET para Arrays
                },
                User: function (msApi)
                {
                    return msApi.resolve('contacts.user@get');
                }
                // FormaPgto: function (msApi)
                // {
                //   return msApi.resolve('contacts.formaPgto@query')
                // }
            }
        });

        // Translation
        // $translatePartialLoaderProvider.addPart('app/main/apps/contacts');

        // Api
        msApiProvider.register('contacts.contacts', ['/api/usuario']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

        // Navigation
        msNavigationServiceProvider.saveItem('testes', { // Adiciona um item no menu
            title : 'Administração', // Nome do item/grupo no menu.
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 0 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('testes.contatos', {
            title : 'Usuários',
            icon  : 'icon-account-circle',
            state : 'app.usuario',
            weight: 10
        });

    }
})();
