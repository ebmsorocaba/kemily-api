(function ()
{
    'use strict';

    angular
        .module('app.associados',
            [
                // 3rd Party Dependencies
                // 'xeditable'
            ]
        )
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {

        $stateProvider.state('app.associados', {
            url    : '/contatos',
            views  : {
                'content@app': {
                    templateUrl: 'app/main/associados/associados.html',
                    controller : 'AssociadosController as vm'
                }
            },
            resolve: {
                Associados: function (msApi)
                {
                    return msApi.resolve('associados.associados@query'); // GET para Arrays
                },
                User: function (msApi)
                {
                    return msApi.resolve('associados.user@get');
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
        msApiProvider.register('associados.associados', ['/api/associado']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        msApiProvider.register('associados.user', ['app/data/contacts/user.json']);

        // Navigation
        msNavigationServiceProvider.saveItem('financeiro', { // Adiciona um item no menu
            title : 'Financeiro', // Nome do item/grupo no menu.
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 2 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('financeiro.associado', {
            title : 'Gerenciar Associados',
            icon  : 'icon-account-box',
            state : 'app.associados',
            weight: 1
        });

    }
})();
