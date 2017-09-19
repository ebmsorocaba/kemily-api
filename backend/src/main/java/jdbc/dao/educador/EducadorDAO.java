package jdbc.dao.educador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.educador.Educador;

public class EducadorDAO {
	private Connection connection;
	
	public EducadorDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}
	
	public void adiciona(Educador educador) throws SQLException {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO educador (cpf, nome, data_nascimento, sexo, telefone, email, cargo, numero_carteira_profissional, serie_carteira_profissional, numero_pis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			stmt.setString(1, educador.getCpf());
			stmt.setString(2, educador.getNome());
			stmt.setDate(3, educador.getDataNasc());
			stmt.setString(4, educador.getSexo());
			stmt.setString(5, educador.getTelefone());
			stmt.setString(6, educador.getEmail());
			stmt.setString(7, educador.getCargo());
			stmt.setInt(8, educador.getNumeroCarteiraProfissional());
			stmt.setInt(9, educador.getSerieCarteiraProfissional());
			stmt.setString(10, educador.getNumeroPis());
			
			stmt.execute();
			
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public List<Educador> getLista() throws SQLException {
		List<Educador> educadores = new ArrayList<Educador>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM educador");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Educador edu = new Educador();
			
			edu.setCpf(rs.getString("cpf"));
			edu.setNome(rs.getString("nome"));
			edu.setDataNasc(rs.getDate("data_nascimento"));
			edu.setSexo(rs.getString("sexo"));
			edu.setTelefone(rs.getString("telefone"));
			edu.setEmail(rs.getString("email"));
			edu.setCargo(rs.getString("cargo"));
			edu.setNumeroCarteiraProfissional(rs.getInt("numero_carteira_profissional"));
			edu.setSerieCarteiraProfissional(rs.getInt("serie_carteira_profissional"));
			edu.setNumeroPis(rs.getString("numero_pis"));
			
			educadores.add(edu);
		}
		
		rs.close();
		stmt.close();
		
		return educadores;
	}
	
	public Educador getEducador(String search) throws SQLException {
		Educador edu = new Educador();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM educador WHERE " + "cpf = ?");
			
			stmt.setString(1, search);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				edu.setCpf(rs.getString("cpf"));
				edu.setNome(rs.getString("nome"));
				edu.setDataNasc(rs.getDate("data_nascimento"));
				edu.setSexo(rs.getString("sexo"));
				edu.setTelefone(rs.getString("telefone"));
				edu.setEmail(rs.getString("email"));
				edu.setCargo(rs.getString("cargo"));
				edu.setNumeroCarteiraProfissional(rs.getInt("numero_carteira_profissional"));
				edu.setSerieCarteiraProfissional(rs.getInt("serie_carteira_profissional"));
				edu.setNumeroPis(rs.getString("numero_pis"));
			}
			
			stmt.close();
		} catch (SQLException ex) {
            System.out.println(ex.toString());
        }
		
		return edu;
	}
	
	public void excluir(String search) {
		try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM educador WHERE cpf = ?");
            stmt.setString(1, search);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
	}
	
	public void altera(Educador educador, String cpf) throws SQLException {

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE educador SET nome = ?, data_nascimento = ?, sexo = ?, telefone = ?, email = ?, cargo = ?, numero_carteira_profissional = ?, serie_carteira_profissional = ?, numero_pis = ? WHERE cpf = ?");
		
		stmt.setString(1, educador.getNome());
		stmt.setDate(2, educador.getDataNasc());
		stmt.setString(3, educador.getSexo());
		stmt.setString(4, educador.getTelefone());
		stmt.setString(5, educador.getEmail());
		stmt.setString(6, educador.getCargo());
		stmt.setInt(7, educador.getNumeroCarteiraProfissional());
		stmt.setInt(8, educador.getSerieCarteiraProfissional());
		stmt.setString(9, educador.getNumeroPis());
		stmt.setString(10, cpf);
		
		stmt.execute();
		
		stmt.close();
			
	}
}
