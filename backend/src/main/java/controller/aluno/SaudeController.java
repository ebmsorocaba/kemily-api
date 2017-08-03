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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jdbc.dao.aluno.SaudeDAO;
import model.aluno.Saude;

@RestController
public class SaudeController {
	private Map<Integer, Saude> saudes;
	private SaudeDAO saudeDao = new SaudeDAO();
	
	public SaudeController() throws SQLException {
		saudes = new HashMap<Integer, Saude>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/saude", method = RequestMethod.GET)
	public ResponseEntity<List<Saude>> listar() throws SQLException {
		int index = 0;
		
		List<Saude> saudesGetted = new ArrayList<Saude>();
		saudes = new HashMap<Integer, Saude>();
		
		saudesGetted = saudeDao.getLista();
		
		for(Saude saude : saudesGetted) {
			saudes.put(index, saude);
			index++;
		}
		
		return new ResponseEntity<List<Saude>>(new ArrayList<Saude>(saudes.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/saude/{raAluno}", method = RequestMethod.GET)
	public ResponseEntity<Saude> buscar(@PathVariable("raAluno") int raAluno) throws SQLException {
		Saude saude = saudeDao.getSaude(raAluno);
		
		if(saude == null) {
			return new ResponseEntity<Saude>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Saude>(saude, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/saude/{raAluno}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("raAluno") int raAluno) throws SQLException {
		saudeDao.excluir(raAluno);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/saude", method = RequestMethod.POST)
	public ResponseEntity<Saude> addSaude(@RequestBody Saude saude)  throws JsonParseException, JsonMappingException, IOException, SQLException {
		saudeDao.adiciona(saude);
		return new ResponseEntity<Saude>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value= "/api/saude/{raAluno}", method = RequestMethod.PUT)
	public ResponseEntity<Saude> updateSaude(@RequestBody Saude saude, @PathVariable("raAluno") int raAluno) throws JsonParseException, JsonMappingException, IOException, SQLException {
		saudeDao.altera(saude, raAluno);
		return new ResponseEntity<Saude>(saude, HttpStatus.CREATED);
	}

}
