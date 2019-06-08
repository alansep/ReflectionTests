package util;

import annotation.Tabela;

public class Auxiliar {

	public static boolean criarTabela(Object objeto) {
		Boolean retorno = false;
		if(objeto.getClass().isAnnotationPresent(Tabela.class)) {
			Tabela anotacao = objeto.getClass().getAnnotation(Tabela.class);
			if(anotacao.criarSeNaoExistir()){
				retorno = true;
			}
		}
		return retorno;
	}
	
	
}
