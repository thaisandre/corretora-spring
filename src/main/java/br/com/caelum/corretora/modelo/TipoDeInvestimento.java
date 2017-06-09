package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public enum TipoDeInvestimento {
	CDB(new ImpostoDeRenda()),
	LCI(new SemDesconto()),
	FDI(new ImpostoDeRenda(), new TaxaDeAdministracao());
	
	private List<Desconto> desconto;
	
	TipoDeInvestimento (Desconto...descontos) {
		desconto = Arrays.asList(descontos); 
	}
	
	public BigDecimal calcula(Aplicacao aplicacao) {
		return desconto.stream().map(d -> d.getValor(aplicacao))
				.reduce(new BigDecimal(0.0), (a,b) -> a.add(b));
	}
}
