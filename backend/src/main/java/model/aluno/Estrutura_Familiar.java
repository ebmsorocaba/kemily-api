package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.Aluno;

public class Estrutura_Familiar {

	private int id;
	private String estado_civil_pais;
	private String crianca_reside_com;
	private boolean problemas_financeiros;
	private boolean uso_alcool_drogas;
	private boolean alguem_agressivo;
	private boolean programas_sociais;
	private Aluno aluno;
	
	public Estrutura_Familiar() {}
	
	public Estrutura_Familiar(
			@JsonProperty("id")int id,
			@JsonProperty("estado_civil_pais")String estado_civil_pais,
			@JsonProperty("crianca_reside_com")String crianca_reside_com,
			@JsonProperty("problemas_financeiros")boolean problemas_financeiros,
			@JsonProperty("uso_alcool_drogas")boolean uso_alcool_drogas,
			@JsonProperty("alguem_agressivo")boolean alguem_agressivo,
			@JsonProperty("programas_sociais")boolean programas_sociais,
			@JsonProperty("aluno")Aluno aluno) {
		
		this.id = id;
		this.estado_civil_pais = estado_civil_pais;
		this.crianca_reside_com = crianca_reside_com;
		this.problemas_financeiros = problemas_financeiros;
		this.uso_alcool_drogas = uso_alcool_drogas;
		this.alguem_agressivo = alguem_agressivo;
		this.programas_sociais = programas_sociais;
		this.aluno = aluno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado_civil_pais() {
		return estado_civil_pais;
	}

	public void setEstado_civil_pais(String estado_civil_pais) {
		this.estado_civil_pais = estado_civil_pais;
	}

	public String getCrianca_reside_com() {
		return crianca_reside_com;
	}

	public void setCrianca_reside_com(String crianca_reside_com) {
		this.crianca_reside_com = crianca_reside_com;
	}

	public boolean isProblemas_financeiros() {
		return problemas_financeiros;
	}

	public void setProblemas_financeiros(boolean problemas_financeiros) {
		this.problemas_financeiros = problemas_financeiros;
	}

	public boolean isUso_alcool_drogas() {
		return uso_alcool_drogas;
	}

	public void setUso_alcool_drogas(boolean uso_alcool_drogas) {
		this.uso_alcool_drogas = uso_alcool_drogas;
	}

	public boolean isAlguem_agressivo() {
		return alguem_agressivo;
	}

	public void setAlguem_agressivo(boolean alguem_agressivo) {
		this.alguem_agressivo = alguem_agressivo;
	}

	public boolean isProgramas_sociais() {
		return programas_sociais;
	}

	public void setProgramas_sociais(boolean programas_sociais) {
		this.programas_sociais = programas_sociais;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
