package domain.service;

import domain.model.Produto;
import domain.model.ProdutoAplicacao;
import domain.model.ProdutoImagem;
import domain.model.ProdutoReferencia;
import domain.model.ProdutoRelacionado;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CadastroProdutoService {
    private final DatabaseManager dbManager;

    public CadastroProdutoService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void salvarProduto(Produto produto,
                              List<ProdutoImagem> imagens,
                              List<ProdutoReferencia> referencias,
                              List<ProdutoAplicacao> aplicacoes,
                              List<ProdutoRelacionado> relacionados) {
        validarProduto(produto);

        try (Connection connection = dbManager.getConnection()) {
            connection.setAutoCommit(false);
            Long produtoId = inserirProduto(connection, produto);
            inserirImagens(connection, produtoId, imagens);
            inserirReferencias(connection, produtoId, referencias);
            inserirAplicacoes(connection, produtoId, aplicacoes);
            inserirRelacionados(connection, produtoId, relacionados);
            connection.commit();
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao salvar produto", e);
        }
    }

    private void validarProduto(Produto produto) {
        if (produto.getMarcaId() == null) {
            throw new IllegalArgumentException("Marca é obrigatória");
        }
        if (produto.getCodigo() == null || produto.getCodigo().isBlank()) {
            throw new IllegalArgumentException("Código é obrigatório");
        }
        if (produto.getDescricao() == null || produto.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
    }

    private Long inserirProduto(Connection connection, Produto produto) throws SQLException {
        String sql = """
                INSERT INTO produto (marca_id, codigo, descricao, grupo_id, observacao, aplicacao_resumida, data_lancamento)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, produto.getMarcaId());
            stmt.setString(2, produto.getCodigo());
            stmt.setString(3, produto.getDescricao());
            if (produto.getGrupoId() == null) {
                stmt.setObject(4, null);
            } else {
                stmt.setLong(4, produto.getGrupoId());
            }
            stmt.setString(5, produto.getObservacao());
            stmt.setString(6, produto.getAplicacaoResumida());
            LocalDate data = produto.getDataLancamento();
            stmt.setString(7, data == null ? null : data.toString());
            stmt.executeUpdate();
            try (var rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        throw new IllegalStateException("Não foi possível obter ID do produto");
    }

    private void inserirImagens(Connection connection, Long produtoId, List<ProdutoImagem> imagens) throws SQLException {
        if (imagens == null) {
            return;
        }
        String sql = "INSERT INTO produto_imagem (produto_id, tipo, titulo, caminho, ordem) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (ProdutoImagem imagem : imagens) {
                stmt.setLong(1, produtoId);
                stmt.setString(2, imagem.getTipo().name());
                stmt.setString(3, imagem.getTitulo());
                stmt.setString(4, imagem.getCaminho());
                stmt.setInt(5, imagem.getOrdem());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void inserirReferencias(Connection connection, Long produtoId, List<ProdutoReferencia> referencias) throws SQLException {
        if (referencias == null) {
            return;
        }
        String sql = """
                INSERT INTO produto_referencia (produto_id, tipo, marca_id, nome, codigo, fonte_url, observacao)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (ProdutoReferencia referencia : referencias) {
                stmt.setLong(1, produtoId);
                stmt.setString(2, referencia.getTipo().name());
                if (referencia.getMarcaId() == null) {
                    stmt.setObject(3, null);
                } else {
                    stmt.setLong(3, referencia.getMarcaId());
                }
                stmt.setString(4, referencia.getNome());
                stmt.setString(5, referencia.getCodigo());
                stmt.setString(6, referencia.getFonteUrl());
                stmt.setString(7, referencia.getObservacao());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void inserirAplicacoes(Connection connection, Long produtoId, List<ProdutoAplicacao> aplicacoes) throws SQLException {
        if (aplicacoes == null) {
            return;
        }
        String sql = """
                INSERT INTO produto_aplicacao
                (produto_id, montadora_id, veiculo_id, motor_id, ano_inicial, ano_final, observacao)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (ProdutoAplicacao aplicacao : aplicacoes) {
                stmt.setLong(1, produtoId);
                stmt.setLong(2, aplicacao.getMontadoraId());
                stmt.setLong(3, aplicacao.getVeiculoId());
                if (aplicacao.getMotorId() == null) {
                    stmt.setObject(4, null);
                } else {
                    stmt.setLong(4, aplicacao.getMotorId());
                }
                if (aplicacao.getAnoInicial() == null) {
                    stmt.setObject(5, null);
                } else {
                    stmt.setInt(5, aplicacao.getAnoInicial());
                }
                if (aplicacao.getAnoFinal() == null) {
                    stmt.setObject(6, null);
                } else {
                    stmt.setInt(6, aplicacao.getAnoFinal());
                }
                stmt.setString(7, aplicacao.getObservacao());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void inserirRelacionados(Connection connection, Long produtoId, List<ProdutoRelacionado> relacionados) throws SQLException {
        if (relacionados == null) {
            return;
        }
        String sql = "INSERT INTO produto_relacionado (produto_id, relacionado_id, tipo_relacao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (ProdutoRelacionado relacionado : relacionados) {
                stmt.setLong(1, produtoId);
                stmt.setLong(2, relacionado.getRelacionadoId());
                stmt.setString(3, relacionado.getTipoRelacao());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}
