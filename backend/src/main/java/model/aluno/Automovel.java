package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Automovel {
	
	private int id;
	private String modelo;
	private String ano;
	private boolean financiado;
	private EstruturaFamiliar estruturaFamiliar;
	
	public Automovel() {};
	
	public Automovel(
			@JsonProperty("id")int id,
			@JsonProperty("modelo")String modelo,
			@JsonProperty("ano")String ano,
			@JsonProperty("financiado")boolean financiado,
			@JsonProperty("estruturaFamiliar")EstruturaFamiliar estruturaFamiliar) {

		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.financiado = financiado;
		this.estruturaFamiliar = estruturaFamiliar;
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

	public EstruturaFamiliar getEstruturaFamiliar() {
		return estruturaFamiliar;
	}

	public void setEstruturaFamiliar(EstruturaFamiliar estruturaFamiliar) {
		this.estruturaFamiliar = estruturaFamiliar;
	}
	
}
