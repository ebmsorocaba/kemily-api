package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatPagAssociado {

	private String cpf;
	private String nome;
	private String mes;
	private Date dataPag;
	private Double valor;

	public RelatPagAssociado() {};
	
	public RelatPagAssociado(
			@JsonProperty("cpf")String cpf,
			@JsonProperty("nome")String nome,
			@JsonProperty("mes")String mes,
			@JsonProperty("dataPag")Date dataPag,
			@JsonProperty("valor")Double valor){

        this.cpf = cpf;
        this.nome = nome;
        this.mes = mes;
        this.dataPag = dataPag;
        this.valor = valor;

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Date getDataPag() {
		return dataPag;
	}

	public void setDataPag(Date dataPag) {
		this.dataPag = dataPag;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
