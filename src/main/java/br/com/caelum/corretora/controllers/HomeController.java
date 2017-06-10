package br.com.caelum.corretora.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.corretora.modelo.Usuario;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public ModelAndView index(@AuthenticationPrincipal Usuario usuarioLogado) {		
		ModelAndView modelAndView;
		if(usuarioLogado.getLogin().equals("admin@corretora.com")) {
			modelAndView = new ModelAndView("/home/index-admin");
		} else {
			modelAndView = new ModelAndView("/home/index");
		}
		modelAndView.addObject("usuarioLogado", usuarioLogado);
		return modelAndView;
	}
}
