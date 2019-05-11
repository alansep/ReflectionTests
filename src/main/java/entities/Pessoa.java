package entities;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;

@Tabela(nome = "pessoa", criarSeNaoExistir = true)
public class Pessoa {

	@Id(primaryKey = true, autoIncrement = true)
	@Coluna(nome = "codigo", tipo = "integer")
	private String codigoPessoa;

	@Coluna(nome="nome",tipo = "varchar(40)", notNull = true)
	private String nomePessoa;
	
	@Coluna(nome = "sexo", tipo = "varchar(1)")
	private String sexo;
	
	@Coluna(nome="idade", tipo = "integer", notNull = true)
	private String idadePessoa;

	public Pessoa(String codigoPessoa, String nomePessoa, String sexo, String idadePessoa) {
		this.codigoPessoa = codigoPessoa;
		this.nomePessoa = nomePessoa;
		this.sexo = sexo;
		this.idadePessoa = idadePessoa;
	}

	public String getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(String codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdadePessoa() {
		return idadePessoa;
	}

	public void setIdadePessoa(String idadePessoa) {
		this.idadePessoa = idadePessoa;
	}

}
