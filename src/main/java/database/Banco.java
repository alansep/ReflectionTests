package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

	private static Connection conexao = null;

	public static Connection getConnection() {
		if (conexao == null) {
			try {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/reflection", "alansep", "ruterute");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conexao;
	}

	public static void closeConnection() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
