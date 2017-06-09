package br.com.caelum.corretora.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.corretora.controllers.ContaController;
import br.com.caelum.corretora.controllers.HomeController;
import br.com.caelum.corretora.controllers.InvestimentoController;
import br.com.caelum.corretora.controllers.UsuarioController;
import br.com.caelum.corretora.daos.ContaDAO;
import br.com.caelum.corretora.daos.InvestimentoDAO;
import br.com.caelum.corretora.daos.UsuarioDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ContaController.class, UsuarioController.class, InvestimentoController.class,
		ContaDAO.class, UsuarioDAO.class, InvestimentoDAO.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}
}
