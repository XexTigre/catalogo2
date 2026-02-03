package infra.dao;

import domain.model.Montadora;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MontadoraDao {
    private final DatabaseManager dbManager;

    public MontadoraDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Montadora> listarTodas() {
        String sql = "SELECT * FROM montadora ORDER BY nome";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Montadora> montadoras = new ArrayList<>();
            while (rs.next()) {
                montadoras.add(new Montadora(rs.getLong("id"), rs.getString("nome")));
            }
            return montadoras;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar montadoras", e);
        }
    }
}
