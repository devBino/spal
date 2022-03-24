package br.com.ntconsult.spal.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntconsult.arquivo.builder.NomeArquivo;
import br.com.ntconsult.arquivo.builder.NomeArquivoBuilder;

/**
 * {@code ProcessoRepository} fornece métodos
 * reutilizáveis para tratativas e leitura de processos
 * @author Fernando Bino Machado
 */
@Service
public class ProcessoRepository {

	@Autowired
	LoteRepository loteRepository;
	
	public ArrayList<String> getNomesLotes(){
		
		ArrayList<String> nomesLotes = new ArrayList<>();
		
		for(String[] arquivo : loteRepository.getArquivosLotes()) {
			
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
