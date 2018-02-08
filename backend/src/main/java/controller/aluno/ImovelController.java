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

    private ImovelDAO imovelDao = new ImovelDAO();

    public ImovelController() throws SQLException { }

    @CrossOrigin
    @RequestMapping(value = "/api/imovel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) {
        imovelDao.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
