package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagamento {

	private Long id;
	private String formaPgto;
	private String codBoleto;
	private double valorPago;
	private Date vencimento;
	private Date dataPgto;

	private Associado associado;
	private Cartao cartao;

	public Pagamento(
			@JsonProperty("id")Long id,
			@JsonProperty("formaPgto")String formaPgto,
			@JsonProperty("codBoleto")String codBoleto,
			@JsonProperty("valorPago")double valorPago,
			@JsonProperty("vencimento")Date vencimento,
			@JsonProperty("dataPgto")Date vencAtual,
			@JsonProperty("associado")Associado associado,
			@JsonProperty("cartao")Cartao cartao){

		this.id = id;
		this.formaPgto = formaPgto;
    this.codBoleto = codBoleto;
    this.valorPago = valorPago;
    this.vencimento = vencimento;
    this.dataPgto = vencAtual;
    this.associado = associado;
    this.cartao = cartao;

  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(String formaPgto) {
		this.formaPgto = formaPgto;
	}

	public String getCodBoleto() {
		return codBoleto;
	}

	public void setCodBoleto(String codBoleto) {
		this.codBoleto = codBoleto;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getDataPgto() {
		return dataPgto;
	}

	public void setDataPgto(Date dataPgto) {
		this.dataPgto = dataPgto;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}


}
