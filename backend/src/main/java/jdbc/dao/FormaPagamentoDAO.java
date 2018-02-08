package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.FormaPagamento;

public class FormaPagamentoDAO {

	private Connection connection;
	private AssociadoDAO daoAssociado = new AssociadoDAO();

	public FormaPagamentoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public void adiciona(FormaPagamento formaPgto) throws SQLException {
		// prepared statement para inserção
		
		formaPgto.setFormaPagamento("Dinheiro");
		formaPgto.setAtual(true);
		System.out.println( formaPgto.getAssociado().getCpf() + " " + formaPgto.getFormaPagamento() + " " + formaPgto.getAtual());
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual) VALUES (?, ?, ?)");
		// seta os valores
		stmt.setString(1, formaPgto.getAssociado().getCpf());
		stmt.setString(2, formaPgto.getFormaPagamento());
		stmt.setBoolean(3, formaPgto.getAtual());
		
		// executa
		stmt.execute();
		
		formaPgto.setFormaPagamento("Boleto");
		formaPgto.setAtual(false);
		stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual) VALUES (?, ?, ?)");
		// seta os valores
		stmt.setString(1, formaPgto.getAssociado().getCpf());
		stmt.setString(2, formaPgto.getFormaPagamento());
		stmt.setBoolean(3, formaPgto.getAtual());
		
		// executa
		stmt.execute();
		
		formaPgto.setFormaPagamento("Cartão");
		stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO associado_forma_pagamento (cpf_associado, forma_pgto, atual) VALUES (?, ?, ?)");
		// seta os valores
		stmt.setString(1, formaPgto.getAssociado().getCpf());
		stmt.setString(2, formaPgto.getFormaPagamento());
		stmt.setBoolean(3, formaPgto.getAtual());
		
		// executa
		stmt.execute();
		
		
		
		stmt.close();
	}
	
	public List<FormaPagamento> getLista() throws SQLException {

		List<FormaPagamento> formasPgto = new ArrayList<FormaPagamento>();

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM associado_forma_pagamento");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			// criando o objeto Aluno
			FormaPagamento formaPgto = new FormaPagamento();
			formaPgto.setAssociado(daoAssociado.getAssociado(rs.getString("cpf_associado")));
			formaPgto.setFormaPagamento(rs.getString("forma_pgto"));
			formaPgto.setAtual(rs.getBoolean("atual"));
			
			// adicionando o objeto à lista
			formasPgto.add(formaPgto);
		}
		rs.close();
		stmt.close();
		return formasPgto;

	}

	public FormaPagamento getFormaPgtoAtual(String search) throws SQLException {

		FormaPagamento formaPgto = new FormaPagamento();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM associado_forma_pagamento WHERE " + "cpf_associado = ? AND " + "atual = ?");

			stmt.setString(1, search);//Note que essa variavel é passada da função principal
			stmt.setBoolean(2, true);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				formaPgto.setAssociado(daoAssociado.getAssociado(rs.getString("cpf_associado")));
				formaPgto.setFormaPagamento(rs.getString("forma_pgto"));
				formaPgto.setAtual(rs.getBoolean("atual"));
			}
		}
		
	    catch (SQLException ex) {
	      System.out.println(ex.toString());
	    }

		return (formaPgto);
	}
	
	public FormaPagamento getFormaPgto(String search, String formaPgtoSearch) throws SQLException {

		FormaPagamento formaPgto = new FormaPagamento();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM associado_forma_pagamento WHERE " + "cpf_associado = ? AND " + "forma_pgto = ?");

			stmt.setString(1, search);
			stmt.setString(2, formaPgtoSearch);//Note que essa variavel é passada da função principal
			
			System.out.println(search + " " + formaPgtoSearch);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				formaPgto.setAssociado(daoAssociado.getAssociado(rs.getString("cpf_associado")));
				formaPgto.setFormaPagamento(rs.getString("forma_pgto"));
				formaPgto.setAtual(rs.getBoolean("atual"));
			}
		}
		
	    catch (SQLException ex) {
	      System.out.println(ex.toString());
	    }

		return (formaPgto);
	}

/*
	public void excluir(Long search) {

        try {
        	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM cartao WHERE numero = ?");

            stmt.setLong(1, search);

            stmt.execute();

        } catch (SQLException ex) {
             System.out.println(ex.toString());
        }
    }

*/
/*
	public void altera(Cartao cartao) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE cartao SET bandeira=?, atual=?, cpf_associado=? WHERE numero=?");
			stmt.setString(2, cartao.getBandeira());
			stmt.setBoolean(3, cartao.isAtual());
			stmt.setString(4, cartao.getAssociado().getCpf());
			stmt.setLong(4, cartao.getNumero());

			stmt.execute();
			stmt.close();
		}
*/
}
