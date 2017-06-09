package br.com.caelum.corretora.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.corretora.daos.ContaDAO;
import br.com.caelum.corretora.modelo.Conta;
import br.com.caelum.corretora.modelo.ContaForm;
import br.com.caelum.corretora.modelo.Usuario;

@Controller
public class ContaController {
	
	@Autowired
	private ContaDAO contaDAO;
	
	@RequestMapping("/conta/form")
	public ModelAndView form(@AuthenticationPrincipal Usuario usuarioLogado, ContaForm contaForm) {
		ModelAndView modelAndView = new ModelAndView("/conta/form");
		modelAndView.addObject("usuarioLogado", usuarioLogado);
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value="/conta", method=RequestMethod.POST)
	public ModelAndView salva(@AuthenticationPrincipal Usuario usuarioLogado, @Valid ContaForm contaForm, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return form(usuarioLogado, contaForm);
		}
		
		Conta conta = contaForm.build(usuarioLogado);
		System.out.println("cadastrando conta " + conta.getNumero());
		contaDAO.salva(conta);
		redirectAttributes.addFlashAttribute("sucesso", "Conta cadastrada com sucesso");
		return new ModelAndView("redirect:/conta");
	}
	
	@RequestMapping(value="/conta", method=RequestMethod.GET)
	public ModelAndView lista(@AuthenticationPrincipal Usuario usuarioLogado) {
		ModelAndView modelAndView = new ModelAndView("/conta/lista");
		modelAndView.addObject("contas", contaDAO.listaPor(usuarioLogado));
		return modelAndView;
	}
}
