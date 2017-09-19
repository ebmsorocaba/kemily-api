package model.educador;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Educador {
	private String cpf;
	private String nome;
	private Date dataNasc;
	private String sexo;
	private String telefone;
	private String email;
	private String cargo;
	private int numeroCarteiraProfissional;
	private int serieCarteiraProfissional;
	private long numeroPis;
	
	public Educador() {}
	
	public Educador (
			@JsonProperty("cpf") String cpf,
			@JsonProperty("nome") String nome,
			@JsonProperty("dataNasc") Date dataNasc,
			@JsonProperty("sexo") String sexo,
			@JsonProperty("telefone") String telefone,
			@JsonProperty("email") String email) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = email;
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
	
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getNumeroCarteiraProfissional() {
		return numeroCarteiraProfissional;
	}

	public void setNumeroCarteiraProfissional(int numeroCarteiraProfissional) {
		this.numeroCarteiraProfissional = numeroCarteiraProfissional;
	}

	public int getSerieCarteiraProfissional() {
		return serieCarteiraProfissional;
	}

	public void setSerieCarteiraProfissional(int serieCarteiraProfissional) {
		this.serieCarteiraProfissional = serieCarteiraProfissional;
	}

	public long getNumeroPis() {
		return numeroPis;
	}

	public void setNumeroPis(long numeroPis) {
		this.numeroPis = numeroPis;
	}
	
}
