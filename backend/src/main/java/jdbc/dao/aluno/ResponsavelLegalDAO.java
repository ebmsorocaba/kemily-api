package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.ResponsavelLegal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelLegalDAO {
    private Connection connection;
    private AlunoDAO alunoDao;
    
    public ResponsavelLegalDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDao = new AlunoDAO();
    }

    public void adicionar(ResponsavelLegal responsavel) throws SQLException {
        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO responsavel_legal(nome, telefone, email, rede_social, rg, cpf, grau_parentesco, estado,ra_aluno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, responsavel.getNome());
            stmt.setString(2, responsavel.getTelefone());
            stmt.setString(3, responsavel.getEmail());
            stmt.setString(4, responsavel.getRedeSocial());
            stmt.setString(5, responsavel.getRg());
            stmt.setString(6, responsavel.getCpf());
            stmt.setString(7, responsavel.getGrauParentesco());
            stmt.setString(8, responsavel.getEstado());
            stmt.setInt(9, responsavel.getAluno().getRa());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }



    public List<ResponsavelLegal> getLista() throws SQLException {
        List<ResponsavelLegal> contatos = new ArrayList<ResponsavelLegal>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM responsavel_legal");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            ResponsavelLegal responsavel = new ResponsavelLegal();

            responsavel.setId(rs.getInt("id"));
            responsavel.setNome(rs.getString("nome"));
            responsavel.setTelefone(rs.getString("telefone"));
            responsavel.setEmail(rs.getString("email"));
            responsavel.setRedeSocial(rs.getString("rede_social"));
            responsavel.setCpf(rs.getString("cpf"));
            responsavel.setRg(rs.getString("rg"));
            responsavel.setGrauParentesco(rs.getString("grau_parentesco"));
            responsavel.setEstado(rs.getString("estado"));
            responsavel.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));

            contatos.add(responsavel);
        }

        rs.close();
        stmt.close();

        return contatos;
    }

    public List<ResponsavelLegal> getByAluno(int ra) throws SQLException {
        List<ResponsavelLegal> contatos = new ArrayList<ResponsavelLegal>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM responsavel_legal WHERE ra_aluno = ?");
        stmt.setInt(1, ra);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            ResponsavelLegal responsavel = new ResponsavelLegal();

            responsavel.setId(rs.getInt("id"));
            responsavel.setNome(rs.getString("nome"));
            responsavel.setTelefone(rs.getString("telefone"));
            responsavel.setEmail(rs.getString("email"));
            responsavel.setRedeSocial(rs.getString("rede_social"));
            responsavel.setCpf(rs.getString("cpf"));
            responsavel.setRg(rs.getString("rg"));
            responsavel.setGrauParentesco(rs.getString("grau_parentesco"));
            responsavel.setEstado(rs.getString("estado"));
            responsavel.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));

            contatos.add(responsavel);
        }

        rs.close();
        stmt.close();

        return contatos;
    }

    public ResponsavelLegal getContato(int id) throws SQLException {
        ResponsavelLegal responsavel = new ResponsavelLegal();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM responsavel_legal WHERE id = ?");
            stmt.setInt(1,  id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                responsavel.setId(rs.getInt("id"));
                responsavel.setNome(rs.getString("nome"));
                responsavel.setTelefone(rs.getString("telefone"));
                responsavel.setEmail(rs.getString("email"));
                responsavel.setRedeSocial(rs.getString("rede_social"));
                responsavel.setCpf(rs.getString("cpf"));
                responsavel.setRg(rs.getString("rg"));
                responsavel.setGrauParentesco(rs.getString("grau_parentesco"));
                responsavel.setEstado(rs.getString("estado"));
                responsavel.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return responsavel;
    }

    public void excluir(int id) throws SQLException {

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM responsavel_legal WHERE responsavel_legal.id = ?");
            stmt.setInt(1, id);


            stmt.execute();
            stmt.close();
        } catch(SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void excluirByAluno(int ra) throws SQLException {


        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM responsavel_legal WHERE responsavel_legal.ra_aluno = ?");
            stmt.setInt(1, ra);
            stmt.execute();
            stmt.close();


        } catch(SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void altera(ResponsavelLegal responsavel, int id) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE responsavel_legal SET nome = ?, telefone = ?, email = ?, rede_social = ?, rg = ?, cpf = ?, grau_parentesco = ?, estado = ?, ra_aluno = ? WHERE id = ?");

        stmt.setString(1, responsavel.getNome());
        stmt.setString(2, responsavel.getTelefone());
        stmt.setString(3, responsavel.getEmail());
        stmt.setString(4, responsavel.getRedeSocial());
        stmt.setString(5, responsavel.getRg());
        stmt.setString(6, responsavel.getCpf());
        stmt.setString(7, responsavel.getGrauParentesco());
        stmt.setString(8, responsavel.getEstado());
        stmt.setInt(9, responsavel.getAluno().getRa());
        stmt.setInt(10, id);

        stmt.execute();
        stmt.close();
    }
}
