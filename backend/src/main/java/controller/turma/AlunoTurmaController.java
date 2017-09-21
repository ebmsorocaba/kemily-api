package controller.turma;

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

import jdbc.dao.turma.AlunoTurmaDAO;
import model.turma.AlunoTurma;
import model.turma.Turma;

@RestController
public class AlunoTurmaController {
	private Map<Integer, AlunoTurma> listaAlunoTurma;
	private Map<Integer, AlunoTurma> listaAlunoTurmaByTurma;
	private Map<Integer, AlunoTurma> listaAlunoTurmaByAluno;
	private AlunoTurmaDAO alunoTurmaDao = new AlunoTurmaDAO();
	
	public AlunoTurmaController() throws SQLException {
		listaAlunoTurma = new HashMap<Integer, AlunoTurma>();
		listaAlunoTurmaByTurma = new HashMap<Integer, AlunoTurma>();
		listaAlunoTurmaByAluno = new HashMap<Integer, AlunoTurma>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma", method = RequestMethod.GET)
	public ResponseEntity<List<AlunoTurma>> listar() throws SQLException {
		int index = 0;
		
		List<AlunoTurma> alunoTurmasGetted = new ArrayList<AlunoTurma>();
		listaAlunoTurma = new HashMap<Integer, AlunoTurma>();
		
		alunoTurmasGetted = alunoTurmaDao.getListaAlunoTurma();
		
		for(AlunoTurma at : alunoTurmasGetted) {
			listaAlunoTurma.put(index, at);
			index++;
		}
		
		return new ResponseEntity<List<AlunoTurma>>(new ArrayList<AlunoTurma>(listaAlunoTurma.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma/turma/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AlunoTurma>> listarByTurma(@PathVariable("id") int id) throws SQLException {
		int index = 0;
		
		List<AlunoTurma> alunoTurmaByTurmaGetted = new ArrayList<AlunoTurma>();
		listaAlunoTurmaByTurma = new HashMap<Integer, AlunoTurma>();
		
		alunoTurmaByTurmaGetted = alunoTurmaDao.getListaAlunoTurmaByTurma(id);
		
		for(AlunoTurma at : alunoTurmaByTurmaGetted) {
			listaAlunoTurmaByTurma.put(index, at);
			index++;
		}
		
		return new ResponseEntity<List<AlunoTurma>>(new ArrayList<AlunoTurma>(listaAlunoTurmaByTurma.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma/aluno/{ra}", method = RequestMethod.GET)
	public ResponseEntity<List<AlunoTurma>> listarByAluno(@PathVariable("ra") int ra) throws SQLException {
		int index = 0;
		
		List<AlunoTurma> alunoTurmaByAlunoGetted = new ArrayList<AlunoTurma>();
		listaAlunoTurmaByAluno = new HashMap<Integer, AlunoTurma>();
		
		alunoTurmaByAlunoGetted = alunoTurmaDao.getListaAlunoTurmaByAluno(ra);
		
		for(AlunoTurma at : alunoTurmaByAlunoGetted) {
			listaAlunoTurmaByAluno.put(index, at);
			index++;
		}
		
		return new ResponseEntity<List<AlunoTurma>>(new ArrayList<AlunoTurma>(listaAlunoTurmaByAluno.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma/{ra}", method = RequestMethod.DELETE, params= {"id"})
	public ResponseEntity<?> deletar(@PathVariable("ra") int ra, @RequestParam("id") int id) throws SQLException {
		alunoTurmaDao.excluir(ra, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma/turma/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletarByTurma(@PathVariable("id") int id) throws SQLException {
		alunoTurmaDao.excluirByTurma(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma/aluno/{ra}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletarByAluno(@PathVariable("ra") int ra) throws SQLException {
		alunoTurmaDao.excluirByAluno(ra);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/alunoTurma", method = RequestMethod.POST)
	public ResponseEntity<List<AlunoTurma>> addAlunoTurma(@RequestBody List<AlunoTurma> alunoTurma) throws JsonParseException, JsonMappingException, IOException, SQLException {
		alunoTurmaDao.adiciona(alunoTurma);
		return new ResponseEntity<List<AlunoTurma>>(alunoTurma, HttpStatus.CREATED);
	}
}
