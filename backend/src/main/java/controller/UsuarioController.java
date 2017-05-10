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

import jdbc.dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;


@RestController
 //E isso
public class UsuarioController {

	private Map<Integer, Usuario> usuarios;
	private UsuarioDAO usuarioDao = new UsuarioDAO();

	public UsuarioController() throws SQLException {
	  usuarios = new HashMap<Integer, Usuario>();
	}

	@CrossOrigin
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() throws SQLException {
		int index=0;

		List<Usuario> usuariosGetted = new ArrayList<Usuario>();
		usuarios = new HashMap<Integer, Usuario>();

		usuariosGetted = usuarioDao.getLista();

		for (Usuario usu : usuariosGetted) { //Coloca todos alunos vindos do SELECT da DAO em um hashmap
			usuarios.put(index, usu);
			index++;
		}

		return new ResponseEntity<List<Usuario>>(new ArrayList<Usuario>(usuarios.values()), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/usuario/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscar(@PathVariable("nome") String nome) throws SQLException {

	  Usuario usuario = usuarioDao.getUsuario(nome);
	  if (usuario == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/usuario/{nome}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("nome") String nome) {
		//Aluno aluno = alunos.remove(id);

		/*Configurar caso não ache o objeto a ser excluido*/
		//if (aluno == null) {
	    //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		usuarioDao.excluir(nome);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	//@CrossOrigin
	@RequestMapping(value = "/usuario", method = RequestMethod.POST) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) throws JsonParseException, JsonMappingException, IOException, SQLException {

		//Usuario usuario = new ObjectMapper().readValue(usuarioJSON, Usuario.class); //Aqui o json é convertido em objeto Java Aluno
		System.out.println("Usuario que chegou no backend: " + usuario.getNome());
		usuarioDao.adiciona(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}

}
