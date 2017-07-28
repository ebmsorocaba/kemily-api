package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imovel {
	
	private int id;
	private Estrutura_Familiar estrutura_familiar;
	
	public Imovel() {};
	
	public Imovel(
			@JsonProperty("id")int id,
			@JsonProperty("estrutura_familiar")Estrutura_Familiar estrutura_familiar) {
		
		this.id = id;
		this.estrutura_familiar = estrutura_familiar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estrutura_Familiar getEstrutura_familiar() {
		return estrutura_familiar;
	}

	public void setEstrutura_familiar(Estrutura_Familiar estrutura_familiar) {
		this.estrutura_familiar = estrutura_familiar;
	}
	
	
}
