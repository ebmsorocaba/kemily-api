SET datestyle = "ISO, DMY";

/***************************/
/*                         */
/** Modulo Administrativo **/
/*                         */
/***************************/
/*                         */

CREATE TABLE usuario (
  codigo  			SERIAL	PRIMARY KEY,
  email 			VARCHAR(50) 	NOT NULL	UNIQUE,
  nome 				VARCHAR(50) 	NOT NULL,
  senha 			VARCHAR(60) 	NOT NULL,
  perguntasecreta 	VARCHAR(100)	NOT NULL,
  respostasecreta 	VARCHAR(100)    NOT NULL,
  ativo				BOOLEAN     	NOT NULL,
  setor			 	VARCHAR(100)    NOT NULL
);


INSERT INTO usuario (email, nome, senha, perguntasecreta, respostasecreta, ativo, setor) VALUES ('felipe@luz', 'Felipe', '$2a$10$seZfixnw6yZmEXZhG2EkjubDZP1a4JQXCuoZVDgwMzb5PUguWUQ/C', 'Qual nome do seu jogo preferido', '$2a$10$RxMS4lIRlrhjl24Z/M31Tuu/UL.psQQArEN3.s1Wpia0K1Dn6NZDu', true, 'financeiro');
/* senha: dota2  respostasecreta: dota */

INSERT INTO usuario (email, nome, senha, perguntasecreta, respostasecreta, ativo, setor) VALUES ('admin@admin', 'admin', '$2a$10$RDCA8772eMDUSQGF9zwPsunuL5h76GuvQpDBVQwqgFpz/j5Gc9l6y', 'Qual nome do seu cachorro', '$2a$10$b/V28KFPdtIM15wfbmnb3eZxf/vlhTdp3ZjT7jsxV2.g8syuXkjne', true, 'administrativo');
/* senha: admin  respostasecreta: bob */

/***********************/
/*                     */
/** Modulo Financeiro **/
/*                     */
/***********************/
/*                     */


CREATE TABLE associado (
  cpf         VARCHAR(14)    NOT NULL PRIMARY KEY,
  nome        VARCHAR(80)    NOT NULL,
  celular     TEXT,
  email       VARCHAR(50)    NOT NULL,
  valor_atual NUMERIC(12, 2) NOT NULL,
  venc_atual  INTEGER        NOT NULL
);

CREATE TABLE associado_forma_pagamento (
  cpf_associado VARCHAR(14) NOT NULL REFERENCES associado (cpf),
  forma_pgto    VARCHAR(20) NOT NULL,
  atual         BOOLEAN     NOT NULL,
  PRIMARY KEY (cpf_associado, forma_pgto)
);

CREATE TABLE cartao (
  numero        BIGINT      NOT NULL PRIMARY KEY,
  bandeira      VARCHAR(30) NOT NULL,
  atual         BOOLEAN     NOT NULL, -- Indica se este o cartão sendo utilizado atualmente pelo associado.
  cpf_associado VARCHAR(14) NOT NULL REFERENCES associado (cpf)
);

CREATE TABLE boleto (
  codigo VARCHAR(60) NOT NULL PRIMARY KEY
);

CREATE TABLE pagamento (
  id                  SERIAL         NOT NULL PRIMARY KEY,
  valor_pago          NUMERIC(12, 2) NOT NULL,
  vencimento          DATE           NOT NULL,
  data_pgto           DATE           NOT NULL,
  cpf_associado       VARCHAR(14)    NOT NULL,
  forma_pgto_efetuada VARCHAR(20)    NOT NULL,

  CONSTRAINT fk_associado_forma FOREIGN KEY (cpf_associado, forma_pgto_efetuada)
  REFERENCES associado_forma_pagamento (cpf_associado, forma_pgto)
);

CREATE TABLE pagamento_boleto (
  id_pagamento  BIGINT      NOT NULL PRIMARY KEY REFERENCES pagamento (id),
  codigo_boleto VARCHAR(60) NOT NULL REFERENCES boleto (codigo)
);

CREATE TABLE pagamento_cartao (
  id_pagamento  BIGINT NOT NULL PRIMARY KEY REFERENCES pagamento (id),
  numero_cartao BIGINT NOT NULL REFERENCES cartao (numero)
);

/***************************/
/*                         */
/**     Modulo Social     **/
/*                         */
/***************************/
/*                         */

CREATE TABLE endereco (
  cep              VARCHAR(15) NOT NULL,
  numero           VARCHAR(6)  NOT NULL,
  rua              VARCHAR(60) NOT NULL,
  bairro           VARCHAR(30) NOT NULL,
  cidade           VARCHAR(50) NOT NULL,
  ponto_referencia VARCHAR(50),
  complemento      VARCHAR(10),
  PRIMARY KEY (cep, numero)
);

CREATE TABLE aluno (
  ra              SERIAL PRIMARY KEY,
  nome            VARCHAR(80) NOT NULL,
  data_nascimento DATE        NOT NULL,
  rg              VARCHAR(10),
  naturalidade    VARCHAR(50) NOT NULL,
  estado          VARCHAR(2)  NOT NULL,
  data_cadastro   DATE        NOT NULL,
  meio_transporte VARCHAR(20) NOT NULL,
  etnia           TEXT        NOT NULL,
  observacoes     TEXT        NOT NULL,
  cep_aluno       VARCHAR(15) NOT NULL,
  numero_aluno    VARCHAR(6)  NOT NULL,
  FOREIGN KEY (cep_aluno, numero_aluno) REFERENCES endereco (cep, numero) ON DELETE CASCADE
);

CREATE TABLE estrutura_familiar (
  id                    SERIAL PRIMARY KEY,
  estado_civil_pais     VARCHAR(15) NOT NULL,
  crianca_reside_com    VARCHAR(15) NOT NULL,
  problemas_financeiros BOOLEAN     NOT NULL,
  uso_de_alcool_drogas  BOOLEAN     NOT NULL,
  alguem_agressivo      BOOLEAN     NOT NULL,
  programas_sociais     BOOLEAN     NOT NULL,
  ra_aluno              BIGINT REFERENCES aluno (ra) ON DELETE CASCADE
);

CREATE TABLE aparelhos_eletronicos (
  id               SERIAL PRIMARY KEY,
  televisao        BOOLEAN NOT NULL,
  tv_assinatura    BOOLEAN NOT NULL,
  computador       BOOLEAN NOT NULL,
  notebook         BOOLEAN NOT NULL,
  fogao            BOOLEAN NOT NULL,
  geladeira        BOOLEAN NOT NULL,
  microondas       BOOLEAN NOT NULL,
  maquina_de_lavar BOOLEAN NOT NULL,
  maquina_de_secar BOOLEAN NOT NULL,
  telefone_fixo    BOOLEAN NOT NULL,
  celular          BOOLEAN NOT NULL
);

CREATE TABLE despesa (
  id_estrutura_familiar BIGINT PRIMARY KEY REFERENCES estrutura_familiar (id) ON DELETE CASCADE,
  agua                  NUMERIC(12, 2) NOT NULL,
  energia_eletrica      NUMERIC(12, 2) NOT NULL,
  telefone              NUMERIC(12, 2) NOT NULL,
  aluguel               NUMERIC(12, 2) NOT NULL,
  financiamento_casa    NUMERIC(12, 2) NOT NULL,
  financiamento_carro   NUMERIC(12, 2) NOT NULL,
  transporte            NUMERIC(12, 2) NOT NULL,
  alimentacao           NUMERIC(12, 2) NOT NULL,
  gas                   NUMERIC(12, 2) NOT NULL,
  cartao_credito        NUMERIC(12, 2) NOT NULL,
  emprestimo            NUMERIC(12, 2) NOT NULL,
  tv_cabo               NUMERIC(12, 2) NOT NULL,
  educacao              NUMERIC(12, 2) NOT NULL,
  pensao                NUMERIC(12, 2) NOT NULL,
  convenio_medico       NUMERIC(12, 2) NOT NULL
);

CREATE TABLE contato (
  id           SERIAL,
  nome         VARCHAR(80) NOT NULL,
  telefone     VARCHAR(20) NOT NULL,
  email        TEXT        NOT NULL,
  rede_social  TEXT,
  profissional BOOLEAN,
  cargo        TEXT,
  ra_aluno     BIGINT      NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (ra_aluno) REFERENCES aluno (ra) ON DELETE CASCADE
);

CREATE TABLE roupa (
  ra_aluno         BIGINT PRIMARY KEY REFERENCES aluno (ra) ON DELETE CASCADE,
  tamanho_camiseta VARCHAR(5) NOT NULL,
  tamanho_calca    VARCHAR(5) NOT NULL,
  tamanho_sapato   VARCHAR(5) NOT NULL
);

CREATE TABLE responsavel_legal (
  id              SERIAL PRIMARY KEY,
  nome            VARCHAR(80) NOT NULL,
  telefone        VARCHAR(20) NOT NULL,
  email           TEXT        NOT NULL,
  rg              VARCHAR(20) NOT NULL,
  cpf             VARCHAR(20) NOT NULL,
  rede_social     TEXT,
  grau_parentesco VARCHAR(30) NOT NULL,
  estado          VARCHAR(20) NOT NULL,
  ra_aluno        BIGINT      NOT NULL,
  FOREIGN KEY (ra_aluno) REFERENCES aluno (ra) ON DELETE CASCADE
);


CREATE TABLE automovel (
  id                    SERIAL PRIMARY KEY,
  modelo                VARCHAR(30) NOT NULL,
  ano                   VARCHAR(5)  NOT NULL,
  financiado            BOOLEAN     NOT NULL,
  id_estrutura_familiar BIGINT REFERENCES estrutura_familiar (id) ON DELETE CASCADE
);

CREATE TABLE imovel (
  id                    SERIAL PRIMARY KEY,
  financiado            BOOLEAN NOT NULL,
  id_estrutura_familiar BIGINT REFERENCES estrutura_familiar (id) ON DELETE CASCADE
);

CREATE TABLE situacao_habitacional (
  ra_aluno                 BIGINT PRIMARY KEY REFERENCES ALUNO (ra) ON DELETE CASCADE,
  situacao                 VARCHAR(20) NOT NULL,
  esgoto                   BOOLEAN     NOT NULL,
  rede_eletrica            BOOLEAN     NOT NULL,
  asfalto                  BOOLEAN     NOT NULL,
  numero_comodos           INTEGER     NOT NULL,
  alvenaria                BOOLEAN     NOT NULL,
  madeira                  BOOLEAN     NOT NULL,
  area_irregular           BOOLEAN     NOT NULL,
  id_aparelhos_eletronicos BIGINT REFERENCES aparelhos_eletronicos (id)
);

CREATE TABLE membro_familiar (
  id                SERIAL PRIMARY KEY,
  nome              TEXT           NOT NULL,
  parentesco        VARCHAR(20)    NOT NULL,
  escolaridade      VARCHAR(40)    NOT NULL,
  data_nascimento   DATE           NOT NULL,
  ocupacao          TEXT           NOT NULL,
  salario           NUMERIC(12, 2) NOT NULL,
  local_de_trabalho VARCHAR(50)    NOT NULL,
  condicao_trabalho VARCHAR(12)    NOT NULL,
  ra_aluno          BIGINT REFERENCES aluno (ra) ON DELETE CASCADE
);

CREATE TABLE saude (
  ra_aluno                      BIGINT PRIMARY KEY REFERENCES aluno (ra) ON DELETE CASCADE,
  faz_tratamentos_medicos       BOOLEAN NOT NULL,
  descricao_tratamento          TEXT,
  problemas_de_saude_na_familia BOOLEAN NOT NULL,
  plano_de_saude                BOOLEAN NOT NULL,
  pessoas_idosas                BOOLEAN NOT NULL,
  problemas_psiquiatricos       BOOLEAN NOT NULL,
  possui_alergia                BOOLEAN NOT NULL,
  descricao_alergia             TEXT,
  toma_medicacao                BOOLEAN NOT NULL,
  descricao_medicacao                TEXT
);

CREATE TABLE educador (
  cpf                          VARCHAR(20) PRIMARY KEY,
  nome                         TEXT          NOT NULL,
  data_nascimento              DATE          NOT NULL,
  sexo                         VARCHAR(30)   NOT NULL,
  telefone                     VARCHAR(20)   NOT NULL,
  email                        TEXT          NOT NULL,
  cargo                        VARCHAR(15)   NOT NULL,
  numero_carteira_profissional NUMERIC(8, 0) NOT NULL,
  serie_carteira_profissional  NUMERIC(7, 0) NOT NULL,
  numero_pis                   VARCHAR(20)   NOT NULL,
  cep_educador                 VARCHAR(15)   NOT NULL,
  numero_educador              VARCHAR(6)    NOT NULL,
  hora_entrada VARCHAR(10) NOT NULL,
  hora_saida VARCHAR(10) NOT NULL,
  FOREIGN KEY (cep_educador, numero_educador) REFERENCES endereco (cep, numero) ON DELETE CASCADE
);

CREATE TABLE turma (
  id SERIAL PRIMARY KEY,
  cpf_educador VARCHAR(20) REFERENCES educador(cpf),
  periodo TEXT,
  nome TEXT
);

CREATE TABLE aluno_turma (
  ra_aluno BIGINT REFERENCES aluno(ra) on delete CASCADE,
  id_turma BIGINT REFERENCES turma(id) on delete CASCADE,
  PRIMARY KEY(ra_aluno, id_turma)
);

CREATE TABLE historico_ocorrencia (
  data DATE,
  hora TIME,
  ra_aluno BIGINT REFERENCES aluno(ra) on delete CASCADE,
  descricao TEXT NOT NULL,
  PRIMARY KEY(data, hora, ra_aluno)
);

INSERT INTO associado (cpf, nome, email, valor_atual, venc_atual)
VALUES ('444.444.444-44', 'Godoy Oliveira', 'godoy@gmail.com', 10.44, 12);
INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual)
VALUES ('555.555.555-55', 'Solange Goes', '(15) 99779-0000', 'solange@hotmail.com', 15.20, 05);
INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual)
VALUES ('666.666.666-66', 'Jonathan Nunes', '(11) 98881-5555', 'jojo@uol.com', 200.11, 12);
INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual)
VALUES ('333.333.333-33', 'Cleiton Soares', '(15) 90099-1122', 'cletinho@gmail.com', 500.99, 05);

INSERT INTO cartao (numero, bandeira, atual, cpf_associado)
VALUES (4396378924129673, 'Visa', TRUE, '444.444.444-44');
INSERT INTO cartao (numero, bandeira, atual, cpf_associado)
VALUES (5645646545646454, 'MasterCard', TRUE, '555.555.555-55');
INSERT INTO cartao (numero, bandeira, atual, cpf_associado)
VALUES (3132132133213233, 'Discover', TRUE, '666.666.666-66');
INSERT INTO cartao (numero, bandeira, atual, cpf_associado)
VALUES (4564645464611111, 'AmericanExpress', FALSE, '333.333.333-33');

INSERT INTO boleto (codigo)
VALUES ('564654564546454645646');
INSERT INTO boleto (codigo)
VALUES ('111111111111111111111');
INSERT INTO boleto (codigo)
VALUES ('999999999999999999999');
INSERT INTO boleto (codigo)
VALUES ('222222222222222222222');

INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('444.444.444-44', 'Dinheiro', TRUE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('444.444.444-44', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('444.444.444-44', 'Cartão', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('555.555.555-55', 'Dinheiro', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('555.555.555-55', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('555.555.555-55', 'Cartão', TRUE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('666.666.666-66', 'Dinheiro', TRUE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('666.666.666-66', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('666.666.666-66', 'Cartão', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('333.333.333-33', 'Dinheiro', TRUE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('333.333.333-33', 'Boleto', FALSE);
INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual)
VALUES ('333.333.333-33', 'Cartão', FALSE);

INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (10, '05/10/17', '15/10/17', '444.444.444-44', 'Boleto');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (10, '05/10/17', '15/10/17', '444.444.444-44', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (10000, '05/10/17', '15/10/17', '666.666.666-66', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (15, '05/10/17', '15/1/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (2000, '05/10/17', '15/2/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (555, '05/10/17', '15/3/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (11.95, '05/10/17', '15/4/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (11.95, '05/10/17', '15/5/17', '333.333.333-33', 'Dinheiro');
INSERT INTO pagamento (valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada)
VALUES (15, '05/10/17', '15/10/17', '555.555.555-55', 'Cartão');

INSERT INTO endereco (cep, numero, rua, bairro, cidade, ponto_referencia, complemento)
VALUES ('12345234', '312', 'Domingues', 'Centro', 'Sorocaba', '', '');
INSERT INTO endereco (cep, numero, rua, bairro, cidade, ponto_referencia, complemento)
VALUES ('12345234', '316', 'Figueredo', 'Centro', 'Sorocaba', '', '');
INSERT INTO endereco (cep, numero, rua, bairro, cidade, ponto_referencia, complemento)
VALUES ('12345234', '311', 'Assis', 'Centro', 'Sorocaba', '', '');
INSERT INTO endereco (cep, numero, rua, bairro, cidade, ponto_referencia, complemento)
VALUES ('12345234', '318', 'Assis', 'Centro', 'Sorocaba', '', '');

INSERT INTO aluno (nome, data_nascimento, rg, naturalidade, estado, data_cadastro, meio_transporte, etnia, observacoes, cep_aluno, numero_aluno)
VALUES
  ('Kemily', '12/12/2010', '758471231', 'Brasileira', 'SP', '10/07/2017', 'Carro', 'Asiático', '', '12345234', '312');
INSERT INTO aluno (nome, data_nascimento, rg, naturalidade, estado, data_cadastro, meio_transporte, etnia, observacoes, cep_aluno, numero_aluno)
VALUES
  ('Miriam', '07/10/2010', '758471232', 'Brasileira', 'SP', '10/07/2017', 'Onibus', 'Negro', '', '12345234', '316');
INSERT INTO aluno (nome, data_nascimento, rg, naturalidade, estado, data_cadastro, meio_transporte, etnia, observacoes, cep_aluno, numero_aluno)
VALUES
  ('Douglas', '14/07/2010', '758471233', 'Brasileira', 'SP', '10/07/2017', 'A pe', 'Branco', '', '12345234', '311');

INSERT INTO aparelhos_eletronicos (televisao, tv_assinatura, computador, notebook, fogao, geladeira, microondas, maquina_de_lavar, maquina_de_secar, telefone_fixo, celular)
VALUES (TRUE, FALSE, TRUE, FALSE, TRUE, TRUE, TRUE, FALSE, TRUE, FALSE, TRUE);
INSERT INTO aparelhos_eletronicos (televisao, tv_assinatura, computador, notebook, fogao, geladeira, microondas, maquina_de_lavar, maquina_de_secar, telefone_fixo, celular)
VALUES (FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, TRUE);
INSERT INTO aparelhos_eletronicos (televisao, tv_assinatura, computador, notebook, fogao, geladeira, microondas, maquina_de_lavar, maquina_de_secar, telefone_fixo, celular)
VALUES (TRUE, TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, FALSE, FALSE);

INSERT INTO contato (nome, telefone, email, rede_social, profissional, cargo, ra_aluno)
VALUES ('Patricia', '12361415908', 'patricia@gmail.com', 'facebook.com/patricia.324', TRUE, 'Psiquiatra', 1);
INSERT INTO contato (nome, telefone, email, rede_social, profissional, cargo, ra_aluno)
VALUES ('Patricio', '12361415908', 'patricio@gmail.com', 'facebook.com/patrici0.324', TRUE, 'Psiquiatra', 2);
INSERT INTO contato (nome, telefone, email, rede_social, profissional, cargo, ra_aluno)
VALUES ('Claudio', '12361415908', 'claudio@gmail.com', 'facebook.com/claudio.324', TRUE, 'Psiquiatra', 3);

INSERT INTO roupa (ra_aluno, tamanho_camiseta, tamanho_calca, tamanho_sapato)
VALUES (1, 'G', 'G', '20');
INSERT INTO roupa (ra_aluno, tamanho_camiseta, tamanho_calca, tamanho_sapato)
VALUES (2, 'P', 'M', '22');
INSERT INTO roupa (ra_aluno, tamanho_camiseta, tamanho_calca, tamanho_sapato)
VALUES (3, 'M', 'G', '26');

INSERT INTO responsavel_legal (id, nome, cpf, email, estado, grau_parentesco, rg, rede_social, telefone, ra_aluno)
VALUES (1, 'Juliana', '390.903.467-01', 'juliana@gmail.com', 'Presente', 'Mae', '381928093', '', '28491237348', 1);

INSERT INTO responsavel_legal (id, nome, cpf, email, estado, grau_parentesco, rg, rede_social, telefone, ra_aluno)
VALUES (2, 'Julia', '390.903.464-01', 'julia@gmail.com', 'Presente', 'Mae', '381928033', '', '28491278348', 2);

INSERT INTO responsavel_legal (id, nome, cpf, email, estado, grau_parentesco, rg, rede_social, telefone, ra_aluno)
VALUES (3, 'Julio', '390.903.464-11', 'julio@gmail.com', 'Presente', 'Pai', '981928033', '', '28491278378', 3);

INSERT INTO situacao_habitacional (ra_aluno, situacao, esgoto, rede_eletrica, asfalto, numero_comodos, alvenaria, madeira, area_irregular, id_aparelhos_eletronicos)
VALUES (1, 'Casa Alugada', TRUE, FALSE, TRUE, 3, TRUE, FALSE, TRUE, 1);
INSERT INTO situacao_habitacional (ra_aluno, situacao, esgoto, rede_eletrica, asfalto, numero_comodos, alvenaria, madeira, area_irregular, id_aparelhos_eletronicos)
VALUES (2, 'Casa Própria', TRUE, TRUE, FALSE, 4, TRUE, FALSE, FALSE, 2);
INSERT INTO situacao_habitacional (ra_aluno, situacao, esgoto, rede_eletrica, asfalto, numero_comodos, alvenaria, madeira, area_irregular, id_aparelhos_eletronicos)
VALUES (3, 'Casa Própria', TRUE, TRUE, FALSE, 1, FALSE, TRUE, TRUE, 3);

INSERT INTO membro_familiar (nome, parentesco, escolaridade, data_nascimento, ocupacao, salario, local_de_trabalho, condicao_trabalho, ra_aluno)
VALUES ('Marcia', 'Tia', 'Superior - Completo', '16/09/1982', 'Advogada', 2500.00, 'Advocacia', 'CLT', 1);
INSERT INTO membro_familiar (nome, parentesco, escolaridade, data_nascimento, ocupacao, salario, local_de_trabalho, condicao_trabalho, ra_aluno)
VALUES ('Paulo', 'Primo', 'Medio - Completo', '20/02/1992', 'Estagiario', 900.00, 'MotoresCia', 'CLT', 2);
INSERT INTO membro_familiar (nome, parentesco, escolaridade, data_nascimento, ocupacao, salario, local_de_trabalho, condicao_trabalho, ra_aluno)
VALUES ('Ricardo', 'Tio', 'Medio - Completo', '15/01/1987', 'Motorista', 1800.00, 'Transportadora', 'MEI', 3);

INSERT INTO estrutura_familiar (estado_civil_pais, crianca_reside_com, problemas_financeiros, uso_de_alcool_drogas, alguem_agressivo, programas_sociais, ra_aluno)
VALUES ('Divorciados', 'Pai', TRUE, TRUE, TRUE, TRUE, 1);
INSERT INTO estrutura_familiar (estado_civil_pais, crianca_reside_com, problemas_financeiros, uso_de_alcool_drogas, alguem_agressivo, programas_sociais, ra_aluno)
VALUES ('Casados', 'Pais', TRUE, FALSE, TRUE, FALSE, 2);
INSERT INTO estrutura_familiar (estado_civil_pais, crianca_reside_com, problemas_financeiros, uso_de_alcool_drogas, alguem_agressivo, programas_sociais, ra_aluno)
VALUES ('Solteiros', 'Mãe', FALSE, TRUE, TRUE, FALSE, 3);

INSERT INTO despesa (id_estrutura_familiar, agua, energia_eletrica, telefone, aluguel, financiamento_casa, financiamento_carro, transporte, alimentacao, gas, cartao_credito, emprestimo, tv_cabo, educacao, pensao, convenio_medico)
VALUES (1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO despesa (id_estrutura_familiar, agua, energia_eletrica, telefone, aluguel, financiamento_casa, financiamento_carro, transporte, alimentacao, gas, cartao_credito, emprestimo, tv_cabo, educacao, pensao, convenio_medico)
VALUES (2, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
INSERT INTO despesa (id_estrutura_familiar, agua, energia_eletrica, telefone, aluguel, financiamento_casa, financiamento_carro, transporte, alimentacao, gas, cartao_credito, emprestimo, tv_cabo, educacao, pensao, convenio_medico)
VALUES (3, 0, 100, 0, 100, 0, 100, 0, 100, 0, 100, 0, 100, 0, 100, 0);

INSERT INTO imovel (financiado, id_estrutura_familiar)
VALUES (FALSE, 1);
INSERT INTO imovel (financiado, id_estrutura_familiar)
VALUES (TRUE, 2);
INSERT INTO imovel (financiado, id_estrutura_familiar)
VALUES (TRUE, 3);

INSERT INTO automovel (modelo, ano, financiado, id_estrutura_familiar)
VALUES ('Gol', '2000', FALSE, 2);
INSERT INTO automovel (modelo, ano, financiado, id_estrutura_familiar)
VALUES ('Golf', '2002', TRUE, 2);
INSERT INTO automovel (modelo, ano, financiado, id_estrutura_familiar)
VALUES ('Uno Mille', '1997', FALSE, 3);

INSERT INTO saude (ra_aluno, faz_tratamentos_medicos, descricao_tratamento, problemas_de_saude_na_familia, plano_de_saude, pessoas_idosas, problemas_psiquiatricos, possui_alergia, descricao_alergia, toma_medicacao, descricao_medicacao)
VALUES (1, TRUE, 'Doença do rato', TRUE, TRUE, TRUE, TRUE, TRUE, 'Amendoim', TRUE, 'Amoxilina 500 mg');
INSERT INTO saude (ra_aluno, faz_tratamentos_medicos, descricao_tratamento, problemas_de_saude_na_familia, plano_de_saude, pessoas_idosas, problemas_psiquiatricos, possui_alergia, descricao_alergia, toma_medicacao, descricao_medicacao)
VALUES (2, FALSE, '', FALSE, FALSE, FALSE, FALSE, FALSE, '', FALSE, '');
INSERT INTO saude (ra_aluno, faz_tratamentos_medicos, descricao_tratamento, problemas_de_saude_na_familia, plano_de_saude, pessoas_idosas, problemas_psiquiatricos, possui_alergia, descricao_alergia, toma_medicacao, descricao_medicacao)
VALUES (3, FALSE, '', TRUE, FALSE, TRUE, FALSE, TRUE, 'Lactose', FALSE, '');

INSERT INTO educador (cpf, nome, data_nascimento, sexo, telefone, email, cargo, numero_carteira_profissional, serie_carteira_profissional, numero_pis, cep_educador, numero_educador,hora_entrada, hora_saida)
VALUES ('450.059.448-50', 'Diego Ferreira Silva', '16/09/1995', 'Masculino', '(15) 99751-3436', 'diegofs01@hotmail.com',
                          'Voluntário', 012345, 01234, '120.8525.943-1', '12345234', '318', '10:30', '18:30');

INSERT INTO turma(cpf_educador, periodo, nome)
VALUES('450.059.448-50', 'Manhã', 'Turma do Diego Manha');
INSERT INTO turma(cpf_educador, periodo, nome)
VALUES('450.059.448-50', 'Tarde', 'Turma do Diego Tarde');

INSERT INTO aluno_turma(ra_aluno, id_turma)
VALUES(1,1);
INSERT INTO aluno_turma(ra_aluno, id_turma)
VALUES(2,1);
INSERT INTO aluno_turma(ra_aluno, id_turma)
VALUES(3,1);

INSERT INTO historico_ocorrencia (data, hora, ra_aluno, descricao)
VALUES('10/10/2017', '15:35:00', 1, 'Teste');
