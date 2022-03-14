package br.com.ntconsult.spal.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.ntconsult.spal.business.LoteBusiness;
import br.com.ntconsult.spal.repository.LoteRepository;
import br.com.ntconsult.spal.request.ArquivoRequest;

import org.springframework.ui.Model;

/**
 * {@code Lote} recebe e processa requisições vindas
 * da view lote.
 * @author Fernando Bino Machado
 */
@Controller
public class Lote {

	@Autowired
	LoteBusiness business;
	
	@Autowired
	LoteRepository loteRepository;
	
	@GetMapping( path = "/lotes" )
	public String index(Model model) {
		
		model.addAttribute("arquivos", loteRepository.getArquivosLotes());
		return "lote";
		
	}
	
	@PostMapping( path = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public String salvarLote(Model model, @RequestParam("descricao") String descricaoLote, 
		@RequestParam("files") MultipartFile[] listFiles ) 
			throws IllegalStateException, IOException {
		
		business.resetarMensagens();
		business.validaLoteBusiness(listFiles);
		business.validaDescricaoLote(descricaoLote);
		
		if( business.existemErros() ) {
			model.addAttribute("sucesso","false");
			model.addAttribute("mensagens", business.getMensagens());
			
			return "lote";
		}
		
		loteRepository.salvarLote(listFiles, descricaoLote);
		
		model.addAttribute("sucesso", "true");
		
		return "lote";
		
	}
	
	@PostMapping( path = "/lotes" )
	public ResponseEntity<ArquivoRequest> deletar(@RequestBody ArquivoRequest arquivo) {
		
		loteRepository.deletarArquivoLote(arquivo);
		
		return new ResponseEntity<ArquivoRequest>(arquivo, HttpStatus.OK); 
		
	}
	
	
}
