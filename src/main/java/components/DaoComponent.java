package components;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;
import database.Banco;
import entities.ChaveValor;
import interfaces.ObjectDao;

public class DaoComponent<Tipo> implements ObjectDao<Tipo> {

	public Connection conexao;
	public Statement statement;

	public DaoComponent(Connection conexao) {
		this.conexao = conexao;
		try {
			this.statement = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Tipo> findAll() {
		return null;
	}

	public void insert(Tipo objeto) {
		String tableName;
		List<String> columns = new ArrayList<String>();
		List<String> valores = new ArrayList<String>();
		Class<?> classeObjeto = objeto.getClass();
		try {
			if (classeObjeto.isAnnotationPresent(Tabela.class)) {
				Tabela tabela = classeObjeto.getAnnotation(Tabela.class);
				tableName = tabela.nome();
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
							columns.add(nomeColuna);
							valores.add(valorColuna);
						}
					} else {
						columns.add(nomeColuna);
						valores.add(valorColuna);
					}
				}
				realizarOperacaoDeInsercao(tableName, columns, valores);
			}
		} catch (SQLSyntaxErrorException sqlError) {
			System.out.println(sqlError.getMessage());
			System.out.println("Criando Tabela");
			String[] error = sqlError.getMessage().split(" ");
			if (error[error.length - 1].equals("exist") && error[error.length - 2].equals("doesn't")) {
				if (classeObjeto.getAnnotation(Tabela.class).criarSeNaoExistir()) {
					try {
						this.createTable(this.gerarScriptCreateTable(objeto));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.insert(objeto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private void realizarOperacaoDeInsercao(String tableName, List<String> columns, List<String> valores)
			throws SQLException {
		String query = gerarInsertQuery(tableName, columns, valores);
		statement.execute(query);
	}

	private String gerarInsertQuery(String tableName, List<String> columns, List<String> valores) {
		StringBuilder query = new StringBuilder();
		query.append("insert into " + tableName + "(");
		for (int i = 0; i < columns.size() - 1; i++) {
			query.append(columns.get(i) + ",");
		}
		query.append(columns.get(columns.size() - 1));
		query.append(") values (");
		for (int i = 0; i < valores.size() - 1; i++) {
			query.append("'" + valores.get(i) + "',");
		}
		query.append("'" + valores.get(valores.size() - 1) + "')");
		return query.toString();
	}

	public String gerarScriptCreateTable(Tipo objeto) {
		StringBuilder sb = new StringBuilder();
		String nomeTabela = "";
		List<ChaveValor> colunaTipo = new ArrayList<ChaveValor>();
		Class<?> classe = objeto.getClass();
		Tabela tabela = classe.getAnnotation(Tabela.class);
		nomeTabela = tabela.nome();
		for (Field campo : classe.getDeclaredFields()) {
			String nomeColuna = "";
			String tipoASerConstruido = "";
			campo.setAccessible(true);
			Coluna coluna = campo.getAnnotation(Coluna.class);
			nomeColuna = coluna.nome();
			tipoASerConstruido += coluna.tipo();
			if (campo.isAnnotationPresent(Id.class)) {
				Id id = campo.getAnnotation(Id.class);
				if (id.primaryKey()) {
					tipoASerConstruido += " primary key";
					if (id.autoIncrement()) {
						tipoASerConstruido += " auto_increment";
					}
				}
			}
			if (coluna.notNull()) {
				tipoASerConstruido += " not null";
			}
			colunaTipo.add(new ChaveValor(nomeColuna, tipoASerConstruido.toString()));
		}
		sb.append("CREATE TABLE " + nomeTabela + " (");
		for (int i = 0; i < colunaTipo.size() - 1; i++) {
			sb.append(colunaTipo.get(i).getColuna() + " " + colunaTipo.get(i).getTipo() + ", ");
		}
		sb.append(colunaTipo.get(colunaTipo.size() - 1).getColuna() + " "
				+ colunaTipo.get(colunaTipo.size() - 1).getTipo() + ")");
		return sb.toString();
	}

	public void createTable(String query) throws SQLException {
		statement.execute(query);
	}

}
