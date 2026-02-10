package infra.dao;

import domain.model.Marca;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDao {
    private final DatabaseManager dbManager;

    public MarcaDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Marca> listarTodas() {
        String sql = "SELECT * FROM marca ORDER BY nome";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Marca> marcas = new ArrayList<>();
            while (rs.next()) {
                marcas.add(new Marca(rs.getLong("id"), rs.getString("nome")));
            }
            return marcas;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar marcas", e);
        }
    }
}
