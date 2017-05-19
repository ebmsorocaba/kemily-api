(function ()
{
    'use strict';

    /**
     * Main module of the Fuse
     */
    angular
        .module('fuse', [

            // Core
            'app.core',

            // Navigation
            'app.navigation',

            // Toolbar
            'app.toolbar',

            // Cadastro de Usuários
            'app.usuario',

            // Cadastro de Associados
            'app.associados',

            // Informar pagamento
            'app.pagamento',

            // Relatório financeiro
            'app.relatorio',

            // angular-mask (ver bower.json)
            'ngMask'
        ]);
})();
