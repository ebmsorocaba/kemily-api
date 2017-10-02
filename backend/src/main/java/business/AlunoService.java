package business;

import DTO.AlunoDTO;
import jdbc.ConnectionFactory;
import jdbc.dao.aluno.*;
import model.aluno.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoService {
    private AlunoDAO alunoDAO;
    private AparelhosEletronicosDAO aparelhosEletronicosDAO;
    private AutomovelDAO automovelDAO;
    private MembroFamiliarDAO membroFamiliarDAO;
    private DespesaDAO despesaDAO;
    private EnderecoDAO enderecoDAO;
    private EstruturaFamiliarDAO estruturaFamiliarDAO;
    private ImovelDAO imovelDAO;
    private RoupaDAO roupaDAO;
    private SaudeDAO saudeDAO;
    private SituacaoHabitacionalDAO situacaoHabitacionalDAO;
    private ResponsavelLegalDAO responsavelLegalDAO;
    private ContatoDAO contatoDAO;

    public AlunoService() throws SQLException {
        this.alunoDAO = new AlunoDAO();
        this.aparelhosEletronicosDAO = new AparelhosEletronicosDAO();
        this.automovelDAO = new AutomovelDAO();
        this.membroFamiliarDAO = new MembroFamiliarDAO();
        this.despesaDAO = new DespesaDAO();
        this.enderecoDAO = new EnderecoDAO();
        this.estruturaFamiliarDAO = new EstruturaFamiliarDAO();
        this.imovelDAO = new ImovelDAO();
        this.roupaDAO = new RoupaDAO();
        this.saudeDAO = new SaudeDAO();
        this.situacaoHabitacionalDAO = new SituacaoHabitacionalDAO();
        this.responsavelLegalDAO = new ResponsavelLegalDAO();
        this.contatoDAO = new ContatoDAO();
    }

    public List<AlunoDTO> GetAll() throws SQLException {
        ArrayList<AlunoDTO> result = new ArrayList<AlunoDTO>();
        List<Aluno> alunos = alunoDAO.getLista();
        for (Aluno a : alunos) {
            AlunoDTO aluno = new AlunoDTO();
            aluno.setAluno(a);
            aluno.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(a.getRa()));
            aluno.setDespesa(despesaDAO.getDespesa(aluno.getEstruturaFamiliar().getId()));
            aluno.setSituacaoHabitacional(situacaoHabitacionalDAO.getSituacaoHabitacional(a.getRa()));
            aluno.setAparelhosEletronicos(aluno.getSituacaoHabitacional().getAparelhosEletronicos());
            aluno.setRoupa(roupaDAO.getRoupa(a.getRa()));
            aluno.setEndereco(enderecoDAO.getEndereco(a.getEndereco().getCep(), a.getEndereco().getNumero()));
            aluno.setSaude(saudeDAO.getSaude(a.getRa()));
            aluno.setAutomovelList(automovelDAO.getByEstruturaFamiliar(aluno.getEstruturaFamiliar().getId()));
            aluno.setImovelList(imovelDAO.getByEstruturaFamiliar(aluno.getEstruturaFamiliar().getId()));
            aluno.setContatoList(contatoDAO.getByAluno(aluno.getAluno().getRa()));
            aluno.setMembroFamiliarList(membroFamiliarDAO.getByAluno(aluno.getAluno().getRa()));
            aluno.setResponsavelLegalList(responsavelLegalDAO.getByAluno(aluno.getAluno().getRa()));

            result.add(aluno);
        }

        return result;
    }

    public void Post(AlunoDTO aluno) throws SQLException {

        System.out.println(aluno.getEndereco());
        Endereco endereco = aluno.getEndereco();
        enderecoDAO.adiciona(endereco);

        Aluno alu = aluno.getAluno();
        alu.setEndereco(endereco);
        alunoDAO.adiciona(alu);

        Roupa roupa = aluno.getRoupa();
        EstruturaFamiliar estruturaFamiliar = aluno.getEstruturaFamiliar();
        AparelhosEletronicos aparelhosEletronicos = aluno.getAparelhosEletronicos();
        Despesa despesa = aluno.getDespesa();
        List<Automovel> automoveis = aluno.getAutomovelList();
        List<Imovel> imoveis = aluno.getImovelList();
        SituacaoHabitacional situacaoHabitacional = aluno.getSituacaoHabitacional();
        Saude saude = aluno.getSaude();
        List<MembroFamiliar> membroFamiliars = aluno.getMembroFamiliarList();
        List<Contato> contatos = aluno.getContatoList();
        List<ResponsavelLegal> responsavelLegals = aluno.getResponsavelLegalList();


        roupa.setAluno(aluno.getAluno());
        estruturaFamiliar.setAluno(aluno.getAluno());
        situacaoHabitacional.setAluno(aluno.getAluno());
        saude.setAluno(aluno.getAluno());


        roupaDAO.adiciona(roupa);
        saudeDAO.adiciona(saude);
        estruturaFamiliarDAO.adiciona(estruturaFamiliar);
        aparelhosEletronicosDAO.adiciona(aparelhosEletronicos);
        despesa.setEstruturaFamiliar(aluno.getEstruturaFamiliar());
        situacaoHabitacional.setAparelhosEletronicos(aluno.getAparelhosEletronicos());

        despesaDAO.adiciona(despesa);
        situacaoHabitacionalDAO.adicionar(situacaoHabitacional);

        for (Automovel a : automoveis) {
            a.setEstruturaFamiliar(aluno.getEstruturaFamiliar());
            automovelDAO.adiciona(a);
        }

        for (Imovel i : imoveis) {
            i.setEstruturaFamiliar(aluno.getEstruturaFamiliar());
            imovelDAO.adiciona(i);
        }

        for (MembroFamiliar c : membroFamiliars) {
            c.setAluno(aluno.getAluno());
            membroFamiliarDAO.adiciona(c);
        }

        for (Contato c : contatos) {
            c.setAluno(aluno.getAluno());
            contatoDAO.adicionar(c);
        }

        for (ResponsavelLegal r : responsavelLegals) {
            r.setAluno(aluno.getAluno());
            responsavelLegalDAO.adicionar(r);
        }
    }

    public void Put(AlunoDTO aluno, int ra) throws SQLException {
        alunoDAO.altera(aluno.getAluno(), ra);
        roupaDAO.altera(aluno.getRoupa(), ra);
        estruturaFamiliarDAO.altera(aluno.getEstruturaFamiliar(), aluno.getEstruturaFamiliar().getId());
        enderecoDAO.altera(aluno.getEndereco(), aluno.getEndereco().getCep(), aluno.getEndereco().getNumero());
        aparelhosEletronicosDAO.altera(aluno.getAparelhosEletronicos(), aluno.getAparelhosEletronicos().getId());
        despesaDAO.altera(aluno.getDespesa(), aluno.getDespesa().getEstruturaFamiliar().getId());
        situacaoHabitacionalDAO.altera(aluno.getSituacaoHabitacional(), ra);
        saudeDAO.altera(aluno.getSaude(), ra);

        for (Imovel i : aluno.getImovelList()) {
            if (i.getId() != 0) {
                imovelDAO.altera(i, i.getId());
            } else {
                i.setEstruturaFamiliar(aluno.getEstruturaFamiliar());
                imovelDAO.adiciona(i);
            }
        }

        for (Automovel a : aluno.getAutomovelList()) {
            if (a.getId() != 0) {
                automovelDAO.altera(a, a.getId());
            } else {
                a.setEstruturaFamiliar(aluno.getEstruturaFamiliar());
                automovelDAO.adiciona(a);
            }
        }

        for (MembroFamiliar c : aluno.getMembroFamiliarList()) {
            if (c.getId() != 0) {
                membroFamiliarDAO.altera(c, c.getId());
            } else {
                c.setAluno(aluno.getAluno());
                membroFamiliarDAO.adiciona(c);
            }
        }

        for (Contato c : aluno.getContatoList()) {
            if (c.getId() != 0) {
                contatoDAO.altera(c, c.getId());
            } else {
                c.setAluno(aluno.getAluno());
                contatoDAO.adicionar(c);
            }
        }

        for (ResponsavelLegal r : aluno.getResponsavelLegalList()) {
            if (r.getId() != 0) {
                responsavelLegalDAO.altera(r, r.getId());
            } else {
                r.setAluno(aluno.getAluno());
                responsavelLegalDAO.adicionar(r);
            }
        }
    }

    public AlunoDTO GetSingle(int ra) throws SQLException {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setAluno(alunoDAO.getAluno(ra));
        alunoDTO.setRoupa(roupaDAO.getRoupa(ra));
        alunoDTO.setEstruturaFamiliar(estruturaFamiliarDAO.getEstruturaFamiliar(ra));
        alunoDTO.setSaude(saudeDAO.getSaude(ra));
        alunoDTO.setSituacaoHabitacional(situacaoHabitacionalDAO.getSituacaoHabitacional(ra));
        alunoDTO.setAparelhosEletronicos(alunoDTO.getSituacaoHabitacional().getAparelhosEletronicos());
        alunoDTO.setEndereco(alunoDTO.getAluno().getEndereco());
        alunoDTO.setDespesa(despesaDAO.getDespesa(alunoDTO.getEstruturaFamiliar().getId()));
        alunoDTO.setContatoList(contatoDAO.getByAluno(ra));
        alunoDTO.setResponsavelLegalList(responsavelLegalDAO.getByAluno(ra));
        alunoDTO.setMembroFamiliarList(membroFamiliarDAO.getByAluno(ra));
        alunoDTO.setImovelList(imovelDAO.getByEstruturaFamiliar(alunoDTO.getEstruturaFamiliar().getId()));
        alunoDTO.setAutomovelList(automovelDAO.getByEstruturaFamiliar(alunoDTO.getEstruturaFamiliar().getId()));

        return alunoDTO;
    }
}
