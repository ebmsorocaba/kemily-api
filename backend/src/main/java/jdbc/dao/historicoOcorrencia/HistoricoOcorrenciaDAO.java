package jdbc.dao.historicoOcorrencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.historicoOcorrencia.HistoricoOcorrencia;

public class HistoricoOcorrenciaDAO {
	private Connection connection;

	public HistoricoOcorrenciaDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public void adiciona(HistoricoOcorrencia histOcorrencia) throws SQLException {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO historico_ocorrencia (data, hora, ra_aluno, descricao) VALUES (?, ?, ?, ?)");
			
			stmt.setDate(1, histOcorrencia.getData());
			stmt.setTime(2, histOcorrencia.getHora());
			stmt.setInt(3, histOcorrencia.getRaAluno());
			stmt.setString(4, histOcorrencia.getDescricao());
			
			stmt.execute();
			
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public List<HistoricoOcorrencia> getLista() throws SQLException {
		List<HistoricoOcorrencia> ocorrencias = new ArrayList<HistoricoOcorrencia>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM historico_ocorrencia");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HistoricoOcorrencia ho = new HistoricoOcorrencia();
			
			ho.setData(rs.getDate("data"));
			ho.setHora(rs.getTime("hora"));
			ho.setRaAluno(rs.getInt("ra_aluno"));
			ho.setDescricao(rs.getString("descricao"));
			
			ocorrencias.add(ho);
		}
		
		rs.close();
		stmt.close();
		
		return ocorrencias;
	}
	
	public List<HistoricoOcorrencia> getListaByAluno(int raAluno) throws SQLException {
		List<HistoricoOcorrencia> ocorrenciasByAluno = new ArrayList<HistoricoOcorrencia>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM historico_ocorrencia WHERE ra_aluno = ?");
		
		stmt.setInt(1, raAluno);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HistoricoOcorrencia ho = new HistoricoOcorrencia();
			
			ho.setData(rs.getDate("data"));
			ho.setHora(rs.getTime("hora"));
			ho.setRaAluno(rs.getInt("ra_aluno"));
			ho.setDescricao(rs.getString("descricao"));
			
			ocorrenciasByAluno.add(ho);
		}
		
		rs.close();
		stmt.close();
		
		return ocorrenciasByAluno;
	}
	 
	public void excluir(Date data, Time hora, int raAluno) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM historico_ocorrencia WHERE data = ? AND hora = ? AND ra_aluno = ?");
			
			stmt.setDate(1, data);
			stmt.setTime(2, hora);
			stmt.setInt(3, raAluno);
			
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException ex) {
            System.out.println(ex.toString());
        }
	}
	
	public void excluirByAluno(int raAluno) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM historico_ocorrencia WHERE ra_aluno = ?");
			
			stmt.setInt(1, raAluno);
			
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException ex) {
            System.out.println(ex.toString());
        }
	}
	
	public void altera(int raAluno, HistoricoOcorrencia ho) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE historico_ocorrencia SET descricao = ? WHERE data = ? AND hora = ? AND ra_aluno = ?");
		
		stmt.setString(1, ho.getDescricao());
		stmt.setDate(2, ho.getData());
		stmt.setTime(3, ho.getHora());
		stmt.setInt(4, raAluno);
		
		stmt.execute();
		
		stmt.close();
	}

}
