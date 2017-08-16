(function() {
  'use strict';

  angular.module('app.aluno').controller('AlunoDialogController', AlunoDialogController);

  /** @ngInject */
  function AlunoDialogController($mdDialog, Aluno, Roupa, Endereco, Saude, Contatos, PaiContato, MaeContato, Parentes, EstruturaFamiliar, SituacaoHabitacional, AparelhosEletronicos, Imoveis, Automoveis, Despesa, Alunos, Turmas, User, msUtils, api) {
    var vm = this;

    // Data
    vm.title = 'Alterar Aluno';
    vm.roupa = angular.copy(Roupa);
    vm.endereco = angular.copy(Endereco);
    vm.saude = angular.copy(Saude);
    if (Contatos != '') {
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
          'grau_parentesco': '',
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
          'data_nascimento': '',
          'ocupacao': '',
          'salario': '',
          'local_trabalho': '',
          'aluno': {
            'ra': '',
            'nome': '',
            'turma': {
              'educador': ''
            },
            'data_nascimento': '',
            'rg': '',
            'naturalidade': '',
            'estado': '',
            'data_cadastro': '',
            'meio_transporte': '',
            'observacoes': ''
          }
        }
      ];
    }
    vm.estrutura_familiar = angular.copy(EstruturaFamiliar);
    vm.situacao_habitacional = angular.copy(SituacaoHabitacional);
    vm.despesa = angular.copy(Despesa);
    vm.aparelhos_eletronicos = AparelhosEletronicos;
    if (Automoveis != '') {
      vm.automoveis = angular.copy(Automoveis);
    } else {
      vm.automoveis = [
        {
          'modelo': '',
          'ano': '',
          'financiado': '',
          'estrutura_familiar': {
            'id': ''
          }
        }
      ];
    }
    if (Imoveis != '') {
      vm.imoveis = angular.copy(Imoveis);
    } else {
      vm.imoveis = [
        {
          'financiado': '',
          'estrutura_familiar': {
            'id': ''
          }
        }
      ];
    }
    vm.paiContato = angular.copy(PaiContato);
    vm.maeContato = angular.copy(MaeContato);
    // function fixContatoArray(pai, mae) {
    //   var indexPai = vm.contatos.indexOf(pai);
    //   var indexMae = vm.contatos.indexOf(mae);
    //
    //   vm.contatos.splice(indexPai, 1);
    //   vm.contatos.splice(indexMae, 1);
    // }
    // fixContatoArray(vm.paiContato, vm.maeContato);
    vm.aluno = angular.copy(Aluno);
    vm.alunos = Alunos;
    vm.user = User;
    vm.turmas = Turmas;
    vm.newAluno = false;
    vm.allFields = false;
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
      'P',
      'M',
      'G',
      'GG',
      'GGG'
    ];
    vm.estados_civis = [
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

    //vm.ok = false;

    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];
    //vm.calculaCPF = calculaCPF;

    // TODO Ajustar o Aluno conforme o BackEnd
    if (!vm.aluno) {
      vm.aluno = {
        'ra': '',
        'nome': '',
        'data_nascimento': '',
        'rg': '',
        'estado': '',
        'data_cadastro': new Date(),
        'meio_transporte': '',
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
          'data_nascimento': '',
          'rg': '',
          'estado': '',
          'data_cadastro': new Date(),
          'meio_transporte': '',
          'observacoes': '',
          'naturalidade': '',
          'turma': {
            'educador': ''
          }
        },
        'tamanho_camiseta': '',
        'tamanho_calca': ''
      };

      vm.endereco = {
        'cep': '',
        'numero': '',
        'rua': '',
        'bairro': '',
        'cidade': '',
        'ponto_referencia': '',
        'complemento': '',
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'data_nascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'data_cadastro': '',
          'meio_transporte': 'Carro',
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
          'data_nascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'data_cadastro': '',
          'meio_transporte': '',
          'observacoes': ''
        },
        'faz_tratamentos_medicos': '',
        'problemas_saude_familia': '',
        'plano_saude': '',
        'pessoas_idosas': '',
        'problemas_psiquiatricos': ''
      };

      vm.estrutura_familiar = {
        'id': '',
        'estado_civil_pais': '',
        'crianca_reside_com': '',
        'problemas_financeiros': '',
        'uso_alcool_drogas': '',
        'alguem_agressivo': '',
        'programas_sociais': '',
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'data_nascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'data_cadastro': '',
          'meio_transporte': '',
          'observacoes': ''
        }
      };

      vm.despesa = {
        'estrutura_familiar': {
          'id': '',
          'estado_civil_pais': '',
          'crianca_reside_com': '',
          'problemas_financeiros': '',
          'uso_alcool_drogas': '',
          'alguem_agressivo': '',
          'programas_sociais': '',
          'aluno': {
            'ra': '',
            'nome': '',
            'turma': {
              'educador': ''
            },
            'data_nascimento': '',
            'rg': '',
            'naturalidade': '',
            'estado': '',
            'data_cadastro': '',
            'meio_transporte': '',
            'observacoes': ''
          }
        },
        'agua': '',
        'energia_eletrica': '',
        'telefone': '',
        'aluguel': '',
        'financiamento_casa': '',
        'financiamento_carro': '',
        'transporte': '',
        'alimentacao': '',
        'gas': '',
        'cartao_credito': '',
        'emprestimo': '',
        'tv_cabo': '',
        'educacao': '',
        'pensao': '',
        'convenio_medico': ''
      };

      vm.aparelhos_eletronicos = {
        'id': '',
        'televisao': '',
        'tv_assinatura': '',
        'computador': '',
        'notebook': '',
        'fogao': '',
        'geladeira': '',
        'microondas': '',
        'tablet': '',
        'maquina_lavar': '',
        'maquina_secar': '',
        'telefone_fixo': '',
        'celular': ''
      };

      vm.situacao_habitacional = {
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'data_nascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'data_cadastro': '',
          'meio_transporte': '',
          'observacoes': ''
        },
        'situacao': '',
        'esgoto': false,
        'rede_eletrica': false,
        'asfalto': false,
        'numero_comodos': '',
        'alvenaria': false,
        'madeira': false,
        'area_irregular': false,
        "aparelhos_eletronicos": {
          "id": '',
          "televisao": '',
          "tv_assinatura": '',
          "computador": '',
          "notebook": '',
          "fogao": '',
          "geladeira": '',
          "microondas": '',
          "tablet": '',
          "maquina_lavar": '',
          "maquina_secar": '',
          "telefone_fixo": '',
          "celular": ''
        }
      };

      vm.parentes = [
        {
          'nome': '',
          'parentesco': '',
          'escolaridade': '',
          'data_nascimento': '',
          'ocupacao': '',
          'salario': '',
          'local_trabalho': '',
          'aluno': {
            'ra': '',
            'nome': '',
            'turma': {
              'educador': ''
            },
            'data_nascimento': '',
            'rg': '',
            'naturalidade': '',
            'estado': '',
            'data_cadastro': '',
            'meio_transporte': '',
            'observacoes': ''
          }
        }
      ];
      vm.automoveis = [
        {
          'modelo': '',
          'ano': '',
          'financiado': '',
          'estrutura_familiar': {
            'id': ''
          }
        }
      ];
      vm.imoveis = [
        {
          'financiado': '',
          'estrutura_familiar': {
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
          'grau_parentesco': '',
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
        'grau_parentesco': 'Pai',
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
        'grau_parentesco': 'Mae',
        'presente': '',
        cargo: ''
      };

      vm.title = 'Novo Aluno';
      vm.newAluno = true;
      // vm.aluno.tags = [];
    }
    else {
      vm.aluno.data_nascimento = new Date(vm.aluno.data_nascimento);
    }

    // Methods
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
        'grau_parentesco': '',
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
            'grau_parentesco': '',
            'presente': '',
            cargo: ''
          }
        ];
      }
    }

    vm.addParente = function(c) {
      var parente = {
        'nome': '',
        'parentesco': '',
        'escolaridade': '',
        'data_nascimento': '',
        'ocupacao': '',
        'salario': '',
        'local_trabalho': '',
        'aluno': {
          'ra': '',
          'nome': '',
          'turma': {
            'educador': ''
          },
          'data_nascimento': '',
          'rg': '',
          'naturalidade': '',
          'estado': '',
          'data_cadastro': '',
          'meio_transporte': '',
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
            'data_nascimento': '',
            'ocupacao': '',
            'salario': '',
            'local_trabalho': '',
            'aluno': {
              'ra': '',
              'nome': '',
              'turma': {
                'educador': ''
              },
              'data_nascimento': '',
              'rg': '',
              'naturalidade': '',
              'estado': '',
              'data_cadastro': '',
              'meio_transporte': '',
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
        'modelo': '',
        'ano': '',
        'financiado': '',
        'estrutura_familiar': {
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
            'estrutura_familiar': {
              'id': ''
            }
          }
        ];
      }
    }

    vm.addImovel = function(i) {
      var imovel = {
        'financiado': '',
        'estrutura_familiar': {
          'id': ''
        }
      };
      vm.imoveis.push(imovel);
    }

    vm.removeImovel = function(i) {
      if (vm.imoveis.length > 1) {
        vm.imoveis.splice(vm.imoveis.indexOf(i), 1);
        if (vm.aluno.ra != '') {
          api.imovel.getById.delete(i, function(response) {}, function(response) {});
        }
      } else {
        if (vm.aluno.ra != '') {
          api.imovel.getById.delete(i, function(response) {}, function(response) {});
        }
        vm.imoveis = [
          {
            'financiado': '',
            'estrutura_familiar': {
              'id': ''
            }
          }
        ];
      }
    }
    vm.addNewAluno = addNewAluno;
    vm.saveAluno = saveAluno;
    vm.deleteAlunoConfirm = deleteAlunoConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;

    vm.sendForm = sendForm;
    //////////

    /**
    * Add new aluno
    */
    function addNewAluno() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD

      api.aluno.list.save(vm.aluno,
      // Exibe o resultado no console do navegador:
      // Sucesso
      function(response) {
        aluno = response;
      },
      // Erro
      function(response) {
        console.error(response);
      });

      // Adiciona uma nova linha no topo da lista na tela
      vm.alunos.unshift(vm.aluno);

      closeDialog();
      //}
    }

    /**
    * Save new aluno
    */
    function saveAluno() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.alunos.length; i++) {
        if (vm.alunos[i].cpf === vm.aluno.cpf) {
          vm.alunos[i] = angular.copy(vm.aluno);
          break;
        }
      }

      // Grava as alterações no BD:
      api.aluno.getByRa.update({
        'ra': vm.aluno.ra
      }, vm.aluno,
      // Exibe o resultado no console do navegador:
      // Sucesso
      function(response) {},
      // Erro
      function(response) {});

      closeDialog();
    }

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

        console.log('Contatos = ' + vm.contatos);
        vm.contatos.forEach(function(contato) {
          if(contato.id != '') {
            if (contato.tipo == 'Generico') {
              api.contato.getById.update({'id': contato.id}, contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Responsavel') {
              api.contato.getResponsavelById.update({'id': contato.id}, contato, function(response) {}, function(response) {});
            }
            if (contato.tipo == 'Profissional') {
              api.contato.getProfissionalById.update({'id': contato.id}, contato, function(response) {}, function(response) {});
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

        console.log('CEP = ' + vm.endereco.cep);
        console.log('Numero = ' + vm.endereco.numero);
        api.endereco.getByCepNumero.update({
          'cep': vm.endereco.cep,
          'numero': vm.endereco.numero
        }, vm.endereco, function(response) {},
        // Erro
        function(response) {
          console.error(response);
        });

        console.log('Saude = ' + vm.saude);
        api.saude.getById.update({
          'ra_aluno': vm.saude.aluno.ra
        }, vm.saude, function(response) {},
        // Erro
        function(response) {
          console.error(response);
        });

        console.log('Estrutura Familiar = ' + vm.estrutura_familiar);
        api.estruturaFamiliar.getById.update({
          'id': vm.estrutura_familiar.id
        }, vm.estrutura_familiar, function(response) {},
        function(response) {
          console.error(response);
        });

        console.log('Automoveis' + vm.automoveis);
        vm.automoveis.forEach(function(automovel) {
          if (automovel.id != '') {
            api.automovel.getById.update({
              'id': automovel.id
            }, automovel, function(response) {},
            function(response) {
              console.error(response);
            });
          } else {
            automovel.estrutura_familiar.id = vm.estrutura_familiar.id;
            api.automovel.list.save(automovel, function(response) {}, function(response) {});
          }

        });

        vm.imoveis.forEach(function(imovel) {
          if (imovel.id != '') {
            api.imovel.getById.update({
              'id': imovel.id
            }, imovel, function(response) {},
            function(response) {
              console.error(response);
            });
          } else {
            imovel.estrutura_familiar.id = vm.estrutura_familiar.id;
            api.imovel.list.save(imovel, function(response) {}, function(response) {});
          }

        });

        if(vm.despesa.estrutura_familiar.id != '') {
          api.despesa.getById.update({
            'id': vm.despesa.estrutura_familiar.id
          }, vm.despesa, function(response) {},
          // Erro
          function(response) {
            console.error(response);
          });
        } else {
          api.despesa.list.save(vm.despesa,
          function(response) {},
          // Erro
          function(response) {});
        }


        vm.parentes.forEach(function(parente) {
          if (parente.id != '') {
            api.parente.getById.update({
              'id': parente.id
            }, parente, function(response) {},
            function(response) {
              console.error(response);
            });
          } else {
            api.parente.list.save(parente, function(response) {}, function(response) {});
          }
        });

        if(vm.aparelhos_eletronicos.id != '') {
          api.aparelhosEletronicos.getById.update({
            'id': vm.aparelhos_eletronicos.id
          }, vm.aparelhos_eletronicos, function(response) {},
          function(response) {
            console.error(response);
          });
        } else {
          api.aparelhosEletronicos.list.save(vm.aparelhos_eletronicos, function(response) {}, function(response) {});
        }

        if (vm.situacao_habitacional.aluno.ra != '') {
          api.situacaoHabitacional.getById.update({
            'ra': vm.situacao_habitacional.aluno.ra
          }, vm.situacao_habitacional, function(response) {},
          // Erro
          function(response) {
            console.error(response);
          });
        } else {
          api.situacaoHabitacional.list.save(vm.situacao_habitacional,
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

          vm.estrutura_familiar.aluno.ra = vm.aluno.ra;

          //ADD ESTRUTURA FAMILIAR
          api.estruturaFamiliar.list.save(vm.estrutura_familiar, function(response) {

            vm.estrutura_familiar = response;

            vm.automoveis.forEach(function(automovel) {
              automovel.estrutura_familiar.id = vm.estrutura_familiar.id;
              api.automovel.list.save(automovel, function(response) {}, function(response) {});
            });

            vm.imoveis.forEach(function(imovel) {
              imovel.estrutura_familiar.id = vm.estrutura_familiar.id;
              api.imovel.list.save(imovel, function(response) {}, function(response) {});
            });

            vm.despesa.estrutura_familiar.id = vm.estrutura_familiar.id;

            //ADD DESPESA
            api.despesa.list.save(vm.despesa,
            // Exibe o resultado no console do navegador:
            // Sucesso
            function(response) {},
            // Erro
            function(response) {});

            //ADD APARALHOS ELETRÔNICOS
            api.aparelhosEletronicos.list.save(vm.aparelhos_eletronicos, function(response) {

              vm.aparelhos_eletronicos = response;
              vm.situacao_habitacional.aparelhos_eletronicos.id = vm.aparelhos_eletronicos.id;
              vm.situacao_habitacional.aluno.ra = vm.aluno.ra;

              //ADD SITUAÇÃO HABITACIONAL
              api.situacaoHabitacional.list.save(vm.situacao_habitacional,
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
        vm.alunos.unshift(vm.aluno);
      }
      //
      closeDialog();

    }
  }

})();
