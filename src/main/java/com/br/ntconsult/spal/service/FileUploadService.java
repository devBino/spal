package com.br.ntconsult.spal.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.br.ntconsult.spal.repository.HomePath;

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
