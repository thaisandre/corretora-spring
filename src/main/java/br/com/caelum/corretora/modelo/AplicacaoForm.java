package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AplicacaoForm {
	
	@NotNull
	private Integer contaId;
	
	@NotNull
	private Integer investimentoId;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal valor;
	
	public AplicacaoForm() {};
	
	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getInvestimentoId() {
		return investimentoId;
	}

	public void setInvestimentoId(Integer investimentoId) {
		this.investimentoId = investimentoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Aplicacao build(Conta conta, Investimento investimento, BigDecimal valor) {
		Aplicacao aplicacao = new Aplicacao(conta, investimento, LocalDate.now(), valor);
		return aplicacao;
	}
}
