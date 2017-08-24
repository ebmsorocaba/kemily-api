# EBM Kemily
>_Sistema de administração de alunos para associações sem fins lucrativos no formato contra turno escolar._

#### Projeto em construção!
* `dev` : versões parciais de teste de novas funções, mudanças e correções.  
* `master`: versões periódicas para homologação interna.

#### Algumas das tecnologias envolvidas
* [AngularJS 1.6.4](http://angularjs.org)  
* [Fuse - AngularJS Material Design Admin Template 1.4.6](http://themeforest.net/item/fuse-angularjs-material-design-admin-template/12931855)  
* [Angular Material 1.1.4](http://material.angularjs.org)
* [Spring Boot](http://projects.spring.io/spring-boot)

#### Requisitos de software
* [Java JDK & Runtime 8](http://oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (ou [compatível](http://openjdk.java.net/install/index.html))  
* [Node.js 6 LTS](http://nodejs.org)  
* [PostgreSQL 9.6](http://postgresql.org/download/)  
* [Apache Maven 3.5](http://maven.apache.org/download.cgi)  

>_**Obs.:** É necessário estar **conectado à Internet** para fazer a transferência das dependências do projeto._

#### Executando o projeto localmente
1. Configure o arquivo de conexão com os dados do seu servidor PostgreSQL  
`backend/src/main/java/jdbc/ConnectionFactory.java`

2. Edite as linhas 31, 32 e 33. Seguindo o formato (substitua de acordo):  
```javascript
pool.setUrl("jdbc:postgresql://endereco_servidor:porta_psql/base_dados");
pool.setUser("nome_de_usuario_no_banco_de_dados");
pool.setPassword("senha_do_usuario_no_banco_de_dados");
```

3. Execute o script SQL no banco de dados em seu servidor. O arquivo está na raiz do projeto, chamado:  
`scriptBanco.sql`

4. No **diretório raiz**, use o **Maven** para construir o projeto e transferir suas dependências:  
`mvn clean install`

5. Acesse o diretório `backend` e inicialize o servidor:  
`mvn spring-boot:run`

**Pronto!** O projeto agora está em execução na **porta 8080**. Basta acessar a seguinte URL no seu navegador:  
>http://localhost:8080/

#### Atenção!
Se o projeto apresentar erros, tente as seguintes soluções:  
* Verifique novamente as configurações da **conexão** com o  banco de dados. Falhas de conexão **impedem** a construção do projeto.
* Remova os diretórios `node_modules`, `node`, `dist` e `bower_components`. Isso fará com que o Maven faça novamente a transferência das **dependências** do projeto.
* No **Windows**, pode ser necessário executar o _Prompt de Comando_ `cmd.exe` como **Administrador** para instalar as dependências.

>Quer aprender a editar o `README.md` do seu repositório? Veja estes links (em inglês): [Markdown Tutorial](http://markdowntutorial.com), [Wikipedia Markdown Example](http://en.wikipedia.org/wiki/Markdown#Example) e [Mastering Markdown](https://guides.github.com/features/mastering-markdown).
