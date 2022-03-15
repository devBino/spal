package br.com.ntconsult.service.model;

import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.service.constants.NomesArquivos;

public class ClienteModel extends AbstractModel {

	private String cnpj;
	private String nome;
	private String areaNegocio;
	
	public ClienteModel() {
		
		super(NomesArquivos.CLIENTE);
		setRecursoDataDir(RecursoDataDir.DADOS_SEPARADOS);
		
	}
	
	@Override
	public ClienteModel setId(int id) {
		super.setId(id);
		return this;
	}

	public String getCnpj() {
		return cnpj;
	}

	public ClienteModel setCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public ClienteModel setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getAreaNegocio() {
		return areaNegocio;
	}

	public ClienteModel setAreaNegocio(String areaNegocio) {
		this.areaNegocio = areaNegocio;
		return this;
	}
	
	@Override
	public String toString() {
		
		StringBuilder cliente = new StringBuilder()
			.append(id)
			.append(";")
			.append(cnpj)
			.append(";")
			.append(nome)
			.append(";")
			.append(areaNegocio)
			.append("\n");
		
		return cliente.toString();
	}
	
	@Override
	public ClienteModel salvar() {
		super.salvar();
		return this;
	}
	
}
