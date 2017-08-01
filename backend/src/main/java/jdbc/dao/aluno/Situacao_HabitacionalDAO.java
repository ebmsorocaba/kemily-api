package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.Aparelhos_Eletronicos;
import model.aluno.Situacao_Habitacional;

public class Situacao_HabitacionalDAO {
	private Connection connection;
	private Aparelhos_EletronicosDAO aparelhosEletronicosDao;
	private AlunoDAO alunoDao;
	
	public Situacao_HabitacionalDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.aparelhosEletronicosDao = new Aparelhos_EletronicosDAO();
		this.alunoDao = new AlunoDAO();
	}
	
	public void adicionar(Situacao_Habitacional situacao_Habitacional) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO situacao_habitacional(ra_aluno, situacao, esgoto, rede_eletrica, asfalto, numero_comodos, alvenaria, madeira, area_irregular, id_aparelhos_eletronicos) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		stmt.setInt(1, situacao_Habitacional.getAluno().getRa());
		stmt.setString(2, situacao_Habitacional.getSituacao());
		stmt.setBoolean(3, situacao_Habitacional.getEsgoto());
		stmt.setBoolean(4, situacao_Habitacional.getRede_eletrica());
		stmt.setBoolean(5, situacao_Habitacional.getAsfalto());
		stmt.setInt(6, situacao_Habitacional.getNumero_comodos());
		stmt.setBoolean(7, situacao_Habitacional.getAlvenaria());
		stmt.setBoolean(8, situacao_Habitacional.getMadeira());
		stmt.setBoolean(9, situacao_Habitacional.getArea_irregular());
		stmt.setInt(10, situacao_Habitacional.getAparelhos_eletronicos().getId());
		
		stmt.execute();
		stmt.close();
	}
	
	public List<Situacao_Habitacional> getSituacao_Habitacional() throws SQLException {
		List<Situacao_Habitacional> listaSituacaoHabitacional = new ArrayList<Situacao_Habitacional>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM situacao_habitacional");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Situacao_Habitacional sh = new Situacao_Habitacional();
			
			sh.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
			sh.setSituacao(rs.getString("situacao"));
			sh.setEsgoto(rs.getBoolean("esgoto"));
			sh.setRede_eletrica(rs.getBoolean("rede_eletrica"));
			sh.setAsfalto(rs.getBoolean("asfalto"));
			sh.setNumero_comodos(rs.getInt("numero_comodos"));
			sh.setAlvenaria(rs.getBoolean("alvenaria"));
			sh.setMadeira(rs.getBoolean("madeira"));
			sh.setArea_irregular(rs.getBoolean("area_irregular"));
			sh.setAparelhos_eletronicos(aparelhosEletronicosDao.getAparelhos_Eletronicos(rs.getInt("id_aparelhos_eletronicos")));
			
			listaSituacaoHabitacional.add(sh);
		}
		
		rs.close();
		stmt.close();
		
		return listaSituacaoHabitacional;
	}
	
	public Situacao_Habitacional getSituacao_Habitacional(int ra) throws SQLException {
		Situacao_Habitacional sh = new Situacao_Habitacional();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM situacao_habitacional WHERE ra_aluno = ?");
			stmt.setInt(1, ra);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				sh.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
				sh.setSituacao(rs.getString("situacao"));
				sh.setEsgoto(rs.getBoolean("esgoto"));
				sh.setRede_eletrica(rs.getBoolean("rede_eletrica"));
				sh.setAsfalto(rs.getBoolean("asfalto"));
				sh.setNumero_comodos(rs.getInt("numero_comodos"));
				sh.setAlvenaria(rs.getBoolean("alvenaria"));
				sh.setMadeira(rs.getBoolean("madeira"));
				sh.setArea_irregular(rs.getBoolean("area_irregular"));
				sh.setAparelhos_eletronicos(aparelhosEletronicosDao.getAparelhos_Eletronicos(rs.getInt("id_aparelhos_eletronicos")));
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return sh;
	}
	
	public void excluir(Situacao_Habitacional sh) {
		try {
			
			aparelhosEletronicosDao.excluir(sh.getAparelhos_eletronicos().getId());
			
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM situacao_habitacional WHERE ra_aluno = ?");
			stmt.setInt(1, sh.getAluno().getRa());
			
			stmt.execute();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(Situacao_Habitacional situacao_Habitacional, int ra) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE situacao_habitacional SET situacao = ?, esgoto = ?, rede_eletrica = ?, asfalto = ?, numero_comodos = ?, alvenaria = ?, madeira = ?, area_irregular = ?, id_aparelhos_eletronicos = ? WHERE ra_aluno = ?");
		
		stmt.setString(1, situacao_Habitacional.getSituacao());
		stmt.setBoolean(2, situacao_Habitacional.getEsgoto());
		stmt.setBoolean(3, situacao_Habitacional.getRede_eletrica());
		stmt.setBoolean(4, situacao_Habitacional.getAsfalto());
		stmt.setInt(5, situacao_Habitacional.getNumero_comodos());
		stmt.setBoolean(6, situacao_Habitacional.getAlvenaria());
		stmt.setBoolean(7, situacao_Habitacional.getMadeira());
		stmt.setBoolean(8, situacao_Habitacional.getArea_irregular());
		stmt.setInt(9, situacao_Habitacional.getAparelhos_eletronicos().getId());
		stmt.setInt(10, ra);
		
		stmt.execute();
		stmt.close();
	}
}
