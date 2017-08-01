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
    private Estrutura_FamiliarDAO estrutura_familiarDao;

    public DespesaDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
        this.estrutura_familiarDao = new Estrutura_FamiliarDAO();
    }

    public void adiciona(Despesa despesa) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("INSERT INTO despesa (id_estrutura_familiar, agua, energia_eletrica, telefone, aluguel, financiamento_casa, financiamento_carro, transporte, alimentacao, gas, cartao_credito, emprestimo, tv_cabo, educacao, pensao, convenio_medico) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        stmt.setInt(1,despesa.getEstrutura_familiar().getId());
        stmt.setDouble(2, despesa.getAgua());
        stmt.setDouble(3, despesa.getEnergia_eletrica());
        stmt.setDouble(4, despesa.getTelefone());
        stmt.setDouble(5, despesa.getAluguel());
        stmt.setDouble(6, despesa.getFinanciamento_casa());
        stmt.setDouble(7, despesa.getFinanciamento_carro());
        stmt.setDouble(8, despesa.getTransporte());
        stmt.setDouble(9, despesa.getAlimentacao());
        stmt.setDouble(10, despesa.getGas());
        stmt.setDouble(11, despesa.getCartao_credito());
        stmt.setDouble(12, despesa.getEmprestimo());
        stmt.setDouble(13, despesa.getTv_cabo());
        stmt.setDouble(14, despesa.getEducacao());
        stmt.setDouble(15, despesa.getPensao());
        stmt.setDouble(16, despesa.getConvenio_medico());

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

            despesa.setEstrutura_familiar(estrutura_familiarDao.getEstrutura_Familiar(rs.getInt("id_estrutura_familiar")));
            despesa.setAgua(rs.getDouble("agua"));
            despesa.setEnergia_eletrica(rs.getDouble("energia_eletrica"));
            despesa.setTelefone(rs.getDouble("telefone"));
            despesa.setAluguel(rs.getDouble("aluguel"));
            despesa.setFinanciamento_casa(rs.getDouble("financiamento_casa"));
            despesa.setFinanciamento_carro(rs.getDouble("financiamento_carro"));
            despesa.setTransporte(rs.getDouble("transporte"));
            despesa.setAlimentacao(rs.getDouble("alimentacao"));
            despesa.setGas(rs.getDouble("gas"));
            despesa.setCartao_credito(rs.getDouble("cartao_credito"));
            despesa.setEmprestimo(rs.getDouble("emprestimo"));
            despesa.setTv_cabo(rs.getDouble("tv_cabo"));
            despesa.setEducacao(rs.getDouble("educacao"));
            despesa.setPensao(rs.getDouble("pensao"));
            despesa.setConvenio_medico(rs.getDouble("convenio_medico"));

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
                despesa.setEstrutura_familiar(estrutura_familiarDao.getEstrutura_Familiar(rs.getInt("id_estrutura_familiar")));
                despesa.setAgua(rs.getDouble("agua"));
                despesa.setEnergia_eletrica(rs.getDouble("energia_eletrica"));
                despesa.setTelefone(rs.getDouble("telefone"));
                despesa.setAluguel(rs.getDouble("aluguel"));
                despesa.setFinanciamento_casa(rs.getDouble("financiamento_casa"));
                despesa.setFinanciamento_carro(rs.getDouble("financiamento_carro"));
                despesa.setTransporte(rs.getDouble("transporte"));
                despesa.setAlimentacao(rs.getDouble("alimentacao"));
                despesa.setGas(rs.getDouble("gas"));
                despesa.setCartao_credito(rs.getDouble("cartao_credito"));
                despesa.setEmprestimo(rs.getDouble("emprestimo"));
                despesa.setTv_cabo(rs.getDouble("tv_cabo"));
                despesa.setEducacao(rs.getDouble("educacao"));
                despesa.setPensao(rs.getDouble("pensao"));
                despesa.setConvenio_medico(rs.getDouble("convenio_medico"));
            }
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

        }

        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }


    public void altera(Despesa despesa, int id) throws SQLException {

        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("UPDATE despesa SET agua=?, energia_eletrica=?, telefone=?, aluguel=?, financiamento_casa=?, financiamento_carro=?, transporte=?, alimentacao=?, gas=?, cartao_credito=?, emprestimo=?, tv_cabo=?, educacao=?, pensao=?, convenio_medico=? WHERE id_estrutura_familiar=?");

        stmt.setDouble(1, despesa.getAgua());
        stmt.setDouble(2, despesa.getEnergia_eletrica());
        stmt.setDouble(3, despesa.getTelefone());
        stmt.setDouble(4, despesa.getAluguel());
        stmt.setDouble(5, despesa.getFinanciamento_casa());
        stmt.setDouble(6, despesa.getFinanciamento_carro());
        stmt.setDouble(7, despesa.getTransporte());
        stmt.setDouble(8, despesa.getAlimentacao());
        stmt.setDouble(9, despesa.getGas());
        stmt.setDouble(10, despesa.getCartao_credito());
        stmt.setDouble(11, despesa.getEmprestimo());
        stmt.setDouble(12, despesa.getTv_cabo());
        stmt.setDouble(13, despesa.getEducacao());
        stmt.setDouble(14, despesa.getPensao());
        stmt.setDouble(15, despesa.getConvenio_medico());
        stmt.setInt(16, id);

        stmt.execute();
        stmt.close();
    }
}
