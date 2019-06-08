package application;

import database.Banco;
import entities.Aula;
import entities.Pessoa;
import services.DaoComponentService;

public class Program {

	public static void main(String[] args) {

		try {
		
		/*Pessoa pessoa = new Pessoa("1", "Rei Leonardo", "M", "201");
		DaoComponentService<Pessoa> daoComponentService = new DaoComponentService<Pessoa>(Banco.getConnection());
		daoComponentService.insert(pessoa);
	*/	
		Aula aula = new Aula("1", "Compiladores");
		DaoComponentService<Aula> daoComponentService = new DaoComponentService<Aula>(Banco.getConnection());
		daoComponentService.insert(aula);
		
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			Banco.closeConnection();
		}
	}

}
