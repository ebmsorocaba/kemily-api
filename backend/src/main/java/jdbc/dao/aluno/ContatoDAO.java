package jdbc.dao.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.aluno.Contato;

public class ContatoDAO {
	private Connection connection;
	private AlunoDAO alunoDao;
	
	public ContatoDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
		this.alunoDao = new AlunoDAO();
	}
	
	public void adicionar(Contato contato) throws SQLException {
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO contato(nome, telefone, tipo, ra_aluno) VALUES (?, ?, ?, ?)");
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getTipo());
			stmt.setInt(4, contato.getAluno().getRa());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public Contato adicionarEspecializado(Contato contato) throws SQLException {
		try (
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO contato(nome, telefone, tipo, ra_aluno) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		) {
		
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getTipo());
			stmt.setInt(4, contato.getAluno().getRa());
			
			int id = stmt.executeUpdate(); 
			
			if(id == 0) {
				contato.setId(-1);
				throw new SQLException("Falha na craição do contato, linha nao alterada");
			} 
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					contato.setId(generatedKeys.getInt(1));
				} else {
					contato.setId(-1);
					throw new SQLException("Falha na craição do contato, ID nao retornado"); 	
				}
			}
			
			stmt.close();
		
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return contato;
	}

	public List<Contato> getLista() throws SQLException {
		List<Contato> contatos = new ArrayList<Contato>();
		
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Contato contato = new Contato();
			
			contato.setId(rs.getInt("id"));
			contato.setNome(rs.getString("nome"));
			contato.setTelefone(rs.getString("telefone"));
			contato.setTipo(rs.getString("tipo"));
			contato.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
			
			contatos.add(contato);
		}
		
		rs.close();
		stmt.close();
		
		return contatos;
	}
	
	public Contato getContato(int id) throws SQLException {
		Contato contato = new Contato();
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contato WHERE id = ?");
			stmt.setInt(1,  id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next() == true) {
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setTipo(rs.getString("tipo"));
				contato.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
			}
			stmt.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		
		return contato;
	}
	
	public void excluir(int id) throws SQLException {
		
		try {
			System.out.println("[ContatoDAO] Excluindo Contato/Contato Genérico");
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM contato WHERE id = ?");
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch(SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void excluirByAluno(int ra) throws SQLException {
		System.out.println("[ContatoDAO] Criando variaveis, Instanciando DAOs");
		
		System.out.println("[ContatoDAO] Criando Lista de IDs");
		List<Integer> ids = new ArrayList<Integer>();
		
		System.out.println("[ContatoDAO] Criando Lista de Tipos");
		List<String> tipos = new ArrayList<String>();
		
		System.out.println("[ContatoDAO] Instanciando Responsavel DAO");
		Contato_ProfissionalDAO cpDAO;
		
		System.out.println("[ContatoDAO] Instanciando Profissional DAO");
		Contato_ResponsavelDAO crDAO;
		
		try {
			System.out.println("[ContatoDAO] Selecionando TODOS os contatos pelo RA");
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT id, tipo FROM contato WHERE ra_aluno = ?");
			stmt.setInt(1, ra);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println("[ContatoDAO] Adicionando ID e Tipo dos Contatos");
			while(rs.next()) {
				ids.add(rs.getInt("id"));
				tipos.add(rs.getString("tipo"));
			}
			
			System.out.println("[ContatoDAO] Fechando ResultSet e PreparedStatement");
			rs.close();
			stmt.close();
			
			if(!ids.isEmpty() && !tipos.isEmpty()) {
				
				for(int i = 0; i < ids.size(); i++) {
					System.out.println("[ContatoDAO] Verificando o tipo do contato");
					
					if(tipos.get(i).equals("profissional")) {
						System.out.println("[ContatoDAO] Instanciando Responsavel DAO");
						cpDAO = new Contato_ProfissionalDAO();
						System.out.println("[ContatoDAO] Excluindo Contato Profissional");
						cpDAO.excluir(ids.get(i));
					}
					
					if(tipos.get(i).equals("responsavel")) {
						System.out.println("[ContatoDAO] Instanciando Profissional DAO");
						crDAO = new Contato_ResponsavelDAO();
						System.out.println("[ContatoDAO] Excluindo Contato Responsavel");
						crDAO.excluir(ids.get(i));
					}
					
					this.excluir(ids.get(i));
				}
				
			}
			
		} catch(SQLException ex) {
			System.out.println(ex.toString());
		}
	}
	
	public void altera(Contato contato, int id) throws SQLException {
		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE contato SET nome = ?, telefone = ?, tipo = ?, ra_aluno = ? WHERE id = ?");
		
		stmt.setString(1, contato.getNome());
		stmt.setString(2, contato.getTelefone());
		stmt.setString(3, contato.getTipo());
		stmt.setInt(4, contato.getAluno().getRa());
		stmt.setInt(5, id);
		
		stmt.execute();
		stmt.close();
	}
}
