package br.com.ntconsult.nomearquivo.builder;

/**
 * {@code NomeArquivoBuilder} fornece um padrão
 * para criação do nome dos arquivos
 * @author Fernando Bino Machado
 */
public class NomeArquivoBuilder {

	private NomeArquivo nomeArquivo;
	
	public NomeArquivoBuilder() {
		nomeArquivo = new NomeArquivo();
	}

	public NomeArquivoBuilder setDiretorio(String diretorio) {
		nomeArquivo.setDiretorio(diretorio);
		return this;
	}

	public NomeArquivoBuilder setDescricaoLote(String descricaoLote) {
		nomeArquivo.setDescricaoLote(descricaoLote);
		return this;
	}

	public NomeArquivoBuilder setHashData(String hashData) {
		nomeArquivo.setHashData(hashData);
		return this;
	}

	public NomeArquivoBuilder setNomeArquivo(String nome) {
		nomeArquivo.setNome(nome);
		return this;
	}

	public NomeArquivoBuilder setRecursoDataDir(String recursoDataDir) {
		nomeArquivo.setRecursoDataDir(recursoDataDir);
		return this;
	}
	
	public NomeArquivoBuilder setCaminhoCompleto(String caminhoCompleto) {
		nomeArquivo.setCaminhoCompleto(new StringBuilder(caminhoCompleto));
		return this;
	}
	
	public NomeArquivo builder() {
		nomeArquivo.construirNomeArquivo();
		return nomeArquivo;
	}
	
	
}
