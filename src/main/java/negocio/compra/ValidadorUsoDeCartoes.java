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

public class ValidadorUsoDeCartoes implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        Double valorTotal = compra.getValorFinal();
        Double valorPago = 0.0;

        if (compra.getValorFinal() <= 0){
            if (compra.getPagamentos().size() > 0) {
                    return "Sua compra já resultará em crédito. Não é necessário realizar pagamentos com cartões.";
                }
            }
        return null;
    }
}
