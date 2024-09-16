CREATE TABLE Edital (
    id INT AUTO_INCREMENT PRIMARY KEY,         	-- Identificador único do edital
    codigo VARCHAR(100) NOT NULL,              	-- Código do edital
    numero INT NOT NULL,                     	-- Número do edital
    qtd_de_inscricao_por_aluno INT NOT NULL,    -- Quantidade de inscrições permitidas por aluno
    situacao_do_edital VARCHAR(10) NOT NULL,   	-- Situação do edital (ex: "aberto", "fechado")
    data_inicio DATE,                         	-- Data de início do edital
    data_fim DATE,                            	-- Data de fim do edital
    peso_cre FLOAT,                            	-- Peso do CRE (Coeficiente de Rendimento Escolar)
    peso_nota FLOAT,                          	-- Peso da nota
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Data e hora de criação
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Data e hora de atualização
);

CREATE TABLE Disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único da Disciplina
    nome VARCHAR(255) NOT NULL,               	-- Nome da Disciplina
    qtd_de_vagas_voluntarios INT NOT NULL, 		-- Quantidade de Vagas de Voluntários
    qtd_de_vagas_remuneradas INT,     			-- Quantidade de Vagas Remuneradas
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Data e hora de criação
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Data e hora de atualização
);

CREATE TABLE Edital_Disciplina (
    edital_id INT,                                			-- Referência ao Edital
    disciplina_id INT,                            			-- Referência à Disciplina
    PRIMARY KEY (edital_id, disciplina_id),       			-- Chave primária composta por edital_id e disciplina_id
    FOREIGN KEY (edital_id) REFERENCES Edital(id), 			-- Chave estrangeira referenciando a tabela Edital
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id) 	-- Chave estrangeira referenciando a tabela Disciplina
);

CREATE TABLE Aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único do Aluno
    nome VARCHAR(255) NOT NULL,               	-- Nome do Aluno
    matricula VARCHAR(50) NOT NULL,             -- Matrícula do Aluno
    email VARCHAR(255) NOT NULL,             	-- Email do Aluno
    senha VARCHAR(255) NOT NULL,             	-- Senha do Aluno
    sexo VARCHAR(10),             				-- Sexo do Aluno
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Data e hora de criação
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Data e hora de atualização
);

CREATE TABLE Coordenador (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único do Coordenador
    nome VARCHAR(255) NOT NULL,               	-- Nome do Coordenador
    email VARCHAR(255) NOT NULL,             	-- Email do Coordenador
    senha VARCHAR(255) NOT NULL,             	-- Senha do Coordenador
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Data e hora de criação
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Data e hora de atualização
);

CREATE TABLE Inscricao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,                       			-- Aluno que fez a inscrição
    disciplina_id INT NOT NULL,                  			-- Disciplina na qual o aluno se inscreveu
    edital_id INT,                               			-- Edital ao qual a inscrição está vinculada (se aplicável)
    data_inscricao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  	-- Data de inscrição
    situacao VARCHAR(20) NOT NULL,                       	-- Situação da inscrição (ex: "ativa", "cancelada")
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id), 			-- Relaciona com a tabela Aluno
    FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id), 	-- Relaciona com a tabela Disciplina
    FOREIGN KEY (edital_id) REFERENCES Edital(id) 			-- Se você quiser relacionar com a tabela de Editais
);