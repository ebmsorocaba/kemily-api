(function ()
{
    'use strict';

    angular
        .module('app.usuario')
        .controller('UsuarioController', UsuarioController);

    /** @ngInject */
    function UsuarioController(api, $scope){
        var vm = this;
        var usuario = {};
        var usuarios = [];

        // Data
        // vm.helloText = SampleData.data.helloText;

        // Dados de exemplo para testes

        // Methods
        vm.limpaForm = limpaForm;
        vm.addUsuario = addUsuario;
        vm.getUsuario = getUsuario;
        vm.getUsuarios = getUsuarios;

        //////////

        vm.$onInit = function () {
          vm.getUsuarios();
          //console.log(usuarios);
          //etc...
        }


        function getUsuarios(){
          api.usuario.getUsuarios.query({},
            // Success
            function (response)
            {
                console.log(response);
                vm.usuarios = response;
            },
            // Error
            function (response)
            {
                console.error(response);
            }
          );
        }

        function limpaForm(){
          vm.nome = "";
          vm.senha = "";
          vm.email = "";
          vm.setor = "";
          vm.ativo = null;

        }

    function addUsuario(usuario){

      /*
      usuario.nome = vm.nome;
      usuario.senha = vm.senha;
      usuario.email = vm.email;
      usuario.setor = vm.setor;
      usuario.ativo = vm.ativo;
      */
      api.usuario.addUsuario.save(usuario,
        // Success
        function (response)
        {
            console.log(response);
        },
        // Error
        function (response)
        {
            console.error(response);
        }
      );

    }


    function getUsuario(usuario){
      api.usuario.getByNome.get({'nome': usuario.nome},
        // Success
        function (response)
        {
            console.log(response);
        },
        // Error
        function (response)
        {
            console.error(response);
        }
      );
    }
  }
})();
