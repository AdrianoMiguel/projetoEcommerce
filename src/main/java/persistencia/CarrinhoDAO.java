package persistencia;

import dominio.EntidadeDominio;
import dominio.compra.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO extends ConexaoBD implements IDAO {
    @Override
    public void salvar(EntidadeDominio var1) throws Exception {
        Carrinho carrinho = (Carrinho) var1;
        if (buscarCarrinhoNaoEfetivadoPorCliente(carrinho.getClienteId()) == null) {
            try (Connection conexao = getConexao()) {
                String sql = "INSERT INTO carrinhos (cliente_id, tempo, efetivado,total) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, carrinho.getClienteId());
                ps.setTime(2, carrinho.getTempo());
                ps.setBoolean(3, carrinho.getEfetivado());
                ps.setDouble(4, carrinho.getTotal());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    carrinho.setId(rs.getInt(1));
                }
                for (Item item : carrinho.getItens()) {
                    sql = "INSERT INTO itens (carrinho_id, produto_id, quantidade) VALUES (?, ?, ?)";
                    ps = conexao.prepareStatement(sql);
                    ps.setInt(1, carrinho.getId());
                    ps.setInt(2, item.getProduto().getId());
                    ps.setInt(3, item.getQuantidade());
                    ps.execute();
                }
            }
        } else {
            alterar(var1);
        }
    }

    @Override
    public void alterar(EntidadeDominio var1) throws Exception {
        Double total = 0.0;
        Carrinho carrinho = (Carrinho) var1;

        try (Connection conexao = getConexao()) {
            // Começa uma transação


            // Busca os itens antigos do carrinho
            List<Item> itensAntigos = new ArrayList<>();
            String sql = "SELECT * FROM itens WHERE carrinho_id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, carrinho.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Item item = new Item(rs.getInt("quantidade"), ProdutoDAO.buscarVinhoPorId(rs.getInt("produto_id")));
                        itensAntigos.add(item);
                    }
                }
            }



            // Atualiza o carrinho
            sql = "UPDATE carrinhos SET tempo = ?, efetivado = ?, total = ? WHERE id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setTime(1, carrinho.getTempo());
                ps.setBoolean(2, carrinho.getEfetivado());
                ps.setDouble(3, carrinho.getTotal());
                ps.setInt(4, carrinho.getId());
                ps.execute();
            }

            // Remove os itens antigos
            sql = "DELETE FROM itens WHERE carrinho_id = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setInt(1, carrinho.getId());
                ps.execute();
            }

            // Insere os novos itens e calcula o total
            for (Item item : carrinho.getItens()) {
                sql = "INSERT INTO itens (carrinho_id, produto_id, quantidade) VALUES (?, ?, ?)";
                try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                    ps.setInt(1, carrinho.getId());
                    ps.setInt(2, item.getProduto().getId());
                    ps.setInt(3, item.getQuantidade());
                    ps.execute();

                }
            }
        }
    }


    public void reporEstoque(Carrinho carrinho) throws Exception {
        try (Connection conexao = getConexao()) {
            for (Item item : carrinho.getItens()) {
                String sql = "UPDATE vinhos SET qtde_estoque = qtde_estoque + ? WHERE id = ?";
                try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                    ps.setInt(1, item.getQuantidade());
                    ps.setInt(2, item.getProduto().getId());
                    ps.execute();
                }
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio var1) throws Exception {
        Carrinho carrinho = (Carrinho) var1;
        try (Connection conexao = getConexao()) {

            reporEstoque(carrinho);

            String sql = "DELETE FROM itens WHERE carrinho_id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, carrinho.getId());
            ps.execute();

            sql = "DELETE FROM carrinhos WHERE id = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, carrinho.getId());
            ps.execute();
        }
    }

    public static Carrinho buscarCarrinhoNaoEfetivadoPorCliente(Integer id) throws Exception {
        criarTabelas();
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM carrinhos WHERE cliente_id = ? AND efetivado = false";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrinho carrinho = new Carrinho(rs.getInt("id"));
                carrinho.setClienteId(rs.getInt("cliente_id"));
                carrinho.setItens(buscarItensDoCarrinho(carrinho.getId()));
                carrinho.setTotal(rs.getDouble("total"));
                carrinho.setTempo(rs.getTime("tempo"));
                carrinho.setEfetivado(rs.getBoolean("efetivado"));
                return carrinho;
            } else {
                return null;
            }
        }
    }

    private static List<Item> buscarItensDoCarrinho(Integer id) throws Exception {
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM itens WHERE carrinho_id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Item> itens = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item(rs.getInt("id"), rs.getInt("quantidade"), ProdutoDAO.buscarVinhoPorId(rs.getInt("produto_id")));
                itens.add(item);
            }
            return itens;
        }
    }

    public static Carrinho buscarCarrinhoPorId (Integer carrinhoId) {
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM carrinhos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, carrinhoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrinho carrinho = new Carrinho(rs.getInt("id"));
                carrinho.setClienteId(rs.getInt("cliente_id"));
                carrinho.setItens(buscarItensDoCarrinho(carrinhoId));
                carrinho.setTotal(rs.getDouble("total"));
                carrinho.setTempo(rs.getTime("tempo"));
                carrinho.setEfetivado(rs.getBoolean("efetivado"));
                return carrinho;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Carrinho> buscarCarrinhosFinalizados() {
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM carrinhos WHERE efetivado = true";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Carrinho> carrinhos = new ArrayList<>();
            while (rs.next()) {
                Carrinho carrinho = new Carrinho(rs.getInt("id"));
                carrinho.setClienteId(rs.getInt("cliente_id"));
                carrinho.setItens(buscarItensDoCarrinho(carrinho.getId()));
                carrinho.setTotal(rs.getDouble("total"));
                carrinho.setTempo(rs.getTime("tempo"));
                carrinho.setEfetivado(rs.getBoolean("efetivado"));
                carrinhos.add(carrinho);
            }
            return carrinhos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Carrinho> buscarCarrinhosVencidos() {
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM carrinhos WHERE efetivado = false AND (tempo + INTERVAL '10 minutes') < CURRENT_TIME";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Carrinho> carrinhos = new ArrayList<>();
            while (rs.next()) {
                Carrinho carrinho = new Carrinho(rs.getInt("id"));
                carrinho.setClienteId(rs.getInt("cliente_id"));
                carrinho.setItens(buscarItensDoCarrinho(carrinho.getId()));
                carrinho.setTotal(rs.getDouble("total"));
                carrinho.setTempo(rs.getTime("tempo"));
                carrinho.setEfetivado(rs.getBoolean("efetivado"));
                carrinhos.add(carrinho);
            }
            return carrinhos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void criarTabelas() {
        try (Connection conexao = getConexao()) {
            String sql = """
                            CREATE TABLE IF NOT EXISTS carrinhos (
                            id SERIAL PRIMARY KEY,
                            cliente_id INTEGER NOT NULL,
                            total DOUBLE PRECISION,
                            tempo TIME NOT NULL,
                            efetivado BOOLEAN NOT NULL
                            );

                    CREATE TABLE IF NOT EXISTS itens (
                            id SERIAL PRIMARY KEY,
                            carrinho_id INTEGER NOT NULL,
                            produto_id INTEGER NOT NULL,
                            quantidade INTEGER NOT NULL
                    );
                    """;
            conexao.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
