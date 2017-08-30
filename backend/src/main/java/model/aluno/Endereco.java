package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endereco {

    private String cep;
    private String numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String pontoReferencia;
    private String complemento;
    private Aluno aluno;

    public Endereco() {};

    public Endereco(
            @JsonProperty("cep")String cep,
            @JsonProperty("numero")String numero,
            @JsonProperty("rua")String rua,
            @JsonProperty("bairro")String bairro,
            @JsonProperty("cidade")String cidade,
            @JsonProperty("pontoReferencia")String pontoReferencia,
            @JsonProperty("complemento")String complemento,
            @JsonProperty("aluno")Aluno aluno) {
        
        this.cep = cep;
        this.numero = numero;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.pontoReferencia = pontoReferencia;
        this.complemento = complemento;
        this.aluno = aluno;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }
    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}