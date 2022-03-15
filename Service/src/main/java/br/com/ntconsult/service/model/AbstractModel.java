package br.com.ntconsult.service.model;

import br.com.ntconsult.nomearquivo.repository.HomePath;
import br.com.ntconsult.service.odata.ArquivoData;

public class AbstractModel {
	
	protected int id;
	protected String nomeArquivo;
	protected HomePath homePath;
	protected String recursoDataDir;
	
	public AbstractModel() {}
	
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

	public AbstractModel salvar() {

		ArquivoData arquivoData = new ArquivoData(homePath.getHomePathAndDataDir(
				recursoDataDir).concat("/").concat(this.nomeArquivo));
		
		arquivoData.escrever(this.toString());
		
		return this;
		
	}
	
}
