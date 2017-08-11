package model.aluno;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.Aluno;

public class Parente {

	private int id;
	private String nome;
	private String parentesco;
	private String escolaridade;
	private Date data_nascimento;
	private String ocupacao;
	private double salario;
	private String local_trabalho;
	private Aluno aluno;
	
	public Parente() {};
	
	public Parente(
			@JsonProperty("id")int id,
			@JsonProperty("nome")String nome,
			@JsonProperty("parentesco")String parentesco,
			@JsonProperty("escolaridade")String escolaridade,
			@JsonProperty("data_nascimento")Date data_nascimento,
			@JsonProperty("ocupacao")String ocupacao,
			@JsonProperty("salario")double salario,
			@JsonProperty("local_trabalho")String local_trabalho,
			@JsonProperty("aluno")Aluno aluno) {
		
		this.id = id;
		this.nome = nome;
		this.parentesco = parentesco;
		this.escolaridade = escolaridade;
		this.data_nascimento = data_nascimento;
		this.ocupacao = ocupacao;
		this.salario = salario;
		this.local_trabalho = local_trabalho;
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

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getLocal_trabalho() {
		return local_trabalho;
	}

	public void setLocal_trabalho(String local_trabalho) {
		this.local_trabalho = local_trabalho;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
