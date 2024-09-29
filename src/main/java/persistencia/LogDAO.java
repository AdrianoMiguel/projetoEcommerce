package persistencia;


import dominio.*;
import dominio.cliente.*;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LogDAO extends ConexaoBD implements IDAO{
    @Override

    public void salvar(EntidadeDominio entidade) throws Exception{
        criarTabela();
        Log log = (Log) entidade;
        try (Connection conexao = getConexao()) {
            String query = " insert into logs (clienteId,dtHorario,usuarioAcao,tipoAcao,detalhesAcao)"
                    + " values (?,?,?,?,?)";

            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, log.getClienteID());
            sql.setTimestamp(2, Timestamp.from(Instant.now()));
            sql.setString(3, log.getUsuarioAcao().toString());
            sql.setString(4, log.getTipoAcao());
            sql.setString(5, log.getDetalhesAcao());

            sql.execute();
        }
    }

    public List<Log> listar(Integer clienteId) {

        List<Log> logs = new ArrayList<>();

        String query = "SELECT * FROM logs WHERE clienteId = ?";

        try (Connection conexao = getConexao();
             PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setLong(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Log log = new Log(rs.getInt("clienteId"));
                log.setDataHoraAcao((rs.getTimestamp("dtHorario")));
                log.setUsuarioAcao(Usuario.valueOf(rs.getString("usuarioAcao")));
                log.setTipoAcao(rs.getString("tipoAcao"));
                log.setDetalhesAcao(rs.getString("detalhesAcao"));

                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }

    public static void criarTabela() throws SQLException {
        try (Connection conexao = getConexao()) {
            PreparedStatement sql = conexao.prepareStatement(
                    """                   
CREATE TABLE IF NOT EXISTS logs (
    id BIGSERIAL PRIMARY KEY,
    clienteId BIGINT NOT NULL,
    dtHorario TIMESTAMP NOT NULL,
    usuarioAcao VARCHAR(255) NOT NULL,
    tipoAcao VARCHAR(255) NOT NULL,
    detalhesAcao VARCHAR(255) NOT NULL,
      FOREIGN KEY (clienteId)
      REFERENCES Clientes (id)
      ON DELETE CASCADE
);"""
            );
            sql.execute();
        }
    }

    @Override
    public void alterar(EntidadeDominio var1) throws Exception {

    }

    @Override
    public void excluir(EntidadeDominio var1) throws Exception {

    }
}

