package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.AlunoDAO;
import jdbc.dao.aluno.ContatoDAO;
import jdbc.dao.aluno.EnderecoDAO;
import jdbc.dao.aluno.EstruturaFamiliarDAO;
import jdbc.dao.aluno.ParenteDAO;
import jdbc.dao.aluno.RoupaDAO;
import jdbc.dao.aluno.SaudeDAO;
import jdbc.dao.aluno.SituacaoHabitacionalDAO;
import model.aluno.Aluno;

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
public class AlunoController {
    private Map<Integer, Aluno> alunos;
    private AlunoDAO alunoDao = new AlunoDAO();

    public AlunoController() throws SQLException {
        alunos = new HashMap<Integer, Aluno>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno", method = RequestMethod.GET)
    public ResponseEntity<List<Aluno>> listar() throws SQLException {
        int index=0;

        List<Aluno> alunosGetted = new ArrayList<Aluno>();
        alunos = new HashMap<Integer, Aluno>();

        alunosGetted = alunoDao.getLista();

        for (Aluno usu : alunosGetted) {
            alunos.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Aluno>>(new ArrayList<Aluno>(alunos.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno/{ra}", method = RequestMethod.GET)
    public ResponseEntity<Aluno> buscar(@PathVariable("ra") int ra) throws SQLException {

        Aluno aluno = alunoDao.getAluno(ra);
        if (aluno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno/{ra}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("ra") int ra) throws SQLException {
        alunoDao.excluir(ra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/aluno", method = RequestMethod.POST)
    public ResponseEntity<Aluno> addAluno(@RequestBody Aluno aluno) throws JsonParseException, JsonMappingException, IOException, SQLException {

        alunoDao.adiciona(aluno);
        return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno/{ra}", method = RequestMethod.PUT)
    public ResponseEntity<Aluno> updateAluno(@RequestBody Aluno aluno, @PathVariable("ra") int ra) throws JsonParseException, JsonMappingException, IOException, SQLException {

        alunoDao.altera(aluno, ra);
        return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
    }

}
