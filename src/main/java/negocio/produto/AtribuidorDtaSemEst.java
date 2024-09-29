package negocio.produto;

import dominio.EntidadeDominio;
import dominio.produto.Vinho;
import negocio.IStrategy;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AtribuidorDtaSemEst implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Vinho vinho = (Vinho) entidade;
        if (vinho.getQtdeEstoque() == 0) { //
            vinho.setDataSemEstoque(Timestamp.valueOf(LocalDateTime.now()));
            return null;
}
        if (vinho.getQtdeEstoque() > 0) { //
            vinho.setDataSemEstoque(null);
        }
        return null;
    }
}

