package infra.dao;

import domain.model.Produto;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {
    private final DatabaseManager dbManager;

    public ProdutoDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Produto> pesquisar(String codigo, String descricao, Long marcaId, Long grupoId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM produto WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (codigo != null && !codigo.isBlank()) {
            sql.append(" AND codigo LIKE ?");
            params.add("%" + codigo + "%");
        }
        if (descricao != null && !descricao.isBlank()) {
            sql.append(" AND descricao LIKE ?");
            params.add("%" + descricao + "%");
        }
        if (marcaId != null) {
            sql.append(" AND marca_id = ?");
            params.add(marcaId);
        }
        if (grupoId != null) {
            sql.append(" AND grupo_id = ?");
            params.add(grupoId);
        }
        sql.append(" ORDER BY descricao");

        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(map(rs));
            }
            return produtos;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao pesquisar produtos", e);
        }
    }

    public Produto buscarPorId(Long id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return map(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao buscar produto", e);
        }
    }

    public List<Produto> listarRelacionados(Long produtoId) {
        String sql = """
                SELECT p.*
                FROM produto_relacionado pr
                JOIN produto p ON p.id = pr.relacionado_id
                WHERE pr.produto_id = ?
                ORDER BY p.descricao
                """;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(map(rs));
            }
            return produtos;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar relacionados", e);
        }
    }

    public List<Produto> listarSugestoes(Long produtoId) {
        String sql = "SELECT * FROM produto WHERE id <> ? ORDER BY RANDOM() LIMIT 5";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(map(rs));
            }
            return produtos;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar sugestões", e);
        }
    }

    private Produto map(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setMarcaId(rs.getLong("marca_id"));
        produto.setCodigo(rs.getString("codigo"));
        produto.setDescricao(rs.getString("descricao"));
        long grupoId = rs.getLong("grupo_id");
        produto.setGrupoId(rs.wasNull() ? null : grupoId);
        produto.setObservacao(rs.getString("observacao"));
        produto.setAplicacaoResumida(rs.getString("aplicacao_resumida"));
        String data = rs.getString("data_lancamento");
        produto.setDataLancamento(data == null ? null : LocalDate.parse(data));
        return produto;
    }
}
