package br.com.ntconsult.service.process;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static br.com.ntconsult.service.constants.Processos.TEMPO_INTERVALO_JOB;

/**
 * {@code VerificaLote} veirica se existem lotes para serem processados,
 * e inicia o processamento
 * @author Fernando Bino Machado
 */
@Component
@EnableScheduling
public class VerificaLote {

	@Scheduled( fixedDelay = TEMPO_INTERVALO_JOB )
	public static void processarLotes() {
		
		try {
			
			new LoteProcessor().iniciar();
			
		}catch(Exception error) {
			
			System.out.println( error.getMessage() );
			
		}
		
	}
	
}
