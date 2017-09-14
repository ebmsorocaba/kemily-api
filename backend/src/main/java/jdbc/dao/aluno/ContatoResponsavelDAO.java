package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.ContatoResponsavel;

public class ContatoResponsavelDAO {
	private Connection connection;
	private ContatoDAO contatoDao;
	
	public ContatoResponsavelDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.contatoDao = new ContatoDAO();
	}
	
	public void adicionar(ContatoResponsavel contatoResponsavel) throws SQLException {
		contatoResponsavel = (ContatoResponsavel) contatoDao.adicionarEspecializado(contatoResponsavel);
		
		if(contatoResponsavel.getId() == -1 ) {
			
			throw new SQLException("ID -1, Contato nao criado");
			
		} else {
		
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO contato_responsavel(id_contato, grau_parentesco, estado) VALUES(?, ?, ?);");
		
			stmt.setInt(1, contatoResponsavel.getId());
			stmt.setString(2, contatoResponsavel.getGrauParentesco());
			stmt.setString(3, contatoResponsavel.getEstado());
			
			stmt.execute();
			stmt.close();
			
		}
	}
	
	public List<ContatoResponsavel> getLista() throws SQLException {
		List<ContatoResponsavel> responsaveis = new ArrayList<ContatoResponsavel>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_responsavel");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			ContatoResponsavel resp = new ContatoResponsavel();
			
			resp.setId(rs.getInt("id_contato"));
			resp.setGrauParentesco(rs.getString("grau_parentesco"));
			resp.setEstado(rs.getString("estado"));
			
			responsaveis.add(resp);
		}
		
		rs.close();
		stmt.close();
		
		return responsaveis;
	}
	
	public ContatoResponsavel getContatoResponsavel(int id) throws SQLException {
		ContatoResponsavel contatoResponsavel = new ContatoResponsavel();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato_responsavel WHERE id_contato = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				contatoResponsavel.setId(rs.getInt("id_contato"));
				contatoResponsavel.setGrauParentesco(rs.getString("grau_parentesco"));
				contatoResponsavel.setEstado(rs.getString("estado"));
			}
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return contatoResponsavel;
	}
	
	public void excluir(int id) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM contato_responsavel WHERE id_contato = ?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(ContatoResponsavel contatoResponsavel, int id) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE contato_responsavel SET grau_parentesco = ?, estado = ? WHERE id_contato = ?");
		
		stmt.setString(1, contatoResponsavel.getGrauParentesco());
		stmt.setString(2, contatoResponsavel.getEstado());
		stmt.setInt(3, id);
		
		stmt.execute();
		stmt.close();
	}
	
}
