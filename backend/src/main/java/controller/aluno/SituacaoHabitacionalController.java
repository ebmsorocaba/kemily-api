package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.SituacaoHabitacionalDAO;
import model.aluno.SituacaoHabitacional;
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
public class SituacaoHabitacionalController {
    private Map<Integer, SituacaoHabitacional> situacaoHabitacionais;
    private SituacaoHabitacionalDAO situacaoHabitacionalDao = new SituacaoHabitacionalDAO();

    public SituacaoHabitacionalController() throws SQLException {
        situacaoHabitacionais = new HashMap<Integer, SituacaoHabitacional>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacaoHabitacional", method = RequestMethod.GET)
    public ResponseEntity<List<SituacaoHabitacional>> listar() throws SQLException {
        int index=0;

        List<SituacaoHabitacional> situacaoHabitacionaisGetted = new ArrayList<SituacaoHabitacional>();
        situacaoHabitacionais = new HashMap<Integer, SituacaoHabitacional>();

        situacaoHabitacionaisGetted = situacaoHabitacionalDao.getLista();

        for (SituacaoHabitacional usu : situacaoHabitacionaisGetted) {
            situacaoHabitacionais.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<SituacaoHabitacional>>(new ArrayList<SituacaoHabitacional>(situacaoHabitacionais.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacaoHabitacional/{ra}", method = RequestMethod.GET)
    public ResponseEntity<SituacaoHabitacional> buscar(@PathVariable("ra") int ra) throws SQLException {

        SituacaoHabitacional situacaoHabitacional = situacaoHabitacionalDao.getSituacaoHabitacional(ra);
        if (situacaoHabitacional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SituacaoHabitacional>(situacaoHabitacional, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacaoHabitacional/{ra}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("ra") int ra) {
        situacaoHabitacionalDao.excluir(ra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/situacaoHabitacional", method = RequestMethod.POST)
    public ResponseEntity<SituacaoHabitacional> addSituacaoHabitacional(@RequestBody SituacaoHabitacional situacaoHabitacional) throws JsonParseException, JsonMappingException, IOException, SQLException {

        situacaoHabitacionalDao.adicionar(situacaoHabitacional);
        return new ResponseEntity<SituacaoHabitacional>(situacaoHabitacional, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacaoHabitacional/{ra}", method = RequestMethod.PUT)
    public ResponseEntity<SituacaoHabitacional> updateSituacaoHabitacional(@RequestBody SituacaoHabitacional situacaoHabitacional, @PathVariable("ra") int ra) throws JsonParseException, JsonMappingException, IOException, SQLException {

        situacaoHabitacionalDao.altera(situacaoHabitacional, ra);
        return new ResponseEntity<SituacaoHabitacional>(situacaoHabitacional, HttpStatus.CREATED);
    }
}
