package persistencia;

import dominio.EntidadeDominio;
import dominio.cliente.Cupom;
import dominio.compra.Notificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO extends ConexaoBD implements IDAO {

    public void salvar(EntidadeDominio entidade) throws SQLException {
        Notificacao notificacao = (Notificacao) entidade;
        criarTabela();

        try (Connection conexao = getConexao()) {
            String sql = "INSERT INTO notificacoes (clienteId, mensagem) VALUES (?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, notificacao.getClienteId());
            ps.setString(2, notificacao.getMensagem());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                notificacao.setId(generatedKeys.getInt(1));
            }
        }
    }

    public void alterar(EntidadeDominio entidade) throws SQLException {
        Notificacao notificacao = (Notificacao) entidade;
        try (Connection conexao = getConexao()) {
            String sql = "UPDATE notificacoes SET clienteId = ?, mensagem = ? WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, notificacao.getClienteId());
            ps.setString(2, notificacao.getMensagem());
            ps.setInt(3, notificacao.getId());
            ps.execute();
        }
    }

    public void excluir(EntidadeDominio entidade) throws SQLException {
        Notificacao notificacao = (Notificacao) entidade;
        try (Connection conexao = getConexao()) {
            String sql = "DELETE FROM notificacoes WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, notificacao.getId());
            ps.execute();
        }
    }

    private static void criarTabela() throws SQLException {
        try (Connection conexao = getConexao()) {
            String sql = "CREATE TABLE IF NOT EXISTS notificacoes (" +
                    "id SERIAL PRIMARY KEY," +
                    "clienteId INTEGER NOT NULL," +
                    "mensagem VARCHAR(255) NOT NULL" +
                    ")";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.execute();
        }
    }

    public static List<Notificacao> listar(Integer clienteId) throws SQLException {
        List<Notificacao> notificacoes = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM notificacoes WHERE clienteId = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, clienteId);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Notificacao notificacao = new Notificacao(rs.getInt("id"), rs.getInt("clienteId"), rs.getString("mensagem"));
                notificacoes.add(notificacao);
            }
            return notificacoes;
        }
    }

    public static Notificacao buscarNotificacaoPorId(Integer id) throws SQLException {
        Notificacao notificacao = null;
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM notificacoes WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                notificacao = new Notificacao(rs.getInt("id"), rs.getInt("clienteId"), rs.getString("mensagem"));
            }
            return notificacao;
        }
    }

}