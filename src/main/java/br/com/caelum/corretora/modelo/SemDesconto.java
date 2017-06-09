package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	public BigDecimal getValor(Aplicacao aplicacao) {
		return new BigDecimal(0.0);
	}

}
