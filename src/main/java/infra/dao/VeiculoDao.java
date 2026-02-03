package infra.dao;

import domain.model.Veiculo;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao {
    private final DatabaseManager dbManager;

    public VeiculoDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Veiculo> listarPorMontadora(Long montadoraId) {
        String sql = "SELECT * FROM veiculo WHERE montadora_id = ? ORDER BY modelo";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, montadoraId);
            ResultSet rs = stmt.executeQuery();
            List<Veiculo> veiculos = new ArrayList<>();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getLong("id"));
                veiculo.setMontadoraId(rs.getLong("montadora_id"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setVersao(rs.getString("versao"));
                veiculos.add(veiculo);
            }
            return veiculos;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar veículos", e);
        }
    }
}
