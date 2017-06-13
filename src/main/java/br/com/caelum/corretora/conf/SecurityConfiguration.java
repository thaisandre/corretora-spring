package br.com.caelum.corretora.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.caelum.corretora.daos.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioDAO usuarios;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuario").hasRole("ADMIN")
		.antMatchers("/usuario/lista").hasRole("ADMIN")
		.antMatchers("/usuario/form").hasRole("ADMIN")
		.antMatchers("/investimento").hasRole("ADMIN")
		.antMatchers("/investimento/lista").hasRole("ADMIN")
		.antMatchers("/conta").hasRole("CLIENTE")
		.antMatchers("/aplicacao").hasRole("CLIENTE")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.permitAll()
		.and().exceptionHandling()
		.accessDeniedPage("/WEB-INF/views/errors/403.jsp");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarios)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
}
