package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.Aparelhos_Eletronicos;

public class Aparelhos_EletronicosDAO {
	private Connection connection;
	
	public Aparelhos_EletronicosDAO() throws SQLException {
		connection = ConnectionFactory.getConnection();
	}
	
	public Aparelhos_Eletronicos adiciona(Aparelhos_Eletronicos aparelhosEletronicos) throws SQLException {
		try(
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO aparelhos_eletronicos(televisao, tv_assinatura, computador, notebook, fogao, geladeira, microondas, tablet, maquina_de_lavar, maquina_de_secar, telefone_fixo, celular) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		){
			stmt.setBoolean(1, aparelhosEletronicos.getTelevisao());
			stmt.setBoolean(2, aparelhosEletronicos.getTv_assinatura());
			stmt.setBoolean(3, aparelhosEletronicos.getComputador());
			stmt.setBoolean(4, aparelhosEletronicos.getNotebook()); 
			stmt.setBoolean(5, aparelhosEletronicos.getFogao());
			stmt.setBoolean(6, aparelhosEletronicos.getGeladeira());
			stmt.setBoolean(7, aparelhosEletronicos.getMicroondas());
			stmt.setBoolean(8, aparelhosEletronicos.getTablet());
			stmt.setBoolean(9, aparelhosEletronicos.getMaquina_lavar());
			stmt.setBoolean(10, aparelhosEletronicos.getMaquina_secar());
			stmt.setBoolean(11, aparelhosEletronicos.getTelefone_fixo());
			stmt.setBoolean(12, aparelhosEletronicos.getCelular());
			
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
	
	public List<Aparelhos_Eletronicos> getLista() throws SQLException {
		List<Aparelhos_Eletronicos> listaAparelhosEletronicos = new ArrayList<Aparelhos_Eletronicos>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aparelhos_eletronicos");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Aparelhos_Eletronicos ae = new Aparelhos_Eletronicos();
			
			ae.setId(rs.getInt("id"));
			ae.setTelevisao(rs.getBoolean("televisao"));
			ae.setTv_assinatura(rs.getBoolean("tv_assinatura"));
			ae.setComputador(rs.getBoolean("computador"));
			ae.setNotebook(rs.getBoolean("notebook"));
			ae.setFogao(rs.getBoolean("fogao"));
			ae.setGeladeira(rs.getBoolean("geladeira"));
			ae.setMicroondas(rs.getBoolean("microondas"));
			ae.setTablet(rs.getBoolean("tablet"));
			ae.setMaquina_lavar(rs.getBoolean("maquina_de_lavar"));
			ae.setMaquina_secar(rs.getBoolean("maquina_de_secar"));
			ae.setTelefone_fixo(rs.getBoolean("telefone_fixo"));
			ae.setCelular(rs.getBoolean("celular"));
			
			listaAparelhosEletronicos.add(ae);
		}
		
		rs.close();
		stmt.close();
		
		return listaAparelhosEletronicos;
	}
	
	public Aparelhos_Eletronicos getAparelhos_Eletronicos(int id) throws SQLException {
		Aparelhos_Eletronicos ae = new Aparelhos_Eletronicos();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aparelhos_eletronicos where id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next() == true) {
				ae.setId(id);
				ae.setTelevisao(rs.getBoolean("televisao"));
				ae.setTv_assinatura(rs.getBoolean("tv_assinatura"));
				ae.setComputador(rs.getBoolean("computador"));
				ae.setNotebook(rs.getBoolean("notebook"));
				ae.setFogao(rs.getBoolean("fogao"));
				ae.setGeladeira(rs.getBoolean("geladeira"));
				ae.setMicroondas(rs.getBoolean("microondas"));
				ae.setTablet(rs.getBoolean("tablet"));
				ae.setMaquina_lavar(rs.getBoolean("maquina_de_lavar"));
				ae.setMaquina_secar(rs.getBoolean("maquina_de_secar"));
				ae.setTelefone_fixo(rs.getBoolean("telefone_fixo"));
				ae.setCelular(rs.getBoolean("celular"));
			}
			
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
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(Aparelhos_Eletronicos aparelhosEletronicos, int id) throws SQLException { 
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE aparelhos_eletronicos SET televisao = ?, tv_assinatura = ?, computador = ?, notebook = ?, fogao = ?, geladeira = ?, microondas = ?, tablet = ?, maquina_de_lavar = ?, maquina_de_secar = ?, telefone_fixo = ?, celular = ? WHERE id = ?");
		
		stmt.setBoolean(1, aparelhosEletronicos.getTelevisao());
		stmt.setBoolean(2, aparelhosEletronicos.getTv_assinatura());
		stmt.setBoolean(3, aparelhosEletronicos.getComputador());
		stmt.setBoolean(4, aparelhosEletronicos.getNotebook()); 
		stmt.setBoolean(5, aparelhosEletronicos.getFogao());
		stmt.setBoolean(6, aparelhosEletronicos.getGeladeira());
		stmt.setBoolean(7, aparelhosEletronicos.getMicroondas());
		stmt.setBoolean(8, aparelhosEletronicos.getTablet());
		stmt.setBoolean(9, aparelhosEletronicos.getMaquina_lavar());
		stmt.setBoolean(10, aparelhosEletronicos.getMaquina_secar());
		stmt.setBoolean(11, aparelhosEletronicos.getTelefone_fixo());
		stmt.setBoolean(12, aparelhosEletronicos.getCelular());
		stmt.setInt(13, id);
		
		stmt.execute();
		stmt.close();
	}
}
