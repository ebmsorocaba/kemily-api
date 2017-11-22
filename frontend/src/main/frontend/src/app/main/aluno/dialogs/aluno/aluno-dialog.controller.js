(function() {
    'use strict';

    angular.module('app.aluno').controller('AlunoDialogController', AlunoDialogController);

    /** @ngInject */
    function AlunoDialogController($window, $state, $mdDialog, Data, Datas, User, msUtils, api) {
        var vm = this;

        // Data
        vm.title = 'Alterar Aluno';
        vm.automovel = false;
        vm.imovel = false;
        vm.data = angular.copy(Data);
        vm.datas = Datas;
        vm.pai = {};
        vm.mae = {};

        if (vm.data !== undefined) {
            if (vm.data.contatoList.length === 0 || vm.data.contatoList == null) {
                vm.data.contatoList = [{ id: "", nome: "", telefone: "", email: "", redeSocial: "", cargo: "", profissional: false, aluno: { ra: "" } }];
            }

            if (vm.data.responsavelLegalList.length === 0 || vm.data.responsavelLegalList == null) {
                vm.data.responsavelLegalList = [{ id: "", nome: "", telefone: "", email: "", redeSocial: "", rg: "", cpf: "", grauParentesco: "", estado: "", aluno: { ra: "" } }];
            } else {
                var indexMae = 0;
                var indexPai = 0;
                vm.data.responsavelLegalList.forEach(function(a, index) {
                    if (a.grauParentesco == 'Pai') {
                        vm.pai = a;
                        indexPai = index;
                    }
                    if (a.grauParentesco == 'Mae') {
                        vm.mae = a;
                        indexMae = index;
                    }
                })

                vm.data.responsavelLegalList.splice(indexPai, 1);
                vm.data.responsavelLegalList.splice(indexMae, 1);

                if (vm.data.responsavelLegalList.length === 0) {
                    vm.data.responsavelLegalList.push({ id: "", nome: "", telefone: "", email: "", redeSocial: "", rg: "", cpf: "", grauParentesco: "", estado: "", aluno: { ra: "" } })
                }
            }

            if (vm.data.membroFamiliarList.length === 0 || vm.data.membroFamiliarList == null) {
                vm.data.membroFamiliarList = [{ id: "", nome: "", parentesco: "", escolaridade: "", dataNascimento: "", ocupacao: "", salario: "", localTrabalho: "", condicaoTrabalho: "", aluno: { ra: "" } }];
            }

            if (vm.data.automovelList.length === 0 || vm.data.automovelList == null) {
                vm.data.automovelList = [{ id: "", modelo: "", ano: "", financiado: "", estruturaFamiliar: { id: "" } }];
            } else {
                vm.automovel = true;
            }

            if (vm.data.imovelList.length === 0 || vm.data.imovelList == null) {
                vm.data.imovelList = [{ id: "", financiado: "", estruturaFamiliar: { id: "" } }];
            } else {
                vm.imovel = true;
            }
        }

        vm.user = User;
        vm.newAluno = false;
        vm.allFields = false;
        vm.escolaridades = [
            'Fundamental - Incompleto',
            'Fundamental - Completo',
            'Medio - Incompleto',
            'Medio - Completo',
            'Superior - Incompleto',
            'Superior - Completo',
            'Analfabeto',
            'Semi-Analfabeto'
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
        vm.sapatos = [
            '21',
            '22',
            '23',
            '24',
            '25',
            '26',
            '27',
            '28',
            '29',
            '30',
            '31',
            '32',
            '33',
            '34',
            '35',
            '36',
            '37',
            '38',
            '39',
            '40',
            '41',
            '42',
            '43',
            '44',
            '45',
            '46'
        ]
        vm.condicoes = [
            'CLT',
            'MEI',
            'ME',
            'Informal',
            'Desempregado',
            'Estudante',
            'Do Lar'
        ]
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
            'EG',
            'EXG'
        ];
        vm.status = [
            'Presente',
            'Ausente',
            'Falecido'
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
            'EG',
            'EXG'
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
        vm.situacoes = ['Casa Própria', 'Casa Alugada', 'Casa Cedida', 'Casa Financiada'];
        vm.tiposContato = ['Generico', 'Responsavel', 'Profissional'];

        if (vm.data === undefined) {
            vm.title = 'Novo Aluno';
            vm.data = {}
            vm.data.aluno = {
                'ra': '',
                'nome': '',
                'dataNascimento': new Date(),
                'rg': '',
                'estado': '',
                'etnia': '',
                'dataCadastro': new Date(),
                'meioTransporte': '',
                'observacoes': '',
                'naturalidade': '',
                'escola': ''
            };

            vm.data.roupa = {
                'aluno': {
                    'ra': '',
                    'nome': '',
                    'dataNascimento': null,
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

            vm.data.endereco = {
                'cep': '',
                'numero': '',
                'rua': '',
                'bairro': '',
                'cidade': '',
                'pontoReferencia': '',
                'complemento': '',
            };

            vm.data.saude = {
                'aluno': {
                    'ra': '',
                    'nome': '',
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

            vm.data.estruturaFamiliar = {
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
                    'dataNascimento': null,
                    'rg': '',
                    'naturalidade': '',
                    'estado': '',
                    'dataCadastro': '',
                    'meioTransporte': '',
                    'observacoes': ''
                }
            };

            vm.data.despesa = {
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

            vm.data.aparelhosEletronicos = {
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

            vm.data.situacaoHabitacional = {
                'aluno': {
                    'ra': '',
                    'nome': '',
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

            vm.data.membroFamiliarList = [{
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
                    'dataNascimento': null,
                    'rg': '',
                    'naturalidade': '',
                    'estado': '',
                    'dataCadastro': '',
                    'meioTransporte': '',
                    'observacoes': ''
                }
            }];
            vm.data.automovelList = [{
                'modelo': '',
                'ano': '',
                'financiado': '',
                'estruturaFamiliar': {
                    'id': ''
                }
            }];
            vm.data.imovelList = [{
                'financiado': '',
                'estruturaFamiliar': {
                    'id': ''
                }
            }];
            vm.data.contatoList = [{
                'nome': '',
                'telefone': '',
                'tipo': '',
                'estado': '',
                'email': '',
                'redeSocial': '',
                'cargo': '',
                'profissional': false,
                'aluno': {
                    'ra': ''
                }
            }];

            vm.data.responsavelLegalList = [{
                'nome': '',
                'telefone': '',
                'email': '',
                'redeSocial': '',
                'rg': '',
                'cpf': '',
                'grauParentesco': '',
                'estado': '',
                'aluno': {
                    'ra': ''
                }
            }]


            vm.title = 'Novo Aluno';
            vm.newAluno = true;
            vm.automovel = false;
            vm.imovel = false;
            // vm.data.aluno.tags = [];
        } else {
            vm.data.aluno.dataNascimento = new Date(vm.data.aluno.dataNascimento);
            vm.data.membroFamiliarList.forEach(function(membroFamiliar) {
                membroFamiliar.dataNascimento = new Date(membroFamiliar.dataNascimento);
            });
        }

        // Methods
        vm.routeReload = function() {
            $window.location.reload();
        }

        vm.isLastContato = function(c) {
            var index = vm.data.contatoList.indexOf(c);
            return index == (vm.data.contatoList.length - 1);
        };

        vm.isLastMembroFamiliar = function(c) {
            var index = vm.data.membroFamiliarList.indexOf(c);
            return index == (vm.data.membroFamiliarList.length - 1);
        };

        vm.isLastResponsavelLegal = function(c) {
            var index = vm.data.responsavelLegalList.indexOf(c);
            return index == (vm.data.responsavelLegalList.length - 1);
        };

        vm.isLastImovel = function(i) {
            var index = vm.data.imovelList.indexOf(i);
            return index == (vm.data.imovelList.length - 1);
        };

        vm.isLastAutomovel = function(a) {
            var index = vm.data.automovelList.indexOf(a);
            return index == (vm.data.automovelList.length - 1);
        };

        vm.addContato = function(c) {
            var contato = {
                'nome': '',
                'telefone': '',
                'tipo': '',
                'estado': '',
                'email': '',
                'redeSocial': '',
                'cargo': '',
                'profissional': false,
                'aluno': {
                    'ra': ''
                }
            };
            vm.data.contatoList.push(contato);
        }

        vm.addResponsavelLegal = function(c) {
            var responsavelLegal = {
                'nome': '',
                'telefone': '',
                'email': '',
                'redeSocial': '',
                'rg': '',
                'cpf': '',
                'grauParentesco': '',
                'estado': '',
                'aluno': {
                    'ra': ''
                }
            };
            vm.data.responsavelLegalList.push(responsavelLegal);
        }

        vm.removeResponsavelLegal = function(c) {
            if (vm.data.responsavelLegalList.length > 1) {
                vm.data.responsavelLegalList.splice(vm.data.responsavelLegalList.indexOf(c), 1);
                if (vm.data.aluno.ra != '') {
                    api.responsavelLegal.getById.delete(c, function(response) {}, function(response) {});
                }
            } else {
                if (vm.data.aluno.ra != '') {
                    api.responsavelLegal.getById.delete(c, function(response) {}, function(response) {});
                }
                vm.data.responsavelLegalList = [{
                    'nome': '',
                    'telefone': '',
                    'tipo': '',
                    'estado': '',
                    'email': '',
                    'redeSocial': '',
                    'cargo': '',
                    'profissional': false,
                    'aluno': {
                        'ra': ''
                    }
                }];
            }
        }

        vm.isLastResponsavelLegal = function(p) {
            var index = vm.data.responsavelLegalList.indexOf(p);
            return index == (vm.data.responsavelLegalList.length - 1);
        };

        vm.removeContato = function(c) {
            if (vm.data.contatoList.length > 1) {
                vm.data.contatoList.splice(vm.data.contatoList.indexOf(c), 1);
                if (vm.data.aluno.ra != '') {
                    api.contato.getById.delete(c, function(response) {}, function(response) {});
                }
            } else {
                if (vm.data.aluno.ra != '') {
                    api.contato.getById.delete(c, function(response) {}, function(response) {});
                }
                vm.data.contatoList = [{
                    'nome': '',
                    'telefone': '',
                    'tipo': '',
                    'estado': '',
                    'email': '',
                    'redeSocial': '',
                    'cargo': '',
                    'profissional': false,
                    'aluno': {
                        'ra': ''
                    }
                }];
            }
        }

        vm.addParente = function(c) {
            var membroFamiliar = {
                'nome': '',
                'parentesco': '',
                'escolaridade': '',
                'dataNascimento': null,
                'ocupacao': '',
                'salario': '',
                'localTrabalho': '',
                'condicaoTrabalho': '',
                'aluno': {
                    'ra': '',
                    'nome': '',
                    'dataNascimento': null,
                    'rg': '',
                    'naturalidade': '',
                    'estado': '',
                    'dataCadastro': '',
                    'meioTransporte': '',
                    'observacoes': ''
                }
            };
            vm.data.membroFamiliarList.push(membroFamiliar);
        }

        vm.removeParente = function(c) {
            if (vm.data.membroFamiliarList.length > 1) {
                vm.data.membroFamiliarList.splice(vm.data.membroFamiliarList.indexOf(c), 1);
                if (vm.data.aluno.ra != '') {
                    api.membroFamiliar.getById.delete(c, function(response) {}, function(response) {});
                }
            } else {
                if (vm.data.aluno.ra != '') {
                    api.membroFamiliar.getById.delete(c, function(response) {}, function(response) {});
                }
                vm.data.membroFamiliarList = [{
                    'nome': '',
                    'parentesco': '',
                    'escolaridade': '',
                    'dataNascimento': '',
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
                }];
            }
        }

        vm.isLastParente = function(p) {
            var index = vm.data.membroFamiliarList.indexOf(p);
            if (index == (vm.data.membroFamiliarList.length - 1)) {
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
            vm.data.automovelList.push(automovel);
        }

        vm.removeAutomovel = function(a) {
            if (vm.data.automovelList.length > 1) {
                vm.data.automovelList.splice(vm.data.automovelList.indexOf(a), 1);
                if (vm.data.aluno.ra != '') {
                    api.automovel.getById.delete(a, function(response) {}, function(response) {});
                }
            } else {
                if (vm.data.aluno.ra != '') {
                    api.automovel.getById.delete(a, function(response) {}, function(response) {});
                }
                vm.data.automovelList = [{
                    'modelo': '',
                    'ano': '',
                    'financiado': '',
                    'estruturaFamiliar': {
                        'id': ''
                    }
                }];
            }
        }

        vm.addImovel = function(i) {
            var imovel = {
                'financiado': '',
                'estruturaFamiliar': {
                    'id': ''
                }
            };
            vm.data.imovelList.push(imovel);
        }

        vm.removeImovel = function(i) {
            if (vm.data.imovelList.length > 1) {
                vm.data.imovelList.splice(vm.data.imovelList.indexOf(i), 1);
                if (vm.data.aluno.ra !== '') {
                    api.imovel.getById.delete(i, function(response) {}, function(response) {});
                }
            } else {
                if (vm.data.aluno.ra !== '') {
                    api.imovel.getById.delete(i, function(response) {}, function(response) {});
                }
                vm.data.imovelList = [{
                    'financiado': '',
                    'estruturaFamiliar': {
                        'id': ''
                    }
                }];
            }
        }
        vm.deleteAlunoConfirm = deleteAlunoConfirm;
        vm.closeDialog = closeDialog;
        vm.toggleInArray = msUtils.toggleInArray;
        vm.exists = msUtils.exists;

        vm.sendForm = sendForm;
        //////////

        /**
         * Delete Aluno Confirm Dialog
         */
        function deleteAlunoConfirm(ev) {
            var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar este aluno?').htmlContent('<b>' + vm.data.aluno.nome + ' (' + vm.data.aluno.ra + '</b>' + ') será apagado(a).').ariaLabel('apagar aluno').targetEvent(ev).ok('OK').cancel('Cancelar');

            $mdDialog.show(confirm).then(function() {

                // TODO Remover também a [formaPgto] do Aluno.

                // Remove o Aluno do BD
                api.aluno.getByRa.delete({
                        'ra': vm.data.aluno.ra
                    },
                    // Sucesso
                    function(response) {},
                    // Erro
                    function(response) {
                        console.error(response);
                    });

                // Remove a da lista na tela a linha deste Aluno
                vm.datas.splice(vm.datas.indexOf(Data), 1);
            });
        }

        function closeDialog() {
            $mdDialog.hide();
        }

        function sendForm() {

            vm.data.aluno.rg = vm.data.aluno.rg.replace(/\-/g, "");
            vm.data.aluno.rg = vm.data.aluno.rg.replace(/\./g, "");

            vm.mae.grauParentesco = 'Mae';
            vm.pai.grauParentesco = 'Pai';

            vm.data.responsavelLegalList.push(vm.mae);
            vm.data.responsavelLegalList.push(vm.pai);

            if (vm.data.aluno.ra != '') {
                //Atualiza ALUNO

                api.aluno.getByRa.update({
                    'ra': vm.data.aluno.ra
                }, vm.data, function(response) {}, function(response) {});

            } else {
                api.aluno.list.save(vm.data,
                    function(response) {
                        vm.datas.unshift(response);
                    },
                    function(response) {});
            }
            //
            closeDialog()
            vm.routeReload()
        }
    }

})();
