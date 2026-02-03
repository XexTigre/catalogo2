package domain.model;

public class Veiculo {
    private Long id;
    private Long montadoraId;
    private String modelo;
    private String versao;

    public Veiculo() {
    }

    public Veiculo(Long id, Long montadoraId, String modelo, String versao) {
        this.id = id;
        this.montadoraId = montadoraId;
        this.modelo = modelo;
        this.versao = versao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMontadoraId() {
        return montadoraId;
    }

    public void setMontadoraId(Long montadoraId) {
        this.montadoraId = montadoraId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
