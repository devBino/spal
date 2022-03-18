package br.com.ntconsult.service.process;

import br.com.ntconsult.service.constants.Processos;
import br.com.ntconsult.service.model.RelatorioModel;
import br.com.ntconsult.service.repository.LoteRepository;
import br.com.ntconsult.service.singleton.ListaLoteModel;
import br.com.ntconsult.service.singleton.RelatorioLotes;

/**
 * {@code LoteProcessor} processa um lote de arquivos
 * @author Fernando Bino Machado
 */
public class LoteProcessor implements Runnable {

	private Thread processo; 
	
	public LoteProcessor() {
		processo = new Thread(this, Processos.PRINCIPAL);
	}

	public LoteProcessor iniciar() {
		processo.start();
		return this;
	}
	
	public Thread getProcesso() {
		return processo;
	}
	
	@Override
	public void run() {
		
		try {
			
			if( verificaListaLoteModelBloqueada() ) {
				return;
			}
			
			bloquearListaLoteModel();
			
			LoteRepository.agruparLotes(); 
			
			if( !existemLotes() ) {
				desBloquearListaLoteModel();
				return;
			}
			
			new ArquivoLoteProcessor()
				.iniciar()
				.getProcesso()
				.join();
			
			if( !existemDadosRelatorio() ) {
				desBloquearListaLoteModel();
				return;
			}
			
			gerarRelatorio();
			
			desBloquearListaLoteModel();
			
		}catch(Exception error) {
			
			error.printStackTrace();
			
			desBloquearListaLoteModel();
			
		}
		
	}
	
	private void gerarRelatorio() {
		
		RelatorioLotes relatorioLotes = RelatorioLotes.getInstance();
		
		new RelatorioModel()
			.setConteudo(relatorioLotes.toString())
			.salvar();
		
		System.out.println( "\nRESULTADO:\n" );
		System.out.println( relatorioLotes.toString() );
		System.out.println("\n------------------------------------------------------\n");
		
		RelatorioLotes.getInstance().resetParciais();
		
	}
	
	private boolean verificaListaLoteModelBloqueada() {
		return ListaLoteModel.getInstance().isBloqueada();
	}
	
	private void desBloquearListaLoteModel() {
		ListaLoteModel.getInstance().setBloqueada(false);
	}
	
	private void bloquearListaLoteModel() {
		ListaLoteModel.getInstance().setBloqueada(true);
	}
	
	private boolean existemLotes() {
		return !ListaLoteModel.getInstance().getLotes().isEmpty();
	}
	
	private boolean existemDadosRelatorio() {
		
		RelatorioLotes relatorioLotes = RelatorioLotes.getInstance();
		
		return relatorioLotes.existemDados();
		
	}
	
	
}
