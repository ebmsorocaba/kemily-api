package model.turma;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Turma {
	private int id;
	private String cpfEducador;
	private String periodo;
	private String nome;

	public Turma() {}
	
	public Turma(
			@JsonProperty("id")int id,
			@JsonProperty("cpfEducador")String cpfEducador,
			@JsonProperty("periodo")String periodo,
			@JsonProperty("nome")String nome) {
		
		this.id = id;
		this.cpfEducador = cpfEducador;
		this.periodo = periodo;
		this.nome = nome;
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
