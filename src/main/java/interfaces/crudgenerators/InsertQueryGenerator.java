package interfaces.crudgenerators;

import java.util.Map;

public interface InsertQueryGenerator<Tipo> {

	public String gerarInsert(String nomeTabela, Map<String, String> dados);

}
