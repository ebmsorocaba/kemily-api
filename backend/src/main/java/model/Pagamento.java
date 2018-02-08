package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagamento {

	private Integer id;
	private double valorPago;
	private Date vencimento;
	private Date dataPgto;
	
	private FormaPagamento formaPgto;
	
	public Pagamento() {};
	
	public Pagamento(
			@JsonProperty("id")Integer id,
			@JsonProperty("valorPago")double valorPago,
			@JsonProperty("vencimento")Date vencimento,
			@JsonProperty("dataPgto")Date vencAtual,
			@JsonProperty("formaPgto")FormaPagamento formaPgto){

		this.id = id;
		this.valorPago = valorPago;
		this.vencimento = vencimento;
		this.dataPgto = vencAtual;
		this.formaPgto = formaPgto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public FormaPagamento getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPagamento formaPgto) {
		this.formaPgto = formaPgto;
	}	

}
