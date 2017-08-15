(function() {
  'use strict';

  angular.module('app.aluno').controller('AlunoController', AlunoController);

  /** @ngInject */
  function AlunoController($scope, $mdSidenav, User, Alunos, Turmas, Enderecos, Roupas, Despesas, EstruturasFamiliares, SituacoesHabitacionais, AparelhosEletronicos, Contatos, Imoveis, Automoveis, Saudes, Parentes, msUtils, $mdDialog, $document, api, $window) {
    var vm = this;
    var estruturaFamiliar = null;
    var situacaoHabitacional = null;
    var paiContato = null;
    var maeContato = null;
    // Data
    vm.turmas = Turmas;
    vm.alunos = Alunos;
    vm.saudes = Saudes;
    vm.parentes = Parentes;
    vm.enderecos = Enderecos;
    vm.despesas = Despesas;
    vm.roupas = Roupas;
    vm.estruturasFamiliares = EstruturasFamiliares;
    vm.situacoesHabitacionais = SituacoesHabitacionais;
    vm.contatos = Contatos;
    vm.aparelhosEletronicos = AparelhosEletronicos;
    vm.imoveis = Imoveis;
    vm.automoveis = Automoveis;
    //vm.user = User.data;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedAlunos = [];

    // Methods
    vm.openAlunoDialog = openAlunoDialog;
    vm.deleteAlunoConfirm = deleteAlunoConfirm;
    vm.deleteAluno = deleteAluno;
    vm.deleteSelectedAlunos = deleteSelectedAlunos;
    vm.toggleSelectAluno = toggleSelectAluno;
    vm.deselectAlunos = deselectAlunos;
    vm.selectAllAlunos = selectAllAlunos;
    vm.deleteAluno = deleteAluno;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;

    vm.selectRoupa = selectRoupa;
    vm.selectEndereco = selectEndereco;
    vm.selectSaude = selectSaude;
    vm.selectContato = selectContato;
    vm.selectParente = selectParente;
    vm.selectAutomovel = selectAutomovel;
    vm.selectImovel = selectImovel;
    vm.selectDespesa = selectDespesa;
    vm.selectEstruturaFamiliar = selectEstruturaFamiliar;
    vm.selectSituacaoHabitacional = selectSituacaoHabitacional;
    vm.selectAparelhosEletronicos = selectAparelhosEletronicos;
    vm.selectMaeContato = selectMaeContato;
    vm.selectPaiContato = selectPaiContato;
    //////////

    //vm.currentUser = $window.sessionStorage.getItem("currentUser");

    //console.log("Logado:" + User.nome);
    //console.log("Logado: " + vm.currentUser.nome);

    /**.
       * Open new Aluno dialog
       *
       * @param ev
       * @param contact
       */
    function openAlunoDialog(ev, aluno) {
      $mdDialog.show({
        controller: 'AlunoDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/aluno/dialogs/aluno/aluno-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        locals: {
          Aluno: aluno,
          User: vm.user,
          Alunos: vm.alunos,
          Turmas: vm.turmas,
          Roupa: vm.selectRoupa(aluno),
          Endereco: vm.selectEndereco(aluno),
          Saude: vm.selectSaude(aluno),
          Contatos: vm.selectContato(aluno),
          Parentes: vm.selectParente(aluno),
          EstruturaFamiliar: vm.selectEstruturaFamiliar(aluno),
          SituacaoHabitacional: vm.selectSituacaoHabitacional(aluno),
          Imoveis: vm.selectImovel(estruturaFamiliar),
          Automoveis: vm.selectAutomovel(estruturaFamiliar),
          Despesa: vm.selectDespesa(estruturaFamiliar),
          AparelhosEletronicos: vm.selectAparelhosEletronicos(situacaoHabitacional),
          PaiContato: vm.selectPaiContato(),
          MaeContato: vm.selectMaeContato()
        }
      });
    }

    function selectRoupa(aluno) {
      var i;
      if (!aluno) //Logica caso o aluno seja vazio
        return 0;

      console.log(vm.roupas);
      console.log('Entrou');
      for (i = 0; i <= vm.roupas.length; i++) {
        if (vm.roupas[i].aluno.ra === aluno.ra) {
          return vm.roupas[i];
        }
      }
    }

    function selectEndereco(aluno) {
      var i;
      if (!aluno)
        return 0;

      console.log(vm.enderecos);
      for (i = 0; i <= vm.enderecos.length; i++) {
        if (vm.enderecos[i].aluno.ra === aluno.ra) {
          return vm.enderecos[i];
        }
      }
    }

    function selectContato(aluno) {
      if (!aluno) {
        return null;
      }
      var result = [];
      vm.contatos.forEach(function(contato) {
        if (contato.aluno.ra === aluno.ra) {
          if(contato.tipo == 'Responsavel'  && contato.grau_parentesco == 'Pai') {
            paiContato = contato;
          } else if(contato.tipo == 'Responsavel'  && contato.grau_parentesco == 'Mae') {
            maeContato = contato;
          } else {
            result.push(contato);
          }
        }
      });
      return result;
    }

    function selectEstruturaFamiliar(aluno) {
      if (!aluno) {
        return null;
      }
      var result = null;
      vm.estruturasFamiliares.forEach(function(estrutura) {
        if (estrutura.aluno.ra === aluno.ra) {
          estruturaFamiliar = estrutura;
          result = estrutura;
        }
      });
      return result;
    }

    function selectSaude(aluno) {
      if (!aluno) {
        return null;
      }
      var result = null;
      vm.saudes.forEach(function(saude) {
        if (saude.aluno.ra === aluno.ra) {
          result = saude;
        }
      });
      return result;
    }

    function selectParente(aluno) {
      if (!aluno) {
        return null;
      }
      var result = [];
      vm.parentes.forEach(function(parente) {
        if (parente.aluno.ra === aluno.ra) {
          result.push(parente);
        }
      });
      return result;
    }

    function selectSituacaoHabitacional(aluno) {
      if (!aluno) {
        return null;
      }
      var result = null;
      vm.situacoesHabitacionais.forEach(function(situacao) {
        if (situacao.aluno.ra === aluno.ra) {
          situacaoHabitacional = situacao;
          result = situacao;
        }
      });
      console.log(result);
      return result;
    }

    function selectAutomovel(estrutura) {
      if (!estrutura) {
        return null;
      }
      var result = [];
      vm.automoveis.forEach(function(automovel) {
        if (automovel.estrutura_familiar.id === estrutura.id) {
          result.push(automovel);
        }
      });
      return result;
    }

    function selectImovel(estrutura) {
      if (!estrutura) {
        return null;
      }
      var result = [];
      vm.imoveis.forEach(function(imovel) {
        if (imovel.estrutura_familiar.id === estrutura.id) {
          result.push(imovel);
        }
      });
      return result;
    }

    function selectDespesa(estrutura) {
      if (!estrutura) {
        return null;
      }
      var result = null;
      vm.despesas.forEach(function(despesa) {
        if (despesa.estrutura_familiar.id === estrutura.id) {
          result = despesa;
        }
      });
      return result;
    }

    function selectAparelhosEletronicos(situacao) {
      if (!situacao) {
        return null;
      }
      var result = null;
      vm.aparelhosEletronicos.forEach(function(aparelhos) {
        if (aparelhos.id === situacao.aparelhos_eletronicos.id) {
          result = aparelhos;
        }
      })
      return result;
    }

    function selectMaeContato() {
      return maeContato;
    }

    function selectPaiContato() {
      return paiContato;
    }

    /**
       * Delete Aluno Confirm Dialog
       */
    function deleteAlunoConfirm(aluno, ev) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar este aluno?').htmlContent('<b>' + aluno.nome + ' (' + aluno.ra + ')</b>' + ' será apagado(a).').ariaLabel('apagar contato').targetEvent(ev).ok('Sim').cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteAluno(aluno);
        vm.selectedAlunos = [];

      }, function() {
        //console.log('Cancelou');
      });
    }

    /**
       * Delete Aluno
       */
    function deleteAluno(aluno) {

      // TODO Remover também a [formaPgto] do Aluno.

      // Remove o Aluno do BD
      console.log('deleteAluno @ alunos.controller.js');
      api.aluno.getByRa.delete({
        'ra': aluno.ra
      },
      // Sucesso
      function(response) {
        console.log(response);
      },
      // Erro
      function(response) {
        console.error(response);
      });

      // Remove a da lista na tela a linha deste Aluno
      vm.alunos.splice(vm.alunos.indexOf(aluno), 1);
    }

    /**
       * Delete Selected Alunos
       */
    function deleteSelectedAlunos(ev) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar os alunos selecionados?').htmlContent('<b>' + vm.selectedAlunos.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).').ariaLabel('apagar contatos').targetEvent(ev).ok('Sim').cancel('Cancelar');
      es = Parentes;
      $mdDialog.show(confirm).then(function() {

        vm.selectedAlunos.forEach(function(aluno) {
          deleteAluno(aluno);
        });

        vm.selectedAlunos = [];

      });

    }

    /**
       * Toggle selected status of the aluno
       *
       * @param aluno
       * @param event
       */
    function toggleSelectAluno(aluno, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedAlunos.indexOf(aluno) > -1) {
        vm.selectedAlunos.splice(vm.selectedAlunos.indexOf(aluno), 1);
      } else {
        vm.selectedAlunos.push(aluno);
      }
    }

    /**
       * Deselect alunos
       */
    function deselectAlunos() {
      vm.selectedAlunos = [];
    }

    /**
       * Sselect all alunos
       */
    function selectAllAlunos() {
      vm.selectedAlunos = $scope.filteredAlunos;
    }
  }

})();
