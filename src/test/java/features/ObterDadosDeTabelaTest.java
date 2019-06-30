package features;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import annotation.Coluna;
import annotation.Id;
import entities.Cachorro;
import interfaces.datagenerators.DataGetter;

public class ObterDadosDeTabelaTest {

	@Test
	public void testarObterDadosDeTabela() {

		class Bola {

			@Coluna(nome = "cod_bola", notNull = true)
			@Id(primaryKey = true, autoIncrement = true)
			private String codigo;

			@Coluna(nome = "nome_bola", tipo = "varchar(30)")
			private String nome;

			public Bola(String codigo, String nome) {
				this.codigo = codigo;
				this.nome = nome;
			}

		}

		DataGetter data = new DataGetter();
		Map<String, String> dados = data.obterDadosDeTabela(new Bola("1", "Leonardo"));
		Map<String, String> esperado = new HashMap<>();
		esperado.put("cod_bola", "integer not null primary key auto_increment");
		esperado.put("nome_bola", "varchar(30)");
		System.out.println(dados);
		System.out.println(esperado);

	}

}
