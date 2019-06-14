package util;

import java.lang.reflect.Field;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;

public class Auxiliar {

	public static boolean verificarSePodeCriarTabela(Object objeto) {
		Boolean retorno = false;
		if(objeto.getClass().isAnnotationPresent(Tabela.class)) {
			Tabela anotacao = objeto.getClass().getAnnotation(Tabela.class);
			if(anotacao.criarSeNaoExistir()){
				retorno = true;
			}
		}
		return retorno;
	}
	
	
	public static boolean possuiColuna(Field campo) {
		return campo.isAnnotationPresent(Coluna.class);
	}
	

	public static boolean possuiId(Field campo) {
		return campo.isAnnotationPresent(Id.class);
	}
	
	public static boolean possuiAI(Object objeto) {
		boolean retorno = false;
		Field[] campos = objeto.getClass().getDeclaredFields();
		for (Field campo : campos) {
			if(campo.isAnnotationPresent(Id.class)) {
				Id id = campo.getAnnotation(Id.class);
				if(id.autoIncrement()) {
					retorno = true;
				} 
			}
		}
		return retorno;
	}
	
}
