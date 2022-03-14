package br.com.ntconsult.service.repository;

import java.io.File;

import br.com.ntconsult.nomearquivo.builder.NomeArquivo;

import br.com.ntconsult.service.model.LoteModel;
import br.com.ntconsult.service.singleton.ListaLoteModel;

public class LoteRepository {
	
	public static void agruparLotes(ListaLoteModel listaLoteModel) {
	
		File diretorio = new File( new NomeArquivo().getHomePathAndDataDir() );
		File[] arquivos = diretorio.listFiles();
		
		for(File a : arquivos) {
			
			LoteModel loteModel = new LoteModel(a);
			
			if( !listaLoteModel.getNomes().contains(loteModel.getDescricaoLote()) ) {
			
				HistoricoLoteRepository historico = new HistoricoLoteRepository(loteModel);
				historico.criarHistoricoLote();
				
				loteModel.setArquivos(arquivos);
				listaLoteModel.getLotes().add(loteModel);
				listaLoteModel.getNomes().add(loteModel.getDescricaoLote());
				
			}
			
		}
		
	}

}
