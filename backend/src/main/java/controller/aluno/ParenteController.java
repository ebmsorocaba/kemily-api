package controller.aluno;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jdbc.dao.aluno.ParenteDAO;
import model.aluno.Parente;

@RestController
public class ParenteController {
	private Map<Integer, Parente> parentes;
	private ParenteDAO parenteDao = new ParenteDAO();
	
	public ParenteController() throws SQLException {
		parentes = new HashMap<Integer, Parente>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/parente", method = RequestMethod.GET)
	public ResponseEntity<List<Parente>> listar() throws SQLException {
		int index = 0;
		
		List<Parente> parentesGetted = new ArrayList<Parente>();
		parentes = new HashMap<Integer, Parente>();
		
		parentesGetted = parenteDao.getLista();
		
		for(Parente par : parentesGetted) {
			parentes.put(index, par);
			index++;
		}
		
		return new ResponseEntity<List<Parente>>(new ArrayList<Parente>(parentes.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/parente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Parente> buscar(@PathVariable("id") int id) throws SQLException {
		Parente parente = parenteDao.getParente(id);
		if(parente == null) {
			return new ResponseEntity<Parente>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Parente>(parente, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/parente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) throws SQLException {
		parenteDao.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/parente", method = RequestMethod.POST)
	public ResponseEntity<Parente> addParente(@RequestBody Parente parente) throws JsonParseException, JsonMappingException, IOException, SQLException {
		parenteDao.adiciona(parente);
		
		return new ResponseEntity<Parente>(parente, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/parente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Parente> buscar(@RequestBody Parente parente, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		parenteDao.altera(parente, id);
		return new ResponseEntity<Parente>(parente, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/parente", method = RequestMethod.GET, params={"ra_aluno"})
	public ResponseEntity<Parente> buscarAluno(@RequestParam("ra_aluno") int ra_aluno) throws SQLException {
		Parente parente = parenteDao.getParenteAluno(ra_aluno);
		if(parente == null) {
			return new ResponseEntity<Parente>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Parente>(parente, HttpStatus.OK);
	}
}
