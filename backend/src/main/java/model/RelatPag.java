package model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatPag {

	private String cpf;
	private String nome;
	private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

	public RelatPag() {};
	
	public RelatPag(
			@JsonProperty("cpf")String cpf,
			@JsonProperty("nome")String nome,
			@JsonProperty("pagamentos") List<Pagamento> pagamentos){

        this.cpf = cpf;
        this.nome = nome;
        this.pagamentos = pagamentos;

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

}
