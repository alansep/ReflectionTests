package application;

import database.Banco;
import entities.Cachorro;
import services.DaoComponentService;

public class Program {

	public static void main(String[] args) {

		DaoComponentService<Cachorro> daoComponentService = new DaoComponentService<Cachorro>(Banco.getConnection());
		daoComponentService.insert(new Cachorro("Alberto", "1"));
		Banco.closeConnection();

	}

}
