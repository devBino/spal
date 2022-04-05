package br.com.ntconsult.spal.repository;


import java.io.File;

import org.springframework.stereotype.Service;

import br.com.ntconsult.arquivo.builder.NomeArquivoBuilder;
import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.arquivo.odata.ArquivoData;

/**
 * {@code HistoricoRepository} fornece métodos
 * reutilizáveis para verificações no histórico
 * dos Lotes
 * 
 * @author Fernando Bino Machado
 */
@Service
public class HistoricoRepository {

	public boolean existeHistoricoLote(String descricaoLote) {

		boolean historicoEncontrado = false;
		
		String diretorioHistorico = new NomeArquivoBuilder()
			.setRecursoDataDir(RecursoDataDir.HISTORICO_LOTE)
			.builder()
			.getHomePathAndDataDir();
		
		File[] files = new File(diretorioHistorico).listFiles();
		
		if( files.length > 0 ) {
			
			ArquivoData arquivoData = new ArquivoData( files[0].getAbsolutePath(),
					"\n",";");
			
			arquivoData.ler();
			
			for( String[] dadosLinha : arquivoData.toListString() ) {
				
				if( descricaoLote.toLowerCase().contains(dadosLinha[0].toLowerCase()) ) {
					
					historicoEncontrado = true;
					break;
					
				}
				
			}
			
		}
		
		return historicoEncontrado;
		
	}
	
}
