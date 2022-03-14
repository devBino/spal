package br.com.ntconsult.spal.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ntconsult.nomearquivo.builder.NomeArquivo;
import br.com.ntconsult.nomearquivo.builder.NomeArquivoBuilder;

/**
 * {@code FileUploadService} faz o upload de um MultipartFile
 * para o diretório padrão em home path.
 * @author Fernando Bino Machado
 */
@Service
public class FileUploadService {

	public void uploadFile(MultipartFile file, String descricaoLote) 
			throws IllegalStateException, IOException {
		
		NomeArquivo nomeArquivo = new NomeArquivoBuilder()
				.setDescricaoLote(descricaoLote)
				.setNomeArquivo(file.getOriginalFilename())
				.builder();
		
		file.transferTo( Paths.get( nomeArquivo.toString() ) );
		
		
	}
	
}
