package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.DespesaDAO;
import model.aluno.Despesa;
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
public class DespesaController {
    private Map<Integer, Despesa> despesas;
    private DespesaDAO despesaDao = new DespesaDAO();

    public DespesaController() throws SQLException {
        despesas = new HashMap<Integer, Despesa>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/despesa", method = RequestMethod.GET)
    public ResponseEntity<List<Despesa>> listar() throws SQLException {
        int index=0;

        List<Despesa> despesasGetted = new ArrayList<Despesa>();
        despesas = new HashMap<Integer, Despesa>();

        despesasGetted = despesaDao.getLista();

        for (Despesa usu : despesasGetted) {
            despesas.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Despesa>>(new ArrayList<Despesa>(despesas.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/despesa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Despesa> buscar(@PathVariable("id") int id) throws SQLException {

        Despesa despesa = despesaDao.getDespesa(id);
        if (despesa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Despesa>(despesa, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/despesa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        despesaDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/despesa", method = RequestMethod.POST)
    public ResponseEntity<Despesa> addDespesa(@RequestBody Despesa despesa) throws JsonParseException, JsonMappingException, IOException, SQLException {

        despesaDao.adiciona(despesa);
        return new ResponseEntity<Despesa>(despesa, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/despesa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Despesa> updateDespesa(@RequestBody Despesa despesa, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        despesaDao.altera(despesa, id);
        return new ResponseEntity<Despesa>(despesa, HttpStatus.CREATED);
    }
}
