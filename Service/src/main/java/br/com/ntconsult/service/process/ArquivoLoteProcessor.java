package br.com.ntconsult.service.process;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ntconsult.service.annotations.LayoutMethod;
import br.com.ntconsult.service.constants.DelimitadoresTexto;
import br.com.ntconsult.service.constants.Processos;
import br.com.ntconsult.service.odata.ArquivoData;
import br.com.ntconsult.service.singleton.ListaLoteModel;

public class ArquivoLoteProcessor implements Runnable {

	@Autowired
	LayoutProcessor layoutProcessor;
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
			
			ListaLoteModel.getInstance().getLotes().forEach(lote -> {
				
				lote.getListaArquivos().forEach(arquivo -> {
					
					ArquivoData file = new ArquivoData(arquivo.getNomeArquivo()
							.getCaminhoCompleto().toString());
					
					processarLayout(file);
					
					
				});
				
			});
			
			ListaLoteModel.getInstance().getLotes().clear();
			ListaLoteModel.getInstance().getNomes().clear();
			
			resume();
			
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}
	
	public synchronized void processarLayout(ArquivoData file) {
		
		try {
			
			LayoutProcessor layout = new LayoutProcessor();
			
			Method[] methods = layout.getClass().getDeclaredMethods();
			
			ArrayList<String[]> registros = file.toListString();
			
			for(String[] dadosRegistro : registros) {
			
				for(Method m : methods) {
					
					LayoutMethod l = (LayoutMethod) m.getAnnotation(LayoutMethod.class);
					
					if( l.tipo().equals(dadosRegistro[0]) ) {
						m.invoke(layout, String.join(DelimitadoresTexto.CEDILHA, 
								dadosRegistro));
					}
					
				}
				
			}
			
		}catch(Exception error) {
			System.out.println(error.getMessage());
		}
		
	}
	
	public synchronized void resume() {
		notify();
	}
	
	
}
