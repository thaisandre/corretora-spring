package br.com.caelum.corretora.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView form(@AuthenticationPrincipal Usuario usuarioLogado, AplicacaoForm aplicacaoForm, RedirectAttributes redirectAttributes) {
		if(contaDao.listaPor(usuarioLogado).isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "você ainda não possui uma conta");
			return new ModelAndView("redirect:/home");
		}
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
			return form(usuarioLogado, aplicacaoForm, redirectAttributes);
		}

		Conta conta = contaDao.buscaPor(aplicacaoForm.getContaId());
		System.out.println(conta);

		Investimento investimento = investimentoDAO.buscaPor(aplicacaoForm.getInvestimentoId());
		System.out.println(investimento);

		try {
			Aplicacao aplicacao = conta.investe(investimento, aplicacaoForm.getValor());
			aplicacaoDAO.salva(aplicacao);
			redirectAttributes.addFlashAttribute("message", " operação realizada com sucesso");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		} finally {
			return new ModelAndView("redirect:/aplicacao/form");
		}
		
	}
	
	@RequestMapping(value = "/aplicacao", method = RequestMethod.GET)
	public ModelAndView lista(@AuthenticationPrincipal Usuario usuarioLogado, RedirectAttributes redirectAttributes) {
		if(contaDao.listaPor(usuarioLogado).isEmpty() || aplicacaoDAO.buscaPor(usuarioLogado).isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "você ainda não possui investimentos");
			return new ModelAndView("redirect:/home");
		}
		
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

	@Transactional
	@RequestMapping(value = "resgatar/{id}", method = RequestMethod.GET)
	public ModelAndView resgata(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/aplicacao");
		Aplicacao aplicacao = aplicacaoDAO.buscaPor(id);
		try {
			aplicacao.resgata();
			aplicacaoDAO.remove(aplicacao);
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		} finally {
			return modelAndView;
		}
	}
}
