package br.com.ntconsult.service.model;

import br.com.ntconsult.arquivo.builder.NomeArquivo;

/**
 * Modelo de dados para arquivos
 * @author Fernando Bino Machado
 */
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
