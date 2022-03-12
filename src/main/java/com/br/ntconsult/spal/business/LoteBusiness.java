package com.br.ntconsult.spal.business;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * {@code LoteBusiness} faz validações de regras negociais
 * ao enviar/processar lotes de arquivos.
 * 
 * @author Fernando Bino Machado
 */
@Service
public class LoteBusiness extends GenericBusiness {
	
	public LoteBusiness() {
		super();
	}
	
	public void validaLoteBusiness(MultipartFile[] listFiles) {

		resetarMensagens();
		
		for(MultipartFile file : listFiles) {
			
			if( !extensaoValida(file.getOriginalFilename()) ) {
				
				addMensagem(new StringBuilder()
					.append("Arquivo ")
					.append(file.getOriginalFilename())
					.append(" deve possuir extensão \".dat\"")
					.toString());
				
			}
			
		}
		
	}
	
	public boolean extensaoValida(String nomeArquivo) {
		return nomeArquivo.endsWith(".dat");
	}
	
}