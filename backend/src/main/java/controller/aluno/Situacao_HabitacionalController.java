package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.Situacao_HabitacionalDAO;
import model.aluno.Situacao_Habitacional;
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
public class Situacao_HabitacionalController {
    private Map<Integer, Situacao_Habitacional> situacao_Habitacionals;
    private Situacao_HabitacionalDAO situacao_HabitacionalDao = new Situacao_HabitacionalDAO();

    public Situacao_HabitacionalController() throws SQLException {
        situacao_Habitacionals = new HashMap<Integer, Situacao_Habitacional>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacao_habitacional", method = RequestMethod.GET)
    public ResponseEntity<List<Situacao_Habitacional>> listar() throws SQLException {
        int index=0;

        List<Situacao_Habitacional> situacao_HabitacionalsGetted = new ArrayList<Situacao_Habitacional>();
        situacao_Habitacionals = new HashMap<Integer, Situacao_Habitacional>();

        situacao_HabitacionalsGetted = situacao_HabitacionalDao.getLista();

        for (Situacao_Habitacional usu : situacao_HabitacionalsGetted) {
            situacao_Habitacionals.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Situacao_Habitacional>>(new ArrayList<Situacao_Habitacional>(situacao_Habitacionals.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacao_habitacional/{ra}", method = RequestMethod.GET)
    public ResponseEntity<Situacao_Habitacional> buscar(@PathVariable("ra") int ra) throws SQLException {

        Situacao_Habitacional situacao_Habitacional = situacao_HabitacionalDao.getSituacao_Habitacional(ra);
        if (situacao_Habitacional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Situacao_Habitacional>(situacao_Habitacional, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacao_habitacional/{ra}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("ra") int ra) {
        situacao_HabitacionalDao.excluir(ra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/situacao_habitacional", method = RequestMethod.POST)
    public ResponseEntity<Situacao_Habitacional> addSituacao_Habitacional(@RequestBody Situacao_Habitacional situacao_Habitacional) throws JsonParseException, JsonMappingException, IOException, SQLException {

        situacao_HabitacionalDao.adicionar(situacao_Habitacional);
        return new ResponseEntity<Situacao_Habitacional>(situacao_Habitacional, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/situacao_habitacional/{ra}", method = RequestMethod.PUT)
    public ResponseEntity<Situacao_Habitacional> updateSituacao_Habitacional(@RequestBody Situacao_Habitacional situacao_Habitacional, @PathVariable("ra") int ra) throws JsonParseException, JsonMappingException, IOException, SQLException {

        situacao_HabitacionalDao.altera(situacao_Habitacional, ra);
        return new ResponseEntity<Situacao_Habitacional>(situacao_Habitacional, HttpStatus.CREATED);
    }
}
