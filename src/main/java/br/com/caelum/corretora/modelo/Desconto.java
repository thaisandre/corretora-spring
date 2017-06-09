package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;

public interface Desconto {
	
	public BigDecimal getValor(Aplicacao aplicacao);
}
