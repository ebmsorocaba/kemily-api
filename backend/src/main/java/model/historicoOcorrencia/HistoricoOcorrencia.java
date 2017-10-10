package model.historicoOcorrencia;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricoOcorrencia {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy kk:mm:ss")
	private Timestamp data;
	private int raAluno;
	private String descricao;
	
	public HistoricoOcorrencia() {}
	
	public HistoricoOcorrencia(
			@JsonProperty("data")Timestamp data,
			@JsonProperty("raAluno")int raAluno,
			@JsonProperty("descricao")String descricao) {
		
		this.data = data;
		this.raAluno = raAluno;
		this.descricao = descricao;
	}
	
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	
	public int getRaAluno() {
		return raAluno;
	}
	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
