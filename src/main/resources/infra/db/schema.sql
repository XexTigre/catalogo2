PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS marca (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS grupo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    ordem INTEGER NOT NULL DEFAULT 0,
    visivel_menu INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    marca_id INTEGER NOT NULL,
    codigo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    grupo_id INTEGER,
    observacao TEXT,
    aplicacao_resumida TEXT,
    data_lancamento TEXT,
    UNIQUE(marca_id, codigo),
    FOREIGN KEY (marca_id) REFERENCES marca(id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id)
);

CREATE TABLE IF NOT EXISTS produto_imagem (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    produto_id INTEGER NOT NULL,
    tipo TEXT NOT NULL,
    titulo TEXT,
    caminho TEXT NOT NULL,
    ordem INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS produto_referencia (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    produto_id INTEGER NOT NULL,
    tipo TEXT NOT NULL,
    marca_id INTEGER,
    nome TEXT,
    codigo TEXT,
    fonte_url TEXT,
    observacao TEXT,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE,
    FOREIGN KEY (marca_id) REFERENCES marca(id)
);

CREATE TABLE IF NOT EXISTS montadora (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS veiculo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    montadora_id INTEGER NOT NULL,
    modelo TEXT NOT NULL,
    versao TEXT,
    FOREIGN KEY (montadora_id) REFERENCES montadora(id)
);

CREATE TABLE IF NOT EXISTS motor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    montadora_id INTEGER NOT NULL,
    nome TEXT NOT NULL,
    codigo TEXT,
    FOREIGN KEY (montadora_id) REFERENCES montadora(id)
);

CREATE TABLE IF NOT EXISTS produto_aplicacao (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    produto_id INTEGER NOT NULL,
    montadora_id INTEGER NOT NULL,
    veiculo_id INTEGER NOT NULL,
    motor_id INTEGER,
    ano_inicial INTEGER,
    ano_final INTEGER,
    observacao TEXT,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE,
    FOREIGN KEY (montadora_id) REFERENCES montadora(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id),
    FOREIGN KEY (motor_id) REFERENCES motor(id)
);

CREATE TABLE IF NOT EXISTS produto_relacionado (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    produto_id INTEGER NOT NULL,
    relacionado_id INTEGER NOT NULL,
    tipo_relacao TEXT,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE,
    FOREIGN KEY (relacionado_id) REFERENCES produto(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_produto_codigo ON produto(codigo);
CREATE INDEX IF NOT EXISTS idx_produto_descricao ON produto(descricao);
CREATE INDEX IF NOT EXISTS idx_produto_marca ON produto(marca_id);
CREATE INDEX IF NOT EXISTS idx_produto_imagem_produto ON produto_imagem(produto_id);
CREATE INDEX IF NOT EXISTS idx_produto_referencia_produto ON produto_referencia(produto_id);
CREATE INDEX IF NOT EXISTS idx_produto_aplicacao_produto ON produto_aplicacao(produto_id);
CREATE INDEX IF NOT EXISTS idx_produto_relacionado_produto ON produto_relacionado(produto_id);
