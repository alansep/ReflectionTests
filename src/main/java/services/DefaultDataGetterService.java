package services;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;
import interfaces.datagenerators.DataGetterInterface;
import util.Auxiliar;
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
				campo.setAccessible(true);
				StringBuilder nome = new StringBuilder();
				StringBuilder valor = new StringBuilder();
				Object _valor;
				if (Auxiliar.possuiColuna(campo)) {
					Coluna coluna = campo.getAnnotation(Coluna.class);
					Id id = campo.getAnnotation(Id.class);
					if (!coluna.nome().equals("")) {
						nome.append(coluna.nome());
					} else {
						nome.append(campo.getName());
					}
				} else {
					nome.append(campo.getName());
				}
				_valor = campo.get(objeto);
				valor.append(String.valueOf(_valor));
				dados.put(nome.toString(), valor.toString());
			}

			return dados;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<String, String> obterDadosDeTabela(Tipo objeto) {
		Map<String, String> dados = new HashMap<String, String>();
		Class<?> classe = objeto.getClass();
		Field[] campos = classe.getDeclaredFields();
		for (Field field : campos) {
			StringBuilder nome = new StringBuilder();
			StringBuilder tipo = new StringBuilder();
			if (Auxiliar.possuiColuna(field) && Auxiliar.possuiId(field)) {
				Coluna coluna = field.getAnnotation(Coluna.class);
				Id id = field.getAnnotation(Id.class);
				tipo.append("integer");

				if (!coluna.nome().equals("")) {
					nome.append(coluna.nome());
				} else {
					nome.append(field.getName());
				}

				if (id.primaryKey()) {
					tipo.append(" primary key");
				}
				if (id.autoIncrement()) {
					tipo.append(" auto_increment");
				}
				if (coluna.notNull()) {
					tipo.append(" not null");
				}

			} else if (!Auxiliar.possuiColuna(field) && Auxiliar.possuiId(field)) {
				Id id = field.getAnnotation(Id.class);
				nome.append(field.getName());
				tipo.append("integer ");

				if (id.primaryKey()) {
					tipo.append(" primary key");
				}
				if (id.autoIncrement()) {
					tipo.append(" auto_increment");
				}
			} else if (Auxiliar.possuiColuna(field) && !Auxiliar.possuiId(field)) {
				Coluna coluna = field.getAnnotation(Coluna.class);

				if (!coluna.nome().equals("")) {
					nome.append(coluna.nome());
				} else {
					nome.append(field.getName());
				}
				if (coluna.tipo().equals("")) {
					tipo.append(DadosColunas.getTipo(field.getType()));
				} else {
					tipo.append(coluna.tipo());
				}
				if (coluna.notNull()) {
					tipo.append(" not null");
				}

			} else {
				nome.append(field.getName());
				tipo.append(DadosColunas.getTipo(field.getType()));
			}
			dados.put(nome.toString(), tipo.toString());
		}
		return dados;
	}

	@Override
	public Map<String, String> obterDadosDeColuna(Field campo) {
		// TODO Auto-generated method stub
		return null;
	}

}
