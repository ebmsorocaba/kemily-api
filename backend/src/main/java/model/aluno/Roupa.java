package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Aluno;

public class Roupa {
    
    private Aluno aluno;
    private String tamanho_camiseta;
    private String tamanho_calca;

    public Roupa() {};

    public Roupa(
            @JsonProperty("aluno")Aluno aluno,
            @JsonProperty("tamanho_camiseta")String tamanho_camiseta,
            @JsonProperty("tamanho_calca")String tamanho_calca) {

        this.aluno = aluno;
        this.tamanho_camiseta = tamanho_camiseta;
        this.tamanho_calca = tamanho_calca;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getTamanho_camiseta() {
        return tamanho_camiseta;
    }
    public void setTamanho_camiseta(String tamanho_camiseta) {
        this.tamanho_camiseta = tamanho_camiseta;
    }

    public String getTamanho_calca() {
        return tamanho_calca;
    }
    public void setTamanho_calca(String tamanho_calca) {
        this.tamanho_calca = tamanho_calca;
    }
}