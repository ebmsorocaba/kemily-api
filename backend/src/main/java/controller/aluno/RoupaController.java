package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.RoupaDAO;
import model.aluno.Roupa;
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
public class RoupaController {
    private Map<Integer, Roupa> roupas;
    private RoupaDAO roupaDao = new RoupaDAO();

    public RoupaController() throws SQLException {
        roupas = new HashMap<Integer, Roupa>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/roupa", method = RequestMethod.GET)
    public ResponseEntity<List<Roupa>> listar() throws SQLException {
        int index=0;

        List<Roupa> roupasGetted = new ArrayList<Roupa>();
        roupas = new HashMap<Integer, Roupa>();

        roupasGetted = roupaDao.getLista();

        for (Roupa usu : roupasGetted) {
            roupas.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Roupa>>(new ArrayList<Roupa>(roupas.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/roupa/{ra_aluno}", method = RequestMethod.GET)
    public ResponseEntity<Roupa> buscar(@PathVariable("ra_aluno") int ra_aluno) throws SQLException {

        Roupa roupa = roupaDao.getRoupa(ra_aluno);
        if (roupa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Roupa>(roupa, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/roupa/{ra_aluno}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("ra_aluno") int ra_aluno) {
        roupaDao.excluir(ra_aluno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/roupa", method = RequestMethod.POST)
    public ResponseEntity<Roupa> addRoupa(@RequestBody Roupa roupa) throws JsonParseException, JsonMappingException, IOException, SQLException {

        roupaDao.adiciona(roupa);
        return new ResponseEntity<Roupa>(roupa, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/roupa/{ra_aluno}", method = RequestMethod.PUT)
    public ResponseEntity<Roupa> updateRoupa(@RequestBody Roupa roupa, @PathVariable("ra_aluno") int ra_aluno) throws JsonParseException, JsonMappingException, IOException, SQLException {

        roupaDao.altera(roupa, ra_aluno);
        return new ResponseEntity<Roupa>(roupa, HttpStatus.CREATED);
    }
}
