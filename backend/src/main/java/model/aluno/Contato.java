package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contato {

	private int id;
	private String nome;
	private String telefone;
	private String tipo;
	private Aluno aluno;
	
	public Contato() {};
	
	public Contato(
			@JsonProperty("id")int id,
			@JsonProperty("nome")String nome,
			@JsonProperty("telefone")String telefone,
			@JsonProperty("tipo")String tipo,
			@JsonProperty("aluno")Aluno aluno) {
		
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.tipo = tipo;
		this.aluno = aluno;
	}

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
