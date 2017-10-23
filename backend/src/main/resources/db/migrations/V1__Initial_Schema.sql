/***************************/
/*                         */
/** Modulo Administrativo **/
/*                         */
/***************************/
/*                         */

CREATE TABLE usuario (
  nome  VARCHAR(50) NOT NULL PRIMARY KEY,
  senha VARCHAR(20) NOT NULL,
  setor VARCHAR(20) NOT NULL,
  email VARCHAR(50) NOT NULL,
  ativo BOOLEAN     NOT NULL -- Indica para o sistema de login se o usuário pode logar.
);

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