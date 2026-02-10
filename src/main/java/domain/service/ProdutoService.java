package domain.service;

import domain.model.Produto;
import domain.model.ProdutoAplicacao;
import domain.model.ProdutoImagem;
import domain.model.ProdutoReferencia;
import infra.dao.ProdutoAplicacaoDao;
import infra.dao.ProdutoDao;
import infra.dao.ProdutoImagemDao;
import infra.dao.ProdutoReferenciaDao;
import infra.db.DatabaseManager;

import java.util.List;

public class ProdutoService {
    private final ProdutoDao produtoDao;
    private final ProdutoImagemDao imagemDao;
    private final ProdutoReferenciaDao referenciaDao;
    private final ProdutoAplicacaoDao aplicacaoDao;

    public ProdutoService(DatabaseManager dbManager) {
        this.produtoDao = new ProdutoDao(dbManager);
        this.imagemDao = new ProdutoImagemDao(dbManager);
        this.referenciaDao = new ProdutoReferenciaDao(dbManager);
        this.aplicacaoDao = new ProdutoAplicacaoDao(dbManager);
    }

    public Produto buscarProduto(Long produtoId) {
        return produtoDao.buscarPorId(produtoId);
    }

    public List<ProdutoImagem> listarImagens(Long produtoId) {
        return imagemDao.listarPorProduto(produtoId);
    }

    public List<ProdutoReferencia> listarReferencias(Long produtoId) {
        return referenciaDao.listarPorProduto(produtoId);
    }

    public List<ProdutoAplicacao> listarAplicacoes(Long produtoId) {
        return aplicacaoDao.listarPorProduto(produtoId);
    }

    public List<Produto> listarRelacionadosDiretos(Long produtoId) {
        return produtoDao.listarRelacionados(produtoId);
    }

    public List<Produto> listarSugestoes(Long produtoId) {
        return produtoDao.listarSugestoes(produtoId);
    }
}
