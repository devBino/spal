package br.com.ntconsult.service.process;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * {@code VerificaLote} veirica se existem lotes para serem processados,
 * e inicia o processamento
 * @author Fernando Bino Machado
 */
@Component
@EnableScheduling
public class VerificaLote {

	@Scheduled( fixedDelay = 10000 )
	public static void processarLotes() {
		
		new LoteProcessor().iniciar();
		
	}
	
}
