package interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import components.generators.DefaultInsertGenerator;
import interfaces.crudgenerators.InsertQueryGenerator;
import services.DefaultDataGetterService;
import util.Auxiliar;

public abstract class DaoComponent<Tipo> implements ObjectDao<Tipo> {

	private DataGetterInterface<Tipo> dataGetter = new DefaultDataGetterService<Tipo>();
	private InsertQueryGenerator<Tipo> insertGenerator = new DefaultInsertGenerator<Tipo>();
	private Connection conexao;
	private Statement statement;

	public DaoComponent(Connection conexao) {
		this.conexao = conexao;
	}

	public void insert(Tipo objeto) {
		try {
			statement = conexao.createStatement();
			statement.execute(insertGenerator.gerarInsert(dataGetter.obterNomeTabela(objeto).toUpperCase(),
					dataGetter.obterDadosDeObjeto(objeto)));
		} catch (Exception e) {
			if(Auxiliar.criarTabela(objeto)) {
				for(String palavra : e.getMessage().split(" ")) {
					System.err.println(palavra);
				}
				Map<String, String> dados = new DefaultDataGetterService<Tipo>().obterDadosDeTabela(objeto);
				try {
					System.err.println("Criando Tabela!\n");
					System.out.println(this.gerarCreateTable(objeto, dados));
					statement.execute(this.gerarCreateTable(objeto, dados));
					this.insert(objeto);
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.err.println(e1.getMessage());
				}
			}
		}
	}

}
