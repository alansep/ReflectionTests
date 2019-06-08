package services;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;
import interfaces.DataGetterInterface;
import util.DadosColunas;

public class DefaultDataGetterService<Tipo> implements DataGetterInterface<Tipo> {

	@Override
	public String obterNomeTabela(Tipo objeto) {
		String nomeTabela;
		Class<?> classeObjeto = objeto.getClass();

		if (classeObjeto.isAnnotationPresent(Tabela.class)) {
			Tabela tabela = classeObjeto.getAnnotation(Tabela.class);
			nomeTabela = tabela.nome();
		} else {
			nomeTabela = classeObjeto.getSimpleName();
		}
		return nomeTabela;
	}

	@Override
	public Map<String, String> obterDadosDeObjeto(Tipo objeto) {
		Map<String, String> dados = new HashMap<String, String>();
		Class<?> classeObjeto = objeto.getClass();
		try {
			Field[] campos = classeObjeto.getDeclaredFields();
			for (Field campo : campos) {
				String nomeColuna;
				String valorColuna;
				campo.setAccessible(true);
				if (campo.isAnnotationPresent(Coluna.class)) {
					Coluna coluna = campo.getAnnotation(Coluna.class);
					nomeColuna = coluna.nome();
				} else {
					nomeColuna = campo.getName();
				}
				Object objetoDeInformacao = (Object) campo.get(objeto);
				valorColuna = (String) objetoDeInformacao;
				if (campo.isAnnotationPresent(Id.class)) {
					Id id = campo.getAnnotation(Id.class);
					if (!id.autoIncrement()) {
						dados.put(nomeColuna, valorColuna);
					}
				} else {
					dados.put(nomeColuna, valorColuna);
				}
			}

			return dados;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<String, String> obterDadosDeTabela(Tipo objeto){
		Map<String, String> dados = new HashMap<String, String>();
		Class<?> classe = objeto.getClass();
		Field[] campos = classe.getDeclaredFields();
		for(Field field: campos) {
			if(field.isAnnotationPresent(Coluna.class)) {
				String extra = "";
				if(field.isAnnotationPresent(Id.class)) {
					Id id = field.getAnnotation(Id.class);
					if(id.primaryKey()) {
						extra += " PRIMARY KEY";
					}
					if(id.autoIncrement()) {
						extra += " AUTO_INCREMENT";
					}
				}
				Coluna coluna = field.getAnnotation(Coluna.class);
				
				if(coluna.notNull()) {
					extra += " NOT NULL";
				}
				dados.put(coluna.nome(), coluna.tipo() + extra);
			} else {
				dados.put(field.getName(), DadosColunas.getTipo(field.getType()));
			}
		}
		return dados;
	}
}
