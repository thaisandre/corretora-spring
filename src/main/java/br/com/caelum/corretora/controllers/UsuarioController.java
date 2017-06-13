package br.com.caelum.corretora.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.corretora.daos.UsuarioDAO;
import br.com.caelum.corretora.modelo.Usuario;
import br.com.caelum.corretora.validators.UsuarioValidator;

@Controller
public class UsuarioController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidator());
	}
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping("/usuario/form")
	public String form() {
		return "usuario/form";
	}
	
	@Transactional
	@RequestMapping(value="/usuario", method=RequestMethod.POST)
	public String salva(Usuario usuario, RedirectAttributes redirectAttributes) {
		usuarioDAO.salva(usuario);
		redirectAttributes.addFlashAttribute("sucesso", "usario cadastrado com sucesso");
		return "redirect:/usuario";
	}
	
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView("/usuario/lista");
		modelAndView.addObject("usuarios", usuarioDAO.lista());
		return modelAndView;
	}
}
