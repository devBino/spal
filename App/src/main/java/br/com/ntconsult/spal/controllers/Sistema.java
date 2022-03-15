package br.com.ntconsult.spal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
