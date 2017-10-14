package model.historicoOcorrencia;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricoOcorrencia {
	private Date data;
	private Time hora;
	private int raAluno;
	private String descricao;
	
	public HistoricoOcorrencia() {}
	
	public HistoricoOcorrencia(
			@JsonProperty("data")Date data,
			@JsonProperty("hora")Time hora,
			@JsonProperty("raAluno")int raAluno,
			@JsonProperty("descricao")String descricao) {
		
		this.data = data;
		this.hora = hora;
		this.raAluno = raAluno;
		this.descricao = descricao;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
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
