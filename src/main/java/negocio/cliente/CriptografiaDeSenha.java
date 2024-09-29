package negocio.cliente;
import dominio.EntidadeDominio;
import dominio.cliente.*;
import negocio.IStrategy;
import utils.Criptografia;

public class CriptografiaDeSenha implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        if (!cliente.getSenhaFoiCriptografada()) {
            cliente.setSenha(Criptografia.criptografar(cliente.getSenha()));
            cliente.setSenhaFoiCriptografada(true);

        }
        return null;
    }
}
