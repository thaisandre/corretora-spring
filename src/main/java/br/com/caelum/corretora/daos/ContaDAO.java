package br.com.caelum.corretora.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Conta;
import br.com.caelum.corretora.modelo.Usuario;

@Repository
public class ContaDAO {
	
	@PersistenceContext
	public EntityManager manager;
	
	public void salva(Conta conta) {
		manager.persist(conta);
	}
	
	public List<Conta> listaPor(Usuario usuario) {
		System.out.println(usuario.getLogin());
		return manager.createQuery("select c from Conta c where c.usuario = :usuario", Conta.class)
				.setParameter("usuario", usuario).getResultList();
	}
}
