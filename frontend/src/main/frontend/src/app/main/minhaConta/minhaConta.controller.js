(function ()
{
    'use strict';

    angular
        .module('app.minhaConta')
        .controller('minhaContaController', minhaContaController);

    /** @ngInject */
    function minhaContaController(User, api, $mdDialog, $scope, $state, $window)
    {
        var vm = this;

        vm.currentUser = User;
        vm.alterarSenha = alterarSenha;
        vm.senhaAntiga = null;
        vm.senhaConfirmar = null;
        vm.novaSenha = null;


        function alterarSenha(ev){

          if(vm.senhaAntiga == vm.currentUser.senha){
            if(vm.novaSenha == vm.senhaConfirmar){
              console.log(vm.currentUser.senha);
              vm.currentUser.senha = vm.novaSenha;

              api.usuario.getByNome.update({
                'nome': vm.currentUser.nome
              },vm.currentUser,
                // Exibe o resultado no console do navegador:
                // Sucesso
                function(response) {

                  console.log(response);
                  $window.localStorage.setItem("currentUser", angular.toJson(vm.currentUser));
                  var confirm = $mdDialog.alert()
                        .title('SUCESSO')
                        .textContent('Houve exito na mudança de senha.')
                        .ariaLabel('ok!')
                        .targetEvent(ev)
                        .ok('Ok!')

                  $mdDialog.show(confirm).then(function() {
                    $scope.status = 'ok!';
                    $state.reload();
                  });


                },
                // Erro
                function(response) {
                  console.error(response);

                  var confirm = $mdDialog.alert()
                        .title('FALHA')
                        .textContent('Problema com o servidor contate um administrador.')
                        .ariaLabel('Vou verificar!')
                        .targetEvent(ev)
                        .ok('Vou verificar!')

                  $mdDialog.show(confirm).then(function() {
                    $scope.status = 'falha!';

                  });
                }
              );

            }
            else{
              var confirm = $mdDialog.alert()
                    .title('FALHA')
                    .textContent('Nova senha com a senha de confirmação não batem!')
                    .ariaLabel('Vou verificar!')
                    .targetEvent(ev)
                    .ok('Vou verificar!')

              $mdDialog.show(confirm).then(function() {
                $scope.status = 'falha!';

              });
            }

          }
          else{
            var confirm = $mdDialog.alert()
                  .title('FALHA')
                  .textContent('Não é essa a senha antiga!')
                  .ariaLabel('Vou verificar!')
                  .targetEvent(ev)
                  .ok('Vou verificar!')

            $mdDialog.show(confirm).then(function() {
              $scope.status = 'falha!';

            });
          }
        }
        // Data
        //vm.helloText = SampleData.data.helloText;

        // Methods

        //////////
    }
})();
