package br.com.caelum.corretora.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.caelum.corretora.modelo.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public void salva(Usuario usuario) {
		manager.persist(usuario);
	}

	public List<Usuario> lista() {
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}

	// obs.: spring já verifica a senha
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.login = :login", 
				Usuario.class).setParameter("login", login).getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("O usuario " + login + "não existe");
		}
		return (UserDetails) usuarios.get(0);
	}
}
