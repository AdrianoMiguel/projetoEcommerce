package negocio.produto;

import dominio.EntidadeDominio;
import dominio.produto.Vinho;
import negocio.IStrategy;

import java.text.DecimalFormat;

//RN0014
public class ValidadorMargLuc implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Vinho vinho = (Vinho) entidade;
        DecimalFormat df = new DecimalFormat("0.00");
        if(vinho.getPreco() == null) {
            vinho.setPreco(vinho.getValorMinimoVenda());
        }
        if(vinho.getPreco() < vinho.getValorMinimoVenda()) {
            String mensagem = "Pelo vinho pertencer ao grupo de precificação " + vinho.getGrupoPrecificacao().toString() + ", o valor de venda não pode ser inferior a R$" + df.format(vinho.getValorMinimoVenda());
            return mensagem;
        }
        return null;
    }
}
