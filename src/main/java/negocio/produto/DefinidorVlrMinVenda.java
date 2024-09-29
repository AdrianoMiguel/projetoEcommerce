package negocio.produto;

import dominio.EntidadeDominio;
import dominio.produto.*;
import negocio.IStrategy;


//RN0013
public class DefinidorVlrMinVenda implements IStrategy {
    public String processar(EntidadeDominio entidade)
    {
        Vinho vinho = (Vinho) entidade;
        if(vinho.getGrupoPrecificacao().equals(Precificacao.A)) {
            vinho.setValorMinimoVenda(vinho.getMaiorCusto() * 1.2);
        }
        if(vinho.getGrupoPrecificacao().equals(Precificacao.B)) {
            vinho.setValorMinimoVenda(vinho.getMaiorCusto() * 1.4);
        }
        if(vinho.getGrupoPrecificacao().equals(Precificacao.C)) {
            vinho.setValorMinimoVenda(vinho.getMaiorCusto() * 1.6);
        }
        if(vinho.getGrupoPrecificacao().equals(Precificacao.D)) {
            vinho.setValorMinimoVenda(vinho.getMaiorCusto() * 1.8);
        }

        return null;
    }
}
