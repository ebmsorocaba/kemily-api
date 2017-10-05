package model.historicoOcorrencia;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricoOcorrencia {
	private Timestamp dataOcorrencia;
	private int raAluno;
	private String nomeAluno;
	private String descricaoOcorrencia;
	
	public HistoricoOcorrencia() {}
	
	public HistoricoOcorrencia(
			@JsonProperty("dataOcorrencia")Timestamp dataOcorrencia,
			@JsonProperty("raAluno")int raAluno,
			@JsonProperty("nomeAluno")String nomeAluno,
			@JsonProperty("descricaoOcorrencia")String descricaoOcorrencia) {
		
		this.dataOcorrencia = dataOcorrencia;
		this.raAluno = raAluno;
		this.nomeAluno = nomeAluno;
		this.descricaoOcorrencia = descricaoOcorrencia;
	}
	
	public Timestamp getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(Timestamp dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	
	public int getRaAluno() {
		return raAluno;
	}
	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	
	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}
	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}

}
