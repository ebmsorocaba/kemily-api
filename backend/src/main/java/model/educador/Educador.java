package model.educador;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Educador {
	private String cpf;
	private String nome;
	private Date dataNasc;
	private String sexo;
	
	public Educador() {}
	
	public Educador (
			@JsonProperty("cpf") String cpf,
			@JsonProperty("nome") String nome,
			@JsonProperty("dataNasc") Date dataNasc,
			@JsonProperty("sexo") String sexo) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
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

}
