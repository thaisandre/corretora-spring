package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Investimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoDeInvestimento tipo;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal taxaDeJuros;
	
	@NotNull
	@Min(0)
	private Integer prazo;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal valorMinimo;

	public Investimento(TipoDeInvestimento tipo, BigDecimal taxaDeJuros, Integer prazo, BigDecimal valorMinimo) {
		validaTipo(tipo);
		validaTaxaDeJuros(taxaDeJuros);
		validaPrazo(prazo);
		validaValorMinimo(valorMinimo);

		this.tipo = tipo;
		this.taxaDeJuros = taxaDeJuros;
		this.prazo = prazo;
		this.valorMinimo = valorMinimo;
	}

	private void validaValorMinimo(BigDecimal valorMinimoAValidar) {
		if (valorMinimoAValidar == null) {
			throw new IllegalArgumentException("valo mínimo não pode ser nulo");
		}
		if (valorMinimoAValidar.doubleValue() <= 0.0) {
			throw new IllegalArgumentException("valor mínimo não pode ser negativo");
		}
	}

	private void validaPrazo(Integer prazoAValidar) {
		if (prazoAValidar == null) {
			throw new IllegalArgumentException("prazo não pode ser nulo");
		}
		if (prazoAValidar < 0) {
			throw new IllegalArgumentException("prazo não pode ter valor negativo");
		}
	}

	private void validaTaxaDeJuros(BigDecimal taxaDeJurosAValidar) {
		if (taxaDeJurosAValidar == null) {
			throw new IllegalArgumentException("taxa não pode ser nula");
		}
		if (taxaDeJurosAValidar.doubleValue() <= 0.0) {
			throw new IllegalArgumentException("taxa deve possuir valor positivo");
		}
	}

	private void validaTipo(TipoDeInvestimento tipoAValidar) {
		if (tipoAValidar == null) {
			throw new IllegalArgumentException("tipo não pode ser nulo");
		}
	}

	public Investimento() {
	}

	public TipoDeInvestimento getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoDeInvestimento tipo) {
		this.tipo = tipo;
	}

	public Integer getPrazo() {
		return prazo;
	}
	
	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public BigDecimal getTaxaDeJuros() {
		return taxaDeJuros;
	}
	
	public void setTaxaDeJuros(BigDecimal taxaDeJuros) {
		this.taxaDeJuros = taxaDeJuros;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getRentabilidadeMensal() {
		//return Math.pow(10, Math.log10(getTaxaDeJuros().add(new BigDecimal(1))) / 12) - 1;
		return new BigDecimal(0.8);
	}

	@Override
	public String toString() {
		return "[" + this.getTipo() + ", " + this.getTaxaDeJuros() + ", " + this.getPrazo() + "]";
	}
}
