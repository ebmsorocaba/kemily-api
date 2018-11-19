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
  cpf         VARCHAR(11)    NOT NULL PRIMARY KEY,
  nome        VARCHAR(80)    NOT NULL,
  celular     TEXT,
  email       VARCHAR(50)    NOT NULL,
  valor_atual NUMERIC(12, 2) NOT NULL,
  venc_atual  INTEGER        NOT NULL
);

CREATE TABLE pagamento (
  id                  	SERIAL         	NOT NULL PRIMARY KEY,
  valor_pago         	NUMERIC(12, 2) 	NOT NULL,
  data_pgto           	DATE           	NOT NULL,
  forma_pgto			VARCHAR(20)    	NOT NULL,
  cpf_associado      	VARCHAR(11)   	NOT NULL
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
VALUES ('44444444444', 'Godoy Oliveira', 'godoy@gmail.com', 10.44, 12);
INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual)
VALUES ('55555555555', 'Solange Goes', '(15) 99779-0000', 'solange@hotmail.com', 15.20, 05);
INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual)
VALUES ('66666666666', 'Jonathan Nunes', '(11) 98881-5555', 'jojo@uol.com', 200.11, 12);
INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual)
VALUES ('33333333333', 'Cleiton Soares', '(15) 90099-1122', 'cletinho@gmail.com', 500.99, 05);

INSERT INTO pagamento (valor_pago, data_pgto, forma_pgto, cpf_associado)
VALUES 				  (10, '15/10/17', 'Boleto', '44444444444');

INSERT INTO pagamento (valor_pago, data_pgto, forma_pgto, cpf_associado)
VALUES 				  (10, '15/10/17', 'Dinheiro', '44444444444');

INSERT INTO pagamento (valor_pago, data_pgto, forma_pgto, cpf_associado)
VALUES 				  (10, '15/10/17', 'Dinheiro', '66666666666');

INSERT INTO pagamento (valor_pago, data_pgto, forma_pgto, cpf_associado)
VALUES 				  (10, '15/1/17', 'Cartao', '33333333333');

INSERT INTO pagamento (valor_pago, data_pgto, forma_pgto, cpf_associado)
VALUES 				  (10, '15/1/17', 'Cartao', '55555555555');


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
