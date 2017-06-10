package br.com.caelum.corretora.controllers;

import java.util.ArrayList;
import java.util.List;

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

import br.com.caelum.corretora.daos.AplicacaoDAO;
import br.com.caelum.corretora.daos.ContaDAO;
import br.com.caelum.corretora.daos.InvestimentoDAO;
import br.com.caelum.corretora.modelo.Aplicacao;
import br.com.caelum.corretora.modelo.AplicacaoForm;
import br.com.caelum.corretora.modelo.Conta;
import br.com.caelum.corretora.modelo.Investimento;
import br.com.caelum.corretora.modelo.Usuario;

@Controller
public class AplicacaoController {

	@Autowired
	private AplicacaoDAO aplicacaoDAO;

	@Autowired
	private ContaDAO contaDao;

	@Autowired
	private InvestimentoDAO investimentoDAO;

	@RequestMapping("/aplicacao/form")
	public ModelAndView form(@AuthenticationPrincipal Usuario usuarioLogado, AplicacaoForm aplicacaoForm) {
		ModelAndView modelAndView = new ModelAndView("/aplicacao/form");
		modelAndView.addObject("investimentos", investimentoDAO.lista());
		modelAndView.addObject("contas", contaDao.listaPor(usuarioLogado));
		return modelAndView;
	}

	@Transactional
	@RequestMapping(value = "/aplicar", method = RequestMethod.POST)
	public ModelAndView salva(@AuthenticationPrincipal Usuario usuarioLogado, @Valid AplicacaoForm aplicacaoForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return form(usuarioLogado, aplicacaoForm);
		}

		Conta conta = contaDao.buscaPor(aplicacaoForm.getContaId());
		Investimento investimento = investimentoDAO.buscaPor(aplicacaoForm.getInvestimentoId());
		Aplicacao aplicacao = aplicacaoForm.build(conta, investimento, aplicacaoForm.getValor());
		aplicacaoDAO.salva(aplicacao);

		redirectAttributes.addFlashAttribute("sucesso", "Aplicacao cadastrada com sucesso");
		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/aplicacao", method = RequestMethod.GET)
	public ModelAndView lista(@AuthenticationPrincipal Usuario usuarioLogado) {
		ModelAndView modelAndView = new ModelAndView("aplicacao/lista");

		List<Aplicacao> lista = new ArrayList<Aplicacao>();
		List<Aplicacao> aplicacoes = aplicacaoDAO.lista();
		List<Conta> contas = contaDao.listaPor(usuarioLogado);
		
		for (Conta c : contas) {
			for (Aplicacao a : aplicacoes) {
				if (c.getId() == a.getConta().getId()) {
					lista.add(a);
				}
			}
		}
		modelAndView.addObject("aplicacoes", lista);
		return modelAndView;
	}
}
