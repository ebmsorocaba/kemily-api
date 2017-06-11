(function() {
  'use strict';

  angular
    .module('app.associados')
    .controller('AssociadoDialogController', AssociadoDialogController);

  /** @ngInject */
  function AssociadoDialogController($mdDialog, Associado, Associados, User, msUtils, api) {
    var vm = this;

    // Data
    vm.title = 'Alterar Associado';
    vm.associado = angular.copy(Associado);
    vm.associados = Associados;
    vm.user = User;
    vm.newAssociado = false;
    vm.allFields = false;
    //vm.ok = false;

    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];
    //vm.calculaCPF = calculaCPF;


    // TODO Ajustar o Associado conforme o BackEnd
    if (!vm.associado) {
      vm.associado = {
        'cpf': '',
        'nome': '',
        'celular': null,
        'email': '',
        // 'formaPgto': 'Dinheiro', // TODO Tirar do objeto (ver [/api/formaPgto])
        // 'cartao': 1231231231231231, // TODO Tirar do objeto (ver [/cartao])
        'valorAtual': null,
        'vencAtual': null,
      };

      vm.title = 'Novo Associado';
      vm.newAssociado = true;
      // vm.associado.tags = [];
    }

    // Methods
    vm.addNewAssociado = addNewAssociado;
    vm.saveAssociado = saveAssociado;
    vm.deleteAssociadoConfirm = deleteAssociadoConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Add new associado
     */
    function addNewAssociado() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
        api.associado.list.save(vm.associado,
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
        vm.associados.unshift(vm.associado);

        closeDialog();
      //}
    }

    /**
     * Save new associado
     */
    function saveAssociado() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.associados.length; i++) {
        if (vm.associados[i].cpf === vm.associado.cpf) {
          vm.associados[i] = angular.copy(vm.associado);
          break;
        }
      }

      // Grava as alterações no BD:
      api.associado.getByCpf.update({
        'cpf': vm.associado.cpf
      },vm.associado,
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

    /**
     * Delete Associado Confirm Dialog
     */
    function deleteAssociadoConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este associado?')
        .htmlContent('<b>' + vm.associado.nome + ' (' + vm.associado.cpf + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar associado')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Associado.

        // Remove o Associado do BD
        console.log('deleteAssociado @ associados.controller.js');
        api.associado.getByCpf.delete({
            'cpf': associado.cpf
          },
          // Sucesso
          function(response) {
            console.log(response);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        );

        // Remove a da lista na tela a linha deste Associado
        vm.associados.splice(vm.associados.indexOf(Associado), 1);
      });
    }

    /*
    function calculaCPF(strCPF) {
      var Soma;
      var Resto;
      Soma = 0;
      var i;
      var flag = 0;

      //retirar mascara
      if(strCPF != null){
        strCPF = strCPF.replace(/\-/g,"");
        strCPF = strCPF.replace(/\./g,"");
      }
      else{
        return false;
      }
      //verificar se os numeros do cpf são todos iguais ex: 000.000.000-00
      for(i=0; i<11; i++){
          if(strCPF[i] == strCPF[i+1]){
              flag++;
          }
      }

      if(flag==10){
          vm.ok = false;
          return false;
      }

      for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
      Resto = (Soma * 10) % 11;

      if ((Resto == 10) || (Resto == 11))  Resto = 0;
      if (Resto != parseInt(strCPF.substring(9, 10)) ){
        vm.ok = false;
        return false;
      }

      Soma = 0;
      for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
      Resto = (Soma * 10) % 11;

      if ((Resto == 10) || (Resto == 11))  Resto = 0;
      if (Resto != parseInt(strCPF.substring(10, 11) ) ){
        vm.ok = false;
        return false;
      }

      vm.ok = true;
      return true;
    }
    */

    /**
     * Close dialog
     */
    function closeDialog() {
      $mdDialog.hide();
    }

  }
})();

/*
app.directive('myDirective', function() {
  return {
    require: 'ngModel',
    link: function(scope, element, attr, mCtrl) {
      function myValidation(value) {
        if (value.indexOf("e") > -1) {
          mCtrl.$setValidity('charE', true);
        } else {
          mCtrl.$setValidity('charE', false);
        }
        return value;
      }
      mCtrl.$parsers.push(myValidation);
    }
  };
});
*/
