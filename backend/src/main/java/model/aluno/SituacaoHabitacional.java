package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SituacaoHabitacional {

    private Aluno aluno;
    private String situacao;
    private boolean esgoto;
    private boolean redeEletrica;
    private boolean asfalto;
    private int numeroComodos;
    private boolean alvenaria;
    private boolean madeira;
    private boolean areaIrregular;
    private AparelhosEletronicos aparelhosEletronicos;

    public SituacaoHabitacional() {};

    public SituacaoHabitacional(
            @JsonProperty("aluno")Aluno aluno,
            @JsonProperty("situacao")String situacao,
            @JsonProperty("esgoto")Boolean esgoto,
            @JsonProperty("redeEletrica")Boolean redeEletrica,
            @JsonProperty("asfalto")Boolean asfalto,
            @JsonProperty("numeroComodos")int numeroComodos,
            @JsonProperty("alvenaria")Boolean alvenaria,
            @JsonProperty("madeira")Boolean madeira,
            @JsonProperty("areaIrregular")Boolean areaIrregular,
            @JsonProperty("aparelhosEletronicos")AparelhosEletronicos aparelhosEletronicos){
        this.aluno = aluno;
        this.situacao = situacao;
        this.esgoto = esgoto;
        this.redeEletrica = redeEletrica;
        this.asfalto = asfalto;
        this.numeroComodos = numeroComodos;
        this.alvenaria = alvenaria;
        this.madeira = madeira;
        this.areaIrregular = areaIrregular;
        this.aparelhosEletronicos = aparelhosEletronicos;
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

    public Boolean getRedeEletrica() {
        return redeEletrica;
    }
    public void setRedeEletrica(Boolean redeEletrica) {
        this.redeEletrica = redeEletrica;
    }

    public Boolean getAsfalto() {
        return asfalto;
    }
    public void setAsfalto(Boolean asfalto) {
        this.asfalto = asfalto;
    }

    public int getNumeroComodos() {
        return numeroComodos;
    }
    public void setNumeroComodos(int numeroComodos) {
        this.numeroComodos = numeroComodos;
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

    public Boolean getAreaIrregular() {
        return areaIrregular;
    }
    public void setAreaIrregular(Boolean areaIrregular) {
        this.areaIrregular = areaIrregular;
    }

    public AparelhosEletronicos getAparelhosEletronicos() {
        return aparelhosEletronicos;
    }
    public void setAparelhosEletronicos(AparelhosEletronicos aparelhosEletronicos) {
        this.aparelhosEletronicos = aparelhosEletronicos;
    }
}