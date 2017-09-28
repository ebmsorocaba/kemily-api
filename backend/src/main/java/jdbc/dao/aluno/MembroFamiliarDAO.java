package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.MembroFamiliar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembroFamiliarDAO {
    private Connection connection;
    private AlunoDAO alunoDao;

    public MembroFamiliarDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDao = new AlunoDAO();
    }

    public void adiciona(MembroFamiliar membro) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO membro_familiar (nome, parentesco, escolaridade, data_nascimento, ocupacao, salario, local_de_trabalho, condicao_trabalho, ra_aluno) VALUES (?,?,?,?,?,?,?,?,?)");

        stmt.setString(1,membro.getNome());
        stmt.setString(2,membro.getParentesco());
        stmt.setString(3,membro.getEscolaridade());
        stmt.setDate(4,membro.getDataNascimento());
        stmt.setString(5,membro.getOcupacao());
        stmt.setDouble(6,membro.getSalario());
        stmt.setString(7, membro.getLocalTrabalho());
        stmt.setString(8, membro.getCondicaoTrabalho());
        stmt.setInt(9, membro.getAluno().getRa());

        // executa
        stmt.execute();
        stmt.close();
    }

    public List<MembroFamiliar> getLista() throws SQLException {

        List<MembroFamiliar> parentes = new ArrayList<MembroFamiliar>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM membro_familiar");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            MembroFamiliar membro = new MembroFamiliar();

            membro.setId(rs.getInt("id"));
            membro.setNome(rs.getString("nome"));
            membro.setParentesco(rs.getString("parentesco"));
            membro.setEscolaridade(rs.getString("escolaridade"));
            membro.setDataNascimento(rs.getDate("data_nascimento"));
            membro.setOcupacao(rs.getString("ocupacao"));
            membro.setSalario(rs.getDouble("salario"));
            membro.setLocalTrabalho(rs.getString("local_de_trabalho"));
            membro.setCondicaoTrabalho(rs.getString("condicao_trabalho"));
            membro.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));


            parentes.add(membro);

        }

        rs.close();
        stmt.close();

        return parentes;

    }

    public MembroFamiliar getMembroFamiliar(int search) throws SQLException {

        MembroFamiliar membro = new MembroFamiliar();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM membro_familiar WHERE " + "id = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                membro.setId(rs.getInt("id"));
                membro.setNome(rs.getString("nome"));
                membro.setParentesco(rs.getString("parentesco"));
                membro.setEscolaridade(rs.getString("escolaridade"));
                membro.setDataNascimento(rs.getDate("data_nascimento"));
                membro.setOcupacao(rs.getString("ocupacao"));
                membro.setSalario(rs.getDouble("salario"));
                membro.setLocalTrabalho(rs.getString("local_de_trabalho"));
                membro.setCondicaoTrabalho(rs.getString("condicao_trabalho"));
                membro.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (membro);

    }


    public void excluir(int search) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM membro_familiar WHERE id = ?");
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

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM membro_familiar WHERE ra_aluno = ?");
            stmt.setInt(1, ra);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }


    public void altera(MembroFamiliar membro, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE membro_familiar SET nome=?, parentesco=?, escolaridade=?, data_nascimento=?, ocupacao=?, salario=?, local_de_trabalho=?, condicao_trabalho=?, ra_aluno=? WHERE id=?");

        stmt.setString(1,membro.getNome());
        stmt.setString(2,membro.getParentesco());
        stmt.setString(3,membro.getEscolaridade());
        stmt.setDate(4,membro.getDataNascimento());
        stmt.setString(5,membro.getOcupacao());
        stmt.setDouble(6,membro.getSalario());
        stmt.setString(7, membro.getLocalTrabalho());
        stmt.setString(8, membro.getCondicaoTrabalho());
        stmt.setInt(9, membro.getAluno().getRa());
        stmt.setInt(10, id);

        stmt.execute();
        stmt.close();
    }
    
    public List<MembroFamiliar> getByAluno(int ra) throws SQLException {

        List<MembroFamiliar> parentes = new ArrayList<MembroFamiliar>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM membro_familiar WHERE ra_aluno = ?");
        stmt.setInt(1, ra);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            MembroFamiliar membro = new MembroFamiliar();

            membro.setId(rs.getInt("id"));
            membro.setNome(rs.getString("nome"));
            membro.setParentesco(rs.getString("parentesco"));
            membro.setEscolaridade(rs.getString("escolaridade"));
            membro.setDataNascimento(rs.getDate("data_nascimento"));
            membro.setOcupacao(rs.getString("ocupacao"));
            membro.setSalario(rs.getDouble("salario"));
            membro.setLocalTrabalho(rs.getString("local_de_trabalho"));
            membro.setCondicaoTrabalho(rs.getString("condicao_trabalho"));
            membro.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));

            parentes.add(membro);
        }


        stmt.close();


        return parentes;

    }
}
