package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jdbc.dao.PagamentoDAO;
import model.Pagamento;

@CrossOrigin //(origins = "http://localhost:8081/")
@RestController
 //E isso
public class RelatorioController {

	private Map<Integer, Pagamento> pagamentos;
	private PagamentoDAO pagamentoDao = new PagamentoDAO();

	public RelatorioController() throws SQLException {
		pagamentos = new HashMap<Integer, Pagamento>();
	}

	@RequestMapping(value = "/api/relatoriopagamentos", method = RequestMethod.GET, params={"dataInicio", "dataFim"})
	public ResponseEntity<List<Pagamento>> listar(@RequestParam("dataInicio") String dataInicio, @RequestParam("dataFim") String dataFim) throws SQLException, ParseException {
		int index=0;

		List<Pagamento> pagamentosGetted = new ArrayList<Pagamento>();
		pagamentos = new HashMap<Integer, Pagamento>();
		
		//Conversão da data que vem em formato de string por parametro
		
		SimpleDateFormat formatBarra = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatTraco = new SimpleDateFormat("dd-MM-yyyy");
		
		java.util.Date parsedDI; //Aqui declara a DATA do tipo UTIL
    	java.util.Date parsedDF;
		
    	//TODO depois da estabilidade tirar isso aqui
    	//Blindagem temporaria
        try{
        	
        	parsedDI = formatBarra.parse(dataInicio);
        	parsedDF = formatBarra.parse(dataFim);
    		
        	
        } catch(ParseException ex){
        	parsedDI = formatTraco.parse(dataInicio);
        	parsedDF = formatTraco.parse(dataFim);
        }
        
        java.sql.Date sqlDI = new java.sql.Date(parsedDI.getTime()); //Converte para SQL
        java.sql.Date sqlDF = new java.sql.Date(parsedDF.getTime());
        
			pagamentosGetted = pagamentoDao.getListabyDate(sqlDI, sqlDF);

		
		
		for (Pagamento pag : pagamentosGetted) { //Coloca todos pagamentos vindos do SELECT da DAO em um hashmap
			pagamentos.put(index, pag);
			index++;
		}
		
		return new ResponseEntity<List<Pagamento>>(new ArrayList<Pagamento>(pagamentos.values()), HttpStatus.OK);
	}
	
	

}
