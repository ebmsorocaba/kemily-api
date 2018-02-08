package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contato extends Pessoa{

	private int id;
	private boolean profissional;
	private String cargo;
	private Aluno aluno;

	public Contato () {}
	
	public Contato(
			@JsonProperty("id")int id,
			@JsonProperty("nome")String nome,
			@JsonProperty("telefone")String telefone,
			@JsonProperty("email")String email,
			@JsonProperty("redeSocial")String redeSocial,
			@JsonProperty("cargo")String cargo,
			@JsonProperty("profissional")Boolean profissional,
			@JsonProperty("aluno")Aluno aluno) {

		super(nome, telefone, email, redeSocial);
		this.id = id;
		this.profissional = profissional;
		this.cargo = cargo;
		this.aluno = aluno;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public boolean isProfissional() {
		return profissional;
	}

	public void setProfissional(boolean profissional) {
		this.profissional = profissional;
	}
}
