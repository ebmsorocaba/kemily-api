package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Turma {
	
	private String eucador;
	
	public Turma() {};
	
	public Turma(
			@JsonProperty("educador")String educador) {
		
		this.eucador = educador;
	}

	public String getEucador() {
		return eucador;
	}

	public void setEucador(String eucador) {
		this.eucador = eucador;
	}
	
}
