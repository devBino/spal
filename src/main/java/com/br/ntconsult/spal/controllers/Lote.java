package com.br.ntconsult.spal.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.br.ntconsult.spal.service.FileUploadService;

@Controller
public class Lote {

	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping( path = "/lotes", method = RequestMethod.GET )
	public String index() {
		return "lote";
	}
	
	@RequestMapping( path = "/salvar", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public String salvarLote(@RequestParam("descricao") String descricao, 
			@RequestParam("files") MultipartFile[] files ) 
			throws IllegalStateException, IOException {
		
		fileUploadService.uploadFile( files[0] );
		
		return "lote";
		
	}
	
}
