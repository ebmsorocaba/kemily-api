(function() {
  'use strict';

  angular.module('app.aluno', []).config(config);

  /** @ngInject */
  function config($stateProvider, msApiProvider, msNavigationServiceProvider) {
    // State
    $stateProvider.state('app.aluno', {
      url: '/aluno',
      views: {
        'content@app': {
          templateUrl: 'app/main/aluno/aluno.html',
          controller: 'AlunoController as vm'
        }
      },
      resolve: {
        Datas: function(msApi) {
          return msApi.resolve('alunos.alunos@query'); // GET para Arrays
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
    msApiProvider.register('alunos.alunos', ['/api/aluno'])
    //msApiProvider.register('sample', ['app/data/sample/sample.json']);

    //Navigation
    msNavigationServiceProvider.saveItem('social', {
      title: 'Social',
      group: true,
      weight: 8
    });

    msNavigationServiceProvider.saveItem('social.aluno', {
      title: 'Gerenciar Alunos',
      icon: 'icon-library',
      state: 'app.aluno',
      weight: 1
    });
  }
})();
