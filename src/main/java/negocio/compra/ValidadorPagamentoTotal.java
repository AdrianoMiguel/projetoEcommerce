package negocio.compra;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.compra.Compra;
import dominio.compra.Status;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import dominio.produto.Vinho;
import persistencia.ClienteDAO;
import persistencia.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPagamentoTotal implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        Double valorTotal = compra.getValorFinal();
        Double valorPago = 0.0;

        if (compra.getValorFinal() > 0){

            for (int i = 0; i < compra.getPagamentos().size(); i++) {
                if (compra.getPagamentos().get(i).getValor() == null) {
                    return "Favor informar o valor de pagamento.";
                } else {
                    valorPago += compra.getPagamentos().get(i).getValor();
                }
            }
            if (!valorPago.equals(valorTotal)) {
                return "O valor dos meios de pagamento não confere com o valor total da compra! Por favor, verifique os valores e tente novamente.";
            } else {
                return null;
            }
        } else {
            compra.setStatus(Status.PAGAMENTO_APROVADO);
        }

        return null;
    }
}
