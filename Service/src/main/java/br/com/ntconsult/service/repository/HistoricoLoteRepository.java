package br.com.ntconsult.service.repository;

import br.com.ntconsult.arquivo.builder.NomeArquivo;
import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.arquivo.odata.ArquivoData;
import br.com.ntconsult.service.constants.NomesArquivos;
import br.com.ntconsult.service.model.HistoricoModel;
import br.com.ntconsult.service.model.LoteModel;

public class HistoricoLoteRepository {

	LoteModel loteModel;
	
	public HistoricoLoteRepository(LoteModel loteModel) {
		this.loteModel = loteModel;
	}

	public LoteModel getLoteModel() {
		return loteModel;
	}

	public void setLoteModel(LoteModel loteModel) {
		this.loteModel = loteModel;
	}
	
	public boolean exists(String descricaoLote) {
		
		String caminhoArquivo = new NomeArquivo()
				.getHomePath()
				.getHomePathAndDataDir(RecursoDataDir.HISTORICO_LOTE)
				.concat(NomesArquivos.HISTORICO_LOTE);
		
		ArquivoData arquivo = new ArquivoData(caminhoArquivo);
		arquivo.ler();
		
		boolean existe = false;
		
		for(String historico : arquivo.getContents().toString().split("\n")) {
			
			if( historico.contains(descricaoLote) ) {
				existe = true;
				break;
			}
			
		}
		
		return existe;
		
	}

	public void criarHistoricoLote() {
		
		new HistoricoModel(loteModel.getDescricaoLote()).salvar();
		
	}
	
}
