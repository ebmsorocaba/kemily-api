package model.aluno;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MembroFamiliar {

	private int id;
	private String nome;
	private String parentesco;
	private String escolaridade;
	private Date dataNascimento;
	private String ocupacao;
	private double salario;
	private String localTrabalho;
	private String condicaoTrabalho;
	private Aluno aluno;
	
	public MembroFamiliar() {};
	
	public MembroFamiliar(
			@JsonProperty("id")int id,
			@JsonProperty("nome")String nome,
			@JsonProperty("parentesco")String parentesco,
			@JsonProperty("escolaridade")String escolaridade,
			@JsonProperty("dataNascimento")Date dataNascimento,
			@JsonProperty("ocupacao")String ocupacao,
			@JsonProperty("salario")double salario,
			@JsonProperty("localTrabalho")String localTrabalho,
			@JsonProperty("condicaoTrabalho")String condicaoTrabalho,
			@JsonProperty("aluno")Aluno aluno) {
		
		this.id = id;
		this.nome = nome;
		this.parentesco = parentesco;
		this.escolaridade = escolaridade;
		this.dataNascimento = dataNascimento;
		this.ocupacao = ocupacao;
		this.salario = salario;
		this.localTrabalho = localTrabalho;
		this.condicaoTrabalho = condicaoTrabalho;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getCondicaoTrabalho() {
		return condicaoTrabalho;
	}

	public void setCondicaoTrabalho(String condicaoTrabalho) {
		this.condicaoTrabalho = condicaoTrabalho;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
