package br.com.ntconsult.service.constants;

/**
 * {@code StatusProcesso} fornece valores
 * para identificação de status de processamento
 * dos arquivos
 * @author Fernando Bino Machado
 */
public class StatusProcesso {

	public static final String PROCESSANDO = "Processando";
	public static final String MOTIVO_PROCESSANDO = "Iniciado pelo Serviço de Processamento de Arquivos em Lote";
	
	public static final String PROCESSADO_ERRO = "Processado com Erro" ;
	public static final String MOTIVO_PROCESSADO_ERRO = "Ocorreu um erro durante o processamento...";
	
	public static final String PROCESSADO_SUCESSO = "Processado com Sucesso";
	public static final String MOTIVO_PROCESSADO_SUCESSO = "Finalizado pelo Serviço de Processamento de Arquivos em Lote";
	
}
