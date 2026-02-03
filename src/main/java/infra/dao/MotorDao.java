package infra.dao;

import domain.model.Motor;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotorDao {
    private final DatabaseManager dbManager;

    public MotorDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Motor> listarPorMontadora(Long montadoraId) {
        String sql = "SELECT * FROM motor WHERE montadora_id = ? ORDER BY nome";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, montadoraId);
            ResultSet rs = stmt.executeQuery();
            List<Motor> motores = new ArrayList<>();
            while (rs.next()) {
                Motor motor = new Motor();
                motor.setId(rs.getLong("id"));
                motor.setMontadoraId(rs.getLong("montadora_id"));
                motor.setNome(rs.getString("nome"));
                motor.setCodigo(rs.getString("codigo"));
                motores.add(motor);
            }
            return motores;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar motores", e);
        }
    }
}
