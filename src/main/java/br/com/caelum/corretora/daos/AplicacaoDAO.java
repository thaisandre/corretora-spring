package br.com.caelum.corretora.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Aplicacao;
import br.com.caelum.corretora.modelo.Conta;
import br.com.caelum.corretora.modelo.Investimento;

@Repository
public class AplicacaoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salva(Aplicacao aplicacao) {
		manager.persist(aplicacao);
	}

	public List<Aplicacao> lista() {
		return manager.createQuery("select a from Aplicacao a", Aplicacao.class).getResultList();
	}
	
	public List<Investimento> getInvestimentosPor(Conta conta) {
		Query query = manager.createQuery("select a.investimento from aplicacao a "+
		          "where a.conta.id like :idDaConta").setParameter("idDaConta", conta.getId());
				   
		List<Investimento> investimentos = query.getResultList();
		return investimentos;
	}
	
	public List<Aplicacao> buscaPor(Conta conta) {
		Query query = manager.createQuery("select a from aplicacao a where a.conta.id "
				+ "like :idDaConta").setParameter("idDaConta", conta.getId());
		List<Aplicacao> aplicacoes = query.getResultList();
		return aplicacoes;
	}
}
