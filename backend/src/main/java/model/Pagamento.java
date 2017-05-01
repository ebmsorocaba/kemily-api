package model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagamento {
	
	private Long id;
	private String formaPgto;
	private Long cpf;
	private Long numCartao;
	private String codBoleto;
	private double valorPago;
	private Date vencimento;
	private Date vencAtual;
	private Associado associado;
	
	public Pagamento(
			@JsonProperty("id")Long id,
			@JsonProperty("formaPgto")String formaPgto,
			@JsonProperty("cpf")Long cpf,
			@JsonProperty("numCartao")Long numCartao,
			@JsonProperty("codBoleto")String codBoleto,
			@JsonProperty("valorPago")double valorPago,
			@JsonProperty("vencimento")Date vencimento,
			@JsonProperty("vencAtual")Date vencAtual,
			@JsonProperty("associado")Associado associado){
		
		this.id = id;
		this.formaPgto = formaPgto;
        this.cpf = cpf;
        this.numCartao = numCartao;
        this.codBoleto = codBoleto;
        this.valorPago = valorPago;
        this.vencimento = vencimento;
        this.vencAtual = vencAtual;
        this.associado = associado;
        
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

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(Long numCartao) {
		this.numCartao = numCartao;
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

	public Date getVencAtual() {
		return vencAtual;
	}

	public void setVencAtual(Date vencAtual) {
		this.vencAtual = vencAtual;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
	
	
}
