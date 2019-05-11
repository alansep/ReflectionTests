package entities;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;

@Tabela(nome="animal", criarSeNaoExistir = true)
public class Animal {

	@Id(autoIncrement = true, primaryKey = true)
	@Coluna(nome = "codigo_animal", tipo = "integer")
	private String codigo;

	@Coluna(nome = "nome_animal", tipo = "varchar(30)")
	private String nome;

	@Coluna(nome = "tipo_animal", tipo = "varchar(30)")
	private String tipo;

	public Animal(String codigo, String nome, String tipo) {
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
