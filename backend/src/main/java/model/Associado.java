package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Associado {

	private String cpf;
	private String nome;
	private String celular;
	private String email;
	private double valorAtual;
	private Integer vencAtual;

	public Associado() {};
	
	public Associado(
			@JsonProperty("cpf")String cpf,
			@JsonProperty("nome")String nome,
			@JsonProperty("celular")String celular,
			@JsonProperty("email")String email,
			@JsonProperty("valorAtual")double valorAtual,
			@JsonProperty("vencAtual")Integer vencAtual){

        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.valorAtual = valorAtual;
        this.vencAtual = vencAtual;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public Integer getVencAtual() {
		return vencAtual;
	}

	public void setVencAtual(Integer vencAtual) {
		this.vencAtual = vencAtual;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}


}
