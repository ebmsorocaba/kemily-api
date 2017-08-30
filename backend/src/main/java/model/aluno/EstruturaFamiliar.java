package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstruturaFamiliar {

	private int id;
	private String estadoCivilPais;
	private String criancaResideCom;
	private boolean problemasFinanceiros;
	private boolean usoAlcoolDrogas;
	private boolean alguemAgressivo;
	private boolean programasSociais;
	private Aluno aluno;
	
	public EstruturaFamiliar() {}
	
	public EstruturaFamiliar(
			@JsonProperty("id")int id,
			@JsonProperty("estadoCivilPais")String estadoCivilPais,
			@JsonProperty("criancaResideCom")String criancaResideCom,
			@JsonProperty("problemasFinanceiros")boolean problemasFinanceiros,
			@JsonProperty("usoAlcoolDrogas")boolean usoAlcoolDrogas,
			@JsonProperty("alguemAgressivo")boolean alguemAgressivo,
			@JsonProperty("programasSociais")boolean programasSociais,
			@JsonProperty("aluno")Aluno aluno) {
		
		this.id = id;
		this.estadoCivilPais = estadoCivilPais;
		this.criancaResideCom = criancaResideCom;
		this.problemasFinanceiros = problemasFinanceiros;
		this.usoAlcoolDrogas = usoAlcoolDrogas;
		this.alguemAgressivo = alguemAgressivo;
		this.programasSociais = programasSociais;
		this.aluno = aluno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstadoCivilPais() {
		return estadoCivilPais;
	}

	public void setEstadoCivilPais(String estadoCivilPais) {
		this.estadoCivilPais = estadoCivilPais;
	}

	public String getCriancaResideCom() {
		return criancaResideCom;
	}

	public void setCriancaResideCom(String criancaResideCom) {
		this.criancaResideCom = criancaResideCom;
	}

	public boolean isProblemasFinanceiros() {
		return problemasFinanceiros;
	}

	public void setProblemasFinanceiros(boolean problemasFinanceiros) {
		this.problemasFinanceiros = problemasFinanceiros;
	}

	public boolean isUsoAlcoolDrogas() {
		return usoAlcoolDrogas;
	}

	public void setUsoAlcoolDrogas(boolean usoAlcoolDrogas) {
		this.usoAlcoolDrogas = usoAlcoolDrogas;
	}

	public boolean isAlguemAgressivo() {
		return alguemAgressivo;
	}

	public void setAlguemAgressivo(boolean alguemAgressivo) {
		this.alguemAgressivo = alguemAgressivo;
	}

	public boolean isProgramasSociais() {
		return programasSociais;
	}

	public void setProgramasSociais(boolean programasSociais) {
		this.programasSociais = programasSociais;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
