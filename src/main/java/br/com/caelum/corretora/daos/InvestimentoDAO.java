package br.com.caelum.corretora.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Investimento;
import br.com.caelum.corretora.modelo.Usuario;

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
	
	public Investimento buscaPor(Integer id) {
		return manager.find(Investimento.class, id);
	}

	public List<Investimento> buscaPor(Usuario usuarioLogado) {
		return manager.createQuery("select i from Investimento i inner join Conta c join Usuario u where"
				+ " u.login link :user", Investimento.class).setParameter("user", usuarioLogado.getLogin()).getResultList();
	}
}
