package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roupa {
    
    private Aluno aluno;
    private String tamanhoCamiseta;
    private String tamanhoCalca;

    public Roupa() {};

    public Roupa(
            @JsonProperty("aluno")Aluno aluno,
            @JsonProperty("tamanhoCamiseta")String tamanhoCamiseta,
            @JsonProperty("tamanhoCalca")String tamanhoCalca) {

        this.aluno = aluno;
        this.tamanhoCamiseta = tamanhoCamiseta;
        this.tamanhoCalca = tamanhoCalca;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getTamanhoCamiseta() {
        return tamanhoCamiseta;
    }
    public void setTamanhoCamiseta(String tamanhoCamiseta) {
        this.tamanhoCamiseta = tamanhoCamiseta;
    }

    public String getTamanhoCalca() {
        return tamanhoCalca;
    }
    public void setTamanhoCalca(String tamanhoCalca) {
        this.tamanhoCalca = tamanhoCalca;
    }
}