package model.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Despesa {

	private EstruturaFamiliar estruturaFamiliar;
	private double agua;
	private double energiaEletrica;
	private double telefone;
	private double aluguel;
	private double financiamentoCasa;
	private double financiamentoCarro;
	private double transporte;
	private double alimentacao;
	private double gas;
	private double cartaoCredito;
	private double emprestimo;
	private double tvCabo;
	private double educacao;
	private double pensao;
	private double convenioMedico;
	
	public Despesa() {};
	
	public Despesa(
			@JsonProperty("estruturaFamiliar")EstruturaFamiliar estruturaFamiliar,
			@JsonProperty("agua")double agua,
			@JsonProperty("energiaEletrica")double energiaEletrica,
			@JsonProperty("telefone")double telefone,
			@JsonProperty("aluguel")double aluguel,
			@JsonProperty("financiamentoCasa")double financiamentoCasa,
			@JsonProperty("financiamentoCarro")double financiamentoCarro,
			@JsonProperty("transporte")double transporte,
			@JsonProperty("alimentacao")double alimentacao,
			@JsonProperty("gas")double gas,
			@JsonProperty("cartaoCredito")double cartaoCredito,
			@JsonProperty("emprestimo")double emprestimo,
			@JsonProperty("tvCabo")double tvCabo,
			@JsonProperty("educacao")double educacao,
			@JsonProperty("pensao")double pensao,
			@JsonProperty("convenioMedico")double convenioMedico) {
		
		this.estruturaFamiliar = estruturaFamiliar;
		this.agua = agua;
		this.energiaEletrica = energiaEletrica;
		this.telefone = telefone;
		this.aluguel = aluguel;
		this.financiamentoCasa = financiamentoCasa;
		this.financiamentoCarro = financiamentoCarro;
		this.transporte = transporte;
		this.alimentacao = alimentacao;
		this.gas = gas;
		this.cartaoCredito = cartaoCredito;
		this.emprestimo = emprestimo;
		this.tvCabo = tvCabo;
		this.educacao = educacao;
		this.pensao = pensao;
		this.convenioMedico = convenioMedico;
	}

	public EstruturaFamiliar getEstruturaFamiliar() {
		return estruturaFamiliar;
	}

	public void setEstruturaFamiliar(EstruturaFamiliar estruturaFamiliar) {
		this.estruturaFamiliar = estruturaFamiliar;
	}

	public double getAgua() {
		return agua;
	}

	public void setAgua(double agua) {
		this.agua = agua;
	}

	public double getEnergiaEletrica() {
		return energiaEletrica;
	}

	public void setEnergiaEletrica(double energiaEletrica) {
		this.energiaEletrica = energiaEletrica;
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

	public double getFinanciamentoCasa() {
		return financiamentoCasa;
	}

	public void setFinanciamentoCasa(double financiamentoCasa) {
		this.financiamentoCasa = financiamentoCasa;
	}

	public double getFinanciamentoCarro() {
		return financiamentoCarro;
	}

	public void setFinanciamentoCarro(double financiamentoCarro) {
		this.financiamentoCarro = financiamentoCarro;
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

	public double getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(double cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public double getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(double emprestimo) {
		this.emprestimo = emprestimo;
	}

	public double getTvCabo() {
		return tvCabo;
	}

	public void setTvCabo(double tvCabo) {
		this.tvCabo = tvCabo;
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

	public double getConvenioMedico() {
		return convenioMedico;
	}

	public void setConvenioMedico(double convenioMedico) {
		this.convenioMedico = convenioMedico;
	}
	
	
}
