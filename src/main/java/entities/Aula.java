package entities;

import annotation.Coluna;
import annotation.Id;
import annotation.Tabela;

@Tabela(criarSeNaoExistir = true, nome = "disciPLInas")
public class Aula {

	@Id(autoIncrement = true, primaryKey = true)
	@Coluna(nome = "codigo_aula", tipo = "integer")
	private String codigo;

	@Coluna(nome = "disciplina_aula")
	private String disciplina;

	public Aula(String codigo, String disciplina) {
		this.codigo = codigo;
		this.disciplina = disciplina;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

}
