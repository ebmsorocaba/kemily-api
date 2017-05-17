package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;

import jdbc.dao.RelatorioDAO;
import model.RelatPagAssociado;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@RestController
 //E isso
public class RelatorioController {

	private Map<Integer, RelatPagAssociado> pagRelatAssociados;
	private RelatorioDAO relatorioDao = new RelatorioDAO();

	public RelatorioController() throws SQLException {
		pagRelatAssociados = new HashMap<Integer, RelatPagAssociado>();
	}

	@CrossOrigin
	@RequestMapping(value = "/api/relatPagAssociado/{cpf}", method = RequestMethod.GET, params={"dataInicio", "dataFim"})
	public ResponseEntity<List<RelatPagAssociado>> listar(@PathVariable("cpf") String cpf, @RequestParam("dataInicio") String dataInicio, @RequestParam("dataFim") String dataFim) throws SQLException, ParseException {
		int index=0;

		List<RelatPagAssociado> associadosGetted = new ArrayList<RelatPagAssociado>();
		pagRelatAssociados = new HashMap<Integer, RelatPagAssociado>();
		
		//Conversão da data que vem em formato de string por parametro
		
		SimpleDateFormat formatBarra = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatTraco = new SimpleDateFormat("dd-MM-yyyy");
		
		java.util.Date parsedDI; //Aqui declara a DATA do tipo UTIL
    	java.util.Date parsedDF;
		
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
        
		associadosGetted = relatorioDao.relatPagAssociado(cpf, sqlDI, sqlDF);

		
		for (RelatPagAssociado pag : associadosGetted) { //Coloca todos pagamentos vindos do SELECT da DAO em um hashmap
			pagRelatAssociados.put(index, pag);
			index++;
		}

		return new ResponseEntity<List<RelatPagAssociado>>(new ArrayList<RelatPagAssociado>(pagRelatAssociados.values()), HttpStatus.OK);
	}



}
