package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormaPagamento {

	private Associado associado;
	private String formaPagamento;
	private Boolean atual;

	public FormaPagamento() {};
	
	public FormaPagamento(
			@JsonProperty("associado")Associado associado,
			@JsonProperty("formaPagamento")String formaPagamento,
			@JsonProperty("atual")Boolean atual) {

	    this.associado = associado;
	    this.formaPagamento = formaPagamento;
	    this.atual = atual;

	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Boolean getAtual() {
		return atual;
	}

	public void setAtual(Boolean atual) {
		this.atual = atual;
	}


}
