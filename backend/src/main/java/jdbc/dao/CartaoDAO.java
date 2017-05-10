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
	private AssociadoDAO daoAssociado = new AssociadoDAO();

	public CartaoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Cartao cartao) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO cartao (numero, bandeira, atual, cpf_associado) VALUES (?, ?, ?, ?)");
		// seta os valores
		stmt.setLong(1, cartao.getNumero());
		stmt.setString(2, cartao.getBandeira());
		stmt.setBoolean(3, cartao.isAtual());
		stmt.setLong(4, cartao.getAssociado().getCpf());
		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Cartao> getLista() throws SQLException {

		List<Cartao> cartoes = new ArrayList<Cartao>();

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM cartao");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			// criando o objeto Aluno
			Cartao cartao = new Cartao();
			cartao.setNumero(rs.getLong("numero"));
			cartao.setBandeira(rs.getString("bandeira"));
			cartao.setAtual(rs.getBoolean("atual"));
			cartao.setAssociado(daoAssociado.getAssociado(rs.getLong("cpf_associado")));
			// adicionando o objeto à lista
			cartoes.add(cartao);
		}
		rs.close();
		stmt.close();
		return cartoes;

	}

	public Cartao getCartao(Long search) throws SQLException {

		Cartao cartao = new Cartao();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM cartao WHERE " + "numero = ?");

			stmt.setLong(1, search); //Note que essa variavel é passada da função principal
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				cartao.setNumero(rs.getLong("numero"));
				cartao.setBandeira(rs.getString("bandeira"));
				cartao.setAtual(rs.getBoolean("atual"));
				cartao.setAssociado(daoAssociado.getAssociado(rs.getLong("cpf_associado")));
			}
		}
		
	    catch (SQLException ex) {
	      System.out.println(ex.toString());
	    }

		return (cartao);
	}


	public void excluir(Long search) {

        try {
        	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM cartao WHERE numero = ?");

            stmt.setLong(1, search);

            stmt.execute();

        } catch (SQLException ex) {
             System.out.println(ex.toString());
        }
    }


	public void altera(Cartao cartao) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE cartao SET bandeira=?, atual=?, cpf_associado=? WHERE numero=?");
			stmt.setString(2, cartao.getBandeira());
			stmt.setBoolean(3, cartao.isAtual());
			stmt.setLong(4, cartao.getAssociado().getCpf());
			stmt.setLong(4, cartao.getNumero());

			stmt.execute();
			stmt.close();
		}

}
