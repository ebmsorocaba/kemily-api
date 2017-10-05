package jdbc.dao.historicoOcorrencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO historico_ocorrencia (data_ocorrencia, ra_aluno, nome_aluno, descricao_ocorrencia) VALUES (?, ?, ?, ?)");
			
			stmt.setTimestamp(1, histOcorrencia.getDataOcorrencia());
			stmt.setInt(2, histOcorrencia.getRaAluno());
			stmt.setString(3, histOcorrencia.getNomeAluno());
			stmt.setString(4, histOcorrencia.getDescricaoOcorrencia());
			
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
			
			ho.setDataOcorrencia(rs.getTimestamp("data_ocorrencia"));
			ho.setRaAluno(rs.getInt("ra_aluno"));
			ho.setNomeAluno(rs.getString("nome_aluno"));
			ho.setDescricaoOcorrencia(rs.getString("descricao_ocorrencia"));
			
			ocorrencias.add(ho);
		}
		
		rs.close();
		stmt.close();
		
		return ocorrencias;
	}
	
	public HistoricoOcorrencia getHistoricoOcorrencia(Timestamp data, int raAluno) {
		HistoricoOcorrencia ho = new HistoricoOcorrencia();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM historico_ocorrencia WHERE data_ocorrencia = ? AND ra_aluno = ?");
			
			stmt.setTimestamp(1, data);
			stmt.setInt(2, raAluno);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				ho.setDataOcorrencia(rs.getTimestamp("data_ocorrencia"));
				ho.setRaAluno(rs.getInt("ra_aluno"));
				ho.setNomeAluno(rs.getString("nome_aluno"));
				ho.setDescricaoOcorrencia(rs.getString("descricao_ocorrencia"));
			}
			
			stmt.close();
		} catch (SQLException ex) {
			 System.out.println(ex.toString());
		}
		
		return ho;
	}
	
	public List<HistoricoOcorrencia> getListaByData(Timestamp data) throws SQLException {
		List<HistoricoOcorrencia> ocorrenciasByData = new ArrayList<HistoricoOcorrencia>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM historico_ocorrencia WHERE data_ocorrencia::date = ?");
		
		stmt.setTimestamp(1, data);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HistoricoOcorrencia ho = new HistoricoOcorrencia();
			
			ho.setDataOcorrencia(rs.getTimestamp("data_ocorrencia"));
			ho.setRaAluno(rs.getInt("ra_aluno"));
			ho.setNomeAluno(rs.getString("nome_aluno"));
			ho.setDescricaoOcorrencia(rs.getString("descricao_ocorrencia"));
			
			ocorrenciasByData.add(ho);
		}
		
		rs.close();
		stmt.close();
		
		return ocorrenciasByData;
	}
	
	public List<HistoricoOcorrencia> getListaByAluno(int raAluno) throws SQLException {
		List<HistoricoOcorrencia> ocorrenciasByAluno = new ArrayList<HistoricoOcorrencia>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM historico_ocorrencia WHERE ra_aluno = ?");
		
		stmt.setInt(1, raAluno);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			HistoricoOcorrencia ho = new HistoricoOcorrencia();
			
			ho.setDataOcorrencia(rs.getTimestamp("data_ocorrencia"));
			ho.setRaAluno(rs.getInt("ra_aluno"));
			ho.setNomeAluno(rs.getString("nome_aluno"));
			ho.setDescricaoOcorrencia(rs.getString("descricao_ocorrencia"));
			
			ocorrenciasByAluno.add(ho);
		}
		
		rs.close();
		stmt.close();
		
		return ocorrenciasByAluno;
	}
	 
	public void excluir(Timestamp data, int raAluno) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM historico_ocorrencia WHERE data_ocorrencia::date = ? AND ra_aluno = ?");
			
			stmt.setTimestamp(1, data);
			stmt.setInt(2, raAluno);
			
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException ex) {
            System.out.println(ex.toString());
        }
	}
	
	public void excluirByData(Timestamp data) {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM historico_ocorrencia WHERE data_ocorrencia::date = ?");
			
			stmt.setTimestamp(1, data);
			
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
	
	public void altera(Timestamp data, int raAluno, HistoricoOcorrencia ho) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE historico_ocorrencia SET descricao_ocorrencia = ? WHERE data_ocorrencia = ? AND ra_aluno = ?");
		
		stmt.setString(1, ho.getDescricaoOcorrencia());
		stmt.setTimestamp(2, data);
		stmt.setInt(3, raAluno);
		
		stmt.execute();
		
		stmt.close();
	}
	
	public static void main(String args[]) throws SQLException {
		HistoricoOcorrencia ho = new HistoricoOcorrencia();
		ho.setDataOcorrencia(Timestamp.valueOf(LocalDateTime.now()));
		ho.setRaAluno(1);
		ho.setNomeAluno("Kemily");
		ho.setDescricaoOcorrencia("Teste4");
		
		HistoricoOcorrenciaDAO hoDAO = new HistoricoOcorrenciaDAO();
		
		System.out.println(ho.getDataOcorrencia().toString());
		
		hoDAO.adiciona(ho);
	}
}
