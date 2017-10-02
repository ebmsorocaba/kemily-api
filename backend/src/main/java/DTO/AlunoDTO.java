package DTO;

import model.aluno.*;

import java.util.List;

public class AlunoDTO {
    private Aluno aluno;
    private AparelhosEletronicos aparelhosEletronicos;
    private List<Automovel> automovelList;
    private List<MembroFamiliar> membroFamiliarList;
    private List<Contato> contatoList;
    private Despesa despesa;
    private Endereco endereco;
    private EstruturaFamiliar estruturaFamiliar;
    private List<Imovel> imovelList;
    private Roupa roupa;
    private Saude saude;
    private SituacaoHabitacional situacaoHabitacional;
    private List<ResponsavelLegal> responsavelLegalList;

    public List<ResponsavelLegal> getResponsavelLegalList() {
        return responsavelLegalList;
    }

    public void setResponsavelLegalList(List<ResponsavelLegal> responsavelLegalList) {
        this.responsavelLegalList = responsavelLegalList;
    }

    public AparelhosEletronicos getAparelhosEletronicos() {
        return aparelhosEletronicos;
    }

    public void setAparelhosEletronicos(AparelhosEletronicos aparelhosEletronicos) {
        this.aparelhosEletronicos = aparelhosEletronicos;
    }

    public List<Automovel> getAutomovelList() {
        return automovelList;
    }

    public void setAutomovelList(List<Automovel> automovelList) {
        this.automovelList = automovelList;
    }

    public List<MembroFamiliar> getMembroFamiliarList() {
        return membroFamiliarList;
    }

    public void setMembroFamiliarList(List<MembroFamiliar> membroFamiliarList) {
        this.membroFamiliarList = membroFamiliarList;
    }

    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EstruturaFamiliar getEstruturaFamiliar() {
        return estruturaFamiliar;
    }

    public void setEstruturaFamiliar(EstruturaFamiliar estruturaFamiliar) {
        this.estruturaFamiliar = estruturaFamiliar;
    }

    public List<Imovel> getImovelList() {
        return imovelList;
    }

    public void setImovelList(List<Imovel> imovelList) {
        this.imovelList = imovelList;
    }

    public Saude getSaude() {
        return saude;
    }

    public void setSaude(Saude saude) {
        this.saude = saude;
    }

    public SituacaoHabitacional getSituacaoHabitacional() {
        return situacaoHabitacional;
    }

    public void setSituacaoHabitacional(SituacaoHabitacional situacaoHabitacional) {
        this.situacaoHabitacional = situacaoHabitacional;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }
}