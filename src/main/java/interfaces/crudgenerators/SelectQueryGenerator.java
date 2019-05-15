package interfaces.crudgenerators;

public interface SelectQueryGenerator<Tipo> {

	public String gerarSelect(Tipo objeto);

	public String gerarSelect(Tipo objeto, String clausula);

}
