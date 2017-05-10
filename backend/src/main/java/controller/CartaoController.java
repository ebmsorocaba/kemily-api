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

import jdbc.dao.CartaoDAO;
import model.Cartao;

import java.sql.SQLException;


@RestController
 //E isso
public class CartaoController {

	private Map<Integer, Cartao> cartoes;
	private CartaoDAO cartaoDao = new CartaoDAO();

	public CartaoController() throws SQLException {
		cartoes = new HashMap<Integer, Cartao>();
	}

	@CrossOrigin
	@RequestMapping(value = "/cartao", method = RequestMethod.GET)
	public ResponseEntity<List<Cartao>> listar() throws SQLException {
		int index=0;

		List<Cartao> cartoesGetted = new ArrayList<Cartao>();
		cartoes = new HashMap<Integer, Cartao>();
		
		cartoesGetted = cartaoDao.getLista();
		for (Cartao card : cartoesGetted) { //Coloca todos alunos vindos do SELECT da DAO em um hashmap
			cartoes.put(index, card);
			index++;
		}

		return new ResponseEntity<List<Cartao>>(new ArrayList<Cartao>(cartoes.values()), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/cartao/{numero}", method = RequestMethod.GET)
	public ResponseEntity<Cartao> buscar(@PathVariable("numero") Long numero) throws SQLException {

	  Cartao cartao = cartaoDao.getCartao(numero);
	  if (cartao == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Cartao>(cartao, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/cartao/{numero}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("numero") Long numero) {
		//Aluno aluno = alunos.remove(id);

		/*Configurar caso não ache o objeto a ser excluido*/
		//if (aluno == null) {
	    //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		cartaoDao.excluir(numero);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@CrossOrigin
	@RequestMapping(value = "/cartao", method = RequestMethod.POST) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Cartao> addCartao(@RequestBody Cartao cartao) throws JsonParseException, JsonMappingException, IOException, SQLException {

		//Cartao cartao = new ObjectMapper().readValue(cartaoJSON, Cartao.class); //Aqui o json é convertido em objeto Java Aluno
		System.out.println("Cartao que chegou no backend: " + cartao.getNumero());
		cartaoDao.adiciona(cartao);
		return new ResponseEntity<Cartao>(cartao, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}

}
