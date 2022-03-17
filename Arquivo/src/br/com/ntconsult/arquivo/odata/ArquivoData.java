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
	
	public ArquivoData(String paramPath) {
		path = paramPath;
		contents = new StringBuilder();
	}
	
	public boolean arquivoExiste() {
			
		File file = new File( path );
		return file.exists();
		
	}
	
	public boolean deletarArquivo() {
		
		if( arquivoExiste() ) {
		
			File file = new File(path);
			return file.delete();
			
		}
		
		return false;
		
	}

	public void ler() {
		
		try {
			
			if( arquivoExiste() ) {
			
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
	
	public StringBuilder getContents() {
		return contents;
	}
	
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
