package jdbc.dao.turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.turma.AlunoTurma;

public class AlunoTurmaDAO {
	private Connection connection;
	
	public AlunoTurmaDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public void adiciona(AlunoTurma alunoTurma) throws SQLException {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO aluno_turma (ra_aluno, id_turma) VALUES (?, ?)");
			
					
			stmt.setInt(1, alunoTurma.getRaAluno());
			stmt.setInt(2, alunoTurma.getIdTurma());
			
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public List<AlunoTurma> getListaAlunoTurma() throws SQLException {
		List<AlunoTurma> listaAlunoTurma = new ArrayList<AlunoTurma>();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno_turma");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				AlunoTurma at = new AlunoTurma();
				
				at.setRaAluno(rs.getInt("ra_aluno"));
				at.setIdTurma(rs.getInt("id_turma"));
				
				listaAlunoTurma.add(at);
			}
			
			rs.close();
			
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return listaAlunoTurma;
	}
	
	public List<AlunoTurma> getListaAlunoTurmaByTurma(int idTurma) throws SQLException {
		List<AlunoTurma> listaAlunoTurma = new ArrayList<AlunoTurma>();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno_turma WHERE id_turma = ?");
			stmt.setInt(1, idTurma);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				AlunoTurma at = new AlunoTurma();
				
				at.setRaAluno(rs.getInt("ra_aluno"));
				at.setIdTurma(rs.getInt("id_turma"));
				
				listaAlunoTurma.add(at);
			}
			
			rs.close();
			
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return listaAlunoTurma;
	}
	
	public List<AlunoTurma> getListaAlunoTurmaByAluno(int raAluno) throws SQLException {
		List<AlunoTurma> listaAlunoTurma = new ArrayList<AlunoTurma>();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno_turma WHERE ra_aluno = ?");
			stmt.setInt(1, raAluno);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				AlunoTurma at = new AlunoTurma();
				
				at.setRaAluno(rs.getInt("ra_aluno"));
				at.setIdTurma(rs.getInt("id_turma"));
				
				listaAlunoTurma.add(at);
			}
			
			rs.close();
			
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return listaAlunoTurma;
	}
	
	public void excluir(int raAluno, int idTurma) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM aluno_turma WHERE ra_aluno = ? AND id_turma = ?");
			stmt.setInt(1, raAluno);
			stmt.setInt(2, idTurma);
			stmt.execute();
			stmt.close();
			
		}  catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void excluirByTurma(int idTurma) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM aluno_turma WHERE id_turma = ?");
			
			stmt.setInt(1, idTurma);
			stmt.execute();
			
			stmt.close();
			
		}  catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void excluirByAluno(int raAluno) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM aluno_turma WHERE ra_aluno = ?");
			
			stmt.setInt(1, raAluno);
			stmt.execute();
			
			stmt.close();
			
		}  catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
}
