package br.com.ntconsult.service.exception;

/**
 * {@code ServiceException} captura exceções 
 * durante a execução do serviço de processamento
 * de arquivos
 * @author Fernando Bino Machado
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	
	public ServiceException(String mensagem) {
		
		this.mensagem = new StringBuilder()
			.append("SERVICE ERROR: \n")
			.append(mensagem)
			.append("\n")
			.toString();
		
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	@Override
	public String toString() {
		return mensagem;
	}

}
