(function() {
    'use strict';

    angular.module('app.historicoOcorrencia').controller('HistoricoOcorrenciaController', HistoricoOcorrenciaController);

    /** @ngInject */
    function HistoricoOcorrenciaController ($scope, $mdSidenav, User, msUtils, $mdDialog, $document, api, $window, Ocorrencias, Alunos) {
        var vm = this;
        
        vm.ocorrencias = Ocorrencias;
        
        vm.alunos = [];

        Alunos.forEach(function(alu) {
            vm.alunos.push(alu.aluno);
        });

        vm.listType = 'all';
        vm.listOrder = 'ra';
        vm.listOrderAsc = false;
        vm.selectedAlunos = [];

        vm.toggleSelectAluno = toggleSelectAluno;

        vm.openOcorrenciaListaDialog = openOcorrenciaListaDialog;
        /* vm.deleteOcorrenciaConfirm = deleteOcorrenciaConfirm;
        vm.deleteOcorrencia = deleteOcorrencia;
        vm.deleteSelectedOcorrencias = deleteSelectedOcorrencias;
        vm.toggleSelectOcorrencia = toggleSelectOcorrencia;
        vm.deselectOcorrencias = deselectOcorrencias;
        vm.selectAllOcorrencias = selectAllOcorrencias; */
        vm.toggleInArray = msUtils.toggleInArray;
        vm.exists = msUtils.exists;

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

        function openOcorrenciaListaDialog (ev, aluno) {
            $mdDialog.show({
              controller: 'HistoricoOcorrenciaListaDialogController',
              controllerAs: 'vm',
              templateUrl: 'app/main/historicoOcorrencia/dialogs/historicoOcorrencia/historicoOcorrencia-lista-dialog.html',
              parent: (angular.element(document.body)),
              targetEvent: ev,
              clickOutsideToClose: false,
              locals: {
                User: vm.user,
                Ocorrencias: vm.ocorrencias,
                Aluno: aluno
              }
            });
        }
        /*
        function deleteOcorrenciaConfirm(ocorrencia, ev) {
            var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar esta ocorrencia?').htmlContent('<b>' + ocorrencia.data + '</b>' + ' será apagado(a).').ariaLabel('apagar ocorrencia').targetEvent(ev).ok('Sim').cancel('Cancelar');
      
            $mdDialog.show(confirm).then(function() {
              deleteOcorrencia(ocorrencia);
              vm.selectedOcorrencias = [];
      
            }, function() {
              //console.log('Cancelou');
            });
        }

        function deleteOcorrencia(ocorrencia) {
            console.log('deleteOcorrencia @ historicoOcorrencia.controller.js');
            api.historicoOcorrencia.getByData.delete({
                'data': data.ocorrencia.data,
                'ra': data.ocorrencia.raAluno
            },
            // Sucesso
            function(response) {
            console.log(response);
            },
            // Erro
            function(response) {
            console.error(response);
            });
    
            vm.ocorrencias.splice(vm.ocorrencias.indexOf(ocorrencia), 1);
        }

        function deleteSelectedOcorrencias(ev) {
            var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar as ocorrencias selecionados?').htmlContent('<b>' + vm.selectedAlunos.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).').ariaLabel('apagar contatos').targetEvent(ev).ok('Sim').cancel('Cancelar');
            $mdDialog.show(confirm).then(function() {
        
                vm.selectedOcorrencias.forEach(function(ocorrencia) {
                deleteOcorrencias(ocorrencia);
                });
        
                vm.selectedOcorrencias = [];
        
            });
    
        }

        function toggleSelectOcorrencia(ocorrencia, event) {
            if (event) {
              event.stopPropagation();
            }
            if (vm.selectedOcorrencias.indexOf(ocorrencia) > -1) {
              vm.selectedOcorrencias.splice(vm.selectedOcorrencias.indexOf(ocorrencia), 1);
            } else {
              vm.selectedOcorrencias.push(ocorrencia);
            }
        }

        function selectAllOcorrencias () {
            vm.selectedOcorrencias = $scope.filteredOcorrencias;
        }

        function deselectOcorrencias () {
            vm.selectedAlunos = [];
        } */

    }

})();  