package persistencia;

import dominio.cliente.*;
import dominio.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class ClienteDAO extends ConexaoBD implements IDAO {

    @Override
    // Salvar Cliente
    public void salvar(EntidadeDominio entidade) throws Exception {
        criarTabela();
        Cliente cliente = (Cliente) entidade;

        try (Connection conexao = getConexao()) {
            String query = "INSERT INTO clientes (nome, cpf, genero, dataNascimento, endResid_tiporesid, endResid_tipolograd, endResid_logradouro, endResid_numero, " +
                    "endResid_bairro, endResid_cidade, endResid_estado, endResid_pais, endResid_cep, endResid_obs, " +
                    "endCob_tiporesid, endCob_tipolograd, endCob_logradouro, endCob_numero, endCob_bairro, endCob_cidade, endCob_estado, endCob_pais, endCob_cep, endCob_obs, " +
                    "contato_email, contato_tipotel, contato_ddd, contato_numerotel,senha,status,ranking,criptografia) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement sql = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getGenero().toString());
            sql.setDate(4, new java.sql.Date(cliente.getDataNascimento().getTime()));
            // Endereço Residencial
            sql.setString(5, cliente.getEndResid().getTipoResid().toString());
            sql.setString(6, cliente.getEndResid().getTipoLograd().toString());
            sql.setString(7, cliente.getEndResid().getLogradouro());
            sql.setInt(8, cliente.getEndResid().getNumero());
            sql.setString(9, cliente.getEndResid().getBairro().getNome());
            sql.setString(10, cliente.getEndResid().getBairro().getCidade().getNome());
            sql.setString(11, cliente.getEndResid().getBairro().getCidade().getEstado().getNome());
            sql.setString(12, cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
            sql.setString(13, cliente.getEndResid().getCep());
            sql.setString(14, cliente.getEndResid().getObs());
            // Endereço de Cobrança
            sql.setString(15, cliente.getEndCob().getTipoResid().toString());
            sql.setString(16, cliente.getEndCob().getTipoLograd().toString());
            sql.setString(17, cliente.getEndCob().getLogradouro());
            sql.setInt(18, cliente.getEndCob().getNumero());
            sql.setString(19, cliente.getEndCob().getBairro().getNome());
            sql.setString(20, cliente.getEndCob().getBairro().getCidade().getNome());
            sql.setString(21, cliente.getEndCob().getBairro().getCidade().getEstado().getNome());
            sql.setString(22, cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
            sql.setString(23, cliente.getEndCob().getCep());
            sql.setString(24, cliente.getEndCob().getObs());
            // Contato
            sql.setString(25, cliente.getContato().getEmail());
            sql.setString(26, cliente.getContato().getTelefone().getTipo().toString());
            sql.setInt(27, cliente.getContato().getTelefone().getDdd());
            sql.setString(28, cliente.getContato().getTelefone().getNumero().toString());

            sql.setString(29, cliente.getSenha());
            sql.setBoolean(30,cliente.getStatus());
            sql.setInt(31,cliente.getRanking());
            sql.setBoolean(32,cliente.getSenhaFoiCriptografada());
            sql.executeUpdate();

            // Salvando os cartões e endereços de entrega
            ResultSet generatedKeys = sql.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1));
                for (Cartao cartao : cliente.getCartoes()) {
                    salvarCartao(cartao, cliente.getId(), conexao);
                }
                for (Endereco endereco_entrega : cliente.getEndEnt()) {
                    salvarEnderecoEntrega(endereco_entrega, cliente.getId(), conexao);
                }
            }
        }
    }

    // Salvar Cartões
    private void salvarCartao(Cartao cartao, int clienteId, Connection conexao) throws SQLException {
        String query = "INSERT INTO cartoes (nome, bandeira, numero, codSeguranca, principal, clienteId) VALUES (?,?,?,?,?,?)";
        PreparedStatement sql = conexao.prepareStatement(query);
        sql.setString(1, cartao.getNome());
        sql.setString(2, cartao.getBandeira().toString());
        sql.setString(3, cartao.getNumero());
        sql.setInt(4, cartao.getCod());
        sql.setBoolean(5, cartao.getPrincipal());
        sql.setInt(6, clienteId);
        sql.executeUpdate();
    }
    // Salvar Endereços de entrega
    private void salvarEnderecoEntrega(Endereco endereco, int clienteId, Connection conexao) throws SQLException {
        String query = "INSERT INTO enderecos_entrega (tiporesid, tipolograd, logradouro, numero, bairro, cidade, estado, pais, cep, obs, clienteId,nome,principal) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement sql = conexao.prepareStatement(query);
        sql.setString(1, endereco.getTipoResid().toString());
        sql.setString(2, endereco.getTipoLograd().toString());
        sql.setString(3, endereco.getLogradouro());
        sql.setInt(4, endereco.getNumero());
        sql.setString(5, endereco.getBairro().getNome());
        sql.setString(6, endereco.getBairro().getCidade().getNome());
        sql.setString(7, endereco.getBairro().getCidade().getEstado().getNome());
        sql.setString(8, endereco.getBairro().getCidade().getEstado().getPais().getNome());
        sql.setString(9, endereco.getCep());
        sql.setString(10, endereco.getObs());
        sql.setInt(11, clienteId);
        sql.setString(12, endereco.getNome());
        sql.setBoolean(13,endereco.getPrincipal());
        sql.executeUpdate();
    }

    @Override
    // Alterar cadastro de Cliente
    public void alterar(EntidadeDominio entidade) throws Exception {
        Cliente cliente = (Cliente) entidade;

        try (Connection conexao = getConexao()) {
            String query = "UPDATE clientes SET nome=?, cpf=?, genero=?, dataNascimento=?, " +
                    "endResid_tiporesid=?, endResid_tipolograd=?, endResid_logradouro=?, endResid_numero=?, " +
                    "endResid_bairro=?, endResid_cidade=?, endResid_estado=?, endResid_pais=?, endResid_cep=?, endResid_obs=?, " +
                    "endCob_tiporesid=?, endCob_tipolograd=?, endCob_logradouro=?, endCob_numero=?, endCob_bairro=?, " +
                    "endCob_cidade=?, endCob_estado=?, endCob_pais=?, endCob_cep=?, endCob_obs=?, " +
                    "contato_email=?, contato_tipotel=?, contato_ddd=?, contato_numerotel=?, senha=?, status=?, ranking=? WHERE id=?";

            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getGenero().toString());
            sql.setDate(4, new java.sql.Date(cliente.getDataNascimento().getTime()));
            // Endereço Residencial
            sql.setString(5, cliente.getEndResid().getTipoResid().toString());
            sql.setString(6, cliente.getEndResid().getTipoLograd().toString());
            sql.setString(7, cliente.getEndResid().getLogradouro());
            sql.setInt(8, cliente.getEndResid().getNumero());
            sql.setString(9, cliente.getEndResid().getBairro().getNome());
            sql.setString(10, cliente.getEndResid().getBairro().getCidade().getNome());
            sql.setString(11, cliente.getEndResid().getBairro().getCidade().getEstado().getNome());
            sql.setString(12, cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome());
            sql.setString(13, cliente.getEndResid().getCep());
            sql.setString(14, cliente.getEndResid().getObs());
            // Endereço de Cobrança
            sql.setString(15, cliente.getEndCob().getTipoResid().toString());
            sql.setString(16, cliente.getEndCob().getTipoLograd().toString());
            sql.setString(17, cliente.getEndCob().getLogradouro());
            sql.setInt(18, cliente.getEndCob().getNumero());
            sql.setString(19, cliente.getEndCob().getBairro().getNome());
            sql.setString(20, cliente.getEndCob().getBairro().getCidade().getNome());
            sql.setString(21, cliente.getEndCob().getBairro().getCidade().getEstado().getNome());
            sql.setString(22, cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
            sql.setString(23, cliente.getEndCob().getCep());
            sql.setString(24, cliente.getEndCob().getObs());
            // Contato
            sql.setString(25, cliente.getContato().getEmail());
            sql.setString(26, cliente.getContato().getTelefone().getTipo().toString());
            sql.setInt(27, cliente.getContato().getTelefone().getDdd());
            sql.setString(28, cliente.getContato().getTelefone().getNumero().toString());
            sql.setString(29, cliente.getSenha());
            sql.setBoolean(30,cliente.getStatus());

            sql.setInt(31,cliente.getRanking());
            sql.setInt(32, cliente.getId());
            sql.executeUpdate();

            // Atualizar cartões
            removerCartoes(cliente.getId(), conexao);
            for (Cartao cartao : cliente.getCartoes()) {
                salvarCartao(cartao, cliente.getId(), conexao);
            }

            // Atualizar enderecos de entrega
            removerEnderecosEntrega(cliente.getId(), conexao);
            for (Endereco endereco : cliente.getEndEnt()) {
                salvarEnderecoEntrega(endereco, cliente.getId(), conexao);
            }
        }
    }

    // Remover cartões ao alterar o cliente
    private void removerCartoes(int clienteId, Connection conexao) throws SQLException {
        String query = "DELETE FROM cartoes WHERE clienteId = ?";
        PreparedStatement sql = conexao.prepareStatement(query);
        sql.setInt(1, clienteId);
        sql.executeUpdate();
    }
    // Remover enderecos de entrega ao alterar o cliente
    private void removerEnderecosEntrega(int clienteId, Connection conexao) throws SQLException {
        String query = "DELETE FROM enderecos_entrega WHERE clienteId = ?";
        PreparedStatement sql = conexao.prepareStatement(query);
        sql.setInt(1, clienteId);
        sql.executeUpdate();
    }

    // Consulta de Clientes
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null; // Implementar se necessário
    }

    // Buscar Cliente por ID
    public static Cliente buscarClientePorId(int id) throws Exception {
        Cliente cliente = null;

        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, id);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setGenero(Genero.valueOf(rs.getString("genero")));
                cliente.setDataNascimento(rs.getDate("datanascimento"));
                cliente.setSenha(rs.getString("senha"));
                // Endereço Residencial
                cliente.setEndResid(new Endereco("Residencial",
                        TpResidencia.valueOf(rs.getString("endResid_tiporesid")),
                        TpLogradouro.valueOf(rs.getString("endResid_tipolograd")),
                        rs.getString("endResid_logradouro"),
                        rs.getInt("endResid_numero"),
                        new Bairro(rs.getString("endResid_bairro"),
                        new Cidade (rs.getString("endResid_cidade"),
                        new Estado(rs.getString("endResid_estado"),
                        new Pais(rs.getString("endResid_pais"))))),
                        rs.getString("endResid_cep"),
                        rs.getString("endResid_obs")
                ));
                // Endereço de Cobrança
                cliente.setEndCob(new Endereco("Cobranca",
                        TpResidencia.valueOf(rs.getString("endCob_tiporesid")),
                        TpLogradouro.valueOf(rs.getString("endCob_tipolograd")),
                        rs.getString("endCob_logradouro"),
                        rs.getInt("endCob_numero"),
                        new Bairro(rs.getString("endCob_bairro"),
                        new Cidade(rs.getString("endCob_cidade"),
                        new Estado(rs.getString("endCob_estado"),
                        new Pais(rs.getString("endCob_pais"))))),
                        rs.getString("endCob_cep"),
                        rs.getString("endCob_obs")
                ));
                // Contato
                cliente.setContato(new Contato(
                        rs.getString("contato_email"),
                        new Telefone(
                                TpTelefone.valueOf(rs.getString("contato_tipotel")),
                                rs.getInt("contato_ddd"),
                                Long.valueOf(rs.getString("contato_numerotel"))
                        )
                ));

                cliente.setCartoes(buscarCartoesPorClienteId(cliente.getId()));
                cliente.setEndEnt(buscarEnderecosEntregaPorClienteId(cliente.getId()));
                cliente.setRanking(rs.getInt("ranking"));
                cliente.setStatus(rs.getBoolean("status"));
                cliente.setSenhaFoiCriptografada(rs.getBoolean("criptografia"));

                CupomDAO cupomDAO = new CupomDAO();
                List<Cupom> cupons = cupomDAO.listar(cliente.getId());
                cliente.setCupons(cupons);
            }
        }

        return cliente;
    }

    // Buscar Cartões de um Cliente
    private static List<Cartao> buscarCartoesPorClienteId(int clienteId) throws SQLException {
        List<Cartao> cartoes = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM cartoes WHERE clienteId = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, clienteId);

            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Cartao cartao = new Cartao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        Bandeira.valueOf(rs.getString("bandeira")),
                        rs.getString("numero"),
                        rs.getInt("codSeguranca"),
                        rs.getBoolean("principal")
                );
                cartoes.add(cartao);
            }
            return cartoes;
        }
    }

    public static Cartao buscarCartaoPorId(int cartaoId) throws SQLException {
        Cartao cartao = null;
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM cartoes WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, cartaoId);

            ResultSet rs = sql.executeQuery();
            if (rs.next()) {
                cartao = new Cartao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        Bandeira.valueOf(rs.getString("bandeira")),
                        rs.getString("numero"),
                        rs.getInt("codSeguranca"),
                        rs.getBoolean("principal")
                );
            }
        }
        return cartao;
    }

    private static List<Endereco> buscarEnderecosEntregaPorClienteId(int clienteId) throws SQLException {
        List<Endereco> enderecos = new ArrayList<>();
        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM enderecos_entrega WHERE clienteId = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, clienteId);

            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getString("nome"),
                        TpResidencia.valueOf(rs.getString("tiporesid")),
                        TpLogradouro.valueOf(rs.getString("tipolograd")),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        new Bairro(rs.getString("bairro"),
                                new Cidade(rs.getString("cidade"),
                                        new Estado(rs.getString("estado"),
                                                new Pais(rs.getString("pais"))))),
                        rs.getString("cep"),
                        rs.getString("obs")
                );
                endereco.setPrincipal(rs.getBoolean("principal"));
                enderecos.add(endereco);
            }
            return enderecos;
        }
    }
    // Criar tabela de Clientes
    private void criarTabela() throws SQLException {
        try (Connection conexao = getConexao()) {
            String query = """
            CREATE TABLE IF NOT EXISTS clientes (
                id BIGSERIAL PRIMARY KEY,
                nome VARCHAR(100),
                cpf VARCHAR(11),
                genero VARCHAR(10),
                dataNascimento DATE,
                endResid_tiporesid VARCHAR(50),
                endResid_tipolograd VARCHAR(50),
                endResid_logradouro VARCHAR(255),
                endResid_numero INTEGER,
                endResid_bairro VARCHAR(50),
                endResid_cidade VARCHAR(50),
                endResid_estado VARCHAR(50),
                endResid_pais VARCHAR(50),
                endResid_cep VARCHAR(10),
                endResid_obs VARCHAR(255),
                endCob_tiporesid VARCHAR(50),
                endCob_tipolograd VARCHAR(50),
                endCob_logradouro VARCHAR(255),
                endCob_numero INTEGER,
                endCob_bairro VARCHAR(50),
                endCob_cidade VARCHAR(50),
                endCob_estado VARCHAR(50),
                endCob_pais VARCHAR(50),
                endCob_cep VARCHAR(11),
                endCob_obs VARCHAR(255),
                contato_email VARCHAR(100),
                contato_tipotel VARCHAR(20),
                contato_ddd INTEGER,
                contato_numerotel VARCHAR(10),
                senha VARCHAR(65),
                ranking INTEGER,
                status BOOLEAN,
                criptografia BOOLEAN
            );

            CREATE TABLE IF NOT EXISTS enderecos_entrega (
                id BIGSERIAL NOT NULL PRIMARY KEY,
                clienteId BIGINT NOT NULL REFERENCES clientes(id) ON DELETE CASCADE,
                nome VARCHAR(100),
                tiporesid VARCHAR(50),
                tipolograd VARCHAR(50),
                logradouro VARCHAR(255),
                numero INTEGER,
                bairro VARCHAR(50),
                cidade VARCHAR(50),
                estado VARCHAR(50),
                pais VARCHAR(50),
                cep VARCHAR(11),
                obs VARCHAR(255),
                principal BOOLEAN
            );

            CREATE TABLE IF NOT EXISTS cartoes (
                id BIGSERIAL NOT NULL PRIMARY KEY,
                clienteId BIGINT NOT NULL REFERENCES clientes(id) ON DELETE CASCADE,
                nome VARCHAR(255) NOT NULL,
                bandeira VARCHAR(20) NOT NULL,
                numero VARCHAR(16) NOT NULL,
                codSeguranca INTEGER NOT NULL,
                principal BOOLEAN
            );
        """;

            try (Statement sql = conexao.createStatement()) {
                sql.executeUpdate(query);
            }
        }
    }

    public static List<Cliente> listar() throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conexao = getConexao()) {
            String query = "SELECT * FROM clientes WHERE status = true";
            PreparedStatement sql = conexao.prepareStatement(query);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setGenero(Genero.valueOf(rs.getString("genero")));
                cliente.setDataNascimento(rs.getDate("dataNascimento"));
                cliente.setSenha(rs.getString("senha"));
                // Endereço Residencial
                cliente.setEndResid(new Endereco("Residencial",
                        TpResidencia.valueOf(rs.getString("endResid_tiporesid")),
                        TpLogradouro.valueOf(rs.getString("endResid_tipolograd")),
                        rs.getString("endResid_logradouro"),
                        rs.getInt("endResid_numero"),
                        new Bairro(rs.getString("endResid_bairro"),
                                new Cidade(rs.getString("endResid_cidade"),
                                        new Estado(rs.getString("endResid_estado"),
                                                new Pais(rs.getString("endResid_pais"))))),
                        rs.getString("endResid_cep"),
                        rs.getString("endResid_obs")
                ));
                // Endereço de Cobrança
                cliente.setEndCob(new Endereco("Cobranca",
                        TpResidencia.valueOf(rs.getString("endCob_tiporesid")),
                        TpLogradouro.valueOf(rs.getString("endCob_tipolograd")),
                        rs.getString("endCob_logradouro"),
                        rs.getInt("endCob_numero"),
                        new Bairro(rs.getString("endCob_bairro"),
                                new Cidade(rs.getString("endCob_cidade"),
                                        new Estado(rs.getString("endCob_estado"),
                                                new Pais(rs.getString("endCob_pais"))))),
                        rs.getString("endCob_cep"),
                        rs.getString("endCob_obs")
                ));
                // Contato
                cliente.setContato(new Contato(
                        rs.getString("contato_email"),
                        new Telefone(
                                TpTelefone.valueOf(rs.getString("contato_tipotel")),
                                rs.getInt("contato_ddd"),
                                Long.valueOf(rs.getString("contato_numerotel"))
                        )
                ));
                cliente.setStatus(rs.getBoolean("status"));
                cliente.setRanking(rs.getInt("ranking"));
                cliente.setSenhaFoiCriptografada(rs.getBoolean("criptografia"));

                clientes.add(cliente);
            }

        }
        Collections.sort(clientes, Comparator.comparing(c -> c.getNome()));
        return clientes;
    }

    public static List<Cliente> listar(String filtro) throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conexao = getConexao()) {
            String[] palavrasFiltro = filtro.toLowerCase().split("\\s+");
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM clientes WHERE ");

            for (int i = 0; i < palavrasFiltro.length; i++) {
                queryBuilder.append("(UNACCENT(nome) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(cpf) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(genero) ILIKE UNACCENT(?) OR ")
                        .append("CAST(dataNascimento AS TEXT) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_tiporesid) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_tipolograd) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_logradouro) ILIKE UNACCENT(?) OR ")
                        .append("CAST(endResid_numero AS TEXT) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_bairro) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_cidade) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_estado) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_pais) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_cep) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endResid_obs) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_tiporesid) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_tipolograd) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_logradouro) ILIKE UNACCENT(?) OR ")
                        .append("CAST(endCob_numero AS TEXT) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_bairro) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_cidade) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_estado) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_pais) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_cep) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(endCob_obs) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(contato_email) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(contato_tipotel) ILIKE UNACCENT(?) OR ")
                        .append("CAST(contato_ddd AS TEXT) ILIKE UNACCENT(?) OR ")
                        .append("UNACCENT(contato_numerotel) ILIKE UNACCENT(?))");
                if (i < palavrasFiltro.length - 1) {
                    queryBuilder.append(" AND ");
                }
            }

            PreparedStatement sql = conexao.prepareStatement(queryBuilder.toString());

            int paramIndex = 1;
            for (String palavra : palavrasFiltro) {
                String filtroLike = "%" + palavra + "%";
                for (int j = 0; j < 28; j++) {
                    sql.setString(paramIndex++, filtroLike);
                }
            }

            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setGenero(Genero.valueOf(rs.getString("genero")));
                cliente.setDataNascimento(rs.getDate("dataNascimento"));
                cliente.setSenha(rs.getString("senha"));
                // Endereço Residencial
                cliente.setEndResid(new Endereco("Residencial",
                        TpResidencia.valueOf(rs.getString("endResid_tiporesid")),
                        TpLogradouro.valueOf(rs.getString("endResid_tipolograd")),
                        rs.getString("endResid_logradouro"),
                        rs.getInt("endResid_numero"),
                        new Bairro(rs.getString("endResid_bairro"),
                                new Cidade (rs.getString("endResid_cidade"),
                                        new Estado(rs.getString("endResid_estado"),
                                                new Pais(rs.getString("endResid_pais"))))),
                        rs.getString("endResid_cep"),
                        rs.getString("endResid_obs")
                ));
                // Endereço de Cobrança
                cliente.setEndCob(new Endereco("Cobranca",
                        TpResidencia.valueOf(rs.getString("endCob_tiporesid")),
                        TpLogradouro.valueOf(rs.getString("endCob_tipolograd")),
                        rs.getString("endCob_logradouro"),
                        rs.getInt("endCob_numero"),
                        new Bairro(rs.getString("endCob_bairro"),
                                new Cidade(rs.getString("endCob_cidade"),
                                        new Estado(rs.getString("endCob_estado"),
                                                new Pais(rs.getString("endCob_pais"))))),
                        rs.getString("endCob_cep"),
                        rs.getString("endCob_obs")
                ));
                // Contato
                cliente.setContato(new Contato(
                        rs.getString("contato_email"),
                        new Telefone(
                                TpTelefone.valueOf(rs.getString("contato_tipotel")),
                                rs.getInt("contato_ddd"),
                                Long.valueOf(rs.getString("contato_numerotel"))
                        )
                ));
                cliente.setStatus(rs.getBoolean("status"));
                cliente.setRanking(rs.getInt("ranking"));
                cliente.setSenhaFoiCriptografada(rs.getBoolean("criptografia"));

                clientes.add(cliente);
            }
        }
        Collections.sort(clientes, Comparator.comparing(c -> c.getNome()));
        return clientes;
    }

    public void excluir(EntidadeDominio entidade) throws Exception {
        Cliente cliente = (Cliente) entidade;

        try (Connection conexao = getConexao()) {
            String query = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setInt(1, cliente.getId());
            sql.executeUpdate();
            removerCartoes(cliente.getId(),conexao);
            removerEnderecosEntrega(cliente.getId(),conexao);
        }


    }

}

