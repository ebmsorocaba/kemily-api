package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.EstruturaFamiliar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstruturaFamiliarDAO {
    private Connection connection;
    private AlunoDAO alunoDao;

    public EstruturaFamiliarDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDao = new AlunoDAO();
    }

    public EstruturaFamiliar adiciona(EstruturaFamiliar estruturaFamiliar) throws SQLException {
    	try (
    			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO estrutura_familiar (estado_civil_pais, crianca_reside_com, problemas_financeiros, uso_de_alcool_drogas, alguem_agressivo, programas_sociais, ra_aluno) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    	) {
	        stmt.setString(1,estruturaFamiliar.getEstadoCivilPais());
	        stmt.setString(2,estruturaFamiliar.getCriancaResideCom());
	        stmt.setBoolean(3,estruturaFamiliar.isProblemasFinanceiros());
	        stmt.setBoolean(4,estruturaFamiliar.isUsoAlcoolDrogas());
	        stmt.setBoolean(5,estruturaFamiliar.isAlguemAgressivo());
	        stmt.setBoolean(6,estruturaFamiliar.isProgramasSociais());
	        stmt.setInt(7, estruturaFamiliar.getAluno().getRa());

	        // executa
	        int key = stmt.executeUpdate();
	        
	        if(key == 0) {
	        	estruturaFamiliar.setId(-1);
	        	throw new SQLException("Falha na criação do EstruturaFamiliar, linha nao alterada");
	        }
	        
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	        	if(generatedKeys.next()) {
	        		estruturaFamiliar.setId(generatedKeys.getInt(1));
	        	} else {
	        		estruturaFamiliar.setId(-1);
	        		throw new SQLException("Falha na criação do EstruturaFamiliar, ID nao retornado"); 
	        	}
	        }
	        
	     stmt.close();
	        
    	}
	        
        return estruturaFamiliar;
    }

    public List<EstruturaFamiliar> getLista() throws SQLException {

        List<EstruturaFamiliar> estruturasFamiliares = new ArrayList<EstruturaFamiliar>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM estrutura_familiar");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            EstruturaFamiliar estruturaFamiliar = new EstruturaFamiliar();

            estruturaFamiliar.setId(rs.getInt("id"));
            estruturaFamiliar.setEstadoCivilPais(rs.getString("estado_civil_pais"));
            estruturaFamiliar.setCriancaResideCom(rs.getString("crianca_reside_com"));
            estruturaFamiliar.setProblemasFinanceiros(rs.getBoolean("problemas_financeiros"));
            estruturaFamiliar.setUsoAlcoolDrogas(rs.getBoolean("uso_de_alcool_drogas"));
            estruturaFamiliar.setAlguemAgressivo(rs.getBoolean("alguem_agressivo"));
            estruturaFamiliar.setProgramasSociais(rs.getBoolean("programas_sociais"));
            estruturaFamiliar.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));


            estruturasFamiliares.add(estruturaFamiliar);

        }

        rs.close();
        stmt.close();

        return estruturasFamiliares;

    }

    public EstruturaFamiliar getEstruturaFamiliar(int search) throws SQLException {

        EstruturaFamiliar estruturaFamiliar = new EstruturaFamiliar();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM estrutura_familiar WHERE " + "id = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                estruturaFamiliar.setId(rs.getInt("id"));
                estruturaFamiliar.setEstadoCivilPais(rs.getString("estado_civil_pais"));
                estruturaFamiliar.setCriancaResideCom(rs.getString("crianca_reside_com"));
                estruturaFamiliar.setProblemasFinanceiros(rs.getBoolean("problemas_financeiros"));
                estruturaFamiliar.setUsoAlcoolDrogas(rs.getBoolean("uso_de_alcool_drogas"));
                estruturaFamiliar.setAlguemAgressivo(rs.getBoolean("alguem_agressivo"));
                estruturaFamiliar.setProgramasSociais(rs.getBoolean("programas_sociais"));
                estruturaFamiliar.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (estruturaFamiliar);

    }


    public void excluir(int search) throws SQLException {
    	
        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM estrutura_familiar WHERE id = ?");
            stmt.setInt(1, search);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }
    
    public void excluirByAluno(int ra) throws SQLException {
    	int idEstruturaFamiliar = -1;
    	
        try {
        	System.out.println("[EstruturaFamiliarDAO] Buscando ID Estrutura Familiar pelo RA");
        	PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT id FROM estrutura_familiar WHERE ra_aluno = ?");
        	stmt.setInt(1, ra);
        	ResultSet rs = stmt.executeQuery();
        	
        	if(rs.next() == true) {
        		System.out.println("[EstruturaFamiliarDAO] Adicionando ID Estrutura Familiar na variável");
        		idEstruturaFamiliar = rs.getInt("id");
        	}

        	System.out.println("[EstruturaFamiliarDAO] ID Estrutura Familiar: " + idEstruturaFamiliar);
        	
        	if(idEstruturaFamiliar > -1) {
        		System.out.println("[EstruturaFamiliarDAO] Deletanto Automovel, Imovel, Despesa e EstruturaFamiliar");
        		
	        	this.excluir(idEstruturaFamiliar);
        	}
        	stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(EstruturaFamiliar estruturaFamiliar, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE estrutura_familiar SET estado_civil_pais=?, crianca_reside_com=?, problemas_financeiros=?, uso_de_alcool_drogas=?, alguem_agressivo=?, programas_sociais=?, ra_aluno=? WHERE id=?");

        stmt.setString(1,estruturaFamiliar.getEstadoCivilPais());
        stmt.setString(2,estruturaFamiliar.getCriancaResideCom());
        stmt.setBoolean(3,estruturaFamiliar.isProblemasFinanceiros());
        stmt.setBoolean(4,estruturaFamiliar.isUsoAlcoolDrogas());
        stmt.setBoolean(5,estruturaFamiliar.isAlguemAgressivo());
        stmt.setBoolean(6,estruturaFamiliar.isProgramasSociais());
        stmt.setInt(7, estruturaFamiliar.getAluno().getRa());
        stmt.setInt(8, id);

        stmt.execute();
        stmt.close();
    }
}
