(function() {
    'use strict';

    angular.module('app.historicoOcorrencia', []).config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider) {
      // State
      $stateProvider.state('app.historicoOcorrencia', {
        url: '/historicoOcorrencia',
        views: {
          'content@app': {
            templateUrl: 'app/main/historicoOcorrencia/historicoOcorrencia.html',
            controller: 'HistoricoOcorrenciaController as vm'
          }
        },
        resolve: {
          Alunos: function(msApi) {
            return msApi.resolve('alunos.alunos@query');
          },
          Ocorrencias: function(msApi) {
            return msApi.resolve('ocorrencias.ocorrencias@query'); // GET para Arrays
          },
          User: function($window) {
            return JSON.parse($window.localStorage.getItem("currentUser"));
          },
          authenticate: authenticate
        }
      });

      function authenticate($q, User, $state, $timeout, $mdDialog) {
        if (User != null) {
          // Resolve the promise successfully
          if (User.setor == 'Social' || User.setor == "Desenvolvimento") {
            return $q.when();
          } else {
            var confirm = $mdDialog.alert().title('Não permitido!').textContent('Apenas o setor social pode acessar esse módulo.').ariaLabel('OK').ok('OK')

            $mdDialog.show(confirm).then(function() {});
            return $q.reject();
          }
        } else {
          // The next bit of code is asynchronously tricky.

          $timeout(function() {
            // This code runs after the authentication promise has been rejected.
            // Go to the log-in page
            $window.location.href = '/#/login';
          })

          // Reject the authentication promise to prevent the state from loading
          return $q.reject();
        }
      }

      // Translation
      // $translatePartialLoaderProvider.addPart('app/main/relatorio');

      // Api
      msApiProvider.register('alunos.alunos', ['/api/aluno']);
      msApiProvider.register('ocorrencias.ocorrencias', ['/api/historicoOcorrencia']);
      //msApiProvider.register('sample', ['app/data/sample/sample.json']);

      //Navigation
      msNavigationServiceProvider.saveItem('social', {
        title: 'Social',
        group: true,
        weight: 8
      });

      msNavigationServiceProvider.saveItem('social.historicoOcorrencia', {
        title: 'Histórico de Ocorrências',
        icon: 'icon-clipboard-alert',
        state: 'app.historicoOcorrencia',
        weight: 3
      });
    }
  })();
