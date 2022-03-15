package br.com.ntconsult.service.singleton;


public class RelatorioLotes {

	private int quantidadeClientes;
	private int quantidadeVendedores;
	private int idVendaMaisCara;
	private int idPiorVendedor;
	
	private RelatorioLotes relatorio;
	
	private RelatorioLotes() {
		quantidadeClientes = 0;
		quantidadeVendedores = 0;
		idVendaMaisCara = 0;
		idPiorVendedor = 0;
	}
	
	public RelatorioLotes getInstance() {
		
		if( relatorio == null ) {
			relatorio = new RelatorioLotes();
		}
		
		return relatorio;
		
	}

	public int getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(int quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public int getQuantidadeVendedores() {
		return quantidadeVendedores;
	}

	public void setQuantidadeVendedores(int quantidadeVendedores) {
		this.quantidadeVendedores = quantidadeVendedores;
	}

	public int getIdVendaMaisCara() {
		return idVendaMaisCara;
	}

	public void setIdVendaMaisCara(int idVendaMaisCara) {
		this.idVendaMaisCara = idVendaMaisCara;
	}

	public int getIdPiorVendedor() {
		return idPiorVendedor;
	}

	public void setIdPiorVendedor(int idPiorVendedor) {
		this.idPiorVendedor = idPiorVendedor;
	}
	
	
	
}
