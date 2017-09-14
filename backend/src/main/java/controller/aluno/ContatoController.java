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

import jdbc.dao.aluno.ContatoDAO;
import jdbc.dao.aluno.ContatoProfissionalDAO;
import jdbc.dao.aluno.ContatoResponsavelDAO;
import model.aluno.Contato;
import model.aluno.ContatoProfissional;
import model.aluno.ContatoResponsavel;

@RestController
public class ContatoController {
	private Map<Integer, ContatoResponsavel> responsaveis;
	private Map<Integer, ContatoProfissional> profissionais;
	private Map<Integer, Contato> genericos;
	private ContatoDAO contatoDao = new ContatoDAO();
	private ContatoResponsavelDAO contatoResponsavelDAO = new ContatoResponsavelDAO();
	private ContatoProfissionalDAO contatoProfissionalDAO = new ContatoProfissionalDAO();
	
	public ContatoController() throws SQLException {
		responsaveis = new HashMap<Integer, ContatoResponsavel>();
		profissionais = new HashMap<Integer, ContatoProfissional>();
		genericos = new HashMap<Integer, Contato>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> listar() throws SQLException {
		int indexResp = 0;
		int indexProf = 0;
		int indexGen = 0;
		
		List<Contato> contatosGetted = new ArrayList<Contato>();
		responsaveis = new HashMap<Integer, ContatoResponsavel>();
		profissionais = new HashMap<Integer, ContatoProfissional>();
		genericos = new HashMap<Integer, Contato>();
		
		contatosGetted = contatoDao.getLista();
		
		for(Contato contato : contatosGetted) {
			
			if(contato.getTipo().equals("Responsavel")) {
				ContatoResponsavel contatoResp = contatoResponsavelDAO.getContatoResponsavel(contato.getId()); 
				contatoResp.setNome(contato.getNome());
				contatoResp.setTelefone(contato.getTelefone());
				contatoResp.setEmail(contato.getEmail());
				contatoResp.setRedeSocial(contato.getRedeSocial());
				contatoResp.setTipo(contato.getTipo());
				contatoResp.setAluno(contato.getAluno());
				
				responsaveis.put(indexResp, contatoResp);
				indexResp++;
				
			} else if(contato.getTipo().equals("Profissional")) {
				ContatoProfissional contatoProf = contatoProfissionalDAO.getContatoProfissional(contato.getId());
				
				contatoProf.setNome(contato.getNome());
				contatoProf.setTelefone(contato.getTelefone());
				contatoProf.setEmail(contato.getEmail());
				contatoProf.setRedeSocial(contato.getRedeSocial());
				contatoProf.setTipo(contato.getTipo());
				contatoProf.setAluno(contato.getAluno());
				
				profissionais.put(indexProf, contatoProf);
				indexProf++;
				
			} else {
				genericos.put(indexGen, contato);
				indexGen++;
			}
		}
		
		List<Contato> contatos = new ArrayList<Contato>();
		contatos.addAll(responsaveis.values());
		contatos.addAll(profissionais.values());
		contatos.addAll(genericos.values());
		
		return new ResponseEntity<List<Contato>>(contatos, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/{id}", method = RequestMethod.GET)
	public ResponseEntity<Contato> buscar(@PathVariable("id") int id) throws SQLException {
		Contato contato = contatoDao.getContato(id);
		ContatoProfissional contatoProf = null;
		ContatoResponsavel contatoResp = null;

		if (contato == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if(contato.getTipo().equals("Profissional")) {
			
			contatoProf = contatoProfissionalDAO.getContatoProfissional(contato.getId());
			
			contatoProf.setNome(contato.getNome());
			contatoProf.setTelefone(contato.getTelefone());
			contatoProf.setEmail(contato.getEmail());
			contatoProf.setRedeSocial(contato.getRedeSocial());
			contatoProf.setTipo(contato.getTipo());
			contatoProf.setAluno(contato.getAluno());
			
			return new ResponseEntity<Contato>(contatoProf, HttpStatus.OK);
			
		} else if (contato.getTipo().equals("Responsavel")){
			
			contatoResp = contatoResponsavelDAO.getContatoResponsavel(contato.getId());
			
			contatoResp.setNome(contato.getNome());
			contatoResp.setTelefone(contato.getTelefone());
			contatoResp.setEmail(contato.getEmail());
			contatoResp.setRedeSocial(contato.getRedeSocial());
			contatoResp.setTipo(contato.getTipo());
			contatoResp.setAluno(contato.getAluno());
			
			return new ResponseEntity<Contato>(contatoResp, HttpStatus.OK);
			
		} else  {
			
			return new ResponseEntity<Contato>(contato, HttpStatus.OK);
			
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato", method = RequestMethod.POST)
	public ResponseEntity<Contato> addContato(@RequestBody Contato contato) throws JsonParseException, JsonMappingException, IOException, SQLException {

		if (contato.getTipo().equals("Generico")) {
			contatoDao.adicionar(contato);
			return new ResponseEntity<Contato>(contato, HttpStatus.CREATED);
		}
		return new ResponseEntity<Contato>(contato, HttpStatus.BAD_REQUEST );
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/profissional", method = RequestMethod.POST)
	public ResponseEntity<Contato> addContato(@RequestBody ContatoProfissional contatoProfissional) throws JsonParseException, JsonMappingException, IOException, SQLException {
		if (contatoProfissional.getTipo().equals("Profissional") ){
			contatoProfissionalDAO.adicionar(contatoProfissional);
			return new ResponseEntity<Contato>(contatoProfissional, HttpStatus.CREATED);
		}
		return new ResponseEntity<Contato>(contatoProfissional, HttpStatus.BAD_REQUEST );
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/responsavel", method = RequestMethod.POST)
	public ResponseEntity<Contato> addContato(@RequestBody ContatoResponsavel contatoResponsavel) throws JsonParseException, JsonMappingException, IOException, SQLException {
		if (contatoResponsavel.getTipo().equals("Responsavel")) {
			contatoResponsavelDAO.adicionar(contatoResponsavel);
			return new ResponseEntity<Contato>(contatoResponsavel, HttpStatus.CREATED);
		}
		return new ResponseEntity<Contato>(contatoResponsavel, HttpStatus.BAD_REQUEST );
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Contato> updateContato(@RequestBody Contato contato, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		if (contato.getTipo().equals("Generico")) {
			contatoDao.altera(contato, id);
			return new ResponseEntity<Contato>(contato, HttpStatus.CREATED);
		}
		return new ResponseEntity<Contato>(contato, HttpStatus.BAD_REQUEST );
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/responsavel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ContatoResponsavel> updateContatoResponsavel(@RequestBody ContatoResponsavel contatoResponsavel, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		if (contatoResponsavel.getTipo().equals("Responsavel")) {
			contatoDao.altera(contatoResponsavel, id);
			contatoResponsavelDAO.altera(contatoResponsavel, id);
			return new ResponseEntity<ContatoResponsavel>(contatoResponsavel, HttpStatus.CREATED);
		}
		return new ResponseEntity<ContatoResponsavel>(contatoResponsavel, HttpStatus.BAD_REQUEST );
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/profissional/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ContatoProfissional> updateContatoProfissional(@RequestBody ContatoProfissional contatoProfissional, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		if (contatoProfissional.getTipo().equals("Profissional")) {
			contatoDao.altera(contatoProfissional, id);
			contatoProfissionalDAO.altera(contatoProfissional, id);
			return new ResponseEntity<ContatoProfissional>(contatoProfissional, HttpStatus.CREATED);
		}
		return new ResponseEntity<ContatoProfissional>(contatoProfissional, HttpStatus.BAD_REQUEST );
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) throws SQLException {
		contatoProfissionalDAO.excluir(id);
		contatoResponsavelDAO.excluir(id);
		contatoDao.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
