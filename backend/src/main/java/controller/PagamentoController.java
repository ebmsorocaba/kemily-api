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

import jdbc.dao.PagamentoDAO;
import model.Pagamento;

import java.sql.SQLException;


@RestController
 //E isso
public class PagamentoController {

	private Map<Integer, Pagamento> pagamentos;
	private PagamentoDAO pagamentoDao = new PagamentoDAO();

	public PagamentoController() throws SQLException {
		pagamentos = new HashMap<Integer, Pagamento>();
	}

	@CrossOrigin
	@RequestMapping(value = "/pagamento", method = RequestMethod.GET)
	public ResponseEntity<List<Pagamento>> listar() throws SQLException {
		int index=0;

		List<Pagamento> pagamentosGetted = new ArrayList<Pagamento>();
		pagamentos = new HashMap<Integer, Pagamento>();

		pagamentosGetted = pagamentoDao.getLista();

		for (Pagamento pag : pagamentosGetted) { //Coloca todos alunos vindos do SELECT da DAO em um hashmap
			pagamentos.put(index, pag);
			index++;
		}

		return new ResponseEntity<List<Pagamento>>(new ArrayList<Pagamento>(pagamentos.values()), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/pagamento/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pagamento> buscar(@PathVariable("id") Long id) throws SQLException {

	  Pagamento pagamento = pagamentoDao.getPagamento(id);
	  if (pagamento == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Pagamento>(pagamento, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/pagamento/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		//Aluno aluno = alunos.remove(id);

		/*Configurar caso não ache o objeto a ser excluido*/
		//if (aluno == null) {
	    //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		pagamentoDao.excluir(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@CrossOrigin
	@RequestMapping(value = "/pagamento", method = RequestMethod.POST) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Pagamento> addPagamento(@RequestBody Pagamento pagamento) throws JsonParseException, JsonMappingException, IOException, SQLException {

		//Pagamento pagamento = new ObjectMapper().readValue(pagamentoJSON, Pagamento.class); //Aqui o json é convertido em objeto Java Aluno
		System.out.println("Pagamento que chegou no backend: " + pagamento.getId());
		pagamentoDao.adiciona(pagamento);
		return new ResponseEntity<Pagamento>(pagamento, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}

}
