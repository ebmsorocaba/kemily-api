package model.turma;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlunoTurma {
	private int raAluno;
	private int idTurma;

	public AlunoTurma() {}
	
	public AlunoTurma(
			@JsonProperty("raAluno")int raAluno,
			@JsonProperty("idTurma")int idTurma) {
		
		this.raAluno = raAluno;
		this.idTurma = idTurma;
	}

	public int getRaAluno() {
		return raAluno;
	}

	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	
	
}
