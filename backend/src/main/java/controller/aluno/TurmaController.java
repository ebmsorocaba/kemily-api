package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.AlunoDAO;
import jdbc.dao.aluno.TurmaDAO;
import model.aluno.Aluno;
import model.aluno.Turma;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TurmaController {
    private Map<Integer, Turma> turmas;
    private TurmaDAO turmaDao = new TurmaDAO();

    public TurmaController() throws SQLException {
        turmas = new HashMap<Integer, Turma>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/turma", method = RequestMethod.GET)
    public ResponseEntity<List<Turma>> listar() throws SQLException {
        int index=0;

        List<Turma> turmasGetted = new ArrayList<Turma>();
        turmas = new HashMap<Integer, Turma>();

        turmasGetted = turmaDao.getLista();

        for (Turma usu : turmasGetted) {
            turmas.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Turma>>(new ArrayList<Turma>(turmas.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/turma/{educador}", method = RequestMethod.GET)
    public ResponseEntity<Turma> buscar(@PathVariable("educador") String educador) throws SQLException {

        Turma turma = turmaDao.getTurma(educador);
        if (turma == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Turma>(turma, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/turma/{educador}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("educador") String educador) {
        turmaDao.excluir(educador);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/turma", method = RequestMethod.POST)
    public ResponseEntity<Turma> addTurma(@RequestBody Turma turma) throws JsonParseException, JsonMappingException, IOException, SQLException {

        turmaDao.adiciona(turma);
        return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
    }

//    @CrossOrigin
//    @RequestMapping(value = "/api/turma/{educadora}", method = RequestMethod.PUT)
//    public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma, @PathVariable("educadora") String educadora) throws JsonParseException, JsonMappingException, IOException, SQLException {
//
//        turmaDao.altera(turma, educadora);
//        return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
//    }
}
