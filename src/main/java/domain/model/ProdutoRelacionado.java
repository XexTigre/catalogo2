package domain.model;

public class ProdutoRelacionado {
    private Long id;
    private Long produtoId;
    private Long relacionadoId;
    private String tipoRelacao;

    public ProdutoRelacionado() {
    }

    public ProdutoRelacionado(Long id, Long produtoId, Long relacionadoId, String tipoRelacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.relacionadoId = relacionadoId;
        this.tipoRelacao = tipoRelacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getRelacionadoId() {
        return relacionadoId;
    }

    public void setRelacionadoId(Long relacionadoId) {
        this.relacionadoId = relacionadoId;
    }

    public String getTipoRelacao() {
        return tipoRelacao;
    }

    public void setTipoRelacao(String tipoRelacao) {
        this.tipoRelacao = tipoRelacao;
    }
}
