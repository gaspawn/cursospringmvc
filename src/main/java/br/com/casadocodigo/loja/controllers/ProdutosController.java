package br.com.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void bind(WebDataBinder wdb) {
		wdb.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {

		ModelAndView mav = new ModelAndView("produtos/form");
		mav.addObject("tipos", TipoPreco.values());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value = "produtosHome")
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult br,
			RedirectAttributes attributes) {
		if (br.hasErrors()) {
			return form(produto);
		}
		String sumarioPath = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(sumarioPath);
		System.out.println(sumario.getOriginalFilename());
		System.out.println(produto);
		produtoDao.gravar(produto);
		attributes.addFlashAttribute("sucesso", "Produto adicionado com sucesso");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtoDao.listar());
		return modelAndView;
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto p = produtoDao.findById(id.intValue());
		modelAndView.addObject("produto", p);
		return modelAndView;
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public Produto detalheJson(@PathVariable Integer id) {
		return produtoDao.findById(id.intValue());
	}

}
