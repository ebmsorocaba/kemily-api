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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.CrossOrigin;

import jdbc.dao.UsuarioDAO;
import model.RelatPagAssociado;
import model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;


@RestController
 //E isso
public class EmailController {

	private Map<Integer, Usuario> usuarios;
	private UsuarioDAO usuarioDao = new UsuarioDAO();


	private JavaMailSender javaMailSender;

    @Autowired
    void MailSubmissionController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

	public EmailController() throws SQLException {


	}

	@CrossOrigin
	@RequestMapping(value = "/api/recuperarSenha/{nome}", method = RequestMethod.GET)
	public ResponseEntity<SimpleMailMessage> recuperarSenha(@PathVariable("nome") String nome) throws SQLException, ParseException {

		Usuario usuario = new Usuario();
		usuario = usuarioDao.getUsuario(nome);
		usuario.setSenha("SENHA@123");
		usuarioDao.altera(usuario, nome);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usuario.getEmail());
        //mailMessage.setReplyTo("diegoluizbaptista@gmail.com");
        mailMessage.setFrom("testingx99999@gmail.com");
        mailMessage.setSubject("Mudança de senha");
        mailMessage.setText("Saudações, senhor(a) " + usuario.getNome() + " sua senha foi resetada para a senha padrão SENHA@123, pedimos para que assim que acessar sua conta já a altere.");
        javaMailSender.send(mailMessage);
        //return mailMessage;

		return new ResponseEntity<SimpleMailMessage>(mailMessage, HttpStatus.CREATED);
	}

}
