(function() {
  'use strict';

  angular.module('app.aluno').controller('AlunoDialogController', AlunoDialogController);

  /** @ngInject */
  function AlunoDialogController($window, $state, $mdDialog, Aluno, Roupa, Endereco, Saude, Contatos, PaiContato, MaeContato, Parentes, EstruturaFamiliar, SituacaoHabitacional, AparelhosEletronicos, Imoveis, Automoveis, Despesa, Alunos, Turmas, User, msUtils, api) {
    var vm = this;

    // Data
    vm.title = 'Alterar Aluno';
    vm.roupa = angular.copy(Roupa);
    vm.endereco = angular.copy(Endereco);
    vm.saude = angular.copy(Saude);
    if (Contatos !== '') {
      vm.contatos = angular.copy(Contatos);
    } else {
      vm.contatos = [
        {
          'nome': '',
          'telefone': '',
          'tipo': '',
          'aluno': {
            'ra': ''
          },
          'grauParentesco': '',
          'presente': '',
          cargo: ''
        }
      ];
    }
    if (Parentes != '') {
      vm.parentes = angular.copy(Parentes);
    } else {
      vm.parentes = [
        {
          'nome': '',
          'parentesco': '',
          'escolaridade': '',
          'dataNascimento': new Date(),
          'ocupacao': '',
          'salario': '',
          'localTrabalho': '',
          'aluno': {
            'ra': '',
            'nome': '',
            'turma': {
              'educador': ''
            },
            'dataNascimento': null,
            'rg': '',
            'naturalidade': '',
            'estado': '',
            'dataCadastro': '',
            'meioTransporte': '',
            'observacoes': ''
          }
        }
      ];
    }
    vm.estruturaFamiliar = angular.copy(EstruturaFamiliar);
    vm.situacaoHabitacional = angular.copy(SituacaoHabitacional);
    vm.despesa = angular.copy(Despesa);
    vm.aparelhosEletronicos = AparelhosEletronicos;
    if (Automoveis != '') {
      vm.automoveis = angular.copy(Automoveis);
      vm.automovel = true
    } else {
      vm.automoveis = [
        {
          'modelo': '',
          'ano': '',
          'financiado': '',
          'estruturaFamiliar': {
            'id': ''
          }
        }
      ];
    }
    if (Imoveis != '') {
      vm.imoveis = angular.copy(Imoveis);
      vm.imovel = true
    } else {
      vm.imoveis = [
        {
          'financiado': '',
          'estruturaFamiliar': {
            'id': ''
          }
        }
      ];
    }
    vm.paiContato = angular.copy(PaiContato);
    vm.maeContato = angular.copy(MaeContato);
    vm.aluno = angular.copy(Aluno);
    vm.alunos = Alunos;
    vm.user = User;
    vm.turmas = Turmas;
    vm.newAluno = false;
    vm.allFields = false;
    vm.escolaridades = [
      'Fundamental - Incompleto',
      'Fundamental - Completo',
      'Medio - Incompleto',
      'Medio - Completo',
      'Superior - Incompleto',
      'Superior - Completo'
    ];
    vm.estados = [
      'AC',
      'AL',
      'AP',
      'AM',
      'BA',
      'CE',
      'DF',
      'ES',
      'GO',
      'MA',
      'MT',
      'MS',
      'MG',
      'PA',
      'PB',
      'PR',
      'PE',
      'PI',
      'RJ',
      'RN',
      'RS',
      'RO',
      'RR',
      'SC',
      'SP',
      'SE',
      'TO'
    ];
    vm.camisetas = [
      '2',
      '4',
      '6',
      '8',
      '10',
      '12',
      '14',
      '16',
      'PP',
      'P',
      'M',
      'G',
      'GG',
      'GGG'
    ];
    vm.calcas = [
      '2',
      '4',
      '6',
      '8',
      '10',
      '12',
      '14',
      '16',
      '38',
      '40',
      '42',
      '44',
      '46',
      '48',
      '50',
      'PP',
      'P',
      'M',
      'G',
      'GG',
      'GGG'
    ];
    vm.estadosCivis = [
      'Solteiros',
      'Casados',
      'Separados',
      'Divorciados',
      'Mãe Viúva',
      'Pai Viúvo',
      'União Estável'
    ];
    vm.situacoes = ['Casa Própria', 'Casa Alugada', 'Casa Cedida', 'Cada Financiada'];
    vm.tiposContato = ['Generico', 'Responsavel', 'Profissional'];

    if (!vm.aluno) {
      vm.aluno = {
        'ra': '',
        'nome': '',
        'dataNascimento': null,
        'rg': '',
        'estado': '',
        'dataCadastro': new Date(),
        'meioTransporte': '',
        'observacoes': '',
        'naturalidade': '',
        'turma': {
          'educador': ''
        }
      };

      vm.roupa = {
        'aluno': {
          'ra': '',
          'nome': '',
          'dataNascimento': null,
          'rg': '',
          'estado': '',
          'dataCadastro': new Date(),
          'meioTransporte': '',
          'observacoes': '',
          'naturalidade': '',
          'turma': {
            'educador': ''
          }
        },
        'tamanhoCamiseta': '',
        'tamanhoCalca': ''
      };

      vm.endereco = {
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
          'turma': {
            'educador': ''
          },
          'dataNascimento': null,
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'dataCadastro': '',
          'meioTransporte': '',
          'observacoes': ''
        }
      };

      vm.saude = {
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'dataNascimento': null,
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

      vm.estruturaFamiliar = {
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
          'turma': {
            'educador': ''
          },
          'dataNascimento': null,
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'dataCadastro': '',
          'meioTransporte': '',
          'observacoes': ''
        }
      };

      vm.despesa = {
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
            'turma': {
              'educador': ''
            },
            'dataNascimento': null,
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

      vm.aparelhosEletronicos = {
        'id': '',
        'televisao': '',
        'tvAssinatura': '',
        'computador': '',
        'notebook': '',
        'fogao': '',
        'geladeira': '',
        'microondas': '',
        'tablet': '',
        'maquinaLavar': '',
        'maquinaSecar': '',
        'telefoneFixo': '',
        'celular': ''
      };

      vm.situacaoHabitacional = {
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'dataNascimento': null,
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'dataCadastro': '',
          'meioTransporte': '',
          'observacoes': ''
        },
        'situacao': null,
        'numeroComodos': null,
        'esgoto': false,
        'redeEletrica': false,
        'asfalto': false,
        'alvenaria': false,
        'madeira': false,
        'areaIrregular': false,
        'aparelhosEletronicos': {
          'id': '',
          'televisao': '',
          'tvAssinatura': '',
          'computador': '',
          'notebook': '',
          'fogao': '',
          'geladeira': '',
          'microondas': '',
          'tablet': '',
          'maquinaLavar': '',
          'maquinaSecar': '',
          'telefoneFixo': '',
          'celular': ''
        }
      }

      vm.parentes = [
        {
          'nome': '',
          'parentesco': '',
          'escolaridade': '',
          'dataNascimento': null,
          'ocupacao': '',
          'salario': '',
          'localTrabalho': '',
          'aluno': {
            'ra': '',
            'nome': '',
            'turma': {
              'educador': ''
            },
            'dataNascimento': null,
            'rg': '',
            'naturalidade': '',
            'estado': '',
            'dataCadastro': '',
            'meioTransporte': '',
            'observacoes': ''
          }
        }
      ];
      vm.automoveis = [
        {
          'modelo': '',
          'ano': '',
          'financiado': '',
          'estruturaFamiliar': {
            'id': ''
          }
        }
      ];
      vm.imoveis = [
        {
          'financiado': '',
          'estruturaFamiliar': {
            'id': ''
          }
        }
      ];
      vm.contatos = [
        {
          'nome': '',
          'telefone': '',
          'tipo': '',
          'aluno': {
            'ra': ''
          },
          'grauParentesco': '',
          'presente': '',
          cargo: ''
        }
      ];
      vm.paiContato = {
        'nome': '',
        'telefone': '',
        'tipo': 'Responsavel',
        'aluno': {
          'ra': ''
        },
        'grauParentesco': 'Pai',
        'presente': '',
        cargo: ''
      };
      vm.maeContato = {
        'nome': '',
        'telefone': '',
        'tipo': 'Responsavel',
        'aluno': {
          'ra': ''
        },
        'grauParentesco': 'Mae',
        'presente': '',
        cargo: ''
      };

      vm.title = 'Novo Aluno';
      vm.newAluno = true;
      // vm.aluno.tags = [];
    } else {
      vm.aluno.dataNascimento = new Date(vm.aluno.dataNascimento);
      vm.parentes.forEach(function(parente) {
        parente.dataNascimento = new Date(parente.dataNascimento);
      });
    }

    // Methods
    vm.routeReload = function() {
      $state.reload()
      // $window.location.reload()
    }

    vm.isLastContato = function(c) {
      var index = vm.contatos.indexOf(c);
      if (index == (vm.contatos.length - 1)) {
        return true;
      } else {
        return false;
      }
    }

    vm.isLastImovel = function(i) {
      var index = vm.imoveis.indexOf(i);
      if (index == (vm.imoveis.length - 1)) {
        return true;
      } else {
        return false;
      }
    }

    vm.isLastAutomovel = function(a) {
      var index = vm.automoveis.indexOf(a);
      if (index == (vm.automoveis.length - 1)) {
        return true;
      } else {
        return false;
      }
    }

    vm.addContato = function(c) {
      var contato = {
        'nome': '',
        'telefone': '',
        'tipo': '',
        'aluno': {
          'ra': ''
        },
        'grauParentesco': '',
        'presente': '',
        cargo: ''
      };
      vm.contatos.push(contato);
    }

    vm.removeContato = function(c) {
      if (vm.contatos.length > 1) {
        vm.contatos.splice(vm.contatos.indexOf(c), 1);
        if (vm.aluno.ra != '') {
          api.contato.getById.delete(c, function(response) {}, function(response) {});
        }
      } else {
        if (vm.aluno.ra != '') {
          api.contato.getById.delete(c, function(response) {}, function(response) {});
        }
        vm.contatos = [
          {
            'nome': '',
            'telefone': '',
            'tipo': '',
            'aluno': {
              'ra': ''
            },
            'grauParentesco': '',
            'presente': '',
            'cargo': ''
          }
        ];
      }
    }

    vm.addParente = function(c) {
      var parente = {
        'nome': '',
        'parentesco': '',
        'escolaridade': '',
        'dataNascimento': null,
        'ocupacao': '',
        'salario': '',
        'localTrabalho': '',
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'dataNascimento': null,
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'dataCadastro': '',
          'meioTransporte': '',
          'observacoes': ''
        }
      };
      vm.parentes.push(parente);
    }

    vm.removeParente = function(c) {
      if (vm.parentes.length > 1) {
        vm.parentes.splice(vm.parentes.indexOf(c), 1);
        if (vm.aluno.ra != '') {
          api.parente.getById.delete(c, function(response) {}, function(response) {});
        }
      } else {
        if (vm.aluno.ra != '') {
          api.parente.getById.delete(c, function(response) {}, function(response) {});
        }
        vm.parentes = [
          {
            'nome': '',
            'parentesco': '',
            'escolaridade': '',
            'dataNascimento': '',
            'ocupacao': '',
            'salario': '',
            'localTrabalho': '',
            'aluno': {
              'ra': '',
              'nome': '',
              'turma': {
                'educador': ''
              },
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
      }
    }

    vm.isLastParente = function(p) {
      var index = vm.parentes.indexOf(p);
      if (index == (vm.parentes.length - 1)) {
        return true;
      } else {
        return false;
      }
    }

    vm.addAutomovel = function(a) {
      var automovel = {
        'id': '',
        'modelo': '',
        'ano': '',
        'financiado': '',
        'estruturaFamiliar': {
          'id': ''
        }
      };
      vm.automoveis.push(automovel);
    }

    vm.removeAutomovel = function(a) {
      if (vm.automoveis.length > 1) {
        vm.automoveis.splice(vm.automoveis.indexOf(a), 1);
        if (vm.aluno.ra != '') {
          api.automovel.getById.delete(a, function(response) {}, function(response) {});
        }
      } else {
        if (vm.aluno.ra != '') {
          api.automovel.getById.delete(a, function(response) {}, function(response) {});
        }
        vm.automoveis = [
          {
            'modelo': '',
            'ano': '',
            'financiado': '',
            'estruturaFamiliar': {
              'id': ''
            }
          }
        ];
      }
    }

    vm.addImovel = function(i) {
      var imovel = {
        'financiado': '',
        'estruturaFamiliar': {
          'id': ''
        }
      };
      vm.imoveis.push(imovel);
    }

    vm.removeImovel = function (i) {
      if (vm.imoveis.length > 1) {
        vm.imoveis.splice(vm.imoveis.indexOf(i), 1);
        if (vm.aluno.ra !== '') {
          api.imovel.getById.delete(i, function(response) {}, function(response) {});
        }
      } else {
        if (vm.aluno.ra !== '') {
          api.imovel.getById.delete(i, function(response) {}, function(response) {});
        }
        vm.imoveis = [
          {
            'financiado': '',
            'estruturaFamiliar': {
              'id': ''
            }
          }
        ];
      }
    }
    vm.addNewAluno = addNewAluno;
    vm.deleteAlunoConfirm = deleteAlunoConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;

    vm.sendForm = sendForm;

    /**
  * Delete Aluno Confirm Dialog
  */
    function deleteAlunoConfirm(ev) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar este aluno?').htmlContent('<b>' + vm.aluno.nome + ' (' + vm.aluno.ra + '</b>' + ') será apagado(a).').ariaLabel('apagar aluno').targetEvent(ev).ok('OK').cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Aluno.

        // Remove o Aluno do BD
        api.aluno.getByRa.delete({
          'ra': vm.aluno.ra
        },
        // Sucesso
        function(response) {},
        // Erro
        function(response) {
          console.error(response);
        });

        // Remove a da lista na tela a linha deste Aluno
        vm.alunos.splice(vm.alunos.indexOf(Aluno), 1);
      });
    }

    function closeDialog() {
      $mdDialog.hide();
    }

    function sendForm() {

      vm.aluno.rg = vm.aluno.rg.replace(/\-/g, "");
      vm.aluno.rg = vm.aluno.rg.replace(/\./g, "");

      if (vm.aluno.ra != '') {
        //Atualiza ALUNO

        api.aluno.getByRa.update({
          'ra': vm.aluno.ra
        }, vm.aluno, function(response) {}, function(response) {});

        api.roupa.getById.update({
          'id': vm.roupa.aluno.ra
        }, vm.roupa, function(response) {}, function(response) {});

        vm.contatos.push(vm.paiContato);
        vm.contatos.push(vm.maeContato);

        vm.contatos.forEach(function(contato) {
          if (contato.id !== undefined) {
            if (contato.tipo == 'Generico') {
              api.contato.getById.update({
                'id': contato.id
              }, contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Responsavel') {
              api.contato.getResponsavelById.update({
                'id': contato.id
              }, contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Profissional') {
              api.contato.getProfissionalById.update({
                'id': contato.id
              }, contato, function(response) {}, function(response) {});
            }
          } else {
            contato.aluno.ra = vm.aluno.ra;
            if (contato.tipo == 'Generico') {
              api.contato.list.save(contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Responsavel') {
              api.contato.responsavel.save(contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Profissional') {
              api.contato.profissional.save(contato, function(response) {}, function(response) {});
            }
          }
        });

        api.endereco.getByCepNumero.update({
          'cep': vm.endereco.cep,
          'numero': vm.endereco.numero
        }, vm.endereco, function(response) {},
        // Erro
        function(response) {
          console.error(response);
        });

        api.saude.getById.update({
          'raAluno': vm.saude.aluno.ra
        }, vm.saude, function(response) {},
        // Erro
        function(response) {
          console.error(response);
        });

        api.estruturaFamiliar.getById.update({
          'id': vm.estruturaFamiliar.id
        }, vm.estruturaFamiliar, function(response) {}, function(response) {
          console.error(response);
        });

        vm.automoveis.forEach(function(automovel) {
          if (automovel.id !== '') {
            api.automovel.getById.update({
              'id': automovel.id
            }, automovel, function(response) {}, function(response) {
              console.error(response);
            });
          } else {
            automovel.estruturaFamiliar.id = vm.estruturaFamiliar.id;
            api.automovel.list.save(automovel, function(response) {}, function(response) {});
          }

        });

        vm.imoveis.forEach(function(imovel) {
          if (imovel.id !== undefined) {
            api.imovel.getById.update({
              'id': imovel.id
            }, imovel, function(response) {}, function(response) {
              console.error(response);
            });
          } else {
            imovel.estruturaFamiliar.id = vm.estruturaFamiliar.id;
            api.imovel.list.save(imovel, function(response) {}, function(response) {});
          }

        });

        if (vm.despesa.estruturaFamiliar.id != '') {
          api.despesa.getById.update({
            'id': vm.despesa.estruturaFamiliar.id
          }, vm.despesa, function(response) {},
          // Erro
          function(response) {
            console.error(response);
          });
        } else {
          api.despesa.list.save(vm.despesa, function(response) {},
          // Erro
          function(response) {});
        }

        vm.parentes.forEach(function(parente) {
          if (parente.id !== undefined) {
            api.parente.getById.update({
              'id': parente.id
            }, parente, function(response) {}, function(response) {
              console.error(response);
            });
          } else {
            parente.aluno.ra = vm.aluno.ra;
            api.parente.list.save(parente, function(response) {}, function(response) {});
          }
        });

        if (vm.aparelhosEletronicos.id != '') {
          api.aparelhosEletronicos.getById.update({
            'id': vm.aparelhosEletronicos.id
          }, vm.aparelhosEletronicos, function(response) {}, function(response) {
            console.error(response);
          });
        } else {
          api.aparelhosEletronicos.list.save(vm.aparelhosEletronicos, function(response) {}, function(response) {});
        }

        if (vm.situacaoHabitacional.aluno.ra != '') {
          api.situacaoHabitacional.getById.update({
            'ra': vm.situacaoHabitacional.aluno.ra
          }, vm.situacaoHabitacional, function(response) {},
          // Erro
          function(response) {
            console.error(response);
          });
        } else {
          api.situacaoHabitacional.list.save(vm.situacaoHabitacional,
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {},
          // Erro
          function(response) {});
        }

      } else {
        //ADD ALUNO
        api.aluno.list.save(vm.aluno,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) { //Até descobrirmos uma forma melhor de mexer com promise
          // Nós esperamos o promise do aluno para fazer posteriores requisições

          vm.aluno = response;
          vm.roupa.aluno.ra = vm.aluno.ra;

          vm.contatos.push(vm.paiContato);
          vm.contatos.push(vm.maeContato);

          vm.contatos.forEach(function(contato) {
            contato.aluno.ra = vm.aluno.ra;
            if (contato.tipo == 'Generico') {
              api.contato.list.save(contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Responsavel') {
              api.contato.responsavel.save(contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Profissional') {
              api.contato.profissional.save(contato, function(response) {}, function(response) {});
            }
          });

          //ADD ROUPA
          api.roupa.list.save(vm.roupa,
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {},
          // Erro
          function(response) {});

          vm.endereco.aluno.ra = vm.aluno.ra;

          //ADD ENDEREÇO
          api.endereco.list.save(vm.endereco,
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {},
          // Erro
          function(response) {});

          vm.saude.aluno.ra = vm.aluno.ra;

          //ADD SAUDE
          api.saude.list.save(vm.saude,
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {},
          // Erro
          function(response) {});

          vm.estruturaFamiliar.aluno.ra = vm.aluno.ra;

          //ADD ESTRUTURA FAMILIAR
          api.estruturaFamiliar.list.save(vm.estruturaFamiliar, function(response) {

            vm.estruturaFamiliar = response;

            vm.automoveis.forEach(function(automovel) {
              automovel.estruturaFamiliar.id = vm.estruturaFamiliar.id;
              api.automovel.list.save(automovel, function(response) {}, function(response) {});
            });

            vm.imoveis.forEach(function(imovel) {
              imovel.estruturaFamiliar.id = vm.estruturaFamiliar.id;
              api.imovel.list.save(imovel, function(response) {}, function(response) {});
            });

            vm.despesa.estruturaFamiliar.id = vm.estruturaFamiliar.id;

            //ADD DESPESA
            api.despesa.list.save(vm.despesa,
            // Exibe o resultado no console do navegador:
            // Sucesso
            function(response) {},
            // Erro
            function(response) {});

            //ADD APARALHOS ELETRÔNICOS
            api.aparelhosEletronicos.list.save(vm.aparelhosEletronicos, function(response) {

              vm.aparelhosEletronicos = response;
              vm.situacaoHabitacional.aparelhosEletronicos.id = vm.aparelhosEletronicos.id;
              vm.situacaoHabitacional.aluno.ra = vm.aluno.ra;

              //ADD SITUAÇÃO HABITACIONAL
              api.situacaoHabitacional.list.save(vm.situacaoHabitacional,
              // Exibe o resultado no console do navegador:
              // Sucesso
              function(response) {},
              // Erro
              function(response) {});
            },
            // Erro
            function(response) {});

          },
          // Erro
          function(response) {
            console.error(response);
          });

          vm.parentes.forEach(function(parente) {
            parente.aluno.ra = vm.aluno.ra;
            api.parente.list.save(parente, function(response) {}, function(response) {});
          });

        },
        // Erro
        function(response) {
          console.error(response);
        });
      }
      //
      closeDialog()
    }
  }

})();
