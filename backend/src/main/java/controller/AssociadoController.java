package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;

import jdbc.dao.AssociadoDAO;
import jdbc.dao.FormaPagamentoDAO;
import model.Associado;
import model.FormaPagamento;

import java.sql.SQLException;


@RestController
 //E isso
public class AssociadoController {

	private Map<Integer, Associado> associados;
	private AssociadoDAO associadoDao = new AssociadoDAO();
	private FormaPagamentoDAO formPagDao = new FormaPagamentoDAO();

	public AssociadoController() throws SQLException {
		associados = new HashMap<Integer, Associado>();
	}

	@CrossOrigin
	@RequestMapping(value = "/api/associados", method = RequestMethod.GET)
	public ResponseEntity<List<Associado>> listar() throws SQLException {
		int index=0;

		List<Associado> associadosGetted = new ArrayList<Associado>();
		associados = new HashMap<Integer, Associado>();

		associadosGetted = associadoDao.getLista();

		for (Associado asso : associadosGetted) { //Coloca todos alunos vindos do SELECT da DAO em um hashmap
			associados.put(index, asso);
			index++;
		}

		return new ResponseEntity<List<Associado>>(new ArrayList<Associado>(associados.values()), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/api/associado/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Associado> buscar(@PathVariable("cpf") String cpf) throws SQLException {

	  Associado associado = associadoDao.getAssociado(cpf);
	  if (associado == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Associado>(associado, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/api/associado/{cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("cpf") String cpf) {
		//Aluno aluno = alunos.remove(id);

		/*Configurar caso não ache o objeto a ser excluido*/
		//if (aluno == null) {
	    //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		associadoDao.excluir(cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@CrossOrigin
	@RequestMapping(value = "/api/associado", method = RequestMethod.POST) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Associado> addAssociado(@RequestBody Associado associado) throws JsonParseException, JsonMappingException, IOException, SQLException {

		//Associado associado = new ObjectMapper().readValue(associadoJSON, Associado.class); //Aqui o json é convertido em objeto Java Aluno
		System.out.println("Associado que chegou no backend: " + associado.getCpf());
		associadoDao.adiciona(associado);
		
		FormaPagamento formaPag = new FormaPagamento();
		formaPag.setAssociado(associado);
		formPagDao.adiciona(formaPag);
		
		return new ResponseEntity<Associado>(associado, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}

	/**
	 * Este método faz alguma coisa
	 * @param associado
	 * @param cpf
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @since 15/05/2017
	 */
	@CrossOrigin
	@RequestMapping(value = "/api/associado/{cpf}", method = RequestMethod.PUT) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Associado> updateUsuario(@RequestBody Associado associado, @PathVariable("cpf") String cpf) throws JsonParseException, JsonMappingException, IOException, SQLException {

		//TODO procurar outra forma de log
		
		//Usuario usuario = new ObjectMapper().readValue(usuarioJSON, Usuario.class); //Aqui o json é convertido em objeto Java Aluno
		System.out.println("Alterar associado de CPF: " + cpf);
		System.out.println("Associado que chegou no backend para alteração: ");
		System.out.println("CPF: " + associado.getCpf());
		System.out.println("Nome: " + associado.getNome());
		
		
		
		associadoDao.altera(associado, cpf);
		return new ResponseEntity<Associado>(associado, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}
}
