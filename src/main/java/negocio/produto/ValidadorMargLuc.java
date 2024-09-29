package negocio.produto;

import dominio.EntidadeDominio;
import dominio.produto.Vinho;
import negocio.IStrategy;

//RN0014
public class ValidadorMargLuc implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Vinho vinho = (Vinho) entidade;
        if(vinho.getPreco() == null) {
            vinho.setPreco(vinho.getValorMinimoVenda());
        }
        if(vinho.getPreco() < vinho.getValorMinimoVenda()) {
            String mensagem = "O valor de venda não pode ser inferior a R$" + vinho.getValorMinimoVenda();
            return mensagem;
        }
        return null;
    }
}
