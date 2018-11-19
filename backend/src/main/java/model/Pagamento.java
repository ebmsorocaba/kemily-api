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
	private String cpf_associado;
	private Associado associado;
	
	public Pagamento() {};

	public Pagamento(Integer id, double valorPago, Date dataPgto, String formapgto, String cpf_associado) {
		super();
		this.id = id;
		this.valorPago = valorPago;
		this.dataPgto = dataPgto;
		this.formapgto = formapgto;
		this.cpf_associado = cpf_associado;
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

	public String getCpf_associado() {
		return cpf_associado;
	}

	public void setCpf_associado(String cpf_associado) {
		this.cpf_associado = cpf_associado;
	}

	public String getFormapgto() {
		return formapgto;
	}

	public void setFormapgto(String formapgto) {
		this.formapgto = formapgto;
	}	

}
