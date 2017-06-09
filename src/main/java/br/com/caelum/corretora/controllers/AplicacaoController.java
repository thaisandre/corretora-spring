package br.com.caelum.corretora.controllers;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.corretora.daos.AplicacaoDAO;
import br.com.caelum.corretora.modelo.Aplicacao;
import br.com.caelum.corretora.modelo.AplicacaoForm;
import br.com.caelum.corretora.modelo.Conta;
import br.com.caelum.corretora.modelo.Investimento;

public class AplicacaoController {
	
	private AplicacaoDAO aplicacaoDAO;
	
	@RequestMapping("/aplicacao/aplicar")
	public ModelAndView form(AplicacaoForm aplicacaoForm) {
		ModelAndView modelAndView = new ModelAndView("/aplicacao/form");
		return modelAndView;
	}
	
	@RequestMapping("/aplicacao")
	public ModelAndView salva(@Valid AplicacaoForm aplicacaoForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Conta conta, Investimento investimento) {
		if(bindingResult.hasErrors()) {
			return form(aplicacaoForm);
		}
	
		Aplicacao aplicacao = aplicacaoForm.build(conta, investimento);
		aplicacaoDAO.salva(aplicacao);
		redirectAttributes.addFlashAttribute("sucesso", "Aplicacao cadastrado com sucesso");
		return new ModelAndView("redirect:/aplicacao");
	}
	
	@RequestMapping(value="/aplicacao", method=RequestMethod.GET)
	public ModelAndView salva() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("aplicacoes", aplicacaoDAO.lista());
		return modelAndView;
	}
}
