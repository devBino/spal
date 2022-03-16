package br.com.ntconsult.spal.request;

/**
 * {@code ArquivoRequest} representa os parâmetros de 
 * uma requisição de ArquivoRequest
 * @author Fernando Bino Machado
 */
public class ArquivoRequest extends GenericRequest {

	private String nomeArquivo;
	private String descricaoLote;
	private boolean sucesso;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getDescricaoLote() {
		return descricaoLote;
	}

	public void setDescricaoLote(String descricao) {
		this.descricaoLote = descricao;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	
}
