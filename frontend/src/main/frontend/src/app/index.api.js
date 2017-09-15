(function ()
{
    'use strict';

    angular
        .module('fuse')
        .factory('api', apiService);

    /** @ngInject */
    function apiService($resource)
    {
        /**
         * You can use this service to define your API urls. The "api" service
         * is designed to work in parallel with "apiResolver" service which you can
         * find in the "app/core/services/api-resolver.service.js" file.
         *
         * You can structure your API urls whatever the way you want to structure them.
         * You can either use very simple definitions, or you can use multi-dimensional
         * objects.
         *
         * Here's a very simple API url definition example:
         *
         *      api.getBlogList = $resource('http://api.example.com/getBlogList');
         *
         * While this is a perfectly valid $resource definition, most of the time you will
         * find yourself in a more complex situation where you want url parameters:
         *
         *      api.getBlogById = $resource('http://api.example.com/blog/:id', {id: '@id'});
         *
         * You can also define your custom methods. Custom method definitions allow you to
         * add hardcoded parameters to your API calls that you want to sent every time you
         * make that API call:
         *
         *      api.getBlogById = $resource('http://api.example.com/blog/:id', {id: '@id'}, {
         *         'getFromHomeCategory' : {method: 'GET', params: {blogCategory: 'home'}}
         *      });
         *
         * In addition to these definitions, you can also create multi-dimensional objects.
         * They are nothing to do with the $resource object, it's just a more convenient
         * way that we have created for you to packing your related API urls together:
         *
         *      api.blog = {
         *                   list     : $resource('http://api.example.com/blog'),
         *                   getById  : $resource('http://api.example.com/blog/:id', {id: '@id'}),
         *                   getByDate: $resource('http://api.example.com/blog/:date', {id: '@date'}, {
         *                       get: {
         *                            method: 'GET',
         *                            params: {
         *                                getByDate: true
         *                            }
         *                       }
         *                   })
         *       }
         *
         * If you look at the last example from above, we overrode the 'get' method to put a
         * hardcoded parameter. Now every time we make the "getByDate" call, the {getByDate: true}
         * object will also be sent along with whatever data we are sending.
         *
         * All the above methods are using standard $resource service. You can learn more about
         * it at: https://docs.angularjs.org/api/ngResource/service/$resource
         *
         * -----
         *
         * After you defined your API urls, you can use them in Controllers, Services and even
         * in the UIRouter state definitions.
         *
         * If we use the last example from above, you can do an API call in your Controllers and
         * Services like this:
         *
         *      function MyController (api)
         *      {
         *          // Get the blog list
         *          api.blog.list.get({},
         *
         *              // Success
         *              function (response)
         *              {
         *                  console.log(response);
         *              },
         *
         *              // Error
         *              function (response)
         *              {
         *                  console.error(response);
         *              }
         *          );
         *
         *          // Get the blog with the id of 3
         *          var id = 3;
         *          api.blog.getById.get({'id': id},
         *
         *              // Success
         *              function (response)
         *              {
         *                  console.log(response);
         *              },
         *
         *              // Error
         *              function (response)
         *              {
         *                  console.error(response);
         *              }
         *          );
         *
         *          // Get the blog with the date by using custom defined method
         *          var date = 112314232132;
         *          api.blog.getByDate.get({'date': date},
         *
         *              // Success
         *              function (response)
         *              {
         *                  console.log(response);
         *              },
         *
         *              // Error
         *              function (response)
         *              {
         *                  console.error(response);
         *              }
         *          );
         *      }
         *
         * Because we are directly using $resource service, all your API calls will return a
         * $promise object.
         *
         * --
         *
         * If you want to do the same calls in your UI Router state definitions, you need to use
         * "apiResolver" service we have prepared for you:
         *
         *      $stateProvider.state('app.blog', {
         *          url      : '/blog',
         *          views    : {
         *               'content@app': {
         *                   templateUrl: 'app/main/apps/blog/blog.html',
         *                   controller : 'BlogController as vm'
         *               }
         *          },
         *          resolve  : {
         *              Blog: function (apiResolver)
         *              {
         *                  return apiResolver.resolve('blog.list@get');
         *              }
         *          }
         *      });
         *
         *  You can even use parameters with apiResolver service:
         *
         *      $stateProvider.state('app.blog.show', {
         *          url      : '/blog/:id',
         *          views    : {
         *               'content@app': {
         *                   templateUrl: 'app/main/apps/blog/blog.html',
         *                   controller : 'BlogController as vm'
         *               }
         *          },
         *          resolve  : {
         *              Blog: function (apiResolver, $stateParams)
         *              {
         *                  return apiResolver.resolve('blog.getById@get', {'id': $stateParams.id);
         *              }
         *          }
         *      });
         *
         *  And the "Blog" object will be available in your BlogController:
         *
         *      function BlogController(Blog)
         *      {
         *          var vm = this;
         *
         *          // Data
         *          vm.blog = Blog;
         *
         *          ...
         *      }
         */

        var api = {};

        // Base Url
        /**
          Indica aonde começa a URI da API do BackEnd na URL.
          Não é necessário incluir o domínio se a API está no mesmo
          domínio/endereço do FrontEnd.
        */
        api.baseUrl = 'api/';

        // Exemplo do FUSE
        // api.sample = $resource(api.baseUrl + 'sample/sample.json');

        // APIs do EBM Kemily
        api.usuario = {
            getUsuarios     : $resource(api.baseUrl + 'usuario'),
            getByNome       : $resource(api.baseUrl + 'usuario/:nome', {nome: '@nome'}, {'update': {method: 'PUT'}}),
            addUsuario      : $resource(api.baseUrl + 'usuario'),
            login           : $resource(api.baseUrl + 'usuario/:nome?senha=:senha', {nome: '@nome'}, {senha: '@senha'}),
            resetarSenha    : $resource(api.baseUrl + 'recuperarSenha/:nome', {nome: '@nome'})
        }

        ///api/recuperarSenha/{nome}
        // API Associado
        api.associado = {
          list      : $resource(api.baseUrl + 'associado'),
          getByCpf  : $resource(api.baseUrl + 'associado/:cpf' + '/', {cpf: '@cpf'}, {'update': {method: 'PUT'}})
        }

        api.formaPgto = {
          list      : $resource(api.baseUrl + 'formaPgto'),
          getByCpf  : $resource(api.baseUrl + 'formaPgto/:cpf', {cpf: '@cpf'})
        }

        api.pagamento = {
          list     : $resource(api.baseUrl + 'pagamento'),
          getById  : $resource(api.baseUrl + 'pagamento/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.relatPagAssociado = {
          list     : $resource(api.baseUrl + 'relatPagAssociado/:cpf/?dataInicio=:dataInicio&dataFim=:dataFim', {cpf: '@cpf'}, {dataInicio: '@dataInicio'}, {dataFim: '@dataFim'})
        }

        api.relatPag = {
          list     : $resource(api.baseUrl + 'relatPag?dataInicio=:dataInicio&dataFim=:dataFim', {dataInicio: '@dataInicio'}, {dataFim: '@dataFim'})
        }

        api.aluno = {
          list  : $resource(api.baseUrl + 'aluno'),
          getByRa : $resource(api.baseUrl + 'aluno/:ra' + '/', {ra: '@ra'}, {'update': {method: 'PUT'}})
        }

        api.aparelhosEletronicos = {
          list  : $resource(api.baseUrl + 'aparelhosEletronicos'),
          getById : $resource(api.baseUrl + 'aparelhosEletronicos/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.automovel = {
          list  : $resource(api.baseUrl + 'automovel'),
          getById : $resource(api.baseUrl + 'automovel/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.despesa = {
          list  : $resource(api.baseUrl + 'despesa'),
          getById : $resource(api.baseUrl + 'despesa/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.endereco = {
          list  : $resource(api.baseUrl + 'endereco'),
          getByCepNumero : $resource(api.baseUrl + 'endereco/:cep?numero=:numero', {cep: '@cep', numero: '@numero'}, {'update': {method: 'PUT'}})
        }

        api.estruturaFamiliar = {
          list  : $resource(api.baseUrl + 'estruturaFamiliar'),
          getById : $resource(api.baseUrl + 'estruturaFamiliar/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.imovel = {
          list  : $resource(api.baseUrl + 'imovel'),
          getById : $resource(api.baseUrl + 'imovel/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.parente = {
          list  : $resource(api.baseUrl + 'parente'),
          getById : $resource(api.baseUrl + 'parente/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.roupa = {
          list  : $resource(api.baseUrl + 'roupa'),
          getById : $resource(api.baseUrl + 'roupa/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        api.saude = {
          list  : $resource(api.baseUrl + 'saude'),
          getById : $resource(api.baseUrl + 'saude/:raAluno' + '/', {raAluno: '@raAluno'}, {'update': {method: 'PUT'}})
        }

        api.situacaoHabitacional = {
          list  : $resource(api.baseUrl + 'situacaoHabitacional'),
          getById : $resource(api.baseUrl + 'situacaoHabitacional/:ra' + '/', {ra: '@ra'}, {'update': {method: 'PUT'}})
        }

        api.educador = {
          list: $resource(api.baseUrl + 'educador'),
          getById : $resource(api.baseUrl + 'educador/:cpf' + '/', {cpf: '@cpf'}, {'update': {method: 'PUT'}})
        }

        api.contato = {
          list  : $resource(api.baseUrl + 'contato'),
          profissional  : $resource(api.baseUrl + 'contato/profissional'),
          responsavel : $resource(api.baseUrl + 'contato/responsavel'),
          getById : $resource(api.baseUrl + 'contato/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}}),
          getProfissionalById : $resource(api.baseUrl + 'contato/profissional/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}}),
          getResponsavelById  : $resource(api.baseUrl + 'contato/responsavel/:id' + '/', {id: '@id'}, {'update': {method: 'PUT'}})
        }

        return api;
    }

})();
