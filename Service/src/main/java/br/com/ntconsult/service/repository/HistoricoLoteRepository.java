package br.com.ntconsult.service.repository;

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

	public void criarHistoricoLote() {

		HistoricoModel historicoModel = new HistoricoModel(
				loteModel.getDescricaoLote()).salvar();
		
	}
	
}
