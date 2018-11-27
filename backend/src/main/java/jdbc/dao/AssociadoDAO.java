package jdbc.dao;

import java.sql.Connection;

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
	
	
	public AssociadoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public void adiciona(Associado associado) throws SQLException {
		// prepared statement para inserção
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement(
				"INSERT INTO associado (cpf, nome, celular, email, valor_atual, venc_atual) VALUES (?, ?, ?, ?, ?, ?)");
		// seta os valores
		stmt.setString(1, associado.getCpf());
		stmt.setString(2, associado.getNome());
		stmt.setString(3, associado.getCelular());
		stmt.setString(4, associado.getEmail());
		stmt.setDouble(5, associado.getValorAtual());
		stmt.setInt(6, associado.getVencAtual());
		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Associado> getLista() throws SQLException {

		List<Associado> associados = new ArrayList<Associado>();

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM associado");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			// criando o objeto Associado
			Associado associado = new Associado();

			associado.setCpf(rs.getString("cpf"));
			associado.setNome(rs.getString("nome"));
			associado.setCelular(rs.getString("celular"));
			associado.setEmail(rs.getString("email"));
			associado.setValorAtual(rs.getDouble("valor_atual"));
			associado.setVencAtual(rs.getInt("venc_atual"));

			// adicionando o objeto à lista
			associados.add(associado);

		}

		rs.close();
		stmt.close();
		return associados;

	}

	public Associado getAssociado(String search) throws SQLException {

		Associado associado = new Associado();

		try {
			PreparedStatement stmt = (PreparedStatement) this.connection
					.prepareStatement("SELECT * FROM associado WHERE " + "cpf = ?");
			stmt.setString(1, search); // Note que essa variavel é passada da
										// função principal
			ResultSet rs = stmt.executeQuery();

			if (rs.next() == true) {
				associado.setCpf(rs.getString("cpf"));
				associado.setNome(rs.getString("nome"));
				associado.setCelular(rs.getString("celular"));
				associado.setEmail(rs.getString("email"));
				associado.setValorAtual(rs.getDouble("valor_atual"));
				associado.setVencAtual(rs.getInt("venc_atual"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return (associado);

	}

	public void excluir(String search) {
		try {
			
			//tenta apagar da tabela de associado
			PreparedStatement stmt = (PreparedStatement) this.connection
					.prepareStatement("DELETE FROM associado WHERE cpf = ?");
			
			stmt.setString(1, search);
			stmt.execute();
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}

	public void altera(Associado associado, String cpf) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement(
				"UPDATE associado SET nome=?, celular=?, email=?, valor_atual=?, venc_atual=? WHERE cpf=?");

		// TODO Perguntar para o Polis como deve fazer com a tabela de
		// pagamento, se deve alterar o cpf do associado

		// stmt.setString(1, associado.getCpf());
		stmt.setString(1, associado.getNome());

		stmt.setString(2, associado.getCelular());
		stmt.setString(3, associado.getEmail());
		stmt.setDouble(4, associado.getValorAtual());
		stmt.setInt(5, associado.getVencAtual());
		stmt.setString(6, cpf);

		stmt.execute();
		stmt.close();
	}
	
}