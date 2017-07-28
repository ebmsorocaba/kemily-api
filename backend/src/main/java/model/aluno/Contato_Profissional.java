package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contato_Profissional extends Contato { 
	
	private String cargo;
	
	public Contato_Profissional() {};
	
	public Contato_Profissional(
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
