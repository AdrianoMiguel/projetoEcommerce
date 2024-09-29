package persistencia;

import dominio.EntidadeDominio;
import dominio.cliente.Cupom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupomDAO extends ConexaoBD implements IDAO {

    public void salvar(EntidadeDominio entidade) throws SQLException {
        Cupom cupom = (Cupom) entidade;
        try (Connection conexao = getConexao()) {
            String sql = "INSERT INTO cupons (clienteId, codigo, valor, utilizado) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cupom.getClienteId());
            ps.setString(2, cupom.getCodigo());
            ps.setDouble(3, cupom.getValor());
            ps.setBoolean(4, cupom.getUtilizado());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                cupom.setId(generatedKeys.getInt(1));
            }
        }
    }

    public void alterar(EntidadeDominio entidade) throws SQLException {
        Cupom cupom = (Cupom) entidade;
        try (Connection conexao = getConexao()) {
            String sql = "UPDATE cupons SET codigo = ?, valor = ?, utilizado = ? WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cupom.getCodigo());
            ps.setDouble(2, cupom.getValor());
            ps.setBoolean(3, cupom.getUtilizado());
            ps.setInt(4, cupom.getId());
            ps.execute();
        }
    }

    public void excluir(EntidadeDominio entidade) throws SQLException {
        Cupom cupom = (Cupom) entidade;
        try (Connection conexao = getConexao()) {
            String sql = "DELETE FROM cupons WHERE codigo = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cupom.getCodigo());
            ps.execute();
        }
    }

    private static void criarTabela() throws SQLException {
        try (Connection conexao = getConexao()) {
            String sql = "CREATE TABLE IF NOT EXISTS cupons (" +
                    "id SERIAL PRIMARY KEY," +
                    "clienteId INTEGER NOT NULL," +
                    "codigo VARCHAR(255) NOT NULL," +
                    "valor DOUBLE PRECISION NOT NULL," +
                    "utilizado BOOLEAN NOT NULL" +
                    ")";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.execute();
        }
    }

    public static List<Cupom> listar(Integer clienteId) throws SQLException {
        criarTabela();
        List<Cupom> cupons = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM cupons WHERE clienteId = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, clienteId);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Cupom cupom = new Cupom(rs.getInt("id"), rs.getInt("clienteId"),
                        rs.getString("codigo"), rs.getDouble("valor"), rs.getBoolean("utilizado"));
                cupons.add(cupom);
            }
        }
        return cupons;
    }

    public static Cupom buscarCupomPorId(Integer id) throws SQLException {
        Cupom cupom = null;
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM cupons WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                cupom = new Cupom(rs.getInt("id"), rs.getInt("clienteId"),
                        rs.getString("codigo"), rs.getDouble("valor"), rs.getBoolean("utilizado"));
            }
        }
        return cupom;
    }

}