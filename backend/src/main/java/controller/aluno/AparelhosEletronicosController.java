package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.AparelhosEletronicosDAO;
import model.aluno.AparelhosEletronicos;
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
public class AparelhosEletronicosController {
    private Map<Integer, AparelhosEletronicos> aparelhosEletronicos;
    private AparelhosEletronicosDAO aparelhosEletronicosDao = new AparelhosEletronicosDAO();

    public AparelhosEletronicosController() throws SQLException {
        aparelhosEletronicos = new HashMap<Integer, AparelhosEletronicos>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos", method = RequestMethod.GET)
    public ResponseEntity<List<AparelhosEletronicos>> listar() throws SQLException {
        int index=0;

        List<AparelhosEletronicos> aparelhosGetted = new ArrayList<AparelhosEletronicos>();
        aparelhosEletronicos = new HashMap<Integer, AparelhosEletronicos>();

        aparelhosGetted = aparelhosEletronicosDao.getLista();

        for (AparelhosEletronicos apa : aparelhosGetted) {
            aparelhosEletronicos.put(index, apa);
            index++;
        }

        return new ResponseEntity<List<AparelhosEletronicos>>(new ArrayList<AparelhosEletronicos>(aparelhosEletronicos.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos/{id}", method = RequestMethod.GET)
    public ResponseEntity<AparelhosEletronicos> buscar(@PathVariable("id") int id) throws SQLException {

        AparelhosEletronicos aparelhosEletronicos = aparelhosEletronicosDao.getAparelhosEletronicos(id);
        if (aparelhosEletronicos == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AparelhosEletronicos>(aparelhosEletronicos, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        aparelhosEletronicosDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos", method = RequestMethod.POST)
    public ResponseEntity<AparelhosEletronicos> addAparelhosEletronicos(@RequestBody AparelhosEletronicos aparelhosEletronicos) throws JsonParseException, JsonMappingException, IOException, SQLException {

        aparelhosEletronicosDao.adiciona(aparelhosEletronicos);
        return new ResponseEntity<AparelhosEletronicos>(aparelhosEletronicos, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AparelhosEletronicos> updateAparelhosEletronicos(@RequestBody AparelhosEletronicos aparelhosEletronicos, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        aparelhosEletronicosDao.altera(aparelhosEletronicos, id);
        return new ResponseEntity<AparelhosEletronicos>(aparelhosEletronicos, HttpStatus.CREATED);
    }
}
