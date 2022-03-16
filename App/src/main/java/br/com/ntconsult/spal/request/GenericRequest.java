package br.com.ntconsult.spal.request;

/**
 * {@code GenericRequest} é uma abstração
 * para outras requisições, onde cada requisição 
 * sempre terá uma String mensagem, que 
 * terá a mensagem de retorno do processamento da
 * requisição
 * @author Fernando Bino Machado
 */
public class GenericRequest {

	public String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
