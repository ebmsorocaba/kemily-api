package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contato_Responsavel extends Contato{
	
	private String grau_parentesco;
	private boolean presente;
	
	public Contato_Responsavel() {};
	
	public Contato_Responsavel(
			@JsonProperty("grau_parentesco")String grau_parentesco,
			@JsonProperty("presente")boolean presente) {
		
		this.grau_parentesco = grau_parentesco;
		this.presente = presente;
	}

	public String getGrau_parentesco() {
		return grau_parentesco;
	}

	public void setGrau_parentesco(String grau_parentesco) {
		this.grau_parentesco = grau_parentesco;
	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}
	
}
