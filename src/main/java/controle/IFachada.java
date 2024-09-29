package controle;

import dominio.*;


import java.util.List;

public interface IFachada {
    public String salvar(EntidadeDominio entidade) throws Exception;

    public String alterar(EntidadeDominio entidade) throws Exception;

    public String excluir(EntidadeDominio entidade);
}