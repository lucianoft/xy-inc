CREATE TABLE modelo (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE modelo_atributo (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	tamanho Int(20) NOT NULL,
	id_modelo BIGINT(20) NOT NULL,
	FOREIGN KEY (id_modelo) REFERENCES modelo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;