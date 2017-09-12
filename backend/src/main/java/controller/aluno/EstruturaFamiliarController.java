package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.EstruturaFamiliarDAO;
import model.aluno.EstruturaFamiliar;
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
public class EstruturaFamiliarController {
    private Map<Integer, EstruturaFamiliar> estruturaFamiliares;
    private EstruturaFamiliarDAO estruturaFamiliarDao = new EstruturaFamiliarDAO();

    public EstruturaFamiliarController() throws SQLException {
        estruturaFamiliares = new HashMap<Integer, EstruturaFamiliar>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estruturaFamiliar", method = RequestMethod.GET)
    public ResponseEntity<List<EstruturaFamiliar>> listar() throws SQLException {
        int index=0;

        List<EstruturaFamiliar> estruturaFamiliaresGetted = new ArrayList<EstruturaFamiliar>();
        estruturaFamiliares = new HashMap<Integer, EstruturaFamiliar>();

        estruturaFamiliaresGetted = estruturaFamiliarDao.getLista();

        for (EstruturaFamiliar usu : estruturaFamiliaresGetted) {
            estruturaFamiliares.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<EstruturaFamiliar>>(new ArrayList<EstruturaFamiliar>(estruturaFamiliares.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estruturaFamiliar/{id}", method = RequestMethod.GET)
    public ResponseEntity<EstruturaFamiliar> buscar(@PathVariable("id") int id) throws SQLException {

        EstruturaFamiliar estrutura_Familiar = estruturaFamiliarDao.getEstruturaFamiliar(id);
        if (estrutura_Familiar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<EstruturaFamiliar>(estrutura_Familiar, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estruturaFamiliar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) throws SQLException {
        estruturaFamiliarDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/estruturaFamiliar", method = RequestMethod.POST)
    public ResponseEntity<EstruturaFamiliar> addEstruturaFamiliar(@RequestBody EstruturaFamiliar estruturaFamiliar) throws JsonParseException, JsonMappingException, IOException, SQLException {

        estruturaFamiliarDao.adiciona(estruturaFamiliar);
        return new ResponseEntity<EstruturaFamiliar>(estruturaFamiliar, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/estruturaFamiliar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EstruturaFamiliar> updateEstruturaFamiliar(@RequestBody EstruturaFamiliar estruturaFamiliar, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        estruturaFamiliarDao.altera(estruturaFamiliar, id);
        return new ResponseEntity<EstruturaFamiliar>(estruturaFamiliar, HttpStatus.CREATED);
    }
}
