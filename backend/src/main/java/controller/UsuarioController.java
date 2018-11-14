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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	public UsuarioController() throws SQLException {
	  usuarios = new HashMap<Integer, Usuario>();
	}
	//lista de usuarios (somente Admin)
	//@PreAuthorize("hasAnyRole('ADMIN')")
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
	
	
	//busca de usuario espeficico
	@RequestMapping(value = "/api/usuario/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscar(@PathVariable("codigo") Integer codigo) throws SQLException {

		/* só retorna o usuario logado, a menos que seja admin
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !codigo.equals(user.getId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}*/
		
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

	// reseta senha para senha padrão quando enviar a resposta secreta como padrão // senha123
	@RequestMapping(value = "/Login/recuperarSenha/{email}", method = RequestMethod.GET, params= {"respostasecreta"})
	public ResponseEntity<String> recuperarSenha(@PathVariable("email") String email, @RequestParam("respostasecreta") String respostasecreta) throws SQLException {
		
		Usuario usuario = new Usuario();
		usuario = usuarioDao.getUsuarioByEmail(email);

		// se as respostas secretas baterem:
		if(passwordEncoder.matches(respostasecreta, usuario.getRespostasecreta())){
			// reseta a senha
			usuario.setSenha("SENHA123");
			// grava no banco
			usuarioDao.altera(usuario, usuario.getCodigo());
			// e devolve a senha nova
			return new ResponseEntity<String>(usuario.getSenha(), HttpStatus.OK);
		}
		else{
			usuario = null;
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
}
