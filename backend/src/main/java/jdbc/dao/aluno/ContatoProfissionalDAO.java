package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.ContatoProfissional;

public class ContatoProfissionalDAO {
	private Connection connection;
	private ContatoDAO contatoDao;
	
	public ContatoProfissionalDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.contatoDao = new ContatoDAO();
	}
	
	public void adicionar(ContatoProfissional contatoProfissional) throws SQLException {
		contatoProfissional = (ContatoProfissional) contatoDao.adicionarEspecializado(contatoProfissional);
		
		if(contatoProfissional.getId() == -1 ) {
			
			throw new SQLException("ID -1, Contato nao criado");
			
		} else {
		
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO contato_profissional(id_contato, cargo) VALUES(?, ?)");
		
			stmt.setInt(1, contatoProfissional.getId());
			stmt.setString(2, contatoProfissional.getCargo());
			
			stmt.execute();
			stmt.close();
			
		}
	}
	
	public List<ContatoProfissional> getLista() throws SQLException {
		List<ContatoProfissional> responsaveis = new ArrayList<ContatoProfissional>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_profissional");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ContatoProfissional resp = new ContatoProfissional();
			
			resp.setId(rs.getInt("id_contato"));
			resp.setCargo(rs.getString("cargo"));
			
			responsaveis.add(resp);
		}
		
		rs.close();
		stmt.close();
		
		return responsaveis;
	}
	
	public ContatoProfissional getContatoProfissional(int id) throws SQLException {
		ContatoProfissional contatoProfissional = new ContatoProfissional();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_profissional WHERE id_contato = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				contatoProfissional.setId(rs.getInt("id_contato"));
				contatoProfissional.setCargo(rs.getString("cargo"));
			}
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return contatoProfissional;
	}
	
	public void excluir(int id) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM contato_profissional WHERE id_contato = ?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(ContatoProfissional contatoProfissional, int id) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE contato_profissional SET cargo = ? WHERE id_contato = ?");
		
		stmt.setString(1, contatoProfissional.getCargo());
		stmt.setInt(2, id);
		
		stmt.execute();
		stmt.close();
	}
}
