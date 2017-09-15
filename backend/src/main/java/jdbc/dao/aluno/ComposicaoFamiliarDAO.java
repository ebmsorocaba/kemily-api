package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.ComposicaoFamiliar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposicaoFamiliarDAO {
    private Connection connection;
    private AlunoDAO alunoDao;

    public ComposicaoFamiliarDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDao = new AlunoDAO();
    }

    public void adiciona(ComposicaoFamiliar parente) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO composicao_familiar (nome, parentesco, escolaridade, data_nascimento, ocupacao, salario, local_de_trabalho, condicao_trabalho, ra_aluno) VALUES (?,?,?,?,?,?,?,?,?)");

        stmt.setString(1,parente.getNome());
        stmt.setString(2,parente.getParentesco());
        stmt.setString(3,parente.getEscolaridade());
        stmt.setDate(4,parente.getDataNascimento());
        stmt.setString(5,parente.getOcupacao());
        stmt.setDouble(6,parente.getSalario());
        stmt.setString(7, parente.getLocalTrabalho());
        stmt.setString(8, parente.getCondicaoTrabalho());
        stmt.setInt(9, parente.getAluno().getRa());

        // executa
        stmt.execute();
        stmt.close();
    }

    public List<ComposicaoFamiliar> getLista() throws SQLException {

        List<ComposicaoFamiliar> parentes = new ArrayList<ComposicaoFamiliar>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM composicao_familiar");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ComposicaoFamiliar parente = new ComposicaoFamiliar();

            parente.setId(rs.getInt("id"));
            parente.setNome(rs.getString("nome"));
            parente.setParentesco(rs.getString("parentesco"));
            parente.setEscolaridade(rs.getString("escolaridade"));
            parente.setDataNascimento(rs.getDate("data_nascimento"));
            parente.setOcupacao(rs.getString("ocupacao"));
            parente.setSalario(rs.getDouble("salario"));
            parente.setLocalTrabalho(rs.getString("local_de_trabalho"));
            parente.setCondicaoTrabalho(rs.getString("condicao_trabalho"));
            parente.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));


            parentes.add(parente);

        }

        rs.close();
        stmt.close();

        return parentes;

    }

    public ComposicaoFamiliar getParente(int search) throws SQLException {

        ComposicaoFamiliar parente = new ComposicaoFamiliar();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM composicao_familiar WHERE " + "id = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                parente.setId(rs.getInt("id"));
                parente.setNome(rs.getString("nome"));
                parente.setParentesco(rs.getString("parentesco"));
                parente.setEscolaridade(rs.getString("escolaridade"));
                parente.setDataNascimento(rs.getDate("data_nascimento"));
                parente.setOcupacao(rs.getString("ocupacao"));
                parente.setSalario(rs.getDouble("salario"));
                parente.setLocalTrabalho(rs.getString("local_de_trabalho"));
                parente.setCondicaoTrabalho(rs.getString("condicao_trabalho"));
                parente.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (parente);

    }


    public void excluir(int search) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM composicao_familiar WHERE id = ?");
            stmt.setInt(1, search);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }
    
    public void excluirByAluno(int ra) {
    	try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM composicao_familiar WHERE ra_aluno = ?");
            stmt.setInt(1, ra);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }


    public void altera(ComposicaoFamiliar parente, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE composicao_familiar SET nome=?, parentesco=?, escolaridade=?, data_nascimento=?, ocupacao=?, salario=?, local_de_trabalho=?, condicao_trabalho=?, ra_aluno=? WHERE id=?");

        stmt.setString(1,parente.getNome());
        stmt.setString(2,parente.getParentesco());
        stmt.setString(3,parente.getEscolaridade());
        stmt.setDate(4,parente.getDataNascimento());
        stmt.setString(5,parente.getOcupacao());
        stmt.setDouble(6,parente.getSalario());
        stmt.setString(7, parente.getLocalTrabalho());
        stmt.setString(8, parente.getCondicaoTrabalho());
        stmt.setInt(9, parente.getAluno().getRa());
        stmt.setInt(10, id);

        stmt.execute();
        stmt.close();
    }
    
    public ComposicaoFamiliar getParenteAluno(int ra) throws SQLException {

        ComposicaoFamiliar parente = new ComposicaoFamiliar();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM composicao_familiar WHERE " + "ra_aluno = ?");

            stmt.setInt(1, ra);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                parente.setId(rs.getInt("id"));
                parente.setNome(rs.getString("nome"));
                parente.setParentesco(rs.getString("parentesco"));
                parente.setEscolaridade(rs.getString("escolaridade"));
                parente.setDataNascimento(rs.getDate("data_nascimento"));
                parente.setOcupacao(rs.getString("ocupacao"));
                parente.setSalario(rs.getDouble("salario"));
                parente.setLocalTrabalho(rs.getString("local_de_trabalho"));
                parente.setCondicaoTrabalho(rs.getString("condicao_trabalho"));
                parente.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (parente);

    }
}
