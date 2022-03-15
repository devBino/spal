package br.com.ntconsult.service.process;

import java.util.ArrayList;

import br.com.ntconsult.service.constants.Processos;
import br.com.ntconsult.service.model.LoteModel;
import br.com.ntconsult.service.model.RelatorioModel;
import br.com.ntconsult.service.repository.LoteRepository;
import br.com.ntconsult.service.singleton.ListaLoteModel;
import br.com.ntconsult.service.singleton.RelatorioLotes;

public class LoteProcessor implements Runnable {

	private ArrayList<LoteModel> lotes;
	private Thread processo; 
	
	public LoteProcessor() {
		processo = new Thread(this, Processos.PRINCIPAL);
		lotes = new ArrayList<LoteModel>();
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
			
			ListaLoteModel listaLoteModel = ListaLoteModel.getInstance();
			
			if( !listaLoteModel.getNomes().isEmpty() ) {
				stop();
				return;
			}
			
			verificarRegistrosLotes(listaLoteModel);
			resume();
			
			ArquivoLoteProcessor arquivoLoteProcessor = new ArquivoLoteProcessor();
			arquivoLoteProcessor.iniciar();
			
			arquivoLoteProcessor.getProcesso().join();
			resume();
			
			gerarRelatorio();
			stop();
			
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}
	
	public synchronized void verificarRegistrosLotes(ListaLoteModel listaLoteModel) {
		
		try {
			
			LoteRepository.agruparLotes(listaLoteModel); 
			
			if( listaLoteModel.getNomes().isEmpty() ) {
				notify();
				return;
			}
			
			notify();
			
		}catch(Exception error){
			error.printStackTrace();
		}
		
	}
	
	public synchronized void gerarRelatorio() {
		
		RelatorioLotes relatorioLotes = RelatorioLotes.getInstance();
		
		if( relatorioLotes.getQuantidadeClientes() > 0
				&& relatorioLotes.getQuantidadeVendedores() > 0 ) {
		
			new RelatorioModel()
				.setConteudo(relatorioLotes.toString())
				.salvar();
			
		}
		
		relatorioLotes.resetParciais();
		
	}
	
	public synchronized void resume() {
		
		ListaLoteModel listaLoteModel = ListaLoteModel.getInstance();
		
		if( listaLoteModel.getNomes().isEmpty() ) {
			
			System.out.println( RelatorioLotes.getInstance().toString() );
			
			stop();
			
		}
		
	}
	
	public synchronized void stop() {
		notify();
		return;
	}
	
	
}
