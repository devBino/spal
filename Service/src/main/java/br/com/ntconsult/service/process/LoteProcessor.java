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
	
	public boolean existemLotes() {
		return !ListaLoteModel.getInstance().getLotes().isEmpty();
	}
	
	public boolean existemDadosRelatorio() {
		
		RelatorioLotes relatorioLotes = RelatorioLotes.getInstance();
		
		return relatorioLotes.existemDados();
		
	}
	
	@Override
	public void run() {
		
		try {
			
			LoteRepository.agruparLotes(); 
			
			if( !existemLotes() ) {
				return;
			}
			
			new ArquivoLoteProcessor()
				.iniciar()
				.getProcesso()
				.join();
			
			if( !existemDadosRelatorio() ) {
				return;
			}
			
			gerarRelatorio();
			
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}
	
	public void gerarRelatorio() {
		
		RelatorioLotes relatorioLotes = RelatorioLotes.getInstance();
		
		new RelatorioModel()
			.setConteudo(relatorioLotes.toString())
			.salvar();
		
		System.out.println( "\nRESULTADO:\n" );
		System.out.println( relatorioLotes.toString() );
		System.out.println("\n------------------------------------------------------\n");
		
		RelatorioLotes.getInstance().resetParciais();
		
	}
	
	
}
