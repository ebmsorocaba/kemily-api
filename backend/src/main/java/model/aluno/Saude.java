package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saude {

	private Aluno aluno;
	private boolean fazTratamentosMedicos;
	private String descricaoTratamento;
	private boolean problemasSaudeFamilia;
	private boolean planoSaude;
	private boolean pessoasIdosas;
	private boolean problemasPsiquiatricos;
	private boolean possuiAlergia;
	private String descricaoAlergia;
	private boolean tomaMedicacao;
	private String descricaoMedicacao;
	
	public Saude() {};
	
	public Saude(
			@JsonProperty("aluno")Aluno aluno,
			@JsonProperty("fazTratamentosMedicos")boolean fazTratamentosMedicos,
			@JsonProperty("descricaoTratamento")String descricaoTratamento,
			@JsonProperty("problemasSaudeFamilia")boolean problemasSaudeFamilia,
			@JsonProperty("planoSaude")boolean planoSaude,
			@JsonProperty("pessoasIdosas")boolean pessoasIdosas,
			@JsonProperty("problemasPsiquiatricos")boolean problemasPsiquiatricos,
			@JsonProperty("possuiAlergia")boolean possuiAlergia,
			@JsonProperty("descricaoAlergia")String descricaoAlergia,
			@JsonProperty("tomaMedicacao")boolean tomaMedicacao,
			@JsonProperty("descricaoMedicacao")String descricaoMedicacao) {
		
		this.aluno = aluno;
		this.fazTratamentosMedicos = fazTratamentosMedicos;
		this.descricaoTratamento = descricaoTratamento;
		this.problemasSaudeFamilia = problemasSaudeFamilia;
		this.planoSaude = planoSaude;
		this.pessoasIdosas = pessoasIdosas;
		this.problemasPsiquiatricos = problemasPsiquiatricos;
		this.possuiAlergia = possuiAlergia;
		this.descricaoAlergia = descricaoAlergia;
		this.tomaMedicacao = tomaMedicacao;
		this.descricaoMedicacao = descricaoMedicacao;
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

	public String getDescricaoTratamento() {
		return descricaoTratamento;
	}

	public void setDescricaoTratamento(String descricaoTratamento) {
		this.descricaoTratamento = descricaoTratamento;
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

	public String getDescricaoAlergia() {
		return descricaoAlergia;
	}

	public void setDescricaoAlergia(String descricaoAlergia) {
		this.descricaoAlergia = descricaoAlergia;
	}

	public boolean isTomaMedicacao() {
		return tomaMedicacao;
	}

	public void setTomaMedicacao(boolean tomaMedicacao) {
		this.tomaMedicacao = tomaMedicacao;
	}

	public String getDescricaoMedicacao() {
		return descricaoMedicacao;
	}

	public void setDescricaoMedicacao(String descricaoMedicacao) {
		this.descricaoMedicacao = descricaoMedicacao;
	}
	
	
}
