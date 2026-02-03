package infra.dao;

import domain.model.ProdutoRelacionado;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRelacionadoDao {
    private final DatabaseManager dbManager;

    public ProdutoRelacionadoDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<ProdutoRelacionado> listarPorProduto(Long produtoId) {
        String sql = "SELECT * FROM produto_relacionado WHERE produto_id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            List<ProdutoRelacionado> relacionados = new ArrayList<>();
            while (rs.next()) {
                ProdutoRelacionado relacionado = new ProdutoRelacionado();
                relacionado.setId(rs.getLong("id"));
                relacionado.setProdutoId(rs.getLong("produto_id"));
                relacionado.setRelacionadoId(rs.getLong("relacionado_id"));
                relacionado.setTipoRelacao(rs.getString("tipo_relacao"));
                relacionados.add(relacionado);
            }
            return relacionados;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar relacionados", e);
        }
    }
}
