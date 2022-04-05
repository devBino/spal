package br.com.ntconsult.spal.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ntconsult.spal.repository.HistoricoRepository;
import br.com.ntconsult.spal.request.ArquivoRequest;

/**
 * {@code LoteBusiness} faz validações de regras negociais
 * ao enviar/processar lotes de arquivos.
 * 
 * @author Fernando Bino Machado
 */
@Service
public class LoteBusiness extends GenericBusiness {
	
	@Autowired
	HistoricoRepository historicoRepository;
	
	public LoteBusiness() {
		super();
	}
	
	public void validaLoteBusiness(MultipartFile file) {
		
		if( !extensaoValida(file.getOriginalFilename()) ) {
			
			addMensagem(new StringBuilder()
				.append("Arquivo \"")
				.append(file.getOriginalFilename())
				.append("\" deve possuir extensão \".dat\"")
				.toString());
			
		}
		
	}
	
	public void validaDescricaoLote(String descricaoLote) {
		
		if( !descricaoLote.matches("Lote{1}_{1}[0-9]{1,6}") ) {
			
			addMensagem(new StringBuilder()
				.append("Descrição \"")
				.append(descricaoLote)
				.append("\" fora do padrão. Por favor, ")
				.append("siga o formato: Lote{1}_{1}[0-9]{1,6} ")
				.append("Exemplo: Lote_999999")
				.toString());
			
		}
		
	}
	
	public void verificaHistoricoLote(ArquivoRequest arquivo) {
		
		if( historicoRepository.existeHistoricoLote( arquivo.getNomeArquivo() ) ) {
			
			addMensagem(new StringBuilder()
					.append("Não é possível deletar o lote, ")
					.append("pois existem históricos para o lote selecionado...")
					.toString());
			
		}
		
	}
	
	public boolean extensaoValida(String nomeArquivo) {
		return nomeArquivo.endsWith(".dat");
	}
	
}