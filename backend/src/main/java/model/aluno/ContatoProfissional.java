package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContatoProfissional extends Contato { 
	
	private String cargo;
	
	public ContatoProfissional() {};
	
	public ContatoProfissional(
			@JsonProperty("cargo")String cargo) {
		
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
