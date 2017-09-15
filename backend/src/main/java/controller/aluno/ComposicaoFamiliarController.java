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

import jdbc.dao.aluno.ComposicaoFamiliarDAO;
import model.aluno.ComposicaoFamiliar;

@RestController
public class ComposicaoFamiliarController {
	private Map<Integer, ComposicaoFamiliar> parentes;
	private ComposicaoFamiliarDAO parenteDao = new ComposicaoFamiliarDAO();
	
	public ComposicaoFamiliarController() throws SQLException {
		parentes = new HashMap<Integer, ComposicaoFamiliar>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/composicaoFamiliar", method = RequestMethod.GET)
	public ResponseEntity<List<ComposicaoFamiliar>> listar() throws SQLException {
		int index = 0;
		
		List<ComposicaoFamiliar> parentesGetted = new ArrayList<ComposicaoFamiliar>();
		parentes = new HashMap<Integer, ComposicaoFamiliar>();
		
		parentesGetted = parenteDao.getLista();
		
		for(ComposicaoFamiliar par : parentesGetted) {
			parentes.put(index, par);
			index++;
		}
		
		return new ResponseEntity<List<ComposicaoFamiliar>>(new ArrayList<ComposicaoFamiliar>(parentes.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/composicaoFamiliar/{id}", method = RequestMethod.GET)
	public ResponseEntity<ComposicaoFamiliar> buscar(@PathVariable("id") int id) throws SQLException {
		ComposicaoFamiliar parente = parenteDao.getParente(id);
		if(parente == null) {
			return new ResponseEntity<ComposicaoFamiliar>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ComposicaoFamiliar>(parente, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/composicaoFamiliar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) throws SQLException {
		parenteDao.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/composicaoFamiliar", method = RequestMethod.POST)
	public ResponseEntity<ComposicaoFamiliar> addParente(@RequestBody ComposicaoFamiliar parente) throws JsonParseException, JsonMappingException, IOException, SQLException {
		parenteDao.adiciona(parente);
		
		return new ResponseEntity<ComposicaoFamiliar>(parente, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/composicaoFamiliar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ComposicaoFamiliar> buscar(@RequestBody ComposicaoFamiliar parente, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		parenteDao.altera(parente, id);
		return new ResponseEntity<ComposicaoFamiliar>(parente, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/composicaoFamiliar", method = RequestMethod.GET, params={"ra_aluno"})
	public ResponseEntity<ComposicaoFamiliar> buscarAluno(@RequestParam("ra_aluno") int ra_aluno) throws SQLException {
		ComposicaoFamiliar parente = parenteDao.getParenteAluno(ra_aluno);
		if(parente == null) {
			return new ResponseEntity<ComposicaoFamiliar>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ComposicaoFamiliar>(parente, HttpStatus.OK);
	}
}
