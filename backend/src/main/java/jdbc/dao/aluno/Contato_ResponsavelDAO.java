package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.Contato_Responsavel;

public class Contato_ResponsavelDAO {
	private Connection connection;
	private ContatoDAO contatoDao;
	
	public Contato_ResponsavelDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.contatoDao = new ContatoDAO();
	}
	
	public void adicionar(Contato_Responsavel contato_Responsavel) throws SQLException {
		contato_Responsavel = (Contato_Responsavel) contatoDao.adicionar(contato_Responsavel);
		
		if(contato_Responsavel.getId() == -1 ) {
			
			throw new SQLException("ID -1, Contato nao criado");
			
		} else {
		
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO contato_responsavel(id_contato, grau_parentesco, presente) VALUES(?, ?, ?);");
		
			stmt.setInt(1, contato_Responsavel.getId());
			stmt.setString(2, contato_Responsavel.getGrau_parentesco());
			stmt.setBoolean(3, contato_Responsavel.isPresente());
			
			stmt.execute();
			stmt.close();
			
		}
	}
	
	public List<Contato_Responsavel> getContato_Responsavel() throws SQLException {
		List<Contato_Responsavel> responsaveis = new ArrayList<Contato_Responsavel>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_responsavel");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Contato_Responsavel resp = new Contato_Responsavel();
			
			resp.setId(rs.getInt("id_contato"));
			resp.setGrau_parentesco(rs.getString("grau_parentesco"));
			resp.setPresente(rs.getBoolean("presente"));
			
			responsaveis.add(resp);
		}
		
		rs.close();
		stmt.close();
		
		return responsaveis;
	}
	
	public Contato_Responsavel getContato_Responsavel(int id) throws SQLException {
		Contato_Responsavel contato_Responsavel = new Contato_Responsavel();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_responsavel WHERE id_contato = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				contato_Responsavel.setId(rs.getInt("id_contato"));
				contato_Responsavel.setGrau_parentesco(rs.getString("grau_parentesco"));
				contato_Responsavel.setPresente(rs.getBoolean("presente"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return contato_Responsavel;
	}
	
	public void excluir(int id) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM contato_responsavel WHERE id_contato = ?");
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(Contato_Responsavel contato_Responsavel, int id) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE contato_responsavel SET grau_parentesco = ?, presente = ? WHERE id_contato = ?");
		
		stmt.setString(1, contato_Responsavel.getGrau_parentesco());
		stmt.setBoolean(2, contato_Responsavel.isPresente());
		stmt.setInt(3, id);
		
		stmt.execute();
		stmt.close();
	}
	
}
