package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Despesa {

	private Estrutura_Familiar estrutura_familiar;
	private double agua;
	private double energia_eletrica;
	private double telefone;
	private double aluguel;
	private double financiamento_casa;
	private double financiamento_carro;
	private double transporte;
	private double alimentacao;
	private double gas;
	private double cartao_credito;
	private double emprestimo;
	private double tv_cabo;
	private double educacao;
	private double pensao;
	private double convenio_medico;
	
	public Despesa() {};
	
	public Despesa(
			@JsonProperty("estrutura_familiar")Estrutura_Familiar estrutura_familiar,
			@JsonProperty("agua")double agua,
			@JsonProperty("energia_eletrica")double energia_eletrica,
			@JsonProperty("telefone")double telefone,
			@JsonProperty("aluguel")double aluguel,
			@JsonProperty("financiamento_casa")double financiamento_casa,
			@JsonProperty("financiamento_carro")double financiamento_carro,
			@JsonProperty("transporte")double transporte,
			@JsonProperty("alimentacao")double alimentacao,
			@JsonProperty("gas")double gas,
			@JsonProperty("cartao_credito")double cartao_credito,
			@JsonProperty("emprestimo")double emprestimo,
			@JsonProperty("tv_cabo")double tv_cabo,
			@JsonProperty("educacao")double educacao,
			@JsonProperty("pensao")double pensao,
			@JsonProperty("convenio_medico")double convenio_medico) {
		
		this.estrutura_familiar = estrutura_familiar;
		this.agua = agua;
		this.energia_eletrica = energia_eletrica;
		this.telefone = telefone;
		this.aluguel = aluguel;
		this.financiamento_casa = financiamento_casa;
		this.financiamento_carro = financiamento_carro;
		this.transporte = transporte;
		this.alimentacao = alimentacao;
		this.gas = gas;
		this.cartao_credito = cartao_credito;
		this.emprestimo = emprestimo;
		this.tv_cabo = tv_cabo;
		this.educacao = educacao;
		this.pensao = pensao;
		this.convenio_medico = convenio_medico;
	}

	public Estrutura_Familiar getEstrutura_familiar() {
		return estrutura_familiar;
	}

	public void setEstrutura_familiar(Estrutura_Familiar estrutura_familiar) {
		this.estrutura_familiar = estrutura_familiar;
	}

	public double getAgua() {
		return agua;
	}

	public void setAgua(double agua) {
		this.agua = agua;
	}

	public double getEnergia_eletrica() {
		return energia_eletrica;
	}

	public void setEnergia_eletrica(double energia_eletrica) {
		this.energia_eletrica = energia_eletrica;
	}

	public double getTelefone() {
		return telefone;
	}

	public void setTelefone(double telefone) {
		this.telefone = telefone;
	}

	public double getAluguel() {
		return aluguel;
	}

	public void setAluguel(double aluguel) {
		this.aluguel = aluguel;
	}

	public double getFinanciamento_casa() {
		return financiamento_casa;
	}

	public void setFinanciamento_casa(double financiamento_casa) {
		this.financiamento_casa = financiamento_casa;
	}

	public double getFinanciamento_carro() {
		return financiamento_carro;
	}

	public void setFinanciamento_carro(double financiamento_carro) {
		this.financiamento_carro = financiamento_carro;
	}

	public double getTransporte() {
		return transporte;
	}

	public void setTransporte(double transporte) {
		this.transporte = transporte;
	}

	public double getAlimentacao() {
		return alimentacao;
	}

	public void setAlimentacao(double alimentacao) {
		this.alimentacao = alimentacao;
	}

	public double getGas() {
		return gas;
	}

	public void setGas(double gas) {
		this.gas = gas;
	}

	public double getCartao_credito() {
		return cartao_credito;
	}

	public void setCartao_credito(double cartao_credito) {
		this.cartao_credito = cartao_credito;
	}

	public double getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(double emprestimo) {
		this.emprestimo = emprestimo;
	}

	public double getTv_cabo() {
		return tv_cabo;
	}

	public void setTv_cabo(double tv_cabo) {
		this.tv_cabo = tv_cabo;
	}

	public double getEducacao() {
		return educacao;
	}

	public void setEducacao(double educacao) {
		this.educacao = educacao;
	}

	public double getPensao() {
		return pensao;
	}

	public void setPensao(double pensao) {
		this.pensao = pensao;
	}

	public double getConvenio_medico() {
		return convenio_medico;
	}

	public void setConvenio_medico(double convenio_medico) {
		this.convenio_medico = convenio_medico;
	}
	
	
}
