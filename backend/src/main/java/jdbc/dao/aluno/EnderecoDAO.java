package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private Connection connection;
    private AlunoDAO alunoDAO;

    public EnderecoDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDAO = new AlunoDAO();
    }

    public void adiciona(Endereco endereco) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO endereco (cep, numero, rua, bairro, cidade, ponto_referencia, complemento, ra_aluno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(1,endereco.getCep());
        stmt.setString(2,endereco.getNumero());
        stmt.setString(3,endereco.getRua());
        stmt.setString(4,endereco.getBairro());
        stmt.setString(5,endereco.getCidade());
        stmt.setString(6,endereco.getPontoReferencia());
        stmt.setString(7,endereco.getComplemento());
        stmt.setInt(8, endereco.getAluno().getRa());

        stmt.execute();
        stmt.close();
    }

    public List<Endereco> getLista() throws SQLException {

        List<Endereco> enderecos = new ArrayList<Endereco>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM endereco");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Endereco endereco = new Endereco();

            endereco.setNumero(rs.getString("numero"));
            endereco.setCep(rs.getString("cep"));
            endereco.setRua(rs.getString("rua"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setPontoReferencia(rs.getString("ponto_referencia"));
            endereco.setComplemento(rs.getString("complemento"));
            endereco.setAluno(alunoDAO.getAluno(rs.getInt("ra_aluno")));

            enderecos.add(endereco);

        }

        rs.close();
        stmt.close();

        return enderecos;

    }

    public Endereco getEndereco(String cep, String numero) throws SQLException {

        Endereco endereco = new Endereco();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM endereco WHERE " + "cep = ?" + "AND " + "numero = ?");

            stmt.setString(1, cep);
            stmt.setString(2, numero);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                endereco.setNumero(rs.getString("numero"));
                endereco.setCep(rs.getString("cep"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setPontoReferencia(rs.getString("ponto_referencia"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setAluno(alunoDAO.getAluno(rs.getInt("ra_aluno")));
            }
            
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (endereco);

    }


    public void excluir(String cep, String numero) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM endereco WHERE cep = ? AND numero = ?");
            stmt.setString(1, cep);
            stmt.setString(2, numero);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    public void excluirByAluno(int ra) {
    	try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM endereco WHERE ra_aluno = ?");
            stmt.setInt(1, ra);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void altera(Endereco endereco, String cep, String numero) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE endereco SET rua=?, bairro=?, cidade=?, ponto_referencia=?, complemento=?, ra_aluno=? WHERE cep=? AND numero=?");

        stmt.setString(1,endereco.getRua());
        stmt.setString(2,endereco.getBairro());
        stmt.setString(3,endereco.getCidade());
        stmt.setString(4,endereco.getPontoReferencia());
        stmt.setString(5,endereco.getComplemento());
        stmt.setInt(6, endereco.getAluno().getRa());
        stmt.setString(7, cep);
        stmt.setString(8, numero);

        stmt.execute();
        stmt.close();
    }
    
    public Endereco getEnderecoAluno(int raAluno) throws SQLException {

        Endereco endereco = new Endereco();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM endereco WHERE " + "ra_aluno = ?");

            stmt.setInt(1, raAluno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                endereco.setNumero(rs.getString("numero"));
                endereco.setCep(rs.getString("cep"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setPontoReferencia(rs.getString("ponto_referencia"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setAluno(alunoDAO.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (endereco);

    }
}
