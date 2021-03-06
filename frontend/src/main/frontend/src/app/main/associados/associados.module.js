(function ()
{
    'use strict';

    angular
        .module('app.associados',
            [
                // 3rd Party Dependencies
                // 'xeditable'
            ]
        )
        .config(config)
        .directive('cpf', function() {
          return {
            require: 'ngModel',
            link: function(scope, element, attr, mCtrl) {
              function myValidation(strCPF) {
                /*
                if (value.indexOf("2") > -1) {
                  mCtrl.$setValidity('charE', true);
                } else {
                  mCtrl.$setValidity('charE', false);
                }
                return value;
                */

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

                  mCtrl.$setValidity('cpf', false);
                  //console.log("1");
                  return strCPF;
                }
                //verificar se os numeros do cpf são todos iguais ex: 000.000.000-00
                for(i=0; i<11; i++){
                    if(strCPF[i] == strCPF[i+1]){
                        flag++;
                    }
                }

                if(flag==10){

                    mCtrl.$setValidity('cpf', false);
                    //console.log("2");
                    return strCPF;
                }

                for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
                Resto = (Soma * 10) % 11;

                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(strCPF.substring(9, 10)) ){

                  mCtrl.$setValidity('cpf', false);
                  //console.log("3");
                  return strCPF;
                }

                Soma = 0;
                for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
                Resto = (Soma * 10) % 11;

                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(strCPF.substring(10, 11) ) ){

                  mCtrl.$setValidity('cpf', false);
                  //console.log("4");
                  return strCPF;
                }


                mCtrl.$setValidity('cpf', true);
                //console.log("5");
                return strCPF;

              }
              mCtrl.$parsers.push(myValidation);
            }
          };
        });

    /** @ngInject */
    function config($stateProvider, msApiProvider, msNavigationServiceProvider)
    {

        $stateProvider.state('app.associados', {
            url    : '/associado',
            views  : {
                'content@app': {
                    templateUrl: 'app/main/associados/associados.html',
                    controller : 'AssociadosController as vm'
                }
            },
            resolve: {
                Associados: function (msApi)
                {
                    return msApi.resolve('associados.associados@query'); // GET para Arrays
                },
                User: function ($window)
                {
                    return JSON.parse($window.localStorage.getItem("currentUser"));
                },
                authenticate: authenticate
                // FormaPgto: function (msApi)
                // {
                //   return msApi.resolve('contacts.formaPgto@query')
                // }
            }

        });

        function authenticate($q, User, $state, $timeout, $mdDialog) {

          if (User != null) {
            // Resolve the promise successfully
            if(User.setor == 'Financeiro' || User.setor == "Desenvolvimento"){
              return $q.when();
            }
            else{
              var confirm = $mdDialog.alert()
                    .title('Não permitido!')
                    .textContent('Apenas o setor financeiro pode acessar esse módulo.')
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
            return $q.reject()
          }
        }

        // Api
        msApiProvider.register('associados.associados', ['/api/associado']);
        //msApiProvider.register('contacts.formaPgto', ['/formaPgto']);
        //msApiProvider.register('associados.user', vm.currentUser = JSON.parse($window.localStorage.getItem("currentUser")););

        // Navigation
        msNavigationServiceProvider.saveItem('financeiro', { // Adiciona um item no menu
            title : 'Financeiro', // Nome do item/grupo no menu.
            group : true, // Define se é um item [false] ou grupo de itens [true].
            weight: 2 // Ordem no menu. Baseado em prioridade.
        });

        msNavigationServiceProvider.saveItem('financeiro.associado', {
            title : 'Gerenciar Associados',
            icon  : 'icon-account-box',
            state : 'app.associados',
            weight: 1
        });

    }
})();
