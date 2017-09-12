package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Roupa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoupaDAO {
    private Connection connection;
    private AlunoDAO alunoDAO;

    public RoupaDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDAO = new AlunoDAO();
    }

    public void adiciona(Roupa roupa) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO roupa (ra_aluno, tamanho_calca, tamanho_camiseta) VALUES (?, ?, ?)");

        stmt.setInt(1,roupa.getAluno().getRa());
        stmt.setString(2,roupa.getTamanhoCalca());
        stmt.setString(3,roupa.getTamanhoCamiseta());

        stmt.execute();
        stmt.close();
    }

    public List<Roupa> getLista() throws SQLException {

        List<Roupa> roupas = new ArrayList<Roupa>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM roupa");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Roupa roupa = new Roupa();

            roupa.setTamanhoCalca(rs.getString("tamanho_calca"));
            roupa.setTamanhoCamiseta(rs.getString("tamanho_camiseta"));
            roupa.setAluno(alunoDAO.getAluno(rs.getInt("ra_aluno")));

            roupas.add(roupa);

        }

        rs.close();
        stmt.close();

        return roupas;

    }

    public Roupa getRoupa(int ra) throws SQLException {

        Roupa roupa = new Roupa();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM roupa WHERE " + "ra_aluno = ?");

            stmt.setInt(1, ra);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                roupa.setTamanhoCalca(rs.getString("tamanho_calca"));
                roupa.setTamanhoCamiseta(rs.getString("tamanho_camiseta"));
                roupa.setAluno(alunoDAO.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (roupa);

    }


    public void excluir(int ra) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM roupa WHERE ra_aluno = ?");
            stmt.setInt(1, ra);
            stmt.execute();
            stmt.close();

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Roupa roupa, int ra) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE roupa SET tamanho_calca=?, tamanho_camiseta=? WHERE ra_aluno=?");

        stmt.setString(1,roupa.getTamanhoCalca());
        stmt.setString(2,roupa.getTamanhoCamiseta());
        stmt.setInt(3, ra);


        stmt.execute();
        stmt.close();
    }
}
