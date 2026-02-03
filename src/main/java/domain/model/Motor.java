package domain.model;

public class Motor {
    private Long id;
    private Long montadoraId;
    private String nome;
    private String codigo;

    public Motor() {
    }

    public Motor(Long id, Long montadoraId, String nome, String codigo) {
        this.id = id;
        this.montadoraId = montadoraId;
        this.nome = nome;
        this.codigo = codigo;
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
}
