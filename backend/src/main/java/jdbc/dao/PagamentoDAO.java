package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Pagamento;

public class PagamentoDAO {
	// a conexão com o banco de dados
	private Connection connection;

	AssociadoDAO associadoDAO = new AssociadoDAO();
	
	public PagamentoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Pagamento pagamento) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO pagamento (valor_pago, data_pgto, forma_pgto, cpf_associado) VALUES (?, ?, ?, ?)");
		// seta os valores
		
		stmt.setDouble(1,pagamento.getValorPago());
		stmt.setDate(2,pagamento.getDataPgto());
		stmt.setString(3, pagamento.getFormapgto());
		stmt.setString(4, pagamento.getCpfassociado());
		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Pagamento> getLista() throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM pagamento");
		ResultSet rs = stmt.executeQuery();

		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		while (rs.next()) {
			// criando o objeto Aluno
			Pagamento pagamento = new Pagamento();

			pagamento.setId(rs.getInt("id"));
			pagamento.setValorPago(rs.getDouble("valor_pago"));
			pagamento.setDataPgto(rs.getDate("data_pgto"));
			pagamento.setFormapgto(rs.getString("forma_pgto"));
			pagamento.setAssociado(associadoDAO.getAssociado(rs.getString("cpf_associado")));

			// adicionando o objeto à lista
			pagamentos.add(pagamento);

		}
		rs.close();
		stmt.close();
		return pagamentos;

	}

	public Pagamento getPagamento(Long search) throws SQLException {

		Pagamento pagamento = new Pagamento();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM pagamento WHERE " + "id = ?");

			stmt.setLong(1, search); //Note que essa variavel é passada da função principal
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				pagamento.setId(rs.getInt("id"));
				pagamento.setValorPago(rs.getDouble("valor_pago"));
				pagamento.setDataPgto(rs.getDate("data_pgto"));
				pagamento.setFormapgto(rs.getString("forma_pgto"));
				pagamento.setAssociado(associadoDAO.getAssociado(rs.getString("cpf_associado")));
				}
		}
	    catch (SQLException ex) {
	    	System.out.println(ex.toString());
	    }

		return (pagamento);
		
	}


	public void excluir(Long search) {

  	try {
    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM pagamento WHERE id = ?");

		stmt.setLong(1, search);
		stmt.execute();

    }
	catch (SQLException ex) {
		System.out.println(ex.toString());
    }
  	
  }


	public void altera(Pagamento pagamento, int id) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE pagamento SET valor_pago=?, data_pgto=?, forma_pgto=?, cpf_associado=? WHERE id=?");

		
		stmt.setDouble(1,pagamento.getValorPago());
		stmt.setDate(2,pagamento.getDataPgto());
		stmt.setString(3, pagamento.getFormapgto());
		stmt.setString(4, pagamento.getCpfassociado());
		stmt.setInt(5,id);
		
		stmt.execute();
		stmt.close();
	}
}
