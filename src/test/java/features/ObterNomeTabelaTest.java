package features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import entities.Cachorro;
import interfaces.datagenerators.DataGetter;

public class ObterNomeTabelaTest {

	@Test
	public void testarObterNomeTabela() {
		DataGetter data = new DataGetter();
		String obtido = data.obterNomeTabela(new Cachorro("teste", "s"));
		assertEquals("Cachorro", obtido);
	}

}
