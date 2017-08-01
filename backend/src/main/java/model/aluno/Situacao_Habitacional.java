package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Aluno;

public class Situacao_Habitacional {

    private Aluno aluno;
    private String situacao;
    private boolean esgoto;
    private boolean rede_eletrica;
    private boolean asfalto;
    private int numero_comodos;
    private boolean alvenaria;
    private boolean madeira;
    private boolean area_irregular;
    private Aparelhos_Eletronicos aparelhos_eletronicos;

    public Situacao_Habitacional() {};

    public Situacao_Habitacional(
            @JsonProperty("aluno")Aluno aluno,
            @JsonProperty("situacao")String situacao,
            @JsonProperty("esgoto")Boolean esgoto,
            @JsonProperty("rede_eletrica")Boolean rede_eletrica,
            @JsonProperty("asfalto")Boolean asfalto,
            @JsonProperty("numero_comodos")int numero_comodos,
            @JsonProperty("alvenaria")Boolean alvenaria,
            @JsonProperty("madeira")Boolean madeira,
            @JsonProperty("area_irregular")Boolean area_irregular,
            @JsonProperty("aparelhos_eletronicos")Aparelhos_Eletronicos aparelhos_eletronicos){
        this.aluno = aluno;
        this.situacao = situacao;
        this.esgoto = esgoto;
        this.rede_eletrica = rede_eletrica;
        this.asfalto = asfalto;
        this.numero_comodos = numero_comodos;
        this.alvenaria = alvenaria;
        this.madeira = madeira;
        this.area_irregular = area_irregular;
        this.aparelhos_eletronicos = aparelhos_eletronicos;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }

    public String getSituacao() {
        return situacao;
    }
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Boolean getEsgoto() {
        return esgoto;
    }
    public void setEsgoto(Boolean esgoto) {
        this.esgoto = esgoto;
    }

    public Boolean getRede_eletrica() {
        return rede_eletrica;
    }
    public void setRede_eletrica(Boolean rede_eletrica) {
        this.rede_eletrica = rede_eletrica;
    }

    public Boolean getAsfalto() {
        return asfalto;
    }
    public void setAsfalto(Boolean asfalto) {
        this.asfalto = asfalto;
    }

    public int getNumero_comodos() {
        return numero_comodos;
    }
    public void setNumero_comodos(int numero_comodos) {
        this.numero_comodos = numero_comodos;
    }

    public Boolean getAlvenaria() {
        return alvenaria;
    }
    public void setAlvenaria(Boolean alvenaria) {
        this.alvenaria = alvenaria;
    }

    public Boolean getMadeira() {
        return madeira;
    }
    public void setMadeira(Boolean madeira) {
        this.madeira = madeira;
    }

    public Boolean getArea_irregular() {
        return area_irregular;
    }
    public void setArea_irregular(Boolean area_irregular) {
        this.area_irregular = area_irregular;
    }

    public Aparelhos_Eletronicos getAparelhos_eletronicos() {
        return aparelhos_eletronicos;
    }
    public void setAparelhos_eletronicos(Aparelhos_Eletronicos aparelhos_eletronicos) {
        this.aparelhos_eletronicos = aparelhos_eletronicos;
    }
}