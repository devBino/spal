package br.com.ntconsult.arquivo.builder;

import java.util.Date;

import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.arquivo.repository.HomePath;


/**
 * {@code NomeArquivo} possui atributos e métodos necessários
 * para criação dos nomes dos arquivos
 * @author Fernando Bino Machado
 */
public class NomeArquivo {

	private HomePath homePath;
	private String diretorio;
	private String recursoDataDir;
	private String descricaoLote;
	private String hashData;
	private String nome;
	private StringBuilder caminhoCompleto;
	private String separadorPastas;
	
	public NomeArquivo() {
		
		homePath = Enum.valueOf(HomePath.class, 
				System.getProperty("os.name").toUpperCase());
		
		hashData = getNumeroHashData();
		
		recursoDataDir = RecursoDataDir.IN;
		
		caminhoCompleto = new StringBuilder();
		
		setSeparadorPastas();
		
	}

	private String getNumeroHashData() {
		Date dataAtual = new Date();
		String hash = String.valueOf(dataAtual.getTime());
		
		return hash.substring(5, hash.length());
	}
	
	public HomePath getHomePath() {
		return homePath;
	}
	
	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	
	public String getHomePathAndDataDir() {
		return homePath.getHomePathAndDataDir(recursoDataDir);
	}
	
	public String getRecursoDataDir() {
		return recursoDataDir;
	}

	public void setRecursoDataDir(String recursoDataDir) {
		this.recursoDataDir = recursoDataDir;
	}

	public String getDescricaoLote() {
		return descricaoLote;
	}

	public void setDescricaoLote(String descricaoLote) {
		this.descricaoLote = descricaoLote;
	}

	public String getHashData() {
		return hashData;
	}

	public void setHashData(String hashData) {
		this.hashData = hashData;
	}

	public String getNomeArquivo() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StringBuilder getCaminhoCompleto() {
		return caminhoCompleto;
	}

	public void setCaminhoCompleto(StringBuilder caminhoCompleto) {
		
		this.caminhoCompleto = caminhoCompleto;
		
		desmontarNomeArquivo();
		
	}

	/**
	 * @implNote Caso necessite modificar o padrão do nome
	 * gerado nesse método, deve analisar o método
	 * desmontarNomeArquivo() nessa mesma classe
	 */
	public void construirNomeArquivo() {
		
		this.caminhoCompleto.delete(0, this.caminhoCompleto.length());
		
		this.caminhoCompleto
			.append(homePath.getHomePathAndDataDir(recursoDataDir))
			.append(descricaoLote)
			.append("_")
			.append(hashData)
			.append("_")
			.append(nome);
		
	}
	
	public void desmontarNomeArquivo() {
		
		if( caminhoCompleto.toString().contains(
				String.format("%s%s", separadorPastas, RecursoDataDir.IN)) ) {
			setRecursoDataDir(RecursoDataDir.IN);
		}else {
			setRecursoDataDir(RecursoDataDir.OUT);
		}
		
		String[] dadosCaminhoArquivo = caminhoCompleto.toString().split(separadorPastas);
		String[] dadosNomeArquivo = dadosCaminhoArquivo[dadosCaminhoArquivo.length - 1].split("_");

		setDescricaoLote(String.format("%s_%s", dadosNomeArquivo[0], dadosNomeArquivo[1]));
		setHashData(dadosNomeArquivo[2]);
		setNome(dadosNomeArquivo[ dadosNomeArquivo.length - 1 ]);
		
	}
	
	private void setSeparadorPastas() {
		
		String sistemaOperacional = System.getProperty("os.name");
		
		if( sistemaOperacional.equals("Linux") ) {
			separadorPastas = "/";
		}else if( sistemaOperacional.equals("Windows") ) {
			separadorPastas = "\\";
		}
		
	}
	
	@Override
	public String toString() {
		return caminhoCompleto.toString();
	}
	
}
