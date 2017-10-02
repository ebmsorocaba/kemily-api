package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.AparelhosEletronicos;

public class AparelhosEletronicosDAO {
	private Connection connection;
	
	public AparelhosEletronicosDAO() throws SQLException {
		connection = ConnectionFactory.getConnection();
	}
	
	public AparelhosEletronicos adiciona(AparelhosEletronicos aparelhosEletronicos) throws SQLException {
		try(
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO aparelhos_eletronicos(televisao, tv_assinatura, computador, notebook, fogao, geladeira, microondas, maquina_de_lavar, maquina_de_secar, telefone_fixo, celular) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		){
			stmt.setBoolean(1, aparelhosEletronicos.isTelevisao());
			stmt.setBoolean(2, aparelhosEletronicos.isTvAssinatura());
			stmt.setBoolean(3, aparelhosEletronicos.isComputador());
			stmt.setBoolean(4, aparelhosEletronicos.isNotebook()); 
			stmt.setBoolean(5, aparelhosEletronicos.isFogao());
			stmt.setBoolean(6, aparelhosEletronicos.isGeladeira());
			stmt.setBoolean(7, aparelhosEletronicos.isMicroondas());
			stmt.setBoolean(8, aparelhosEletronicos.isMaquinaLavar());
			stmt.setBoolean(9, aparelhosEletronicos.isMaquinaSecar());
			stmt.setBoolean(10, aparelhosEletronicos.isTelefoneFixo());
			stmt.setBoolean(11, aparelhosEletronicos.isCelular());
			
			int key = stmt.executeUpdate();
			
			if(key == 0) {
				aparelhosEletronicos.setId(-1);
				throw new SQLException("Falha na criação do AparelhosEletronicos, linha nao alterada");
			}
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					aparelhosEletronicos.setId(generatedKeys.getInt(1));
				} else {
					aparelhosEletronicos.setId(-1);
					throw new SQLException("Falha na criação do AparelhosEletronicos, ID nao retornado");
				}
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}
			
			stmt.close();
		}
		
		return aparelhosEletronicos;
	}
	
	public List<AparelhosEletronicos> getLista() throws SQLException {
		List<AparelhosEletronicos> listaAparelhosEletronicos = new ArrayList<AparelhosEletronicos>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aparelhos_eletronicos");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			AparelhosEletronicos ae = new AparelhosEletronicos();
			
			ae.setId(rs.getInt("id"));
			ae.setTelevisao(rs.getBoolean("televisao"));
			ae.setTvAssinatura(rs.getBoolean("tv_assinatura"));
			ae.setComputador(rs.getBoolean("computador"));
			ae.setNotebook(rs.getBoolean("notebook"));
			ae.setFogao(rs.getBoolean("fogao"));
			ae.setGeladeira(rs.getBoolean("geladeira"));
			ae.setMicroondas(rs.getBoolean("microondas"));
			ae.setMaquinaLavar(rs.getBoolean("maquina_de_lavar"));
			ae.setMaquinaSecar(rs.getBoolean("maquina_de_secar"));
			ae.setTelefoneFixo(rs.getBoolean("telefone_fixo"));
			ae.setCelular(rs.getBoolean("celular"));
			
			listaAparelhosEletronicos.add(ae);
		}
		
		rs.close();
		stmt.close();
		
		return listaAparelhosEletronicos;
	}
	
	public AparelhosEletronicos getAparelhosEletronicos(int id) throws SQLException {
		AparelhosEletronicos ae = new AparelhosEletronicos();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aparelhos_eletronicos where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next() == true) {
				ae.setId(id);
				ae.setTelevisao(rs.getBoolean("televisao"));
				ae.setTvAssinatura(rs.getBoolean("tv_assinatura"));
				ae.setComputador(rs.getBoolean("computador"));
				ae.setNotebook(rs.getBoolean("notebook"));
				ae.setFogao(rs.getBoolean("fogao"));
				ae.setGeladeira(rs.getBoolean("geladeira"));
				ae.setMicroondas(rs.getBoolean("microondas"));
				ae.setMaquinaLavar(rs.getBoolean("maquina_de_lavar"));
				ae.setMaquinaSecar(rs.getBoolean("maquina_de_secar"));
				ae.setTelefoneFixo(rs.getBoolean("telefone_fixo"));
				ae.setCelular(rs.getBoolean("celular"));
			}
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return ae;
	}
	
	public void excluir(int id) {
		try  {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM aparelhos_eletronicos where id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(AparelhosEletronicos aparelhosEletronicos, int id) throws SQLException { 
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE aparelhos_eletronicos SET televisao = ?, tv_assinatura = ?, computador = ?, notebook = ?, fogao = ?, geladeira = ?, microondas = ?, maquina_de_lavar = ?, maquina_de_secar = ?, telefone_fixo = ?, celular = ? WHERE id = ?");
		
		stmt.setBoolean(1, aparelhosEletronicos.isTelevisao());
		stmt.setBoolean(2, aparelhosEletronicos.isTvAssinatura());
		stmt.setBoolean(3, aparelhosEletronicos.isComputador());
		stmt.setBoolean(4, aparelhosEletronicos.isNotebook()); 
		stmt.setBoolean(5, aparelhosEletronicos.isFogao());
		stmt.setBoolean(6, aparelhosEletronicos.isGeladeira());
		stmt.setBoolean(7, aparelhosEletronicos.isMicroondas());
		stmt.setBoolean(8, aparelhosEletronicos.isMaquinaLavar());
		stmt.setBoolean(9, aparelhosEletronicos.isMaquinaSecar());
		stmt.setBoolean(10, aparelhosEletronicos.isTelefoneFixo());
		stmt.setBoolean(11, aparelhosEletronicos.isCelular());
		stmt.setInt(12, id);
		
		stmt.execute();
		stmt.close();
	}
}
