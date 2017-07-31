package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import model.aluno.Turma;

import java.sql.Date;

public class Aluno {
    private int ra;
    private String nome;
    private Turma turma;
    private Date data_nascimento;
    private String rg;
    private String naturalidade;
    private String estado;
    private Date data_cadastro;
    private String meio_transporte;
    private String observacoes;


    public Aluno () {}

    public Aluno(
            @JsonProperty("ra") int ra,
            @JsonProperty("nome") String nome,
            @JsonProperty("turma") Turma turma,
            @JsonProperty("data_nascimento") Date data_nascimento,
            @JsonProperty("rg") String rg,
            @JsonProperty("naturalidade") String naturalidade,
            @JsonProperty("estado") String estado,
            @JsonProperty("data_cadastro") Date data_cadastro,
            @JsonProperty("meio_transporte") String meio_transporte,
            @JsonProperty("observacoes") String observacoes) {

        this.ra = ra;
        this.nome = nome;
        this.turma = turma;
        this.data_nascimento = data_nascimento;
        this.rg = rg;
        this.naturalidade = naturalidade;
        this.estado = estado;
        this.data_cadastro = data_cadastro;
        this.meio_transporte = meio_transporte;
        this.observacoes = observacoes;
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
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

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getMeio_transporte() {
        return meio_transporte;
    }

    public void setMeio_transporte(String meio_transporte) {
        this.meio_transporte = meio_transporte;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}

