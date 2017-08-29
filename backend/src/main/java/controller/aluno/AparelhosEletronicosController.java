package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.Aparelhos_EletronicosDAO;
import model.aluno.Aparelhos_Eletronicos;
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
    private Map<Integer, Aparelhos_Eletronicos> aparelhosEletronicos;
    private Aparelhos_EletronicosDAO aparelhosEletronicosDao = new Aparelhos_EletronicosDAO();

    public AparelhosEletronicosController() throws SQLException {
        aparelhosEletronicos = new HashMap<Integer, Aparelhos_Eletronicos>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos", method = RequestMethod.GET)
    public ResponseEntity<List<Aparelhos_Eletronicos>> listar() throws SQLException {
        int index=0;

        List<Aparelhos_Eletronicos> aparelhosGetted = new ArrayList<Aparelhos_Eletronicos>();
        aparelhosEletronicos = new HashMap<Integer, Aparelhos_Eletronicos>();

        aparelhosGetted = aparelhosEletronicosDao.getLista();

        for (Aparelhos_Eletronicos apa : aparelhosGetted) {
            aparelhosEletronicos.put(index, apa);
            index++;
        }

        return new ResponseEntity<List<Aparelhos_Eletronicos>>(new ArrayList<Aparelhos_Eletronicos>(aparelhosEletronicos.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Aparelhos_Eletronicos> buscar(@PathVariable("id") int id) throws SQLException {

        Aparelhos_Eletronicos aparelhosEletronicos = aparelhosEletronicosDao.getAparelhos_Eletronicos(id);
        if (aparelhosEletronicos == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Aparelhos_Eletronicos>(aparelhosEletronicos, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        aparelhosEletronicosDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos", method = RequestMethod.POST)
    public ResponseEntity<Aparelhos_Eletronicos> addAparelhosEletronicos(@RequestBody Aparelhos_Eletronicos aparelhos_Eletronicos) throws JsonParseException, JsonMappingException, IOException, SQLException {

        aparelhosEletronicosDao.adiciona(aparelhos_Eletronicos);
        return new ResponseEntity<Aparelhos_Eletronicos>(aparelhos_Eletronicos, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/aparelhosEletronicos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Aparelhos_Eletronicos> updateAparelhosEletronicos(@RequestBody Aparelhos_Eletronicos aparelhosEletronicos, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        aparelhosEletronicosDao.altera(aparelhosEletronicos, id);
        return new ResponseEntity<Aparelhos_Eletronicos>(aparelhosEletronicos, HttpStatus.CREATED);
    }
}
