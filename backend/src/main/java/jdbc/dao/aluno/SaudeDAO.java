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
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO saude (ra_aluno, faz_tratamentos_medicos, descricao_tratamento, problemas_de_saude_na_familia, plano_de_saude, pessoas_idosas, problemas_psiquiatricos, possui_alergia, tipo_alergia, toma_medicacao, tipo_medicacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		stmt.setInt(1, saude.getAluno().getRa());
		stmt.setBoolean(2, saude.isFazTratamentosMedicos());
		stmt.setString(3, saude.getDescricaoTratamento());
		stmt.setBoolean(4, saude.isProblemasSaudeFamilia());
		stmt.setBoolean(5, saude.isPlanoSaude());
		stmt.setBoolean(6, saude.isPessoasIdosas());
		stmt.setBoolean(7, saude.isProblemasPsiquiatricos());
		stmt.setBoolean(8, saude.isPossuiAlergia());
		stmt.setString(9, saude.getDescricaoAlergia());
		stmt.setBoolean(10, saude.isTomaMedicacao());
		stmt.setString(11, saude.getTipoMedicacao());
		
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
			saude.setDescricaoTratamento(rs.getString("descricao_tratamento"));
			saude.setProblemasSaudeFamilia(rs.getBoolean("problemas_de_saude_na_familia"));
			saude.setPlanoSaude(rs.getBoolean("plano_de_saude"));
			saude.setPessoasIdosas(rs.getBoolean("pessoas_idosas"));
			saude.setProblemasPsiquiatricos(rs.getBoolean("problemas_psiquiatricos"));
			saude.setPossuiAlergia(rs.getBoolean("possui_alergia"));
			saude.setDescricaoAlergia(rs.getString("tipo_alergia"));
			saude.setTomaMedicacao(rs.getBoolean("toma_medicacao"));
			saude.setTipoMedicacao(rs.getString("tipo_medicacao"));
			
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
				saude.setDescricaoTratamento(rs.getString("descricao_tratamento"));
				saude.setProblemasSaudeFamilia(rs.getBoolean("problemas_de_saude_na_familia"));
				saude.setPlanoSaude(rs.getBoolean("plano_de_saude"));
				saude.setPessoasIdosas(rs.getBoolean("pessoas_idosas"));
				saude.setProblemasPsiquiatricos(rs.getBoolean("problemas_psiquiatricos"));
				saude.setPossuiAlergia(rs.getBoolean("possui_alergia"));
				saude.setDescricaoAlergia(rs.getString("tipo_alergia"));
				saude.setTomaMedicacao(rs.getBoolean("toma_medicacao"));
				saude.setTipoMedicacao(rs.getString("tipo_medicacao"));
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
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE saude SET faz_tratamentos_medicos = ?, descricao_tratamento = ?, problemas_de_saude_na_familia = ?, plano_de_saude = ?, pessoas_idosas = ?, problemas_psiquiatricos = ?, possui_alergia = ?, tipo_alergia = ?, toma_medicacao = ?, tipo_medicacao = ? WHERE ra_aluno = ?");
		
		stmt.setBoolean(1, saude.isFazTratamentosMedicos());
		stmt.setString(2, saude.getDescricaoTratamento());
		stmt.setBoolean(3, saude.isProblemasSaudeFamilia());
		stmt.setBoolean(4, saude.isPlanoSaude());
		stmt.setBoolean(5, saude.isPessoasIdosas());
		stmt.setBoolean(6, saude.isProblemasPsiquiatricos());
		stmt.setBoolean(7, saude.isPossuiAlergia());
		stmt.setString(8, saude.getDescricaoAlergia());
		stmt.setBoolean(9, saude.isTomaMedicacao());
		stmt.setString(10, saude.getTipoMedicacao());
		stmt.setInt(11, ra);
		
		stmt.execute();
		stmt.close();
	}
}
