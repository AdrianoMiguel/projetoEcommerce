package persistencia;

import dominio.EntidadeDominio;
import dominio.cliente.*;
import dominio.compra.*;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class CompraDAO extends ConexaoBD implements IDAO {

    @Override
    public void salvar(EntidadeDominio var1) throws Exception {
        criarTabelas();
        Compra compra = (Compra) var1;
        try (Connection conexao = getConexao()) {
            String sql = "INSERT INTO compras (cliente_id, status, data_hora, carrinho_id," +
                    "endereco_tipoResid, endereco_tipolograd, endereco_logradouro," +
                    " endereco_numero, endereco_bairro, endereco_cep, endereco_cidade," +
                    " endereco_estado, endereco_pais, endereco_obs, frete_valor, frete_prazo, valor_final) VALUES (?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, compra.getClienteId());
            ps.setString(2, compra.getStatus().toString());
            ps.setTimestamp(3, compra.getDataHora());
            ps.setInt(4, compra.getCarrinho().getId());
            ps.setString(5, compra.getEnderecoEntrega().getTipoResid().toString());
            ps.setString(6, compra.getEnderecoEntrega().getTipoLograd().toString());
            ps.setString(7, compra.getEnderecoEntrega().getLogradouro());
            ps.setString(8, compra.getEnderecoEntrega().getNumero().toString());
            ps.setString(9, compra.getEnderecoEntrega().getBairro().getNome());
            ps.setString(10, compra.getEnderecoEntrega().getCep());
            ps.setString(11, compra.getEnderecoEntrega().getBairro().getCidade().getNome());
            ps.setString(12, compra.getEnderecoEntrega().getBairro().getCidade().getEstado().getNome());
            ps.setString(13, compra.getEnderecoEntrega().getBairro().getCidade().getEstado().getPais().getNome());
            ps.setString(14, compra.getEnderecoEntrega().getObs());
            ps.setDouble(15, compra.getFrete().getValor());
            ps.setString(16, compra.getFrete().getPrazo());
            ps.setDouble(17, compra.getValorFinal());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                compra.setId(generatedKeys.getInt(1));
                if (compra.getPagamentos() != null) {
                    for (Pagamento pagamento : compra.getPagamentos()) {
                        salvarPagamento(pagamento, compra.getId(), conexao);
                    }
                }
                if (compra.getCupons() != null) {
                    for (Cupom cupom : compra.getCupons()) {
                        salvarCupomUtilizado(cupom, compra.getId(), conexao);
                    }
                }
            }
        }
    }

    private void salvarPagamento(Pagamento pagamento, int compraId, Connection conexao) throws SQLException {
        String sql = "INSERT INTO pagamentos (compra_id, cartao_nome, cartao_bandeira, cartao_numero, cartao_cvv, valor) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, compraId);
        ps.setString(2, pagamento.getCartao().getNome());
        ps.setString(3, pagamento.getCartao().getBandeira().toString());
        ps.setString(4, pagamento.getCartao().getNumero());
        ps.setString(5, pagamento.getCartao().getCod().toString());
        ps.setDouble(6, pagamento.getValor());
        ps.execute();

    }

    private void salvarCupomUtilizado(Cupom cupom, int compraId, Connection conexao) throws SQLException {
        String sql = "INSERT INTO cuponsUtilizados (compra_id, codigo, valor, utilizado) VALUES (?,?,?,?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, compraId);
        ps.setString(2, cupom.getCodigo());
        ps.setDouble(3, cupom.getValor());
        ps.setBoolean(4, cupom.getUtilizado());
        ps.execute();
    }

    private static List<Pagamento> buscarPagamentosPorCompraId(int compraId) {
        List<Pagamento> pagamentos = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM pagamentos WHERE compra_id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, compraId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cartao cartao = new Cartao(rs.getString("cartao_nome"), Bandeira.valueOf(rs.getString("cartao_bandeira")),
                        rs.getString("cartao_numero"), Integer.valueOf(rs.getString("cartao_cvv")), false);
                Pagamento pagamento = new Pagamento(cartao, rs.getDouble("valor"));
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }

    private static List<Cupom> buscarCuponsPorCompraId(int compraId) {
        List<Cupom> cupons = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM cuponsUtilizados WHERE compra_id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, compraId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cupom cupom = new Cupom(rs.getInt("id"), rs.getInt("compra_id"),
                        rs.getString("codigo"), rs.getDouble("valor"), rs.getBoolean("utilizado"));
                cupons.add(cupom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cupons;
    }

    @Override
    public void alterar(EntidadeDominio var1) throws Exception {
        Compra compra = (Compra) var1;
        try (Connection conexao = getConexao()) {
            String sql = "UPDATE compras SET status = ? WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, compra.getStatus().toString());
            ps.setInt(2, compra.getId());
            ps.execute();
        }

    }

    @Override
    public void excluir(EntidadeDominio var1) throws Exception {

    }

    private void criarTabelas() {
        try (Connection conexao = getConexao()) {
            String sql = "CREATE TABLE IF NOT EXISTS compras (" +
                    "id SERIAL PRIMARY KEY," +
                    "cliente_id INTEGER NOT NULL," +
                    "status VARCHAR(255) NOT NULL," +
                    "data_hora TIMESTAMP NOT NULL," +
                    "carrinho_id INTEGER NOT NULL," +
                    "endereco_tipoResid VARCHAR(255) NOT NULL," +
                    "endereco_tipolograd VARCHAR(255) NOT NULL," +
                    "endereco_logradouro VARCHAR(255) NOT NULL," +
                    "endereco_numero VARCHAR(255) NOT NULL," +
                    "endereco_bairro VARCHAR(255) NOT NULL," +
                    "endereco_cep VARCHAR(255) NOT NULL," +
                    "endereco_cidade VARCHAR(255) NOT NULL," +
                    "endereco_estado VARCHAR(255) NOT NULL," +
                    "endereco_pais VARCHAR(255) NOT NULL," +
                    "endereco_obs VARCHAR(255)," +
                    "frete_valor DOUBLE PRECISION NOT NULL," +
                    "frete_prazo VARCHAR(255) NOT NULL," +
                    "valor_final DOUBLE PRECISION NOT NULL" +
                    ")";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.execute();

            sql = "CREATE TABLE IF NOT EXISTS pagamentos (" +
                    "id SERIAL PRIMARY KEY," +
                    "compra_id INTEGER NOT NULL," +
                    "cartao_nome VARCHAR(255) NOT NULL," +
                    "cartao_bandeira VARCHAR(20) NOT NULL," +
                    "cartao_numero VARCHAR(16) NOT NULL," +
                    "cartao_cvv VARCHAR(3) NOT NULL," +
                    "valor DOUBLE PRECISION NOT NULL" +
                    ")";
            ps = conexao.prepareStatement(sql);
            ps.execute();

            sql = "CREATE TABLE IF NOT EXISTS cuponsUtilizados (" +
                    "id SERIAL PRIMARY KEY," +
                    "compra_id INTEGER NOT NULL," +
                    "codigo VARCHAR(255) NOT NULL," +
                    "valor DOUBLE PRECISION NOT NULL," +
                    "utilizado BOOLEAN NOT NULL" +
                    ")";

            ps = conexao.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static List<Compra> listarPorClienteId(Integer clienteId) {
        List<Compra> compras = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM compras WHERE cliente_id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra(rs.getInt("id"), rs.getInt("cliente_id"),
                        Status.valueOf(rs.getString("status")), CarrinhoDAO.buscarCarrinhoPorId(rs.getInt("carrinho_id")),
                        buscarPagamentosPorCompraId(rs.getInt("id")),
                        new Endereco("Endereco de Entrega", TpResidencia.valueOf(rs.getString("endereco_tipoResid")),
                                TpLogradouro.valueOf(rs.getString("endereco_tipolograd")),
                                rs.getString("endereco_logradouro"),
                                Integer.valueOf(rs.getString("endereco_numero")),
                                new Bairro(rs.getString("endereco_bairro"),
                                        new Cidade(rs.getString("endereco_cidade"),
                                                new Estado(rs.getString("endereco_estado"),
                                                        new Pais(rs.getString("endereco_pais"))))),
                                rs.getString("endereco_cep"),
                                rs.getString("endereco_obs")),
                        new Frete(rs.getDouble("frete_valor"), rs.getString("frete_prazo")));
                compra.setDataHora(rs.getTimestamp("data_hora"));
                compra.setCupons(buscarCuponsPorCompraId(rs.getInt("id")));
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        Collections.sort(compras, Comparator.comparing(Compra::getDataHora).reversed());
        return compras;
    }


    public static List<Compra> listar() {
        List<Compra> compras = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM compras";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra(rs.getInt("id"), rs.getInt("cliente_id"),
                        Status.valueOf(rs.getString("status")), CarrinhoDAO.buscarCarrinhoPorId(rs.getInt("carrinho_id")),
                        buscarPagamentosPorCompraId(rs.getInt("id")),
                        new Endereco("Endereco de Entrega", TpResidencia.valueOf(rs.getString("endereco_tipoResid")),
                                TpLogradouro.valueOf(rs.getString("endereco_tipolograd")),
                                rs.getString("endereco_logradouro"),
                                Integer.valueOf(rs.getString("endereco_numero")),
                                new Bairro(rs.getString("endereco_bairro"),
                                        new Cidade(rs.getString("endereco_cidade"),
                                                new Estado(rs.getString("endereco_estado"),
                                                        new Pais(rs.getString("endereco_pais"))))),
                                rs.getString("endereco_cep"),
                                rs.getString("endereco_obs")),
                        new Frete(rs.getDouble("frete_valor"), rs.getString("frete_prazo")));
                compra.setDataHora(rs.getTimestamp("data_hora"));
                compra.setCupons(buscarCuponsPorCompraId(rs.getInt("id")));
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        Collections.sort(compras, Comparator.comparing(Compra::getDataHora).reversed());
        return compras;
    }

    public static List<Compra> listarPedidosDeTroca() {
        List<dominio.compra.Compra> compras = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM compras WHERE status = 'EM_TROCA' OR status = 'TROCADO'";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dominio.compra.Compra compra = new dominio.compra.Compra(rs.getInt("id"), rs.getInt("cliente_id"),
                        Status.valueOf(rs.getString("status")), CarrinhoDAO.buscarCarrinhoPorId(rs.getInt("carrinho_id")),
                        buscarPagamentosPorCompraId(rs.getInt("id")),
                        new Endereco("Endereco de Entrega", TpResidencia.valueOf(rs.getString("endereco_tipoResid")),
                                TpLogradouro.valueOf(rs.getString("endereco_tipolograd")),
                                rs.getString("endereco_logradouro"),
                                Integer.valueOf(rs.getString("endereco_numero")),
                                new Bairro(rs.getString("endereco_bairro"),
                                        new Cidade(rs.getString("endereco_cidade"),
                                                new Estado(rs.getString("endereco_estado"),
                                                        new Pais(rs.getString("endereco_pais"))))),
                                rs.getString("endereco_cep"),
                                rs.getString("endereco_obs")),
                        new Frete(rs.getDouble("frete_valor"), rs.getString("frete_prazo")));
                compra.setDataHora(rs.getTimestamp("data_hora"));
                compra.setCupons(buscarCuponsPorCompraId(rs.getInt("id")));
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        Collections.sort(compras, Comparator.comparing(dominio.compra.Compra::getDataHora).reversed());
        return compras;
    }

    public static Compra buscarCompraPorId(Integer compraId) {
        try (Connection conexao = getConexao()) {
            String sql = "SELECT * FROM compras WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, compraId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Compra compra = new Compra(rs.getInt("id"), rs.getInt("cliente_id"),
                        Status.valueOf(rs.getString("status")), CarrinhoDAO.buscarCarrinhoPorId(rs.getInt("carrinho_id")),
                        buscarPagamentosPorCompraId(rs.getInt("id")),
                        new Endereco("Endereco de Entrega", TpResidencia.valueOf(rs.getString("endereco_tipoResid")),
                                TpLogradouro.valueOf(rs.getString("endereco_tipolograd")),
                                rs.getString("endereco_logradouro"),
                                Integer.valueOf(rs.getString("endereco_numero")),
                                new Bairro(rs.getString("endereco_bairro"),
                                        new Cidade(rs.getString("endereco_cidade"),
                                                new Estado(rs.getString("endereco_estado"),
                                                        new Pais(rs.getString("endereco_pais"))))),
                                rs.getString("endereco_cep"),
                                rs.getString("endereco_obs")),
                        new Frete(rs.getDouble("frete_valor"), rs.getString("frete_prazo")));
                compra.setDataHora(rs.getTimestamp("data_hora"));
                compra.setCupons(buscarCuponsPorCompraId(rs.getInt("id")));
                return compra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }
}

