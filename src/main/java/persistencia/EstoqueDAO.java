package persistencia;

import dominio.EntidadeDominio;
import dominio.estoque.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EstoqueDAO extends ConexaoBD implements IDAO {
    @Override
    public void salvar(EntidadeDominio entidade) throws Exception {
        criarTabela();
        Estoque estoque = (Estoque) entidade;

        try (Connection conexao = getConexao()) {
            String query = "INSERT INTO estoque (vinhoId, dataEntrada, custoUnitario, quantidade, fornecedor) VALUES (?,?,?,?,?)";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, estoque.getVinhoId());
            sql.setTimestamp(2, estoque.getDataEntrada());
            sql.setDouble(3, estoque.getCustoUnitario());
            sql.setInt(4, estoque.getQuantidade());
            sql.setString(5, estoque.getFornecedor().toString());
            sql.executeUpdate();

            String queryUpdateVinhos = "UPDATE vinhos SET qtde_estoque = qtde_estoque + ? WHERE id = ?";
            PreparedStatement sqlUpdateVinhos = conexao.prepareStatement(queryUpdateVinhos);
            sqlUpdateVinhos.setDouble(1, estoque.getQuantidade());
            sqlUpdateVinhos.setInt(2, estoque.getVinhoId());
            sqlUpdateVinhos.executeUpdate();
        }
    }

    @Override
    public void alterar(EntidadeDominio entidade) throws Exception {

    }

    @Override
    public void excluir(EntidadeDominio entidade) throws Exception {

    }


    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws Exception {
        return null;
    }

    private void criarTabela() throws SQLException {
        try (Connection conexao = getConexao()) {
            String query = """
        CREATE TABLE IF NOT EXISTS estoque (
            id BIGSERIAL NOT NULL PRIMARY KEY,
            vinhoId BIGINT NOT NULL REFERENCES vinhos(id) ON DELETE CASCADE,
            dataEntrada TIMESTAMP NOT NULL,
            custoUnitario DOUBLE PRECISION,
            quantidade INTEGER,
            fornecedor VARCHAR(255)
        );
    
        """;
            try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                stmt.executeUpdate();
            }
        }
    }
}
