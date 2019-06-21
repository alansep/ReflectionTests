package components;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import components.generators.DefaultInsertGenerator;
import interfaces.datagenerators.DataGetterInterface;
import interfaces.datagenerators.ObjectDao;
import util.Auxiliar;

public class DaoComponent<Tipo> implements ObjectDao<Tipo>, DataGetterInterface {

	private DefaultInsertGenerator<Tipo> insertGenerator = new DefaultInsertGenerator<Tipo>();
	private Connection conexao;
	private Statement statement;

	public DaoComponent(Connection conexao) {
		this.conexao = conexao;
	}

	public void insert(Tipo objeto) {
		try {
			statement = conexao.createStatement();
			System.out.println(Auxiliar.possuiAI(objeto));
			if (Auxiliar.possuiAI(objeto)) {
				statement.execute(insertGenerator.gerarInsertAutoIncrement(
						this.obterNomeTabela(objeto).toUpperCase(), this.obterDadosDeObjeto(objeto)));
			} else {
				statement.execute(insertGenerator.gerarInsert(this.obterNomeTabela(objeto).toUpperCase(),
						this.obterDadosDeObjeto(objeto)));
			}
			System.out.println("A Informação foi inserida com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			for (String palavra : e.getMessage().split(" ")) {
				System.err.println(palavra);
			}

			String[] erro =e.getMessage().split(" ");
			List<String> lista = new ArrayList<String>();
			for (String palavra : erro) {
				lista.add(palavra);
			}
			if (lista.contains("exist")) {

				if (Auxiliar.verificarSePodeCriarTabela(objeto)) {
					for (String palavra : e.getMessage().split(" ")) {
						System.err.println(palavra);
					}
					Map<String, String> dados = this.obterDadosDeTabela(objeto);
					try {
						System.err.println("Criando Tabela!\n");
						System.out.println(this.gerarCreateTable(objeto, dados));
						statement.execute(this.gerarCreateTable(objeto, dados));
						this.insert(objeto);
					} catch (SQLException e1) {
						e1.printStackTrace();
						System.out.println("Erro ao criar tabela");
					}
				}
			}

		}
	}

}
