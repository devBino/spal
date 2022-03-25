package br.com.ntconsult.service.process;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.ntconsult.service.annotations.LayoutMethod;

import br.com.ntconsult.service.constants.DelimitadoresTexto;
import br.com.ntconsult.service.constants.Formatos;
import br.com.ntconsult.service.constants.Layouts;

import br.com.ntconsult.service.model.ClienteModel;
import br.com.ntconsult.service.model.VendaModel;
import br.com.ntconsult.service.model.VendedorModel;

import br.com.ntconsult.service.singleton.RelatorioLotes;
import br.com.ntconsult.service.util.RegexUtil;

/**
 * {@code LayoutProcessor} processa registro de
 * um arquivo de acordo com layout
 * @author Fernando Bino Machado
 */
@Service
public class LayoutProcessor {

	RelatorioLotes relatoriosLotes;
	
	public LayoutProcessor() {
		relatoriosLotes = RelatorioLotes.getInstance();
	}
	
	@LayoutMethod( tipo = Layouts.VENDEDOR )
	public void processaLayoutVendedor(String dados) {
		
		//seta dados na model e salva em arquivo do vendedor
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
	
	@LayoutMethod( tipo = Layouts.CLIENTE )
	public void processaLayoutCliente(String dados) {
		
		//seta dados na model e salva em arquivo do cliente
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
	
	@LayoutMethod( tipo = Layouts.VENDA )
	public void processaLayoutVenda(String dados) {
		
		//seta dados na model e salva em arquivo de venda
		String[] dadosModel = dados.split(DelimitadoresTexto.CEDILHA);
		
		VendaModel vendaModel = new VendaModel()
			.setId(Integer.parseInt(dadosModel[1]))
			.setNomeVendedor(dadosModel[3]);
		
		//salva itens da venda
		String dadosItem = RegexUtil.extrairTexto(dados, Formatos.REGEX_ITENS_VENDA)
				.replaceAll("[\\[\\]]", "");
		
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
