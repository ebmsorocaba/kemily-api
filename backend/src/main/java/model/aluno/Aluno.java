package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Aluno {
    private int ra;
    private String nome;
    private Date dataNascimento;
    private String rg;
    private String naturalidade;
    private String estado;
    private Date dataCadastro;
    private String meioTransporte;
    private String etnia;
    private String observacoes;
    private Endereco endereco;
    private String escola;


    public Aluno () {}

    public Aluno(
            @JsonProperty("ra") int ra,
            @JsonProperty("nome") String nome,
            @JsonProperty("dataNascimento") Date dataNascimento,
            @JsonProperty("rg") String rg,
            @JsonProperty("naturalidade") String naturalidade,
            @JsonProperty("estado") String estado,
            @JsonProperty("dataCadastro") Date dataCadastro,
            @JsonProperty("meioTransporte") String meioTransporte,
            @JsonProperty("etnia") String etnia,
            @JsonProperty("observacoes") String observacoes,
            @JsonProperty("endereco") Endereco endereco,
            @JsonProperty("escola") String escola)
    {

        this.ra = ra;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.naturalidade = naturalidade;
        this.estado = estado;
        this.dataCadastro = dataCadastro;
        this.meioTransporte = meioTransporte;
        this.etnia = etnia;
        this.observacoes = observacoes;
        this.endereco = endereco;
        this.escola = escola;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(String meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}

