package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domain.enums.Perfil;
import jdbc.ConnectionFactory;
import model.Usuario;

@Component
public class UsuarioDAO {
	// a conexão com o banco de dados
		private Connection connection;
		
		private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		public UsuarioDAO() throws SQLException {
			this.connection = ConnectionFactory.getConnection();
		}
		
		private PerfilDAO perfilDao = new PerfilDAO();

		public void adiciona(Usuario usuario) throws SQLException {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO usuario ( email, nome, senha, perguntasecreta, respostasecreta, ativo, setor) VALUES ( ?, ?, ?, ?, ?, ?, ?)");
			// seta os valores
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, passwordEncoder.encode(usuario.getSenha()));
			stmt.setString(4, usuario.getPerguntasecreta());
			stmt.setString(5, passwordEncoder.encode(usuario.getRespostasecreta()));
			stmt.setBoolean(6, usuario.isAtivo());
			stmt.setString(7, usuario.getSetor());

			// executa
			stmt.execute();
			stmt.close();
		}
		
		public List<Usuario> getLista() throws SQLException {

			List<Usuario> usuarios = new ArrayList<Usuario>();

			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM usuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Aluno
				Usuario usuario = new Usuario();
				
				usuario.setCodigo(rs.getInt("codigo"));
				usuario = perfilDao.getPerfis(usuario.getCodigo());
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerguntasecreta(rs.getString("perguntasecreta"));
				usuario.setRespostasecreta(rs.getString("respostasecreta"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				usuario.setSetor(rs.getString("setor"));
			
				
				// adicionando o objeto à lista
				usuarios.add(usuario);

			}

			rs.close();
			stmt.close();

			return usuarios;

		}
		
		
		
		public Usuario getUsuario(Integer search) throws SQLException {

			Usuario usuario = new Usuario();

			try {
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM usuario WHERE " + "codigo = ?");

				stmt.setInt(1, search); //Note que essa variavel é passada da função principal
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					usuario.setCodigo(rs.getInt("codigo"));
					usuario = perfilDao.getPerfis(usuario.getCodigo());
					usuario.setEmail(rs.getString("email"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setPerguntasecreta(rs.getString("perguntasecreta"));
					usuario.setRespostasecreta(rs.getString("respostasecreta"));
					usuario.setAtivo(rs.getBoolean("ativo"));
					usuario.setSetor(rs.getString("setor"));
					}
				
			}

		    catch (SQLException ex) {
		    	System.out.println(ex.toString());
		    }


			return (usuario);


		}
		
// -------------------------------------------- busca por email
		public Usuario getUsuarioByEmail(String search){

			Usuario usuario = new Usuario();
			try {
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM usuario WHERE " + "email = ?");

				stmt.setString(1, search); //Note que essa variavel é passada da função principal
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					usuario.setCodigo(rs.getInt("codigo"));
					usuario = perfilDao.getPerfis(usuario.getCodigo());
					usuario.setEmail(rs.getString("email"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setPerguntasecreta(rs.getString("perguntasecreta"));
					usuario.setRespostasecreta(rs.getString("respostasecreta"));
					usuario.setAtivo(rs.getBoolean("ativo"));
					usuario.setSetor(rs.getString("setor"));
					
					
				}
			}

		    catch (SQLException ex) {
		    	System.out.println(ex.toString());
		    }


			return (usuario);


		}


		public void excluir(Integer search) {

	    try {
// editar esse STATEMENT
	    	
	    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement
	    			("DELETE FROM usuario WHERE codigo = ? AND email != 'admin@admin'");
	    	stmt.setInt(1, search);
	    	stmt.execute();

	    }

		catch (SQLException ex) {
			System.out.println(ex.toString());
	    }

	  }


		public void altera(Usuario usuario, Integer codigo) throws SQLException {

			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE usuario SET codigo=?, email=?, nome=?, senha=?, perguntasecreta=?, respostasecreta=?, ativo=?, setor=? WHERE codigo=?");
			
			stmt.setInt(1, usuario.getCodigo());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getNome());
			stmt.setString(4, passwordEncoder.encode(usuario.getSenha()));
			stmt.setString(5, usuario.getPerguntasecreta());
			stmt.setString(6, passwordEncoder.encode(usuario.getRespostasecreta()));
			stmt.setBoolean(7, usuario.isAtivo());
			stmt.setString(8, usuario.getSetor());
			stmt.setInt(9, codigo);

			stmt.execute();
			stmt.close();
		}
}
