package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;
    private EnderecoDAO enderecoDAO;

    public AlunoDAO(Connection connection) throws  SQLException {
        this.connection = connection;
    }

    public AlunoDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        enderecoDAO = new EnderecoDAO();
    }

    public Aluno adiciona(Aluno aluno) throws SQLException {
    	try (
    			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO aluno (nome, data_nascimento, rg, naturalidade, estado, data_cadastro, meio_transporte, etnia, observacoes, cep_aluno, numero_aluno, escola, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    	){
	
        stmt.setString(1,aluno.getNome());
        stmt.setDate(2,aluno.getDataNascimento());
        stmt.setString(3,aluno.getRg());
        stmt.setString(4,aluno.getNaturalidade());
        stmt.setString(5,aluno.getEstado());
        stmt.setDate(6,aluno.getDataCadastro());
        stmt.setString(7,aluno.getMeioTransporte());
        stmt.setString(8, aluno.getEtnia());
        stmt.setString(9,aluno.getObservacoes());
        stmt.setString(10, aluno.getEndereco().getCep());
        stmt.setString(11, aluno.getEndereco().getNumero());
        stmt.setString(12, aluno.getEscola());
        stmt.setBoolean(13, aluno.isAtivo());

        // executa
        int ra = stmt.executeUpdate();
        
        if(ra == 0) {
        	aluno.setRa(-1);
        	throw new SQLException("Falha na craição do aluno, linha nao alterada");
        }
        
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        	if(generatedKeys.next()) {
        		aluno.setRa(generatedKeys.getInt(1));
        	} else {
        		aluno.setRa(-1);
				throw new SQLException("Falha na criação do contato, ID nao retornado"); 	
        	}
        }
        
        stmt.close();
        
	    } catch(SQLException ex) {
	    	System.out.println(ex.toString());
	    }
    	
    	return aluno;
    }

    public List<Aluno> getLista() throws SQLException {

        List<Aluno> alunos = new ArrayList<Aluno>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Aluno aluno = new Aluno();

            aluno.setRa(rs.getInt("ra"));
            aluno.setNome(rs.getString("nome"));
            aluno.setRg(rs.getString("rg"));
            aluno.setDataCadastro(rs.getDate("data_cadastro"));
            aluno.setDataNascimento(rs.getDate("data_nascimento"));
            aluno.setEstado(rs.getString("estado"));
            aluno.setMeioTransporte(rs.getString("meio_transporte"));
            aluno.setEtnia(rs.getString("etnia"));
            aluno.setObservacoes(rs.getString("observacoes"));
            aluno.setNaturalidade(rs.getString("naturalidade"));
            aluno.setEndereco(enderecoDAO.getEndereco(rs.getString("cep_aluno"), rs.getString("numero_aluno")));
            aluno.setEscola(rs.getString("escola"));
            aluno.setAtivo(rs.getBoolean("ativo"));

            alunos.add(aluno);

        }

        rs.close();
        stmt.close();

        return alunos;

    }

    public Aluno getAluno(int search) throws SQLException {

        Aluno aluno = new Aluno();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM aluno WHERE " + "ra = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno.setRa(rs.getInt("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setRg(rs.getString("rg"));
                aluno.setDataCadastro(rs.getDate("data_cadastro"));
                aluno.setDataNascimento(rs.getDate("data_nascimento"));
                aluno.setEstado(rs.getString("estado"));
                aluno.setMeioTransporte(rs.getString("meio_transporte"));
                aluno.setEtnia(rs.getString("etnia"));
                aluno.setObservacoes(rs.getString("observacoes"));
                aluno.setNaturalidade(rs.getString("naturalidade"));
                aluno.setEscola(rs.getString("escola"));
                aluno.setAtivo(rs.getBoolean("ativo"));
                aluno.setEndereco(enderecoDAO.getEndereco(rs.getString("cep_aluno"), rs.getString("numero_aluno")));
            }
            
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (aluno);

    }


    public void excluir(int search) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM aluno WHERE ra = ?");
            stmt.setInt(1, search);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Aluno aluno, int ra) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE aluno SET nome=?, data_nascimento=?, rg=?, naturalidade=?, estado=?, data_cadastro=?, meio_transporte=?, etnia=?, observacoes=?, escola=?, ativo=? WHERE ra=?");

        stmt.setString(1,aluno.getNome());
        stmt.setDate(2,aluno.getDataNascimento());
        stmt.setString(3,aluno.getRg());
        stmt.setString(4,aluno.getNaturalidade());
        stmt.setString(5,aluno.getEstado());
        stmt.setDate(6,aluno.getDataCadastro());
        stmt.setString(7,aluno.getMeioTransporte());
        stmt.setString(8, aluno.getEtnia());
        stmt.setString(9,aluno.getObservacoes());
        stmt.setString(10, aluno.getEscola());
        stmt.setBoolean(11, aluno.isAtivo());
        stmt.setInt(12, ra);

        stmt.execute();
        stmt.close();
    }
}
