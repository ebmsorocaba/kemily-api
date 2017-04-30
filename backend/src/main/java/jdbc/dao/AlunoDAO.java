package jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
//import com.mysql.jdbc.PreparedStatement;


import jdbc.ConnectionFactory;
import com.jdriven.ng2boot.Aluno;

public class AlunoDAO {
		// a conexão com o banco de dados
		private Connection connection;
		
		public AlunoDAO() throws SQLException {
			this.connection = ConnectionFactory.getConnection();
		}
		
		public void adiciona(Aluno aluno) throws SQLException {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO alunos (id,nome,gender) VALUES (?, ?, ?)");
			// seta os valores
			stmt.setInt(1,aluno.getId());
			stmt.setString(2,aluno.getName());
			stmt.setString(3,aluno.getGender());
			// executa
			stmt.execute();
			stmt.close();
		}
		
		public List<Aluno> getLista() throws SQLException {
			
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM alunos");
				ResultSet rs = stmt.executeQuery();
				System.out.println("Entrou DAO");
				List<Aluno> alunos = new ArrayList<Aluno>();
				while (rs.next()) {
					// criando o objeto Aluno
					Aluno aluno = new Aluno(-1,"","");
					aluno.setId(rs.getInt("id"));
					aluno.setName(rs.getString("nome"));
					aluno.setGender(rs.getString("gender"));
					// adicionando o objeto à lista
					alunos.add(aluno);
	
				}
				rs.close();
				stmt.close();
				return alunos;
			
	}
		
		public Aluno getAluno(int search) throws SQLException {
			
			Aluno aluno = new Aluno(-1,"","");
			
			try {
				PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM alunos WHERE " + "id = ?");
	            
				stmt.setLong(1, search); //Note que essa variavel é passada da função principal
	            ResultSet rs = stmt.executeQuery();
	           
	            if (rs.next() == true) {
	            	aluno.setId(rs.getInt("id"));
					aluno.setName(rs.getString("nome"));
					aluno.setGender(rs.getString("gender"));
	                
	            }
	        }
	        catch (SQLException ex) { 
	             System.out.println(ex.toString());   
	        }
			
			
	        return (aluno);
	}
	
		
		public void excluir(int search) {
	        
	        try {
	        	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM alunos WHERE id = ?");
	            
	            stmt.setLong(1, search);
	                      
	            stmt.execute();
	            
	        } catch (SQLException ex) {
	             System.out.println(ex.toString());   
	        }
	    }
	
		
		public void altera(Aluno aluno) throws SQLException {

			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE alunos SET nome=?, gender=? WHERE id=?");
				stmt.setString(1, aluno.getName());
				stmt.setString(2, aluno.getGender());
				stmt.setInt(3, aluno.getId());
				stmt.execute();
				stmt.close();
			}
}
		
