(function() {
    'use strict';

    angular.module('app.historicoOcorrencia').controller('ManipularOcorrenciaDialogController', ManipularOcorrenciaDialogController)

    /** @ngInject */
    function ManipularOcorrenciaDialogController($mdDialog, $state, $window, Ocorrencia, User, msUtils, Aluno, api) {
        var vm = this;
        vm.title = 'Manipular Ocorrencia';
        vm.ocorrencia = angular.copy(Ocorrencia);
        vm.user = User;
        vm.allFields = false;

        if (!vm.ocorrencia) {
            vm.ocorrencia = {
                'data': '',
                'hora': '',
                'descricao': '',
                'raAluno': Aluno.ra,
            };
            vm.title = 'Nova Ocorrencia';
        } else {
            //2017-10-14
            vm.ocorrencia.data = new Date(vm.ocorrencia.data);
            vm.ocorrencia.data.setDate(vm.ocorrencia.data.getDate() + 1)
            vm.title = 'Alterar Ocorrencia';
        }

        if (Ocorrencia !== undefined) {
            vm.newOcorrencia = false
        } else {
            vm.newOcorrencia = true
        }

        vm.closeDialog = closeDialog;
        vm.salvarOcorrencia = salvarOcorrencia;
        vm.adicionarOcorrencia = adicionarOcorrencia;
        vm.deletarOcorrencia = deletarOcorrencia;

        vm.routeReload = function() {
            $window.location.reload()
        }

        function closeDialog() {
            $mdDialog.hide();
            vm.routeReload();
        }

        function formataData(data) {
            var aux = new Date(data);
            aux.setDate(aux.getDate() + 1)
            return aux.toLocaleDateString("pt-BR");
        };

        function formataHora(hora) {
            var aux = hora
            return aux.replace(/\:\d\d$/, '');
        }

        function salvarOcorrencia() {
            vm.ocorrencia.hora += ':00';
            vm.ocorrencia.dataSearch = undefined;
            vm.ocorrencia.horaSearch = undefined;
            //Descomentar a linha abaixo se for fazer o deploy no heroku
            // vm.ocorrencia.data.setDate(vm.ocorrencia.data.getDate() - 1) 

            api.historicoOcorrencia.ocorrencia.update({
                    'ra': vm.ocorrencia.raAluno
                }, vm.ocorrencia,
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

        function adicionarOcorrencia() {
            vm.ocorrencia.hora = vm.ocorrencia.hora + ':00';

            api.historicoOcorrencia.list.save(vm.ocorrencia,
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

        function deletarOcorrencia(ev, ocorrencia) {
            var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar a ocorrencia?')
                .htmlContent('<b>A ocorrencia com a data: ' + formataData(ocorrencia.data) + ' ' + ocorrencia.hora + '</b> será apagada.')
                .ariaLabel('apagar ocorrencia')
                .targetEvent(ev)
                .ok('Sim')
                .cancel('Cancelar');
            $mdDialog.show(confirm).then(function() {
                ocorrencia.hora += ':00'

                ocorrencia.dataSearch = undefined;
                ocorrencia.horaSearch = undefined;
                ocorrencia.data.setDate(vm.ocorrencia.data.getDate() - 1)

                api.historicoOcorrencia.deletarOcorrencia.delete({
                        'data': ocorrencia.data.toISOString().substring(0, 10),
                        'hora': ocorrencia.hora,
                        'ra': ocorrencia.raAluno
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

                closeDialog();

            });
        }

    }
})();
