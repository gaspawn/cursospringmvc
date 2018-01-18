package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;

@Controller
@Cacheable(value="produtosHome")
public class HomeController {

	@Autowired
	ProdutoDAO produtoDao;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtoDao.listar());
		System.out.println("Entrando na home do CDC");
		return modelAndView;
	}
}
