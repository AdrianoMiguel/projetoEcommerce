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

public class ValorMinimoCartaoComCupons implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;

        if (compra.getValorFinal() > 0) {

            if (compra.getCupons().size() > 0 && compra.getValorFinal() < 10) {
                for (Pagamento pagamento : compra.getPagamentos()) {
                    if (pagamento.getValor() < compra.getValorFinal()) {
                        String valorFinal = String.format("%.2f", compra.getValorFinal());
                        return "O valor mínimo para pagamento com cartão é de R$" + valorFinal + ".";
                    }
                }
            } else {
                ValorMinimoCartao valMinCartao = new ValorMinimoCartao();
                return valMinCartao.processar(entidade);
            }
        }
        return null;
    }
}
