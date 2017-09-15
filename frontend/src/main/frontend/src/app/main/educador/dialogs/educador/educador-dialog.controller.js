(function() {
  'use strict';

  angular.module('app.educador').controller('EducadorDialogController', EducadorDialogController);

  /** @ngInject */
  function EducadorDialogController($mdDialog, Educador, Educadores, User, msUtils, api) {
    var vm = this;

    // Methods
    vm.addNewEducador = addNewEducador;
    vm.saveEducador = saveEducador;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Add new educador
     */
    function addNewEducador() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
        api.educador.list.save(vm.educador,
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {
            console.log(response);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        );

      // Adiciona uma nova linha no topo da lista na tela
        vm.educadores.unshift(vm.educador);

        closeDialog();
      //}
    }

    /**
     * Save new educador
     */
    function saveEducador() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.educadores.length; i++) {
        if (vm.educadores[i].cpf === vm.educador.cpf) {
          vm.educadores[i] = angular.copy(vm.educador);
          break;
        }
      }

      // Grava as alterações no BD:
      api.educador.getByCpf.update({
        'cpf': vm.educador.cpf
      },vm.educador,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
          console.log(response);
        },
        // Erro
        function(response) {
          console.error(response);
        }
      );

      closeDialog();
    }

  }
})();
