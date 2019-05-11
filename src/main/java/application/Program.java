package application;

import java.sql.Connection;

import components.DaoComponent;
import database.Banco;
import entities.Animal;
import entities.Musica;
import entities.Pessoa;

public class Program {

	public static void main(String[] args) {

		try {
		Connection conexao = Banco.getConnection();
		Pessoa pessoa = new Pessoa("1", "Gabriel Alan", "M", "21");
		DaoComponent<Pessoa> componentePessoa = new DaoComponent<Pessoa>(conexao);
		componentePessoa.insert(pessoa);
		DaoComponent<Animal> componentAnimal = new DaoComponent<Animal>(conexao);
		componentAnimal.insert(new Animal("1","Leonardo","Gato"));
		DaoComponent<Musica> componenteMusica = new DaoComponent<Musica>(conexao);
		componenteMusica.insert(new Musica("1","Halo","Beyoncé"));
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			Banco.closeConnection();
		}
	}

}
