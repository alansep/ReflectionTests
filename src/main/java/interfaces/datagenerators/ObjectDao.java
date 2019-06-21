package interfaces.datagenerators;

import java.util.Map;

public interface ObjectDao<Tipo> {

	public void insert(Tipo objeto);

	default String gerarCreateTable(Tipo objeto, Map<String, String> dados) {
		String nomeTabela = new DataGetter().obterNomeTabela(objeto);
		Map<String, String> estruturaTabela = new DataGetter().obterDadosDeTabela(objeto);
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
