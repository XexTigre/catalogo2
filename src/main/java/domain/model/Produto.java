package domain.model;

import java.time.LocalDate;

public class Produto {
    private Long id;
    private Long marcaId;
    private String codigo;
    private String descricao;
    private Long grupoId;
    private String observacao;
    private String aplicacaoResumida;
    private LocalDate dataLancamento;

    public Produto() {
    }

    public Produto(Long id, Long marcaId, String codigo, String descricao, Long grupoId, String observacao,
                   String aplicacaoResumida, LocalDate dataLancamento) {
        this.id = id;
        this.marcaId = marcaId;
        this.codigo = codigo;
        this.descricao = descricao;
        this.grupoId = grupoId;
        this.observacao = observacao;
        this.aplicacaoResumida = aplicacaoResumida;
        this.dataLancamento = dataLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAplicacaoResumida() {
        return aplicacaoResumida;
    }

    public void setAplicacaoResumida(String aplicacaoResumida) {
        this.aplicacaoResumida = aplicacaoResumida;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
