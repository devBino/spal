package br.com.ntconsult.service.singleton;

public class RelatorioLotes {

	private int quantidadeClientes;
	private int quantidadeVendedores;
	private int idVendaMaisCara;
	
	private double maiorValorVenda; 
	private double menorValorVenda;
	
	private String piorVendedor;
	
	private static RelatorioLotes relatorio;
	
	private RelatorioLotes() {
		menorValorVenda = 9999999999999.99;
		piorVendedor = "";
	}
	
	public static RelatorioLotes getInstance() {
		
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

	public double getMaiorValorVenda() {
		return maiorValorVenda;
	}

	public void setMaiorValorVenda(double maiorValorVenda) {
		this.maiorValorVenda = maiorValorVenda;
	}

	public double getMenorValorVenda() {
		return menorValorVenda;
	}

	public void setMenorValorVenda(double menorValorVenda) {
		this.menorValorVenda = menorValorVenda;
	}

	public String getPiorVendedor() {
		return piorVendedor;
	}

	public void setPiorVendedor(String piorVendedor) {
		this.piorVendedor = piorVendedor;
	}

	public void resetParciais() {
		
		quantidadeClientes = 0;
		quantidadeVendedores = 0;
		idVendaMaisCara = 0;
		maiorValorVenda = 0.00;
		menorValorVenda = 9999999999999.99;
		piorVendedor = "";
		
	}
	
	@Override
	public String toString() {

		StringBuilder relatorio = new StringBuilder()
			.append("Clientes nos Lotes Processados: ")
			.append(String.valueOf(this.quantidadeClientes))
			.append("\n")
			.append("Vendedores nos Lotes Processados: ")
			.append(String.valueOf(this.quantidadeVendedores))
			.append("\n")
			.append("ID da Venda mais cara: ")
			.append(String.valueOf(this.idVendaMaisCara))
			.append("\n")
			.append("Vendedor com menor valor em vendas: ")
			.append(this.piorVendedor)
			.append("\n");
		
		return relatorio.toString();
		
	}
	
	public boolean existemDados() {
		
		if( quantidadeClientes == 0 && quantidadeVendedores == 0
				&& idVendaMaisCara == 0 && piorVendedor.isEmpty() ) {
			return false;
		}
		
		return true;
		
	}
	
	
}
