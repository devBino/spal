package br.com.ntconsult.spal.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.ntconsult.arquivo.builder.NomeArquivo;
import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.arquivo.odata.ArquivoData;

/**
 * {@code RelatorioRepository} fornece métodos
 * reutilizáveis para tratativas e leitura do relatório
 * @author Fernando Bino Machado
 */
@Service
public class RelatorioRepository {

	public static final String RELATORIO = "relatorio.txt";
	
	public ArrayList<String[]> getRelatorio(){
		
		String caminhoArquivo = new NomeArquivo()
				.getHomePath()
				.getHomePathAndDataDir(RecursoDataDir.OUT)
				.concat(RELATORIO);
		
		ArrayList<String[]> registros = new ArrayList<>();
		
		ArquivoData arquivo = new ArquivoData(caminhoArquivo);
		
		if( arquivo.arquivoExiste() ) {
			
			arquivo.ler();
			
			for( String linha : arquivo.getContents().toString().split("\n") ) {
				String[] dados = linha.split(":");
				registros.add(dados);
			}
			
		}
		
		return registros;
		
	}
	
}
