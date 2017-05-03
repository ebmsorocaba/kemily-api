package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Associado;

public class AssociadoDAO {
	// a conexão com o banco de dados
	private Connection connection;
	private CartaoDAO daoCartao = new CartaoDAO();

	public AssociadoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Associado associado) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO tb_associados (cpf, nome, celular, email, forma_pgto, num_cartao, valor_atual, venc_atual) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		// seta os valores
		stmt.setLong(1,associado.getCpf());
		stmt.setString(2,associado.getNome());
		stmt.setLong(3,associado.getCelular());
		stmt.setString(4,associado.getEmail());
		stmt.setString(5,associado.getFormaPgto());
		stmt.setLong(6,associado.getCartao().getNumero());
		stmt.setDouble(7,associado.getValorAtual());
		stmt.setDate(8,associado.getVencAtual());
		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Associado> getLista() throws SQLException {

		List<Associado> associados = new ArrayList<Associado>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_associados");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// criando o objeto Associado
			Associado associado = new Associado(null, null, null, null, null, null, -1, null);

			associado.setCpf(rs.getLong("cpf"));
			associado.setNome(rs.getString("nome"));
			associado.setCelular(rs.getLong("celular"));
			associado.setEmail(rs.getString("email"));
			associado.setFormaPgto(rs.getString("forma_pgto"));
			associado.setCartao(daoCartao.getCartao(rs.getLong("num_cartao")));
			associado.setValorAtual(rs.getDouble("valor_atual"));
			associado.setVencAtual(rs.getDate("venc_atual"));

			// adicionando o objeto à lista
			associados.add(associado);

		}

		rs.close();
		stmt.close();
		return associados;

	}

	public Associado getAssociado(Long search) throws SQLException {

		Associado associado = new Associado(null, null, null, null, null, null, 0, null);

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_associados WHERE " + "cpf = ?");
			stmt.setLong(1, search); //Note que essa variavel é passada da função principal
      ResultSet rs = stmt.executeQuery();
      if (rs.next() == true) {
        associado.setCpf(rs.getLong("cpf"));
				associado.setNome(rs.getString("nome"));
				associado.setCelular(rs.getLong("celular"));
				associado.setEmail(rs.getString("email"));
				associado.setFormaPgto(rs.getString("forma_pgto"));
				associado.setCartao(daoCartao.getCartao(rs.getLong("num_cartao")));
				associado.setValorAtual(rs.getDouble("valor_atual"));
				associado.setVencAtual(rs.getDate("venc_atual"));
      }
    }
    catch (SQLException ex) {
    	System.out.println(ex.toString());
    }

  	return (associado);

	}


	public void excluir(Long search) {
    try {
    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM tb_associados WHERE cpf = ?");
      stmt.setLong(1, search);
      stmt.execute();
    }
		catch (SQLException ex) {
      System.out.println(ex.toString());
    }
  }


	public void altera(Associado associado) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE tb_associados SET nome=?, celular=?, email=?, forma_pgto=?, num_cartao=?, valor_atual=?, venc_atual=? WHERE cpf=?");

		stmt.setString(1,associado.getNome());
		stmt.setLong(2,associado.getCelular());
		stmt.setString(3,associado.getEmail());
		stmt.setString(4,associado.getFormaPgto());
		stmt.setLong(5,associado.getCartao().getNumero());
		stmt.setDouble(6,associado.getValorAtual());
		stmt.setDate(7,(Date)associado.getVencAtual());
		stmt.setLong(8,associado.getCpf());

		stmt.execute();
		stmt.close();
	}

}