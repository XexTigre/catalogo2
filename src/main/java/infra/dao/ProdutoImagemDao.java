package infra.dao;

import domain.enums.ProdutoImagemTipo;
import domain.model.ProdutoImagem;
import infra.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoImagemDao {
    private final DatabaseManager dbManager;

    public ProdutoImagemDao(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public List<ProdutoImagem> listarPorProduto(Long produtoId) {
        String sql = "SELECT * FROM produto_imagem WHERE produto_id = ? ORDER BY ordem";
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            List<ProdutoImagem> imagens = new ArrayList<>();
            while (rs.next()) {
                ProdutoImagem imagem = new ProdutoImagem();
                imagem.setId(rs.getLong("id"));
                imagem.setProdutoId(rs.getLong("produto_id"));
                imagem.setTipo(ProdutoImagemTipo.valueOf(rs.getString("tipo")));
                imagem.setTitulo(rs.getString("titulo"));
                imagem.setCaminho(rs.getString("caminho"));
                imagem.setOrdem(rs.getInt("ordem"));
                imagens.add(imagem);
            }
            return imagens;
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao listar imagens", e);
        }
    }
}
