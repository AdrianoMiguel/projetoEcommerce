package negocio.compra;
import dominio.EntidadeDominio;
import dominio.cliente.Cartao;
import dominio.cliente.Cliente;
import dominio.cliente.Cupom;
import dominio.compra.Compra;
import dominio.compra.Pagamento;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import dominio.produto.Vinho;
import persistencia.ClienteDAO;
import persistencia.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class ValorMinimoCartao implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        for (Pagamento pagamento : compra.getPagamentos()) {
            if (pagamento.getValor() < 10) {
                return "O valor mínimo para pagamento com cartão é de R$10,00.";
            }
        }
        return null;
    }
}
