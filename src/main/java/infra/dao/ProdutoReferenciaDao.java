package infra.dao;

import domain.enum.ProdutoReferenciaTipo;
import domain.model.ProdutoReferencia;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoReferenciaDao {
    private final DatabaseManager dbManager;

    public ProdutoReferenciaDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<ProdutoReferencia> listarPorProduto(Long produtoId) {
        String sql = "SELECT * FROM produto_referencia WHERE produto_id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            List<ProdutoReferencia> referencias = new ArrayList<>();
            while (rs.next()) {
                ProdutoReferencia ref = new ProdutoReferencia();
                ref.setId(rs.getLong("id"));
                ref.setProdutoId(rs.getLong("produto_id"));
                ref.setTipo(ProdutoReferenciaTipo.valueOf(rs.getString("tipo")));
                long marcaId = rs.getLong("marca_id");
                ref.setMarcaId(rs.wasNull() ? null : marcaId);
                ref.setNome(rs.getString("nome"));
                ref.setCodigo(rs.getString("codigo"));
                ref.setFonteUrl(rs.getString("fonte_url"));
                ref.setObservacao(rs.getString("observacao"));
                referencias.add(ref);
            }
            return referencias;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar referências", e);
        }
    }
}
