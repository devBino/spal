package br.com.ntconsult.service.model;

import java.util.List;

import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.service.constants.NomesArquivos;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Modelo de dados para vendas
 * @author Fernando Bino Machado
 */
public class VendaModel extends AbstractModel {

	private String nomeVendedor;
	private List<ItemModel> items;
	private BigDecimal valorTotalVenda;
	
	public VendaModel() {
		
		super(NomesArquivos.VENDAS);
		setRecursoDataDir(RecursoDataDir.DADOS_SEPARADOS);
		
		items = new ArrayList<ItemModel>();
		valorTotalVenda = new BigDecimal("0.00");
		
	}

	@Override
	public VendaModel setId(int id) {
		super.setId(id);
		return this;
	}
	
	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public VendaModel setNomeVendedor(String nome) {
		this.nomeVendedor = nome;
		return this;
	}
	
	public BigDecimal getValorTotalVenda() {
		return valorTotalVenda;
	}
	
	public VendaModel addItem(String dadosItem) {
		this.items.add(new ItemModel(dadosItem));
		return this;
	}
	
	public VendaModel somarValorTotalVenda() {
		
		double total = 0.00;
		
		for(ItemModel item : this.items) {
			total += item.getValorTotalItem().doubleValue();
		}
		
		this.valorTotalVenda = new BigDecimal(String.valueOf(total));
		
		return this;
	}

	@Override
	public String toString() {

		StringBuilder venda = new StringBuilder()
			.append( String.valueOf(id) )
			.append(";")
			.append(nomeVendedor)
			.append(";")
			.append("[");

		for(int i=0; i<items.size(); i++) {
			
			venda.append(items.get(i).toString());
			
			if( (i+1) < items.size() ) {
				venda.append(",");
			}
			
		}
		
		venda.append("]")
			.append(";")
			.append(valorTotalVenda.toString())
			.append("\n");
		
		
		
		return venda.toString();
		
	}
	
	@Override
	public VendaModel salvar() {
		super.salvar();
		return this;
	}
	
}
