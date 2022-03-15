package br.com.ntconsult.service.model;

import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.service.constants.NomesArquivos;

public class RelatorioModel extends AbstractModel {

	private String conteudo;
	
	public RelatorioModel() {
		super(NomesArquivos.RELATORIO);
		setRecursoDataDir(RecursoDataDir.OUT);
	}

	public String getConteudo() {
		return conteudo;
	}

	public RelatorioModel setConteudo(String conteudo) {
		this.conteudo = conteudo;
		return this;
	}
	
	@Override
	public String toString() {
		return conteudo;
	}
	
	@Override
	public RelatorioModel salvar() {
		super.remover();
		super.salvar();
		return this;
	}
	
}
