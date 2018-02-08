package jdbc.dao.aluno;

import jdbc.ConnectionFactory;
import model.aluno.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {
    private Connection connection;
    private EstruturaFamiliarDAO estruturaFamiliarDao;

    public DespesaDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.estruturaFamiliarDao = new EstruturaFamiliarDAO();
    }

    public void adiciona(Despesa despesa) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO despesa (id_estrutura_familiar, agua, energia_eletrica, telefone, aluguel, financiamento_casa, financiamento_carro, transporte, alimentacao, gas, cartao_credito, emprestimo, tv_cabo, educacao, pensao, convenio_medico) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        stmt.setInt(1,despesa.getEstruturaFamiliar().getId());
        stmt.setDouble(2, despesa.getAgua());
        stmt.setDouble(3, despesa.getEnergiaEletrica());
        stmt.setDouble(4, despesa.getTelefone());
        stmt.setDouble(5, despesa.getAluguel());
        stmt.setDouble(6, despesa.getFinanciamentoCasa());
        stmt.setDouble(7, despesa.getFinanciamentoCarro());
        stmt.setDouble(8, despesa.getTransporte());
        stmt.setDouble(9, despesa.getAlimentacao());
        stmt.setDouble(10, despesa.getGas());
        stmt.setDouble(11, despesa.getCartaoCredito());
        stmt.setDouble(12, despesa.getEmprestimo());
        stmt.setDouble(13, despesa.getTvCabo());
        stmt.setDouble(14, despesa.getEducacao());
        stmt.setDouble(15, despesa.getPensao());
        stmt.setDouble(16, despesa.getConvenioMedico());

        // executa
        stmt.execute();
        stmt.close();
    }

    public List<Despesa> getLista() throws SQLException {

        List<Despesa> despesas = new ArrayList<Despesa>();

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM despesa");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Despesa despesa = new Despesa();

            despesa.setEstruturaFamiliar(estruturaFamiliarDao.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));
            despesa.setAgua(rs.getDouble("agua"));
            despesa.setEnergiaEletrica(rs.getDouble("energia_eletrica"));
            despesa.setTelefone(rs.getDouble("telefone"));
            despesa.setAluguel(rs.getDouble("aluguel"));
            despesa.setFinanciamentoCasa(rs.getDouble("financiamento_casa"));
            despesa.setFinanciamentoCarro(rs.getDouble("financiamento_carro"));
            despesa.setTransporte(rs.getDouble("transporte"));
            despesa.setAlimentacao(rs.getDouble("alimentacao"));
            despesa.setGas(rs.getDouble("gas"));
            despesa.setCartaoCredito(rs.getDouble("cartao_credito"));
            despesa.setEmprestimo(rs.getDouble("emprestimo"));
            despesa.setTvCabo(rs.getDouble("tv_cabo"));
            despesa.setEducacao(rs.getDouble("educacao"));
            despesa.setPensao(rs.getDouble("pensao"));
            despesa.setConvenioMedico(rs.getDouble("convenio_medico"));

            despesas.add(despesa);

        }

        rs.close();
        stmt.close();

        return despesas;

    }

    public Despesa getDespesa(int search) throws SQLException {

        Despesa despesa = new Despesa();

        try {
            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM despesa WHERE " + "id_estrutura_familiar = ?");

            stmt.setInt(1, search);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                despesa.setEstruturaFamiliar(estruturaFamiliarDao.getEstruturaFamiliar(rs.getInt("id_estrutura_familiar")));
                despesa.setAgua(rs.getDouble("agua"));
                despesa.setEnergiaEletrica(rs.getDouble("energia_eletrica"));
                despesa.setTelefone(rs.getDouble("telefone"));
                despesa.setAluguel(rs.getDouble("aluguel"));
                despesa.setFinanciamentoCasa(rs.getDouble("financiamento_casa"));
                despesa.setFinanciamentoCarro(rs.getDouble("financiamento_carro"));
                despesa.setTransporte(rs.getDouble("transporte"));
                despesa.setAlimentacao(rs.getDouble("alimentacao"));
                despesa.setGas(rs.getDouble("gas"));
                despesa.setCartaoCredito(rs.getDouble("cartao_credito"));
                despesa.setEmprestimo(rs.getDouble("emprestimo"));
                despesa.setTvCabo(rs.getDouble("tv_cabo"));
                despesa.setEducacao(rs.getDouble("educacao"));
                despesa.setPensao(rs.getDouble("pensao"));
                despesa.setConvenioMedico(rs.getDouble("convenio_medico"));
            }
            
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return (despesa);

    }


    public void excluir(int search) {

        try {

            PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("DELETE FROM despesa WHERE id_estrutura_familiar = ?");
            stmt.setInt(1, search);
            stmt.execute();
            stmt.close();
        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Despesa despesa, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE despesa SET agua=?, energia_eletrica=?, telefone=?, aluguel=?, financiamento_casa=?, financiamento_carro=?, transporte=?, alimentacao=?, gas=?, cartao_credito=?, emprestimo=?, tv_cabo=?, educacao=?, pensao=?, convenio_medico=? WHERE id_estrutura_familiar=?");

        stmt.setDouble(1, despesa.getAgua());
        stmt.setDouble(2, despesa.getEnergiaEletrica());
        stmt.setDouble(3, despesa.getTelefone());
        stmt.setDouble(4, despesa.getAluguel());
        stmt.setDouble(5, despesa.getFinanciamentoCasa());
        stmt.setDouble(6, despesa.getFinanciamentoCarro());
        stmt.setDouble(7, despesa.getTransporte());
        stmt.setDouble(8, despesa.getAlimentacao());
        stmt.setDouble(9, despesa.getGas());
        stmt.setDouble(10, despesa.getCartaoCredito());
        stmt.setDouble(11, despesa.getEmprestimo());
        stmt.setDouble(12, despesa.getTvCabo());
        stmt.setDouble(13, despesa.getEducacao());
        stmt.setDouble(14, despesa.getPensao());
        stmt.setDouble(15, despesa.getConvenioMedico());
        stmt.setInt(16, id);

        stmt.execute();
        stmt.close();
    }
}
