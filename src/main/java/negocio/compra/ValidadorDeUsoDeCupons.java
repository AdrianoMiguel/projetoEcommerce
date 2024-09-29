package negocio.compra;
import controle.Fachada;
import controle.IFachada;
import dominio.EntidadeDominio;
import dominio.cliente.Cupom;
import dominio.compra.Compra;
import dominio.compra.Item;
import dominio.compra.Status;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import persistencia.CompraDAO;

import java.util.*;

public class ValidadorDeUsoDeCupons implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        Double valorCompra = compra.getFrete().getValor();
        for (Item item : compra.getCarrinho().getItens()) {
            valorCompra += item.getProduto().getPreco() * item.getQuantidade();
        }
        List<Cupom> cuponsOrdenadosDesc = new ArrayList<>();
        cuponsOrdenadosDesc = compra.getCupons();
        Collections.sort(cuponsOrdenadosDesc, Comparator.comparing(Cupom::getValor).reversed());



        if (compra.getValorFinal() < 0) {
            Double valorBruto = compra.getValorFinal();

            for (Cupom cupom : cuponsOrdenadosDesc) {
                if (valorBruto > (valorCompra + compra.getValorFinal())) {
                    return "Não é permitido o uso excessivo de cupons, quando a quantidade de cupons utilizados já supera o valor da compra.";
                }
                valorBruto += cupom.getValor();
            }
        }
    return null;
    }
}
