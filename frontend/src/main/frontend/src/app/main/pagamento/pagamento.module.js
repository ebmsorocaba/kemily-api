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
                // Associados: function (msApi)
                // {
                //     return msApi.resolve('associados.associados@query'); // GET para Arrays
                // },
                User: function (msApi)
                {
                    return msApi.resolve('contacts.user@get');
                }
            }
        });

        // Api
        // msApiProvider.register('associados.associados', ['/api/associado']);
        msApiProvider.register('contacts.user', ['app/data/contacts/user.json']);

        // Navigation
        // msNavigationServiceProvider.saveItem('financeiro', { // Adiciona um item no menu
        //     title : 'Financeiro', // Nome do item/grupo no menu.
        //     group : true, // Define se é um item [false] ou grupo de itens [true].
        //     weight: 2 // Ordem no menu. Baseado em prioridade.
        // });

        msNavigationServiceProvider.saveItem('financeiro.pagamento', {
            title : 'Informar Pagamento',
            icon  : 'icon-credit-card',
            state : 'app.pagamento',
            weight: 2
        });

    }
})();
