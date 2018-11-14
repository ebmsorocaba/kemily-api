package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.enums.Perfil;
import jdbc.ConnectionFactory;
import model.Usuario;

public class PerfilDAO {	
	// a conexão com o banco de dados
	private Connection connection;

	public PerfilDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();

}
	
	
	public Usuario getPerfis(Integer codigo) throws SQLException {
		Usuario usuario = new Usuario();
		
		usuario.setCodigo(codigo);
		
		try {
			
		// Statement para pegar os perfis
		PreparedStatement stmt2 = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM perfil WHERE " + "codigo_usu = ?");
		// pelo codigo do usuario na busca, ele busca os perfis no formato inteiro
		stmt2.setInt(1, codigo); 
		// tras nesse resultset
		ResultSet rs2 = stmt2.executeQuery();
		// e para cada perfil
		while (rs2.next()) {
			// ele instancia um perfil do tipo enumerado para esse usuario
			usuario.addPerfil(Perfil.toEnum(rs2.getInt("perfil")));
		}
		}
		catch(SQLException ex) {
	    	System.out.println(ex.toString());
	    }


		return (usuario);
	}
	
	public void excluir(Integer codigo) {

	    try {
// editar esse STATEMENT
	    	
	    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement
	    			("DELETE FROM perfil WHERE codigo_usu = ?");
	    	stmt.setInt(1, codigo);
	    	stmt.execute();

	    }

		catch (SQLException ex) {
			System.out.println(ex.toString());
	    }

	  }
}