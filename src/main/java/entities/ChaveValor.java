package entities;

public class ChaveValor {

	private String coluna;
	private String tipo;

	public ChaveValor(String coluna, String tipo) {
		this.coluna = coluna;
		this.tipo = tipo;
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
