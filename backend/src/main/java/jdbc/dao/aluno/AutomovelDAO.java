package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Automovel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutomovelDAO {
    private Connection connection;
    private EstruturaFamiliarDAO estruturaFamiliarDAO;

    public AutomovelDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.estruturaFamiliarDAO = new EstruturaFamiliarDAO();
    }

    public void adiciona(Automovel automovel) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO automovel (modelo, ano, financiado, id_estrutura_familiar) VALUES (?, ?, ?, ?)");

        stmt.setString(1,automovel.getModelo());
        stmt.setString(2,automovel.getAno());
        stmt.setBoolean(3,automovel.isFinanciado());
        stmt.setInt(4, automovel.getEstruturaFamiliar().getId());

        stmt.execute();
        stmt.close();
    }

    public List<Automovel> getLista() throws SQLException {

        List<Automovel> automoveis = new ArrayList<Automovel>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM automovel");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Automovel automovel = new Automovel();

            automovel.setId(rs.getInt("id"));
            automovel.setModelo(rs.getString("modelo"));
            automovel.setAno(rs.getString("ano"));
            automovel.setFinanciado(rs.getBoolean("financiado"));
            automovel.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));



            automoveis.add(automovel);

        }

        rs.close();
        stmt.close();

        return automoveis;

    }

    public Automovel getAutomovel(int id) throws SQLException {

        Automovel automovel = new Automovel();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM automovel WHERE " + "id = ?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                automovel.setId(rs.getInt("id"));
                automovel.setModelo(rs.getString("modelo"));
                automovel.setAno(rs.getString("ano"));
                automovel.setFinanciado(rs.getBoolean("financiado"));
                automovel.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (automovel);

    }

    public List<Automovel> getByEstruturaFamiliar(int id) throws SQLException {
        List<Automovel> automoveis = new ArrayList<Automovel>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM automovel where id_estrutura_familiar = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Automovel automovel = new Automovel();

            automovel.setId(rs.getInt("id"));
            automovel.setModelo(rs.getString("modelo"));
            automovel.setAno(rs.getString("ano"));
            automovel.setFinanciado(rs.getBoolean("financiado"));
            automovel.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));

            automoveis.add(automovel);
        }

        rs.close();
        stmt.close();

        return automoveis;
    }


    public void excluir(int id) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM automovel WHERE id=?");
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
    		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM automovel WHERE id_estrutura_familiar=?");
    		stmt.setInt(1, idEstruturaFamiliar);
    		stmt.execute();
    		stmt.close();
    	} catch (SQLException ex) {
    		System.out.println(ex.toString());
    	}
    }
    

    public void altera(Automovel automovel, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE automovel SET modelo=?, ano=?, financiado=?, id_estrutura_familiar=? WHERE id=?");

        stmt.setString(1,automovel.getModelo());
        stmt.setString(2,automovel.getAno());
        stmt.setBoolean(3,automovel.isFinanciado());
        stmt.setInt(4, automovel.getEstruturaFamiliar().getId());
        stmt.setInt(5, id);

        stmt.execute();
        stmt.close();
    }
}
