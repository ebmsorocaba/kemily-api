(function() {
  'use strict';

  angular.module('app.educador').controller('EducadoresController', EducadoresController);

  /** @ngInject */
  function EducadoresController ($scope, $mdSidenav, User, msUtils, $mdDialog, $document, api, $window, Educadores) {

    var vm = this;

    // Data

    vm.educadores = Educadores
    //vm.user = User.data;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedEducadores = [];


    // Methods
    vm.openEducadorDialog = openEducadorDialog;
    vm.deleteEducadorConfirm = deleteEducadorConfirm;
    vm.deleteEducador = deleteEducador;
    vm.deleteSelectedEducadores = deleteSelectedEducadores;
    vm.toggleSelectEducador = toggleSelectEducador;
    vm.deselectEducadores = deselectEducadores;
    vm.selectAllEducadores = selectAllEducadores;
    vm.deleteEducador = deleteEducador;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;

    function openEducadorDialog(ev, educador) {
      $mdDialog.show({
        controller: 'EducadorDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/educador/dialogs/educador/educador-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        locals: {
          Educador: educador,
          User: vm.user,
          Educadores: vm.educadores
        }
      });
    }

    function deleteEducadorConfirm(educador, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este educador?')
        .htmlContent('<b>' + educador.nome + ' (' + educador.cpf + ')</b>' + ' será apagado(a).')
        .ariaLabel('apagar contato')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteEducador(educador);
        vm.selectedEducadores = [];

      }, function() {
        //console.log('Cancelou');
      });
    }

    function deleteEducador(educador) {
      console.log('deleteEducador @ educadores.controller.js');
      api.educador.getByCpf.delete({
          'cpf': educador.cpf
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
      vm.educadores.splice(vm.educadores.indexOf(educador), 1);
    }

    function deleteSelectedEducadores(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar os educadores selecionados?')
        .htmlContent('<b>' + vm.selectedEducadores.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
        .ariaLabel('apagar contatos')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        vm.selectedEducadores.forEach(function(educador) {
          deleteEducador(educador);
        });

        vm.selectedEducadores = [];

      });

    }

    function toggleSelectEducador(educador, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedEducadores.indexOf(educador) > -1) {
        vm.selectedEducadores.splice(vm.selectedEducadores.indexOf(educador), 1);
      } else {
        vm.selectedEducadores.push(educador);
      }
    }
    function deselectEducadores() {
      vm.selectedEducadores = [];
    }
    function selectAllEducadores() {
      vm.selectedEducadores = $scope.filteredEducadores;
    }

  }

})();
