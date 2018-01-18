package br.com.casadocodigo.loja.infra;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String arg0, Locale arg1) throws Exception {
		MappingJackson2JsonView mapJsonView = new MappingJackson2JsonView();
		mapJsonView.setPrettyPrint(true);
		return mapJsonView;
	}

}
