package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Estrutura_Familiar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Estrutura_FamiliarDAO {
    private Connection connection;
    private AlunoDAO alunoDao;

    public Estrutura_FamiliarDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.alunoDao = new AlunoDAO();
    }

    public Estrutura_Familiar adiciona(Estrutura_Familiar estrutura_Familiar) throws SQLException {
    	try (
    			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO estrutura_Familiar (estado_civil_pais, crianca_reside_com, problemas_financeiros, uso_de_alcool_drogas, alguem_agressivo, programas_sociais, ra_aluno) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    	) {
	        stmt.setString(1,estrutura_Familiar.getEstado_civil_pais());
	        stmt.setString(2,estrutura_Familiar.getCrianca_reside_com());
	        stmt.setBoolean(3,estrutura_Familiar.isProblemas_financeiros());
	        stmt.setBoolean(4,estrutura_Familiar.isUso_alcool_drogas());
	        stmt.setBoolean(5,estrutura_Familiar.isAlguem_agressivo());
	        stmt.setBoolean(6,estrutura_Familiar.isProgramas_sociais());
	        stmt.setInt(7, estrutura_Familiar.getAluno().getRa());

	        // executa
	        int key = stmt.executeUpdate();
	        
	        if(key == 0) {
	        	estrutura_Familiar.setId(-1);
	        	throw new SQLException("Falha na criação do EstruturaFamiliar, linha nao alterada");
	        }
	        
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	        	if(generatedKeys.next()) {
	        		estrutura_Familiar.setId(generatedKeys.getInt(1));
	        	} else {
	        		estrutura_Familiar.setId(-1);
	        		throw new SQLException("Falha na criação do EstruturaFamiliar, ID nao retornado"); 
	        	}
	        }
	        
	     stmt.close();
	        
    	}
	        
        return estrutura_Familiar;
    }

    public List<Estrutura_Familiar> getLista() throws SQLException {

        List<Estrutura_Familiar> estruturas_Familiares = new ArrayList<Estrutura_Familiar>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM estrutura_Familiar");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Estrutura_Familiar estrutura_Familiar = new Estrutura_Familiar();

            estrutura_Familiar.setId(rs.getInt("id"));
            estrutura_Familiar.setEstado_civil_pais(rs.getString("estado_civil_pais"));
            estrutura_Familiar.setCrianca_reside_com(rs.getString("crianca_reside_com"));
            estrutura_Familiar.setProblemas_financeiros(rs.getBoolean("problemas_financeiros"));
            estrutura_Familiar.setUso_alcool_drogas(rs.getBoolean("uso_de_alcool_drogas"));
            estrutura_Familiar.setAlguem_agressivo(rs.getBoolean("alguem_agressivo"));
            estrutura_Familiar.setProgramas_sociais(rs.getBoolean("programas_sociais"));
            estrutura_Familiar.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));


            estruturas_Familiares.add(estrutura_Familiar);

        }

        rs.close();
        stmt.close();

        return estruturas_Familiares;

    }

    public Estrutura_Familiar getEstrutura_Familiar(int search) throws SQLException {

        Estrutura_Familiar estrutura_Familiar = new Estrutura_Familiar();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM estrutura_Familiar WHERE " + "id = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                estrutura_Familiar.setId(rs.getInt("id"));
                estrutura_Familiar.setEstado_civil_pais(rs.getString("estado_civil_pais"));
                estrutura_Familiar.setCrianca_reside_com(rs.getString("crianca_reside_com"));
                estrutura_Familiar.setProblemas_financeiros(rs.getBoolean("problemas_financeiros"));
                estrutura_Familiar.setUso_alcool_drogas(rs.getBoolean("uso_de_alcool_drogas"));
                estrutura_Familiar.setAlguem_agressivo(rs.getBoolean("alguem_agressivo"));
                estrutura_Familiar.setProgramas_sociais(rs.getBoolean("programas_sociais"));
                estrutura_Familiar.setAluno(alunoDao.getAluno(rs.getInt("ra_aluno")));
            }
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (estrutura_Familiar);

    }


    public void excluir(int search) throws SQLException {
    	AutomovelDAO autoDAO = new AutomovelDAO();
    	ImovelDAO imovelDAO = new ImovelDAO();
    	DespesaDAO despDAO = new DespesaDAO();
    	
        try {

        	System.out.println("[EstruturaFamiliarDAO] Deletando Automovel");
        	autoDAO.excluirByIdEstruturaFamiliar(search);
        	
        	System.out.println("[EstruturaFamiliarDAO] Deletando Imovel");
        	imovelDAO.excluirByIdEstruturaFamiliar(search);
        	
        	System.out.println("[EstruturaFamiliarDAO] Deletando Despesa");
        	despDAO.excluir(search);
        	
        	System.out.println("[EstruturaFamiliarDAO] Deletando Estrutura Familiar");
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM estrutura_Familiar WHERE id = ?");
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


    public void altera(Estrutura_Familiar estrutura_Familiar, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE estrutura_Familiar SET estado_civil_pais=?, crianca_reside_com=?, problemas_financeiros=?, uso_de_alcool_drogas=?, alguem_agressivo=?, programas_sociais=?, ra_aluno=? WHERE id=?");

        stmt.setString(1,estrutura_Familiar.getEstado_civil_pais());
        stmt.setString(2,estrutura_Familiar.getCrianca_reside_com());
        stmt.setBoolean(3,estrutura_Familiar.isProblemas_financeiros());
        stmt.setBoolean(4,estrutura_Familiar.isUso_alcool_drogas());
        stmt.setBoolean(5,estrutura_Familiar.isAlguem_agressivo());
        stmt.setBoolean(6,estrutura_Familiar.isProgramas_sociais());
        stmt.setInt(7, estrutura_Familiar.getAluno().getRa());
        stmt.setInt(8, id);

        stmt.execute();
        stmt.close();
    }
}
