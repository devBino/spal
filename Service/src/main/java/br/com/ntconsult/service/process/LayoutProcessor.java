package br.com.ntconsult.service.process;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.ntconsult.service.annotations.LayoutMethod;
import br.com.ntconsult.service.constants.DelimitadoresTexto;
import br.com.ntconsult.service.model.ClienteModel;
import br.com.ntconsult.service.model.VendaModel;
import br.com.ntconsult.service.model.VendedorModel;
import br.com.ntconsult.service.singleton.RelatorioLotes;

@Service
public class LayoutProcessor {

	RelatorioLotes relatoriosLotes;
	
	public LayoutProcessor() {
		relatoriosLotes = RelatorioLotes.getInstance();
	}
	
	@LayoutMethod( tipo = "001" )
	public void processaLayoutVendedor(String dados) {
		
		//seta dados na model e salva dados em arquivos separados
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		new VendedorModel()
			.setId(Integer.parseInt(dadosModel[0]))
			.setCpf(dadosModel[1])
			.setNome(dadosModel[2])
			.setSalario(new BigDecimal(dadosModel[3]))
			.salvar();
		
		//prepara dados para relatórios
		relatoriosLotes.setQuantidadeVendedores(
			relatoriosLotes.getQuantidadeVendedores() + 1
		);
		
	}
	
	@LayoutMethod( tipo = "002" )
	public void processaLayoutCliente(String dados) {
		
		//seta dados na model e salva dados em arquivos separados
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		new ClienteModel()
			.setId(Integer.parseInt(dadosModel[0]))
			.setCnpj(dadosModel[1])
			.setNome(dadosModel[2])
			.setAreaNegocio(dadosModel[3])
			.salvar();
		
		//prepara dados para relatórios
		relatoriosLotes.setQuantidadeClientes(
			relatoriosLotes.getQuantidadeClientes() + 1
		);
		
	}
	
	@LayoutMethod( tipo = "003" )
	public void processaLayoutVenda(String dados) {
		
		//seta dados na model e salva dados em arquivos separados
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		VendaModel vendaModel = new VendaModel()
			.setId(Integer.parseInt(dadosModel[1]))
			.setNomeVendedor(dadosModel[3]);
		
		//salva itens da venda
		int indexInicio = dados.indexOf('[');
		int indexFinal = dados.indexOf(']');
		
		String dadosItem = dados.substring(indexInicio+1, indexFinal); 
		
		for(String item : dadosItem.split(DelimitadoresTexto.VIRGULA)) {
			vendaModel.addItem(item);
		}
		
		vendaModel
			.somarValorTotalVenda()
			.salvar();
		
		//prepara dados para relatórios
		if( relatoriosLotes.getMaiorValorVenda() < vendaModel.getValorTotalVenda().doubleValue() ) {
			
			relatoriosLotes.setMaiorValorVenda( vendaModel.getValorTotalVenda().doubleValue() );
			relatoriosLotes.setIdVendaMaisCara( vendaModel.getId() );
			
		}
		
		if( relatoriosLotes.getMenorValorVenda() > vendaModel.getValorTotalVenda().doubleValue() ) {
		
			relatoriosLotes.setMenorValorVenda( vendaModel.getValorTotalVenda().doubleValue() );
			relatoriosLotes.setPiorVendedor( vendaModel.getNomeVendedor() );
			
		}
		
	}
	
}
