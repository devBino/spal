package br.com.ntconsult.service.singleton;

import java.util.HashSet;
import java.util.Set;

import br.com.ntconsult.service.model.LoteModel;

public class ListaLoteModel {

	private static ListaLoteModel listaLoteModel;
	private Set<LoteModel> lotes;
	private Set<String> nomes;
	private boolean bloqueado;
	
	private ListaLoteModel() {
		lotes = new HashSet<LoteModel>();
		nomes = new HashSet<String>();
	}
	
	public static ListaLoteModel getInstance() {
	
		if( listaLoteModel == null ) {
			listaLoteModel = new ListaLoteModel();
		}
		
		return listaLoteModel;
		
	}

	public Set<LoteModel> getLotes() {
		return lotes;
	}

	public void setLotes(Set<LoteModel> lotes) {
		this.lotes = lotes;
	}

	public Set<String> getNomes() {
		return nomes;
	}

	public void setNomes(Set<String> nomes) {
		this.nomes = nomes;
	}

	public void bloquear() {
		this.bloqueado = true;
	}
	
	public void liberar() {
		this.bloqueado = false;
	}
	
	
	
}
