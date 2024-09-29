package persistencia;

import dominio.*;

import java.util.List;

public interface IDAO {
    void salvar(EntidadeDominio var1) throws Exception;

    void alterar(EntidadeDominio var1) throws Exception;

    void excluir(EntidadeDominio var1) throws Exception;
}
