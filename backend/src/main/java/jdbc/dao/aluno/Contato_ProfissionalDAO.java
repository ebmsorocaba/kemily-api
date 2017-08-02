package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.Contato_Profissional;

public class Contato_ProfissionalDAO {
	private Connection connection;
	private ContatoDAO contatoDao;
	
	public Contato_ProfissionalDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.contatoDao = new ContatoDAO();
	}
	
	public void adicionar(Contato_Profissional contato_Profissional) throws SQLException {
		contato_Profissional = (Contato_Profissional) contatoDao.adicionar(contato_Profissional);
		
		if(contato_Profissional.getId() == -1 ) {
			
			throw new SQLException("ID -1, Contato nao criado");
			
		} else {
		
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO contato_profissional(id_contato, cargo) VALUES(?, ?)");
		
			stmt.setInt(1, contato_Profissional.getId());
			stmt.setString(2, contato_Profissional.getCargo());
			
			stmt.execute();
			stmt.close();
			
		}
	}
	
	public List<Contato_Profissional> getContato_Profissional() throws SQLException {
		List<Contato_Profissional> responsaveis = new ArrayList<Contato_Profissional>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_profissional");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Contato_Profissional resp = new Contato_Profissional();
			
			resp.setId(rs.getInt("id_contato"));
			resp.setCargo(rs.getString("cargo"));
			
			responsaveis.add(resp);
		}
		
		rs.close();
		stmt.close();
		
		return responsaveis;
	}
	
	public Contato_Profissional getContato_Profissional(int id) throws SQLException {
		Contato_Profissional contato_Profissional = new Contato_Profissional();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_profissional WHERE id_contato = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				contato_Profissional.setId(rs.getInt("id_contato"));
				contato_Profissional.setCargo(rs.getString("cargo"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return contato_Profissional;
	}
	
	public void excluir(int id) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM contato_profissional WHERE id_contato = ?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(Contato_Profissional contato_Profissional, int id) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE contato_profissional SET cargo = ? WHERE id_contato = ?");
		
		stmt.setString(1, contato_Profissional.getCargo());
		stmt.setInt(2, id);
		
		stmt.execute();
		stmt.close();
	}
}
