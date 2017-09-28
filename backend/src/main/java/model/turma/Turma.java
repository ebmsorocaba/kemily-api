package model.turma;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Turma {
	private int id;
	private String cpfEducador;
	private String descricao;

	public Turma() {}
	
	public Turma(
			@JsonProperty("id")int id,
			@JsonProperty("cpfEducador")String cpfEducador,
			@JsonProperty("descricao")String descricao) {
		
		this.id = id;
		this.cpfEducador = cpfEducador;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpfEducador() {
		return cpfEducador;
	}

	public void setCpfEducador(String cpfEducador) {
		this.cpfEducador = cpfEducador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
