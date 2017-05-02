package model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Associado {
	
	private Long cpf;
	private String nome;
	private Long celular;
	private String email;
	private String formaPgto;
	private Cartao cartao;
	private double valorAtual;
	private Date vencAtual;
	
	public Associado(
			@JsonProperty("cpf")Long cpf,
			@JsonProperty("nome")String nome,
			@JsonProperty("celular")Long celular,
			@JsonProperty("email")String email,
			@JsonProperty("formaPgto")String formaPgto,
			@JsonProperty("cartao")Cartao cartao,
			@JsonProperty("valorAtual")double valorAtual,
			@JsonProperty("vencAtual")Date vencAtual){
		
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.formaPgto = formaPgto;
        this.cartao = cartao;
        this.valorAtual = valorAtual;
        this.vencAtual = vencAtual;
        
    }

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(String formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public Date getVencAtual() {
		return vencAtual;
	}

	public void setVencAtual(Date vencAtual) {
		this.vencAtual = vencAtual;
	}
	
	
}
