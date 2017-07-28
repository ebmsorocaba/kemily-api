package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Automovel {
	
	private int id;
	private String modelo;
	private String ano;
	private boolean financiado;
	private Estrutura_Familiar estrutura_familiar;
	
	public Automovel() {};
	
	public Automovel(
			@JsonProperty("id")int id,
			@JsonProperty("modelo")String modelo,
			@JsonProperty("ano")String ano,
			@JsonProperty("financiado")boolean financiado,
			@JsonProperty("estrutura_familiar")Estrutura_Familiar estrutura_familiar) {

		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.financiado = financiado;
		this.estrutura_familiar = estrutura_familiar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public boolean isFinanciado() {
		return financiado;
	}

	public void setFinanciado(boolean financiado) {
		this.financiado = financiado;
	}

	public Estrutura_Familiar getEstrutura_familiar() {
		return estrutura_familiar;
	}

	public void setEstrutura_familiar(Estrutura_Familiar estrutura_familiar) {
		this.estrutura_familiar = estrutura_familiar;
	}
	
}
