package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aparelhos_Eletronicos {

    private int id;
    private boolean televisao;
    private boolean tv_assinatura;
    private boolean computador;
    private boolean notebook;
    private boolean fogao;
    private boolean geladeira;
    private boolean microondas;
    private boolean tablet;
    private boolean maquina_lavar;
    private boolean maquina_secar;
    private boolean telefone_fixo;
    private boolean celular;

    public Aparelhos_Eletronicos() {};

    public Aparelhos_Eletronicos(
            @JsonProperty("id")int id,
            @JsonProperty("televisao")boolean televisao,
            @JsonProperty("tv_assinatura")boolean tv_assinatura,
            @JsonProperty("computador")boolean computador,
            @JsonProperty("notebook")boolean notebook,
            @JsonProperty("fogao")boolean fogao,
            @JsonProperty("geladeira")boolean geladeira,
            @JsonProperty("microondas")boolean microondas,
            @JsonProperty("tablet")boolean tablet,
            @JsonProperty("maquina_lavar")boolean maquina_lavar,
            @JsonProperty("maquina_secar")boolean maquina_secar,
            @JsonProperty("telefone_fixo")boolean telefone_fixo,
            @JsonProperty("celular")boolean celular) {

        this.id = id;
        this.televisao = televisao;
        this.tv_assinatura = tv_assinatura;
        this.computador = computador;
        this.notebook = notebook;
        this.fogao = fogao;
        this.geladeira = geladeira;
        this.microondas = microondas;
        this.tablet = tablet;
        this.maquina_lavar = maquina_lavar;
        this.maquina_secar = maquina_secar;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean getTelevisao() {
        return televisao;
    }
    public void setTelevisao(boolean televisao) {
        this.televisao = televisao;
    }

    public boolean getTv_assinatura() {
        return tv_assinatura;
    }
    public void setTv_assinatura(boolean tv_assinatura) {
        this.tv_assinatura = tv_assinatura;
    }
    
    public boolean getComputador() {
        return computador;
    }
    public void setComputador(boolean computador) {
        this.computador = computador;
    }

    public boolean getNotebook() {
        return notebook;
    }
    public void setNotebook(boolean notebook) {
        this.notebook = notebook;
    }

    public boolean getFogao() {
        return fogao;
    }
    public void setFogao(boolean fogao) {
        this.fogao = fogao;
    }

    public boolean getGeladeira() {
        return geladeira;
    }
    public void setGeladeira(boolean geladeira) {
        this.geladeira = geladeira;
    }

    public boolean getMicroondas() {
        return microondas;
    }
    public void setMicroondas(boolean microondas) {
        this.microondas = microondas;
    }

    public boolean getTablet() {
        return tablet;
    }
    public void setTablet(boolean tablet) {
        this.tablet = tablet;
    }

    public boolean getMaquina_lavar() {
        return maquina_lavar;
    }
    public void setMaquina_lavar(boolean maquina_lavar) {
        this.maquina_lavar = maquina_lavar;
    }

    public boolean getMaquina_secar() {
        return maquina_secar;
    }
    public void setMaquina_secar(boolean maquina_secar) {
        this.maquina_secar = maquina_secar;
    }

    public boolean getTelefone_fixo() {
        return telefone_fixo;
    }
    public void setTelefone_fixo(boolean telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }

    public boolean getCelular() {
        return celular;
    }
    public void setCelular(boolean celular) {
        this.celular = celular;
    }
}