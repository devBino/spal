package br.com.ntconsult.spal.request;

/**
 * {@code LoteRequest} representa os parâmetros de 
 * uma requisição de LoteRequest
 * @author Fernando Bino Machado
 */
public class LoteRequest extends GenericRequest {

	private String nomeLote;

	public String getNomeLote() {
		return nomeLote;
	}

	public void setNomeLote(String nomeLote) {
		this.nomeLote = nomeLote;
	}
	
}
