package negocio.compra;
import dominio.EntidadeDominio;
import dominio.compra.Compra;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import dominio.produto.Vinho;
import persistencia.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class AtualizadorDeEstoque implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        List<Vinho> vinhos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        for (int i = 0; i < compra.getCarrinho().getItens().size(); i++) {
            vinhos.add(compra.getCarrinho().getItens().get(i).getProduto());
        }

        for (Vinho vinho : vinhos) {
            try {
                produtoDAO.alterar(vinho);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
