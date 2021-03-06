package br.com.ntconsult.spal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.ntconsult.spal.repository.RelatorioRepository;

/**
 * {@code Lote} recebe e processa requisições vindas
 * da view relatorio
 * @author Fernando Bino Machado
 */
@Controller
public class Relatorio {

	@Autowired
	RelatorioRepository relatorioRepository;
	
	@GetMapping( path = "/relatorios" )
	public String index(Model model) {
	
		model.addAttribute("relatorios", relatorioRepository.getRelatorio());
		
		return "relatorio";
	}
	
}
