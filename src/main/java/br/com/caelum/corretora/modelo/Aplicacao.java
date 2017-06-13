package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Aplicacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	@OneToOne
	@JoinColumn(name = "id_investimento")
	private Investimento investimento;
	
	@Column
	private LocalDate dataInicial;
	
	private BigDecimal valor;

	public Aplicacao(Conta conta, Investimento investimento, LocalDate dataInicial, BigDecimal valor) {
		if (conta == null)
			throw new IllegalArgumentException("conta não pode ser nula");
		if (investimento == null)
			throw new IllegalArgumentException("investimento não pode ser nulo");
		if(dataInicial == null)
			throw new IllegalArgumentException("data não pode ser nula");
		if(valor == null)
			throw new IllegalArgumentException("valor não pode ser nulo");
		if(valor.doubleValue() <investimento.getValorMinimo().doubleValue())
			throw new IllegalArgumentException("valor mínimo deve ser " + investimento.getValorMinimo());
		this.conta = conta;
		this.investimento = investimento;
		this.dataInicial = dataInicial;
		this.valor = valor;
	}
	
	public Aplicacao(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public Investimento getInvestimento() {
		return investimento;
	}


	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public Long getIntervalo() {
		return getDataInicial().until(LocalDate.now(), ChronoUnit.MONTHS);
	}
	
	
	private BigDecimal getRendimentoBruto() {
		return getValor().multiply((new BigDecimal(1.0).add(investimento.getRentabilidadeMensal()).pow((int) (getIntervalo() - 1))));
	}
	
	private BigDecimal getDesconto() {
		return investimento.getTipo().calcula(this).multiply(getRendimentoBruto());
	}

	private BigDecimal getRendimentoLiquido() {
		return getRendimentoBruto().subtract(getDesconto());
	}

	public BigDecimal getTotalResgate() {
		return getValor().add(getRendimentoLiquido());
	}

	public boolean resgata() {
		if (getIntervalo() >= investimento.getPrazo()) {
			conta.deposita(getTotalResgate());
			return true;
		} else {
			throw new RuntimeException("operação inválida - não pode resgatar antes de completar " + investimento.getPrazo() +  " meses");
		}
	}
}
