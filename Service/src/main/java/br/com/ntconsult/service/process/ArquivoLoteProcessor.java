package br.com.ntconsult.service.process;

import br.com.ntconsult.service.constants.Processos;
import br.com.ntconsult.service.singleton.ListaLoteModel;

public class ArquivoLoteProcessor implements Runnable {

	private Thread processo;
	
	public ArquivoLoteProcessor() {
	
		processo = new Thread(this, Processos.PROCESSAMENTO_LOTES);
		
	}
	
	public void iniciar() {
		processo.start();
	}
	
	public Thread getProcesso() {
		return processo;
	}
	
	@Override
	public void run() {
		
		try {
			
			Thread.sleep(30000);
			
			ListaLoteModel.getInstance().getNomes().clear();
			
			resume();
			
		}catch(Exception error) {
			
		}
		
	}
	
	public synchronized void resume() {
		notify();
	}
	
	
	
}
