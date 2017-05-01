package jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
//import com.mysql.jdbc.PreparedStatement;


import jdbc.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {
		// a conexão com o banco de dados
		private Connection connection;
		
		public UsuarioDAO() throws SQLException {
			this.connection = ConnectionFactory.getConnection();
		}
		
		public void adiciona(Usuario usuario) throws SQLException {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO tb_usuarios (nome,senha,grupo,ativo) VALUES (?, ?, ?, ?)");
			// seta os valores
			stmt.setString(1,usuario.getNome());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getGrupo());
			stmt.setBoolean(4,usuario.isAtivo());
			// executa
			stmt.execute();
			stmt.close();
		}
		
		public List<Usuario> getLista() throws SQLException {
			
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_usuarios");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Entrou DAO");
			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				// criando o objeto Aluno
				Usuario usuario = new Usuario("","","",false);
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setGrupo(rs.getString("grupo"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				// adicionando o objeto à lista
				usuarios.add(usuario);
			
			}
			rs.close();
			stmt.close();
			return usuarios;
			
		}
		
		public Usuario getUsuario(String search) throws SQLException {
			
			Usuario usuario = new Usuario("","","",false);
			
			try {
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_usuarios WHERE " + "nome = ?");
	            
				stmt.setString(1, search); //Note que essa variavel é passada da função principal
	            ResultSet rs = stmt.executeQuery();
	           
	            if (rs.next() == true) {
	            	usuario.setNome(rs.getString("nome"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setGrupo(rs.getString("grupo"));
					usuario.setAtivo(rs.getBoolean("grupo"));
	            }
	        }
	        catch (SQLException ex) { 
	             System.out.println(ex.toString());   
	        }
			
			
	        return (usuario);
	}
	
		
		public void excluir(String search) {
	        
	        try {
	        	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM tb_usuarios WHERE nome = ?");
	            
	            stmt.setString(1, search);
	                      
	            stmt.execute();
	            
	        } catch (SQLException ex) {
	             System.out.println(ex.toString());   
	        }
	    }
	
		
		public void altera(Usuario usuario) throws SQLException {

			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE tb_usuarios SET senha=?, grupo=?, ativo=? WHERE nome=?");
				stmt.setString(1, usuario.getSenha());
				stmt.setString(2, usuario.getGrupo());
				stmt.setBoolean(3, usuario.isAtivo());
				stmt.setString(4, usuario.getNome());
				
				stmt.execute();
				stmt.close();
			}
}
		
