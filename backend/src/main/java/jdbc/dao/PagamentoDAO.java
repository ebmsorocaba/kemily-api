package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Pagamento;

@SuppressWarnings("unused")
public class PagamentoDAO {
	// a conexão com o banco de dados
	private Connection connection;
	private CartaoDAO daoCartao = new CartaoDAO();
	private AssociadoDAO daoAssociado = new AssociadoDAO();

	public PagamentoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Pagamento pagamento) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO tb_pagamentos (id, cpf, forma_pgto, num_cartao, cod_boleto, valor_pago, vencimento, data_pgto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		// seta os valores
		stmt.setLong(1,pagamento.getId());
		stmt.setLong(2,pagamento.getAssociado().getCpf());
		stmt.setString(3,pagamento.getFormaPgto());
		stmt.setLong(4,pagamento.getCartao().getNumero());
		stmt.setString(5,pagamento.getCodBoleto());
		stmt.setDouble(6,pagamento.getValorPago());
		stmt.setDate(7,pagamento.getVencimento());
		stmt.setDate(8,pagamento.getDataPgto());


		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Pagamento> getLista() throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_pagamentos");
		ResultSet rs = stmt.executeQuery();

		System.out.println("Entrou DAO");
		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		while (rs.next()) {
			// criando o objeto Aluno
			Pagamento pagamento = new Pagamento(null, null, null, 0, null, null, null, null);

			pagamento.setId(rs.getLong("id"));
			pagamento.setAssociado(daoAssociado.getAssociado(rs.getLong("cpf")));
			pagamento.setFormaPgto(rs.getString("forma_pgto"));
			pagamento.setCartao(daoCartao.getCartao(rs.getLong("num_cartao")));
			pagamento.setCodBoleto(rs.getString("cod_boleto"));
			pagamento.setValorPago(rs.getDouble("valor_pago"));
			pagamento.setVencimento(rs.getDate("vencimento"));
			pagamento.setDataPgto(rs.getDate("data_pgto"));

			// adicionando o objeto à lista
			pagamentos.add(pagamento);

		}
		rs.close();
		stmt.close();
		return pagamentos;

	}

	public Pagamento getPagamento(Long search) throws SQLException {

		Pagamento pagamento = new Pagamento(null, null, null, 0, null, null, null, null);

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM tb_pagamentos WHERE " + "id = ?");

			stmt.setLong(1, search); //Note que essa variavel é passada da função principal
      ResultSet rs = stmt.executeQuery();

      if (rs.next() == true) {
        pagamento.setId(rs.getLong("id"));
  			pagamento.setAssociado(daoAssociado.getAssociado(rs.getLong("cpf")));
  			pagamento.setFormaPgto(rs.getString("forma_pgto"));
  			pagamento.setCartao(daoCartao.getCartao(rs.getLong("num_cartao")));
  			pagamento.setCodBoleto(rs.getString("cod_boleto"));
  			pagamento.setValorPago(rs.getDouble("valor_pago"));
  			pagamento.setVencimento(rs.getDate("vencimento"));
  			pagamento.setDataPgto(rs.getDate("data_pgto"));
      }
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }

    return (pagamento);
		
	}


	public void excluir(Long search) {

  	try {
    	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM tb_pagamentos WHERE id = ?");

      stmt.setLong(1, search);
			stmt.execute();

    }
		catch (SQLException ex) {
      System.out.println(ex.toString());
    }
  }


	public void altera(Pagamento pagamento) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE tb_pagamentos SET cpf=?, forma_pgto=?, num_cartao=?, cod_boleto=?, valor_pago=?, vencimento=?, data_pgto=? WHERE cpf=?");

		stmt.setLong(1,pagamento.getAssociado().getCpf());
		stmt.setString(2,pagamento.getFormaPgto());
		stmt.setLong(3,pagamento.getCartao().getNumero());
		stmt.setString(4,pagamento.getCodBoleto());
		stmt.setDouble(5,pagamento.getValorPago());
		stmt.setDate(6,pagamento.getVencimento());
		stmt.setDate(7,pagamento.getDataPgto());
		stmt.setLong(8,pagamento.getId());

		stmt.execute();
		stmt.close();
	}
}
