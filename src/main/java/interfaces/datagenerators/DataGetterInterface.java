package interfaces.datagenerators;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;
import util.Auxiliar;
import util.DadosColunas;

public interface DataGetterInterface {

	public default String obterNomeTabela(Object objeto) {
		try {
			String nomeTabela;
			Class<?> classeObjeto = objeto.getClass();

			if (classeObjeto.isAnnotationPresent(Tabela.class)) {
				Tabela tabela = classeObjeto.getAnnotation(Tabela.class);
				nomeTabela = tabela.nome().equals("") ? classeObjeto.getSimpleName() : tabela.nome();
			} else {
				nomeTabela = classeObjeto.getSimpleName();
			}
			return nomeTabela;
		} catch (Exception e) {
			System.out.println("Erro ao obter nome da Tabela!");
			e.printStackTrace();
			return null;
		}
	}

	public default Map<String, String> obterDadosDeObjeto(Object objeto) {
		try {
			Map<String, String> dados = new HashMap<String, String>();
			Class<?> classeObjeto = objeto.getClass();
			Field[] campos = classeObjeto.getDeclaredFields();
			for (Field campo : campos) {
				campo.setAccessible(true);
				StringBuilder nome = new StringBuilder();
				StringBuilder valor = new StringBuilder();
				Object _valor;
				if (Auxiliar.possuiColuna(campo)) {
					Coluna coluna = campo.getAnnotation(Coluna.class);
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
			System.out.println("Erro ao obter dados da tabela!");
			e.printStackTrace();
			return null;
		}
	}

	public default Map<String, String> obterDadosDeTabela(Object objeto){
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
}
