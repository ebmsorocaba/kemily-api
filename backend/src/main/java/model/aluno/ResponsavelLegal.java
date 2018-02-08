package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsavelLegal extends Pessoa {
    private int id;
    private String rg;
    private String cpf;
    private String grauParentesco;
    private String estado;
    private Aluno aluno;

    public ResponsavelLegal() {}

    public ResponsavelLegal(
            @JsonProperty("id")int id,
            @JsonProperty("nome")String nome,
            @JsonProperty("telefone")String telefone,
            @JsonProperty("email")String email,
            @JsonProperty("redeSocial")String redeSocial,
            @JsonProperty("rg")String rg,
            @JsonProperty("cpf")String cpf,
            @JsonProperty("grauParentesco")String grauParentesco,
            @JsonProperty("estado")String estado,
            @JsonProperty("aluno")Aluno aluno) {

        super(nome, telefone, email, redeSocial);
        this.rg = rg;
        this.cpf = cpf;
        this.grauParentesco = grauParentesco;
        this.estado = estado;
        this.aluno = aluno;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
