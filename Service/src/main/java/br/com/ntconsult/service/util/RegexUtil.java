package br.com.ntconsult.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code RegexUtil} fornece métodos uteis
 * para tratativas de expressões regulares
 * @author Fernando Bino Machado
 */
public class RegexUtil {

	public static String extrairTexto(String texto, String regex) {
	
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(texto);
		
		if( matcher.find() ) {
			return matcher.group();
		}
		
		return "";
		
	}
	
}
