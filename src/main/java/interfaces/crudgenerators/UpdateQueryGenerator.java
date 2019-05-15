package interfaces.crudgenerators;

public interface UpdateQueryGenerator<Tipo> {

	public String gerarUpdate(Tipo objeto);

	public String gerarUpdate(Tipo objeto, String clausula);

}
