package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String folder, MultipartFile file) {

		String realPath = request.getServletContext().getRealPath("/" + folder);
		String finalPath = realPath + "/" + file.getOriginalFilename();
		try {
			file.transferTo(new File(finalPath));
		} catch (IllegalStateException | IOException e) {

			throw new RuntimeException(e);
		}

		return folder + "/" + file.getOriginalFilename();

	}
}
