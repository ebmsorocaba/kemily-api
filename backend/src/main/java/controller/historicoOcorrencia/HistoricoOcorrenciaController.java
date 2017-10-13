package controller.historicoOcorrencia;

import java.sql.SQLException;
import java.sql.Timestamp;
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
	@RequestMapping(value="/api/historicoOcorrencia/data/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoOcorrencia>> listarByData(@PathVariable("data") Timestamp data) throws SQLException {
		List<HistoricoOcorrencia> ocorrenciasByData = historicoOcorrenciaDAO.getListaByData(data);
		
		return new ResponseEntity<List<HistoricoOcorrencia>>(ocorrenciasByData, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/aluno/{ra}", method = RequestMethod.GET)
	public ResponseEntity<List<HistoricoOcorrencia>> listarByAluno(@PathVariable("ra") int ra) throws SQLException {
		List<HistoricoOcorrencia> ocorrenciasByAluno = historicoOcorrenciaDAO.getListaByAluno(ra);
		
		return new ResponseEntity<List<HistoricoOcorrencia>>(ocorrenciasByAluno, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/{data}", method = RequestMethod.GET, params={"ra"})
	public ResponseEntity<HistoricoOcorrencia> buscar(@PathVariable("data") Timestamp data, @RequestParam("ra") int ra) throws SQLException {
		HistoricoOcorrencia ho = historicoOcorrenciaDAO.getHistoricoOcorrencia(data, ra);
		
		if(ho == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HistoricoOcorrencia>(ho, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/{data}", method = RequestMethod.DELETE, params={"ra"})
	public ResponseEntity<?> deletar(@PathVariable("data") Timestamp data, @RequestParam("ra") int ra) throws SQLException {
		historicoOcorrenciaDAO.excluir(data, ra);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/data/{data}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("data") Timestamp data) throws SQLException {
		historicoOcorrenciaDAO.excluirByData(data);
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
	public ResponseEntity<?> addHistoricoOcorrencia(@RequestBody HistoricoOcorrencia ho) throws SQLException {
		ho.getData().setNanos(0);
		historicoOcorrenciaDAO.adiciona(ho);
		return new ResponseEntity<HistoricoOcorrencia>(ho, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value="/api/historicoOcorrencia/{data}", method = RequestMethod.PUT, params={"ra"})
	public ResponseEntity<?> deletar(@PathVariable("data") Timestamp data, @RequestParam("ra") int ra, @RequestBody HistoricoOcorrencia ho) throws SQLException {
		data.setNanos(0);
		ho.getData().setNanos(0);
		historicoOcorrenciaDAO.altera(data, ra, ho);
		return new ResponseEntity<HistoricoOcorrencia>(ho, HttpStatus.CREATED);
	}
}