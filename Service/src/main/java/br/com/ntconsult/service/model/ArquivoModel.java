package br.com.ntconsult.service.model;

import br.com.ntconsult.arquivo.builder.NomeArquivo;

public class ArquivoModel {

	private NomeArquivo nomeArquivo;
	
	public ArquivoModel(NomeArquivo nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public NomeArquivo getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(NomeArquivo nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
}
