package interfaces;

import java.util.List;

public interface ObjectDao <Tipo> {
	
	public List<Tipo> findAll();
	public void insert(Tipo objeto);

}
