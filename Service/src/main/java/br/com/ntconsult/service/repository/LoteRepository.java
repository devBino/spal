package br.com.ntconsult.service.repository;

import java.io.File;

import br.com.ntconsult.arquivo.builder.NomeArquivo;

import br.com.ntconsult.service.model.LoteModel;
import br.com.ntconsult.service.singleton.ListaLoteModel;

public class LoteRepository {
	
	public static void agruparLotes(ListaLoteModel listaLoteModel) {
	
		File diretorio = new File( new NomeArquivo().getHomePathAndDataDir() );
		File[] arquivos = diretorio.listFiles();
		
		for(File a : arquivos) {
			
			LoteModel loteModel = new LoteModel(a);
			HistoricoLoteRepository historico = new HistoricoLoteRepository(loteModel);
			
			if( !listaLoteModel.getNomes().contains(loteModel.getDescricaoLote())
				&& !historico.exists(loteModel.getDescricaoLote())	) {
				
				historico.criarHistoricoLote();
				
				loteModel.setArquivos(arquivos);
				listaLoteModel.getLotes().add(loteModel);
				listaLoteModel.getNomes().add(loteModel.getDescricaoLote());
				
			}
			
		}
		
	}

}
