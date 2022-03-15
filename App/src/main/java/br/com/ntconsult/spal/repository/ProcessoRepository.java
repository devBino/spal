package br.com.ntconsult.spal.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntconsult.arquivo.builder.NomeArquivo;
import br.com.ntconsult.arquivo.builder.NomeArquivoBuilder;



@Service
public class ProcessoRepository {

	@Autowired
	LoteRepository loteRepository;
	
	public ArrayList<String> getNomesLotes(){
		
		ArrayList<String[]> arquivosLotes = loteRepository.getArquivosLotes();
		ArrayList<String> nomesLotes = new ArrayList<String>();
		
		for(String[] arquivo : arquivosLotes) {
			
			NomeArquivo nomeArquivo = new NomeArquivoBuilder()
					.setCaminhoCompleto(arquivo[1])
					.builder();
			
			if( !nomesLotes.contains(nomeArquivo.getDescricaoLote()) ) {
				nomesLotes.add(nomeArquivo.getDescricaoLote());
			}
			
		}
		
		return nomesLotes;
		
	}
	
}
