(function ()
{
    'use strict';

    angular
        .module('app.contatos',
            [
                // 3rd Party Dependencies
                // 'xeditable'
            ]
        )
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {

        $stateProvider.state('app.contatos', {
            url    : '/contatos',
            views  : {
                'content@app': {
                    templateUrl: 'app/main/contacts/contacts.html',
                    controller : 'ContactsController as vm'
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
            }
        });

        // Translation
        // $translatePartialLoaderProvider.addPart('app/main/apps/contacts');

        // Api
        msApiProvider.register('contacts.contacts', ['http://localhost:8080/associados']);
        msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

        // Navigation
        msNavigationServiceProvider.saveItem('testes', { // Adiciona um item no menu
            title : 'Testes', // Nome do item/grupo no menu.
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 10 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('testes.contatos', {
            title : 'Contatos',
            icon  : 'icon-account-box',
            state : 'app.contatos',
            weight: 10
        });

    }
})();
