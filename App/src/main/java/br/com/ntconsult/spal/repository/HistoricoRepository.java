package br.com.ntconsult.spal.repository;


import java.io.File;

import org.springframework.stereotype.Service;

import br.com.ntconsult.arquivo.builder.NomeArquivoBuilder;
import br.com.ntconsult.arquivo.constants.RecursoDataDir;

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
	
		String diretorioHistorico = new NomeArquivoBuilder()
			.setRecursoDataDir(RecursoDataDir.HISTORICO_LOTE)
			.builder()
			.getHomePathAndDataDir();
		
		File[] files = new File(diretorioHistorico).listFiles();
		
		return false;
		
	}
	
}
