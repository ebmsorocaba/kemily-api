package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.Aluno;

public class Saude {

	private Aluno aluno;
	private boolean faz_tratamentos_medicos;
	private boolean problemas_saude_familia;
	private boolean plano_saude;
	private boolean pessoas_idosas;
	private boolean problemas_psiquiatricos;
	
	public Saude() {};
	
	public Saude(
			@JsonProperty("aluno")Aluno aluno,
			@JsonProperty("faz_tratamentos_medicos")boolean faz_tratamentos_medicos,
			@JsonProperty("problemas_saude_familia")boolean problemas_saude_familia,
			@JsonProperty("plano_saude")boolean plano_saude,
			@JsonProperty("pessoas_idosas")boolean pessoas_idosas,
			@JsonProperty("problemas_psiquiatricos")boolean problemas_psiquiatricos) {
		
		this.aluno = aluno;
		this.faz_tratamentos_medicos = faz_tratamentos_medicos;
		this.problemas_saude_familia = problemas_saude_familia;
		this.plano_saude = plano_saude;
		this.pessoas_idosas = pessoas_idosas;
		this.problemas_psiquiatricos = problemas_psiquiatricos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public boolean isFaz_tratamentos_medicos() {
		return faz_tratamentos_medicos;
	}

	public void setFaz_tratamentos_medicos(boolean faz_tratamentos_medicos) {
		this.faz_tratamentos_medicos = faz_tratamentos_medicos;
	}

	public boolean isProblemas_saude_familia() {
		return problemas_saude_familia;
	}

	public void setProblemas_saude_familia(boolean problemas_saude_familia) {
		this.problemas_saude_familia = problemas_saude_familia;
	}

	public boolean isPlano_saude() {
		return plano_saude;
	}

	public void setPlano_saude(boolean plano_saude) {
		this.plano_saude = plano_saude;
	}

	public boolean isPessoas_idosas() {
		return pessoas_idosas;
	}

	public void setPessoas_idosas(boolean pessoas_idosas) {
		this.pessoas_idosas = pessoas_idosas;
	}

	public boolean isProblemas_psiquiatricos() {
		return problemas_psiquiatricos;
	}

	public void setProblemas_psiquiatricos(boolean problemas_psiquiatricos) {
		this.problemas_psiquiatricos = problemas_psiquiatricos;
	}
	
	
}
