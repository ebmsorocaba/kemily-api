(function() {
  'use strict';

  angular
    .module('app.aluno')
    .controller('AlunoDialogController', AlunoDialogController);

  /** @ngInject */
  function AlunoDialogController($mdDialog, Aluno, Roupa, Endereco, Alunos, Turmas, User, msUtils, api) {
    var vm = this;

    console.log('AlunoDialogController EAEEEEEEEE CACHE');

    // Data
    vm.title = 'Alterar Aluno';
    vm.roupa = Roupa;
    vm.endereco = Endereco;

    console.log(vm.roupa);
    vm.aluno = angular.copy(Aluno);
    vm.alunos = Alunos;
    vm.automoveis = [{'modelo': '', 'ano': '', 'financiado': ''}];
    vm.imoveis = [{'financiado': ''}];
    vm.user = User;
    vm.turmas = Turmas;
    vm.newAluno = false;
    vm.allFields = false;
    vm.estados = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];
    vm.camisetas = ['2', '4', '6', '8', '10', '12', '14', '16', 'P', 'M', 'G', 'GG', 'GGG'];
    vm.calcas = ['2', '4', '6', '8', '10', '12', '14', '16', 'P', 'M', 'G', 'GG', 'GGG'];
    vm.estados_civis = ['Solteiros', 'Casados', 'Separados', 'Divorciados', 'Mãe Viúva', 'Pai Viúvo', 'União Estável'];
    vm.situacoes = ['Casa Própria', 'Casa Alugada', 'Casa Cedida', 'Cada Financiada'];
    vm.contatos = [{'nome': '', 'telefone': '', 'tipo': '', 'ra': '', 'grau_parentesco': '', 'presente': '', cargo: ''}];
    vm.tiposContato = ['Generico', 'Responsavel', 'Profissional'];

    vm.isLastContato = function(c) {
      var index = vm.contatos.indexOf(c);
      if (index == (vm.contatos.length - 1)) {
        return true;
      }
      else {
        return false;
      }
    }

    vm.isLastImovel = function(i) {
      var index = vm.imoveis.indexOf(i);
      if (index == (vm.imoveis.length - 1)) {
        return true;
      }
      else {
        return false;
      }
    }

    vm.isLastAutomovel = function(a) {
      var index = vm.automoveis.indexOf(a);
      if (index == (vm.automoveis.length - 1)) {
        return true;
      }
      else {
        return false;
      }
    }

    vm.addContato = function(c) {
      var contato = {'nome': '', 'telefone': '', 'tipo': '', 'ra': '', 'grau_parentesco': '', 'presente': '', cargo: ''};
      vm.contatos.push(contato);
      console.log(vm.contatos);
    }

    vm.removeContato = function(c) {
      if (vm.contatos.length > 1) {
        vm.contatos.splice(vm.contatos.indexOf(c), 1);
      }
      else {
        vm.contatos = [{'nome': '', 'telefone': '', 'tipo': '', 'ra': '', 'grau_parentesco': '', 'presente': '', cargo: ''}];
      }
    }

    vm.addAutomovel = function(a) {
      var automovel = {'modelo': '', 'ano': '', 'financiado': ''};
      vm.automoveis.push(automovel);
    }

    vm.removeAutomovel = function(a) {
      if (vm.automoveis.length > 1) {
        vm.automoveis.splice(vm.automoveis.indexOf(a), 1);
      }
      else {
        vm.automoveis = [{'modelo': '', 'ano': '', 'financiado': ''}];
      }
    }

    vm.addImovel = function(i) {
      var imovel = {'financiado': ''};
      vm.imoveis.push(imovel);
    }

    vm.removeImovel = function(i) {
      if (vm.imoveis.length > 1) {
        vm.imoveis.splice(vm.imoveis.indexOf(i), 1);
      }
      else {
        vm.imoveis = [{'financiado': ''}];
      }
    }
    //vm.ok = false;

    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];
    //vm.calculaCPF = calculaCPF;


    // TODO Ajustar o Aluno conforme o BackEnd
    if (!vm.aluno) {
      // vm.aluno = {
      //   'nome': "",
      //   'turma': {
      //       'educador': "Marina"
      //   },
      //   'data_nascimento': "",
      //   'rg': "",
      //   'naturalidade': "",
      //   'estado': "",
      //   'data_cadastro': "",
      //   'meio_transporte': "",
      //   'observacoes': ""
      // };
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
            'educador': '',
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
              'educador': '',
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
          'problemas_financeiros': null,
          'uso_alcool_drogas': null,
          'alguem_agressivo': null,
          'programas_sociais': null,
          'aluno': {
              'ra': null,
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
      'agua': null,
      'energia_eletrica': null,
      'telefone': null,
      'aluguel': null,
      'financiamento_casa': null,
      'financiamento_carro': null,
      'transporte': null,
      'alimentacao': null,
      'gas': null,
      'cartao_credito': null,
      'emprestimo': null,
      'tv_cabo': null,
      'educacao': null,
      'pensao': null,
      'convenio_medico': null
    };

    vm.aparelhos_eletronicos = {
        'id': null,
        'televisao': null,
        'tv_assinatura': null,
        'computador': null,
        'notebook': null,
        'fogao': null,
        'geladeira': null,
        'microondas': null,
        'tablet': null,
        'maquina_lavar': null,
        'maquina_secar': null,
        'telefone_fixo': null,
        'celular': null
    };

    vm.situacao_habitacional = {
      'aluno': {
          'ra': null,
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
      'situacao': false,
      'esgoto': false,
      'rede_eletrica': false,
      'asfalto': false,
      'numero_comodos': '',
      'alvenaria': false,
      'madeira': false,
      'area_irregular': false,
      'aparelhos_eletronicos': {
          'id': null,
          'televisao': null,
          'tv_assinatura': null,
          'computador': null,
          'notebook': null,
          'fogao': null,
          'geladeira': null,
          'microondas': null,
          'tablet': null,
          'maquina_lavar': null,
          'maquina_secar': null,
          'telefone_fixo': null,
          'celular': null
      }
    };


      vm.title = 'Novo Aluno';
      vm.newAluno = true;
      // vm.aluno.tags = [];
    }

    // Methods
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
            console.log("Oi" + aluno.ra);
            console.log(response);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        );

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
      },vm.aluno,
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
     * Delete Aluno Confirm Dialog
     */
    function deleteAlunoConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este aluno?')
        .htmlContent('<b>' + vm.aluno.nome + ' (' + vm.aluno.ra + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar aluno')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

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
          }
        );

        // Remove a da lista na tela a linha deste Aluno
        vm.alunos.splice(vm.alunos.indexOf(Aluno), 1);
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

    function sendForm(){

      vm.aluno.rg = vm.aluno.rg.replace(/\-/g,"");
      vm.aluno.rg = vm.aluno.rg.replace(/\./g,"");

      //ADD ALUNO
      api.aluno.list.save(vm.aluno,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) { //Até descobrirmos uma forma melhor de mexer com promise
                             // Nós esperamos o promise do aluno para fazer posteriores requisições
          console.log(response);

          vm.aluno = response;
          vm.roupa.aluno.ra = vm.aluno.ra;

          //ADD ROUPA
          api.roupa.list.save(vm.roupa,
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

          vm.endereco.aluno.ra = vm.aluno.ra;

          //ADD ENDEREÇO
          api.endereco.list.save(vm.endereco,
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

          vm.saude.aluno.ra = vm.aluno.ra;

          //ADD SAUDE
          api.saude.list.save(vm.saude,
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

          vm.estrutura_familiar.aluno.ra = vm.aluno.ra;

          //ADD ESTRUTURA FAMILIAR
          api.estruturaFamiliar.list.save(vm.estrutura_familiar,
            function(response) {
              console.log(response);

              vm.estrutura_familiar = response;
              vm.despesa.estrutura_familiar.id = vm.estrutura_familiar.id;

              console.log('AQUUUUUUUUUUUUUUUUUUUUUUI ' + vm.despesa.estrutura_familiar.id);

              //ADD DESPESA
              api.despesa.list.save(vm.despesa,
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

              //ADD APARALHOS ELETRÔNICOS
              api.aparelhosEletronicos.list.save(vm.aparelhos_eletronicos,
                // Exibe o resultado no console do navegador:
                // Sucesso
                function(response) {
                  console.log(response);

                  vm.aparelhos_eletronicos = response;
                  vm.situacao_habitacional.aparelhos_eletronicos.id = vm.aparelhos_eletronicos.id;
                  vm.situacao_habitacional.aluno.ra = vm.aluno.ra;
                  console.log(vm.situacao_habitacional);

                  //ADD SITUAÇÃO HABITACIONAL
                  api.situacaoHabitacional.list.save(vm.situacao_habitacional,
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


                },
                // Erro
                function(response) {
                  console.error(response);
                }
              );



            },
            // Erro
            function(response) {
              console.error(response);
            }
          );

        },
        // Erro
        function(response) {
          console.error(response);
        }
      );



    // Adiciona uma nova linha no topo da lista na tela
      vm.alunos.unshift(vm.aluno);

      closeDialog();

      console.log('Olá sendForm');

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
