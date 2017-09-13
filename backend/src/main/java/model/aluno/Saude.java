package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saude {

	private Aluno aluno;
	private boolean fazTratamentosMedicos;
	private String tipoTratamentoMedico;
	private boolean problemasSaudeFamilia;
	private boolean planoSaude;
	private boolean pessoasIdosas;
	private boolean problemasPsiquiatricos;
	private boolean possuiAlergia;
	private String tipoAlergia;
	private boolean tomaMedicacao;
	private String tipoMedicacao;
	
	public Saude() {};
	
	public Saude(
			@JsonProperty("aluno")Aluno aluno,
			@JsonProperty("fazTratamentosMedicos")boolean fazTratamentosMedicos,
			@JsonProperty("tipoTratamentoMedico")String tipoTratamentoMedico,
			@JsonProperty("problemasSaudeFamilia")boolean problemasSaudeFamilia,
			@JsonProperty("planoSaude")boolean planoSaude,
			@JsonProperty("pessoasIdosas")boolean pessoasIdosas,
			@JsonProperty("problemasPsiquiatricos")boolean problemasPsiquiatricos,
			@JsonProperty("possuiAlergia")boolean possuiAlergia,
			@JsonProperty("tipoAlergia")String tipoAlergia,
			@JsonProperty("tomaMedicacao")boolean tomaMedicacao,
			@JsonProperty("tipoMedicacao")String tipoMedicacao) {
		
		this.aluno = aluno;
		this.fazTratamentosMedicos = fazTratamentosMedicos;
		this.tipoTratamentoMedico = tipoTratamentoMedico;
		this.problemasSaudeFamilia = problemasSaudeFamilia;
		this.planoSaude = planoSaude;
		this.pessoasIdosas = pessoasIdosas;
		this.problemasPsiquiatricos = problemasPsiquiatricos;
		this.possuiAlergia = possuiAlergia;
		this.tipoAlergia = tipoAlergia;
		this.tomaMedicacao = tomaMedicacao;
		this.tipoMedicacao = tipoMedicacao;
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

	public String getTipoTratamentoMedico() {
		return tipoTratamentoMedico;
	}

	public void setTipoTratamentoMedico(String tipoTratamentoMedico) {
		this.tipoTratamentoMedico = tipoTratamentoMedico;
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

	public boolean isPossuiAlergia() {
		return possuiAlergia;
	}

	public void setPossuiAlergia(boolean possuiAlergia) {
		this.possuiAlergia = possuiAlergia;
	}

	public String getTipoAlergia() {
		return tipoAlergia;
	}

	public void setTipoAlergia(String tipoAlergia) {
		this.tipoAlergia = tipoAlergia;
	}

	public boolean isTomaMedicacao() {
		return tomaMedicacao;
	}

	public void setTomaMedicacao(boolean tomaMedicacao) {
		this.tomaMedicacao = tomaMedicacao;
	}

	public String getTipoMedicacao() {
		return tipoMedicacao;
	}

	public void setTipoMedicacao(String tipoMedicacao) {
		this.tipoMedicacao = tipoMedicacao;
	}
	
	
}
