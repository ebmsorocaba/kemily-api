package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cartao {
	private Long numero;
	private String bandeira;
	private boolean atual;

	public Cartao(
    		@JsonProperty("numero")Long numero,
    		@JsonProperty("bandeira")String bandeira,
    		@JsonProperty("atual")boolean atual) {

        this.numero = numero;
        this.bandeira = bandeira;
        this.atual = atual;
  }

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}


}
