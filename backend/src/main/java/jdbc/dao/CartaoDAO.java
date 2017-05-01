package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Cartao;

public class CartaoDAO {

	private Connection connection;
	
	public CartaoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public void adiciona(Cartao cartao) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO tb_cartao (numero,cpf,bandeira,atual) VALUES (?, ?, ?, ?)");
		// seta os valores
		stmt.setLong(1,cartao.getNumero());
		stmt.setLong(2,cartao.getCpf());
		stmt.setString(3,cartao.getBandeira());
		stmt.setBoolean(4,cartao.isAtual());
		// executa
		stmt.execute();
		stmt.close();
	}
	
	public List<Cartao> getLista() throws SQLException {
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_cartao");
		ResultSet rs = stmt.executeQuery();
		System.out.println("Entrou DAO");
		List<Cartao> cartoes = new ArrayList<Cartao>();
		while (rs.next()) {
			// criando o objeto Aluno
			Cartao cartao = new Cartao(null,null,"",false);
			cartao.setNumero(rs.getLong("numero"));
			cartao.setCpf(rs.getLong("cpf"));
			cartao.setBandeira(rs.getString("bandeira"));
			cartao.setAtual(rs.getBoolean("atual"));
			// adicionando o objeto à lista
			cartoes.add(cartao);
		
		}
		rs.close();
		stmt.close();
		return cartoes;
		
	}
	
	public Cartao getCartao(Long search) throws SQLException {
		
		Cartao cartao = new Cartao(null,null,"",false);
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_cartao WHERE " + "numero = ?");
            
			stmt.setLong(1, search); //Note que essa variavel é passada da função principal
            ResultSet rs = stmt.executeQuery();
           
            if (rs.next() == true) {
            	cartao.setNumero(rs.getLong("numero"));
				cartao.setCpf(rs.getLong("cpf"));
				cartao.setBandeira(rs.getString("bandeira"));
				cartao.setAtual(rs.getBoolean("atual"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
		
		
        return (cartao);
}

	
	public void excluir(Long search) {
        
        try {
        	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM tb_cartao WHERE numero = ?");
            
            stmt.setLong(1, search);
                      
            stmt.execute();
            
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }

	
	public void altera(Cartao cartao) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE tb_cartao SET cpf=?, bandeira=?, atual=? WHERE numero=?");
			stmt.setLong(1, cartao.getCpf());
			stmt.setString(2, cartao.getBandeira());
			stmt.setBoolean(3, cartao.isAtual());
			stmt.setLong(4, cartao.getNumero());
			
			stmt.execute();
			stmt.close();
		}
	
}
