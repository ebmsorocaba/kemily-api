package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.AutomovelDAO;
import model.aluno.Automovel;
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
public class AutomovelController {
    private Map<Integer, Automovel> automovels;
    private AutomovelDAO automovelDao = new AutomovelDAO();

    public AutomovelController() throws SQLException {
        automovels = new HashMap<Integer, Automovel>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/automovel", method = RequestMethod.GET)
    public ResponseEntity<List<Automovel>> listar() throws SQLException {
        int index=0;

        List<Automovel> automovelsGetted = new ArrayList<Automovel>();
        automovels = new HashMap<Integer, Automovel>();

        automovelsGetted = automovelDao.getLista();

        for (Automovel usu : automovelsGetted) {
            automovels.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Automovel>>(new ArrayList<Automovel>(automovels.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/automovel/{id}", method = RequestMethod.GET)
    public ResponseEntity<Automovel> buscar(@PathVariable("id") int id) throws SQLException {

        Automovel automovel = automovelDao.getAutomovel(id);
        if (automovel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Automovel>(automovel, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/automovel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        automovelDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/automovel", method = RequestMethod.POST)
    public ResponseEntity<Automovel> addAutomovel(@RequestBody Automovel automovel) throws JsonParseException, JsonMappingException, IOException, SQLException {

        automovelDao.adiciona(automovel);
        return new ResponseEntity<Automovel>(automovel, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/automovel/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Automovel> updateAutomovel(@RequestBody Automovel automovel, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        automovelDao.altera(automovel, id);
        return new ResponseEntity<Automovel>(automovel, HttpStatus.CREATED);
    }
}
