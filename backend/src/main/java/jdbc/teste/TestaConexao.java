package jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionFactory;

public class TestaConexao {
	public static void main (String[] args) throws SQLException {
		System.out.println("Oi");
		Connection con = ConnectionFactory.getConnection();
		System.out.println(con);
		con.close();
	}
}