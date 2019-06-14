package interfaces.datagenerators;

import java.lang.reflect.Field;
import java.util.Map;

public interface DataGetterInterface<Tipo> {

	public String obterNomeTabela(Tipo objeto);
	
	public Map<String,String> obterDadosDeObjeto(Tipo objeto);
	
	public Map<String,String> obterDadosDeColuna(Field campo);
	
	
}
