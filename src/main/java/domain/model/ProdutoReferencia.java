package domain.model;

import domain.enum.ProdutoReferenciaTipo;

public class ProdutoReferencia {
    private Long id;
    private Long produtoId;
    private ProdutoReferenciaTipo tipo;
    private Long marcaId;
    private String nome;
    private String codigo;
    private String fonteUrl;
    private String observacao;

    public ProdutoReferencia() {
    }

    public ProdutoReferencia(Long id, Long produtoId, ProdutoReferenciaTipo tipo, Long marcaId, String nome,
                             String codigo, String fonteUrl, String observacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.marcaId = marcaId;
        this.nome = nome;
        this.codigo = codigo;
        this.fonteUrl = fonteUrl;
        this.observacao = observacao;
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

    public ProdutoReferenciaTipo getTipo() {
        return tipo;
    }

    public void setTipo(ProdutoReferenciaTipo tipo) {
        this.tipo = tipo;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFonteUrl() {
        return fonteUrl;
    }

    public void setFonteUrl(String fonteUrl) {
        this.fonteUrl = fonteUrl;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
