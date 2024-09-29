package negocio.compra;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.compra.Compra;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import dominio.produto.Vinho;
import persistencia.ClienteDAO;
import persistencia.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class RankeadorDeCliente implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        Cliente cliente = new Cliente(compra.getClienteId());
        cliente.setRanking(cliente.getRanking() + 1);
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            clienteDAO.alterar(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
}
}
