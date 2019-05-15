package application;

import database.Banco;

public class Program {

	public static void main(String[] args) {

		try {
		
		/*Pessoa pessoa = new Pessoa("1", "MC CAVALCANTI", "M", "21");
		DaoComponent<Pessoa> componentePessoa = new DaoComponent<Pessoa>(conexao);
		componentePessoa.insert(pessoa);
		DaoComponent<Animal> componentAnimal = new DaoComponent<Animal>(conexao);
		componentAnimal.insert(new Animal("1","Leonardo","Gato"));
		*/
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			Banco.closeConnection();
		}
	}

}
