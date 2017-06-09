package br.com.caelum.corretora.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Investimento;

@Repository
public class InvestimentoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salva(Investimento investimento) {
		manager.persist(investimento);
	}

	public List<Investimento> lista() {
		return manager.createQuery("select i from Investimento i", Investimento.class).getResultList();
	}
}
