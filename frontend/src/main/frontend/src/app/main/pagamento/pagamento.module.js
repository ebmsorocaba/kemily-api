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
                User: function (msApi)
                {
                    return msApi.resolve('pagamento.user@get');
                },
                Associados: function (msApi)
                {
                    return msApi.resolve('pagamento.associados@query');
                }
            }
        });

        // Api
        msApiProvider.register('pagamento.pagamentos', ['/api/pagamento']);
        msApiProvider.register('pagamento.associados', ['/api/associado']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        msApiProvider.register('pagamento.user', ['app/data/contacts/user.json']);

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
