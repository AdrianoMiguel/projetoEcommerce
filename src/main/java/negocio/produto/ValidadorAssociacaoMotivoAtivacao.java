package negocio.produto;

import dominio.EntidadeDominio;
import dominio.produto.*;
import negocio.IStrategy;

public class ValidadorAssociacaoMotivoAtivacao implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Vinho vinho = (Vinho) entidade;

        if(vinho.getStatus()){
            if (vinho.getMotivo().getCategoria().equals(MotivoCategoria.Motivo)) {
                return "Todo vinho ativo deve ter um motivo associado";
            }
            if (vinho.getMotivo().getJustificativa().isBlank()) {
                return "Todo vinho ativo deve ter uma justificativa do motivo associado";
            }
        }
        return "";
    }
}
