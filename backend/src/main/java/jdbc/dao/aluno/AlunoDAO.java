package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;
    private TurmaDAO turmaDAO;

    public AlunoDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.turmaDAO = new TurmaDAO();
    }

    public Aluno adiciona(Aluno aluno) throws SQLException {
    	try (
    			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO aluno (nome, turma_educador, data_nascimento, rg, naturalidade, estado, data_cadastro, meio_transporte, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    	){
	
        stmt.setString(1,aluno.getNome());
        stmt.setString(2,aluno.getTurma().getEducador());
        stmt.setDate(3,aluno.getData_nascimento());
        stmt.setString(4,aluno.getRg());
        stmt.setString(5,aluno.getNaturalidade());
        stmt.setString(6,aluno.getEstado());
        stmt.setDate(7,aluno.getData_cadastro());
        stmt.setString(8,aluno.getMeio_transporte());
        stmt.setString(9,aluno.getObservacoes());

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
            aluno.setTurma(turmaDAO.getTurma(rs.getString("turma_educador")));
            aluno.setRg(rs.getString("rg"));
            aluno.setData_cadastro(rs.getDate("data_cadastro"));
            aluno.setData_nascimento(rs.getDate("data_nascimento"));
            aluno.setEstado(rs.getString("estado"));
            aluno.setMeio_transporte(rs.getString("meio_transporte"));
            aluno.setObservacoes(rs.getString("observacoes"));
            aluno.setNaturalidade(rs.getString("naturalidade"));

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

            if (rs.next() == true) {
                aluno.setRa(rs.getInt("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setTurma(turmaDAO.getTurma(rs.getString("turma_educador")));
                aluno.setRg(rs.getString("rg"));
                aluno.setData_cadastro(rs.getDate("data_cadastro"));
                aluno.setData_nascimento(rs.getDate("data_nascimento"));
                aluno.setEstado(rs.getString("estado"));
                aluno.setMeio_transporte(rs.getString("meio_transporte"));
                aluno.setObservacoes(rs.getString("observacoes"));
                aluno.setNaturalidade(rs.getString("naturalidade"));
            }
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

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Aluno aluno, int ra) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE aluno SET nome=?, turma_educador=?, data_nascimento=?, rg=?, naturalidade=?, estado=?, data_cadastro=?, meio_transporte=?, observacoes=? WHERE ra=?");

        stmt.setString(1,aluno.getNome());
        stmt.setString(2,aluno.getTurma().getEducador());
        stmt.setDate(3,aluno.getData_nascimento());
        stmt.setString(4,aluno.getRg());
        stmt.setString(5,aluno.getNaturalidade());
        stmt.setString(6,aluno.getEstado());
        stmt.setDate(7,aluno.getData_cadastro());
        stmt.setString(8,aluno.getMeio_transporte());
        stmt.setString(9,aluno.getObservacoes());
        stmt.setInt(10, ra);

        stmt.execute();
        stmt.close();
    }
}
