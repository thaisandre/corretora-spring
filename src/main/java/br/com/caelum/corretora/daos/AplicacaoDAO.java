package br.com.caelum.corretora.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Aplicacao;
import br.com.caelum.corretora.modelo.Conta;
import br.com.caelum.corretora.modelo.Investimento;
import br.com.caelum.corretora.modelo.Usuario;

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
				+ "= :idDaConta").setParameter("idDaConta", conta.getId());
		List<Aplicacao> aplicacoes = query.getResultList();
		return aplicacoes;
	}

	public void remove(Aplicacao aplicacao) {
		manager.remove(aplicacao);
		
	}

	public Aplicacao buscaPor(Integer id) {
		return manager.find(Aplicacao.class, id);
	}
	
	public void update(Aplicacao aplicacao) {
		manager.merge(aplicacao);
	}
	
	public List<Aplicacao> buscaPor(Usuario usuario) {
		Query query = manager.createQuery("select a from Aplicacao a where a.conta.usuario.login = :login",
				Aplicacao.class).setParameter("login", usuario.getLogin());
		List<Aplicacao> aplicacoes = query.getResultList();
		return aplicacoes;
	}
	
}
