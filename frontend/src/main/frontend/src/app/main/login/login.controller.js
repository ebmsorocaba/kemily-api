(function ()
{
    'use strict';

    angular
        .module('app.login')
        .controller('LoginController', LoginController);

    /** @ngInject */
    function LoginController(api, $window, $mdDialog, $scope)
    {
        var vm = this;

        vm.usuario;

        vm.login = login;
        vm.fail = fail;

        // if($window.localStorage.getItem("currentUser") != null)
        //   vm.currentUser = JSON.parse($window.localStorage.getItem("currentUser"));
        //
        // if(vm.currentUser != null)
        //   console.log(vm.currentUser.nome);

        function login(ev){
          api.usuario.login.get({
              'nome': vm.usuario.nome,
              'senha': vm.usuario.senha
            },
            // Sucesso
            function(response) {
              console.log(response);
              vm.usuario = response;

              $window.localStorage.setItem("currentUser", angular.toJson(vm.usuario));
              //$window.localStorage.setItem("currentUser", vm.usuario);
              if(vm.usuario.setor == "Social" && vm.usuario.ativo==true){
                $window.location.href = '/#/educador';
              }
              else if(vm.usuario.setor == "Administração" && vm.usuario.ativo==true){
                $window.location.href = '/#/usuario';
              }
              else if(vm.usuario.setor == "Financeiro" && vm.usuario.ativo==true){
                $window.location.href = '/#/associado';
              }


            },
            // Erro
            function(response) {


              console.error(response);
              vm.fail(ev);
              console.log("errooooooooooooooooooooooooooooo");
            }
          );




        }

        function fail(ev) {
        // Appending dialog to document.body to cover sidenav in docs app
          var confirm = $mdDialog.alert()
                .title('FALHA')
                .textContent('Usuario não cadastrado ou senha invalida! Verifique os dados informador ou contate um administrador.')
                .ariaLabel('Vou verificar!')
                .targetEvent(ev)
                .ok('Vou verificar!')

          $mdDialog.show(confirm).then(function() {
            $scope.status = 'falha!';

          });
        };
    }
})();
