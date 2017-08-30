package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saude {

	private Aluno aluno;
	private boolean fazTratamentosMedicos;
	private boolean problemasSaudeFamilia;
	private boolean planoSaude;
	private boolean pessoasIdosas;
	private boolean problemasPsiquiatricos;
	
	public Saude() {};
	
	public Saude(
			@JsonProperty("aluno")Aluno aluno,
			@JsonProperty("fazTratamentosMedicos")boolean fazTratamentosMedicos,
			@JsonProperty("problemasSaudeFamilia")boolean problemasSaudeFamilia,
			@JsonProperty("planoSaude")boolean planoSaude,
			@JsonProperty("pessoasIdosas")boolean pessoasIdosas,
			@JsonProperty("problemasPsiquiatricos")boolean problemasPsiquiatricos) {
		
		this.aluno = aluno;
		this.fazTratamentosMedicos = fazTratamentosMedicos;
		this.problemasSaudeFamilia = problemasSaudeFamilia;
		this.planoSaude = planoSaude;
		this.pessoasIdosas = pessoasIdosas;
		this.problemasPsiquiatricos = problemasPsiquiatricos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public boolean isFazTratamentosMedicos() {
		return fazTratamentosMedicos;
	}

	public void setFazTratamentosMedicos(boolean fazTratamentosMedicos) {
		this.fazTratamentosMedicos = fazTratamentosMedicos;
	}

	public boolean isProblemasSaudeFamilia() {
		return problemasSaudeFamilia;
	}

	public void setProblemasSaudeFamilia(boolean problemasSaudeFamilia) {
		this.problemasSaudeFamilia = problemasSaudeFamilia;
	}

	public boolean isPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(boolean planoSaude) {
		this.planoSaude = planoSaude;
	}

	public boolean isPessoasIdosas() {
		return pessoasIdosas;
	}

	public void setPessoasIdosas(boolean pessoasIdosas) {
		this.pessoasIdosas = pessoasIdosas;
	}

	public boolean isProblemasPsiquiatricos() {
		return problemasPsiquiatricos;
	}

	public void setProblemasPsiquiatricos(boolean problemasPsiquiatricos) {
		this.problemasPsiquiatricos = problemasPsiquiatricos;
	}
	
	
}
