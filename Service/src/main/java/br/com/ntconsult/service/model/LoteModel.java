package br.com.ntconsult.service.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.ntconsult.nomearquivo.builder.NomeArquivo;
import br.com.ntconsult.nomearquivo.builder.NomeArquivoBuilder;

public class LoteModel {

	private String descricaoLote;
	private List<ArquivoModel> arquivos;
	
	public LoteModel(String descricaoLote) {
		this.descricaoLote = descricaoLote;
		this.arquivos = new ArrayList<ArquivoModel>();
	}
	
	public LoteModel(File file) {
		
		NomeArquivo nomeArquivo = new NomeArquivoBuilder()
				.setCaminhoCompleto(file.getAbsolutePath())
				.builder();
		
		this.descricaoLote = nomeArquivo.getDescricaoLote();
		this.arquivos = new ArrayList<ArquivoModel>();
		
	}

	public String getDescricaoLote() {
		return descricaoLote;
	}

	public void setDescricaoLote(String descricaoLote) {
		this.descricaoLote = descricaoLote;
	}
	
	public void setArquivos(File[] files) {
		
		for( File f : files ) {
			
			NomeArquivo nomeArquivo = new NomeArquivoBuilder()
					.setCaminhoCompleto(f.getAbsolutePath())
					.builder();
			
			if( descricaoLote.equals( nomeArquivo.getDescricaoLote() ) ) {
				this.arquivos.add(new ArquivoModel(nomeArquivo));
			}
			
		}
		
	}
	
	public List<ArquivoModel> getListaArquivos(){
		return arquivos;
	}
	
	
}
