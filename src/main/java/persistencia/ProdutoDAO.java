package persistencia;

import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.produto.*;
import dominio.estoque.*;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutoDAO extends ConexaoBD implements IDAO {


    public static Vinho buscarPorId(int id) {
        Vinho vinho = null;

        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM vinhos WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                vinho = new Vinho(rs.getInt("id"));
                vinho.setNome(rs.getString("nome"));
                vinho.setSafra(rs.getInt("safra"));
                vinho.setTeorAlc(rs.getDouble("teor_alcoolico"));
                vinho.setDescricao(rs.getString("descricao"));
                vinho.setTipoVinho(TpVinho.valueOf(rs.getString("tipo_vinho")));
                List<TpUva> tiposUva = Arrays.stream(rs.getString("tipo_uva").split(","))
                        .map(String::trim)
                        .map(nomeUva -> nomeUva.replaceAll("[\\[\\]]", "")) // Remove colchetes
                        .map(TpUva::valueOf)
                        .collect(Collectors.toList());
                vinho.setTipoUva(tiposUva);
                vinho.setPais(Pais.valueOf(rs.getString("pais")));
                vinho.setMaiorCusto(rs.getDouble("maior_custo"));
                vinho.setPreco(rs.getDouble("preco"));
                vinho.setQtdeEstoque(rs.getInt("qtde_estoque"));
                vinho.setGrupoPrecificacao(Precificacao.valueOf(rs.getString("grupo_precificacao")));
                vinho.setCodBarras(rs.getString("cod_barras"));
                vinho.setVolume(rs.getInt("volume"));
                vinho.setStatus(rs.getBoolean("status"));
                vinho.setMotivo(new Motivo(MotivoCategoria.valueOf(rs.getString("motivoCategoria")),
                        rs.getString("justificativa")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vinho;
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws Exception {
        criarTabela();
        Vinho vinho = (Vinho) entidade;
        try (Connection conexao = getConexao()) {
            String query = "INSERT INTO vinhos (nome, safra, teor_alcoolico, descricao, tipo_vinho, tipo_uva, pais, " +
                    "maior_custo, preco, qtde_estoque, grupo_precificacao, cod_barras, volume, status, motivoCategoria, Justificativa)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement sql = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sql.setString(1, vinho.getNome());
            sql.setInt(2, vinho.getSafra());
            sql.setDouble(3, vinho.getTeorAlc());
            sql.setString(4, vinho.getDescricao());
            sql.setString(5, vinho.getTipoVinho().toString());
            sql.setString(6, vinho.getTipoUva().toString());
            sql.setString(7, vinho.getPais().toString());
            sql.setDouble(8, vinho.getMaiorCusto());
            sql.setDouble(9, vinho.getPreco());
            sql.setInt(10, 0);
            sql.setString(11, vinho.getGrupoPrecificacao().toString());
            sql.setString(12, vinho.getCodBarras());
            sql.setInt(13, vinho.getVolume());
            sql.setBoolean(14, vinho.getStatus());
            sql.setString(15, vinho.getMotivo().getCategoria().toString());
            sql.setString(16, vinho.getMotivo().getJustificativa());
            sql.executeUpdate();

            try (ResultSet rs = sql.getGeneratedKeys()) {
                if (rs.next()) {
                    vinho.setId(rs.getInt(1));
                }
            }

        }
    }


    @Override
    public void alterar(EntidadeDominio entidade) throws Exception {
        Vinho vinho = (Vinho) entidade;

        try (Connection conexao = getConexao()) {
            String query = "UPDATE vinhos SET nome = ?, safra = ?, teor_alcoolico = ?, descricao = ?, tipo_vinho = ?, tipo_uva = ?, pais = ?, " +
                    "maior_custo = ?, preco = ?, qtde_estoque = ?, grupo_precificacao = ?, cod_barras = ?, volume = ?, status = ?, motivoCategoria = ?, justificativa = ?" +
                    "WHERE id = ?";

            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setString(1, vinho.getNome());
            sql.setInt(2, vinho.getSafra());
            sql.setDouble(3, vinho.getTeorAlc());
            sql.setString(4, vinho.getDescricao());
            sql.setString(5, vinho.getTipoVinho().toString());
            sql.setString(6, vinho.getTipoUva().toString());
            sql.setString(7, vinho.getPais().toString());
            sql.setDouble(8, vinho.getMaiorCusto());
            sql.setDouble(9, vinho.getPreco());
            sql.setInt(10, vinho.getQtdeEstoque());
            sql.setString(11, vinho.getGrupoPrecificacao().toString());
            sql.setString(12, vinho.getCodBarras());
            sql.setInt(13, vinho.getVolume());
            sql.setBoolean(14, vinho.getStatus());
            sql.setString(15, vinho.getMotivo().getCategoria().toString());
            sql.setString(16, vinho.getMotivo().getJustificativa());
            sql.setInt(17, vinho.getId());
            sql.executeUpdate();
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws Exception {
        Vinho vinho = (Vinho) entidade;

        try (Connection conexao = getConexao()) {
            String query = "DELETE FROM vinhos WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, vinho.getId());
            sql.executeUpdate();
            removerEstoque(vinho.getId(), conexao);
        }


    }

    private void removerEstoque(int vinhoId, Connection conexao) throws SQLException {
        String query = "DELETE FROM estoque WHERE vinhoId = ?";
        PreparedStatement sql = conexao.prepareStatement(query);
        sql.setInt(1, vinhoId);
        sql.executeUpdate();
    }


    private void criarTabela() throws SQLException {
        try (Connection conexao = getConexao()) {
            String query = """
        
                    CREATE TABLE IF NOT EXISTS vinhos (
            id BIGSERIAL PRIMARY KEY,
            nome VARCHAR(100),
            safra INTEGER,
            teor_alcoolico DOUBLE PRECISION,
            descricao TEXT,
            tipo_vinho VARCHAR(50),
            tipo_uva VARCHAR(255), -- Usando VARCHAR para armazenar uma lista de tipos de uva, separadas por vírgula
            pais VARCHAR(50),
            maior_custo DECIMAL(10, 2),
            preco DECIMAL(10, 2),
            qtde_estoque INTEGER,
            grupo_precificacao VARCHAR(50),
            cod_barras VARCHAR(13),
            volume INTEGER,
            status BOOLEAN,
            motivoCategoria VARCHAR(255) NOT NULL,    
            justificativa TEXT NOT NULL,
            dtSemEstoque TIMESTAMP
        );
        """;
                try (PreparedStatement stmt = conexao.prepareStatement(query)) {
                    stmt.executeUpdate();
                }
            }
        }


        public static List<Vinho> listar() throws Exception {
            List<Vinho> vinhos = new ArrayList<>();

            try (Connection conexao = getConexao()) {
                String query = "SELECT * FROM vinhos WHERE status = true";
                PreparedStatement sql = conexao.prepareStatement(query);
                ResultSet rs = sql.executeQuery();
                while (rs.next()) {
                    Vinho vinho = new Vinho(rs.getInt("id"));
                    vinho.setNome(rs.getString("nome"));
                    vinho.setSafra(rs.getInt("safra"));
                    vinho.setTeorAlc(rs.getDouble("teor_alcoolico"));
                    vinho.setDescricao(rs.getString("descricao"));
                    vinho.setTipoVinho(TpVinho.valueOf(rs.getString("tipo_vinho")));
                    List<TpUva> tiposUva = Arrays.stream(rs.getString("tipo_uva").split(","))
                            .map(String::trim)
                            .map(nomeUva -> nomeUva.replaceAll("[\\[\\]]", "")) // Remove colchetes
                            .map(TpUva::valueOf)
                            .collect(Collectors.toList());
                    vinho.setTipoUva(tiposUva);
                    vinho.setPais(Pais.valueOf(rs.getString("pais")));
                    vinho.setMaiorCusto(rs.getDouble("maior_custo"));
                    vinho.setPreco(rs.getDouble("preco"));
                    vinho.setQtdeEstoque(rs.getInt("qtde_estoque"));
                    vinho.setGrupoPrecificacao(Precificacao.valueOf(rs.getString("grupo_precificacao")));
                    vinho.setCodBarras(rs.getString("cod_barras"));
                    vinho.setVolume(rs.getInt("volume"));
                    vinho.setStatus(rs.getBoolean("status"));
                    vinho.setMotivo(new Motivo(MotivoCategoria.valueOf(rs.getString("motivoCategoria")), rs.getString("justificativa")));
                    vinhos.add(vinho);
                }
            }
            Collections.sort(vinhos, Comparator.comparing(v -> v.getNome()));
            return vinhos;
        }

    public static List<Vinho> listar(String filtro) throws Exception {
        List<Vinho> vinhos = new ArrayList<>();

        try (Connection conexao = getConexao()) {
            String[] palavrasFiltro = filtro.toLowerCase().split("\\s+");
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM vinhos WHERE ");

            for (int i = 0; i < palavrasFiltro.length; i++) {
                queryBuilder.append("(LOWER(nome) ILIKE ? OR ")
                        .append("CAST(safra AS TEXT) ILIKE ? OR ")
                        .append("CAST(teor_alcoolico AS TEXT) ILIKE ? OR ")
                        .append("LOWER(descricao) ILIKE ? OR ")
                        .append("LOWER(tipo_vinho) ILIKE ? OR ")
                        .append("LOWER(tipo_uva) ILIKE ? OR ")
                        .append("LOWER(pais) ILIKE ? OR ")
                        .append("CAST(preco AS TEXT) ILIKE ? OR ")
                        .append("LOWER(grupo_precificacao) ILIKE ? OR ")
                        .append("LOWER(cod_barras) ILIKE ? OR ")
                        .append("CAST(volume AS TEXT) ILIKE ? OR ")
                        .append("CAST(status AS TEXT) ILIKE ? OR ")
                        .append("LOWER(motivoCategoria) ILIKE ? OR ")
                        .append("LOWER(justificativa) ILIKE ?)");
                if (i < palavrasFiltro.length - 1) {
                    queryBuilder.append(" AND ");
                }
            }

            PreparedStatement sql = conexao.prepareStatement(queryBuilder.toString());

            int paramIndex = 1;
            for (String palavra : palavrasFiltro) {
                String filtroLike = "%" + palavra + "%";
                for (int j = 0; j < 14; j++) {
                    sql.setString(paramIndex++, filtroLike);
                }
            }

            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Vinho vinho = new Vinho(rs.getInt("id"));
                vinho.setNome(rs.getString("nome"));
                vinho.setSafra(rs.getInt("safra"));
                vinho.setTeorAlc(rs.getDouble("teor_alcoolico"));
                vinho.setDescricao(rs.getString("descricao"));
                vinho.setTipoVinho(TpVinho.valueOf(rs.getString("tipo_vinho")));
                List<TpUva> tiposUva = Arrays.stream(rs.getString("tipo_uva").split(","))
                        .map(String::trim)
                        .map(nomeUva -> nomeUva.replaceAll("[\\[\\]]", "")) // Remove colchetes
                        .map(TpUva::valueOf)
                        .collect(Collectors.toList());
                vinho.setTipoUva(tiposUva);
                vinho.setPais(Pais.valueOf(rs.getString("pais")));
                vinho.setMaiorCusto(rs.getDouble("maior_custo"));
                vinho.setPreco(rs.getDouble("preco"));
                vinho.setQtdeEstoque(rs.getInt("qtde_estoque"));
                vinho.setGrupoPrecificacao(Precificacao.valueOf(rs.getString("grupo_precificacao")));
                vinho.setCodBarras(rs.getString("cod_barras"));
                vinho.setVolume(rs.getInt("volume"));
                vinho.setStatus(rs.getBoolean("status"));
                vinho.setMotivo(new Motivo(MotivoCategoria.valueOf(rs.getString("motivoCategoria")), rs.getString("justificativa")));
                vinhos.add(vinho);
            }
        }
        Collections.sort(vinhos, Comparator.comparing(v -> v.getNome()));
        return vinhos;
    }

    public static Vinho buscarVinhoPorId (Integer id) throws Exception {
        Vinho vinho = null;
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM vinhos WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                vinho = new Vinho(rs.getInt("id"));
                vinho.setNome(rs.getString("nome"));
                vinho.setSafra(rs.getInt("safra"));
                vinho.setTeorAlc(rs.getDouble("teor_alcoolico"));
                vinho.setDescricao(rs.getString("descricao"));
                vinho.setTipoVinho(TpVinho.valueOf(rs.getString("tipo_vinho")));
                List<TpUva> tiposUva = Arrays.stream(rs.getString("tipo_uva").split(","))
                        .map(String::trim)
                        .map(nomeUva -> nomeUva.replaceAll("[\\[\\]]", "")) // Remove colchetes
                        .map(TpUva::valueOf)
                        .collect(Collectors.toList());
                vinho.setTipoUva(tiposUva);
                vinho.setPais(Pais.valueOf(rs.getString("pais")));
                vinho.setMaiorCusto(rs.getDouble("maior_custo"));
                vinho.setPreco(rs.getDouble("preco"));
                vinho.setQtdeEstoque(rs.getInt("qtde_estoque"));
                vinho.setGrupoPrecificacao(Precificacao.valueOf(rs.getString("grupo_precificacao")));
                vinho.setCodBarras(rs.getString("cod_barras"));
                vinho.setVolume(rs.getInt("volume"));
                vinho.setStatus(rs.getBoolean("status"));
                vinho.setMotivo(new Motivo(MotivoCategoria.valueOf(rs.getString("motivoCategoria")), rs.getString("justificativa")));
            }
        }
        return vinho;
    }
}
