package br.com.ntconsult.service.process;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.ntconsult.service.annotations.LayoutMethod;
import br.com.ntconsult.service.constants.DelimitadoresTexto;
import br.com.ntconsult.service.model.ClienteModel;
import br.com.ntconsult.service.model.VendaModel;
import br.com.ntconsult.service.model.VendedorModel;

@Service
public class LayoutProcessor {

	@LayoutMethod( tipo = "001" )
	public void processaLayoutVendedor(String dados) {
		
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		VendedorModel vendedorModel = new VendedorModel()
			.setId(Integer.parseInt(dadosModel[0]))
			.setCpf(dadosModel[1])
			.setNome(dadosModel[2])
			.setSalario(new BigDecimal(dadosModel[3]))
			.salvar();
		
	}
	
	@LayoutMethod( tipo = "002" )
	public void processaLayoutCliente(String dados) {
		
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		ClienteModel clienteModel = new ClienteModel()
			.setId(Integer.parseInt(dadosModel[0]))
			.setCnpj(dadosModel[1])
			.setNome(dadosModel[2])
			.setAreaNegocio(dadosModel[3])
			.salvar();
		
	}
	
	@LayoutMethod( tipo = "003" )
	public void processaLayoutVenda(String dados) {
		
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		VendaModel vendaModel = new VendaModel()
			.setId(Integer.parseInt(dadosModel[1]))
			.setNomeVendedor(dadosModel[3]);
		
		int indexIni = dados.indexOf('[');
		int indexFim = dados.indexOf(']');
		
		String dadosItem = dados.substring(indexIni+1, indexFim); 
		
		for(String item : dadosItem.split(DelimitadoresTexto.VIRGULA)) {
			vendaModel.addItem(item);
		}
		
		vendaModel.somarValorTotalVenda()
			.salvar();
		
	}
	
}
