package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.AlunoDAO;
import jdbc.dao.aluno.Estrutura_FamiliarDAO;
import model.Aluno;
import model.aluno.Estrutura_Familiar;
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
public class Estrutura_FamiliarController {
    private Map<Integer, Estrutura_Familiar> estrutura_Familiars;
    private Estrutura_FamiliarDAO estrutura_FamiliarDao = new Estrutura_FamiliarDAO();

    public Estrutura_FamiliarController() throws SQLException {
        estrutura_Familiars = new HashMap<Integer, Estrutura_Familiar>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estrutura_familiar", method = RequestMethod.GET)
    public ResponseEntity<List<Estrutura_Familiar>> listar() throws SQLException {
        int index=0;

        List<Estrutura_Familiar> estrutura_FamiliarsGetted = new ArrayList<Estrutura_Familiar>();
        estrutura_Familiars = new HashMap<Integer, Estrutura_Familiar>();

        estrutura_FamiliarsGetted = estrutura_FamiliarDao.getLista();

        for (Estrutura_Familiar usu : estrutura_FamiliarsGetted) {
            estrutura_Familiars.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Estrutura_Familiar>>(new ArrayList<Estrutura_Familiar>(estrutura_Familiars.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estrutura_familiar/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estrutura_Familiar> buscar(@PathVariable("id") int id) throws SQLException {

        Estrutura_Familiar estrutura_Familiar = estrutura_FamiliarDao.getEstrutura_Familiar(id);
        if (estrutura_Familiar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Estrutura_Familiar>(estrutura_Familiar, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estrutura_familiar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        estrutura_FamiliarDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/estrutura_familiar", method = RequestMethod.POST)
    public ResponseEntity<Estrutura_Familiar> addEstrutura_Familiar(@RequestBody Estrutura_Familiar estrutura_Familiar) throws JsonParseException, JsonMappingException, IOException, SQLException {

        estrutura_FamiliarDao.adiciona(estrutura_Familiar);
        return new ResponseEntity<Estrutura_Familiar>(estrutura_Familiar, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estrutura_familiar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Estrutura_Familiar> updateEstrutura_Familiar(@RequestBody Estrutura_Familiar estrutura_Familiar, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        estrutura_FamiliarDao.altera(estrutura_Familiar, id);
        return new ResponseEntity<Estrutura_Familiar>(estrutura_Familiar, HttpStatus.CREATED);
    }
}
