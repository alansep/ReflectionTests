package interfaces;

import java.util.Map;

import services.DefaultDataGetterService;

public interface ObjectDao<Tipo> {

	public void insert(Tipo objeto);

	default String gerarCreateTable(Tipo objeto, Map<String, String> dados) {
		String nomeTabela = new DefaultDataGetterService<Tipo>().obterNomeTabela(objeto);
		Map<String, String> estruturaTabela = new DefaultDataGetterService<Tipo>().obterDadosDeTabela(objeto);
		StringBuilder query = new StringBuilder();
		query.append("CREATE TABLE " + nomeTabela.toUpperCase() + "(");

		int contador = 1;
		for (String coluna : estruturaTabela.keySet()) {
			if (contador == estruturaTabela.size()) {
				query.append(coluna + " " + estruturaTabela.get(coluna));
			} else {
				query.append(coluna + " " + estruturaTabela.get(coluna) + ", ");
			}
			contador++;
		}
		query.append(")");
		return query.toString().toUpperCase();
	}

}
