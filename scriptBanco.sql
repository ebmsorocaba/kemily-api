/*************************/
/*                       */
/** Instruções iniciais **/
/*                       */
/*************************/
/*                       */

-- CREATE DATABASE ebm_admin;
-- Mudar de banco no PostgreSQL (PSQL):
-- \c ebm_admin

-- Define o formato de data no banco de dados: DD/MM/YYYY
SET datestyle = "ISO, DMY";

/*************************/
/*                       */
/** Limpeza das tabelas **/
/*                       */
/*************************/
/*                       */

DROP TABLE pagamento_boleto;
DROP TABLE pagamento_cartao;
DROP TABLE pagamento;
DROP TABLE cartao;
DROP TABLE associado_forma_pagamento;
DROP TABLE associado;
DROP TABLE usuario;
DROP TABLE boleto;

/*---Fim da limpeza---*/
/*                    */

/*************************/
/*                       */
/** Criação das Tabelas **/
/*                       */
/*************************/
/*                       */

/***************************/
/*                         */
/** Modulo Administrativo **/
/*                         */
/***************************/
/*                         */

CREATE TABLE usuario (
  nome VARCHAR(50) NOT NULL PRIMARY KEY,
  senha VARCHAR(20) NOT NULL,
  setor VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL,
  ativo BOOLEAN NOT NULL -- Indica para o sistema de login se o usuário pode logar.
);

/***********************/
/*                     */
/** Modulo Financeiro **/
/*                     */
/***********************/
/*                     */


CREATE TABLE associado (
  cpf VARCHAR(14) NOT NULL PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  celular TEXT,
  email VARCHAR(50) NOT NULL,
  valor_atual NUMERIC(12,2) NOT NULL,
  venc_atual INTEGER NOT NULL
);

CREATE TABLE associado_forma_pagamento (
  cpf_associado VARCHAR(14) NOT NULL REFERENCES associado(cpf),
  forma_pgto VARCHAR(20) NOT NULL,
  atual BOOLEAN NOT NULL,
  PRIMARY KEY(cpf_associado, forma_pgto)
);

CREATE TABLE cartao (
  numero BIGINT NOT NULL PRIMARY KEY,
  bandeira VARCHAR(30) NOT NULL,
  atual BOOLEAN NOT NULL, -- Indica se este o cartão sendo utilizado atualmente pelo associado.
  cpf_associado VARCHAR(14) NOT NULL REFERENCES associado(cpf)
);

CREATE TABLE boleto (
  codigo VARCHAR(60) NOT NULL PRIMARY KEY
);

CREATE TABLE pagamento (
  id SERIAL NOT NULL PRIMARY KEY,
  valor_pago NUMERIC(12,2) NOT NULL,
  vencimento DATE NOT NULL,
  data_pgto DATE NOT NULL,
  cpf_associado VARCHAR(14) NOT NULL,
  forma_pgto_efetuada VARCHAR(20) NOT NULL,

  CONSTRAINT fk_associado_forma FOREIGN KEY(cpf_associado, forma_pgto_efetuada)
  REFERENCES associado_forma_pagamento(cpf_associado,forma_pgto)
);

CREATE TABLE pagamento_boleto (
  id_pagamento BIGINT NOT NULL PRIMARY KEY REFERENCES pagamento(id),
  codigo_boleto VARCHAR(60) NOT NULL REFERENCES boleto(codigo)
);

CREATE TABLE pagamento_cartao (
  id_pagamento BIGINT NOT NULL PRIMARY KEY REFERENCES pagamento(id),
  numero_cartao BIGINT NOT NULL REFERENCES cartao(numero)
);

/***************************/
/*                         */
/**     Modulo Social     **/
/*                         */
/***************************/
/*                         */

CREATE TABLE turma (
  educadora TEXT PRIMARY KEY
);

CREATE TABLE aluno (
  ra SERIAL PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  turma_educadora TEXT NOT NULL REFERENCES turma(educadora),
  data_nascimento DATE NOT NULL,
  rg VARCHAR(10) NOT NULL,
  naturalidade VARCHAR(50) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  data_cadastro DATE NOT NULL,
  meio_transporte VARCHAR(20) NOT NULL,
  observacoes TEXT NOT NULL
);

CREATE TABLE ESTRUTURA_FAMILIAR (
  id: SERIAL PRIMARY KEY,
  estado_civil_pais: VARCHAR(15) NOT NULL,
  crianca_reside_com: VARCHAR(15) NOT NULL,
  problemas_financeiros: BOOLEAN NOT NULL,
  uso_de_alcool_drogas: BOOLEAN NOT NULL,
  alguem_agressivo: BOOLEAN NOT NULL,
  problemas_sociais: BOOLEAN NOT NULL,
  ra_aluno: BIGINT FOREIGN KEY REFERENCES ALUNO(ra)
);

CREATE TABLE endereco (
  cep VARCHAR(15),
  numero VARCHAR(6),
  rua VARCHAR(60),
  bairro VARCHAR(30),
  cidade VARCHAR(50),
  ponto_referencia VARCHAR(50),
  completemento VARCHAR(10),
  ra_aluno BIGINT NOT NULL REFERENCES aluno(ra),
  PRIMARY KEY(cep, numero)
);

CREATE TABLE aparelhos_eletronicos (
  id SERIAL PRIMARY KEY,
  televisao BOOLEAN NOT NULL,
  tv_assinatura BOOLEAN NOT NULL,
  computador BOOLEAN NOT NULL,
  notebook BOOLEAN NOT NULL,
  fogao BOOLEAN NOT NULL,
  geladeira BOOLEAN NOT NULL,
  microondas BOOLEAN NOT NULL,
  tablet BOOLEAN NOT NULL,
  maquina_de_lavar BOOLEAN NOT NULL,
  maquina_de_secar BOOLEAN NOT NULL,
  telefone_fixo BOOLEAN NOT NULL,
  celular BOOLEAN NOT NULL
);

CREATE TABLE despesa (
  id_estrutura_familiar BIGINT PRIMARY KEY REFERENCES estrutura_familiar(id),
  agua NUMERIC(12,2) NOT NULL,
  energia_eletrica NUMERIC(12,2) NOT NULL,
  telefone NUMERIC(12,2) NOT NULL,
  aluguel NUMERIC(12,2) NOT NULL,
  financiamento_casa NUMERIC(12,2) NOT NULL,
  financiamento_carro NUMERIC(12,2) NOT NULL,
  transporte NUMERIC(12,2) NOT NULL,
  alimentacao NUMERIC(12,2) NOT NULL,
  gas NUMERIC(12,2) NOT NULL,
  cartao_credito NUMERIC(12,2) NOT NULL,
  emprestimo NUMERIC(12,2) NOT NULL,
  tv_cabo NUMERIC(12,2) NOT NULL,
  educacao NUMERIC(12,2) NOT NULL,
  pensao NUMERIC(12,2) NOT NULL,
  convenio_medico NUMERIC(12,2) NOT NULL
);

CREATE TABLE CONTATO (
  id: SERIAL,
  nome: VARCHAR(80) NOT NULL,
  telefone: VARCHAR(20) NOT NULL,
  ra_aluno: VARCHAR(15) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (ra_aluno) REFERENCES ALUNO(ra)
);

CREATE TABLE ROUPA (
  ra_aluno: BIGINT PRIMARY KEY REFERENCES ALUNO(ra),
  tamanho_camiseta: VARCHAR(5) NOT NULL,
  tamanho_calca: VARCHAR(5) NOT NULL
);

CREATE TABLE CONTATO_RESPONSAVEL (
  id_contato: BIGINT PRIMARY KEY REFERENCES CONTATO(id),
  grau_parentesco: VARCHAR(15) NOT NULL,
  presente: BOOLEAN NOT NULL
);

CREATE TABLE CONTATO_PROFISSIONAL (
  id_contato: BIGINT PRIMARY KEY REFERENCES CONTATO(id),
  cargo: TEXT NOT NULL
);

CREATE TABLE AUTOMOVEL (
  id: SERIAL PRIMARY KEY,
  modelo: VARCHAR(30) NOT NULL,
  ano: VARCHAR(5) NOT NULL,
  financiado: BOOLEAN NOT NULL,
  id_estrutura_familiar: BIGINT FOREIGN KEY REFERENCES ESTRUTURA_FAMILIAR(id)
);

CREATE TABLE IMOVEL (
  id: SERIAL PRIMARY KEY,
  quantidade: INTEGER NOT NULL,
  financiado: BOOLEAN NOT NULL,
  id_estrutura_familiar: BIGINT FOREIGN KEY REFERENCES ESTRUTURA_FAMILIAR(id)
);

CREATE TABLE SITUACAO_HABITACIONAL (
  ra_aluno: BIGINT PRIMARY KEY REFERENCES ALUNO(ra),
  situacao: VARCHAR(20),
  esgoto: BOOLEAN,
  asfalto: BOOLEAN,
  numero_comodos: INTEGER,
  alvenaria: BOOLEAN,
  madeira: BOOLEAN,
  area_irregular: BOOLEAN,
  id_aparelhos_eletronicos: BIGINT FOREIGN KEY REFERENCES APARELHOS_ELETRONICOS(id)
);

/*---Fim da criação das tabelas---*/
/*                                */

/**********************************/
/*                                */
/** Dados de exemplo para testes **/
/*                                */
/**********************************/
/*                                */

INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES('Admin', 'pass', 'Administração', 'diegoluizdb@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES ('Ronaldo Pereira', '123456', 'Financeiro', 'diegoluizdb@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES ('Carlos Eduardo', '654321', 'Financeiro', 'diegoluizdb@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES ('Elias Humberto', 'abcd', 'Social', 'diegoluizdb@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES ('Financeiro', '123', 'Financeiro', 'diegoluizdb@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES ('Administração', '123', 'Adminitração', 'diegoluizdb@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
  VALUES ('Social', '123', 'Social', 'diegoluizdb@gmail.com', TRUE);

INSERT INTO associado(cpf, nome, email, valor_atual, venc_atual)
  VALUES('444.444.444-44', 'Godoy Oliveira', 'godoy@gmail.com', 10.44, 12);
INSERT INTO associado(cpf, nome, celular, email, valor_atual, venc_atual)
  VALUES('555.555.555-55', 'Solange Goes', '(15) 99779-0000', 'solange@hotmail.com', 15.20, 05);
INSERT INTO associado(cpf, nome, celular, email, valor_atual, venc_atual)
  VALUES('666.666.666-66', 'Jonathan Nunes', '(11) 98881-5555', 'jojo@uol.com', 200.11, 12);
INSERT INTO associado(cpf, nome, celular, email, valor_atual, venc_atual)
  VALUES('333.333.333-33', 'Cleiton Soares', '(15) 90099-1122', 'cletinho@gmail.com', 500.99, 05);

INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
  VALUES(4396378924129673, 'Visa', TRUE, '444.444.444-44');
INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
  VALUES(5645646545646454, 'MasterCard', TRUE, '555.555.555-55');
INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
  VALUES(3132132133213233, 'Discover', TRUE, '666.666.666-66');
INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
  VALUES(4564645464611111, 'AmericanExpress', FALSE, '333.333.333-33');

INSERT INTO boleto(codigo)
  VALUES ('564654564546454645646');
INSERT INTO boleto(codigo)
  VALUES ('111111111111111111111');
INSERT INTO boleto(codigo)
  VALUES ('999999999999999999999');
INSERT INTO boleto(codigo)
  VALUES ('222222222222222222222');

INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('444.444.444-44', 'Dinheiro', TRUE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('444.444.444-44', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('444.444.444-44', 'Cartão', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('555.555.555-55', 'Dinheiro', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('555.555.555-55', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('555.555.555-55', 'Cartão', TRUE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('666.666.666-66', 'Dinheiro', TRUE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('666.666.666-66', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('666.666.666-66', 'Cartão', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('333.333.333-33', 'Dinheiro', TRUE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('333.333.333-33', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento(cpf_associado, forma_pgto, atual)
  VALUES('333.333.333-33', 'Cartão', FALSE);

INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(10, '05/10/17', '15/10/17', '444.444.444-44', 'Boleto');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(10, '05/10/17', '15/10/17', '444.444.444-44', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(10000, '05/10/17', '15/10/17', '666.666.666-66', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(15, '05/10/17', '15/1/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(2000, '05/10/17', '15/2/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(555, '05/10/17', '15/3/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(11.95, '05/10/17', '15/4/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(11.95, '05/10/17', '15/5/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento(valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
  VALUES(15, '05/10/17', '15/10/17', '555.555.555-55', 'Cartão');

/*
INSERT INTO pagamento_boleto(id_pagamento, codigo_boleto)
  VALUES (1, '564654564546454645646');

INSERT INTO pagamento_cartao(id_pagamento, numero_cartao)
  VALUES (5, '4396378924129673');
*/

/*---Fim de inserção de dados de exemplo---*/
/*                                         */
;
