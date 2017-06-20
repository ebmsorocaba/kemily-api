(function ()
{
    'use strict';

    angular
        .module('app.esqueciSenha')
        .controller('EsqueciSenhaController', EsqueciSenhaController);

    /** @ngInject */
    function EsqueciSenhaController(api, $window, $mdDialog, $scope)
    {
        var vm = this;

        vm.loading = false;
        vm.fail = fail;
        vm.sucess = sucess;
        //vm.showWait = showWait;
        vm.resetarSenha = resetarSenha;

        // if($window.localStorage.getItem("currentUser") != null)
        //   vm.currentUser = JSON.parse($window.localStorage.getItem("currentUser"));
        //
        // if(vm.currentUser != null)
        //   console.log(vm.currentUser.nome);

        function resetarSenha(ev){

          vm.loading = true;
          api.usuario.resetarSenha.get({
              'nome': vm.nome
            },
            // Sucesso
            function(response) {
              vm.loading = false;
              console.log(response);
              vm.sucess(ev);
              $window.location.href = '/#/login';
            },
            // Erro
            function(response) {

              vm.loading = false;
              console.error(response);
              vm.fail(ev);

            }
          );




        }

        function sucess(ev) {
        // Appending dialog to document.body to cover sidenav in docs app
          var confirm = $mdDialog.alert()
                .title('SUCESSO')
                .textContent('Sua senha foi resetada, verifique seu e-mail.')
                .ariaLabel('OK')
                .targetEvent(ev)
                .ok('OK')

          $mdDialog.show(confirm).then(function() {
            $scope.status = 'ok!';

          });
        };


        function fail(ev) {
        // Appending dialog to document.body to cover sidenav in docs app
          var confirm = $mdDialog.alert()
                .title('FALHA')
                .textContent('Houve algum problema ao resetar a senha. Contate um adminitrador.')
                .ariaLabel('OK')
                .targetEvent(ev)
                .ok('OK')

          $mdDialog.show(confirm).then(function() {
            $scope.status = 'falha!';

          });
        };
    }
})();
