package model.aluno;

public class Pessoa {
    private String nome;
    private String telefone;
    private String email;
    private String redeSocial;

    public Pessoa () {}

    public Pessoa(String nome, String telefone, String email, String redeSocial) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.redeSocial = redeSocial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }
}
