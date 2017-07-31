package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Turma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {
    private Connection connection;

    public TurmaDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Turma turma) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO turma (educador) VALUES (?)");

        stmt.setString(1,turma.getEducador());

        // executa
        stmt.execute();
        stmt.close();
    }

    public List<Turma> getLista() throws SQLException {

        List<Turma> turmas = new ArrayList<Turma>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM turma");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Turma turma = new Turma();

            turma.setEducador(rs.getString("educador"));

            turmas.add(turma);

        }

        rs.close();
        stmt.close();

        return turmas;

    }

    public Turma getTurma(String search) throws SQLException {

        Turma turma = new Turma();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM turma WHERE " + "educador = ?");

            stmt.setString(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                turma.setEducador(rs.getString("educador"));
            }
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (turma);

    }


    public void excluir(String search) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM turma WHERE educador = ?");
            stmt.setString(1, search);
            stmt.execute();

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

}
