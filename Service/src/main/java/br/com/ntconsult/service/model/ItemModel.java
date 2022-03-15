package br.com.ntconsult.service.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.ntconsult.service.constants.DelimitadoresTexto;

public class ItemModel {

	private int id;
	private BigInteger quantidade;
	private BigDecimal preco;
	private BigDecimal valorToralItem;
	
	public ItemModel(String dados) {
		
		super();
		
		String[] dadosItem = dados.split(DelimitadoresTexto.TRACO);
		
		this.id = Integer.parseInt(dadosItem[0]);
		this.quantidade = new BigInteger(dadosItem[1]);
		this.preco = new BigDecimal(dadosItem[2]);
		
		this.valorToralItem = this.preco.multiply(
			new BigDecimal(String.valueOf(this.quantidade.doubleValue())) 
		);
		
	}
	
	public ItemModel(int id, int quantidade, BigInteger preco) {
		
		super();
		
		this.id = id;
		this.quantidade = new BigInteger(String.valueOf(quantidade));
		this.preco = new BigDecimal(String.valueOf(preco));
		
		this.valorToralItem = this.preco.multiply(
			new BigDecimal(String.valueOf(this.quantidade.doubleValue())) 
		);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = new BigInteger(String.valueOf(quantidade));
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public BigDecimal getValorTotalItem() {
		return this.valorToralItem;
	}

	public void setPreco(double preco) {
		this.preco = new BigDecimal(String.valueOf(preco));
	}
	
	@Override
	public String toString() {
		
		StringBuilder item = new StringBuilder()
			.append(id)
			.append("-")
			.append(quantidade.toString())
			.append("-")
			.append(preco.toString())
			.append("-")
			.append(valorToralItem.toString());
		
		return item.toString();
		
	}
	
}
