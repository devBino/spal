package br.com.ntconsult.service.process;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ntconsult.service.annotations.LayoutMethod;
import br.com.ntconsult.service.constants.DelimitadoresTexto;
import br.com.ntconsult.service.constants.Processos;
import br.com.ntconsult.service.singleton.ListaLoteModel;

import br.com.ntconsult.arquivo.odata.ArquivoData;

/**
 * {@code ArquivoLoteProcessor} processa arquivos em lotes
 * @author Fernando Bino Machado
 */
public class ArquivoLoteProcessor implements Runnable {

	@Autowired
	LayoutProcessor layoutProcessor;
	private Thread processo;
	
	public ArquivoLoteProcessor() {
		processo = new Thread(this, Processos.PROCESSAMENTO_LOTES);
	}
	
	public ArquivoLoteProcessor iniciar() {
		processo.start();
		return this;
	}
	
	public Thread getProcesso() {
		return processo;
	}
	
	@Override
	public void run() {
		
		try {
			
			ListaLoteModel.getInstance().getLotes().forEach(lote -> {
				
				lote.getListaArquivos().forEach(arquivo -> {
					
					ArquivoData file = new ArquivoData(arquivo.getNomeArquivo()
							.getCaminhoCompleto().toString());
					
					processarLayout(file);
					
					
				});
				
			});
			
			ListaLoteModel.getInstance().getLotes().clear();
			ListaLoteModel.getInstance().getNomes().clear();
			
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}
	
	public void processarLayout(ArquivoData file) {
		
		try {
			
			LayoutProcessor layout = new LayoutProcessor();
			
			for(String[] dadosRegistro : file.toListString()) {
			
				for(Method m : layout.getClass().getDeclaredMethods()) {
					
					LayoutMethod l = (LayoutMethod) m.getAnnotation(LayoutMethod.class);
					
					if( l.tipo().equals(dadosRegistro[0]) ) {
						m.invoke(layout, String.join(DelimitadoresTexto.CEDILHA, 
								dadosRegistro));
					}
					
				}
				
			}
			
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}
	
	
}
