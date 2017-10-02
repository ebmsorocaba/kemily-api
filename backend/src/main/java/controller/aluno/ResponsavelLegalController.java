package controller.aluno;

import jdbc.dao.aluno.ResponsavelLegalDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ResponsavelLegalController {
    private ResponsavelLegalDAO responsavelLegalDAO = new ResponsavelLegalDAO();

    public ResponsavelLegalController () throws SQLException {}

    @CrossOrigin
    @RequestMapping(value = "/api/responsavelLegal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletar(@PathVariable("id") int id) throws SQLException {
        responsavelLegalDAO.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
