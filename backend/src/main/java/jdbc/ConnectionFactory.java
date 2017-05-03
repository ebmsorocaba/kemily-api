package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
		
		
		String url = "jdbc:postgresql://ec2-107-22-236-252.compute-1.amazonaws.com:5432/d3k8ui2hd4460h?sslmode=require";
		Properties props = new Properties();
		props.setProperty("user","uimgbmczwnxbtx");
		props.setProperty("password","20bd33bf81cf0b1ec35371a0c742783c027b8c5ff856067b6a456501ae83c06e");
		props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);
		
		
		
		//return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ebm_admin", "postgres", "postgres");
		//return DriverManager.getConnection("jdbc:postgresql://ec2-107-22-236-252.compute-1.amazonaws.com:5432/d3k8ui2hd4460h", "uimgbmczwnxbtx", "20bd33bf81cf0b1ec35371a0c742783c027b8c5ff856067b6a456501ae83c06e");
		return conn;
	} catch (ClassNotFoundException e) {
		throw new SQLException(e.getMessage());
	}
}
	
	
}