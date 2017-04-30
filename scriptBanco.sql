DROP TABLE tb_usuarios;

CREATE TABLE tb_usuarios (
  nome VARCHAR(60) NOT NULL PRIMARY KEY,
  senha VARCHAR(255) NOT NULL,
  grupo VARCHAR(60) NOT NULL,
  ativo BOOLEAN NOT NULL /** Indica para o sistema de login se o usuário pode logar. */
);

INSERT INTO tb_usuarios (nome, senha, grupo, ativo) VALUES ('Ronaldo Pereira', '123456', 'admin', TRUE);
INSERT INTO tb_usuarios (nome, senha, grupo, ativo) VALUES ('Carlos Alberto', '654321', 'financeiro', TRUE);
INSERT INTO tb_usuarios (nome, senha, grupo, ativo) VALUES ('Elias Humberto', 'abcd', 'social', FALSE);
