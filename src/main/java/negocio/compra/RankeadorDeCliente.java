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
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = null;
        try {
            cliente = ClienteDAO.buscarClientePorId(compra.getClienteId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cliente.setRanking(cliente.getRanking() + 1);
        try {
            clienteDAO.alterar(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
}
}
