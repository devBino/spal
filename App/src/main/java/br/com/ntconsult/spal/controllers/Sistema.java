package br.com.ntconsult.spal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * {@code Lote} recebe e processa requisições vindas
 * da view sistema.
 * @author Fernando Bino Machado
 */
@Controller
public class Sistema {

	@GetMapping( path = "/" )
	public String index() {
		return "sistema";
	}
	
	@GetMapping( path = "/sistema" )
	public String sistema() {
		return "sistema";
	}
	
}
