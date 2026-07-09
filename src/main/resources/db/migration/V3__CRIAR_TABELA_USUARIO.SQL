CREATE TABLE usuario (
     id BIGSERIAL PRIMARY KEY,
     nome_usuario VARCHAR(60) NOT NULL UNIQUE,
     senha VARCHAR(100) NOT NULL,
     perfil VARCHAR(20) NOT NULL
);

INSERT INTO usuario (nome_usuario, senha, perfil) VALUES
('recepcionista', '$2b$10$tjI5WF8IgdE7i1rT9viaA.EMjrx1A3SUDAvZY3IRIegA297alzjLq', 'RECEPCIONISTA'),
('gerente', '$2b$10$fges8GgRzVNm1lARGhQ7Seta6//ddOBIRz.sjmUyYmqw.qQFjiHOW', 'GERENTE');