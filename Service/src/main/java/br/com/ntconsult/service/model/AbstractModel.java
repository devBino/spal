package br.com.ntconsult.service.model;

import br.com.ntconsult.arquivo.repository.HomePath;
import br.com.ntconsult.arquivo.odata.ArquivoData;

/**
 * {@code AbstractModel} fornece padrão
 * abstrato para Models representando registros
 * nos arquivos
 * @author Fernando Bino Machado
 */
public class AbstractModel {
	
	protected int id;
	protected String nomeArquivo;
	protected HomePath homePath;
	protected String recursoDataDir;
	
	public AbstractModel(String nomeArquivo) {
		
		this.nomeArquivo = nomeArquivo;
		
		homePath = Enum.valueOf(HomePath.class, 
				System.getProperty("os.name").toUpperCase());
		
	}
	
	public int getId() {
		return id;
	}

	public AbstractModel setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public AbstractModel setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		return this;
	}
	
	protected void setRecursoDataDir(String recursoDataDir) {
		this.recursoDataDir = recursoDataDir;
	}

	/**
	 * @implNote Remove um arquivo completo
	 * @return retorna a classe atual
	 */
	public AbstractModel remover() {
		
		ArquivoData arquivoData = new ArquivoData(homePath.getHomePathAndDataDir(
				recursoDataDir).concat(this.nomeArquivo));
		
		arquivoData.deletarArquivo();
		
		return this;
		
	}
	
	/**
	 * Salva uma linha no arquivo
	 * onde a linha representa a classe atual 
	 * e classes filhas
	 * @return retorna a classe atual
	 */
	public AbstractModel salvar() {

		ArquivoData arquivoData = new ArquivoData(homePath.getHomePathAndDataDir(
				recursoDataDir).concat(this.nomeArquivo));
		
		arquivoData.escrever(this.toString());
		
		return this;
		
	}
	
}
