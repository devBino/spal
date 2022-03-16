package br.com.ntconsult.arquivo.repository;

/**
 * {@code HomePath} De acordo com o sistema operacional,
 * identifica o homePath, e monta o caminho do diretório 
 * padrão para armazenar os arquivos
 * @author Fernando Bino Machado
 */
public enum HomePath {

	LINUX("Linux"){
		
		@Override
		public String getHomePathAndDataDir(String recurso) {
			
			StringBuilder homePathDir = new StringBuilder();
			
			homePathDir.append( System.getProperty("user.home") );
			homePathDir.append( "/data/" );
			homePathDir.append(recurso);
			homePathDir.append("/");
			
			return homePathDir.toString();
		}
		
	},
	
	WINDOWS("Windows"){
		
		@Override
		public String getHomePathAndDataDir(String recurso) {
			
			StringBuilder homePathDir = new StringBuilder();
			
			homePathDir.append( System.getProperty("user.home") );
			homePathDir.append("\\data\\");
			homePathDir.append(recurso);
			homePathDir.append("\\");
			
			return homePathDir.toString();
		}
		
	};
	
	private String osName;
	
	HomePath(String osName) {
		this.osName = osName;
	}
	
	public String getOs() {
		return osName;
	}
	
	public abstract String getHomePathAndDataDir(String recurso);
	
}
