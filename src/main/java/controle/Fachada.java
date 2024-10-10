package controle;


import dominio.*;
import dominio.cliente.Cliente;
import dominio.cliente.Cupom;
import dominio.cliente.Log;
import dominio.compra.Carrinho;
import dominio.compra.Compra;
import dominio.estoque.Estoque;
import dominio.produto.Vinho;
import negocio.*;
import negocio.cliente.*;
import negocio.compra.*;
import persistencia.*;

import java.util.*;


public class Fachada implements IFachada {
        private Map<String, IDAO> daos = new HashMap();
        private Map<String, List<IStrategy>> RegrasEReq = new HashMap();

    public Fachada() {
            this.daos.put(Cliente.class.getName(), new ClienteDAO());
            this.daos.put(Log.class.getName(), new LogDAO());
            this.daos.put(Vinho.class.getName(), new ProdutoDAO());
            this.daos.put(Estoque.class.getName(), new EstoqueDAO());
            this.daos.put(Carrinho.class.getName(), new CarrinhoDAO());
            this.daos.put(Cupom.class.getName(), new CupomDAO());
            this.daos.put(Compra.class.getName(), new CompraDAO());

            List<IStrategy> rnClientes = new ArrayList();
            rnClientes.add(new ValidadorDadosObg());
            rnClientes.add(new ValidadorFraseCurtaEndereco());
            rnClientes.add(new ValidadorSenhaForte());
            rnClientes.add(new CriptografiaDeSenha());
            rnClientes.add(new GeradorDescricaoAlteracoes());

            List<IStrategy> rnLogs = new ArrayList();
            rnLogs.add(new GeradorLog());

            List<IStrategy> rnVinhos = new ArrayList();

            List<IStrategy> rnEstoque = new ArrayList();

            List<IStrategy> rnCarrinho = new ArrayList();

            List<IStrategy> rnCupom = new ArrayList();

            List<IStrategy> rnCompra = new ArrayList();
            rnCompra.add(new ValidadorUsoDeCartoes());
            rnCompra.add(new ValidadorPagamentoTotal());
            rnCompra.add(new ValidadorDeUsoDeCupons());
            rnCompra.add(new ValorMinimoCartaoComCupons());
            rnCompra.add(new AtualizadorDeCarrinho());
            rnCompra.add(new AtualizadorDeEstoque());
            rnCompra.add(new AtualizadorDeCupons());
            rnCompra.add(new GeradorCupomDeTroca());
            rnCompra.add(new RankeadorDeCliente());

            this.RegrasEReq.put(Log.class.getName(), rnLogs);
            this.RegrasEReq.put(Cliente.class.getName(), rnClientes);
            this.RegrasEReq.put(Vinho.class.getName(), rnVinhos);
            this.RegrasEReq.put(Estoque.class.getName(), rnEstoque);
            this.RegrasEReq.put(Carrinho.class.getName(), rnCarrinho);
            this.RegrasEReq.put(Cupom.class.getName(), rnCupom);
            this.RegrasEReq.put(Compra.class.getName(), rnCompra);

        }

        public String salvar(EntidadeDominio entidade) throws Exception {

            String msgRetorno = this.validar(entidade);
            if (msgRetorno == null) {
                IDAO dao = this.daos.get(entidade.getClass().getName());
                dao.salvar(entidade);
                return "Registro realizado com sucesso!";
            } else {
                return msgRetorno;
            }
        }

        private String validar(EntidadeDominio entidade) {
            String nmClasse = entidade.getClass().getName();
            List<IStrategy> regras = this.RegrasEReq.get(nmClasse);
            StringBuilder sb = new StringBuilder();
            Iterator var6 = regras.iterator();

            do {
                if (!var6.hasNext()) {
                    return null;
                }

                IStrategy s = (IStrategy)var6.next();
                String msg = s.processar(entidade);
                if (msg != null) {
                    sb.append(msg);
                }
            } while(sb.length() == 0);

            return sb.toString();
        }


    @Override
    public String alterar(EntidadeDominio entidade) throws Exception{
        String msgRetorno = this.validar(entidade);
        if (msgRetorno == null) {
            IDAO dao = this.daos.get(entidade.getClass().getName());
            dao.alterar(entidade);
            return "Registro atualizado com sucesso!";
        } else {
            return msgRetorno;
        }
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        IDAO dao = this.daos.get(entidade.getClass().getName());
        try {
            dao.excluir(entidade);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Registro Excluido com sucesso!";
    }

    public List<Cliente> consultarClientes(String filtro) {
        List<Cliente> clientes = null;
        try {
            clientes = ClienteDAO.listar(filtro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
    public List<Log> consultarLogs(Integer clienteId) {
        try {
            LogDAO logDAO = new LogDAO();
            List<Log> logs = logDAO.listar(clienteId);
            return logs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Vinho> consultarVinhos(String filtro) {
        List<Vinho> vinhos = null;
        try {
            vinhos = ProdutoDAO.listar(filtro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vinhos;
    }

    public List<Vinho> listarVinhos() throws Exception {
        List<Vinho> vinhos = null;
            vinhos = ProdutoDAO.listar();

        return vinhos;
    }
    public List<Cliente> listarClientes() throws Exception {
        List<Cliente> clientes = null;

            clientes = ClienteDAO.listar();

        return clientes;
    }

}