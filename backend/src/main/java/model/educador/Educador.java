package model.educador;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.aluno.Endereco;

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
	private String numeroPis;
	private Endereco endereco;
	private String horaEntrada;
	private String horaSaida;
	
	public Educador() {}
	
	public Educador (
			@JsonProperty("cpf") String cpf,
			@JsonProperty("nome") String nome,
			@JsonProperty("dataNasc") Date dataNasc,
			@JsonProperty("sexo") String sexo,
			@JsonProperty("telefone") String telefone,
			@JsonProperty("email") String email,
			@JsonProperty("cargo") String cargo,
			@JsonProperty("numeroCarteiraProfissional") int numeroCarteiraProfissional,
			@JsonProperty("serieCarteiraProfissional") int serieCarteiraProfissional,
			@JsonProperty("numeroPis") String numeroPis,
			@JsonProperty("endereco") Endereco endereco,
      @JsonProperty("horaEntrada") String horaEntrada,
			@JsonProperty("horaSaida") String horaSaida) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = email;
		this.cargo = cargo;
		this.numeroCarteiraProfissional = numeroCarteiraProfissional;
		this.serieCarteiraProfissional = serieCarteiraProfissional;
		this.numeroPis = numeroPis;
		this.endereco = endereco;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public String getNumeroPis() {
		return numeroPis;
	}

	public void setNumeroPis(String numeroPis) {
		this.numeroPis = numeroPis;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	
}
