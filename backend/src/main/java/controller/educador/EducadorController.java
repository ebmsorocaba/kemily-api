package controller.educador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jdbc.dao.educador.EducadorDAO;
import model.educador.Educador;

@RestController
public class EducadorController {
	private Map<Integer, Educador> educadores;
	private EducadorDAO educadorDao = new EducadorDAO();
	
	public EducadorController() throws SQLException {
		educadores = new HashMap<Integer, Educador>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/educador", method = RequestMethod.GET)
	public ResponseEntity<List<Educador>> listar() throws SQLException {
		int index = 0;
		
		List<Educador> educadoresGetted = new ArrayList<Educador>();
		educadores = new HashMap<Integer, Educador>();
		
		educadoresGetted = educadorDao.getLista();
		
		for(Educador edu : educadoresGetted) {
			educadores.put(index, edu);
			index++;
		}
		
		return new ResponseEntity<List<Educador>>(new ArrayList<Educador>(educadores.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/educador/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Educador> buscar(@PathVariable("cpf") String cpf) throws SQLException {
		Educador edu = educadorDao.getEducador(cpf);
		if(edu == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Educador>(edu, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/educador/{cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("cpf") String cpf) throws SQLException {
		educadorDao.excluir(cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/educador", method = RequestMethod.POST)
	public ResponseEntity<Educador> addEducador(@RequestBody Educador educador) throws JsonParseException, JsonMappingException, IOException, SQLException {
		educadorDao.adiciona(educador);
		return new ResponseEntity<Educador>(educador, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/educador/{cpf}", method = RequestMethod.PUT)
	public ResponseEntity<Educador> updateEducador(@RequestBody Educador educador, @PathVariable("cpf") String cpf) throws JsonParseException, JsonMappingException, IOException, SQLException {
		educadorDao.altera(educador, cpf);
		return new ResponseEntity<Educador>(educador, HttpStatus.CREATED);
	}
	
}
