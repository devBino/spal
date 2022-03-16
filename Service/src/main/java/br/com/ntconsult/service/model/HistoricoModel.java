package br.com.ntconsult.service.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.ntconsult.arquivo.constants.RecursoDataDir;
import br.com.ntconsult.arquivo.repository.HomePath;
import br.com.ntconsult.service.constants.NomesArquivos;
import br.com.ntconsult.service.constants.StatusProcesso;

/**
 * Modelo de dados para historico
 * @author Fernando Bino Machado
 */
public class HistoricoModel extends AbstractModel {

	private String lote;
	private Calendar dataInicio;
	private Calendar dataFim;
	private String status;
	private String motivoStatus;
	
	public HistoricoModel(String lote) {
		
		super(NomesArquivos.HISTORICO_LOTE);
		setRecursoDataDir(RecursoDataDir.HISTORICO_LOTE);
		
		this.lote = lote;
		this.dataInicio = new GregorianCalendar();
		this.status = StatusProcesso.PROCESSANDO;
		this.motivoStatus = StatusProcesso.MOTIVO_PROCESSANDO;
		
		this.homePath = (HomePath) Enum.valueOf(HomePath.class, 
				System.getProperty("os.name").toUpperCase());
		
	}

	public String getLote() {
		return lote;
	}

	public HistoricoModel setLote(String lote) {
		this.lote = lote;
		return this;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public HistoricoModel setDataInicio(GregorianCalendar dataInicio) {
		this.dataInicio = dataInicio;
		return this;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public HistoricoModel setDataFim(GregorianCalendar dataFim) {
		this.dataFim = dataFim;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public HistoricoModel setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getMotivoStatus() {
		return motivoStatus;
	}

	public HistoricoModel setMotivoStatus(String motivoStatus) {
		this.motivoStatus = motivoStatus;
		return this;
	}
	
	@Override
	public String toString() {
		
		String formatoData = "dd-MM-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatoData);
		
		StringBuilder historico = new StringBuilder();
		
		historico.append(lote);
		historico.append(";");
		
		historico.append(simpleDateFormat.format( dataInicio.getTime() ));
		historico.append(";");
		
		if( dataFim == null ) {
			historico.append(" - ;");
		}else {
			historico.append(simpleDateFormat.format( dataInicio.getTime() ));
			historico.append(";");
		}
		
		historico.append(status);
		historico.append(";");
		
		historico.append(motivoStatus);
		historico.append("\n");
		
		return historico.toString();
		
	}
	
	@Override
	public HistoricoModel salvar() {
		super.salvar();
		return this;
	}
	
	
}
