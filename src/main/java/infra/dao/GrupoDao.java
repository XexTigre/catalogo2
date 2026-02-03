package infra.dao;

import domain.model.Grupo;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao {
    private final DatabaseManager dbManager;

    public GrupoDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<Grupo> listarTodos() {
        String sql = "SELECT * FROM grupo ORDER BY ordem, nome";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Grupo> grupos = new ArrayList<>();
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getLong("id"));
                grupo.setNome(rs.getString("nome"));
                grupo.setOrdem(rs.getInt("ordem"));
                grupo.setVisivelMenu(rs.getInt("visivel_menu") == 1);
                grupos.add(grupo);
            }
            return grupos;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar grupos", e);
        }
    }
}
