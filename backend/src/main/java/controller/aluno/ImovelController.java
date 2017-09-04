package controller.aluno;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jdbc.dao.aluno.ImovelDAO;
import model.aluno.Imovel;
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
public class ImovelController {
    private Map<Integer, Imovel> imovels;
    private ImovelDAO imovelDao = new ImovelDAO();

    public ImovelController() throws SQLException {
        imovels = new HashMap<Integer, Imovel>();
    }

    @CrossOrigin
    @RequestMapping(value = "/api/imovel", method = RequestMethod.GET)
    public ResponseEntity<List<Imovel>> listar() throws SQLException {
        int index=0;

        List<Imovel> imovelsGetted = new ArrayList<Imovel>();
        imovels = new HashMap<Integer, Imovel>();

        imovelsGetted = imovelDao.getLista();

        for (Imovel usu : imovelsGetted) {
            imovels.put(index, usu);
            index++;
        }

        return new ResponseEntity<List<Imovel>>(new ArrayList<Imovel>(imovels.values()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/imovel/{id}", method = RequestMethod.GET)
    public ResponseEntity<Imovel> buscar(@PathVariable("id") int id) throws SQLException {

        Imovel imovel = imovelDao.getImovel(id);
        if (imovel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Imovel>(imovel, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/imovel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        imovelDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @CrossOrigin
    @RequestMapping(value = "/api/imovel", method = RequestMethod.POST)
    public ResponseEntity<Imovel> addImovel(@RequestBody Imovel imovel) throws JsonParseException, JsonMappingException, IOException, SQLException {

        imovelDao.adiciona(imovel);
        return new ResponseEntity<Imovel>(imovel, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/imovel/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Imovel> updateImovel(@RequestBody Imovel imovel, @PathVariable("id") int id) throws JsonParseException, JsonMappingException, IOException, SQLException {

        imovelDao.altera(imovel, id);
        return new ResponseEntity<Imovel>(imovel, HttpStatus.CREATED);
    }
}
