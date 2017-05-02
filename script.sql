/** Remove o banco completamente, para fins de desenvolvimento:
DROP DATABASE ebm_admin;
*/
CREATE DATABASE ebm_admin;
/** Mudar de banco no PostgreSQL (PSQL):
\c ebm_admin
*/

/** Tabelas */
CREATE TABLE tb_usuarios (
  nome VARCHAR(60) NOT NULL PRIMARY KEY,
  senha VARCHAR(255) NOT NULL,
  grupo VARCHAR(60) NOT NULL,
  ativo BOOLEAN NOT NULL /** Indica para o sistema de login se o usuário pode logar. */
);

CREATE TABLE tb_cartao (
  numero BIGINT NOT NULL,
  cpf BIGINT NOT NULL,
  bandeira VARCHAR(30) NOT NULL,
  atual BOOLEAN NOT NULL /** Indica se este o cartão sendo utilizado atualmente pelo associado. */
);

CREATE TABLE tb_associados (
  cpf BIGINT NOT NULL PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  celular BIGINT,
  email VARCHAR(255) NOT NULL,
  forma_pgto VARCHAR(8) NOT NULL,
  num_cartao BIGINT REFERENCES tb_cartao(numero),
  valor_atual MONEY NOT NULL,
  venc_atual DATE NOT NULL
);

CREATE TABLE tb_pagamentos (
  id BIGINT NOT NULL PRIMARY KEY,
  cpf BIGINT NOT NULL,
  forma_pgto VARCHAR(8) NOT NULL,
  num_cartao BIGINT REFERENCES tb_cartao(numero),
  cod_boleto TEXT,
  valor_pago MONEY NOT NULL,
  vencimento DATE NOT NULL,
  data_pgto DATE NOT NULL
);

/** Dados de exemplo para testes */
INSERT INTO tb_usuarios(nome, senha, grupo, ativo)
 VALUES('dev', 'pass', 'Administradores', TRUE);
