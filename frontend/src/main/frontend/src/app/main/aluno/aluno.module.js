(function ()
{
    'use strict';

    angular
        .module('app.aluno', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider
            .state('app.aluno', {
                url    : '/aluno',
                views  : {
                    'content@app': {
                        templateUrl: 'app/main/aluno/aluno.html',
                        controller : 'AlunoController as vm'
                    }
                },
                resolve: {
                  AparelhosEletronicos: function(msApi) {
                    return msApi.resolve('aparelhosEletronicos.aparelhosEletronicos@query');
                  },
                  Contatos: function(msApi) {
                    return msApi.resolve('contatos.contatos@query');
                  },
                  Enderecos: function (msApi)
                  {
                      return msApi.resolve('enderecos.enderecos@query'); // GET para Arrays
                  },
                  Roupas: function (msApi)
                  {
                      return msApi.resolve('roupas.roupas@query'); // GET para Arrays
                  },
                  Despesas: function (msApi)
                  {
                      return msApi.resolve('despesas.despesas@query'); // GET para Arrays
                  },
                  EstruturasFamiliares: function (msApi)
                  {
                      return msApi.resolve('estruturasFamiliares.estruturasFamiliares@query'); // GET para Arrays
                  },
                  SituacoesHabitacionais: function (msApi)
                  {
                      return msApi.resolve('situacoesHabitacionais.situacoesHabitacionais@query'); // GET para Arrays
                  },
                  Saudes: function (msApi)
                  {
                      return msApi.resolve('saudes.saudes@query'); // GET para Arrays
                  },
                  Alunos: function (msApi)
                  {
                      return msApi.resolve('alunos.alunos@query'); // GET para Arrays
                  },
                  Automoveis: function (msApi)
                  {
                    return msApi.resolve('automoveis.automoveis@query');
                  },
                  Imoveis: function (msApi) {
                    return msApi.resolve('imoveis.imoveis@query');
                  },
                  Parentes: function (msApi)
                  {
                      return msApi.resolve('parentes.parentes@query');
                  },
                  User: function ($window)
                  {
                      return JSON.parse($window.localStorage.getItem("currentUser"));
                  },
                  authenticate: authenticate
                }
            });

            function authenticate($q, User, $state, $timeout, $mdDialog) {
              if (User != null) {
                // Resolve the promise successfully
                if(User.setor == 'Social' || User.setor == "Administração"){
                  return $q.when();
                }
                else{
                  var confirm = $mdDialog.alert()
                        .title('Não permitido!')
                        .textContent('Apenas adminitradores e o setor social pode acessar esse módulo.')
                        .ariaLabel('OK')
                        .ok('OK')

                  $mdDialog.show(confirm).then(function() {

                  });
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
        msApiProvider.register('roupas.roupas', ['/api/roupa'])
        msApiProvider.register('despesas.despesas', ['/api/despesa'])
        msApiProvider.register('enderecos.enderecos', ['/api/endereco'])
        msApiProvider.register('saudes.saudes', ['/api/saude'])
        msApiProvider.register('situacoesHabitacionais.situacoesHabitacionais', ['/api/situacaoHabitacional'])
        msApiProvider.register('estruturasFamiliares.estruturasFamiliares', ['/api/estruturaFamiliar'])
        msApiProvider.register('contatos.contatos', ['/api/contato'])
        msApiProvider.register('imoveis.imoveis', ['/api/imovel'])
        msApiProvider.register('automoveis.automoveis', ['/api/automovel'])
        msApiProvider.register('aparelhosEletronicos.aparelhosEletronicos', ['/api/aparelhosEletronicos'])
        msApiProvider.register('parentes.parentes', ['/api/composicaoFamiliar'])


        //msApiProvider.register('sample', ['app/data/sample/sample.json']);

        //Navigation
        msNavigationServiceProvider.saveItem('social', {
            title : 'Social',
            group : true,
            weight: 8
        });

        msNavigationServiceProvider.saveItem('social.aluno', {
            title    : 'Gerenciar Alunos',
            icon     : 'icon-school',
            state    : 'app.aluno',
            weight   : 1
        });
    }
})();
