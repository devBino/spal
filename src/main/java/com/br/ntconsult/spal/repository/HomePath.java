package com.br.ntconsult.spal.repository;

import java.util.Date;

public enum HomePath {

	LINUX("Linux"){
		@Override
		public String getHomePathAndDataDir() {
			
			StringBuilder homePath = new StringBuilder();
			
			homePath.append( System.getProperty("user.home") );
			homePath.append( "/data/in/" );
			homePath.append(new Date().getTime());
			homePath.append("_");
			
			return homePath.toString();
		}
	},
	
	WINDOWS("Windows"){
		@Override
		public String getHomePathAndDataDir() {
			
			StringBuilder homePath = new StringBuilder();
			
			homePath.append( System.getProperty("user.home") );
			homePath.append("\\data\\in\\");
			homePath.append(new Date().getTime());
			homePath.append("_");
			
			return homePath.toString();
		}
	};
	
	private String osName;
	
	HomePath(String osName) {
		this.osName = osName;
	}
	
	public String getOs() {
		return osName;
	}
	
	public abstract String getHomePathAndDataDir();
	
}
