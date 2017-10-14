package controller.historicoOcorrencia;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jdbc.dao.historicoOcorrencia.HistoricoOcorrenciaDAO;
import model.historicoOcorrencia.HistoricoOcorrencia;

@RestController
public class HistoricoOcorrenciaController {
	private HistoricoOcorrenciaDAO historicoOcorrenciaDAO = new HistoricoOcorrenciaDAO();
	
	public HistoricoOcorrenciaController() throws SQLException {}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoOcorrencia>> listar() throws SQLException {
		List<HistoricoOcorrencia> ocorrencias = historicoOcorrenciaDAO.getLista();
		
		return new ResponseEntity<List<HistoricoOcorrencia>>(ocorrencias, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/aluno/{ra}", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoOcorrencia>> listarByAluno(@PathVariable("ra") int ra) throws SQLException {
		List<HistoricoOcorrencia> ocorrenciasByAluno = historicoOcorrenciaDAO.getListaByAluno(ra);
		
		return new ResponseEntity<List<HistoricoOcorrencia>>(ocorrenciasByAluno, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/{data}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("data") Date data, @RequestParam("hora") Time hora, @RequestParam("ra") int ra) throws SQLException {
		historicoOcorrenciaDAO.excluir(data, hora, ra);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/aluno/{ra}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("ra") int ra) throws SQLException {
		historicoOcorrenciaDAO.excluirByAluno(ra);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia", method = RequestMethod.POST)
	public ResponseEntity<HistoricoOcorrencia> addHistoricoOcorrencia(@RequestBody HistoricoOcorrencia ho) throws SQLException {
		historicoOcorrenciaDAO.adiciona(ho);
		return new ResponseEntity<HistoricoOcorrencia>(ho, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/{ra}", method = RequestMethod.PUT)
	public ResponseEntity<HistoricoOcorrencia> updateOcorrencia(@PathVariable("ra") int ra, @RequestBody HistoricoOcorrencia ho) throws SQLException {
		historicoOcorrenciaDAO.altera(ra, ho);
		return new ResponseEntity<HistoricoOcorrencia>(ho, HttpStatus.CREATED);
	}
}
