package br.com.ntconsult.spal.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ntconsult.arquivo.builder.NomeArquivo;

import br.com.ntconsult.spal.request.ArquivoRequest;
import br.com.ntconsult.spal.service.FileUploadService;

/**
 * {@code LoteRepository} fornece métodos
 * reutilizáveis para tratativas e leitura de lotes
 * @author Fernando Bino Machado
 */
@Service
public class LoteRepository {
	
	@Autowired
	FileUploadService fileUploadService;
	
	public ArrayList<String[]> getArquivosLotes() {
	
		ArrayList<String[]> arquivosLotes = new ArrayList<>();
		
		File[] files = new File(new NomeArquivo().getHomePathAndDataDir()).listFiles();
		
		for( File f : files ) {
			arquivosLotes.add(new String[] {
				f.getName(),
				f.getAbsolutePath()
			});
		}
		
		return arquivosLotes;
		
	}
	
	public void salvarLote(MultipartFile file, String descricaoLote) 
		throws IllegalStateException, IOException {
		
		fileUploadService.uploadFile( file, descricaoLote );
		
	}
	
	public void deletarArquivoLote(ArquivoRequest arquivo) {

		File[] files = new File(new NomeArquivo().getHomePathAndDataDir()).listFiles();
		
		for( File f : files ) {
			
			if( f.getName().equals(arquivo.getNomeArquivo()) ) {
				arquivo.setSucesso(true);
				f.delete();
				break;
			}
			
		}
		
		if( !arquivo.isSucesso() ) {
			
			arquivo.setMensagem(new StringBuilder()
				.append("Arquivo não localizado...")
				.toString());
			
		}
		
	}

}
