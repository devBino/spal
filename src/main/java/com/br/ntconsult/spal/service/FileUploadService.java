package com.br.ntconsult.spal.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.ntconsult.spal.repository.HomePath;

/**
 * {@code FileUploadService} faz o upload de um MultipartFile
 * para o diretório padrão em home path.
 * @author Fernando Bino Machado
 */
@Service
public class FileUploadService {

	public void uploadFile(MultipartFile file) 
			throws IllegalStateException, IOException {
		
		HomePath homePath = Enum.valueOf(HomePath.class, 
				System.getProperty("os.name").toUpperCase());
		
		file.transferTo( 
			Paths.get( homePath.getHomePathAndDataDir().concat(file.getOriginalFilename()) ) 
		);
		
		
	}
	
}
