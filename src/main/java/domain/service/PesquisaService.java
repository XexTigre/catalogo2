package domain.service;

import domain.model.Produto;
import infra.dao.ProdutoDao;
import infra.db.DatabaseManager;

import java.util.List;

public class PesquisaService {
    private final ProdutoDao produtoDao;

    public PesquisaService(DatabaseManager dbManager) {
        this.produtoDao = new ProdutoDao(dbManager);
    }

    public List<Produto> pesquisar(String codigo, String descricao, Long marcaId, Long grupoId) {
        return produtoDao.pesquisar(codigo, descricao, marcaId, grupoId);
    }
}
