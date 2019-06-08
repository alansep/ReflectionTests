package services;

import java.sql.Connection;

import interfaces.DaoComponent;

public class DaoComponentService<Tipo> extends DaoComponent<Tipo> {

	public DaoComponentService(Connection conexao) {
		super(conexao);
	}

}
