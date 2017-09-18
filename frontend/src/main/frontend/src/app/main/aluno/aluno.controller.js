(function() {
  'use strict';

  angular.module('app.aluno').controller('AlunoController', AlunoController);

  /** @ngInject */
  function AlunoController($scope, $mdSidenav, User, Alunos, Enderecos, Roupas, Despesas, EstruturasFamiliares, SituacoesHabitacionais, AparelhosEletronicos, Contatos, Imoveis, Automoveis, Saudes, ComposicaoFamiliar, msUtils, $mdDialog, $document, api, $window) {
    var vm = this;
    var estruturaFamiliar = null;
    var situacaoHabitacional = null;
    var paiContato = null;
    var maeContato = null;
    var alunoVazio = {
      'ra': '',
      'nome': '',
      'dataNascimento': '',
      'etnia': '',
      'rg': '',
      'estado': '',
      'dataCadastro': new Date(),
      'meioTransporte': '',
      'observacoes': '',
      'naturalidade': ''
    };

    var roupaVazio = {
      'aluno': {
        'ra': '',
        'nome': '',
        'dataNascimento': '',
        'rg': '',
        'estado': '',
        'dataCadastro': new Date(),
        'meioTransporte': '',
        'observacoes': '',
        'naturalidade': ''
      },
      'tamanhoCamiseta': '',
      'tamanhoCalca': ''
    };

    var enderecoVazio = {
      'cep': '',
      'numero': '',
      'rua': '',
      'bairro': '',
      'cidade': '',
      'pontoReferencia': '',
      'complemento': '',
      'aluno': {
        'ra': '',
        'nome': '',
        'dataNascimento': '',
        'rg': '',
        'naturalidade': '',
        'estado': '',
        'dataCadastro': '',
        'meioTransporte': '',
        'observacoes': ''
      }
    };

    var saudeVazio = {
      'aluno': {
        'ra': '',
        'nome': '',
        'dataNascimento': '',
        'rg': '',
        'naturalidade': '',
        'estado': '',
        'dataCadastro': '',
        'meioTransporte': '',
        'observacoes': ''
      },
      'fazTratamentosMedicos': '',
      'problemasSaudeFamilia': '',
      'planoSaude': '',
      'pessoasIdosas': '',
      'problemasPsiquiatricos': ''
    };

    var estruturaFamiliarVazio = {
      'id': '',
      'estadoCivilPais': '',
      'criancaResideCom': '',
      'problemasFinanceiros': '',
      'usoAlcoolDrogas': '',
      'alguemAgressivo': '',
      'programasSociais': '',
      'aluno': {
        'ra': '',
        'nome': '',
        'dataNascimento': '',
        'rg': '',
        'naturalidade': '',
        'estado': '',
        'dataCadastro': '',
        'meioTransporte': '',
        'observacoes': ''
      }
    };

    var despesaVazio = {
      'estruturaFamiliar': {
        'id': '',
        'estadoCivilPais': '',
        'criancaResideCom': '',
        'problemasFinanceiros': '',
        'usoAlcoolDrogas': '',
        'alguemAgressivo': '',
        'programasSociais': '',
        'aluno': {
          'ra': '',
          'nome': '',
          'dataNascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'dataCadastro': '',
          'meioTransporte': '',
          'observacoes': ''
        }
      },
      'agua': '',
      'energiaEletrica': '',
      'telefone': '',
      'aluguel': '',
      'financiamentoCasa': '',
      'financiamentoCarro': '',
      'transporte': '',
      'alimentacao': '',
      'gas': '',
      'cartaoCredito': '',
      'emprestimo': '',
      'tvCabo': '',
      'educacao': '',
      'pensao': '',
      'convenioMedico': ''
    };

    var aparelhosEletronicosVazio = {
      'id': '',
      'televisao': '',
      'tvAssinatura': '',
      'computador': '',
      'notebook': '',
      'fogao': '',
      'geladeira': '',
      'microondas': '',
      'maquinaLavar': '',
      'maquinaSecar': '',
      'telefoneFixo': '',
      'celular': ''
    };

    var situacaoHabitacionalVazio = {
      'aluno': {
        'ra': '',
        'nome': '',
        'dataNascimento': '',
        'rg': '',
        'naturalidade': '',
        'estado': '',
        'dataCadastro': '',
        'meioTransporte': '',
        'observacoes': ''
      },
      'situacao': '',
      'esgoto': '',
      'redeEletrica': '',
      'asfalto': '',
      'numeroComodos': '',
      'alvenaria': '',
      'madeira': '',
      'areaIrregular': '',
      'aparelhosEletronicos': {
        'id': '',
        'televisao': '',
        'tvAssinatura': '',
        'computador': '',
        'notebook': '',
        'fogao': '',
        'geladeira': '',
        'microondas': '',
        'maquinaLavar': '',
        'maquinaSecar': '',
        'telefoneFixo': '',
        'celular': ''
      }
    };

    var composicaoFamiliarVazio = [
      {
        'nome': '',
        'parentesco': '',
        'escolaridade': '',
        'dataNascimento': new Date(),
        'ocupacao': '',
        'salario': '',
        'localTrabalho': '',
        'condicaoTrabalho': '',
        'aluno': {
          'ra': '',
          'nome': '',
          'dataNascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'dataCadastro': '',
          'meioTransporte': '',
          'observacoes': ''
        }
      }
    ];
    var automoveisVazio = [
      {
        'modelo': '',
        'ano': '',
        'id': '',
        'financiado': '',
        'estruturaFamiliar': {
          'id': ''
        }
      }
    ];
    var imoveisVazio = [
      {
        'financiado': '',
        'id': '',
        'estruturaFamiliar': {
          'id': ''
        }
      }
    ];
    var contatosVazio = [
      {
        'nome': '',
        'telefone': '',
        'tipo': '',
        'estado': '',
        'email': '',
        'redeSocial': '',
        'aluno': {
          'ra': ''
        },
        'grauParentesco': '',
        'presente': '',
        'cargo': ''
      }
    ];
    var paiContato = {
      'nome': '',
      'telefone': '',
      'tipo': 'Responsavel',
      'estado': '',
      'email': '',
      'redeSocial': '',
      'aluno': {
        'ra': ''
      },
      'grauParentesco': 'Pai',
      'presente': '',
      'cargo': ''
    };
    var maeContato = {
      'nome': '',
      'telefone': '',
      'tipo': 'Responsavel',
      'estado': '',
      'email': '',
      'redeSocial': '',
      'aluno': {
        'ra': ''
      },
      'grauParentesco': 'Mae',
      'presente': '',
      'cargo': ''
    };
    // Data
    vm.alunos = Alunos;
    vm.saudes = Saudes;
    vm.composicaoFamiliar = ComposicaoFamiliar;
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

    function solveAluno() {
      $window.location.reload()
    }

    function openAlunoDialog (ev, aluno) {
      ev.preventDefault()
      $mdDialog.show({
        controller: 'AlunoDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/aluno/dialogs/aluno/aluno-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        locals: {
          User: vm.user,
          Alunos: vm.alunos,
          Roupa: vm.selectRoupa(aluno),
          Endereco: vm.selectEndereco(aluno),
          Saude: vm.selectSaude(aluno),
          Contatos: vm.selectContato(aluno),
          ComposicaoFamiliar: vm.selectParente(aluno),
          EstruturaFamiliar: vm.selectEstruturaFamiliar(aluno),
          SituacaoHabitacional: vm.selectSituacaoHabitacional(aluno),
          Imoveis: vm.selectImovel(estruturaFamiliar),
          Automoveis: vm.selectAutomovel(estruturaFamiliar),
          Despesa: vm.selectDespesa(estruturaFamiliar),
          AparelhosEletronicos: vm.selectAparelhosEletronicos(situacaoHabitacional),
          PaiContato: vm.selectPaiContato(),
          MaeContato: vm.selectMaeContato(),
          Aluno: aluno
        }
      });
    }

    function selectRoupa(aluno) {
      if (!aluno) {
        return roupaVazio;
      }
      var result = roupaVazio;
      vm.roupas.forEach(function(roupa) {
        if (roupa.aluno.ra === aluno.ra) {
          result = roupa;
        }
      });
      return result;
    }

    function selectEndereco(aluno) {
      if (!aluno) {
        return enderecoVazio;
      }
      var result = enderecoVazio;
      vm.enderecos.forEach(function(endereco) {
        if (endereco.aluno.ra === aluno.ra) {
          result = endereco;
        }
      });
      return result;
    }

    function selectContato(aluno) {
      if (!aluno) {
        return contatosVazio;
      }
      var result = [];
      vm.contatos.forEach(function(contato) {
        if (contato.aluno.ra === aluno.ra) {
          if(contato.tipo == 'Responsavel'  && contato.grauParentesco == 'Pai') {
            paiContato = contato;
          } else if(contato.tipo == 'Responsavel'  && contato.grauParentesco == 'Mae') {
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
        return estruturaFamiliarVazio;
      }
      var result = estruturaFamiliarVazio;
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
        return saudeVazio;
      }
      var result = saudeVazio;
      vm.saudes.forEach(function(saude) {
        if (saude.aluno.ra === aluno.ra) {
          result = saude;
        }
      });
      return result;
    }

    function selectParente(aluno) {
      if (!aluno) {
        return composicaoFamiliarVazio;
      }
      var result = [];
      vm.composicaoFamiliar.forEach(function(composicaoFamiliar) {
        if (composicaoFamiliar.aluno.ra === aluno.ra) {
          result.push(composicaoFamiliar);
        }
      });
      return result;
    }

    function selectSituacaoHabitacional(aluno) {
      if (!aluno) {
        return situacaoHabitacionalVazio;
      }
      var result = situacaoHabitacionalVazio;
      vm.situacoesHabitacionais.forEach(function(situacao) {
        if (situacao.aluno.ra === aluno.ra) {
          situacaoHabitacional = situacao;
          result = situacao;
        }
      });
      return result;
    }

    function selectAutomovel(estrutura) {
      if (!estrutura) {
        return automoveisVazio;
      }
      var result = [];
      vm.automoveis.forEach(function(automovel) {
        if (automovel.estruturaFamiliar.id === estrutura.id) {
          result.push(automovel);
        }
      });
      return result;
    }

    function selectImovel(estrutura) {
      if (!estrutura) {
        return imoveisVazio;
      }
      var result = [];
      vm.imoveis.forEach(function(imovel) {
        if (imovel.estruturaFamiliar.id === estrutura.id) {
          result.push(imovel);
        }
      });
      return result;
    }

    function selectDespesa(estrutura) {
      if (!estrutura) {
        return despesaVazio;
      }
      var result = despesaVazio;
      vm.despesas.forEach(function(despesa) {
        if (despesa.estruturaFamiliar.id === estrutura.id) {
          result = despesa;
        }
      });
      return result;
    }

    function selectAparelhosEletronicos(situacao) {
      if (!situacao) {
        return aparelhosEletronicosVazio;
      }
      var result = aparelhosEletronicosVazio;
      vm.aparelhosEletronicos.forEach(function(aparelhos) {
        if (aparelhos.id === situacao.aparelhosEletronicos.id) {
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
      $mdDialog.show(confirm).then(function() {

        vm.selectedAlunos.forEach(function(aluno) {
          deleteAluno(aluno);
        });

        vm.selectedAlunos = [];

      });

    }

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
    function deselectAlunos () {
      vm.selectedAlunos = [];
    }

    /**
       * Sselect all alunos
       */
    function selectAllAlunos () {
      vm.selectedAlunos = $scope.filteredAlunos;
    }
  }

})();
