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

import jdbc.dao.aluno.EnderecoDAO;
import model.aluno.Endereco;

@RestController
public class EnderecoController {
	private Map<Integer, Endereco> enderecos;
	private EnderecoDAO enderecoDao = new EnderecoDAO();
	
	public EnderecoController() throws SQLException {
		enderecos = new HashMap<Integer, Endereco>();
	}

	@CrossOrigin
	@RequestMapping(value = "/api/endereco", method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> listar() throws SQLException {
		int index = 0;
		
		List<Endereco> enderecosGetted = new ArrayList<Endereco>();
		enderecos = new HashMap<Integer, Endereco>();
		
		enderecosGetted = enderecoDao.getLista();
		
		for(Endereco end : enderecosGetted) {
			enderecos.put(index, end);
			index++;
		}
		
		return new ResponseEntity<List<Endereco>>(new ArrayList<Endereco>(enderecos.values()), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/endereco/{cep}", method = RequestMethod.GET, params= {"numero"})
	public ResponseEntity<Endereco> buscar(@PathVariable("cep") String cep, @RequestParam("numero") String numero) throws JsonParseException, JsonMappingException, IOException, SQLException {
		System.out.println("cep: " + cep + ", numero: " + numero);
		
		Endereco endereco = enderecoDao.getEndereco(cep, numero);
		
		if(endereco == null) {
			return new ResponseEntity<Endereco>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/endereco/{cep}", method = RequestMethod.DELETE, params= {"numero"})
	public ResponseEntity<?> deletar(@PathVariable("cep") String cep, @RequestParam("numero") String numero) throws JsonParseException, JsonMappingException, IOException, SQLException {
		enderecoDao.excluir(cep, numero);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/endereco", method = RequestMethod.POST)
	public ResponseEntity<Endereco> addEndereco(@RequestBody Endereco endereco) throws JsonParseException, JsonMappingException, IOException, SQLException {
		enderecoDao.adiciona(endereco);
		return new ResponseEntity<Endereco>(HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/endereco/{cep}", method = RequestMethod.PUT, params = {"numero"})
	public ResponseEntity<Endereco> buscar(@RequestBody Endereco endereco, @PathVariable("cep") String cep, @RequestParam("numero") String numero) throws JsonParseException, JsonMappingException, IOException, SQLException {
		enderecoDao.altera(endereco, cep, numero);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/endereco", method = RequestMethod.GET, params={"ra_aluno"})
	public ResponseEntity<Endereco> buscarAluno(@RequestParam("ra_aluno") int ra_aluno) throws SQLException {
		Endereco endereco = enderecoDao.getEnderecoAluno(ra_aluno);
		if(endereco == null) {
			return new ResponseEntity<Endereco>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	}
}
