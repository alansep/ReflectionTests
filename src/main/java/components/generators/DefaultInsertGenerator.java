package components.generators;

import java.util.Map;

import interfaces.crudgenerators.InsertQueryGenerator;

public class DefaultInsertGenerator<Tipo> implements InsertQueryGenerator<Tipo> {

	public String gerarInsert(String nomeTabela, Map<String, String> dados) {
		
		
		int contador = 1;
		StringBuilder query = new StringBuilder();
		query.append("insert into " + nomeTabela + "(");

		for (String coluna : dados.keySet()) {
			if (contador == dados.size()) {
				query.append(coluna);
			} else {
				query.append(coluna + ",");
			}
			contador++;
		}
		contador = 1;
		query.append(") values (");
		for (String coluna : dados.keySet()) {
			if (contador == dados.size()) {
				query.append("'" + dados.get(coluna));
			} else {
				query.append("'" + dados.get(coluna) + "',");
			}
			contador++;
		}
		query.append("')");
		return query.toString();
	}

	public String gerarInsertAutoIncrement(String nomeTabela, Map<String, String> dados) {
		for (String chave : dados.keySet()) {
			if(dados.get(chave).equals("null")) {
				dados.remove(chave);
				break;
			}
		}
		System.out.println(this.gerarInsert(nomeTabela, dados));
		return this.gerarInsert(nomeTabela, dados);
	}
}
