package br.com.ntconsult.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code LayoutMethod} é utilizado para identificar
 * em tempo de execuçao, qual leiaute deverá ser processado
 * @author Fernando Bino Machado
 * @see br.com.ntconsult.service.process.LayoutProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LayoutMethod {

	String tipo() default "001";
	
}