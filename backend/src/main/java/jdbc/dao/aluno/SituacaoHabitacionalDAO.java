package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.SituacaoHabitacional;

public class SituacaoHabitacionalDAO {
	private Connection connection;
	private AparelhosEletronicosDAO aparelhosEletronicosDao;
	private AlunoDAO alunoDao;
	
	public SituacaoHabitacionalDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.aparelhosEletronicosDao = new AparelhosEletronicosDAO();
		this.alunoDao = new AlunoDAO();
	}
	
	public void adicionar(SituacaoHabitacional situacaoHabitacional) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO situacao_habitacional(ra_aluno, situacao, esgoto, rede_eletrica, asfalto, numero_comodos, alvenaria, madeira, area_irregular, id_aparelhos_eletronicos) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		stmt.setInt(1, situacaoHabitacional.getAluno().getRa());
		stmt.setString(2, situacaoHabitacional.getSituacao());
		stmt.setBoolean(3, situacaoHabitacional.getEsgoto());
		stmt.setBoolean(4, situacaoHabitacional.getRedeEletrica());
		stmt.setBoolean(5, situacaoHabitacional.getAsfalto());
		stmt.setInt(6, situacaoHabitacional.getNumeroComodos());
		stmt.setBoolean(7, situacaoHabitacional.getAlvenaria());
		stmt.setBoolean(8, situacaoHabitacional.getMadeira());
		stmt.setBoolean(9, situacaoHabitacional.getAreaIrregular());
		stmt.setInt(10, situacaoHabitacional.getAparelhosEletronicos().getId());
		
		stmt.execute();
		stmt.close();
	}
	
	public List<SituacaoHabitacional> getLista() throws SQLException {
		List<SituacaoHabitacional> listaSituacaoHabitacional = new ArrayList<SituacaoHabitacional>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM situacao_habitacional");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			SituacaoHabitacional sh = new SituacaoHabitacional();
			
			sh.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
			sh.setSituacao(rs.getString("situacao"));
			sh.setEsgoto(rs.getBoolean("esgoto"));
			sh.setRedeEletrica(rs.getBoolean("rede_eletrica"));
			sh.setAsfalto(rs.getBoolean("asfalto"));
			sh.setNumeroComodos(rs.getInt("numero_comodos"));
			sh.setAlvenaria(rs.getBoolean("alvenaria"));
			sh.setMadeira(rs.getBoolean("madeira"));
			sh.setAreaIrregular(rs.getBoolean("area_irregular"));
			sh.setAparelhosEletronicos(aparelhosEletronicosDao.getAparelhosEletronicos(rs.getInt("id_aparelhos_eletronicos")));
			
			listaSituacaoHabitacional.add(sh);
		}
		
		rs.close();
		stmt.close();
		
		return listaSituacaoHabitacional;
	}
	
	public SituacaoHabitacional getSituacaoHabitacional(int ra) throws SQLException {
		SituacaoHabitacional sh = new SituacaoHabitacional();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM situacao_habitacional WHERE ra_aluno = ?");
			stmt.setInt(1, ra);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				sh.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
				sh.setSituacao(rs.getString("situacao"));
				sh.setEsgoto(rs.getBoolean("esgoto"));
				sh.setRedeEletrica(rs.getBoolean("rede_eletrica"));
				sh.setAsfalto(rs.getBoolean("asfalto"));
				sh.setNumeroComodos(rs.getInt("numero_comodos"));
				sh.setAlvenaria(rs.getBoolean("alvenaria"));
				sh.setMadeira(rs.getBoolean("madeira"));
				sh.setAreaIrregular(rs.getBoolean("area_irregular"));
				sh.setAparelhosEletronicos(aparelhosEletronicosDao.getAparelhosEletronicos(rs.getInt("id_aparelhos_eletronicos")));
			}
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return sh;
	}
	
	public void excluir(int ra) {
		try {
			
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM situacao_habitacional USING APARELHOS_ELETRONICOS WHERE situacaoHabitacional.ID_APARELHOS_ELETRONICOS = APARELHOS_ELETRONICOS.ID AND situacaoHabitacional.RA_ALUNO = ?");
			stmt.setInt(1, ra);
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(SituacaoHabitacional situacaoHabitacional, int ra) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE situacao_habitacional SET situacao = ?, esgoto = ?, rede_eletrica = ?, asfalto = ?, numero_comodos = ?, alvenaria = ?, madeira = ?, area_irregular = ?, id_aparelhos_eletronicos = ? WHERE ra_aluno = ?");
		
		stmt.setString(1, situacaoHabitacional.getSituacao());
		stmt.setBoolean(2, situacaoHabitacional.getEsgoto());
		stmt.setBoolean(3, situacaoHabitacional.getRedeEletrica());
		stmt.setBoolean(4, situacaoHabitacional.getAsfalto());
		stmt.setInt(5, situacaoHabitacional.getNumeroComodos());
		stmt.setBoolean(6, situacaoHabitacional.getAlvenaria());
		stmt.setBoolean(7, situacaoHabitacional.getMadeira());
		stmt.setBoolean(8, situacaoHabitacional.getAreaIrregular());
		stmt.setInt(9, situacaoHabitacional.getAparelhosEletronicos().getId());
		stmt.setInt(10, ra);
		
		stmt.execute();
		stmt.close();
	}
}
