(function() {
  'use strict';

  angular
    .module('app.minhaConta')
    .controller('minhaContaController', minhaContaController);

  /** @ngInject */
  function minhaContaController(User, api, $mdDialog, $scope, $state, $window) {
    var vm = this;

    vm.currentUser = User;
    vm.alterarSenha = alterarSenha;
    vm.senhaAntiga = null;
    vm.senhaConfirmar = null;
    vm.novaSenha = null;


    function alterarSenha(ev) {
      vm.currentUser.usuarioSenha = {senhaAntiga: vm.senhaAntiga, senhaNova: vm.novaSenha};
      api.usuario.getByNome.update({
          'nome': vm.currentUser.nome
        }, vm.currentUser,
        function(response) {
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
          var confirm = $mdDialog.alert()
            .title('ERRO')
            .textContent('A nova senha deve ser diferente da antiga')
            .ariaLabel('ok!')
            .targetEvent(ev)
            .ok('Ok!')

          $mdDialog.show(confirm).then(function() {
            $scope.status = 'ok!';
            $state.reload();
          });
        }
      );
    }
    // Data
    //vm.helloText = SampleData.data.helloText;

    // Methods

    //////////
  }
})();
