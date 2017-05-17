package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import jdbc.ConnectionFactory;
import model.RelatPagAssociado;

public class RelatorioDAO {

	private Connection connection;
	private AssociadoDAO associadoDao = new AssociadoDAO();

	public RelatorioDAO() throws SQLException {
		this.connection = ConnectionFactory.getConnection();
	}

	public List<RelatPagAssociado> relatPagAssociado(String cpf, Date dataInicio, Date dataFim) throws SQLException {

		System.out.println("Chegou no RelatorioDAO <<log para possiveis anomalias por causa de formato de DATA>>");
		System.out.println("Data inicio: " + dataInicio + " Data Final: " + dataFim);
		Locale.setDefault(new Locale("pt", "BR")); 
		Calendar cal = Calendar.getInstance();
		
		List<RelatPagAssociado> pagRelatAssociados = new ArrayList<RelatPagAssociado>();

		PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM pagamento WHERE cpf_associado=? AND data_pgto >= ? AND data_pgto <= ?");
		
		stmt.setString(1, cpf);
		stmt.setDate(2, dataInicio);
		stmt.setDate(3, dataFim);
		
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			// criando o objeto Aluno
			RelatPagAssociado relatPagAssociado = new RelatPagAssociado();
			
			relatPagAssociado.setCpf(rs.getString("cpf_associado"));
			relatPagAssociado.setNome(associadoDao.getAssociado(rs.getString("cpf_associado")).getNome());
			relatPagAssociado.setDataPag(rs.getDate("data_pgto"));
			relatPagAssociado.setValor(rs.getDouble("valor_pago"));
			
			//create calander instance and get required params
			
			cal.setTime(rs.getDate("data_pgto"));

			relatPagAssociado.setMes(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
			// adicionando o objeto à lista
			pagRelatAssociados.add(relatPagAssociado);
		}
		rs.close();
		stmt.close();
		return pagRelatAssociados;

	}


}
