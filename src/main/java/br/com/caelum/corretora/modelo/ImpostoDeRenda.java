package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;

public class ImpostoDeRenda implements Desconto {
	
	public BigDecimal getValor(Aplicacao aplicacao) {
		if(aplicacao.getIntervalo() <= 12) {
			return new BigDecimal(0.25);
		} else if(aplicacao.getIntervalo() > 12 && aplicacao.getIntervalo() <= 24) {
			return new BigDecimal(0.20);
		} else {
			return new BigDecimal(0.15);
		}
	}
}
