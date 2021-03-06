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
import model.Associado;
import model.Pagamento;
import model.RelatPag;
import model.RelatPagAssociado;

public class RelatorioDAO {

	private Connection connection;
	private AssociadoDAO associadoDao = new AssociadoDAO();
	private FormaPagamentoDAO formPagDAO = new FormaPagamentoDAO();

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
	
	
	//TODO Melhorar e aprimorar essa lógica
	
	public List<RelatPag> relatPag(Date dataInicio, Date dataFim) throws SQLException {

		System.out.println("Chegou no RelatorioDAO <<log para possiveis anomalias por causa de formato de DATA>>");
		System.out.println("Data inicio: " + dataInicio + " Data Final: " + dataFim);
		Locale.setDefault(new Locale("pt", "BR")); 
		//Calendar cal = Calendar.getInstance();
		
		Double total;
		
		Calendar calDataInicio = Calendar.getInstance();
		calDataInicio.setTime(dataInicio);
		
		Calendar calDataFim = Calendar.getInstance();
		calDataFim.setTime(dataFim);
		
		Calendar calSearch = Calendar.getInstance();
		calSearch.setTime(dataInicio);
		
		List<Associado> associados = new ArrayList<Associado>();
		List<RelatPag> pagRelats = new ArrayList<RelatPag>();
		
		associados = associadoDao.getLista();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		calDataInicio.set(Calendar.DAY_OF_MONTH, 1);
		//calDataInicio.set(Calendar.DAY_OF_MONTH, 27);
		
		
		
		for (Associado a : associados) { //Para cada associado cadatrado
			System.out.println("Looping? associado");
			
			RelatPag relatPag = new RelatPag();
			relatPag.setCpf(a.getCpf());
			relatPag.setNome(a.getNome());
			
			List<Pagamento> pags = new ArrayList<Pagamento>();
			
			calDataInicio.setTime(dataInicio);

			total = 0.0;
			
			System.out.println("Looping? out Month: " + calDataInicio.getTime() + " " + calDataFim.getTime());
			
			
			while(calDataInicio.get(Calendar.MONTH) <= calDataFim.get(Calendar.MONTH) || calDataInicio.get(Calendar.YEAR) < calDataFim.get(Calendar.YEAR)){
				Pagamento pag = new Pagamento();
				
				
				
				stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM pagamento WHERE cpf_associado=? AND data_pgto >= ? AND data_pgto <= ?");
				
				stmt.setString(1, a.getCpf());
				calDataInicio.set(Calendar.DAY_OF_MONTH, 1);
				
				stmt.setDate(2, new java.sql.Date(calDataInicio.getTimeInMillis()));
				System.out.println("DATA INI: " + calDataInicio.getTime());
				
				calSearch = calDataInicio;
				calSearch.set(Calendar.DATE, calSearch.getActualMaximum(Calendar.DATE)); //Para setar o final do mês a ser pesquisado
;				
				
				stmt.setDate(3, new java.sql.Date(calSearch.getTimeInMillis()));
				System.out.println("DATA FIM: " + calSearch.getTime());
				
				rs = stmt.executeQuery();
				
				while (rs.next()) {

					System.out.println("Looping? post sql");
					
					pag.setId(rs.getInt("id"));
					pag.setValorPago(rs.getDouble("valor_pago"));
					pag.setVencimento(rs.getDate("vencimento"));
					pag.setDataPgto(rs.getDate("data_pgto"));
					pag.setFormaPgto(formPagDAO.getFormaPgto(a.getCpf(),rs.getString("forma_pgto_efetuada")));	
					
					
				}
				
				if(pag.getDataPgto() == null){ //Paga o front saber de que mês/ano esse pagamento se trata
					pag.setDataPgto(new java.sql.Date(calDataInicio.getTimeInMillis()));
					System.out.println(pag.getDataPgto());
				}
				
				total += pag.getValorPago();
				
				pags.add(pag);
				
				if(calDataInicio.get(Calendar.MONTH) == calDataFim.get(Calendar.MONTH) && calDataInicio.get(Calendar.YEAR) == calDataFim.get(Calendar.YEAR)){ //Pra sair do bug do mês de dezembro
					break;
				}
				calDataInicio.add(Calendar.MONTH,1);
				
			
			}
			relatPag.setPagamentos(pags);
			relatPag.setTotal(total);
			
			pagRelats.add(relatPag);
		}
		
		System.out.println("Looping? ???????");
		rs.close();
		stmt.close();
		return pagRelats;

	}


}
