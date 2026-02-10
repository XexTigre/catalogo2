package infra.dao;

import domain.model.ProdutoAplicacao;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoAplicacaoDao {
    private final DatabaseManager dbManager;

    public ProdutoAplicacaoDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<ProdutoAplicacao> listarPorProduto(Long produtoId) {
        String sql = "SELECT * FROM produto_aplicacao WHERE produto_id = ?";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            List<ProdutoAplicacao> aplicacoes = new ArrayList<>();
            while (rs.next()) {
                ProdutoAplicacao aplicacao = new ProdutoAplicacao();
                aplicacao.setId(rs.getLong("id"));
                aplicacao.setProdutoId(rs.getLong("produto_id"));
                aplicacao.setMontadoraId(rs.getLong("montadora_id"));
                aplicacao.setVeiculoId(rs.getLong("veiculo_id"));
                long motorId = rs.getLong("motor_id");
                aplicacao.setMotorId(rs.wasNull() ? null : motorId);
                int anoInicial = rs.getInt("ano_inicial");
                aplicacao.setAnoInicial(rs.wasNull() ? null : anoInicial);
                int anoFinal = rs.getInt("ano_final");
                aplicacao.setAnoFinal(rs.wasNull() ? null : anoFinal);
                aplicacao.setObservacao(rs.getString("observacao"));
                aplicacoes.add(aplicacao);
            }
            return aplicacoes;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar aplicações", e);
        }
    }
}
