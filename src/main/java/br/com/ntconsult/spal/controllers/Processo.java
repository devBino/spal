package br.com.ntconsult.spal.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ntconsult.spal.repository.ProcessoRepository;
import br.com.ntconsult.spal.request.LoteRequest;

@Controller
public class Processo {

	@Autowired
	ProcessoRepository processoRepository;
	
	@GetMapping( path = "/processos" )
	public String index(Model model) {
	
		ArrayList<String> nomesLotes = processoRepository.getNomesLotes(); 
		
		model.addAttribute("nomesLotes", nomesLotes);
		
		return "processo";
		
	}

	@PostMapping( path = "/solicitar-processamento-lote" )
	public ResponseEntity<LoteRequest> solicitarProcessamentoLote(
			@RequestBody LoteRequest lote ){
		
		return new ResponseEntity<LoteRequest>(lote, HttpStatus.OK);
	}
	
	@PostMapping( path = "/pesquisar" )
	public String pesquisar() {
		return "processo";
	}
	
	
	
}