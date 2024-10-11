package negocio.compra;
import controle.IFachada;
import dominio.EntidadeDominio;
import dominio.cliente.Cupom;
import dominio.compra.Compra;
import dominio.compra.Item;
import dominio.compra.Status;
import negocio.IStrategy;
import persistencia.CarrinhoDAO;
import dominio.produto.Vinho;
import persistencia.ProdutoDAO;
import controle.Fachada;

import java.util.ArrayList;
import java.util.List;

public class GeradorCupomDeTroca implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Compra compra = (Compra) entidade;


        if (compra.getStatus().equals(Status.TROCADO)) {
            IFachada fachada = new Fachada();

                Cupom cupom = new Cupom(compra.getClienteId(), "TROCA" + compra.getCarrinho().getId(), compra.getCarrinho().getTotal());

                try {
                    fachada.salvar(cupom);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
            if (compra.getValorFinal()<0) {

                Cupom cupom = new Cupom(compra.getClienteId(),"CREDITO"+ compra.getCarrinho().getId(),compra.getValorFinal() * -1);
                IFachada fachada = new Fachada();

                try {
                    String mensagem = fachada.salvar(cupom);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }
}
