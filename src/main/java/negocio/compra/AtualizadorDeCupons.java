package negocio.compra;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Cupom;
import dominio.compra.Compra;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import dominio.produto.Vinho;
import persistencia.ClienteDAO;
import persistencia.CupomDAO;
import persistencia.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class AtualizadorDeCupons implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        CupomDAO cupomDAO = new CupomDAO();
        for (int i = 0; i < compra.getCupons().size(); i++) {
            Cupom cupom = compra.getCupons().get(i);
            try {
                cupomDAO.alterar(cupom);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
