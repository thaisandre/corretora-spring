package br.com.caelum.corretora.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Role;

@Repository
public class RoleDAO {
	
	@PersistenceContext
	EntityManager manager;
	
	public Role buscaPor(String id) {
		return manager.find(Role.class, id);
		//return manager.createQuery("select r from Role r where r.id like :roleId", Role.class).setParameter("roleId", roleId).getSingleResult();
	}

}
