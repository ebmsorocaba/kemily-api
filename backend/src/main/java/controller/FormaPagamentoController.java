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

import jdbc.dao.FormaPagamentoDAO;
import model.FormaPagamento;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
 //E isso
public class FormaPagamentoController {

	private Map<Integer, FormaPagamento> formasPgto;
	private FormaPagamentoDAO formaPgtoDAO = new FormaPagamentoDAO();

	public FormaPagamentoController() throws SQLException {
		formasPgto = new HashMap<Integer, FormaPagamento>();
	}

	@RequestMapping(value = "/api/formaPgto", method = RequestMethod.GET)
	public ResponseEntity<List<FormaPagamento>> listar() throws SQLException {
		int index=0;

		List<FormaPagamento> formasPgtoGetted = new ArrayList<FormaPagamento>();
		formasPgto = new HashMap<Integer, FormaPagamento>();

		formasPgtoGetted = formaPgtoDAO.getLista();

		for (FormaPagamento formas : formasPgtoGetted) { //Coloca todos alunos vindos do SELECT da DAO em um hashmap
			formasPgto.put(index, formas);
			index++;
		}

		return new ResponseEntity<List<FormaPagamento>>(new ArrayList<FormaPagamento>(formasPgto.values()), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/formaPgto/{cpf}/{formaPgto}", method = RequestMethod.GET)
	public ResponseEntity<FormaPagamento> buscar(@PathVariable("cpf") String cpf, @PathVariable("formaPgto") String formaPgto) throws SQLException {

	  FormaPagamento formaPgtoObj = formaPgtoDAO.getFormaPgto(cpf, formaPgto);
	  if (formaPgtoObj == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<FormaPagamento>(formaPgtoObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/formaPgto/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<FormaPagamento> buscar(@PathVariable("cpf") String cpf) throws SQLException {

	  FormaPagamento formaPgtoObj = formaPgtoDAO.getFormaPgtoAtual(cpf);
	  if (formaPgtoObj == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<FormaPagamento>(formaPgtoObj, HttpStatus.OK);
	}

/*
	@RequestMapping(value = "/formasPgto/{cpf}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("cpf") String cpf) {
		//Aluno aluno = alunos.remove(id);

		/*Configurar caso não ache o objeto a ser excluido*/
		//if (aluno == null) {
	    //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
	/*
		associadoDao.excluir(cpf);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
*/

//TODO Verificar os deletes e melhorar o controller do pagamento
	
	@RequestMapping(value = "/api/formaPgto", method = RequestMethod.POST) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<FormaPagamento> addFormaPagamento(@RequestBody FormaPagamento formaPgto) throws JsonParseException, JsonMappingException, IOException, SQLException {

		//Associado associado = new ObjectMapper().readValue(associadoJSON, Associado.class); //Aqui o json é convertido em objeto Java Aluno
		System.out.println("Associado que chegou no backend: " + formaPgto.getAssociado().getCpf());
		formaPgtoDAO.adiciona(formaPgto);
		return new ResponseEntity<FormaPagamento>(formaPgto, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}

}
