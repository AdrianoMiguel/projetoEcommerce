package negocio.produto;

import dominio.EntidadeDominio;

import dominio.produto.*;
import negocio.IStrategy;

public class ValidadorAssociacaoMotivoInativacao implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
            Vinho vinho = (Vinho) entidade;

            if(!vinho.getStatus()){
                if (vinho.getMotivo().getCategoria() == MotivoCategoria.Motivo) {
                    return "Todo vinho inativo deve ter um motivo associado";
                }
                if (vinho.getMotivo().getJustificativa().isBlank()) {
                    return "Todo vinho inativo deve ter uma justificativa do motivo associado";
                }
            }
            return "";
        }
    }

