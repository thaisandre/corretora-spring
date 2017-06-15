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

import br.com.caelum.corretora.daos.InvestimentoDAO;
import br.com.caelum.corretora.modelo.Investimento;
import br.com.caelum.corretora.modelo.TipoDeInvestimento;
import br.com.caelum.corretora.modelo.Usuario;

@Controller
public class InvestimentoController {
	
	@Autowired
	private InvestimentoDAO investimentoDAO;
	
	@RequestMapping("/investimento/form")
	public ModelAndView form(Investimento investimento) {
		ModelAndView modelAndView = new ModelAndView("investimento/form");
		modelAndView.addObject("tipos", TipoDeInvestimento.values());
		return modelAndView;
	}
	
	@Transactional
	@RequestMapping(value="/investimento", method=RequestMethod.POST)
	public ModelAndView salva(@Valid Investimento investimento, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return form(investimento);
		}
		investimentoDAO.salva(investimento);
		redirectAttributes.addFlashAttribute("sucesso", "Investimento cadastrado com sucesso");
		return new ModelAndView("redirect:/investimento");
	}
	
	@RequestMapping(value="/investimento", method=RequestMethod.GET)
	public ModelAndView lista(@AuthenticationPrincipal Usuario usuarioLogado) {
		ModelAndView modelAndView = new ModelAndView("/investimento/lista");
		modelAndView.addObject("investimentos", investimentoDAO.lista());
		return modelAndView;
	}
	
	/**public ModelAndView listaPor(@AuthenticationPrincipal Usuario usuarioLogado) {
		ModelAndView modelAndView = new ModelAndView("/investimento/lista");
		modelAndView.addObject("investimentos", investimentoDAO.listaPor(usuarioLogado));
		return modelAndView;
	}**/
}
