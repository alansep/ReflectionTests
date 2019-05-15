package interfaces.crudgenerators;

public interface DeleteQueryGenerator <Tipo> {

	public String gerarDelete(Tipo objeto);
	public String gerarDelete(Tipo objeto, String clausula);
}
