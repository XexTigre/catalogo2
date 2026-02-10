package domain.model;

public class ProdutoAplicacao {
    private Long id;
    private Long produtoId;
    private Long montadoraId;
    private Long veiculoId;
    private Long motorId;
    private Integer anoInicial;
    private Integer anoFinal;
    private String observacao;

    public ProdutoAplicacao() {
    }

    public ProdutoAplicacao(Long id, Long produtoId, Long montadoraId, Long veiculoId, Long motorId,
                            Integer anoInicial, Integer anoFinal, String observacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.montadoraId = montadoraId;
        this.veiculoId = veiculoId;
        this.motorId = motorId;
        this.anoInicial = anoInicial;
        this.anoFinal = anoFinal;
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

    public Long getMontadoraId() {
        return montadoraId;
    }

    public void setMontadoraId(Long montadoraId) {
        this.montadoraId = montadoraId;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getMotorId() {
        return motorId;
    }

    public void setMotorId(Long motorId) {
        this.motorId = motorId;
    }

    public Integer getAnoInicial() {
        return anoInicial;
    }

    public void setAnoInicial(Integer anoInicial) {
        this.anoInicial = anoInicial;
    }

    public Integer getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(Integer anoFinal) {
        this.anoFinal = anoFinal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
