package features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import entities.Aula;
import interfaces.datagenerators.DataGetter;

public class ObterDadosDeObjetoTest {

	@Test
	public void testarObterDadosDeObjeto() {
		DataGetter data = new DataGetter();
		Map<String, String> dados = data.obterDadosDeObjeto(new Aula("1", "Compiladores"));
		Map<String, String> esperado = new HashMap<String, String>();
		esperado.put("codigo", "1");
		esperado.put("disciplina_aula", "Compiladores");
		assertEquals(dados, esperado);
	}

}
