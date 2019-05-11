package entities;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;

@Tabela(nome = "cachorro", criarSeNaoExistir = true)
public class Cachorro {

	@Id(autoIncrement = true, primaryKey = true)
	@Coluna(nome = "codigo",  tipo = "integer")
	private String codigoCao;

	@Coluna(nome = "nome", tipo = "varchar(30)")
	private String nomeCao;

	@Coluna(nome = "vivo", tipo = "boolean")
	private String vivoCao;

	public Cachorro(String codigoCao, String nomeCao, String vivoCao) {
		this.codigoCao = codigoCao;
		this.nomeCao = nomeCao;
		this.vivoCao = vivoCao;
	}

	public String getCodigoCao() {
		return codigoCao;
	}

	public void setCodigoCao(String codigoCao) {
		this.codigoCao = codigoCao;
	}

	public String getNomeCao() {
		return nomeCao;
	}

	public void setNomeCao(String nomeCao) {
		this.nomeCao = nomeCao;
	}

	public String getVivoCao() {
		return vivoCao;
	}

	public void setVivoCao(String vivoCao) {
		this.vivoCao = vivoCao;
	}

}
