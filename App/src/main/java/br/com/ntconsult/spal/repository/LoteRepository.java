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

@Service
public class LoteRepository {
	
	@Autowired
	FileUploadService fileUploadService;
	
	public ArrayList<String[]> getArquivosLotes() {
	
		File diretorio = new File( new NomeArquivo().getHomePathAndDataDir() );
		File[] arquivos = diretorio.listFiles();
		
		ArrayList<String[]> arquivosLotes = new ArrayList<String[]>();
		
		for(File a : arquivos) {
			arquivosLotes.add(new String[] {
				a.getName(),
				a.getAbsolutePath()
			});
		}
		
		return arquivosLotes;
		
	}
	
	public void salvarLote(MultipartFile file, String descricaoLote) 
		throws IllegalStateException, IOException {
		
		fileUploadService.uploadFile( file, descricaoLote );
		
	}
	
	public void deletarArquivoLote(ArquivoRequest arquivo) {
		
		File diretorio = new File( new NomeArquivo().getHomePathAndDataDir() );
		File[] files = diretorio.listFiles();

		for( File a : files ) {
			if( a.getName().equals(arquivo.getNomeArquivo()) ) {
				arquivo.setSucesso(true);
				a.delete();
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
