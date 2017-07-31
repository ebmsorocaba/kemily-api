package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Turma {
	
	private String educador;
	
	public Turma() {};
	
	public Turma(
			@JsonProperty("educador")String educador) {
		
		this.educador = educador;
	}

	public String getEducador() {
		return educador;
	}

	public void setEducador(String educador) {
		this.educador = educador;
	}
	
}
