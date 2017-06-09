package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;

public class TaxaDeAdministracao implements Desconto {

	public BigDecimal getValor(Aplicacao aplicacao) {
		return new BigDecimal(0.01);
	}

}
