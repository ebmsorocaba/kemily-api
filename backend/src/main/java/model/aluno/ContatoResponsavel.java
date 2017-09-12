package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContatoResponsavel extends Contato{
	
	private String grauParentesco;
	private boolean presente;
	
	public ContatoResponsavel() {};
	
	public ContatoResponsavel(
			@JsonProperty("grauParentesco")String grauParentesco,
			@JsonProperty("presente")boolean presente) {
		
		this.grauParentesco = grauParentesco;
		this.presente = presente;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}
	
}
