DROP TABLE tb_pagamentos;
DROP TABLE tb_associados;
DROP TABLE tb_cartao;
DROP TABLE tb_usuarios;

/** Remove o banco completamente, para fins de desenvolvimento:
DROP DATABASE ebm_admin;
*/
/**CREATE DATABASE ebm_admin;*/
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
INSERT INTO tb_usuarios (nome, senha, grupo, ativo) 
 VALUES ('Ronaldo Pereira', '123456', 'financeiro', TRUE);
INSERT INTO tb_usuarios (nome, senha, grupo, ativo) 
 VALUES ('Carlos Alberto', '654321', 'financeiro', TRUE);
INSERT INTO tb_usuarios (nome, senha, grupo, ativo) 
 VALUES ('Elias Humberto', 'abcd', 'social', FALSE);

INSERT INTO tb_cartao(numero, cpf, bandeira, atual)
 VALUES(4396378924129673, 44444444444, 'Visa', TRUE);
INSERT INTO tb_cartao(numero, cpf, bandeira, atual)
 VALUES(5645646545646454, 55555555555, 'MasterCard', TRUE);
INSERT INTO tb_cartao(numero, cpf, bandeira, atual)
 VALUES(3132132133213233, 66666666666, 'Discover', TRUE);
INSERT INTO tb_cartao(numero, cpf, bandeira, atual)
 VALUES(4564645464611111, 33333333333, 'AmericanExpress', FALSE);

INSERT INTO tb_associados(cpf, nome, celular, email, forma_pgto, num_cartao, valor_atual, venc_atual)
 VALUES(44444444444, 'Godoy Oliveira', 8945666666, 'godoy@gmail.com', 'boleto', 4396378924129673, 10, '12/10/15');
INSERT INTO tb_associados(cpf, nome, celular, email, forma_pgto, num_cartao, valor_atual, venc_atual)
 VALUES(55555555555, 'Solange Goes', 4145666666, 'solange@hotmail.com', 'cartao', 5645646545646454, 15, '05/10/15');
INSERT INTO tb_associados(cpf, nome, celular, email, forma_pgto, num_cartao, valor_atual, venc_atual)
 VALUES(66666666666, 'Jonathan Nunes', 1145666666, 'jojo@uol.com', 'a vista',3132132133213233, 200, '12/10/15');
INSERT INTO tb_associados(cpf, nome, celular, email, forma_pgto, num_cartao, valor_atual, venc_atual)
 VALUES(33333333333, 'Brenda Silva Dias', 1545666666, 'brendadias@gmail.com', 'cartao',4564645464611111, 500, '05/10/15');

INSERT INTO tb_pagamentos(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(1, 44444444444, 'boleto', 4396378924129673, 19191919191919191911, 10, '05/10/15', '15/10/15');
INSERT INTO tb_pagamentos(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(2, 55555555555, 'boleto', 5645646545646454, 65165156116516156166, 107, '05/10/15', '15/10/15');
INSERT INTO tb_pagamentos(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(3, 66666666666, 'boleto', 3132132133213233, 89478948949849849449, 10000, '05/10/15', '15/10/15');
INSERT INTO tb_pagamentos(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(4, 77777777777, 'boleto', 4564645464611111, 32031325165156156615, 15, '05/10/15', '15/10/15');
