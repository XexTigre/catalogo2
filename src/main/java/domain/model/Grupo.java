package domain.model;

public class Grupo {
    private Long id;
    private String nome;
    private int ordem;
    private boolean visivelMenu;

    public Grupo() {
    }

    public Grupo(Long id, String nome, int ordem, boolean visivelMenu) {
        this.id = id;
        this.nome = nome;
        this.ordem = ordem;
        this.visivelMenu = visivelMenu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public boolean isVisivelMenu() {
        return visivelMenu;
    }

    public void setVisivelMenu(boolean visivelMenu) {
        this.visivelMenu = visivelMenu;
    }
}
