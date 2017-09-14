package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContatoResponsavel extends Contato{
	
	private String grauParentesco;
	private String estado;
	
	public ContatoResponsavel() {};
	
	public ContatoResponsavel(
			@JsonProperty("grauParentesco")String grauParentesco,
			@JsonProperty("estado")String estado) {
		
		this.grauParentesco = grauParentesco;
		this.estado = estado;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
