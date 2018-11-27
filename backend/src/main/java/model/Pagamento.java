package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Pagamento {

	private Integer id;
	private double valorPago;
	private Date dataPgto;
	private String formapgto;
	
	@JsonIgnore
	private String cpfassociado;
	
	private Associado associado;
	
	public Pagamento() {};

	public Pagamento(Integer id, double valorPago, Date dataPgto, String formapgto, String cpfassociado) {
		super();
		this.id = id;
		this.valorPago = valorPago;
		this.dataPgto = dataPgto;
		this.formapgto = formapgto;
		this.cpfassociado = cpfassociado;
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

	public Date getDataPgto() {
		return dataPgto;
	}

	public void setDataPgto(Date dataPgto) {
		this.dataPgto = dataPgto;
	}
	
	@JsonIgnoreProperties({ "celular", "email", "valorAtual", "vencAtual"})
	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public String getCpfassociado() {
		return cpfassociado;
	}

	public void setCpfassociado(String cpfassociado) {
		this.cpfassociado = cpfassociado;
	}

	public String getFormapgto() {
		return formapgto;
	}

	public void setFormapgto(String formapgto) {
		this.formapgto = formapgto;
	}	

}
