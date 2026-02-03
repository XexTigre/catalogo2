package domain.model;

import domain.enum.ProdutoImagemTipo;

public class ProdutoImagem {
    private Long id;
    private Long produtoId;
    private ProdutoImagemTipo tipo;
    private String titulo;
    private String caminho;
    private int ordem;

    public ProdutoImagem() {
    }

    public ProdutoImagem(Long id, Long produtoId, ProdutoImagemTipo tipo, String titulo, String caminho, int ordem) {
        this.id = id;
        this.produtoId = produtoId;
        this.tipo = tipo;
        this.titulo = titulo;
        this.caminho = caminho;
        this.ordem = ordem;
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

    public ProdutoImagemTipo getTipo() {
        return tipo;
    }

    public void setTipo(ProdutoImagemTipo tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
