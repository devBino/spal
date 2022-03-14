package br.com.ntconsult.spal.business;

import java.util.ArrayList;
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

	private ArrayList<String> mensagens;
	
	public GenericBusiness() {
		mensagens = new ArrayList<String>();
	}
	
	public void addMensagem(String mensagem) {
		this.mensagens.add(mensagem);
	}
	
	public ArrayList<String> getMensagens(){
		return mensagens;
	}
	
	public boolean existemErros() {
		return !mensagens.isEmpty();
	}
	
	public void resetarMensagens() {
		mensagens.clear();
	}
	
}
