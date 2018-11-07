package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jdbc.dao.UsuarioDAO;
import model.Usuario;


//@CrossOrigin(origins = "http://localhost:8081")
@RestController
 //E isso
public class UsuarioController {

	private Map<Integer, Usuario> usuarios;
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	

	public UsuarioController() throws SQLException {
	  usuarios = new HashMap<Integer, Usuario>();
	}
	//lista de usuarios (somente Admin)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/api/usuarios", method = RequestMethod.GET)
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
	
	// busca de perfis especifico (mal implementada / retorna um usuario inteiro não somente os perfis)
	// Problemas na tabela associativa Usarios/Perfis
	@RequestMapping(value = "/api/perfis/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarperfis(@PathVariable("codigo") Integer codigo) throws SQLException {


	  Usuario usuario = usuarioDao.getPerfis(codigo);
	  if (usuario == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	//busca de usuario espeficico
	@RequestMapping(value = "/api/usuario/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscar(@PathVariable("codigo") Integer codigo) throws SQLException {


	  Usuario usuario = usuarioDao.getUsuario(codigo);
	  if (usuario.getCodigo() == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }

	  return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	//deleta um usuario especifico (somente ADMIN)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/api/usuario/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("codigo") Integer codigo) {
		
		// tem que dar uma arrumada aqui	
		
		usuarioDao.excluir(codigo);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	// adiciona um usuario novo
	@RequestMapping(value = "/api/usuario", method = RequestMethod.POST) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) throws JsonParseException, JsonMappingException, IOException, SQLException {

		usuarioDao.adiciona(usuario);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED); //Aqui ele retorna o objecto aluno como confirmação que deu tudo certo, lá no t ele vai tranformar em JSON novamente
	}

	// altera um usuario especifico
	@RequestMapping(value = "/api/usuario/{codigo}", method = RequestMethod.PUT) //Esse metodo recebe uma String em formato de JSON
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable("codigo") Integer codigo) throws JsonParseException, JsonMappingException, IOException, SQLException {
		
			usuarioDao.altera(usuario, codigo);
			return new ResponseEntity<Usuario>(HttpStatus.CREATED);
		}

	/*

	@RequestMapping(value = "/api/usuario/{nome}", method = RequestMethod.GET, params={"senha"})
	public ResponseEntity<Usuario> login(@PathVariable("nome") String nome, @RequestParam("senha") String senha) throws SQLException, ParseException {
	
		Usuario usuario = new Usuario();
		
		try{
			usuario = usuarioDao.getUsuario(nome);
		} catch (SQLException ex){
			System.out.println(ex.toString());
			usuario = null;
			return new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
		}
		if(passwordEncoder.matches(senha, usuario.getSenha())){
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		else{
			usuario = null;
			return new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_FOUND);
		}
		
	}
<<<<<<< Updated upstream
=======

	@RequestMapping(value = "/api/recuperarSenha/{nome}", method = RequestMethod.GET)
	public ResponseEntity<SimpleMailMessage> recuperarSenha(@PathVariable("nome") String nome) throws SQLException, ParseException {

		Usuario usuario = new Usuario();
		usuario = usuarioDao.getUsuario(nome);
		usuario.setSenha("SENHA@123");
		usuarioDao.altera(usuario, nome);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(usuario.getEmail());
		mailMessage.setFrom("testingx99999@gmail.com");
		mailMessage.setSubject("Mudança de senha");
		mailMessage.setText("Saudações, senhor(a) " + usuario.getNome() + " sua senha foi resetada para a senha padrão SENHA@123, pedimos para que assim que acessar sua conta já a altere.");
		javaMailSender.send(mailMessage);
		//return mailMessage;

		return new ResponseEntity<SimpleMailMessage>(mailMessage, HttpStatus.CREATED);
	}
	*/
	
}
