package controller.aluno;

import DTO.AlunoDTO;
import business.AlunoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class AlunoController {
    private AlunoDAO alunoDao = new AlunoDAO();
    private AlunoService alunoService = new AlunoService();

    public AlunoController() throws SQLException { }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno", method = RequestMethod.GET)
    public ResponseEntity<List<AlunoDTO>> listar() throws SQLException {
        List<AlunoDTO> alunos = alunoService.GetAll();

        return new ResponseEntity<List<AlunoDTO>>(alunos, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno/{ra}", method = RequestMethod.GET)
    public ResponseEntity<AlunoDTO> buscar(@PathVariable("ra") int ra) throws SQLException {

        AlunoDTO alunoDTO = alunoService.GetSingle(ra);

        if (alunoDTO.getAluno() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AlunoDTO>(alunoDTO, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno/{ra}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("ra") int ra) throws SQLException {
        alunoDao.excluir(ra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/aluno", method = RequestMethod.POST)
    public ResponseEntity<AlunoDTO> addAluno(@RequestBody AlunoDTO aluno) throws JsonParseException, JsonMappingException, IOException, SQLException {

        alunoService.Post(aluno);
        return new ResponseEntity<AlunoDTO>(aluno, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aluno/{ra}", method = RequestMethod.PUT)
    public ResponseEntity<AlunoDTO> updateAluno(@RequestBody AlunoDTO aluno, @PathVariable("ra") int ra) throws JsonParseException, JsonMappingException, IOException, SQLException {
        alunoService.Put(aluno, ra);

        return new ResponseEntity<AlunoDTO>(aluno, HttpStatus.CREATED);
    }

}
