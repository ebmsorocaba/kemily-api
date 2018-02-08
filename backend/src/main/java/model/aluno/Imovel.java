package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imovel {
	
	private int id;
	private boolean financiado;
	private EstruturaFamiliar estruturaFamiliar;
	
	public Imovel() {};
	
	public Imovel(
			@JsonProperty("id")int id,
            @JsonProperty("financiado") boolean financiado,
			@JsonProperty("estruturaFamiliar")EstruturaFamiliar estruturaFamiliar) {
		
		this.id = id;
		this.financiado = financiado;
		this.estruturaFamiliar = estruturaFamiliar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public boolean isFinanciado() {
        return financiado;
    }

    public void setFinanciado(boolean financiado) {
        this.financiado = financiado;
    }

    public EstruturaFamiliar getEstruturaFamiliar() {
		return estruturaFamiliar;
	}

	public void setEstruturaFamiliar(EstruturaFamiliar estruturaFamiliar) {
		this.estruturaFamiliar = estruturaFamiliar;
	}
	
	
}
