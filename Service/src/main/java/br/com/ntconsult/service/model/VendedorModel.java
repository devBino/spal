package br.com.ntconsult.service.model;

import java.math.BigDecimal;

import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.service.constants.NomesArquivos;

public class VendedorModel extends AbstractModel {

	private String cpf;
	private BigDecimal salario;
	private String nome;
	
	public VendedorModel() {
		super(NomesArquivos.VENDEDOR);
		setRecursoDataDir(RecursoDataDir.DADOS_SEPARADOS);
	}

	@Override
	public VendedorModel setId(int id) {
		super.setId(id);
		return this;
	}
	
	@Override
	public VendedorModel setNomeArquivo(String nomeArquivo) {
		super.setNomeArquivo(nomeArquivo);
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public VendedorModel setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public VendedorModel setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public VendedorModel setSalario(BigDecimal salario) {
		this.salario = salario;
		return this;
	}
	
	@Override
	public String toString() {
		
		StringBuilder vendedor = new StringBuilder()
			.append( String.valueOf(id) )
			.append(";")
			.append(cpf)
			.append(";")
			.append(nome)
			.append(";")
			.append(salario.toString())
			.append("\n");
		
		return vendedor.toString();
		
	}
	
	@Override
	public VendedorModel salvar() {
		super.salvar();
		return this;
	}
	
	
}
