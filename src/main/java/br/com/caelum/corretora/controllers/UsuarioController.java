package br.com.caelum.corretora.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.corretora.daos.RoleDAO;
import br.com.caelum.corretora.daos.UsuarioDAO;
import br.com.caelum.corretora.modelo.Usuario;
import br.com.caelum.corretora.modelo.UsuarioForm;

@Controller
public class UsuarioController {
	
	//@InitBinder
	//public void initBinder(WebDataBinder binder) {
	//	binder.addValidators(new UsuarioValidator());
	//s}
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@RequestMapping("/usuario/form")
	public ModelAndView form(UsuarioForm usuarioForm) {
		ModelAndView modelAndView = new ModelAndView("/usuario/form");
		modelAndView.addObject("usuarioForm", usuarioForm);
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value="/usuario", method=RequestMethod.POST)
	public String salva(@Valid UsuarioForm usuarioForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "/usuario/form";
		}
		
		Usuario usuario = usuarioForm.build();
		for(String r : usuarioForm.getRoles()) {
			usuario.getRoles().add(roleDAO.buscaPor(r));
		}
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
