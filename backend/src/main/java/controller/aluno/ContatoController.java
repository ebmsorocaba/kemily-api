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
import jdbc.dao.aluno.Contato_ProfissionalDAO;
import jdbc.dao.aluno.Contato_ResponsavelDAO;
import model.aluno.Contato;
import model.aluno.Contato_Profissional;
import model.aluno.Contato_Responsavel;

@RestController
public class ContatoController {
	private Map<Integer, Contato_Responsavel> responsaveis;
	private Map<Integer, Contato_Profissional> profissionais;
	private Map<Integer, Contato> genericos;
	private ContatoDAO contatoDao = new ContatoDAO();
	private Contato_ResponsavelDAO contato_ResponsavelDAO = new Contato_ResponsavelDAO();
	private Contato_ProfissionalDAO contato_ProfissionalDAO = new Contato_ProfissionalDAO();
	
	public ContatoController() throws SQLException {
		responsaveis = new HashMap<Integer, Contato_Responsavel>();
		profissionais = new HashMap<Integer, Contato_Profissional>();
		genericos = new HashMap<Integer, Contato>();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> listar() throws SQLException {
		int index_resp = 0;
		int index_prof = 0;
		int index_gen = 0;
		
		List<Contato> contatosGetted = new ArrayList<Contato>();
		responsaveis = new HashMap<Integer, Contato_Responsavel>();
		profissionais = new HashMap<Integer, Contato_Profissional>();
		genericos = new HashMap<Integer, Contato>();
		
		contatosGetted = contatoDao.getLista();
		
		for(Contato contato : contatosGetted) {
			
			if(contato.getTipo().equals("responsavel")) {
				Contato_Responsavel contato_resp = contato_ResponsavelDAO.getContato_Responsavel(contato.getId()); 
				contato_resp.setNome(contato.getNome());
				contato_resp.setTelefone(contato.getTelefone());
				contato_resp.setTipo(contato.getTipo());
				contato_resp.setAluno(contato.getAluno());
				
				responsaveis.put(index_resp, contato_resp);
				index_resp++;
				
			} else if(contato.getTipo().equals("profissional")) {
				Contato_Profissional contato_prof = contato_ProfissionalDAO.getContato_Profissional(contato.getId());
				
				contato_prof.setNome(contato.getNome());
				contato_prof.setTelefone(contato.getTelefone());
				contato_prof.setTipo(contato.getTipo());
				contato_prof.setAluno(contato.getAluno());
				
				profissionais.put(index_prof, contato_prof);
				index_prof++;
				
			} else {
				genericos.put(index_gen, contato);
				index_gen++;
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
		Contato_Profissional contato_prof = null;
		Contato_Responsavel contato_resp = null;
		
		if(contato == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(contato.getTipo().equals("profissional")) {
			
			contato_prof = contato_ProfissionalDAO.getContato_Profissional(contato.getId());
			
			contato_prof.setNome(contato.getNome());
			contato_prof.setTelefone(contato.getTelefone());
			contato_prof.setTipo(contato.getTipo());
			contato_prof.setAluno(contato.getAluno());
			
			return new ResponseEntity<Contato>(contato_prof, HttpStatus.OK);
			
		} else if (contato.getTipo().equals("responsavel")){
			
			contato_resp = contato_ResponsavelDAO.getContato_Responsavel(contato.getId());
			
			contato_resp.setNome(contato.getNome());
			contato_resp.setTelefone(contato.getTelefone());
			contato_resp.setTipo(contato.getTipo());
			contato_resp.setAluno(contato.getAluno());
			
			return new ResponseEntity<Contato>(contato_resp, HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<Contato>(contato, HttpStatus.OK);
			
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato", method = RequestMethod.POST)
	public ResponseEntity<Contato> addContato(@RequestBody Contato contato) throws JsonParseException, JsonMappingException, IOException, SQLException {
		contatoDao.adicionar(contato);
		return new ResponseEntity<Contato>(contato, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/profissional", method = RequestMethod.POST)
	public ResponseEntity<Contato> addContato(@RequestBody Contato_Profissional contatoProfissional) throws JsonParseException, JsonMappingException, IOException, SQLException {
		contato_ProfissionalDAO.adicionar(contatoProfissional);
		return new ResponseEntity<Contato>(contatoProfissional, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/responsavel", method = RequestMethod.POST)
	public ResponseEntity<Contato> addContato(@RequestBody Contato_Responsavel contatoResponsavel) throws JsonParseException, JsonMappingException, IOException, SQLException {
		contato_ResponsavelDAO.adicionar(contatoResponsavel);
		return new ResponseEntity<Contato>(contatoResponsavel, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Contato> updateContato(@RequestBody Contato contato, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		contatoDao.altera(contato, id);
		return new ResponseEntity<Contato>(contato, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/responsavel/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Contato_Responsavel> updateContatoResponsavel(@RequestBody Contato_Responsavel contatoResponsavel, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		contatoDao.altera(contatoResponsavel, id);
		contato_ResponsavelDAO.altera(contatoResponsavel, id);
		return new ResponseEntity<Contato_Responsavel>(contatoResponsavel, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/profissional/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Contato_Profissional> updateContatoProfissional(@RequestBody Contato_Profissional contatoProfissional, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {
		contatoDao.altera(contatoProfissional, id);
		contato_ProfissionalDAO.altera(contatoProfissional, id);
		return new ResponseEntity<Contato_Profissional>(contatoProfissional, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/contato/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") int id) {
		contato_ProfissionalDAO.excluir(id);
		contato_ResponsavelDAO.excluir(id);
		contatoDao.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
