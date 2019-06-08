package interfaces;

import java.util.Map;

public interface DataGetterInterface<Tipo> {

	public String obterNomeTabela(Tipo objeto);
	
	public Map<String,String> obterDadosDeObjeto(Tipo objeto);
	
}
