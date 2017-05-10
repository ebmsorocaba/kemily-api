/** Precisa setar o estilo de data para Dia/Mês/Ano */
SET datestyle = "ISO, DMY";

DROP TABLE pagamento;
DROP TABLE cartao;
DROP TABLE associado;
DROP TABLE usuario;

/**CREATE DATABASE ebm_admin;*/
/** Mudar de banco no PostgreSQL (PSQL):
\c ebm_admin
*/

/** Tabelas */
CREATE TABLE usuario (
  nome VARCHAR(50) NOT NULL PRIMARY KEY,
  senha VARCHAR(20) NOT NULL,
  setor VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL,
  ativo BOOLEAN NOT NULL /** Indica para o sistema de login se o usuário pode logar. */
);

CREATE TABLE associado (
  cpf BIGINT NOT NULL PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  celular BIGINT,
  email VARCHAR(20) NOT NULL,
  forma_pgto VARCHAR(10) NOT NULL,
  valor_atual NUMERIC(12,2) NOT NULL,
  venc_atual DATE NOT NULL
);

CREATE TABLE cartao (
  numero BIGINT NOT NULL PRIMARY KEY,
  bandeira VARCHAR(30) NOT NULL,
  atual BOOLEAN NOT NULL, /** Indica se este o cartão sendo utilizado atualmente pelo associado. */
  cpf_associado BIGINT REFERENCES associado(cpf)
);

CREATE TABLE pagamento (
  id BIGINT NOT NULL PRIMARY KEY,
  cpf BIGINT NOT NULL REFERENCES associado(cpf),
  forma_pgto VARCHAR(10) NOT NULL,
  num_cartao BIGINT REFERENCES cartao(numero),
  cod_boleto TEXT,
  valor_pago NUMERIC(12,2) NOT NULL,
  vencimento DATE NOT NULL,
  data_pgto DATE NOT NULL
);

/** Dados de exemplo para testes */
INSERT INTO usuario(nome, senha, setor, email, ativo)
 VALUES('dev', 'pass', 'Administradores', 'dev@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
 VALUES ('Ronaldo Pereira', '123456', 'Financeiro', 'ronaldo@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
 VALUES ('Carlos Alberto', '654321', 'Financeiro', 'carlos@gmail.com', TRUE);
INSERT INTO usuario(nome, senha, setor, email, ativo)
 VALUES ('Elias Humberto', 'abcd', 'Social', 'elias@gmail.com', FALSE);

INSERT INTO associado(cpf, nome, celular, email, forma_pgto, valor_atual, venc_atual)
 VALUES(44444444444, 'Godoy Oliveira', 8945666666, 'godoy@gmail.com', 'boleto', 10.44, '12/10/15');
INSERT INTO associado(cpf, nome, celular, email, forma_pgto, valor_atual, venc_atual)
 VALUES(55555555555, 'Solange Goes', 4145666666, 'solange@hotmail.com', 'cartao', 15.20, '05/10/15');
INSERT INTO associado(cpf, nome, celular, email, forma_pgto, valor_atual, venc_atual)
 VALUES(66666666666, 'Jonathan Nunes', 1145666666, 'jojo@uol.com', 'a vista', 200.11, '12/10/15');
INSERT INTO associado(cpf, nome, celular, email, forma_pgto, valor_atual, venc_atual)
 VALUES(33333333333, 'Brenda Silva Dias', 1545666666, 'brendadias@gmail.com', 'cartao', 500.99, '05/10/15');

INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
 VALUES(4396378924129673, 'Visa', TRUE, 44444444444);
INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
 VALUES(5645646545646454, 'MasterCard', TRUE, 55555555555);
INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
 VALUES(3132132133213233, 'Discover', TRUE, 66666666666);
INSERT INTO cartao(numero, bandeira, atual, cpf_associado)
 VALUES(4564645464611111, 'AmericanExpress', FALSE, 33333333333);

INSERT INTO pagamento(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(1, 44444444444, 'boleto', 4396378924129673, 19191919191919191911, 10, '05/10/15', '15/10/15');
INSERT INTO pagamento(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(2, 55555555555, 'boleto', 5645646545646454, 65165156116516156166, 107, '05/10/15', '15/10/15');
INSERT INTO pagamento(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(3, 66666666666, 'boleto', 3132132133213233, 89478948949849849449, 10000, '05/10/15', '15/10/15');
INSERT INTO pagamento(id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto)
 VALUES(4, 33333333333, 'boleto', 4564645464611111, 32031325165156156615, 15, '05/10/15', '15/10/15');

