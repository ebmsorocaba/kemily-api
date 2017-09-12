package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.Saude;

public class SaudeDAO {
	private Connection connection;
	private AlunoDAO alunoDao;
	
	public SaudeDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.alunoDao = new AlunoDAO();
	}
	
	public void adiciona(Saude saude) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO saude (ra_aluno, faz_tratamentos_medicos, problemas_de_saude_na_familia, plano_de_saude, pessoas_idosas, problemas_psiquiatricos) VALUES (?, ?, ?, ?, ?, ?)");
		
		stmt.setInt(1, saude.getAluno().getRa());
		stmt.setBoolean(2, saude.isFazTratamentosMedicos());
		stmt.setBoolean(3, saude.isProblemasSaudeFamilia());
		stmt.setBoolean(4, saude.isPlanoSaude());
		stmt.setBoolean(5, saude.isPessoasIdosas());
		stmt.setBoolean(6, saude.isProblemasPsiquiatricos());
		
		stmt.execute();
		stmt.close();
	}
	
	public List<Saude> getLista() throws SQLException {
		List<Saude> saudes = new ArrayList<Saude>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM saude");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next() ) {
			Saude saude = new Saude();
			
			saude.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
			saude.setFazTratamentosMedicos(rs.getBoolean("faz_tratamentos_medicos"));
			saude.setProblemasSaudeFamilia(rs.getBoolean("problemas_de_saude_na_familia"));
			saude.setPlanoSaude(rs.getBoolean("plano_de_saude"));
			saude.setPessoasIdosas(rs.getBoolean("pessoas_idosas"));
			saude.setProblemasPsiquiatricos(rs.getBoolean("problemas_psiquiatricos"));
			
			saudes.add(saude);
		}
		
		rs.close();
		stmt.close();
		
		return saudes;
	}
	
	public Saude getSaude(int ra) throws SQLException {
		Saude saude = new Saude();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM saude WHERE " + "ra_aluno = ?");
			stmt.setInt(1, ra);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				saude.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
				saude.setFazTratamentosMedicos(rs.getBoolean("faz_tratamentos_medicos"));
				saude.setProblemasSaudeFamilia(rs.getBoolean("problemas_de_saude_na_familia"));
				saude.setPlanoSaude(rs.getBoolean("plano_de_saude"));
				saude.setPessoasIdosas(rs.getBoolean("pessoas_idosas"));
				saude.setProblemasPsiquiatricos(rs.getBoolean("problemas_psiquiatricos"));
			}
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		//plano_de_saude, pessoas_idosas, problemas_psiquiatricos
		return saude;
	}
	
	public void excluir(int ra) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM saude WHERE ra_aluno = ?");
			
			stmt.setInt(1, ra);
			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(Saude saude, int ra) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE saude SET faz_tratamentos_medicos = ?, problemas_de_saude_na_familia = ?, plano_de_saude = ?, pessoas_idosas = ?, problemas_psiquiatricos = ? WHERE ra_aluno = ?");
		
		stmt.setBoolean(1, saude.isFazTratamentosMedicos());
		stmt.setBoolean(2, saude.isProblemasSaudeFamilia());
		stmt.setBoolean(3, saude.isPlanoSaude());
		stmt.setBoolean(4, saude.isPessoasIdosas());
		stmt.setBoolean(5, saude.isProblemasPsiquiatricos());
		stmt.setInt(6, ra);
		
		stmt.execute();
		stmt.close();
	}
}
