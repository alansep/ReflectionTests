package entities;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;

@Tabela(nome = "musica", criarSeNaoExistir = true)
public class Musica {
	
	@Id(autoIncrement = true, primaryKey = true)
	@Coluna(nome = "codigo_musica",tipo = "integer",notNull = true)
	private String codigo;	
	
	@Coluna(nome = "nome_musica",tipo = "varchar(20)",notNull = true)
	private String nome;
	
	@Coluna(nome = "autor_musica", tipo="varchar(20)",notNull = true)
	private String autor;
	
	
	public Musica(String codigo, String nome, String autor) {
		this.codigo = codigo;
		this.nome = nome;
		this.autor = autor;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	

}
