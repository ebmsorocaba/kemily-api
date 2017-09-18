package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AparelhosEletronicos {

    private int id;
    private boolean televisao;
    private boolean tvAssinatura;
    private boolean computador;
    private boolean notebook;
    private boolean fogao;
    private boolean geladeira;
    private boolean microondas;
    private boolean maquinaLavar;
    private boolean maquinaSecar;
    private boolean telefoneFixo;
    private boolean celular;

    public AparelhosEletronicos() {};

    public AparelhosEletronicos(
            @JsonProperty("id")int id,
            @JsonProperty("televisao")boolean televisao,
            @JsonProperty("tvAssinatura")boolean tvAssinatura,
            @JsonProperty("computador")boolean computador,
            @JsonProperty("notebook")boolean notebook,
            @JsonProperty("fogao")boolean fogao,
            @JsonProperty("geladeira")boolean geladeira,
            @JsonProperty("microondas")boolean microondas,
            @JsonProperty("maquinaLavar")boolean maquinaLavar,
            @JsonProperty("maquinaSecar")boolean maquinaSecar,
            @JsonProperty("telefoneFixo")boolean telefoneFixo,
            @JsonProperty("celular")boolean celular) {

        this.id = id;
        this.televisao = televisao;
        this.tvAssinatura = tvAssinatura;
        this.computador = computador;
        this.notebook = notebook;
        this.fogao = fogao;
        this.geladeira = geladeira;
        this.microondas = microondas;
        this.maquinaLavar = maquinaLavar;
        this.maquinaSecar = maquinaSecar;
        this.telefoneFixo = telefoneFixo;
        this.celular = celular;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTelevisao() {
		return televisao;
	}

	public void setTelevisao(boolean televisao) {
		this.televisao = televisao;
	}

	public boolean isTvAssinatura() {
		return tvAssinatura;
	}

	public void setTvAssinatura(boolean tvAssinatura) {
		this.tvAssinatura = tvAssinatura;
	}

	public boolean isComputador() {
		return computador;
	}

	public void setComputador(boolean computador) {
		this.computador = computador;
	}

	public boolean isNotebook() {
		return notebook;
	}

	public void setNotebook(boolean notebook) {
		this.notebook = notebook;
	}

	public boolean isFogao() {
		return fogao;
	}

	public void setFogao(boolean fogao) {
		this.fogao = fogao;
	}

	public boolean isGeladeira() {
		return geladeira;
	}

	public void setGeladeira(boolean geladeira) {
		this.geladeira = geladeira;
	}

	public boolean isMicroondas() {
		return microondas;
	}

	public void setMicroondas(boolean microondas) {
		this.microondas = microondas;
	}

	public boolean isMaquinaLavar() {
		return maquinaLavar;
	}

	public void setMaquinaLavar(boolean maquinaLavar) {
		this.maquinaLavar = maquinaLavar;
	}

	public boolean isMaquinaSecar() {
		return maquinaSecar;
	}

	public void setMaquinaSecar(boolean maquinaSecar) {
		this.maquinaSecar = maquinaSecar;
	}

	public boolean isTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(boolean telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public boolean isCelular() {
		return celular;
	}

	public void setCelular(boolean celular) {
		this.celular = celular;
	}

}