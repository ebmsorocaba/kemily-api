package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roupa {
    
    private Aluno aluno;
    private String tamanhoCamiseta;
    private String tamanhoCalca;
    private String tamanhoSapato;

    public Roupa() {};

    public Roupa(
            @JsonProperty("aluno")Aluno aluno,
            @JsonProperty("tamanhoCamiseta")String tamanhoCamiseta,
            @JsonProperty("tamanhoCalca")String tamanhoCalca, 
            @JsonProperty("tamanhoSapato")String tamanhoSapato) {

        this.aluno = aluno;
        this.tamanhoCamiseta = tamanhoCamiseta;
        this.tamanhoCalca = tamanhoCalca;
        this.tamanhoSapato = tamanhoSapato;
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

	public String getTamanhoSapato() {
		return tamanhoSapato;
	}
	public void setTamanhoSapato(String tamanhoSapato) {
		this.tamanhoSapato = tamanhoSapato;
	}
    
}