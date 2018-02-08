(function() {
  'use strict';

  angular
    .module('app.usuario')
    .controller('UsuariosController', UsuariosController);

  /** @ngInject */
  function UsuariosController($scope, $mdSidenav, Usuarios, User, msUtils, $mdDialog, $document, api) {

    var vm = this;

    // Data
    // vm.formaPgto = FormaPgto;

    vm.usuarios = Usuarios;
    vm.user = User;
    // vm.filterIds = null;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedUsuarios = [];
    // vm.newGroupName = '';

    // Methods
    // vm.filterChange = filterChange;
    vm.openUsuarioDialog = openUsuarioDialog;
    vm.deleteUsuarioConfirm = deleteUsuarioConfirm;
    vm.deleteUsuario = deleteUsuario;
    vm.deleteSelectedUsuarios = deleteSelectedUsuarios;
    vm.toggleSelectUsuario = toggleSelectUsuario;
    vm.deselectUsuarios = deselectUsuarios;
    vm.selectAllUsuarios = selectAllUsuarios;
    vm.deleteUsuario = deleteUsuario;
    // vm.addNewGroup = addNewGroup;
    // vm.deleteGroup = deleteGroup;
    // vm.toggleSidenav = toggleSidenav;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Change Usuarios List Filter
     * @param type
     */
    // function filterChange(type)
    // {
    //
    //     vm.listType = type;
    //
    //     if ( type === 'all' )
    //     {
    //         vm.filterIds = null;
    //     }
    //     else if ( type === 'frequent' )
    //     {
    //         vm.filterIds = vm.user.frequentUsuarios;
    //     }
    //     else if ( type === 'starred' )
    //     {
    //         vm.filterIds = vm.user.starred;
    //     }
    //     else if ( angular.isObject(type) )
    //     {
    //         vm.filterIds = type.usuarioIds;
    //     }
    //
    //     vm.selectedUsuarios = [];
    //
    // }

    /**
     * Open new usuario dialog
     *
     * @param ev
     * @param usuario
     */
    function openUsuarioDialog(ev, usuario) {
      $mdDialog.show({
        controller: 'UsuarioDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/usuario/dialogs/usuario/usuario-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        locals: {
          Usuario: usuario,
          User: vm.user,
          Usuarios: vm.usuarios
        }
      });
    }

    /**
     * Delete Usuario Confirm Dialog
     */
    function deleteUsuarioConfirm(usuario, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este usuário?')
        .htmlContent('<b>' + usuario.nome + ' (' + usuario.nome + ')</b>' + ' será apagado(a).')
        .ariaLabel('apagar usuario')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteUsuario(usuario);
        vm.selectedUsuarios = [];

      }, function() {
        //console.log('Cancelou');
      });
    }

    /**
     * Delete Usuario
     */
    function deleteUsuario(usuario) {

      // TODO Remover também a [formaPgto] do Associado.
      if(User.nome !== usuario.nome) {

        // Remove o Associado do BD

        console.log('deleteUsuario @ usuarios.controller.js');
          api.usuario.getByNome.delete({
            'nome': usuario.nome
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
        vm.usuarios.splice(vm.usuarios.indexOf(usuario), 1);
      } else {
        var erro = $mdDialog.confirm()
        .title('ERRO!')
        .htmlContent('<p><b>USUÁRIO LOGADO NAO PODE SE EXCLUIR!</b></p>')
        .ariaLabel('erro')
        .ok('OK')

        $mdDialog.show(erro);
      }
    }

    /**
     * Delete Selected Usuarios
     */
    function deleteSelectedUsuarios(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar os usuários selecionados?')
        .htmlContent('<b>' + vm.selectedUsuarios.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
        .ariaLabel('apagar contatos')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        vm.selectedUsuarios.forEach(function(usuario) {
          deleteUsuario(usuario);
        });

        vm.selectedUsuarios = [];

      });

    }

    /**
     * Toggle selected status of the usuario
     *
     * @param usuario
     * @param event
     */
    function toggleSelectUsuario(usuario, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedUsuarios.indexOf(usuario) > -1) {
        vm.selectedUsuarios.splice(vm.selectedUsuarios.indexOf(usuario), 1);
      } else {
        vm.selectedUsuarios.push(usuario);
      }
    }

    /**
     * Deselect usuarios
     */
    function deselectUsuarios() {
      vm.selectedUsuarios = [];
    }

    /**
     * Sselect all usuarios
     */
    function selectAllUsuarios() {
      vm.selectedUsuarios = $scope.filteredUsuarios;
    }

    /**
     *
     */
    // function addNewGroup()
    // {
    //     if ( vm.newGroupName === '' )
    //     {
    //         return;
    //     }
    //
    //     var newGroup = {
    //         'id'        : msUtils.guidGenerator(),
    //         'name'      : vm.newGroupName,
    //         'usuarioIds': []
    //     };
    //
    //     vm.user.groups.push(newGroup);
    //     vm.newGroupName = '';
    // }

    /**
     * Delete Group
     */
    // function deleteGroup(ev)
    // {
    //     var group = vm.listType;
    //
    //     var confirm = $mdDialog.confirm()
    //         .title('Are you sure want to delete the group?')
    //         .htmlContent('<b>' + group.name + '</b>' + ' will be deleted.')
    //         .ariaLabel('delete group')
    //         .targetEvent(ev)
    //         .ok('OK')
    //         .cancel('CANCEL');
    //
    //     $mdDialog.show(confirm).then(function ()
    //     {
    //
    //         vm.user.groups.splice(vm.user.groups.indexOf(group), 1);
    //
    //         filterChange('all');
    //     });
    //
    // }

    /**
     * Toggle sidenav
     *
     * @param sidenavId
     */
    // function toggleSidenav(sidenavId)
    // {
    //     $mdSidenav(sidenavId).toggle();
    // }

  }

})();
