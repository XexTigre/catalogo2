package infra.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_FOLDER = "data";
    private static final String DB_FILE = "catalogo.db";
    private static final String SCHEMA_RESOURCE = "/infra/db/schema.sql";
    private static DatabaseManager instance;

    private final String jdbcUrl;

    private DatabaseManager() {
        Path dbPath = Paths.get(DB_FOLDER, DB_FILE);
        try {
            Files.createDirectories(dbPath.getParent());
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível criar a pasta de dados", e);
        }
        jdbcUrl = "jdbc:sqlite:" + dbPath.toAbsolutePath();
        applySchemaIfNeeded();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }

    private void applySchemaIfNeeded() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String schema = loadSchema();
            for (String command : schema.split(";")) {
                String trimmed = command.trim();
                if (!trimmed.isEmpty()) {
                    statement.execute(trimmed + ";");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Falha ao aplicar schema do banco", e);
        }
    }

    private String loadSchema() {
        try (InputStream input = DatabaseManager.class.getResourceAsStream(SCHEMA_RESOURCE)) {
            if (input == null) {
                throw new IllegalStateException("Schema não encontrado: " + SCHEMA_RESOURCE);
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível ler schema", e);
        }
    }
}
