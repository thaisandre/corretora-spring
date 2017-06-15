package br.com.caelum.corretora.modelo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caelum.corretora.daos.RoleDAO;

public class UsuarioForm {
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String senha;
	
	private List<String> roles = new ArrayList<>();
	
	@Autowired
	RoleDAO roleDAO; 
	
	public UsuarioForm(){}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public Usuario build() {
		Usuario usuario = new Usuario(login, new BCryptPasswordEncoder().encode(senha));
		return usuario;
	}
}
