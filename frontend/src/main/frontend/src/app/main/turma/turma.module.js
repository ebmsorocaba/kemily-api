(function() {
  'use strict';

  angular.module('app.turma', []).config(config);

  /** @ngInject */
  function config($stateProvider, msApiProvider, msNavigationServiceProvider) {
    // State
    $stateProvider.state('app.turma', {
      url: '/turma',
      views: {
        'content@app': {
          templateUrl: 'app/main/turma/turma.html',
          controller: 'TurmasController as vm'
        }
      },
      resolve: {
        User: function($window) {
          return JSON.parse($window.localStorage.getItem("currentUser"));
        },
        authenticate: authenticate,
        Turmas: function(msApi) {
          return msApi.resolve('turmas.turmas@query')
        },
        AlunoTurma: function(msApi){
          return msApi.resolve('alunoTurma.alunoTurma@query')
        },
        Alunos: function(msApi){
          return msApi.resolve('alunos.alunos@query')
        },
        Educadores: function(msApi) {
          return msApi.resolve('educadores.educadores@query')
        }
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

    msApiProvider.register('turmas.turmas', ['/api/turma'])
    msApiProvider.register('alunoTurma.alunoTurma', ['/api/alunoTurma'])
    msApiProvider.register('alunos.alunos', ['/api/aluno'])
    msApiProvider.register('educadores.educadores', ['/api/educador'])

    // Translation
    // $translatePartialLoaderProvider.addPart('app/main/relatorio');

    // Api
    //msApiProvider.register('sample', ['app/data/sample/sample.json']);

    //Navigation
    // msNavigationServiceProvider.saveItem('social', {
    //     title : 'Social',
    //     group : true,
    //     weight: 8
    // });

    msNavigationServiceProvider.saveItem('social.turma', {
      title: 'Gerenciar Turmas',
      icon: 'icon-people',
      state: 'app.turma',
      weight: 2
    });
  }
})();
