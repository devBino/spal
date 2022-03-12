package com.br.ntconsult.spal.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.br.ntconsult.spal.business.LoteBusiness;
import com.br.ntconsult.spal.service.FileUploadService;

/**
 * {@code Lote} recebe e processa requisições vindas
 * da view lote.
 * @author Fernando Bino Machado
 */
@Controller
public class Lote {

	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired
	LoteBusiness business;
	
	@GetMapping( path = "/lotes" )
	public String index() {
		return "lote";
	}
	
	@PostMapping( path = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ModelAndView salvarLote(Model model, @RequestParam String descricao, 
		@RequestParam("files") MultipartFile[] listFiles ) 
			throws IllegalStateException, IOException {
		
		business.validaLoteBusiness(listFiles);
		
		if( business.existemErros() ) {
			model.addAttribute("sucesso","false");
			model.addAttribute("mensagens", business.getMensagens());
			
			return new ModelAndView("redirect:/lotes");
		}
		
		for(MultipartFile file : listFiles) {
			fileUploadService.uploadFile( file );	
		}
		
		model.addAttribute("sucesso", "true");
		
		return new ModelAndView("redirect:/lotes");
		
	}
	
}
