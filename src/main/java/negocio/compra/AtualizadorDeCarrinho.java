package negocio.compra;
import dominio.EntidadeDominio;
import dominio.compra.Compra;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;

public class AtualizadorDeCarrinho implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;
        CarrinhoDAO carrinhoDao = new CarrinhoDAO();
        try {
            carrinhoDao.alterar(compra.getCarrinho());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
