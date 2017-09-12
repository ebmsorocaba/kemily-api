package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Imovel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImovelDAO {
    private Connection connection;
    private EstruturaFamiliarDAO estruturaFamiliarDAO;

    public ImovelDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.estruturaFamiliarDAO = new EstruturaFamiliarDAO();
    }

    public void adiciona(Imovel imovel) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO imovel (financiado, id_estrutura_familiar) VALUES (?, ?)");

        stmt.setBoolean(1,imovel.isFinanciado());
        stmt.setInt(2, imovel.getEstruturaFamiliar().getId());

        stmt.execute();
        stmt.close();
    }

    public List<Imovel> getLista() throws SQLException {

        List<Imovel> imoveis = new ArrayList<Imovel>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM imovel");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Imovel imovel = new Imovel();

            imovel.setId(rs.getInt("id"));
            imovel.setFinanciado(rs.getBoolean("financiado"));
            imovel.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));

            imoveis.add(imovel);

        }

        rs.close();
        stmt.close();

        return imoveis;

    }

    public Imovel getImovel(int id) throws SQLException {

        Imovel imovel = new Imovel();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM imovel WHERE " + "id = ?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                imovel.setId(rs.getInt("id"));
                imovel.setFinanciado(rs.getBoolean("financiado"));
                imovel.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (imovel);

    }


    public void excluir(int id) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM imovel WHERE id=?");
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }
    
    public void excluirByIdEstruturaFamiliar(int idEstruturaFamiliar) {
    	try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM imovel WHERE id_estrutura_familiar=?");
            stmt.setInt(1, idEstruturaFamiliar);

            stmt.execute();
            stmt.close();

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }


    public void altera(Imovel imovel, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE imovel SET financiado=?, id_estrutura_familiar=? WHERE id=?");

        stmt.setBoolean(1,imovel.isFinanciado());
        stmt.setInt(2, imovel.getEstruturaFamiliar().getId());
        stmt.setInt(3, id);

        stmt.execute();
        stmt.close();
    }
}
