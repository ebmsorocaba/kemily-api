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
	private FormaPagamentoDAO daoFormaPgto = new FormaPagamentoDAO();

	public PagamentoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Pagamento pagamento) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO pagamento (id, valor_pago, vencimento, data_pgto, cpf_associado, forma_pgto_efetuada) VALUES (?, ?, ?, ?, ?, ?)");
		// seta os valores
		stmt.setInt(1,pagamento.getId());
		stmt.setDouble(2,pagamento.getValorPago());
		stmt.setDate(3,pagamento.getVencimento());
		stmt.setDate(4,pagamento.getDataPgto());
		stmt.setString(5,pagamento.getFormaPgto().getAssociado().getCpf());
		stmt.setString(6,pagamento.getFormaPgto().getFormaPagamento());
		
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
			pagamento.setVencimento(rs.getDate("vencimento"));
			pagamento.setDataPgto(rs.getDate("data_pgto"));
			pagamento.setFormaPgto(daoFormaPgto.getFormaPgto(rs.getString("cpf_associado"), rs.getString("forma_pgto_efetuada")));

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
				pagamento.setVencimento(rs.getDate("vencimento"));
				pagamento.setDataPgto(rs.getDate("data_pgto"));
				pagamento.setFormaPgto(daoFormaPgto.getFormaPgto(rs.getString("cpf_associado"), rs.getString("forma_pgto_efetuada")));
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


	public void altera(Pagamento pagamento) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE pagamento SET valor_pago=?, vencimento=?, data_pgto=?, cpf_associado=?, forma_pgto=? WHERE id=?");

		
		stmt.setDouble(1,pagamento.getValorPago());
		stmt.setDate(2,pagamento.getVencimento());
		stmt.setDate(3,pagamento.getDataPgto());
		stmt.setString(4,pagamento.getFormaPgto().getAssociado().getCpf());
		stmt.setString(5,pagamento.getFormaPgto().getFormaPagamento());
		stmt.setInt(6,pagamento.getId());
		
		stmt.execute();
		stmt.close();
	}
}
