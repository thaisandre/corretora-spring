package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AplicacaoForm {
	
	@NotNull
	private Conta conta;
	
	@NotNull
	private Investimento investimento;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal valor;
	
	@NotNull
	private LocalDate dataInicial;

	
	public AplicacaoForm() {};
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Aplicacao build(Conta conta, Investimento investimento) {
		Aplicacao aplicacao = new Aplicacao(conta, investimento, dataInicial, valor);
		return aplicacao;
	}
}
