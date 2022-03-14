package br.com.ntconsult.spal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Sistema {

	@RequestMapping( path = "/", method = RequestMethod.GET )
	public String index() {
		return "sistema";
	}
	
	@RequestMapping( path = "/sistema", method = RequestMethod.GET )
	public String sistema() {
		return "sistema";
	}
	
}
