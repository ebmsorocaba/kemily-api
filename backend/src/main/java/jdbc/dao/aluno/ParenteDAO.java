package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Parente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParenteDAO {
    private Connection connection;
    private AlunoDAO alunoDao;

    public ParenteDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDao = new AlunoDAO();
    }

    public void adiciona(Parente parente) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO parente (nome, parentesco, escolaridade, data_nascimento, ocupacao, salario, local_de_trabalho, ra_aluno) VALUES (?,?,?,?,?,?,?,?)");

        stmt.setString(1,parente.getNome());
        stmt.setString(2,parente.getParentesco());
        stmt.setString(3,parente.getEscolaridade());
        stmt.setDate(4,parente.getData_nascimento());
        stmt.setString(5,parente.getOcupacao());
        stmt.setDouble(6,parente.getSalario());
        stmt.setString(7, parente.getLocal_trabalho());
        stmt.setInt(8, parente.getAluno().getRa());

        // executa
        stmt.execute();
        stmt.close();
    }

    public List<Parente> getLista() throws SQLException {

        List<Parente> parentes = new ArrayList<Parente>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM parente");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Parente parente = new Parente();

            parente.setId(rs.getInt("id"));
            parente.setNome(rs.getString("nome"));
            parente.setParentesco(rs.getString("parentesco"));
            parente.setEscolaridade(rs.getString("escolaridade"));
            parente.setData_nascimento(rs.getDate("data_nascimento"));
            parente.setOcupacao(rs.getString("ocupacao"));
            parente.setSalario(rs.getDouble("salario"));
            parente.setLocal_trabalho(rs.getString("local_de_trabalho"));
            parente.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));


            parentes.add(parente);

        }

        rs.close();
        stmt.close();

        return parentes;

    }

    public Parente getParente(int search) throws SQLException {

        Parente parente = new Parente();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM parente WHERE " + "id = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                parente.setId(rs.getInt("id"));
                parente.setNome(rs.getString("nome"));
                parente.setParentesco(rs.getString("parentesco"));
                parente.setEscolaridade(rs.getString("escolaridade"));
                parente.setData_nascimento(rs.getDate("data_nascimento"));
                parente.setOcupacao(rs.getString("ocupacao"));
                parente.setSalario(rs.getDouble("salario"));
                parente.setLocal_trabalho(rs.getString("local_de_trabalho"));
                parente.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (parente);

    }


    public void excluir(int search) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM parente WHERE id = ?");
            stmt.setInt(1, search);
            stmt.execute();

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Parente parente, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE parente SET nome=?, parentesco=?, escolaridade=?, data_nascimento=?, ocupacao=?, salario=?, local_de_trabalho=?, ra_aluno=? WHERE id=?");

        stmt.setString(1,parente.getNome());
        stmt.setString(2,parente.getParentesco());
        stmt.setString(3,parente.getEscolaridade());
        stmt.setDate(4,parente.getData_nascimento());
        stmt.setString(5,parente.getOcupacao());
        stmt.setDouble(6,parente.getSalario());
        stmt.setString(7, parente.getLocal_trabalho());
        stmt.setInt(8, parente.getAluno().getRa());
        stmt.setInt(9, id);

        stmt.execute();
        stmt.close();
    }
    
    public Parente getParenteAluno(int ra) throws SQLException {

        Parente parente = new Parente();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM parente WHERE " + "ra_aluno = ?");

            stmt.setInt(1, ra);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                parente.setId(rs.getInt("id"));
                parente.setNome(rs.getString("nome"));
                parente.setParentesco(rs.getString("parentesco"));
                parente.setEscolaridade(rs.getString("escolaridade"));
                parente.setData_nascimento(rs.getDate("data_nascimento"));
                parente.setOcupacao(rs.getString("ocupacao"));
                parente.setSalario(rs.getDouble("salario"));
                parente.setLocal_trabalho(rs.getString("local_de_trabalho"));
                parente.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (parente);

    }
}
