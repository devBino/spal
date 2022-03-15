package br.com.ntconsult.arquivo.odata;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

import java.util.ArrayList;

/**
 * {@code Arquivo} fornece métodos
 * para leitura e escrita em arquivos.
 * 
 * @author Fernando Bino Machado
 */
public class ArquivoData {

	private String path;
	private StringBuilder contents;
	
	/**
	 * Recebe caminho do arquivo 
	 * e cria instância de {@code Arquivo}
	 * @param paramPath String caminho do arquivo
	 */
	public ArquivoData(String paramPath) {
		path = paramPath;
		contents = new StringBuilder();
	}
	
	/**
	 * Verifica se o arquivo existe
	 * @return boolean indicando sim ou não
	 */
	public boolean arqExiste() {
			
		File file = new File( path );
		return file.exists();
		
	}
	
	/**
	 * Deleta arquivo e retorna se deletou
	 * com sucesso.
	 * @return boolean indicando se deletou ou não
	 */
	public boolean deletarArquivo() {
		
		File file = new File(path);
		return file.delete();
		
	}

	/**
	 * Lê conteúdo do arquivo e seta o valor 
	 * no atributo contents
	 */
	public void ler() {
		
		try {
			
			if( arqExiste() ) {
			
				FileReader fReader = new FileReader( path );
				BufferedReader bReader = new BufferedReader( fReader );
				
				bReader.lines().forEach(ln -> {
					contents.append(ln.toString());
					contents.append("\n");
				});
				
				bReader.close();
			}
			
		}catch(Exception error) {
			return;
		}
		
	}
	
	/**
	 * Recebe texto e escreve no arquivo e
	 * retorna se escreveu com sucesso, onde
	 * 
	 * @param conteudo String contendo o texto
	 * @return boolean indicando se escreveu
	 */
	public boolean escrever(String conteudo) {
		
		if( conteudo.isEmpty() ) {
			return false;
		}
		
		try {
			FileWriter fWrite = new FileWriter(path,true);
			BufferedWriter bWriter = new BufferedWriter(fWrite);
			
			bWriter.append(conteudo);			
			bWriter.close();
			
			return true;
			
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
	
	/**
	 * Retorna o texto lido no arquivo
	 * @return String contendo o texto
	 */
	public StringBuilder getContents() {
		return contents;
	}
	
	/**
	 * Converte o conteudo do arquivo em ume lista de Strings
	 * onde
	 * @return registros contem a lista
	 */
	public ArrayList<String[]> toListString() {
		
		ler();
		
		String[] linhas = contents.toString().split("\n");

		ArrayList<String[]> registros = new ArrayList<String[]>(); 
		
		if( linhas.length == 1 && linhas[0].isEmpty() ) {
			return registros; 
		}
		
		for( int i=0; i<linhas.length; i++ ) {
			String[] arrLinha = linhas[i].split("ç");
			registros.add(arrLinha);
		}
		
		return registros;
		
	}
	
	
}
