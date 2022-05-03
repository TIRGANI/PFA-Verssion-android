package emsi.pfa.smart_wattering_v0.ui.beans;

public class Role {
    private int id;
    private String nome;
    private static int cpt=0;
    public Role(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Role( String nome) {
        this.id = ++cpt;
        this.nome = nome;
    }
    public Role( ) { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
