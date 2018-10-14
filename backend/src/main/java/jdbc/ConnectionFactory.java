package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.jdbc3.Jdbc3ConnectionPool;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

public class ConnectionFactory {

	private static Connection conn;
	private static Jdbc3PoolingDataSource dataSource;

	/** NOTA DO DIEGO FERREIRA*/

	//Eu criei a função createConnectionPool() com uma string pra cada tipo de conexão (localhost, heroku testing, heroku homologação)
	//Pra alterar a conexão, troca a 'entrada' da função na linha 72.
	/*As Strings são:
	 * 'localhost' para o localhost,
	 * 'heroku-test' para o heroku-test,
	 * 'heroku-homologacao' para o heroku-homologação.
	 */


	private static void createConnectionPool(String tipo) {
			if(tipo.equals("localhost")) {
				//LOCALHOST
				Jdbc3PoolingDataSource pool = new Jdbc3PoolingDataSource();
				pool.setUrl("jdbc:postgresql://localhost:5432/ebm_admin");
				pool.setUser("postgres");
				pool.setPassword("bgxeso2d");
				pool.setMaxConnections(15);
				dataSource = pool;
			} else {
				if(tipo.equals("heroku-test")) {
					//HEROKU TESTING
					Jdbc3PoolingDataSource pool = new Jdbc3PoolingDataSource();
					pool.setUrl("jdbc:postgresql://ec2-107-22-236-252.compute-1.amazonaws.com:5432/d3k8ui2hd4460h?sslmode=require");
					pool.setUser("uimgbmczwnxbtx");
					pool.setPassword("20bd33bf81cf0b1ec35371a0c742783c027b8c5ff856067b6a456501ae83c06e");
					pool.setSsl(true);
					pool.setMaxConnections(15);
					dataSource = pool;
				} else {
					if(tipo.equals("heroku-homologacao")) {
						//HEROKU HOMOLOGAÇÃO
						Jdbc3PoolingDataSource pool = new Jdbc3PoolingDataSource();
						pool.setUrl("jdbc:postgresql://ec2-50-17-236-15.compute-1.amazonaws.com:5432/d18n1ki8qe2orj?sslmode=require");
						pool.setUser("pjcvqvzaytfmmi");
						pool.setPassword("9416bca22c6cf3392b8c9483de6c78cd52cf96b1d2f3504ea51de2eae260d9ba");
						pool.setSsl(true);
						pool.setMaxConnections(15);
						dataSource = pool;
					} else {
						if(tipo.equals("producao")) {
							//PRODUÇÃO
							Jdbc3PoolingDataSource pool = new Jdbc3PoolingDataSource();
							pool.setUrl("jdbc:postgresql://localhost:5432/kemily");
							pool.setUser("postgres");
							pool.setPassword("k3m1l1");
							pool.setSsl(false);
							pool.setMaxConnections(15);
							dataSource = pool;
					}
				}
			}
		}
	}
	public static Connection getConnection() throws SQLException {

		if(dataSource == null) {

			//Aqui que muda a string de conexão
			/**
			 * localhost:		"localhost"
		 	 * teste:			"heroku-test"
		 	 * homologação:		"heroku-homologacao"
			 */
			createConnectionPool("producao");
		}

		if(conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();
		}

		return conn;
	}

		//Class.forName("org.postgresql.Driver");

		/** Heroku Testing */
		// String url = "jdbc:postgresql://ec2-107-22-236-252.compute-1.amazonaws.com:5432/d3k8ui2hd4460h?sslmode=require";
		// Properties props = new Properties();
		// props.setProperty("user","uimgbmczwnxbtx");
		// props.setProperty("password","20bd33bf81cf0b1ec35371a0c742783c027b8c5ff856067b6a456501ae83c06e");
		// props.setProperty("ssl","true");
		// Connection conn = DriverManager.getConnection(url, props);
		//
		// return conn;

		/** Heroku Homologation */
		/*String url = "jdbc:postgresql://ec2-50-17-236-15.compute-1.amazonaws.com:5432/d18n1ki8qe2orj?sslmode=require";
		Properties props = new Properties();
		props.setProperty("user","pjcvqvzaytfmmi");
		props.setProperty("password","9416bca22c6cf3392b8c9483de6c78cd52cf96b1d2f3504ea51de2eae260d9ba");
		props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);*/

		//return conn;

		/** Localhost */

		//return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ebm_admin", "postgres", "postgres");
}
