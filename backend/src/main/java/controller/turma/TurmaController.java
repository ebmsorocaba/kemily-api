package controller.turma;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

import jdbc.dao.turma.TurmaDAO;
import model.turma.Turma;

@RestController
public class TurmaController {
	private TurmaDAO turmaDao = new TurmaDAO();
	
	public TurmaController() throws SQLException {}
	
	@CrossOrigin
	@RequestMapping(value = "/api/turma", method = RequestMethod.GET)
	public ResponseEntity<List<Turma>> listar() throws SQLException {
		List<Turma> turmas = new ArrayList<Turma>();
		
		turmas = turmaDao.getLista();
		
		return new ResponseEntity<List<Turma>>(turmas, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/turma/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> buscar(@PathVariable("id") int id) throws SQLException {
		
		Turma turma = turmaDao.getTurma(id);
		
		if(turma == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
		
		return new ResponseEntity<Turma>(turma, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/turma/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) throws SQLException {
		turmaDao.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/turma", method = RequestMethod.POST)
	public ResponseEntity<Turma> addTurma(@RequestBody Turma turma) throws JsonParseException, JsonMappingException, IOException, SQLException {
		turmaDao.adiciona(turma);
		return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/turma/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		turmaDao.altera(turma, id);
		return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
	}
}
