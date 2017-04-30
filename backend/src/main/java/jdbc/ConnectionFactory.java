package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
/*
	public static Connection getConnection() throws SQLException {
		try {
			System.out.println("Oi factory");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Conectando ao banco");
		return DriverManager.getConnection("jdbc:mysql://localhost/crudAluno", "root", "%D1eg*Bpt@");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
*/
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/crudAluno", "postgres", "postgres");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	
}
