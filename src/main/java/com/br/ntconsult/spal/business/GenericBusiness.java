package com.br.ntconsult.spal.business;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * {@code GenericBusiness} fornece operações
 * para validações de regras de negócio ao enviar/processar
 * arquivos em lote
 *  
 * @author Fernando Bino Machado
 */
@Service
public abstract class GenericBusiness {

	private Set<String> mensagens;
	
	public GenericBusiness() {
		mensagens = new HashSet<String>();
	}
	
	public void addMensagem(String mensagem) {
		mensagens.add(mensagem);
	}
	
	public Set<String> getMensagens(){
		return mensagens;
	}
	
	public boolean existemErros() {
		return !mensagens.isEmpty();
	}
	
	public void resetarMensagens() {
		mensagens.clear();
	}
	
}
