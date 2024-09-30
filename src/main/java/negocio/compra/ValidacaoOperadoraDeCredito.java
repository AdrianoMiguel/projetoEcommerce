package negocio.compra;
import controle.Fachada;
import controle.IFachada;
import dominio.EntidadeDominio;
import dominio.compra.Compra;
import dominio.compra.Status;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import persistencia.CompraDAO;

import java.util.Random;

public class ValidacaoOperadoraDeCredito implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        IFachada fachada = new Fachada();
        Compra compra = (Compra) entidade;

        if (compra.getStatus() == Status.EM_PROCESSAMENTO) {
            Random random = new Random();
            int randomNumber = random.nextInt(100);

            if (randomNumber < 99) {
                compra.setStatus(Status.PAGAMENTO_APROVADO);
                try {
                    fachada.alterar(compra);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                compra.setStatus(Status.PAGAMENTO_REJEITADO);
                CompraDAO compraDAO = new CompraDAO();
                CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
                try {
                    carrinhoDAO.reporEstoque(compra.getCarrinho());
                    compraDAO.alterar(compra);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return null;
    }
}
