package br.com.ntconsult.service.process;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class VerificaLote {

	@Scheduled( fixedDelay = 500000 )
	public static void processarLotes() {
		
		LoteProcessor processo = new LoteProcessor();
		processo.iniciar();
		
	}
	
}
